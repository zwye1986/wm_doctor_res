package com.pinde.res.ctrl.hzyy;

import com.pinde.app.common.NfyyGlobalConstant;
import com.pinde.core.commom.enums.DeptStatusEnum;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.hzyy.IHzyyAppBiz;
import com.pinde.res.biz.hzyy.IHzyyStudentBiz;
import com.pinde.res.biz.hzyy.IHzyyTeacherBiz;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/res/hzyy")
public class HzyyAppController {
	
    private static Logger logger = LoggerFactory.getLogger(HzyyAppController.class);
    
    private boolean alert = true;
    
    @Autowired
    private IHzyyAppBiz hzyyAppBiz;
    @Autowired
    private IHzyyStudentBiz hzyyStudentBiz;
	@Autowired
	private IHzyyTeacherBiz hzyyTeacherBiz;
	
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/hzyy/500";
    }
	
	@RequestMapping(value={"/version"},method={RequestMethod.GET})
	public String version(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return "res/hzyy/version";
	}
	
	@RequestMapping(value={"/login"},method={RequestMethod.POST})
	public String login(String userCode,String userPasswd , Model model){
		String result = "res/hzyy/login";
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
		Map<String,String> user = hzyyAppBiz.login(userCode, userPasswd);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERCODEORPASSWDERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERCODEORPASSWDERROR);
			return result;
		}//Teacher Secretary Header
		List<String> allowRole=new ArrayList<>();
		allowRole.add("Teacher");
		allowRole.add("Secretary");
		allowRole.add("Header");
		allowRole.add("Trainee");
		allowRole.add("Tutor");
		if(!allowRole.contains(user.get("roleId")))
		{
			model.addAttribute("resultId", "11111");
			model.addAttribute("resultType", "您无权限使用");
			return result;
		}
		String userFlow=user.get("userFlow");
		List<Map<String,String>> roles=hzyyAppBiz.readUserRoles(userFlow);
		model.addAttribute("roles", roles);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		model.addAttribute("user" , user);
		return result;
	}
	
	
	@RequestMapping(value={"/notice"},method={RequestMethod.GET})
	public String notice(String userFlow , String roleId , Integer pageIndex , Integer pageSize , Model model){
		String result = "res/hzyy/notice";
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

		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
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
			List<Map<String,Object>> schPlans = this.hzyyStudentBiz.getPlanList(userFlow, schStatusId, schDeptName , 1, Integer.MAX_VALUE,examStatusId);
			List<Map<String,Object>> schPlansToDoIn = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> schPlansToDoOut = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> outDopsList = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> outMiniCexList = new ArrayList<Map<String,Object>>();
			for(Map<String,Object> schPlan : schPlans){
				String schDeptFlow = (String)schPlan.get("schDeptFlow");
				Map<String,Object> enteredDeptEdu = hzyyStudentBiz.readEnteredDeptEdu(schDeptFlow);
				if(enteredDeptEdu==null){
					schPlansToDoIn.add(schPlan);
					dataCount++;
				}
				Evaluation evaluation = this.hzyyStudentBiz.readOutSecBrief(schDeptFlow);
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
				Map<String,Object> outDops = hzyyTeacherBiz.readOutDops(schDeptFlow);
				if(outDops!=null&&0==(Integer)outDops.get("DOPS_State")){
					outDops.put("schDeptFlow", schPlan.get("schDeptFlow"));
					outDops.put("schDeptName", schPlan.get("schDeptName"));
					outDops.put("dataFlow", outDops.get("DOPS_ID"));
					outDopsList.add(outDops);
					dataCount++;
				}
				
				//Minicex
				Map<String,Object> outMiniCex = hzyyTeacherBiz.readOutMiniCex(schDeptFlow);
				if(outMiniCex!=null&&0==(Integer)outMiniCex.get("Mini_State")){
					outMiniCex.put("schDeptFlow", schPlan.get("schDeptFlow"));
					outMiniCex.put("schDeptName", schPlan.get("schDeptName"));
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
			List<Activity> activitys = this.hzyyStudentBiz.getActicitys(params , 1 , Integer.MAX_VALUE);
			for(Activity userActivity : activitys){
				Activity activity = this.hzyyStudentBiz.readActivity(userActivity.getDataFlow());
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
			activitys = this.hzyyStudentBiz.getActicitys(params , 1 , Integer.MAX_VALUE);
			for(Activity userActivity : activitys){
				Activity activity = this.hzyyStudentBiz.readActivity(userActivity.getDataFlow());
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
			List<Map<String,Object>> notEnteredDoctotList = hzyyTeacherBiz.getDoctorList(userFlow, schStatusId, null, null, 1 , Integer.MAX_VALUE);
			model.addAttribute("notEnteredDoctotList", notEnteredDoctotList);
			dataCount = dataCount + notEnteredDoctotList.size();
			
			List<Map<String,Object>> waitEvalOutSecBriefList = hzyyTeacherBiz.getWaitEvalOutSecBriefList(userFlow);
			model.addAttribute("waitEvalOutSecBriefList", waitEvalOutSecBriefList);
			dataCount = dataCount + waitEvalOutSecBriefList.size();
			
			Map<String,Object> waitArrangeActivityCount = hzyyTeacherBiz.getWaitArrangeActivityCount(userFlow);
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
		String result = "res/hzyy/noticeCount";
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
			Map<String,String> user = hzyyAppBiz.readUser(userFlow);
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
			List<Map<String,Object>> schPlans = this.hzyyStudentBiz.getPlanList(userFlow, schStatusId, schDeptName , 1, Integer.MAX_VALUE,examStatusId);
			for(Map<String,Object> schPlan : schPlans){
				String schDeptFlow = (String)schPlan.get("schDeptFlow");
				
				Map<String,Object> enteredDeptEdu = hzyyStudentBiz.readEnteredDeptEdu(schDeptFlow);
				if(enteredDeptEdu==null){
					dataCount++;
				}
				Evaluation evaluation = this.hzyyStudentBiz.readOutSecBrief(schDeptFlow);
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
				Map<String,Object> outDops = hzyyTeacherBiz.readOutDops(schDeptFlow);
				if(outDops!=null&&0==(Integer)outDops.get("DOPS_State")){
					dataCount++;
				}
				
				//Minicex
				Map<String,Object> outMiniCex = hzyyTeacherBiz.readOutMiniCex(schDeptFlow);
				if(outMiniCex!=null&&0==(Integer)outMiniCex.get("Mini_State")){
					dataCount++;
				}
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userFlow", userFlow);
			params.put("join", "NotEntered");
			List<Activity> activitys = this.hzyyStudentBiz.getActicitys(params , 1 , Integer.MAX_VALUE);
			for(Activity userActivity : activitys){
				Activity activity = this.hzyyStudentBiz.readActivity(userActivity.getDataFlow());
				String currDateTime = DateUtil.getCurrDateTime(DateUtil.defDtPtn02);
				String startDateTime = activity.getTime();
				int sing = (int) DateUtil.signMinutesBetweenTowDate(startDateTime, currDateTime);
				if(sing>=0&&sing<=60){
					dataCount++;
				}
			}
			
			params.put("join", "NotScore");
			activitys = this.hzyyStudentBiz.getActicitys(params , 1 , Integer.MAX_VALUE);
			for(Activity userActivity : activitys){
				Activity activity = this.hzyyStudentBiz.readActivity(userActivity.getDataFlow());
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
			List<Map<String,Object>> doctotList = hzyyTeacherBiz.getDoctorList(userFlow, schStatusId, null, null, 1 , Integer.MAX_VALUE);
			dataCount = dataCount + doctotList.size();
			
			List<Map<String,Object>> waitEvalOutSecBriefList = hzyyTeacherBiz.getWaitEvalOutSecBriefList(userFlow);
			dataCount = dataCount + waitEvalOutSecBriefList.size();
			
			Map<String,Object> waitArrangeActivityCount = hzyyTeacherBiz.getWaitArrangeActivityCount(userFlow);
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
