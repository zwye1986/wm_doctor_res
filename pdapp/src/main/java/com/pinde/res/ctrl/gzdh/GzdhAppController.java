package com.pinde.res.ctrl.gzdh;


import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.app.common.PasswordUtil;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.common.IResPowerCfgBiz;
import com.pinde.res.biz.jswjw.IJswjwBiz;
import com.pinde.res.biz.stdp.*;
import com.pinde.res.biz.gzdh.IGzdhAppBiz;
import com.pinde.res.biz.gzdh.IGzdhStudentBiz;
import com.pinde.res.biz.gzdh.IGzdhTeacherBiz;
import com.pinde.res.enums.hbres.ResAssessTypeEnum;
import com.pinde.res.enums.hbres.ResRecTypeEnum;
import com.pinde.res.enums.hbres.SigninTypeEnum;
import com.pinde.res.enums.stdp.ResultEnum;
import com.pinde.res.enums.stdp.UserStatusEnum;
import com.pinde.res.model.stdp.mo.InxInfoForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.util.PasswordHelper;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/res/gzdh")
public class GzdhAppController {
	private static Logger logger = LoggerFactory.getLogger(GzdhAppController.class);

	@Autowired
	private IGzdhAppBiz appBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private IResGradeBiz gradeBiz;
	@Autowired
	private IGzdhStudentBiz studentBiz;
	@Autowired
	private IGzdhTeacherBiz teacherBiz;
	@Autowired
	private IResPowerCfgBiz resPowerCfgBiz;
	@Autowired
	private IInxInfoBiz inxInfoBiz;
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private IResActivityBiz activityBiz;
	@Autowired
	private IJswjwBiz jswjwBiz;

	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/gzdh/500";
    }

	@RequestMapping(value={"/version"},method={RequestMethod.GET})
	public String version(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "res/gzdh/version";
	}


	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(){
		return "res/gzdh/test";
	}

	@RequestMapping(value={"/remember"},method={RequestMethod.GET})
	public String remember(String userFlow,String roleId,String schDeptFlow,String deptFlow,String doctorFlow,String cataFlow,String dataFlow,String funcTypeId,String funcFlow,HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("userFlow", userFlow);
		session.setAttribute("roleId", roleId);
		session.setAttribute("schDeptFlow", schDeptFlow);
		session.setAttribute("deptFlow", deptFlow);
		session.setAttribute("doctorFlow", doctorFlow);
		session.setAttribute("cataFlow", cataFlow);
		session.setAttribute("dataFlow", dataFlow);
		session.setAttribute("funcTypeId", funcTypeId);
		session.setAttribute("funcFlow", funcFlow);
		return "/res/gzdh/test";
	}

	@RequestMapping(value={"/login"},method={RequestMethod.POST})
	public String login(String userCode,String userPasswd,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(userCode)){
			model.addAttribute("resultId", "3010101");
			model.addAttribute("resultType", "用户名为空");
			return "res/gzdh/login";
		}

		if(StringUtil.isEmpty(userPasswd)){
			model.addAttribute("resultId", "3010102");
			model.addAttribute("resultType", "密码为空");
			return "res/gzdh/login";
		}

		//验证用户是否存在
		SysUser userinfo = appBiz.getUserByCode(userCode);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
		}else{
			String passwd = userinfo.getUserPasswd();
			String userFlow = userinfo.getUserFlow();

			//验证密码是否正确或者是否使用超级密码
			if(!PasswordUtil.isRootPass(userPasswd) &&
					!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(userFlow,userPasswd))){
				model.addAttribute("resultId", "3010104");
				model.addAttribute("resultType", "用户名或密码错误");
				return "res/gzdh/login";
			}

			//验证用户是否锁定,锁定用户不能登录
			String userStatus = userinfo.getStatusId();
			if(UserStatusEnum.Locked.getId().equals(userStatus)){
				model.addAttribute("resultId", "3010105");
				model.addAttribute("resultType", "该用户已被锁定");
				return "res/gzdh/login";
			}

			//验证用户是否有角色
			List<SysUserRole> userRoles = appBiz.getSysUserRole(userFlow);
			if(userRoles==null || userRoles.isEmpty()){
				model.addAttribute("resultId", "3010106");
				model.addAttribute("resultType", "用户未赋权");
				return "res/gzdh/login";
			}

			boolean isDoctor = false;
			boolean isTeacher = false;
			boolean isHeader = false;
			boolean isSecretary = false;
			boolean isBaser = false;

			//获取当前配置的医师角色
			String doctorRole = appBiz.getCfgByCode("res_doctor_role_flow");

			//获取当前配置的老师角色
			String teacherRole = appBiz.getCfgByCode("res_teacher_role_flow");

			//获取当前配置的教学主任角色
			String headRole = appBiz.getCfgCode("res_head_role_flow");

			//获取当前配置的教学秘书角色
			String secretaryRole = appBiz.getCfgCode("res_secretary_role_flow");

			//获取当前配置的专业基地管理员角色
			String baseRole = appBiz.getCfgCode("res_professionalBase_admin_role_flow");

			//获取最先匹配到的角色,认定该用户的角色为匹配到的角色
			for(SysUserRole sur : userRoles){
				String ur = sur.getRoleFlow();

				model.addAttribute("isDoctor", isDoctor);

				if(doctorRole.equals(ur)){
					isDoctor = true;
					model.addAttribute("roleId","Student");
					model.addAttribute("roleName","医师");
					break;
				}else if(teacherRole.equals(ur)){
					isTeacher = true;
					model.addAttribute("roleId","Teacher");
					model.addAttribute("roleName","带教老师");
				}else if(headRole.equals(ur)){
					isHeader = true;
					model.addAttribute("roleId","Head");
					model.addAttribute("roleName","教学主任");
				}else if(secretaryRole.equals(ur)){
					isSecretary = true;
					model.addAttribute("roleId","Secretary");
					model.addAttribute("roleName","教学秘书");
				}else if(baseRole.equals(ur)){
					isBaser = true;
					model.addAttribute("roleId","Spe");
					model.addAttribute("roleName","专业基地管理员");
					break;
				}
			}

			//如果有三个角色 带教与主任 则只要判断主任角色权限
			if(isHeader)
			{
				isTeacher=false;
				isSecretary = false;
				model.addAttribute("roleId","Head");
				model.addAttribute("roleName","教学主任");
			}else if(isSecretary)
			{
				isHeader=false;
				isTeacher=false;
				model.addAttribute("roleId","Secretary");
				model.addAttribute("roleName","教学秘书");
			}
			model.addAttribute("isDoctor", isDoctor);
			model.addAttribute("isTeacher", isTeacher);
			model.addAttribute("isHeader", isHeader);
			model.addAttribute("isSecretary", isSecretary);
			String orgFlow2=userinfo.getOrgFlow();
			//如果是医师需要获取医师的一些培训信息
			if(isDoctor){
				//读取是否开启自主增加轮转计划开关 res_custom_result_flag
				String isManualFlag = appBiz.getCfgByCode("res_custom_result_flag");
				if(!GlobalConstant.FLAG_Y.equals(isManualFlag)){
					isManualFlag = GlobalConstant.FLAG_N;
				}
				model.addAttribute("manualRotationFlag",isManualFlag);

				//读取这个用户的医师信息
				ResDoctor doctor = appBiz.readResDoctor(userFlow);

				if(doctor==null){
					model.addAttribute("resultId", "3010107");
					model.addAttribute("resultType", "登录失败,学员数据出错!");
					return "res/gzdh/login";
				}else{
					//验证该医师是否存在培训机构,如果存在则验证该机构是否开启过程
					String orgFlow = doctor.getOrgFlow();
					orgFlow2 = doctor.getOrgFlow();
					if(StringUtil.isNotBlank(orgFlow)){
//						学员开通app登录权限
						Map<String,String> paramMap = new HashMap();
//						是否开通APP
						String isAppFlag = "";
//						后台配置是否校验权限时间
						String isPermitOpen = appBiz.getCfgByCode("res_permit_open_doc");
//						开通web权限的key
						String cfgCode = "res_doctor_app_login_" + userFlow;
//						如果校验权限时间，并且开通web权限，并且登录时间大于开始时间，并且登录时间小于结束时间，则开通APP
						if (GlobalConstant.FLAG_Y.equals(isPermitOpen)) {
//							当前的登录时间
							String loginDate = DateUtil.getCurrDate();
							paramMap.put("cfgCode",cfgCode);
							paramMap.put("loginDate",loginDate);
							isAppFlag = resPowerCfgBiz.getResPowerCfg(paramMap);
						} else {
//							如果不校验校验权限时间，并且开通APP权限，则开通APP
							paramMap.put("cfgCode",cfgCode);
							isAppFlag = resPowerCfgBiz.getResPowerCfg(paramMap);
						}
						if(!GlobalConstant.FLAG_Y.equals(isAppFlag)){
							model.addAttribute("resultId", "3010110");
							model.addAttribute("resultType", "登录失败,您还未开通过程APP!");
							return "res/gzdh/login";
						}
					}else{
						model.addAttribute("resultId", "3010109");
						model.addAttribute("resultType", "登录失败,该学员暂无培训机构!");
						return "res/gzdh/login";
					}
				}

				model.addAttribute("doctor", doctor);

				//出科考核对接判断
				boolean ckk=false;
				String regScore=appBiz.getCfgByCode("res_doc_reg_score");
				if(GlobalConstant.FLAG_Y.equals(regScore))
				{
					String switchFlag=appBiz.getCfgByCode("res_after_test_switch");
					String urlCfg=appBiz.getCfgByCode("res_mobile_after_url_cfg");
					//学员开通出科考试权限
					Map<String,String> paramMap = new HashMap();
					String cfgCode = "res_doctor_ckks_" + userFlow;
					paramMap.put("cfgCode",cfgCode);
					String isCkksFlag = resPowerCfgBiz.getResPowerCfg(paramMap);
					if(GlobalConstant.FLAG_Y.equals(switchFlag)
							&& StringUtil.isNotBlank(urlCfg))
					{
						ckk=true;
					}

				}
				model.addAttribute("isCkk",ckk);
				//获取该用户的入科记录的第一次入科时间和最后一次出科时间
				Map<String,Object> processAreaMap = appBiz.getDocProcessArea(userFlow);
				if(processAreaMap!=null){
					String minDate = (String)processAreaMap.get("minDate");
					if(StringUtil.isNotBlank(doctor.getSessionNumber())) {
						minDate = doctor.getSessionNumber()+"-08-01";
					}
					model.addAttribute("minDate",minDate);
					doctor.setInHosDate(minDate);
					appBiz.editResDoctor(doctor);
					long schDays = 0;
					if(StringUtil.isNotBlank(minDate)){
						String currDate = DateUtil.getCurrDate();
						model.addAttribute("maxDate",currDate);

						//获取该医师的已轮转天数
						schDays = DateUtil.signDaysBetweenTowDate(currDate,minDate)+1;

						//获取该医师的轮转计划的区间
						Map<String,Object> resultAreaMap = appBiz.getDocResultArea(userFlow);
						if(resultAreaMap!=null){
							String schStartDate = (String)resultAreaMap.get("minDate");
							String schEndDate = (String)resultAreaMap.get("maxDate");

							long sumDays = 0;
							if(StringUtil.isNotBlank(schStartDate) && StringUtil.isNotBlank(schEndDate)){
								//计算轮转计划的总轮转天数
								sumDays = DateUtil.signDaysBetweenTowDate(schEndDate,schStartDate)+1;

								BigDecimal schProcess = BigDecimal.valueOf(0);
								if(schDays!=0 && sumDays!=0){
									//计算该医师的已轮转的进度 已轮转天数和总轮转天数的比
									double process = (schDays/(sumDays*1.0))*100;
									schProcess = BigDecimal.valueOf(process);
									schProcess = schProcess.setScale(0,BigDecimal.ROUND_HALF_UP);
								}
								model.addAttribute("schProcess",schProcess);
							}
						}
						model.addAttribute("schDays",schDays);
					}
				}
			}else if(isTeacher){
				//验证该医师是否存在培训机构,如果存在则验证该机构是否开启过程
				String orgFlow = userinfo.getOrgFlow();
				if(StringUtil.isNotBlank(orgFlow)){
					String isAppFlag = appBiz.getCfgByCode("jswjw_"+orgFlow+"_P006");
					if(!GlobalConstant.FLAG_Y.equals(isAppFlag)){
						model.addAttribute("resultId", "3010110");
						model.addAttribute("resultType", "登录失败,你暂无权限使用,请联系培训基地管理员!");
						return "res/gzdh/login";
					}

				}else{
					model.addAttribute("resultId", "3010109");
					model.addAttribute("resultType", "登录失败,该带教暂无所属机构!");
					return "res/gzdh/login";
				}
			}else if(isHeader||isBaser||isSecretary){
				//验证该教学主任是否存在培训机构,如果存在则验证该机构是否开启过程
				String orgFlow = userinfo.getOrgFlow();
				if(StringUtil.isNotBlank(orgFlow)){
					String isAppFlag = appBiz.getCfgByCode("jswjw_"+orgFlow+"_P001");
					if(!GlobalConstant.FLAG_Y.equals(isAppFlag)){
						model.addAttribute("resultId", "3010110");
						model.addAttribute("resultType", "登录失败,你暂无权限使用,请联系培训基地管理员!");
						return "res/gzdh/login";
					}

				}else{
					model.addAttribute("resultId", "3010109");
					model.addAttribute("resultType", "登录失败,暂无所属机构!");
					return "res/gzdh/login";
				}
				if(isBaser&&StringUtil.isBlank(userinfo.getResTrainingSpeId()))
				{
					model.addAttribute("resultId", "3010109");
					model.addAttribute("resultType", "登录失败,暂未分配专业基地，请联系培训基地管理员!");
					return "res/gzdh/login";
				}
			}else{
				model.addAttribute("resultId", "3010109");
				model.addAttribute("resultType", "登录失败,无权登录!");
				return "res/gzdh/login";
			}
			model.addAttribute("userinfo", userinfo);

			ResRingLetterUser ring = appBiz.getRingUser(userinfo.getUserFlow());
			model.addAttribute("ring", ring);
			List<Map<String,String>> infos = this.noticeBiz.searchInfoByOrgNotRead(orgFlow2, GlobalConstant.RES_NOTICE_TYPE5_ID, GlobalConstant.RES_NOTICE_SYS_ID, userFlow);
			if(infos!=null)
			{
				model.addAttribute("hasNotReadInfo",infos.size());
			}else{
				model.addAttribute("hasNotReadInfo",0);
			}
		}
		String uploadBaseUrl = appBiz.getCfgByCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		return "res/gzdh/login";
	}

	@RequestMapping(value={"/notice"},method={RequestMethod.GET})
	public String notice(String userFlow,String roleId,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		String result = "res/gzdh/notice";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户角色为空");
			return result;
		}
		return result;
	}

	@RequestMapping(value={"/noticeCount"},method={RequestMethod.GET})
	public String noticeCount(String userFlow,String roleId,HttpServletRequest request,Model model){
		String result = "res/gzdh/noticeCount";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户角色为空");
			return result;
		}
		return result;
	}


	private static final String COMMON_PATH = "student";
	//11
	@RequestMapping(value={"/viewData"},method={RequestMethod.GET})
	public String viewData(
			String userFlow,
			String roleId,
			String deptFlow,
			String doctorFlow,
			String funcTypeId,
			String funcFlow,
			String dataFlow,
			String cataFlow,
			HttpServletRequest request,
			Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "3010601");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/viewData";
		}

		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3010602");
			model.addAttribute("resultType", "用户角色为空");
			return "res/gzdh/viewData";
		}

		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", "3010603");
			model.addAttribute("resultType", "功能类型为空");
			return "res/gzdh/viewData";
		}

		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3010604");
			model.addAttribute("resultType", "功能标识为空");
			return "res/gzdh/viewData";
		}

		if("Student".equals(roleId)&&StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3010605");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/gzdh/viewData";
		}

		if("Teacher".equals(roleId)&&StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3010606");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/gzdh/viewData";
		}

		//科室流水号
		String resultFlow = deptFlow;

		//判断角色
		boolean sSwitch = "Student".equals(roleId);
		boolean tSwitch = "Teacher".equals(roleId);

		String path = COMMON_PATH;
		//定义dataList路径
		if(sSwitch){
			path = COMMON_PATH;
		}else if(tSwitch){
			path = "teacher";
			//所有1n模式的数据取学员的viewData
			if("dataInput1N".equals(funcTypeId)){
				path = COMMON_PATH;
			}

			resultFlow = doctorFlow;

		}
		model.addAttribute("path",path);

		//要求数据
		SchRotationDeptReq req = null;

		//当前轮转计划信息
		SchArrangeResult result = studentBiz.searcheDocResult(null,resultFlow);
		if(result!=null){
			model.addAttribute("result",result);

			ResDoctor doctor=appBiz.readResDoctor(result.getDoctorFlow());
			model.addAttribute("doctor", doctor);
			//读取要求
			if(StringUtil.isNotBlank(cataFlow)){
				String standardDeptId = result.getStandardDeptId();
				String standardGroupFlow = result.getStandardGroupFlow();
				SchRotationDept rotationDept = appBiz.getRotationDeptByResult(standardGroupFlow,standardDeptId);
				if(rotationDept!=null){
					String relRecordFlow = rotationDept.getRecordFlow();
					req = appBiz.readReq(null,relRecordFlow,cataFlow);
				}
			}
		}

		String version="2.0";
		//是否是评分表单
		String assessType = "";
		boolean isGrade = false;
		if(ResRecTypeEnum.TeacherGrade.getId().equals(funcFlow)){
			assessType = ResAssessTypeEnum.TeacherAssess.getId();
			isGrade = true;
		}else if(ResRecTypeEnum.DeptGrade.getId().equals(funcFlow)){
			assessType = ResAssessTypeEnum.DeptAssess.getId();
			isGrade = true;
		}

		//是否是其他几种表非数据表单，如果是，则去 RES_SCH_PROCESS_EXPRESS 表取数据
		boolean isAfter=false;
		if(ResRecTypeEnum.AfterEvaluation.getId().equals(funcFlow)
				||ResRecTypeEnum.AfterSummary.getId().equals(funcFlow)
				||ResRecTypeEnum.DOPS.getId().equals(funcFlow)
				||ResRecTypeEnum.Mini_CEX.getId().equals(funcFlow)
				)
		{
			isAfter=true;
		}
		//是否是评分，如果是评分，则去DEPT_TEACHER_GRADE_INFO表取数据
		if(isGrade)
		{
			if(StringUtil.isNotBlank(assessType)){
				//读取评分配置
				ResAssessCfg assessCfg = gradeBiz.getGradeTemplate(assessType);
				model.addAttribute("assessCfg",assessCfg);
				if(assessCfg!=null){
					String content = assessCfg.getCfgBigValue();
					//解析评分xml
					List<Map<String,Object>> assessMaps = gradeBiz.parseAssessCfg(content);
					model.addAttribute("assessMaps",assessMaps);
				}
				//读取相关信息
				String processFlow = "";
				ResDoctorSchProcess process = studentBiz.getProcessByResult(resultFlow);
				if(process!=null){
					//登记表单的科室
					processFlow = process.getProcessFlow();
				}
				DeptTeacherGradeInfo gradeInfo = gradeBiz.getRecByRecType(processFlow,funcFlow);
				if(gradeInfo!=null&&StringUtil.isNotBlank(gradeInfo.getRecContent()))
				{
					//解析登记信息的xml
					Object formDataMap = gradeBiz.parseDocGradeXml(gradeInfo.getRecContent());
					model.addAttribute("formDataMap",formDataMap);
					//如果读取到数据就使用数据本身的funcFlow
					funcFlow = gradeInfo.getRecTypeId();
				}
			}
		}else if(isAfter){
			ResDoctorSchProcess process = studentBiz.getProcessByResult(resultFlow);
			//获取当前这条登记信息
			ResSchProcessExpress rec = null;
			if(StringUtil.isNotBlank(dataFlow)){
				rec = expressBiz.getExpressByRecFlow(dataFlow);
				//如果是11模式根据类型和科室查询
			}else if("dataInput11".equals(funcTypeId)){
				String processFlow = "";
				if(process!=null){
					//登记表单的科室
					processFlow = process.getProcessFlow();
				}
				rec = expressBiz.getExpressByRecType(processFlow,funcFlow);
			}
			if(rec!=null){
				String content = rec.getRecContent();
				//解析登记信息的xml
				Object formDataMap = appBiz.parseRecContent(content);
				model.addAttribute("formDataMap",formDataMap);
				//如果读取到数据就使用数据本身的funcFlow
				funcFlow = rec.getRecTypeId();
				version = rec.getRecVersion();
			}
			model.addAttribute("currRegProcess",process);
			//不可操作开关
			boolean isAudit = rec!=null && StringUtil.isNotBlank(rec.getAuditStatusId());
			model.addAttribute("cannotOperSwitch",(tSwitch || isAudit));
			model.addAttribute("isAudit",isAudit);
		}else{
			//获取当前这条登记信息
			ResRec rec = null;
			if(StringUtil.isNotBlank(dataFlow)){
				rec = appBiz.getRecByRecFlow(dataFlow);
				//如果是11模式根据类型和科室查询
			}else if("dataInput11".equals(funcTypeId)){
				String processFlow = "";
				ResDoctorSchProcess process = studentBiz.getProcessByResult(resultFlow);
				if(process!=null){
					//登记表单的科室
					processFlow = process.getProcessFlow();
				}
				rec = appBiz.getRecByRecType(processFlow,funcFlow);
			}
			if(StringUtil.isBlank(dataFlow)&&!"dataInput11".equals(funcTypeId))
			{

				//历史数据
				if(sSwitch)
				{
					String processFlow = "";
					ResDoctorSchProcess process = studentBiz.getProcessByResult(resultFlow);
					if(process!=null){
						//登记表单的科室
						processFlow = process.getProcessFlow();
					}
					if(StringUtil.isNotBlank(processFlow)) {
						String endTime = DateUtil.getCurrDateTime();
						String startTime = DateUtil.addMinute(DateUtil.getCurrDateTime(), -6 * 24 * 60);
						Map<String, Object> param = new HashMap<>();
						param.put("startTime", startTime);
						param.put("endTime", endTime);
						List<String> ids=new ArrayList<>();
								ids.add(ResRecTypeEnum.DiseaseRegistry.getId());
								ids.add(ResRecTypeEnum.CaseRegistry.getId());
								ids.add(ResRecTypeEnum.SkillRegistry.getId());
								ids.add(ResRecTypeEnum.OperationRegistry.getId());
						param.put("recTypeIds", ids);
						param.put("processFlow", processFlow);
						List<ResRec> hos=studentBiz.readResRecHos(param);
						List<Map<String,String>> list=new ArrayList<>();
						if(hos!=null&&hos.size()>0)
						{
							List<String> exists=new ArrayList<>();
							for(ResRec r:hos)
							{
								String content = r.getRecContent();
								//解析登记信息的xml
								Map<String,String> formDataMap = appBiz.parseRecContent(content);
								if(formDataMap!=null) {
									String name="";
									String no="";
									if(ResRecTypeEnum.DiseaseRegistry.getId().equals(r.getRecTypeId()))
									{
										name=formDataMap.get("disease_pName");
										no=formDataMap.get("disease_mrNo");
									}else if(ResRecTypeEnum.CaseRegistry.getId().equals(r.getRecTypeId()))
									{
										name=formDataMap.get("mr_pName");
										no=formDataMap.get("mr_no");
									}else if(ResRecTypeEnum.SkillRegistry.getId().equals(r.getRecTypeId()))
									{
										name=formDataMap.get("skill_pName");
										no=formDataMap.get("skill_mrNo");
									}else if(ResRecTypeEnum.OperationRegistry.getId().equals(r.getRecTypeId()))
									{
										name=formDataMap.get("operation_pName");
										no=formDataMap.get("operation_mrNo");
									}
									if(!exists.contains(name+no)&&StringUtil.isNotBlank(name+no)) {
										formDataMap.put("createTime", DateUtil.transDate(r.getCreateTime()));
										formDataMap.put("recTypeId", r.getRecTypeId());
										list.add(formDataMap);
										exists.add(name+no);
									}
								}
							}
							model.addAttribute("hos",list);
						}
					}
				}
			}
			String itemId = cataFlow;
			if(rec!=null){
				model.addAttribute("rec",rec);
				itemId = rec.getItemId();

				//为子项选项赋值
				if(req==null){
					req = new SchRotationDeptReq();
					req.setItemId(rec.getItemId());
					req.setItemName(rec.getItemName());
				}
			}

			//是否是其他
			model.addAttribute("isOther",GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(itemId));

			//要求数据
			model.addAttribute("req",req);
			if(rec!=null){
				String content = rec.getRecContent();
				//解析登记信息的xml
				Object formDataMap = appBiz.parseRecContent(content);
				model.addAttribute("formDataMap",formDataMap);

				//如果读取到数据就使用数据本身的funcFlow
				funcFlow = rec.getRecTypeId();
				version = rec.getRecVersion();
			}

			//不可操作开关
			boolean isAudit = rec!=null && StringUtil.isNotBlank(rec.getAuditStatusId());
			model.addAttribute("cannotOperSwitch",(tSwitch || isAudit));
			model.addAttribute("isAudit",isAudit);
		}
		model.addAttribute("funcFlow",funcFlow);

		//老师和学生的控件开关
		model.addAttribute("sSwitch",sSwitch);
		model.addAttribute("tSwitch",tSwitch);
		return "res/gzdh/viewData";
	}

	//1N
	@RequestMapping(value={"/dataList"},method={RequestMethod.GET})
	public String dataList(String userFlow,
			String roleId,
			String deptFlow,
			String doctorFlow,
			String funcTypeId,
			String funcFlow,
			String searchData,
			String cataFlow,
			Integer pageIndex,
			Integer pageSize,
			HttpServletRequest request,
			Model model){

		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "3010701");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/dataList";
		}

		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3010702");
			model.addAttribute("resultType", "用户角色为空");
			return "res/gzdh/dataList";
		}

		if("Student".equals(roleId)&&StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3010703");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/gzdh/dataList";
		}

		if("Teacher".equals(roleId)&&StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3010704");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/gzdh/dataList";
		}

		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", "3010705");
			model.addAttribute("resultType", "功能类型为空");
			return "res/gzdh/dataList";
		}

		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3010706");
			model.addAttribute("resultType", "功能标识为空");
			return "res/gzdh/dataList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "3010707");
			model.addAttribute("resultType", "起始页为空");
			return "res/gzdh/dataList";
		}

		if(pageSize==null){
			model.addAttribute("resultId", "3010708");
			model.addAttribute("resultType", "页面大小为空");
			return "res/gzdh/dataList";
		}

		//轮转计划
		String resultFlow = deptFlow;

		if("Teacher".equals(roleId)){
			resultFlow = doctorFlow;
		}


		//包装查询条件
		Map<String,Object> paramMap = new HashMap<String, Object>();

		//解析查询条件json字符串  NotAudit未审核  Audited已审核
		Map<String,String> searchMap = null;
		String join = "";
		if(StringUtil.isNotBlank(searchData)){
			try {
				//为json字符串转码
				searchData = new String(searchData.getBytes("ISO8859-1") , "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//转换json字符串为map对象
			searchMap = (Map<String,String>)JSON.parse(searchData);
			if(searchMap!=null && !searchMap.isEmpty()){
				join = searchMap.get("join");
				paramMap.put("join",join);
			}
		}

		//表单的子项
		paramMap.put("itemId",cataFlow);
		ResDoctorSchProcess process = studentBiz.getProcessByResult(resultFlow);
		if(process!=null){
			//登记表单的科室
			paramMap.put("processFlow",process.getProcessFlow());

			//登记数据的用户
			paramMap.put("operUserFlow",process.getUserFlow());
		}
		//查询rec数据//分页
		PageHelper.startPage(pageIndex, pageSize);

		//封装好的rec数据
		List<Map<String,Object>> recMaps = null;

		//定义dataList路径
		String path = "";
		if("Student".equals(roleId)){
			//dataList路径
			path = "student";

			//登记表单的类型
			paramMap.put("recTypeId",funcFlow);

			//查询登记数据
			recMaps = appBiz.getParsedRecs(paramMap);
		}else if("Teacher".equals(roleId)){
			//dataList路径
			path = "teacher";

			//不查询11
			List<String> doNotSearch = new ArrayList<String>();
			doNotSearch.add(ResRecTypeEnum.AfterSummary.getId());
			doNotSearch.add(ResRecTypeEnum.TeacherGrade.getId());
			doNotSearch.add(ResRecTypeEnum.DeptGrade.getId());
			paramMap.put("doNotSearch",doNotSearch);

			//查询登记数据
			recMaps = appBiz.getTeacherParsedRecs(paramMap);
		}
		model.addAttribute("path",path);
		model.addAttribute("recMaps",recMaps);

		//百分比算法
		Map<String,Object> perMap = studentBiz.getRegPer(0, userFlow, resultFlow,funcFlow,cataFlow,true);
		model.addAttribute("perMap",perMap);

		model.addAttribute("dataCount",PageHelper.total);

		return "res/gzdh/dataList";
	}

	//只有funcTypeId为dataInputNN时使用
	@RequestMapping(value={"/cataList"},method={RequestMethod.GET})
	public String cataList(String userFlow,
			String roleId,
			String deptFlow,
			String doctorFlow,
			String funcTypeId,
			String funcFlow,
			String searchData,
			Integer pageIndex,
			Integer pageSize,
			HttpServletRequest request,
			Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "3010801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/cataList";
		}
		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3010802");
			model.addAttribute("resultType", "用户角色为空");
			return "res/gzdh/cataList";
		}
		if("Student".equals(roleId)&&StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3010803");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/gzdh/cataList";
		}
		if("Teacher".equals(roleId)&&StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3010804");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/gzdh/cataList";
		}
		if(StringUtil.isNotEquals(funcTypeId, "dataInputNN")){
			model.addAttribute("resultId", "3010805");
			model.addAttribute("resultType", "功能类型不匹配");
			return "res/gzdh/cataList";
		}
		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3010806");
			model.addAttribute("resultType", "功能标识为空");
			return "res/gzdh/cataList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "3010807");
			model.addAttribute("resultType", "起始页为空");
			return "res/gzdh/cataList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3010808");
			model.addAttribute("resultType", "页面大小为空");
			return "res/gzdh/cataList";
		}

		//轮转计划
		String resultFlow = deptFlow;

		if("Teacher".equals(roleId)){
			resultFlow = doctorFlow;
		}

		//解析查询条件json字符串
		Map<String,String> searchMap = null;
		if(StringUtil.isNotBlank(searchData)){
//			try {
//				searchData = new String(searchData.getBytes("ISO8859-1") , "UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
			searchMap = (Map<String, String>) JSON.parse(searchData);
		}

		//包装查询条件
		Map<String,Object> paramMap = new HashMap<String, Object>();
		if(searchMap!=null && !searchMap.isEmpty()){
			paramMap.put("itemName",searchMap.get("name"));
		}

		//表单类型
		paramMap.put("recTypeId",funcFlow);

		//获取当前排班计划的科室
		SchArrangeResult result = studentBiz.searcheDocResult(null,resultFlow);
		if(result!=null){
			String standardDeptId = result.getStandardDeptId();
			String standardGroupFlow = result.getStandardGroupFlow();
			SchRotationDept rotationDept = appBiz.getRotationDeptByResult(standardGroupFlow,standardDeptId);
			if(rotationDept!=null){
				String rocordFlow = rotationDept.getRecordFlow();
				paramMap.put("relRecordFlow",rocordFlow);
			}
		}

		//根据条件获取要求列表
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> reqMaps = appBiz.getReqByResult(paramMap);
		model.addAttribute("reqMaps",reqMaps);

		model.addAttribute("dataCount",PageHelper.total);

		//百分比算法
		Map<String,Object> perMap = studentBiz.getRegPer(0, userFlow, resultFlow,funcFlow);
		model.addAttribute("perMap",perMap);

		String recTypeName = ResRecTypeEnum.getNameById(funcFlow);
		model.addAttribute("recTypeName",recTypeName);

		return "res/gzdh/cataList";
	}

	@RequestMapping(value={"/saveData"},method={RequestMethod.POST})
	public String saveData(String userFlow,
			String roleId,
			String deptFlow,
			String doctorFlow,
			String funcTypeId,
			String funcFlow,
			String dataFlow,
			String cataFlow,
			HttpServletRequest request,
			Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "3010901");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/saveData";
		}

		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3010902");
			model.addAttribute("resultType", "用户角色为空");
			return "res/gzdh/saveData";
		}

		if("Student".equals(roleId)&&StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3010903");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/gzdh/saveData";
		}

		if("Teacher".equals(roleId)&&StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3010904");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/gzdh/saveData";
		}

		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", "3010905");
			model.addAttribute("resultType", "功能类型为空");
			return "res/gzdh/saveData";
		}

		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3010906");
			model.addAttribute("resultType", "功能标识为空");
			return "res/gzdh/saveData";
		}

		//科室流水号
		String resultFlow = deptFlow;

		//获取当前操作人
		String operUserFlow = "";
		if("Student".equals(roleId)){
			operUserFlow = userFlow;
		}else if("Teacher".equals(roleId)){
			resultFlow = doctorFlow;
		}
		boolean isGrade = false;
		if(ResRecTypeEnum.TeacherGrade.getId().equals(funcFlow)){
			isGrade = true;
		}else if(ResRecTypeEnum.DeptGrade.getId().equals(funcFlow)){
			isGrade = true;
		}

		//是否是其他几种表非数据表单，如果是，则去 RES_SCH_PROCESS_EXPRESS 表取数据
		boolean isAfter=false;
		if(ResRecTypeEnum.AfterEvaluation.getId().equals(funcFlow)
				||ResRecTypeEnum.AfterSummary.getId().equals(funcFlow)
				||ResRecTypeEnum.DOPS.getId().equals(funcFlow)
				||ResRecTypeEnum.Mini_CEX.getId().equals(funcFlow)
				)
		{
			isAfter=true;
		}
		//是否是评分，如果是评分，则去DEPT_TEACHER_GRADE_INFO表取数据
		if(isGrade)
		{
				//读取相关信息
				String processFlow = "";
				ResDoctorSchProcess process = studentBiz.getProcessByResult(resultFlow);
				if(process!=null){
					//登记表单的科室
					processFlow = process.getProcessFlow();
				}
				DeptTeacherGradeInfo gradeInfo = gradeBiz.getRecByRecType(processFlow,funcFlow);
				if(gradeInfo!=null)
				{
					dataFlow = gradeInfo.getRecFlow();
					funcFlow = gradeInfo.getRecTypeId();
				}
		}else if(isAfter){
			//如果是11查出dataFlow
			ResSchProcessExpress rec = null;
			if ("dataInput11".equals(funcTypeId)) {
				String processFlow = "";
				ResDoctorSchProcess process = studentBiz.getProcessByResult(resultFlow);
				if (process != null) {
					//登记表单的科室
					processFlow = process.getProcessFlow();

					operUserFlow = process.getUserFlow();
				}
				rec = expressBiz.getExpressByRecType(processFlow, funcFlow);
				if (rec != null) {
					dataFlow = rec.getRecFlow();
				}
			} else {
				rec = expressBiz.getExpressByRecFlow(dataFlow);
			}
			if (rec != null) {
				funcFlow = rec.getRecTypeId();
			}
		} else {
			//如果是11查出dataFlow
			ResRec rec = null;
			if ("dataInput11".equals(funcTypeId)) {
				String processFlow = "";
				ResDoctorSchProcess process = studentBiz.getProcessByResult(resultFlow);
				if (process != null) {
					//登记表单的科室
					processFlow = process.getProcessFlow();

					operUserFlow = process.getUserFlow();
				}
				rec = appBiz.getRecByRecType(processFlow, funcFlow);
				if (rec != null) {
					dataFlow = rec.getRecFlow();
				}
			} else {
				rec = appBiz.getRecByRecFlow(dataFlow);
			}

			if (rec != null) {
				funcFlow = rec.getRecTypeId();
			}
		}
		if(isGrade){
			dataFlow = gradeBiz.editGradeInfo(dataFlow, operUserFlow, resultFlow, funcFlow, request,GlobalConstant.RES_DGDH_DEFAULT_FORM);
			if(dataFlow!=null&&dataFlow.startsWith("error:"))
			{
				model.addAttribute("resultId", "31801");
				model.addAttribute("resultType", dataFlow.split(":")[1]);
				return "res/gzdh/saveData";
			}
		}else if(isAfter){
			//编辑这条rec
			String version="1.0";
			if(ResRecTypeEnum.AfterEvaluation.getId().equals(funcFlow))
			{
				version="2.0";
			}
			request.setAttribute("recTypeId",funcFlow);
			dataFlow = expressBiz.editZseyExpress(dataFlow, operUserFlow, resultFlow, funcFlow,GlobalConstant.RES_DGDH_DEFAULT_FORM, request,"","",version);
			if("Teacher".equals(roleId)){
				expressBiz.auditDate(userFlow,dataFlow);
			}
		}else {
			//编辑这条rec
			dataFlow = appBiz.editRec(dataFlow, operUserFlow, resultFlow, funcFlow, cataFlow, request);
			if("Teacher".equals(roleId)){
				teacherBiz.auditDate(userFlow,dataFlow);
			}
		}

		return "res/gzdh/saveData";
	}

	@RequestMapping(value={"/delData"},method={RequestMethod.GET})
	public String delData(String userFlow,
			String roleId,
			String deptFlow,
			String doctorFlow,
			String funcTypeId,
			String funcFlow,
			String dataFlow,
			HttpServletRequest request,
			Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "3011001");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/delData";
		}

		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3011002");
			model.addAttribute("resultType", "用户角色为空");
			return "res/gzdh/delData";
		}

		if("Student".equals(roleId)&&StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3011003");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/gzdh/delData";
		}

		if("Teacher".equals(roleId)&&StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3011004");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/gzdh/delData";
		}

		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", "3011005");
			model.addAttribute("resultType", "功能类型为空");
			return "res/gzdh/delData";
		}

		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3011006");
			model.addAttribute("resultType", "功能标识为空");
			return "res/gzdh/delData";
		}

		if(StringUtil.isEmpty(dataFlow)){
			model.addAttribute("resultId", "3011007");
			model.addAttribute("resultType", "登记数据标识为空");
			return "res/gzdh/delData";
		}

		//删除rec
		appBiz.delRec(dataFlow);

		return "res/gzdh/delData";
	}
	@RequestMapping(value={"/qrCode"},method={RequestMethod.POST})
	public String qrCode(String userFlow,
			String roleId,
			String deptFlow,
			String doctorFlow,
			String funcTypeId,
			String funcFlow,
			String codeInfo,
			HttpServletRequest request,
			Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/qrCode";
		}



		if(StringUtil.isNotEquals("qrCode", funcTypeId)){
			model.addAttribute("resultId", "3011104");
			model.addAttribute("resultType", "功能类型错误");
			return "res/gzdh/qrCode";
		}

		Map<String,String> paramMap = new HashMap<String,String>();
		transCodeInfo(paramMap, codeInfo);
		funcFlow=paramMap.get("funcFlow");
		//讲座签到
		if(StringUtil.isEquals(funcFlow, "lectureSignin")){
			String lectureFlow = paramMap.get("lectureFlow");
			if(StringUtil.isBlank(lectureFlow))
			{
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "讲座信息不存在！");
				return "res/gzdh/qrCode";
			}
			if(StringUtil.isBlank(roleId))
			{
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "roleId为空！");
				return "res/gzdh/qrCode";
			}
			if(!"Student".equals(roleId)&&!"Teacher".equals(roleId)&&!"Head".equals(roleId)&&!"Seretary".equals(roleId)) {
				model.addAttribute("resultId", "30906");
				model.addAttribute("resultType", "roleId标识符不正确");
				return "res/gzdh/qrCode";
			}

			SysUser currUser = appBiz.readSysUser(userFlow);
			if(currUser==null){
				model.addAttribute("resultId", "30906");
				model.addAttribute("resultType", "用户不存在");
				return "res/gzdh/qrCode";
			}
			ResDoctor doctor=null;
			if("Student".equals(roleId)) {
				doctor = appBiz.readResDoctor(currUser.getUserFlow());
				if(doctor==null)
				{
					model.addAttribute("resultId", "30906");
					model.addAttribute("resultType", "学员医师信息不存在");
					return "res/gzdh/qrCode";
				}
			}
			ResLectureScanRegist lectureScanRegist = jswjwBiz.searchByUserFlowAndLectureFlow(userFlow,lectureFlow);
			if (lectureScanRegist == null || !"Y".equals(lectureScanRegist.getIsRegist()) ) {
				model.addAttribute("resultId", "3011209");
				model.addAttribute("resultType", "您还没有报名，请先报名！");
				return "res/jswjw/qrCode";
			}
			ResLectureInfo info=appBiz.read(lectureFlow);
			if(info!=null)
			{
				String signTime = paramMap.get("time");
				String key="res_"+info.getOrgFlow()+"_org_jiangzhuo_code_type";
				String cfgv=appBiz.getResCfgCode(key);
				if(GlobalConstant.FLAG_Y.equals(cfgv)) {
					if(StringUtil.isBlank(signTime))
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "无效二维码");
						return "res/gzdh/qrCode";
					}
					//5秒钟有效
					String currTime = DateUtil.getCurrDateTime();
					if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(currTime), DateUtil.transDateTime(signTime)) > 5) {
						model.addAttribute("resultId", "3011106");
						model.addAttribute("resultType", "二维码已失效，请重新扫码！");
						return "res/gzdh/qrCode";
					}
				}
				String key1=appBiz.getResCfgCode("res_"+info.getOrgFlow()+"_org_jiangzuo_start_time");
				int startTime=10;
				if(StringUtil.isNotBlank(key1))
				{
					startTime=Integer.valueOf(key1);
				}
				//扫码报名
				ResLectureScanRegist regist=appBiz.searchByUserFlowAndLectureFlow(userFlow,lectureFlow);
				if(regist!=null)
				{
					if(regist.getIsScan().equals(GlobalConstant.FLAG_Y))
					{
						model.addAttribute("resultId", "3011109");
						model.addAttribute("resultType", "已经扫过码了！");
						return "res/gzdh/qrCode";
					}
					String startDate=info.getLectureTrainDate()+" "+info.getLectureStartTime();
					String endDate=info.getLectureTrainDate()+" "+info.getLectureEndTime();

					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Date date;
					try {
						date = simpleDateFormat.parse(startDate);
						Calendar calender = Calendar.getInstance();
						calender.setTime(date);
						calender.add(Calendar.MINUTE, -startTime);
						startDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");

						calender.add(Calendar.MINUTE, startTime*2);
						endDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");
					} catch (ParseException e) {
					}
					String nowDate=DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");
					if( nowDate.compareTo(startDate)<0)
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "不在扫码时间范围内，无法扫码！");
						return "res/gzdh/qrCode";
					}
					if(nowDate.compareTo(endDate)>0)
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "不在扫码时间范围内，无法扫码！");
						return "res/gzdh/qrCode";
					}
					if(nowDate.compareTo(endDate)>0)
					{
						model.addAttribute("resultId", "30111013");
						model.addAttribute("resultType", "讲座签到时间已结束，无法签到！");
						return "res/gzdh/qrCode";
					}
					regist.setIsScan(GlobalConstant.FLAG_Y);
					regist.setScanTime(DateUtil.getCurrDateTime());
				}else{
					String startDate=info.getLectureTrainDate()+" "+info.getLectureStartTime();
					String endDate=info.getLectureTrainDate()+" "+info.getLectureEndTime();

					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Date date;
					try {
						date = simpleDateFormat.parse(startDate);
						Calendar calender = Calendar.getInstance();
						calender.setTime(date);
						calender.add(Calendar.MINUTE, -startTime);
						startDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");

						calender.add(Calendar.MINUTE, startTime*2);
						endDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");
					} catch (ParseException e) {
					}
					String nowDate=DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");
					if( nowDate.compareTo(startDate)<0)
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "不在扫码时间范围内，无法扫码！");
						return "res/gzdh/qrCode";
					}
					if(nowDate.compareTo(endDate)>0)
					{
						model.addAttribute("resultId", "30111013");
						model.addAttribute("resultType", "讲座签到时间已结束，无法签到！");
						return "res/gzdh/qrCode";
					}
					regist=new ResLectureScanRegist();
					regist.setIsScan(GlobalConstant.FLAG_Y);
					regist.setScanTime(DateUtil.getCurrDateTime());
					regist.setLectureFlow(lectureFlow);
					regist.setOperUserFlow(userFlow);
					regist.setOperUserName(currUser.getUserName());
					if(doctor!=null) {
						regist.setTrainingTypeId(doctor.getTrainingTypeId());
						regist.setTrainingTypeName(doctor.getTrainingTypeName());
						regist.setTrainingSpeId(doctor.getTrainingSpeId());
						regist.setTrainingSpeName(doctor.getTrainingSpeName());
						regist.setSessionNumber(doctor.getSessionNumber());
					}
				}
				int count =appBiz.scanRegist(regist);
				if(count!=1)
				{
					model.addAttribute("resultId", "3011110");
					model.addAttribute("resultType", "扫码失败，请稍后再试！");
					return "res/gzdh/qrCode";
				}
				return "res/gzdh/qrCode";
			}else{
				model.addAttribute("resultId", "3011108");
				model.addAttribute("resultType", "二维码已失效！讲座信息不存在！");

			}

			return "res/gzdh/qrCode";
		}else if(StringUtil.isEquals(funcFlow, "lectureOutSignin")){
			String lectureFlow = paramMap.get("lectureFlow");
			if(StringUtil.isBlank(lectureFlow))
			{
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "讲座信息不存在！");
				return "res/gzdh/qrCode";
			}
			if(StringUtil.isBlank(roleId))
			{
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "roleId为空！");
				return "res/gzdh/qrCode";
			}
			if(!"Student".equals(roleId)&&!"Teacher".equals(roleId)&&!"Head".equals(roleId)&&!"Seretary".equals(roleId)) {
				model.addAttribute("resultId", "30906");
				model.addAttribute("resultType", "roleId标识符不正确");
				return "res/gzdh/qrCode";
			}

			SysUser currUser = appBiz.readSysUser(userFlow);
			if(currUser==null){
				model.addAttribute("resultId", "30906");
				model.addAttribute("resultType", "用户不存在");
				return "res/gzdh/qrCode";
			}
			ResDoctor doctor=null;
			if("Student".equals(roleId)) {
				doctor = appBiz.readResDoctor(currUser.getUserFlow());
				if(doctor==null)
				{
					model.addAttribute("resultId", "30906");
					model.addAttribute("resultType", "学员医师信息不存在");
					return "res/gzdh/qrCode";
				}
			}
			ResLectureInfo info=appBiz.read(lectureFlow);
			if(info!=null)
			{
				String signTime = paramMap.get("time");
				String key="res_"+info.getOrgFlow()+"_org_jiangzuo_code_type";
				String cfgv=appBiz.getResCfgCode(key);
				if(GlobalConstant.FLAG_Y.equals(cfgv)) {
					if(StringUtil.isBlank(signTime))
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "无效二维码");
						return "res/gzdh/qrCode";
					}
					//5秒钟有效
					String currTime = DateUtil.getCurrDateTime();
					if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(currTime), DateUtil.transDateTime(signTime)) > 5) {
						model.addAttribute("resultId", "3011106");
						model.addAttribute("resultType", "二维码已失效，请重新扫码！");
						return "res/gzdh/qrCode";
					}
				}
				String key2=appBiz.getResCfgCode("res_"+info.getOrgFlow()+"_org_jiangzuo_end_time");
				int endTime=10;
				if(StringUtil.isNotBlank(key2))
				{
					endTime=Integer.valueOf(key2);
				}
				//扫码报名
				ResLectureScanRegist regist=appBiz.searchByUserFlowAndLectureFlow(userFlow,lectureFlow);
				if(regist!=null)
				{
					if(!"Y".equals(regist.getIsScan()))
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "你未签到参加该讲座，无法进行签退！");
						return "res/gzdh/qrCode";
					}
					if(regist.getIsScan2().equals(GlobalConstant.FLAG_Y))
					{
						model.addAttribute("resultId", "3011109");
						model.addAttribute("resultType", "已经扫过码了！");
						return "res/gzdh/qrCode";
					}
					String startDate=info.getLectureTrainDate()+" "+info.getLectureStartTime();
					String endDate=info.getLectureTrainDate()+" "+info.getLectureEndTime();

					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Date date;
					try {
						date = simpleDateFormat.parse(endDate);
						Calendar calender = Calendar.getInstance();
						calender.setTime(date);
						calender.add(Calendar.MINUTE, -endTime);
						startDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");

						calender.add(Calendar.MINUTE, endTime*2);
						endDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");
					} catch (ParseException e) {
					}
					String nowDate=DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");
					if( nowDate.compareTo(startDate)<0)
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "不在扫码时间范围内，无法扫码！");
						return "res/gzdh/qrCode";
					}
					if( nowDate.compareTo(endDate)>0)
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "不在扫码时间范围内，无法扫码！");
						return "res/gzdh/qrCode";
					}
					if(nowDate.compareTo(endDate)>0)
					{
						model.addAttribute("resultId", "30111013");
						model.addAttribute("resultType", "讲座已经结束！");
						return "res/gzdh/qrCode";
					}
					regist.setIsScan2(GlobalConstant.FLAG_Y);
					regist.setScan2Time(DateUtil.getCurrDateTime());
				}else{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "你未参加该讲座，无法进行签退！");
					return "res/gzdh/qrCode";
				}
				int count =appBiz.scanRegist(regist);
				if(count!=1)
				{
					model.addAttribute("resultId", "3011110");
					model.addAttribute("resultType", "扫码失败，请稍后再试！");
					return "res/gzdh/qrCode";
				}
				return "res/gzdh/qrCode";
			}else{
				model.addAttribute("resultId", "3011108");
				model.addAttribute("resultType", "二维码已失效！讲座信息不存在！");

			}
			return "res/gzdh/qrCode";
		}

		else if(StringUtil.isEquals(funcFlow, "randomSignIn")){//讲座随机签到

			String scanTime = DateUtil.getCurrDateTime();
			String randomFlow=paramMap.get("randomFlow");
			if(StringUtil.isBlank(randomFlow)) {
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "无效二维码");
				return "res/gzdh/qrCode";
			}
			ResLectureRandomSign sign=appBiz.readLectureRandomSign(randomFlow);
			if(sign==null)
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "随机签到信息不存在");
				return "res/gzdh/qrCode";
			}
			ResLectureInfo lectureInfo=appBiz.read(sign.getLectureFlow());
			if(lectureInfo==null)
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "讲座信息不存在");
				return "res/gzdh/qrCode";
			}
			String nowDate=DateUtil.getCurrDate();
			if(!nowDate.equals(lectureInfo.getLectureTrainDate()))
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "讲座日期不是当前日期");
				return "res/gzdh/qrCode";
			}
			//学员信息
			SysUser docUser=appBiz.readSysUser(userFlow);
			if(docUser==null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "用户信息不存在");
				return "res/gzdh/qrCode";
			}
			ResDoctor doctor=appBiz.readResDoctor(userFlow);
			if(doctor==null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "医师信息不存在");
				return "res/gzdh/qrCode";
			}
			ResLectureScanRegist regist=appBiz.searchByUserFlowAndLectureFlow(userFlow,sign.getLectureFlow());
			if(regist==null||!"Y".equals(regist.getIsScan()))
			{
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "讲座未签到无法进行随机签到");
				return "res/gzdh/qrCode";
			}
			ResLectureRandomScan scan=appBiz.readLectureRandomScan(userFlow,randomFlow);
			if(scan!=null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "此次随机签到，你已完成！");
				return "res/gzdh/qrCode";
			}
			if("Y".equals(sign.getCodeStatusType()))
			{
				String signTime = paramMap.get("time");
				if (StringUtil.isBlank(signTime)) {
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "无效二维码");
					return "res/gzdh/qrCode";
				}
				//5秒钟有效
				if (StringUtil.isBlank(scanTime)) {
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "缺少参数");
					return "res/gzdh/qrCode";
				}
				if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(scanTime), DateUtil.transDateTime(signTime)) > 10) {
					model.addAttribute("resultId", "3011106");
					model.addAttribute("resultType", "二维码已失效，请重新扫码！");
					return "res/gzdh/qrCode";
				}
			}
			String nowTime=DateUtil.transDateTime(scanTime,"yyyyMMddHHmmss","HH:mm:ss");
			if(nowTime.compareTo(sign.getCodeStartTime())<0||nowTime.compareTo(sign.getCodeEndTime())>0)
			{
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "扫码时间不在签到时间范围内！");
				return "res/gzdh/qrCode";
			}
			scan=new ResLectureRandomScan();
			scan.setIsScan(GlobalConstant.FLAG_Y);
			scan.setScanTime(DateUtil.getCurrDateTime());
			scan.setRandomFlow(randomFlow);
			scan.setLectureFlow(lectureInfo.getLectureFlow());
			scan.setOperUserFlow(userFlow);
			scan.setCreateUserFlow(userFlow);
			scan.setCreateTime(DateUtil.getCurrDateTime());
			scan.setModifyUserFlow(userFlow);
			scan.setModifyTime(DateUtil.getCurrDateTime());
			scan.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			if(doctor!=null)
			{
				String session = doctor.getSessionNumber();
				String trainingTypeId = doctor.getTrainingTypeId();
				String trainingTypeName = doctor.getTrainingTypeName();
				String trainingSpeId = doctor.getTrainingSpeId();
				String trainingSpeName = doctor.getTrainingSpeName();
				if (StringUtil.isNotBlank(session)) {
					scan.setSessionNumber(session);
				}
				if (StringUtil.isNotBlank(trainingTypeId)) {
					scan.setTrainingTypeId(trainingTypeId);
				}
				if (StringUtil.isNotBlank(trainingTypeName)) {
					scan.setTrainingTypeName(trainingTypeName);
				}
				if (StringUtil.isNotBlank(trainingSpeId)) {
					scan.setTrainingSpeId(trainingSpeId);
				}
				if (StringUtil.isNotBlank(trainingSpeName)) {
					scan.setTrainingSpeName(trainingSpeName);
				}
			}
			int c=appBiz.saveLectureRandomScan(scan);
			if(c==0){
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "随机签到失败！");
				return "res/gzdh/qrCode";
			}
			return "res/gzdh/qrCode";
		}else if(StringUtil.isEquals(funcFlow, "activitySigin")) {//教学活动签到

				String activityFlow=paramMap.get("activityFlow");
				if(StringUtil.isBlank(activityFlow))
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "二维码错误");
					return "res/gzdh/qrCode";
				}
				TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
				if(info==null||!GlobalConstant.RECORD_STATUS_Y.equals(info.getRecordStatus()))
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "活动信息不存在");
					return "res/gzdh/qrCode";
				}

				String signTime = paramMap.get("time");
				String key="res_"+info.getOrgFlow()+"_org_activity_code_type";
				String cfgv=appBiz.getResCfgCode(key);
				if(GlobalConstant.FLAG_Y.equals(cfgv)) {
					if(StringUtil.isBlank(signTime))
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "无效二维码");
						return "res/gzdh/qrCode";
					}
					//5秒钟有效
					String currTime = DateUtil.getCurrDateTime();
					if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(currTime), DateUtil.transDateTime(signTime)) > 5) {
						model.addAttribute("resultId", "3011106");
						model.addAttribute("resultType", "二维码已失效，请重新扫码！");
						return "res/gzdh/qrCode";
					}
				}
				String key1=appBiz.getResCfgCode("res_"+info.getOrgFlow()+"_org_activity_start_time");
				int startTime=10;
				if(StringUtil.isNotBlank(key1))
				{
					startTime=Integer.valueOf(key1);
				}
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date date;
				String startDate=info.getStartTime();
				String endDate=info.getEndTime();
				try {
					date = simpleDateFormat.parse(info.getStartTime());
					Calendar calender = Calendar.getInstance();
					calender.setTime(date);
					calender.add(Calendar.MINUTE, -startTime);

					startDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");

					calender.add(Calendar.MINUTE, startTime*2);
					endDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");
				} catch (ParseException e) {
					startDate=info.getStartTime();
					endDate=info.getEndTime();
				}
				if( DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm").compareTo(startDate)<0)
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "活动暂未开始，无法参加");
					return "res/gzdh/qrCode";
				}
				if( DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm").compareTo(endDate)>0)
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "活动已结束，无法参加");
					return "res/gzdh/qrCode";
				}
				int count=activityBiz.checkJoin(activityFlow,userFlow);
				if(count>0)
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "该时间段，你已报名参加其他教学活动，无法参加");
					return "res/gzdh/qrCode";
				}

				ResDoctor doctor=appBiz.readResDoctor(userFlow);
				if(doctor==null)
				{
					model.addAttribute("resultId", "30203");
					model.addAttribute("resultType", "医师信息不存在");
					return "res/gzdh/qrCode";
				}
				String orgFlow = "";
				if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
					orgFlow = doctor.getSecondOrgFlow();
				} else {
					orgFlow = doctor.getOrgFlow();
				}
				Map<String,String> param=new HashMap<>();
				param.put("roleFlag","doctor");
				param.put("userFlow",userFlow);
				param.put("infoOrgFlow",info.getOrgFlow());
				param.put("orgFlow",orgFlow);
				param.put("deptFlow",info.getDeptFlow());
				List<Map<String,Object>> list=activityBiz.findActivityList(param);
				if(list==null||list.size()<=0)
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "该教学活动你无权限参加");
					return "res/gzdh/qrCode";
				}
				TeachingActivityResult result=activityBiz.readRegistInfo(activityFlow,userFlow);
				if(result!=null)
				{
					if(GlobalConstant.FLAG_Y.equals(result.getIsScan()))
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "你已扫码签到成功！");
						return "res/gzdh/qrCode";
					}
					result.setIsScan(GlobalConstant.FLAG_Y);
					result.setScanTime(DateUtil.getCurrDateTime());

					int c=activityBiz.saveResult(result,userFlow);
					if(c==0)
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "签到失败！");
						return "res/gzdh/qrCode";
					}
				}else{
					result=new TeachingActivityResult();
					result.setIsScan(GlobalConstant.FLAG_Y);
					result.setScanTime(DateUtil.getCurrDateTime());
					result.setActivityFlow(activityFlow);
					result.setUserFlow(userFlow);
					result.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
					int c=activityBiz.saveResult(result,userFlow);
					if(c==0)
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "签到失败！");
						return "res/gzdh/qrCode";
					}
				}
				return "res/gzdh/qrCode";
		}else if(StringUtil.isEquals(funcFlow, "activityOutSigin")) {//教学活动签退

				String activityFlow=paramMap.get("activityFlow");
				if(StringUtil.isBlank(activityFlow))
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "二维码错误");
					return "res/gzdh/qrCode";
				}
				TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
				if(info==null||!GlobalConstant.RECORD_STATUS_Y.equals(info.getRecordStatus()))
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "活动信息不存在");
					return "res/gzdh/qrCode";
				}
				String signTime = paramMap.get("time");
				String key="res_"+info.getOrgFlow()+"_org_activity_code_type";
				String cfgv=appBiz.getResCfgCode(key);
				if(GlobalConstant.FLAG_Y.equals(cfgv)) {
					if(StringUtil.isBlank(signTime))
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "无效二维码");
						return "res/gzdh/qrCode";
					}
					//5秒钟有效
					String currTime = DateUtil.getCurrDateTime();
					if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(currTime), DateUtil.transDateTime(signTime)) > 5) {
						model.addAttribute("resultId", "3011106");
						model.addAttribute("resultType", "二维码已失效，请重新扫码！");
						return "res/gzdh/qrCode";
					}
				}
				String key2=appBiz.getResCfgCode("res_"+info.getOrgFlow()+"_org_activity_end_time");
				int endTime=10;
				if(StringUtil.isNotBlank(key2))
				{
					endTime=Integer.valueOf(key2);
				}

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date date;
				String startDate=info.getEndTime();
				String endDate=info.getEndTime();
				try {
					date = simpleDateFormat.parse(info.getEndTime());
					Calendar calender = Calendar.getInstance();
					calender.setTime(date);
					calender.add(Calendar.MINUTE, -endTime);
					startDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");

					calender.add(Calendar.MINUTE, endTime*2);
					endDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");
				} catch (ParseException e) {
					startDate=info.getEndTime();
					endDate=info.getEndTime();
				}

				TeachingActivityResult result=activityBiz.readRegistInfo(activityFlow,userFlow);
				if(result==null)
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "你未参加该活动，无法进行签退！");
					return "res/gzdh/qrCode";
				}
				if(!"Y".equals(result.getIsScan()))
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "你未签到参加该活动，无法进行签退！");
					return "res/gzdh/qrCode";
				}
				String nowDate=DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");
				if( nowDate.compareTo(startDate)<0)
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "活动签退暂未开始，无法签退！");
					return "res/gzdh/qrCode";
				}
				if( nowDate.compareTo(endDate)>0)
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "活动签退已结束，无法签退！");
					return "res/gzdh/qrCode";
				}
				if(GlobalConstant.FLAG_Y.equals(result.getIsScan2()))
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "你已扫码签退成功！");
					return "res/gzdh/qrCode";
				}
				result.setIsEffective(GlobalConstant.FLAG_Y);
				result.setIsScan2(GlobalConstant.FLAG_Y);
				result.setScanTime2(DateUtil.getCurrDateTime());
				int c=activityBiz.saveResult(result,userFlow);
				if(c==0)
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "签退失败！");
					return "res/gzdh/qrCode";
				}
				return "res/gzdh/qrCode";
		}else if(StringUtil.isEquals(funcFlow, "signin")){
			String signTime = paramMap.get("signTime");
			String currTime = DateUtil.getCurrDateTime();
			if(StringUtil.isEmpty(deptFlow)){
				model.addAttribute("resultId", "3011102");
				model.addAttribute("resultType", "科室标识符为空");
				return "res/gzdh/qrCode";
			}

			if(StringUtil.isEmpty(funcTypeId)){
				model.addAttribute("resultId", "3011103");
				model.addAttribute("resultType", "功能类型为空");
				return "res/gzdh/qrCode";
			}
			//一分钟有效
			if(DateUtil.signSecondsBetweenTowDate(DateUtil.transDate(currTime),DateUtil.transDate(signTime))>60){
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "二维码已失效！");
				return "res/gzdh/qrCode";
			}
		}else {
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "无效二维码");
			return "res/gzdh/qrCode";
		}

		//签到
		String signinType = SigninTypeEnum.Day.getId();
		boolean result = appBiz.signin(userFlow,deptFlow,signinType);
		if(!result){
			model.addAttribute("resultId", "3011108");
			model.addAttribute("resultType", "您已完成本次签到！");
		}else{
			model.addAttribute("resultId", "3011109");
			model.addAttribute("resultType", "签到成功！");
		}

		return "res/gzdh/qrCode";
	}

	//解析二维码字符串为map
	private void transCodeInfo(Map<String,String> paramMap,String codeInfo){
		String[] params = StringUtil.split(codeInfo, "&");
		for(String paramStr : params){
			if(paramStr.indexOf("=")>0){
				String key = paramStr.substring(0, paramStr.indexOf("="));
			    String value = paramStr.substring(paramStr.indexOf("=")+1, paramStr.length());
			    paramMap.put(key, value);
			}
		}
	}

	@RequestMapping(value={"/schDeptList"},method={RequestMethod.POST})
	public String schDeptList(String userFlow,String searchStr,Integer pageIndex,Integer pageSize,HttpServletRequest request,
							  String standardDeptId,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31601");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/schDeptList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "31602");
			model.addAttribute("resultType", "起始页为空");
			return "res/gzdh/schDeptList";
		}

		if(pageSize==null){
			model.addAttribute("resultId", "31603");
			model.addAttribute("resultType", "页面大小为空");
			return "res/gzdh/schDeptList";
		}

		//读取这个用户的医师信息
		ResDoctor doctor = appBiz.readResDoctor(userFlow);
		if(doctor==null){
			model.addAttribute("resultId", "31604");
			model.addAttribute("resultType", "读取医师信息失败!");
			return "res/gzdh/schDeptList";
		}

		String orgFlow = doctor.getOrgFlow();//医师所在培训机构
		if(StringUtil.isEmpty(orgFlow)){
			model.addAttribute("resultId", "31605");
			model.addAttribute("resultType", "该医师暂无培训基地!");
			return "res/gzdh/schDeptList";
		}

		PageHelper.startPage(pageIndex, pageSize);
		List<SchDeptRel> schDeptList = appBiz.searchRelByStandard(orgFlow,standardDeptId,searchStr);
		model.addAttribute("schDeptList",schDeptList);

		model.addAttribute("dataCount",PageHelper.total);//数据总量

		return "res/gzdh/schDeptList";
	}

	@RequestMapping(value={"/teacherList"},method={RequestMethod.POST})
	public String teacherList(String schDeptFlow,String searchStr,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", "31701");
			model.addAttribute("resultType", "轮转科室标识符为空");
			return "res/gzdh/teacherList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "31702");
			model.addAttribute("resultType", "起始页为空");
			return "res/gzdh/teacherList";
		}

		if(pageSize==null){
			model.addAttribute("resultId", "31703");
			model.addAttribute("resultType", "页面大小为空");
			return "res/gzdh/teacherList";
		}

		String roleFlow = appBiz.getCfgByCode("res_teacher_role_flow");
		if(StringUtil.isNotBlank(roleFlow)){
			PageHelper.startPage(pageIndex, pageSize);
			List<SysUser> userList =  appBiz.getUserListBySchDept(schDeptFlow,roleFlow,searchStr);
			model.addAttribute("userList", userList);
			model.addAttribute("dataCount",PageHelper.total);//数据总量
		}

		return "res/gzdh/teacherList";
	}

	@RequestMapping(value={"/headList"},method={RequestMethod.POST})
	public String headList(String schDeptFlow,String searchStr,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", "31701");
			model.addAttribute("resultType", "轮转科室标识符为空");
			return "res/gzdh/headList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "31702");
			model.addAttribute("resultType", "起始页为空");
			return "res/gzdh/headList";
		}

		if(pageSize==null){
			model.addAttribute("resultId", "31703");
			model.addAttribute("resultType", "页面大小为空");
			return "res/gzdh/headList";
		}

		String roleFlow = appBiz.getCfgByCode("res_head_role_flow");
		if(StringUtil.isNotBlank(roleFlow)){
			PageHelper.startPage(pageIndex, pageSize);
			List<SysUser> userList =  appBiz.getUserListBySchDept(schDeptFlow,roleFlow,searchStr);
			model.addAttribute("userList", userList);
			model.addAttribute("dataCount",PageHelper.total);//数据总量
		}

		return "res/gzdh/headList";
	}

	/**
	 * 新闻动态
	 *
	 * @return
	 */
	@RequestMapping(value = {"/getNews"}, method = {RequestMethod.POST})
	public String getNews(String userFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request,Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/news/getNews";
		}
		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/news/getNews";
		}

		if (pageIndex == null) {
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzdh/news/getNews";
		}
		if (pageSize == null) {
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzdh/news/getNews";
		}
		PageHelper.startPage(pageIndex, pageSize);
		InxInfoForm form = new InxInfoForm();
		form.setRoleFlow(GlobalConstant.RES_NOTICE_SYS_ID);
		form.setColumnId(GlobalConstant.RES_NOTICE_TYPE1_ID);
		form.setIsWithBlobs(GlobalConstant.FLAG_Y);
		List<InxInfo> infoList = inxInfoBiz.getList(form);
		Map<String,Object> isReadMap=new HashMap<>();
		if(infoList!=null&&infoList.size()>0)
		{
			for(InxInfo info:infoList)
			{
				ResReadInfo resReadInfo=inxInfoBiz.getReadInfoByFlow(info.getInfoFlow(),userFlow);
				isReadMap.put(info.getInfoFlow(),resReadInfo);
			}
		}
		model.addAttribute("isReadMap", isReadMap);
		model.addAttribute("infoList", infoList);

		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/gzdh/news/no-pic.png";
		model.addAttribute("hostUrl",hostUrl);
		//获取访问路径前缀
		String uploadBaseUrl = appBiz.getCfgByCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/gzdh/news/getNews";
	}

	@RequestMapping(value={"/newDetail"})
	public String newDetail(String userFlow,String infoFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/news/newDetail";
		}
		if(StringUtil.isEmpty(infoFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "住培动态标识符为空");
			return "res/gzdh/news/newDetail";
		}
		InxInfo info = inxInfoBiz.getByInfoFlow(infoFlow);
		if(info==null)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "住培动态不存在，请刷新列表页面");
			return "res/gzdh/news/newDetail";
		}
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/gzdh/news/showNew.jsp?recordFlow="+infoFlow;
		String androidimgurl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/gzdh/news/showNew2.jsp?recordFlow="+infoFlow;
		model.addAttribute("iosDetailUrl",hostUrl);
		model.addAttribute("androidDetailUrl",androidimgurl);
		return "res/gzdh/news/newDetail";
	}
	@RequestMapping(value={"/showNew"},method ={ RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String showNew(String infoFlow,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map=new HashMap<>();
		InxInfo info = inxInfoBiz.getByInfoFlow(infoFlow);
		map.put("info",info);
		return JSON.toJSONString(map);
	}
	/**
	 * 科教通知
	 *
	 * @return
	 */
	@RequestMapping(value = {"/getSysNotice"}, method = {RequestMethod.POST})
	public String getSysNotice(String userFlow,String isDoctor, Integer pageIndex, Integer pageSize, HttpServletRequest request,Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/sysNotices/getSysNotice";
		}
		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/sysNotices/getSysNotice";
		}

		if (pageIndex == null) {
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzdh/sysNotices/getSysNotice";
		}
		if (pageSize == null) {
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzdh/sysNotices/getSysNotice";
		}
		String orgFlow="";
		orgFlow=userinfo.getOrgFlow();
		if(StringUtil.isNotBlank(isDoctor))//学员
		{
			ResDoctor doctor=appBiz.readResDoctor(userFlow);
			if(doctor!=null)
				orgFlow=doctor.getOrgFlow();
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,String>> infos = this.noticeBiz.searchInfoByOrgBeforeDate(orgFlow,null, GlobalConstant.RES_NOTICE_TYPE5_ID, GlobalConstant.RES_NOTICE_SYS_ID, userFlow,null);
		model.addAttribute("infoList",infos);

		Map<String,Object> isReadMap=new HashMap<>();
		if(infos!=null&&infos.size()>0)
		{
			for(Map<String,String> info:infos)
			{
				ResReadInfo resReadInfo=inxInfoBiz.getReadInfoByFlow(info.get("infoFlow"),userFlow);
				isReadMap.put(info.get("infoFlow"),resReadInfo);
			}
		}
		model.addAttribute("isReadMap", isReadMap);
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/gzdh/sysNotices/no-pic.png";
		model.addAttribute("hostUrl",hostUrl);
		//获取访问路径前缀
		String uploadBaseUrl = appBiz.getCfgByCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/gzdh/sysNotices/getSysNotice";
	}

	@RequestMapping(value={"/sysNoticeDetail"})
	public String sysNoticeDetail(String userFlow,String infoFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/sysNotices/sysNoticeDetail";
		}
		if(StringUtil.isEmpty(infoFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "住培动态标识符为空");
			return "res/gzdh/sysNotices/sysNoticeDetail";
		}
		InxInfo info = inxInfoBiz.getByInfoFlow(infoFlow);
		if(info==null)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "住培动态不存在，请刷新列表页面");
			return "res/gzdh/sysNotices/sysNoticeDetail";
		}
		ResReadInfo resReadInfo=inxInfoBiz.getReadInfoByFlow(infoFlow,userFlow);
		if(resReadInfo==null)
		{
			resReadInfo=new ResReadInfo();
			resReadInfo.setInfoFlow(infoFlow);
			resReadInfo.setUserFlow(userFlow);
			inxInfoBiz.saveReadInfo(userFlow,resReadInfo);
		}
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/gzdh/sysNotices/showSysNotice.jsp?recordFlow="+infoFlow;
		String androidimgurl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/gzdh/sysNotices/showSysNotice2.jsp?recordFlow="+infoFlow;
		model.addAttribute("iosDetailUrl",hostUrl);
		model.addAttribute("androidDetailUrl",androidimgurl);
		return "res/gzdh/sysNotices/sysNoticeDetail";
	}
	@RequestMapping(value={"/showSysNotice"},method ={ RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String showSysNotice(String infoFlow,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map=new HashMap<>();
		InxInfo info = inxInfoBiz.getByInfoFlow(infoFlow);
		map.put("info",info);
		return JSON.toJSONString(map);
	}

	@RequestMapping(value={"/mailList"})
	public String mailList(String userFlow,String role,HttpServletRequest request,Model model,String userName){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/mailList";
		}
		if(StringUtil.isEmpty(role)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "角色标识符为空");
			return "res/gzdh/mailList";
		}
		if(!"Student".equals(role)&&!"Teacher".equals(role)&&!"Head".equals(role)&&!"Spe".equals(role)&&!"Secretary".equals(role)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "角色标识符错误");
			return "res/gzdh/mailList";
		}
//		后台配置是否校验权限时间
		String isPermitOpen = appBiz.getCfgByCode("res_permit_open_doc");
		//获取当前配置的老师角色
		String teacherRole = appBiz.getCfgByCode("res_teacher_role_flow");

		//获取当前配置的教学主任角色
		String headRole = appBiz.getCfgCode("res_head_role_flow");
		//获取当前配置的专业基地管理员角色
		String baseRole = appBiz.getCfgCode("res_professionalBase_admin_role_flow");
		//获取当前配置的教学秘书角色
		String secretaryRole = appBiz.getCfgCode("res_secretary_role_flow");

		//
		List<String> userFlows=new ArrayList<>();
		//科室学员
		List<Map<String,Object>> deptStudents=appBiz.getDeptStudents(userName,userFlow,role);
		clearReUser(deptStudents,userFlows);
		model.addAttribute("deptStudents", deptStudents);
		//科室带教
		List<Map<String,Object>> deptTeachers=appBiz.getDeptTeachers(userName,userFlow,role,teacherRole);
		clearReUser(deptTeachers,userFlows);
		model.addAttribute("deptTeachers", deptTeachers);
		//科室带教
		List<Map<String,Object>> deptSecretarys=appBiz.getDeptTeachers(userName,userFlow,role,secretaryRole);
		clearReUser(deptSecretarys,userFlows);
		model.addAttribute("deptSecretarys", deptSecretarys);
		//专业基地管理员
		List<Map<String,Object>> speUsers=appBiz.getSpeUsers(userName,userFlow,role,baseRole);
		clearReUser(speUsers,userFlows);
		model.addAttribute("speUsers", speUsers);

		//获取访问路径前缀
		String uploadBaseUrl = appBiz.getCfgByCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		if("Student".equals(role))
		{
			//科室主任
			List<Map<String,Object>> deptHeads=appBiz.getDeptHeads(userName,userFlow,role,headRole);
			clearReUser(deptHeads,userFlows);
			model.addAttribute("deptHeads", deptHeads);

		}else if("Spe".equals(role))
		{
			//科室主任
			List<Map<String,Object>> deptHeads=appBiz.getDeptHeads(userName,userFlow,role,headRole);
			clearReUser(deptHeads,userFlows);
			model.addAttribute("deptHeads", deptHeads);

		}else if("Teacher".equals(role)||"Head".equals(role))
		{
			//科室主任
			List<Map<String,Object>> deptHeads=appBiz.getDeptHeads(userName,userFlow,role,headRole);
			clearReUser(deptHeads,userFlows);
			model.addAttribute("deptHeads", deptHeads);
			boolean isTeacher=false;
			boolean isHeader=false;
			List<SysUserRole> userRoles = appBiz.getSysUserRole(userFlow);
			//获取最先匹配到的角色,认定该用户的角色为匹配到的角色
			for(SysUserRole sur : userRoles){
				String ur = sur.getRoleFlow();
				 if(teacherRole.equals(ur)){
					isTeacher = true;
				}else if(headRole.equals(ur)){
					isHeader = true;
				}
			}
			if(isHeader&&isTeacher) role="Head";
			List<Map<String,Object>> getCycleStudents=appBiz.getCycleStudents(userName,userFlow,role,isPermitOpen);
			clearReUser(getCycleStudents,userFlows);
			model.addAttribute("getCycleStudents", getCycleStudents);

		}
		return "res/gzdh/mailList";
	}
	public void clearReUser(List<Map<String,Object>> deptStudents,List<String> userFlows)
	{
		if(deptStudents!=null&&deptStudents.size()>0)
		{
			for(int i=0;i<deptStudents.size();i++)
			{
				Map<String,Object> u=deptStudents.get(i);
				if(!userFlows.contains(String.valueOf(u.get("userFlow"))))
				{
					userFlows.add(String.valueOf(u.get("userFlow")));
				}else{
					deptStudents.remove(i);
					i--;
				}
			}
		}
	}
	@RequestMapping(value={"/findActivityList"},method={RequestMethod.GET,RequestMethod.POST})
	public String findActivityList(String userFlow,String typeId,String isCurrent,String deptFlow,Integer pageIndex,Integer pageSize,HttpServletRequest request,HttpServletResponse response,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/findActivityList";
		}
		if(StringUtil.isEmpty(typeId)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "类型标识符为空");
			return "res/gzdh/findActivityList";
		}
		if(!"isNew".equals(typeId)&&!"isEval".equals(typeId))
		{
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "类型标识符不正确,isNew，isEval");
			return "res/gzdh/findActivityList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30202");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzdh/findActivityList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzdh/findActivityList";
		}
		ResDoctor doctor=appBiz.readResDoctor(userFlow);
		if(doctor==null)
		{
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "医师信息不存在");
			return "res/gzdh/findActivityList";
		}
		Map<String,String> param=new HashMap<>();
		if ("isNew".equals(typeId)) {
			param.put("isNew","Y");//最新活动
		}
		if ("isEval".equals(typeId)) {
			param.put("isEval","Y");//活动评价
		}
		param.put("roleFlag","doctor");
		param.put("userFlow",userFlow);
		String orgFlow = "";
		if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
			orgFlow = doctor.getSecondOrgFlow();
		} else {
			orgFlow = doctor.getOrgFlow();
		}

		List<Map<String, Object>> depts=activityBiz.searchDeptByDoctor(userFlow,orgFlow);
		model.addAttribute("depts",depts);
		param.put("orgFlow",orgFlow);
		param.put("isCurrent",isCurrent);
		param.put("deptFlow",deptFlow);
		model.addAttribute("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> list=activityBiz.findActivityList(param);
		model.addAttribute("list", list);

		model.addAttribute("dataCount", PageHelper.total);
		return "res/gzdh/findActivityList";
	}
	@RequestMapping(value = "/joinActivity")
	public String joinActivity(Model model, String activityFlow, String userFlow) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/success";
		}
		if (StringUtil.isEmpty(activityFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/gzdh/success";
		}
		TeachingActivityInfo info = activityBiz.readActivityInfo(activityFlow);
		if (info == null || !GlobalConstant.FLAG_Y.equals(info.getRecordStatus())) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "活动信息不存在，请刷新列表页面！");
			return "res/gzdh/success";
		}

		if (DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm").compareTo(info.getStartTime()) >0) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "活动已开始，无法报名！");
			return "res/gzdh/success";
		}
		TeachingActivityResult result = activityBiz.readRegistInfo(activityFlow, userFlow);
		if(result!=null&&GlobalConstant.FLAG_Y.equals(result.getIsRegiest()))
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "你已报名，请勿重复报名！");
			return "res/gzdh/success";
		}
		List<TeachingActivityInfo> infos=activityBiz.checkJoinList(activityFlow,userFlow);
		if(infos!=null&&infos.size()>0)
		{
			TeachingActivityInfo activityInfo=infos.get(0);
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "已报名同一时间【"+activityInfo.getActivityName()+"】，不能报名！");
			return "res/gzdh/success";
		}
		if(result==null){
			result = new TeachingActivityResult();
		}
		result.setActivityFlow(activityFlow);
		result.setUserFlow(userFlow);
		result.setIsRegiest(GlobalConstant.FLAG_Y);
		result.setRegiestTime(DateUtil.getCurrDateTime());
		result.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		int c = activityBiz.saveResult(result, userFlow);
		if (c == 0) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "报名失败！");
			return "res/gzdh/success";
		}
		return "res/gzdh/success";

	}
	@RequestMapping(value="/cannelRegiest")
	public String cannelRegiest(Model model,String activityFlow,String resultFlow, String userFlow){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/success";
		}
		if (StringUtil.isEmpty(activityFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/gzdh/success";
		}
		TeachingActivityInfo info = activityBiz.readActivityInfo(activityFlow);
		if (info == null || !GlobalConstant.FLAG_Y.equals(info.getRecordStatus())) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "活动信息不存在，请刷新列表页面！");
			return "res/gzdh/success";
		}
		TeachingActivityResult result = activityBiz.readRegistInfo(activityFlow, userFlow);
		if (result == null) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "你未报名，无法取消报名信息！");
			return "res/gzdh/success";
		}
		if (!GlobalConstant.FLAG_Y.equals(result.getIsRegiest()))
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "你已取消报名！");
			return "res/gzdh/success";
		}
		if(GlobalConstant.FLAG_Y.equals(result.getIsScan()))
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "你已扫码签到，无法取消报名信息！");
			return "res/gzdh/success";
		}
		result.setIsRegiest(GlobalConstant.FLAG_N);
		int c = activityBiz.saveResult(result, userFlow);
		if (c == 0) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "取消报名失败！");
			return "res/gzdh/success";
		}
		return "res/gzdh/success";

	}
	@RequestMapping(value = "/showDocEval")
	public String showDocEval(Model model, String resultFlow, String userFlow) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/showDocEval";
		}
		if (StringUtil.isEmpty(resultFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "评价标识符为空");
			return "res/gzdh/showDocEval";
		}
		//评价人员
		TeachingActivityResult result=activityBiz.readResult(resultFlow);
		model.addAttribute("result",result);
		if(result!=null)
		{
			//评价人员评分详情
			Map<String,Object> evalDetailMap=new HashMap<>();
			//评价的指标
			List<Map<String,Object>> targets=activityBiz.readActivityTargetEvals(result.getActivityFlow());
			model.addAttribute("targets",targets);
			List<TeachingActivityEval> evals=activityBiz.readActivityResultEvals(resultFlow);
			if(evals!=null)
			{
				for(TeachingActivityEval e:evals)
				{
					evalDetailMap.put(e.getResultFlow()+e.getTargetFlow(),e.getEvalScore());
				}
			}
			model.addAttribute("evalDetailMap",evalDetailMap);
		}else{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "扫码信息不存在 ！");
			return "res/gzdh/showDocEval";
		}
		return "res/gzdh/showDocEval";

	}
	@RequestMapping(value = {"/saveEvalInfo"}, method = {RequestMethod.POST,RequestMethod.GET})
	public	 String saveEvalInfo(String evals, String resultFlow,String userFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws DocumentException {

		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/success";
		}
		if (StringUtil.isEmpty(resultFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "请选择需要评价的活动");
			return "res/gzdh/success";
		}
		TeachingActivityResult result=activityBiz.readResult(resultFlow);
		if(result==null)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "你未扫码参加该活动，无法评价");
			return "res/gzdh/success";
		}
		if(result!=null&&result.getEvalScore()!=null)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "该活动已评价，请刷新页面！");
			return "res/gzdh/success";
		}
		if(StringUtil.isBlank(evals))
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "请选择评分！");
			return "res/gzdh/success";
		}
		List<TeachingActivityEval> activityEvals=null;
		try {
			activityEvals=JSON.parseArray(evals, TeachingActivityEval.class);
		}catch (Exception e){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "评分数据格式不正确！");
			return "res/gzdh/success";
		}
		if(activityEvals==null||activityEvals.size()<=0)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "请选择评分！");
			return "res/gzdh/success";
		}
		int count=activityBiz.saveEvalInfo(activityEvals, resultFlow,userFlow);
		if(count==0)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "评价失败！");
			return "res/gzdh/success";
		}
		return "res/gzdh/success";
	}
}

