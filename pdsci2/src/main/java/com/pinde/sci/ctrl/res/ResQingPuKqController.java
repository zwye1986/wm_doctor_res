package com.pinde.sci.ctrl.res;

import com.alibaba.fastjson.JSON;
import com.pinde.core.jspform.ItemGroupData;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.jsres.IJsResUserBlackBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.biz.test.ITestResultBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.dao.base.SysUserDeptMapper;
import com.pinde.sci.enums.jszy.JszyResTrainYearEnum;
import com.pinde.sci.enums.jszy.JszyTrainCategoryEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.res.*;
import com.pinde.sci.enums.sch.SchUnitEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.form.res.ResRecForm;
import com.pinde.sci.form.sch.SchGradeFrom;
import com.pinde.sci.form.sch.SelectDept;
import com.pinde.sci.form.sch.SelectDeptForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
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

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

@Controller
@RequestMapping("/res/qingpuKq")
public class ResQingPuKqController extends GeneralController{
	@Autowired
	private IResGradeBiz resGradeBiz;
	@Autowired
	private ISchRotationBiz schRotationtBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResEnterOpenCfgBiz enterOpenCfgBiz;
	@Autowired
	private IResPowerCfgBiz resPowerCfgBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private IResDoctorProcessBiz resDoctorProcessBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private ISchRotationDeptBiz rotationDeptBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private ISchRotationGroupBiz schRotationtGroupBiz;
	@Autowired
	private ISchDoctorDeptBiz schDoctorDeptBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private ISchDoctorAbsenceBiz schDoctorAbsenceBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private ISchDeptExternalRelBiz deptExtRelBiz;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private ITestResultBiz resultBiz;
	@Autowired
	private ISchArrangeBiz arrangeBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IResLectureEvaDetailBiz resLectureEvaDetailBiz;
    @Autowired
    private IDiscipleBiz discipleBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private IResDoctorDelayTeturnBiz resDoctorDelayTeturnBiz;
	@Autowired
	private IResDoctorRecruitBiz doctorRecruitBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IZseyHrKqMonthBiz zseyHrKqMonthBiz;
	@Autowired
	private IJsResUserBlackBiz blackBiz;
	@Autowired
	private ISchDeptRelBiz deptRelBiz;
	@Autowired
	private IQingPuKqBiz qingPuKqBiz;
	@Autowired
	private SysUserDeptMapper sysUserDeptMapper;
	@Autowired
	private IResEntryReportBiz reportBiz;

	/**
	 *  学员功能
	 */
	@RequestMapping("/appealList")
	public String appealList(QingpuDoctorKq kq,Integer currentPage,HttpServletRequest request,Model model){
		SysUser currentUser = GlobalContext.getCurrentUser();
		kq.setDoctorFlow(currentUser.getUserFlow());
		kq.setQingpuKqTypeId(QingpuKqTypeEnum.Appeal.getId());
		kq.setOrgFlow(currentUser.getOrgFlow());
		PageHelper.startPage(currentPage,getPageSize(request));
		List<QingpuDoctorKq> kqList = qingPuKqBiz.searchQingpuDoctorKq(kq);
		model.addAttribute("kqList",kqList);
		return "/res/qingpuKq/appealList";
	}

	@RequestMapping("/editAppeal")
	public String editAppeal(String recordFlow,String roleFlag,Model model){
		if(StringUtil.isNotBlank(recordFlow)){
			QingpuDoctorKq kq = qingPuKqBiz.readQingpuDoctorKq(recordFlow);
			model.addAttribute("kq",kq);
		}
		if("doctor".equals(roleFlag)){
			SysUser currentUser = GlobalContext.getCurrentUser();
			model.addAttribute("doctor",doctorBiz.readDoctor(currentUser.getUserFlow()));
			ResDoctorSchProcess process = resDoctorProcessBiz.searchCurrDept(currentUser);
			model.addAttribute("process",process);
		}
		return "/res/qingpuKq/editAppeal";
	}

	@RequestMapping("/saveAppeal")
	@ResponseBody
	public String saveAppeal(QingpuDoctorKq kq,String deptFlow){
		if(StringUtil.isBlank(kq.getDoctorFlow())){
			kq.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
			kq.setDoctorName(GlobalContext.getCurrentUser().getUserName());
			kq.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		}
		kq.setQingpuKqTypeId(QingpuKqTypeEnum.Appeal.getId());
		kq.setQingpuKqTypeName(QingpuKqTypeEnum.Appeal.getName());
		kq.setQingpuKqTypeDetailName(QingpuKqAppealTypeEnum.getNameById(kq.getQingpuKqTypeDetailId()));

		Map<String,Object> paramMap = new HashMap<>();
		String wsId = (String)GlobalContext.getSession().getAttribute(GlobalConstant.CURRENT_WS_ID);
		paramMap.put("wsId",wsId);
		//查找教秘res_secretary_role_flow
		String secretaryFlow = InitConfig.getSysCfg("res_secretary_role_flow");
		paramMap.put("roleFlow",secretaryFlow);
		paramMap.put("deptFlow",deptFlow);
		List<SysUser> sysUserList2 = userBiz.searchUserWithRole(paramMap);
		if(sysUserList2!=null&&sysUserList2.size()>0){
			kq.setHeadFlow(sysUserList2.get(0).getUserFlow());
			kq.setHeadName(sysUserList2.get(0).getUserName());
		}
		qingPuKqBiz.editQingpuDoctorKq(kq);
		return "1";
	}

	@RequestMapping("/leaveList")
	public String leaveList(QingpuDoctorKq kq,Integer currentPage,HttpServletRequest request, Model model){
		SysUser currentUser = GlobalContext.getCurrentUser();
		kq.setDoctorFlow(currentUser.getUserFlow());
		kq.setQingpuKqTypeId(QingpuKqTypeEnum.Leave.getId());
		kq.setOrgFlow(currentUser.getOrgFlow());
		PageHelper.startPage(currentPage,getPageSize(request));
		List<QingpuDoctorKq> kqList = qingPuKqBiz.searchQingpuDoctorKq(kq);
		model.addAttribute("kqList",kqList);
		return "/res/qingpuKq/leaveList";
	}

	@RequestMapping("/editLeave")
	public String editLeave(String recordFlow,String roleFlag,Model model){
		if(StringUtil.isNotBlank(recordFlow)){
			QingpuDoctorKq kq = qingPuKqBiz.readQingpuDoctorKq(recordFlow);
			model.addAttribute("kq",kq);
		}
		if("doctor".equals(roleFlag)){
			SysUser currentUser = GlobalContext.getCurrentUser();
			model.addAttribute("doctor",doctorBiz.readDoctor(currentUser.getUserFlow()));
			ResDoctorSchProcess process = resDoctorProcessBiz.searchCurrDept(currentUser);
			model.addAttribute("process",process);
		}
		return "/res/qingpuKq/editLeave";
	}

	@RequestMapping("/saveLeave")
	@ResponseBody
	public String saveLeave(QingpuDoctorKq kq,String deptFlow){
		if(StringUtil.isBlank(kq.getDoctorFlow())){
			kq.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
			kq.setDoctorName(GlobalContext.getCurrentUser().getUserName());
			kq.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		}
		kq.setQingpuKqTypeId(QingpuKqTypeEnum.Leave.getId());
		kq.setQingpuKqTypeName(QingpuKqTypeEnum.Leave.getName());
		kq.setQingpuKqTypeDetailName(QingpuKqLeaveTypeEnum.getNameById(kq.getQingpuKqTypeDetailId()));

		Map<String,Object> paramMap = new HashMap<>();
		String wsId = (String)GlobalContext.getSession().getAttribute(GlobalConstant.CURRENT_WS_ID);
		paramMap.put("wsId",wsId);
		//查找医院管理员res_admin_role_flow
		String adminFlow = InitConfig.getSysCfg("res_admin_role_flow");
		paramMap.put("roleFlow",adminFlow);
		paramMap.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
		List<SysUser> sysUserList = userBiz.searchUserWithRole(paramMap);
		if(sysUserList!=null&&sysUserList.size()>0){
			kq.setManagerFlow(sysUserList.get(0).getUserFlow());
			kq.setManagerName(sysUserList.get(0).getUserName());
		}
		//查找教秘res_secretary_role_flow
		String secretaryFlow = InitConfig.getSysCfg("res_secretary_role_flow");
		paramMap.put("roleFlow",secretaryFlow);
		paramMap.put("deptFlow",deptFlow);
		List<SysUser> sysUserList2 = userBiz.searchUserWithRole(paramMap);
		if(sysUserList2!=null&&sysUserList2.size()>0){
			kq.setHeadFlow(sysUserList2.get(0).getUserFlow());
			kq.setHeadName(sysUserList2.get(0).getUserName());
		}
		qingPuKqBiz.editQingpuDoctorKq(kq);
		return "1";
	}

	@RequestMapping("/delKq")
	@ResponseBody
	public String delKq(QingpuDoctorKq kq){
		kq.setRecordStatus("N");
		qingPuKqBiz.editQingpuDoctorKq(kq);
		return "1";
	}

	/**
	 * 请假审批列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/leaveAuditList/{roleFlag}"},method={RequestMethod.GET,RequestMethod.POST})
	public String leaveAuditList(@PathVariable String roleFlag, QingpuDoctorKq kq, Integer currentPage, HttpServletRequest request,  Model model){
		SysUser currUser = GlobalContext.getCurrentUser();
		kq.setQingpuKqTypeId(QingpuKqTypeEnum.Leave.getId());
		kq.setOrgFlow(currUser.getOrgFlow());
		if("teacher".equals(roleFlag)){//带教
			kq.setTeacherFlow(currUser.getUserFlow());
		}
		if("head".equals(roleFlag)){
			kq.setHeadFlow(currUser.getUserFlow());
		}
		if("manager".equals(roleFlag)){
			kq.setManagerFlow(currUser.getUserFlow());
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<QingpuDoctorKq> kqList = qingPuKqBiz.searchQingpuDoctorKq(kq);
		model.addAttribute("kqList", kqList);
		model.addAttribute("roleFlag", roleFlag);

		// 医院管理员
		if(GlobalConstant.RES_ROLE_SCOPE_MANAGER.equals(roleFlag)){
			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(currUser.getOrgFlow());
			model.addAttribute("schDeptList", schDeptList);
		}

		return "/res/qingpuKq/leaveAuditList";
	}

	/**
	 * 申诉审批列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/appealAuditList/{roleFlag}"},method={RequestMethod.GET,RequestMethod.POST})
	public String appealAuditList(@PathVariable String roleFlag, QingpuDoctorKq kq, Integer currentPage, HttpServletRequest request,  Model model){
		SysUser currUser = GlobalContext.getCurrentUser();
		kq.setQingpuKqTypeId(QingpuKqTypeEnum.Appeal.getId());
		kq.setOrgFlow(currUser.getOrgFlow());
		if("teacher".equals(roleFlag)){//带教
			kq.setTeacherFlow(currUser.getUserFlow());
		}
		if("head".equals(roleFlag)){
			kq.setHeadFlow(currUser.getUserFlow());
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<QingpuDoctorKq> kqList = qingPuKqBiz.searchQingpuDoctorKq(kq);
		model.addAttribute("kqList", kqList);
		model.addAttribute("roleFlag", roleFlag);
		return "/res/qingpuKq/appealAuditList";
	}

	/**
	 * 打开填写审核意见页面
	 */
	@RequestMapping(value="/saveKqInfo")
	public String saveKqInfo(String recordFlow,String agreeFlag,String roleFlag,Model model){
		model.addAttribute("recordFlow",recordFlow);
		model.addAttribute("agreeFlag",agreeFlag);
		model.addAttribute("roleFlag",roleFlag);
		return "res/qingpuKq/saveKqInfo";
	}

	/**
	 * 保存考勤审批
	 * @param
	 * @return
	 */
	@RequestMapping(value="/saveKqAudit")
	@ResponseBody
	public String saveKqAudit(QingpuDoctorKq kq) throws ParseException {
		SysUser currtUser = GlobalContext.getCurrentUser();
		if(StringUtil.isNotBlank(kq.getRecordFlow())){
			boolean AppealFlag = false;
			QingpuDoctorKq kq0 = qingPuKqBiz.readQingpuDoctorKq(kq.getRecordFlow());
			if(QingpuKqTypeEnum.Appeal.getId().equals(kq0.getQingpuKqTypeId())){
				AppealFlag = true;
			}
			SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String time = sdf0.format(new Date());
			if(StringUtil.isNotBlank(kq.getTeacherAgreeFlag())){
				kq.setTeacherAuditTime(time);
				if(AppealFlag && "Y".equals(kq.getTeacherAgreeFlag())){
					kq.setManagerAgreeFlag(GlobalConstant.RECORD_STATUS_Y);
				}
			}
			if(StringUtil.isNotBlank(kq.getHeadAgreeFlag())){
				kq.setHeadAuditTime(time);
				if(AppealFlag && "Y".equals(kq.getHeadAgreeFlag())){
					kq.setManagerAgreeFlag(GlobalConstant.RECORD_STATUS_Y);
				}
			}
			if(StringUtil.isNotBlank(kq.getManagerAgreeFlag())){
				kq.setManagerAuditTime(time);
			}
			int result = qingPuKqBiz.editQingpuDoctorKq(kq);
			if(GlobalConstant.ZERO_LINE != result){
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 违纪登记列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/submitList/{roleFlag}"},method={RequestMethod.GET,RequestMethod.POST})
	public String submitList(@PathVariable String roleFlag, QingpuDoctorKq kq, Integer currentPage, HttpServletRequest request,  Model model){
		SysUser currUser = GlobalContext.getCurrentUser();
		kq.setQingpuKqTypeId(QingpuKqTypeEnum.Submit.getId());
		kq.setOrgFlow(currUser.getOrgFlow());
		if("teacher".equals(roleFlag)){//带教
			kq.setTeacherFlow(currUser.getUserFlow());
		}
		if("head".equals(roleFlag)){
			kq.setHeadFlow(currUser.getUserFlow());
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<QingpuDoctorKq> kqList = qingPuKqBiz.searchQingpuDoctorKq(kq);
		model.addAttribute("kqList", kqList);
		model.addAttribute("roleFlag", roleFlag);
		return "/res/qingpuKq/submitList";
	}

	@RequestMapping("/editSubmit")
	public String editSubmit(String recordFlow,String roleFlag,Model model){
		SysUser currentUser = GlobalContext.getCurrentUser();
		if(StringUtil.isNotBlank(recordFlow)){
			QingpuDoctorKq kq = qingPuKqBiz.readQingpuDoctorKq(recordFlow);
			model.addAttribute("kq",kq);
		}
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("userFlow",currentUser.getUserFlow());
		paramMap.put("isCurrentFlag","Y");
		List<Map<String,Object>> users = null;
		if("teacher".equals(roleFlag)){
			users = qingPuKqBiz.teacherGetStudents(paramMap);
		}
		if("head".equals(roleFlag)){
			users = qingPuKqBiz.headGetStudents(paramMap);
		}
		model.addAttribute("users",users);
		model.addAttribute("roleFlag",roleFlag);
		return "/res/qingpuKq/editSubmit";
	}

	@RequestMapping("/saveSubmit")
	@ResponseBody
	public String saveSubmit(QingpuDoctorKq kq,String roleFlag){
		kq.setQingpuKqTypeId(QingpuKqTypeEnum.Submit.getId());
		kq.setQingpuKqTypeName(QingpuKqTypeEnum.Submit.getName());
		kq.setQingpuKqTypeDetailName(QingpuKqSubmitTypeEnum.getNameById(kq.getQingpuKqTypeDetailId()));
		SysUser currentUser = GlobalContext.getCurrentUser();
		kq.setOrgFlow(currentUser.getOrgFlow());
		if("teacher".equals(roleFlag)){
			kq.setTeacherFlow(currentUser.getUserFlow());
			kq.setTeacherName(currentUser.getUserName());
			kq.setTeacherAgreeFlag("Y");
		}
		if("head".equals(roleFlag)){
			kq.setHeadFlow(currentUser.getUserFlow());
			kq.setHeadName(currentUser.getUserName());
			kq.setHeadAgreeFlag("Y");
		}
		Map<String,Object> paramMap = new HashMap<>();
		String wsId = (String)GlobalContext.getSession().getAttribute(GlobalConstant.CURRENT_WS_ID);
		paramMap.put("wsId",wsId);
		//查找医院管理员res_admin_role_flow
		String adminFlow = InitConfig.getSysCfg("res_admin_role_flow");
		paramMap.put("roleFlow",adminFlow);
		paramMap.put("orgFlow",currentUser.getOrgFlow());
		List<SysUser> sysUserList = userBiz.searchUserWithRole(paramMap);
		if(sysUserList!=null&&sysUserList.size()>0){
			kq.setManagerFlow(sysUserList.get(0).getUserFlow());
			kq.setManagerName(sysUserList.get(0).getUserName());
		}
		qingPuKqBiz.editQingpuDoctorKq(kq);
		return "1";
	}

	@RequestMapping("/kqStatisticsList")
	public String kqStatisticsList(String deptFlow,String startDate,String endDate,String sessionNumber,String trainingSpeId,
								 String doctorName,Model model,HttpServletRequest request,Integer currentPage){
		SysUser currentUser = GlobalContext.getCurrentUser();
		Map<String,Object> paramMap = new HashMap<>();
//		paramMap.put("deptFlow",deptFlow);
		paramMap.put("startDate",startDate);
		paramMap.put("endDate",endDate);
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("trainingSpeId",trainingSpeId);
		paramMap.put("doctorName",doctorName);
		paramMap.put("orgFlow",currentUser.getOrgFlow());
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> resultMapList = qingPuKqBiz.getKqStatistics(paramMap);
		model.addAttribute("resultMapList",resultMapList);
		//所有科室
		List<SysDept> deptList = deptBiz.searchDeptByOrg(currentUser.getOrgFlow());
		model.addAttribute("deptList",deptList);
		return "/res/qingpuKq/kqStatisticsList";
	}

	//导出考勤统计表
	@RequestMapping("/kqStatisticsListExport")
	public void kqStatisticsListExport(String deptFlow,String startDate,String endDate,String sessionNumber,String trainingSpeId,
								   String doctorName,Model model,HttpServletResponse response) throws Exception {
		SysUser currentUser = GlobalContext.getCurrentUser();
		Map<String,Object> paramMap = new HashMap<>();
//		paramMap.put("deptFlow",deptFlow);
		paramMap.put("startDate",startDate);
		paramMap.put("endDate",endDate);
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("trainingSpeId",trainingSpeId);
		paramMap.put("doctorName",doctorName);
		paramMap.put("orgFlow",currentUser.getOrgFlow());
		List<Map<String,Object>> resultMapList = qingPuKqBiz.getKqStatistics(paramMap);
		String[] titles = new String[]{
				"USER_NAME:学员姓名",
				"TRAINING_SPE_NAME:培训专业",
				"SESSION_NUMBER:参培年份",
				"LEAVE:请假次数",
				"APPEAL:申诉次数",
				"SUBMIT:违纪次数",
				"LATE:迟到次数",
		};
		String splitSymbol = "";
		if(StringUtil.isNotBlank(startDate)&&StringUtil.isNotBlank(endDate)){
			splitSymbol = "-";
		}
		String fileName = startDate+splitSymbol+endDate+"考勤统计表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, resultMapList, response.getOutputStream());
	}

	@RequestMapping("/kqStatisticsDetail")
	public String kqStatisticsDetail(QingpuDoctorKq kq,String type,Model model){
		SysUser currentUser = GlobalContext.getCurrentUser();
		kq.setOrgFlow(currentUser.getOrgFlow());
		if("Late".equals(type)){
			kq.setQingpuKqTypeDetailId(QingpuKqSubmitTypeEnum.Late.getId());
			model.addAttribute("isSubmit",true);
		}else{
			kq.setQingpuKqTypeId(type);
			if("Submit".equals(type)){
				model.addAttribute("isSubmit",true);
			}
		}
		List<QingpuDoctorKq> kqList = qingPuKqBiz.searchQingpuDoctorKq(kq);
		model.addAttribute("kqList",kqList);
		return "/res/qingpuKq/kqStatisticsDetailList";
	}

	@RequestMapping("/exportDoc")
	public void exportDoc(String recordFlow,HttpServletRequest request, HttpServletResponse response)throws Exception{
		List<WordprocessingMLPackage> addTemplates = new ArrayList<WordprocessingMLPackage>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		SysUser user = GlobalContext.getCurrentUser();
		ResDoctor doctor = doctorBiz.readDoctor(user.getUserFlow());
		String doctorName = user.getUserName();
		String orgName = user.getOrgName();
		String userCode = user.getUserCode();
		QingpuDoctorKq kq = qingPuKqBiz.readQingpuDoctorKq(recordFlow);
		String schDeptName = kq.getSchDeptName();
		String schDeptStartDate = kq.getSchDeptStartDate();
		String schDeptEndDate = kq.getSchDeptEndDate();
		String doctorRemarks = kq.getDoctorRemarks();
		String startDate = kq.getStartDate();
		String endDate = kq.getEndDate();
		String intervalDays = kq.getIntervalDays();
		dataMap.put("doctorName",doctorName);
		dataMap.put("userCode",userCode);
		dataMap.put("orgName",orgName);
		dataMap.put("schDeptName",schDeptName);
		dataMap.put("schDeptStartDate",schDeptStartDate);
		dataMap.put("schDeptEndDate",schDeptEndDate);
		dataMap.put("doctorRemarks",doctorRemarks);
		dataMap.put("startDate",startDate);
		dataMap.put("endDate",endDate);
		dataMap.put("intervalDays",intervalDays);
		WordprocessingMLPackage temeplete1 = new WordprocessingMLPackage();
		String trainingSpeId = doctor.getTrainingSpeId();
		String path1 = "";
		if("3500".equals(trainingSpeId)){
			path1 = "/jsp/res/qingpuKq/zhuliquankeLeave.docx";//模板
		}else if(QingpuKqLeaveTypeEnum.MaternityLeave.getId().equals(kq.getQingpuKqTypeDetailId())||
				QingpuKqLeaveTypeEnum.SickLeave.getId().equals(kq.getQingpuKqTypeDetailId())){
			path1 = "/jsp/res/qingpuKq/maternityLeave.docx";//模板
		}else{
			path1 = "/jsp/res/qingpuKq/leave.docx";//模板
		}
		ServletContext context = request.getServletContext();
		String watermark = "";
		temeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path1)), dataMap, watermark, true);
		addTemplates.add(temeplete1);

		WordprocessingMLPackage temeplete = Docx4jUtil.mergeDocx(addTemplates);
		if(temeplete!=null){
			String name = "助理全科医师请假单.docx";
			response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
			response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			ServletOutputStream out = response.getOutputStream ();
			(new SaveToZipFile (temeplete)).save (out);
			out.flush ();
		}
	}

	//签到列表
	@RequestMapping(value="/main")
	public String main(Model model) {
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		SysUserDeptExample example = new SysUserDeptExample();
		example.createCriteria().andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		model.addAttribute("signUrl","func://funcFlow=qingpuSingin&teacherUserFlow="+userFlow);
		return "res/qingpuKq/report/main";
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
		List<Map<String,String>> list=qingPuKqBiz.getSigninList(param);
		model.addAttribute("list",list);
		return "res/qingpuKq/report/signinData";
	}
	@RequestMapping(value="/codeIsUse")
	@ResponseBody
	public Object codeIsUse(Model model,String userName,String deptName,String sessionNumber,
							String token) {
		Object val="";
		if(val==null)
		{
			return "N";
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
		List<Map<String,String>> list=qingPuKqBiz.getSigninList(param);
		model.addAttribute("list",list);
		return "res/qingpuKq/signinList";
	}

	//科室签到查询列表
	@RequestMapping(value="/signinList/head")
	public String signinListHead(Model model,String doctorName,String trainingYears,String sessionNumber,String signinDate,
								 String reportDate, String trainingSpeId,Integer currentPage,HttpServletRequest request){
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		SysUserDeptExample example = new SysUserDeptExample();
		example.createCriteria().andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
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
		List<Map<String,String>> list=qingPuKqBiz.getSigninList(param);
		model.addAttribute("list",list);
		return "res/qingpuKq/signinListHead";
	}
}
