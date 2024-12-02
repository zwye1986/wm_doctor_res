package com.pinde.sci.ctrl.sch;

import com.alibaba.fastjson.JSON;
import com.pinde.core.util.BeanUtil;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResEnterOpenCfgBiz;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.core.common.enums.sch.SchSelYearEnum;
import com.pinde.sci.form.sch.SchRotationOrgGroupForm;
import com.pinde.sci.model.mo.*;
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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sch/rotationCfg")
public class SchRotationCfgController extends GeneralController{

	private static Logger logger = LoggerFactory.getLogger(SchRotationCfgController.class);

	@Autowired
	private IResEnterOpenCfgBiz enterOpenCfgBiz;
	@Autowired
	private ISchDoctorSelectDeptBiz doctorDeptBiz;
	@Autowired
	private ISchRotationCfgBiz rotationCfgBiz;
	@Autowired
	private ISchRotationBiz schRotationtBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private ISchRotationGroupBiz schRotationtGroupBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private IDeptBiz sysDeptBiz;
	@Autowired
	private IOrgBiz orgBiz;

	@RequestMapping(value="/local",method=RequestMethod.GET)
	public String local(String orgFlow,String currRoleFlag,Model model){
		model.addAttribute("currRoleFlag",currRoleFlag);
        model.addAttribute("roleFlag", com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL);
        model.addAttribute("publishFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("orgFlow",orgFlow);
		return "redirect:/sch/rotationCfg/list";
	}

	@RequestMapping(value = {"/list" } )
	public String rotationList (String roleFlag,SchRotation rotation,String rotationName,Integer updateCount,String currRoleFlag,String orgFlow,Model model) throws UnsupportedEncodingException {
		model.addAttribute("updateCount",updateCount);
		if(StringUtil.isNotBlank(rotationName)){
			rotation.setRotationName(rotationName);
			model.addAttribute("rotationName",rotationName);
		}
		//获取轮转方案（如果是对指定机构展示，则只显示非指定机构和指定本机构的）
		List<SchRotation> rotationList = schRotationtBiz.searchOrgStandardRotation(rotation);
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
        if (!com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
			rotationList=schRotationtBiz.schRotations(rotationList,orgFlow);
		}
		//轮转方案轮转科室统计轮转时间，按轮转方案分组统计
		List<Map<String,Object>> mustSumList = schRotationDeptBiz.getRotationMustSum(null);
		if(mustSumList!=null && mustSumList.size()>0){
			Map<String,Object> mustSumMap = new HashMap<String, Object>();
			for(Map<String,Object> map : mustSumList){
				String key = (String)map.get("rotationFlow");
				mustSumMap.put(key,map.get("monthSum"));
			}
			model.addAttribute("mustSumMap",mustSumMap);
		}

		//轮转组合科室统计轮转时间，按轮转方案分组统计
		List<Map<String,Object>> groupSumList = schRotationDeptBiz.getRotationGroupSum(null);
		if(groupSumList!=null && groupSumList.size()>0){
			Map<String,Object> groupSumMap = new HashMap<String, Object>();
			for(Map<String,Object> map : groupSumList){
				String key = (String)map.get("rotationFlow");
				groupSumMap.put(key,map.get("monthSum"));
			}
			model.addAttribute("groupSumMap",groupSumMap);
		}

		model.addAttribute("rotationList",rotationList);

		return "sch/rotationCfg/list";
	}

	/**
	 * 轮转规则配置
	 * */
	@RequestMapping(value = {"/rule"},method = RequestMethod.GET)
	public String rule (String roleFlag,String rotationFlow,String orgFlow,String selectYear,
						Model model,String sessionNumber,HttpServletRequest request) throws Exception {

		//加载基地本地化的内容
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);

		if(StringUtil.isBlank(sessionNumber)){
			String[] time = DateUtil.getCurrMonth().split("-");
			String currenYear = time[0];
			String currenMonth = time[1];
			if(Integer.parseInt(currenMonth) > 8)
				sessionNumber = currenYear;
			else
				sessionNumber = Integer.parseInt(currenYear) -1 + "";
		}
		if(StringUtil.isBlank(selectYear)){
			selectYear= SchSelYearEnum.One.getId();
		}


		model.addAttribute("selectYear",selectYear);
		model.addAttribute("sessionNumber",sessionNumber);

		if(StringUtil.isNotBlank(rotationFlow)){
			//轮转方案
			SchRotation rotation = schRotationtBiz.readSchRotation(rotationFlow);
			model.addAttribute("rotation",rotation);
			//排班类型
			SchRotationOrgCfg cfg=rotationCfgBiz.readOrgRotationCycleType(rotationFlow,orgFlow,sessionNumber);
			model.addAttribute("cfg",cfg);
			//轮转方案组别
			List<SchRotationGroup> standardRotationGroupList = schRotationtGroupBiz.searchSchRotationGroup(rotationFlow);
			model.addAttribute("standardRotationGroupList",standardRotationGroupList);


			//初始化该基地的SchRotationOrgGroup(区分届别与培养年限)
			Map<String,SchRotationOrgGroup> localGroupMap = new HashMap<String, SchRotationOrgGroup>();
			List<SchRotationOrgGroup> orgRotationGroupList = new ArrayList<>();
			if(standardRotationGroupList != null && standardRotationGroupList.size() > 0){
				for(SchRotationGroup tempGroup : standardRotationGroupList){
					Map<String,String> tempMap = new HashMap<>();
					tempMap.put("rotationFlow",rotationFlow);
					tempMap.put("sessionNumber",sessionNumber);
					tempMap.put("selectYear",selectYear);
					tempMap.put("orgFlow",orgFlow);
					tempMap.put("standardGroupFlow",tempGroup.getGroupFlow());
					//查询当前机构下当前届别当前标准科室的该方案下的SchRotationGroup oneGroup只能有一条
					List<SchRotationOrgGroup> oneGroup = rotationCfgBiz.searchSchRotationOrgGroup(tempMap);
					//复制对应标准科室下的SchRotationGroup
					SchRotationOrgGroup newGroup =null;
					//如果oneGroup为空则需要初始化
					if (oneGroup == null || oneGroup.size() < 1) {
						newGroup=new SchRotationOrgGroup();
						BeanUtil.mergeNotSameClassObject(tempGroup,newGroup);
						//注意将groupFlow置空，因为要重新插入新的数据
						newGroup.setGroupFlow("");
						//标准科室下的SchRotationGroup没有绑定机构，需要绑定
						newGroup.setOrgFlow(orgFlow);
						newGroup.setOrgName(sysOrg.getOrgName());
						//绑定届别
						newGroup.setSelectYear(selectYear);
						newGroup.setSessionNumber(sessionNumber);
						newGroup.setStandardGroupFlow(tempGroup.getGroupFlow());
						rotationCfgBiz.saveSchRotationOrgGroup(newGroup);
					}else{
						newGroup=oneGroup.get(0);
					}
					orgRotationGroupList.add(newGroup);
					localGroupMap.put(tempGroup.getGroupFlow(),newGroup);

				}
			}
			model.addAttribute("rotationGroupList",orgRotationGroupList);
			model.addAttribute("localGroupMap",localGroupMap);

			//轮转方案标准科室
			List<SchRotationDept> standardRotationDeptList = schRotationDeptBiz.searchSchRotationDept(rotationFlow);
			if(standardRotationDeptList!=null && standardRotationDeptList.size()>0){
				model.addAttribute("standardRotationDeptList",standardRotationDeptList);

				//取本地化的标准科室信息
				Map<String,String> paramMap2 = new HashMap<>();
				paramMap2.put("rotationFlow",rotationFlow);
				paramMap2.put("orgFlow",orgFlow);
				paramMap2.put("sessionNumber",sessionNumber);
				paramMap2.put("selectYear",selectYear);
				paramMap2.put("schDeptFlowIsNull","schDeptFlowIsNull");
				Map<String,List<SchRotationDept>> standardGroupDeptMap = new HashMap<String, List<SchRotationDept>>();
				Map<String,SchRotationOrgDept> standardOrgDeptMap = new HashMap<String, SchRotationOrgDept>();
				for(SchRotationDept rotationDept : standardRotationDeptList){
					String key = rotationDept.getGroupFlow();
					SchRotationOrgGroup newGroup =localGroupMap.get(rotationDept.getGroupFlow());
					if(standardGroupDeptMap.get(key)==null){
						List<SchRotationDept> standardRotationDeptTempList = new ArrayList<SchRotationDept>();
						standardRotationDeptTempList.add(rotationDept);
						standardGroupDeptMap.put(key,standardRotationDeptTempList);
					}else{
						standardGroupDeptMap.get(key).add(rotationDept);
					}
					paramMap2.put("groupFlow",newGroup.getGroupFlow());
					paramMap2.put("standardDeptId",rotationDept.getStandardDeptId());
					//查询当前机构下当前届别当前标准科室的该方案下的SchRotationGroup oneGroup只能有一条
					List<SchRotationOrgDept> oneDept = rotationCfgBiz.searchSchRotationOrgDept(paramMap2);
					//复制对应标准科室下的SchRotationGroup
					SchRotationOrgDept newDept =null;
					//如果oneGroup为空则需要初始化
					if (oneDept == null || oneDept.size() < 1) {
						newDept=new SchRotationOrgDept();
						BeanUtil.mergeNotSameClassObject(rotationDept,newDept);
						//注意将groupFlow置空，因为要重新插入新的数据
						newDept.setRecordFlow("");
						newDept.setGroupFlow(newGroup.getGroupFlow());
						//标准科室下的SchRotationGroup没有绑定机构，需要绑定
						newDept.setOrgFlow(orgFlow);
						newDept.setOrgName(sysOrg.getOrgName());
						//绑定届别
						newDept.setSelectYear(selectYear);
						newDept.setSessionNumber(sessionNumber);
						rotationCfgBiz.saveSchRotationOrgDept(newDept);
					}else{
						newDept=oneDept.get(0);
					}
					standardOrgDeptMap.put(newDept.getGroupFlow()+newDept.getStandardDeptId(),newDept);
				}
				model.addAttribute("standardGroupDeptMap",standardGroupDeptMap);//标准科室 按组分类
				//本地化科室 按组与科室对应 1对1 DEPT中的group_flow 为org_group中的group_flow
				model.addAttribute("standardOrgDeptMap",standardOrgDeptMap);

			}


			//取本地化的标准科室信息
			Map<String,String> paramMap2 = new HashMap<>();
			paramMap2.put("rotationFlow",rotationFlow);
			paramMap2.put("orgFlow",orgFlow);
			paramMap2.put("sessionNumber",sessionNumber);
			paramMap2.put("selectYear",selectYear);
			paramMap2.put("schDeptFlowIsNotNull","schDeptFlowIsNotNull");
            paramMap2.put("recordStatus", com.pinde.core.common.GlobalConstant.FLAG_Y);
			List<SchRotationOrgDept> rotationDeptList = rotationCfgBiz.searchSchRotationOrgDept(paramMap2);
			if(rotationDeptList!=null && rotationDeptList.size()>0){
				Map<String,List<SchRotationOrgDept>> localRotationDeptListMap = new HashMap<String, List<SchRotationOrgDept>>();
				for(SchRotationOrgDept rotationDept : rotationDeptList){
					String key = rotationDept.getGroupFlow()+rotationDept.getStandardDeptId();
					if(localRotationDeptListMap.get(key)==null){
						List<SchRotationOrgDept> rotationDeptListTemp = new ArrayList<SchRotationOrgDept>();
						rotationDeptListTemp.add(rotationDept);
						localRotationDeptListMap.put(key,rotationDeptListTemp);
					}else{
						localRotationDeptListMap.get(key).add(rotationDept);
					}
				}
				model.addAttribute("localRotationDeptListMap",localRotationDeptListMap);
			}


				//医院轮转科室
				List<SysDept> sysDeptList = sysDeptBiz.searchDeptByOrg(orgFlow);
				if(sysDeptList!=null && sysDeptList.size()>0){
					List<String> deptFlows = new ArrayList<String>();
					for(SysDept dept : sysDeptList){
						deptFlows.add(dept.getDeptFlow());
					}
					List<SchDept> deptList = schDeptBiz.searchDeptByDeotFlows(deptFlows);
					model.addAttribute("deptList",deptList);
				}

		}
		return "sch/rotationCfg/rule";
	}

	@RequestMapping(value = {"/checkRotationCfg"},method = RequestMethod.POST)
	@ResponseBody
	public Object checkRotationCfg( String selectYear, String orgFlow, String sessionNumber, String rotationFlow){
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		if(StringUtil.isBlank(sessionNumber))
		{
			return "请选择年级！";
		}
		if(StringUtil.isBlank(selectYear))
		{
			return "请选择培养年限！";
		}
		if(StringUtil.isBlank(rotationFlow))
		{
			return "请选择轮转方案！";
		}
		if(SchSelYearEnum.One.getId().equals(selectYear))
		{
			selectYear="1";
		}
		if(SchSelYearEnum.Two.getId().equals(selectYear))
		{
			selectYear="2";
		}
		if(SchSelYearEnum.Three.getId().equals(selectYear))
		{
			selectYear="3";
		}
		int c=rotationCfgBiz.checkRotationCfg(orgFlow,selectYear,sessionNumber,rotationFlow);
		return c;
	}
	@RequestMapping(value = {"/checkHaveSch"},method = RequestMethod.POST)
	@ResponseBody
	public Object checkHaveSch(String orgFlow, String sessionNumber, String rotationFlow){
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());

		int sch=rotationCfgBiz.checkSchNum(orgFlow,sessionNumber,rotationFlow);
		int sel=rotationCfgBiz.checkSelNum(orgFlow,sessionNumber,rotationFlow);
		Map<String,Object> resp=new HashMap<>();
		resp.put("sch",sch);
		resp.put("sel",sel);
		return resp;
	}
	@RequestMapping(value = {"/saveCycleCfg"},method = RequestMethod.POST)
	@ResponseBody
	public Object saveCycleCfg(String orgFlow, String sessionNumber, String rotationFlow, String cycleTypeId){
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		rotationCfgBiz.saveCycleCfg(orgFlow, sessionNumber, rotationFlow, cycleTypeId);
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}

	@RequestMapping(value = {"/saveRotationCfg"},method = RequestMethod.POST)
	@ResponseBody
	public String saveCfg(@RequestBody List<SchRotationOrgGroupForm> groupForms, String selectYear, String rotationFlow, String orgFlow, String sessionNumber) throws Exception {
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		if(StringUtil.isBlank(sessionNumber))
		{
			return "请选择年级！";
		}
		if(StringUtil.isBlank(selectYear))
		{
			return "请选择培养年限！";
		}
		if(StringUtil.isBlank(rotationFlow))
		{
			return "请选择轮转方案！";
		}
		if(groupForms==null||groupForms.size()<=0)
		{
			return "请保留至少一个轮转科室信息！";
		}
		System.err.println(JSON.toJSONString(groupForms));
		rotationCfgBiz.saveCfg(groupForms,orgFlow,selectYear,sessionNumber,rotationFlow);
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}

	public static String[] source = { "1", "2", "3" };//源数据
	public static int len = source.length;    //源数据大小
	public static int blen = 1 << len;    //全组合数量为2的len次方

	public static void main(String[] args) {
		fullComb();
	}
	/**
	 * 计算全组合
	 */
	public static void fullComb() {
		for (int i = 0; i < blen; i++) {    //遍历所有对应的二进制数字
			System.out.println(getComb(source, i)); //打印当前数字对应的组合
		}
	}
	/**
	 * 遍历数字 计算出对应的组合
	 * @param source
	 * @param index
	 * @return
	 */
	public static List<String> getComb(String[] source, int index) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < len; i++) {    //逐个遍历为禁止的位
			int tmp = index << i;
			if ((tmp & blen>>1) != 0) {    //遇到1就将数据加入组合中
				list.add(source[i]);
			}
		}
		return list;
	}

	/**
	 * 加载报名时间配置信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/enterCfg")
	public String enterCfg(Model model){
		String orgFlow =GlobalContext.getCurrentUser().getOrgFlow();
		ResEnterOpenCfg resEnterOpenCfg = enterOpenCfgBiz.readResEnterOpenCfg(orgFlow);
		model.addAttribute("resEnterOpenCfg",resEnterOpenCfg);
		return "sch/rotationCfg/enterCfg";
	}

	@RequestMapping("/saveEnterCfg")
	@ResponseBody
	public String saveEnterCfg(ResEnterOpenCfg enterOpenCfg){
		String orgFlow=enterOpenCfg.getOrgFlow();
		if(StringUtil.isBlank(enterOpenCfg.getOrgFlow()))
		{

			orgFlow =GlobalContext.getCurrentUser().getOrgFlow();
		}
		SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
		enterOpenCfg.setOrgFlow(orgFlow);
		if(sysOrg!=null){
			enterOpenCfg.setOrgName(sysOrg.getOrgName());
		}
		ResEnterOpenCfg resEnterOpenCfg = enterOpenCfgBiz.readResEnterOpenCfg(orgFlow);
		if(resEnterOpenCfg!=null)
			enterOpenCfg.setCfgFlow(resEnterOpenCfg.getCfgFlow());
		int result = enterOpenCfgBiz.saveEnterOpenCfg(enterOpenCfg);
        if (result > com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 加载排班时间配置信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/arrangeCfg")
	public String arrangeCfg(Model model){
		String orgFlow =GlobalContext.getCurrentUser().getOrgFlow();
		List<SchArrangeTime> times=enterOpenCfgBiz.getArrangeTimes(orgFlow);
		Map<String,String> canEditMap=new HashMap<>();
		if(times!=null) {
			for (SchArrangeTime time : times) {
				int count = doctorDeptBiz.findSesssionNumberResults(time.getSessionNumber(), time.getOrgFlow());
				if (count > 0) {
                    canEditMap.put(time.getRecordFlow(), com.pinde.core.common.GlobalConstant.FLAG_N);
				} else {
                    canEditMap.put(time.getRecordFlow(), com.pinde.core.common.GlobalConstant.FLAG_Y);
				}
			}
		}
		model.addAttribute("canEditMap",canEditMap);
		model.addAttribute("times",times);
		return "sch/arrangeCfg/list";
	}
	@RequestMapping("/addArrangeCfg")
	public String addArrangeCfg(String recordFlow,Model model){

		SchArrangeTime time=enterOpenCfgBiz.getArrangeTime(recordFlow);
		String orgFlow =GlobalContext.getCurrentUser().getOrgFlow();
		model.addAttribute("orgFlow",orgFlow);
        model.addAttribute("canEdit", com.pinde.core.common.GlobalConstant.FLAG_Y);
		if(time!=null)
		{
			int count=doctorDeptBiz.findSesssionNumberResults(time.getSessionNumber(),time.getOrgFlow());
			if(count>0)
			{
                model.addAttribute("canEdit", com.pinde.core.common.GlobalConstant.FLAG_N);
			}
		}
		model.addAttribute("time",time);
		return "sch/arrangeCfg/addArrangeCfg";
	}

	@RequestMapping("/saveArrangeCfg")
	@ResponseBody
	public String saveArrangeCfg(SchArrangeTime time){
		String orgFlow=time.getOrgFlow();
		if(StringUtil.isBlank(time.getOrgFlow()))
		{
			orgFlow =GlobalContext.getCurrentUser().getOrgFlow();
		}
		if(StringUtil.isNotBlank(time.getRecordFlow()))
		{
			SchArrangeTime old=enterOpenCfgBiz.getArrangeTime(time.getRecordFlow());
			if(old==null)
			{
				old=enterOpenCfgBiz.getArrangeTimeByOrgYear(time.getSessionNumber(),orgFlow);
			}
			if(old!=null) {
				if(!old.getRecordFlow().equals(time.getRecordFlow()))
				{
					return "当前年级已维护排班时间！";
				}
				if (!orgFlow.equals(old.getOrgFlow()))
				{
					return "当前登录人已改变，请重新登录！";
				}
				int count = doctorDeptBiz.findSesssionNumberResults(old.getSessionNumber(), old.getOrgFlow());
				if (count > 0) {
					return "当前年级已有学员安排排班，无法再次修改！";
				}
			}
		}else{
			SchArrangeTime old=enterOpenCfgBiz.getArrangeTimeByOrgYear(time.getSessionNumber(),orgFlow);
			if(old!=null)
			{
				return "当前年级已维护排班时间！";
			}
		}
		time.setOrgFlow(orgFlow);
		int result = enterOpenCfgBiz.saveSchArrangeTime(time);
        if (result > com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

}

