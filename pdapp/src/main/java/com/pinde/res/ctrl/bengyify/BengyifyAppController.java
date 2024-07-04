package com.pinde.res.ctrl.bengyify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pinde.app.common.BengyifyGlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.bengyify.IBengyifyAppBiz;
import com.pinde.res.biz.bengyify.IBengyifyStudentBiz;
import com.pinde.res.biz.bengyify.IBengyifyTeacherBiz;
import com.pinde.res.enums.bengyify.DeptStatusEnum;
import com.pinde.res.model.bengyify.mo.Activity;
import com.pinde.res.model.bengyify.mo.Evaluation;


@Controller
@RequestMapping("/res/bengyify")
public class BengyifyAppController {
	
    private static Logger logger = LoggerFactory.getLogger(BengyifyAppController.class);
    
    private boolean alert = true;
    
    @Autowired
    private IBengyifyAppBiz bengyifyAppBiz;
    @Autowired
    private IBengyifyStudentBiz bengyifyStudentBiz;
	@Autowired
	private IBengyifyTeacherBiz bengyifyTeacherBiz;
	
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/bengyify/500";
    }

	@RequestMapping(value={"/version"},method={RequestMethod.GET})
	public String version(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return "res/bengyify/version";
	}

	@RequestMapping(value={"/login"},method={RequestMethod.POST})
	public String login(String userCode,String userPasswd , Model model){
		String result = "res/bengyify/login";
		if(StringUtil.isEmpty(userCode)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(userPasswd)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_PASSWD_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_PASSWD_NULL);
			return result;
		}
		//login
		Map<String,String> user = bengyifyAppBiz.login(userCode, userPasswd);
		if(user==null){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERCODEORPASSWDERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERCODEORPASSWDERROR);
			return result;
		}
		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		model.addAttribute("user" , user);
		return result;
	}


	@RequestMapping(value={"/notice"},method={RequestMethod.GET})
	public String notice(String userFlow , String roleId , Integer pageIndex , Integer pageSize , Model model){
		String result = "res/bengyify/notice";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_ROLEID_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_ROLEID_NULL);
			return result;
		}
		if(pageIndex==null){
			pageIndex = BengyifyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = BengyifyGlobalConstant.DEFAULT_PAGE_SIZE;
		}

		Map<String,String> user = bengyifyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);

		int dataCount = 0;
		if(user==null){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SERVICEERROR);
		}
		String userType = user.get("userType");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SERVICEERROR);
		}
		int userTypeInt = Integer.valueOf(userType);

		int examStatusId = userTypeInt==4?9:7;
		if(BengyifyGlobalConstant.ROLE_ID_4.equals(roleId)&&alert){
			String schStatusId = DeptStatusEnum.Entering.getId();
			String schDeptName = "";
			List<Map<String,Object>> schPlans = this.bengyifyStudentBiz.getPlanList(userFlow, schStatusId, schDeptName , 1, Integer.MAX_VALUE,examStatusId);
			List<Map<String,Object>> schPlansToDoIn = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> schPlansToDoOut = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> outDopsList = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> outMiniCexList = new ArrayList<Map<String,Object>>();
			for(Map<String,Object> schPlan : schPlans){
				String schDeptFlow = (String)schPlan.get("schDeptFlow");
				Map<String,Object> enteredDeptEdu = bengyifyStudentBiz.readEnteredDeptEdu(schDeptFlow);
				if(enteredDeptEdu==null){
					schPlansToDoIn.add(schPlan);
					dataCount++;
				}
				Evaluation evaluation = this.bengyifyStudentBiz.readOutSecBrief(schDeptFlow);
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
				Map<String,Object> outDops = bengyifyTeacherBiz.readOutDops(schDeptFlow);
				if(outDops!=null&&0==(Integer)outDops.get("DOPS_State")){
					outDops.put("schDeptFlow", schPlan.get("schDeptFlow"));
					outDops.put("schDeptName", schPlan.get("schDeptName"));
					outDops.put("dataFlow", outDops.get("DOPS_ID"));
					outDopsList.add(outDops);
					dataCount++;
				}

				//Minicex
				Map<String,Object> outMiniCex = bengyifyTeacherBiz.readOutMiniCex(schDeptFlow);
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
			List<Activity> activitys = this.bengyifyStudentBiz.getActicitys(params , 1 , Integer.MAX_VALUE);
			for(Activity userActivity : activitys){
				Activity activity = this.bengyifyStudentBiz.readActivity(userActivity.getDataFlow());
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
			activitys = this.bengyifyStudentBiz.getActicitys(params , 1 , Integer.MAX_VALUE);
			for(Activity userActivity : activitys){
				Activity activity = this.bengyifyStudentBiz.readActivity(userActivity.getDataFlow());
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


		if(BengyifyGlobalConstant.ROLE_ID_6.equals(roleId)&&alert){
			String schStatusId = DeptStatusEnum.NotEntered.getId();
			List<Map<String,Object>> notEnteredDoctotList = bengyifyTeacherBiz.getDoctorList(userFlow, schStatusId, null, null, 1 , Integer.MAX_VALUE);
			model.addAttribute("notEnteredDoctotList", notEnteredDoctotList);
			dataCount = dataCount + notEnteredDoctotList.size();

			List<Map<String,Object>> waitEvalOutSecBriefList = bengyifyTeacherBiz.getWaitEvalOutSecBriefList(userFlow);
			model.addAttribute("waitEvalOutSecBriefList", waitEvalOutSecBriefList);
			dataCount = dataCount + waitEvalOutSecBriefList.size();

			Map<String,Object> waitArrangeActivityCount = bengyifyTeacherBiz.getWaitArrangeActivityCount(userFlow);
			if((Integer)waitArrangeActivityCount.get("arrangeCount")>0){
				model.addAttribute("waitArrangeActivityCount", waitArrangeActivityCount);
				dataCount = dataCount + 1;
			}
		}

		model.addAttribute("dataCount", dataCount);
		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/noticeCount"},method={RequestMethod.GET})
	public String noticeCount(String userFlow , String roleId , Model model){
		String result = "res/bengyify/noticeCount";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_ROLEID_NULL);
			model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_ROLEID_NULL);
			return result;
		}
		int dataCount = 0;
		if(BengyifyGlobalConstant.ROLE_ID_4.equals(roleId)&&alert){
			Map<String,String> user = bengyifyAppBiz.readUser(userFlow);
			model.addAttribute("user" , user);

			if(user==null){
				model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SERVICEERROR);
				model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SERVICEERROR);
			}
			String userType = user.get("userType");
			if(StringUtil.isBlank(userType)){
				model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SERVICEERROR);
				model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SERVICEERROR);
			}
			int userTypeInt = Integer.valueOf(userType);

			int examStatusId = userTypeInt==4?9:7;

			String schStatusId = DeptStatusEnum.Entering.getId();
			String schDeptName = "";
			List<Map<String,Object>> schPlans = this.bengyifyStudentBiz.getPlanList(userFlow, schStatusId, schDeptName , 1, Integer.MAX_VALUE,examStatusId);
			for(Map<String,Object> schPlan : schPlans){
				String schDeptFlow = (String)schPlan.get("schDeptFlow");
				
				Map<String,Object> enteredDeptEdu = bengyifyStudentBiz.readEnteredDeptEdu(schDeptFlow);
				if(enteredDeptEdu==null){
					dataCount++;
				}
				Evaluation evaluation = this.bengyifyStudentBiz.readOutSecBrief(schDeptFlow);
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
				Map<String,Object> outDops = bengyifyTeacherBiz.readOutDops(schDeptFlow);
				if(outDops!=null&&0==(Integer)outDops.get("DOPS_State")){
					dataCount++;
				}
				
				//Minicex
				Map<String,Object> outMiniCex = bengyifyTeacherBiz.readOutMiniCex(schDeptFlow);
				if(outMiniCex!=null&&0==(Integer)outMiniCex.get("Mini_State")){
					dataCount++;
				}
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userFlow", userFlow);
			params.put("join", "NotEntered");
			List<Activity> activitys = this.bengyifyStudentBiz.getActicitys(params , 1 , Integer.MAX_VALUE);
			for(Activity userActivity : activitys){
				Activity activity = this.bengyifyStudentBiz.readActivity(userActivity.getDataFlow());
				String currDateTime = DateUtil.getCurrDateTime(DateUtil.defDtPtn02);
				String startDateTime = activity.getTime();
				int sing = (int) DateUtil.signMinutesBetweenTowDate(startDateTime, currDateTime);
				if(sing>=0&&sing<=60){
					dataCount++;
				}
			}
			
			params.put("join", "NotScore");
			activitys = this.bengyifyStudentBiz.getActicitys(params , 1 , Integer.MAX_VALUE);
			for(Activity userActivity : activitys){
				Activity activity = this.bengyifyStudentBiz.readActivity(userActivity.getDataFlow());
				String endDateTime = activity.getEndTime();
			    String currDateTime = DateUtil.getCurrDateTime(DateUtil.defDtPtn02);
			    int sing = (int) DateUtil.signMinutesBetweenTowDate(currDateTime, endDateTime);
				if(sing>=0&&sing<=60){
					dataCount++;
				}
			}
		}

		if(BengyifyGlobalConstant.ROLE_ID_6.equals(roleId)&&alert){
			String schStatusId = DeptStatusEnum.NotEntered.getId();
			List<Map<String,Object>> doctotList = bengyifyTeacherBiz.getDoctorList(userFlow, schStatusId, null, null, 1 , Integer.MAX_VALUE);
			dataCount = dataCount + doctotList.size();
			
			List<Map<String,Object>> waitEvalOutSecBriefList = bengyifyTeacherBiz.getWaitEvalOutSecBriefList(userFlow);
			dataCount = dataCount + waitEvalOutSecBriefList.size();
			
			Map<String,Object> waitArrangeActivityCount = bengyifyTeacherBiz.getWaitArrangeActivityCount(userFlow);
			if((Integer)waitArrangeActivityCount.get("arrangeCount")>0){
				dataCount = dataCount + 1;
			}
		}
		
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("resultId", BengyifyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", BengyifyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

}
