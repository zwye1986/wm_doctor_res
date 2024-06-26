package com.pinde.sci.ctrl.sch;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IResRotationOrgBiz;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.res.RecDocCategoryEnum;
import com.pinde.sci.enums.res.RegistryTypeEnum;
import com.pinde.sci.enums.res.ResDoctorStatusEnum;
import com.pinde.sci.enums.res.ResRecTypeEnum;
import com.pinde.sci.enums.sch.SchRotationMedicineType;
import com.pinde.sci.enums.sch.SchStatusEnum;
import com.pinde.sci.enums.sch.SchUnitEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.sch.DoctorSearchForm;
import com.pinde.sci.form.sch.SchArrangeResultForm;
import com.pinde.sci.form.sch.SchRotationDeptForm;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/schTwo")
public class SchDocTwoController extends GeneralController{

	private static Logger logger = LoggerFactory.getLogger(SchDocTwoController.class);


	@Autowired
	private ISchAndStandardDeptCfgBiz deptCfgBiz;
	@Autowired
	private ISchDoctorBiz schDoctortBiz;
	@Autowired
	private ISchRotationBiz schRotationtBiz;
	@Autowired
	private ISchArrangePeriodRelBiz schArrangePeriodRelBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private ISchRotationGroupBiz schRotationtGroupBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private IDeptBiz sysDeptBiz;
	@Autowired
	private ISchDoctorDeptBiz schDoctorDeptBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private ISchDoctorAbsenceBiz schDoctorAbsenceBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private ISchDeptRelBiz deptRelBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IResRotationOrgBiz resRotationOrgBiz;

	/**
	 * 医师信息列表
	 * */
	@RequestMapping(value = {"/doc/userInfo/list" },method = RequestMethod.GET)
	public String doctorList (DoctorSearchForm doctorSearchForm,Model model) throws Exception{
		List<ResDoctor> doctorList = schDoctortBiz.searchResDoctorAll(GlobalContext.getCurrentUser().getOrgFlow(),doctorSearchForm);
		model.addAttribute("doctorList",doctorList);
		return "schTwo/doc/userInfo/list";
	}

	/**
	 * 医师信息查询
	 * */
	@RequestMapping(value = {"/doc/userInfo/searchList" }, method = RequestMethod.POST)
	public String searchList (DoctorSearchForm doctorSearchForm,Model model) throws Exception{
		return doctorList(doctorSearchForm,model);
	}

	/**
	 * 医师信息编辑
	 * */
	@RequestMapping(value = {"/doc/userInfo/editDoc" }, method = RequestMethod.GET)
	public String editDoc (ResDoctor doctor,Model model) throws Exception{
		if(doctor != null && StringUtil.isNotBlank(doctor.getDoctorFlow())){
			doctor = schDoctortBiz.readResDoctor(doctor.getDoctorFlow());
		}
		model.addAttribute("schDoctor",doctor);

		List<SchRotation> rotationList = schRotationtBiz.searchSchRotation();
		model.addAttribute("rotationList",rotationList);

		return "schTwo/doc/userInfo/editDoc";
	}

	@RequestMapping(value = "/doc/userInfo/saveDoctor",method={RequestMethod.POST})
	@ResponseBody
	public String saveDoctor(ResDoctor doctor, Model model) throws Exception{
		 if(null != doctor){
			 doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			 doctor.setOrgName(GlobalContext.getCurrentUser().getOrgName());
			 doctor.setGraduatedName(DictTypeEnum.GraduateSchool.getDictNameById(doctor.getGraduatedId()));
			 doctor.setTrainingSpeName(DictTypeEnum.DoctorTrainingSpe.getDictNameById(doctor.getTrainingSpeId()));
			 SchRotation rotation = schRotationtBiz.readSchRotation(doctor.getRotationFlow());
			 if(rotation!=null){
				 doctor.setRotationName(rotation.getRotationName());
			 }
			 doctor.setDoctorStatusName(ResDoctorStatusEnum.getNameById(doctor.getDoctorStatusId()));
			 if(!StringUtil.isNotBlank(doctor.getRecordStatus())){
				 doctor.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			 }
			 int result = schDoctortBiz.saveResDoctor(doctor);
			 if(result != GlobalConstant.ZERO_LINE){
				 return GlobalConstant.SAVE_SUCCESSED;
			 }
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 *  轮转方案配置
	 * */
	@RequestMapping(value="/template/gToLocalList",method=RequestMethod.GET)
	public String gToLocalList(String orgFlow,Model model){
		if(StringUtil.isNotBlank(orgFlow)){
			List<SchRotation> updateList = getPlatformRotation(orgFlow);
			model.addAttribute("updateCount",updateList.size());
			return "redirect:/schTwo/template/gToLocalListInfo";
		}
		model.addAttribute("currRoleFlag",GlobalConstant.USER_LIST_GLOBAL);
		return "redirect:/schTwo/template/localList";
	}

	@RequestMapping(value="/template/gToLocalListInfo",method=RequestMethod.GET)
	@ResponseBody
	public String gToLocalListInfo(Integer updateCount){
		return "更新" + updateCount + "条方案！";
	}

	@RequestMapping(value="/template/localList")
	public String localList(String orgFlow,String currRoleFlag,Model model){
		model.addAttribute("currRoleFlag",currRoleFlag);
		model.addAttribute("roleFlag",GlobalConstant.USER_LIST_LOCAL);
		model.addAttribute("publishFlag",GlobalConstant.FLAG_Y);

		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("orgFlow",orgFlow);

		List<SchRotation> updateList = getPlatformRotation(orgFlow);

		if(updateList!=null){
			model.addAttribute("updateCount",updateList.size());
		}
		return "redirect:/schTwo/template/list";
	}

	@RequestMapping(value = "/template/list")
	public String rotationList (String roleFlag,SchRotation rotation,Integer updateCount,String currRoleFlag,String orgFlow,Model model,String rotationName) throws UnsupportedEncodingException {
		model.addAttribute("updateCount",updateCount);
		if(StringUtil.isNotBlank(rotationName)){
//			rotationName = new String(rotationName.getBytes("iso8859-1"),"UTF-8");
			rotation.setRotationName(rotationName);
			model.addAttribute("rotationName",rotationName);
		}
		if(StringUtil.isBlank(rotation.getDoctorCategoryId())){
			rotation.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
			if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
				rotation.setDoctorCategoryId("");
			}
		}
		if("all".equals(rotation.getDoctorCategoryId())){
			rotation.setDoctorCategoryId("");
		}
		//获取轮转方案（如果是对指定机构展示，则只显示非指定机构和指定本机构的）
		List<SchRotation> rotationList = schRotationtBiz.searchOrgStandardRotation(rotation);
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		if (!GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
			rotationList=schRotationtBiz.schRotations(rotationList,orgFlow);
		}
		model.addAttribute("rotationList",rotationList);

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

		//配置表单使用
		if(GlobalConstant.ROOT_USER_FLOW.equals(GlobalContext.getCurrentUser().getUserFlow())){
			String wsId = (String)getSessionAttribute(GlobalConstant.CURRENT_WS_ID);
			SysCfg cfg=new SysCfg();
			cfg.setWsId(wsId);
			List<SysCfg> sysCfgList=cfgBiz.search(cfg);
			Map<String, String> sysCfgMap=new HashMap<String, String>();
			for(SysCfg sysCfg:sysCfgList ){
				sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgValue());
				if(StringUtil.isNotBlank(sysCfg.getCfgBigValue())){
					sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgBigValue());
				}
			}
			model.addAttribute("sysCfgMap",sysCfgMap);
		}
		return "schTwo/template/list";
	}

	/**
	 * 本地标准方案配置
	 */
	@RequestMapping(value = {"/template/orgDiyRotationList" },method = RequestMethod.GET)
	public String orgDiyRotationList (SchRotation rotation,Model model){
		model.addAttribute("schRotation",rotation);
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
//		rotation.setOrgFlow(orgFlow);
//		rotation.setRotationTypeId(SchRotationTypeEnum.Standard.getId());
//		if(rotation.getSessionNumber()==null){
//			rotation.setSessionNumber(DateUtil.getYear());
//		}
		List<SchRotation> rotationList = schRotationtBiz.searchOrgStandardRotation(rotation);
		model.addAttribute("rotationList",rotationList);

		//配置表单使用
		if(GlobalConstant.ROOT_USER_FLOW.equals(GlobalContext.getCurrentUser().getUserFlow())){
			String wsId = (String)getSessionAttribute(GlobalConstant.CURRENT_WS_ID);
			SysCfg cfg=new SysCfg();
			cfg.setWsId(wsId);
			List<SysCfg> sysCfgList=cfgBiz.search(cfg);
			Map<String, String> sysCfgMap=new HashMap<String, String>();
			for(SysCfg sysCfg:sysCfgList ){
				sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgValue());
				if(StringUtil.isNotBlank(sysCfg.getCfgBigValue())){
					sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgBigValue());
				}
			}
			model.addAttribute("sysCfgMap",sysCfgMap);
		}
		return "schTwo/template/orgDiyRotationList";
	}

	/**
	 *  自动同步平台方案
	 * */
	private List<SchRotation> getPlatformRotation(String orgFlow){
		List<SchRotation> rotationList = schRotationtBiz.searchNotExistRotation(orgFlow);
		if(rotationList!=null && rotationList.size()>0){
			schRotationtBiz.saveLocalRotation(rotationList,orgFlow);
		}
		return rotationList;
	}

	@RequestMapping(value = {"/template/showExplanation"}, method = RequestMethod.GET)
	public String showExplanation(String rotationFlow,Model model){
		SchRotation rotation = null;
		if(StringUtil.isNotBlank(rotationFlow)){
			rotation = schRotationtBiz.readSchRotation(rotationFlow);
		}
		model.addAttribute("rotation",rotation);
		return "schTwo/template/viewRotationMain";
	}

	/**
	 * 跳转至方案编辑页面
	 * */
	@RequestMapping(value = {"/template/readRotation"}, method = RequestMethod.GET)
	public String readRotation (String rotationFlow, String viewFlag, Model model){
		SchRotation rotation = null;
		if(StringUtil.isNotBlank(rotationFlow)){
			rotation = schRotationtBiz.readSchRotation(rotationFlow);
		}
		model.addAttribute("rotation",rotation);
		return "schTwo/template/viewRotation";
	}

	/**
	 * 跳转至方案编辑页面
	 * */
	@RequestMapping(value = {"/template/editRotation"}, method = RequestMethod.GET)
	public String editRotation (String rotationFlow, String viewFlag, Model model){
		SchRotation rotation = null;
		if(StringUtil.isNotBlank(rotationFlow)){
			rotation = schRotationtBiz.readSchRotation(rotationFlow);
		}
		model.addAttribute("rotation",rotation);
		if(GlobalConstant.FLAG_Y.equals(viewFlag)){
			return "schTwo/template/viewRotation";
		}
		return "schTwo/template/editRotation";
	}

	@RequestMapping(value = "/template/saveRotation",method={RequestMethod.POST})
	@ResponseBody
	public String saveRotation(SchRotation rotation, Model model) throws Exception{
		 if(null != rotation){
			 rotation.setSpeName("");
			 if(StringUtil.isNotBlank(rotation.getSpeId())){
				 if(rotation.getDoctorCategoryId().equals(RecDocCategoryEnum.Doctor.getId())){
					 rotation.setSpeName(DictTypeEnum.DoctorTrainingSpe.getDictNameById(rotation.getSpeId()));
				 }else if(rotation.getDoctorCategoryId().equals(RecDocCategoryEnum.WMFirst.getId()) ){
					 rotation.setSpeName(DictTypeEnum.WMFirst.getDictNameById(rotation.getSpeId()));
				 }else if(rotation.getDoctorCategoryId().equals(RecDocCategoryEnum.WMSecond.getId()) ){
					 rotation.setSpeName(DictTypeEnum.WMSecond.getDictNameById(rotation.getSpeId()));
				 }else if(rotation.getDoctorCategoryId().equals(RecDocCategoryEnum.AssiGeneral.getId()) ){
					 rotation.setSpeName(DictTypeEnum.AssiGeneral.getDictNameById(rotation.getSpeId()));
				 }else if(rotation.getDoctorCategoryId().equals(RecDocCategoryEnum.ChineseMedicine.getId()) ){
					 String speName=DictTypeEnum.ChineseMedicine.getDictNameById(rotation.getSpeId());
					 if(StringUtil.isBlank(speName))
						 speName=DictTypeEnum.SecondTrainingSpe.getDictNameById(rotation.getSpeId())+"(二级专业)";
					 rotation.setSpeName(speName);
				 }else if(rotation.getDoctorCategoryId().equals(RecDocCategoryEnum.TCMGeneral.getId()) ){
					 rotation.setSpeName(DictTypeEnum.TCMGeneral.getDictNameById(rotation.getSpeId()));
				 }else if(rotation.getDoctorCategoryId().equals(RecDocCategoryEnum.TCMAssiGeneral.getId()) ){
					 rotation.setSpeName(DictTypeEnum.TCMAssiGeneral.getDictNameById(rotation.getSpeId()));
				 }else{
					 String speName=DictTypeEnum.DoctorTrainingSpe.getDictNameById(rotation.getSpeId());
					 rotation.setSpeName(speName);
				 }
			 }

			 if(StringUtil.isNotBlank(rotation.getDoctorCategoryId())){
				 rotation.setDoctorCategoryName(RecDocCategoryEnum.getNameById(rotation.getDoctorCategoryId()));
			 }else{
				 rotation.setDoctorCategoryName("");
			 }

			 if(StringUtil.isNotBlank(rotation.getRotationTypeId())){
				 rotation.setRotationTypeName(SchRotationMedicineType.getNameById(rotation.getRotationTypeId()));
			 }else{
				 rotation.setRotationTypeName("");
			 }

//			 if(StringUtil.isNotBlank(rotation.getRotationTypeId())){
//				 rotation.setRotationTypeName(SchRotationTypeEnum.getNameById(rotation.getRotationTypeId()));
//			 }else{
//				 rotation.setRotationTypeName("");
//			 }

			 if(StringUtil.isNotBlank(rotation.getRotationFlow()) && StringUtil.isNotBlank(rotation.getRotationName())){
				 SchRotation oldRotation = schRotationtBiz.readSchRotation(rotation.getRotationFlow());
				 if(oldRotation!=null){
					 if(!oldRotation.getRotationName().equals(rotation.getRotationName())){
							ResDoctor doctor = new ResDoctor();
							doctor.setRotationFlow(rotation.getRotationFlow());
							doctor.setRotationName(rotation.getRotationName());
							doctorBiz.updateRedundancyData(doctor);
						}
				 }
			 }

			 int result = schRotationtBiz.saveSchRotation(rotation);
			 if(result != GlobalConstant.ZERO_LINE){
				 return GlobalConstant.SAVE_SUCCESSED;
			 }
		}
		return GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value = "/template/publishRotation",method={RequestMethod.POST})
	@ResponseBody
	public String publishRotation(SchRotation rotation, Model model) throws Exception{
		 if(null != rotation){
			 int result = schRotationtBiz.saveSchRotation(rotation);
			 if(result != GlobalConstant.ZERO_LINE){
				 return GlobalConstant.SAVE_SUCCESSED;
			 }
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 轮转规则配置
	 * */
	@RequestMapping(value = {"/template/rule"},method = RequestMethod.GET)
	public String rule (String roleFlag,String rotationFlow,String orgFlow,String currRoleFlag,
						Model model,String sessionNumber,HttpServletRequest request){
		if(StringUtil.isNotBlank(orgFlow) && StringUtil.isNotBlank(currRoleFlag)){
			getPlatformRotation(orgFlow);
		}

		if(StringUtil.isNotBlank(rotationFlow)){
			SchRotation rotation = schRotationtBiz.readSchRotation(rotationFlow);
			model.addAttribute("rotation",rotation);

			List<SchRotationDept> standardRotationDeptList = schRotationDeptBiz.searchSchRotationDept(rotationFlow);
			if(standardRotationDeptList!=null && standardRotationDeptList.size()>0){
				model.addAttribute("standardRotationDeptList",standardRotationDeptList);

				Map<String,List<SchRotationDept>> standardGroupDeptMap = new HashMap<String, List<SchRotationDept>>();
				for(SchRotationDept rotationDept : standardRotationDeptList){
					String key = rotationDept.getGroupFlow();
					if(standardGroupDeptMap.get(key)==null){
						List<SchRotationDept> standardRotationDeptTempList = new ArrayList<SchRotationDept>();
						standardRotationDeptTempList.add(rotationDept);
						standardGroupDeptMap.put(key,standardRotationDeptTempList);
					}else{
						standardGroupDeptMap.get(key).add(rotationDept);
					}
				}
				model.addAttribute("standardGroupDeptMap",standardGroupDeptMap);
			}


			List<SchRotationGroup> standardRotationGroupList = schRotationtGroupBiz.searchSchRotationGroup(rotationFlow);
			model.addAttribute("standardRotationGroupList",standardRotationGroupList);

			if(!GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
				orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());

				if(StringUtil.isBlank(sessionNumber)){
					String[] time = DateUtil.getCurrMonth().split("-");
					String currenYear = time[0];
					String currenMonth = time[1];
					if(Integer.parseInt(currenMonth) > 8)
						sessionNumber = currenYear;
					else
						sessionNumber = Integer.parseInt(currenYear) -1 + "";
				}

				Map<String,String> paramMap2 = new HashMap<>();
				paramMap2.put("rotationFlow",rotationFlow);
				paramMap2.put("orgFlow",orgFlow);
				paramMap2.put("sessionNumber",sessionNumber);
				paramMap2.put("schDeptFlowIsNotNull","schDeptFlowIsNotNull");
				model.addAttribute("sessionNumber",sessionNumber);
				List<SchRotationDept> rotationDeptList = schRotationDeptBiz.searchSchRotationDeptByItems(paramMap2);
				if(rotationDeptList!=null && rotationDeptList.size()>0){
					Map<String,List<SchRotationDept>> localRotationDeptListMap = new HashMap<String, List<SchRotationDept>>();
					for(SchRotationDept rotationDept : rotationDeptList){
						String key = rotationDept.getGroupFlow()+rotationDept.getStandardDeptId();
						if(localRotationDeptListMap.get(key)==null){
							List<SchRotationDept> rotationDeptListTemp = new ArrayList<SchRotationDept>();
							rotationDeptListTemp.add(rotationDept);
							localRotationDeptListMap.put(key,rotationDeptListTemp);
						}else{
							localRotationDeptListMap.get(key).add(rotationDept);
						}
					}
					model.addAttribute("localRotationDeptListMap",localRotationDeptListMap);
				}

				SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);

				//初始化该基地的SchRotationGroup(区分届别)
				//考虑到不同组不同界别的轮转时间不一样
				//因为方案发布以后还可以对方案进行编辑，可以新增组，
				//所以需要对方案里的group进行逐条判断并初始化
				if(standardRotationGroupList != null && standardRotationGroupList.size() > 0){
					for(SchRotationGroup tempGroup : standardRotationGroupList){
						Map<String,String> tempMap = new HashMap<>();
						tempMap.put("rotationFlow",rotationFlow);
						tempMap.put("sessionNumber",sessionNumber);
						tempMap.put("orgFlow",orgFlow);
						tempMap.put("standardGroupFlow",tempGroup.getGroupFlow());
						//查询当前机构下当前届别当前标准科室的该方案下的SchRotationGroup oneGroup只能有一条
						List<SchRotationGroup> oneGroup = schRotationtGroupBiz.searchSchRotationGroupByItems(tempMap);
						//如果oneGroup为空则需要初始化
						if (oneGroup == null || oneGroup.size() < 1) {
							//复制对应标准科室下的SchRotationGroup
							SchRotationGroup newGroup = new SchRotationGroup();
							BeanUtil.mergeObject(tempGroup,newGroup);
							//注意将groupFlow置空，因为要重新插入新的数据
							newGroup.setGroupFlow("");
							//标准科室下的SchRotationGroup没有绑定机构，需要绑定
							newGroup.setOrgFlow(orgFlow);
							newGroup.setOrgName(sysOrg.getOrgName());
							//绑定届别
							newGroup.setSessionNumber(sessionNumber);
							newGroup.setStandardGroupFlow(tempGroup.getGroupFlow());
							schRotationtGroupBiz.saveSchRotationGroup(newGroup);
						}
					}
				}

				Map<String,String> paramMap1 = new HashMap<>();
				paramMap1.put("rotationFlow",rotationFlow);
				paramMap1.put("sessionNumber",sessionNumber);
				paramMap1.put("orgFlow",orgFlow);
				//查询当前机构下当前届别的该方案下的SchRotationGroup
				List<SchRotationGroup> orgRotationGroupList = schRotationtGroupBiz.searchSchRotationGroupByItems(paramMap1);
				model.addAttribute("rotationGroupList",orgRotationGroupList);

				Map<String,SchRotationGroup> localGroupMap = new HashMap<String, SchRotationGroup>();
				for(SchRotationGroup group : orgRotationGroupList){
					localGroupMap.put(group.getStandardGroupFlow(),group);
				}
				model.addAttribute("localGroupMap",localGroupMap);

				List<SysDept> sysDeptList = sysDeptBiz.searchDeptByOrg(orgFlow);
				if(sysDeptList!=null && sysDeptList.size()>0){
					List<String> deptFlows = new ArrayList<String>();
					for(SysDept dept : sysDeptList){
						deptFlows.add(dept.getDeptFlow());
					}
					List<SchDept> deptList = schDeptBiz.searchDeptByDeotFlows(deptFlows);
					model.addAttribute("deptList",deptList);
				}

			}else{
				int selNum = schDoctorDeptBiz.countRotationUse(rotationFlow);
				int rosteringNum = schArrangeResultBiz.countRotationUse(rotationFlow);
				model.addAttribute("useCount",selNum+rosteringNum);
			}
		}
		return "schTwo/template/rule";
	}


	/**
	 *  关联的轮转科室
	 * */
	@RequestMapping(value = {"/template/relSchDept"},method = RequestMethod.POST)
	@ResponseBody
	public List<SchDeptRel> relSchDept(String standardDeptId,String orgFlow){
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		List<SchDeptRel> deptRelList = null;
		if(StringUtil.isNotBlank(standardDeptId)){
			deptRelList = deptRelBiz.searchRelByStandard(orgFlow, standardDeptId);
		}
		return deptRelList;
	}

	/**
	 *  更新当前操作区域下的映射科室
	 * */
	@RequestMapping(value = "/template/reUpdateRotationDept",method={RequestMethod.POST})
	public String reUpdateRotationDept(String rotationFlow,String standardDeptId,String groupFlow,String orgFlow,Model model){
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		SysOrg org = orgBiz.readSysOrg(orgFlow);
		int result = schRotationDeptBiz.updateAreaRule(rotationFlow,standardDeptId,groupFlow,org);
		if(result!=GlobalConstant.ZERO_LINE){
			List<SchRotationDept> rotationDeptList = schRotationDeptBiz.searchDeptByStandard(rotationFlow,groupFlow,standardDeptId,orgFlow);
			model.addAttribute("rotationDeptList",rotationDeptList);

			List<SchDeptRel> deptRelList = deptRelBiz.searchRelByStandard(orgFlow,standardDeptId);
			model.addAttribute("deptRelList",deptRelList);

			SchRotationDept standardRotationDept =  schRotationDeptBiz.readStandardRotationDeptByLocal(rotationFlow,groupFlow,standardDeptId);
			model.addAttribute("standardRotationDept",standardRotationDept);

			if(StringUtil.isNotBlank(groupFlow)){
				SchRotationGroup group = schRotationtGroupBiz.readSchRotationGroup(groupFlow);
				model.addAttribute("group",group);
			}
		}
		return "schTwo/template/ruleCfg";
	}

	/**
	 *  微调轮转科室
	 * */
	@RequestMapping(value = {"/template/saveCfg"},method = RequestMethod.POST)
	@ResponseBody
	public String saveCfg(@RequestBody List<SchRotationDept> rotationDeptList,String groupFlow,Integer deptNum,Integer maxDeptNum,String orgFlow,String sessionNumber){
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());

		if(rotationDeptList!=null && rotationDeptList.size() > 0){
			//修改的轮转时间的和,每次修改的SchRotationDept是对应同一
			// orgFlow，rotationFlow，standarddeptId，groupFlow。每次
			// 修改后需将修改后的时间和SchMonth更新到对应的SchRotationGroup中

			Double standardDeptSchMonth = 0.0;//用于计算每次修改标准科室对应的SchRotationDept时间的和
			String standarddeptId = rotationDeptList.get(0).getStandardDeptId();
			String rotationFlow = rotationDeptList.get(0).getRotationFlow();
			for(SchRotationDept rotationDept : rotationDeptList){
				String schMonth = rotationDept.getSchMonth();
				Double dSchMonth = 0.0;
				if(StringUtil.isNotBlank(schMonth)){
					dSchMonth = Double.parseDouble(schMonth);
				}
				standardDeptSchMonth += dSchMonth;
				String schDeptFlow = rotationDept.getSchDeptFlow();
				if(StringUtil.isNotBlank(schDeptFlow)){
					SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
					rotationDept.setSchDeptName(dept.getSchDeptName());
					rotationDept.setDeptFlow(dept.getDeptFlow());
					rotationDept.setDeptName(dept.getDeptName());
				}else{
					rotationDept.setSchDeptName("");
					rotationDept.setDeptFlow("");
					rotationDept.setDeptName("");
				}

				if(StringUtil.isNotBlank(orgFlow)){
					SysOrg org = orgBiz.readSysOrg(orgFlow);
					if(org!=null){
						rotationDept.setOrgFlow(org.getOrgFlow());
						rotationDept.setOrgName(org.getOrgName());
					}
				}

				if(StringUtil.isNotBlank(sessionNumber)){
					rotationDept.setSessionNumber(sessionNumber);
				}
			}

			SchRotationGroup group = null;
			SchRotationDept schRotationDept4Search = new SchRotationDept();

			if(StringUtil.isNotBlank(groupFlow)){
				schRotationDept4Search.setRotationFlow(rotationFlow);
				schRotationDept4Search.setStandardDeptId(standarddeptId);
				schRotationDept4Search.setGroupFlow(groupFlow);
				Map<String,String> paramemap = new HashMap<>();
				paramemap.put("rotationFlow",rotationFlow);
				paramemap.put("groupFlow",groupFlow);
				paramemap.put("orgFlow",orgFlow);
				paramemap.put("sessionNumber",sessionNumber);
				List<SchRotationDept> schRotationDepts = schRotationDeptBiz.searchDeptByMap(paramemap);
				Double otherStandardDeptSchMonth = 0.0;//用于计算未修改标准科室对应的SchRotationDept时间的和
				if(schRotationDepts != null && schRotationDepts.size() > 0){
					for (SchRotationDept tempSchDept : schRotationDepts) {
						if(!tempSchDept.getStandardDeptId().equals(standarddeptId)){
						    Double tempSchMonth = Double.parseDouble(tempSchDept.getSchMonth());
							otherStandardDeptSchMonth += tempSchMonth;
						}
					}
				}
				group = new SchRotationGroup();
				group.setGroupFlow(groupFlow);
				group.setDeptNum(deptNum);
				if(maxDeptNum!=null && maxDeptNum!=0){
					group.setMaxDeptNum(maxDeptNum);
				}
				// SchRotationGroup中SchMonth = 该组下所有未改SchRotationDept的SchMonth时间和 + 该组下所有已修改SchRotationDept的SchMonth时间和
				group.setSchMonth(standardDeptSchMonth + otherStandardDeptSchMonth + "");
				group.setSessionNumber(sessionNumber);
			}
			schRotationDeptBiz.saveRotationDeptList(rotationDeptList,group);

		}
		return GlobalConstant.SAVE_SUCCESSED;
	}

	/**
	 *  检查轮转科室是否有学员已经出科
	 * */
	@RequestMapping(value = {"/template/checkSchDeptHasOutDoctor"},method = RequestMethod.GET)
	@ResponseBody
	public String checkSchDeptHasOutDoctor(String rotationFlow, String groupFlow, String standardDeptId, String shcDeptFlow) {
		if (StringUtil.isBlank(rotationFlow) || StringUtil.isBlank(groupFlow) ||
				StringUtil.isBlank(standardDeptId) || StringUtil.isBlank(shcDeptFlow)) {
			return GlobalConstant.FLAG_Y;
		}
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("rotationFlow",rotationFlow);
		paramMap.put("groupFlow",groupFlow);
		paramMap.put("standardDeptId",standardDeptId);
		paramMap.put("shcDeptFlow",shcDeptFlow);
		List<SchArrangeResult> results = schArrangeResultBiz.searchProcessByItems(paramMap);
		if(results != null && results.size() > 0){
			String resultFlow = results.get(0).getResultFlow();
			ResDoctorSchProcess process = processBiz.searchByResultFlow(resultFlow);
			if(process != null && GlobalConstant.FLAG_Y.equals(process.getSchFlag())){
				return GlobalConstant.FLAG_N;
			}
		}
		return GlobalConstant.FLAG_Y;
	}
	/**
	 *  轮转规范
	 * */
	@RequestMapping(value = {"/template/rotationStandard"},method = RequestMethod.GET)
	public String rotationStandard(String recordFlow,Model model){
		SchRotationDept rotationDept = schRotationDeptBiz.readSchRotationDept(recordFlow);
		model.addAttribute("rotationDept",rotationDept);
		return "schTwo/template/rotationStandard";
	}

	@RequestMapping(value = {"/template/saveStandard"},method = RequestMethod.POST)
	@ResponseBody
	public String saveStandard(SchRotationDept rotationDept){
		if(rotationDept!=null){
			if(schRotationDeptBiz.saveSchRotationDept(rotationDept)!=GlobalConstant.ZERO_LINE){
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 *  轮转要求
	 * */
	@RequestMapping(value = {"/template/rotationRequire"},method = RequestMethod.GET)
	public String rotationRequire(String recordFlow,String rotationFlow,Model model){
		SchRotation rotation = schRotationtBiz.readSchRotation(rotationFlow);
		model.addAttribute("rotation",rotation);

		List<SchRotationDept> rotationDeptList = schRotationDeptBiz.searchSchRotationDept(rotationFlow);
		model.addAttribute("rotationDeptList",rotationDeptList);

		SchRotationDept rotationDept = schRotationDeptBiz.readSchRotationDept(recordFlow);
		model.addAttribute("rotationDept",rotationDept);

		List<SchRotationGroup> groupList = schRotationtGroupBiz.searchSchRotationGroup(rotationFlow);
		if(groupList!=null && groupList.size()>0){
			Map<String,SchRotationGroup> groupMap = new HashMap<String, SchRotationGroup>();
			for(SchRotationGroup group : groupList){
				groupMap.put(group.getGroupFlow(),group);
			}
			model.addAttribute("groupMap",groupMap);
		}

		return "schTwo/template/rotationRequire";
	}

	@RequestMapping(value = {"/template/requireList"},method = RequestMethod.GET)
	public String requireList(SchRotationDeptReq deptReq,Model model){
		if(deptReq !=null){
			//默认添加其他项
			schRotationDeptBiz.defaultOtherItem(deptReq.getRelRecordFlow(),deptReq.getRecTypeId());

			List<SchRotationDeptReq> deptReqList = schRotationDeptBiz.searchDeptReqByRel(deptReq.getRelRecordFlow(),deptReq.getRecTypeId());
			model.addAttribute("deptReqList",deptReqList);
		}
		return "schTwo/template/requireList";
	}
	@RequestMapping("/template/synchRequire")
	public String synchRequire( Model model, SchRotation rotation){

		List<SchRotation> rotationList = schRotationtBiz.searchOrgStandardRotation(rotation);
		model.addAttribute("rotationList",rotationList);

		List<SchRotationGroup> standardRotationGroupList = schRotationtGroupBiz.searchAllSchRotationGroup();
		model.addAttribute("standardRotationGroupList",standardRotationGroupList);

		List<SchRotationDept> standardRotationDeptList = schRotationDeptBiz.searchAllSchRotationDept();
		model.addAttribute("standardRotationDeptList",standardRotationDeptList);

		return "schTwo/template/synchRequire";
	}
	@RequestMapping("/template/synchronize")
	@ResponseBody
	public String synchronize( Model model,String relRecordFlow,String currRelRecordFlow,String recTypeId){
		if(StringUtil.isNotBlank(relRecordFlow) && StringUtil.isNotBlank(currRelRecordFlow)){
			int result=schRotationDeptBiz.synchronizeReq(relRecordFlow, currRelRecordFlow,recTypeId);
			if(result != GlobalConstant.ZERO_LINE){
				return GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}
	@RequestMapping(value = {"/template/editDeptReq"},method = RequestMethod.GET)
	public String editDeptReq(String reqFlow,Model model) throws Exception{
		SchRotationDeptReq deptReq = schRotationDeptBiz.readDeptReq(reqFlow);
		model.addAttribute("deptReq",deptReq);
		return "schTwo/template/editRequire";
	}

	@RequestMapping(value = {"/template/saveDeptReq"},method = RequestMethod.POST)
	@ResponseBody
	public String saveDeptReq(SchRotationDeptReq deptReq){
		String returnStrF = GlobalConstant.SAVE_FAIL;
		String returnStrS = GlobalConstant.SAVE_SUCCESSED;
		if(deptReq!=null){
			if(StringUtil.isNotBlank(deptReq.getRecordStatus()) && GlobalConstant.RECORD_STATUS_N.equals(deptReq.getRecordStatus())){
				returnStrF = GlobalConstant.DELETE_FAIL;
				returnStrS = GlobalConstant.DELETE_SUCCESSED;
			}
			if(!StringUtil.isNotBlank(deptReq.getReqFlow())){
				SchRotationDept rotationDept = schRotationDeptBiz.readSchRotationDept(deptReq.getRelRecordFlow());
				if(rotationDept!=null){
					deptReq.setOrgFlow(rotationDept.getOrgFlow());
					deptReq.setOrgName(rotationDept.getOrgName());
					deptReq.setStandardDeptId(rotationDept.getStandardDeptId());
					deptReq.setStandardDeptName(rotationDept.getStandardDeptName());
				}
				if(StringUtil.isNotBlank(deptReq.getRecTypeId())){
					RegistryTypeEnum rte = EnumUtil.getById(deptReq.getRecTypeId(),RegistryTypeEnum.class);
					if(rte!=null && GlobalConstant.FLAG_Y.equals(rte.getHaveItem())){
						deptReq.setItemId(PkUtil.getUUID());
					}
				}
			}

			if(StringUtil.isNotBlank(deptReq.getRecTypeId())){
				deptReq.setRecTypeName(RegistryTypeEnum.getNameById(deptReq.getRecTypeId()));
			}

			if(schRotationDeptBiz.editDeptReq(deptReq)!=GlobalConstant.ZERO_LINE){
				return returnStrS;
			}
		}
		return returnStrF;
	}

	@RequestMapping(value = {"/template/readCaseReq"},method = RequestMethod.GET)
	@ResponseBody
	public SchRotationDeptReq readCaseReq(String relRecordFlow,String recTypeId){
		SchRotationDeptReq deptReq = null;
		if(StringUtil.isNotBlank(relRecordFlow) && StringUtil.isNotBlank(recTypeId)){
			List<SchRotationDeptReq> deptReqList = schRotationDeptBiz.searchDeptReqByRel(relRecordFlow,recTypeId);
			if(deptReqList!=null && deptReqList.size()>0){
				deptReq = deptReqList.get(0);
			}
		}
		return deptReq;
	}

	/**
	 * 轮转规则编辑
	 * */
	@RequestMapping(value = {"/template/deptcfg_edit"}, method = RequestMethod.GET)
	public String deptCfgEdit (String groupFlow,String rotationFlow,String roleFlag,Model model) throws Exception{
		if(StringUtil.isNotBlank(rotationFlow)){
			Map<String,SchRotationDept> rotationDeptMap = new HashMap<String,SchRotationDept>();
			List<SchRotationDept> rotationDeptList = null;
			if(StringUtil.isNotBlank(groupFlow)){
				rotationDeptList = schRotationtGroupBiz.readSchRotationDept(groupFlow);
				SchRotationGroup rotationGroup = schRotationtGroupBiz.readSchRotationGroup(groupFlow);
				model.addAttribute("rotationGroup",rotationGroup);
			}else{
				//rotationDeptList = schRotationDeptBiz.searchSchRotationDeptMust(rotationFlow);
			}
			if(rotationDeptList!=null && rotationDeptList.size()>0){
				for(SchRotationDept rotationDept : rotationDeptList){
					String key = null;
					if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
						key = rotationDept.getStandardDeptId();
					}else{
						key = rotationDept.getSchDeptFlow();
					}
					rotationDeptMap.put(key,rotationDept);
				}
				model.addAttribute("rotationDeptMap",rotationDeptMap);
			}

			SchRotation rotation = schRotationtBiz.readSchRotation(rotationFlow);
			model.addAttribute("rotation",rotation);
		}

//		List<SchDept> deptList = null;
//		if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
//			List<SysDict> dictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.StandardDept.getId());
//			if(dictList!=null && dictList.size()>0){
//				deptList = new ArrayList<SchDept>();
//				for(SysDict dict : dictList){
//					SchDept dept = new SchDept();
//					dept.setSchDeptFlow(dict.getDictId());
//					dept.setSchDeptName(dict.getDictName());
//					deptList.add(dept);
//				}
//			}
//		}else{
//			deptList = schDeptBiz.searchSchDeptList(GlobalContext.getCurrentUser().getOrgFlow());
//		}
//		model.addAttribute("deptList",deptList);

		return "schTwo/template/deptcfg_edit";
	}

	@RequestMapping(value = "/template/saveRotationDept",method={RequestMethod.POST})
	@ResponseBody
	public String saveRotationDept(@RequestBody SchRotationDeptForm rotationDeptFrom,String roleFlag,String rotationFlow,Model model){
		if(rotationDeptFrom != null && rotationDeptFrom.getRotationDeptList()!=null && rotationDeptFrom.getRotationDeptList().size()>0){
			if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
				for(SchRotationDept rotationDept : rotationDeptFrom.getRotationDeptList()){
					rotationDept.setStandardDeptId(rotationDept.getSchDeptFlow());
					rotationDept.setStandardDeptName(rotationDept.getSchDeptName());
					rotationDept.setSchDeptFlow(null);
					rotationDept.setSchDeptName(null);
				}
			}
			int result = schRotationDeptBiz.saveSchRotationDeptForm(rotationDeptFrom,rotationFlow);
			if(result != GlobalConstant.ZERO_LINE){
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value = "/template/saveRotationDeptOrd",method={RequestMethod.POST})
	@ResponseBody
	public String saveRotationDeptOrd(String[] recordFlow, Model model) throws Exception{
		int result = schRotationDeptBiz.saveRotationDeptOrd(recordFlow);
		if(result != GlobalConstant.ZERO_LINE){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value = "/template/delRotationDept",method={RequestMethod.POST})
	@ResponseBody
	public String delRotationDept(String recordFlow,String groupFlow,String rotationFlow){
		int result = schRotationDeptBiz.delGroupOrRotationDept(recordFlow,groupFlow,rotationFlow);
		if(result != GlobalConstant.ZERO_LINE){
			return GlobalConstant.DELETE_SUCCESSED;
		}
		return GlobalConstant.DELETE_FAIL;
	}

	/**
	 *  医师选科排班
	 * */
	@RequestMapping(value = {"/doc/seldept" },method = {RequestMethod.GET,RequestMethod.POST})
	public String selDept(ResDoctor doctor,Model model,Integer currentPage,HttpServletRequest request){
		if(currentPage==null){
			currentPage=1;
		}
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
//		if(doctor.getSessionNumber()==null){
//			doctor.setSessionNumber(DateUtil.getYear());
//		}
		if(doctor.getDoctorCategoryId()==null){
			String firstCategoryId = null;
			for(RecDocCategoryEnum docCategoryE : RecDocCategoryEnum.values()){
				String inUsed = InitConfig.getSysCfg("res_doctor_category_"+docCategoryE.getId());
				if(GlobalConstant.FLAG_Y.equals(inUsed)){
					firstCategoryId = docCategoryE.getId();
					break;
				}
			}
			doctor.setDoctorCategoryId(firstCategoryId);
		}
		doctor.setOrgFlow(orgFlow);
		model.addAttribute("doctor",doctor);

		PageHelper.startPage(currentPage,getPageSize(request));

		List<ResDoctor> doctorList = doctorBiz.searchSelDeptDoctor(doctor);
		if(doctorList!=null && doctorList.size()>0){
			model.addAttribute("doctorList",doctorList);

			List<String> rotationFlows = new ArrayList<String>();
			List<String> doctorFlows = new ArrayList<String>();
			for(ResDoctor doctorTemp : doctorList){
				String rotationFlow = doctorTemp.getRotationFlow();
				String doctorFlow = doctorTemp.getDoctorFlow();
				if(!rotationFlows.contains(rotationFlow)){
					rotationFlows.add(rotationFlow);
				}
				doctorFlows.add(doctorFlow);
			}

			//所有组合
			List<SchRotationGroup> groupList = schRotationtGroupBiz.searchOrgGroupByRotations(rotationFlows,orgFlow);
			if(groupList!=null && groupList.size()>0){
				Map<String,List<SchRotationGroup>> groupListMap = new HashMap<String, List<SchRotationGroup>>();
				for(SchRotationGroup group : groupList){
					String key = group.getRotationFlow();
					if(groupListMap.get(key)==null){
						List<SchRotationGroup> groupListTemp = new ArrayList<SchRotationGroup>();
						groupListTemp.add(group);
						groupListMap.put(key,groupListTemp);
					}else{
						groupListMap.get(key).add(group);
					}
				}
				model.addAttribute("groupListMap",groupListMap);
			}

			//所有组合内科室
			List<SchRotationDept> rotationDeptList = schRotationDeptBiz.searchOrgSelDeptByRotations(rotationFlows,orgFlow);
			if(rotationDeptList!=null && rotationDeptList.size()>0){
				Map<String,List<SchRotationDept>> rotationDeptListMap = new HashMap<String, List<SchRotationDept>>();
				for(SchRotationDept rotationDept : rotationDeptList){
					String key = rotationDept.getGroupFlow();
					if(rotationDeptListMap.get(key)==null){
						List<SchRotationDept> rotationDeptListTemp = new ArrayList<SchRotationDept>();
						rotationDeptListTemp.add(rotationDept);
						rotationDeptListMap.put(key,rotationDeptListTemp);
					}else{
						rotationDeptListMap.get(key).add(rotationDept);
					}
				}
				model.addAttribute("rotationDeptListMap",rotationDeptListMap);
			}

			//所有已选科室
			List<SchDoctorDept> doctorDeptList = schDoctorDeptBiz.searchDoctorDeptByDoctorFlows(doctorFlows);
			if(doctorDeptList!=null && doctorDeptList.size()>0){
				Map<String,SchDoctorDept> doctorDeptMap = new HashMap<String, SchDoctorDept>();
				for(SchDoctorDept doctorDept : doctorDeptList){
					String key = doctorDept.getDoctorFlow()+doctorDept.getGroupFlow()+doctorDept.getStandardDeptId()+doctorDept.getSchDeptFlow();
					doctorDeptMap.put(key,doctorDept);
				}
				model.addAttribute("doctorDeptMap",doctorDeptMap);
			}

			//排班数据
			List<SchArrangeResult> resultList = schArrangeResultBiz.searchArrangeResultByDoctorFlows(doctorFlows);
			if(resultList!=null && resultList.size()>0){
				Map<String,SchArrangeResult>  resultMap = new HashMap<String, SchArrangeResult>();
				for(SchArrangeResult result : resultList){
					String key = result.getDoctorFlow();
					if(resultMap.get(key)==null){
						resultMap.put(key, result);
					}
				}
				model.addAttribute("resultMap",resultMap);
			}

			//所有轮转方案
			List<SchRotation> rotationList = schRotationtBiz.searchRotationByrotationFlows(rotationFlows);
			if(rotationList!=null && rotationList.size()>0){
				Map<String,SchRotation> rotationMap = new HashMap<String, SchRotation>();
				for(SchRotation sr : rotationList){
					rotationMap.put(sr.getRotationFlow(),sr);
				}
				model.addAttribute("rotationMap",rotationMap);
			}
		}

		return "schTwo/doc/seldept";
	}

	/**
	 *  排班镶嵌选科
	 * */
	@RequestMapping(value = {"/doc/toSelDept" },method = {RequestMethod.GET,RequestMethod.POST})
	public String toSelDept(String doctorFlow,Model model){
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		if(StringUtil.isNotBlank(doctorFlow)){
			//排班数据
			List<SchArrangeResult> resultList = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctorFlow);
			if(resultList!=null && resultList.size()>0){
				model.addAttribute("doctorFlow",doctorFlow);
				return "redirect:/schTwo/arrange/rosteringHandDept";
			}

			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
			if(doctor!=null){
				model.addAttribute("doctor",doctor);

				SchRotation rotation = schRotationtBiz.readSchRotation(doctor.getRotationFlow());
				model.addAttribute("rotation",rotation);

				//所有组合
				List<SchRotationGroup> groupList = schRotationtGroupBiz.searchOrgGroupOrAll(doctor.getRotationFlow(),orgFlow,GlobalConstant.FLAG_N);
				if(groupList!=null && groupList.size()>0){
					Map<String,List<SchRotationGroup>> groupListMap = new HashMap<String, List<SchRotationGroup>>();
					for(SchRotationGroup group : groupList){
						String key = group.getRotationFlow();
						if(groupListMap.get(key)==null){
							List<SchRotationGroup> groupListTemp = new ArrayList<SchRotationGroup>();
							groupListTemp.add(group);
							groupListMap.put(key,groupListTemp);
						}else{
							groupListMap.get(key).add(group);
						}
					}
					model.addAttribute("groupListMap",groupListMap);
				}

				//所有组合内科室
				List<SchRotationDept> rotationDeptList = schRotationDeptBiz.searchOrgSchRotationDeptGroup(doctor.getRotationFlow(),orgFlow);
				if(rotationDeptList!=null && rotationDeptList.size()>0){
					Map<String,List<SchRotationDept>> rotationDeptListMap = new HashMap<String, List<SchRotationDept>>();
					for(SchRotationDept rotationDept : rotationDeptList){
						String key = rotationDept.getGroupFlow();
						if(rotationDeptListMap.get(key)==null){
							List<SchRotationDept> rotationDeptListTemp = new ArrayList<SchRotationDept>();
							rotationDeptListTemp.add(rotationDept);
							rotationDeptListMap.put(key,rotationDeptListTemp);
						}else{
							rotationDeptListMap.get(key).add(rotationDept);
						}
					}
					model.addAttribute("rotationDeptListMap",rotationDeptListMap);
				}

				//所有已选科室
				List<SchDoctorDept> doctorDeptList = schDoctorDeptBiz.searchSchDoctorDept(doctorFlow);
				if(doctorDeptList!=null && doctorDeptList.size()>0){
					Map<String,SchDoctorDept> doctorDeptMap = new HashMap<String, SchDoctorDept>();
					for(SchDoctorDept doctorDept : doctorDeptList){
						String key = doctorDept.getDoctorFlow()+doctorDept.getGroupFlow()+doctorDept.getStandardDeptId()+doctorDept.getSchDeptFlow();
						doctorDeptMap.put(key,doctorDept);
					}
					model.addAttribute("doctorDeptMap",doctorDeptMap);
				}
			}
		}

		return "schTwo/arrange/rosteringHandSelDept";
	}

	/**
	 *  操作选科
	 * */
	@RequestMapping(value = "/template/operSelDept",method={RequestMethod.POST})
	@ResponseBody
	public String operSelDept(String doctorFlow,String doctorDeptFlow,String rotationDeptFlow,String recordStatus,String schMonth){
		SchDoctorDept doctorDept = new SchDoctorDept();
		if(StringUtil.isNotBlank(doctorDeptFlow)){
			doctorDept.setRecordFlow(doctorDeptFlow);
		}else{
			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
			if(doctor!=null){
				doctorDept.setDoctorFlow(doctor.getDoctorFlow());
				doctorDept.setDoctorName(doctor.getDoctorName());
				doctorDept.setRotationFlow(doctor.getRotationFlow());
				doctorDept.setRotationName(doctor.getRotationName());
				doctorDept.setSessionNumber(doctor.getSessionNumber());
				doctorDept.setOrgFlow(doctor.getOrgFlow());
				doctorDept.setOrgName(doctor.getOrgName());
			}
			SchRotationDept rotationDept = schRotationDeptBiz.readSchRotationDept(rotationDeptFlow);
			if(rotationDept!=null){
				doctorDept.setGroupFlow(rotationDept.getGroupFlow());
				doctorDept.setSchMonth(rotationDept.getSchMonth());
				doctorDept.setSchDeptFlow(rotationDept.getSchDeptFlow());
				doctorDept.setSchDeptName(rotationDept.getSchDeptName());
				doctorDept.setDeptFlow(rotationDept.getDeptFlow());
				doctorDept.setDeptName(rotationDept.getDeptName());
				doctorDept.setStandardDeptId(rotationDept.getStandardDeptId());
				doctorDept.setStandardDeptName(rotationDept.getStandardDeptName());
				doctorDept.setOrdinal(rotationDept.getOrdinal());
			}
			doctorDept.setIsRequired(GlobalConstant.FLAG_N);
		}
		if(StringUtil.isNotBlank(schMonth)){
			doctorDept.setSchMonth(schMonth);
		}
		doctorDept.setRecordStatus(recordStatus);
		schDoctorDeptBiz.editDoctorDept(doctorDept);
		return doctorDept.getRecordFlow();
	}

//	@RequestMapping(value = {"/doc/seldept" },method = RequestMethod.GET)
//	public String seldept(DoctorSearchForm doctorSearchForm,Model model){
//		//医师列表
//		List<ResDoctor> doctorList = schDoctortBiz.searchResDoctor(GlobalContext.getCurrentUser().getOrgFlow(),doctorSearchForm);
//		if(doctorList!=null && doctorList.size()>0){
//			List<String> doctorFlows = new ArrayList<String>();
//			List<String> rotationFlows = new ArrayList<String>();
// 			model.addAttribute("doctorList",doctorList);
// 			for(ResDoctor doctor : doctorList){
// 				String rotationFlow = doctor.getRotationFlow();
// 				doctorFlows.add(doctor.getDoctorFlow());
// 				if(!rotationFlows.contains(rotationFlow) && StringUtil.isNotBlank(rotationFlow)){
// 					rotationFlows.add(rotationFlow);
// 				}
// 			}
// 			//轮转方案
// 			List<SchRotation> rotationList = schRotationtBiz.searchRotationByrotationFlows(rotationFlows);
// 			if(rotationList != null && rotationList.size()>0){
// 				Map<String,SchRotation> rotationMap = new HashMap<String,SchRotation>();
// 				for(SchRotation rotation : rotationList){
// 					rotationMap.put(rotation.getRotationFlow(),rotation);
// 				}
// 				model.addAttribute("rotationMap",rotationMap);
// 			}
//
// 			//科室组
// 			List<SchRotationGroup> rotationGroupList = schRotationtGroupBiz.searchSchRotationGroupByorg(GlobalContext.getCurrentUser().getOrgFlow());
// 			if(rotationGroupList != null && rotationGroupList.size()>0){
// 				Map<String,List<SchRotationGroup>> rotationGroupMap = new HashMap<String,List<SchRotationGroup>>();
// 				for(SchRotationGroup rotationGroup : rotationGroupList){
// 					if(rotationGroupMap.get(rotationGroup.getRotationFlow()) == null){
// 						List<SchRotationGroup> rotationGroupTempList = new ArrayList<SchRotationGroup>();
// 						rotationGroupTempList.add(rotationGroup);
// 						rotationGroupMap.put(rotationGroup.getRotationFlow(),rotationGroupTempList);
// 					}else{
// 						rotationGroupMap.get(rotationGroup.getRotationFlow()).add(rotationGroup);
// 					}
// 				}
// 				model.addAttribute("rotationGroupMap",rotationGroupMap);
// 			}
//
// 			//已选科室
// 			List<SchDoctorDept> doctorDeptList = schDoctorDeptBiz.searchDoctorDeptByDoctorFlows(doctorFlows);
// 			if(doctorDeptList != null && doctorDeptList.size()>0){
// 				Map<String,List<SchDoctorDept>> doctorDeptMap = new HashMap<String,List<SchDoctorDept>>();
// 				for(SchDoctorDept doctorDept : doctorDeptList){
// 					if(doctorDeptMap.get(doctorDept.getDoctorFlow()) == null){
// 						List<SchDoctorDept> doctorDeptTempList = new ArrayList<SchDoctorDept>();
// 						doctorDeptTempList.add(doctorDept);
// 						doctorDeptMap.put(doctorDept.getDoctorFlow(),doctorDeptTempList);
// 					}else{
// 						doctorDeptMap.get(doctorDept.getDoctorFlow()).add(doctorDept);
// 					}
// 				}
// 				model.addAttribute("doctorDeptMap",doctorDeptMap);
// 			}
//
// 			//是否完成排班
// 			List<SchArrangeResult> resultList = schArrangeResultBiz.searchArrangeResultByDoctorFlows(doctorFlows);
// 			if(resultList!=null && resultList.size()>0){
// 				Map<String,Integer> resultMap = new HashMap<String, Integer>();
// 				for(SchArrangeResult result : resultList){
// 					if(resultMap.get(result.getDoctorFlow())==null){
// 						resultMap.put(result.getDoctorFlow(),1);
// 					}else{
// 						resultMap.put(result.getDoctorFlow(),(int)resultMap.get(result.getDoctorFlow())+1);
// 					}
// 				}
// 				model.addAttribute("resultMap",resultMap);
// 			}
//		}
//		return "schTwo/doc/seldept";
//	}
//
//	@RequestMapping(value = {"/doc/searchSeldept" },method = RequestMethod.POST)
//	public String searchSeldept (DoctorSearchForm doctorSearchForm,Model model){
//		return seldept(doctorSearchForm,model);
//	}

	/**
	 * 轮转规则选择
	 * */
	@RequestMapping(value = {"/doc/seldept_item" }, method = RequestMethod.GET)
	public String seldeptItem (String doctorFlow,Model model) throws Exception{
		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctor doctor = schDoctortBiz.readResDoctor(doctorFlow);
			model.addAttribute("doctor",doctor);

			String rotationFlow = doctor.getRotationFlow();
			//科室组
			List<SchRotationGroup> rotationGroupList = schRotationtGroupBiz.searchSchRotationGroup(rotationFlow);
			model.addAttribute("rotationGroupList",rotationGroupList);

			//组合科室
			List<SchRotationDept> rotationDeptList = schRotationDeptBiz.searchSchRotationDeptGroup(rotationFlow);
			if(rotationDeptList != null && rotationDeptList.size()>0){
				Map<String,List<SchRotationDept>> rotationDeptMap = new HashMap<String,List<SchRotationDept>>();
				for(SchRotationDept rotationDept : rotationDeptList){
					if(rotationDeptMap.get(rotationDept.getGroupFlow()) == null){
						List<SchRotationDept> rotationDeptTempList = new ArrayList<SchRotationDept>();
						rotationDeptTempList.add(rotationDept);
						rotationDeptMap.put(rotationDept.getGroupFlow(),rotationDeptTempList);
					}else{
						rotationDeptMap.get(rotationDept.getGroupFlow()).add(rotationDept);
					}
				}
				model.addAttribute("rotationDeptMap",rotationDeptMap);
			}

			//已选科室
			List<SchDoctorDept> doctorDeptList = schDoctorDeptBiz.searchSchDoctorDept(doctorFlow);
			if(doctorDeptList != null && doctorDeptList.size()>0){
				Map<String,SchDoctorDept> doctorDeptMap = new HashMap<String,SchDoctorDept>();
				for(SchDoctorDept doctorDept : doctorDeptList){
					doctorDeptMap.put(doctorDept.getGroupFlow()+doctorDept.getStandardDeptId()+doctorDept.getSchDeptFlow(),doctorDept);
				}
				model.addAttribute("doctorDeptMap",doctorDeptMap);
			}
		}

		return "schTwo/doc/seldept_item";
	}

	@RequestMapping(value = "/doc/saveDoctorDept",method={RequestMethod.POST})
	@ResponseBody
	public String saveDoctorDept(SchDoctorDept doctorDept){
		if(doctorDept != null){
			int result = GlobalConstant.ZERO_LINE;
			SchDoctorDept doctorDeptTemp = schDoctorDeptBiz.readSchDoctorDeptByObj(doctorDept.getDoctorFlow(),doctorDept.getSchDeptFlow(),doctorDept.getGroupFlow(),doctorDept.getStandardDeptId());
			if(doctorDeptTemp != null){
				if(GlobalConstant.RECORD_STATUS_Y.equals(doctorDeptTemp.getRecordStatus())){
					doctorDeptTemp.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
				}else{
					doctorDeptTemp.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				}
				result = saveSchDoctorDept(doctorDeptTemp);
			}else{
				result = saveSchDoctorDept(doctorDept);
			}
			if(result != GlobalConstant.ZERO_LINE){
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}

	private int saveSchDoctorDept(SchDoctorDept doctorDept){
		doctorDept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		doctorDept.setOrgName(GlobalContext.getCurrentUser().getOrgName());
		if(StringUtil.isNotBlank(doctorDept.getStandardDeptId())){
			doctorDept.setStandardDeptName(DictTypeEnum.StandardDept.getDictNameById(doctorDept.getStandardDeptId()));
		}
		return schDoctorDeptBiz.saveSchDoctorDept(doctorDept);
	}



	/**
	 * 手动排班
	 * */
	@RequestMapping(value = {"/arrange/rosteringHand" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String rosteringHandList (ResDoctor doctor,String rosteringType,Model model){
		SysUser user = GlobalContext.getCurrentUser();
		doctor.setOrgFlow(user.getOrgFlow());
		if(doctor.getSessionNumber()==null){
			doctor.setSessionNumber(DateUtil.getYear());
		}
		if(StringUtil.isBlank(doctor.getDoctorCategoryId())){
			doctor.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
			if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
				doctor.setDoctorCategoryId(RecDocCategoryEnum.ChineseMedicine.getId());
			}
		}
		model.addAttribute("doctor",doctor);

		//如果按组分则查询组内人数
		if(GlobalConstant.FLAG_Y.equals(rosteringType)){
			ResDoctor doctorTemp = new ResDoctor();
			doctorTemp.setOrgFlow(user.getOrgFlow());
			doctorTemp.setDoctorCategoryId(RecDocCategoryEnum.Intern.getId());
			List<Map<String,Object>> groupCountMapList = doctorBiz.countGroupDoc(doctorTemp);
			if(groupCountMapList!=null && groupCountMapList.size()>0){
				Map<String,Object> groupCountMap = new HashMap<String, Object>();
				for(Map<String,Object> map : groupCountMapList){
					groupCountMap.put((String)map.get("key"),map.get("value"));
				}
				model.addAttribute("groupCountMap",groupCountMap);
			}
		}else{
			if("All".equals(doctor.getSchFlag())){
				doctor.setSchFlag("");
				model.addAttribute("schFlag","All");
			}
			List<ResDoctor> doctorList = doctorBiz.searchByDocHaveRotation(doctor);
			model.addAttribute("doctorList",doctorList);
		}
		return "schTwo/arrange/rosteringHand";
	}



	/**
	 * B阶段课程维护
	 * */
	@RequestMapping(value = {"/arrange/courseMaint" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String courseMaintList (ResDoctor doctor,String rosteringType,Model model){
		SysUser user = GlobalContext.getCurrentUser();
		doctor.setOrgFlow(user.getOrgFlow());
		if(doctor.getSessionNumber()==null){
			doctor.setSessionNumber(DateUtil.getYear());
		}
		doctor.setDoctorCategoryId(RecDocCategoryEnum.Graduate.getId());
		model.addAttribute("doctor",doctor);

		List<ResDoctor> doctorList = doctorBiz.searchByDocHaveRotation(doctor);
		model.addAttribute("doctorList",doctorList);

		return "schTwo/arrange/courseMaint";
	}

	/**
	 * 选择学员加载信息
	 * @param doctorFlow
	 * @param model
     * @return
     */
	@RequestMapping(value = {"/arrange/courseMaintDept"}, method = {RequestMethod.GET,RequestMethod.POST})
	public String courseMaintDept(String doctorFlow,Model model){
		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctor doctor = doctorBiz.searchByUserFlow(doctorFlow);
			model.addAttribute("doctor",doctor);
			if(StringUtil.isNotBlank(doctor.getRotationFlow())){
				String rotationFlow =doctor.getRotationFlow();
				List<SchArrangeResult> arrangeResultList = schArrangeResultBiz.searchSchArrangeResultByDoctorAndRotationFlow(doctorFlow,rotationFlow);
				model.addAttribute("arrangeResultList",arrangeResultList);

				Map<String,ResDoctorSchProcess> processMap = new HashMap<>();
				if(arrangeResultList.size()>0 && arrangeResultList!=null){
					for(SchArrangeResult result:arrangeResultList){
						String resultFlow = result.getResultFlow();
						ResDoctorSchProcess process = processBiz.searchByResultFlow(resultFlow);
						processMap.put(resultFlow,process);
					}
					model.addAttribute("processMap",processMap);
				}
			}
		}
		return "schTwo/arrange/courseMaintDept";
	}

	/**
	 * B阶段课程维护编辑/添加
	 * @param model
	 * @return
     */
	@RequestMapping("/arrange/addCourseMaint")
	public String addCourseMaint(String doctorFlow,String resultFlow,Model model){

		if(StringUtil.isNotBlank(doctorFlow)){
			model.addAttribute("doctorFlow",doctorFlow);
		}

		if(StringUtil.isNotBlank(resultFlow)){
			SchArrangeResult arrangeResult = schArrangeResultBiz.readSchArrangeResult(resultFlow);
			model.addAttribute("result",arrangeResult);
			ResDoctorSchProcess schProcess = processBiz.searchByResultFlow(resultFlow);
			model.addAttribute("process",schProcess);
		}
		ResDoctor doctor=doctorBiz.readDoctor(doctorFlow);
		if(doctor!=null&&StringUtil.isNotBlank(doctor.getRotationFlow())) {
			List<SchRotationGroup> rotationGroupList = schRotationtGroupBiz.searchSchRotationGroup(doctor.getRotationFlow());
			model.addAttribute("rotationFlow", doctor.getRotationFlow());
			model.addAttribute("rotationGroupList", rotationGroupList);
		}
		return "schTwo/arrange/editCourseMaint";
	}

	/**
	 * 删除
	 * @param resultFlow
	 * @return
     */
	@RequestMapping("/arrange/delPeriodRel")
	@ResponseBody
	public String delPeriodRel(String resultFlow,String doctorFlow){
		//删除单条记录
		if(StringUtil.isNotBlank(resultFlow) && StringUtil.isNotBlank(doctorFlow)){
			int reslut = schArrangeResultBiz.delResultByResultFlow(resultFlow);
			if(reslut > GlobalConstant.ZERO_LINE){
				return GlobalConstant.DELETE_SUCCESSED;
			}
		}
//		//删除全部记录
//		if(StringUtil.isBlank(resultFlow) && StringUtil.isNotBlank(doctorFlow)){
//			int reslut = schArrangePeriodRelBiz.deleteByDoctorFlow(doctorFlow);
//			if(reslut > GlobalConstant.ZERO_LINE){
//				return GlobalConstant.DELETE_SUCCESSED;
//			}
//		}
		return GlobalConstant.DELETE_FAIL;
	}

	/**
	 * 根据机构加载轮转科室
	 * @param orgFlow
	 * @return
     */
	@RequestMapping("/loadSchDept")
	@ResponseBody
	public List<SchDept> loadSchDept(String orgFlow){
		List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(orgFlow);

		return schDeptList;
	}

	/**
	 * 加载标准科室
	 * @param rotationFlow
	 * @param groupFlow
     * @return
     */
	@RequestMapping("/loadStandardDept")
	@ResponseBody
	public List<SchRotationDept> loadStandardDept(String rotationFlow,String groupFlow){

		List<SchRotationDept> rotationDeptList = schRotationDeptBiz.searchDeptByStandard(rotationFlow,groupFlow,null,null);

		return rotationDeptList;

	}

	/**
	 * 保存
	 * @param process
	 * @param result
     * @return
     */
	@RequestMapping("/arrange/saveCourseMaint")
	@ResponseBody
	public String saveCourseMaint(ResDoctorSchProcess process,SchArrangeResult result){

		int count = schArrangeResultBiz.saveProcessAndResult(process,result);
		if(count > GlobalConstant.ZERO_LINE){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value = {"/arrange/rosteringHandDept"}, method = {RequestMethod.GET,RequestMethod.POST})
	public String rosteringHandDept(String doctorFlow,Model model){
		if(StringUtil.isNotBlank(doctorFlow)){

			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);

			if(doctor!=null){
				model.addAttribute("doctor",doctor);
				List<SchRotationDept> schRotationDepts=new ArrayList<>();
				Map<String,SchRotationGroup> groupMap = new HashMap<String, SchRotationGroup>();
				if(StringUtil.isNotBlank(doctor.getRotationFlow())){
					SchRotation rotation = schRotationtBiz.readSchRotation(doctor.getRotationFlow());
					model.addAttribute("rotation",rotation);

					List<SchRotationGroup> groupList = schRotationtGroupBiz.searchOrgGroupBySessionNumber(doctor.getRotationFlow(),doctor.getOrgFlow(),doctor.getSessionNumber());
					if(groupList!=null && groupList.size()>0){
						for(SchRotationGroup group : groupList){
							groupMap.put(group.getGroupFlow(),group);
						}
					}
					List<SchRotationDept> depts=schRotationDeptBiz.searchOrgSchRotationDeptBySessionNumber(doctor.getRotationFlow(),doctor.getOrgFlow(),doctor.getSessionNumber());
					schRotationDepts.addAll(depts);
				}
				SchRotation secondRotation = null;
				if(StringUtil.isNotBlank(doctor.getSecondRotationFlow())){
					secondRotation = schRotationtBiz.readSchRotation(doctor.getSecondRotationFlow());
					model.addAttribute("secondRotation",secondRotation);
					List<SchRotationGroup> groupList = schRotationtGroupBiz.searchOrgGroupBySessionNumber(doctor.getSecondRotationFlow(),doctor.getOrgFlow(),doctor.getSessionNumber());
					if(groupList!=null && groupList.size()>0){
						for(SchRotationGroup group : groupList){
							groupMap.put(group.getGroupFlow(),group);
						}
					}
					List<SchRotationDept> depts=schRotationDeptBiz.searchOrgSchRotationDeptBySessionNumber(doctor.getSecondRotationFlow(),doctor.getOrgFlow(),doctor.getSessionNumber());
					schRotationDepts.addAll(depts);
				}
				model.addAttribute("groupMap",groupMap);

				//初始化学员排班
				schArrangeResultBiz.initDocResult(doctor,schRotationDepts);
				//学员SCH_FLAG改成Y
				if(!GlobalConstant.FLAG_Y.equals(doctor.getSchFlag())){
					doctor.setSchFlag(GlobalConstant.FLAG_Y);
					doctorBiz.editDoctor(doctor);
				}


				Map<String,Object> paramMap = new HashMap<>();
				paramMap.put("doctorFlow",doctorFlow);
				List<String> recordStatus = new ArrayList<>();
				recordStatus.add(GlobalConstant.RECORD_STATUS_D);
				recordStatus.add(GlobalConstant.RECORD_STATUS_Y);
				paramMap.put("recordStatus",recordStatus);
				paramMap.put("sessionNumber",doctor.getSessionNumber());
				List<SchArrangeResult> resultList = schArrangeResultBiz.searchProcessByItems(paramMap);
				model.addAttribute("resultList", resultList);
				Map<String, SchArrangeResult> resultMap = new HashMap<>();
				if (secondRotation != null && resultList != null && resultList.size() > 0) {
					Map<String, String> isSecondRotation = new HashMap<>();
					for (SchArrangeResult temp : resultList) {
						resultMap.put(temp.getResultFlow(),temp);
						if(secondRotation.getRotationFlow().equals(temp.getRotationFlow())){
							isSecondRotation.put(temp.getResultFlow(),GlobalConstant.FLAG_Y);
						}
					}
					model.addAttribute("isSecondRotation",isSecondRotation);
				}

				List<Map<String, String>> times = new ArrayList<>();
				List<ResDoctorSchProcess> processList = processBiz.searchProcessByDoctor(doctorFlow);
				if(processList!=null && processList.size()>0){
					Map<String,ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
					for(ResDoctorSchProcess process : processList){
						processMap.put(process.getSchResultFlow(),process);
						if(GlobalConstant.FLAG_Y.equals(process.getSchFlag()))
						{
							SchArrangeResult result=resultMap.get(process.getSchResultFlow());
							if(result!=null&&GlobalConstant.RECORD_STATUS_Y.equals(result.getRecordStatus())) {
								String startDate = process.getSchStartDate();
								String endDate = process.getSchEndDate();
								Map<String, String> map = new HashMap<>();
								map.put("startDate", startDate);
								map.put("endDate", endDate);
								times.add(map);
							}
						}
					}

					List<Map<String, String>> mapList = TimeUtil.getNewTimes(times);
					if(mapList.size()>1){
						model.addAttribute("series","N");
					}
					model.addAttribute("processMap",processMap);
				}
			}
		}

		//取前10年和后10年的每月最后一天
		List<String> lastDays = new ArrayList<String>();
		String currDate = DateUtil.getCurrDate();
		if(StringUtil.isNotBlank(currDate) && currDate.length()>5){
			String currYear = currDate.substring(0, 4);
			Integer currYearI = Integer.parseInt(currYear);
			Integer startYear = currYearI-10;
			Integer endYear = currYearI+10;
			while(startYear<=endYear){
				for(int m = 1 ; m<=12 ; m++){
					String ld = DateUtil.newDateOfAddMonths(startYear+"-"+m+"-"+"01",1);
					ld = DateUtil.addDate(ld,-1);
					lastDays.add(ld);
				}
				startYear++;
			}
		}
		model.addAttribute("lastDays",lastDays);

		return "schTwo/arrange/rosteringHandDept";
	}

	@RequestMapping(value = {"/arrange/showEditResult"})
	public String showEditResult(String doctorFlow, Model model){
		ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
		if (doctor != null) {
			List<SchRotationGroup> groups = new ArrayList<>();
			Map<String,List<SchRotationDept>> schDeptMap = new HashMap<>();
			if(StringUtil.isNotBlank(doctor.getRotationFlow())){
				String rotationFlow = doctor.getRotationFlow();
				List<SchRotationGroup> standardRotationGroupList = schRotationtGroupBiz.searchOrgGroupBySessionNumber(rotationFlow,doctor.getOrgFlow(),doctor.getSessionNumber());
				List<SchRotationDept> standardRotationDeptList = schRotationDeptBiz.searchOrgSchRotationDeptBySessionNumber(rotationFlow,doctor.getOrgFlow(),doctor.getSessionNumber());
				groups.addAll(standardRotationGroupList);
				if (standardRotationDeptList != null && standardRotationDeptList.size() > 0) {
					for(SchRotationDept tempDept : standardRotationDeptList){
						String key = tempDept.getGroupFlow();
						if(schDeptMap.get(key)==null){
							List<SchRotationDept> standardRotationDeptTempList = new ArrayList<SchRotationDept>();
							standardRotationDeptTempList.add(tempDept);
							schDeptMap.put(key,standardRotationDeptTempList);
						}else{
							schDeptMap.get(key).add(tempDept);
						}
					}
				}
			}
			if(StringUtil.isNotBlank(doctor.getSecondRotationFlow())){
				String rotationFlow = doctor.getSecondRotationFlow();
				List<SchRotationGroup> standardRotationGroupList = schRotationtGroupBiz.searchOrgGroupBySessionNumber(rotationFlow,doctor.getOrgFlow(),doctor.getSessionNumber());
				groups.addAll(standardRotationGroupList);
				List<SchRotationDept> standardRotationDeptList = schRotationDeptBiz.searchOrgSchRotationDeptBySessionNumber(rotationFlow,doctor.getOrgFlow(),doctor.getSessionNumber());
				if (standardRotationDeptList != null && standardRotationDeptList.size() > 0) {
					for(SchRotationDept tempDept : standardRotationDeptList){
						String key = tempDept.getGroupFlow();
						if(schDeptMap.get(key)==null){
							List<SchRotationDept> standardRotationDeptTempList = new ArrayList<SchRotationDept>();
							standardRotationDeptTempList.add(tempDept);
							schDeptMap.put(key,standardRotationDeptTempList);
						}else{
							schDeptMap.get(key).add(tempDept);
						}
					}
				}
			}
			model.addAttribute("doctorFlow",doctorFlow);
			model.addAttribute("groups",groups);
			model.addAttribute("schDeptMap",schDeptMap);
			String orgFlow = "";
			orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
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
		return "schTwo/arrange/editResult";
	}
	/**
	 * 按组选科排班,选科页面
	 * @param doctor
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/arrange/groupSelAndRostering" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String groupSelAndRostering(ResDoctor doctor,Model model){
		SysUser user = GlobalContext.getCurrentUser();

		doctor.setOrgFlow(user.getOrgFlow());
		List<ResDoctor> doctorList = doctorBiz.searchByDoc(doctor);
		if(doctorList!=null && doctorList.size()>0){
			model.addAttribute("doctorList",doctorList);

			//获取一个方案
			String rotationFlow = doctorList.get(0).getRotationFlow();
			if(StringUtil.isNotBlank(rotationFlow)){
				SchRotation rotation = schRotationtBiz.readSchRotation(rotationFlow);
				model.addAttribute("rotation",rotation);

				List<SchRotationGroup> groupList = schRotationtGroupBiz.searchOrgGroupOrAll(rotationFlow,user.getOrgFlow(),GlobalConstant.FLAG_N);
				if(groupList!=null && groupList.size()>0){
					model.addAttribute("groupList",groupList);

					List<SchRotationDept> groupRotationDeptList = schRotationDeptBiz.searchOrgSchRotationDeptGroup(rotationFlow,user.getOrgFlow());
					if(groupRotationDeptList!=null && groupRotationDeptList.size()>0){
						Map<String,List<SchRotationDept>> rotationDeptListMap = new HashMap<String, List<SchRotationDept>>();
						for(SchRotationDept rotationDept : groupRotationDeptList){
							String key = rotationDept.getGroupFlow();
							if(rotationDeptListMap.get(key)==null){
								List<SchRotationDept> rotationDeptList = new ArrayList<SchRotationDept>();
								rotationDeptList.add(rotationDept);
								rotationDeptListMap.put(key,rotationDeptList);
							}else{
								rotationDeptListMap.get(key).add(rotationDept);
							}
						}
						model.addAttribute("rotationDeptListMap",rotationDeptListMap);
					}
				}
			}

			List<String> doctorFlows = new ArrayList<String>();
			for(ResDoctor doc : doctorList){
				doctorFlows.add(doc.getDoctorFlow());
			}

			Map<String,Boolean> isUseMap = new HashMap<String, Boolean>();

			List<SchDoctorDept> doctorDeptList = schDoctorDeptBiz.searchDoctorDeptByDoctorFlows(doctorFlows);
			if(doctorDeptList!=null && doctorDeptList.size()>0){
				for(SchDoctorDept docDept : doctorDeptList){
					String key = docDept.getDoctorFlow();
					if(isUseMap.get(key)==null){
						isUseMap.put(key,true);
						doctorFlows.remove(key);
					}
				}
			}

			List<SchArrangeResult> resultList = schArrangeResultBiz.searchArrangeResultByDoctorFlows(doctorFlows);
			if(resultList!=null && resultList.size()>0){
				for(SchArrangeResult result : resultList){
					String key = result.getDoctorFlow();
					if(isUseMap.get(key)==null){
						isUseMap.put(key,true);
					}
				}
			}

			model.addAttribute("isUseMap",isUseMap);
		}
		return "schTwo/arrange/groupSelAndRostering";
	}

	/**
	 * 清空选科排班
	 * @param doctorFlows
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/arrange/clearSelAndRostering" }, method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String clearSelAndRostering(String[] doctorFlows,Model model){
		if(doctorFlows!=null && doctorFlows.length>0){
			List<String> doctorFlowList = Arrays.asList(doctorFlows);
			doctorFlowList.toArray(doctorFlows);
			int result = doctorBiz.clearSelAndRostering(doctorFlowList);
			if(result!=GlobalConstant.ZERO_LINE){
				return GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}
	/**
	 * 生成排班数据
	 * */
	@RequestMapping(value = "/doc/createResults",method={RequestMethod.POST})
	@ResponseBody
	public String createResults(String doctorFlow){
		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
			if(doctor!=null){
				doctor.setSchFlag(GlobalConstant.FLAG_Y);
				int result = schArrangeResultBiz.saveResultByDoctor(doctor);
				if(result!=GlobalConstant.ZERO_LINE){
					return GlobalConstant.OPRE_SUCCESSED_FLAG;
				}
			}
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}

//	@RequestMapping(value = {"/arrange/rostering_hand_dept" }, method = RequestMethod.GET)
//	public String rosteringHandDept (String doctorFlow,Model model) throws Exception{
//		if(StringUtil.isNotBlank(doctorFlow)){
//			ResDoctor doctor = schDoctortBiz.readResDoctor(doctorFlow);
//			model.addAttribute("doctor",doctor);
//
//			SchRotation rotation = schRotationtBiz.readSchRotation(doctor.getRotationFlow());
//			model.addAttribute("rotationYear",rotation.getRotationYear());
//
//			List<SchRotationDept> rotationDeptList = schRotationDeptBiz.searchSchRotationDeptMust(doctor.getRotationFlow());
//			model.addAttribute("rotationDeptList",rotationDeptList);
//
//			if(GlobalConstant.FLAG_Y.equals(doctor.getSelDeptFlag())){
//				List<SchRotationDept> rotationDeptTempList = schRotationDeptBiz.searchSchRotationDeptGroup(doctor.getRotationFlow());
//				if(rotationDeptTempList != null && rotationDeptTempList.size()>0){
//					Map<String,SchRotationDept> rotationDeptMap = new HashMap<String,SchRotationDept>();
//					for(SchRotationDept rotationDept : rotationDeptTempList){
//						rotationDeptMap.put(rotationDept.getGroupFlow()+rotationDept.getSchDeptFlow(),rotationDept);
//					}
//					model.addAttribute("rotationDeptMap",rotationDeptMap);
//				}
//
//				List<SchRotationGroup> rotationGroupList = schRotationtGroupBiz.searchSchRotationGroup(doctor.getRotationFlow());
//				if(rotationGroupList != null && rotationGroupList.size()>0){
//					Map<String,SchRotationGroup> rotationGroupMap = new HashMap<String,SchRotationGroup>();
//					for(SchRotationGroup rotationGroup : rotationGroupList){
//						rotationGroupMap.put(rotationGroup.getGroupFlow(),rotationGroup);
//					}
//					model.addAttribute("rotationGroupMap",rotationGroupMap);
//				}
//
//				List<SchDoctorDept> doctorDeptList = schDoctorDeptBiz.searchSchDoctorDept(doctorFlow);
//				model.addAttribute("doctorDeptList",doctorDeptList);
//
//				List<SchArrangeResult> resultList = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctorFlow);
//				if(resultList != null && resultList.size()>0){
//					Map<String,SchArrangeResult> resultMap = new HashMap<String,SchArrangeResult>();
//					for(SchArrangeResult arrResult : resultList){
//						resultMap.put(arrResult.getSchDeptFlow()+arrResult.getSchYear(),arrResult);
//					}
//					model.addAttribute("resultMap",resultMap);
//				}
//			}
//		}
//		return "schTwo/arrange/rostering_hand_dept";
//	}

	@RequestMapping(value = "/arrange/saveArrangeResult",method={RequestMethod.POST})
	@ResponseBody
	public String saveArrangeResult(@RequestBody SchArrangeResultForm arrangeResultForm, Model model) throws Exception{
		int result = GlobalConstant.ZERO_LINE;
		result = schArrangeResultBiz.saveSchArrangeResultForm(arrangeResultForm);
		if(result != GlobalConstant.ZERO_LINE){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value = "/arrange/delArrangeResult",method={RequestMethod.POST})
	@ResponseBody
	public String delArrangeResult(String doctorFlow, Model model) throws Exception{
		int result = GlobalConstant.ZERO_LINE;
		result = schArrangeResultBiz.delArrangeResult(doctorFlow);
		if(result != GlobalConstant.ZERO_LINE){
			return GlobalConstant.DELETE_SUCCESSED;
		}
		return GlobalConstant.DELETE_FAIL;
	}

	/**
	 * 终止医师
	 * */
	@RequestMapping(value = {"/doc/aid/terminat" },method = RequestMethod.GET)
	public String terminat (DoctorSearchForm doctorSearchForm,Model model) throws Exception{
		List<ResDoctor> doctorList = schDoctortBiz.searchTerminatResDoctor(GlobalContext.getCurrentUser().getOrgFlow(),doctorSearchForm);
		model.addAttribute("doctorList",doctorList);
		return "schTwo/doc/aid/terminat";
	}

	@RequestMapping(value = {"/doc/aid/searchTerminat" }, method = RequestMethod.POST)
	public String searchTerminat (DoctorSearchForm doctorSearchForm,Model model) throws Exception{
		return terminat(doctorSearchForm,model);
	}

	/**
	 * 新增终止
	 * */
	@RequestMapping(value = {"/doc/aid/editTerminat" }, method = RequestMethod.GET)
	public String editTerminat (Model model,String doctorFlow) throws Exception{
		List<ResDoctor> doctorList = schDoctortBiz.searchNotTerminatResDoctor(GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("doctorList",doctorList);
		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctor doctor=schDoctortBiz.readResDoctor(doctorFlow);
			model.addAttribute("doctor", doctor);
		}
		return "schTwo/doc/aid/terminatEdit";
	}
	@RequestMapping(value = {"/doc/aid/terminatCheck" }, method = RequestMethod.GET)
	public String terminatCheck (Model model,String doctorFlow) throws Exception{
		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctor doctor=schDoctortBiz.readResDoctor(doctorFlow);
			model.addAttribute("doctor", doctor);
		}
		return "schTwo/doc/aid/terminatCheck";
	}

	@RequestMapping(value = "doc/aid/saveTerminat",method={RequestMethod.POST})
	@ResponseBody
	public String saveTerminat(ResDoctor doctor, Model model) throws Exception{
		if(doctor != null){
			doctor.setDoctorStatusId(ResDoctorStatusEnum.Terminat.getId());
			doctor.setDoctorStatusName(ResDoctorStatusEnum.Terminat.getName());
			int result = schDoctortBiz.saveResDoctor(doctor);
			if(result != GlobalConstant.ZERO_LINE){
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 缺勤医师
	 * */
	@RequestMapping(value = {"/doc/aid/absence" },method = RequestMethod.GET)
	public String absence (DoctorSearchForm doctorSearchForm,Model model) throws Exception{
		List<ResDoctor> doctorList = schDoctortBiz.searchResDoctor(GlobalContext.getCurrentUser().getOrgFlow(),doctorSearchForm);
		model.addAttribute("doctorList",doctorList);

		List<SchDoctorAbsence> docAbsenceList = schDoctorAbsenceBiz.searchSchDoctorAbsenceByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		if(docAbsenceList != null && docAbsenceList.size()>0){
			Map<String,List<SchDoctorAbsence>> docAbsenceMap = new HashMap<String,List<SchDoctorAbsence>>();
			for(SchDoctorAbsence docAbsence : docAbsenceList){
				if(docAbsenceMap.get(docAbsence.getDoctorFlow()) == null){
					List<SchDoctorAbsence> docAbsenceTempList = new ArrayList<SchDoctorAbsence>();
					docAbsenceTempList.add(docAbsence);
					docAbsenceMap.put(docAbsence.getDoctorFlow(),docAbsenceTempList);
				}else{
					docAbsenceMap.get(docAbsence.getDoctorFlow()).add(docAbsence);
				}
			}
			model.addAttribute("docAbsenceMap",docAbsenceMap);
		}

		return "schTwo/doc/aid/absence";
	}

	@RequestMapping(value = {"/doc/aid/searchAbsence" }, method = RequestMethod.POST)
	public String searchAbsence (DoctorSearchForm doctorSearchForm,Model model) throws Exception{
		return absence(doctorSearchForm,model);
	}

	/**
	 * 新增缺勤
	 * */
	@RequestMapping(value = {"/doc/aid/editAbsence" }, method = RequestMethod.GET)
	public String editAbsence (SchDoctorAbsence docAbsence,Model model) throws Exception{
		List<ResDoctor> doctorList = schDoctortBiz.searchResDoctor(GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("doctorList",doctorList);

		if(docAbsence != null && StringUtil.isNotBlank(docAbsence.getAbsenceFlow())){
			docAbsence = schDoctorAbsenceBiz.readSchDoctorAbsence(docAbsence.getAbsenceFlow());
		}
		model.addAttribute("docAbsence",docAbsence);

		return "schTwo/doc/aid/absenceEdit";
	}

	@RequestMapping(value = "doc/aid/saveAbsence",method={RequestMethod.POST})
	@ResponseBody
	public String saveAbsence(SchDoctorAbsence docAbsence, Model model) throws Exception{
		if(docAbsence != null){
			docAbsence.setTrainingSpeName(DictTypeEnum.DoctorTrainingSpe.getDictNameById(docAbsence.getTrainingSpeId()));
			docAbsence.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			docAbsence.setOrgName(GlobalContext.getCurrentUser().getOrgName());
			if(StringUtil.isNotBlank(docAbsence.getEndDate()) && StringUtil.isNotBlank(docAbsence.getStartDate())){
				docAbsence.setIntervalDay(String.valueOf(DateUtil.signDaysBetweenTowDate(docAbsence.getEndDate(),docAbsence.getStartDate())+1));
			}

			String schDeptFlow = docAbsence.getSchDeptFlow();
			if(StringUtil.isNotBlank(schDeptFlow)){
				SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
				if(dept!=null){
					docAbsence.setDeptFlow(dept.getDeptFlow());
					docAbsence.setDeptName(dept.getDeptName());
				}
			}

			int result = schDoctorAbsenceBiz.saveSchDoctorAbsence(docAbsence);
			if(result != GlobalConstant.ZERO_LINE){
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value = "doc/aid/delAbsence",method={RequestMethod.POST})
	@ResponseBody
	public String delAbsence(String absenceFlow, Model model) throws Exception{
		if(StringUtil.isNotBlank(absenceFlow)){
			SchDoctorAbsence docAbsence = new SchDoctorAbsence();
			docAbsence.setAbsenceFlow(absenceFlow);
			docAbsence.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			int result = schDoctorAbsenceBiz.saveSchDoctorAbsence(docAbsence);
			if(result != GlobalConstant.ZERO_LINE){
				return GlobalConstant.DELETE_SUCCESSED;
			}
		}
		return GlobalConstant.DELETE_FAIL;
	}

	/**
	 * 科室轮转查询
	 * */
	@RequestMapping(value = {"/doc/schDept/{roleFlag}" })
	public String schDept (String startDate, String endDate, @PathVariable String roleFlag,String orgFlow,Model model){
		model.addAttribute("roleFlag",roleFlag);
		List<String> titleDate = null;
		boolean isWeek = SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"));
		if(!StringUtil.isNotBlank(startDate)){
			startDate = DateUtil.getCurrDate();
			endDate = DateUtil.newDateOfAddMonths(startDate,12);
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
		}
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)){
			if(isWeek){
				titleDate = getWeeksByTwoDate(startDate,endDate);
			}else{// if(SchUnitEnum.Month.getId().equals(InitConfig.getSysCfg("res_rotation_unit")))
				String schStartMonth = startDate.substring(0,7);
				String schEndMonth = endDate.substring(0,7);
				titleDate = getMonthsByTwoMonth(schStartMonth,schEndMonth);
			}
			model.addAttribute("titleDate",titleDate);
			List<SchArrangeResult> arrResultList = null;
			if(GlobalConstant.USER_LIST_LOCAL.equals(roleFlag))//医院角色下
			{
				List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(GlobalContext.getCurrentUser().getOrgFlow());
				arrResultList =schArrangeResultBiz.searchArrangeResultByDateAndOrg(startDate, endDate,GlobalContext.getCurrentUser().getOrgFlow());
				model.addAttribute("schDeptList",schDeptList);
			}
			if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag))//平台角色下
			{
				List<SysOrg> orgList = orgBiz.searchSysOrg();
				model.addAttribute("orgList", orgList);
				if(StringUtil.isNotBlank(orgFlow))
				{
					List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(orgFlow);
					arrResultList =schArrangeResultBiz.searchArrangeResultByDateAndOrg(startDate, endDate,orgFlow);
					model.addAttribute("schDeptList",schDeptList);
				}
			}


			if(arrResultList != null && arrResultList.size()>0){
				Map<String,List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
				for(SchArrangeResult sar : arrResultList){
					String schDeptFlow = sar.getSchDeptFlow();
					String resultStartDate = sar.getSchStartDate();
					String resultEndDate = sar.getSchEndDate();
					if(StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)){
						if(isWeek){
							List<String> weeks = getWeeksByTwoDate(resultStartDate,resultEndDate);
							if(weeks!=null){
								for(String week : weeks){
									String key = schDeptFlow+week;
									if(resultListMap.get(key)==null){
										List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key,sarList);
									}else{
										resultListMap.get(key).add(sar);
									}
								}
							}
						}else{
							resultStartDate = resultStartDate.substring(0,7);
							resultEndDate = resultEndDate.substring(0,7);
							//计算该轮转科室该月人数
							List<String> months = getMonthsByTwoMonth(resultStartDate,resultEndDate);
							if(months!=null){
								for(String month : months){
									String key = schDeptFlow+month;
									if(resultListMap.get(key)==null){
										List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key,sarList);
									}else{
										resultListMap.get(key).add(sar);
									}
								}
							}
						}
					}
				}
				model.addAttribute("resultListMap",resultListMap);
			}
		}
		return "schTwo/doc/schDept";
	}
	@RequestMapping(value = {"/doc/getSchDept" })
	@ResponseBody
	public Object getSchDept(String orgFlow){
		List<SchDept> schDeptList =null;
			if(StringUtil.isNotBlank(orgFlow))//医院角色下
			{
				 schDeptList=schDeptBiz.searchSchDeptList(orgFlow);

			}
		return schDeptList;
	}
	/**
	 * 获取两个月份之间的所有月
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private List<String> getMonthsByTwoMonth(String startDate,String endDate){
		List<String> months = null;
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && startDate.compareTo(endDate)<=0){
			months = new ArrayList<String>();
			months.add(startDate);
			if(!startDate.equals(endDate)){
				String currDate = startDate;
				while(!currDate.equals(endDate)){
					currDate = DateUtil.newMonthOfAddMonths(currDate,1);
					months.add(currDate);
				}
			}
		}
		return months;
	}

	/**
	 * 获取两个日期之间的所有周
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private List<String> getWeeksByTwoDate(String startDate,String endDate){
		List<String> weeks = new ArrayList<>();
        List<String> periodTimeList = getPeriodTimeByTwoDate(startDate,endDate);
        for(String per : periodTimeList){
            weeks.add(per.split("~")[0]);
        }
		/*if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && startDate.compareTo(endDate)<=0){
			weeks = new ArrayList<String>();
			String startYear = startDate.substring(0,4);
			String endYear = endDate.substring(0,4);
			if(startYear.equals(endYear)){
				long startDays = DateUtil.signDaysBetweenTowDate(startDate,startYear+"-01-01");
				long endDays = DateUtil.signDaysBetweenTowDate(endDate,startYear+"-01-01");
				long startWeek = weekFormat(startDays);
				long endWeek = weekFormat(endDays);
				while(startWeek<=endWeek){
					weeks.add(startYear+"-"+startWeek);
					startWeek++;
				}
			}else{
				int start = Integer.parseInt(startYear);
				int end = Integer.parseInt(endYear);
				while(start<=end){
					String currYear = String.valueOf(start);
					long dayNum = 0;
					if(startYear.equals(currYear)){
						dayNum = DateUtil.signDaysBetweenTowDate(startDate,startYear+"-01-01");
						long endNum = DateUtil.signDaysBetweenTowDate(currYear+"-12-31",currYear+"-01-01");
						long startWeek = weekFormat(dayNum);
						long endWeek = weekFormat(endNum);
						while(startWeek<=endWeek){
							weeks.add(currYear+"-"+startWeek);
							startWeek++;
						}
					}else if(endYear.equals(currYear)){
						dayNum = DateUtil.signDaysBetweenTowDate(endDate,currYear+"-01-01");
						long startWeek = 1;
						long endWeek = weekFormat(dayNum);
						while(startWeek<=endWeek){
							weeks.add(currYear+"-"+startWeek);
							startWeek++;
						}
					}else{
						dayNum = DateUtil.signDaysBetweenTowDate(currYear+"-12-31",currYear+"-01-01");
						long startWeek = 1;
						long endWeek = weekFormat(dayNum);
						while(startWeek<=endWeek){
							weeks.add(currYear+"-"+startWeek);
							startWeek++;
						}
					}
					start++;
				}
			}
		}*/
		return weeks;
	}

	private long weekFormat(long days){
		long result = 1;
		if(days!=0){
			result = (long)Math.ceil(days/7.0);
		}
		return result;
	}

	/**
	 * 医师轮转查询
	 * */
	@RequestMapping(value = {"/doc/schDoc" },method = RequestMethod.GET)
	public String schDoc (DoctorSearchForm doctorSearchForm,Model model,Integer currentPage,HttpServletRequest request) throws Exception{
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		if(doctorSearchForm != null ){
//			if(StringUtil.isBlank(doctorSearchForm.getDoctorCategoryId())){
//				doctorSearchForm.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
//			}
			model.addAttribute("doctorCategoryId",doctorSearchForm.getDoctorCategoryId());
			if(currentPage==null || currentPage==0){
				currentPage = 1;
			}

			PageHelper.startPage(currentPage,getPageSize(request));
			List<ResDoctor> doctorList = schDoctortBiz.searchResDoctor(orgFlow,doctorSearchForm);

			if(doctorList != null && doctorList.size()>0){
				model.addAttribute("doctorList",doctorList);

				List<String> doctorFlows = new ArrayList<String>();
				for(ResDoctor doctor : doctorList){
					doctorFlows.add(doctor.getDoctorFlow());
				}
//				List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchArrangeResultByDoctorFlows(doctorFlows);
//				if(arrResultList != null && arrResultList.size()>0){
//					Map<String,List<SchArrangeResult>> arrResultMap = new HashMap<String,List<SchArrangeResult>>();
//					for(SchArrangeResult arrResult : arrResultList){
//						if(arrResultMap.get(arrResult.getDoctorFlow()) == null){
//							List<SchArrangeResult> arrResultTempList = new ArrayList<SchArrangeResult>();
//							arrResultTempList.add(arrResult);
//							arrResultMap.put(arrResult.getDoctorFlow(),arrResultTempList);
//						}else{
//							arrResultMap.get(arrResult.getDoctorFlow()).add(arrResult);
//						}
//					}
//					model.addAttribute("arrResultMap",arrResultMap);
//				}
				List<SysUser> userList = userBiz.searchSysUserByuserFlows(doctorFlows);
				if(userList!=null && userList.size()>0){
					Map<String,SysUser> userMap = new HashMap<String, SysUser>();
					for(SysUser user : userList){
						userMap.put(user.getUserFlow(),user);
					}
					model.addAttribute("userMap",userMap);
				}
			}
		}

		List<SchRotation> rotationList = schRotationtBiz.searchSchRotationByIsPublish();
		rotationList = schRotationtBiz.schRotations(rotationList,orgFlow);
		if(rotationList != null && rotationList.size()>0){
			Map<String,SchRotation> rotationMap = new HashMap<String,SchRotation>();
			for(SchRotation rotation : rotationList){
				rotationMap.put(rotation.getRotationFlow(),rotation);
			}
			model.addAttribute("rotationMap",rotationMap);
		}
		return "schTwo/doc/schDoc";
	}

	@RequestMapping(value = {"/doc/searchSchDoc" },method = RequestMethod.POST)
	public String searchSchDoc (DoctorSearchForm doctorSearchForm,Model model,Integer currentPage,HttpServletRequest request) throws Exception{
		return schDoc(doctorSearchForm,model,currentPage,request);
	}

	@RequestMapping(value = {"/doc/rotationDetail" },method = RequestMethod.GET)
	public String rotationDetail(String doctorFlow,Model model){
		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctor doctor = schDoctortBiz.readResDoctor(doctorFlow);
			model.addAttribute("doctor",doctor);

			List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctorFlow);
			model.addAttribute("arrResultList",arrResultList);

			List<SchDoctorAbsence> absenceList = schDoctorAbsenceBiz.searchSchDoctorAbsenceByDoctor(doctorFlow);
			if(absenceList!=null && absenceList.size()>0){
				Map<String,Integer> absenceCount = new HashMap<String, Integer>();
				for(SchDoctorAbsence absence : absenceList){
					String key = absence.getSchDeptFlow();
					Integer day = 0;
					if(StringUtil.isNotBlank(absence.getIntervalDay())){
						day = Integer.parseInt(absence.getIntervalDay());
					}
					if(absenceCount.get(key)==null){
						absenceCount.put(key,day);
					}else{
						absenceCount.put(key,absenceCount.get(key)+day);
					}
				}
				model.addAttribute("absenceCount",absenceCount);
			}
		}
		return "schTwo/doc/rotationDetail";
	}

	@RequestMapping(value = {"/template/ruleView" },method = RequestMethod.GET)
	public String ruleView(String rotationFlow,Model model){
		SchRotation rotation = schRotationtBiz.readSchRotation(rotationFlow);
		model.addAttribute("rotation",rotation);

		List<SchRotationDept> rotationDeptList = schRotationDeptBiz.searchSchRotationDept(rotationFlow);
		List<SchRotationGroup> groupList = schRotationtGroupBiz.searchSchRotationGroup(rotationFlow);
		model.addAttribute("groupList",groupList);
		if(rotationDeptList!=null && rotationDeptList.size()>0){
			Map<String,List<SchRotationDept>> groupRotationDeptListMap = new HashMap<String, List<SchRotationDept>>();
			List<String> relRecordFlows = new ArrayList<String>();
			for(SchRotationDept rotationDept : rotationDeptList){
				relRecordFlows.add(rotationDept.getRecordFlow());
				String key = rotationDept.getGroupFlow();
				if(groupRotationDeptListMap.get(key)==null){
					List<SchRotationDept> rotationDeptTempList = new ArrayList<SchRotationDept>();
					rotationDeptTempList.add(rotationDept);
					groupRotationDeptListMap.put(key,rotationDeptTempList);
				}else{
					groupRotationDeptListMap.get(key).add(rotationDept);
				}
			}
			model.addAttribute("groupRotationDeptListMap",groupRotationDeptListMap);

			List<SchRotationDeptReq> rotationDeptReqList = schRotationDeptBiz.searchStandardReqByRelFlows(relRecordFlows);
			if(rotationDeptReqList!=null && rotationDeptReqList.size()>0){
				Map<String,List<SchRotationDeptReq>> reqMap = new HashMap<String,List<SchRotationDeptReq>>();
				for(SchRotationDeptReq req : rotationDeptReqList){
					String key = req.getRelRecordFlow()+req.getRecTypeId();
					if(reqMap.get(key)==null){
						List<SchRotationDeptReq> reqListTemp = new ArrayList<SchRotationDeptReq>();
						reqListTemp.add(req);
						reqMap.put(key,reqListTemp);
					}else{
						if(ResRecTypeEnum.CaseRegistry.getId().equals(req.getRecTypeId())){
							SchRotationDeptReq bigReq = reqMap.get(key).get(0);
							bigReq.setReqNum(req.getReqNum().add(bigReq.getReqNum()));
						}else{
							reqMap.get(key).add(req);
						}
					}
				}
				model.addAttribute("reqMap",reqMap);
			}
		}
		return "schTwo/template/ruleList";
	}

	@RequestMapping(value = {"/template/rotationClone"},method = RequestMethod.POST)
	@ResponseBody
	public String rotationClone(String rotationFlow,String rotationYear){
		int result = schRotationtBiz.rotationClone(rotationFlow,rotationYear);
		if(result!=GlobalConstant.ZERO_LINE){
			return GlobalConstant.OPRE_SUCCESSED_FLAG;
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}

	/**
	 *  计划审核
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/arrange/auditResult"},method = {RequestMethod.GET,RequestMethod.POST})
	public String auditResult(ResDoctor doctor,Model model){
		model.addAttribute("doctor",doctor);

		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		doctor.setOrgFlow(orgFlow);

		List<SchDept> schDeptList = schDeptBiz.countDeptArea(doctor);
		model.addAttribute("schDeptList",schDeptList);

		if(doctor.getSessionNumber()==null){
			doctor.setSessionNumber(DateUtil.getYear());
		}
		Map<String,String> resultDateAreaMap = schArrangeResultBiz.countDateArea(doctor);
		if(resultDateAreaMap!=null){
			String minDate = resultDateAreaMap.get("min");
			String maxDate = resultDateAreaMap.get("max");

			String[] minDates = minDate.split("-");
			String[] maxDates = maxDate.split("-");

			Integer minYear = Integer.parseInt(minDates[0]);
			Integer minMonth = Integer.parseInt(minDates[1]);

			Integer maxYear = Integer.parseInt(maxDates[0]);
			Integer maxMonth = Integer.parseInt(maxDates[1]);

			Integer beforeMonth = (maxYear-minYear)*12+(maxMonth-minMonth);
			List<String> monthList = new ArrayList<String>();
			Map<String,Object> resultCountMap = new HashMap<String, Object>();
			Map<String,Object> resultSubmitCountMap = new HashMap<String, Object>();

			String startMonth = minDates[0]+"-"+minDates[1];
			for(int i = 0 ; i<beforeMonth ; i++){
				String currMonth = DateUtil.newMonthOfAddMonths(startMonth,i);
				monthList.add(currMonth);
				doctor.setSchStatusId(null);
				List<Map<String,Object>> currMonthCountList = schArrangeResultBiz.countMonthNum(currMonth,doctor);
				if(currMonthCountList!=null && currMonthCountList.size()>0){
					for(Map<String,Object> map : currMonthCountList){
						resultCountMap.put(map.get("schDeptFlow")+currMonth,map.get("countNum"));
					}
				}

				doctor.setSchStatusId(SchStatusEnum.Submit.getId());
				List<Map<String,Object>> currMonthSubmitCountList = schArrangeResultBiz.countMonthNum(currMonth,doctor);
				if(currMonthSubmitCountList!=null && currMonthSubmitCountList.size()>0){
					for(Map<String,Object> map : currMonthSubmitCountList){
						resultSubmitCountMap.put(map.get("schDeptFlow")+currMonth,map.get("countNum"));
					}
				}
			}
			String lastMonth = maxDates[0]+"-"+maxDates[1];
			monthList.add(lastMonth);
			doctor.setSchStatusId(null);
			List<Map<String,Object>> currMonthCountList = schArrangeResultBiz.countMonthNum(lastMonth,doctor);
			if(currMonthCountList!=null && currMonthCountList.size()>0){
				for(Map<String,Object> map : currMonthCountList){
					resultCountMap.put(map.get("schDeptFlow")+lastMonth,map.get("countNum"));
				}
			}

			doctor.setSchStatusId(SchStatusEnum.Submit.getId());
			List<Map<String,Object>> currMonthSubmitCountList = schArrangeResultBiz.countMonthNum(lastMonth,doctor);
			if(currMonthSubmitCountList!=null && currMonthSubmitCountList.size()>0){
				for(Map<String,Object> map : currMonthSubmitCountList){
					resultSubmitCountMap.put(map.get("schDeptFlow")+lastMonth,map.get("countNum"));
				}
			}
			model.addAttribute("resultCountMap",resultCountMap);
			model.addAttribute("resultSubmitCountMap",resultSubmitCountMap);
			model.addAttribute("monthList",monthList);
		}

		return "schTwo/doc/auditResult";
	}

	/**
	 *  计划审核医师列表
	 * @return
	 */
	@RequestMapping(value = {"/template/doctorDetailList" },method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doctorDetailList(String schDeptFlow,String month,ResDoctor doctorForm){
		Map<String,Object> dataMap = null;
		if(StringUtil.isNotBlank(month) && StringUtil.isNotBlank(schDeptFlow)){
			dataMap = new HashMap<String, Object>();
			String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
			doctorForm.setOrgFlow(orgFlow);
			List<SchArrangeResult> resultList = schArrangeResultBiz.searchInMonthResult(schDeptFlow,month,doctorForm);
			if(resultList!=null && resultList.size()>0){
				dataMap.put("resultList",resultList);

				List<String> doctorFlows = new ArrayList<String>();
				for(SchArrangeResult result : resultList){
					doctorFlows.add(result.getDoctorFlow());
				}

				List<ResDoctor> doctorList = doctorBiz.searchDoctorByuserFlow(doctorFlows);
				if(doctorList!=null && doctorList.size()>0){
					Map<String,ResDoctor> doctorMap = new HashMap<String, ResDoctor>();
					for(ResDoctor doctor : doctorList){
						doctorMap.put(doctor.getDoctorFlow(),doctor);
					}
					dataMap.put("doctorMap",doctorMap);
				}
			}
		}
		return dataMap;
	}

	/**
	 *  计划审核通过
	 * @return
	 */
	@RequestMapping(value = {"/template/auditComplate" },method = RequestMethod.POST)
	@ResponseBody
	public String auditComplate(){
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		doctorBiz.resultAudit(orgFlow);
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}

	/**
	 *  计划变更审核
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/arrange/auditResultChange"},method = RequestMethod.GET)
	public String auditResultChange(Model model){
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();

		ResDoctor doctor = new ResDoctor();
		doctor.setOrgFlow(orgFlow);
		doctor.setSchStatusId(SchStatusEnum.Submit.getId());
		List<ResDoctor> doctorList = doctorBiz.searchByDoc(doctor);
		if(doctorList!=null && doctorList.size()>0){
			model.addAttribute("doctorList",doctorList);

			List<String> doctorFlows = new ArrayList<String>();
			for(ResDoctor doctorTemp : doctorList){
				doctorFlows.add(doctorTemp.getDoctorFlow());
			}

			List<SysUser> userList = userBiz.searchSysUserByuserFlows(doctorFlows);
			if(userList!=null && userList.size()>0){
				Map<String,SysUser> userMap = new HashMap<String, SysUser>();
				for(SysUser user : userList){
					userMap.put(user.getUserFlow(),user);
				}
				model.addAttribute("userMap",userMap);
			}
		}

		return "schTwo/doc/auditResultChange";
	}

	/**
	 *  医师计划列表
	 * @return
	 */
	@RequestMapping(value = {"/template/doctorResultList"},method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doctorResultList(String doctorFlow){
		Map<String,Object> resultProcessMap = null;
		if(StringUtil.isNotBlank(doctorFlow)){
			resultProcessMap = new HashMap<String, Object>();

			List<SchArrangeResult> resultList = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctorFlow);
			resultProcessMap.put("resultList",resultList);

			List<ResDoctorSchProcess> processList = processBiz.searchProcessByDoctor(doctorFlow);
			if(processList!=null && processList.size()>0){
				Map<String,ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
				for(ResDoctorSchProcess process : processList){
					processMap.put(process.getSchResultFlow(),process);
				}
				resultProcessMap.put("processMap",processMap);
			}
		}
		return resultProcessMap;
	}

	/**
	 * 保存自由排班结果
	 * @param doctorFlow
	 * @param groupFlow
	 * @param standardDeptId
	 * @param standardDeptId
	 * @return
	 */
	@RequestMapping(value = {"/template/saveFreeRostering"},method = RequestMethod.POST)
	@ResponseBody
	public String saveFreeRostering(String doctorFlow,String groupFlow,String standardDeptId,String standardDeptName,String schDeptFlow){
		if(StringUtil.isNotBlank(doctorFlow)){
			int result = schArrangeResultBiz.editFreeRostering(doctorFlow,groupFlow,standardDeptId,standardDeptName,schDeptFlow);
			if(GlobalConstant.ZERO_LINE!=result){
				SysUser currUser = GlobalContext.getCurrentUser();
				ResDoctor doctor = doctorBiz.readDoctor(currUser.getUserFlow());
				if(doctor!=null && doctorFlow.equals(doctor.getDoctorFlow())){
					doctor.setSchFlag(GlobalConstant.FLAG_N);
				}
				return GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}

	/**
	 * 保存新顺序
	 * @param recordFlows
	 * @return
	 */
	@RequestMapping(value = {"/template/sortRotationDept"},method = RequestMethod.POST)
	@ResponseBody
	public String sortRotationDept(String[] recordFlows){
		int result = schRotationDeptBiz.saveRotationDeptOrd(recordFlows);
		if(GlobalConstant.ZERO_LINE!=result){
			return GlobalConstant.OPRE_SUCCESSED_FLAG;
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}

	/**
	 * 查询需轮转科室,以供排班
	 * @param rotationFlow
	 * @param selDepts
	 * @return
	 */
	@RequestMapping(value = {"/template/simulateResult"},method = RequestMethod.POST)
	public String simulateResult(String rotationFlow,String[] selDepts,Model model){
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		List<SchRotationDept> mustRotationDeptList = schRotationDeptBiz.searchOrgSchRotationDeptMust(rotationFlow,orgFlow);

		if(selDepts!=null && selDepts.length>0){
			List<String> selDeptList = Arrays.asList(selDepts);
			List<SchRotationDept> selRotationDeptList = schRotationDeptBiz.searchRotationDeptByRecordFlows(selDeptList);
			if(mustRotationDeptList!=null){
				mustRotationDeptList.addAll(selRotationDeptList);
			}
		}
		model.addAttribute("rotationDeptList",mustRotationDeptList);
		return "schTwo/arrange/groupRosteringHandDept";
	}


	@RequestMapping(value = {"/template/saveGroupResult"},method = RequestMethod.POST)
	@ResponseBody
	public String saveGroupResult(@RequestBody List<SchArrangeResult> resultList,String groupId){
		int result = schArrangeResultBiz.saveGroupResult(resultList,groupId,GlobalContext.getCurrentUser());
		if(result!=GlobalConstant.ZERO_LINE){
			return GlobalConstant.OPRE_SUCCESSED_FLAG;
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}

	/**
	 * 获取未关联好的规则数量
	 * @param orgFlow
	 * @param rotationFlow
	 * @return
	 */
	@RequestMapping(value = {"/template/getUnrelCount"},method = RequestMethod.GET)
	@ResponseBody
	public Object getUnrelCount(String orgFlow,String rotationFlow,String secondRotationFlow){
		Map<String,Object> map=new HashMap<>();
		int result = 0;
		if(StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(orgFlow)){
			result = schRotationDeptBiz.getUnrelCount(orgFlow, rotationFlow);
			map.put(rotationFlow,result);
		}
		result = 0;
		if(StringUtil.isNotBlank(secondRotationFlow))
		{
			result = schRotationDeptBiz.getUnrelCount(orgFlow, secondRotationFlow);
			map.put(secondRotationFlow,result);
		}
		return map;
	}

	@RequestMapping("/exportExcel")
	public void exportExcel(String startDate,String endDate,ResDoctor doctor,DoctorSearchForm doctorSearchForm,HttpServletResponse response) throws Exception{
		//单位
		String unit = SchUnitEnum.Month.getName();
		if(SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"))){
			unit = SchUnitEnum.Week.getName();
		}else{
			unit = SchUnitEnum.Month.getName();
		}

		//按条件获取医师
		List<ResDoctor> doctorList = schDoctortBiz.searchResDoctor(GlobalContext.getCurrentUser().getOrgFlow(),doctorSearchForm);

		//按条件查找排班
		List<SchArrangeResult> resultList = schArrangeResultBiz.searchResultByDoctor(doctor);
		Map<String,List<SchArrangeResult>> resultListMap = null;
		if(resultList!=null && resultList.size()>0){
			resultListMap = new HashMap<String, List<SchArrangeResult>>();
			for(SchArrangeResult sar : resultList){
				String key = sar.getDoctorFlow();
				if(resultListMap.get(key)==null){
					List<SchArrangeResult> sars = new ArrayList<SchArrangeResult>();
					sars.add(sar);
					resultListMap.put(key,sars);
				}else{
					resultListMap.get(key).add(sar);
				}
			}
		}

		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");

		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		//列宽自适应
		Map<Integer, Integer> colWidthAuto = new HashMap<Integer, Integer>();

		if(doctorList!=null && doctorList.size()>0 && resultListMap!=null){
	    	int rownum = 0;
	    	for(ResDoctor rd : doctorList){
	    		String key = rd.getDoctorFlow();

				//合并单元格
				sheet.addMergedRegion(new CellRangeAddress(rownum, rownum + 1, 0, 0));

				HSSFRow rowDept = sheet.createRow(rownum);
	    		HSSFRow rowDate = sheet.createRow(rownum+1);

	    		HSSFCell cell = rowDept.createCell(0);
	    		cell.setCellValue(rd.getDoctorName());
	    		cell.setCellStyle(styleLeft);

	    		int colnum = 1;
	    		List<SchArrangeResult> sars = resultListMap.get(key);
	    		if(sars!=null && sars.size()>0){
	    			for(SchArrangeResult sar : sars){
	    				Integer width = colWidthAuto.get(colnum);
	    				if(width==null){
	    					width = 0;
	    				}

	    				String deptvalue = sar.getSchDeptName()+"("+sar.getSchMonth()+unit+")";
	    				deptvalue = StringUtil.defaultString(deptvalue);
	    				HSSFCell deptCell = rowDept.createCell(colnum);
	    				deptCell.setCellValue(deptvalue);
	    				deptCell.setCellStyle(styleCenter);
	    				Integer deptNewWidth = deptvalue.getBytes().length*1*256;

	    				rowDate.createCell(0);
	    				String datevalue = sar.getSchStartDate()+"~"+sar.getSchEndDate();
	    				datevalue = StringUtil.defaultString(datevalue);
	    				HSSFCell dateCell = rowDate.createCell(colnum);
	    				dateCell.setCellValue(datevalue);
	    				dateCell.setCellStyle(styleCenter);
	    				Integer dateNewWidth = datevalue.getBytes().length*1*256;

	    				width = width<deptNewWidth?deptNewWidth:width;
	    				width = width<dateNewWidth?dateNewWidth:width;
	    				sheet.setColumnWidth(colnum,width);
	    				colWidthAuto.put(colnum,width);

	    				colnum++;
	    			}
	    		}else{
	    			sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,1,1));
	    			HSSFCell noRosterCell = rowDept.createCell(1);
					noRosterCell.setCellValue("暂无排班！");
					noRosterCell.setCellStyle(stylevwc);
					rowDate.createCell(0);
	    			rowDate.createCell(1);
	    		}
	    		rownum+=2;
	    	}
	    }

		String fileName = "学员排班表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
	    response.setContentType("application/octet-stream;charset=UTF-8");
	    wb.write(response.getOutputStream());
	}

	/**
	 * 删除排班
	 */
	@RequestMapping(value = {"/template/delDocRostering"},method = RequestMethod.GET)
	@ResponseBody
	public String delDocRostering(String doctorFlow){
		if(StringUtil.isNotBlank(doctorFlow)){
			int result = schArrangeResultBiz.delArrangeResult(doctorFlow,true);
			if(GlobalConstant.ZERO_LINE!=result){
				return GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}
	@RequestMapping("/template/exportExcel/{roleFlag}")
	public void exportExcel(String startDate,String endDate, @PathVariable String roleFlag,String orgFlow,String schDeptFlow, HttpServletResponse response) throws IOException, Exception{
		List<String> titleDate = null;
		boolean isWeek = SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"));
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)){
			if(isWeek){
				titleDate = getWeeksByTwoDate(startDate,endDate);
			}else{
				String schStartMonth = startDate.substring(0,7);
				String schEndMonth = endDate.substring(0,7);
				titleDate = getMonthsByTwoMonth(schStartMonth,schEndMonth);
			}
			List<SchDept> schDeptList = new ArrayList<>();
			List<SchArrangeResult> arrResultList = null;
			if(GlobalConstant.USER_LIST_LOCAL.equals(roleFlag))//医院角色下
			{
                if (StringUtil.isNotBlank(schDeptFlow)) {
                    SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
                    schDeptList.add(dept);
                } else {
                    schDeptList = schDeptBiz.searchSchDeptList(GlobalContext.getCurrentUser().getOrgFlow());
                }
                    arrResultList =schArrangeResultBiz.searchArrangeResultByDateAndOrg(startDate, endDate,GlobalContext.getCurrentUser().getOrgFlow());

            }
			if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag))//平台角色下
			{

				if(StringUtil.isNotBlank(orgFlow))
				{
                    if (StringUtil.isNotBlank(schDeptFlow)) {
                        SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
                        schDeptList.add(dept);
                    } else {
                        schDeptList = schDeptBiz.searchSchDeptList(orgFlow);
                    }
					arrResultList =schArrangeResultBiz.searchArrangeResultByDateAndOrg(startDate, endDate,orgFlow);

				}
			}
			//创建工作簿
			HSSFWorkbook wb = new HSSFWorkbook();
			// 为工作簿添加sheet
			HSSFSheet sheet = wb.createSheet("sheet1");

			if(arrResultList != null && arrResultList.size()>0){
				Map<String,List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
				for(SchArrangeResult sar : arrResultList){
					String sdetFlow = sar.getSchDeptFlow();
					String resultStartDate = sar.getSchStartDate();
					String resultEndDate = sar.getSchEndDate();
					if(StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)){
						if(isWeek){
							List<String> weeks = getWeeksByTwoDate(resultStartDate,resultEndDate);
							if(weeks!=null){
								for(String week : weeks){
									String key = sdetFlow+week;
									if(resultListMap.get(key)==null){
										List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key,sarList);
									}else{
										resultListMap.get(key).add(sar);
									}
								}
							}
						}else{
							resultStartDate = resultStartDate.substring(0,7);
							resultEndDate = resultEndDate.substring(0,7);
							//计算该轮转科室该月人数
							List<String> months = getMonthsByTwoMonth(resultStartDate,resultEndDate);
							if(months!=null){
								for(String month : months){
									String key = sdetFlow+month;
									if(resultListMap.get(key)==null){
										List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key,sarList);
									}else{
										resultListMap.get(key).add(sar);
									}
								}
							}
						}
					}
				}


				//定义将用到的样式
				HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
				styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

				sheet.setColumnWidth(0, 3000);
				HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
				styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

				HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
				stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

				//列宽自适应
				Map<Integer, Integer> colWidthAuto = new HashMap<Integer, Integer>();
				List<Integer> deptFLowList=new ArrayList<Integer>();
			    if(schDeptList!=null && schDeptList.size()>0 && resultListMap!=null){
			    	int rownum = 0;
			    	for(SchDept rd : schDeptList){
			    		HSSFRow rowDept = sheet.createRow(rownum);
			    		int j=0;
			    		for (String tDate : titleDate) {
			    			String flow =rd.getSchDeptFlow()+tDate;
			    			List<SchArrangeResult> sars = resultListMap.get(flow);
			    			if (sars!=null&&!sars.isEmpty()) {
			    				deptFLowList.add(sars.size());
							}
			    		}
			    		if (deptFLowList!=null&&deptFLowList.isEmpty()) {
			    			deptFLowList.add(1);
						}
			    			j=Collections.max(deptFLowList);
			    			for (int i = 1; i <=j; i++) {
			    			HSSFRow rowDate = sheet.createRow(rownum+i);
			    			int colnum = 1;
				    			for (String tDate : titleDate) {
				    				Integer width = colWidthAuto.get(colnum);
				    				if(width==null){
				    					width = 0;
				    				}
				    				String flow =rd.getSchDeptFlow()+tDate;
					    			List<SchArrangeResult> sars = resultListMap.get(flow);
					    			if (sars!=null&&!sars.isEmpty()) {
					    				String doctorName="";
					    				if (sars.size()>=i) {
					    					SchArrangeResult result=sars.get(i-1);
					    					HSSFCell cell = rowDate.createCell(colnum);
					    					doctorName=doctorName+result.getDoctorName()+"("+result.getSchStartDate()+"~"+result.getSchEndDate()+")";
								    		cell.setCellValue(doctorName);
								    		cell.setCellStyle(styleLeft);
								    		Integer dateNewWidth = doctorName.getBytes().length*1*256;
						    				width = width<dateNewWidth?dateNewWidth:width;
						    				sheet.setColumnWidth(colnum,width);
						    				colWidthAuto.put(colnum,width);
										}
					    			}else{
										HSSFCell cell = rowDate.createCell(colnum);
										String doctorName="";
										cell.setCellValue(doctorName);
							    		cell.setCellStyle(styleLeft);
									}
					    			colnum++;
			    			}
						}
						//合并单元格
						sheet.addMergedRegion(new CellRangeAddress(rownum, rownum + j, 0, 0));
						deptFLowList.clear();
			    		HSSFCell cell = rowDept.createCell(0);
			    		cell.setCellValue(rd.getDeptName());
			    		cell.setCellStyle(styleLeft);
			    		int colnum = 1;
			    		for (String Date : titleDate) {
			    			HSSFCell deptCell = rowDept.createCell(colnum);
		    				deptCell.setCellValue(Date);
		    				deptCell.setCellStyle(styleCenter);
		    				colnum++;
						}
			    		rownum=rownum+(j+1);
			    	}
			    }
			}
			String fileName = "学员排班表.xls";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
			wb.write(response.getOutputStream());
		}
	}
	@RequestMapping("/template/guanLian")
	public String guanLian(String flow,SysOrg org,Model model){
			SchRotation schRotation=schRotationtBiz.readSchRotation(flow);
			model.addAttribute("schRotation",schRotation);
			List<SysOrg> sysOrgs=orgBiz.queryAllSysOrg(org);
			model.addAttribute("sysOrgs",sysOrgs);
			List<ResRotationOrg> resRotationOrgList=resRotationOrgBiz.searchByRotationFlow(flow);
			Map<String,Object> map=new HashMap<String, Object>();
			Map<String,String> recordFlowMap=new HashMap<String, String>();
			for (ResRotationOrg resRotationOrg : resRotationOrgList) {
				map.put(resRotationOrg.getOrgFlow(),resRotationOrg);
				recordFlowMap.put(resRotationOrg.getOrgFlow(),resRotationOrg.getRecordFlow());
			}
			model.addAttribute("recordFlowMap",recordFlowMap);
			model.addAttribute("map",map);
			return "schTwo/template/jointRead";
	}


    @RequestMapping("/template/exportExcel2/{roleFlag}")
    public void exportExcel2(String startDate, String endDate, @PathVariable String roleFlag, String orgFlow, String schDeptFlow, HttpServletResponse response) throws IOException, Exception {
        List<String> titleDate = new ArrayList<>();
        SysOrg org = new SysOrg();
        boolean isWeek = SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"));
        if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {

            if (isWeek) {
                titleDate = getPeriodTimeByTwoDate(startDate, endDate);
            }
            List<SchDept> schDeptList = new ArrayList<>();
            List<SchArrangeResult> arrResultList = null;
            if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag))//医院角色下
            {
                if (StringUtil.isNotBlank(schDeptFlow)) {
                    SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
                    schDeptList.add(dept);
                } else {
                    schDeptList = schDeptBiz.searchSchDeptList(GlobalContext.getCurrentUser().getOrgFlow());
                }
                org = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
                arrResultList = schArrangeResultBiz.searchArrangeResultByDateAndOrg(startDate, endDate, GlobalContext.getCurrentUser().getOrgFlow());

            }
            if (GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag))//平台角色下
            {

                if (StringUtil.isNotBlank(orgFlow)) {
                    org = orgBiz.readSysOrg(orgFlow);
                    if (StringUtil.isNotBlank(schDeptFlow)) {
                        SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
                        schDeptList.add(dept);
                    } else {
                        schDeptList = schDeptBiz.searchSchDeptList(orgFlow);
                    }
                    arrResultList = schArrangeResultBiz.searchArrangeResultByDateAndOrg(startDate, endDate, orgFlow);

                }
            }
            titleDate.add(0, "科室\\时间");
            //创建工作簿
            HSSFWorkbook wb = new HSSFWorkbook();
            // 为工作簿添加sheet
            HSSFSheet sheet = wb.createSheet("sheet1");
            Map<String, List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
            if (arrResultList != null && arrResultList.size() > 0) {
                for (SchArrangeResult sar : arrResultList) {
                    String deptFlow = sar.getSchDeptFlow();
                    String resultStartDate = sar.getSchStartDate();
                    String resultEndDate = sar.getSchEndDate();
                    if (StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)) {
                        if (isWeek) {
                            List<String> weeks = getPeriodTimeByTwoDate(resultStartDate, resultEndDate);
                            if (weeks != null) {
                                for (String week : weeks) {
                                    String key = deptFlow + week.split("~")[0];
                                    if (resultListMap.get(key) == null) {
                                        List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
                                        sarList.add(sar);
                                        resultListMap.put(key, sarList);
                                    } else {
                                        resultListMap.get(key).add(sar);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //定义将用到的样式
            HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
            styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            sheet.setColumnWidth(0, 3000);
            HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
            styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

            HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
            stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

            // List<String> paramsId = new ArrayList<>();
            HSSFRow rowDept = sheet.createRow(0);
            HSSFCell cell = null;
            cell = rowDept.createCell(0);
            cell.setCellValue(titleDate.get(0));
            cell.setCellStyle(stylevwc);
            for (int i = 1; i < titleDate.size(); i++) {
                // String flow = titleDate.get(i).split("~")[0];
                cell = rowDept.createCell(i);
                cell.setCellValue(titleDate.get(i).split("~")[1]);
                int length = titleDate.get(i).split("~")[1].length();
                sheet.setColumnWidth(i, length * 300);
                //     paramsId.add(flow);
            }
            for (int i = 0; i < schDeptList.size(); i++) {
                rowDept = sheet.createRow(i + 1);
                rowDept.createCell(0).setCellValue(schDeptList.get(i).getSchDeptName());
                int h=1;
                for (int j = 1; j < titleDate.size(); j++) {
                    String keyFlow = schDeptList.get(i).getSchDeptFlow() + titleDate.get(j).split("~")[0];
                    List<SchArrangeResult> resultList = resultListMap.get(keyFlow);
                    String result = "";
                    if (null != resultList && resultList.size() > 0) {
                        for (SchArrangeResult arrangeResult : resultList) {
                            result = result + "  \n" + arrangeResult.getDoctorName();

                        }
                        if(resultList.size() > h){
                            h=resultList.size();
                        }
                    }
                    rowDept.setHeight((short) ((h+1) * 228));
                    rowDept.createCell(j).setCellValue(new HSSFRichTextString(result));
                }
            }

            //表头
            /*CellRangeAddress cra = new CellRangeAddress(0, 0, 0, titleDate.size());
            sheet.addMergedRegion(cra);
            rowDept = sheet.createRow(0);
            rowDept.createCell(0).setCellStyle(styleCenter);
            rowDept.createCell(0).setCellValue(org.getOrgName() + "实习轮转表");*/

            CellRangeAddress cra = new CellRangeAddress(schDeptList.size() + 1, schDeptList.size() + 2, 0, titleDate.size());
            sheet.addMergedRegion(cra);
            rowDept = sheet.createRow(schDeptList.size() + 1);
            rowDept.createCell(0).setCellValue("医院名称：" + org.getOrgName() + "   \n 盖章：");

            String fileName = org.getOrgName() + "实习轮转表.xls";
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            wb.write(response.getOutputStream());

        }

    }
    /**
     * 获取两个时间段之间所有周时间段
     * @param startDate
     * @param endDate
     * @return
     */
    private List<String> getPeriodTimeByTwoDate(String startDate,String endDate){
        List<String> weekTime = new ArrayList<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd");
            Date startTime = sdf.parse(startDate);
            Date endTime = sdf.parse(endDate);
            Calendar ca = Calendar.getInstance();//创建一个日期实例
            ca.setTime(startTime);
            //int dayNum = ca.get(Calendar.DAY_OF_WEEK);
            int dayNum =startTime.getDay();
            int year = ca.get(Calendar.YEAR);
            int week = ca.get(Calendar.WEEK_OF_YEAR);
            //ca.add(Calendar.DAY_OF_MONTH, -7);
          //  System.out.println(ca.get(Calendar.MONTH));
            if(week==1&&1<ca.get(Calendar.MONTH)){
                year+=1;
            }
            String startWeek = year+"-"+week +"~" + sdf2.format(startTime)+ "—" + sdf2.format(new Date(startTime.getTime() + (7-dayNum) * 24 * 60 * 60 * 1000));
            weekTime.add(startWeek);
            startTime = new Date(startTime.getTime() + (8-dayNum) * 24 * 60 * 60 * 1000);
            while ((startTime.compareTo(new Date(endTime.getTime() - 7 * 24 * 60 * 60 * 1000))) < 0){
                ca.setTime(startTime);
                int year1 = ca.get(Calendar.YEAR);
                int week1 = ca.get(Calendar.WEEK_OF_YEAR);
                //ca.add(Calendar.DAY_OF_MONTH, -7);
                if(week1==1&&1<ca.get(Calendar.MONTH)){
                    year1+=1;
                }
                String periodTime =year1+"-"+week1 +"~" + sdf2.format(startTime) +"—" + sdf2.format(new Date(startTime.getTime() + 6 * 24 * 60 * 60 * 1000));
                weekTime.add(periodTime);
                startTime = new Date(startTime.getTime() + 7 * 24 * 60 * 60 * 1000);
            }
            ca.setTime(startTime);//实例化一个日期
            int year2 = ca.get(Calendar.YEAR);
            int week2 = ca.get(Calendar.WEEK_OF_YEAR);
            //ca.add(Calendar.DAY_OF_MONTH, -7);
            if(week2==1&&1<ca.get(Calendar.MONTH)){
                year2+=1;
            }
            String endWeek = year2+"-"+week2 +"~" +sdf2.format(startTime) + "—" + sdf2.format(endTime);
            weekTime.add(endWeek);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return weekTime;
    }
}

