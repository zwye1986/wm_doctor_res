package com.pinde.sci.ctrl.sch;

import com.alibaba.fastjson.JSON;
import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResEnterOpenCfg;
import com.pinde.core.model.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResEnterOpenCfgBiz;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.core.common.enums.sch.SchCycleTypeEnum;
import com.pinde.core.common.enums.sch.SchSelYearEnum;
import com.pinde.sci.form.sch.SchSelectDeptForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sch/sel")
public class SchSelController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(SchSelController.class);

	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private ISchRotationCfgBiz rotationCfgBiz;
	@Autowired
	private ISchRotationBiz schRotationtBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private ISchRotationGroupBiz schRotationtGroupBiz;
	@Autowired
	private IOrgBiz orgBiz;

	@Autowired
	private IResEnterOpenCfgBiz enterOpenCfgBiz;
	@Autowired
	private ISchDoctorSelectDeptBiz doctorDeptBiz;

	@RequestMapping(value = {"/seldept"}, method = {RequestMethod.GET})
	public String seldept( String doctorFlow, Model model,String role) throws DocumentException {
		doctorFlow=StringUtil.defaultIfEmpty(doctorFlow, GlobalContext.getCurrentUser().getUserFlow());
		ResDoctor doctor=doctorBiz.readDoctor(doctorFlow);

		model.addAttribute("doctor",doctor);
		if(doctor==null)
		{
			model.addAttribute("result","无法读取医师信息，请联系管理员！");
			return "sch/sel/selDeptError";
		}
		String orgFlow = doctor.getOrgFlow();
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(doctor.getSelDeptFlag())) {
			if (StringUtil.isBlank(orgFlow)) {
				model.addAttribute("result", "无法读取基地信息，请联系管理员！");
				return "sch/sel/selDeptError";
			}
			if (StringUtil.isBlank(doctor.getTrainingSpeId())) {
				model.addAttribute("result", "无法读取培训专业信息，请联系管理员！");
				return "sch/sel/selDeptError";
			}
			if (StringUtil.isBlank(doctor.getTrainingYears())) {
				model.addAttribute("result", "无法读取培养年限信息，请联系管理员！");
				return "sch/sel/selDeptError";
			}
			if (StringUtil.isBlank(doctor.getRotationFlow())) {
				model.addAttribute("result", "无法读取轮转方案信息，请联系管理员！");
				return "sch/sel/selDeptError";
			}
			ResEnterOpenCfg openCfg = enterOpenCfgBiz.readResEnterOpenCfg(orgFlow);
			String currTime = DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");
			if (openCfg == null) {
				model.addAttribute("result", "管理员未配置选科时间，当前时间无法进行选科！");
				return "sch/sel/selDeptError";
			}
			int result1 = currTime.compareTo(openCfg.getStartDate());
			int result2 = currTime.compareTo(openCfg.getEndDate());
			if (result1 < 0 || result2 > 0) {
				model.addAttribute("result", "选科时间为：" + openCfg.getStartDate() + "~" + openCfg.getEndDate() + ",当前时间无法进行选科！");
				return "sch/sel/selDeptError";
			}
		}
		String rotationFlow=doctor.getRotationFlow();
		String sessionNumber=doctor.getSessionNumber();
		String selectYear=doctor.getTrainingYears();
		if("1".equals(selectYear))
		{
			selectYear=SchSelYearEnum.One.getId();
		}
		if("2".equals(selectYear))
		{
			selectYear=SchSelYearEnum.Two.getId();
		}
		if("3".equals(selectYear))
		{
			selectYear=SchSelYearEnum.Three.getId();
		}
		model.addAttribute("selectYear",selectYear);
		Map<String,String> tempMap = new HashMap<>();
		tempMap.put("rotationFlow",rotationFlow);
		tempMap.put("sessionNumber",sessionNumber);
		tempMap.put("selectYear",selectYear);
		tempMap.put("orgFlow",orgFlow);
		List<SchRotationOrgGroup> groups=rotationCfgBiz.searchSchRotationOrgGroup(tempMap);
		if(groups==null||groups.size()==0)
		{
			model.addAttribute("result","管理员未配置选科信息！");
			return "sch/sel/selDeptError";
		}
        tempMap.put("recordStatus", com.pinde.core.common.GlobalConstant.FLAG_Y);
		tempMap.put("schDeptFlowIsNull","schDeptFlowIsNull");
		List<SchRotationOrgDept> baseDepts=rotationCfgBiz.searchSchRotationOrgDept(tempMap);
		if(baseDepts==null||baseDepts.size()==0)
		{
			model.addAttribute("result","管理员未配置选科信息！");
			return "sch/sel/selDeptError";
		}
        tempMap.put("recordStatus", com.pinde.core.common.GlobalConstant.FLAG_Y);
		tempMap.put("schDeptFlowIsNull","");
		tempMap.put("schDeptFlowIsNotNull","schDeptFlowIsNotNull");
		List<SchRotationOrgDept> depts=rotationCfgBiz.searchSchRotationOrgDept(tempMap);
		if(depts==null||depts.size()==0)
		{
			model.addAttribute("result","管理员未配置选科信息！");
			return "sch/sel/selDeptError";
		}
		//排班类型
		SchRotationOrgCfg cfg=rotationCfgBiz.readOrgRotationCycleType(rotationFlow,orgFlow,sessionNumber);
		model.addAttribute("cfg",cfg);
		if(cfg==null)
		{
			model.addAttribute("result","管理员未配置排班方式！");
			return "sch/sel/selDeptError";
		}
		model.addAttribute("groups",groups);

		if(baseDepts!=null && baseDepts.size()>0){
			Map<String,List<SchRotationOrgDept>> standardGroupDeptMap = new HashMap<String, List<SchRotationOrgDept>>();
			for(SchRotationOrgDept rotationDept : baseDepts){
				String key = rotationDept.getGroupFlow();
				if(standardGroupDeptMap.get(key)==null){
					List<SchRotationOrgDept> rotationDeptListTemp = new ArrayList<SchRotationOrgDept>();
					rotationDeptListTemp.add(rotationDept);
					standardGroupDeptMap.put(key,rotationDeptListTemp);
				}else{
					standardGroupDeptMap.get(key).add(rotationDept);
				}
			}
			model.addAttribute("standardGroupDeptMap",standardGroupDeptMap);
		}

		if(depts!=null && depts.size()>0){
			Map<String,List<SchRotationOrgDept>> localRotationDeptListMap = new HashMap<String, List<SchRotationOrgDept>>();
			if(SchCycleTypeEnum.AllYear.getId().equals(cfg.getCycleTypeId())) {
				for (SchRotationOrgDept rotationDept : depts) {
					String key = rotationDept.getGroupFlow() + rotationDept.getStandardDeptId();
					if(StringUtil.isNotBlank(rotationDept.getSchMonth())&&0!=Double.valueOf(rotationDept.getSchMonth()))
					{
						if (localRotationDeptListMap.get(key) == null) {
							List<SchRotationOrgDept> rotationDeptListTemp = new ArrayList<SchRotationOrgDept>();
							rotationDeptListTemp.add(rotationDept);
							localRotationDeptListMap.put(key, rotationDeptListTemp);
						} else {
							localRotationDeptListMap.get(key).add(rotationDept);
						}
					}
				}
			}else{
				for (SchRotationOrgDept rotationDept : depts) {
					String key = rotationDept.getGroupFlow() + rotationDept.getStandardDeptId()+"One";
					if(StringUtil.isNotBlank(rotationDept.getOneSchMonth())&&0!=Double.valueOf(rotationDept.getOneSchMonth()))
					{
						if (localRotationDeptListMap.get(key) == null) {
							List<SchRotationOrgDept> rotationDeptListTemp = new ArrayList<SchRotationOrgDept>();
							rotationDeptListTemp.add(rotationDept);
							localRotationDeptListMap.put(key, rotationDeptListTemp);
						} else {
							localRotationDeptListMap.get(key).add(rotationDept);
						}
					}
					String key2 = rotationDept.getGroupFlow() + rotationDept.getStandardDeptId()+"Two";
					if(StringUtil.isNotBlank(rotationDept.getTwoSchMonth())&&0!=Double.valueOf(rotationDept.getTwoSchMonth()))
					{
						if (localRotationDeptListMap.get(key2) == null) {
							List<SchRotationOrgDept> rotationDeptListTemp = new ArrayList<SchRotationOrgDept>();
							rotationDeptListTemp.add(rotationDept);
							localRotationDeptListMap.put(key2, rotationDeptListTemp);
						} else {
							localRotationDeptListMap.get(key2).add(rotationDept);
						}
					}
					String key3 = rotationDept.getGroupFlow() + rotationDept.getStandardDeptId()+"Three";
					if(StringUtil.isNotBlank(rotationDept.getThreeSchMonth())&&0!=Double.valueOf(rotationDept.getThreeSchMonth()))
					{
						if (localRotationDeptListMap.get(key3) == null) {
							List<SchRotationOrgDept> rotationDeptListTemp = new ArrayList<SchRotationOrgDept>();
							rotationDeptListTemp.add(rotationDept);
							localRotationDeptListMap.put(key3, rotationDeptListTemp);
						} else {
							localRotationDeptListMap.get(key3).add(rotationDept);
						}
					}
				}
			}
			model.addAttribute("localRotationDeptListMap",localRotationDeptListMap);
		}
		//查询学员选科信息
		List<SchDoctorSelectDept> selectDepts=doctorDeptBiz.readSelectDepts(doctorFlow,doctor.getSessionNumber(),doctor.getRotationFlow(),doctor.getOrgFlow(), "");
		if(selectDepts!=null)
		{
			Map<String,SchDoctorSelectDept> selectDeptMap=new HashMap<>();
			for(SchDoctorSelectDept dept:selectDepts)
			{
				String key=dept.getGroupFlow()+dept.getStandardDeptId()+dept.getSchDeptFlow();
				if(StringUtil.isNotBlank(dept.getSelectYear()))
				{
					key+=dept.getSelectYear();
				}
				selectDeptMap.put(key,dept);
			}
			model.addAttribute("selectDeptMap",selectDeptMap);
		}
		return "sch/sel/seldept";
	}
	@RequestMapping(value = {"/arrange/results"}, method = {RequestMethod.GET})
	public String results( String doctorFlow, Model model,String role) throws DocumentException {
		doctorFlow=StringUtil.defaultIfEmpty(doctorFlow, GlobalContext.getCurrentUser().getUserFlow());
		ResDoctor doctor=doctorBiz.readDoctor(doctorFlow);

		model.addAttribute("doctor",doctor);
		if(doctor==null)
		{
			model.addAttribute("result","无法读取医师信息，请联系管理员！");
			return "sch/sel/selDeptError";
		}
		String orgFlow=doctor.getOrgFlow();
		if(StringUtil.isBlank(orgFlow))
		{
			model.addAttribute("result","无法读取基地信息，请联系管理员！");
			return "sch/sel/selDeptError";
		}
		if(StringUtil.isBlank(doctor.getTrainingSpeId()))
		{
			model.addAttribute("result","无法读取培训专业信息，请联系管理员！");
			return "sch/sel/selDeptError";
		}
		if(StringUtil.isBlank(doctor.getTrainingYears()))
		{
			model.addAttribute("result","无法读取培养年限信息，请联系管理员！");
			return "sch/sel/selDeptError";
		}
		if(StringUtil.isBlank(doctor.getRotationFlow()))
		{
			model.addAttribute("result","无法读取轮转方案信息，请联系管理员！");
			return "sch/sel/selDeptError";
		}
		//查询学员选科信息
		List<SchOrgArrangeResult> results=doctorDeptBiz.readArrangeResults(doctorFlow,doctor.getSessionNumber(),doctor.getRotationFlow(),doctor.getOrgFlow());
		model.addAttribute("results",results);

		return "sch/sel/results";
	}

	@RequestMapping(value = "/doctorList")
	public String doctorList(ResDoctorExt doctor, Integer currentPage, HttpServletRequest request, Model model) {
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
		if (currentPage == null) {
			currentPage = 1;
		}

		ResEnterOpenCfg openCfg = enterOpenCfgBiz.readResEnterOpenCfg(GlobalContext.getCurrentUser().getOrgFlow());
		String currTime = DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");
		if(openCfg==null){
			model.addAttribute("result","管理员未配置选科时间，当前时间无法进行选科！");
			return "/sch/sel/doctorlist";
		}
		int result1 = currTime.compareTo(openCfg.getStartDate());
		int result2 = currTime.compareTo(openCfg.getEndDate());
		if(result1<0 || result2>0){
			model.addAttribute("result","选科时间为："+openCfg.getStartDate()+"~"+openCfg.getEndDate()+",当前时间无法进行选科！");
			return "/sch/sel/doctorlist";
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<ResDoctorExt> doctorList = doctorBiz.searchDocUser(doctor, "");
		//复选框勾选标识
		Map<String,String> doctorTypeSelectMap = new HashMap<>();
		List<String> doctorTypeIdList = doctor.getDoctorTypeIdList();
		SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorType.getId());
        sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysDict> dictList = dictBiz.searchDictList(sysDict);
		if(dictList!=null&&dictList.size()>0&&doctorTypeIdList!=null&&doctorTypeIdList.size()>0){
			for (SysDict dict:dictList){
				if(doctorTypeIdList.contains(dict.getDictId())){
					doctorTypeSelectMap.put(dict.getDictId(),"checked");
				}
			}
		}
		//结束
		model.addAttribute("doctorTypeSelectMap",doctorTypeSelectMap);
		model.addAttribute("doctor", doctor);
		model.addAttribute("doctorList", doctorList);
		return "/sch/sel/doctorlist";
	}
	@RequestMapping(value = {"/saveSelDept"},method = RequestMethod.POST)
	@ResponseBody
	public String saveSelDept(@RequestBody List<SchSelectDeptForm> selDepts, String rotationFlow, String doctorFlow
			, String orgFlow, String sessionNumber) throws Exception {
		doctorFlow=StringUtil.defaultIfEmpty(doctorFlow, GlobalContext.getCurrentUser().getUserFlow());
		if(selDepts==null||selDepts.size()<=0)
		{
			return "请至少选择一个轮转科室信息！";
		}
		ResDoctor doctor=doctorBiz.readDoctor(doctorFlow);
		if(doctor==null)
		{
			return "医师信息不存在！";
		}
		if(!doctor.getSessionNumber().equals(sessionNumber))
		{
			return "培训年级不一致，请重新登录！";
		}
		if(!doctor.getRotationFlow().equals(rotationFlow))
		{
			return "培训方案不一致，请重新登录！";
		}
		if(!doctor.getOrgFlow().equals(orgFlow))
		{
			return "培训基地不一致，请重新登录！";
		}
		System.err.println(JSON.toJSONString(selDepts));
		doctorDeptBiz.saveSelDept(selDepts,orgFlow,doctorFlow,sessionNumber,rotationFlow);
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
}


