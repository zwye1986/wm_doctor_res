package com.pinde.sci.ctrl.jsres;


import com.pinde.core.common.sci.dao.SysLogMapper;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sch.ISchManualBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.InitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author tiger
 *
 *
 */
@Controller
@RequestMapping("/jsres/business")
public class JsResBusinessController extends GeneralController {
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IJsResPowerCfgBiz jsResPowerCfgBiz;
	@Autowired
	private IOrgBiz sysOrgBiz;
	@Autowired
	private ISchManualBiz schManualBiz;
	@Autowired
	private SysLogMapper logMapper;


	@RequestMapping(value="/index")
	public String index(){
		return "jsres/business/index";
	}

	@RequestMapping(value="/doctorUserList")
	public String doctorUserList(Model model,HttpServletRequest request){
		return "jsres/business/doctorHead";
	}

	@RequestMapping(value = "/accounts")
	public String accounts(Model model) throws Exception {
        SysUser user = (SysUser) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER);
		SysLogExample example = new SysLogExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andUserFlowEqualTo(user.getUserFlow());
		example.setOrderByClause("create_time desc");
		List<SysLog> logs = logMapper.selectByExample(example);
		if (logs != null && logs.size() > 0) {
			model.addAttribute("log", logs.get(0));
		}
		return "jsres/accounts";
	}

	/**
	 * @Department：研发部
	 * @Description 审核管理查询
	 * @Author lim
	 * @Date 2020/8/6
	 */
	@RequestMapping(value = {"/userList" })
	public String userList (Model model,Integer currentPage,HttpServletRequest request,
							String orgFlow,String sessionNumber,String workOrgId,String userName,String idNo,String userCode,String trainingYears
			,String datas[]	,String ifOpen,String[] powerTypeId,String checkStatusId,String startTime,String endTime) throws Exception{
		List<String> docTypeList=new ArrayList<>();
		if(datas!=null&&datas.length>0) {
			docTypeList.addAll(Arrays.asList(datas));
		}
		Map<String,Object> params=new HashMap<>();
		params.put("orgFlow",orgFlow);
		params.put("sessionNumber",sessionNumber);
		params.put("workOrgId",workOrgId);
		params.put("userName",userName);
		params.put("idNo",idNo);
		params.put("docTypeList",docTypeList);
		params.put("userCode",userCode);//登录名
		params.put("trainingYears",trainingYears);//培养年限
		if(StringUtil.isBlank(checkStatusId) || "undefined".equals(checkStatusId)){
			checkStatusId = "Passing";//默认待审核
		}
		params.put("checkStatusId",checkStatusId);//审核状态
		params.put("startTime",startTime);//数据审核区间
		params.put("endTime",endTime);//数据审核区间
		if(currentPage==null){
			currentPage=1;
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> list = new ArrayList<>();
		if(StringUtil.isNotBlank(ifOpen)){
			params.put("ifOpen",ifOpen);
			for(int i=0;i<powerTypeId.length;i++){
                params.put(powerTypeId[i], com.pinde.core.common.GlobalConstant.FLAG_Y);
                model.addAttribute(powerTypeId[i], com.pinde.core.common.GlobalConstant.FLAG_Y);
			}
			list = schManualBiz.userListByJsResPower2(params);
		}else {
			list = schManualBiz.userList2(params);
		}
		Map<String,JsresPowerCfg> cfgMap=new HashMap<>();
		for(Map<String,Object> map:list) {
			String userFlow= (String) map.get("userFlow");
			cfgMap.put(userFlow,jsResPowerCfgBiz.read("jsres_doctor_data_time_"+userFlow));
		}
		model.addAttribute("cfgMap",cfgMap);
		model.addAttribute("list",list);
		model.addAttribute("datas",docTypeList);
		return "jsres/business/doctorUserList";
	}

	@RequestMapping(value="/updateCheck",method= RequestMethod.POST)
	@ResponseBody
	public String updateCheck(String[] userFlows,String flag,String reason){
		Map<String,Object> param = new HashMap<>();
		if(null != userFlows && userFlows.length>0){
			List<String> userFlowList = Arrays.asList(userFlows);
			param.put("list",userFlowList);
			param.put("flag",flag);
			param.put("checkReason",reason);
			param.put("checkTime",DateUtil.getCurrDateTime2());
			int count = resDoctorBiz.updateCheckAll(param);
			if(count > 0) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value="/sendBack",method= RequestMethod.POST)
	@ResponseBody
	public String sendBack(String[] userFlows){
		if(null != userFlows && userFlows.length>0){
			List<String> userFlowList = Arrays.asList(userFlows);
			int count = resDoctorBiz.saveSubmitAll(userFlowList);
			if(count > 0) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}

	@RequestMapping(value="/checkReason",method= RequestMethod.GET)
	public String checkReason(Model model,String userFlows,HttpServletRequest request){
		model.addAttribute("userFlows",userFlows);
		return "jsres/business/checkReason";
	}

	@RequestMapping(value="/teacherHead")
	public String teacherHead(Model model,HttpServletRequest request){
		return "jsres/business/teacherHead";
	}

	@RequestMapping(value = {"/teacherList" })
	public String teacherList (Model model,Integer currentPage,HttpServletRequest request,
							String orgFlow,String deptFlow,String userName,String userCode,String checkStatusId	) throws Exception{
		Map<String,Object> params=new HashMap<>();
		String roleFlow= InitConfig.getSysCfg("res_teacher_role_flow");
		params.put("roleFlow",roleFlow);
		params.put("orgFlow",orgFlow);
		params.put("deptFlow",deptFlow);
		params.put("userName",userName);
		params.put("userCode",userCode);
		if(StringUtil.isBlank(checkStatusId) || "undefined".equals(checkStatusId)){
			checkStatusId = "Passing";//默认待审核
		}
		params.put("checkStatusId",checkStatusId);
		if(currentPage==null){
			currentPage=1;
		}
		if(StringUtil.isNotBlank(roleFlow)) {
			PageHelper.startPage(currentPage, getPageSize(request));
			List<Map<String, Object>> list = schManualBiz.teaList(params);
			model.addAttribute("list", list);
		}
		return "jsres/business/teacherList";
	}

	@RequestMapping(value="/checkTeaReason",method= RequestMethod.GET)
	public String checkTeaReason(Model model,String userFlows,HttpServletRequest request){
		model.addAttribute("userFlows",userFlows);
		return "jsres/business/checkTeaReason";
	}

	@RequestMapping(value="/updateTeaCheck",method= RequestMethod.POST)
	@ResponseBody
	public String updateTeaCheck(String[] userFlows,String flag,String checkReason){
		Map<String,Object> param = new HashMap<>();
		if(null != userFlows && userFlows.length>0){
			List<String> userFlowList = Arrays.asList(userFlows);
			param.put("list",userFlowList);
			param.put("flag",flag);
			param.put("checkReason",checkReason);
			int count = userBiz.updateCheckAll(param);
			if(count > 0) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value="/sendBackTea",method= RequestMethod.POST)
	@ResponseBody
	public String sendBackTea(String[] userFlows){
		if(null != userFlows && userFlows.length>0){
			List<String> userFlowList = Arrays.asList(userFlows);
			int count = userBiz.updateTeaNotSubmit(userFlowList);
			if(count > 0) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}

	@RequestMapping(value="/hospitalHead")
	public String hospitalHead(Model model,HttpServletRequest request){
		return "jsres/business/hospitalHead";
	}

	@RequestMapping(value = {"/hospitalList" })
	public String hospitalList (SysOrg sysOrg, Integer currentPage, HttpServletRequest request, Model model, String orgFlag){
		SysOrg sysOrg2 = new SysOrg();
        sysOrg2.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        sysOrg2.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<SysOrg> allSysOrgList = orgBiz.searchOrgs(sysOrg2,null);
		model.addAttribute("allSysOrgList", allSysOrgList);

        sysOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        sysOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<SysOrg> sysOrgList = orgBiz.searchOrgs(sysOrg,orgFlag);
		model.addAttribute("sysOrgList", sysOrgList);
		return "jsres/business/hospitalList";
	}

	@RequestMapping(value="/checkHospitalReason",method= RequestMethod.GET)
	public String checkHospitalReason(Model model,String orgFlows,HttpServletRequest request){
		model.addAttribute("orgFlows",orgFlows);
		return "jsres/business/checkHospitalReason";
	}

	@RequestMapping(value="/updateHospitalCheck",method= RequestMethod.POST)
	@ResponseBody
	public String updateHospitalCheck(String[] orgFlows,String flag,String checkReason){
		Map<String,Object> param = new HashMap<>();
		if(null != orgFlows && orgFlows.length>0){
			List<String> orgFlowList = Arrays.asList(orgFlows);
			param.put("list",orgFlowList);
			param.put("flag",flag);
			param.put("checkReason",checkReason);
			int count = sysOrgBiz.updateCheckAll(param);
			if(count > 0) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value="/sendBackHospital",method= RequestMethod.POST)
	@ResponseBody
	public String sendBackHospital(String[] orgFlows){
		if(null != orgFlows && orgFlows.length>0){
			List<String> userFlowList = Arrays.asList(orgFlows);
			int count = sysOrgBiz.updateHospitalNotSubmit(userFlowList);
			if(count > 0) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}

	@RequestMapping(value="/schoolHead")
	public String schoolHead(Model model,HttpServletRequest request){
		return "jsres/business/schoolHead";
	}

	@RequestMapping(value = {"/schoolList" })
	public String schoolList (SysDict sysDict, Integer currentPage, HttpServletRequest request, Model model){
		if (sysDict == null) {
			sysDict = new SysDict();
		}
        sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        sysDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.SendSchool.getId());
        sysDict.setDictTypeName(com.pinde.core.common.enums.DictTypeEnum.SendSchool.getName());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<SysDict> dictList = dictBiz.searchDictList(sysDict);
		model.addAttribute("dictList", dictList);
		return "jsres/business/schoolList";
	}

	@RequestMapping(value="/checkSchoolReason",method= RequestMethod.GET)
	public String checkSchoolReason(Model model,String dictFlows,HttpServletRequest request){
		model.addAttribute("dictFlows",dictFlows);
		return "jsres/business/checkSchoolReason";
	}

	@RequestMapping(value="/updateSchoolCheck",method= RequestMethod.POST)
	@ResponseBody
	public String updateSchoolCheck(String[] dictFlows,String flag,String checkReason){
		Map<String,Object> param = new HashMap<>();
		if(null != dictFlows && dictFlows.length>0){
			List<String> dictFlowList = Arrays.asList(dictFlows);
			param.put("list",dictFlowList);
			param.put("flag",flag);
			param.put("checkReason",checkReason);
			int count = dictBiz.updateCheckAll(param);
			if(count > 0) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value="/sendBackSchool",method= RequestMethod.POST)
	@ResponseBody
	public String sendBackSchool(String[] dictFlows){
		if(null != dictFlows && dictFlows.length>0){
			List<String> dictFlowList = Arrays.asList(dictFlows);
			int count = dictBiz.updateSchoolNotSubmit(dictFlowList);
			if(count > 0) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}

	@RequestMapping(value = {"/exportDoc" })
	public void exportDoc (HttpServletRequest request,HttpServletResponse response, String orgFlow,String sessionNumber,
						   String workOrgId,String userName,String idNo,String userCode,String trainingYears,String datas[]	,
						   String ifOpen,String[] powerTypeId,String checkStatusId,String startTime,String endTime) throws Exception {
		List<String> docTypeList = new ArrayList<>();
		if (datas != null && datas.length > 0) {
			docTypeList.addAll(Arrays.asList(datas));
		}
		Map<String, Object> params = new HashMap<>();
		params.put("orgFlow", orgFlow);
		params.put("sessionNumber", sessionNumber);
		params.put("workOrgId", workOrgId);
		params.put("userName", userName);
		params.put("idNo", idNo);
		params.put("docTypeList", docTypeList);
		params.put("userCode", userCode);//登录名
		params.put("trainingYears", trainingYears);//培养年限
		if (StringUtil.isBlank(checkStatusId) || "undefined".equals(checkStatusId)) {
			checkStatusId = "Passing";//默认待审核
		}
		params.put("checkStatusId", checkStatusId);//审核状态
		params.put("startTime", startTime);//数据审核区间
		params.put("endTime", endTime);//数据审核区间
		List<Map<String, Object>> list = new ArrayList<>();
		if (StringUtil.isNotBlank(ifOpen)) {
			params.put("ifOpen", ifOpen);
			for (int i = 0; i < powerTypeId.length; i++) {
                params.put(powerTypeId[i], com.pinde.core.common.GlobalConstant.FLAG_Y);
			}
			list = schManualBiz.userListByJsResPower(params);
		} else {
			list = schManualBiz.userList(params);
		}
		// 数据审核区间
//		Map<String, JsresPowerCfg> cfgMap = new HashMap<>();
//		for (Map<String, Object> map : list) {
//			String userFlow = (String) map.get("userFlow");
//			cfgMap.put(userFlow, jsResPowerCfgBiz.read("jsres_doctor_data_time_" + userFlow));
//		}
		jsResPowerCfgBiz.exportInfo(list,docTypeList,response);
	}
}
