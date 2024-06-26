package com.pinde.sci.ctrl.xjgl;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.xjgl.IChangeApplyBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.enums.xjgl.UserChangeApplyStatusEnum;
import com.pinde.sci.enums.xjgl.UserChangeApplyTypeEnum;
import com.pinde.sci.form.xjgl.SubmitApplyForm;
import com.pinde.sci.form.xjgl.UserChangeApplyForm;
import com.pinde.sci.model.edu.EduAnswerExt;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.xjgl.XjEduStudentChangeExt;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * 
 * @author PPBear
 *
 */
@Controller
@RequestMapping("/xjgl/change/apply")
public class XjglChangeApplyController extends GeneralController{
	@Autowired
	private IChangeApplyBiz iChangeApplyBiz;
	@Autowired
	private IUserBiz iUserBiz;
	@Autowired
	private IDeptBiz deptBiz;
	/**
	 * 编辑申请界面跳转
	 * @param userFlow
	 * @param model
	 */
	@RequestMapping("/editApply")
	public String editApply(String userFlow,Model model,String changeFlag,String recordFlow,String applyTypeId,String applyTime,String applyStatus,Integer currentPage,HttpServletRequest request ){
		if (StringUtil.isBlank(userFlow)) {
			userFlow=GlobalContext.getCurrentUser().getUserFlow();
		}
		EduUser user=iChangeApplyBiz.readEduUser(userFlow);
		SysUser sysUser=iUserBiz.findByFlow(userFlow);
		Map<String, Object> paramMap=new HashMap<String,Object>();
		sysUser.setUserFlow(userFlow);
		paramMap.put("sysUser", sysUser);
		List<EduCourse> educourseList=iChangeApplyBiz.searchStuCourseList(paramMap);
		ResDoctor doctor = iChangeApplyBiz.searchByUserFlow(userFlow);
		EduUserChangeApply eduChangeUser=new EduUserChangeApply();
		Map<String, String>UserAuditTimeMap=new HashMap<String,String>();
		Map<String, String>userAuditPersonMap=new HashMap<String,String>();
		if(StringUtil.isNotBlank(recordFlow)){
			eduChangeUser.setRecordFlow(recordFlow);
			EduUserChangeApply existApply=iChangeApplyBiz.readEduUserChangeApply(recordFlow);
			SubmitApplyForm form=JaxbUtil.converyToJavaBean(existApply.getContent(), SubmitApplyForm.class);
			model.addAttribute("form",form);
			model.addAttribute("existApply", existApply);
		}else{
			eduChangeUser.setUserFlow(userFlow);
			eduChangeUser.setApplyTime(applyTime);
			eduChangeUser.setApplyTypeId(applyTypeId);
			eduChangeUser.setStatusId(applyStatus);
			PageHelper.startPage(currentPage,getPageSize(request));
			List<EduUserChangeApply> eduUserChangeApplies=iChangeApplyBiz.searchEduUserChangeApply(eduChangeUser);
			for(EduUserChangeApply edu:eduUserChangeApplies){
				if(UserChangeApplyStatusEnum.Approve.getId().equals(edu.getStatusId())|| UserChangeApplyStatusEnum.NotApprove.getId().equals(edu.getStatusId()))	
				{
					String key=edu.getRecordFlow();
					SubmitApplyForm form=JaxbUtil.converyToJavaBean(edu.getContent(), SubmitApplyForm.class);
					String value=form.getAuditDate();
					String person=form.getAuditPerson();
					UserAuditTimeMap.put(key, value);
					userAuditPersonMap.put(key, person);
				}
			}
			model.addAttribute("eduUserChangeApplies", eduUserChangeApplies);
			model.addAttribute("userAuditPersonMap", userAuditPersonMap);
			model.addAttribute("UserAuditTimeMap", UserAuditTimeMap);
		}
		model.addAttribute("user", user);
		model.addAttribute("sysUser", sysUser);
		model.addAttribute("educourseList", educourseList);
		model.addAttribute("doctor", doctor);
		if (GlobalConstant.FLAG_Y.equals(changeFlag)) {
			return "xjgl/student/changeApply";
		}
		return "xjgl/student/application";
	}
	/**
	 * 上传图片文件
	 * @param file
	 * @throws Exception
	 */
	@RequestMapping(value={"/uploadChangeApply"})
	@ResponseBody
	public String uploadChangeApply(MultipartFile file) throws Exception{
		if(file!=null && !file.isEmpty()){
			String checkResult = iChangeApplyBiz.checkImg(file);
			String resultPath = "";
			if(!GlobalConstant.FLAG_Y.equals(checkResult)){
				return checkResult;
			}else{
				resultPath = iChangeApplyBiz.saveFileToDirs("", file, "xjglImages");
				return resultPath;
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}

	/**
	 * 支持上传表格文件（支持各种类型）
	 * @param file
	 * @throws Exception
	 */
	@RequestMapping(value={"/uploadTblFile"})
	@ResponseBody
	public String uploadAllFile(MultipartFile file,String applyTypeId) throws Exception{
		if(file!=null && !file.isEmpty()){
			return iChangeApplyBiz.saveFileToDirs(file,"xjglTblFile",applyTypeId);
		}
		return GlobalConstant.UPLOAD_FAIL;
	}

	/**
	 * 保存上传表格文件（存储路径）
	 */
	@RequestMapping(value={"/saveTblFile"})
	@ResponseBody
	public String saveTblFile(SubmitApplyForm form,String applyTypeId) throws Exception{
		if(null != form && null != form.getChangeApplyUrlList()){
			Iterator<String> it = form.getChangeApplyUrlList().iterator();
			while(it.hasNext()) {
				if(StringUtil.isBlank(it.next())) {
					it.remove();//去除页面初始上传附件模板的展示
				}
			}
		}
		int result=iChangeApplyBiz.saveTblFileInfo(form,applyTypeId);
		if (result!=GlobalConstant.ZERO_LINE) {
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 打印excel
	 * @param changeApply
	 * @param eduUser
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value={"/exportExcel"})
	public void exportExcel(EduUserChangeApply changeApply, EduUser eduUser, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] headLines = null;
		headLines = new String[]{
				"异动审核一览表",
			};
		String[] titles = new String[]{
				"eduUser.period:入学年级",
				"eduUser.className:班级",
				"eduUser.sid:学号",
				"sysUser.userName:姓名",
				"sysUser.sexName:性别",
				"eduUser.trainOrgName:学分位委员会",
				"eduUser.majorName:专业名称",
				"applyTypeName:申请类型",
				"applyTime:申请时间",
				"content:批准时间",
				"statusName:状态",
		};
		Map<String, Object> paramMap=new HashMap<String, Object>();
		List<String> statusIdList=new ArrayList<String>();
		statusIdList.add(UserChangeApplyStatusEnum.Approve.getId());
		statusIdList.add(UserChangeApplyStatusEnum.NotApprove.getId());
		statusIdList.add(UserChangeApplyStatusEnum.Submit.getId());
		paramMap.put("changeApply", changeApply); 
		paramMap.put("statusIdList", statusIdList);
		paramMap.put("eduUser", eduUser);
		List<XjEduStudentChangeExt> changeExtList = iChangeApplyBiz.searchStdentChangeExtsList(paramMap);
		if(changeExtList!=null && !changeExtList.isEmpty()){
			for (XjEduStudentChangeExt eduStudentChangeExt : changeExtList) {
				eduStudentChangeExt.getEduUser().setMajorName("["+eduStudentChangeExt.getEduUser().getMajorId()+"]"+eduStudentChangeExt.getEduUser().getMajorName());
			}
		}
		if(changeExtList!=null&&!changeExtList.isEmpty()){
			for (XjEduStudentChangeExt ext : changeExtList) {
				if (ext.getStatusId().equals(UserChangeApplyStatusEnum.Approve.getId()) || ext.getStatusId().equals(UserChangeApplyStatusEnum.NotApprove.getId())){
					SubmitApplyForm form=JaxbUtil.converyToJavaBean(ext.getContent(), SubmitApplyForm.class);
					ext.setContent(form.getAuditDate());
				}else{
					ext.setContent("");
				}
			}
		}
		String fileName = "异动审核一览表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		iChangeApplyBiz.exportExcel(headLines, titles, changeExtList, response.getOutputStream());
		response.setContentType("application/octet-stream;charset=UTF-8");
	}
	/**
	 * 提交申请
	 * @param userFlow
	 * @param model
	 */
	@RequestMapping("/saveApply")
	@ResponseBody
	public String saveApply(UserChangeApplyForm applyForm,SubmitApplyForm form,String userFlow,String recordFlow,Model model){
		if(null != form && null != form.getChangeApplyUrlList()){
			Iterator<String> it = form.getChangeApplyUrlList().iterator();
			while(it.hasNext()) {
				if(StringUtil.isBlank(it.next())) {
					it.remove();//去除页面初始上传附件模板的展示
				}
			}
		}
		int result=iChangeApplyBiz.saveAndUpdateChangeApplyInfo(form,applyForm,userFlow,recordFlow);
		if (result!=GlobalConstant.ZERO_LINE) {
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping("/submitApply")
	@ResponseBody
	public String submitApply(String recordFlow,Model model){
		EduUserChangeApply eduUser=iChangeApplyBiz.readEduUserChangeApply(recordFlow);
		int count = iChangeApplyBiz.reqTypeCount(eduUser);
		if(count == 0){//同种异动申请在未审核前不能提交多次
			eduUser.setStatusId(UserChangeApplyStatusEnum.Submit.getId());
			eduUser.setStatusName(UserChangeApplyStatusEnum.Submit.getName());
			int reslut=iChangeApplyBiz.updataApplyInfo(eduUser,null);
			if(GlobalConstant.ZERO_LINE!=reslut){
				return GlobalConstant.OPRE_SUCCESSED;
			}
		}
		return GlobalConstant.OPRE_FAIL;
	}
	@RequestMapping("/findChangeInfo")
	public String findChangeInfo(EduUserChangeApply userChangeApply,EduUser eduUser,Model model,Integer currentPage,HttpServletRequest request){
		EduUserChangeApply changeApply=new EduUserChangeApply();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		if (StringUtil.isNotBlank(userChangeApply.getApplyTypeId())) {
			changeApply.setApplyTypeId(userChangeApply.getApplyTypeId());
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<String> statusIdList=new ArrayList<String>();
		statusIdList.add(UserChangeApplyStatusEnum.Approve.getId());
		statusIdList.add(UserChangeApplyStatusEnum.NotApprove.getId());
		statusIdList.add(UserChangeApplyStatusEnum.Submit.getId());
		paramMap.put("changeApply", changeApply); 
		paramMap.put("statusIdList", statusIdList);
		paramMap.put("eduUser", eduUser);
		List<XjEduStudentChangeExt> changeExtList = iChangeApplyBiz.searchStdentChangeExtsList(paramMap);
		Map<String, String> extMap=new HashMap<String,String>();
		if(changeExtList!=null&&!changeExtList.isEmpty()){
			for (XjEduStudentChangeExt ext : changeExtList) {
				if (ext.getStatusId().equals(UserChangeApplyStatusEnum.Approve.getId()) || ext.getStatusId().equals(UserChangeApplyStatusEnum.NotApprove.getId())){
					SubmitApplyForm form=JaxbUtil.converyToJavaBean(ext.getContent(), SubmitApplyForm.class);
					String key=ext.getRecordFlow();
					String value=form.getAuditDate();
					extMap.put(key, value);
				}
			}
			model.addAttribute("extMap", extMap);
		}
		//学位分委员会
		SysDept sysDept=new SysDept();
		sysDept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<SysDept> deptList=this.deptBiz.searchDept(sysDept);
		model.addAttribute("deptList", deptList);
		model.addAttribute("changeExtList", changeExtList);
		return "xjgl/plat/tranAudit";
	}
	@RequestMapping("/auditApply")
	@ResponseBody
	public String auditApply(SubmitApplyForm form,String recordFlow,String status,Model model){
		EduUserChangeApply user=new EduUserChangeApply();
		user.setRecordFlow(recordFlow);
		EduUserChangeApply eduUser=iChangeApplyBiz.readEduUserChangeApply(recordFlow);
		if(eduUser!=null){
			if(GlobalConstant.FLAG_Y.equals(status)){
				eduUser.setStatusId(UserChangeApplyStatusEnum.Approve.getId());
				eduUser.setStatusName(UserChangeApplyStatusEnum.Approve.getName());
			}
			if(GlobalConstant.FLAG_N.equals(status)){
				eduUser.setStatusId(UserChangeApplyStatusEnum.NotApprove.getId());
				eduUser.setStatusName(UserChangeApplyStatusEnum.NotApprove.getName());
			}
			int result=iChangeApplyBiz.updataApplyInfo(eduUser,form);
			if (GlobalConstant.ZERO_LINE==result) {
				return GlobalConstant.OPRE_SUCCESSED;
			}
		}
		return GlobalConstant.OPRE_SUCCESSED;
	}
	@RequestMapping("/print")
	public void print(String recordFlow, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		
		WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
		ServletContext context =  request.getServletContext();
		String watermark = GeneralMethod.getWatermark(null);
		String name="";

		EduUserChangeApply 	user=new EduUserChangeApply();
		if (StringUtil.isNotBlank(recordFlow)) {
			user.setRecordFlow(recordFlow);
		paramMap.put("changeApply", user);

			List<XjEduStudentChangeExt> eduUserList = iChangeApplyBiz.searchStdentChangeExtsList(paramMap);
		if (eduUserList.size()>0&&!eduUserList.equals("")) {
			XjEduStudentChangeExt exitApply = eduUserList.get(0);
			ResDoctor doctor = iChangeApplyBiz.searchByUserFlow(exitApply.getUserFlow());
			SubmitApplyForm form=JaxbUtil.converyToJavaBean(exitApply.getContent(), SubmitApplyForm.class);
			String exitApplyType=exitApply.getApplyTypeId();
				dataMap.put("sid",exitApply.getEduUser().getSid());
				dataMap.put("userName", exitApply.getSysUser().getUserName());
				dataMap.put("majorName",exitApply.getEduUser().getMajorName());
			if (UserChangeApplyTypeEnum.Makeup.getId().equals(exitApplyType)) {
				dataMap.put("trainTypeName",exitApply.getEduUser().getTrainCategoryName());
				//dataMap.put("trainOrgName", exitApply.getEduUser().getTrainOrgName());
				dataMap.put("trainOrgName", doctor.getOrgName());
				dataMap.put("applyMakeUpCou",form.getApplyMakeUpCou());
				dataMap.put("teacherSugg", form.getTeacherSugg());
				String path = "/jsp/xjgl/print/makeUp.docx";//模板
				temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
				name = "课程补考申请表.docx";  
			}
			if (UserChangeApplyTypeEnum.OutStudy.getId().equals(exitApplyType)) {
				dataMap.put("trainTypeName",exitApply.getEduUser().getTrainCategoryName());
				dataMap.put("trainOrgName",  exitApply.getEduUser().getTrainOrgName());
				dataMap.put("destination", form.getDestination());
				dataMap.put("startTime", form.getStartTime());
				dataMap.put("endTime", form.getEndTime());
				dataMap.put("teacherSugg", form.getTeacherSugg());
				dataMap.put("trainOrgSugg", form.getTrainOrgSugg());
				dataMap.put("studySuggg", form.getStudySuggg());
				dataMap.put("trainSugg", form.getTrainSugg());
				dataMap.put("applyReason", form.getApplyReason());
				
				String path = "/jsp/xjgl/print/outStudy.docx";//模板
				temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
				name = "外出学习申请表.docx";  
			}
			if (UserChangeApplyTypeEnum.ChangeTrainType.getId().equals(exitApplyType)) {
				String teacherName="";
				if(StringUtil.isNotBlank(exitApply.getEduUser().getFirstTeacher())){
					teacherName=exitApply.getEduUser().getFirstTeacher();
				}
				if(StringUtil.isNotBlank(exitApply.getEduUser().getSecondTeacher())){
					teacherName=teacherName+" "+exitApply.getEduUser().getSecondTeacher();
				}
				
				dataMap.put("teacherName",teacherName);
				dataMap.put("trainTypeName", exitApply.getEduUser().getTrainCategoryName());
				dataMap.put("willTrainType", form.getWillTrainType());
				dataMap.put("docQualifiedNo", form.getDocQualifiedNo());
				dataMap.put("qualifiedNo", form.getQualifiedNo());
				dataMap.put("applyReason", form.getApplyReason());
				dataMap.put("teacherSugg", form.getTeacherSugg());
				dataMap.put("trainOrgSugg", form.getTrainOrgSugg());
				dataMap.put("postgraduSchSugg", form.getPostgraduSchSugg());
				
				String path = "/jsp/xjgl/print/changeTrainType.docx";//模板
				temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
				name = "更改培养类型申请表.docx";
			}
			if(UserChangeApplyTypeEnum.ChangeTeacher.getId().equals(exitApplyType)){
				dataMap.put("trainTypeName", exitApply.getEduUser().getTrainCategoryName());
				dataMap.put("applyReason", form.getApplyReason());
				dataMap.put("teacherSugg", form.getTeacherSugg());
				dataMap.put("swichTeachSugg", form.getSwichTeachSugg());
				dataMap.put("trainOrgSugg", form.getTrainOrgSugg());
				dataMap.put("switchOrgSugg", form.getSwitchOrgSugg());
				dataMap.put("postgraduSchSugg", form.getPostgraduSchSugg());
				
				String path = "/jsp/xjgl/print/changeTeacher.docx";//模板
				temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
				name = "更换导师申请表.docx";
			}
			if (UserChangeApplyTypeEnum.DelayExam.getId().equals(exitApplyType)) {
				dataMap.put("trainTypeName",exitApply.getEduUser().getTrainCategoryName());
				dataMap.put("delayExamTime", form.getDelayExamTime());
				dataMap.put("makeUpTime", form.getMakeUpTime());
				dataMap.put("delayCourName", form.getDelayCourName());
				dataMap.put("applyReason", form.getApplyReason());
				dataMap.put("postgraduSchSugg", form.getPostgraduSchSugg());
				
				String path = "/jsp/xjgl/print/delayExam.docx";//模板
				temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
				name = "缓考申请表.docx";
			}
			if (UserChangeApplyTypeEnum.DelayStudy.getId().equals(exitApplyType)) {
				dataMap.put("trainTypeName",exitApply.getEduUser().getTrainCategoryName());
				dataMap.put("delayStudyTime", form.getDelayStudyTime());
				dataMap.put("againStudyTime", form.getAgainStudyTime());
				dataMap.put("delayStudycourName", form.getDelayStudycourName());
				dataMap.put("applyReason", form.getApplyReason());
				dataMap.put("postgraduSchSugg", form.getPostgraduSchSugg());
				dataMap.put("teacherSugg", form.getTeacherSugg());
				
				String path = "/jsp/xjgl/print/delayStudy.docx";//模板
				temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
				name = "缓修申请表.docx";
			}
			if (UserChangeApplyTypeEnum.LeaveSchool.getId().equals(exitApplyType)) {
				dataMap.put("sex", exitApply.getSysUser().getSexName());
				dataMap.put("studyDegree", exitApply.getEduUser().getTrainTypeName());
				dataMap.put("trainTypeName",exitApply.getEduUser().getTrainCategoryName());
				dataMap.put("applyReason", form.getApplyReason());
				dataMap.put("teacherSugg", form.getTeacherSugg());
				dataMap.put("trainOrgSugg", form.getTrainOrgSugg());
				dataMap.put("postgraduSchSugg", form.getPostgraduSchSugg());
				
				String path = "/jsp/xjgl/print/leaveSchool.docx";//模板
				temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
				name = "退学申请表.docx";
			}
			if (UserChangeApplyTypeEnum.TransferStudy.getId().equals(exitApplyType)) {
				dataMap.put("sex", exitApply.getSysUser().getSexName());
				dataMap.put("studyDegree", exitApply.getEduUser().getTrainTypeName());
				dataMap.put("trainTypeName",exitApply.getEduUser().getTrainCategoryName());
				dataMap.put("applyReason", form.getApplyReason());
				dataMap.put("teacherSugg", form.getTeacherSugg());
				dataMap.put("trainOrgSugg", form.getTrainOrgSugg());
				dataMap.put("postgraduSchSugg", form.getPostgraduSchSugg());
				
				String path = "/jsp/xjgl/print/TransferStudy.docx";//模板
				temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
				name = "转学申请表.docx";
			}
			if (UserChangeApplyTypeEnum.StopStudy.getId().equals(exitApplyType)) {
				String yearY="  ";String monthY="  ";String dayY="  ";
				String yearN="  ";String monthN="  ";String dayN="  ";
				if(StringUtil.isNotBlank(form.getStopStudyStarTime())){
					String time[]=form.getStopStudyStarTime().split("-");
					yearY=time[0];
					monthY=time[1];
					dayY=time[2];
				}else{
					yearY="  - ";
					monthY=" - ";
					dayY=" - ";
				}
				if (StringUtil.isNotBlank(form.getStopStudyEndTime())) {
					String timeN[]=form.getStopStudyEndTime().split("-");
					yearN=timeN[0];
					monthN=timeN[1];
					dayN=timeN[2];
				}else{
					yearN=" - ";
					monthN=" - ";
					dayN=" - ";
				}
				String timeInfoY="";String timeInfoN="";String time="";
				timeInfoY=yearY+"年"+monthY+"月"+dayY+"日"+"至";
				timeInfoN=yearN+"年"+monthN+"月"+dayN+"日";
				time=timeInfoY+timeInfoN;
				
				dataMap.put("studyDegree", exitApply.getEduUser().getTrainTypeName());
				dataMap.put("sex", exitApply.getSysUser().getSexName());
				dataMap.put("trainTypeName",exitApply.getEduUser().getTrainCategoryName());
				dataMap.put("applyReason", form.getApplyReason());
				dataMap.put("time", time);
				dataMap.put("teacherSugg", form.getTeacherSugg());
				dataMap.put("trainOrgSugg", form.getTrainOrgSugg());
				dataMap.put("postgraduSchSugg", form.getPostgraduSchSugg());
				
				String path = "/jsp/xjgl/print/stopStudy.docx";//模板
				temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
				name = "休学申请表.docx";
			}
			if (UserChangeApplyTypeEnum.BackStudy.getId().equals(exitApplyType)) {
				String yearY="  ";String monthY="  ";String dayY="  ";
				String yearN="  ";String monthN="  ";String dayN="  ";
				if(StringUtil.isNotBlank(form.getStopStudyStarTime())){
					String time[]=form.getStopStudyStarTime().split("-");
					yearY=time[0];
					monthY=time[1];
					dayY=time[2];
				}else{
					yearY="  - ";
					monthY=" - ";
					dayY=" - ";
				}
				if (StringUtil.isNotBlank(form.getStopStudyEndTime())) {
					String timeN[]=form.getStopStudyEndTime().split("-");
					yearN=timeN[0];
					monthN=timeN[1];
					dayN=timeN[2];
				}else{
					yearN=" - ";
					monthN=" - ";
					dayN=" - ";
				}
				String timeInfoY="";String timeInfoN="";String time="";
				timeInfoY=yearY+"年"+monthY+"月"+dayY+"日"+"至";
				timeInfoN=yearN+"年"+monthN+"月"+dayN+"日";
				time=timeInfoY+timeInfoN;
				
				dataMap.put("studyDegree", exitApply.getEduUser().getTrainTypeName());
				dataMap.put("sex", exitApply.getSysUser().getSexName());
				dataMap.put("trainTypeName",exitApply.getEduUser().getTrainCategoryName());
				dataMap.put("applyReason", form.getApplyReason());
				dataMap.put("time", time);
				dataMap.put("teacherSugg", form.getTeacherSugg());
				dataMap.put("trainOrgSugg", form.getTrainOrgSugg());
				dataMap.put("postgraduSchSugg", form.getPostgraduSchSugg());
				
				String path = "/jsp/xjgl/print/backStudy.docx";//模板
				temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
				name = "复学申请表.docx";
			}
			if (UserChangeApplyTypeEnum.DelayGraduate.getId().equals(exitApplyType)) {
				dataMap.put("studyDegree", exitApply.getEduUser().getTrainTypeName());
				dataMap.put("sex", exitApply.getSysUser().getSexName());
				dataMap.put("trainTypeName",exitApply.getEduUser().getTrainCategoryName());
				dataMap.put("applyReason", form.getApplyReason());
				dataMap.put("teacherSugg", form.getTeacherSugg());
				dataMap.put("trainOrgSugg", form.getTrainOrgSugg());
				dataMap.put("postgraduSchSugg", form.getPostgraduSchSugg());
				
				String path = "/jsp/xjgl/print/delayGraduate.docx";//模板
				temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
				name = "研究生延期申请表.docx";
			}
			if (UserChangeApplyTypeEnum.ChangeMajor.getId().equals(exitApplyType)) {
				dataMap.put("swichmajorName", form.getSwichmajorName());
				dataMap.put("applyReason", form.getApplyReason());
				dataMap.put("teacherSugg", form.getTeacherSugg());
				dataMap.put("swichTeachSugg", form.getSwitchOrgSugg());
				dataMap.put("trainOrgSugg", form.getTrainOrgSugg());
				dataMap.put("switchOrgSugg", form.getSwitchOrgSugg());
				dataMap.put("postgraduSchSugg", form.getPostgraduSchSugg());
				
				String path = "/jsp/xjgl/print/changeMajor.docx";//模板
				temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
				name = "更换专业申请表.docx";
			}
			if(UserChangeApplyTypeEnum.AddStudy.getId().equals(exitApplyType)){
				dataMap.put("trainTypeName",exitApply.getEduUser().getTrainCategoryName());
				dataMap.put("delayStudyTime", form.getDelayStudyTime());
				dataMap.put("againStudyTime", form.getAgainStudyTime());
				dataMap.put("delayStudycourName", form.getDelayStudycourName());
				dataMap.put("applyReason", form.getApplyReason());
				dataMap.put("postgraduSchSugg", form.getPostgraduSchSugg());
				dataMap.put("teacherSugg", form.getTeacherSugg());
				
				String path = "/jsp/xjgl/print/addStudy.docx";//模板
				temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
				name = "补修申请表.docx";
			}
			if(temeplete!=null){
				response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +""); 
				response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
				ServletOutputStream out = response.getOutputStream ();
				(new SaveToZipFile (temeplete)).save (out);
				out.flush ();
			}
		}
	}
}

	@RequestMapping("/uploadForm")
	public String uploadForm(Model model,String applyTypeId){
		if(StringUtil.isNotBlank(applyTypeId)){
			List<String> urlList = iChangeApplyBiz.searchUrlList(applyTypeId,"filePath");
			SubmitApplyForm form = new SubmitApplyForm();
			form.setChangeApplyUrlList(urlList);
			model.addAttribute("form",form);
		}
		return "xjgl/plat/uploadForm";
	}
	@RequestMapping(value={"/downloadTblFile"})
	public void downloadTblFile(String applyTypeId, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, Exception{
		String folder = System.getProperty("java.io.tmpdir") + File.separator + PkUtil.getUUID();
		String dir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "xjglTblFile" + File.separator + applyTypeId;
		File dirFile = new File(dir);
		List<String> fileList = iChangeApplyBiz.searchUrlList(applyTypeId,"fileName");
		ZipUtil.makeDirectoryToZip(dirFile,fileList,new File(folder+".zip"), 7);
		String fileName = UserChangeApplyTypeEnum.getNameById(applyTypeId)+"申请表格.zip";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());

		byte[] data = getByte(new File(folder+".zip"));
		if (data != null) {
			outputStream.write(data);
		}
		outputStream.flush();
		outputStream.close();
	}
	public static byte[] getByte(File file) throws Exception {
		if (file == null) {
			return null;
		}
		try {
			int length = (int) file.length();
			if (length > Integer.MAX_VALUE) {    //当文件的长度超过了int的最大值
				System.out.println("this file is max ");
			}
			FileInputStream stream = new FileInputStream(file);
			ByteArrayOutputStream out = new ByteArrayOutputStream(length);
			byte[] b = new byte[length];
			int n;
			while ((n = stream.read(b)) != -1)
				out.write(b, 0, n);
			stream.close();
			out.close();
			return out.toByteArray();
		} catch (IOException e) {
		}
		return null;
	}

	@RequestMapping("/uploadInstruction")
	public String uploadInstruction(Model model,String viewFlag,String applyTypeId){
		PubFile file = iChangeApplyBiz.searchInstrutionInfo(applyTypeId);
		if(null != file){
			System.out.println(file.getFileContent());
			model.addAttribute("content",new String(file.getFileContent()==null?"".getBytes():file.getFileContent()));
		}
		return "xjgl/plat/uploadInstruction";
	}

	@RequestMapping("/submitInstruction")
	@ResponseBody
	public String submitInstruction(String applyTypeId,String content){
		int result=iChangeApplyBiz.saveInstructionInfo(applyTypeId,content);
		if (result!=GlobalConstant.ZERO_LINE) {
			return "上传成功！";
		}
		return "上传失败！";
	}
}