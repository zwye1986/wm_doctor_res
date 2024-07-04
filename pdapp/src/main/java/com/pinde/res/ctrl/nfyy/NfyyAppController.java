package com.pinde.res.ctrl.nfyy;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinde.core.util.CodeUtil;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pinde.app.common.NfyyGlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.nfyy.INfyyAppBiz;
import com.pinde.res.biz.nfyy.INfyyStudentBiz;
import com.pinde.res.biz.nfyy.INfyyTeacherBiz;
import com.pinde.res.enums.nfyy.DeptStatusEnum;
import com.pinde.res.model.nfyy.mo.Activity;
import com.pinde.res.model.nfyy.mo.Evaluation;


@Controller
@RequestMapping("/res/nfyy")
public class NfyyAppController {
	
    private static Logger logger = LoggerFactory.getLogger(NfyyAppController.class);
    
    private boolean alert = true;
    
    @Autowired
    private INfyyAppBiz nfyyAppBiz;
    @Autowired
    private INfyyStudentBiz nfyyStudentBiz;
	@Autowired
	private INfyyTeacherBiz nfyyTeacherBiz;

	@Value("#{configProperties['com.pinde.net.cfg.http']}")
	private String cfg_url;

	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/nfyy/500";
    }
	
	@RequestMapping(value={"/version"},method={RequestMethod.GET})
	public String version(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return "res/nfyy/version";
	}

	public String getCfgValue(String cfgCode) throws DocumentException {
		if(StringUtil.isBlank(cfg_url))
		{
			return "未配置住培系统配置文件地址";
		}

		URL urlfile = null;
		HttpURLConnection httpUrl = null;
		BufferedInputStream bis = null;
		StringBuffer resultBuffer = new StringBuffer();
		BufferedReader reader = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		String tempLine = null;
		String url = cfg_url;
		String xml = "";
		try {
			urlfile = new URL(url);
			httpUrl = (HttpURLConnection) urlfile.openConnection();
			httpUrl.connect();
			inputStream = httpUrl.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream);
			reader = new BufferedReader(inputStreamReader);
			resultBuffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			resultBuffer.append("<appSettings>");
			while ((tempLine = reader.readLine()) != null) {
				if(tempLine.indexOf("<add")>=0)
				{
					resultBuffer.append(tempLine);
					resultBuffer.append("\n");
				}
			}
			resultBuffer.append("</appSettings>");
			xml = resultBuffer.toString();
		} catch (Exception e) {
			return "住培系统配置文件地址配置错误";
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (bis != null) {
					bis.close();
				}
				httpUrl.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if(StringUtil.isNotBlank(xml))
		{
			Element oldRootEle =  DocumentHelper.parseText(xml).getRootElement();
			List<Element> elements=oldRootEle.elements();
			if(elements!=null)
			{
				for(Element e:elements)
				{
					String key=e.attribute("key").getValue();
					String v=e.attribute("value").getValue();
					if(key.equals(cfgCode))
					{
						return v;
					}
				}
			}
		}
		return "";
	}
	@RequestMapping(value={"/login"},method={RequestMethod.POST})
	public String login(String userCode,String userPasswd , Model model) throws DocumentException {
		String result = "res/nfyy/login";
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
		String v=getCfgValue("DefaultPwd");
		if("未配置住培系统配置文件地址".equals(v)||"住培系统配置文件地址配置错误".equals(v))
		{

			model.addAttribute("resultId", "2020202");
			model.addAttribute("resultType", v);
			return result;
		}
		//login
		Map<String,String> user = nfyyAppBiz.login(userCode, userPasswd);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERCODEORPASSWDERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERCODEORPASSWDERROR);
			return result;
		}
		String needChange="N";
		if(StringUtil.isNotBlank(v))
		{
			String newPassword = _md5Dnet(v, userCode);
			if(StringUtil.isEquals(newPassword, user.get("password")))
			{
				needChange="Y";
			}
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		model.addAttribute("user" , user);
		model.addAttribute("needChange" , needChange);
		return result;
	}


	@RequestMapping(value={"/changePass"},method={RequestMethod.POST})
	public String changePass(String userFlow,String userPasswd ,String newUserPasswd,String reNewUserPasswd , Model model){
		String result = "res/nfyy/success";
		model.addAttribute("resultId","200");
		model.addAttribute("resultType", "修改成功，请重新登录！");
		if(StringUtil.isBlank(userFlow))
		{
			model.addAttribute("resultId","1501");
			model.addAttribute("resultType", "用户标识符为空！");
			return result;
		}
		if(StringUtil.isBlank(userPasswd))
		{
			model.addAttribute("resultId","1502");
			model.addAttribute("resultType", "密码不能为空！");
			return result;
		}
		if(StringUtil.isBlank(newUserPasswd))
		{
			model.addAttribute("resultId","1503");
			model.addAttribute("resultType", "新密码不能为空！");
			return result;
		}
		if(StringUtil.isBlank(reNewUserPasswd))
		{
			model.addAttribute("resultId","1504");
			model.addAttribute("resultType", "确认密码不能为空！");
			return result;
		}
		if(!checkPass(newUserPasswd))
		{
			model.addAttribute("resultId","1505");
			model.addAttribute("resultType", "密码填写有误，必须包含数字、大小写字母、特殊字符，长度在8－20！！");
			return result;
		}
		if(!newUserPasswd.equals(reNewUserPasswd))
		{
			model.addAttribute("resultId","1506");
			model.addAttribute("resultType", "两次密码输入不一致，请重新输入！");
			return result;
		}
		Map<String,String> user=nfyyAppBiz.readUser(userFlow);
		if(user==null){
			model.addAttribute("resultId","1507");
			model.addAttribute("resultType", "用户不存在！");
			return result;
		}
		String userCode=user.get("userCode");
		String md5Password = _md5Dnet(userPasswd, userCode);

		if(!StringUtil.isEquals(md5Password, user.get("password"))){
			model.addAttribute("resultId", "1508");
			model.addAttribute("resultType", "密码错误，请重新输入！");
			return result;
		}
		String newPassword = _md5Dnet(newUserPasswd, userCode);
		int c=nfyyAppBiz.updateUserPass(userFlow,newPassword);
		if(c==0)
		{
			model.addAttribute("resultId","1509");
			model.addAttribute("resultType", "修改失败！");
			return result;
		}
		return result;
	}

	private String _md5Dnet(String pTocrypt, String pKey) {
		String ret = "";
		byte[] data3 = new byte[80];
		try {
			pTocrypt = StringUtil.defaultString(pTocrypt).toUpperCase();
			pKey = StringUtil.defaultString(pKey).toUpperCase();
			byte[] data1 = pTocrypt.getBytes("UTF-8");
			byte[] data2 = pKey.getBytes("UTF-8");
			int d1len = 0;
			int d2len = 0;
			for (int i = 0; i < 80; i++) {
				if ((i % 2) == 0 && d1len < data1.length) {
					data3[i] = data1[d1len++];
				} else if ((i % 2) == 1 && d2len < data2.length) {
					data3[i] = data2[d2len++];
				}
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		try {
			ret = CodeUtil.md5(new String(data3,"UTF-8")).toUpperCase();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ret;
	}
	public static boolean checkPass(String str) throws PatternSyntaxException {
		String regExp = "^(?=.*[0-9].*)(?=.*[A-Z].*)(?=.*[a-z].*)(?=.*[!@#$%^|*?\\(\\)]).{8,20}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	@RequestMapping(value={"/notice"},method={RequestMethod.GET})
	public String notice(String userFlow , String roleId , Integer pageIndex , Integer pageSize , Model model){
		String result = "res/nfyy/notice";
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

		Map<String,String> user = nfyyAppBiz.readUser(userFlow);
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
			List<Map<String,Object>> schPlans = this.nfyyStudentBiz.getPlanList(userFlow, schStatusId, schDeptName , 1, Integer.MAX_VALUE,examStatusId);
			List<Map<String,Object>> schPlansToDoIn = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> schPlansToDoOut = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> outDopsList = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> outMiniCexList = new ArrayList<Map<String,Object>>();
			for(Map<String,Object> schPlan : schPlans){
				String schDeptFlow = (String)schPlan.get("schDeptFlow");
				Map<String,Object> enteredDeptEdu = nfyyStudentBiz.readEnteredDeptEdu(schDeptFlow);
				if(enteredDeptEdu==null){
					schPlansToDoIn.add(schPlan);
					dataCount++;
				}
				Evaluation evaluation = this.nfyyStudentBiz.readOutSecBrief(schDeptFlow);
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
				Map<String,Object> outDops = nfyyTeacherBiz.readOutDops(schDeptFlow);
				if(outDops!=null&&0==(Integer)outDops.get("DOPS_State")){
					outDops.put("schDeptFlow", schPlan.get("schDeptFlow"));
					outDops.put("schDeptName", schPlan.get("schDeptName"));
					outDops.put("dataFlow", outDops.get("DOPS_ID"));
					outDopsList.add(outDops);
					dataCount++;
				}
				
				//Minicex
				Map<String,Object> outMiniCex = nfyyTeacherBiz.readOutMiniCex(schDeptFlow);
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
			List<Activity> activitys = this.nfyyStudentBiz.getActicitys(params , 1 , Integer.MAX_VALUE);
			for(Activity userActivity : activitys){
				Activity activity = this.nfyyStudentBiz.readActivity(userActivity.getDataFlow());
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
			activitys = this.nfyyStudentBiz.getActicitys(params , 1 , Integer.MAX_VALUE);
			for(Activity userActivity : activitys){
				Activity activity = this.nfyyStudentBiz.readActivity(userActivity.getDataFlow());
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
			List<Map<String,Object>> notEnteredDoctotList = nfyyTeacherBiz.getDoctorList(userFlow, schStatusId, null, null, 1 , Integer.MAX_VALUE);
			model.addAttribute("notEnteredDoctotList", notEnteredDoctotList);
			dataCount = dataCount + notEnteredDoctotList.size();
			
			List<Map<String,Object>> waitEvalOutSecBriefList = nfyyTeacherBiz.getWaitEvalOutSecBriefList(userFlow);
			model.addAttribute("waitEvalOutSecBriefList", waitEvalOutSecBriefList);
			dataCount = dataCount + waitEvalOutSecBriefList.size();
			
			Map<String,Object> waitArrangeActivityCount = nfyyTeacherBiz.getWaitArrangeActivityCount(userFlow);
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
		String result = "res/nfyy/noticeCount";
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
			Map<String,String> user = nfyyAppBiz.readUser(userFlow);
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
			List<Map<String,Object>> schPlans = this.nfyyStudentBiz.getPlanList(userFlow, schStatusId, schDeptName , 1, Integer.MAX_VALUE,examStatusId);
			for(Map<String,Object> schPlan : schPlans){
				String schDeptFlow = (String)schPlan.get("schDeptFlow");
				
				Map<String,Object> enteredDeptEdu = nfyyStudentBiz.readEnteredDeptEdu(schDeptFlow);
				if(enteredDeptEdu==null){
					dataCount++;
				}
				Evaluation evaluation = this.nfyyStudentBiz.readOutSecBrief(schDeptFlow);
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
				Map<String,Object> outDops = nfyyTeacherBiz.readOutDops(schDeptFlow);
				if(outDops!=null&&0==(Integer)outDops.get("DOPS_State")){
					dataCount++;
				}
				
				//Minicex
				Map<String,Object> outMiniCex = nfyyTeacherBiz.readOutMiniCex(schDeptFlow);
				if(outMiniCex!=null&&0==(Integer)outMiniCex.get("Mini_State")){
					dataCount++;
				}
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userFlow", userFlow);
			params.put("join", "NotEntered");
			List<Activity> activitys = this.nfyyStudentBiz.getActicitys(params , 1 , Integer.MAX_VALUE);
			for(Activity userActivity : activitys){
				Activity activity = this.nfyyStudentBiz.readActivity(userActivity.getDataFlow());
				String currDateTime = DateUtil.getCurrDateTime(DateUtil.defDtPtn02);
				String startDateTime = activity.getTime();
				int sing = (int) DateUtil.signMinutesBetweenTowDate(startDateTime, currDateTime);
				if(sing>=0&&sing<=60){
					dataCount++;
				}
			}
			
			params.put("join", "NotScore");
			activitys = this.nfyyStudentBiz.getActicitys(params , 1 , Integer.MAX_VALUE);
			for(Activity userActivity : activitys){
				Activity activity = this.nfyyStudentBiz.readActivity(userActivity.getDataFlow());
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
			List<Map<String,Object>> doctotList = nfyyTeacherBiz.getDoctorList(userFlow, schStatusId, null, null, 1 , Integer.MAX_VALUE);
			dataCount = dataCount + doctotList.size();
			
			List<Map<String,Object>> waitEvalOutSecBriefList = nfyyTeacherBiz.getWaitEvalOutSecBriefList(userFlow);
			dataCount = dataCount + waitEvalOutSecBriefList.size();
			
			Map<String,Object> waitArrangeActivityCount = nfyyTeacherBiz.getWaitArrangeActivityCount(userFlow);
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
