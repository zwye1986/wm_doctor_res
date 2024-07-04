package com.pinde.res.ctrl.hzyy;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.NfyyGlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.hzyy.IHzyyAppBiz;
import com.pinde.res.biz.hzyy.IHzyySecretarieBiz;
import com.pinde.res.biz.hzyy.IHzyyTeacherBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/res/hzyy/secretarie")
public class HzyySecretarieController {
	
    private static Logger logger = LoggerFactory.getLogger(HzyySecretarieController.class);
    
    private boolean alert = true;

	@Value("#{configProperties['httpurl']}")
	private String httpurl;

    @Autowired
    private IHzyyAppBiz hzyyAppBiz;
	@Autowired
	private IHzyySecretarieBiz hzyySecretarieBiz;

	@Autowired
	private IHzyyTeacherBiz hzyyTeacherBiz;

	@Value("#{configProperties['hzyy.upload.base.dir']}")
	public  String baseDir;
	@Value("#{configProperties['hzyy.upload.dir']}")
	public  String uploadDir;
	@Value("#{configProperties['hzyy.upload.base.url']}")
	public  String baseUrl;
	@Value("#{configProperties['hzyy.upload.all.suffix']}")
	public  String allFileSuf;
	@Value("#{configProperties['hzyy.upload.img.suffix']}")
	public  String imgSuf;
	@Value("#{configProperties['hzyy.upload.file.suffix']}")
	public  String fileSuf;
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "/res/hzyy/500";
    }
	//首页接口
	@RequestMapping(value={"/index"},method={RequestMethod.GET})
	public String index(String userFlow , String roleId ,String indexType, Model model){
		String result = "/res/hzyy/secretarie/index";
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
		if(StringUtil.isBlank(indexType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_INDEX_TYPE_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_INDEX_TYPE_NULL);
			return result;
		}

		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		List<String> hosSecIds=hzyySecretarieBiz.getHosSecId(userFlow);
		if(hosSecIds==null||hosSecIds.size()<=0)
		{	model.addAttribute("resultId", "300210");
			model.addAttribute("resultType", "用户无所在科室");
		    return result;
		}
		if("inProcess".equals(indexType))//入科
		{
			//参数
//			--HosID 医院ID
//			--StartTime 开始时间 默认‘1900-01-01’
//			--EndTime 结束时间 默认当前时间
//			--HosSecID 秘书所在科室
			Map<String,Object> param=new HashMap<>();
			param.put("HosID",user.get("hosID"));
			param.put("StartTime","1900-01-01");
			param.put("EndTime",DateUtil.getCurrDate());
			param.put("HosSecID",hosSecIds);
			//统计人员
//			Map<String,Object> map=hzyySecretarieBiz.InProcessCount(param);
//			model.addAttribute("inProcessCount",map);

			//统计人员
			Map<String,Object> map2=hzyySecretarieBiz.InProcessCount2(param);
			model.addAttribute("inProcessCount",map2);

		}else if("outProcess".equals(indexType))//出科
		{
			Map<String,Object> map=hzyySecretarieBiz.OutProcessCount(userFlow);
			model.addAttribute("outProcessCount",map);
		}else if("teachPlan".equals(indexType))//计划安排
		{

		}else if("teachAction".equals(indexType))//计划审核
		{

		}else{
			model.addAttribute("resultId", "3201011");
			model.addAttribute("resultType", "查询类型错误");
			return result;
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	//首页学员列表接口
	@RequestMapping(value={"/studentList"},method={RequestMethod.POST})
	public String studentList(String userFlow , String roleId , Integer pageIndex , Integer pageSize ,String indexType,String inProcessType,String searhStr, Model model){
		String result = "/res/hzyy/secretarie/studentList";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
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
		if(StringUtil.isBlank(indexType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_INDEX_TYPE_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_INDEX_TYPE_NULL);
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

		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		List<String> hosSecIds=hzyySecretarieBiz.getHosSecId(userFlow);
		if(hosSecIds==null||hosSecIds.size()<=0)
		{	model.addAttribute("resultId", "300210");
			model.addAttribute("resultType", "用户无所在科室");
		    return result;
		}

		int dataCount = 0;
		if("inProcess".equals(indexType))//入科
		{
			//参数
//			--HosID 医院ID
//			--StartTime 开始时间 默认‘1900-01-01’
//			--EndTime 结束时间 默认当前时间
//			--HosSecID 秘书所在科室
			if(StringUtil.isBlank(inProcessType))
			{
				model.addAttribute("resultId", "300210");
				model.addAttribute("resultType", "入科学员列表类型为空");
				return result;
			}
			if(!"Processing".equals(inProcessType)&&!"NotProcess".equals(inProcessType))
			{
				model.addAttribute("resultId", "300210");
				model.addAttribute("resultType", "入科学员列表类型输入错误");
				return result;
			}
			Map<String,Object> param=new HashMap<>();
			param.put("HosID",user.get("hosID"));
			param.put("StartTime","1900-01-01");
			param.put("EndTime",DateUtil.getCurrDate());
			param.put("HosSecID",hosSecIds);
			param.put("searhStr",searhStr);
			param.put("inProcessType",inProcessType);
			//统计人员
			Map<String,Object> map=hzyySecretarieBiz.InProcessCount(param);
			model.addAttribute("inProcessCount",map);
			List<Map<String,Object>> studentList = hzyySecretarieBiz.getInProcessStuList(param,pageIndex,pageSize);
			model.addAttribute("studentList" , studentList);
			List<Map<String,Object>> all =hzyySecretarieBiz.getInProcessStuList(param,1,Integer.MAX_VALUE);
			if(all!=null)
				dataCount=all.size();

		}else if("outProcess".equals(indexType))//出科考核学员列表
		{
			Map<String,Object> param=new HashMap<>();
			param.put("HosID",user.get("hosID"));
			param.put("StartTime","1900-01-01");
			param.put("EndTime",DateUtil.getCurrDate());
			param.put("HosSecID",hosSecIds);
			param.put("userFlow",userFlow);
			param.put("searhStr",searhStr);
			List<Map<String,Object>> studentList = hzyySecretarieBiz.getOutProcessStuList(param,pageIndex,pageSize);
			model.addAttribute("studentList" , studentList);
			List<Map<String,Object>> all =hzyySecretarieBiz.getOutProcessStuList(param,1,Integer.MAX_VALUE);
			if(all!=null)
				dataCount=all.size();
		}else if("teachPlan".equals(indexType))
		{
			Map<String,Object> param=new HashMap<>();
			param.put("HosID",user.get("hosID"));
//			param.put("StartTime","1900-01-01");
//			param.put("EndTime",DateUtil.getCurrDate());
			param.put("HosSecID",hosSecIds);
			param.put("userFlow",userFlow);
			param.put("searhStr",searhStr);
			List<Map<String,Object>> studentList = hzyySecretarieBiz.getTeachPlanList(param,pageIndex,pageSize);
			model.addAttribute("studentList" , studentList);
			List<Map<String,Object>> all =hzyySecretarieBiz.getTeachPlanList(param,1,Integer.MAX_VALUE);
			if(all!=null)
				dataCount=all.size();
		}else if("teachAction".equals(indexType))
		{

		}else{
			model.addAttribute("resultId", "3201011");
			model.addAttribute("resultType", "查询类型错误");
			return result;
		}
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	/**
	 * 教学计划详情
	 * @param userFlow
	 * @param roleId
	 * @param pageIndex
	 * @param pageSize
	 * @param searhStr
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/planDetail"},method={RequestMethod.POST})
	public String planDetail(String userFlow , String CaDisID, Model model){
		String result = "/res/hzyy/secretarie/planDetail";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);

		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(CaDisID)){
			model.addAttribute("resultId","21144334");
			model.addAttribute("resultType", "数据流水号为空");
			return result;
		}
		Map<String,Object> all =hzyySecretarieBiz.getTeachPlanDetail(CaDisID);
		model.addAttribute("detail",all);
		return result;
	}
	@RequestMapping(value={"/activityDetail"},method={RequestMethod.POST})
	public String activityDetail(String userFlow , String CaDisID, Model model){
		String result = "/res/hzyy/secretarie/activityDetail";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);

		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(CaDisID)){
			model.addAttribute("resultId","21144334");
			model.addAttribute("resultType", "数据流水号为空");
			return result;
		}
		Map<String,Object> all =hzyySecretarieBiz.getActivitydetail(CaDisID);
		model.addAttribute("detail",all);
		return result;
	}
	/**
	 * 新增或编辑教学计划
	 * @param userFlow
	 * @param CaDisID
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/addTeachPlan"},method={RequestMethod.POST})
	public String addTeachPlan(String userFlow , String CaDisID, Model model){
		String result = "/res/hzyy/secretarie/addTeachPlan";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);

		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		List<Map<String,Object>> dicts=hzyySecretarieBiz.getActivtyDicts();
		model.addAttribute("dicts",dicts);

		if(StringUtil.isNotBlank(CaDisID)) {//编辑
			Map<String, Object> detail = hzyySecretarieBiz.getTeachPlanDetail(CaDisID);
			model.addAttribute("detail",detail);
			if(detail!=null&&StringUtil.isNotBlank(String.valueOf(detail.get("TecID"))))
			{
				Map<String,String> u=hzyyAppBiz.readUser(String.valueOf(detail.get("TecID")));
				List<Map<String,String>> users=new ArrayList<>();
				users.add(u);
				model.addAttribute("users",users);
				String HosSecId="0";
				List<Map<String,Object>> us=hzyySecretarieBiz.getThisTeachers(userFlow,1,Integer.MAX_VALUE,String.valueOf(detail.get("TecID")));
				if(us==null||us.size()<=0) {
					us = hzyySecretarieBiz.getNotThisTeachers(userFlow, 1, Integer.MAX_VALUE, String.valueOf(detail.get("TecID")));
					if(us!=null&&us.size()>0)
					{
						HosSecId="1";
					}
				}
				model.addAttribute("HosSecId",HosSecId);
			}
		}else{
			//默认本科室带教老师
			List<Map<String,Object>> users=hzyySecretarieBiz.getThisTeachers(userFlow,1,10, "");
			model.addAttribute("users",users);
		}
		return result;
	}

	/**
	 * 删除
	 * @param userFlow
	 * @param CaDisID
	 * @param model
     * @return
     */
	@RequestMapping(value={"/delTeachPlan"},method={RequestMethod.POST})
	public String delTeachPlan(String userFlow , String CaDisID, Model model){
		String result = "/res/hzyy/secretarie/addTeachPlan";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(CaDisID)){
			model.addAttribute("resultId", "12452125");
			model.addAttribute("resultType","教学计划主键为空");
			return result;
		}
		int c=hzyySecretarieBiz.delTeachPlan(CaDisID);
		if(c==0)
		{
			model.addAttribute("resultId", "12452125");
			model.addAttribute("resultType","删除失败");
		}
		return result;
	}

	/**
	 * 保存活动
	 * @param userFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/saveTeachPlan"},method={RequestMethod.POST})
	public String saveTeachPlan(String userFlow ,String CaDisID,
								String CaDisMainSpeakerTypeId ,
								String HosSecId ,
								String TecID ,
								String CaDisPlayClass,
								String CaAddress,
								String CaPeople,
								String CaDisTime,
								String CaEndDisTime, Model model){
		String result = "/res/hzyy/secretarie/success";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(CaDisMainSpeakerTypeId)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "主讲人类型为空");
			return result;
		}
		if(StringUtil.isBlank(HosSecId)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "科室为空");
			return result;
		}
		if(StringUtil.isBlank(TecID)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "主讲人为空");
			return result;
		}
		if(StringUtil.isBlank(CaDisPlayClass)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "活动形式为空");
			return result;
		}
		if(StringUtil.isBlank(CaAddress)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "地点为空");
			return result;
		}
		if(StringUtil.isBlank(CaPeople)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "联系方式为空");
			return result;
		}
		if(StringUtil.isBlank(CaDisTime)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "开始时间为空");
			return result;
		}
		if(StringUtil.isBlank(CaEndDisTime)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "结束时间为空");
			return result;
		}
		if(!"0".equals(CaDisMainSpeakerTypeId)&&!"1".equals(CaDisMainSpeakerTypeId))
		{
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "主讲人类型错误");
			return result;
		}
		if(StringUtil.isNotBlank(CaDisID))
		{
			Map<String, Object> detail = hzyySecretarieBiz.getTeachPlanDetail(CaDisID);
			if(detail!=null&&!"0".equals(String.valueOf(detail.get("CheckStatus"))))
			{
				model.addAttribute("resultId", "3232302");
				model.addAttribute("resultType", "活动状态已变更，无法保存");
				return result;
			}
		}
		Map<String,String> u=hzyyAppBiz.readUser(TecID);
		String HosSecID="";
		if("0".equals(CaDisMainSpeakerTypeId))
		{
			HosSecID=String.valueOf(u.get("szks"));
		}
		if("1".equals(CaDisMainSpeakerTypeId))
		{
			Map<String,String> doc=hzyySecretarieBiz.docHosSecID(TecID);
			HosSecID=String.valueOf(doc.get("HosSecID"));
		}
		Map<String, String> param = new HashMap<>();
		param.put("CaDisMainSpeakerType", CaDisMainSpeakerTypeId);
		param.put("CaDisMainSpeaker", u.get("userName"));
		param.put("TecID", TecID);
		param.put("CaDisPlayClass", CaDisPlayClass);
		param.put("CaAddress", CaAddress);
		param.put("SecretaryID", userFlow);
		param.put("CaPeople", CaPeople);
		param.put("CaDisTime", CaDisTime);
		param.put("CaEndDisTime", CaEndDisTime);
		param.put("CaDisID", CaDisID);
		param.put("HosSecID", HosSecID);
		if(StringUtil.isBlank(CaDisID))
		{
		  int c=hzyySecretarieBiz.addTeachPlan(param);
		  if(c==0)
		  {
			  model.addAttribute("resultId", "3232302");
			  model.addAttribute("resultType", "保存失败");
		  }
		}else{
		  int c=hzyySecretarieBiz.updateTeachPlan(param);
			if(c==0)
			{
				model.addAttribute("resultId", "3232302");
				model.addAttribute("resultType", "保存失败");
			}
		}
		return result;
	}
	/**
	 * 保存活动评分
	 * @param userFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/saveActivityEval"},method={RequestMethod.POST})
	public String saveActivityEval(String userFlow ,String CaDisID,	String UmpireScore, Model model){
		String result = "/res/hzyy/secretarie/success";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(UmpireScore)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "评分为空");
			return result;
		}

		Map<String, String> param = new HashMap<>();;
		param.put("Umpire", userFlow);
		param.put("CaDisID", CaDisID);
		param.put("UmpireScore", UmpireScore);
		  int c=hzyySecretarieBiz.saveActivityEval(param);
		  if(c==0)
		  {
			  model.addAttribute("resultId", "3232302");
			  model.addAttribute("resultType", "保存失败");
		  }
		return result;
	}
	/**
	 * 本科室老师列表
	 * @param userFlow
	 * @param roleId
	 * @param pageIndex
	 * @param pageSize
	 * @param searhStr
     * @param model
     * @return
     */
	@RequestMapping(value={"/getThisTeachers"},method={RequestMethod.POST})
	public String getThisTeachers(String userFlow , String roleId , Integer pageIndex , Integer pageSize,String searhStr, Model model){
		String result = "/res/hzyy/secretarie/getThisTeachers";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
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

		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		int dataCount = 0;//默认本科室带教老师
		List<Map<String,Object>> users=hzyySecretarieBiz.getThisTeachers(userFlow,pageIndex,pageSize,"" );
		model.addAttribute("users",users);
		List<Map<String,Object>> all =hzyySecretarieBiz.getThisTeachers(userFlow,1,Integer.MAX_VALUE, "");
		if(all!=null)
				dataCount=all.size();
		model.addAttribute("dataCount", dataCount);
		return result;
	}
	/**
	 * 非本科室老师列表
	 * @param userFlow
	 * @param roleId
	 * @param pageIndex
	 * @param pageSize
	 * @param searhStr
     * @param model
     * @return
     */
	@RequestMapping(value={"/getNotThisTeachers"},method={RequestMethod.POST})
	public String getNotThisTeachers(String userFlow , String roleId , Integer pageIndex , Integer pageSize,String searhStr, Model model){
		String result = "/res/hzyy/secretarie/getThisTeachers";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
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

		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		int dataCount = 0;//默认本科室带教老师
		List<Map<String,Object>> users=hzyySecretarieBiz.getNotThisTeachers(userFlow,pageIndex,pageSize, "");
		model.addAttribute("users",users);
		List<Map<String,Object>> all =hzyySecretarieBiz.getNotThisTeachers(userFlow,1,Integer.MAX_VALUE, "");
		if(all!=null)
				dataCount=all.size();
		model.addAttribute("dataCount", dataCount);
		return result;
	}
	/**
	 * 培训学员列表
	 * @param userFlow
	 * @param roleId
	 * @param pageIndex
	 * @param pageSize
	 * @param searhStr
     * @param model
     * @return
     */
	@RequestMapping(value={"/getDoctors"},method={RequestMethod.POST})
	public String getDoctors(String userFlow , String roleId , Integer pageIndex , Integer pageSize,String searhStr, Model model){
		String result = "/res/hzyy/secretarie/getThisTeachers";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
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

		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		int dataCount = 0;
		List<Map<String,Object>> users=hzyySecretarieBiz.getDoctors(userFlow,pageIndex,pageSize);
		model.addAttribute("users",users);
		List<Map<String,Object>> all =hzyySecretarieBiz.getDoctors(userFlow,1,Integer.MAX_VALUE);
		if(all!=null)
				dataCount=all.size();
		model.addAttribute("dataCount", dataCount);
		return result;
	}
	/**
	 * 教学活动评分列表
	 * @param userFlow
	 * @param roleId
	 * @param pageIndex
	 * @param pageSize
	 * @param searhStr
     * @param model
     * @return
     */
	@RequestMapping(value={"/activityList"},method={RequestMethod.POST})
	public String activityList(String userFlow , String roleId , Integer pageIndex , Integer pageSize,String searhStr, Model model){
		String result = "/res/hzyy/secretarie/activityList";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
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

		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		List<String> hosSecIds=hzyySecretarieBiz.getHosSecId(userFlow);
		if(hosSecIds==null||hosSecIds.size()<=0)
		{	model.addAttribute("resultId", "300210");
			model.addAttribute("resultType", "用户无所在科室");
		    return result;
		}
		int dataCount = 0;
			Map<String,Object> param=new HashMap<>();
			param.put("HosID",user.get("hosID"));
			param.put("HosSecID",hosSecIds);
			param.put("StartTime",DateUtil.addDate(DateUtil.getCurrDate(),-7)+" 00:00:00");
			param.put("EndTime",DateUtil.getCurrDate()+" 23:59:59");
			param.put("userFlow",userFlow);
			param.put("searhStr",searhStr);
			List<Map<String,Object>> studentList = hzyySecretarieBiz.getActivityList(param,pageIndex,pageSize);
			model.addAttribute("studentList" , studentList);
			List<Map<String,Object>> all =hzyySecretarieBiz.getActivityList(param,1,Integer.MAX_VALUE);
			if(all!=null)
				dataCount=all.size();
		model.addAttribute("dataCount", dataCount);
		return result;
	}

	/**
	 *
	 * @param userFlow
	 * @param CaDisID
	 * @param model
     * @return
     */
	@RequestMapping(value={"/activityStuList"},method={RequestMethod.POST})
	public String activityStuList(String userFlow , String CaDisID, Integer pageIndex , Integer pageSize,String searhStr, Model model){
		String result = "/res/hzyy/secretarie/activityStuList";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);

		if(pageIndex==null){
			pageIndex = NfyyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = NfyyGlobalConstant.DEFAULT_PAGE_SIZE;
		}

		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(CaDisID)){
			model.addAttribute("resultId","21144334");
			model.addAttribute("resultType", "数据流水号为空");
			return result;
		}int dataCount = 0;
		Map<String,Object> param=new HashMap<>();
		param.put("userFlow",userFlow);
		param.put("caDisID",CaDisID);
		param.put("searhStr",searhStr);
		List<Map<String,Object>> datas = hzyySecretarieBiz.activityStuList(param,pageIndex,pageSize);
		model.addAttribute("datas" , datas);
		List<Map<String,Object>> all =hzyySecretarieBiz.activityStuList(param,1,Integer.MAX_VALUE);
		if(all!=null)
			dataCount=all.size();
		model.addAttribute("dataCount", dataCount);
		return result;
	}
	/**
	 * 考勤信息列表接口
	 * @param userFlow
	 * @param roleId
	 * @param pageIndex
	 * @param pageSize
	 * @param searhStr
     * @param model
     * @return
     */
	@RequestMapping(value={"/leaveInfo"},method={RequestMethod.POST})
	public String leaveInfo(String userFlow , String roleId , Integer pageIndex , Integer pageSize ,String searhStr, Model model){
		String result = "/res/hzyy/secretarie/leaveInfo";
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

		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		List<String> hosSecIds=hzyySecretarieBiz.getHosSecId(userFlow);
		if(hosSecIds==null||hosSecIds.size()<=0)
		{	model.addAttribute("resultId", "300210");
			model.addAttribute("resultType", "用户无所在科室");
		    return result;
		}

		int dataCount = 0;

		Map<String,Object> param=new HashMap<>();
		param.put("HosID",user.get("hosID"));
		param.put("HosSecID",hosSecIds);
		param.put("userFlow",userFlow);
		param.put("searhStr",searhStr);
		List<Map<String,Object>> leaveList = hzyySecretarieBiz.getLeaveList(param,pageIndex,pageSize);
		model.addAttribute("leaveList" , leaveList);
		List<Map<String,Object>> all =hzyySecretarieBiz.getLeaveList(param,1,Integer.MAX_VALUE);
		if(all!=null)
				dataCount=all.size();
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	/**
	 *入科教育文档列表
	 * @param userFlow
	 * @param roleId
	 * @param pageIndex
	 * @param pageSize
	 * @param searhStr
     * @param model
     * @return
     */
	@RequestMapping(value={"/inProcessFileList"},method={RequestMethod.POST})
	public String inProcessFileList(String userFlow , String roleId , Integer pageIndex , Integer pageSize ,String searhStr, Model model){
		String result = "/res/hzyy/secretarie/inProcessFileList";
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

		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		List<String> hosSecIds=hzyySecretarieBiz.getHosSecId(userFlow);
		if(hosSecIds==null||hosSecIds.size()<=0)
		{	model.addAttribute("resultId", "300210");
			model.addAttribute("resultType", "用户无所在科室");
		    return result;
		}

		int dataCount = 0;

		Map<String,Object> param=new HashMap<>();
		param.put("HosID",user.get("hosID"));
		param.put("HosSecID",hosSecIds.get(0));
		param.put("userFlow",userFlow);
		param.put("searhStr",searhStr);
		List<Map<String,Object>> inProcessFileList = hzyySecretarieBiz.getInProcessFileList(param,pageIndex,pageSize);
		model.addAttribute("inProcessFileList" , inProcessFileList);
		List<Map<String,Object>> all =hzyySecretarieBiz.getInProcessFileList(param,1,Integer.MAX_VALUE);
		if(all!=null)
				dataCount=all.size();
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping(value={"/inProcessFileDetail"},method={RequestMethod.POST})
	public String inProcessFileDetail(String userFlow , String roleId , HttpServletRequest request,String readSecDocumentId, Model model){
		String result = "/res/hzyy/secretarie/inProcessFileDetail";
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
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);

		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		List<String> hosSecIds=hzyySecretarieBiz.getHosSecId(userFlow);
		if(hosSecIds==null||hosSecIds.size()<=0)
		{	model.addAttribute("resultId", "300210");
			model.addAttribute("resultType", "用户无所在科室");
		    return result;
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);

		if(StringUtil.isBlank(readSecDocumentId))
		{
			model.addAttribute("resultId", "300210");
			model.addAttribute("resultType", "入科教育标识符为空");
			return result;
		}
		if(StringUtil.isBlank(httpurl))
		{
			model.addAttribute("resultId", "300210");
			model.addAttribute("resultType", "请配置访问地址");
			return result;
		}
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath = httpRequest.getServletPath();
		String hostUrl = httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/hzyy/secretarie/showNotice/showNoticeDetail.jsp?readSecDocumentId=" + readSecDocumentId;
		String androidimgurl = httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/hzyy/secretarie/showNotice/showNoticeDetail2.jsp?readSecDocumentId=" + readSecDocumentId;
		model.addAttribute("iosDetailUrl", hostUrl);
		model.addAttribute("androidDetailUrl", androidimgurl);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping(value={"/showZPdetail"},method={RequestMethod.GET})
	@ResponseBody
	public Object showZPdetail(String userFlow , String roleId , HttpServletRequest request,String readSecDocumentId, Model model){

		Map<String,Object> data= hzyySecretarieBiz.inProcessFileDetail(readSecDocumentId);
		return JSON.toJSONString(data);
	}

	/**
	 * 住培信息列表
	 * @param userFlow
	 * @param roleId
	 * @param pageIndex
	 * @param pageSize
	 * @param searhStr
     * @param model
     * @return
     */
	@RequestMapping(value={"/zhuPeiInfoList"},method={RequestMethod.POST})
	public String zhuPeiInfoList(String userFlow , String roleId , Integer pageIndex , Integer pageSize ,String searhStr, Model model){
		String result = "/res/hzyy/secretarie/zhuPeiInfoList";
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

		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		int dataCount = 0;

		Map<String,Object> param=new HashMap<>();
		param.put("HosID",user.get("hosID"));
		param.put("userType","36");
		param.put("userFlow",userFlow);
		param.put("searhStr",searhStr);
		List<Map<String,Object>> zhuPeiInfoList = hzyySecretarieBiz.zhuPeiInfoList(param,pageIndex,pageSize);
		Map<String,String[]> cIAttachFileNameMap=new HashMap<>();
		Map<String,String[]> cIAttachNameMap=new HashMap<>();
		if(zhuPeiInfoList!=null)
		{
			for(Map<String,Object> m:zhuPeiInfoList)
			{
				String CIAttachFileName= (String) m.get("CIAttachFileName");
				String CIAttachName= (String) m.get("CIAttachName");
				if(StringUtil.isNotBlank(CIAttachFileName))
				{
					String []CIAttachFileNames=CIAttachFileName.split("@");
					cIAttachFileNameMap.put(String.valueOf(m.get("CIFlow")),CIAttachFileNames);
				}
				if(StringUtil.isNotBlank(CIAttachName))
				{
					String []CIAttachNames=CIAttachName.split("@");
					cIAttachNameMap.put(String.valueOf(m.get("CIFlow")),CIAttachNames);
				}
			}
		}
		model.addAttribute("cIAttachNameMap" , cIAttachNameMap);
		model.addAttribute("cIAttachFileNameMap" , cIAttachFileNameMap);
		model.addAttribute("zhuPeiInfoList" , zhuPeiInfoList);
		List<Map<String,Object>> all =hzyySecretarieBiz.zhuPeiInfoList(param,1,Integer.MAX_VALUE);
		if(all!=null)
			dataCount=all.size();
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/zhuPeiFileDetail"},method={RequestMethod.POST})
	public String zhuPeiFileDetail(String userFlow , String roleId ,HttpServletRequest request, String cIFlow, Model model){
		String result = "/res/hzyy/secretarie/zhuPeiFileDetail";
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
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);

		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		List<String> hosSecIds=hzyySecretarieBiz.getHosSecId(userFlow);
		if(hosSecIds==null||hosSecIds.size()<=0)
		{	model.addAttribute("resultId", "300210");
			model.addAttribute("resultType", "用户无所在科室");
			return result;
		}
		if(StringUtil.isBlank(cIFlow))
		{
			model.addAttribute("resultId", "300210");
			model.addAttribute("resultType", "住培信息标识符为空");
			return result;
		}
		Map<String, Object> data = hzyySecretarieBiz.zhuPeiFileDetail(cIFlow);
		if (data != null) {

			Map<String, String[]> cIAttachFileNameMap = new HashMap<>();
			Map<String, String[]> cIAttachNameMap = new HashMap<>();
			String CIAttachFileName = (String) data.get("CIAttachFileName");
			String CIAttachName = (String) data.get("CIAttachName");
			if (StringUtil.isNotBlank(CIAttachFileName)) {
				String[] CIAttachFileNames = CIAttachFileName.split("@");
				cIAttachFileNameMap.put(String.valueOf(data.get("CIFlow")), CIAttachFileNames);
			}
			if (StringUtil.isNotBlank(CIAttachName)) {
				String[] CIAttachNames = CIAttachName.split("@");
				cIAttachNameMap.put(String.valueOf(data.get("CIFlow")), CIAttachNames);
			}
			model.addAttribute("cIAttachNameMap", cIAttachNameMap);
			model.addAttribute("cIAttachFileNameMap", cIAttachFileNameMap);
		}
		model.addAttribute("data" , data);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}



	/**
	 * 轮转入科详情
	 * @param userFlow
     * @param model
     * @return
     */
	@RequestMapping(value={"/cycleDetail"},method={RequestMethod.POST})
	public String cycleDetail(String userFlow , String UCSID,String stuType, Model model){
		String result = "/res/hzyy/secretarie/cycleDetail";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(UCSID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","轮转标识为空");
			return result;
		}
		if(StringUtil.isBlank(stuType)){
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型为空");
			return result;
		}
		List<String> hosSecIds=hzyySecretarieBiz.getHosSecId(userFlow);
		if(hosSecIds==null||hosSecIds.size()<=0)
		{	model.addAttribute("resultId", "300210");
			model.addAttribute("resultType", "用户无所在科室");
		    return result;
		}
		Map<String,Object> data=hzyySecretarieBiz.getCycleDetail(UCSID);
		model.addAttribute("data",data);
		if(data==null)
		{
			model.addAttribute("resultId", "300210");
			model.addAttribute("resultType", "轮转信息不存在");
			return result;
		}

		model.addAttribute("isProcessing",StringUtil.isNotBlank(String.valueOf(data.get("RStartTime"))));
		Map<String,Object> param=new HashMap<>();
		param.put("userFlow",userFlow);
		param.put("HosID",user.get("hosID"));
		param.put("userType",stuType);
		List<Map<String,Object>> teas=hzyySecretarieBiz.getTeacherList(param);
		model.addAttribute("teas",teas);
		List<Map<String,Object>> heads=hzyySecretarieBiz.getHeadList(param);
		model.addAttribute("heads",heads);
		return result;
	}
	@RequestMapping(value={"/saveInProcess"},method={RequestMethod.POST})
	public String saveInProcess(String userFlow , String UCSID,String RUserID,
								String RStartTime ,
								String REndTime ,
								String RCySecID ,
								String RHosCySecID ,
								String UserTecID ,
								String HosSecID ,
								String SectionManagerID ,
								Model model){
		String result = "/res/hzyy/secretarie/success";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(UCSID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","轮转标识为空");
			return result;
		}
		if(StringUtil.isBlank(RUserID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","学员ID为空");
			return result;
		}
		if(StringUtil.isBlank(RStartTime)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","轮转开始时间为空");
			return result;
		}
		if(StringUtil.isBlank(REndTime)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","轮转结束时间为空");
			return result;
		}
		if(StringUtil.isBlank(RCySecID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","标准轮转ID为空");
			return result;
		}
		if(StringUtil.isBlank(RHosCySecID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","医院轮转ID为空");
			return result;
		}
		if(StringUtil.isBlank(HosSecID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","医院科室ID为空");
			return result;
		}
		if(StringUtil.isBlank(UserTecID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","带教老师ID为空");
			return result;
		}
		if(StringUtil.isBlank(SectionManagerID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","科主任ID为空");
			return result;
		}
		List<String> hosSecIds=hzyySecretarieBiz.getHosSecId(userFlow);
		if(hosSecIds==null||hosSecIds.size()<=0)
		{	model.addAttribute("resultId", "300210");
			model.addAttribute("resultType", "用户无所在科室");
		    return result;
		}
		Map<String,Object> param=new HashMap<>();
		param.put("userFlow",userFlow);
		param.put("UCSID",UCSID);
		param.put("RUserID",RUserID);
		param.put("RStartTime",RStartTime);
		param.put("REndTime",REndTime);
		param.put("RCySecID",RCySecID);
		param.put("RHosCySecID",RHosCySecID);
		param.put("UserTecID",UserTecID);
		param.put("HosSecID",HosSecID);
		param.put("SectionManagerID",SectionManagerID);
		String r=hzyySecretarieBiz.saveInProcess(param);
		if(StringUtil.isNotBlank(r))
		{
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType",r);
		}
		return result;
	}

	/**
	 * 出入科管理详情
	 * @param userFlow
	 * @param UCSID
	 * @param model
	 * @return
	 */

	@RequestMapping(value={"/outInManageDetail"},method={RequestMethod.POST})
	public String outInManageDetail(String userFlow , String UCSID, Model model){
		String result = "/res/hzyy/secretarie/outInManageDetail";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(UCSID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","轮转标识为空");
			return result;
		}
		//出入科管理详情
		Map<String,Object> detail=hzyySecretarieBiz.getOutInManageDetail(UCSID);
		model.addAttribute("detail",detail);
		//学生填写情况
		List<Map<String,Object>> fillInfos=hzyySecretarieBiz.getFillInfos(UCSID);
		int count=0;
		if(fillInfos!=null)
		{
			for(Map<String,Object> info:fillInfos)
			{
				if("已提交".equals(info.get("DataCount")))
				{
					count++;
				}
			}
			if(count==fillInfos.size()&&count>0)
			{
				model.addAttribute("complete","已完成");
			}else{
				model.addAttribute("complete","未完成");
			}
		}
		return result;
	}

	/**
	 * 出科审核功能列表
	 * @param userFlow 当前用户流水号
	 * @param UCSID		RUCSID 用户轮转主键ID
	 * @param stuType  userType 学员类型
	 * @param model
     * @return
     */
	@RequestMapping(value={"/outProcessFuncList"},method={RequestMethod.POST})
	public String outProcessFuncList(String userFlow , String UCSID,String stuType, Model model){
		String result = "/res/hzyy/secretarie/funcList";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(UCSID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","轮转标识为空");
			return result;
		}
		if(StringUtil.isBlank(stuType)){
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型为空");
			return result;
		}
		List<Map<String,String>> funcList=new ArrayList<>();
		Map<String,String> d1=new HashMap<>();
		d1.put("funcId","AfterSummary");
		d1.put("funcName","出科小结");
		funcList.add(d1);

		d1=new HashMap<>();
		d1.put("funcId","skillExam");
		d1.put("funcName","技能考核");
		funcList.add(d1);

		String isNeed=hzyyTeacherBiz.isNeedPatient(UCSID);
		model.addAttribute("isNeed" , isNeed);
		if("Y".equals(isNeed)) {
			d1 = new HashMap<>();
			d1.put("funcId", "receivePatient");
			d1.put("funcName", "接诊病人");
			funcList.add(d1);
		}

		d1=new HashMap<>();
		d1.put("funcId","theoryScore");
		d1.put("funcName","理论成绩");
		funcList.add(d1);

		d1=new HashMap<>();
		d1.put("funcId","newAfterEvaluation");
		d1.put("funcName","出科考核表");
		funcList.add(d1);
		model.addAttribute("funcList",funcList);
		return result;
	}

	@RequestMapping("/selectSkill")
	public String selectSkill(String userFlow , String UCSID, Model model) throws UnsupportedEncodingException {

		String result = "res/hzyy/secretarie/selectSkill";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(UCSID)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}

		Map<String,String> outSkillMap=hzyyTeacherBiz.getOutSkill(UCSID);
		if(outSkillMap==null||outSkillMap.isEmpty())
		{
			model.addAttribute("resultId", "0302021");
			model.addAttribute("resultType", "带教未审核");
			return result;
		}
		Map<String,String> outSecBrief=hzyyTeacherBiz.getOutSecBrief(UCSID);
		if(outSecBrief!=null&&("2".equals(outSecBrief.get("VerifyState"))||"1".equals(outSecBrief.get("CheckStatus"))))
		{
			model.addAttribute("read", true);
		}else{
			model.addAttribute("read", false);
		}
		Map<String,String> cycleInfo=hzyyTeacherBiz.getCycleInfo(UCSID);
		model.addAttribute("cycleInfo", cycleInfo);
		String skillFlow=outSkillMap.get("Skill_TFlow");
		model.addAttribute("skillFlow", skillFlow);
		//头部病人信息、底部总分数信息
		Map<String,List<Map<String,String>>> itemsMap=new HashMap<>();
		model.addAttribute("outSkillMap", outSkillMap);
		//评分表主表
		List<Map<String,String>> items=hzyyTeacherBiz.getSkillItems(skillFlow,"Y");
		if(items!=null)
		{
			for(Map<String,String> item:items)
			{
				List<Map<String,String>> subItems=hzyyTeacherBiz.getSkillItems(item.get("TItemFlow"), "N");
				itemsMap.put(item.get("TItemFlow"),subItems);
			}
		}
		//获取评分结果
		if(outSkillMap!=null) {
			Map<String, String> scoreMap = hzyyTeacherBiz.getSkillScorelMap(outSkillMap.get("Skill_ID"));
			model.addAttribute("itemScoreMap", scoreMap);
			List<Map<String, String>> fileList = hzyyTeacherBiz.getFileList(String.valueOf(outSkillMap.get("Skill_AttachID")));
			model.addAttribute("fileList", fileList);
		}
		model.addAttribute("baseUrl",baseUrl);
		model.addAttribute("itemsMap", itemsMap);
		model.addAttribute("items", items);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping("/selectPatient")
	public String selectPatient(String userFlow , String UCSID, Model model) throws UnsupportedEncodingException{

		String result = "res/hzyy/secretarie/selectPatient";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(UCSID)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}

		Map<String,String> outPatientMap=hzyyTeacherBiz.getOutPatient(UCSID);
		model.addAttribute("outPatientMap", outPatientMap);
		if(outPatientMap==null||outPatientMap.isEmpty())
		{
			model.addAttribute("resultId", "0302021");
			model.addAttribute("resultType", "带教未审核");
			return result;
		}
		Map<String,String> outSecBrief=hzyyTeacherBiz.getOutSecBrief(UCSID);
		if(outSecBrief!=null&&("2".equals(outSecBrief.get("VerifyState"))||"1".equals(outSecBrief.get("CheckStatus"))))
		{
			model.addAttribute("read", true);
		}else{
			model.addAttribute("read", false);
		}
		Map<String,String> cycleInfo=hzyyTeacherBiz.getCycleInfo(UCSID);
		model.addAttribute("cycleInfo", cycleInfo);
		model.addAttribute("patientTypeId", outPatientMap.get("Patient_Type"));
		//头部病人信息、底部总分数信息
		Map<String,List<Map<String,String>>> itemsMap=new HashMap<>();
		//获取评分结果
		if(outPatientMap!=null) {
			Map<String, String> scoreMap = hzyyTeacherBiz.getPatientScorelMap(outPatientMap.get("Patient_ID"));
			model.addAttribute("itemScoreMap", scoreMap);
			List<Map<String, String>> fileList = hzyyTeacherBiz.getFileList(String.valueOf(outPatientMap.get("Patient_AttachID")));
			model.addAttribute("fileList", fileList);
		}
		model.addAttribute("baseUrl",baseUrl);
		model.addAttribute("itemsMap", itemsMap);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping("/selectTheoryScore")
	public String selectTheoryScore(String userFlow , String UCSID ,Model model) throws UnsupportedEncodingException{

		String result = "res/hzyy/secretarie/selectTheoryScore";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(UCSID)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		Map<String,String> outSecBrief=hzyyTeacherBiz.getOutSecBrief(UCSID);
		if(outSecBrief==null||outSecBrief.isEmpty())
		{
			model.addAttribute("resultId", "2029302");
			model.addAttribute("resultType","尚未提交出科小结，无法录入出科理论成绩！");
			return result;
		}
		if(outSecBrief!=null&&("2".equals(outSecBrief.get("VerifyState"))||"1".equals(outSecBrief.get("CheckStatus"))))
		{
			model.addAttribute("read", true);
		}else{
			model.addAttribute("read", false);
		}
		model.addAttribute("outSecBrief", outSecBrief);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping("/saveTheoryScore")
	public String saveTheoryScore(String userFlow , String UCSID,String TheoryScore,HttpServletRequest request, Model model) throws UnsupportedEncodingException{

		String result = "res/hzyy/secretarie/success";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(UCSID)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(TheoryScore)){
			model.addAttribute("resultId", "3225221");
			model.addAttribute("resultType", "成绩不能为空");
			return result;
		}

		Map<String,String> outSecBrief=hzyyTeacherBiz.getOutSecBrief(UCSID);
		if(outSecBrief==null||outSecBrief.isEmpty())
		{
			model.addAttribute("resultId", "2029302");
			model.addAttribute("resultType","尚未提交出科小结，无法录入出科理论成绩！");
			return result;
		}
		int c=hzyyTeacherBiz.updateOutSecBrief(UCSID,TheoryScore);
		if(c==0)
		{
			model.addAttribute("resultId", "2029302");
			model.addAttribute("resultType","保存失败！");
			return result;
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	/**
	 * 住院医师 研究生 八年制 出科小结审核
	 * @param userFlow 当前用户流水号
	 * @param UCSID		RUCSID 用户轮转主键ID
	 * @param stuType  userType 学员类型
	 * @param UserID  UserID 学员ID
	 * @param CySecID  CySecID 标准轮转ID
	 * @param model
     * @return
     */
	@RequestMapping(value={"/AfterSummary"},method={RequestMethod.POST})
	public String AfterSummary(String userFlow , String CySecID,String UserID, String UCSID,String stuType, Model model){
		String result = "/res/hzyy/secretarie/AfterSummary";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(CySecID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","标准轮转标识为空");
			return result;
		}
		if(StringUtil.isBlank(UCSID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","轮转标识为空");
			return result;
		}
		if(StringUtil.isBlank(UserID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","学员标识为空");
			return result;
		}
		if(StringUtil.isBlank(stuType)){
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型为空");
			return result;
		}
		if("1".equals(stuType)||"2".equals(stuType)||"5".equals(stuType))
		{
			//出科小结基础数据
			Map<String,Object> afterSummmaryData=hzyySecretarieBiz.getAfterSummData(UCSID);
			model.addAttribute("afterSummmaryData",afterSummmaryData);

			//活动情况数据 活动类型（教学病例讨论、教学查房、小讲课、技能技术操作指导）
			Map<String,Object> activitsData=hzyySecretarieBiz.activitsDate(UCSID);
			model.addAttribute("activitsData",activitsData);
			//填写数据
//					--BRequireNumber 大病历要求
//			--CRequireNumber 病种要求
//				--ORequireNumber 操作要求
//			--SRequireNumber 手术要求
//				--BFinish 大病历完成
//			--CFinish 病种完成
//				--OFinish 操作完成
//			--SFinish 手术完成
			Map<String,Object> datas=hzyySecretarieBiz.cycleData(CySecID,UserID);
			model.addAttribute("datas",datas);
			return result;
		}else{
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型错误");
			return result;
		}
	}

	/**
	 * 住院医师 研究生 八年制 日常评价审核
	 * @param userFlow 当前用户流水号
	 * @param UCSID		RUCSID 用户轮转主键ID
	 * @param stuType  userType 学员类型
	 * @param model
     * @return
     */
	@RequestMapping(value={"/DayEval"},method={RequestMethod.POST})
	public String DayEval(String userFlow , String UCSID,String stuType, Model model){
		String result = "/res/hzyy/secretarie/DayEval";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(UCSID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","轮转标识为空");
			return result;
		}
		if(StringUtil.isBlank(stuType)){
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型为空");
			return result;
		}
		if("1".equals(stuType)||"2".equals(stuType)||"5".equals(stuType))
		{
			//日常量化表
			List<Map<String,Object>> items=hzyySecretarieBiz.getDayEvalItems();
			System.err.println("===========items=="+JSON.toJSONString(items));
			model.addAttribute("items",items);
			Map<String,Object> dayEval=hzyySecretarieBiz.getDayEvalData(UCSID);
			model.addAttribute("dayEval",dayEval);
			//学员评价结果
			List<Map<String,Object>> results=hzyySecretarieBiz.getDayEvalResults(UCSID, "7");
			System.err.println("===========results=="+JSON.toJSONString(results));
			Map<Object,Object> resultMap=new HashMap<>();
			if(results!=null&&results.size()>0)
			{
				for(Map<String,Object> m:results)
				{
					resultMap.put(m.get("ReqItemID"),m);
				}
			}
			model.addAttribute("resultMap",resultMap);
			return result;
		}else{
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型错误");
			return result;
		}
	}
	@RequestMapping(value={"/saveDayEval"},method={RequestMethod.POST})
	public String saveDayEval(String userFlow , String resultJson,String stuType,String ExamInfoDf, Model model){
		String result = "/res/hzyy/secretarie/success";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(resultJson)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","打分数据为空");
			return result;
		}
		if(StringUtil.isBlank(stuType)){
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型为空");
			return result;
		}
		if(StringUtil.isBlank(ExamInfoDf)){
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "对学员评分为空");
			return result;
		}
		List<Map<String,String>> items=null;
		try {
			items = (List<Map<String,String>>) JSON.parse(resultJson);
		}catch (Exception e){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "打分结果格式不正确！");
			return "res/osca/success";
		}
		if(items==null||items.size()<=0)
		{
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","打分数据为空");
			return result;
		}
		if("1".equals(stuType)||"2".equals(stuType)||"5".equals(stuType))
		{
			String r=hzyySecretarieBiz.saveDayEval(items,ExamInfoDf);
			if(StringUtil.isNotBlank(r))
			{
				model.addAttribute("resultId", "310023");
				model.addAttribute("resultType",r);
			}
			return result;
		}else{
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型错误");
			return result;
		}
	}
	/**
	 * 住院医师 研究生 八年制 日常评价审核
	 * @param userFlow 当前用户流水号
	 * @param UCSID		RUCSID 用户轮转主键ID
	 * @param stuType  userType 学员类型
	 * @param model
     * @return
     */
	@RequestMapping(value={"/DOPS1"},method={RequestMethod.POST})
	public String DOPS1(String userFlow , String UCSID,String stuType, Model model){
		String result = "/res/hzyy/secretarie/DOPS1";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(UCSID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","轮转标识为空");
			return result;
		}
		if(StringUtil.isBlank(stuType)){
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型为空");
			return result;
		}
		if("1".equals(stuType)||"2".equals(stuType)||"5".equals(stuType))
		{
			//获取学员角色
			List<Map<String,String>> roles=hzyySecretarieBiz.getDopsStuRoles();
			model.addAttribute("roles",roles);
			//获取科室信息
			String secName=hzyySecretarieBiz.getSecName(UCSID);
			model.addAttribute("secName",secName);

			String examItemID="";
			//获取模板信息（标准模板、检验科模板）
			List<Map<String,Object>> mbs=hzyySecretarieBiz.getmbs("11");
			if(mbs==null)
			{
				model.addAttribute("resultId", "310024");
				model.addAttribute("resultType", "获取模板信息出错");
				return result;
			}
			if(mbs.size()==1) {
				examItemID= String.valueOf(mbs.get(0).get("ExamItemID"));
			}else{
				Map<String, Object> mbMap = new HashMap<>();
				String jykmb="";
				//获取模板评价项目
				for (Map<String, Object> mb : mbs) {
					if(mb.get("ExamItemName")!=null&&String.valueOf(mb.get("ExamItemName")).contains("核医学科")&&secName.contains("核医学科"))
					{
						jykmb=String.valueOf(mb.get("ExamItemName"));
					}
					mbMap.put(String.valueOf(mb.get("ExamItemName")), mb);
				}
				Map<String, Object> s= (Map<String, Object>) mbMap.get(jykmb);
				if(s==null)
					s= (Map<String, Object>) mbMap.get("DOPS");
				if(s!=null)
					examItemID= String.valueOf(s.get("ExamItemID"));
			}
			if(StringUtil.isBlank(examItemID))
			{

				model.addAttribute("resultId", "310024");
				model.addAttribute("resultType", "获取模板信息出错");
				return result;
			}
			List<Map<String,Object>> mbEvals=hzyySecretarieBiz.getmbEvals("11",examItemID);
			model.addAttribute("items",mbEvals);

			//获取表格基础数据
			Map<String,Object> stuInitData=hzyySecretarieBiz.getStuInitData(UCSID);
			model.addAttribute("stuInitData",stuInitData);
			//获取DOPS评价表数据
			Map<String,Object> dopsData=hzyySecretarieBiz.getDopsData(UCSID);
			model.addAttribute("dopsData",dopsData);
			//获取各项分值
			List<Map<String,Object>> mbEvalResults=hzyySecretarieBiz.getMbEvalResults("11",UCSID);
			Map<Object,Object> resultMap=new HashMap<>();
			if(mbEvalResults!=null&&mbEvalResults.size()>0)
			{
				for(Map<String,Object> m:mbEvalResults)
				{
					resultMap.put(m.get("ReqItemID"),m);
				}
			}
			model.addAttribute("resultMap",resultMap);
			return result;
		}else{
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型错误");
			return result;
		}
	}
	/**
	 * 住院医师 研究生 八年制 MINI-CEX
	 * @param userFlow 当前用户流水号
	 * @param UCSID		RUCSID 用户轮转主键ID
	 * @param stuType  userType 学员类型
	 * @param model
     * @return
     */
	@RequestMapping(value={"/MiniCex1"},method={RequestMethod.POST})
	public String MiniCex1(String userFlow , String UCSID,String stuType, Model model){
		String result = "/res/hzyy/secretarie/MiniCex1";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(UCSID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","轮转标识为空");
			return result;
		}
		if(StringUtil.isBlank(stuType)){
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型为空");
			return result;
		}
		if("1".equals(stuType)||"2".equals(stuType)||"5".equals(stuType))
		{
			//获取学员角色
			List<Map<String,String>> roles=hzyySecretarieBiz.getDopsStuRoles();
			model.addAttribute("roles",roles);
			//获取科室信息
			String secName=hzyySecretarieBiz.getSecName(UCSID);
			model.addAttribute("secName",secName);

			String examItemID="";
			//获取模板信息（标准模板、检验科模板）
			List<Map<String,Object>> mbs=hzyySecretarieBiz.getmbs("12");
			if(mbs==null)
			{
				model.addAttribute("resultId", "310024");
				model.addAttribute("resultType", "获取模板信息出错");
				return result;
			}
			if(mbs.size()==1) {
				examItemID= String.valueOf(mbs.get(0).get("ExamItemID"));
			}else{
				Map<String, Object> mbMap = new HashMap<>();
				String jykmb="";
				//获取模板评价项目
				for (Map<String, Object> mb : mbs) {
					if(mb.get("ExamItemName")!=null&&String.valueOf(mb.get("ExamItemName")).contains("核医学科")&&secName.contains("核医学科"))
					{
						jykmb=String.valueOf(mb.get("ExamItemName"));
					}
					mbMap.put(String.valueOf(mb.get("ExamItemName")), mb);
				}
				Map<String, Object> s= (Map<String, Object>) mbMap.get(jykmb);
				if(s==null)
					s= (Map<String, Object>) mbMap.get("Mini");
				if(s!=null)
					examItemID= String.valueOf(s.get("ExamItemID"));
			}
			if(StringUtil.isBlank(examItemID))
			{

				model.addAttribute("resultId", "310024");
				model.addAttribute("resultType", "获取模板信息出错");
				return result;
			}
			//获取模板评价项目
			List<Map<String,Object>> mbEvals=hzyySecretarieBiz.getmbEvals("12",examItemID);
			model.addAttribute("items",mbEvals);

			//获取表格基础数据
			Map<String,Object> stuInitData=hzyySecretarieBiz.getStuInitData(UCSID);
			model.addAttribute("stuInitData",stuInitData);
			//获取MINI评价表数据
			Map<String,Object> miniData=hzyySecretarieBiz.getMiniData(UCSID);
			model.addAttribute("miniData",miniData);
			//获取各项分值
			List<Map<String,Object>> mbEvalResults=hzyySecretarieBiz.getMbEvalResults("12",UCSID);
			Map<Object,Object> resultMap=new HashMap<>();
			if(mbEvalResults!=null&&mbEvalResults.size()>0)
			{
				for(Map<String,Object> m:mbEvalResults)
				{
					resultMap.put(m.get("ReqItemID"),m);
				}
			}
			model.addAttribute("resultMap",resultMap);

			return result;
		}else{
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型错误");
			return result;
		}
	}
	/**
	 * 住院医师 研究生 八年制 出科考核表
	 * @param userFlow 当前用户流水号
	 * @param UCSID		RUCSID 用户轮转主键ID
	 * @param stuType  userType 学员类型
	 * @param model
     * @return
     */
	@RequestMapping(value={"/AfterEvaluation1"},method={RequestMethod.POST})
	public String AfterEvaluation1(String userFlow , String UCSID,String stuType, Model model){
		String result = "/res/hzyy/secretarie/AfterEvaluation1";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(UCSID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","轮转标识为空");
			return result;
		}
		if(StringUtil.isBlank(stuType)){
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型为空");
			return result;
		}
			String nowDate=DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm:ss");
			model.addAttribute("nowDate",nowDate);
			//出科验证
			//是否验证出科DOPS、MINI表数据 (否 默认1)
//			String isAssess=hzyySecretarieBiz.getIsAssess(UCSID);
//			//是否评价DOPS、MINI（没有评价不允许出科）
//			String dopsScore="100.0";
//			String miniScore="100.0";
//			if("1".equals(isAssess)) {
//				dopsScore = hzyySecretarieBiz.getDopsScore(UCSID);
//				miniScore = hzyySecretarieBiz.getMiniScore(UCSID);
//				if (StringUtil.isBlank(dopsScore)) {
//					model.addAttribute("resultId", "310024");
//					model.addAttribute("resultType", "未评价DOPS表，不允许出科");
//					return result;
//				}
//				BigDecimal e=new BigDecimal(Double.valueOf(dopsScore)*100/9.0).setScale(2,BigDecimal.ROUND_HALF_UP);
//				dopsScore=String.valueOf(e.doubleValue());
//				model.addAttribute("dopsScore", dopsScore);
//				if (StringUtil.isBlank(miniScore)) {
//					model.addAttribute("resultId", "310024");
//					model.addAttribute("resultType", "未评价Mini-Cex表，不允许出科");
//					return result;
//				}
//				BigDecimal e2=new BigDecimal(Double.valueOf(miniScore)*100/9.0).setScale(2,BigDecimal.ROUND_HALF_UP);
//				miniScore=String.valueOf(e2.doubleValue());
//				model.addAttribute("miniScore", miniScore);
//			}
			//获取考勤分数 只取第2个值（换算成百分制：score * 100 / 9 保留2位小数）
			//
			List<Map<String,Object>> results=hzyySecretarieBiz.getDayEvalResults(UCSID, "7");
			String evalScore="0";
			if(results!=null&&results.size()>2)
			{
				evalScore= String.valueOf(results.get(1).get("MarkDF"));
				if(StringUtil.isNotBlank(evalScore))
				{
					BigDecimal e=new BigDecimal(Double.valueOf(evalScore)*100/9.0).setScale(2,BigDecimal.ROUND_HALF_UP);
					evalScore=String.valueOf(e.doubleValue());
				}else {
					evalScore="0";
				}
			}
			model.addAttribute("evalScore",evalScore);
			String dayEvalScore="0";
			//获取带教综合评价分数
			List<Map<String,Object>> eval=hzyySecretarieBiz.getZhEval(UCSID, "7");
			if(eval!=null&&eval.size()>0)
			{
				model.addAttribute("dayEval",eval.get(0));
				dayEvalScore= String.valueOf(eval.get(0).get("ExamInfoDF"));
				if(StringUtil.isNotBlank(dayEvalScore))
				{
					BigDecimal e=new BigDecimal(Double.valueOf(dayEvalScore)*100/9.0).setScale(2,BigDecimal.ROUND_HALF_UP);
					dayEvalScore=String.valueOf(e.doubleValue());
				}else {
					dayEvalScore="0";
				}
			}
			model.addAttribute("dayEvalScore",dayEvalScore);
			//获取审核数据
			Map<String,Object> auditData=hzyySecretarieBiz.getAuditData(UCSID);
			model.addAttribute("auditData",auditData);
			String theroyScore= String.valueOf(auditData.get("AssessmentMark"));
		 	//BigDecimal bigDecimal=new BigDecimal(Double.valueOf(dopsScore)*0.25+Double.valueOf(miniScore)*0.35+Double.valueOf(dayEvalScore)*0.3+Double.valueOf(theroyScore)*0.1).setScale(2, BigDecimal.ROUND_HALF_UP);
			//model.addAttribute("totelScore",bigDecimal.doubleValue());
			return result;
	}


	/**
	 * 保存出科考核表
	 * @param userFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/saveAfterEvaluation1"},method={RequestMethod.POST})
	public String saveAfterEvaluation1(String userFlow ,String ExamineMark,
									   String AssessmentMark ,
									   String SickLeaveDay ,
									   String AbsenteeismDay ,
									   String SecDate,
									   String TecComment,
									   String SecComment,
									   String UCSID,
									   String ProfesserID,
									   String ProfesserDate,
									   String ID, Model model){
		String result = "/res/hzyy/secretarie/success";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(ID)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "数据主键为空");
			return result;
		}
		if(StringUtil.isBlank(ExamineMark)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "考勤得分为空");
			return result;
		}
		if(StringUtil.isBlank(AssessmentMark)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "理论考核分数为空");
			return result;
		}
		if(StringUtil.isBlank(SickLeaveDay)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "请假为空");
			return result;
		}
		if(StringUtil.isBlank(AbsenteeismDay)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "脱岗为空");
			return result;
		}
		if(StringUtil.isBlank(TecComment)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "带教老师审核意见为空");
			return result;
		}
		if(StringUtil.isBlank(SecComment)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "教学秘书审核意见");
			return result;
		}
		if(StringUtil.isBlank(SecDate)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "教学秘书审核时间为空");
			return result;
		}
		if(StringUtil.isBlank(ProfesserID)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "科主任ID为空");
			return result;
		}
		if(StringUtil.isBlank(ProfesserDate)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "科主任审核时间为空");
			return result;
		}
		if(StringUtil.isBlank(UCSID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","轮转标识为空");
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		Map<String, String> param = new HashMap<>();
		param.put("ExamineMark", ExamineMark);
		param.put("AssessmentMark", AssessmentMark);
		param.put("SickLeaveDay", SickLeaveDay);
		param.put("AbsenteeismDay", AbsenteeismDay);
		param.put("SecretaryID", userFlow);
		param.put("SecDate", SecDate);
		param.put("TecComment", TecComment);
		param.put("SecComment", SecComment);
		param.put("VerifyState", "2");
		param.put("ProfesserID", ProfesserID);
		param.put("ProfesserDate", ProfesserDate);
		param.put("ID", ID);
		param.put("UCSID", UCSID);
		String r = hzyySecretarieBiz.saveAfterEvaluation1(param);
		if (StringUtil.isNotBlank(r)) {
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType", r);
		}
		return result;
	}


	/**
	 * 实习情况记录表
	 * @param userFlow
	 * @param UCSID
	 * @param stuType
	 * @param dataType
	 * @param pageIndex
	 * @param pageSize
     * @param model
     * @return
     */
	@RequestMapping(value={"/Internship"},method={RequestMethod.POST})
	public String Internship(String userFlow , String UCSID,String stuType,String dataType, Integer pageIndex , Integer pageSize , Model model){
		String result = "/res/hzyy/secretarie/Internship";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(UCSID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","轮转标识为空");
			return result;
		}
		if(StringUtil.isBlank(stuType)){
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型为空");
			return result;
		}
		if(StringUtil.isBlank(dataType)){
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "数据类型为空");
			return result;
		}
		if(!"0".equals(dataType)&&!"1".equals(dataType))
		{
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "数据类型错误");
			return result;
		}

		if(pageIndex==null){
			pageIndex = NfyyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = NfyyGlobalConstant.DEFAULT_PAGE_SIZE;
		}
		int dataCount=0;
		if("4".equals(stuType))
		{

			Map<String,Object> param=new HashMap<>();
			param.put("dataType",dataType);
			param.put("userFlow",userFlow);
			param.put("ucsid",UCSID);
			List<Map<String,Object>> internshipList = hzyySecretarieBiz.internshipList(param,pageIndex,pageSize);
			model.addAttribute("internshipList",internshipList);
			List<Map<String,Object>> all =hzyySecretarieBiz.internshipList(param,1,Integer.MAX_VALUE);
			if(all!=null)
				dataCount=all.size();
			model.addAttribute("dataCount", dataCount);
			return result;
		}else{
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型错误");
			return result;
		}
	}

	/**
	 *实习鉴定
	 * @param userFlow
	 * @param UCSID
	 * @param stuType
     * @param model
     * @return
     */
	@RequestMapping(value={"/Appraisal"},method={RequestMethod.POST})
	public String Appraisal(String userFlow , String UCSID,String stuType, Model model){
		String result = "/res/hzyy/secretarie/Appraisal";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(UCSID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","轮转标识为空");
			return result;
		}
		if(StringUtil.isBlank(stuType)){
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型为空");
			return result;
		}
		if("4".equals(stuType))
		{

			Map<String,Object> param=new HashMap<>();
			param.put("userFlow",userFlow);
			param.put("ucsid",UCSID);
			Map<String,Object> appraisalData=hzyySecretarieBiz.getAppraisalData(UCSID);
			model.addAttribute("appraisalData",appraisalData);

			List<Map<String,Object>> mbEvals=hzyySecretarieBiz.getmbEvals("9","");
			model.addAttribute("items",mbEvals);
			//获取各项分值
			List<Map<String,Object>> mbEvalResults=hzyySecretarieBiz.getMbEvalResults("9",UCSID);
			Map<Object,Object> resultMap=new HashMap<>();
			if(mbEvalResults!=null&&mbEvalResults.size()>0)
			{
				for(Map<String,Object> m:mbEvalResults)
				{
					resultMap.put(m.get("ReqItemID"),m);
				}
			}
			model.addAttribute("resultMap",resultMap);

			return result;
		}else{
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型错误");
			return result;
		}
	}

	/**
	 * 保存实习鉴定
	 * @param userFlow
	 * @param resultJson
	 * @param stuType
	 * @param model
     * @return
     */
	@RequestMapping(value={"/saveAppraisal"},method={RequestMethod.POST})
	public String saveAppraisal(String userFlow , String resultJson,String stuType,String BriefID,String ExamInfoDf,String SecretaryContent,String TecAppraise,String UCSID, Model model){
		String result = "/res/hzyy/secretarie/success";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(UCSID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","轮转标识为空");
			return result;
		}
		if(StringUtil.isBlank(resultJson)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","打分数据为空");
			return result;
		}
		if(StringUtil.isBlank(BriefID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","数据流水号为空");
			return result;
		}
		if(StringUtil.isBlank(ExamInfoDf)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","对学员评价为空");
			return result;
		}
		if(StringUtil.isBlank(TecAppraise)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","带教老师鉴定意见为空");
			return result;
		}
		if(StringUtil.isBlank(SecretaryContent)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","教学秘书鉴定意见为空");
			return result;
		}
		if(StringUtil.isBlank(stuType)){
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型为空");
			return result;
		}
		List<Map<String,String>> items=null;
		try {
			items = (List<Map<String,String>>) JSON.parse(resultJson);
		}catch (Exception e){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "打分结果格式不正确！");
			return "res/osca/success";
		}
		if(items==null||items.size()<=0)
		{
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","打分数据为空");
			return result;
		}
		if("4".equals(stuType))
		{
			Map<String,Object> param=new HashMap<>();
			param.put("items",items);
			param.put("UCSID",UCSID);
			param.put("userFlow",userFlow);
			param.put("BriefID",BriefID);
			param.put("ExamInfoDf",ExamInfoDf);
			param.put("SecretaryContent",SecretaryContent);
			param.put("TecAppraise",TecAppraise);
			String r=hzyySecretarieBiz.saveAppraisal(param);
			if(StringUtil.isNotBlank(r))
			{
				model.addAttribute("resultId", "310023");
				model.addAttribute("resultType",r);
			}
			return result;
		}else{
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型错误");
			return result;
		}
	}

	/**
	 * 实习生dops
	 * @param userFlow 当前用户流水号
	 * @param UCSID		RUCSID 用户轮转主键ID
	 * @param stuType  userType 学员类型
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/DOPS"},method={RequestMethod.POST})
	public String DOPS(String userFlow , String UCSID,String stuType, Model model){
		String result = "/res/hzyy/secretarie/DOPS";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(UCSID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","轮转标识为空");
			return result;
		}
		if(StringUtil.isBlank(stuType)){
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型为空");
			return result;
		}
		if("4".equals(stuType))
		{
			//获取学员角色
			List<Map<String,String>> roles=hzyySecretarieBiz.getDopsStuRoles();
			model.addAttribute("roles",roles);
			//获取科室信息
			String secName=hzyySecretarieBiz.getSecName(UCSID);
			model.addAttribute("secName",secName);

			String examItemID="";
			//获取模板信息（标准模板、检验科模板）
			List<Map<String,Object>> mbs=hzyySecretarieBiz.getmbs("11");
			if(mbs==null)
			{
				model.addAttribute("resultId", "310024");
				model.addAttribute("resultType", "获取模板信息出错");
				return result;
			}
			if(mbs.size()==1) {
				examItemID= String.valueOf(mbs.get(0).get("ExamItemID"));
			}else{
				Map<String, Object> mbMap = new HashMap<>();
				String jykmb="";
				//获取模板评价项目
				for (Map<String, Object> mb : mbs) {
					if(mb.get("ExamItemName")!=null&&String.valueOf(mb.get("ExamItemName")).contains("核医学科")&&secName.contains("核医学科"))
					{
						jykmb=String.valueOf(mb.get("ExamItemName"));
					}
					mbMap.put(String.valueOf(mb.get("ExamItemName")), mb);
				}
				Map<String, Object> s= (Map<String, Object>) mbMap.get(jykmb);
				if(s==null)
					s= (Map<String, Object>) mbMap.get("DOPS");
				if(s!=null)
					examItemID= String.valueOf(s.get("ExamItemID"));
			}
			if(StringUtil.isBlank(examItemID))
			{

				model.addAttribute("resultId", "310024");
				model.addAttribute("resultType", "获取模板信息出错");
				return result;
			}
			List<Map<String,Object>> mbEvals=hzyySecretarieBiz.getmbEvals("11",examItemID);
			model.addAttribute("items",mbEvals);

			//获取表格基础数据
			Map<String,Object> stuInitData=hzyySecretarieBiz.getStuInitData(UCSID);
			model.addAttribute("stuInitData",stuInitData);
			//获取DOPS评价表数据
			Map<String,Object> dopsData=hzyySecretarieBiz.getDopsData(UCSID);
			model.addAttribute("dopsData",dopsData);
			//获取各项分值
			List<Map<String,Object>> mbEvalResults=hzyySecretarieBiz.getMbEvalResults("11",UCSID);
			Map<Object,Object> resultMap=new HashMap<>();
			if(mbEvalResults!=null&&mbEvalResults.size()>0)
			{
				for(Map<String,Object> m:mbEvalResults)
				{
					resultMap.put(m.get("ReqItemID"),m);
				}
			}
			model.addAttribute("resultMap",resultMap);
			return result;
		}else{
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型错误");
			return result;
		}
	}
	/**
	 * 实习生 MINI-CEX
	 * @param userFlow 当前用户流水号
	 * @param UCSID		RUCSID 用户轮转主键ID
	 * @param stuType  userType 学员类型
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/MiniCex"},method={RequestMethod.POST})
	public String MiniCex(String userFlow , String UCSID,String stuType, Model model){
		String result = "/res/hzyy/secretarie/MiniCex";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(UCSID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","轮转标识为空");
			return result;
		}
		if(StringUtil.isBlank(stuType)){
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型为空");
			return result;
		}
		if("4".equals(stuType))
		{
			//获取学员角色
			List<Map<String,String>> roles=hzyySecretarieBiz.getDopsStuRoles();
			model.addAttribute("roles",roles);
			//获取科室信息
			String secName=hzyySecretarieBiz.getSecName(UCSID);
			model.addAttribute("secName",secName);

			String examItemID="";
			//获取模板信息（标准模板、检验科模板）
			List<Map<String,Object>> mbs=hzyySecretarieBiz.getmbs("12");
			if(mbs==null)
			{
				model.addAttribute("resultId", "310024");
				model.addAttribute("resultType", "获取模板信息出错");
				return result;
			}
			if(mbs.size()==1) {
				examItemID= String.valueOf(mbs.get(0).get("ExamItemID"));
			}else{
				Map<String, Object> mbMap = new HashMap<>();
				String jykmb="";
				//获取模板评价项目
				for (Map<String, Object> mb : mbs) {
					if(mb.get("ExamItemName")!=null&&String.valueOf(mb.get("ExamItemName")).contains("核医学科")&&secName.contains("核医学科"))
					{
						jykmb=String.valueOf(mb.get("ExamItemName"));
					}
					mbMap.put(String.valueOf(mb.get("ExamItemName")), mb);
				}
				Map<String, Object> s= (Map<String, Object>) mbMap.get(jykmb);
				if(s==null)
					s= (Map<String, Object>) mbMap.get("Mini");
				if(s!=null)
					examItemID= String.valueOf(s.get("ExamItemID"));
			}
			if(StringUtil.isBlank(examItemID))
			{

				model.addAttribute("resultId", "310024");
				model.addAttribute("resultType", "获取模板信息出错");
				return result;
			}
			//获取模板评价项目
			List<Map<String,Object>> mbEvals=hzyySecretarieBiz.getmbEvals("12",examItemID);
			model.addAttribute("items",mbEvals);

			//获取表格基础数据
			Map<String,Object> stuInitData=hzyySecretarieBiz.getStuInitData(UCSID);
			model.addAttribute("stuInitData",stuInitData);
			//获取MINI评价表数据
			Map<String,Object> miniData=hzyySecretarieBiz.getMiniData(UCSID);
			model.addAttribute("miniData",miniData);
			//获取各项分值
			List<Map<String,Object>> mbEvalResults=hzyySecretarieBiz.getMbEvalResults("12",UCSID);
			Map<Object,Object> resultMap=new HashMap<>();
			if(mbEvalResults!=null&&mbEvalResults.size()>0)
			{
				for(Map<String,Object> m:mbEvalResults)
				{
					resultMap.put(m.get("ReqItemID"),m);
				}
			}
			model.addAttribute("resultMap",resultMap);

			return result;
		}else{
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型错误");
			return result;
		}
	}

	/**
	 * 实习生 出科考核表
	 * @param userFlow 当前用户流水号
	 * @param UCSID		RUCSID 用户轮转主键ID
	 * @param stuType  userType 学员类型
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/AfterEvaluation"},method={RequestMethod.POST})
	public String AfterEvaluation(String userFlow , String UCSID,String stuType, Model model){
		String result = "/res/hzyy/secretarie/AfterEvaluation";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(UCSID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","轮转标识为空");
			return result;
		}
		if(StringUtil.isBlank(stuType)){
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型为空");
			return result;
		}
		if("4".equals(stuType))
		{

			String nowDate=DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm:ss");
			model.addAttribute("nowDate",nowDate);
			//出科验证
			//是否验证出科DOPS、MINI表数据 (否 默认1)
//			String isAssess=hzyySecretarieBiz.getIsAssess(UCSID);
			//是否评价DOPS、MINI（没有评价不允许出科）
//			String dopsScore="100.0";
//			String miniScore="100.0";
//			if("1".equals(isAssess)) {
//				dopsScore = hzyySecretarieBiz.getDopsScore(UCSID);
//				miniScore = hzyySecretarieBiz.getMiniScore(UCSID);
//				if (StringUtil.isBlank(dopsScore)) {
//					model.addAttribute("resultId", "310024");
//					model.addAttribute("resultType", "未评价DOPS表，不允许出科");
//					return result;
//				}
//				BigDecimal e=new BigDecimal(Double.valueOf(dopsScore)*100/9.0).setScale(2,BigDecimal.ROUND_HALF_UP);
//				dopsScore=String.valueOf(e.doubleValue());
//				model.addAttribute("dopsScore", dopsScore);
//				if (StringUtil.isBlank(miniScore)) {
//					model.addAttribute("resultId", "310024");
//					model.addAttribute("resultType", "未评价Mini-Cex表，不允许出科");
//					return result;
//				}
//				BigDecimal e2=new BigDecimal(Double.valueOf(miniScore)*100/9.0).setScale(2,BigDecimal.ROUND_HALF_UP);
//				miniScore=String.valueOf(e2.doubleValue());
//				model.addAttribute("miniScore", miniScore);
//			}
			//获取考勤分数 只取第2个值（换算成百分制：score * 100 / 9 保留2位小数）
			//
			List<Map<String,Object>> results=hzyySecretarieBiz.getDayEvalResults(UCSID, "9");
			String evalScore="0";
			if(results!=null&&results.size()>2)
			{
				evalScore= String.valueOf(results.get(1).get("MarkDF"));
				if(StringUtil.isNotBlank(evalScore))
				{
					BigDecimal e=new BigDecimal(Double.valueOf(evalScore)*100/9.0).setScale(2,BigDecimal.ROUND_HALF_UP);
					evalScore=String.valueOf(e.doubleValue());
				}else {
					evalScore="0";
				}
			}
			model.addAttribute("evalScore",evalScore);
			String dayEvalScore="0";
			//获取带教综合评价分数
			List<Map<String,Object>> eval=hzyySecretarieBiz.getZhEval(UCSID, "9");
			if(eval!=null&&eval.size()>0)
			{
				model.addAttribute("dayEval",eval.get(0));
				dayEvalScore= String.valueOf(eval.get(0).get("ExamInfoDF"));
				if(StringUtil.isNotBlank(dayEvalScore))
				{
					BigDecimal e=new BigDecimal(Double.valueOf(dayEvalScore)*100/9.0).setScale(2,BigDecimal.ROUND_HALF_UP);
					dayEvalScore=String.valueOf(e.doubleValue());
				}else {
					dayEvalScore="0";
				}
			}
			model.addAttribute("dayEvalScore",dayEvalScore);
			//获取审核数据
			Map<String,Object> auditData=hzyySecretarieBiz.getAuditData(UCSID);
			model.addAttribute("auditData",auditData);
			String theroyScore= String.valueOf(auditData.get("AssessmentMark"));
//			BigDecimal bigDecimal=new BigDecimal(Double.valueOf(dopsScore)*0.25+Double.valueOf(miniScore)*0.25+Double.valueOf(dayEvalScore)*0.3+Double.valueOf(theroyScore)*0.2).setScale(2, BigDecimal.ROUND_HALF_UP);
//			model.addAttribute("totelScore",bigDecimal.doubleValue());
			return result;
		}else{
			model.addAttribute("resultId", "310024");
			model.addAttribute("resultType", "学员类型错误");
			return result;
		}
	}


	/**
	 * 实习生 保存出科考核表
	 * @param userFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/saveAfterEvaluation"},method={RequestMethod.POST})
	public String saveAfterEvaluation(String userFlow ,String ExamineMark,
									   String AssessmentMark ,
									   String SickLeaveDay ,
									   String AbsenteeismDay ,
									   String SecDate,
									   String TecComment,
									   String SecComment,
									   String UCSID,
									   String ProfesserID,
									   String ProfesserDate,
									   String ID, Model model){
		String result = "/res/hzyy/secretarie/success";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(ID)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "数据主键为空");
			return result;
		}
		if(StringUtil.isBlank(ExamineMark)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "考勤得分为空");
			return result;
		}
		if(StringUtil.isBlank(AssessmentMark)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "理论考核分数为空");
			return result;
		}
		if(StringUtil.isBlank(SickLeaveDay)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "请假为空");
			return result;
		}
		if(StringUtil.isBlank(AbsenteeismDay)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "脱岗为空");
			return result;
		}
		if(StringUtil.isBlank(TecComment)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "带教老师审核意见为空");
			return result;
		}
		if(StringUtil.isBlank(SecComment)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "教学秘书审核意见");
			return result;
		}
		if(StringUtil.isBlank(SecDate)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "教学秘书审核时间为空");
			return result;
		}
		if(StringUtil.isBlank(ProfesserID)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "科主任ID为空");
			return result;
		}
		if(StringUtil.isBlank(ProfesserDate)){
			model.addAttribute("resultId", "3232302");
			model.addAttribute("resultType", "科主任审核时间为空");
			return result;
		}
		if(StringUtil.isBlank(UCSID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","轮转标识为空");
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		Map<String, String> param = new HashMap<>();
		param.put("ExamineMark", ExamineMark);
		param.put("AssessmentMark", AssessmentMark);
		param.put("SickLeaveDay", SickLeaveDay);
		param.put("AbsenteeismDay", AbsenteeismDay);
		param.put("SecretaryID", userFlow);
		param.put("SecDate", SecDate);
		param.put("TecComment", TecComment);
		param.put("SecComment", SecComment);
		param.put("VerifyState", "2");
		param.put("ProfesserID", ProfesserID);
		param.put("ProfesserDate", ProfesserDate);
		param.put("ID", ID);
		param.put("UCSID", UCSID);
		String r = hzyySecretarieBiz.saveAfterEvaluation1(param);
		if (StringUtil.isNotBlank(r)) {
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType", r);
		}
		return result;
	}


	@RequestMapping(value={"/newAfterEvaluation"},method={RequestMethod.POST,RequestMethod.GET})
	public String newAfterEvaluation(String userFlow,String UCSID, Model model){

		String result = "/res/hzyy/secretarie/newAfterEvaluation";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(UCSID)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}

		Map<String,String> cycleOutInfo = hzyyTeacherBiz.getCycleOutInfo(UCSID);
		model.addAttribute("cycleOutInfo" , cycleOutInfo);
		if(cycleOutInfo==null||cycleOutInfo.isEmpty())
		{
			model.addAttribute("resultId", "30201021");
			model.addAttribute("resultType", "带教暂未审核");
			return result;
		}
		Map<String,String> afterEva = hzyyTeacherBiz.readAfterEva(UCSID);
		model.addAttribute("afterEva" , afterEva);
		String isNeed=hzyyTeacherBiz.isNeedPatient(UCSID);
		model.addAttribute("isNeed" , isNeed);
		if(afterEva!=null)
		{
			if("0".equals(String.valueOf(afterEva.get("SectionType"))))
			{
				if(StringUtil.isNotBlank(String.valueOf(afterEva.get("TheoryScore")))){
					Double TheoryScore=Double.valueOf(afterEva.get("TheoryScore"));
					BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0.4).setScale(1, BigDecimal.ROUND_HALF_UP);
					afterEva.put("TheoryScore",String.valueOf(bigDecimal.doubleValue()));
				}
				if("N".equals(isNeed))
				{
					if(StringUtil.isNotBlank(String.valueOf(afterEva.get("Patient_Score")))){
						Double TheoryScore=Double.valueOf(afterEva.get("Patient_Score"));
						BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0).setScale(1, BigDecimal.ROUND_HALF_UP);
						afterEva.put("Patient_Score",String.valueOf(bigDecimal.doubleValue()));
					}
					if(StringUtil.isNotBlank(String.valueOf(afterEva.get("Skill_Score")))){
						Double TheoryScore=Double.valueOf(afterEva.get("Skill_Score"));
						BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0.4).setScale(1, BigDecimal.ROUND_HALF_UP);
						afterEva.put("Skill_Score",String.valueOf(bigDecimal.doubleValue()));
					}
				}else{
					if(StringUtil.isNotBlank(String.valueOf(afterEva.get("Patient_Score")))){
						Double TheoryScore=Double.valueOf(afterEva.get("Patient_Score"));
						BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0.2).setScale(1, BigDecimal.ROUND_HALF_UP);
						afterEva.put("Patient_Score",String.valueOf(bigDecimal.doubleValue()));
					}
					if(StringUtil.isNotBlank(String.valueOf(afterEva.get("Skill_Score")))){
						Double TheoryScore=Double.valueOf(afterEva.get("Skill_Score"));
						BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0.2).setScale(1, BigDecimal.ROUND_HALF_UP);
						afterEva.put("Skill_Score",String.valueOf(bigDecimal.doubleValue()));
					}

				}
			}
			if("1".equals(String.valueOf(afterEva.get("SectionType"))))
			{

				if(StringUtil.isNotBlank(String.valueOf(afterEva.get("TheoryScore")))){
					Double TheoryScore=Double.valueOf(afterEva.get("TheoryScore"));
					BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0.5).setScale(1, BigDecimal.ROUND_HALF_UP);
					afterEva.put("TheoryScore",String.valueOf(bigDecimal.doubleValue()));
				}
				if(StringUtil.isNotBlank(String.valueOf(afterEva.get("Skill_Score")))){
					Double TheoryScore=Double.valueOf(afterEva.get("Skill_Score"));
					BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0.3).setScale(1, BigDecimal.ROUND_HALF_UP);
					afterEva.put("Skill_Score",String.valueOf(bigDecimal.doubleValue()));
				}
			}
			if("2".equals(String.valueOf(afterEva.get("SectionType"))))
			{
				if(StringUtil.isNotBlank(String.valueOf(afterEva.get("TheoryScore")))){
					Double TheoryScore=Double.valueOf(afterEva.get("TheoryScore"));
					BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0.8).setScale(1, BigDecimal.ROUND_HALF_UP);
					afterEva.put("TheoryScore",String.valueOf(bigDecimal.doubleValue()));
				}
			}
		}
		int jxhd=hzyyTeacherBiz.getJxhd(UCSID);
		model.addAttribute("jxhd" , jxhd);
		Map<String,String> outSecBrief=hzyyTeacherBiz.getOutSecBrief(UCSID);
		if(outSecBrief!=null&&("2".equals(outSecBrief.get("VerifyState"))||"1".equals(outSecBrief.get("CheckStatus"))))
		{
			model.addAttribute("read", true);
		}else{
			model.addAttribute("read", false);
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping(value={"/saveNewAfterEvaluation"},method={RequestMethod.POST})
	public synchronized String saveNewAfterEvaluation(String userFlow,String UCSID,HttpServletRequest request, Model model){

		String result = "/res/hzyy/secretarie/success";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(UCSID)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}

		Map<String,String> tea = hzyyAppBiz.readUser(userFlow);

		Map<String,String> cycleOutInfo = hzyyTeacherBiz.getCycleOutInfo(UCSID);
		model.addAttribute("cycleOutInfo" , cycleOutInfo);
		if(cycleOutInfo==null||cycleOutInfo.isEmpty())
		{
			model.addAttribute("resultId", "30201021");
			model.addAttribute("resultType", "带教暂未审核");
			return result;
		}
		int c=hzyySecretarieBiz.updateAfterEvaluation(cycleOutInfo.get("ID"),UCSID,userFlow,request);
		if(c==0)
		{
			model.addAttribute("resultId", "2029302");
			model.addAttribute("resultType","保存失败！");
			return result;
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	/**
	 * 个人信息
	 * @param userFlow
	 * @param model
     * @return
     */
	@RequestMapping(value={"/ownerInfo"},method={RequestMethod.GET})
	public String ownerInfo(String userFlow , Model model){
		String result = "/res/hzyy/secretarie/ownerInfo";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyySecretarieBiz.readUserInfo(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}

		List<Map<String,String>> roles=hzyyAppBiz.readUserRoles(userFlow);
		model.addAttribute("roles", roles);
		return result;
	}

	/**
	 * 科室基地费用
	 * @param userFlow
	 * @param model
     * @return
     */
	@RequestMapping(value={"/deptOrgCost"},method={RequestMethod.GET})
	public String deptOrgCost(String userFlow , Model model,String year){
		String result = "/res/hzyy/secretarie/deptOrgCost";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyySecretarieBiz.readUserInfo(userFlow);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(year))
		{
			year=DateUtil.getYear();
		}
		model.addAttribute("year",year);
		Map<String,String> cost = hzyySecretarieBiz.deptOrgCost(userFlow,year);
		model.addAttribute("cost",cost);
		return result;
	}

}
