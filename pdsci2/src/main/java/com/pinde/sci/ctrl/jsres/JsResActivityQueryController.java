package com.pinde.sci.ctrl.jsres;


import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.core.util.TimeUtil;
import com.pinde.sci.biz.jsres.IJsResActivityBiz;
import com.pinde.sci.biz.jsres.IJsResActivityTargetBiz;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.TeachingActivitySpeakerMapper;
import com.pinde.sci.enums.sch.ActivityTypeEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.dom4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tiger
 *
 *
 */
@Controller
@RequestMapping("/jsres/activityQuery")
public class JsResActivityQueryController extends GeneralController {
	@Autowired
	private IJsResActivityTargetBiz activityTargeBiz;
	@Autowired
	private IJsResActivityBiz activityBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private IJsResPowerCfgBiz jsResPowerCfgBiz;
	@Autowired
	private TeachingActivitySpeakerMapper activitySpeakerMapper;
	@RequestMapping(value="/main")
	public String main(Model model,String  roleFlag, HttpServletRequest request){
		model.addAttribute("roleFlag",roleFlag);
		if(GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			List<SysDept> depts=deptBiz.searchDeptByOrg(GlobalContext.getCurrentUser().getOrgFlow());
			model.addAttribute("depts",depts);
		}else if("teach".equals(roleFlag)||"head".equals(roleFlag) ||"secretary".equals(roleFlag)) {
			List<Map<String, Object>> depts = deptBiz.queryDeptListByFlow(GlobalContext.getCurrentUser().getUserFlow());
			model.addAttribute("depts",depts);
			//判断角色是否可以新增
			String addFlag = "N"; //是否可以新增标识，‘N’ 不可以新增
			String roleFlow = "";
			if("teach".equals(roleFlag)){
				roleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
			}else if("secretary".equals(roleFlag)){
				roleFlow = InitConfig.getSysCfg("res_secretary_role_flow");
			}else {
				roleFlow = InitConfig.getSysCfg("res_head_role_flow");
			}
			List<TeachActivityCfg> cfgList = activityBiz.searchActivityCfgs(roleFlow,GlobalContext.getCurrentUser().getOrgFlow());
			if(null != cfgList && cfgList.size()>0){
				addFlag = "Y";
			}
			model.addAttribute("addFlag",addFlag);
		}else if("doctor".equals(roleFlag)) {
			return "jsres/activity/activityQuery/main2";
		}
		return "jsres/activity/activityQuery/main";
	}
	@RequestMapping(value="/doctorMain")
	public String doctorMain(Model model,String  roleFlag,String isNew,String isEval, HttpServletRequest request){

		String orgFlow="";
		ResDoctor doctor=doctorBiz.readDoctor(GlobalContext.getCurrentUser().getUserFlow());
		if(doctor!=null) {
			if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
				orgFlow = doctor.getSecondOrgFlow();
			} else {
				orgFlow = doctor.getOrgFlow();
			}
		}
		List<Map<String, Object>> depts=deptBiz.searchDeptByDoctor(GlobalContext.getCurrentUser().getUserFlow(),orgFlow);
		model.addAttribute("depts",depts);
		model.addAttribute("doctor",doctor);
		return "jsres/activity/activityQuery/doctorMain";
	}
	@RequestMapping(value="/list")
	public String list(Model model,Integer currentPage,String activityName,String  roleFlag,String  isCurrent,String orderByClo,String orderByFall,
					   String userName,String[] activityTypeId,String deptFlow,String[] deptName,String isNew,String isEval,String isEffective,
					   String startTime,String endTime,String activityStatus,String isUploadImg, HttpServletRequest request) throws DocumentException {
		SysUser curUser=GlobalContext.getCurrentUser();
		SysOrg currentOrg = orgBiz.readSysOrg(curUser.getOrgFlow());
		Map<String,String> param=new HashMap<>();
		param.put("activityName",activityName);
		param.put("userName",userName);
		if(activityTypeId != null) {
			param.put("activityTypeId", String.join(",", activityTypeId));
		}
		param.put("deptFlow",deptFlow);
		if(deptName != null) {
			param.put("deptName", String.join(",", deptName));
		}
		param.put("startTime",startTime);
		param.put("endTime",endTime);
		param.put("isNew",isNew);//最新活动
		param.put("isEval",isEval);//活动评价
		param.put("roleFlag",roleFlag);
		param.put("isCurrent",isCurrent);
		param.put("activityStatus",activityStatus);
		param.put("orderByClo",orderByClo);
		param.put("orderByFall",orderByFall);
		param.put("isUploadImg",isUploadImg);
		assert curUser != null;
		param.put("userFlow",curUser.getUserFlow());
		// 是否有效 1：是 0 ：否
		param.put("isEffective",isEffective);
		model.addAttribute("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
		model.addAttribute("orderByClo", orderByClo);
		model.addAttribute("orderByFall", orderByFall);
		if("doctor".equals(roleFlag))
		{
			String orgFlow="";
			ResDoctor doctor=doctorBiz.readDoctor(curUser.getUserFlow());
			if(doctor!=null) {
				if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
					orgFlow = doctor.getSecondOrgFlow();
				} else {
					orgFlow = doctor.getOrgFlow();
				}
			}
			param.put("orgFlow", orgFlow);
			param.put("activityStatus","pass");//yuh20200513 学员查询教学活动只看已通过
		}else {
			param.put("orgFlow", curUser.getOrgFlow());
		}
		List<SysUserRole> userRoleList = userRoleBiz.getByUserFlow(GlobalContext.getCurrentUser().getUserFlow()); //获取该用户下的所有角色信息
		if("university".equals(roleFlag) ){
			if(StringUtil.isNotEmpty(curUser.getSchool())){
				param.put("school", curUser.getSchool());
			}
			param.put("sendSchoolId", currentOrg.getSendSchoolId());
			param.put("sendSchoolName", currentOrg.getSendSchoolName());
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String,Object>> list=activityBiz.findActivityList2(param);
		for (Map<String,Object> obj: list) {
			for (SysUserRole role:userRoleList) {
				if(obj.containsKey("auditRole")) {
					if (obj.get("auditRole").toString().contains(role.getRoleFlow())) {
						obj.put("audit", "Y");
					}
				}
			}
		}
		Map<String,Object> resultMap=new HashMap<>();
		if(list!=null) {
			for (Map<String,Object> info:list )
			{
				info.put("HaveImg","N");
				String imageUrl= (String) info.get("imageUrl");
				if(StringUtil.isNotBlank(imageUrl))
				{
					Document document=DocumentHelper.parseText(imageUrl);
					Element elem=document.getRootElement();
					List<Element> ec = elem.elements("image");
					if(ec!=null&&ec.size()>0)
					{
						info.put("HaveImg","Y");
					}
				}
				if(!"doctor".equals(roleFlag))
				{
					List<Map<String,Object>>  results=activityBiz.readActivityResults((String) info.get("activityFlow"));
					resultMap.put((String) info.get("activityFlow"),results);
				}
			}
		}
		model.addAttribute("resultMap",resultMap);
		model.addAttribute("list",list);
		model.addAttribute("currentPage",currentPage);
		return "jsres/activity/activityQuery/list";
	}
	@RequestMapping(value="/exportList")
	public void exportList(Model model,Integer currentPage,String activityName,String  roleFlag,String isEffective,
					   String userName,String[] activityTypeId,String deptFlow,String[] deptName,String isNew,String isEval,
					   String startTime,String endTime, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysUser curUser=GlobalContext.getCurrentUser();
		Map<String,String> param=new HashMap<>();
		param.put("activityName",activityName);
		param.put("userName",userName);
		if(activityTypeId != null) {
			param.put("activityTypeId", String.join(",", activityTypeId));
		}
		param.put("deptFlow",deptFlow);
		if(null != deptName) {
			param.put("deptName", String.join(",", deptName));
		}
		param.put("startTime",startTime);
		param.put("endTime",endTime);
		param.put("isNew",isNew);//最新活动
		param.put("isEval",isEval);//活动评价
		param.put("roleFlag",roleFlag);
		param.put("userFlow",curUser.getUserFlow());
		param.put("isEffective",isEffective);
		model.addAttribute("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
		if("doctor".equals(roleFlag))
		{

			String orgFlow="";
			ResDoctor doctor=doctorBiz.readDoctor(curUser.getUserFlow());
			if(doctor!=null) {
				if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
					orgFlow = doctor.getSecondOrgFlow();
				} else {
					orgFlow = doctor.getOrgFlow();
				}
			}
			param.put("orgFlow",doctor.getOrgFlow());
		}else {
			param.put("orgFlow", curUser.getOrgFlow());
		}
//		PageHelper.startPage(currentPage, getPageSize(request));
		List<SysUserRole> userRoleList = userRoleBiz.getByUserFlow(GlobalContext.getCurrentUser().getUserFlow()); //获取该用户下的所有角色信息
		if("university".equals(roleFlag) && StringUtil.isNotEmpty(curUser.getSchool())){
			param.put("school", curUser.getSchool());
		}
		List<Map<String,Object>> list=activityBiz.findActivityList3(param);

		for (Map<String, Object> map : list) {
			if (map.containsKey("IS_EFFECTIVE")) {
				if (map.get("IS_EFFECTIVE").equals("Y")) {
					map.put("effectiveName", "认可");
				} else if (map.get("IS_EFFECTIVE").equals("N")) {
					map.put("effectiveName", "不认可");
				} else {
					map.put("effectiveName", "未操作");
				}
			}
			map.put("HaveImg","否");
			String imageUrl= (String) map.get("imageUrl");
			if(StringUtil.isNotBlank(imageUrl))
			{
				Document document=DocumentHelper.parseText(imageUrl);
				Element elem=document.getRootElement();
				List<Element> ec = elem.elements("image");
				if(ec!=null&&ec.size()>0)
				{
					map.put("HaveImg","是");
				}
			}
		}

		model.addAttribute("list",list);

		String []titles = new String[]{
				"activityName:活动名称",
				"activityTypeName:活动形式",
				"activityAddress:活动地点",
				"userName:主讲人",
				"userCode:用户名",
				"realitySpeaker:实际主讲人",
				//"realitySpeakerUserCode:用户名",
				"deptName:所在科室",
				"startTime:活动开始时间",
				"endTime:活动结束时间",
				"regiestNum:报名人数",
				"scanNum:签到人数",
				//"signingNum:签到人数",
				"evalScore:评价",
				"effectiveName:是否认可",
				"HaveImg:是否有照片"
		};
		String fileName = new String("教学活动信息.xls".getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjsWithWitdh(titles, list, response.getOutputStream());
	}

	@RequestMapping(value="/activityDetail")
	public String activityDetail(Model model,String activityFlow,String roleFlag){
		Map<String,Object> activity=activityBiz.readActivity(activityFlow);
		model.addAttribute("activity",activity);
		//查询附件
		List<PubFile> fileList = fileBiz.findFileByTypeFlow("activity",activityFlow);
		model.addAttribute("fileList",fileList);

		TeachingActivitySpeakerExample example = new TeachingActivitySpeakerExample();
		TeachingActivitySpeakerExample.Criteria criteria = example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andActivityFlowEqualTo(activityFlow);
		example.setOrderByClause("CREATE_TIME DESC");
		List<TeachingActivitySpeaker> list = activitySpeakerMapper.selectByExample(example);
		if (null!=list && list.size()>0){
			model.addAttribute("lastSpeakerName",list.get(0).getSpeakerName());
		}
		TeachingActivityInfo activityInfo = activityBiz.readActivityInfo(activityFlow);
		if (StringUtil.isBlank(activityInfo.getIsLook())){
			activityInfo.setIsLook(GlobalConstant.FLAG_Y);
			activityBiz.saveActivity(activityInfo);
		}
		model.addAttribute("roleFlag", roleFlag);
		return "jsres/activity/activityQuery/activityDetail";
	}
	@RequestMapping(value="/delActivity")
	@ResponseBody
	public String delActivity(Model model,String activityFlow){
		if(StringUtil.isNotBlank(activityFlow))
		{
			TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
			if(info==null)
				return "活动信息不存在，请刷新列表页面！";
//			List<Map<String,Object>>  results=activityBiz.readActivityResults(activityFlow);
//			if(results!=null&&results.size()>0)
//			{
//				return "此活动已有学员扫码，无法删除！";
//			}
			info.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			int c=activityBiz.saveActivity(info);
			if(c==0)
				return GlobalConstant.DELETE_FAIL;
			return GlobalConstant.DELETE_SUCCESSED;
		}else
			return "请选择需要删除的活动！";

	}
	@RequestMapping(value="/effectiveActivity")
	@ResponseBody
	public String effectiveActivity(Model model,String activityFlow,String isEffective){
		if(StringUtil.isNotBlank(activityFlow))
		{
			if(StringUtil.isBlank(isEffective))
			{
				return "请选择需要审核的类型！";
			}
			if(!"Y".equals(isEffective)&&!"N".equals(isEffective))
			{
				return "请选择【认可】还是【不认可】！";
			}
			TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
			if(info==null)
				return "活动信息不存在，请刷新列表页面！";
			info.setIsEffective(isEffective);
			if ("Y".equals(isEffective))
				info.setReasonForDisagreement("");
			int c=activityBiz.saveActivity(info);
			if(c==0)
				return "审核失败";
			return "审核成功";
		}else
			return "请选择需要审核的活动！";

	}

	@RequestMapping(value="/effectiveActivityInfo")
	public String effectiveActivityInfo(Model model,String activityFlow,String isEffective,Integer currentPage){
		if(currentPage == null || currentPage == 0) currentPage = 1;
		model.addAttribute("activityFlow",activityFlow);
		model.addAttribute("isEffective",isEffective);
		model.addAttribute("currentPage",currentPage);
		Map<String, Object> activity = activityBiz.readActivity(activityFlow);
		model.addAttribute("activity",activity);
		return "jsres/activity/activityQuery/effectiveActivityInfo";
	}

	@RequestMapping(value="/saveEffectiveActivityInfo")
	@ResponseBody
	public String saveEffectiveActivityInfo(String activityFlow,String isEffective,String reasonForDisagreement){
		if(StringUtil.isNotBlank(activityFlow))
		{
			if(StringUtil.isBlank(isEffective))
			{
				return "请选择需要审核的类型！";
			}

			TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
			if(info==null)
				return "活动信息不存在，请刷新列表页面！";
			info.setIsEffective(isEffective);
			if (StringUtil.isNotBlank(reasonForDisagreement))
				info.setReasonForDisagreement(reasonForDisagreement);
			int c=activityBiz.saveActivity(info);
			if(c==0)
				return GlobalConstant.SAVE_FAIL;
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return "请选择需要审核的活动！";
	}



	@RequestMapping(value="/effectiveResult")
	@ResponseBody
	public String effectiveResult(Model model,String resultFlow,String isEffective){
		if(StringUtil.isNotBlank(resultFlow))
		{
			if(StringUtil.isBlank(isEffective))
			{
				return "请选择需要审核的类型！";
			}
			if(!"Y".equals(isEffective)&&!"N".equals(isEffective))
			{
				return "请选择【认可】还是【不认可】！";
			}
			TeachingActivityResult info=activityBiz.readResult(resultFlow);
			if(info==null)
				return "学员参加活动结果信息不存在，请刷新列表页面！";
			info.setIsEffective(isEffective);
			int c=activityBiz.saveResult(info);
			if(c==0)
				return "审核失败";
			return "审核成功";
		}else
			return "请选择需要审核的参加活动结果信息！";

	}
	@RequestMapping(value="/joinActivity")
	@ResponseBody
	public String joinActivity(Model model,String activityFlow){
		if(StringUtil.isNotBlank(activityFlow))
		{
			TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
			if(info==null||!GlobalConstant.FLAG_Y.equals(info.getRecordStatus()))
				return "活动信息不存在，请刷新列表页面！";

			if( DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm").compareTo(info.getStartTime())>0)
			{
				return "活动已开始，无法报名！";
			}
			SysUser user=GlobalContext.getCurrentUser();
			TeachingActivityResult result=activityBiz.readRegistInfo(activityFlow,user.getUserFlow());
			if(result!=null&&GlobalConstant.FLAG_Y.equals(result.getIsRegiest()))
			{
				return "你已报名，请勿重复报名！";
			}
			List<TeachingActivityInfo> infos=activityBiz.checkJoinList(activityFlow,user.getUserFlow());
			if(infos!=null&&infos.size()>0)
			{
				TeachingActivityInfo activityInfo=infos.get(0);
				return "已报名同一时间【"+activityInfo.getActivityName()+"】，不能报名！";
			}
			if(result==null)
				result = new TeachingActivityResult();
			result.setActivityFlow(activityFlow);
			result.setUserFlow(user.getUserFlow());
			result.setIsRegiest(GlobalConstant.FLAG_Y);
			result.setRegiestTime(DateUtil.getCurrDateTime());
			result.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			int c=activityBiz.saveRegist(result);
			if(c==0)
				return "报名失败！";
			return "报名成功！";
		}else
			return "请选择将要参加的活动！";

	}
	@RequestMapping(value="/cannelRegiest")
	@ResponseBody
	public String cannelRegiest(Model model,String activityFlow,String resultFlow){
		if(StringUtil.isNotBlank(activityFlow)) {
			TeachingActivityInfo info = activityBiz.readActivityInfo(activityFlow);
			if (info == null || !GlobalConstant.FLAG_Y.equals(info.getRecordStatus()))
				return "活动信息不存在，请刷新列表页面！";
			SysUser user = GlobalContext.getCurrentUser();
			TeachingActivityResult result = activityBiz.readRegistInfo(activityFlow, user.getUserFlow());
			if (result == null) {
				return "你未报名，无法取消报名信息！";
			}
			if (!GlobalConstant.FLAG_Y.equals(result.getIsRegiest()))
			{
				return "你已取消报名！";
			}
			if(GlobalConstant.FLAG_Y.equals(result.getIsScan()))
			{
				return "你已扫码签到，无法取消报名信息！";
			}
			result.setIsRegiest(GlobalConstant.FLAG_N);
			int c=activityBiz.saveRegist(result);
			if(c==0)
				return "取消失败！";
			return "取消成功！";
		}else
			return "请选择将要取消的活动！";

	}
	@RequestMapping(value="/editActivity")
	public String editActivity(Model model,String activityFlow,String role,String action,String scanNum,String currentPage){
		String editTeach="";
		String editZjr="";
		if (StringUtil.isNotBlank(scanNum) && Integer.parseInt(scanNum)>0){
			JsresPowerCfg teachCfg = jsResPowerCfgBiz.read("jsres_" + GlobalContext.getCurrentUser().getOrgFlow() + "_activity_teach");
			if (null !=teachCfg && GlobalConstant.FLAG_Y.equals(teachCfg.getCfgValue())){
				editTeach=GlobalConstant.FLAG_Y;
			}else {
				editTeach=GlobalConstant.FLAG_N;
			}
			model.addAttribute("editTeach",editTeach);
			JsresPowerCfg zjrCfg = jsResPowerCfgBiz.read("jsres_" + GlobalContext.getCurrentUser().getOrgFlow() + "_activity_kzr");
			if (null !=zjrCfg && GlobalConstant.FLAG_Y.equals(zjrCfg.getCfgValue())){
				editZjr=GlobalConstant.FLAG_Y;

			}else {
				editZjr=GlobalConstant.FLAG_N;
			}
			model.addAttribute("editZjr",editZjr);
			model.addAttribute("scanNum",scanNum);
		}
		List<Map<String, Object>> depts=deptBiz.queryDeptListByFlow(GlobalContext.getCurrentUser().getUserFlow());
		model.addAttribute("depts",depts);
		model.addAttribute("user",GlobalContext.getCurrentUser());
		if(StringUtil.isNotBlank(activityFlow))
		{
			List<Map<String,Object>>  results=activityBiz.readActivityResults(activityFlow);
			if(results!=null&&results.size()>0)
			{
				Map<String,Object> activity=activityBiz.readActivity(activityFlow);
				model.addAttribute("activity",activity);
				if (StringUtil.isNotBlank(scanNum) && Integer.parseInt(scanNum)>0
						&& (editZjr.equals(GlobalConstant.FLAG_Y )|| editTeach.equals(GlobalConstant.FLAG_Y))){
					model.addAttribute("nowTime",DateUtil.getCurrDateTime2());
					model.addAttribute("role",role);
					model.addAttribute("action",action);
					String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
					String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");
					String secretaryRoleFlow = InitConfig.getSysCfg("res_secretary_role_flow");
					TeachingActivityInfo activityInfo=activityBiz.readActivityInfo(activityFlow);
					List<SysUser> userList=userBiz.readDeptTeachAndHead(activityInfo.getDeptFlow(),teacherRoleFlow,headRoleFlow, secretaryRoleFlow);
					model.addAttribute("userList",userList);
					List<PubFile> fileList = fileBiz.findFileByTypeFlow("activity",activityFlow);
					model.addAttribute("fileList",fileList);
					return "jsres/activity/activityQuery/editActivity";
				}
				return "jsres/activity/activityQuery/activityDetail";
			}else{
				TeachingActivityInfo activity=activityBiz.readActivityInfo(activityFlow);
				model.addAttribute("activity",activity);
				if(activity!=null)
				{
					String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
					String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");
					String secretaryRoleFlow = InitConfig.getSysCfg("res_secretary_role_flow");
					List<SysUser> userList=userBiz.readDeptTeachAndHead(activity.getDeptFlow(),teacherRoleFlow,headRoleFlow, secretaryRoleFlow);
					model.addAttribute("userList",userList);
//					PubFile file=fileBiz.readFile(activity.getFileFlow());
//					model.addAttribute("file",file);
					//查询附件
					List<PubFile> fileList = fileBiz.findFileByTypeFlow("activity",activityFlow);
					model.addAttribute("fileList",fileList);
				}
			}
		}
		model.addAttribute("role",role);
		model.addAttribute("action",action);
		model.addAttribute("nowTime",DateUtil.getCurrDateTime2());
		if(StringUtil.isBlank(currentPage) || "undefined".equalsIgnoreCase(currentPage)) currentPage = "1";
		model.addAttribute("currentPage",currentPage);
		return "jsres/activity/activityQuery/editActivity";
	}

	@RequestMapping(value="/showActivity")
	public String showActivity(Model model,String activityFlow,String role,String action,String query){
		Map<String, Object> map = activityBiz.readActivity(activityFlow);
		List<Map<String, Object>> depts=deptBiz.queryDeptListByFlow(map.get("createUserFlow").toString());
		model.addAttribute("user",userBiz.readSysUser(map.get("speakerFlow").toString()));
		model.addAttribute("depts",depts);
		model.addAttribute("query",query);//教学活动统计需要显示主讲人身份证信息
		if(StringUtil.isNotBlank(activityFlow))
		{
			List<Map<String,Object>>  results=activityBiz.readActivityResults(activityFlow);
			if(results!=null&&results.size()>0)
			{
				Map<String,Object> activity=activityBiz.readActivity(activityFlow);
				model.addAttribute("activity",activity);
				return "jsres/activity/activityQuery/activityDetail";
			}else{
				TeachingActivityInfo activity=activityBiz.readActivityInfo(activityFlow);
				model.addAttribute("activity",activity);
				if(activity!=null)
				{
					//查询附件
					List<PubFile> fileList = fileBiz.findFileByTypeFlow("activity",activityFlow);
					model.addAttribute("fileList",fileList);
				}
			}
		}
		model.addAttribute("role",role);
		model.addAttribute("action",action);
		return "jsres/activity/activityQuery/showActivity";
	}


	@RequestMapping(value="/saveActivity")
	@ResponseBody
	public String saveActivity(TeachingActivityInfo activity,MultipartFile file,String isRe,String role,Integer currentPage){
		return activityBiz.editActivity(activity,file,isRe, "N",null,role);
	}

	@RequestMapping(value="/saveActivityNew")
	@ResponseBody
	public String saveActivityNew(TeachingActivityInfo activity,HttpServletRequest request,String data,String[] fileFlow) throws ParseException {
		if (StringUtil.isBlank(activity.getActivityFlow())){
			String nowTime = DateUtil.getCurrDateTime2();
			if (nowTime.compareTo(activity.getStartTime())>0){
				return "开始时间不能小于当前时间！";
			}
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String[] fileType=new String[]{"files","kj","zp"};
		HashMap<String,List<MultipartFile>> fileMap = new HashMap();
		for (String s : fileType) {
			List<MultipartFile> fileList = multipartRequest.getFiles(s);
			if (s.equals("zp")){
				for (int i = 0; i < fileList.size(); i++) {
					if (fileList.get(i).getSize() >  2*1024*1024 ){
						return GlobalConstant.UPLOAD_IMG_SIZE_ERROR+2+"M";
					}
				}
			}
			fileMap.put(s,fileList);
		}
		return activityBiz.editActivityNew2(activity,"N", fileMap,data,fileFlow);
	}

	@RequestMapping(value="/saveActivityForAdmin")
	@ResponseBody
	public String saveActivityForAdmin(TeachingActivityInfo activity) throws ParseException {
		TeachingActivityInfo activityInfo = activityBiz.readActivityInfo(activity.getActivityFlow());
		activityInfo.setActivityTypeId(activity.getActivityTypeId());
		activityInfo.setActivityTypeName(ActivityTypeEnum.getNameById(activity.getActivityTypeId()));
		return activityBiz.editActivityForAdmin(activityInfo);
	}

	@RequestMapping(value="/saveActivityFile")
	@ResponseBody
	public Object saveActivityFile(String activityFlow,String fileFlow,MultipartFile file){
		Map<String,String> resp=new HashMap<>();
		resp.put("code","1");
		TeachingActivityInfo activity=activityBiz.readActivityInfo(activityFlow);
		if(activity==null)
		{
			resp.put("code","0");
			resp.put("msg","教学活动信息不存在，请刷新页面！");
			return resp;
		}
		if(!(file!=null&&!file.isEmpty()&&file.getSize()>0)){
			resp.put("code","0");
			resp.put("msg","请选择需要上传的文件！");
			return resp;
		}
		fileFlow=activityBiz.saveActivityFile(fileFlow,file);
		resp.put("fileFlow",fileFlow);
		resp.put("fileName",file.getOriginalFilename());
		if(StringUtil.isBlank(fileFlow))
		{
			resp.put("code","0");
			resp.put("msg","上传文件失败！");
			return resp;
		}
		activity.setFileFlow(fileFlow);
		int c=activityBiz.saveActivity(activity);
		if(c==0)
		{
			resp.put("code","0");
			resp.put("msg","上传文件失败！");
			return resp;
		}
		return resp;
	}

	@RequestMapping(value="/editActivityFile")
	public String editActivityFile(Model model,String activityFlow,String role){
		if(GlobalConstant.USER_LIST_SPE.equals(role)) {
			List<SysDept> depts = deptBiz.searchDeptBySpe(GlobalContext.getCurrentUser().getResTrainingSpeId(),GlobalContext.getCurrentUser().getOrgFlow());
			model.addAttribute("depts",depts);
		}else {
			List<Map<String, Object>> depts = deptBiz.queryDeptListByFlow(GlobalContext.getCurrentUser().getUserFlow());
			model.addAttribute("depts", depts);
		}
		model.addAttribute("user",GlobalContext.getCurrentUser());
		if(StringUtil.isNotBlank(activityFlow)) {
			TeachingActivityInfo activity=activityBiz.readActivityInfo(activityFlow);
			model.addAttribute("activity",activity);
			if(activity!=null) {
				String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
				String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");
				String professionalBaseAdminRoleFlow = InitConfig.getSysCfg("res_professionalBase_admin_role_flow");
				List<String> userFlows=new ArrayList<>();
				List<SysUser> users=new ArrayList<>();
				List<SysUser> userList=userBiz.readDeptTeachAndHead(activity.getDeptFlow(),teacherRoleFlow,headRoleFlow, "");
				if(userList!=null&&userList.size()>0) {
					for (SysUser su:userList ) {
						if(!userFlows.contains(su.getUserFlow())) {
							userFlows.add(su.getUserFlow());
							users.add(su);
						}
					}
				}
				if(GlobalConstant.USER_LIST_SPE.equals(role)) {
					userList=userBiz.readUserBySpe(activity.getDeptFlow(),professionalBaseAdminRoleFlow,GlobalContext.getCurrentUser().getResTrainingSpeId(),GlobalContext.getCurrentUser().getOrgFlow());
					if(userList!=null&&userList.size()>0) {
						for (SysUser su:userList ) {
							if(!userFlows.contains(su.getUserFlow())) {
								userFlows.add(su.getUserFlow());
								users.add(su);
							}
						}
					}
				}
				model.addAttribute("userList",users);
				//查询附件
				List<PubFile> fileList = fileBiz.findFileByTypeFlow("activity",activityFlow);
				model.addAttribute("fileList",fileList);
			}
		}
		model.addAttribute("role",role);
		return "jsres/activity/activityQuery/editActivityFile";
	}

	@RequestMapping(value="/lookSearchFile")
	public String lookSearchFile(Model model,String activityFlow,String role){
		if(GlobalConstant.USER_LIST_SPE.equals(role)) {
			List<SysDept> depts = deptBiz.searchDeptBySpe(GlobalContext.getCurrentUser().getResTrainingSpeId(),GlobalContext.getCurrentUser().getOrgFlow());
			model.addAttribute("depts",depts);
		}else {
			List<Map<String, Object>> depts = deptBiz.queryDeptListByFlow(GlobalContext.getCurrentUser().getUserFlow());
			model.addAttribute("depts", depts);
		}
		model.addAttribute("user",GlobalContext.getCurrentUser());
		if(StringUtil.isNotBlank(activityFlow)) {
			TeachingActivityInfo activity=activityBiz.readActivityInfo(activityFlow);
			model.addAttribute("activity",activity);
			if(activity!=null) {
				String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
				String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");
				String professionalBaseAdminRoleFlow = InitConfig.getSysCfg("res_professionalBase_admin_role_flow");
				List<String> userFlows=new ArrayList<>();
				List<SysUser> users=new ArrayList<>();
				List<SysUser> userList=userBiz.readDeptTeachAndHead(activity.getDeptFlow(),teacherRoleFlow,headRoleFlow, "");
				if(userList!=null&&userList.size()>0) {
					for (SysUser su:userList ) {
						if(!userFlows.contains(su.getUserFlow())) {
							userFlows.add(su.getUserFlow());
							users.add(su);
						}
					}
				}
				if(GlobalConstant.USER_LIST_SPE.equals(role)) {
					userList=userBiz.readUserBySpe(activity.getDeptFlow(),professionalBaseAdminRoleFlow,GlobalContext.getCurrentUser().getResTrainingSpeId(),GlobalContext.getCurrentUser().getOrgFlow());
					if(userList!=null&&userList.size()>0) {
						for (SysUser su:userList ) {
							if(!userFlows.contains(su.getUserFlow())) {
								userFlows.add(su.getUserFlow());
								users.add(su);
							}
						}
					}
				}
				model.addAttribute("userList",users);
				//查询附件
				List<PubFile> fileList = fileBiz.findFileByTypeFlow("activity",activityFlow);
				model.addAttribute("fileList",fileList);
			}
		}
		model.addAttribute("role",role);
		return "jsres/activity/activityQuery/lookSearchFile";
	}

	@RequestMapping(value="/saveActivityFiles")
	@ResponseBody
	public String saveActivityFiles(String activityFlow,HttpServletRequest request,String[] fileFlow){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String[] fileType=new String[]{"files","kj","zp"};
		HashMap<String,List<MultipartFile>> fileMap = new HashMap();
		for (String s : fileType) {
			List<MultipartFile> fileList = multipartRequest.getFiles(s);
			if (s.equals("zp")){
				for (int i = 0; i < fileList.size(); i++) {
					if (fileList.get(i).getSize() >  2*1024*1024 ){
						return GlobalConstant.UPLOAD_IMG_SIZE_ERROR+2+"M";
					}
				}
			}
			fileMap.put(s,fileList);
		}
		return activityBiz.editActivityFiles(activityFlow,fileMap,fileFlow);
	}

	@RequestMapping(value="/loadTeacherAndHead")
	@ResponseBody
	public Object loadTeacherAndHead(String deptFlow){
		if(StringUtil.isNotBlank(deptFlow)){
			String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
			String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");
			String secretaryRoleFlow = InitConfig.getSysCfg("res_secretary_role_flow");
			List<SysUser> userList=userBiz.readDeptTeachAndHead(deptFlow,teacherRoleFlow,headRoleFlow, secretaryRoleFlow);
			return userList;
		}
		return null;
	}
	@RequestMapping(value="/showEval")
	public String showEval(Model model,String activityFlow,String roleFlag){
		TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
		model.addAttribute("activity",info);
		model.addAttribute("user",GlobalContext.getCurrentUser());
		//评价的指标
		List<Map<String,Object>> targets=activityTargeBiz.readActivityTargetEvals(activityFlow);
		model.addAttribute("targets",targets);
		//评价人员
		List<Map<String,Object>> results=activityBiz.readActivityResults(activityFlow);
		model.addAttribute("results",results);
		//评价人员评分详情
		Map<String,Object> evalDetailMap=new HashMap<>();
		Map<String,Object> evalRemarksMap=new HashMap<>();
		if(results!=null)
		{
			for(Map<String,Object> r:results)
			{
				List<TeachingActivityEval> evals=activityBiz.readActivityResultEvals(String.valueOf(r.get("resultFlow")));
				if(evals!=null)
				{
					for(TeachingActivityEval e:evals)
					{
						evalDetailMap.put(e.getResultFlow()+e.getTargetFlow(),e.getEvalScore());
						evalRemarksMap.put(e.getResultFlow()+e.getTargetFlow(),e.getEvalRemarks());
					}
				}
			}
		}
		model.addAttribute("evalDetailMap",evalDetailMap);
		model.addAttribute("evalRemarksMap",evalRemarksMap);
		//查看基地 教学活动评价配置
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		JsresPowerCfg orgApprove = jsResPowerCfgBiz.read("jsres_"+orgFlow+"_org_ctrl_approve_activity");//教学活动评价配置
		JsresPowerCfg approve = jsResPowerCfgBiz.read("jsres_"+orgFlow+"_org_approve_activity");//教学活动评价配置评审类型
		if (null!=orgApprove && null!=approve && StringUtil.isNotNullAndEquala(approve.getCfgValue(),orgApprove.getCfgValue(),"Y")){
			model.addAttribute("approve","Y");
		}
		return "jsres/activity/activityQuery/showEval";
	}
	@RequestMapping(value="/showRegistEval")
	public String showRegistEval(Model model,String activityFlow,String roleFlag){

		//评价人员
		List<Map<String,Object>> results=activityBiz.readActivityRegists(activityFlow);
		model.addAttribute("results",results);
		return "jsres/activity/activityQuery/showRegistEval";
	}
	@RequestMapping(value="/showEvalType")
	public String showEvalType(Model model,String activityFlow,String roleFlag){

		List<Map<String,Object>> regists=activityBiz.readActivityRegists(activityFlow);
		List<Map<String,Object>> results=activityBiz.readActivityResults(activityFlow);
		int scanNum=0;
		if(results!=null) scanNum=results.size();
		int registNum=0;
		if(regists!=null) registNum=regists.size();
		model.addAttribute("scanNum",scanNum);
		model.addAttribute("registNum",registNum);
		return "jsres/activity/activityQuery/showEvalType";
	}

	@RequestMapping(value="/exportSiginList")
	public void exportSiginList(Model model,String activityFlow ,HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Map<String,Object>> results=activityBiz.readActivityResults(activityFlow);
		String []titles = new String[]{
				"userName:姓名",
				"sessionNumber:年级",
				"speName:专业",
				"doctorTypeName:人员类型",
				"siginTime:签到时间",
				"siginTime2:签退时间",
				"evalScore:评分"
		};
		TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
		ExcleUtile.exportSimpleExcleByObjsWithWitdh(titles, results, response.getOutputStream());
		String fileName = new String((info.getActivityName()+"-签到学员表.xls").getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping(value="/exportRegiestList")
	public void exportRegiestList(Model model,String activityFlow ,HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Map<String,Object>> results=activityBiz.readActivityRegists(activityFlow);
		String []titles = new String[]{
				"userName:姓名",
				"sessionNumber:年级",
				"speName:专业",
				"doctorTypeName:人员类型",
				"regiestTime1:报名时间"
		};
		TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
		ExcleUtile.exportSimpleExcleByObjsWithWitdh(titles, results, response.getOutputStream());
		String fileName = new String((info.getActivityName()+"-报名学员表.xls").getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping(value="/showDocEval")
	public String showDocEval(Model model,String resultFlow){
		//评价人员
		TeachingActivityResult result=activityBiz.readResult(resultFlow);
		model.addAttribute("result",result);
		//评价人员评分详情
		Map<String,Object> evalDetailMap=new HashMap<>();
		Map<String,Object> evalRemarksMap=new HashMap<>();
		if(result!=null)
		{
			//评价的指标
			List<Map<String,Object>> targets=activityTargeBiz.readActivityTargetEvals(result.getActivityFlow());
			model.addAttribute("targets",targets);
			List<TeachingActivityEval> evals=activityBiz.readActivityResultEvals(resultFlow);
			if(evals!=null)
			{
				for(TeachingActivityEval e:evals)
				{
					evalDetailMap.put(e.getResultFlow()+e.getTargetFlow(),e.getEvalScore());
					evalRemarksMap.put(e.getResultFlow()+e.getTargetFlow(),e.getEvalRemarks());
				}
			}
		}
		model.addAttribute("evalDetailMap",evalDetailMap);
		model.addAttribute("evalRemarksMap",evalRemarksMap);
		return "jsres/activity/activityQuery/showDocEval";
	}
	@RequestMapping(value="/checkEval")
	@ResponseBody
	public String checkEval(String resultFlow){
		if(StringUtil.isNotBlank(resultFlow)){
			TeachingActivityResult result=activityBiz.readResult(resultFlow);
			if(result==null)
			{
				return "你未参加该活动，无法评价";
			}
			if(result!=null&&result.getEvalScore()!=null)
			{
				return "该活动已评价，请刷新页面！";
			}else{
				return "";
			}
		}else {
			return "请选择需要评价的活动！";
		}
	}
	@RequestMapping(value="/evalInfo")
	public String evalInfo(Model model,String resultFlow){
		//评价人员
		TeachingActivityResult result=activityBiz.readResult(resultFlow);
		model.addAttribute("result",result);
		//评分项
		List<TeachingActivityInfoTarget> targets=activityTargeBiz.readActivityTargets(result.getActivityFlow());
		model.addAttribute("targets",targets);
		return "jsres/activity/activityQuery/evalInfo";
	}
	@RequestMapping(value = {"/saveEvalInfo"}, method = {RequestMethod.POST,RequestMethod.GET})

	public	@ResponseBody String saveEvalInfo(String evals, String resultFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws DocumentException {
		if(StringUtil.isBlank(resultFlow))
		{
			return "请选择需要评价的活动！";
		}else{
			TeachingActivityResult result=activityBiz.readResult(resultFlow);
			if(result==null)
			{
				return "你未参加该活动，无法评价";
			}
			if(result!=null&&result.getEvalScore()!=null)
			{
				return "该活动已评价，请刷新页面！";
			}
		}
		if(StringUtil.isBlank(evals))
		{
			return "请选择评分！";
		}
		List<TeachingActivityEval> activityEvals=null;
		try {
			activityEvals=JSON.parseArray(evals, TeachingActivityEval.class);
		}catch (Exception e){
			return "提交数据格式不正确！";
		}
		if(activityEvals==null||activityEvals.size()<=0)
		{
			return "请选择评分！";
		}
		int count=activityBiz.saveEvalInfo(activityEvals, resultFlow);
		if(count==0)
		{
			return GlobalConstant.SAVE_FAIL;
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}
	@RequestMapping("/signUrl")
	public String signUrl(String activityFlow,Model model){
		//二维码显示的内部信息
		model.addAttribute("activityFlow",activityFlow);
		TeachingActivityInfo activity=activityBiz.readActivityInfo(activityFlow);
		model.addAttribute("activity",activity);
		return "jsres/activity/activityQuery/activityCode";
	}

	//下载附件
	@RequestMapping(value = {"/downFile" }, method = RequestMethod.GET)
	public void fileDown(String fileFlow, final HttpServletResponse response) throws Exception{
		PubFile file = this.fileBiz.readFile(fileFlow);
		downPubFile(file,response);
	}

	public void downPubFile(PubFile file, HttpServletResponse response) throws Exception {
		/*文件是否存在*/
		Boolean fileExists = false;
		if(file !=null){
			byte[] data=null;
			long dataLength = 0;
            /*如果上传类型为“1”(文件保存在磁盘)，且文件相对路径不为空*/
			if(StringUtil.isNotBlank(file.getFilePath())&&StringUtil.isNotBlank(InitConfig.getSysCfg("upload_base_dir"))){
                /*获取文件物理路径*/
				String filePath =InitConfig.getSysCfg("upload_base_dir")+file.getFilePath();
				File downLoadFile = new File(filePath);
                /*文件是否存在*/
				if(downLoadFile.exists()){
					InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
					data = new byte[fis.available()];
					dataLength = downLoadFile.length();
					fis.read(data);
					fis.close();
					fileExists = true;
				}
			}
			if(fileExists) {
				try {

					String fileName = file.getFileName();
					response.reset();
					response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gbk"),"ISO8859-1" )  + "\"");
					response.addHeader("Content-Length", "" + dataLength);
					response.setContentType("application/octet-stream;charset=UTF-8");
					OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
					if (data != null) {
						outputStream.write(data);
					}
					outputStream.flush();
					outputStream.close();
				}catch (IOException e){
					fileExists = false;
				}
			}
		}else {
			fileExists = false;
		}
		if(!fileExists){
            /*设置页面编码为UTF-8*/
			response.setHeader("Content-Type","text/html;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			outputStream.write("<a href='javascript:history.go(-1)'>未发现文件,点击返回上一页</a>".getBytes("UTF-8"));//将字符串转化为一个字节数组（以UTF-8编码格式，默认本地编码）
			outputStream.flush();
			outputStream.close();
		}
	}

	@RequestMapping(value="/upload")
	public String upload(String activityFlow,String isUpload,Model model) throws DocumentException{

		TeachingActivityInfo activity=activityBiz.readActivityInfo(activityFlow);
		model.addAttribute("activity",activity);
		if(activity!=null)
		{
			String content=activity.getImageUrl();
			if(StringUtil.isBlank(content))
			{
				Document dom = DocumentHelper.createDocument();
				Element root= dom.addElement("ActivityImages");
				content=root.asXML();
				activity.setImageUrl(content);
				activityBiz.saveActivity(activity);
			}
			List<Map<String, String>> imageList=activityBiz.parseImageXml(content);
			model.addAttribute("imageList", imageList);
		}
		model.addAttribute("isUpload", isUpload);
		return "jsres/activity/activityQuery/uploadImage";
	}
	@RequestMapping(value="/activityImgUpload")
	@ResponseBody
	public Map<String,String> activityImgUpload(String activityFlow,MultipartFile checkFile){
		Map<String, String> map = null;
		if(checkFile!=null && checkFile.getSize() > 0){
			String fileAddress="activityFlie";
			map=activityBiz.activityImg(activityFlow,checkFile,fileAddress);
		}
		return map;
	}

	@RequestMapping(value="/activityImgDelete")
	@ResponseBody
	public String activityImgDelete(String activityFlow, String imageFlow) throws DocumentException{
		TeachingActivityInfo activity=activityBiz.readActivityInfo(activityFlow);
		if(StringUtil.isNotBlank(activity.getImageUrl())) {
			String content = activity.getImageUrl();
			Document document = DocumentHelper.parseText(content);
			Element elem = document.getRootElement();
			Node delNode = elem.selectSingleNode("image[@imageFlow='" + imageFlow + "']");
			if (delNode != null) {
				delNode.detach();
				activity.setImageUrl(document.asXML());
				activityBiz.saveActivity(activity);
				return GlobalConstant.OPRE_SUCCESSED_FLAG;
			} else {
				return GlobalConstant.OPRE_FAIL_FLAG;
			}
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}

	@RequestMapping(value="/activityMain")
	public String activityMain(Model model,String roleFlag,String month,String flag) throws Exception{
		SysUser curUser=GlobalContext.getCurrentUser();
		SysOrg currentOrg = orgBiz.readSysOrg(curUser.getOrgFlow());
		Map<String,String> param=new HashMap<>();
		if("university".equals(roleFlag) ){
			if(StringUtil.isNotEmpty(curUser.getSchool())){
				param.put("school", curUser.getSchool());
			}
			param.put("sendSchoolId", currentOrg.getSendSchoolId());
			param.put("sendSchoolName", currentOrg.getSendSchoolName());
		}
		param.put("roleFlag",roleFlag);
		param.put("userFlow",curUser.getUserFlow());
		if("doctor".equals(roleFlag))
		{

			String orgFlow="";
			ResDoctor doctor=doctorBiz.readDoctor(curUser.getUserFlow());
			if(doctor!=null) {
				if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
					orgFlow = doctor.getSecondOrgFlow();
				} else {
					orgFlow = doctor.getOrgFlow();
				}
			}
			param.put("orgFlow", orgFlow);
		}else {
			param.put("orgFlow", curUser.getOrgFlow());
		}

		if(StringUtil.isBlank(month)) {
			month = DateUtil.getMonth();
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Date dt = sdf.parse(month);
			Calendar time = Calendar.getInstance();
			time.setTime(dt);
			if(flag.equals("Y")){
				time.add(Calendar.MONTH, 1);
			}else{
				time.add(Calendar.MONTH, -1);
			}
			Date dt1 = time.getTime();
			month = sdf.format(dt1);
		}
		String firstDay=DateUtil.setFirstDayOfMonth(month);
		String lastDay=DateUtil.setLastDateOfMonth(month);
		firstDay =DateUtil.getFirstDateOfWeek(firstDay);
		lastDay =DateUtil.getLastDateOfWeek(lastDay);
		List<String> times= TimeUtil.findDates2(firstDay,lastDay);
		int trRows=times.size()/7;
//		System.out.println(trRows);
		model.addAttribute("trRows",trRows);
		Map<String,Map<String,Object>> tableMap=new HashMap<>();
		String  table[][]=new String[trRows][7];
		for(int i=0;i<trRows;i++)
			for(int j=0;j<7;j++)
			{
				int index=i*7+j;
				Map<String,Object> data=new HashMap<>();
				data.put("date",times.get(index));
				param.put("startTime",times.get(index)+" "+"00:00");
				param.put("endTime",times.get(index)+" "+"23:59");
				List<Map<String,Object>> list=activityBiz.findActivityList2(param);
				if(list!=null&&list.size()>0) {
					StringBuffer sb = new StringBuffer();
					data.put("num", list.size() + "");
					data.put("contentTime", "开始时间："+DateUtil.transDateTime((String)list.get(0).get("startTime"),"yyyy-MM-dd HH:mm", "HH:mm") );
					data.put("contentName", "活动名称："+ list.get(0).get("activityName"));
					for(int k = 0;k<list.size();k++){
						if(k==list.size()-1){
							sb.append(list.get(k).get("activityFlow"));
						}else{
							sb.append(list.get(k).get("activityFlow")+",");
						}
					}
					data.put("activityFlows",sb);
				}
				data.put("month", DateUtil.transDateTime(times.get(index), "yyyy-MM-dd", "yyyy-MM"));
				data.put("day", DateUtil.transDateTime(times.get(index), "yyyy-MM-dd", "dd"));
				tableMap.put(i + "-" + j, data);

			}
//		System.out.println(JSON.toJSONString(tableMap));
		model.addAttribute("tableMap",tableMap);
		model.addAttribute("month",month);

		model.addAttribute("roleFlag",roleFlag);
		return "jsres/activity/activityQuery/eventCalendar";
	}

	@RequestMapping(value="/getActivity")
	public String getActivity(Model model,String activityFlows,String roleFlag){
		SysUser user = GlobalContext.getCurrentUser();
		String[] activityFlowList = activityFlows.split(",");
		List<String> afList = Arrays.asList(activityFlowList);
		Map<String,Object> param = new HashMap<>();
		param.put("afList",afList);
		param.put("orgFlow",user.getOrgFlow());
		List<Map<String, Object>> activityInfos = activityBiz.getActivitys(param);
		model.addAttribute("activityFlows",activityFlows);
		model.addAttribute("activityInfos",activityInfos);
		return "jsres/activity/activityQuery/activityInfoList";
	}

	@RequestMapping(value = "/exportInfo")
	public void exportInfo(HttpServletResponse response,String pageType,String activityFlows,String flag) throws Exception {
		String[] head = new String[]{};
		String[] titles = null;
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFCellStyle styleRight = wb.createCellStyle(); //居中
		styleRight.setAlignment(HorizontalAlignment.CENTER);
		styleRight.setVerticalAlignment(VerticalAlignment.CENTER);

		SysUser user = GlobalContext.getCurrentUser();
		String[] activityFlowList = activityFlows.split(",");
		List<String> afList = Arrays.asList(activityFlowList);
		Map<String,Object> param = new HashMap<>();
		param.put("afList",afList);
		param.put("orgFlow",user.getOrgFlow());
		List<Map<String, Object>> activityInfos = activityBiz.getActivitys(param);

		HSSFSheet sheet = wb.createSheet("sheet1");
		HSSFRow row = sheet.createRow(0);//第一行
		titles = new String[]{
				"活动名称",
				"活动形式",
				"活动地点",
				"主讲人",
				"所在科室",
				"活动时间",
				"实际主讲人"
		};
		HSSFCell cellTitle3 = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle3 = row.createCell(i);
			cellTitle3.setCellValue(titles[i]);
			cellTitle3.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 506);
		}
		int rowNum = 1;
		String[] resultList = null;
		if(activityInfos!=null && activityInfos.size()>0){
			for (int i = 0;i<activityInfos.size();i++,rowNum++) {
				HSSFRow rowTwo = sheet.createRow(rowNum);
				String activityTime = (String)activityInfos.get(i).get("startTime")+"~"+(String)activityInfos.get(i).get("endTime");
				resultList = new String[]{
						(String)activityInfos.get(i).get("activityName"),
						(String)activityInfos.get(i).get("activityTypeName"),
						(String)activityInfos.get(i).get("activityAddress"),
						(String)activityInfos.get(i).get("userName"),
						(String)activityInfos.get(i).get("deptName"),
						activityTime,
						(String)activityInfos.get(i).get("realitySpeaker"),
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowTwo.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(resultList[j]);
				}
			}
		}
		String fileName = "教学活动信息.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}


	/**
	 * 教学活动统计 查询条件
	 * @return
	 */
	@RequestMapping(value="/activityStatistics")
	public String activityStatistics(Model model){
		SysOrg sysorg = new SysOrg();
		sysorg.setOrgProvId("320000");
		sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
		model.addAttribute("orgs", orgs);
		return "jsres/activity/activityQuery/activityMain";
	}


	/**
	 * 教学活动统计列表
	 * @param model
	 * @param request
	 * @param currentPage
	 * @param activityName	活动名称
	 * @param orgFlow	培训基地
	 * @param deptName	所在部门
	 * @param speakerName	主讲人
	 * @param startTime	活动开始时间
	 * @param endTime	活动结束时间
	 * @param realitySpeaker	实际主讲人
	 * @return
	 */
	@RequestMapping(value="/activityStatisticsList")
	public String activityStatisticsList(Model model,HttpServletRequest request,Integer currentPage,
										 String activityName,String orgFlow,String deptName, String speakerName,
										 String startTime,String endTime,String realitySpeaker){
		Map<String,String> param=new HashMap<>();
		param.put("activityName",activityName);
		param.put("orgFlow",orgFlow);
		param.put("speakerName",speakerName);
		param.put("deptName",deptName);
		param.put("startTime",startTime);
		param.put("endTime",endTime);
		param.put("realitySpeaker",realitySpeaker);
		param.put("year",DateUtil.getYear());
		param.put("orgName",GlobalContext.getCurrentUser().getOrgName());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, Object>> list = activityBiz.activityStatisticsList(param);
		model.addAttribute("list",list);
		return "jsres/activity/activityQuery/activityList";
	}

	/**
	 * 教学活动统计-签到人数详情
	 * @param model
	 * @param activityFlow	教学活动流水号
	 * @return
	 */
	@RequestMapping(value="/queryJoin")
	public String queryJoin(Model model,String activityFlow){
		model.addAttribute("list",activityBiz.queryJoin(activityFlow,GlobalContext.getCurrentUser().getOrgName()));
		return "jsres/activity/activityQuery/queryJoinList";
	}

	/**
	 * 教学活动统计列表导出数据
	 * @param activityName 教学活动名称
	 * @param orgFlow	培训单位
	 * @param deptName	所在科室
	 * @param speakerName	主讲人
	 * @param startTime	活动开始时间
	 * @param endTime	活动结束时间
	 * @param realitySpeaker	实际主讲人
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/activityStatisticsExport")
	public void activityStatisticsExport(String activityName,String orgFlow,String deptName, String speakerName,
										 String startTime,String endTime,String realitySpeaker, HttpServletResponse response) throws Exception {
		Map<String,String> param=new HashMap<>();
		param.put("activityName",activityName);
		param.put("orgFlow",orgFlow);
		param.put("speakerName",speakerName);
		param.put("deptName",deptName);
		param.put("startTime",startTime);
		param.put("endTime",endTime);
		param.put("realitySpeaker",realitySpeaker);
		param.put("year",DateUtil.getYear());
		param.put("orgName",GlobalContext.getCurrentUser().getOrgName());
		List<Map<String, Object>> list = activityBiz.activityStatisticsExportList(param);
		String []titles = new String[]{
				"activityName:活动名称",
				"orgName:培训基地",
				"activityTpyeName:活动形式",
				"activityAddress:活动地点",
				"speakerName:主讲人",
				"deptName:所在科室",
				"speakerPhone:联系方式",
				"idNo:身份证号",
				"startTime:活动开始时间",
				"endTime:活动结束时间",
				"realitySpeaker:实际主讲人",
				"joinNum:签到人数"
		};
		String fileName = new String("教学活动信息.xls".getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		ExcleUtile.exportSimpleExcleByObjsAllString(titles, list, response.getOutputStream());
		response.setContentType("application/octet-stream;charset=UTF-8");
	}


	@RequestMapping(value="/IndicatorExport")
	public void IndicatorExport(HttpServletResponse response,String activityFlow) throws Exception {
		String[] titles = null;
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //水平垂直居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER_SELECTION);
		styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCenter.setBorderBottom(BorderStyle.THIN);
		styleCenter.setBorderLeft(BorderStyle.THIN);
		styleCenter.setBorderRight(BorderStyle.THIN);
		styleCenter.setBorderTop(BorderStyle.THIN);

		HSSFCellStyle fontCenStyle = wb.createCellStyle();	//粗体居中显示
		HSSFFont font = wb.createFont();	//设置字体
		font.setBold(true);//粗体显示
		fontCenStyle.setFont(font);
		fontCenStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
		fontCenStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		fontCenStyle.setBorderBottom(BorderStyle.THIN);
		fontCenStyle.setBorderLeft(BorderStyle.THIN);
		fontCenStyle.setBorderRight(BorderStyle.THIN);
		fontCenStyle.setBorderTop(BorderStyle.THIN);

		HSSFSheet sheet = wb.createSheet("指标评分情况表");
		sheet.setColumnWidth(0,5000);
		sheet.setColumnWidth(1,20000);
		sheet.setColumnWidth(2,5000);
		sheet.setColumnWidth(3,5000);
		sheet.setColumnWidth(4,5000);
		sheet.setColumnWidth(5,5000);
		sheet.setColumnWidth(6,5000);
		sheet.setColumnWidth(7,5000);
		sheet.setColumnWidth(8,5000);


		Map<String,String> param=new HashMap<>();
		param.put("activityFlow",activityFlow);
		List<Map<String,Object>> list=activityBiz.findActivityList(param);
		if (null!=list && list.size()>0){
			HSSFRow rowTitle = sheet.createRow(0);
			titles = new String[]{
					"活动名称",
					"活动形式",
					"活动地点",
					"主讲人",
					"所在科室",
					"活动开始时间",
					"活动结束时间",
					"评价",
					"签到人数"
			};
			HSSFCell cellTitle = null;
			for (int i = 0; i < titles.length; i++) {
				cellTitle = rowTitle.createCell(i);
				cellTitle.setCellValue(titles[i]);
				cellTitle.setCellStyle(fontCenStyle);
			}

			Map<String, Object> map = list.get(0);
			HSSFRow rowTitle2 = sheet.createRow(1);//第一行
			titles = new String[]{
					String.valueOf(null==map.get("activityName")?"":map.get("activityName")),
					String.valueOf(null==map.get("activityTypeName")?"":map.get("activityTypeName")),
					String.valueOf(null==map.get("activityAddress")?"":map.get("activityAddress")),
					String.valueOf(null==map.get("userName")?"":map.get("userName")),
					String.valueOf(null==map.get("deptName")?"":map.get("deptName")),
					String.valueOf(null==map.get("startTime")?"":map.get("startTime")),
					String.valueOf(null==map.get("endTime")?"":map.get("endTime")),
					String.valueOf(null==map.get("evalScore")?"":map.get("evalScore")),
					String.valueOf(null==map.get("scanNum")?"":map.get("scanNum"))
			};
			HSSFCell cellTitle2 = null;
			for (int i = 0; i < titles.length; i++) {
				cellTitle2 = rowTitle2.createCell(i);
				cellTitle2.setCellValue(titles[i]);
				cellTitle2.setCellStyle(styleCenter);
			}
		}

		HSSFRow row = sheet.createRow(4);//第一行
		titles = new String[]{
				"学员名称",
				"评价指标",
				"分数",
				"签到时间",
				"签退时间",
				"年级",
				"专业",
				"人员类型",
				"备注"
		};
		HSSFCell cellTitle3 = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle3 = row.createCell(i);
			cellTitle3.setCellValue(titles[i]);
			cellTitle3.setCellStyle(fontCenStyle);
		}

		TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
		//评价的指标
		List<Map<String,Object>> targets=activityTargeBiz.readActivityTargetEvals(activityFlow);
		//评价人员
		List<Map<String,Object>> results=activityBiz.readActivityResults(activityFlow);
		//评价人员评分详情
		List<TeachingActivityEval> evalList = activityTargeBiz.readTeachingEvals(activityFlow);
		int num=5; //总行数
		if(results!=null)
		{
			for(Map<String,Object> r:results) {
//				List<TeachingActivityEval> evals=activityBiz.readActivityResultEvals(String.valueOf(r.get("resultFlow")));
				String resultFlow = String.valueOf(r.get("resultFlow"));
				List<TeachingActivityEval> evals = evalList.stream().filter(e -> e.getResultFlow().equals(resultFlow)).collect(Collectors.toList());
				int startNum=num;
				if(evals!=null && evals.size()>0) {
					for (int i = 0; i < evals.size(); i++) {
						HSSFRow rowNum = sheet.createRow(num);
						String score="";
						if (StringUtil.isNotBlank(String.valueOf(evals.get(i).getEvalScore()))){
							score=String.valueOf(evals.get(i).getEvalScore());
						}
						if (i==0){
							titles = new String[]{
									String.valueOf(r.get("userName")),
									String.valueOf(evals.get(i).getTargetName()),
									score,
									null== r.get("siginTime")?"":String.valueOf(r.get("siginTime")),
									null== r.get("siginTime2")?"":String.valueOf(r.get("siginTime2")),
									null== r.get("sessionNumber")?"":String.valueOf(r.get("sessionNumber")),
									null== r.get("speName")?"":String.valueOf(r.get("speName")),
									null== r.get("doctorTypeName")?"":String.valueOf(r.get("doctorTypeName")),
									null==evals.get(i).getEvalRemarks()?"":String.valueOf(evals.get(i).getEvalRemarks()),
							};
						}else {
							titles = new String[]{
									String.valueOf(r.get("userName")),
									String.valueOf(evals.get(i).getTargetName()),
									score,
									"",
									"",
									"",
									"",
									"",
									null==evals.get(i).getEvalRemarks()?"":String.valueOf(evals.get(i).getEvalRemarks()),
							};
						}

						HSSFCell cellTitle = null;
						for (int j = 0; j < titles.length; j++) {
							cellTitle = rowNum.createCell(j);
							cellTitle.setCellValue(titles[j]);
							cellTitle.setCellStyle(styleCenter);
						}
						if (i==0){
							CellRangeAddress address = new CellRangeAddress(num, num+evals.size()-1, 0, 0);//起始行，终止行，起始列，终止列
							sheet.addMergedRegion(address);
						}
						num++;
					}
				}else {
					for (int i = 0; i < targets.size(); i++) {
						HSSFRow rowNum = sheet.createRow(num);
						if (i==0){
							titles = new String[]{
									String.valueOf(r.get("userName")),
									String.valueOf(targets.get(i).get("targetName")),
									"",
									null== r.get("siginTime")?"":String.valueOf(r.get("siginTime")),
									null== r.get("siginTime2")?"":String.valueOf(r.get("siginTime2")),
									null== r.get("sessionNumber")?"":String.valueOf(r.get("sessionNumber")),
									null== r.get("speName")?"":String.valueOf(r.get("speName")),
									null== r.get("doctorTypeName")?"":String.valueOf(r.get("doctorTypeName")),
									""
							};
						}else {
							titles = new String[]{
									String.valueOf(r.get("userName")),
									String.valueOf(targets.get(i).get("targetName")),
									"",
									"",
									"",
									"",
									"",
									"",
									""
							};
						}

						HSSFCell cellTitle = null;
						for (int j = 0; j < titles.length; j++) {
							cellTitle = rowNum.createCell(j);
							cellTitle.setCellValue(titles[j]);
							cellTitle.setCellStyle(styleCenter);
						}
						if (i==0){
							CellRangeAddress address = new CellRangeAddress(num, num+targets.size()-1, 0, 0);//起始行，终止行，起始列，终止列
							sheet.addMergedRegion(address);
						}
						num++;
					}
				}
				CellRangeAddress address3 = new CellRangeAddress(startNum, num-1, 3, 3);//起始行，终止行，起始列，终止列
				CellRangeAddress address4 = new CellRangeAddress(startNum, num-1, 4, 4);//起始行，终止行，起始列，终止列
				CellRangeAddress address5 = new CellRangeAddress(startNum, num-1, 5, 5);//起始行，终止行，起始列，终止列
				CellRangeAddress address6 = new CellRangeAddress(startNum, num-1, 6, 6);//起始行，终止行，起始列，终止列
				CellRangeAddress address7 = new CellRangeAddress(startNum, num-1, 7, 7);//起始行，终止行，起始列，终止列
				sheet.addMergedRegion(address3);
				sheet.addMergedRegion(address4);
				sheet.addMergedRegion(address5);
				sheet.addMergedRegion(address6);
				sheet.addMergedRegion(address7);
			}
		}
		String fileName = info.getActivityName()+"教学活动评价指标信息表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}
}
