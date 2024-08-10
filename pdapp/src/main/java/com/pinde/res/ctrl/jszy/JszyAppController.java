package com.pinde.res.ctrl.jszy;


import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.app.common.PasswordUtil;
import com.pinde.core.commom.enums.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.common.IResPowerCfgBiz;
import com.pinde.res.biz.hbres.IHbresAppBiz;
import com.pinde.res.biz.jswjw.IResLiveTrainingBiz;
import com.pinde.res.biz.jswjw.ISchExamCfgBiz;
import com.pinde.res.biz.jszy.IJszyAppBiz;
import com.pinde.res.biz.jszy.IJszyStudentBiz;
import com.pinde.res.biz.jszy.IJszyTeacherBiz;
import com.pinde.res.biz.sctcm120.ISctcm120AppBiz;
import com.pinde.res.biz.stdp.*;
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
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/res/jszy")
public class JszyAppController{
	private static Logger logger = LoggerFactory.getLogger(JszyAppController.class);

	@Autowired
	private IHbresAppBiz hbresAppBiz;
	@Autowired
	private IResActivityBiz activityBiz;
	@Autowired
	private IResActivityTargetBiz activityTargeBiz;

	@Autowired
	private ISchExamCfgBiz examCfgBiz;
	@Autowired
	private IJszyAppBiz jszyAppBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private IJszyStudentBiz jszyStudentBiz;
	@Autowired
	private IJszyTeacherBiz teacherBiz;
	@Autowired
	private IResGradeBiz gradeBiz;
	@Autowired
	private IResPowerCfgBiz resPowerCfgBiz;

	@Autowired
	private IInxInfoBiz inxInfoBiz;
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private IResLiveTrainingBiz resLiveTrainingBiz;
	@Autowired
	private IExamResultsBiz examResultsBiz;
	@Autowired
	private ResPaperBiz paperBiz;
	@Autowired
	private ISchAndStandardDeptCfgBiz deptCfgBiz;
	@Autowired
	private ISctcm120AppBiz sctcm120AppBiz;

	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/jszy/500";
    }

	@RequestMapping(value={"/version"},method={RequestMethod.GET})
	public String version(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "res/jszy/version";
	}


	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(){
		return "res/jszy/test";
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
		return "/res/jszy/test";
	}

	@RequestMapping(value={"/login"},method={RequestMethod.POST})
	public String login(String userCode,String userPasswd,String uuid,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(userCode)){
			model.addAttribute("resultId", "3010101");
			model.addAttribute("resultType", "用户名为空");
			return "res/jszy/login";
		}

		if(StringUtil.isEmpty(userPasswd)){
			model.addAttribute("resultId", "3010102");
			model.addAttribute("resultType", "密码为空");
			return "res/jszy/login";
		}

		//验证用户是否存在
		SysUser userinfo = jszyAppBiz.getUserByCode(userCode);
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
				return "res/jszy/login";
			}

			//验证用户是否锁定,锁定用户不能登录
			String userStatus = userinfo.getStatusId();
			if(UserStatusEnum.Locked.getId().equals(userStatus)){
				model.addAttribute("resultId", "3010105");
				model.addAttribute("resultType", "该用户已被锁定");
				return "res/jszy/login";
			}

			//验证用户是否有角色
			List<SysUserRole> userRoles = jszyAppBiz.getSysUserRole(userFlow);
			if(userRoles==null || userRoles.isEmpty()){
				model.addAttribute("resultId", "3010106");
				model.addAttribute("resultType", "用户未赋权");
				return "res/jszy/login";
			}

			boolean isDoctor = false;
			boolean isTeacher = false;

			//获取当前配置的医师角色
			String doctorRole = jszyAppBiz.getCfgByCode("res_doctor_role_flow");

			//获取当前配置的老师角色
			String teacherRole = jszyAppBiz.getCfgByCode("res_teacher_role_flow");

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
					model.addAttribute("roleName","老师");
					break;
				}
			}

			model.addAttribute("isDoctor", isDoctor);
			model.addAttribute("isTeacher", isTeacher);
			String orgFlow2=userinfo.getOrgFlow();
			if(!isDoctor&&!isTeacher){
				model.addAttribute("resultId", "3010106");
				model.addAttribute("resultType", "用户权限不足！");
				return "res/jszy/login";
			}
			//如果是医师需要获取医师的一些培训信息
			if(isDoctor){
				//读取是否开启自主增加轮转计划开关 res_custom_result_flag
				String isManualFlag = jszyAppBiz.getCfgByCode("res_custom_result_flag");
				if(!GlobalConstant.FLAG_Y.equals(isManualFlag)){
					isManualFlag = GlobalConstant.FLAG_N;
				}
				model.addAttribute("manualRotationFlag",isManualFlag);
				//读取是否允许学员自己入科开关 res_custom_result_flag
				String isInBySelfFlag = jszyAppBiz.getCfgByCode("res_doc_in_by_self");
				if(!GlobalConstant.FLAG_Y.equals(isInBySelfFlag)){
					isInBySelfFlag = GlobalConstant.FLAG_N;
				}
				model.addAttribute("isInBySelfFlag",isInBySelfFlag);

				//出科考核对接判断
				boolean ckk=false;
				String regScore=jszyAppBiz.getCfgByCode("res_doc_reg_score");
				if(GlobalConstant.FLAG_Y.equals(regScore))
				{
					String switchFlag=jszyAppBiz.getCfgByCode("res_after_test_switch");
					String urlCfg=jszyAppBiz.getCfgByCode("res_mobile_after_url_cfg");
					//学员开通出科考试权限
					Map<String,String> paramMap = new HashMap();
					String cfgCode = "res_doctor_ckks_" + userFlow;
					paramMap.put("cfgCode",cfgCode);
					String isCkksFlag = resPowerCfgBiz.getResPowerCfg(paramMap);
					if(GlobalConstant.FLAG_Y.equals(switchFlag) && GlobalConstant.FLAG_Y.equals(isCkksFlag)
							&& StringUtil.isNotBlank(urlCfg))
					{
						ckk=true;
					}

				}
				model.addAttribute("isCkk",ckk);
				//读取这个用户的医师信息
				ResDoctor doctor = jszyAppBiz.readResDoctor(userFlow);

				if(doctor==null){
					model.addAttribute("resultId", "3010107");
					model.addAttribute("resultType", "登录失败,学员数据出错!");
					return "res/jszy/login";
				}else{
					String key1=hbresAppBiz.getJsResCfgCode(doctor.getOrgFlow()+"_bind");
					if("Y".equals(key1)) {
						if (StringUtil.isBlank(uuid)) {
							model.addAttribute("resultId", "3010105");
							model.addAttribute("resultType", "请输入手机uuid");
							return "res/hbres/login";
						}
						ResUserBindMacid macid = hbresAppBiz.readMacidByUserFlow(userinfo.getUserFlow());
						if (macid != null && StringUtil.isNotBlank(macid.getMacId()) && !macid.getMacId().equals(uuid)) {
							model.addAttribute("resultId", "3010105");
							model.addAttribute("resultType", "此账号已绑定其他手机，如需更改绑定手机，请联系管理员解绑");
							return "res/hbres/login";
						}
//				if(macid==null)
//				{
//					macid=hbresAppBiz.readMacidByMacid(uuid);
//
//					if(macid!=null&&StringUtil.isNotBlank(macid.getUserFlow())&&!macid.getUserFlow().equals(userinfo.getUserFlow()))
//					{
//						model.addAttribute("resultId", "3010105");
//						model.addAttribute("resultType", "此手机已绑定其他帐号，如需更改绑定手机，请联系管理员解绑");
//						return "res/hbres/login";
//					}
//				}
						if (macid == null) {
							macid = new ResUserBindMacid();
							macid.setUserFlow(userinfo.getUserFlow());
						}
						if (StringUtil.isBlank(macid.getMacId())) {
							macid.setMacId(uuid);
							hbresAppBiz.saveUserMacid(macid);
						}
					}
					if(GlobalConstant.FLAG_Y.equals(doctor.getIsSchFlag()))
					{
						isManualFlag = GlobalConstant.FLAG_N;
					}
					//验证该医师是否存在培训机构,如果存在则验证该机构是否开启过程
					String orgFlow = doctor.getOrgFlow();
					 orgFlow2 = doctor.getOrgFlow();
					if(StringUtil.isNotBlank(orgFlow)){
//						String isRotationFlag = jszyAppBiz.getCfgByCode("jswjw_"+orgFlow+"_P001");
//						if(!GlobalConstant.FLAG_Y.equals(isRotationFlag)){
//							model.addAttribute("resultId", "3010108");
//							model.addAttribute("resultType", "登录失败,你所在培训基地暂未开通过程系统!");
//							return "res/jszy/login";
//						}
//
//						String isAppFlag = jszyAppBiz.getCfgByCode("jswjw_"+orgFlow+"_P005");
//						if(!GlobalConstant.FLAG_Y.equals(isAppFlag)){
//							model.addAttribute("resultId", "3010110");
//							model.addAttribute("resultType", "登录失败,你所在培训基地暂未开通过程APP!");
//							return "res/jszy/login";
//						}
//						学员开通app登录权限
						Map<String,String> paramMap = new HashMap();
//						是否开通APP
						String isAppFlag = "";
//						后台配置是否校验权限时间
						String isPermitOpen = jszyAppBiz.getCfgByCode("res_permit_open_doc");
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
							return "res/jszy/login";
						}
						//派送单位或者学校
//						if(JszyResDocTypeEnum.Graduate.getId().equals(doctor.getDoctorTypeId())){
//							if(StringUtil.isNotBlank(doctor.getWorkOrgId())){
//								String isSendFlag = jszyAppBiz.getCfgByCode("jswjw_sendSchool_"+doctor.getWorkOrgId()+"_P007");
//								if(GlobalConstant.FLAG_Y.equals(isSendFlag)){
//									model.addAttribute("resultId", "3010110");
//									model.addAttribute("resultType", "你暂无权限使用,请联系培训基地管理员或学校培养部门！");
//									return "res/jszy/login";
//								}
//							}
//						}//住院医师缩减调整
						boolean isReduction = false;
						String trainingType = doctor.getTrainingTypeId();
						if (JszyTrainCategoryEnum.ChineseMedicine.getId().equals(trainingType)||JszyTrainCategoryEnum.TCMGeneral.getId().equals(trainingType)) {
							String sessionNumber = doctor.getSessionNumber();
							String trainingYears = doctor.getTrainingYears();
							isReduction = JszyTrainCategoryEnum.ChineseMedicine.getId().equals(trainingType)||JszyTrainCategoryEnum.TCMGeneral.getId().equals(trainingType);;
							isReduction = isReduction && "2015".compareTo(sessionNumber) <= 0;
							isReduction = isReduction && (JszyResTrainYearEnum.OneYear.getId().equals(trainingYears) || JszyResTrainYearEnum.TwoYear.getId().equals(trainingYears));
						}

						if (isReduction) {
//							Map<String, SchDoctorDept> doctorDeptMap = jszyAppBiz.getReductionDeptMap(doctor.getDoctorFlow(), doctor.getRotationFlow(), doctor.getSecondRotationFlow());
//							if (doctorDeptMap.isEmpty()) {
//								model.addAttribute("resultId", "30195");
//								model.addAttribute("resultType", "轮转方案未调整减免，请联系管理员！");
//								return "res/jszy/login";
//							}
						}
					}else{
						model.addAttribute("resultId", "3010109");
						model.addAttribute("resultType", "登录失败,该学员暂无培训机构!");
						return "res/jszy/login";
					}
				}

				model.addAttribute("doctor", doctor);

				//获取该用户的入科记录的第一次入科时间和最后一次出科时间
				Map<String,Object> processAreaMap = jszyAppBiz.getDocProcessArea(userFlow);
				if(processAreaMap!=null){
					String minDate = (String)processAreaMap.get("minDate");
					model.addAttribute("minDate",minDate);
					doctor.setInHosDate(minDate);
					jszyAppBiz.editResDoctor(doctor);
					long schDays = 0;
					if(StringUtil.isNotBlank(minDate)){
						String currDate = DateUtil.getCurrDate();
						model.addAttribute("maxDate",currDate);

						//获取该医师的已轮转天数
						schDays = DateUtil.signDaysBetweenTowDate(currDate,minDate)+1;

						//获取该医师的轮转计划的区间
						Map<String,Object> resultAreaMap = jszyAppBiz.getDocResultArea(userFlow);
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
				}else{
					model.addAttribute("schDays",0);
				}
			}else if(isTeacher){
//				model.addAttribute("resultId", "3010108");
//				model.addAttribute("resultType", "登录失败,带教端暂未开放!");
//				return "res/jszy/login";
				//验证该医师是否存在培训机构,如果存在则验证该机构是否开启过程
				String orgFlow = userinfo.getOrgFlow();
				if(StringUtil.isNotBlank(orgFlow)){
					String isRotationFlag = jszyAppBiz.getCfgByCode("jswjw_"+orgFlow+"_P001");
					if(!GlobalConstant.FLAG_Y.equals(isRotationFlag)){
						model.addAttribute("resultId", "3010108");
						model.addAttribute("resultType", "登录失败,您所在机构暂未开通过程系统!");
						return "res/jszy/login";
					}

					String isAppFlag = jszyAppBiz.getCfgByCode("jswjw_"+orgFlow+"_P006");
					if(!GlobalConstant.FLAG_Y.equals(isAppFlag)){
						model.addAttribute("resultId", "3010110");
						model.addAttribute("resultType", "登录失败,您所在机构暂未开通过程APP!");
						return "res/jszy/login";
					}

				}else{
					model.addAttribute("resultId", "3010109");
					model.addAttribute("resultType", "登录失败,该带教暂无所属机构!");
					return "res/jszy/login";
				}
			}
			model.addAttribute("userinfo", userinfo);

			List<Map<String,String>> infos = this.noticeBiz.searchInfoByOrgNotRead(orgFlow2, GlobalConstant.RES_NOTICE_TYPE5_ID, GlobalConstant.RES_NOTICE_SYS_ID, userFlow);
			if(infos!=null)
			{
				model.addAttribute("hasNotReadInfo",infos.size());
			}else{
				model.addAttribute("hasNotReadInfo",0);
			}
		}
		return "res/jszy/login";
	}

	@RequestMapping(value={"/notice"},method={RequestMethod.GET})
	public String notice(String userFlow,String roleId,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		String result = "res/jszy/notice";
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
		String result = "res/jszy/noticeCount";
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
			return "res/jszy/viewData";
		}

		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3010602");
			model.addAttribute("resultType", "用户角色为空");
			return "res/jszy/viewData";
		}

		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", "3010603");
			model.addAttribute("resultType", "功能类型为空");
			return "res/jszy/viewData";
		}

		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3010604");
			model.addAttribute("resultType", "功能标识为空");
			return "res/jszy/viewData";
		}

		if("Student".equals(roleId)&&StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3010605");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/jszy/viewData";
		}

		if("Teacher".equals(roleId)&&StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3010606");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jszy/viewData";
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
		SchArrangeResult result = jszyStudentBiz.searcheDocResult(null,resultFlow);
		if(result!=null){
			model.addAttribute("result",result);

			//读取要求
			if(StringUtil.isNotBlank(cataFlow)){
				String standardDeptId = result.getStandardDeptId();
				String standardGroupFlow = result.getStandardGroupFlow();
				SchRotationDept rotationDept = jszyAppBiz.getRotationDeptByResult(standardGroupFlow,standardDeptId);
				if(rotationDept!=null){
					String relRecordFlow = rotationDept.getRecordFlow();
					req = jszyAppBiz.readReq(null,relRecordFlow,cataFlow);
				}
			}
		}

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
				||ResRecTypeEnum.MonthlyAssessment_inpatientArea.getId().equals(funcFlow)
				||ResRecTypeEnum.MonthlyAssessment_clinic.getId().equals(funcFlow)
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
				ResDoctorSchProcess process = jszyStudentBiz.getProcessByResult(resultFlow);
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
				model.addAttribute("gradeInfo",gradeInfo);
			}
		}else if(isAfter){
			//获取当前这条登记信息
			ResSchProcessExpress rec = null;
			String processFlow = "";
			ResDoctorSchProcess process = jszyStudentBiz.getProcessByResult(resultFlow);
			if(process!=null){
				//登记表单的科室
				processFlow = process.getProcessFlow();
			}
			rec = expressBiz.getExpressByRecType(processFlow,funcFlow);
			if(rec!=null){
				String content = rec.getRecContent();
				//解析登记信息的xml
				Object formDataMap = jszyAppBiz.parseRecContent(content);
				model.addAttribute("formDataMap",formDataMap);
				//如果读取到数据就使用数据本身的funcFlow
				funcFlow = rec.getRecTypeId();
			}
			//不可操作开关
			boolean isAudit = rec!=null && StringUtil.isNotBlank(rec.getAuditStatusId());
			model.addAttribute("cannotOperSwitch",(tSwitch || isAudit));
			model.addAttribute("isAudit",isAudit);
		}else{
			//获取当前这条登记信息
			ResRec rec = null;
			if(StringUtil.isNotBlank(dataFlow)){
				rec = jszyAppBiz.getRecByRecFlow(dataFlow);
				//如果是11模式根据类型和科室查询
			}else if("dataInput11".equals(funcTypeId)){
				String processFlow = "";
				ResDoctorSchProcess process = jszyStudentBiz.getProcessByResult(resultFlow);
				if(process!=null){
					//登记表单的科室
					processFlow = process.getProcessFlow();
				}
				rec = jszyAppBiz.getRecByRecType(processFlow,funcFlow);
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
				Object formDataMap = jszyAppBiz.parseRecContent(content);
				model.addAttribute("formDataMap",formDataMap);

				//如果读取到数据就使用数据本身的funcFlow
				funcFlow = rec.getRecTypeId();
			}

			//不可操作开关
            boolean notPass=false;
			boolean isAudit = rec!=null && StringUtil.isNotBlank(rec.getAuditStatusId());
			if(isAudit){
				if(rec.getAuditStatusId().equals(RecStatusEnum.TeacherAuditN.getId()))
				{
                    notPass=true;
					model.addAttribute("notPass",notPass);
				}
			}
			model.addAttribute("cannotOperSwitch",(tSwitch || isAudit&&!notPass));
			model.addAttribute("isAudit",isAudit);
		}


		model.addAttribute("funcFlow",funcFlow);

		//老师和学生的控件开关
		model.addAttribute("sSwitch",sSwitch);
		model.addAttribute("tSwitch",tSwitch);

		return "res/jszy/viewData";
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
			return "res/jszy/dataList";
		}

		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3010702");
			model.addAttribute("resultType", "用户角色为空");
			return "res/jszy/dataList";
		}

		if("Student".equals(roleId)&&StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3010703");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/jszy/dataList";
		}

		if("Teacher".equals(roleId)&&StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3010704");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jszy/dataList";
		}

		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", "3010705");
			model.addAttribute("resultType", "功能类型为空");
			return "res/jszy/dataList";
		}

		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3010706");
			model.addAttribute("resultType", "功能标识为空");
			return "res/jszy/dataList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "3010707");
			model.addAttribute("resultType", "起始页为空");
			return "res/jszy/dataList";
		}

		if(pageSize==null){
			model.addAttribute("resultId", "3010708");
			model.addAttribute("resultType", "页面大小为空");
			return "res/jszy/dataList";
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
		ResDoctorSchProcess process = jszyStudentBiz.getProcessByResult(resultFlow);
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
			recMaps = jszyAppBiz.getParsedRecs(paramMap);
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
			recMaps = jszyAppBiz.getTeacherParsedRecs(paramMap);
		}
		model.addAttribute("path",path);
		model.addAttribute("recMaps",recMaps);

		//百分比算法
		Map<String,Object> perMap = jszyStudentBiz.getRegPer(0, userFlow, resultFlow,funcFlow,cataFlow,true);
		model.addAttribute("perMap",perMap);

		model.addAttribute("dataCount",PageHelper.total);

		return "res/jszy/dataList";
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
			return "res/jszy/cataList";
		}
		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3010802");
			model.addAttribute("resultType", "用户角色为空");
			return "res/jszy/cataList";
		}
		if("Student".equals(roleId)&&StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3010803");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/jszy/cataList";
		}
		if("Teacher".equals(roleId)&&StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3010804");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jszy/cataList";
		}
		if(StringUtil.isNotEquals(funcTypeId, "dataInputNN")){
			model.addAttribute("resultId", "3010805");
			model.addAttribute("resultType", "功能类型不匹配");
			return "res/jszy/cataList";
		}
		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3010806");
			model.addAttribute("resultType", "功能标识为空");
			return "res/jszy/cataList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "3010807");
			model.addAttribute("resultType", "起始页为空");
			return "res/jszy/cataList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3010808");
			model.addAttribute("resultType", "页面大小为空");
			return "res/jszy/cataList";
		}

		//轮转计划
		String resultFlow = deptFlow;

		if("Teacher".equals(roleId)){
			resultFlow = doctorFlow;
		}

		//解析查询条件json字符串
		Map<String,String> searchMap = null;
		if(StringUtil.isNotBlank(searchData)){
			try {
				searchData = new String(searchData.getBytes("ISO8859-1") , "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
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
		SchArrangeResult result = jszyStudentBiz.searcheDocResult(null,resultFlow);
		if(result!=null){
			String standardDeptId = result.getStandardDeptId();
			String standardGroupFlow = result.getStandardGroupFlow();
			SchRotationDept rotationDept = jszyAppBiz.getRotationDeptByResult(standardGroupFlow,standardDeptId);
			if(rotationDept!=null){
				String rocordFlow = rotationDept.getRecordFlow();
				paramMap.put("relRecordFlow",rocordFlow);
			}
		}

		//根据条件获取要求列表
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> reqMaps = jszyAppBiz.getReqByResult(paramMap);
		model.addAttribute("reqMaps",reqMaps);

		model.addAttribute("dataCount",PageHelper.total);

		//百分比算法
		Map<String,Object> perMap = jszyStudentBiz.getRegPer(0, userFlow, resultFlow,funcFlow);
		model.addAttribute("perMap",perMap);

		String recTypeName = ResRecTypeEnum.getNameById(funcFlow);
		model.addAttribute("recTypeName",recTypeName);

		return "res/jszy/cataList";
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
			return "res/jszy/saveData";
		}

		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3010902");
			model.addAttribute("resultType", "用户角色为空");
			return "res/jszy/saveData";
		}

		if("Student".equals(roleId)&&StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3010903");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/jszy/saveData";
		}

		if("Teacher".equals(roleId)&&StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3010904");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jszy/saveData";
		}

		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", "3010905");
			model.addAttribute("resultType", "功能类型为空");
			return "res/jszy/saveData";
		}

		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3010906");
			model.addAttribute("resultType", "功能标识为空");
			return "res/jszy/saveData";
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
				||ResRecTypeEnum.MonthlyAssessment_clinic.getId().equals(funcFlow)
				||ResRecTypeEnum.MonthlyAssessment_inpatientArea.getId().equals(funcFlow)
				)
		{
			isAfter=true;
		}
		String oldContent="";
		//是否是评分，如果是评分，则去DEPT_TEACHER_GRADE_INFO表取数据
		if(isGrade)
		{
			//读取相关信息
			String processFlow = "";
			ResDoctorSchProcess process = jszyStudentBiz.getProcessByResult(resultFlow);
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
			String processFlow = "";
			ResDoctorSchProcess process = jszyStudentBiz.getProcessByResult(resultFlow);
			if (process != null) {
				//登记表单的科室
				processFlow = process.getProcessFlow();

				operUserFlow = process.getUserFlow();
			}
			rec = expressBiz.getExpressByRecType(processFlow, funcFlow);
			if (rec != null) {
				dataFlow = rec.getRecFlow();
			}
			if (rec != null) {
				funcFlow = rec.getRecTypeId();
			}
		}else {
			//如果是11查出dataFlow
			ResRec rec = null;
			if ("dataInput11".equals(funcTypeId)) {
				String processFlow = "";
				ResDoctorSchProcess process = jszyStudentBiz.getProcessByResult(resultFlow);
				if (process != null) {
					//登记表单的科室
					processFlow = process.getProcessFlow();

					operUserFlow = process.getUserFlow();
				}
				rec = jszyAppBiz.getRecByRecType(processFlow, funcFlow);
				if (rec != null) {
					dataFlow = rec.getRecFlow();
				}
			} else {
				rec = jszyAppBiz.getRecByRecFlow(dataFlow);
			}

			if (rec != null) {
				funcFlow = rec.getRecTypeId();
				oldContent=rec.getRecContent();
			}
		}
		if(isGrade){
			dataFlow = gradeBiz.editGradeInfo(dataFlow, operUserFlow, resultFlow, funcFlow, request,GlobalConstant.RES_JSZY_DEFAULT_FORM);
			if(dataFlow!=null&&dataFlow.startsWith("error:"))
			{
				model.addAttribute("resultId", "31801");
				model.addAttribute("resultType", dataFlow.split(":")[1]);
				return "res/jszy/saveData";
			}
		}else if(isAfter){
			//编辑这条rec
			dataFlow = expressBiz.editJszyExpress(dataFlow, operUserFlow, resultFlow, funcFlow,GlobalConstant.RES_JSZY_DEFAULT_FORM, request);
			if("Teacher".equals(roleId)){
				expressBiz.auditDate(userFlow,dataFlow);
			}
		}else {
			//编辑这条rec
			dataFlow = jszyAppBiz.editRec(dataFlow, operUserFlow, resultFlow, funcFlow, cataFlow, request,oldContent);
			if("Teacher".equals(roleId)){
				teacherBiz.auditDate(userFlow,dataFlow);
			}
		}

		return "res/jszy/saveData";
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
			return "res/jszy/delData";
		}

		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3011002");
			model.addAttribute("resultType", "用户角色为空");
			return "res/jszy/delData";
		}

		if("Student".equals(roleId)&&StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3011003");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/jszy/delData";
		}

		if("Teacher".equals(roleId)&&StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3011004");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jszy/delData";
		}

		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", "3011005");
			model.addAttribute("resultType", "功能类型为空");
			return "res/jszy/delData";
		}

		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3011006");
			model.addAttribute("resultType", "功能标识为空");
			return "res/jszy/delData";
		}

		if(StringUtil.isEmpty(dataFlow)){
			model.addAttribute("resultId", "3011007");
			model.addAttribute("resultType", "登记数据标识为空");
			return "res/jszy/delData";
		}

		//删除rec
		jszyAppBiz.delRec(dataFlow);

		return "res/jszy/delData";
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
			return "res/jszy/qrCode";
		}

		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", "3011103");
			model.addAttribute("resultType", "功能类型为空");
			return "res/jszy/qrCode";
		}

		if(StringUtil.isNotEquals("qrCode", funcTypeId)){
			model.addAttribute("resultId", "3011104");
			model.addAttribute("resultType", "功能类型错误");
			return "res/jszy/qrCode";
		}
//
//		if(StringUtil.isEmpty(funcFlow)){
//			model.addAttribute("resultId", "3011105");
//			model.addAttribute("resultType", "功能标识为空");
//			return "res/jszy/qrCode";
//		}
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
				return "res/jszy/qrCode";
			}
			if(StringUtil.isBlank(roleId))
			{
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "roleId为空！");
				return "res/jszy/qrCode";
			}
			if(!"Student".equals(roleId)&&!"Teacher".equals(roleId)&&!"Head".equals(roleId)&&!"Seretary".equals(roleId)) {
				model.addAttribute("resultId", "30906");
				model.addAttribute("resultType", "roleId标识符不正确");
				return "res/jszy/qrCode";
			}

			SysUser currUser = jszyAppBiz.readSysUser(userFlow);
			if(currUser==null){
				model.addAttribute("resultId", "30906");
				model.addAttribute("resultType", "用户不存在");
				return "res/jszy/qrCode";
			}
			ResDoctor doctor=null;
			if("Student".equals(roleId)) {
				doctor = jszyAppBiz.readResDoctor(currUser.getUserFlow());
				if(doctor==null)
				{
					model.addAttribute("resultId", "30906");
					model.addAttribute("resultType", "学员医师信息不存在");
					return "res/jszy/qrCode";
				}
			}
			ResLectureInfo info=jszyAppBiz.read(lectureFlow);
			if(info!=null)
			{
				String signTime = paramMap.get("time");
				String key="res_"+info.getOrgFlow()+"_org_jiangzuo_code_type";
				String cfgv=jszyAppBiz.getResCfgCode(key);
				if(GlobalConstant.FLAG_Y.equals(cfgv)) {
					if(StringUtil.isBlank(signTime))
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "无效二维码");
						return "res/hbres/qrCode";
					}
					//5秒钟有效
					String currTime = DateUtil.getCurrDateTime();
					if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(currTime), DateUtil.transDateTime(signTime)) > 5) {
						model.addAttribute("resultId", "3011106");
						model.addAttribute("resultType", "二维码已失效，请重新扫码！");
						return "res/hbres/qrCode";
					}
				}
				String key1=jszyAppBiz.getResCfgCode("res_"+info.getOrgFlow()+"_org_jiangzuo_start_time");
				int startTime=10;
				if(StringUtil.isNotBlank(key1))
				{
					startTime=Integer.valueOf(key1);
				}
				//扫码报名
				ResLectureScanRegist regist=jszyAppBiz.searchByUserFlowAndLectureFlow(userFlow,lectureFlow);
				if(regist!=null)
				{
					if(regist.getIsScan().equals(GlobalConstant.FLAG_Y))
					{
						model.addAttribute("resultId", "3011109");
						model.addAttribute("resultType", "已经扫过码了！");
						return "res/jszy/qrCode";
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
						return "res/jszy/qrCode";
					}
					if(nowDate.compareTo(endDate)>0)
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "不在扫码时间范围内，无法扫码！");
						return "res/jszy/qrCode";
					}
					if(nowDate.compareTo(endDate)>0)
					{
						model.addAttribute("resultId", "30111013");
						model.addAttribute("resultType", "讲座签到时间已结束，无法签到！");
						return "res/jszy/qrCode";
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
						return "res/jszy/qrCode";
					}
					if(nowDate.compareTo(endDate)>0)
					{
						model.addAttribute("resultId", "30111013");
						model.addAttribute("resultType", "讲座签到时间已结束，无法签到！");
						return "res/jszy/qrCode";
					}
					List<ResLectureInfo> infos=jszyAppBiz.checkJoinList(lectureFlow,userFlow);
					if(infos!=null&&infos.size()>0)
					{
						String msg="";
						for (ResLectureInfo resLectureInfo:infos)
						{
							msg+="【"+resLectureInfo.getLectureContent()+"】,";
						}
						model.addAttribute("resultId", "30111013");
						model.addAttribute("resultType", "当前讲座与"+msg+"时间段重叠，请取消重叠时间段讲座！");
						return "res/jszy/qrCode";
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
				int count =jszyAppBiz.scanRegist(regist);
				if(count!=1)
				{
					model.addAttribute("resultId", "3011110");
					model.addAttribute("resultType", "扫码失败，请稍后再试！");
					return "res/jszy/qrCode";
				}
				return "res/jszy/qrCode";
			}else{
				model.addAttribute("resultId", "3011108");
				model.addAttribute("resultType", "二维码已失效！讲座信息不存在！");

			}

			return "res/jszy/qrCode";
		}else if(StringUtil.isEquals(funcFlow, "lectureOutSignin")){
			String lectureFlow = paramMap.get("lectureFlow");
			if(StringUtil.isBlank(lectureFlow))
			{
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "讲座信息不存在！");
				return "res/jszy/qrCode";
			}
			if(StringUtil.isBlank(roleId))
			{
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "roleId为空！");
				return "res/jszy/qrCode";
			}
			if(!"Student".equals(roleId)&&!"Teacher".equals(roleId)&&!"Head".equals(roleId)&&!"Seretary".equals(roleId)) {
				model.addAttribute("resultId", "30906");
				model.addAttribute("resultType", "roleId标识符不正确");
				return "res/jszy/qrCode";
			}

			SysUser currUser = jszyAppBiz.readSysUser(userFlow);
			if(currUser==null){
				model.addAttribute("resultId", "30906");
				model.addAttribute("resultType", "用户不存在");
				return "res/jszy/qrCode";
			}
			ResDoctor doctor=null;
			if("Student".equals(roleId)) {
				doctor = jszyAppBiz.readResDoctor(currUser.getUserFlow());
				if(doctor==null)
				{
					model.addAttribute("resultId", "30906");
					model.addAttribute("resultType", "学员医师信息不存在");
					return "res/jszy/qrCode";
				}
			}
			ResLectureInfo info=jszyAppBiz.read(lectureFlow);
			if(info!=null)
			{
				String signTime = paramMap.get("time");
				String key="res_"+info.getOrgFlow()+"_org_jiangzuo_code_type";
				String cfgv=jszyAppBiz.getResCfgCode(key);
				if(GlobalConstant.FLAG_Y.equals(cfgv)) {
					if(StringUtil.isBlank(signTime))
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "无效二维码");
						return "res/hbres/qrCode";
					}
					//5秒钟有效
					String currTime = DateUtil.getCurrDateTime();
					if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(currTime), DateUtil.transDateTime(signTime)) > 5) {
						model.addAttribute("resultId", "3011106");
						model.addAttribute("resultType", "二维码已失效，请重新扫码！");
						return "res/hbres/qrCode";
					}
				}
				String key2=jszyAppBiz.getResCfgCode("res_"+info.getOrgFlow()+"_org_jiangzuo_end_time");
				int endTime=10;
				if(StringUtil.isNotBlank(key2))
				{
					endTime=Integer.valueOf(key2);
				}
				//扫码报名
				ResLectureScanRegist regist=jszyAppBiz.searchByUserFlowAndLectureFlow(userFlow,lectureFlow);
				if(regist!=null)
				{
					if(!"Y".equals(regist.getIsScan()))
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "你未签到参加该讲座，无法进行签退！");
						return "res/jszy/qrCode";
					}
					if(regist.getIsScan2().equals(GlobalConstant.FLAG_Y))
					{
						model.addAttribute("resultId", "3011109");
						model.addAttribute("resultType", "已经扫过码了！");
						return "res/jszy/qrCode";
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
						return "res/jszy/qrCode";
					}
					if( nowDate.compareTo(endDate)>0)
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "不在扫码时间范围内，无法扫码！");
						return "res/jszy/qrCode";
					}
					if(nowDate.compareTo(endDate)>0)
					{
						model.addAttribute("resultId", "30111013");
						model.addAttribute("resultType", "讲座已经结束！");
						return "res/jszy/qrCode";
					}
					regist.setIsScan2(GlobalConstant.FLAG_Y);
					regist.setScan2Time(DateUtil.getCurrDateTime());
				}else{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "你未参加该讲座，无法进行签退！");
					return "res/jszy/qrCode";
				}
				int count =jszyAppBiz.scanRegist(regist);
				if(count!=1)
				{
					model.addAttribute("resultId", "3011110");
					model.addAttribute("resultType", "扫码失败，请稍后再试！");
					return "res/jszy/qrCode";
				}
				return "res/jszy/qrCode";
			}else{
				model.addAttribute("resultId", "3011108");
				model.addAttribute("resultType", "二维码已失效！讲座信息不存在！");

			}
			return "res/jszy/qrCode";
		}
		else if(StringUtil.isEquals(funcFlow, "randomSignIn")){//讲座随机签到

			String scanTime = DateUtil.getCurrDateTime();
			String randomFlow=paramMap.get("randomFlow");
			if(StringUtil.isBlank(randomFlow)) {
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "无效二维码");
				return "res/jszy/qrCode";
			}
			ResLectureRandomSign sign=jszyAppBiz.readLectureRandomSign(randomFlow);
			if(sign==null)
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "随机签到信息不存在");
				return "res/jszy/qrCode";
			}
			ResLectureInfo lectureInfo=jszyAppBiz.read(sign.getLectureFlow());
			if(lectureInfo==null)
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "讲座信息不存在");
				return "res/jszy/qrCode";
			}
			String nowDate=DateUtil.getCurrDate();
			if(!nowDate.equals(lectureInfo.getLectureTrainDate()))
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "讲座日期不是当前日期");
				return "res/jszy/qrCode";
			}
			//学员信息
			SysUser docUser=jszyAppBiz.readSysUser(userFlow);
			if(docUser==null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "用户信息不存在");
				return "res/jszy/qrCode";
			}
			ResDoctor doctor=jszyAppBiz.readResDoctor(userFlow);
			if(doctor==null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "医师信息不存在");
				return "res/jszy/qrCode";
			}
			ResLectureScanRegist regist=jszyAppBiz.searchByUserFlowAndLectureFlow(userFlow,sign.getLectureFlow());
			if(regist==null||!"Y".equals(regist.getIsScan()))
			{
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "讲座未签到无法进行随机签到");
				return "res/jszy/qrCode";
			}
			ResLectureRandomScan scan=jszyAppBiz.readLectureRandomScan(userFlow,randomFlow);
			if(scan!=null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "此次随机签到，你已完成！");
				return "res/jszy/qrCode";
			}
			if("Y".equals(sign.getCodeStatusType()))
			{
				String signTime = paramMap.get("time");
				if (StringUtil.isBlank(signTime)) {
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "无效二维码");
					return "res/jszy/qrCode";
				}
				//5秒钟有效
				if (StringUtil.isBlank(scanTime)) {
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "缺少参数");
					return "res/jszy/qrCode";
				}
				if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(scanTime), DateUtil.transDateTime(signTime)) > 10) {
					model.addAttribute("resultId", "3011106");
					model.addAttribute("resultType", "二维码已失效，请重新扫码！");
					return "res/jszy/qrCode";
				}
			}
			String nowTime=DateUtil.transDateTime(scanTime,"yyyyMMddHHmmss","HH:mm:ss");
			if(nowTime.compareTo(sign.getCodeStartTime())<0||nowTime.compareTo(sign.getCodeEndTime())>0)
			{
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "扫码时间不在签到时间范围内！");
				return "res/jszy/qrCode";
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
			int c=jszyAppBiz.saveLectureRandomScan(scan);
			if(c==0){
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "随机签到失败！");
				return "res/jszy/qrCode";
			}
			return "res/jszy/qrCode";
		}else if(StringUtil.isEquals(funcFlow, "signin")){
			if(StringUtil.isEmpty(deptFlow)){
				model.addAttribute("resultId", "3011102");
				model.addAttribute("resultType", "科室标识符为空");
				return "res/jszy/qrCode";
			}
			String signTime = paramMap.get("signTime");
			String currTime = DateUtil.getCurrDateTime();
			//一分钟有效
			if(DateUtil.signSecondsBetweenTowDate(DateUtil.transDate(currTime),DateUtil.transDate(signTime))>60){
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "二维码已失效！");
				return "res/jszy/qrCode";
			}
			//签到
			String signinType = SigninTypeEnum.Day.getId();
			boolean result = jszyAppBiz.signin(userFlow,deptFlow,signinType);
			if(!result){
				model.addAttribute("resultId", "3011108");
				model.addAttribute("resultType", "您已完成本次签到！");
			}else{
				model.addAttribute("resultId", "3011109");
				model.addAttribute("resultType", "签到成功！");
			}
		}else if(StringUtil.isEquals(funcFlow, "activitySigin")) {//教学活动签到

			String activityFlow=paramMap.get("activityFlow");
			if(StringUtil.isBlank(activityFlow))
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "二维码错误");
				return "res/jszy/qrCode";
			}
			TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
			if(info==null||!GlobalConstant.RECORD_STATUS_Y.equals(info.getRecordStatus()))
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "活动信息不存在");
				return "res/jszy/qrCode";
			}

			String signTime = paramMap.get("time");
			String key="res_"+info.getOrgFlow()+"_org_activity_code_type";
			String cfgv=jszyAppBiz.getResCfgCode(key);
			if(GlobalConstant.FLAG_Y.equals(cfgv)) {
				if(StringUtil.isBlank(signTime))
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "无效二维码");
					return "res/hbres/qrCode";
				}
				//5秒钟有效
				String currTime = DateUtil.getCurrDateTime();
				if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(currTime), DateUtil.transDateTime(signTime)) > 5) {
					model.addAttribute("resultId", "3011106");
					model.addAttribute("resultType", "二维码已失效，请重新扫码！");
					return "res/hbres/qrCode";
				}
			}
			String key1=jszyAppBiz.getResCfgCode("res_"+info.getOrgFlow()+"_org_activity_start_time");
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
				return "res/jszy/qrCode";
			}
			if( DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm").compareTo(endDate)>0)
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "签到时间已过，无法签到");
				return "res/jszy/qrCode";
			}
			int count=activityBiz.checkJoin(activityFlow,userFlow);
			if(count>0)
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "该时间段，你已报名参加其他教学活动，无法参加");
				return "res/jszy/qrCode";
			}

			ResDoctor doctor=hbresAppBiz.readResDoctor(userFlow);
			if(doctor==null)
			{
				model.addAttribute("resultId", "30203");
				model.addAttribute("resultType", "医师信息不存在");
				return "res/jszy/qrCode";
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
				return "res/jszy/qrCode";
			}
			TeachingActivityResult result=activityBiz.readRegistInfo(activityFlow,userFlow);
			if(result!=null)
			{
				if(GlobalConstant.FLAG_Y.equals(result.getIsScan()))
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "你已扫码签到成功！");
					return "res/jszy/qrCode";
				}
				result.setIsScan(GlobalConstant.FLAG_Y);
				result.setScanTime(DateUtil.getCurrDateTime());

				int c=activityBiz.saveResult(result,userFlow);
				if(c==0)
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "签到失败！");
					return "res/jszy/qrCode";
				}
			}else{
				List<TeachingActivityInfo> infos=activityBiz.checkJoinList(activityFlow,userFlow);
				if(infos!=null&&infos.size()>0)
				{String msg="";
					for (TeachingActivityInfo activityInfo:infos)
					{
						msg+="【"+activityInfo.getActivityName()+"】,";
					}
					model.addAttribute("resultId", "30111013");
					model.addAttribute("resultType", "当前活动与"+msg+"时间段重叠，请取消重叠时间段的活动！");
					return "res/jszy/qrCode";
				}
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
					return "res/jszy/qrCode";
				}
			}
			return "res/jszy/qrCode";
		}else if(StringUtil.isEquals(funcFlow, "activityOutSigin")) {//教学活动签退

			String activityFlow=paramMap.get("activityFlow");
			if(StringUtil.isBlank(activityFlow))
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "二维码错误");
				return "res/jszy/qrCode";
			}
			TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
			if(info==null||!GlobalConstant.RECORD_STATUS_Y.equals(info.getRecordStatus()))
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "活动信息不存在");
				return "res/jszy/qrCode";
			}
			String signTime = paramMap.get("time");
			String key="res_"+info.getOrgFlow()+"_org_activity_code_type";
			String cfgv=jszyAppBiz.getResCfgCode(key);
			if(GlobalConstant.FLAG_Y.equals(cfgv)) {
				if(StringUtil.isBlank(signTime))
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "无效二维码");
					return "res/hbres/qrCode";
				}
				//5秒钟有效
				String currTime = DateUtil.getCurrDateTime();
				if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(currTime), DateUtil.transDateTime(signTime)) > 5) {
					model.addAttribute("resultId", "3011106");
					model.addAttribute("resultType", "二维码已失效，请重新扫码！");
					return "res/hbres/qrCode";
				}
			}
			String key2=jszyAppBiz.getResCfgCode("res_"+info.getOrgFlow()+"_org_activity_end_time");
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
				return "res/jszy/qrCode";
			}
			if(!"Y".equals(result.getIsScan()))
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "你未签到参加该活动，无法进行签退！");
				return "res/jszy/qrCode";
			}
			String nowDate=DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");
			if( nowDate.compareTo(startDate)<0)
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "活动签退暂未开始，无法签退！");
				return "res/jszy/qrCode";
			}
			if( nowDate.compareTo(endDate)>0)
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "活动签退已结束，无法签退！");
				return "res/jszy/qrCode";
			}
			if(GlobalConstant.FLAG_Y.equals(result.getIsScan2()))
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "你已扫码签退成功！");
				return "res/jszy/qrCode";
			}
			result.setIsEffective(GlobalConstant.FLAG_Y);
			result.setIsScan2(GlobalConstant.FLAG_Y);
			result.setScanTime2(DateUtil.getCurrDateTime());
			int c=activityBiz.saveResult(result,userFlow);
			if(c==0)
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "签退失败！");
				return "res/jszy/qrCode";
			}
			return "res/jszy/qrCode";
		}else if(StringUtil.isEquals(funcFlow, "EntryReport")) {//入科报到

			String signTime = paramMap.get("signTime");
			String teacherDeptFlows = paramMap.get("teacherDeptFlows");
			String token = paramMap.get("token");
			String headUserFlow = paramMap.get("headUserFlow");
			String currTime = DateUtil.getCurrDateTime();

			if(StringUtil.isEmpty(teacherDeptFlows)){
				model.addAttribute("resultId", "3011103");
				model.addAttribute("resultType", "二维码无效");
				return "res/jszy/qrCode";
			}
			if(StringUtil.isEmpty(funcTypeId)){
				model.addAttribute("resultId", "3011103");
				model.addAttribute("resultType", "功能类型为空");
				return "res/jszy/qrCode";
			}
			//一分钟有效
			if(DateUtil.signSecondsBetweenTowDate(DateUtil.transDate(currTime),DateUtil.transDate(signTime))>60){
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "二维码已失效！");
				return "res/jszy/qrCode";
			}
			//校验科室是否有轮转
			List<String> deptFlows= Arrays.asList(teacherDeptFlows.split(","));
			SchArrangeResult result=hbresAppBiz.getResultByDeptFlow(deptFlows,userFlow);
			if(result==null)
			{
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "你在此科室内没有轮转记录信息");
				return "res/jszy/qrCode";
			}
			SchEntryReport schEntryReport=hbresAppBiz.getSchEntryReport(result.getResultFlow());
			if(schEntryReport!=null)
			{
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "你已成功扫码报到！");
				return "res/jszy/qrCode";
			}
			schEntryReport=new SchEntryReport();
			schEntryReport.setDoctorFlow(userFlow);
			schEntryReport.setHeadUserFlow(headUserFlow);
			schEntryReport.setReportTime(DateUtil.getCurrDateTime2());
			schEntryReport.setReportDate(DateUtil.getCurrDate());
			schEntryReport.setRecordStatus("Y");
			schEntryReport.setCodeInfo(codeInfo);
			int c = hbresAppBiz.addEntryReport(schEntryReport,userFlow);
			if(c>0){
				model.addAttribute("resultId", "3011108");
				model.addAttribute("resultType", "你已成功扫码报到！");
			}else{
				model.addAttribute("resultId", "3011109");
				model.addAttribute("resultType", "扫码报到失败，请重试！");
			}
			return "res/jszy/qrCode";
		} else {
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "无效二维码");
			return "res/jszy/qrCode";
		}


		return "res/jszy/qrCode";
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
			return "res/jszy/schDeptList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "31602");
			model.addAttribute("resultType", "起始页为空");
			return "res/jszy/schDeptList";
		}

		if(pageSize==null){
			model.addAttribute("resultId", "31603");
			model.addAttribute("resultType", "页面大小为空");
			return "res/jszy/schDeptList";
		}

		//读取这个用户的医师信息
		ResDoctor doctor = jszyAppBiz.readResDoctor(userFlow);
		if(doctor==null){
			model.addAttribute("resultId", "31604");
			model.addAttribute("resultType", "读取医师信息失败!");
			return "res/jszy/schDeptList";
		}

		String orgFlow ="";
		if(StringUtil.isNotBlank(doctor.getSecondOrgFlow()))
		{
			orgFlow = doctor.getSecondOrgFlow();
		}else {
			orgFlow = doctor.getOrgFlow();
		}
		if(StringUtil.isEmpty(orgFlow)){
			model.addAttribute("resultId", "31605");
			model.addAttribute("resultType", "该医师暂无培训基地!");
			return "res/jszy/schDeptList";
		}

		PageHelper.startPage(pageIndex, pageSize);
		List<SchDeptRel> schDeptList = jszyAppBiz.searchRelByStandard(orgFlow,standardDeptId,searchStr);
		model.addAttribute("schDeptList",schDeptList);

		model.addAttribute("dataCount",PageHelper.total);//数据总量

		return "res/jszy/schDeptList";
	}

	@RequestMapping(value={"/teacherList"},method={RequestMethod.POST})
	public String teacherList(String schDeptFlow,String searchStr,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", "31701");
			model.addAttribute("resultType", "轮转科室标识符为空");
			return "res/jszy/teacherList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "31702");
			model.addAttribute("resultType", "起始页为空");
			return "res/jszy/teacherList";
		}

		if(pageSize==null){
			model.addAttribute("resultId", "31703");
			model.addAttribute("resultType", "页面大小为空");
			return "res/jszy/teacherList";
		}

		String roleFlow = jszyAppBiz.getCfgByCode("res_teacher_role_flow");
		if(StringUtil.isNotBlank(roleFlow)){
			PageHelper.startPage(pageIndex, pageSize);
			List<SysUser> userList =  jszyAppBiz.getUserListBySchDept(schDeptFlow,roleFlow,searchStr);
			model.addAttribute("userList", userList);
			model.addAttribute("dataCount",PageHelper.total);//数据总量
		}

		return "res/jszy/teacherList";
	}

	@RequestMapping(value={"/headList"},method={RequestMethod.POST})
	public String headList(String schDeptFlow,String searchStr,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", "31701");
			model.addAttribute("resultType", "轮转科室标识符为空");
			return "res/jszy/headList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "31702");
			model.addAttribute("resultType", "起始页为空");
			return "res/jszy/headList";
		}

		if(pageSize==null){
			model.addAttribute("resultId", "31703");
			model.addAttribute("resultType", "页面大小为空");
			return "res/jszy/headList";
		}

		String roleFlow = jszyAppBiz.getCfgByCode("res_head_role_flow");
		if(StringUtil.isNotBlank(roleFlow)){
			PageHelper.startPage(pageIndex, pageSize);
			List<SysUser> userList =  jszyAppBiz.getUserListBySchDept(schDeptFlow,roleFlow,searchStr);
			model.addAttribute("userList", userList);
			model.addAttribute("dataCount",PageHelper.total);//数据总量
		}

		return "res/jszy/headList";
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
			return "res/jszy/sysNotices/getSysNotice";
		}
		//验证用户是否存在
		SysUser userinfo = jszyAppBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jszy/sysNotices/getSysNotice";
		}

		if (pageIndex == null) {
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jszy/sysNotices/getSysNotice";
		}
		if (pageSize == null) {
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jszy/sysNotices/getSysNotice";
		}
		String orgFlow="";
		orgFlow=userinfo.getOrgFlow();
		if(StringUtil.isNotBlank(isDoctor))//学员
		{
			ResDoctor doctor=jszyAppBiz.readResDoctor(userFlow);
			if(doctor!=null){
				orgFlow=doctor.getOrgFlow();
			}
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
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/jszy/sysNotices/no-pic.png";
		model.addAttribute("hostUrl",hostUrl);
		//获取访问路径前缀
		String uploadBaseUrl = jszyAppBiz.getCfgByCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/jszy/sysNotices/getSysNotice";
	}

	@RequestMapping(value={"/sysNoticeDetail"})
	public String sysNoticeDetail(String userFlow,String infoFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jszy/sysNotices/sysNoticeDetail";
		}
		if(StringUtil.isEmpty(infoFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "住培动态标识符为空");
			return "res/jszy/sysNotices/sysNoticeDetail";
		}
		InxInfo info = inxInfoBiz.getByInfoFlow(infoFlow);
		if(info==null)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "住培动态不存在，请刷新列表页面");
			return "res/jszy/sysNotices/sysNoticeDetail";
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
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/jszy/sysNotices/showSysNotice.jsp?recordFlow="+infoFlow;
		String androidimgurl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/jszy/sysNotices/showSysNotice2.jsp?recordFlow="+infoFlow;
		model.addAttribute("iosDetailUrl",hostUrl);
		model.addAttribute("androidDetailUrl",androidimgurl);
		return "res/jszy/sysNotices/sysNoticeDetail";
	}
	@RequestMapping(value={"/showSysNotice"},method ={ RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String showSysNotice(String infoFlow,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map=new HashMap<>();
		InxInfo info = inxInfoBiz.getByInfoFlow(infoFlow);
		map.put("info",info);
		return JSON.toJSONString(map);
	}


	@RequestMapping(value={"/findActivityList"},method={RequestMethod.GET,RequestMethod.POST})
	public String findActivityList(String userFlow,String typeId,String isCurrent,String deptFlow,Integer pageIndex,
								   Integer pageSize,HttpServletRequest request,HttpServletResponse response,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jszy/findActivityList";
		}
		if(StringUtil.isEmpty(typeId)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "类型标识符为空");
			return "res/jszy/findActivityList";
		}
		if(!"isNew".equals(typeId)&&!"isEval".equals(typeId))
		{
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "类型标识符不正确,isNew，isEval");
			return "res/jszy/findActivityList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30202");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jszy/findActivityList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jszy/findActivityList";
		}
		ResDoctor doctor=jszyAppBiz.readResDoctor(userFlow);
		if(doctor==null)
		{
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "医师信息不存在");
			return "res/jszy/findActivityList";
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
		return "res/jszy/findActivityList";
	}
	@RequestMapping(value = "/joinActivity")
	public String joinActivity(Model model, String activityFlow, String userFlow) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jszy/success";
		}
		if (StringUtil.isEmpty(activityFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/jszy/success";
		}
		TeachingActivityInfo info = activityBiz.readActivityInfo(activityFlow);
		if (info == null || !GlobalConstant.FLAG_Y.equals(info.getRecordStatus())) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "活动信息不存在，请刷新列表页面！");
			return "res/jszy/success";
		}

		if (DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm").compareTo(info.getStartTime()) >0) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "活动已开始，无法报名！");
			return "res/jszy/success";
		}
		TeachingActivityResult result = activityBiz.readRegistInfo(activityFlow, userFlow);
		if (result != null&&GlobalConstant.FLAG_Y.equals(result.getIsRegiest())) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "你已报名，请勿重复报名！");
			return "res/jszy/success";
		}
		List<TeachingActivityInfo> infos=activityBiz.checkJoinList(activityFlow,userFlow);
		if(infos!=null&&infos.size()>0)
		{
			TeachingActivityInfo activityInfo=infos.get(0);
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "已报名同一时间【"+activityInfo.getActivityName()+"】，不能报名！");
			return "res/jszy/success";
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
			return "res/jszy/success";
		}
		return "res/jszy/success";

	}
	@RequestMapping(value="/cannelRegiest")
	public String cannelRegiest(Model model,String activityFlow,String userFlow){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jszy/success";
		}
		if (StringUtil.isEmpty(activityFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/jszy/success";
		}
		TeachingActivityInfo info = activityBiz.readActivityInfo(activityFlow);
		if (info == null || !GlobalConstant.FLAG_Y.equals(info.getRecordStatus())) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "活动信息不存在，请刷新列表页面！");
			return "res/jszy/success";
		}
		TeachingActivityResult result = activityBiz.readRegistInfo(activityFlow, userFlow);
		if (result == null) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "你未报名，无法取消报名信息！");
			return "res/jszy/success";
		}
		if (!GlobalConstant.FLAG_Y.equals(result.getIsRegiest()))
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "你已取消报名！");
			return "res/jszy/success";
		}
		if(GlobalConstant.FLAG_Y.equals(result.getIsScan()))
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "你已扫码签到，无法取消报名信息！");
			return "res/jszy/success";
		}
		result.setIsRegiest(GlobalConstant.FLAG_N);
		int c = activityBiz.saveResult(result, userFlow);
		if (c == 0) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "取消报名失败！");
			return "res/jszy/success";
		}
		return "res/jszy/success";

	}
	@RequestMapping(value = "/showDocEval")
	public String showDocEval(Model model, String resultFlow, String userFlow) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jszy/showDocEval";
		}
		if (StringUtil.isEmpty(resultFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "评价标识符为空");
			return "res/jszy/showDocEval";
		}
		//评价人员
		TeachingActivityResult result=activityBiz.readResult(resultFlow);
		model.addAttribute("result",result);
		if(result!=null)
		{
			//评价人员评分详情
			Map<String,Object> evalDetailMap=new HashMap<>();
			//评价的指标
			List<Map<String,Object>> targets=activityTargeBiz.readActivityTargetEvals(result.getActivityFlow());
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
			return "res/jszy/showDocEval";
		}
		return "res/jszy/showDocEval";

	}
	@RequestMapping(value = {"/saveEvalInfo"}, method = {RequestMethod.POST,RequestMethod.GET})
	public	 String saveEvalInfo(String evals, String resultFlow,String userFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws DocumentException {

		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jszy/success";
		}
		if (StringUtil.isEmpty(resultFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "请选择需要评价的活动");
			return "res/jszy/success";
		}
		TeachingActivityResult result=activityBiz.readResult(resultFlow);
		if(result==null)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "你未扫码参加该活动，无法评价");
			return "res/jszy/success";
		}
		if(result!=null&&result.getEvalScore()!=null)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "该活动已评价，请刷新页面！");
			return "res/jszy/success";
		}
		if(StringUtil.isBlank(evals))
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "请选择评分！");
			return "res/jszy/success";
		}
		List<TeachingActivityEval> activityEvals=null;
		try {
			activityEvals=JSON.parseArray(evals, TeachingActivityEval.class);
		}catch (Exception e){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "评分数据格式不正确！");
			return "res/jszy/success";
		}
		if(activityEvals==null||activityEvals.size()<=0)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "请选择评分！");
			return "res/jszy/success";
		}
		int count=activityBiz.saveEvalInfo(activityEvals, resultFlow,userFlow);
		if(count==0)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "评价失败！");
			return "res/jszy/success";
		}
		return "res/jszy/success";
	}

	/**
	 * 最新讲座查询
	 */
	@RequestMapping("/getNewLectures")
	public String getNewLectures(Model model,String userFlow,String roleId,Integer pageIndex,Integer pageSize) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jszy/getNewLectures";
		}
		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "roleId标识符为空");
			return "res/jszy/getNewLectures";
		}
		if(!"Student".equals(roleId)&&!"Teacher".equals(roleId)&&!"Head".equals(roleId)&&!"Seretary".equals(roleId)) {
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "roleId标识符不正确");
			return "res/jszy/getNewLectures";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jszy/getNewLectures";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jszy/getNewLectures";
		}
		SysUser currUser = jszyAppBiz.readSysUser(userFlow);
		if(currUser==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "用户不存在");
			return "res/jszy/getNewLectures";
		}
		String orgFlow = currUser.getOrgFlow();
		//获取当前配置的医师角色
		String doctorRole = jszyAppBiz.getCfgCode("res_doctor_role_flow");
		//获取当前配置的老师角色
		String teacherRole = jszyAppBiz.getCfgCode("res_teacher_role_flow");
		//获取当前配置的科主任角色
		String headRole = jszyAppBiz.getCfgCode("res_head_role_flow");
		//获取当前配置的科秘角色
		String secretaryRole = jszyAppBiz.getCfgCode("res_secretary_role_flow");
		String roleFlow="";
		if("Student".equals(roleId)) {
			ResDoctor doctor = jszyAppBiz.readResDoctor(currUser.getUserFlow());

			if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
				orgFlow = doctor.getSecondOrgFlow();
			} else {
				orgFlow = doctor.getOrgFlow();
			}
			roleFlow=doctorRole;
		}


		if("Teacher".equals(roleId)){
			roleFlow=teacherRole;
		}
		if("Head".equals(roleId)) {
			roleFlow=headRole;
		}
		if("Seretary".equals(roleId)) {
			roleFlow=secretaryRole;
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<ResLectureInfo> lectureInfos = jszyAppBiz.SearchNewLectures(orgFlow,roleId,roleFlow);
		model.addAttribute("lectureInfos",lectureInfos);
		model.addAttribute("dataCount", PageHelper.total);
		Map<String,ResLectureScanRegist> registMap = new HashMap<>();
		Map<String,Integer> registNumMap = new HashMap<>();
		if(lectureInfos!=null&&lectureInfos.size()>0){
			for(ResLectureInfo lectureInfo:lectureInfos){
				String lectureFlow = lectureInfo.getLectureFlow();
				ResLectureScanRegist lectureScanRegist = jszyAppBiz.searchByUserFlowAndLectureFlow(userFlow,lectureFlow);
				registMap.put(lectureFlow,lectureScanRegist);
				List<ResLectureScanRegist> resLectureScanRegists=jszyAppBiz.searchIsRegist(lectureFlow);
				if(lectureScanRegist!=null)
				{
					registNumMap.put(lectureFlow,resLectureScanRegists.size());
				}else{
					registNumMap.put(lectureFlow,0);
				}
			}
			model.addAttribute("registMap",registMap);
		}
		return "res/jszy/getNewLectures";
	}
	/**
	 * 历史讲座查询
	 */
	@RequestMapping("/getHistoryLectures")
	public String getHistoryLectures(Model model,String userFlow,Integer pageIndex,Integer pageSize) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jszy/getHistoryLectures";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jszy/getHistoryLectures";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jszy/getHistoryLectures";
		}
		PageHelper.startPage(pageIndex, pageSize);

		Map<String,ResLectureEvaDetail> evaDetailMap = new HashMap<>();
		Map<String,Integer> evaMap = new HashMap<>();
		Map<String,String> scanMap = new HashMap<>();
		Map<String,String> scan2Map = new HashMap<>();
		List<Map<String,String>> newList=jszyAppBiz.getHistoryLecture(userFlow);
		if(newList!=null&&!newList.isEmpty())
		{
			String currDateTime = DateUtil.getCurrDateTime();
			String currDate = currDateTime.substring(0,4)+"-"+currDateTime.substring(4,6)+"-"+currDateTime.substring(6,8);
			String currTime = currDateTime.substring(8,10)+":"+currDateTime.substring(10,12);
			for(Map<String,String> bean:newList){
				String isScan = bean.get("isCan");
				String isScan2 = bean.get("isCan2");
				String lectureFlow = bean.get("lectureFlow");
				String lectureEndTime = bean.get("lectureEndTime");
				String lectureTrainDate = bean.get("lectureTrainDate");
				//判断是否到评价期限
				String date = bean.get("lectureTrainDate");
				String time = bean.get("lectureEndTime");
				String unitID = bean.get("lectureUnitId");
				String period = bean.get("lectureEvaPeriod");
				String startDate = date.substring(0,4)+date.substring(5,7)+date.substring(8,10)+time.substring(0,2)+time.substring(3,5)+"00";
				int step = 0;
				if(SchUnitEnum.Hour.getId().equals(unitID)){
					step = Integer.parseInt(period);
				}
				if(SchUnitEnum.Day.getId().equals(unitID)){
					step = Integer.parseInt(period)*24;
				}
				if(SchUnitEnum.Week.getId().equals(unitID)){
					step = Integer.parseInt(period)*24*7;
				}
				if(SchUnitEnum.Month.getId().equals(unitID)){
					step = Integer.parseInt(period)*24*30;
				}
				if(SchUnitEnum.Year.getId().equals(unitID)){
					step = Integer.parseInt(period)*24*365;
				}
				String endDate = DateUtil.addHour(startDate,step);
				String currentDate = DateUtil.getCurrDateTime();
				int dateFlag = endDate.compareTo(currentDate);
				//判断结束
				if((lectureEndTime.compareTo(currTime)<0&&lectureTrainDate.compareTo(currDate)==0)||(lectureTrainDate.compareTo(currDate)<0)){
					if("Y".equals(isScan))
					{
						scanMap.put(lectureFlow,"Y");
					}
					if("Y".equals(isScan2)) {
						scan2Map.put(lectureFlow, "Y");
					}
					evaMap.put(lectureFlow,dateFlag);
				}
				List<ResLectureEvaDetail> lectureEvaDetails = jszyAppBiz.searchByUserFlowLectureFlow(userFlow,lectureFlow);
				if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0){
					ResLectureEvaDetail lectureEvaDetail = lectureEvaDetails.get(0);
					evaDetailMap.put(lectureFlow,lectureEvaDetail);
				}
			}
		}
		model.addAttribute("scanMap",scanMap);
		model.addAttribute("scan2Map",scan2Map);
		model.addAttribute("evaMap",evaMap);
		model.addAttribute("dataCount", PageHelper.total);
		model.addAttribute("evaDetailMap",evaDetailMap);
		model.addAttribute("lectureInfos",newList);
		return "res/jszy/getHistoryLectures";
	}
	/**
	 * 报名讲座
	 */
	@RequestMapping("/lectureRegist")
	public synchronized String lectureRegist(String lectureFlow,String roleId,String userFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "报名成功！");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jszy/lectureRegist";
		}
		if(StringUtil.isEmpty(lectureFlow)){
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "讲座流水号为空");
			return "res/jszy/lectureRegist";
		}
		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "roleId标识符为空");
			return "res/jszy/lectureRegist";
		}
		if(!"Student".equals(roleId)&&!"Teacher".equals(roleId)&&!"Head".equals(roleId)&&!"Seretary".equals(roleId)) {
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "roleId标识符不正确");
			return "res/jszy/lectureRegist";
		}
		SysUser currUser = jszyAppBiz.readSysUser(userFlow);
		if(currUser==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "用户不存在");
			return "res/jszy/lectureRegist";
		}
		String orgFlow = currUser.getOrgFlow();
		//获取当前配置的医师角色
		String doctorRole = jszyAppBiz.getCfgCode("res_doctor_role_flow");
		//获取当前配置的老师角色
		String teacherRole = jszyAppBiz.getCfgCode("res_teacher_role_flow");
		//获取当前配置的科主任角色
		String headRole = jszyAppBiz.getCfgCode("res_head_role_flow");
		String roleFlow="";
		ResDoctor doctor=null;
		if("Student".equals(roleId)) {
			doctor = jszyAppBiz.readResDoctor(currUser.getUserFlow());
			if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
				orgFlow = doctor.getSecondOrgFlow();
			} else {
				orgFlow = doctor.getOrgFlow();
			}
			roleFlow=doctorRole;
		}
		if("Teacher".equals(roleId)){
			roleFlow=teacherRole;
		}
		if("Head".equals(roleId)) {
			roleFlow=headRole;
		}
		ResLectureScanRegist regist=jszyAppBiz.searchByUserFlowAndLectureFlow(userFlow,lectureFlow);
		if(regist!=null&&StringUtil.isNotBlank(regist.getIsRegist())&&"Y".equals(regist.getIsRegist()))
		{
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "已经报过名了！！");
			return "res/jszy/lectureRegist";
		}

		List<ResLectureScanRegist> resLectureScanRegists=jszyAppBiz.searchIsRegist(lectureFlow);
		ResLectureInfo lectureInfo = jszyAppBiz.read(lectureFlow);
		if(StringUtil.isBlank(lectureInfo.getLimitNum())||resLectureScanRegists==null||resLectureScanRegists.size()<Integer.valueOf(lectureInfo.getLimitNum())) {

			List<ResLectureInfo> infos=jszyAppBiz.checkJoinList(lectureFlow,userFlow);
			if(infos!=null&&infos.size()>0)
			{
				ResLectureInfo resLectureInfo=infos.get(0);
				model.addAttribute("resultId", "30111013");
				model.addAttribute("resultType", "已报名同一时间【"+resLectureInfo.getLectureContent()+"】，不能报名！");
				return "res/jszy/lectureRegist";
			}
			int count = jszyAppBiz.editLectureScanRegist(lectureFlow, currUser, regist,doctor);
			if(count<0)
			{
				model.addAttribute("resultId", "32302");
				model.addAttribute("resultType", "该讲座报名人数已满，请刷新列表页面！");
				return "res/jszy/lectureRegist";
			}
			if (count == 1) {
				String lectureTrainDate = lectureInfo.getLectureTrainDate();
				String lectureStartTime = lectureInfo.getLectureStartTime();
				String year = lectureTrainDate.substring(0, 4);
				model.addAttribute("year", year);
				String month = lectureTrainDate.substring(5, 7);
				model.addAttribute("month", month);
				String day = lectureTrainDate.substring(8, 10);
				model.addAttribute("day", day);
				String hour = lectureStartTime.substring(0, 2);
				model.addAttribute("hour", hour);
				String min = lectureStartTime.substring(3, 5);
				model.addAttribute("min", min);
			} else {
				model.addAttribute("resultId", "32302");
				model.addAttribute("resultType", "报名失败！");
			}
		}else{
			model.addAttribute("resultId", "32302");
			model.addAttribute("resultType", "该讲座报名人数已满，请刷新列表页面！");
		}
		return "res/jszy/lectureRegist";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value={"/getZhupeiNotices"})
	public String getZhupeiNotices(String userFlow,String roleId,Integer pageIndex,Integer pageSize,String noticeTitle,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jszy/noticeList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30202");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jszy/noticeList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jszy/noticeList";
		}

		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色为空");
			return "res/jszy/noticeList";
		}
		SysUser currUser = jszyAppBiz.readSysUser(userFlow);
		String orgFlow = currUser.getOrgFlow();
		String doctorTypeId="";
		ResDoctor doctor =null;
		if("Student".equals(roleId)){
			doctor=jszyAppBiz.readResDoctor(userFlow);
			if(doctor==null)
			{
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "医师信息不存在");
				return "res/jszy/noticeList";
			}
			if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
				orgFlow = doctor.getSecondOrgFlow();
			} else {
				orgFlow = doctor.getOrgFlow();
			}
			doctorTypeId=doctor.getDoctorTypeId();
		}
		if(pageIndex==null){
			pageIndex=1;
		}
		PageHelper.startPage(pageIndex,pageSize);
		List<ResTarinNotice> tarinNotices =null;

		if(StringUtil.isBlank(orgFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户基地信息不存在");
			return "res/jszy/noticeList";
		}
			tarinNotices =resLiveTrainingBiz.searchByTitleOrgFlow(noticeTitle, orgFlow);
		//获取访问路径前缀
		String uploadBaseUrl = jszyAppBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl",uploadBaseUrl);

		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/jszy/showNotice/no-pic.png";
		model.addAttribute("hostUrl",hostUrl);
		model.addAttribute("tarinNotices",tarinNotices);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/jszy/noticeList";
	}
	@RequestMapping(value={"/zpNoticeDetail"})
	public String zpNoticeDetail(String userFlow,String recordFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jszy/zpNoticeDetail";
		}
		if(StringUtil.isEmpty(recordFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "住培指南标识符为空");
			return "res/jszy/zpNoticeDetail";
		}
		ResTarinNotice tarinNotices = resLiveTrainingBiz.readNotice(recordFlow);
		if(tarinNotices==null)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "住培指南标不存在，请刷新列表页面");
			return "res/jszy/zpNoticeDetail";
		}
		model.addAttribute("title",tarinNotices.getResNoticeTitle());
		model.addAttribute("content",tarinNotices.getResNoticeContent());
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/jszy/showNotice/showNoticeDetail.jsp?recordFlow="+recordFlow;
		String androidimgurl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/jszy/showNotice/showNoticeDetail2.jsp?recordFlow="+recordFlow;
		model.addAttribute("iosDetailUrl",hostUrl);
		model.addAttribute("androidDetailUrl",androidimgurl);
		return "res/jszy/zpNoticeDetail";
	}
	/**
	 * 查看错题
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/viewError"},method={RequestMethod.GET})
	public String viewError(String processFlow,Model model){
		String result = "res/jszy/viewError";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "轮转科室标识符为空");
			return result;
		}
		List<ExamResults> results=examResultsBiz.getByProcessFlow(processFlow);
		model.addAttribute("results",results);
		return "res/jszy/viewError";
	}
	@RequestMapping(value={"/viewErrorDetail"},method={RequestMethod.GET})
	public String viewErrorDetail(String processFlow,String resultsId , Model model){
		String result = "res/jszy/viewErrorDetail";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "轮转科室标识符为空");
			return result;
		}

		if(!StringUtil.isNotBlank(resultsId)){
			model.addAttribute("resultId", "30402");
			model.addAttribute("resultType", "当前考试试卷ID为空");
			return result;
		}

		//错题查看地址
		String testUrl = jszyAppBiz.getCfgCode("res_after_wrong_exam_url_cfg");
		if(!StringUtil.isNotBlank(testUrl)){
			model.addAttribute("resultId", "30403");
			model.addAttribute("resultType", "请联系管理员维护错题查看地址");
			return result;
		}
		ExamResults results=examResultsBiz.getByFlow(resultsId);

		if(results==null) {
			model.addAttribute("resultId", "30403");
			model.addAttribute("resultType", "当前考试记录信息获取失败");
			return result;
		}

		testUrl=testUrl+"?RequestType=pc&ProcessFlow="+processFlow+"&SoluID="+results.getSoluId()+"&ResultID="+resultsId;
		model.addAttribute("testUrl",testUrl);
		return result;
	}
	@RequestMapping(value={"/joinExam"},method={RequestMethod.POST})
	public String joinExam(String userFlow,String schDeptFlow,String resultFlow,String processFlow,HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "40401");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jszy/joinExam";
		}
		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", "40402");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/jszy/joinExam";
		}

		if(StringUtil.isEmpty(resultFlow)){
			model.addAttribute("resultId", "40403");
			model.addAttribute("resultType", "轮转计划标识符为空");
			return "res/jszy/joinExam";
		}
		if(StringUtil.isEmpty(processFlow)){
			model.addAttribute("resultId", "40404");
			model.addAttribute("resultType", "请重新维护轮转信息");
			return "res/jszy/joinExam";
		}
		//考试地址
		String testUrl = jszyAppBiz.getCfgCode("res_mobile_after_url_cfg");
		if(!StringUtil.isNotBlank(testUrl)){
			model.addAttribute("resultId", "40405");
			model.addAttribute("resultType", "请联系管理员维护考试地址!");
			return "res/jszy/joinExam";
		}
		SysUser user=jszyAppBiz.readSysUser(userFlow);

		SchAndStandardDeptCfg cfg =deptCfgBiz.readBySchDeptFlow(schDeptFlow);
		if(cfg==null)
		{
			model.addAttribute("resultId", "40406");
			model.addAttribute("resultType", "请基地管理员维护出科考核标准科室!");
			return "res/jszy/joinExam";
		}
		ResDoctorSchProcess process=jszyAppBiz.getProcessByResultFlow(resultFlow);
		if(process==null)
		{
			model.addAttribute("resultId", "40404");
			model.addAttribute("resultType", "当前轮转信息获取失败!");
			return "res/jszy/joinExam";
		}
		String TestNum="";
		ResPowerCfg powerCfg = resPowerCfgBiz.read("out_test_limit_" + process.getOrgFlow());
		if(powerCfg!=null&&StringUtil.isNotBlank(powerCfg.getCfgValue())) {
			TestNum=powerCfg.getCfgValue();
			int c=0;
			List<ExamResults> examResultsList = examResultsBiz.getByProcessFlow(process.getProcessFlow());
			if (examResultsList != null)
				c=examResultsList.size();
			if(c>=Integer.valueOf(powerCfg.getCfgValue()))
			{
				model.addAttribute("resultId", "40404");
				model.addAttribute("resultType", "当前考试次数已经达到"+powerCfg.getCfgValue()+",无法再次考试!");
				return "res/jszy/joinExam";
			}
		}
		//试卷id
		String ExamSoluID = "0";
		//时间戳
		String Date = "0";
		//标准科室
		String standardDeptId = cfg.getStandardDeptId();
		ResPaper paper = paperBiz.getPaperByOrgStandardDeptId(process.getOrgName(), standardDeptId);
		if(paper==null){
			paper = paperBiz.getPaperByStandardDeptId(standardDeptId);
		}
		if (paper != null) {
			ExamSoluID = paper.getPaperFlow();
		}
		if ("0".equals(ExamSoluID)) {
			model.addAttribute("resultId", "40407");
			model.addAttribute("resultType", "该科室暂无试卷信息!");
			return "res/jszy/joinExam";
		}
		SchArrangeResult result=teacherBiz.readSchArrangeResult(resultFlow);
		//创建分数数据
		ResScore score = teacherBiz.readScoreByProcessFlow(processFlow);
		if(score==null){
			score = new ResScore();
			score.setDoctorFlow(userFlow);
			score.setScoreTypeId(ResScoreTypeEnum.DeptScore.getId());
			score.setScoreTypeName(ResScoreTypeEnum.DeptScore.getName());
			score.setResultFlow(resultFlow);
			score.setProcessFlow(processFlow);
			score.setSchDeptFlow(result.getSchDeptFlow());
			score.setSchDeptName(result.getSchDeptName());
		}

		score.setPaperFlow(ExamSoluID);

		int saveResult = jszyAppBiz.saveScore(score,user);
		if(GlobalConstant.ZERO_LINE>=saveResult){
			model.addAttribute("resultId", "40408");
			model.addAttribute("resultType", "分数信息创建出错!");
			return "res/jszy/joinExam";
		}
		testUrl=testUrl+"?Action=ChuKeMobileExam&ExamSoluID="+ExamSoluID+"&CardID="+ URLEncoder.encode(user.getUserCode(), "utf-8")+"&ProcessFlow="+processFlow+"&TestNum="+TestNum+"&Date="+Date;
		model.addAttribute("testUrl",testUrl);
		return "res/jszy/joinExam";
	}

	@RequestMapping(value={"/toGraduationTest"},method={RequestMethod.POST})
	public String toGraduationTest(String userFlow,HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "40401");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jszy/toGraduationTest";
		}
		SysUser user=jszyAppBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "40401");
			model.addAttribute("resultType", "用户不存在");
			return "res/jszy/toGraduationTest";
		}
		//权限
		String cfgv = jszyAppBiz.getResCfgCode("res_doctor_graduation_exam_"+userFlow);
		if(!"Y".equals(cfgv)){
			model.addAttribute("resultId", "40405");
			model.addAttribute("resultType", "无权限参加考试!");
			return "res/jszy/toGraduationTest";
		}
		//考试地址
		String testUrl = jszyAppBiz.getCfgCode("res_mobile_after_url_cfg");
		if(!StringUtil.isNotBlank(testUrl)){
			model.addAttribute("resultId", "40405");
			model.addAttribute("resultType", "请联系管理员维护考试地址!");
			return "res/jszy/toGraduationTest";
		}
		String userCode = user.getUserCode();
		//用户的医师信息
		ResDoctor doctor = jszyAppBiz.readResDoctor(userFlow);
		if(doctor==null)
		{
			model.addAttribute("resultId", "40405");
			model.addAttribute("resultType", "无医师信息，无法参加考试!");
			return "res/jszy/toGraduationTest";
		}
		if(StringUtil.isBlank(doctor.getTrainingSpeId()))
		{
			model.addAttribute("resultId", "40405");
			model.addAttribute("resultType", "医师无培训专业信息，无法参加考试!");
			return "res/jszy/toGraduationTest";
		}
		//获取考试参数
		//考试结果记录流水号
		String recordFlow= PkUtil.getUUID();

		//试卷id
		String ExamSoluID = "0";
		//试卷类型
		String CardType = "0";
		//时间戳
		String Date = "0";

		if(doctor!=null){
			//专业
			String speId = doctor.getTrainingSpeId();
			ResPaper paper =  paperBiz.getPaperBySpeId(speId);

			if(paper!=null){
				ExamSoluID = paper.getPaperFlow();
			}
			if("0".equals(ExamSoluID)){
				model.addAttribute("resultId", "40405");
				model.addAttribute("resultType", "该专业下暂无试卷信息!");
				return "res/jszy/toGraduationTest";
			}

			TestPaper standardPaper = paperBiz.readTestPaper(ExamSoluID);
			if(standardPaper!=null){
				CardType = standardPaper.getPaperTypeId();
			}
		}//创建考核结果
		ResDoctorGraduationExam result=new ResDoctorGraduationExam();
		result.setExamFlow(recordFlow);
		result.setDoctorFlow(userFlow);
		result.setExamYear(DateUtil.getYear());
		int saveResult = examCfgBiz.saveGraduationExam(result);
		if(GlobalConstant.ZERO_LINE>=saveResult){
			model.addAttribute("resultId", "40405");
			model.addAttribute("resultType", "考核结果信息创建出错!");
			return "res/jszy/toGraduationTest";
		}
		testUrl=testUrl+"?Action=ChuKeMobileExam&ExamSoluID="+ExamSoluID+"&CardID="+URLEncoder.encode(user.getUserCode(), "utf-8")+"&ProcessFlow="+recordFlow+"&TestNum="+"&Date="+Date;
		model.addAttribute("testUrl",testUrl);
		return "res/jszy/toGraduationTest";
	}


	@RequestMapping(value={"/allAfterDept"},method={RequestMethod.POST})
	public String allAfterDept(String userFlow,Integer pageIndex,Integer pageSize,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jszy/allAfterDept";
		}
		//考试地址
		String testUrl = jszyAppBiz.getCfgCode("res_mobile_after_url_cfg");
		if(!StringUtil.isNotBlank(testUrl)){
			model.addAttribute("resultId", "30403");
			model.addAttribute("resultType", "请联系管理员维护考试地址!");
			return "res/jszy/allAfterDept";
		}
		ResDoctor doctor=jszyAppBiz.readResDoctor(userFlow);
		List<SchArrangeResult> resultList = jszyAppBiz.getSchArrangeResult(userFlow, doctor.getOrgFlow(),pageIndex, pageSize);
		model.addAttribute("resultList", resultList);
		model.addAttribute("dataCount", PageHelper.total);
		Map<String, ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
		Map<String, ResScore> scoreMap = new HashMap<String, ResScore>();

		Map<String,String> countMap=new HashMap<>();
		List<String> orgFlows=new ArrayList<>();
		for(SchArrangeResult result:resultList) {
			ResDoctorSchProcess process=jszyAppBiz.getProcessByResultFlow(result.getResultFlow());
			if(process!=null)
			{
				processMap.put(result.getResultFlow(), process);
				ResScore score = teacherBiz.readScoreByProcessFlow(process.getProcessFlow());
				scoreMap.put(result.getResultFlow(), score);
				List<ExamResults> examResultsList=examResultsBiz.getByProcessFlow(process.getProcessFlow());
				if(examResultsList!=null)
					countMap.put(process.getProcessFlow(), examResultsList.size()+"");
			}

			if(!orgFlows.contains(result.getOrgFlow())) {
				ResPowerCfg powerCfg = resPowerCfgBiz.read("out_test_limit_" + result.getOrgFlow());
				if (powerCfg != null) {
					countMap.put((String) result.getOrgFlow(), powerCfg.getCfgValue());
				}
				orgFlows.add((String) result.getOrgFlow());
			}

		}
		model.addAttribute("countMap", countMap);
		String currDate = DateUtil.getCurrDate();
		model.addAttribute("currDate", currDate);
		model.addAttribute("processMap", processMap);
		model.addAttribute("scoreMap", scoreMap);
		return "res/jszy/allAfterDept";
	}
	/**
	 * 取消报名讲座
	 */
	@RequestMapping("/lectureCannelRegist")
	public synchronized String lectureCannelRegist(String lectureFlow,String roleId,String userFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "取消成功！");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jszy/success";
		}
		if(StringUtil.isEmpty(lectureFlow)){
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "讲座流水号为空");
			return "res/jszy/success";
		}
		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "roleId标识符为空");
			return "res/jszy/success";
		}
		if(!"Student".equals(roleId)&&!"Teacher".equals(roleId)&&!"Head".equals(roleId)&&!"Seretary".equals(roleId)) {
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "roleId标识符不正确");
			return "res/jszy/success";
		}
		SysUser currUser = jszyAppBiz.readSysUser(userFlow);
		if(currUser==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "用户不存在");
			return "res/jszy/success";
		}
		String orgFlow = currUser.getOrgFlow();
		//获取当前配置的医师角色
		String doctorRole = jszyAppBiz.getCfgCode("res_doctor_role_flow");
		//获取当前配置的老师角色
		String teacherRole = jszyAppBiz.getCfgCode("res_teacher_role_flow");
		//获取当前配置的科主任角色
		String headRole = jszyAppBiz.getCfgCode("res_head_role_flow");
		String roleFlow="";
		ResDoctor doctor=null;
		if("Student".equals(roleId)) {
			doctor = jszyAppBiz.readResDoctor(currUser.getUserFlow());
			orgFlow = doctor.getOrgFlow();
			roleFlow=doctorRole;
		}
		if("Teacher".equals(roleId)){
			roleFlow=teacherRole;
		}
		if("Head".equals(roleId)) {
			roleFlow=headRole;
		}
		ResLectureScanRegist regist=jszyAppBiz.searchByUserFlowAndLectureFlow(userFlow,lectureFlow);
		if(regist==null)
		{
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "你未报名，无法取消报名信息！");
			return "res/jszy/success";
		}
		if(StringUtil.isBlank(regist.getIsRegist()))
		{
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "你未报名，无法取消报名信息");
			return "res/jszy/success";
		}
		if (!GlobalConstant.FLAG_Y.equals(regist.getIsRegist()))
		{
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "你已取消报名！");
			return "res/jszy/success";
		}
		if(GlobalConstant.FLAG_Y.equals(regist.getIsScan()))
		{
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "你已扫码签到，无法取消报名信息！");
			return "res/jszy/success";
		}
		regist.setIsRegist(GlobalConstant.FLAG_N);
		int c=jszyAppBiz.scanRegist(regist);
		if(c==0)
		{
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "取消失败！");
			return "res/jszy/success";
		}
		return "res/jszy/success";
	}
}

