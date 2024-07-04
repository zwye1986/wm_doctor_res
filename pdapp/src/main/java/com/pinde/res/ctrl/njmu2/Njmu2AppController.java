package com.pinde.res.ctrl.njmu2;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pinde.app.common.PasswordUtil;
import com.pinde.res.biz.stdp.IResGradeBiz;
import com.pinde.res.biz.stdp.IResSchProcessExpressBiz;
import com.pinde.res.enums.jswjw.RecDocCategoryEnum;
import com.pinde.sci.model.mo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.njmu2.INjmu2AppBiz;
import com.pinde.res.biz.njmu2.INjmu2StudentBiz;
import com.pinde.res.biz.njmu2.INjmu2TeacherBiz;
import com.pinde.res.enums.njmu2.ResAssessTypeEnum;
import com.pinde.res.enums.njmu2.ResRecTypeEnum;
import com.pinde.res.enums.njmu2.SigninTypeEnum;
import com.pinde.res.enums.stdp.ResultEnum;
import com.pinde.res.enums.stdp.UserStatusEnum;
import com.pinde.sci.util.PasswordHelper;

@Controller
@RequestMapping("/res/njmu2")
public class Njmu2AppController{    
	private static Logger logger = LoggerFactory.getLogger(Njmu2AppController.class);
	 
	@Autowired
	private INjmu2AppBiz njmu2AppBiz;
	@Autowired
	private INjmu2StudentBiz njmu2StudentBiz;
	@Autowired
	private INjmu2TeacherBiz teacherBiz;
	@Autowired
	private IResGradeBiz gradeBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;

	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/njmu2/500";
    }
	
	@RequestMapping(value={"/version"},method={RequestMethod.GET})
	public String version(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "res/njmu2/version";
	}
	
	
	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(){
		return "res/njmu2/test";
	}

	@RequestMapping(value={"/remember"},method={RequestMethod.GET})
	public String remember(String userFlow,String roleId,String deptFlow,String doctorFlow,String cataFlow,String dataFlow,String funcTypeId,String funcFlow,HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("userFlow", userFlow);
		session.setAttribute("roleId", roleId);
		session.setAttribute("deptFlow", deptFlow);
		session.setAttribute("doctorFlow", doctorFlow);
		session.setAttribute("cataFlow", cataFlow);
		session.setAttribute("dataFlow", dataFlow);
		session.setAttribute("funcTypeId", funcTypeId);
		session.setAttribute("funcFlow", funcFlow);
		return "/res/njmu2/test";
	}

	@RequestMapping(value={"/login"},method={RequestMethod.POST})
	public String login(String userCode,String userPasswd,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		
		if(StringUtil.isEmpty(userCode)){
			model.addAttribute("resultId", "3010101");
			model.addAttribute("resultType", "用户名为空");
			return "res/njmu2/login";
		}
		
		if(StringUtil.isEmpty(userPasswd)){
			model.addAttribute("resultId", "3010102");
			model.addAttribute("resultType", "密码为空");
			return "res/njmu2/login";
		}
		
		//验证用户是否存在
		SysUser userinfo = njmu2AppBiz.getUserByCode(userCode);
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
				return "res/njmu2/login";
			}
			
			//验证用户是否锁定,锁定用户不能登录
			String userStatus = userinfo.getStatusId();
			if(UserStatusEnum.Locked.getId().equals(userStatus)){
				model.addAttribute("resultId", "3010105");
				model.addAttribute("resultType", "该用户已被锁定");
				return "res/njmu2/login";
			}
			
			//验证用户是否有角色
			List<SysUserRole> userRoles = njmu2AppBiz.getSysUserRole(userFlow);
			if(userRoles==null || userRoles.isEmpty()){
				model.addAttribute("resultId", "3010106");
				model.addAttribute("resultType", "用户未赋权");
				return "res/njmu2/login";
			}
			
			boolean isDoctor = false;
			boolean isTeacher = false;
			
			//获取当前配置的医师角色
			String doctorRole = njmu2AppBiz.getCfgByCode("res_doctor_role_flow");
			
			//获取当前配置的老师角色
			String teacherRole = njmu2AppBiz.getCfgByCode("res_teacher_role_flow");

			//获取当前配置的医院管理员角色
			String hospitalRole = njmu2AppBiz.getCfgByCode("res_admin_role_flow");

			//获取当前配置的学校管理员角色
			String adminRole = njmu2AppBiz.getCfgByCode("res_school_role_flow");



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
				}else if(hospitalRole.equals(ur)){
					isTeacher = true;
					model.addAttribute("roleId","Hospital");
					model.addAttribute("roleName","医院管理员");
					break;
				}else if(adminRole.equals(ur)){
					isTeacher = true;
					model.addAttribute("roleId","Admin");
					model.addAttribute("roleName","学校管理员");
					break;
				}
			}
			
			model.addAttribute("isDoctor", isDoctor);
			model.addAttribute("isTeacher", isTeacher);
			
			//如果是医师需要获取医师的一些培训信息
			if(isDoctor){
				//读取这个用户的医师信息
				ResDoctor doctor = njmu2AppBiz.readResDoctor(userFlow);
				
				if(doctor==null){
					model.addAttribute("resultId", "3010107");
					model.addAttribute("resultType", "登录失败,学员数据出错!");
					return "res/njmu2/login";
				}
				
				//将该用户的培训年限id转换为name
//				String trainingYears = doctor.getTrainingYears();
//				if(StringUtil.isNotBlank(trainingYears)){
//					trainingYears = TrainYearEnum.getNameById(trainingYears);
//					doctor.setTrainingYears(trainingYears);
//				}
				
				model.addAttribute("doctor", doctor);
				
				//获取该用户的入科记录的第一次入科时间和最后一次出科时间
				Map<String,Object> processAreaMap = njmu2AppBiz.getDocProcessArea(userFlow); 
				if(processAreaMap!=null){
					String minDate = (String)processAreaMap.get("minDate");
					model.addAttribute("minDate",minDate);
					
					long schDays = 0;
					if(StringUtil.isNotBlank(minDate)){
						String currDate = DateUtil.getCurrDate();
						model.addAttribute("maxDate",currDate);
						
						//获取该医师的已轮转天数
						schDays = DateUtil.signDaysBetweenTowDate(currDate,minDate)+1;
						
						//获取该医师的轮转计划的区间
						Map<String,Object> resultAreaMap = njmu2AppBiz.getDocResultArea(userFlow); 
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
			}
			if(!isDoctor&&!isTeacher){
				model.addAttribute("resultId", "3010107");
				model.addAttribute("resultType", "所属角色，无权限登录!");
				return "res/njmu2/login";
			}
			model.addAttribute("userinfo", userinfo);
		}
		return "res/njmu2/login";
	}
	
	@RequestMapping(value={"/notice"},method={RequestMethod.GET})
	public String notice(String userFlow,String roleId,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		String result = "res/njmu2/notice";
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
		String result = "res/njmu2/noticeCount";
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
			return "res/njmu2/viewData";
		}
		
		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3010602");
			model.addAttribute("resultType", "用户角色为空");
			return "res/njmu2/viewData";
		}
		
		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", "3010603");
			model.addAttribute("resultType", "功能类型为空");
			return "res/njmu2/viewData";
		}
		
		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3010604");
			model.addAttribute("resultType", "功能标识为空");
			return "res/njmu2/viewData";
		}
		
		if("Student".equals(roleId)&&StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3010605");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/njmu2/viewData";
		}
		
		if("Teacher".equals(roleId)&&StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3010606");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/njmu2/viewData";
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
		SchArrangeResult result = njmu2StudentBiz.searcheDocResult(null,resultFlow);
		if(result!=null){
			model.addAttribute("result",result);
			
			//读取要求
			if(StringUtil.isNotBlank(cataFlow)){
				String standardDeptId = result.getStandardDeptId();
				String standardGroupFlow = result.getStandardGroupFlow();
				SchRotationDept rotationDept = njmu2AppBiz.getRotationDeptByResult(standardGroupFlow,standardDeptId);
				if(rotationDept!=null){
					String relRecordFlow = rotationDept.getRecordFlow();
					req = njmu2AppBiz.readReq(null,relRecordFlow,cataFlow);
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
		if(ResRecTypeEnum.AfterSummary.getId().equals(funcFlow)
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
				ResDoctorSchProcess process = njmu2StudentBiz.getProcessByResult(resultFlow);
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
			ResSchProcessExpress rec =null;
			String processFlow = "";
			ResDoctorSchProcess process = njmu2StudentBiz.getProcessByResult(resultFlow);
			if(process!=null){
				//登记表单的科室
				processFlow = process.getProcessFlow();
			}
			rec = expressBiz.getExpressByRecType(processFlow,funcFlow);
			if(rec!=null){
				String content = rec.getRecContent();
				//解析登记信息的xml
				Object formDataMap = njmu2AppBiz.parseRecContent(content);
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
				rec = njmu2AppBiz.getRecByRecFlow(dataFlow);
				//如果是11模式根据类型和科室查询
			}else if("dataInput11".equals(funcTypeId)){
				String processFlow = "";
				ResDoctorSchProcess process = njmu2StudentBiz.getProcessByResult(resultFlow);
				if(process!=null){
					//登记表单的科室
					processFlow = process.getProcessFlow();
				}
				rec = njmu2AppBiz.getRecByRecType(processFlow,funcFlow);
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
				Object formDataMap = null;
				if(!isGrade){
					formDataMap = njmu2AppBiz.parseRecContent(content);
				}else{
					formDataMap = gradeBiz.parseDocGradeXml(content);
				}
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
		
		return "res/njmu2/viewData";
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
			return "res/njmu2/dataList";
		}
		
		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3010702");
			model.addAttribute("resultType", "用户角色为空");
			return "res/njmu2/dataList";
		}
		
		if("Student".equals(roleId)&&StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3010703");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/njmu2/dataList";
		}
		
		if("Teacher".equals(roleId)&&StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3010704");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/njmu2/dataList";
		}
		
		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", "3010705");
			model.addAttribute("resultType", "功能类型为空");
			return "res/njmu2/dataList";
		}
		
		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3010706");
			model.addAttribute("resultType", "功能标识为空");
			return "res/njmu2/dataList";
		}
		
		if(pageIndex==null){
			model.addAttribute("resultId", "3010707");
			model.addAttribute("resultType", "起始页为空");
			return "res/njmu2/dataList";
		}
		
		if(pageSize==null){
			model.addAttribute("resultId", "3010708");
			model.addAttribute("resultType", "页面大小为空");
			return "res/njmu2/dataList";
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
		ResDoctorSchProcess process = njmu2StudentBiz.getProcessByResult(resultFlow);
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
			recMaps = njmu2AppBiz.getParsedRecs(paramMap);
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
			recMaps = njmu2AppBiz.getTeacherParsedRecs(paramMap);
		}
		model.addAttribute("path",path);
		model.addAttribute("recMaps",recMaps);
		
		//百分比算法
		Map<String,Object> perMap = njmu2StudentBiz.getRegPer(0, userFlow, resultFlow,funcFlow,cataFlow,true);
		model.addAttribute("perMap",perMap);
		
		model.addAttribute("dataCount",PageHelper.total);		
		
		return "res/njmu2/dataList";
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
			return "res/njmu2/cataList";
		}
		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3010802");
			model.addAttribute("resultType", "用户角色为空");
			return "res/njmu2/cataList";
		}
		if("Student".equals(roleId)&&StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3010803");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/njmu2/cataList";
		}
		if("Teacher".equals(roleId)&&StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3010804");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/njmu2/cataList";
		}
		if(StringUtil.isNotEquals(funcTypeId, "dataInputNN")){
			model.addAttribute("resultId", "3010805");
			model.addAttribute("resultType", "功能类型不匹配");
			return "res/njmu2/cataList";
		}
		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3010806");
			model.addAttribute("resultType", "功能标识为空");
			return "res/njmu2/cataList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "3010807");
			model.addAttribute("resultType", "起始页为空");
			return "res/njmu2/cataList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3010808");
			model.addAttribute("resultType", "页面大小为空");
			return "res/njmu2/cataList";
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
		SchArrangeResult result = njmu2StudentBiz.searcheDocResult(null,resultFlow);
		if(result!=null){
			String standardDeptId = result.getStandardDeptId();
			String standardGroupFlow = result.getStandardGroupFlow();
			SchRotationDept rotationDept = njmu2AppBiz.getRotationDeptByResult(standardGroupFlow,standardDeptId);
			if(rotationDept!=null){
				String rocordFlow = rotationDept.getRecordFlow();
				paramMap.put("relRecordFlow",rocordFlow);
			}
		}
		
		//根据条件获取要求列表
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> reqMaps = njmu2AppBiz.getReqByResult(paramMap);
		model.addAttribute("reqMaps",reqMaps);
		
		model.addAttribute("dataCount",PageHelper.total);
		
		//百分比算法
		Map<String,Object> perMap = njmu2StudentBiz.getRegPer(0, userFlow, resultFlow,funcFlow);
		model.addAttribute("perMap",perMap);
		
		String recTypeName = ResRecTypeEnum.getNameById(funcFlow);
		model.addAttribute("recTypeName",recTypeName);
		
		return "res/njmu2/cataList";
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
			return "res/njmu2/saveData";
		}
		
		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3010902");
			model.addAttribute("resultType", "用户角色为空");
			return "res/njmu2/saveData";
		}
		
		if("Student".equals(roleId)&&StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3010903");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/njmu2/saveData";
		}
		
		if("Teacher".equals(roleId)&&StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3010904");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/njmu2/saveData";
		}
		
		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", "3010905");
			model.addAttribute("resultType", "功能类型为空");
			return "res/njmu2/saveData";
		}
		
		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3010906");
			model.addAttribute("resultType", "功能标识为空");
			return "res/njmu2/saveData";
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
		if(ResRecTypeEnum.AfterSummary.getId().equals(funcFlow)
				)
		{
			isAfter=true;
		}
		//是否是评分，如果是评分，则去DEPT_TEACHER_GRADE_INFO表取数据
		if(isGrade)
		{
			//读取相关信息
			String processFlow = "";
			ResDoctorSchProcess process = njmu2StudentBiz.getProcessByResult(resultFlow);
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
				ResDoctorSchProcess process = njmu2StudentBiz.getProcessByResult(resultFlow);
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
		}else {

			//如果是11查出dataFlow
			ResRec rec = null;
			if("dataInput11".equals(funcTypeId)){
				String processFlow = "";
				ResDoctorSchProcess process = njmu2StudentBiz.getProcessByResult(resultFlow);
				if(process!=null){
					//登记表单的科室
					processFlow = process.getProcessFlow();

					operUserFlow = process.getUserFlow();
				}
				rec = njmu2AppBiz.getRecByRecType(processFlow,funcFlow);
				if(rec!=null){
					dataFlow = rec.getRecFlow();
				}
			}else{
				rec = njmu2AppBiz.getRecByRecFlow(dataFlow);
			}

			if(rec!=null){
				funcFlow = rec.getRecTypeId();
			}

			if("Teacher".equals(roleId)){
				teacherBiz.auditDate(userFlow,dataFlow);
			}
		}
		if(isGrade){
			dataFlow = gradeBiz.editGradeInfo(dataFlow, operUserFlow, resultFlow, funcFlow, request,GlobalConstant.RES_DEFAULT_FORM);
			if(dataFlow!=null&&dataFlow.startsWith("error:"))
			{
				model.addAttribute("resultId", "31801");
				model.addAttribute("resultType", dataFlow.split(":")[1]);
				return "res/njmu2/saveData";
			}
		}else if(isAfter){
			//编辑这条rec
			dataFlow = expressBiz.editSxsExpress(dataFlow, operUserFlow, resultFlow, funcFlow,GlobalConstant.RES_DEFAULT_FORM, request,roleId);
		}else {
			//编辑这条rec
			njmu2AppBiz.editRec(dataFlow,operUserFlow,resultFlow,funcFlow,cataFlow,request,roleId);
		}

		
		return "res/njmu2/saveData";
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
			return "res/njmu2/delData";
		}
		
		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3011002");
			model.addAttribute("resultType", "用户角色为空");
			return "res/njmu2/delData";
		}

		if("Student".equals(roleId)&&StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3011003");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/njmu2/delData";
		}
		
		if("Teacher".equals(roleId)&&StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3011004");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/njmu2/delData";
		}
		
		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", "3011005");
			model.addAttribute("resultType", "功能类型为空");
			return "res/njmu2/delData";
		}
		
		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3011006");
			model.addAttribute("resultType", "功能标识为空");
			return "res/njmu2/delData";
		}
		
		if(StringUtil.isEmpty(dataFlow)){
			model.addAttribute("resultId", "3011007");
			model.addAttribute("resultType", "登记数据标识为空");
			return "res/njmu2/delData";
		}
		
		//删除rec
		njmu2AppBiz.delRec(dataFlow);
		
		return "res/njmu2/delData";
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
			return "res/njmu2/qrCode";
		}
		
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3011102");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/njmu2/qrCode";
		}
		
		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", "3011103");
			model.addAttribute("resultType", "功能类型为空");
			return "res/njmu2/qrCode";
		}
		
		if(StringUtil.isNotEquals("qrCode", funcTypeId)){ 
			model.addAttribute("resultId", "3011104");
			model.addAttribute("resultType", "功能类型错误");
			return "res/njmu2/qrCode";
		}
		
		if(StringUtil.isEmpty(funcFlow)){
			model.addAttribute("resultId", "3011105");
			model.addAttribute("resultType", "功能标识为空");
			return "res/njmu2/qrCode";
		}
		
		if(StringUtil.isEquals(funcFlow, "signin")){
			Map<String,String> paramMap = new HashMap<String,String>();
			transCodeInfo(paramMap, codeInfo);
			String signTime = paramMap.get("signTime");
			String currTime = DateUtil.getCurrDateTime();
			
			//一分钟有效
			if(DateUtil.signSecondsBetweenTowDate(DateUtil.transDate(currTime),DateUtil.transDate(signTime))>60){
				model.addAttribute("resultId", "3011106");
				model.addAttribute("resultType", "二维码已失效！");
				return "res/njmu2/qrCode";
			}
		}else {
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "无效二维码");
			return "res/njmu2/qrCode";
		}
		
		//签到
		String signinType = SigninTypeEnum.Day.getId();
		boolean result = njmu2AppBiz.signin(userFlow,deptFlow,signinType);
		if(!result){
			model.addAttribute("resultId", "3011108");
			model.addAttribute("resultType", "您已完成今日签到！");
		}else{
			model.addAttribute("resultId", "3011109");
			model.addAttribute("resultType", "签到成功！");
		}
		
		return "res/njmu2/qrCode";
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
	@RequestMapping(value={"/doctorTrainingSpe"},method={RequestMethod.GET})
	public String doctorTrainingSpe(
						 HttpServletRequest request,
						 Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		List<SysDict> list=njmu2AppBiz.getDictListByDictId("DoctorTrainingSpe");
		model.addAttribute("list",list);
		return "res/njmu2/dict";
	}
	@RequestMapping(value={"/sessionNumber"},method={RequestMethod.GET})
	public String sessionNumber(
						 HttpServletRequest request,
						 Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		List<SysDict> list=njmu2AppBiz.getDictListByDictId("DoctorSessionNumber");
		model.addAttribute("list",list);
		return "res/njmu2/dict";
	}
	@RequestMapping(value={"/docCategory"},method={RequestMethod.GET})
	public String docCategory(
			HttpServletRequest request,
			Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		List<RecDocCategoryEnum> list=new ArrayList<>();
		for(RecDocCategoryEnum recDocCategoryEnum:RecDocCategoryEnum.values()) {
			 if(GlobalConstant.FLAG_Y.equals(njmu2AppBiz.getCfgByCode("res_doctor_category_"+recDocCategoryEnum.getId())))
			 	list.add(recDocCategoryEnum);
		}
		model.addAttribute("list",list);
		return "res/njmu2/docCategory";
	}
}

