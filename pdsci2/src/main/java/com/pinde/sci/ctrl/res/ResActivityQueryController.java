package com.pinde.sci.ctrl.res;


import com.alibaba.fastjson.JSON;
import com.pinde.core.model.SysDept;
import com.pinde.core.model.SysDict;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResActivityBiz;
import com.pinde.sci.biz.jsres.IJsResActivityTargetBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.res.IResTrainingSpeDeptBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.TeachingActivityResultMapper;
import com.pinde.sci.model.mo.*;
import org.dom4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tiger
 *
 *
 */
@Controller
@RequestMapping("/res/activityQuery")
public class ResActivityQueryController extends GeneralController {
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
	private IResTrainingSpeDeptBiz resTrainingSpeDeptBiz;
	@Autowired
	private TeachingActivityResultMapper resultMapper;
	@Autowired
	private IDictBiz dictBiz;

	@RequestMapping(value="/main")
	public String main(Model model,String  roleFlag, HttpServletRequest request){
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag))
		{
			List<SysDept> depts=deptBiz.searchDeptByOrg(GlobalContext.getCurrentUser().getOrgFlow());
			model.addAttribute("depts",depts);
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_SPE.equals(roleFlag))
		{
			List<SysDept> depts = deptBiz.searchDeptBySpe(GlobalContext.getCurrentUser().getResTrainingSpeId(),GlobalContext.getCurrentUser().getOrgFlow());
			model.addAttribute("depts",depts);
		}else if("teach".equals(roleFlag)||"head".equals(roleFlag))
		{
			List<Map<String, Object>> depts=deptBiz.queryDeptListByFlow(GlobalContext.getCurrentUser().getUserFlow());
			model.addAttribute("depts",depts);
		}else if("doctor".equals(roleFlag))
		{
			return "res/activity/activityQuery/main2";
		}
		return "res/activity/activityQuery/main";
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
		List<Map<String, Object>> depts=deptBiz.searchDeptByDoctor(GlobalContext.getCurrentUser().getUserFlow(), orgFlow);
		model.addAttribute("depts",depts);
		model.addAttribute("doctor",doctor);
		return "res/activity/activityQuery/doctorMain";
	}
	@RequestMapping(value="/list")
	public String list(Model model,Integer currentPage,String activityName,String  roleFlag,String  isCurrent,
					   String userName,String activityTypeId,String deptFlow,String isNew,String isEval,
					   String startTime,String endTime, HttpServletRequest request) throws DocumentException {
		SysUser curUser=GlobalContext.getCurrentUser();
		Map<String,String> param=new HashMap<>();
		param.put("activityName",activityName);
		param.put("userName",userName);
		param.put("activityTypeId",activityTypeId);
		param.put("deptFlow",deptFlow);
		param.put("startTime",startTime);
		param.put("endTime",endTime);
		param.put("isNew",isNew);//最新活动
		param.put("isEval",isEval);//活动评价
		param.put("roleFlag",roleFlag);
		param.put("isCurrent",isCurrent);
		param.put("userFlow",curUser.getUserFlow());
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
			param.put("orgFlow", orgFlow);
		}else {
			param.put("orgFlow", curUser.getOrgFlow());
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String,Object>> list=activityBiz.findActivityList(param);
		Map<String,Object> resultMap=new HashMap<>();
		if(list!=null) {
			for (Map<String,Object> info:list )
			{
                info.put("HaveImg", com.pinde.core.common.GlobalConstant.FLAG_N);
				String imageUrl= (String) info.get("imageUrl");
				if(StringUtil.isNotBlank(imageUrl))
				{
					Document document=DocumentHelper.parseText(imageUrl);
					Element elem=document.getRootElement();
					List<Element> ec = elem.elements("image");
					if(ec!=null&&ec.size()>0)
					{
                        info.put("HaveImg", com.pinde.core.common.GlobalConstant.FLAG_Y);
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
		return "res/activity/activityQuery/list";
	}
	@RequestMapping(value="/exportList")
	public void exportList(Model model,Integer currentPage,String activityName,String  roleFlag,
					   String userName,String activityTypeId,String deptFlow,String isNew,String isEval,
					   String startTime,String endTime, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysUser curUser=GlobalContext.getCurrentUser();
		Map<String,String> param=new HashMap<>();
		param.put("activityName",activityName);
		param.put("userName",userName);
		param.put("activityTypeId",activityTypeId);
		param.put("deptFlow",deptFlow);
		param.put("startTime",startTime);
		param.put("endTime",endTime);
		param.put("isNew",isNew);//最新活动
		param.put("isEval",isEval);//活动评价
		param.put("roleFlag",roleFlag);
		param.put("userFlow",curUser.getUserFlow());
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
		List<Map<String,Object>> list=activityBiz.findActivityList(param);
		model.addAttribute("list",list);
		String []titles =null;
		if("teach".equals(roleFlag)){
			titles =new String[]{
					"activityName:活动名称",
					"activityTypeName:活动形式",
					"activityAddress:活动地点",
					"deptName:所在科室",
					"startTime:活动开始时间",
					"endTime:活动结束时间",
					"regiestNum:报名人数",
					"scanNum:签到人数",
					"evalScore:评价"
			};
		}else{
			titles =new String[]{
					"activityName:活动名称",
					"activityTypeName:活动形式",
					"activityAddress:活动地点",
					"userName:主讲人",
					"deptName:所在科室",
					"startTime:活动开始时间",
					"endTime:活动结束时间",
					"regiestNum:报名人数",
					"scanNum:签到人数",
					"evalScore:评价"
			};
		}

		ExcleUtile.exportSimpleExcleByObjsWithWitdh(titles, list, response.getOutputStream());
		String fileName = new String("教学活动信息.xls".getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping(value="/activityDetail")
	public String activityDetail(Model model,String activityFlow,String roleFlag){
		Map<String,Object> activity=activityBiz.readActivity(activityFlow);
		model.addAttribute("activity",activity);
		return "res/activity/activityQuery/activityDetail";
	}
	@RequestMapping(value="/delActivity")
	@ResponseBody
	public String delActivity(Model model,String activityFlow){
		if(StringUtil.isNotBlank(activityFlow))
		{
			TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
			if(info==null)
				return "活动信息不存在，请刷新列表页面！";
//			if( DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm").compareTo(info.getStartTime())>=0)
//			{
//				return "活动已开始，无法删除！";
//			}
			//查询是否有报名人员已扫码
			TeachingActivityResultExample example=new TeachingActivityResultExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andActivityFlowEqualTo(activityFlow)
                    .andIsScanEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			List<TeachingActivityResult> list=resultMapper.selectByExample(example);
			if(list!=null&&list.size()>0){
				return "已有人员扫码，无法删除！";
			}
            info.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
			int c=activityBiz.saveActivity(info);
			if(c==0)
                return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
            return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
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
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isEffective) && !com.pinde.core.common.GlobalConstant.FLAG_N.equals(isEffective))
			{
				return "请选择【认可】还是【不认可】！";
			}
			TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
			if(info==null)
				return "活动信息不存在，请刷新列表页面！";
			info.setIsEffective(isEffective);
			int c=activityBiz.saveActivity(info);
			if(c==0)
				return "审核失败";
			return "审核成功";
		}else
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
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isEffective) && !com.pinde.core.common.GlobalConstant.FLAG_N.equals(isEffective))
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
            if (info == null || !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(info.getRecordStatus()))
				return "活动信息不存在，请刷新列表页面！";

			if( DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm").compareTo(info.getStartTime())>0)
			{
				return "活动已开始，无法报名！";
			}
			SysUser user=GlobalContext.getCurrentUser();
			TeachingActivityResult result=activityBiz.readRegistInfo(activityFlow,user.getUserFlow());
            if (result != null && com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(result.getIsRegiest()))
			{
				return "你已报名，请勿重复报名！";
			}
			int count=activityBiz.checkJoin(activityFlow,user.getUserFlow());
			if(count>0)
			{
				return "该时间段，你已报名参加其他教学活动！";
			}
			if(result==null){
				result=new TeachingActivityResult();
			}
			result.setActivityFlow(activityFlow);
			result.setUserFlow(user.getUserFlow());
            result.setIsRegiest(com.pinde.core.common.GlobalConstant.FLAG_Y);
			result.setRegiestTime(DateUtil.getCurrDateTime());
            result.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			int c=activityBiz.saveRegist(result);
			if(c==0)
				return "报名失败！";
			return "报名成功！";
		}else
			return "请选择将要参加的活动！";

	}
	@RequestMapping(value="/editActivity")
	public String editActivity(Model model,String activityFlow,String role){
        if (com.pinde.core.common.GlobalConstant.USER_LIST_SPE.equals(role)) {
			List<SysDept> depts = deptBiz.searchDeptBySpe(GlobalContext.getCurrentUser().getResTrainingSpeId(),GlobalContext.getCurrentUser().getOrgFlow());
			model.addAttribute("depts",depts);
		}else {
			List<Map<String, Object>> depts = deptBiz.queryDeptListByFlow(GlobalContext.getCurrentUser().getUserFlow());
			model.addAttribute("depts", depts);
		}
		model.addAttribute("user",GlobalContext.getCurrentUser());
		if(StringUtil.isNotBlank(activityFlow))
		{
			TeachingActivityInfo activity=activityBiz.readActivityInfo(activityFlow);
			model.addAttribute("activity",activity);
			if(activity!=null)
			{
				String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
				String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");
				String professionalBaseAdminRoleFlow = InitConfig.getSysCfg("res_professionalBase_admin_role_flow");
				List<String> userFlows=new ArrayList<>();
				List<SysUser> users=new ArrayList<>();
				List<SysUser> userList=userBiz.readDeptTeachAndHead(activity.getDeptFlow(),teacherRoleFlow,headRoleFlow, "");
				if(userList!=null&&userList.size()>0)
				{
					for (SysUser su:userList ) {
						if(!userFlows.contains(su.getUserFlow()))
						{
							userFlows.add(su.getUserFlow());
							users.add(su);
						}
					}
				}
                if (com.pinde.core.common.GlobalConstant.USER_LIST_SPE.equals(role)) {
					userList=userBiz.readUserBySpe(activity.getDeptFlow(),professionalBaseAdminRoleFlow,GlobalContext.getCurrentUser().getResTrainingSpeId(),GlobalContext.getCurrentUser().getOrgFlow());
					if(userList!=null&&userList.size()>0)
					{
						for (SysUser su:userList ) {
							if(!userFlows.contains(su.getUserFlow()))
							{
								userFlows.add(su.getUserFlow());
								users.add(su);
							}
						}
					}
				}
				model.addAttribute("userList",users);

				PubFile file=fileBiz.readFile(activity.getFileFlow());
				model.addAttribute("file",file);
			}
		}
		model.addAttribute("role",role);
		return "res/activity/activityQuery/editActivity";
	}
	@RequestMapping(value="/saveActivity")
	@ResponseBody
	public String saveActivity(TeachingActivityInfo activity,MultipartFile file,String isRe, String data,String role){
        return activityBiz.editActivity(activity, file, isRe, com.pinde.core.common.GlobalConstant.FLAG_Y, data, role);
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
	@RequestMapping(value="/getFormDetail")
	@ResponseBody
	public Object getFormDetail(String activityTypeId,String activityFlow){
		Map<String,Object> map = new HashMap<>();
		List<DictForm> bigForms = new ArrayList<>();
		List<DictForm> smallForms = new ArrayList<>();
		Map<String,Object> valueMap = new HashMap<>();
		if(StringUtil.isNotBlank(activityTypeId)){
            List<SysDict> dicts = dictBiz.searchDictListByDictTypeIdAndDictId(com.pinde.core.common.enums.DictTypeEnum.ActivityType.getId(), activityTypeId);
			SysDict dict = dicts.get(0);
			String dictFlow = dict.getDictFlow();
			DictForm searchForm = new DictForm();
			searchForm.setDictFlow(dictFlow);
			List<DictForm> dictForms = dictBiz.searchDictForm(searchForm);
			if(dictForms!=null&&dictForms.size()>0){
				for(DictForm dictForm:dictForms){
					if("0".equals(dictForm.getInputType())){
						smallForms.add(dictForm);
					}else if("1".equals(dictForm.getInputType())){
						bigForms.add(dictForm);
					}
				}
			}
			map.put("bigForms",bigForms);
			map.put("smallForms",smallForms);
		}
		if(StringUtil.isNotBlank(activityFlow)){
			TeachingActivityFormValue search = new TeachingActivityFormValue();
			search.setActivityFlow(activityFlow);
			List<TeachingActivityFormValue> formValues = activityTargeBiz.searchFormValue(search);
			if(formValues!=null&&formValues.size()>0){
				for(TeachingActivityFormValue formValue:formValues){
					String formFlow = formValue.getFormFlow();
					valueMap.put(formFlow,formValue);
				}
			}
		}
		map.put("valueMap",valueMap);
		return map;
	}
	@RequestMapping(value="/loadTeacherAndHead")
	@ResponseBody
	public Object loadTeacherAndHead(String deptFlow,String role){
		if(StringUtil.isNotBlank(deptFlow)){
			String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
			String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");
			String professionalBaseAdminRoleFlow = InitConfig.getSysCfg("res_professionalBase_admin_role_flow");
			List<String> userFlows=new ArrayList<>();
			List<SysUser> users=new ArrayList<>();
			List<SysUser> userList=userBiz.readDeptTeachAndHead(deptFlow,teacherRoleFlow,headRoleFlow, "");
			if(userList!=null&&userList.size()>0)
			{
				if(userList!=null&&userList.size()>0)
				{
					for (SysUser su:userList ) {
						if(!userFlows.contains(su.getUserFlow()))
						{
							userFlows.add(su.getUserFlow());
							users.add(su);
						}
					}
				}
			}
            if (com.pinde.core.common.GlobalConstant.USER_LIST_SPE.equals(role)) {
				userList=userBiz.readUserBySpe(deptFlow,professionalBaseAdminRoleFlow,GlobalContext.getCurrentUser().getResTrainingSpeId(),GlobalContext.getCurrentUser().getOrgFlow());
				if(userList!=null&&userList.size()>0)
				{
					if(userList!=null&&userList.size()>0)
					{
						for (SysUser su:userList ) {
							if(!userFlows.contains(su.getUserFlow()))
							{
								userFlows.add(su.getUserFlow());
								users.add(su);
							}
						}
					}
				}
			}
			return users;
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
					}
				}
			}
		}
		model.addAttribute("evalDetailMap",evalDetailMap);
		return "res/activity/activityQuery/showEval";
	}
	@RequestMapping(value="/showRegistEval")
	public String showRegistEval(Model model,String activityFlow,String roleFlag){

		//评价人员
		List<Map<String,Object>> results=activityBiz.readActivityRegists(activityFlow);
		model.addAttribute("results",results);
		return "res/activity/activityQuery/showRegistEval";
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
		return "res/activity/activityQuery/showEvalType";
	}

	@RequestMapping(value="/exportSiginList")
	public void exportSiginList(Model model,String activityFlow ,HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Map<String,Object>> results=activityBiz.readActivityResults(activityFlow);
		if(results!=null&&results.size()>0){
			for (Map<String,Object> map:results) {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(map.get("isEffective"))) {
					map.put("isEffective","认可");
				}else {
					map.put("isEffective","不认可");
				}
			}
		}
		String []titles = new String[]{
				"userName:姓名",
				"sessionNumber:年级",
				"speName:专业",
				"siginTime:签到时间",
				"siginTime2:签退时间",
				"evalScore:评分",
				"isEffective:认可状态"
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
				}
			}
		}
		model.addAttribute("evalDetailMap",evalDetailMap);
		return "res/activity/activityQuery/showDocEval";
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
		return "res/activity/activityQuery/evalInfo";
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
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
	@RequestMapping("/signUrl")
	public String signUrl(String activityFlow,Model model){
		model.addAttribute("activityFlow",activityFlow);
		TeachingActivityInfo activity=activityBiz.readActivityInfo(activityFlow);
		model.addAttribute("activity",activity);
		return "res/activity/activityQuery/activityCode";
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
		return "res/activity/activityQuery/uploadImage";
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
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
			} else {
                return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
	}

	@RequestMapping(value="/regiestCancel")
	@ResponseBody
	public String regiestCancel(String activityFlow){
		if(StringUtil.isNotBlank(activityFlow))
		{
			TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
            if (info == null || !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(info.getRecordStatus()))
				return "活动信息不存在，请刷新列表页面！";
			SysUser user=GlobalContext.getCurrentUser();
			TeachingActivityResult result=activityBiz.readRegistInfo(activityFlow,user.getUserFlow());
			if (result == null) {
				return "你未报名，无法取消报名信息！";
			}
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(result.getIsRegiest()))
			{
				return "你已取消报名！";
			}
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(result.getIsScan()))
			{
				return "你已扫码签到，无法取消报名信息！";
			}
            result.setIsRegiest(com.pinde.core.common.GlobalConstant.FLAG_N);
			int num=activityBiz.saveRegist(result);
			if(num>0){
                return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
			}
            return com.pinde.core.common.GlobalConstant.OPERATE_FAIL;
		}else
			return "请选择将要取消的活动！";

	}
}
