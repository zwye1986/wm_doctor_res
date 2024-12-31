package com.pinde.sci.ctrl.res;

import com.pinde.core.common.enums.DeptActivityItemTypeEnum;
import com.pinde.core.model.PubFile;
import com.pinde.core.model.SysDept;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDeptActivityBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.core.model.QingpuLectureCfgItemExt;
import com.pinde.core.model.QingpuLectureCfgTitleExt;
import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.SysDeptMonthPlanItem;
import com.pinde.core.model.SysDeptMonthPlanItemEval;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/res/deptActivityStatistics")
public class ResDeptActivityStatisticsController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResDeptActivityStatisticsController.class);

	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private ISchArrangeResultBiz resultBiz;
	@Autowired
	private IFileBiz pubFileBiz;
	@Autowired
	private IResDeptActivityBiz deptActivityBiz;
	@Autowired
	private IFileBiz fileBiz;

	@RequestMapping("/main/{role}/{planTypeId}")
	public String info( @PathVariable String planTypeId,@PathVariable String role,Model model){
		model.addAttribute("planTypeId",planTypeId);
		model.addAttribute("role",role);

		SysUser user = GlobalContext.getCurrentUser();
		if("admin".equals(role)) {
			String orgFlow = user.getOrgFlow();
			List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);
			model.addAttribute("deptList", deptList);
		}
		if("doctor".equals(role)) {

		}
		if("head".equals(role)) {
			List<Map<String, Object>> deptList = deptBiz.queryDeptListByFlow(user.getUserFlow());
			model.addAttribute("deptList",deptList);
		}
		return "res/deptActivityStatistics/main";
	}

	@RequestMapping(value="/lists")
	public String list(Model model,Integer currentPage ,HttpServletRequest request,
					   String deptFlow,String planDate,String isEval,String role,String itemTypeId){
		SysUser sysuser=GlobalContext.getCurrentUser();
		Map<String,Object> param=new HashMap<>();
		param.put("userFlow",sysuser.getUserFlow());
		if("doctor".equals(role))
		{
			ResDoctor doctor=doctorBiz.readDoctor(sysuser.getUserFlow());
			if(doctor==null)
			{
				return "res/deptActivityStatistics/list";
			}
			param.put("orgFlow", doctor.getOrgFlow());
		}else {
			param.put("orgFlow", sysuser.getOrgFlow());
		}
		param.put("deptFlow",deptFlow);
		param.put("role",role);
		param.put("planDate",planDate);
		param.put("itemTypeId",itemTypeId);
		param.put("isEval",isEval);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> list=deptActivityBiz.searchStatisticList(param);
		model.addAttribute("list",list);

		Map<String, PubFile> fileMap = new HashMap<String, PubFile>();
		if(list!=null)
		{
			for(Map<String,Object> r:list)
			{
				String itemFlow= (String) r.get("itemFlow");
				PubFile file = fileBiz.readFile(itemFlow);
                if (file != null && com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(file.getRecordStatus()))
					fileMap.put(itemFlow, file);
			}
		}
		model.addAttribute("role",role);
		model.addAttribute("fileMap",fileMap);
		for(DeptActivityItemTypeEnum e :DeptActivityItemTypeEnum.values())
		{
			if(e.getId().equals(itemTypeId))
			{
				model.addAttribute("itemType",e);
			}
		}
		return "res/deptActivityStatistics/list";
	}

	@RequestMapping(value={"/saveFile"})
	@ResponseBody
	public Object saveFile(MultipartFile file, String itemFlow) throws Exception{
		Map<String,String> map=new HashMap<>();
		String result = fileBiz.addDeptFile(file, itemFlow);
		if(result != "fail"){
            map.put("result", com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED);
			map.put("fileFlow",result);
		}else{
            map.put("result", com.pinde.core.common.GlobalConstant.UPLOAD_FAIL);
			map.put("fileFlow",result);
		}
		return map;
	}
	@RequestMapping(value={"/delFile"})
	@ResponseBody
	public Object delFile(String fileFlow) throws Exception{
		PubFile file=fileBiz.readFile(fileFlow);
		if(file!=null)
		{
            file.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
			int l=fileBiz.editFile(file);
			if(l==1)
			{
                return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
	}
	//下载附件
	@RequestMapping(value = {"/downFile" }, method = RequestMethod.GET)
	public void fileDown(String fileFlow, final HttpServletResponse response) throws Exception{
		PubFile file = this.fileBiz.readFile(fileFlow);
		downPubFile(file,response);
	}
	public void downPubFile(PubFile file, HttpServletResponse response) throws Exception {
		/*文件是否存在*/
		Boolean fileExists = false;
		if(file !=null){
			byte[] data=null;
			long dataLength = 0;
            /*如果上传类型为“1”(文件保存在磁盘)，且文件相对路径不为空*/
			if(StringUtil.isNotBlank(file.getFilePath())&&StringUtil.isNotBlank(InitConfig.getSysCfg("upload_base_dir"))){
                /*获取文件物理路径*/
				String filePath =InitConfig.getSysCfg("upload_base_dir")+file.getFilePath();
				File downLoadFile = new File(filePath);
                /*文件是否存在*/
				if(downLoadFile.exists()){
					InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
					data = new byte[fis.available()];
					dataLength = downLoadFile.length();
					fis.read(data);
					fis.close();
					fileExists = true;
				}
			}
			if(fileExists) {
				try {

					String fileName = file.getFileName();
					response.reset();
					response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gbk"),"ISO8859-1" )  + "\"");
					response.addHeader("Content-Length", "" + dataLength);
					response.setContentType("application/octet-stream;charset=UTF-8");
					OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
					if (data != null) {
						outputStream.write(data);
					}
					outputStream.flush();
					outputStream.close();
				}catch (IOException e){
					fileExists = false;
				}
			}
		}else {
			fileExists = false;
		}
		if(!fileExists){
            /*设置页面编码为UTF-8*/
			response.setHeader("Content-Type","text/html;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			outputStream.write("<a href='javascript:history.go(-1)'>未发现文件,点击返回上一页</a>".getBytes("UTF-8"));//将字符串转化为一个字节数组（以UTF-8编码格式，默认本地编码）
			outputStream.flush();
			outputStream.close();
		}
	}

	@RequestMapping(value="/showEvalAll")
	public String showEvalAll(Model model,String itemFlow) throws Exception {
		SysDeptMonthPlanItem item=deptActivityBiz.readPlanItem(itemFlow);
		int all=0;
		if(item!=null)
		{
			if(StringUtil.isNotBlank(item.getEvalCfg()))
			{
				Document dom = DocumentHelper.parseText(item.getEvalCfg());
				String titleXpath = "//title";
				List<Element> titleElementList = dom.selectNodes(titleXpath);
				if(titleElementList != null && !titleElementList.isEmpty()){
					List<QingpuLectureCfgTitleExt> titleFormList = new ArrayList<QingpuLectureCfgTitleExt>();
					for(Element te :titleElementList){
						QingpuLectureCfgTitleExt titleForm = new QingpuLectureCfgTitleExt();
						titleForm.setId(te.attributeValue("id"));
						titleForm.setName(te.attributeValue("name"));
						titleForm.setOrder(te.attributeValue("order"));
						List<Element> itemElementList = te.elements("item");
						List<QingpuLectureCfgItemExt> itemFormList = null;
						if(itemElementList != null && !itemElementList.isEmpty()){
							itemFormList = new ArrayList<QingpuLectureCfgItemExt>();
							int sum=0;
							for(Element ie : itemElementList){
								QingpuLectureCfgItemExt itemForm = new QingpuLectureCfgItemExt();
								itemForm.setId(ie.attributeValue("id"));
								itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
								String score=ie.element("score") == null ? "" : ie.element("score").getTextTrim();
								itemForm.setScore(score);
								itemForm.setOrder(ie.element("order") == null ? "" : ie.element("order").getTextTrim());
								itemFormList.add(itemForm);
								if(StringUtil.isNotBlank(score))
								{
									sum+=Double.parseDouble(score);
									all+=Double.parseDouble(score);
								}
							}
							titleForm.setScore(String.valueOf(sum));
						}
						titleForm.setItemList(itemFormList);
						titleFormList.add(titleForm);
					}
					model.addAttribute("titleFormList", titleFormList);
				}
			}
		}
		model.addAttribute("all",all);
		model.addAttribute("item",item);
		List<SysDeptMonthPlanItemEval> evals=deptActivityBiz.readPlanItemEvals(itemFlow);
		Map<String,Float> scoreMap = new HashMap<String, Float>();
		Map<String,Map<String, Object>> gradeAllMap = new HashMap<String, Map<String, Object>>();
		if(evals!=null)
		{
			for(SysDeptMonthPlanItemEval eval:evals)
			{
				String content = eval.getEvalFormContent();
				String evalFlow = eval.getEvalFlow();
				Map<String, Object> gradeMap = parseGradeXml(content);
				gradeAllMap.put(evalFlow,gradeMap);
				if(gradeMap!=null && !gradeMap.isEmpty()){
					for(String gk : gradeMap.keySet()){
						Object o = gradeMap.get(gk);
						Float score = 0f;
						if(o instanceof Map){
							Map<String,String> dataMap = (Map<String, String>) o;
							if(dataMap!=null){
								try {
									String scoreS = dataMap.get("score");
									score = Float.valueOf(scoreS);
								} catch (Exception e) {
                                    logger.error("", e);
								}
								putMapVal(scoreMap,evalFlow+gk,score);
							}
						}else{
							if(gk.equals("totalScore")) {
								try {
									String scoreS = (String) gradeMap.get("totalScore");
									score = Float.valueOf(scoreS);
								} catch (Exception e) {
                                    logger.error("", e);
								}
								putMapVal(scoreMap, evalFlow, score);
							}else{

							}
						}
					}

				}
			}
		}
		model.addAttribute("gradeAllMap",gradeAllMap);
		model.addAttribute("scoreMap",scoreMap);
		model.addAttribute("evals",evals);
		return "res/deptActivityStatistics/showEvalAll";
	}
	@RequestMapping(value="/showEval")
	public String showEval(Model model,String itemFlow,String evalFlow) throws Exception {
		SysDeptMonthPlanItem item=deptActivityBiz.readPlanItem(itemFlow);
		SysDeptMonthPlanItemEval eval=deptActivityBiz.readPlanItemEval(evalFlow);
		if(eval==null)
		{
			eval=deptActivityBiz.readPlanItemEvalByUser(itemFlow,GlobalContext.getCurrentUser().getUserFlow());
		}
		model.addAttribute("item",item);
		model.addAttribute("eval",eval);
		ResDoctor doctor=doctorBiz.readDoctor(GlobalContext.getCurrentUser().getUserFlow());
		model.addAttribute("doctor",doctor);
		model.addAttribute("user",GlobalContext.getCurrentUser());
		int all=0;
		if(item!=null)
		{
			SysUser user=userBiz.readSysUser(item.getJoinUserFlow());
			model.addAttribute("joinUser",user);
			if(StringUtil.isNotBlank(item.getEvalCfg()))
			{
				Document dom = DocumentHelper.parseText(item.getEvalCfg());
				String titleXpath = "//title";
				List<Element> titleElementList = dom.selectNodes(titleXpath);
				if(titleElementList != null && !titleElementList.isEmpty()){
					List<QingpuLectureCfgTitleExt> titleFormList = new ArrayList<QingpuLectureCfgTitleExt>();
					for(Element te :titleElementList){
						QingpuLectureCfgTitleExt titleForm = new QingpuLectureCfgTitleExt();
						titleForm.setId(te.attributeValue("id"));
						titleForm.setName(te.attributeValue("name"));
						titleForm.setOrder(te.attributeValue("order"));
						List<Element> itemElementList = te.elements("item");
						List<QingpuLectureCfgItemExt> itemFormList = null;
						if(itemElementList != null && !itemElementList.isEmpty()){
							itemFormList = new ArrayList<QingpuLectureCfgItemExt>();
							int sum=0;
							for(Element ie : itemElementList){
								QingpuLectureCfgItemExt itemForm = new QingpuLectureCfgItemExt();
								itemForm.setId(ie.attributeValue("id"));
								itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
								String score=ie.element("score") == null ? "" : ie.element("score").getTextTrim();
								itemForm.setScore(score);
								itemForm.setOrder(ie.element("order") == null ? "" : ie.element("order").getTextTrim());
								itemFormList.add(itemForm);
								if(StringUtil.isNotBlank(score))
								{
									sum+=Double.parseDouble(score);
									all+=Double.parseDouble(score);
								}
							}
							titleForm.setScore(String.valueOf(sum));
						}
						titleForm.setItemList(itemFormList);
						titleFormList.add(titleForm);
					}
					model.addAttribute("titleFormList", titleFormList);
				}
			}
		}
		model.addAttribute("all",all);
		if(eval!=null)
		{
			String content = eval.getEvalFormContent();
			Map<String, Object> gradeMap = parseGradeXml(content);
			model.addAttribute("gradeMap",gradeMap);
		}
		return "/res/deptActivityStatistics/showEval";
	}

	@RequestMapping(value="/saveEval",method={RequestMethod.POST})
	@ResponseBody
	public String saveEval(SysDeptMonthPlanItemEval eval,HttpServletRequest request,String totalScore){
		SysUser current = GlobalContext.getCurrentUser();
		SysDeptMonthPlanItemEval old=deptActivityBiz.readPlanItemEvalByUser(eval.getItemFlow(),current.getUserFlow());
		if(old!=null)
		{
			return "你已对此科室活动进行评价，请刷新列表页面！";
		}
		String recContent = createGradeXml(request.getParameterMap());
		if(eval != null){
			eval.setEvalFormContent(recContent);
			eval.setEvalScore(totalScore);
			String userFlow = current.getUserFlow();
			String userName = current.getUserName();
			if(StringUtil.isNotBlank(userFlow)){
				eval.setOperUserFlow(userFlow);
			}
			if(StringUtil.isNotBlank(userName)){
				eval.setOperUserName(userName);
			}
			ResDoctor doctor=doctorBiz.readDoctor(userFlow);
			if(doctor!=null)
			{
				eval.setOrgFlow(doctor.getOrgFlow());
				eval.setOrgName(doctor.getOrgName());
			}
		}
        if (deptActivityBiz.savePlanItemEval(eval) != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	private String createGradeXml(Map<String,String[]> gradeInfo){
		List<String> params=new ArrayList<>();
		params.add("totalScore");
		params.add("planTime");
		params.add("userName");
		params.add("orgName");
		params.add("name");
		params.add("teaName");
		params.add("teaTitle");
		params.add("deptName");
		params.add("shouHuo");
		Document document = DocumentHelper.createDocument();
		Element rootEle = document.addElement("gradeInfo");
		if(gradeInfo!=null){
			String[] assessIds = gradeInfo.get("assessId");
			String[] scores = gradeInfo.get("score");
			if(assessIds!=null && assessIds.length>0){
				for(int i = 0 ; i<assessIds.length ; i++){
					String assessId = assessIds[i];
					String score = scores[i];
					Element item = rootEle.addElement("grade");
					item.addAttribute("assessId",assessId);

					Element scoreElement = item.addElement("score");
					scoreElement.setText(score);
				}
			}
			for(String param:params) {
				String[] vs = gradeInfo.get(param);
				if (vs != null && vs.length > 0 && StringUtil.isNotBlank(vs[0])) {
					Element item = rootEle.addElement(param);
					item.setText(vs[0]);
				}
			}
		}
		return document.asXML();
	}
	private void putMapVal(Map<String,Float> map,String key,Float val){
		if(map!=null){
			Float v = map.get(key);
			if(v==null){
				v = val;
			}else{
				v+=val;
			}
			v = new BigDecimal(v).setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
			map.put(key,v);
		}
	}
	public Map<String,Object> parseGradeXml(String recContent){
		Map<String,Object> gradeMap = null;
		if(StringUtil.isNotBlank(recContent)){
			try {
				Document doc = DocumentHelper.parseText(recContent);
				Element root = doc.getRootElement();
				if(root!=null){
					List<Element> items = root.elements();
					if(items!=null && items.size()>0){
						gradeMap = new HashMap<String, Object>();
						for(Element e : items){
							if("grade".equals(e.getName())) {
								String assessId = e.attributeValue("assessId");
								Map<String, String> dataMap = new HashMap<String, String>();
								gradeMap.put(assessId, dataMap);

								Element score = e.element("score");
								if (score != null) {
									String scoreStr = score.getText();
									dataMap.put("score", scoreStr);
								}
								Element lostReason = e.element("lostReason");
								if (lostReason != null) {
									dataMap.put("lostReason", lostReason.getText());
								}
							}else{

								gradeMap.put(e.getName(),e.getText());
							}
						}
					}
				}
			} catch (Exception e) {
                logger.error("", e);
			}
		}
		return gradeMap;
	}
	@RequestMapping(value="/exportInfo")
	public void exportInfo(Model model, Integer currentPage , HttpServletRequest request,
						   String deptFlow, String planDate,String role, HttpServletResponse response, String itemTypeId) throws Exception {
		SysUser sysuser=GlobalContext.getCurrentUser();
		Map<String,Object> param=new HashMap<>();
		param.put("userFlow",sysuser.getUserFlow());
		param.put("orgFlow",sysuser.getOrgFlow());
		param.put("deptFlow",deptFlow);
		param.put("planDate",planDate);
		param.put("role",role);
		param.put("itemTypeId",itemTypeId);
		List<Map<String,Object>> list=deptActivityBiz.searchStatisticList(param);
		model.addAttribute("list",list);
		String fileName= DeptActivityItemTypeEnum.getNameById(itemTypeId)+".xls";
		String[] titles = null;
		if (itemTypeId.equals(DeptActivityItemTypeEnum.JXCFAP.getId())
				||  itemTypeId.equals(DeptActivityItemTypeEnum.BLTLAP.getId())
				||  itemTypeId.equals(DeptActivityItemTypeEnum.XJKAP.getId())
				||  itemTypeId.equals(DeptActivityItemTypeEnum.QTHD.getId())
				||  itemTypeId.equals(DeptActivityItemTypeEnum.DSBGHAP.getId() )) {
			titles = new String[]{
					"deptName:科室",
					"planDate:月度",
					"planTime:日期",
					"joinUserName:主持人",
					"userCode:工号",
					"content:内容",
					"address:地点"
			};
		} else if (itemTypeId.equals(DeptActivityItemTypeEnum.JTBKAP.getId())) {
			titles = new String[]{
					"deptName:科室",
					"planDate:月度",
					"planTime:日期",
					"joinUserName:主持人",
					"userCode:工号",
					"content:内容"
			};
		}else if (itemTypeId.equals(DeptActivityItemTypeEnum.TKAP.getId())) {
			titles = new String[]{
					"deptName:科室",
					"planDate:月度",
					"planTime:日期",
					"joinUserName:听课人",
					"userCode:工号",
					"content:主讲人",
					"title:授课题目"
			};
		}else if (itemTypeId.equals(DeptActivityItemTypeEnum.DDAP.getId())) {
			titles = new String[]{
					"deptName:科室",
					"planDate:月度",
					"planTime:日期",
					"content:内容",
					"joinUserName:主持人"
			};
		}else if (itemTypeId.equals(DeptActivityItemTypeEnum.SQTHD.getId())) {
			titles = new String[]{
					"deptName:科室",
					"planDate:月度",
					"planTime:日期",
					"joinUserName:主持人",
					"userCode:工号"
			};
		}
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.setCookie(response);
		ExcleUtile.exportSimpleExcleByObjs(titles, list, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping(value="/exportItemEval")
	public void exportItemEval(Model model , HttpServletRequest request,
						   String itemFlow, String evalFlow, HttpServletResponse response, String itemTypeId) throws Exception {

		SysDeptMonthPlanItem item=deptActivityBiz.readPlanItem(itemFlow);
		int all=0;
		List<QingpuLectureCfgTitleExt> titleFormList = new ArrayList<QingpuLectureCfgTitleExt>();
		Map<String, Object> gradeAllMap=new HashMap<>();
		if(item!=null)
		{
			if(StringUtil.isNotBlank(item.getEvalCfg()))
			{
				Document dom = DocumentHelper.parseText(item.getEvalCfg());
				String titleXpath = "//title";
				List<Element> titleElementList = dom.selectNodes(titleXpath);
				if(titleElementList != null && !titleElementList.isEmpty()){
					for(Element te :titleElementList){
						QingpuLectureCfgTitleExt titleForm = new QingpuLectureCfgTitleExt();
						titleForm.setId(te.attributeValue("id"));
						titleForm.setName(te.attributeValue("name"));
						titleForm.setOrder(te.attributeValue("order"));
						List<Element> itemElementList = te.elements("item");
						List<QingpuLectureCfgItemExt> itemFormList = null;
						if(itemElementList != null && !itemElementList.isEmpty()){
							itemFormList = new ArrayList<QingpuLectureCfgItemExt>();
							int sum=0;
							for(Element ie : itemElementList){
								QingpuLectureCfgItemExt itemForm = new QingpuLectureCfgItemExt();
								itemForm.setId(ie.attributeValue("id"));
								itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
								String score=ie.element("score") == null ? "" : ie.element("score").getTextTrim();
								itemForm.setScore(score);
								itemForm.setOrder(ie.element("order") == null ? "" : ie.element("order").getTextTrim());
								itemFormList.add(itemForm);
								if(StringUtil.isNotBlank(score))
								{
									sum+=Double.parseDouble(score);
									all+=Double.parseDouble(score);
								}
							}
							titleForm.setScore(String.valueOf(sum));
						}
						titleForm.setItemList(itemFormList);
						titleFormList.add(titleForm);
					}
					model.addAttribute("titleFormList", titleFormList);
				}
			}
		}
		if("All".equals(evalFlow))
		{
			List<SysDeptMonthPlanItemEval> evals=deptActivityBiz.readPlanItemEvals(itemFlow);
			if(evals!=null)
			{
				for(SysDeptMonthPlanItemEval eval:evals)
				{
					String content = eval.getEvalFormContent();
					String evalFlow1 = eval.getEvalFlow();
					Map<String, Object> gradeMap = parseGradeXml(content);
					gradeAllMap.put(evalFlow1,gradeMap);
				}
			}
            createExcle(item, titleFormList, gradeAllMap, evals, response, com.pinde.core.common.GlobalConstant.FLAG_Y, request);
			String fileName = new String(("评价表.zip").getBytes("gbk"),"ISO8859-1" );
			ExcleUtile.setCookie(response);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
		}else{
			SysDeptMonthPlanItemEval eval=deptActivityBiz.readPlanItemEval(evalFlow);
			if(eval!=null&&StringUtil.isNotBlank(eval.getEvalFormContent()))
			{
				String content = eval.getEvalFormContent();
				Map<String, Object> gradeMap = parseGradeXml(content);
				gradeAllMap.put(eval.getEvalFlow(),gradeMap);
			}
			List<SysDeptMonthPlanItemEval> evals=new ArrayList<>();
			evals.add(eval);
			String fileName = new String((eval.getOperUserName()+"-评价表.xls").getBytes("gbk"),"ISO8859-1" );
			ExcleUtile.setCookie(response);
            createExcle(item, titleFormList, gradeAllMap, evals, response, com.pinde.core.common.GlobalConstant.FLAG_N, request);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}

	private void createExcle(SysDeptMonthPlanItem item, List<QingpuLectureCfgTitleExt> titleFormList, Map<String, Object> gradeAllMap, List<SysDeptMonthPlanItemEval> evals, HttpServletResponse response, String isList, HttpServletRequest request) throws IOException {
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isList))
		{
			OutputStream out=response.getOutputStream();
			File zip = new File(request.getRealPath("/files") + "/评价表.zip");// 压缩文件


			List<String> fileNames = new ArrayList();// 用于存放生成的文件名称s
			//文件流用于转存文件
			int j=1;
			for(SysDeptMonthPlanItemEval eval:evals)
			{
				HSSFWorkbook wb = createWorkBook(eval,item,titleFormList,gradeAllMap);
				String file = request.getRealPath("/files") + "/" + eval.getOperUserName()+"-评价表"+j+ ".xls";
				File fileTemp = new File(request.getRealPath("/files"));
				if(!fileTemp.exists()){
					fileTemp.mkdir();
				}
				fileNames.add(file);
				FileOutputStream o = new FileOutputStream(file);
				wb.write(o);
				o.close();
				j++;
			}

			File srcfile[] = new File[fileNames.size()];
			for (int i = 0, n1 = fileNames.size(); i < n1; i++) {
				srcfile[i] = new File(fileNames.get(i));
			}
			ZipFiles(srcfile, zip);//压缩文件
			FileInputStream inStream = new FileInputStream(zip);
			byte[] buf = new byte[4096];
			int readLength;
			while (((readLength = inStream.read(buf)) != -1)) {
				out.write(buf, 0, readLength);
			}
			inStream.close();
			zip.delete();//删除服务器上压缩文件
		}else{
			OutputStream os=response.getOutputStream();
			HSSFWorkbook wb = createWorkBook(evals.get(0),item,titleFormList,gradeAllMap);
			wb.write(os);
		}
	}

	private HSSFWorkbook createWorkBook(SysDeptMonthPlanItemEval eval, SysDeptMonthPlanItem item, List<QingpuLectureCfgTitleExt> titleFormList, Map<String, Object> gradeAllMap) {

		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();

		//添加设置列为文本格式
		HSSFDataFormat format = wb.createDataFormat();
		style.setDataFormat(format.getFormat("@"));
		// 创建一个居中格式
		style.setAlignment(HorizontalAlignment.CENTER);

		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		style.setFont(font);

		HSSFCell cell = null;
		HSSFRow row = null;
		int rowNum=0;
		Map<String, Object> gradeMap= (Map<String, Object>) gradeAllMap.get(eval.getEvalFlow());
		if(gradeMap==null)
			gradeMap=new HashMap<>();

		int sum=0;
		if(!item.getItemTypeId().equals("JXCFAP")){
			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellValue("时间");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue(StringUtil.defaultIfEmpty((String) gradeMap.get("planTime"),"") );
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue("姓名");
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue(StringUtil.defaultIfEmpty((String) gradeMap.get("userName"),"") );
			cell.setCellStyle(style);
			cell = row.createCell(4);
			cell.setCellValue("基地名称");
			cell.setCellStyle(style);
			cell = row.createCell(5);
			cell.setCellValue(StringUtil.defaultIfEmpty((String) gradeMap.get("orgName"),"") );
			cell.setCellStyle(style);

			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellValue("课程名称");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue(StringUtil.defaultIfEmpty((String) gradeMap.get("name"),"") );
			cell.setCellStyle(style);

			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellValue("授课教师");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue(StringUtil.defaultIfEmpty((String) gradeMap.get("teaName"),"") );
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue("职称");
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue(StringUtil.defaultIfEmpty((String) gradeMap.get("teaTitle"),"") );
			cell.setCellStyle(style);
			cell = row.createCell(4);
			cell.setCellValue("科室");
			cell.setCellStyle(style);
			cell = row.createCell(5);
			cell.setCellValue(StringUtil.defaultIfEmpty((String) gradeMap.get("deptName"),"") );
			cell.setCellStyle(style);

			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellValue("评分细则");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue("标准分");
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue("得分");
			cell.setCellStyle(style);
			for(QingpuLectureCfgTitleExt titleExt:titleFormList)
			{
				row = sheet.createRow(rowNum++);
				cell = row.createCell(0);
				cell.setCellValue(titleExt.getName());
				cell.setCellStyle(style);
				if(null != titleExt.getItemList() && titleExt.getItemList().size()>0) {
					for (QingpuLectureCfgItemExt itemExt : titleExt.getItemList()) {
						Map<String, String> dataMap= (Map<String, String>) gradeMap.get(itemExt.getId());
						if(dataMap==null)
							dataMap= new HashMap<>();
						row = sheet.createRow(rowNum++);
						cell = row.createCell(0);
						cell.setCellValue(itemExt.getOrder()+"、"+itemExt.getName());
						cell.setCellStyle(style);
						cell = row.createCell(1);
						cell.setCellValue(itemExt.getScore());
						cell.setCellStyle(style);
						cell = row.createCell(2);
						cell.setCellValue(
								gradeMap.get(itemExt.getId())==null?"":
										((Map<String, String>) gradeMap.get(itemExt.getId())).get("score")==null?"":
												((Map<String, String>) gradeMap.get(itemExt.getId())).get("score")
						);
						cell.setCellStyle(style);
						sum+=StringUtil.isBlank(itemExt.getScore())?0:Integer.valueOf(itemExt.getScore());
					}
				}
			}
		}
		if(item.getItemTypeId().equals("JXCFAP")){
			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellValue("教师姓名");
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue("查房方式");
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue("□普  查(共查病例数__________ 例) " );
			cell.setCellStyle(style);
			row = sheet.createRow(rowNum++);
			cell = row.createCell(3);
			cell.setCellValue("□重点查(共查病例数__________ 例) " );
			cell.setCellStyle(style);

			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellValue("被带教对象");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue(" □住院医师   □实习医师   □进修医师 " );
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue("合计人数_________ 人" );
			cell.setCellStyle(style);

			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellValue("查房起止时间");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue("起______________");
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue("止______________");
			cell.setCellStyle(style);

			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellValue("评  定  项  目");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue("优");
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue("良");
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue("中");
			cell.setCellStyle(style);
			cell = row.createCell(4);
			cell.setCellValue("差");
			cell.setCellStyle(style);
			for(QingpuLectureCfgTitleExt titleExt:titleFormList)
			{
				row = sheet.createRow(rowNum++);
				cell = row.createCell(0);
				cell.setCellValue(titleExt.getName());
				cell.setCellStyle(style);
				if(null != titleExt.getItemList() && titleExt.getItemList().size()>0) {
					for (QingpuLectureCfgItemExt itemExt : titleExt.getItemList()) {
						Map<String, String> dataMap= (Map<String, String>) gradeMap.get(itemExt.getId());
						if(dataMap==null)
							dataMap= new HashMap<>();
						row = sheet.createRow(rowNum++);
						cell = row.createCell(0);
						cell.setCellValue(itemExt.getOrder()+"、"+itemExt.getName());
						cell.setCellStyle(style);
						String v=gradeMap.get(itemExt.getId())==null?"":
								((Map<String, String>) gradeMap.get(itemExt.getId())).get("score")==null?"":
										((Map<String, String>) gradeMap.get(itemExt.getId())).get("score");
						int index=1;
						if(gradeMap.get(itemExt.getId())!=null&&((Map<String, String>) gradeMap.get(itemExt.getId())).get("score")!=null)
						{
							Float score=Float.valueOf(((Map<String, String>) gradeMap.get(itemExt.getId())).get("score"));
							Float base=Float.valueOf(itemExt.getScore());
							if(base==5)
							{
								if(score==5)
								{
									index=1;v="优";
								}
								if(score<=4&&score>=3)
								{
									index=2;v="良";
								}
								if(score==2)
								{
									index=3;v="中";
								}
								if(score<=1&&score>=0)
								{
									index=4;v="差";
								}
							}else if(base==10)
							{
								if(score<=10&&score>=9)
								{
									index=1;v="优";
								}
								if(score<=8&&score>=7)
								{
									index=2;v="良";
								}
								if(score<=6&&score>=5)
								{
									index=3;v="中";
								}
								if(score<=4&&score>=0)
								{
									index=4;v="差";
								}
							}else if(base==20)
							{

								if(score<=20&&score>=16)
								{
									index=1;v="优";
								}
								if(score<=15&&score>=11)
								{
									index=2;v="良";
								}
								if(score<=10&&score>=6)
								{
									index=3;v="中";
								}
								if(score<=5&&score>=0)
								{
									index=4;v="差";
								}
							}
						}
						cell = row.createCell(index);
						cell.setCellValue(v);
						cell.setCellStyle(style);
						sum+=StringUtil.isBlank(itemExt.getScore())?0:Integer.valueOf(itemExt.getScore());
					}
				}
			}

			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellValue("参加本次查房后，认为收获");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue(StringUtil.defaultIfEmpty((String) gradeMap.get("shouHuo"),""));
			cell.setCellStyle(style);
		}
		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellValue("意见或建议");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue(StringUtil.defaultIfEmpty(eval.getEvalContent(),""));
		cell.setCellStyle(style);

		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellValue("总分");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue(sum+"");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue(StringUtil.defaultIfEmpty((String) gradeMap.get("totalScore"),""));
		cell.setCellStyle(style);

		return wb;
	}

	public static void ZipFiles(java.io.File[] srcfile, java.io.File zipfile) {
		byte[] buf = new byte[1024];
		ZipOutputStream out=null;
		try {
			out = new ZipOutputStream(new FileOutputStream(zipfile));
			for (int i = 0; i < srcfile.length; i++) {
				try {
					FileInputStream in = new FileInputStream(srcfile[i]);
					out.putNextEntry(new ZipEntry(srcfile[i].getName()));
					int len;
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
					out.closeEntry();
					in.close();
					srcfile[i].delete();//删除服务器上文件
				} catch (IOException e) {
                    logger.error("", e);
				}
			}
			out.close();
		} catch (IOException e) {
            logger.error("", e);
		}finally {
			if(out!=null)
				try {
					out.close();
				} catch (IOException e) {
                    logger.error("", e);
				}
		}
	}
}
