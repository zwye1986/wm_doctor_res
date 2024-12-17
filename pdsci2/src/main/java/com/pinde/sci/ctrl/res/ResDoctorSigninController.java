package com.pinde.sci.ctrl.res;

import com.pinde.core.common.enums.ResDoctorKqStatusEnum;
import com.pinde.core.common.sci.dao.SysUserDeptMapper;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorKqBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.form.res.TimeSetFrom;
import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResDoctorKq;
import com.pinde.core.model.ResDoctorSchProcess;
import com.pinde.core.model.ResDoctorSignin;
import com.pinde.core.model.ResKgCfg;
import com.pinde.core.model.SchDept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/res/doctorSignin")
public class ResDoctorSigninController extends GeneralController{
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorProcessBiz resDoctorProcessBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IResDoctorKqBiz resDoctorKqBiz;
	@Autowired
	private SysUserDeptMapper sysUserDeptMapper;

    private static Logger logger = LoggerFactory.getLogger(ResExamCfgController.class);

	@RequestMapping(value="/timeSet")
	public String timeSet(Model model) {
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		List<ResKgCfg> cfgs=resDoctorKqBiz.readKqCfgList(orgFlow,"");
		Map<String,ResKgCfg> cfgMap=new HashMap<>();
		if(cfgs!=null)
		{
			for(ResKgCfg cfg:cfgs)
			{
				cfgMap.put(cfg.getDoctorTypeId()+cfg.getLessOrGreater(),cfg);
			}
		}
		model.addAttribute("cfgMap",cfgMap);
		return "res/doctorSignin/timeSet";
	}
	@RequestMapping(value="/saveTimeSet")
	@ResponseBody
	public String saveTimeSet(Model model, @RequestBody TimeSetFrom timeSetFrom) {
		String orgFlow =StringUtil.defaultIfEmpty(timeSetFrom.getOrgFlow(),GlobalContext.getCurrentUser().getOrgFlow()) ;
		if(timeSetFrom.getCfgs()==null)
		{
			return "请填写请假时间配置！";
		}
		timeSetFrom.setOrgFlow(orgFlow);
		int c=resDoctorKqBiz.saveKqCfgs(timeSetFrom);
		if(c==0)
		{
            return com.pinde.core.common.GlobalConstant.OPERATE_FAIL;
		}
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}

	//签到列表
	@RequestMapping(value="/main")
	public String main(Model model) {
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		SysUserDeptExample example = new SysUserDeptExample();
        example.createCriteria().andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		model.addAttribute("signUrl","func://funcFlow=resDoctorSingin&teacherUserFlow="+userFlow);
		return "res/doctorSignin/report/main";
	}
	@RequestMapping(value="/signinData")
	public String reportData(Model model,String doctorName,String trainingYears,String sessionNumber,
							 String signinDate, String trainingSpeId,Integer currentPage,HttpServletRequest request) {
		String teacherUserFlow = GlobalContext.getCurrentUser().getUserFlow();
		Map<String,Object> param=new HashMap<>();
		param.put("teacherUserFlow",teacherUserFlow);
		param.put("doctorName",doctorName);
		param.put("trainingYears",trainingYears);
		param.put("sessionNumber",sessionNumber);
		param.put("signinDate",signinDate);
		param.put("trainingSpeId",trainingSpeId);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,String>> list=resDoctorKqBiz.getSigninList(param);
		model.addAttribute("list",list);
		return "res/doctorSignin/report/signinData";
	}

	@RequestMapping(value="/codeIsUse")
	@ResponseBody
	public Object codeIsUse(Model model,String userName,String deptName,String sessionNumber,
							String token) {
		Object val="";
		if(val==null)
		{
            return com.pinde.core.common.GlobalConstant.FLAG_N;
		}
		return val;
	}

	//学员签到查询列表
	@RequestMapping(value="/signinList")
	public String signinList(Integer currentPage,HttpServletRequest request,String signinDate,Model model){
		String doctorFlow = GlobalContext.getCurrentUser().getUserFlow();
		Map<String,Object> param=new HashMap<>();
		param.put("signinDate",signinDate);
		param.put("doctorFlow",doctorFlow);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,String>> list=resDoctorKqBiz.getSigninList(param);
		model.addAttribute("list",list);
		return "res/doctorSignin/signinList";
	}

	//科室签到查询列表
	@RequestMapping(value="/signinList/head")
	public String signinListHead(Model model,String doctorName,String trainingYears,String sessionNumber,String signinDate,
								 String reportDate, String trainingSpeId,Integer currentPage,HttpServletRequest request){
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		SysUserDeptExample example = new SysUserDeptExample();
        example.createCriteria().andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysUserDept> deptList = sysUserDeptMapper.selectByExample(example);
		List<String> deptFlows = new ArrayList<String>();
		for(SysUserDept dept: deptList){
			deptFlows.add(dept.getDeptFlow());
		}
		Map<String,Object> param=new HashMap<>();
		param.put("deptFlows",deptFlows);
		param.put("doctorName",doctorName);
		param.put("trainingYears",trainingYears);
		param.put("sessionNumber",sessionNumber);
		param.put("reportDate",reportDate);
		param.put("trainingSpeId",trainingSpeId);
		param.put("signinDate",signinDate);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,String>> list=resDoctorKqBiz.getSigninList(param);
		model.addAttribute("list",list);
		return "res/doctorSignin/signinListHead";
	}

	//学员请假信息
	@RequestMapping("/leaveList")
	public String leaveList(ResDoctorKq kq, Integer currentPage, HttpServletRequest request, Model model){
		SysUser currentUser = GlobalContext.getCurrentUser();
		kq.setDoctorFlow(currentUser.getUserFlow());
        kq.setKqTypeId(com.pinde.core.common.enums.DictTypeEnum.LeaveType.getId());
        kq.setKqTypeName(com.pinde.core.common.enums.DictTypeEnum.LeaveType.getName());
		PageHelper.startPage(currentPage,getPageSize(request));
		List<ResDoctorKq> kqList = resDoctorKqBiz.searchResDoctorKq(kq, null);
		model.addAttribute("kqList",kqList);
		return "/res/doctorSignin/leaveList";
	}

	/**
	 *  学员申诉功能
	 */
	@RequestMapping("/appealList")
	public String appealList(ResDoctorKq kq,Integer currentPage,HttpServletRequest request,Model model){
		SysUser currentUser = GlobalContext.getCurrentUser();
		kq.setDoctorFlow(currentUser.getUserFlow());
        kq.setKqTypeId(com.pinde.core.common.enums.DictTypeEnum.AppealType.getId());
        kq.setKqTypeName(com.pinde.core.common.enums.DictTypeEnum.AppealType.getName());
		PageHelper.startPage(currentPage,getPageSize(request));
		List<ResDoctorKq> kqList = resDoctorKqBiz.searchResDoctorKq(kq, null);
		model.addAttribute("kqList",kqList);
		return "/res/doctorSignin/appealList";
	}
	@RequestMapping("/editLeave")
	public String editLeave(String recordFlow,String roleFlag,Model model){
		if(StringUtil.isNotBlank(recordFlow)){
			ResDoctorKq kq = resDoctorKqBiz.readResDoctorKq(recordFlow);
			model.addAttribute("kq",kq);
			List<PubFile> files=fileBiz.findFileByTypeFlow("ResDoctorKqFile",recordFlow);
			model.addAttribute("files",files);
		}
		SysUser currentUser = GlobalContext.getCurrentUser();
		ResDoctor doctor=doctorBiz.readDoctor(currentUser.getUserFlow());
		if("doctor".equals(roleFlag)){
			model.addAttribute("doctor",doctor);
			if(doctor!=null)
			{
				List<ResKgCfg> cfgs=resDoctorKqBiz.readKqCfgList(doctor.getOrgFlow(),doctor.getDoctorCategoryId());
				model.addAttribute("cfgs",cfgs);
			}
			List<ResDoctorSchProcess> processes = resDoctorProcessBiz.searchProcessByDoctor(currentUser.getUserFlow());
			model.addAttribute("processes",processes);
		}
		return "/res/doctorSignin/editLeave";
	}

	/**
	 * 编辑申诉
	 * @param recordFlow
	 * @param roleFlag
	 * @param model
     * @return
     */
	@RequestMapping("/editAppeal")
	public String editAppeal(String recordFlow,String roleFlag,Model model){
		if(StringUtil.isNotBlank(recordFlow)){
			ResDoctorKq kq = resDoctorKqBiz.readResDoctorKq(recordFlow);
			model.addAttribute("kq",kq);
			List<PubFile> files=fileBiz.findFileByTypeFlow("ResDoctorKqFile",recordFlow);
			model.addAttribute("files",files);
		}
		SysUser currentUser = GlobalContext.getCurrentUser();
		ResDoctor doctor=doctorBiz.readDoctor(currentUser.getUserFlow());
		model.addAttribute("doctor",doctor);
		return "/res/doctorSignin/editAppeal";
	}
	private String checkFiles(HttpServletRequest request) {
		String result="1";
		ServletContext application = request.getServletContext();
		String imageSuffixStr = ".bmp,.jpg,.png";
		String[] imageSuffixArr = imageSuffixStr.split(",");
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//判断 request 是否有文件上传,即多部分请求
		if(multipartResolver.isMultipart(request)){
			List<String> fileSuffix=new ArrayList<>();
			fileSuffix.addAll(Arrays.asList(imageSuffixArr));
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
						String suffix=fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
						if(!fileSuffix.contains(suffix))
						{
							return fileName + "的文件格式不正确，只能上传" + imageSuffixStr + "图片格式的文件。";
						}
						if (file.getSize() > 10 * 1024 * 1024) {
							return fileName+ "的大小超过10M，不得保存";
						}
					}
				}
			}
		}
		return result;
	}
	@RequestMapping("/saveLeave")
	@ResponseBody
	public String saveLeave(ResDoctorKq kq,String deptFlow,HttpServletRequest request,String []fileFlows){
		ResDoctor doctor=doctorBiz.readDoctor(GlobalContext.getCurrentUser().getUserFlow());
		if(doctor==null)
		{
			return "医师信息不存在！";
		}
		if(StringUtil.isBlank(doctor.getOrgFlow()))
		{
			return "未进入基地参加培训！";
		}
		if(StringUtil.isBlank(kq.getDoctorFlow())){
			kq.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
			kq.setDoctorName(GlobalContext.getCurrentUser().getUserName());
			kq.setOrgFlow(doctor.getOrgFlow());
		}
		List<ResKgCfg> cfgs=resDoctorKqBiz.readKqCfgList(doctor.getOrgFlow(),doctor.getDoctorCategoryId());
		if(cfgs==null||cfgs.size()==0)
		{
			return "请联系基地管理员维护请假审核流程！";
		}
		ResKgCfg less=null;
		ResKgCfg greater=null;
		ResKgCfg midd=null;
		Integer allDays=null;
		Integer intervalDays=null;
		Integer intervalDays2=null;
		for(ResKgCfg cfg:cfgs)
		{
			if("All".equals(cfg.getLessOrGreater()))
			{
				allDays=Integer.valueOf(cfg.getAllDays());
				intervalDays=Integer.valueOf(cfg.getIntervalDays());
				intervalDays2=Integer.valueOf(cfg.getIntervalDays2());
			}
			if("Less".equals(cfg.getLessOrGreater()))
			{
				less=cfg;
			}
			if("Greater".equals(cfg.getLessOrGreater()))
			{
				greater=cfg;
			}
			if("Midd".equals(cfg.getLessOrGreater()))
			{
				midd=cfg;
			}
		}
		if(allDays==null||intervalDays==null||intervalDays2==null||less==null||greater==null||midd==null)
		{
			return "请联系基地管理员维护请假审核流程！";
		}
		ResDoctorKq old=resDoctorKqBiz.readResDoctorKq(kq.getRecordFlow());
		if(old!=null)
		{
			if(!ResDoctorKqStatusEnum.Auditing.getId().equals(old.getAuditStatusId()))
			{
				return "此请假信息已被审核，请刷新列表页！";
			}
		}
        int c = resDoctorKqBiz.checkTime(kq.getRecordFlow(), kq.getDoctorFlow(), kq.getStartDate(), kq.getEndDate(), com.pinde.core.common.enums.DictTypeEnum.LeaveType.getId());
		if(c>0)
		{
			return "在当前请假时间内已有请假信息！";
		}
		if(StringUtil.isBlank(kq.getIntervalDays()))
		{
			return "请输入请假天数";
		}

        double days = resDoctorKqBiz.readAllIntervalDays(kq.getRecordFlow(), kq.getDoctorFlow(), kq.getStartDate(), kq.getEndDate(), com.pinde.core.common.enums.DictTypeEnum.LeaveType.getId());
		if((days+Double.valueOf(kq.getIntervalDays()))>allDays)
		{
			return "请假总天数大于"+allDays+",总天数为"+(days+Double.valueOf(kq.getIntervalDays()))+",你已请假"+days+"天,本次请假天数"+kq.getIntervalDays();
		}
		//校验上传文件大小及格式
		String checkResult = checkFiles(request);
		if (!"1".equals(checkResult)) {
			return checkResult;
		}
		ResDoctorSchProcess process=resDoctorProcessBiz.read(kq.getProcessFlow());
		if(process==null)
		{
			return "请选择轮转科室";
		}
		if(intervalDays2<Double.valueOf(kq.getIntervalDays()))
		{
			setKqAuditInfo(greater,kq,process,doctor);
		}else if(intervalDays2>=Double.valueOf(kq.getIntervalDays())&&intervalDays<Double.valueOf(kq.getIntervalDays())){
			setKqAuditInfo(midd,kq,process,doctor);
		}else{
			setKqAuditInfo(less,kq,process,doctor);
		}

        kq.setKqTypeId(com.pinde.core.common.enums.DictTypeEnum.LeaveType.getId());
        kq.setKqTypeName(com.pinde.core.common.enums.DictTypeEnum.LeaveType.getName());
        kq.setTypeName(com.pinde.core.common.enums.DictTypeEnum.getDictName(com.pinde.core.common.enums.DictTypeEnum.LeaveType, kq.getTypeId()));
		kq.setAuditStatusId(ResDoctorKqStatusEnum.Auditing.getId());
		kq.setAuditStatusName(ResDoctorKqStatusEnum.Auditing.getName());
		int n=resDoctorKqBiz.editResDoctorKq(kq);
		if(n==0)
		{
			return "保存失败";
		}
		List<String> flows=null;
		//上传文件的流水号
		if(fileFlows!=null) {
			flows = Arrays.asList(fileFlows);
		}
		//处理不在本次保存中的文件
		upadteFileInfo(kq.getRecordFlow(), flows);
		//处理上传文件
		addUploadFile(kq.getRecordFlow(), request, "ResDoctorKqFile");
		return "1";
	}
	@RequestMapping("/saveAppeal")
	@ResponseBody
	public String saveAppeal(ResDoctorKq kq,String deptFlow,HttpServletRequest request,String []fileFlows){
		ResDoctor doctor=doctorBiz.readDoctor(GlobalContext.getCurrentUser().getUserFlow());
		if(doctor==null)
		{
			return "医师信息不存在！";
		}
		if(StringUtil.isBlank(doctor.getOrgFlow()))
		{
			return "未进入基地参加培训！";
		}
		ResDoctorKq old=resDoctorKqBiz.readResDoctorKq(kq.getRecordFlow());
		if(old!=null)
		{
			if(!ResDoctorKqStatusEnum.Auditing.getId().equals(old.getAuditStatusId()))
			{
				return "此申诉信息已被审核，请刷新列表页！";
			}
		}
        int c = resDoctorKqBiz.checkTime(kq.getRecordFlow(), kq.getDoctorFlow(), kq.getStartDate(), kq.getEndDate(), com.pinde.core.common.enums.DictTypeEnum.AppealType.getId());
		if(c>0)
		{
			return "在当前申诉时间内已有申诉信息！";
		}
		//校验上传文件大小及格式
		String checkResult = checkFiles(request);
		if (!"1".equals(checkResult)) {
			return checkResult;
		}

        kq.setKqTypeId(com.pinde.core.common.enums.DictTypeEnum.AppealType.getId());
        kq.setKqTypeName(com.pinde.core.common.enums.DictTypeEnum.AppealType.getName());
		if(StringUtil.isNotBlank(kq.getTypeId())) {
            kq.setTypeName(com.pinde.core.common.enums.DictTypeEnum.getDictName(com.pinde.core.common.enums.DictTypeEnum.AppealType, kq.getTypeId()));
		}
		kq.setAuditStatusId(ResDoctorKqStatusEnum.Auditing.getId());
		kq.setAuditStatusName(ResDoctorKqStatusEnum.Auditing.getName());
		if(StringUtil.isBlank(kq.getDoctorFlow())){
			kq.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
			kq.setDoctorName(GlobalContext.getCurrentUser().getUserName());
			kq.setOrgFlow(doctor.getOrgFlow());
		}
		String adminFlow = InitConfig.getSysCfg("res_admin_role_flow");
		Map<String,Object> paramMap = new HashMap<>();
        String wsId = (String) GlobalContext.getSession().getAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
		paramMap.put("wsId",wsId);
		paramMap.put("roleFlow",adminFlow);
		paramMap.put("orgFlow",doctor.getOrgFlow());
		List<SysUser> sysUserList = userBiz.searchUserWithRole(paramMap);
		SysUser admin=null;
		if(sysUserList!=null&&sysUserList.size()>0){
			admin=sysUserList.get(0);
		}
		if(admin!=null)
		{
			kq.setManagerFlow(admin.getUserFlow());
			kq.setManagerName(admin.getUserName());
		}
		int n=resDoctorKqBiz.editResDoctorKq(kq);
		if(n==0)
		{
			return "保存失败";
		}
		List<String> flows=null;
		//上传文件的流水号
		if(fileFlows!=null) {
			flows = Arrays.asList(fileFlows);
		}
		//处理不在本次保存中的文件
		upadteFileInfo(kq.getRecordFlow(), flows);
		//处理上传文件
		addUploadFile(kq.getRecordFlow(), request, "ResDoctorKqFile");
		return "1";
	}

	//保存上传的附件
	private void addUploadFile(String recordFlow, HttpServletRequest request, String noteTypeId) {
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
							String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + noteTypeId + File.separator + dateString+ File.separator+recordFlow;
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
							String filePath =  File.separator + noteTypeId + File.separator + dateString + File.separator+recordFlow+ File.separator + originalFilename;
                            pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
							pubFile.setFilePath(filePath);
							pubFile.setFileName(oldFileName);
							pubFile.setFileSuffix(oldFileName.substring(oldFileName.lastIndexOf(".")));
							pubFile.setProductType(noteTypeId);
							pubFile.setProductFlow(recordFlow);
							pubFiles.add(pubFile);
						}
					}
				}
			}
		}
		if(pubFiles.size()>0)
		{
			fileBiz.saveFiles(pubFiles);
		}
	}
	//处理文件
	private void upadteFileInfo(String recordFlow, List<String> fileFlows) {
		//查询出不在本次保存中的文件信息
		List<PubFile> files=fileBiz.searchByProductFlowAndNotInFileFlows(recordFlow,fileFlows);
		//删除服务器中相应的文件
		if(files!=null&&files.size()>0)
		{
			for (PubFile pubFile : files) {
                pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
				fileBiz.editFile(pubFile);
			}
		}
	}
	private void setKqAuditInfo(ResKgCfg greater, ResDoctorKq kq, ResDoctorSchProcess process, ResDoctor doctor) {
		if(greater!=null)
		{
			kq.setTeacherFlow(process.getTeacherUserFlow());
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(greater.getTeacherFlag()))
			{
				kq.setTeacherName(process.getTeacherUserName());
			}else{
				kq.setTeacherName("-");
			}
			kq.setHeadFlow(process.getHeadUserFlow());
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(greater.getHeadFlag()))
			{
				kq.setHeadName(process.getHeadUserName());
			}else{
				kq.setHeadName("-");
			}
			kq.setTutorFlow(doctor.getTutorFlow());
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(greater.getTutorFlag()))
			{
				kq.setTutorName(doctor.getTutorName());
			}else{
				kq.setTutorName("-");
			}
			String adminFlow = InitConfig.getSysCfg("res_admin_role_flow");
			Map<String,Object> paramMap = new HashMap<>();
            String wsId = (String) GlobalContext.getSession().getAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
			paramMap.put("wsId",wsId);
			paramMap.put("roleFlow",adminFlow);
			paramMap.put("orgFlow",doctor.getOrgFlow());
			List<SysUser> sysUserList = userBiz.searchUserWithRole(paramMap);
			SysUser admin=null;
			if(sysUserList!=null&&sysUserList.size()>0){
				admin=sysUserList.get(0);
			}
			if(admin!=null)
			{
				kq.setManagerFlow(admin.getUserFlow());
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(greater.getManagerFlag()))
				{
					kq.setManagerName(admin.getUserName());
				}else{
					kq.setManagerName("-");
				}
			}
			kq.setSchDeptFlow(process.getSchDeptFlow());
			kq.setSchDeptName(process.getSchDeptName());
			kq.setSchDeptStartDate(process.getSchStartDate());
			kq.setSchDeptEndDate(process.getSchEndDate());
		}
	}

	@RequestMapping("/delKq")
	@ResponseBody
	public String delKq(ResDoctorKq kq){
		ResDoctorKq old=resDoctorKqBiz.readResDoctorKq(kq.getRecordFlow());
		if(old!=null)
		{
			if(!ResDoctorKqStatusEnum.Auditing.getId().equals(old.getAuditStatusId())
					&&!ResDoctorKqStatusEnum.Revoke.getId().equals(old.getAuditStatusId()))
			{
				return "此信息已被审核，请刷新列表页！";
			}
		}
        kq.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
		resDoctorKqBiz.editResDoctorKq(kq);
		return "1";
	}
	@RequestMapping("/revokeKq")
	@ResponseBody
	public String revokeKq(ResDoctorKq kq){
		ResDoctorKq old=resDoctorKqBiz.readResDoctorKq(kq.getRecordFlow());
		if(old!=null)
		{
			if(!ResDoctorKqStatusEnum.Auditing.getId().equals(old.getAuditStatusId()))
			{
				return "此信息已被审核，请刷新列表页！";
			}
		}
		kq.setAuditStatusId(ResDoctorKqStatusEnum.Revoke.getId());
		kq.setAuditStatusName(ResDoctorKqStatusEnum.Revoke.getName());
		resDoctorKqBiz.editResDoctorKq(kq);
		return "1";
	}

	/**
	 * 请假审批列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/leaveAuditList/{roleFlag}"},method={RequestMethod.GET,RequestMethod.POST})
	public String leaveAuditList(@PathVariable String roleFlag, ResDoctorKq kq, Integer currentPage,String sessionNumber,
								 String[] doctorTypeIdArys, HttpServletRequest request, Model model){
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("doctorName",kq.getDoctorName());
		paramMap.put("typeId",kq.getTypeId());
		paramMap.put("startDate",kq.getStartDate());
		paramMap.put("endDate",kq.getEndDate());
		paramMap.put("sessionNumber",sessionNumber);

		SysUser currUser = GlobalContext.getCurrentUser();
        kq.setKqTypeId(com.pinde.core.common.enums.DictTypeEnum.LeaveType.getId());
		kq.setOrgFlow(currUser.getOrgFlow());
		if("teacher".equals(roleFlag)){//带教
			kq.setTeacherFlow(currUser.getUserFlow());
			paramMap.put("teacherFlow",kq.getTeacherFlow());
		}
		if("tutor".equals(roleFlag)){
			kq.setTutorFlow(currUser.getUserFlow());
			paramMap.put("tutorFlow",kq.getTutorFlow());
		}
		if("head".equals(roleFlag)){
			kq.setHeadFlow(currUser.getUserFlow());
			paramMap.put("headFlow",kq.getHeadFlow());
		}
		if("manager".equals(roleFlag)){
			kq.setManagerFlow(currUser.getUserFlow());
			paramMap.put("managerFlow",kq.getManagerFlow());
		}
		//复选框勾选标识
		Map<String,String> doctorTypeSelectMap = new HashMap<>();
		SysDict sysDict = new SysDict();
		List<String> doctorTypeIdList = new ArrayList<>();
		if(doctorTypeIdArys!=null && doctorTypeIdArys.length>0){
			doctorTypeIdList = Arrays.asList(doctorTypeIdArys);
		}
        sysDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorType.getId());
        sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysDict> dictList = dictBiz.searchDictList(sysDict);
		if(dictList!=null&&dictList.size()>0){
			if(doctorTypeIdList!=null&&doctorTypeIdList.size()>0){
				for (SysDict dict:dictList){
					if(doctorTypeIdList.contains(dict.getDictId())){
						doctorTypeSelectMap.put(dict.getDictId(),"checked");
					}
				}
			}else {
				doctorTypeIdList = new ArrayList<>();
				for (SysDict dict:dictList){
					doctorTypeIdList.add(dict.getDictId());
					doctorTypeSelectMap.put(dict.getDictId(),"checked");
				}
			}
		}
		paramMap.put("doctorTypeIdList",doctorTypeIdList);
		model.addAttribute("doctorTypeSelectMap",doctorTypeSelectMap);
		List<String> notStatus=new ArrayList<>();
		notStatus.add(ResDoctorKqStatusEnum.Revoke.getId());
		paramMap.put("notStatus",notStatus);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<ResDoctorKq> kqList = resDoctorKqBiz.searchResDoctorKq(paramMap);
		model.addAttribute("kqList", kqList);
		model.addAttribute("roleFlag", roleFlag);
		Map<String,List<PubFile>> fileMap=new HashMap<>();
		if(kqList!=null)
		{
			for(ResDoctorKq resDoctorKq:kqList)
			{

				List<PubFile> files=fileBiz.findFileByTypeFlow("ResDoctorKqFile",resDoctorKq.getRecordFlow());
				fileMap.put(resDoctorKq.getRecordFlow(),files);
			}
		}
		model.addAttribute("fileMap", fileMap);
		// 医院管理员
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_MANAGER.equals(roleFlag)) {
			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(currUser.getOrgFlow());
			model.addAttribute("schDeptList", schDeptList);
		}

		return "/res/doctorSignin/leaveAuditList";
	}

	/**
	 * 申诉审批列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/appealAuditList/{roleFlag}"},method={RequestMethod.GET,RequestMethod.POST})
	public String appealAuditList(@PathVariable String roleFlag, ResDoctorKq kq, Integer currentPage, HttpServletRequest request,  Model model){
		SysUser currUser = GlobalContext.getCurrentUser();
        kq.setKqTypeId(com.pinde.core.common.enums.DictTypeEnum.AppealType.getId());
		kq.setOrgFlow(currUser.getOrgFlow());
//		if("teacher".equals(roleFlag)){//带教
//			kq.setTeacherFlow(currUser.getUserFlow());
//		}
//		if("tutor".equals(roleFlag)){//带教
//			kq.setTutorFlow(currUser.getUserFlow());
//		}
//		if("head".equals(roleFlag)){
//			kq.setHeadFlow(currUser.getUserFlow());
//		}
		if("manager".equals(roleFlag)){
			kq.setManagerFlow(currUser.getUserFlow());
		}
		List<String> notStatus=new ArrayList<>();
		notStatus.add(ResDoctorKqStatusEnum.Revoke.getId());
		PageHelper.startPage(currentPage,getPageSize(request));
		List<ResDoctorKq> kqList = resDoctorKqBiz.searchResDoctorKq(kq, notStatus);
		model.addAttribute("kqList", kqList);
		model.addAttribute("roleFlag", roleFlag);
		Map<String,List<PubFile>> fileMap=new HashMap<>();
		if(kqList!=null)
		{
			for(ResDoctorKq resDoctorKq:kqList)
			{

				List<PubFile> files=fileBiz.findFileByTypeFlow("ResDoctorKqFile",resDoctorKq.getRecordFlow());
				fileMap.put(resDoctorKq.getRecordFlow(),files);
			}
		}
		model.addAttribute("fileMap", fileMap);
		// 医院管理员
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_MANAGER.equals(roleFlag)) {
			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(currUser.getOrgFlow());
			model.addAttribute("schDeptList", schDeptList);
		}

		model.addAttribute("kqList", kqList);
		model.addAttribute("roleFlag", roleFlag);
		return "/res/doctorSignin/appealAuditList";
	}

	/**
	 * 打开填写审核意见页面
	 */
	@RequestMapping(value="/saveKqInfo")
	public String saveKqInfo(String recordFlow,String agreeFlag,String roleFlag,Model model){
		model.addAttribute("recordFlow",recordFlow);
		model.addAttribute("agreeFlag",agreeFlag);
		model.addAttribute("roleFlag",roleFlag);
		return "res/doctorSignin/saveKqInfo";
	}

	/**
	 * 保存考勤审批
	 * @param
	 * @return
	 */
	@RequestMapping(value="/saveKqAudit")
	@ResponseBody
	public String saveKqAudit(ResDoctorKq kq) throws ParseException {
		SysUser currtUser = GlobalContext.getCurrentUser();
		if(StringUtil.isNotBlank(kq.getRecordFlow())){
			boolean AppealFlag = false;
			ResDoctorKq kq0 = resDoctorKqBiz.readResDoctorKq(kq.getRecordFlow());
            if (com.pinde.core.common.enums.DictTypeEnum.AppealType.getId().equals(kq0.getKqTypeId())) {
				AppealFlag = true;
			}
			SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String time = sdf0.format(new Date());
			if(AppealFlag)
			{
				if(StringUtil.isNotBlank(kq.getManagerAgreeFlag())){
					kq.setManagerAuditTime(time);
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getManagerAgreeFlag())) {
						kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
						kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
					}else{
						kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerUnPass.getId());
						kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerUnPass.getName());
					}
				}
			}else{
				if(StringUtil.isNotBlank(kq.getTeacherAgreeFlag())){
					kq.setTeacherAuditTime(time);
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getTeacherAgreeFlag())) {
						kq.setAuditStatusId(ResDoctorKqStatusEnum.TeacherPass.getId());
						kq.setAuditStatusName(ResDoctorKqStatusEnum.TeacherPass.getName());
					}else{
						kq.setAuditStatusId(ResDoctorKqStatusEnum.TeacherUnPass.getId());
						kq.setAuditStatusName(ResDoctorKqStatusEnum.TeacherUnPass.getName());
					}
				}
				if(StringUtil.isNotBlank(kq.getTutorAgreeFlag())){
					kq.setTutorAuditTime(time);
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getTutorAgreeFlag())) {
						kq.setAuditStatusId(ResDoctorKqStatusEnum.TutorPass.getId());
						kq.setAuditStatusName(ResDoctorKqStatusEnum.TutorPass.getName());
					}else{
						kq.setAuditStatusId(ResDoctorKqStatusEnum.TutorUnPass.getId());
						kq.setAuditStatusName(ResDoctorKqStatusEnum.TutorUnPass.getName());
					}
				}
				if(StringUtil.isNotBlank(kq.getHeadAgreeFlag())){
					kq.setHeadAuditTime(time);
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getHeadAgreeFlag())) {
						kq.setAuditStatusId(ResDoctorKqStatusEnum.HeadPass.getId());
						kq.setAuditStatusName(ResDoctorKqStatusEnum.HeadPass.getName());
					}else{
						kq.setAuditStatusId(ResDoctorKqStatusEnum.HeadUnPass.getId());
						kq.setAuditStatusName(ResDoctorKqStatusEnum.HeadUnPass.getName());
					}
				}
				if(StringUtil.isNotBlank(kq.getManagerAgreeFlag())){
					kq.setManagerAuditTime(time);
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getManagerAgreeFlag())) {
						kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
						kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
					}else{
						kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerUnPass.getId());
						kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerUnPass.getName());
					}
				}
			}
			int result = resDoctorKqBiz.editResDoctorKq(kq);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	//教秘扫码签到
	@RequestMapping(value="/mainJM/{roleFlag}")
	public String mainJM(Model model,@PathVariable String roleFlag) {
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		String schDeptFlow = GlobalContext.getCurrentUser().getDeptFlow();
		SysUserDeptExample example = new SysUserDeptExample();
        example.createCriteria().andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		//TODO 根据app接口修改
		model.addAttribute("signUrl","func://funcFlow=");
		model.addAttribute("roleFlag",roleFlag);
		return "res/doctorSignin/report/mainJM";
	}
	@RequestMapping(value="/signinDataJM")
	public String signinDataJM(Model model,String doctorName,String roleFlag,String isSignin,String[] doctorTypeIdList,
							 String signinDate, String schDeptFlow,Integer currentPage,HttpServletRequest request) {
		if(StringUtil.isBlank(signinDate)){
			signinDate=DateUtil.getCurrDate();
		}

		List<String> typeList =null;
		//复选框勾选标识
		Map<String,String> doctorTypeSelectMap = new HashMap<>();
		SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorType.getId());
        sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysDict> dictList = dictBiz.searchDictList(sysDict);
		if(doctorTypeIdList!=null&&doctorTypeIdList.length>0){
			typeList=Arrays.asList(doctorTypeIdList);
			for (SysDict dict:dictList){
				if(typeList.contains(dict.getDictId())){
					doctorTypeSelectMap.put(dict.getDictId(),"checked");
				}
			}
		}
		if(doctorTypeIdList==null)
		{
			typeList=new ArrayList<>();
			for (SysDict dict:dictList){
				typeList.add(dict.getDictId());
				doctorTypeSelectMap.put(dict.getDictId(),"checked");
			}
		}
		model.addAttribute("doctorTypeSelectMap",doctorTypeSelectMap);
		Map<String,Object> param=new HashMap<>();
		param.put("doctorName",doctorName);
		param.put("isSignin",isSignin);
		param.put("signinDate",signinDate);
		param.put("typeList",typeList);
		if(!"manager".equals(roleFlag)){
			List<String> schDeptFlows=new ArrayList<>();
			SysUserDeptExample example = new SysUserDeptExample();
            example.createCriteria().andUserFlowEqualTo(GlobalContext.getCurrentUser().getUserFlow()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			List<SysUserDept> deptList = sysUserDeptMapper.selectByExample(example);
			for(SysUserDept dept: deptList){
				schDeptFlows.add(dept.getDeptFlow());
			}
			param.put("schDeptFlows",schDeptFlows);
		}else{
			//查出当前机构的所有科室
			List<SysDept> deptList = deptBiz.searchDeptByOrg(GlobalContext.getCurrentUser().getOrgFlow());
			model.addAttribute("depts",deptList);
			param.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
			param.put("schDeptFlow",schDeptFlow);

		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,String>> list=resDoctorKqBiz.getSigninListForMany(param);
		ResDoctorSignin signinTemp=new ResDoctorSignin();
		signinTemp.setSigninDate(signinDate);
		signinTemp.setTeacherUserFlow(schDeptFlow);
		List<ResDoctorSignin> signinData=resDoctorKqBiz.queryDoctorSignins(signinTemp);
		Map<String,String> signinInfo=new HashMap<>();
		if(signinData!=null&&signinData.size()>0){
			for(ResDoctorSignin listMap:signinData){
				if(StringUtil.isNotBlank(listMap.getDoctorFlow())){
					if(signinInfo.containsKey(listMap.getDoctorFlow())){
						String value=signinInfo.get(listMap.getSigninTime());
						value=value+","+listMap.getSigninTime();
						signinInfo.put(listMap.getDoctorFlow(),value);
					}else{
						signinInfo.put(listMap.getDoctorFlow(),listMap.getSigninTime());
					}
				}
			}
		}
		model.addAttribute("list",list);
		model.addAttribute("signinInfo",signinInfo);
		model.addAttribute("roleFlag",roleFlag);
		if("manager".equals(roleFlag)){
			return "res/doctorSignin/report/signinDataHead";
		}
		return "res/doctorSignin/report/signinDataJM";
	}
	//考勤统计
	@RequestMapping(value="/kqStatisticsList")
	public String kqStatisticsList(String startDate,String endDate,String doctorName,Integer currentPage,HttpServletRequest request,Model model) {
		SysUser currentUser = GlobalContext.getCurrentUser();
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("startDate",startDate);
		paramMap.put("doctorName",doctorName);
		paramMap.put("endDate",endDate);
        paramMap.put("dictList", com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get("LeaveType"));
		paramMap.put("orgFlow",currentUser.getOrgFlow());
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> resultMapList = resDoctorKqBiz.getKqStatistics(paramMap);
		model.addAttribute("resultMapList",resultMapList);
		return "res/doctorSignin/kqStatisticsList";
	}
	@RequestMapping("/kqStatisticsDetail")
	public String kqStatisticsDetail(ResDoctorKq kq,Integer currentPage2,HttpServletRequest request,Model model){
		SysUser currentUser = GlobalContext.getCurrentUser();
		kq.setOrgFlow(currentUser.getOrgFlow());
		kq.setKqTypeId("LeaveType");
		kq.setAuditStatusId(ResDoctorKqStatusEnum.HeadPass.getId());
		PageHelper.startPage(currentPage2,getPageSize(request));
		List<ResDoctorKq> kqList = resDoctorKqBiz.kqStatisticsDetail(kq);
		model.addAttribute("kqList",kqList);
		return "/res/doctorSignin/kqStatisticsDetailList";
	}
}
