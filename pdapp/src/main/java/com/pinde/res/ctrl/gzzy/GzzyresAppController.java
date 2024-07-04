package com.pinde.res.ctrl.gzzy;


import com.pinde.app.common.NfyyGlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.gzzy.IGzzyAppBiz;
import com.pinde.res.biz.gzzy.IGzzyStudentBiz;
import com.pinde.res.biz.gzzy.IGzzyTeacherBiz;
import com.pinde.res.enums.nfyy.DeptStatusEnum;
import com.pinde.res.model.nfyy.mo.Activity;
import com.pinde.res.model.nfyy.mo.Evaluation;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/res/gzzyres")
public class GzzyresAppController {
	private static Logger logger = LoggerFactory.getLogger(GzzyresAppController.class);

	private boolean alert = true;

	@Autowired
	private IGzzyAppBiz appBiz;
	@Autowired
	private IGzzyStudentBiz studentBiz;
	@Autowired
	private IGzzyTeacherBiz teacherBiz;

	@ExceptionHandler(Throwable.class)
	public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/gzzyres/500";
	}

	@RequestMapping(value={"/version"},method={RequestMethod.GET})
	public String version(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return "res/gzzyres/version";
	}

	@RequestMapping(value={"/login"},method={RequestMethod.POST})
	public String login(String userCode,String userPasswd , Model model){
		String result = "res/gzzyres/login";
		if(StringUtil.isEmpty(userCode)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(userPasswd)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_PASSWD_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_PASSWD_NULL);
			return result;
		}
		//login
		Map<String,String> user = appBiz.login(userCode, userPasswd);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERCODEORPASSWDERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERCODEORPASSWDERROR);
			return result;
		}
		List<String> allowRole=new ArrayList<>();
		allowRole.add("Teacher");
		allowRole.add("Secretary");
//		allowRole.add("Header");
		allowRole.add("Trainee");
		if(!allowRole.contains(user.get("roleId")))
		{
			model.addAttribute("resultId", "11111");
			model.addAttribute("resultType", "您无权限使用");
			return result;
		}
		String userFlow=user.get("userFlow");
		List<Map<String,String>> roles = appBiz.readUserRoles(userFlow);
		model.addAttribute("roles", roles);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		model.addAttribute("user" , user);

		model.addAttribute("noSign" , "Y");
		String nowDay=DateUtil.getCurrDate();
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("nowDay",nowDay);
		paramMap.put("userFlow",userFlow);
		List<Map<String,String>> list=appBiz.getAttendanceDetailList(paramMap);
		if(list!=null&&list.size()>0)
		{
			model.addAttribute("noSign" , "N");
		}
		return result;
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
	@RequestMapping(value={"/qrCode"},method={RequestMethod.POST})
	public synchronized String qrCode(String userFlow,
									  String funcTypeId,
									  String codeInfo,
									  HttpServletRequest request,
									  Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzzyres/qrCode";
		}

		if(StringUtil.isEmpty(funcTypeId)){
			model.addAttribute("resultId", "3011103");
			model.addAttribute("resultType", "功能类型为空");
			return "res/gzzyres/qrCode";
		}

		if(StringUtil.isNotEquals("qrCode", funcTypeId)){
			model.addAttribute("resultId", "3011104");
			model.addAttribute("resultType", "功能类型错误");

			return "res/gzzyres/qrCode";
		}
		Map<String,String> paramMap = new HashMap<String,String>();
		transCodeInfo(paramMap, codeInfo);
		String  funcFlow=paramMap.get("funcFlow");
		if(StringUtil.isEquals(funcFlow, "activitySigin")) {//教学活动签到

			String activityFlow=paramMap.get("CaDisID");
			if(StringUtil.isBlank(activityFlow))
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "二维码错误");
				return "res/gzzyres/qrCode";
			}
			Map<String,String> info=appBiz.readActivityInfo(activityFlow);
			if(info==null)
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "活动信息不存在");
				return "res/gzzyres/qrCode";
			}
			int startTime=10;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date;
			String startDate=info.get("startTime");
			String endDate=info.get("endTime");
			try {
				date = simpleDateFormat.parse(startDate);
				Calendar calender = Calendar.getInstance();
				calender.setTime(date);
				calender.add(Calendar.MINUTE, -startTime);

				startDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm:ss");

				calender.add(Calendar.MINUTE, startTime*2);
				endDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm:ss");
			} catch (ParseException e) {
				startDate=info.get("startTime");
				endDate=info.get("endTime");
			}
			if( DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm:ss").compareTo(startDate)<0)
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "活动暂未开始，无法参加");
				return "res/gzzyres/qrCode";
			}
			if( DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm:ss").compareTo(endDate)>0)
			{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "活动已结束，无法参加");
				return "res/gzzyres/qrCode";
			}
			Map<String,String> result=appBiz.readRegistInfo(activityFlow,userFlow);
			if(result!=null)
			{
				if(StringUtil.isNotBlank(result.get("isSign")))
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "你已扫码签到成功！");
					return "res/gzzyres/qrCode";
				}
				int c=appBiz.saveSign(activityFlow,userFlow);
				if(c==0)
				{
					model.addAttribute("resultId", "3011107");
					model.addAttribute("resultType", "签到失败！");
					return "res/gzzyres/qrCode";
				}
			}else{
				model.addAttribute("resultId", "3011107");
				model.addAttribute("resultType", "未报名此活动，无法签到！");
				return "res/gzzyres/qrCode";
			}
			return "res/gzzyres/qrCode";
		}  else {
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "无效二维码");
			return "res/gzzyres/qrCode";
		}
	}


	@RequestMapping(value={"/notice"},method={RequestMethod.GET})
	public String notice(String userFlow , String roleId , Integer pageIndex , Integer pageSize , Model model){
		String result = "res/gzzyres/notice";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_ROLEID_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_ROLEID_NULL);
			return result;
		}
		if(pageIndex==null){
			pageIndex = NfyyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = NfyyGlobalConstant.DEFAULT_PAGE_SIZE;
		}

		Map<String,String> user = appBiz.readUser(userFlow);
		model.addAttribute("user" , user);

		int dataCount = 0;
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
		}
		String userType = user.get("userType");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
		}
		int userTypeInt = Integer.valueOf(userType);

		int examStatusId = userTypeInt==4?9:7;
		if(NfyyGlobalConstant.ROLE_ID_4.equals(roleId)&&alert){
			String schStatusId = DeptStatusEnum.Entering.getId();
			String schDeptName = "";
			List<Map<String,Object>> schPlans = this.studentBiz.getPlanList(userFlow, schStatusId, schDeptName , 1, Integer.MAX_VALUE,examStatusId);
			List<Map<String,Object>> schPlansToDoIn = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> schPlansToDoOut = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> outDopsList = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> outMiniCexList = new ArrayList<Map<String,Object>>();
			for(Map<String,Object> schPlan : schPlans){
				String schDeptFlow = (String)schPlan.get("schDeptFlow");
				Map<String,Object> enteredDeptEdu = studentBiz.readEnteredDeptEdu(schDeptFlow);
				if(enteredDeptEdu==null){
					schPlansToDoIn.add(schPlan);
					dataCount++;
				}
				Evaluation evaluation = this.studentBiz.readOutSecBrief(schDeptFlow);
				if(evaluation==null){
					String endDate = (String)schPlan.get("endDate");
					if(StringUtil.isNotBlank(endDate)){
						int signDays = (int) DateUtil.signDaysBetweenTowDate(endDate, DateUtil.getCurrDate())+1;
						if(signDays<=2){
							schPlansToDoOut.add(schPlan);
							dataCount++;
						}
					}
				}
				//Dops量表
				Map<String,Object> outDops = teacherBiz.readOutDops(schDeptFlow);
				if(outDops!=null&&0==(Integer)outDops.get("DOPS_State")){
					outDops.put("schDeptFlow", schPlan.get("schDeptFlow"));
					outDops.put("schDeptName", schPlan.get("schDeptName"));
					outDops.put("schStatusId", schPlan.get("schStatusId"));
					outDops.put("dataFlow", outDops.get("DOPS_ID"));
					outDopsList.add(outDops);
					dataCount++;
				}

				//Minicex
				Map<String,Object> outMiniCex = teacherBiz.readOutMiniCex(schDeptFlow);
				if(outMiniCex!=null&&0==(Integer)outMiniCex.get("Mini_State")){
					outMiniCex.put("schDeptFlow", schPlan.get("schDeptFlow"));
					outMiniCex.put("schDeptName", schPlan.get("schDeptName"));
					outMiniCex.put("schStatusId", schPlan.get("schStatusId"));
					outMiniCex.put("dataFlow", outMiniCex.get("Mini_ID"));
					outMiniCexList.add(outMiniCex);
					dataCount++;
				}
			}
			model.addAttribute("schPlansToDoIn", schPlansToDoIn);
			model.addAttribute("schPlansToDoOut", schPlansToDoOut);
			model.addAttribute("outDopsList", outDopsList);
			model.addAttribute("outMiniCexList", outMiniCexList);

			List<Activity> activitysNotEntered = new ArrayList<Activity>();
			List<Activity> activitysNotScore = new ArrayList<Activity>();

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userFlow", userFlow);
			params.put("join", "NotEntered");
			List<Activity> activitys = this.studentBiz.getActicitys(params , 1 , Integer.MAX_VALUE);
			for(Activity userActivity : activitys){
				Activity activity = this.studentBiz.readActivity(userActivity.getDataFlow());
				String currDateTime = DateUtil.getCurrDateTime(DateUtil.defDtPtn02);
				String startDateTime = activity.getTime();
				int sing = (int) DateUtil.signMinutesBetweenTowDate(currDateTime, startDateTime);
				if(sing>=0&&sing<=60){
					dataCount++;
//					System.err.println(activity.getName()+" "+startDateTime+" "+currDateTime+" "+sing);
					activitysNotEntered.add(userActivity);
				}
			}

			params.put("join", "NotScore");
			activitys = this.studentBiz.getActicitys(params , 1 , Integer.MAX_VALUE);
			for(Activity userActivity : activitys){
				Activity activity = this.studentBiz.readActivity(userActivity.getDataFlow());
				String endDateTime = activity.getEndTime();
				String currDateTime = DateUtil.getCurrDateTime(DateUtil.defDtPtn02);
				int sing = (int) DateUtil.signMinutesBetweenTowDate(endDateTime, currDateTime);
				if(sing>=0&&sing<=60){
					dataCount++;
					activitysNotScore.add(userActivity);
				}
			}
			model.addAttribute("activitysNotEntered", activitysNotEntered);
			model.addAttribute("activitysNotScore", activitysNotScore);
		}


		if(NfyyGlobalConstant.ROLE_ID_6.equals(roleId)&&alert){
			String schStatusId = DeptStatusEnum.NotEntered.getId();
			List<Map<String,Object>> notEnteredDoctotList = teacherBiz.getDoctorList(userFlow, schStatusId, null, null, 1 , Integer.MAX_VALUE);
			model.addAttribute("notEnteredDoctotList", notEnteredDoctotList);
			dataCount = dataCount + notEnteredDoctotList.size();

			List<Map<String,Object>> waitEvalOutSecBriefList = teacherBiz.getWaitEvalOutSecBriefList(userFlow);
			model.addAttribute("waitEvalOutSecBriefList", waitEvalOutSecBriefList);
			dataCount = dataCount + waitEvalOutSecBriefList.size();

			Map<String,Object> waitArrangeActivityCount = teacherBiz.getWaitArrangeActivityCount(userFlow);
			if((Integer)waitArrangeActivityCount.get("arrangeCount")>0){
				model.addAttribute("waitArrangeActivityCount", waitArrangeActivityCount);
				dataCount = dataCount + 1;
			}
		}

		model.addAttribute("dataCount", dataCount);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/noticeCount"},method={RequestMethod.GET})
	public String noticeCount(String userFlow , String roleId , Model model){
		String result = "res/gzzyres/noticeCount";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_ROLEID_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_ROLEID_NULL);
			return result;
		}
		int dataCount = 0;
		if(NfyyGlobalConstant.ROLE_ID_4.equals(roleId)&&alert){
			Map<String,String> user = appBiz.readUser(userFlow);
			model.addAttribute("user" , user);

			if(user==null){
				model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
				model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			}
			String userType = user.get("userType");
			if(StringUtil.isBlank(userType)){
				model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
				model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			}
			int userTypeInt = Integer.valueOf(userType);

			int examStatusId = userTypeInt==4?9:7;

			String schStatusId = DeptStatusEnum.Entering.getId();
			String schDeptName = "";
			List<Map<String,Object>> schPlans = this.studentBiz.getPlanList(userFlow, schStatusId, schDeptName , 1, Integer.MAX_VALUE,examStatusId);
			for(Map<String,Object> schPlan : schPlans){
				String schDeptFlow = (String)schPlan.get("schDeptFlow");

				Map<String,Object> enteredDeptEdu = studentBiz.readEnteredDeptEdu(schDeptFlow);
				if(enteredDeptEdu==null){
					dataCount++;
				}
				Evaluation evaluation = this.studentBiz.readOutSecBrief(schDeptFlow);
				if(evaluation==null){
					String endDate = (String)schPlan.get("endDate");
					if(StringUtil.isNotBlank(endDate)){
						int signDays = (int) DateUtil.signDaysBetweenTowDate(endDate, DateUtil.getCurrDate())+1;
						if(signDays<=2){
							dataCount++;
						}
					}
				}

				//Dops量表
				Map<String,Object> outDops = teacherBiz.readOutDops(schDeptFlow);
				if(outDops!=null&&0==(Integer)outDops.get("DOPS_State")){
					dataCount++;
				}

				//Minicex
				Map<String,Object> outMiniCex = teacherBiz.readOutMiniCex(schDeptFlow);
				if(outMiniCex!=null&&0==(Integer)outMiniCex.get("Mini_State")){
					dataCount++;
				}
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userFlow", userFlow);
			params.put("join", "NotEntered");
			List<Activity> activitys = this.studentBiz.getActicitys(params , 1 , Integer.MAX_VALUE);
			for(Activity userActivity : activitys){
				Activity activity = this.studentBiz.readActivity(userActivity.getDataFlow());
				String currDateTime = DateUtil.getCurrDateTime(DateUtil.defDtPtn02);
				String startDateTime = activity.getTime();
				int sing = (int) DateUtil.signMinutesBetweenTowDate(startDateTime, currDateTime);
				if(sing>=0&&sing<=60){
					dataCount++;
				}
			}

			params.put("join", "NotScore");
			activitys = this.studentBiz.getActicitys(params , 1 , Integer.MAX_VALUE);
			for(Activity userActivity : activitys){
				Activity activity = this.studentBiz.readActivity(userActivity.getDataFlow());
				String endDateTime = activity.getEndTime();
				String currDateTime = DateUtil.getCurrDateTime(DateUtil.defDtPtn02);
				int sing = (int) DateUtil.signMinutesBetweenTowDate(currDateTime, endDateTime);
				if(sing>=0&&sing<=60){
					dataCount++;
				}
			}
		}

		if(NfyyGlobalConstant.ROLE_ID_6.equals(roleId)&&alert){
			String schStatusId = DeptStatusEnum.NotEntered.getId();
			List<Map<String,Object>> doctotList = teacherBiz.getDoctorList(userFlow, schStatusId, null, null, 1 , Integer.MAX_VALUE);
			dataCount = dataCount + doctotList.size();

			List<Map<String,Object>> waitEvalOutSecBriefList = teacherBiz.getWaitEvalOutSecBriefList(userFlow);
			dataCount = dataCount + waitEvalOutSecBriefList.size();

			Map<String,Object> waitArrangeActivityCount = teacherBiz.getWaitArrangeActivityCount(userFlow);
			if((Integer)waitArrangeActivityCount.get("arrangeCount")>0){
				dataCount = dataCount + 1;
			}
		}

		model.addAttribute("dataCount", dataCount);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
}

