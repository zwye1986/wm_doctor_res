package com.pinde.res.ctrl.ezhupei;


import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.app.common.PasswordUtil;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.common.IResPowerCfgBiz;
import com.pinde.res.biz.ezhupei.IEzhupeiAppBiz;
import com.pinde.res.biz.ezhupei.IEzhupeiStudentBiz;
import com.pinde.res.biz.ezhupei.IEzhupeiTeacherBiz;
import com.pinde.res.biz.stdp.IResActivityBiz;
import com.pinde.res.biz.stdp.IResGradeBiz;
import com.pinde.res.biz.stdp.IResSchProcessExpressBiz;
import com.pinde.res.enums.ezhupei.ResAssessTypeEnum;
import com.pinde.res.enums.ezhupei.ResRecTypeEnum;
import com.pinde.res.enums.stdp.ResultEnum;
import com.pinde.res.enums.stdp.UserStatusEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.util.PasswordHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/res/ezhupei")
public class EzhupeiAppController{
	private static Logger logger = LoggerFactory.getLogger(EzhupeiAppController.class);

	@Autowired
	private IEzhupeiAppBiz appBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private IResGradeBiz gradeBiz;
	@Autowired
	private IEzhupeiStudentBiz ezhupeiStudentBiz;
	@Autowired
	private IEzhupeiTeacherBiz teacherBiz;
	@Autowired
	private IResPowerCfgBiz resPowerCfgBiz;
	@Autowired
	private IResActivityBiz activityBiz;

	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/ezhupei/500";
    }

	@RequestMapping(value={"/version"},method={RequestMethod.GET})
	public String version(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "res/ezhupei/version";
	}


	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(){
		return "res/ezhupei/test";
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
		return "/res/ezhupei/test";
	}

	@RequestMapping(value={"/login"},method={RequestMethod.POST})
	public String login(String userCode,String userPasswd,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(userCode)){
			model.addAttribute("resultId", "3010101");
			model.addAttribute("resultType", "用户名为空");
			return "res/ezhupei/login";
		}

		if(StringUtil.isEmpty(userPasswd)){
			model.addAttribute("resultId", "3010102");
			model.addAttribute("resultType", "密码为空");
			return "res/ezhupei/login";
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
				return "res/ezhupei/login";
			}

			//验证用户是否锁定,锁定用户不能登录
			String userStatus = userinfo.getStatusId();
			if(UserStatusEnum.Locked.getId().equals(userStatus)){
				model.addAttribute("resultId", "3010105");
				model.addAttribute("resultType", "该用户已被锁定");
				return "res/ezhupei/login";
			}

			//验证用户是否有角色
			List<SysUserRole> userRoles = appBiz.getSysUserRole(userFlow);
			if(userRoles==null || userRoles.isEmpty()){
				model.addAttribute("resultId", "3010106");
				model.addAttribute("resultType", "用户未赋权");
				return "res/ezhupei/login";
			}

			boolean isDoctor = false;
			boolean isTeacher = false;
			boolean isHeader = false;

			//获取当前配置的医师角色
			String doctorRole = appBiz.getCfgByCode("res_doctor_role_flow");

			//获取当前配置的老师角色
			String teacherRole = appBiz.getCfgByCode("res_teacher_role_flow");

			//获取当前配置的科主任角色
			String headRole = appBiz.getCfgCode("res_head_role_flow");

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
				}else if(headRole.equals(ur)){
					isHeader = true;
					model.addAttribute("roleId","Head");
					model.addAttribute("roleName","科主任");
				}
			}

			//如果有两个角色 带教与主任 则只要判断主任角色权限
			if(isHeader)
			{
				isTeacher=false;
				model.addAttribute("roleId","Head");
				model.addAttribute("roleName","科主任");
			}
			model.addAttribute("isDoctor", isDoctor);
			model.addAttribute("isTeacher", isTeacher);
			model.addAttribute("isHeader", isHeader);

			//如果是医师需要获取医师的一些培训信息
			if(isDoctor){
				//读取是否开启自主增加轮转计划开关 res_custom_result_flag
				String isManualFlag = appBiz.getCfgByCode("res_custom_result_flag");
				if(!GlobalConstant.FLAG_Y.equals(isManualFlag)){
					isManualFlag = GlobalConstant.FLAG_N;
				}
				//读取是否允许学员自己入科开关 res_custom_result_flag
				String isInBySelfFlag = appBiz.getCfgByCode("res_doc_in_by_self");
				if(!GlobalConstant.FLAG_Y.equals(isInBySelfFlag)){
					isInBySelfFlag = GlobalConstant.FLAG_N;
				}
				model.addAttribute("isInBySelfFlag",isInBySelfFlag);
				model.addAttribute("manualRotationFlag",isManualFlag);

				//读取这个用户的医师信息
				ResDoctor doctor = appBiz.readResDoctor(userFlow);

				if(doctor==null){
					model.addAttribute("resultId", "3010107");
					model.addAttribute("resultType", "登录失败,学员数据出错!");
					return "res/ezhupei/login";
				}else{
					//验证该医师是否存在培训机构,如果存在则验证该机构是否开启过程
					String orgFlow = doctor.getOrgFlow();
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
							return "res/ezhupei/login";
						}
					}else{
						model.addAttribute("resultId", "3010109");
						model.addAttribute("resultType", "登录失败,该学员暂无培训机构!");
						return "res/ezhupei/login";
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
					if(GlobalConstant.FLAG_Y.equals(switchFlag) && GlobalConstant.FLAG_Y.equals(isCkksFlag)
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
						return "res/ezhupei/login";
					}

				}else{
					model.addAttribute("resultId", "3010109");
					model.addAttribute("resultType", "登录失败,该带教暂无所属机构!");
					return "res/jszy/login";
				}
			}else if(isHeader){
				//验证该科主任是否存在培训机构,如果存在则验证该机构是否开启过程
				String orgFlow = userinfo.getOrgFlow();
				if(StringUtil.isNotBlank(orgFlow)){
					String isAppFlag = appBiz.getCfgByCode("jswjw_"+orgFlow+"_P001");
					if(!GlobalConstant.FLAG_Y.equals(isAppFlag)){
						model.addAttribute("resultId", "3010110");
						model.addAttribute("resultType", "登录失败,你暂无权限使用,请联系培训基地管理员!");
						return "res/ezhupei/login";
					}

				}else{
					model.addAttribute("resultId", "3010109");
					model.addAttribute("resultType", "登录失败,该带教暂无所属机构!");
					return "res/jszy/login";
				}
			}
			model.addAttribute("userinfo", userinfo);
		}
		return "res/ezhupei/login";
	}

	@RequestMapping(value={"/notice"},method={RequestMethod.GET})
	public String notice(String userFlow,String roleId,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		String result = "res/ezhupei/notice";
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
		String result = "res/ezhupei/noticeCount";
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
			return "res/ezhupei/viewData";
		}

		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3010602");
			model.addAttribute("resultType", "用户角色为空");
			return "res/ezhupei/viewData";
		}

		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", "3010603");
			model.addAttribute("resultType", "功能类型为空");
			return "res/ezhupei/viewData";
		}

		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3010604");
			model.addAttribute("resultType", "功能标识为空");
			return "res/ezhupei/viewData";
		}

		if("Student".equals(roleId)&&StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3010605");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/ezhupei/viewData";
		}

		if("Teacher".equals(roleId)&&StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3010606");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/ezhupei/viewData";
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
		SchArrangeResult result = ezhupeiStudentBiz.searcheDocResult(null,resultFlow);
		if(result!=null){
			model.addAttribute("result",result);

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
				ResDoctorSchProcess process = ezhupeiStudentBiz.getProcessByResult(resultFlow);
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
			//获取当前这条登记信息
			ResSchProcessExpress rec = null;
			if(StringUtil.isNotBlank(dataFlow)){
				rec = expressBiz.getExpressByRecFlow(dataFlow);
				//如果是11模式根据类型和科室查询
			}else if("dataInput11".equals(funcTypeId)){
				String processFlow = "";
				ResDoctorSchProcess process = ezhupeiStudentBiz.getProcessByResult(resultFlow);
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
			}
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
				ResDoctorSchProcess process = ezhupeiStudentBiz.getProcessByResult(resultFlow);
				if(process!=null){
					//登记表单的科室
					processFlow = process.getProcessFlow();
				}
				rec = appBiz.getRecByRecType(processFlow,funcFlow);
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
		return "res/ezhupei/viewData";
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
			return "res/ezhupei/dataList";
		}

		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3010702");
			model.addAttribute("resultType", "用户角色为空");
			return "res/ezhupei/dataList";
		}

		if("Student".equals(roleId)&&StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3010703");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/ezhupei/dataList";
		}

		if("Teacher".equals(roleId)&&StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3010704");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/ezhupei/dataList";
		}

		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", "3010705");
			model.addAttribute("resultType", "功能类型为空");
			return "res/ezhupei/dataList";
		}

		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3010706");
			model.addAttribute("resultType", "功能标识为空");
			return "res/ezhupei/dataList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "3010707");
			model.addAttribute("resultType", "起始页为空");
			return "res/ezhupei/dataList";
		}

		if(pageSize==null){
			model.addAttribute("resultId", "3010708");
			model.addAttribute("resultType", "页面大小为空");
			return "res/ezhupei/dataList";
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
		ResDoctorSchProcess process = ezhupeiStudentBiz.getProcessByResult(resultFlow);
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
		Map<String,Object> perMap = ezhupeiStudentBiz.getRegPer(0, userFlow, resultFlow,funcFlow,cataFlow,true);
		model.addAttribute("perMap",perMap);

		model.addAttribute("dataCount",PageHelper.total);

		return "res/ezhupei/dataList";
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
			return "res/ezhupei/cataList";
		}
		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3010802");
			model.addAttribute("resultType", "用户角色为空");
			return "res/ezhupei/cataList";
		}
		if("Student".equals(roleId)&&StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3010803");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/ezhupei/cataList";
		}
		if("Teacher".equals(roleId)&&StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3010804");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/ezhupei/cataList";
		}
		if(StringUtil.isNotEquals(funcTypeId, "dataInputNN")){
			model.addAttribute("resultId", "3010805");
			model.addAttribute("resultType", "功能类型不匹配");
			return "res/ezhupei/cataList";
		}
		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3010806");
			model.addAttribute("resultType", "功能标识为空");
			return "res/ezhupei/cataList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "3010807");
			model.addAttribute("resultType", "起始页为空");
			return "res/ezhupei/cataList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3010808");
			model.addAttribute("resultType", "页面大小为空");
			return "res/ezhupei/cataList";
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
		SchArrangeResult result = ezhupeiStudentBiz.searcheDocResult(null,resultFlow);
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
		Map<String,Object> perMap = ezhupeiStudentBiz.getRegPer(1, userFlow, resultFlow,funcFlow);
		model.addAttribute("perMap",perMap);

		String recTypeName = ResRecTypeEnum.getNameById(funcFlow);
		model.addAttribute("recTypeName",recTypeName);

		return "res/ezhupei/cataList";
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
			return "res/ezhupei/saveData";
		}

		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3010902");
			model.addAttribute("resultType", "用户角色为空");
			return "res/ezhupei/saveData";
		}

		if("Student".equals(roleId)&&StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3010903");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/ezhupei/saveData";
		}

		if("Teacher".equals(roleId)&&StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3010904");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/ezhupei/saveData";
		}

		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", "3010905");
			model.addAttribute("resultType", "功能类型为空");
			return "res/ezhupei/saveData";
		}

		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3010906");
			model.addAttribute("resultType", "功能标识为空");
			return "res/ezhupei/saveData";
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
				ResDoctorSchProcess process = ezhupeiStudentBiz.getProcessByResult(resultFlow);
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
				ResDoctorSchProcess process = ezhupeiStudentBiz.getProcessByResult(resultFlow);
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
				ResDoctorSchProcess process = ezhupeiStudentBiz.getProcessByResult(resultFlow);
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
			dataFlow = gradeBiz.editGradeInfo(dataFlow, operUserFlow, resultFlow, funcFlow, request,GlobalConstant.RES_EZHUPEI_DEFAULT_FORM);
			if(dataFlow!=null&&dataFlow.startsWith("error:"))
			{
				model.addAttribute("resultId", "31801");
				model.addAttribute("resultType", dataFlow.split(":")[1]);
				return "res/ezhupei/saveData";
			}
		}else if(isAfter){
			//编辑这条rec
			dataFlow = expressBiz.editExpress(dataFlow, operUserFlow, resultFlow, funcFlow,GlobalConstant.RES_EZHUPEI_DEFAULT_FORM, request,"","", "");
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

		return "res/ezhupei/saveData";
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
			return "res/ezhupei/delData";
		}

		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3011002");
			model.addAttribute("resultType", "用户角色为空");
			return "res/ezhupei/delData";
		}

		if("Student".equals(roleId)&&StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3011003");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/ezhupei/delData";
		}

		if("Teacher".equals(roleId)&&StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3011004");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/ezhupei/delData";
		}

		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", "3011005");
			model.addAttribute("resultType", "功能类型为空");
			return "res/ezhupei/delData";
		}

		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3011006");
			model.addAttribute("resultType", "功能标识为空");
			return "res/ezhupei/delData";
		}

		if(StringUtil.isEmpty(dataFlow)){
			model.addAttribute("resultId", "3011007");
			model.addAttribute("resultType", "登记数据标识为空");
			return "res/ezhupei/delData";
		}

		//删除rec
		appBiz.delRec(dataFlow);

		return "res/ezhupei/delData";
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
			return "res/ezhupei/qrCode";
		}



		if(StringUtil.isNotEquals("qrCode", funcTypeId)){
			model.addAttribute("resultId", "3011104");
			model.addAttribute("resultType", "功能类型错误");
			return "res/ezhupei/qrCode";
		}

//		if(StringUtil.isEmpty(funcFlow)){
//			model.addAttribute("resultId", "3011105");
//			model.addAttribute("resultType", "功能标识为空");
//			return "res/ezhupei/qrCode";
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
				return "res/ezhupei/qrCode";
			}
			if(StringUtil.isBlank(roleId))
			{
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "roleId为空！");
				return "res/ezhupei/qrCode";
			}
			if(!"Student".equals(roleId)&&!"Teacher".equals(roleId)&&!"Head".equals(roleId)&&!"Seretary".equals(roleId)) {
				model.addAttribute("resultId", "30906");
				model.addAttribute("resultType", "roleId标识符不正确");
				return "res/ezhupei/qrCode";
			}

			SysUser currUser = appBiz.readSysUser(userFlow);
			if(currUser==null){
				model.addAttribute("resultId", "30906");
				model.addAttribute("resultType", "用户不存在");
				return "res/ezhupei/qrCode";
			}
			ResDoctor doctor=null;
			if("Student".equals(roleId)) {
				doctor = appBiz.readResDoctor(currUser.getUserFlow());
				if(doctor==null)
				{
					model.addAttribute("resultId", "30906");
					model.addAttribute("resultType", "学员医师信息不存在");
					return "res/ezhupei/qrCode";
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
						return "res/ezhupei/qrCode";
					}
					//5秒钟有效
					String currTime = DateUtil.getCurrDateTime();
					if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(currTime), DateUtil.transDateTime(signTime)) > 5) {
						model.addAttribute("resultId", "3011106");
						model.addAttribute("resultType", "二维码已失效，请重新扫码！");
						return "res/ezhupei/qrCode";
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
						return "res/ezhupei/qrCode";
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
						return "res/ezhupei/qrCode";
					}
					if(nowDate.compareTo(endDate)>0)
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "不在扫码时间范围内，无法扫码！");
						return "res/ezhupei/qrCode";
					}
					if(nowDate.compareTo(endDate)>0)
					{
						model.addAttribute("resultId", "30111013");
						model.addAttribute("resultType", "讲座签到时间已结束，无法签到！");
						return "res/ezhupei/qrCode";
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
						return "res/ezhupei/qrCode";
					}
					if(nowDate.compareTo(endDate)>0)
					{
						model.addAttribute("resultId", "30111013");
						model.addAttribute("resultType", "讲座签到时间已结束，无法签到！");
						return "res/ezhupei/qrCode";
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
					return "res/ezhupei/qrCode";
				}
				return "res/ezhupei/qrCode";
			}else{
				model.addAttribute("resultId", "3011108");
				model.addAttribute("resultType", "二维码已失效！讲座信息不存在！");

			}

			return "res/ezhupei/qrCode";
		}else if(StringUtil.isEquals(funcFlow, "lectureOutSignin")){
			String lectureFlow = paramMap.get("lectureFlow");
			if(StringUtil.isBlank(lectureFlow))
			{
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "讲座信息不存在！");
				return "res/ezhupei/qrCode";
			}
			if(StringUtil.isBlank(roleId))
			{
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "roleId为空！");
				return "res/ezhupei/qrCode";
			}
			if(!"Student".equals(roleId)&&!"Teacher".equals(roleId)&&!"Head".equals(roleId)&&!"Seretary".equals(roleId)) {
				model.addAttribute("resultId", "30906");
				model.addAttribute("resultType", "roleId标识符不正确");
				return "res/ezhupei/qrCode";
			}

			SysUser currUser = appBiz.readSysUser(userFlow);
			if(currUser==null){
				model.addAttribute("resultId", "30906");
				model.addAttribute("resultType", "用户不存在");
				return "res/ezhupei/qrCode";
			}
			ResDoctor doctor=null;
			if("Student".equals(roleId)) {
				doctor = appBiz.readResDoctor(currUser.getUserFlow());
				if(doctor==null)
				{
					model.addAttribute("resultId", "30906");
					model.addAttribute("resultType", "学员医师信息不存在");
					return "res/ezhupei/qrCode";
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
						return "res/ezhupei/qrCode";
					}
					//5秒钟有效
					String currTime = DateUtil.getCurrDateTime();
					if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(currTime), DateUtil.transDateTime(signTime)) > 5) {
						model.addAttribute("resultId", "3011106");
						model.addAttribute("resultType", "二维码已失效，请重新扫码！");
						return "res/ezhupei/qrCode";
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
						return "res/ezhupei/qrCode";
					}
					if(regist.getIsScan2().equals(GlobalConstant.FLAG_Y))
					{
						model.addAttribute("resultId", "3011109");
						model.addAttribute("resultType", "已经扫过码了！");
						return "res/ezhupei/qrCode";
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
						return "res/ezhupei/qrCode";
					}
					if( nowDate.compareTo(endDate)>0)
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "不在扫码时间范围内，无法扫码！");
						return "res/ezhupei/qrCode";
					}
					if(nowDate.compareTo(endDate)>0)
					{
						model.addAttribute("resultId", "30111013");
						model.addAttribute("resultType", "讲座已经结束！");
						return "res/ezhupei/qrCode";
					}
					regist.setIsScan2(GlobalConstant.FLAG_Y);
					regist.setScan2Time(DateUtil.getCurrDateTime());
				}else{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "你未参加该讲座，无法进行签退！");
					return "res/ezhupei/qrCode";
				}
				int count =appBiz.scanRegist(regist);
				if(count!=1)
				{
					model.addAttribute("resultId", "3011110");
					model.addAttribute("resultType", "扫码失败，请稍后再试！");
					return "res/ezhupei/qrCode";
				}
				return "res/ezhupei/qrCode";
			}else{
				model.addAttribute("resultId", "3011108");
				model.addAttribute("resultType", "二维码已失效！讲座信息不存在！");

			}
			return "res/ezhupei/qrCode";
		}
		else if(StringUtil.isEquals(funcFlow, "randomSignIn")){//讲座随机签到

			String scanTime = DateUtil.getCurrDateTime();
			String randomFlow=paramMap.get("randomFlow");
			if(StringUtil.isBlank(randomFlow)) {
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "无效二维码");
				return "res/ezhupei/qrCode";
			}
			ResLectureRandomSign sign=appBiz.readLectureRandomSign(randomFlow);
			if(sign==null)
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "随机签到信息不存在");
				return "res/ezhupei/qrCode";
			}
			ResLectureInfo lectureInfo=appBiz.read(sign.getLectureFlow());
			if(lectureInfo==null)
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "讲座信息不存在");
				return "res/ezhupei/qrCode";
			}
			String nowDate=DateUtil.getCurrDate();
			if(!nowDate.equals(lectureInfo.getLectureTrainDate()))
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "讲座日期不是当前日期");
				return "res/ezhupei/qrCode";
			}
			//学员信息
			SysUser docUser=appBiz.readSysUser(userFlow);
			if(docUser==null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "用户信息不存在");
				return "res/ezhupei/qrCode";
			}
			ResDoctor doctor=appBiz.readResDoctor(userFlow);
			if(doctor==null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "医师信息不存在");
				return "res/ezhupei/qrCode";
			}
			ResLectureScanRegist regist=appBiz.searchByUserFlowAndLectureFlow(userFlow,sign.getLectureFlow());
			if(regist==null||!"Y".equals(regist.getIsScan()))
			{
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "讲座未签到无法进行随机签到");
				return "res/ezhupei/qrCode";
			}
			ResLectureRandomScan scan=appBiz.readLectureRandomScan(userFlow,randomFlow);
			if(scan!=null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "此次随机签到，你已完成！");
				return "res/ezhupei/qrCode";
			}
			if("Y".equals(sign.getCodeStatusType()))
			{
				String signTime = paramMap.get("time");
				if (StringUtil.isBlank(signTime)) {
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "无效二维码");
					return "res/ezhupei/qrCode";
				}
				//5秒钟有效
				if (StringUtil.isBlank(scanTime)) {
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "缺少参数");
					return "res/ezhupei/qrCode";
				}
				if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(scanTime), DateUtil.transDateTime(signTime)) > 10) {
					model.addAttribute("resultId", "3011106");
					model.addAttribute("resultType", "二维码已失效，请重新扫码！");
					return "res/ezhupei/qrCode";
				}
			}
			String nowTime=DateUtil.transDateTime(scanTime,"yyyyMMddHHmmss","HH:mm:ss");
			if(nowTime.compareTo(sign.getCodeStartTime())<0||nowTime.compareTo(sign.getCodeEndTime())>0)
			{
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "扫码时间不在签到时间范围内！");
				return "res/ezhupei/qrCode";
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
				return "res/ezhupei/qrCode";
			}
			return "res/ezhupei/qrCode";
		}else if(StringUtil.isEquals(funcFlow, "signin")){
			String signTime = paramMap.get("signTime");
			String currTime = DateUtil.getCurrDateTime();
			if(StringUtil.isEmpty(deptFlow)){
				model.addAttribute("resultId", "3011102");
				model.addAttribute("resultType", "科室标识符为空");
				return "res/ezhupei/qrCode";
			}

			if(StringUtil.isEmpty(funcTypeId)){
				model.addAttribute("resultId", "3011103");
				model.addAttribute("resultType", "功能类型为空");
				return "res/ezhupei/qrCode";
			}
			//一分钟有效
			if(DateUtil.signSecondsBetweenTowDate(DateUtil.transDate(currTime),DateUtil.transDate(signTime))>60){
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "二维码已失效！");
				return "res/ezhupei/qrCode";
			}

			//签到
			String signinType = com.pinde.res.enums.ezhupei.SigninTypeEnum.Day.getId();
			boolean result = appBiz.signin(userFlow,deptFlow,signinType);
			if(!result){
				model.addAttribute("resultId", "3011108");
				model.addAttribute("resultType", "您已完成本次签到！");
			}else{
				model.addAttribute("resultId", "3011109");
				model.addAttribute("resultType", "签到成功！");
			}
			return "res/ezhupei/qrCode";
		}else if(StringUtil.isEquals(funcFlow, "activitySigin")) {//教学活动签到

				String activityFlow=paramMap.get("activityFlow");
				if(StringUtil.isBlank(activityFlow))
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "二维码错误");
					return "res/ezhupei/qrCode";
				}
				TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
				if(info==null||!GlobalConstant.RECORD_STATUS_Y.equals(info.getRecordStatus()))
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "活动信息不存在");
					return "res/ezhupei/qrCode";
				}

				String signTime = paramMap.get("time");
				String key="res_"+info.getOrgFlow()+"_org_activity_code_type";
				String cfgv=appBiz.getResCfgCode(key);
				if(GlobalConstant.FLAG_Y.equals(cfgv)) {
					if(StringUtil.isBlank(signTime))
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "无效二维码");
						return "res/ezhupei/qrCode";
					}
					//5秒钟有效
					String currTime = DateUtil.getCurrDateTime();
					if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(currTime), DateUtil.transDateTime(signTime)) > 5) {
						model.addAttribute("resultId", "3011106");
						model.addAttribute("resultType", "二维码已失效，请重新扫码！");
						return "res/ezhupei/qrCode";
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
					return "res/ezhupei/qrCode";
				}
				if( DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm").compareTo(endDate)>0)
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "活动已结束，无法参加");
					return "res/ezhupei/qrCode";
				}
				int count=activityBiz.checkJoin(activityFlow,userFlow);
				if(count>0)
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "该时间段，你已报名参加其他教学活动，无法参加");
					return "res/ezhupei/qrCode";
				}

				ResDoctor doctor=appBiz.readResDoctor(userFlow);
				if(doctor==null)
				{
					model.addAttribute("resultId", "30203");
					model.addAttribute("resultType", "医师信息不存在");
					return "res/ezhupei/qrCode";
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
					return "res/ezhupei/qrCode";
				}
				TeachingActivityResult result=activityBiz.readRegistInfo(activityFlow,userFlow);
				if(result!=null)
				{
					if(GlobalConstant.FLAG_Y.equals(result.getIsScan()))
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "你已扫码签到成功！");
						return "res/ezhupei/qrCode";
					}
					result.setIsScan(GlobalConstant.FLAG_Y);
					result.setScanTime(DateUtil.getCurrDateTime());

					int c=activityBiz.saveResult(result,userFlow);
					if(c==0)
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "签到失败！");
						return "res/ezhupei/qrCode";
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
						return "res/ezhupei/qrCode";
					}
				}
				return "res/ezhupei/qrCode";
		}else if(StringUtil.isEquals(funcFlow, "activityOutSigin")) {//教学活动签退

				String activityFlow=paramMap.get("activityFlow");
				if(StringUtil.isBlank(activityFlow))
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "二维码错误");
					return "res/ezhupei/qrCode";
				}
				TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
				if(info==null||!GlobalConstant.RECORD_STATUS_Y.equals(info.getRecordStatus()))
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "活动信息不存在");
					return "res/ezhupei/qrCode";
				}
				String signTime = paramMap.get("time");
				String key="res_"+info.getOrgFlow()+"_org_activity_code_type";
				String cfgv=appBiz.getResCfgCode(key);
				if(GlobalConstant.FLAG_Y.equals(cfgv)) {
					if(StringUtil.isBlank(signTime))
					{
						model.addAttribute("resultId", "3011107");
						model.addAttribute("resultType", "无效二维码");
						return "res/ezhupei/qrCode";
					}
					//5秒钟有效
					String currTime = DateUtil.getCurrDateTime();
					if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(currTime), DateUtil.transDateTime(signTime)) > 5) {
						model.addAttribute("resultId", "3011106");
						model.addAttribute("resultType", "二维码已失效，请重新扫码！");
						return "res/ezhupei/qrCode";
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
					return "res/ezhupei/qrCode";
				}
				if(!"Y".equals(result.getIsScan()))
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "你未签到参加该活动，无法进行签退！");
					return "res/ezhupei/qrCode";
				}
				String nowDate=DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");
				if( nowDate.compareTo(startDate)<0)
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "活动签退暂未开始，无法签退！");
					return "res/ezhupei/qrCode";
				}
				if( nowDate.compareTo(endDate)>0)
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "活动签退已结束，无法签退！");
					return "res/ezhupei/qrCode";
				}
				if(GlobalConstant.FLAG_Y.equals(result.getIsScan2()))
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "你已扫码签退成功！");
					return "res/ezhupei/qrCode";
				}
				result.setIsEffective(GlobalConstant.FLAG_Y);
				result.setIsScan2(GlobalConstant.FLAG_Y);
				result.setScanTime2(DateUtil.getCurrDateTime());
				int c=activityBiz.saveResult(result,userFlow);
				if(c==0)
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "签退失败！");
					return "res/ezhupei/qrCode";
				}
				return "res/ezhupei/qrCode";
		} else if(StringUtil.isEquals(funcFlow, "EntryReport")) {//入科报到

			String signTime = paramMap.get("signTime");
			String teacherDeptFlows = paramMap.get("teacherDeptFlows");
			String token = paramMap.get("token");
			String headUserFlow = paramMap.get("headUserFlow");
			String currTime = DateUtil.getCurrDateTime();

			if(StringUtil.isEmpty(teacherDeptFlows)){
				model.addAttribute("resultId", "3011103");
				model.addAttribute("resultType", "二维码无效");
				return "res/ezhupei/qrCode";
			}
			if(StringUtil.isEmpty(funcTypeId)){
				model.addAttribute("resultId", "3011103");
				model.addAttribute("resultType", "功能类型为空");
				return "res/ezhupei/qrCode";
			}
			//一分钟有效
			if(DateUtil.signSecondsBetweenTowDate(DateUtil.transDate(currTime),DateUtil.transDate(signTime))>60){
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "二维码已失效！");
				return "res/ezhupei/qrCode";
			}
			//校验科室是否有轮转
			List<String> deptFlows= Arrays.asList(teacherDeptFlows.split(","));
			SchArrangeResult result=appBiz.getResultByDeptFlow(deptFlows,userFlow);
			if(result==null)
			{
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "你在此科室内没有轮转记录信息");
				return "res/ezhupei/qrCode";
			}
			SchEntryReport schEntryReport=appBiz.getSchEntryReport(result.getResultFlow());
			if(schEntryReport!=null)
			{
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "你已成功扫码报到！");
				return "res/ezhupei/qrCode";
			}
			schEntryReport=new SchEntryReport();
			schEntryReport.setDoctorFlow(userFlow);
			schEntryReport.setHeadUserFlow(headUserFlow);
			schEntryReport.setReportTime(DateUtil.getCurrDateTime2());
			schEntryReport.setReportDate(DateUtil.getCurrDate());
			schEntryReport.setRecordStatus("Y");
			schEntryReport.setCodeInfo(codeInfo);
			int c = appBiz.addEntryReport(schEntryReport,userFlow);
			if(c>0){
				model.addAttribute("resultId", "3011108");
				model.addAttribute("resultType", "你已成功扫码报到！");
			}else{
				model.addAttribute("resultId", "3011109");
				model.addAttribute("resultType", "扫码报到失败，请重试！");
			}
			return "res/ezhupei/qrCode";
		} else {
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "无效二维码");
			return "res/ezhupei/qrCode";
		}
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
			return "res/ezhupei/schDeptList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "31602");
			model.addAttribute("resultType", "起始页为空");
			return "res/ezhupei/schDeptList";
		}

		if(pageSize==null){
			model.addAttribute("resultId", "31603");
			model.addAttribute("resultType", "页面大小为空");
			return "res/ezhupei/schDeptList";
		}

		//读取这个用户的医师信息
		ResDoctor doctor = appBiz.readResDoctor(userFlow);
		if(doctor==null){
			model.addAttribute("resultId", "31604");
			model.addAttribute("resultType", "读取医师信息失败!");
			return "res/ezhupei/schDeptList";
		}

		String orgFlow = doctor.getOrgFlow();//医师所在培训机构
		if(StringUtil.isEmpty(orgFlow)){
			model.addAttribute("resultId", "31605");
			model.addAttribute("resultType", "该医师暂无培训基地!");
			return "res/ezhupei/schDeptList";
		}

		PageHelper.startPage(pageIndex, pageSize);
		List<SchDeptRel> schDeptList = appBiz.searchRelByStandard(orgFlow,standardDeptId,searchStr);
		model.addAttribute("schDeptList",schDeptList);

		model.addAttribute("dataCount",PageHelper.total);//数据总量

		return "res/ezhupei/schDeptList";
	}

	@RequestMapping(value={"/teacherList"},method={RequestMethod.POST})
	public String teacherList(String schDeptFlow,String searchStr,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", "31701");
			model.addAttribute("resultType", "轮转科室标识符为空");
			return "res/ezhupei/teacherList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "31702");
			model.addAttribute("resultType", "起始页为空");
			return "res/ezhupei/teacherList";
		}

		if(pageSize==null){
			model.addAttribute("resultId", "31703");
			model.addAttribute("resultType", "页面大小为空");
			return "res/ezhupei/teacherList";
		}

		String roleFlow = appBiz.getCfgByCode("res_teacher_role_flow");
		if(StringUtil.isNotBlank(roleFlow)){
			PageHelper.startPage(pageIndex, pageSize);
			List<SysUser> userList =  appBiz.getUserListBySchDept(schDeptFlow,roleFlow,searchStr);
			model.addAttribute("userList", userList);
			model.addAttribute("dataCount",PageHelper.total);//数据总量
		}

		return "res/ezhupei/teacherList";
	}

	@RequestMapping(value={"/headList"},method={RequestMethod.POST})
	public String headList(String schDeptFlow,String searchStr,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", "31701");
			model.addAttribute("resultType", "轮转科室标识符为空");
			return "res/ezhupei/headList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "31702");
			model.addAttribute("resultType", "起始页为空");
			return "res/ezhupei/headList";
		}

		if(pageSize==null){
			model.addAttribute("resultId", "31703");
			model.addAttribute("resultType", "页面大小为空");
			return "res/ezhupei/headList";
		}

		String roleFlow = appBiz.getCfgByCode("res_head_role_flow");
		if(StringUtil.isNotBlank(roleFlow)){
			PageHelper.startPage(pageIndex, pageSize);
			List<SysUser> userList =  appBiz.getUserListBySchDept(schDeptFlow,roleFlow,searchStr);
			model.addAttribute("userList", userList);
			model.addAttribute("dataCount",PageHelper.total);//数据总量
		}

		return "res/ezhupei/headList";
	}

}

