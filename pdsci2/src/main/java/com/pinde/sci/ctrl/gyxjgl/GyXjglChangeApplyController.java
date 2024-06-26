package com.pinde.sci.ctrl.gyxjgl;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.edu.IEduUserBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.gyxjgl.IGyChangeApplyBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.enums.gyxjgl.UserAwardApplyTypeEnum;
import com.pinde.sci.enums.gyxjgl.UserChangeApplyStatusEnum;
import com.pinde.sci.enums.gyxjgl.UserChangeApplyTypeEnum;
import com.pinde.sci.form.gyxjgl.SubmitApplyForm;
import com.pinde.sci.form.gyxjgl.UserChangeApplyForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.gyxjgl.XjEduStudentChangeExt;
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
@RequestMapping("/gyxjgl/change/apply")
public class GyXjglChangeApplyController extends GeneralController{
	@Autowired
	private IGyChangeApplyBiz iChangeApplyBiz;
	@Autowired
	private IUserBiz iUserBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IEduUserBiz eduUserBiz;
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
			List<EduUserChangeApply> eduUserChangeApplies=iChangeApplyBiz.searchEduUserChangeApply(eduChangeUser,null);
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
			return "gyxjgl/student/changeApply";
		}
		return "gyxjgl/student/application";
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
	 * awardFlag 区分异动申请和奖助学金申请
	 */
	@RequestMapping(value={"/saveTblFile"})
	@ResponseBody
	public String saveTblFile(SubmitApplyForm form,String applyTypeId,String awardFlag) throws Exception{
		if(null != form && null != form.getChangeApplyUrlList()){
			Iterator<String> it = form.getChangeApplyUrlList().iterator();
			while(it.hasNext()) {
				if(StringUtil.isBlank(it.next())) {
					it.remove();//去除页面初始上传附件模板的展示
				}
			}
		}
		int result=iChangeApplyBiz.saveTblFileInfo(form,applyTypeId,awardFlag);
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
	public void exportExcel(EduUserChangeApply changeApply, EduUser eduUser, String awardFlag, HttpServletResponse response) throws Exception {
		String headName = GlobalConstant.FLAG_Y.equals(awardFlag)?"奖助审核一览表":"异动审核一览表";
		String[] headLines = null;
		headLines = new String[]{headName};
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
		List<String> args = new ArrayList<>();
		if(GlobalConstant.FLAG_Y.equals(awardFlag)){
			for(UserAwardApplyTypeEnum en : UserAwardApplyTypeEnum.values()){
				args.add(en.getId());
			}
		}else{
			for(UserChangeApplyTypeEnum en : UserChangeApplyTypeEnum.values()){
				args.add(en.getId());
			}
		}
		paramMap.put("applyTypeList",args);
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
		String fileName = headName+".xls";
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
		//对应导师异动申请数据
		eduUser.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		paramMap.put("changeApply", changeApply); 
		paramMap.put("statusIdList", statusIdList);
		paramMap.put("eduUser", eduUser);
		List<String> args = new ArrayList<>();
		for(UserChangeApplyTypeEnum en : UserChangeApplyTypeEnum.values()){
			args.add(en.getId());
		}
		paramMap.put("applyTypeList",args);
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
		return "gyxjgl/plat/tranAudit";
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
					dataMap.put("trainOrgName", doctor.getOrgName());
					dataMap.put("applyMakeUpCou",form.getApplyMakeUpCou());
					dataMap.put("teacherSugg", form.getTeacherSugg());
					String path = "/jsp/gyxjgl/print/makeUp.docx";
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
					String path = "/jsp/gyxjgl/print/outStudy.docx";
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
					String path = "/jsp/gyxjgl/print/changeTrainType.docx";
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
					String path = "/jsp/gyxjgl/print/changeTeacher.docx";
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
					String path = "/jsp/gyxjgl/print/delayExam.docx";
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
					String path = "/jsp/gyxjgl/print/delayStudy.docx";
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
					String path = "/jsp/gyxjgl/print/leaveSchool.docx";
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
					String path = "/jsp/gyxjgl/print/TransferStudy.docx";
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
					String path = "/jsp/gyxjgl/print/stopStudy.docx";
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
					String path = "/jsp/gyxjgl/print/backStudy.docx";
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
					String path = "/jsp/gyxjgl/print/delayGraduate.docx";
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
					String path = "/jsp/gyxjgl/print/changeMajor.docx";
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
					String path = "/jsp/gyxjgl/print/addStudy.docx";
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
	public String uploadForm(Model model,String applyTypeId,String awardFlag){
		if(StringUtil.isNotBlank(applyTypeId)){
			List<String> urlList = iChangeApplyBiz.searchUrlList(applyTypeId,"filePath");
			SubmitApplyForm form = new SubmitApplyForm();
			form.setChangeApplyUrlList(urlList);
			model.addAttribute("form",form);
		}
		return "gyxjgl/plat/uploadForm";
	}
	@RequestMapping(value={"/downloadTblFile"})
	public void downloadTblFile(String applyTypeId, String awardFlag, HttpServletResponse response) throws Exception{
		String folder = System.getProperty("java.io.tmpdir") + File.separator + PkUtil.getUUID();
		String dir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "xjglTblFile" + File.separator + applyTypeId;
		File dirFile = new File(dir);
		List<String> fileList = iChangeApplyBiz.searchUrlList(applyTypeId,"fileName");
		ZipUtil.makeDirectoryToZip(dirFile,fileList,new File(folder+".zip"), 7);
		String applyTypeName = GlobalConstant.FLAG_Y.equals(awardFlag)?UserAwardApplyTypeEnum.getNameById(applyTypeId):UserChangeApplyTypeEnum.getNameById(applyTypeId);
		String fileName = applyTypeName+"申请表格.zip";
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

	/**
	 * 上传说明
	 * @param model
	 * @param viewFlag 查看或上传操作
	 * @param applyTypeId 申请类型
	 * @param awardFlag 奖助学金申请标识
     * @return
     */
	@RequestMapping("/uploadInstruction")
	public String uploadInstruction(Model model,String viewFlag,String applyTypeId,String awardFlag){
		PubFile file = iChangeApplyBiz.searchInstrutionInfo(applyTypeId,awardFlag);
		if(null != file){
			System.out.println(file.getFileContent());
			model.addAttribute("content",new String(file.getFileContent()==null?"".getBytes():file.getFileContent()));
		}
		return "gyxjgl/plat/uploadInstruction";
	}

	@RequestMapping("/submitInstruction")
	@ResponseBody
	public String submitInstruction(String applyTypeId,String content,String awardFlag){
		int result=iChangeApplyBiz.saveInstructionInfo(applyTypeId,content,awardFlag);
		if (result!=GlobalConstant.ZERO_LINE) {
			return "上传成功！";
		}
		return "上传失败！";
	}

	/**
	 * 奖助学金管理（异动申请类似）
	 */
	@RequestMapping("/awardApply")
	public String awardApply(String userFlow,String applyTypeId,String applyTime,String applyStatus,Integer currentPage,HttpServletRequest request,Model model){
		if (StringUtil.isBlank(userFlow)) {
			userFlow=GlobalContext.getCurrentUser().getUserFlow();
		}
		EduUserChangeApply eduChangeUser = new EduUserChangeApply();
		eduChangeUser.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		eduChangeUser.setApplyTime(applyTime);
		eduChangeUser.setApplyTypeId(applyTypeId);
		eduChangeUser.setStatusId(applyStatus);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<EduUserChangeApply> eduUserChangeApplies = iChangeApplyBiz.searchEduUserChangeApply(eduChangeUser,"award");
		Map<String, String>userAuditTimeMap=new HashMap<String,String>();
		Map<String, String>userAuditPersonMap=new HashMap<String,String>();
		for(EduUserChangeApply edu:eduUserChangeApplies){
			if(UserChangeApplyStatusEnum.Approve.getId().equals(edu.getStatusId())|| UserChangeApplyStatusEnum.NotApprove.getId().equals(edu.getStatusId())) {
				String key=edu.getRecordFlow();
				SubmitApplyForm form=JaxbUtil.converyToJavaBean(edu.getContent(), SubmitApplyForm.class);
				userAuditTimeMap.put(key, form.getAuditDate());
				userAuditPersonMap.put(key, form.getAuditPerson());
			}
		}
		model.addAttribute("eduUserChangeApplies", eduUserChangeApplies);
		model.addAttribute("userAuditTimeMap", userAuditTimeMap);
		model.addAttribute("userAuditPersonMap", userAuditPersonMap);
		EduUser user=iChangeApplyBiz.readEduUser(userFlow);
		SysUser sysUser=iUserBiz.findByFlow(userFlow);
		ResDoctor doctor = iChangeApplyBiz.searchByUserFlow(userFlow);
		model.addAttribute("user", user);
		model.addAttribute("sysUser", sysUser);
		model.addAttribute("doctor", doctor);
		return "gyxjgl/student/awardApplyList";
	}
	/**
	 * 奖助学金申请页面
	 */
	@RequestMapping("/editAwardApply")
	public String editAwardApply(String userFlow,String recordFlow,Model model){
		if (StringUtil.isBlank(userFlow)) {
			userFlow=GlobalContext.getCurrentUser().getUserFlow();
		}
		EduUser user=iChangeApplyBiz.readEduUser(userFlow);
		SysUser sysUser=iUserBiz.findByFlow(userFlow);
		ResDoctor doctor = iChangeApplyBiz.searchByUserFlow(userFlow);
		if(StringUtil.isNotBlank(recordFlow)){
			EduUserChangeApply existApply=iChangeApplyBiz.readEduUserChangeApply(recordFlow);
			SubmitApplyForm form=JaxbUtil.converyToJavaBean(existApply.getContent(), SubmitApplyForm.class);
			model.addAttribute("form",form);
			model.addAttribute("existApply", existApply);
		}
		model.addAttribute("user", user);
		model.addAttribute("sysUser", sysUser);
		model.addAttribute("doctor", doctor);
		return "gyxjgl/student/editAwardApply";
	}
	@RequestMapping("/awardPrint")
	public void awardPrint(String recordFlow, HttpServletRequest request, HttpServletResponse response) throws Exception{
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
			if (null != eduUserList && eduUserList.size()>0) {
				XjEduStudentChangeExt exitApply = eduUserList.get(0);
				ResDoctor doctor = iChangeApplyBiz.searchByUserFlow(exitApply.getUserFlow());
				SubmitApplyForm form=JaxbUtil.converyToJavaBean(exitApply.getContent(), SubmitApplyForm.class);
				String exitApplyType=exitApply.getApplyTypeId();
				dataMap.put("sid",exitApply.getEduUser().getSid());
				dataMap.put("userName", exitApply.getSysUser().getUserName());
				dataMap.put("majorName",exitApply.getEduUser().getMajorName());
				for(UserAwardApplyTypeEnum en : UserAwardApplyTypeEnum.values()){
					if (en.getId().equals(exitApplyType)) {
						dataMap.put("applyTypeName", exitApplyType);
						dataMap.put("trainTypeName",exitApply.getEduUser().getTrainCategoryName());
						dataMap.put("trainOrgName", doctor.getOrgName());
						dataMap.put("teacherSugg", form.getTeacherSugg());
						String path = "/jsp/gyxjgl/print/awardBaseTemp.docx";
						temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
						name = en.getName()+"申请表.docx";
						break;
					}
				}
				if(null != temeplete){
					response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
					response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
					ServletOutputStream out = response.getOutputStream ();
					(new SaveToZipFile (temeplete)).save (out);
					out.flush ();
				}
			}
		}
	}

	@RequestMapping("/auditAwardApply")
	public String auditAwardApply(EduUserChangeApply userChangeApply,EduUser eduUser,Model model,Integer currentPage,HttpServletRequest request){
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
		List<String> args = new ArrayList<>();
		for(UserAwardApplyTypeEnum en : UserAwardApplyTypeEnum.values()){
			args.add(en.getId());
		}
		paramMap.put("applyTypeList",args);
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
		return "gyxjgl/plat/awardAuditList";
	}

	@RequestMapping("/employManage")
	public String employManage(String role,String sessionNumber,String userName,String majorName,Integer currentPage,HttpServletRequest request,Model model){
		PageHelper.startPage(currentPage,getPageSize(request));
		Map<String,String> map = new HashMap<String,String>();
		if(StringUtil.isBlank(role)){
			map.put("userFlow",GlobalContext.getCurrentUser().getUserFlow());
		}else{
			map.put("sessionNumber",sessionNumber);
			map.put("userName",userName);
			map.put("majorName",majorName);
		}
		List<JobIntensionInfo> dataList = iChangeApplyBiz.jobIntensionList(map);
		model.addAttribute("dataList",dataList);
		if(StringUtil.isBlank(role)){
			return "gyxjgl/student/intensionList";
		}
		return "gyxjgl/student/feedbackList";
	}

	@RequestMapping("/intensionAdd")
	public String intensionAdd(String recordFlow,Model model){
		if(StringUtil.isNotBlank(recordFlow)){
			JobIntensionInfo intension = iChangeApplyBiz.queryIntensionByFlow(recordFlow);
			model.addAttribute("intension",intension);
		}else{
			SysUser sysUser = GlobalContext.getCurrentUser();
			EduUser eduUser = eduUserBiz.readEduUser(sysUser.getUserFlow());
			model.addAttribute("eduUser",eduUser);
			model.addAttribute("sysUser",sysUser);
		}
		return "gyxjgl/student/intensionAdd";
	}
	@RequestMapping("/saveIntesion")
	@ResponseBody
	public String saveIntesion(JobIntensionInfo info){
		int num = iChangeApplyBiz.saveIntesion(info);
		if(num > 0){
			return GlobalConstant.OPERATE_SUCCESSED;
		}
		return GlobalConstant.OPERATE_FAIL;
	}
	@RequestMapping("/delIntension")
	@ResponseBody
	public String delIntension(String recordFlow){
		int num = iChangeApplyBiz.delIntesion(recordFlow);
		if(num > 0){
			return GlobalConstant.DELETE_SUCCESSED;
		}
		return GlobalConstant.DELETE_FAIL;
	}

	@RequestMapping("/feedback")
	public String feedback(String sessionNumber,String userName,String majorName,Integer currentPage,HttpServletRequest request,Model model){
		PageHelper.startPage(currentPage,getPageSize(request));
		Map<String,String> map = new HashMap<String,String>();
		map.put("sessionNumber",sessionNumber);
		map.put("userName",userName);
		map.put("majorName",majorName);
		List<EmploymentFeedback> dataList = iChangeApplyBiz.feedbackList(map);
		model.addAttribute("dataList",dataList);
		return "gyxjgl/student/feedback";
	}
	@RequestMapping("/feedbackAdd")
	public String feedbackAdd(String recordFlow,Model model){
		if(StringUtil.isNotBlank(recordFlow)){
			EmploymentFeedback feedback = iChangeApplyBiz.queryFeedbackByFlow(recordFlow);
			model.addAttribute("feedback",feedback);
		}
		return "gyxjgl/student/feedbackAdd";
	}
	@RequestMapping("/saveFeedback")
	@ResponseBody
	public String saveFeedback(EmploymentFeedback info){
		int num = iChangeApplyBiz.saveFeedback(info);
		if(num > 0){
			return GlobalConstant.OPERATE_SUCCESSED;
		}
		return GlobalConstant.OPERATE_FAIL;
	}
	@RequestMapping("/delFeedback")
	@ResponseBody
	public String delFeedback(String recordFlow){
		int num = iChangeApplyBiz.delFeedback(recordFlow);
		if(num > 0){
			return GlobalConstant.DELETE_SUCCESSED;
		}
		return GlobalConstant.DELETE_FAIL;
	}
}