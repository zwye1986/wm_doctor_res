package com.pinde.res.ctrl.hzyy;

import com.pinde.app.common.NfyyGlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.hzyy.IHzyyAppBiz;
import com.pinde.res.biz.hzyy.IHzyyStudentBiz;
import com.pinde.res.biz.hzyy.IHzyyTutorBiz;
import com.pinde.res.ctrl.upload.FileForm;
import com.pinde.sci.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/res/hzyy/tutor")
public class HzyyTutorController {
	
    private static Logger logger = LoggerFactory.getLogger(HzyyTutorController.class);
    
    private boolean alert = true;
    
    @Autowired
    private IHzyyAppBiz hzyyAppBiz;
    @Autowired
    private IHzyyStudentBiz hzyyStudentBiz;
	@Autowired
	private IHzyyTutorBiz tutorBiz;


	@Value("#{configProperties['hzyy.upload.base.dir']}")
	public  String baseDir;
	@Value("#{configProperties['hzyy.upload.dir']}")
	public  String uploadDir;
	@Value("#{configProperties['hzyy.upload.base.url']}")
	public  String baseUrl;
	@Value("#{configProperties['hzyy.upload.all.suffix']}")
	public  String allFileSuf;
	@Value("#{configProperties['hzyy.upload.img.suffix']}")
	public  String imgSuf;
	@Value("#{configProperties['hzyy.upload.file.suffix']}")
	public  String fileSuf;

	//首页接口
	@RequestMapping(value={"/index"},method={RequestMethod.POST})
	public String index(String userFlow , String roleId ,String year, Model model){
		String result = "/res/hzyy/tutor/index";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_ROLEID_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_ROLEID_NULL);
			return result;
		}
		if(StringUtil.isBlank(year)){
			model.addAttribute("resultId", "33023");
			model.addAttribute("resultType", "年份为空");
			return result;
		}

		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy");
		SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		try
		{
			Date date = dateFormat2.parse(year);
			c.setTime(date);

		}
		catch (Exception e)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "年份格式错误");
			return result;
		}
		String checkResult=checkUploadCfg();
		if(StringUtil.isNotBlank(checkResult))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", checkResult);
			return result;
		}
		//导师年度开始月份
		Map<String,String> map=tutorBiz.readDicItemByName("导师年度开始月份");
		if(map==null||StringUtil.isBlank(map.get("DicItem")))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请联系系统管理员，配置导师年度开始月份");
			return result;
		}
		//导师年度结束月份
		Map<String,String> map2=tutorBiz.readDicItemByName("导师年度结束月份");
		if(map2==null||StringUtil.isBlank(map2.get("DicItem")))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请联系系统管理员，配置导师年度结束月份");
			return result;
		}
		Integer startMonth=Integer.valueOf(map.get("DicItem"));
		c.set(Integer.valueOf(year),startMonth-1,1);
		//开始月份
		String sm=dateFormat3.format(c.getTime());
		Integer endMonth=Integer.valueOf(map2.get("DicItem"));
		if(endMonth<startMonth)
		{
			c.set(Integer.valueOf(year)+1,endMonth-1,1);
		}else {
			c.set(Integer.valueOf(year),endMonth-1,1);
		}
		//结束月份
		String em=dateFormat3.format(c.getTime());

		List<String>times= DateTimeUtil.getMonthsByTwoMonth(sm,em);

		Map<String , Object> searchMap =new HashMap<>();
		searchMap.put("userFlow",userFlow);
		searchMap.put("startTime",DateUtil.setFirstDayOfMonth(sm));
		searchMap.put("endTime",DateUtil.setFirstDayOfMonth(em));
		searchMap.put("type","0");//0 为学生见面照片 2学员培训计划 1为工作总结
		searchMap.put("pageSize",Integer.MAX_VALUE);
		searchMap.put("pageIndex",1);

		//学生见面照片
		List<Map<String, String>> stuImgList =tutorBiz.stuSchImgList(searchMap);
		model.addAttribute("stuImgList", stuImgList);

		//学员培训计划
		searchMap.put("type","2");
		searchMap.put("endTime",DateUtil.setLastDateOfMonth(em));
		searchMap.put("pageSize",3);
		searchMap.put("pageIndex",1);
		List<Map<String,String>> stuSchImgList=tutorBiz.stuSchImgList(searchMap);
		model.addAttribute("stuSchImgList", stuSchImgList);
		//全年
		searchMap.put("startTime",DateUtil.setFirstDayOfMonth(sm));
		searchMap.put("endTime",DateUtil.setLastDateOfMonth(em));
		searchMap.put("type","1");
		searchMap.put("pageSize",3);
		searchMap.put("pageIndex",1);
		List<Map<String,String>> allYearImgList=tutorBiz.stuSchImgList(searchMap);
		model.addAttribute("allYearImgList", allYearImgList);

		//上半年
//		String end=DateUtil.addMonth(em,-times.size()/2);
//		searchMap.put("startTime",DateUtil.setFirstDayOfMonth(sm));
//		searchMap.put("endTime",DateUtil.setLastDateOfMonth(end));
//		searchMap.put("type","1");
//		searchMap.put("pageSize",3);
//		searchMap.put("pageIndex",1);
//		List<Map<String,String>> preYearImgList=tutorBiz.stuSchImgList(searchMap);
//		model.addAttribute("preYearImgList", preYearImgList);

		//下半年
//		String start=DateUtil.addMonth(sm,times.size()/2);
//		searchMap.put("startTime",DateUtil.setFirstDayOfMonth(start));
//		searchMap.put("endTime",DateUtil.setLastDateOfMonth(em));
//		searchMap.put("type","1");
//		searchMap.put("pageSize",3);
//		searchMap.put("pageIndex",1);
//		List<Map<String,String>> afterYearImgList=tutorBiz.stuSchImgList(searchMap);
//		model.addAttribute("afterYearImgList", afterYearImgList);

		model.addAttribute("times", times);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		setUploadUrl(model);
		return result;
	}
	@RequestMapping(value={"/fileList"},method={RequestMethod.POST})
	public String fileList(String userFlow , String roleId ,String year,
						   Integer pageIndex,Integer pageSize,String dataType ,String tabType , Model model){
		String result = "/res/hzyy/tutor/fileList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_ROLEID_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_ROLEID_NULL);
			return result;
		}
		if(StringUtil.isBlank(year)){
			model.addAttribute("resultId", "33023");
			model.addAttribute("resultType", "年份为空");
			return result;
		}
		if(StringUtil.isBlank(dataType)){
			model.addAttribute("resultId", "33023");
			model.addAttribute("resultType", "dataType为空");
			return result;
		}
		if(!"stuImgList".equals(dataType)&&!"stuSchImgList".equals(dataType)
//				&&!"preYearImgList".equals(dataType)&&!"afterYearImgList".equals(dataType)
				&&!"allYearImgList".equals(dataType))
		{
			model.addAttribute("resultId", "33023");
			model.addAttribute("resultType", "dataType错误");
			return result;
		}
		if(!"stuImgList".equals(dataType))
		{
			if(StringUtil.isBlank(tabType)) {
				model.addAttribute("resultId", "33023");
				model.addAttribute("resultType", "tabType为空");
				return result;
			}
			if(!"image".equals(tabType)&&!"file".equals(tabType)) {
				model.addAttribute("resultId", "33023");
				model.addAttribute("resultType", "tabType错误");
				return result;
			}
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy");
		SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		try
		{
			Date date = dateFormat2.parse(year);
			c.setTime(date);

		}
		catch (Exception e)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "年份格式错误");
			return result;
		}
		String checkResult=checkUploadCfg();
		if(StringUtil.isNotBlank(checkResult))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", checkResult);
			return result;
		}
		//导师年度开始月份
		Map<String,String> map=tutorBiz.readDicItemByName("导师年度开始月份");
		if(map==null||StringUtil.isBlank(map.get("DicItem")))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请联系系统管理员，配置导师年度开始月份");
			return result;
		}
		//导师年度结束月份
		Map<String,String> map2=tutorBiz.readDicItemByName("导师年度结束月份");
		if(map2==null||StringUtil.isBlank(map2.get("DicItem")))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请联系系统管理员，配置导师年度结束月份");
			return result;
		}

		Integer startMonth=Integer.valueOf(map.get("DicItem"));
		c.set(Integer.valueOf(year),startMonth-1,1);
		//开始月份
		String sm=dateFormat3.format(c.getTime());
		Integer endMonth=Integer.valueOf(map2.get("DicItem"));
		if(endMonth<startMonth)
		{
			c.set(Integer.valueOf(year)+1,endMonth-1,1);
		}else {
			c.set(Integer.valueOf(year),endMonth-1,1);
		}
		//结束月份
		String em=dateFormat3.format(c.getTime());

		List<String>times= DateTimeUtil.getMonthsByTwoMonth(sm,em);


		Map<String , Object> searchMap =new HashMap<>();
		searchMap.put("userFlow",userFlow);
		searchMap.put("startTime",DateUtil.setFirstDayOfMonth(sm));
		searchMap.put("endTime",DateUtil.setFirstDayOfMonth(em));
		if("stuImgList".equals(dataType))
		{
			searchMap.put("type","0");//0 为学生见面照片 2学员培训计划 1为工作总结
			searchMap.put("pageSize",Integer.MAX_VALUE);
			searchMap.put("pageIndex",1);
			//学生见面照片
			Map<String,List<Map<String, String>>> monthStuImg=new HashMap<>();
			for(String month:times)
			{
				searchMap.put("dateMonth",month);
				List<Map<String, String>> stuImgList =tutorBiz.stuSchImgList(searchMap);
				monthStuImg.put(month,stuImgList);
			}
			model.addAttribute("times", times);
			model.addAttribute("monthStuImg", monthStuImg);

		}else{
			if(pageIndex==null){
				pageIndex = NfyyGlobalConstant.DEFAULT_INDEX_PAGE;
			}
			if(pageSize==null){
				pageSize = NfyyGlobalConstant.DEFAULT_PAGE_SIZE;
			}
			searchMap.put("pageSize",pageSize);
			searchMap.put("pageIndex",pageIndex);

			//学员培训计划
			if("stuSchImgList".equals(dataType))
			{
				searchMap.put("type","2");
				searchMap.put("endTime",DateUtil.setLastDateOfMonth(em));
			}
			//上半年
			if("preYearImgList".equals(dataType))
			{
				String end=DateUtil.addMonth(em,-times.size()/2);
				searchMap.put("startTime",DateUtil.setFirstDayOfMonth(sm));
				searchMap.put("endTime",DateUtil.setLastDateOfMonth(end));
				searchMap.put("type","1");
			}
			//下半年
			if("afterYearImgList".equals(dataType))
			{
				String start=DateUtil.addMonth(sm,times.size()/2);
				searchMap.put("startTime",DateUtil.setFirstDayOfMonth(start));
				searchMap.put("endTime",DateUtil.setLastDateOfMonth(em));
				searchMap.put("type","1");
			}
			if("allYearImgList".equals(dataType))
			{
				searchMap.put("startTime",DateUtil.setFirstDayOfMonth(sm));
				searchMap.put("endTime",DateUtil.setLastDateOfMonth(em));
				searchMap.put("type","1");
			}
			if("image".equals(tabType))
			{
				List<String> sufs=Arrays.asList(imgSuf.split(","));
				searchMap.put("sufs",sufs);
			}
			if("file".equals(tabType))
			{
				List<String> fileSufs=new ArrayList(Arrays.asList(fileSuf.split(",")));
				searchMap.put("sufs",fileSufs);
			}
			List<Map<String,String>> datas=tutorBiz.stuSchImgList(searchMap);
			model.addAttribute("datas", datas);

			searchMap.put("pageIndex",1);
			searchMap.put("pageSize",Integer.MAX_VALUE);
			int count = tutorBiz.stuSchImgList(searchMap).size();
			model.addAttribute("dataCount" , count);
		}

		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		setUploadUrl(model);
		return result;
	}

	@RequestMapping(value = {"/checkFile"}, method = {RequestMethod.POST})
	public String checkFile(String userFlow, String fileFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		String result="/res/hzyy/tutor/success";
		String checkResult=checkUploadCfg();
		if(StringUtil.isNotBlank(checkResult))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", checkResult);
			return result;
		}
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "01000301");
			model.addAttribute("resultType", "用户流水号为空");
			return result;
		}

		Map<String,String> fileMap=tutorBiz.readFile(fileFlow);
		if(fileMap==null|| StringUtil.isBlank(fileMap.get("fileUrl")))
		{
			model.addAttribute("resultId", "01000304");
			model.addAttribute("resultType", "文件信息不存在，请刷新列表页面！");
			return result;
		}
		String filePath = baseDir + "/" ;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		if(fileMap!=null&& StringUtil.isNotBlank(fileMap.get("fileUrl")))
		{
			filePath=filePath+fileMap.get("fileUrl");
		}
		File f = new File(filePath);
		if (!f.exists()) {
			model.addAttribute("resultId", "01000304");
			model.addAttribute("resultType", "文件信息不存在，请联系管理员！");
			return result;
		}
		return result;
	}

	@RequestMapping(value = {"/downFile"}, method = {RequestMethod.POST,RequestMethod.GET})
	public synchronized void downFile(String userFlow, String fileFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

		String filePath = baseDir + "/" ;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		Map<String,String> fileMap=tutorBiz.readFile(fileFlow);
		String fileName="未发现文件.png";
		if(fileMap!=null&& StringUtil.isNotBlank(fileMap.get("fileUrl")))
		{
			filePath=filePath+fileMap.get("fileUrl");
			fileName=fileMap.get("fileName")+fileMap.get("fileUrl").substring(fileMap.get("fileUrl").lastIndexOf("/")+1);
		}

        /*文件是否存在*/
		File downLoadFile = new File(filePath);
		byte[] data = null;
		long dataLength = 0;
		/*文件是否存在*/
		if (downLoadFile.exists()) {
			InputStream fis = new BufferedInputStream(new FileInputStream(downLoadFile));
			data = new byte[fis.available()];
			dataLength = downLoadFile.length();
			fis.read(data);
			fis.close();
		}
		fileName = new String(fileName.getBytes(), "ISO-8859-1");
		try {
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
			response.addHeader("Content-Length", "" + dataLength);
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			if (data != null) {
				outputStream.write(data);
			}
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {

		}
	}



	public static void main(String[] args) {
		String uuIdFilename = "sss.xls".substring( 0,"sss.xls".lastIndexOf(".")+1);
		//System.out.println(uuIdFilename);
		List<String> fileSufs=new ArrayList(Arrays.asList("jpg,gif,png,xls,doc,ppt,xlsx,docx,pptx,zip,rar,txt,pdf".split(",")));
		List<String> imgSufs=new ArrayList(Arrays.asList("jpg,gif,png".split(",")));

		Iterator<String> iterator = fileSufs.iterator();
		while(iterator.hasNext()){
			String integer = iterator.next();
			if(imgSufs.contains(integer))
				iterator.remove();   //注意这个地方
		}
		//System.out.println(fileSufs.toString());
	}
	@RequestMapping(value={"/addFile"},method={RequestMethod.POST})
	public String addFile(FileForm form, String userFlow,String roleId,String yearMonth, String dataType ,String tabType , String year, HttpServletRequest request, HttpServletResponse response, Model model){
		String result = "/res/hzyy/tutor/success";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_ROLEID_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_ROLEID_NULL);
			return result;
		}
		if(StringUtil.isBlank(year)){
			model.addAttribute("resultId", "33023");
			model.addAttribute("resultType", "年份为空");
			return result;
		}
		if(StringUtil.isBlank(dataType)){
			model.addAttribute("resultId", "33023");
			model.addAttribute("resultType", "dataType为空");
			return result;
		}
		MultipartFile uploadFile = form.getUploadFile();
		if(uploadFile==null)
		{
			model.addAttribute("resultId", "33023");
			model.addAttribute("resultType", "上传附件为空");
			return result;
		}
		String fileName = form.getFileName();
		if(StringUtil.isBlank(fileName))
		{
			model.addAttribute("resultId", "33023");
			model.addAttribute("resultType", "上传附件名称为空");
			return result;
		}
		String fileSuffx = fileName.substring( fileName.lastIndexOf(".")+1);
		if(!"stuImgList".equals(dataType)&&!"stuSchImgList".equals(dataType)
				&&!"preYearImgList".equals(dataType)&&!"afterYearImgList".equals(dataType)
				&&!"allYearImgList".equals(dataType))
		{
			model.addAttribute("resultId", "33023");
			model.addAttribute("resultType", "dataType错误");
			return result;
		}

		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy");
		SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		try
		{
			Date date = dateFormat2.parse(year);
			c.setTime(date);
		}
		catch (Exception e)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "年份格式错误");
			return result;
		}

		List<String> fileSufs=new ArrayList(Arrays.asList(fileSuf.split(",")));
		List<String> sufs=new ArrayList(Arrays.asList(imgSuf.split(",")));
		if(!"stuImgList".equals(dataType))
		{
			if(StringUtil.isBlank(tabType)) {
				model.addAttribute("resultId", "33023");
				model.addAttribute("resultType", "tabType为空");
				return result;
			}
			if(!"image".equals(tabType)&&!"file".equals(tabType)) {
				model.addAttribute("resultId", "33023");
				model.addAttribute("resultType", "tabType错误");
				return result;
			}

			if("image".equals(tabType))
			{
				if(!sufs.contains(fileSuffx.toLowerCase()))
				{
					model.addAttribute("resultId", "33023");
					model.addAttribute("resultType", "文件格式错误只能是"+imgSuf);
					return result;
				}
			}
			if("file".equals(tabType))
			{
				if(!fileSufs.contains(fileSuffx.toLowerCase()))
				{
					model.addAttribute("resultId", "33023");
					model.addAttribute("resultType", "文件格式错误只能是"+fileSufs.toString());
					return result;
				}
			}
		}else{

			if(!sufs.contains(fileSuffx.toLowerCase()))
			{
				model.addAttribute("resultId", "33023");
				model.addAttribute("resultType", "文件格式错误只能是"+imgSuf);
				return result;
			}
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String checkResult=checkUploadCfg();
		if(StringUtil.isNotBlank(checkResult))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", checkResult);
			return result;
		}
		//导师年度开始月份
		Map<String,String> map=tutorBiz.readDicItemByName("导师年度开始月份");
		if(map==null||StringUtil.isBlank(map.get("DicItem")))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请联系系统管理员，配置导师年度开始月份");
			return result;
		}
		//导师年度结束月份
		Map<String,String> map2=tutorBiz.readDicItemByName("导师年度结束月份");
		if(map2==null||StringUtil.isBlank(map2.get("DicItem")))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请联系系统管理员，配置导师年度结束月份");
			return result;
		}
		Integer startMonth=Integer.valueOf(map.get("DicItem"));
		c.set(Integer.valueOf(year),startMonth-1,1);
		//开始月份
		String sm=dateFormat3.format(c.getTime());
		Integer endMonth=Integer.valueOf(map2.get("DicItem"));
		if(endMonth<startMonth)
		{
			c.set(Integer.valueOf(year)+1,endMonth-1,1);
		}else {
			c.set(Integer.valueOf(year),endMonth-1,1);
		}
		//结束月份
		String em=dateFormat3.format(c.getTime());
		List<String>times= DateTimeUtil.getMonthsByTwoMonth(sm,em);

		Map<String , Object> param =new HashMap<>();
		param.put("fileSuffx",fileSuffx);
		String fileUrl = fileName.substring(0, fileName.lastIndexOf("."))+ PkUtil.getUUID()+"."+fileSuffx;
		param.put("fileName",fileName.substring(0, fileName.lastIndexOf(".")));
		param.put("fileUrl",uploadDir+fileUrl);
		param.put("userFlow",userFlow);
		param.put("startTime",DateUtil.setFirstDayOfMonth(sm));
		param.put("endTime",DateUtil.setFirstDayOfMonth(em));
		if("stuImgList".equals(dataType))
		{
			if(StringUtil.isBlank(yearMonth)) {
				model.addAttribute("resultId", "33023");
				model.addAttribute("resultType", "yearMonth为空");
				return result;
			}
			try
			{
				dateFormat3.parse(yearMonth);
			}
			catch (Exception e)
			{
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "yearMonth格式错误");
				return result;
			}
			param.put("type","0");//0 为学生见面照片 2学员培训计划 1为工作总结
			//学生见面照片
			yearMonth=DateUtil.setFirstDayOfMonth(yearMonth);
		}else{
			//学员培训计划
			if("stuSchImgList".equals(dataType))
			{
				param.put("type","2");
				yearMonth=DateUtil.setFirstDayOfMonth(sm);
			}
			//全年
			if("allYearImgList".equals(dataType))
			{
				yearMonth=DateUtil.setFirstDayOfMonth(sm);
				param.put("type","1");
			}
			//上半年
			if("preYearImgList".equals(dataType))
			{
				yearMonth=DateUtil.setFirstDayOfMonth(sm);
				param.put("type","1");
			}
			//下半年
			if("afterYearImgList".equals(dataType))
			{
				String start=DateUtil.addMonth(sm,times.size()/2);
				yearMonth=DateUtil.setFirstDayOfMonth(start);
				param.put("type","1");
			}
		}
		param.put("yearMonth",yearMonth);

		int count= tutorBiz.saveFile(form,param);
		if(count==0)
		{
			model.addAttribute("resultId", "31703");
			model.addAttribute("resultType", "保存失败！!");
			return result;
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		setUploadUrl(model);
		return result;
	}
	@RequestMapping(value={"/delFile"},method={RequestMethod.POST})
	public String delFile(String userFlow , String roleId ,String fileFlow, Model model){
		String result = "/res/hzyy/tutor/success";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_ROLEID_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_ROLEID_NULL);
			return result;
		}
		if(StringUtil.isBlank(fileFlow)){
			model.addAttribute("resultId", "33023");
			model.addAttribute("resultType", "文件流水号为空");
			return result;
		}
		int c=tutorBiz.delFile(fileFlow);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	private void setUploadUrl(Model model) {
		model.addAttribute("baseUrl",baseUrl);
	}

	private String checkUploadCfg() {
		if(StringUtil.isBlank(baseDir))
		{
			return "请联系系统管理员，配置文件上传路径！";
		}

		if(StringUtil.isBlank(uploadDir))
		{
			return "请联系系统管理员，配置文件上传保存路径！";
		}
		if(StringUtil.isBlank(baseUrl))
		{
			return "请联系系统管理员，配置访问路径！";
		}
		if(StringUtil.isBlank(fileSuf))
		{
			return "请联系系统管理员，配置上传文件后缀！";
		}
		if(StringUtil.isBlank(imgSuf))
		{
			return "请联系系统管理员，配置图片文件后缀！";
		}
		return "";
	}

	/**
	 * 个人信息
	 * @param userFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/ownerInfo"},method={RequestMethod.GET})
	public String ownerInfo(String userFlow , Model model){
		String result = "/res/hzyy/tutor/ownerInfo";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}

		List<Map<String,String>> roles=hzyyAppBiz.readUserRoles(userFlow);
		model.addAttribute("roles", roles);
		return result;
	}

}
