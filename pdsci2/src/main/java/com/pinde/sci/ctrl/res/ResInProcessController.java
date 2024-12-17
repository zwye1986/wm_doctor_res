package com.pinde.sci.ctrl.res;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pinde.core.model.PubFile;
import com.pinde.core.model.SysDept;
import com.pinde.core.model.SysOrg;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IResInprocessInfoBiz;
import com.pinde.sci.biz.res.IResScoreBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.FileUtil;
import com.pinde.sci.dao.sys.SysOrgExtMapper;
import com.pinde.core.model.ResInprocessInfo;
import com.pinde.core.model.ResInprocessInfoMember;
import org.dom4j.Document;
import org.dom4j.DocumentException;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/res/inprocess")
public class ResInProcessController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResInProcessController.class);
	
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IResScoreBiz scoreBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private ISchArrangeResultBiz resultBiz;
	@Autowired
	private IResInprocessInfoBiz resInprocessInfoBiz;
	@Autowired
	private IFileBiz pubFileBiz;
	@Autowired
	private SysOrgExtMapper orgExtMapper;

	@RequestMapping("/info/{role}")
	public String info(String deptFlow, @PathVariable String role,Model model,String orgFlow){
		model.addAttribute("role",role);
		SysUser user = GlobalContext.getCurrentUser();
		if(!"global".equals(role)){
			orgFlow = user.getOrgFlow();
		}
		if("head".equals(role)){
			if(StringUtil.isBlank(deptFlow)){
				deptFlow = user.getDeptFlow();
			}
			List<Map<String,Object>> deptList = deptBiz.queryDeptListByFlow(user.getUserFlow());
			model.addAttribute("deptList",deptList);
		}
		SysDept dept=deptBiz.readSysDept(deptFlow);
		model.addAttribute("dept",dept);
		ResInprocessInfo info=resInprocessInfoBiz.readByDeptFlowAndOrgFlow(deptFlow,orgFlow);
		model.addAttribute("info",info);
		if(info!=null)
		{
			List<ResInprocessInfoMember> members=resInprocessInfoBiz.readMembersByRecordFlow(info.getRecordFlow());
			model.addAttribute("members",members);
			List<PubFile> files=pubFileBiz.searchByProductFlow(info.getRecordFlow());
			model.addAttribute("files",files);
			String arrange=info.getTeachingArrangement();
			if(StringUtil.isNotBlank(arrange))
			{
				Map<String,Object> arrangeMap = new HashMap<>();
				arrangeMap=paressXml(arrange);
				model.addAttribute("arrangeMap",arrangeMap);
			}
		}
        if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(role)) {
			return "/res/inprocess/info4Global";
		}
		return "/res/inprocess/info";
	}
	@RequestMapping("/showInfo")
	public String showInfo(String deptFlow, Model model){
		SysDept dept=deptBiz.readSysDept(deptFlow);
		model.addAttribute("dept",dept);
		ResInprocessInfo info=resInprocessInfoBiz.readByDeptFlowAndOrgFlow(deptFlow,dept.getOrgFlow());
		model.addAttribute("info",info);
		if(info!=null)
		{
			List<ResInprocessInfoMember> members=resInprocessInfoBiz.readMembersByRecordFlow(info.getRecordFlow());
			model.addAttribute("members",members);
			List<PubFile> files=pubFileBiz.searchByProductFlow(info.getRecordFlow());
			model.addAttribute("files",files);
			String arrange=info.getTeachingArrangement();
			if(StringUtil.isNotBlank(arrange))
			{
				Map<String,Object> arrangeMap = new HashMap<>();
				arrangeMap=paressXml(arrange);
				model.addAttribute("arrangeMap",arrangeMap);
			}
		}
		return "/res/inprocess/showInfo";
	}
	@RequestMapping("/list/{role}")
	public String list( @PathVariable String role,String orgFlow,Integer currentPage,HttpServletRequest request,Model model){
		model.addAttribute("role",role);
        if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(role)) {
			//查询本省所有医院并放入orgFlowList
			SysOrg org = new SysOrg();
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			List<SysOrg> orgs = orgBiz.searchOrg(org);
			model.addAttribute("orgs",orgs);
			PageHelper.startPage(currentPage,getPageSize(request));
			Map<String,Object> map=new HashMap<>();
			map.put("orgFlow",orgFlow);
			List<ResInprocessInfo> infos=resInprocessInfoBiz.readInfoList4Global(map);
			model.addAttribute("infos",infos);
			return "/res/inprocess/list4Global";
		}
        if (com.pinde.core.common.GlobalConstant.USER_LIST_UNIVERSITY.equals(role)) {
			//查询本高校所有医院并放入orgFlowList
			SysUser currentUser = GlobalContext.getCurrentUser();
			SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
			String workOrgId = currentOrg.getSendSchoolId();
			List<SysOrg> orgs = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			model.addAttribute("orgs",orgs);
			List<String> orgFlowList = new ArrayList<>();
			if(orgs!=null&&orgs.size()>0){
				for(SysOrg org:orgs){
					String flow = org.getOrgFlow();
					orgFlowList.add(flow);
				}
			}
			PageHelper.startPage(currentPage,getPageSize(request));
			Map<String,Object> map=new HashMap<>();
			map.put("orgFlow",orgFlow);
			map.put("orgFlowList",orgFlowList);
			List<ResInprocessInfo> infos=resInprocessInfoBiz.readInfoList4Global(map);
			model.addAttribute("infos",infos);
			return "/res/inprocess/list4Global";
	}
		if(StringUtil.isBlank(orgFlow)){
			//管理员
			SysUser admin=GlobalContext.getCurrentUser();
			orgFlow=admin.getOrgFlow();
		}
		List<ResInprocessInfo> infos=resInprocessInfoBiz.readInfoList(orgFlow);
		model.addAttribute("infos",infos);
		return "/res/inprocess/list";
	}

	//下载附件
	@RequestMapping(value = {"/fileDown" }, method = RequestMethod.GET)
	public void fileDown(String fileFlow, final HttpServletResponse response) throws Exception{
		PubFile file = this.pubFileBiz.readFile(fileFlow);
		pubFileBiz.downPubFile(file,response);
	}
	/**
	 */
	@RequestMapping(value={"/save"},method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String save(String jsonData,ResInprocessInfo info,String recordFlow, HttpServletRequest request)throws IOException {
		if(StringUtil.isBlank(recordFlow))
		{
			if(StringUtil.isNotBlank(info.getRecordFlow()))
			{
				recordFlow= info.getRecordFlow();
			}else{
				recordFlow= PkUtil.getUUID();
			}
		}
		info.setRecordFlow(recordFlow);
		jsonData = java.net.URLDecoder.decode(jsonData,"UTF-8");
		//校验上传文件大小及格式
		String checkResult=checkFiles(request);
		if(!"1".equals(checkResult))
		{
			return checkResult;
		}
		Map<String,Object> mp = JSON.parseObject(jsonData,Map.class);


		//科室成员
		List<JSONObject> jsonObjects = (List<JSONObject>)mp.get("members");
		List<ResInprocessInfoMember> members=new ArrayList<>();
		List<String> memberFlows=new ArrayList<>();
		if(jsonObjects!=null&&jsonObjects.size()>0){
			for(JSONObject jo:jsonObjects)
			{
				ObjectMapper objectMapper=new ObjectMapper();
				ResInprocessInfoMember m=objectMapper.readValue(jo.toString(), ResInprocessInfoMember.class);
				m.setInfoRecordFlow(recordFlow);
				members.add(m);
				if(StringUtil.isNotBlank(m.getRecordFlow()))
				{
					memberFlows.add(m.getRecordFlow());
				}
			}
		}
		//删除不在新的保存数据中的成员
		resInprocessInfoBiz.deleteMemberNotInFlow(recordFlow,memberFlows);
		//保存科室成员信息
		saveMembers(members);

		//上传文件的流水号
		List<String> fileFlows= (List<String>) mp.get("fileFlows");
		//处理不在本次保存中的文件
		upadteFileInfo(recordFlow,fileFlows);
		//处理上传文件
		addUploadFile(recordFlow,request);

		//科室固定学术及业务活动安排
		Map<String,Object> teaching = (Map<String,Object>)mp.get("teaching");
		if(teaching!=null)
		{
			String arrange=getXml(teaching);
			info.setTeachingArrangement(arrange);
		}
		SysDept dept=deptBiz.readSysDept(info.getDeptFlow());
		info.setDeptName(dept.getDeptName());
		info.setOrgFlow(dept.getOrgFlow());
		SysOrg org=orgBiz.readSysOrg(dept.getOrgFlow());
		info.setOrgName(org.getOrgName());
		int count=resInprocessInfoBiz.saveInfo(info);
		if(count==0)
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}

	private String checkFiles(HttpServletRequest request) {
		String result="1";
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//判断 request 是否有文件上传,即多部分请求
		if(multipartResolver.isMultipart(request)){
			List<String> fileSuffix=new ArrayList<>();
			fileSuffix.add(".DOC");
			fileSuffix.add(".DOCX");
			fileSuffix.add(".PPT");
			fileSuffix.add(".PDF");
			//转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			//取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while(iter.hasNext()){
				//记录上传过程起始时的时间，用来计算上传时间
				//int pre = (int) System.currentTimeMillis();
				//取得上传文件
				List<MultipartFile> files = multiRequest.getFiles(iter.next());
				if(files != null&&files.size()>0){
					for(MultipartFile file:files) {

						//取得当前上传文件的文件名称
						String fileName = file.getOriginalFilename();
						String suffix=fileName.substring(fileName.lastIndexOf(".")).toUpperCase();
						if(!fileSuffix.contains(suffix))
						{
							return "入科教育文档【" +fileName+ "】的文件格式不正确，只能上传word,ppt,pdf三种格式的文件。";
						}
						if (file.getSize() > 10 * 1024 * 1024) {
							return "入科教育文档【" +fileName+ "】的大小超过10M，不得保存";
						}
					}
				}
			}
		}
		return result;
	}

	//保存上传的附件
	private void addUploadFile(String recordFlow, HttpServletRequest request) {
		//以下为多文件上传********************************************
		//创建一个通用的多部分解析器
		List<PubFile> pubFiles=new ArrayList<>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//判断 request 是否有文件上传,即多部分请求
		if(multipartResolver.isMultipart(request)){
			//转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			//取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while(iter.hasNext()){
				//记录上传过程起始时的时间，用来计算上传时间
				//int pre = (int) System.currentTimeMillis();
				//取得上传文件
				List<MultipartFile> files = multiRequest.getFiles(iter.next());
				if(files != null&&files.size()>0){
					for(MultipartFile file:files) {
						//保存附件
						PubFile pubFile = new PubFile();
						//取得当前上传文件的文件名称
						String oldFileName = file.getOriginalFilename();
						//如果名称不为“”,说明该文件存在，否则说明该文件不存在
						if (StringUtil.isNotBlank(oldFileName)) {
							//定义上传路径
							String dateString = DateUtil.getCurrDate2();
							String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "inProcessFile" + File.separator + dateString+ File.separator+recordFlow;
							File fileDir = new File(newDir);
							if (!fileDir.exists()) {
								fileDir.mkdirs();
							}
							//重命名上传后的文件名
							String originalFilename = "";
							originalFilename = PkUtil.getUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
							File newFile = new File(fileDir, originalFilename);
							try {
								file.transferTo(newFile);
							} catch (Exception e) {
                                logger.error("", e);
								throw new RuntimeException("保存文件失败！");
							}
							String filePath = File.separator + "inProcessFile" + File.separator + dateString + File.separator+recordFlow+ File.separator + originalFilename;
                            pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
							pubFile.setFilePath(filePath);
							pubFile.setFileName(oldFileName);
							pubFile.setFileSuffix(oldFileName.substring(oldFileName.lastIndexOf(".")));
							pubFile.setProductType("inProcessFile");
							pubFile.setProductFlow(recordFlow);
							pubFiles.add(pubFile);
						}
					}
				}
				//记录上传该文件后的时间
				//int finaltime = (int) System.currentTimeMillis();
			}
		}
		if(pubFiles.size()>0)
		{
			pubFileBiz.saveFiles(pubFiles);
		}
	}

	//处理文件
	private void upadteFileInfo(String recordFlow, List<String> fileFlows) {
		//查询出不在本次保存中的文件信息
		List<PubFile> files=pubFileBiz.searchByProductFlowAndNotInFileFlows(recordFlow,fileFlows);
		//删除服务器中相应的文件
		if(files!=null&&files.size()>0)
		{
			String basePath = InitConfig.getSysCfg("upload_base_dir");
			for (PubFile pubFile : files) {
				if (StringUtil.isNotBlank(pubFile.getFilePath())) {
					String filePath = basePath + pubFile.getFilePath();
					FileUtil.deletefile(filePath);
				}
                pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
				pubFileBiz.editFile(pubFile);
			}
		}
	}

	/**
	 * 保存成员信息
	 * @param members
     */
	private void saveMembers(List<ResInprocessInfoMember> members) {
		if(members!=null&&members.size()>0){
			for(ResInprocessInfoMember m:members)
			{
				resInprocessInfoBiz.saveMember(m);
			}
		}
	}

	private String getXml(Map<String, Object> teaching) {
		String xml="";
		if(teaching!=null&&teaching.size()>0) {
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("root");
			for (String key : teaching.keySet()) {
				Element item = root.addElement(key);
				item.setText(String.valueOf(teaching.get(key)));
			}
			xml=doc.asXML();
		}
		return xml;
	}

	private Map<String,Object> paressXml(String content) {
		Map<String,Object> formDataMap = null;
		if(StringUtil.isNotBlank(content)){
			formDataMap = new HashMap<String, Object>();
			try {
				Document document = DocumentHelper.parseText(content);
				Element rootElement = document.getRootElement();
				List<Element> elements = rootElement.elements();
				for(Element element : elements){
					formDataMap.put(element.getName(), element.getText());
				}
			} catch (DocumentException e) {
                logger.error("", e);
			}
		}
		return formDataMap;
	}

}
