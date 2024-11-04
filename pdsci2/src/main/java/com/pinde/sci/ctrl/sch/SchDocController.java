package com.pinde.sci.ctrl.sch;

import com.pinde.core.entyties.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.dao.base.SchRotationDeptMapper;
import com.pinde.sci.dao.base.SchRotationGroupMapper;
import com.pinde.sci.dao.sys.SysOrgExtMapper;
import com.pinde.sci.enums.res.*;
import com.pinde.sci.enums.sch.*;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.form.sch.*;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/sch")
public class SchDocController extends GeneralController{

	private static Logger logger = LoggerFactory.getLogger(SchDocController.class);


    @Autowired
    private IResGradeBiz resGradeBiz;
	@Autowired
	private SchRotationGroupMapper rotationGroupMapper;
	@Autowired
	private SchRotationDeptMapper rotationDeptMapper;
    @Autowired
    private IResRecBiz resRecBiz;
	@Autowired
	private ISchDoctorBiz schDoctortBiz;
	@Autowired
	private IRoleBiz roleBiz;
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
	@Autowired
	private IResTrainingSpeDeptBiz resTrainingSpeDeptBiz;
	@Autowired
	private IResResponsibleTeacherDoctorBiz resResponsibleTeacherDoctorBiz;
	@Autowired
	private IPubUserResumeBiz pubUserResumeBiz;
	@Autowired
	private SysOrgExtMapper orgExtMapper;

	/**
	 * 医师信息列表
	 * */
	@RequestMapping(value = {"/doc/userInfo/list" },method = RequestMethod.GET)
	public String doctorList (DoctorSearchForm doctorSearchForm,Model model) throws Exception{
		List<ResDoctor> doctorList = schDoctortBiz.searchResDoctorAll(GlobalContext.getCurrentUser().getOrgFlow(),doctorSearchForm);
		model.addAttribute("doctorList",doctorList);
		return "sch/doc/userInfo/list";
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

		return "sch/doc/userInfo/editDoc";
	}

	@RequestMapping(value = "/doc/userInfo/saveDoctor",method={RequestMethod.POST})
	@ResponseBody
	public String saveDoctor(ResDoctor doctor, Model model) throws Exception{
		 if(null != doctor){
			 doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			 doctor.setOrgName(GlobalContext.getCurrentUser().getOrgName());
//			 doctor.setSexName(UserSexEnum.getNameById(doctor.getSexId()));
			 doctor.setGraduatedName(DictTypeEnum.GraduateSchool.getDictNameById(doctor.getGraduatedId()));//DoctorGraduated
//			 doctor.setDegreeName(DictTypeEnum.UserDegree.getDictNameById(doctor.getDegreeId()));
//			 doctor.setEducationName(DictTypeEnum.UserEducation.getDictNameById(doctor.getEducationId()));
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
			return "redirect:/sch/template/gToLocalListInfo";
		}
		model.addAttribute("currRoleFlag",GlobalConstant.USER_LIST_GLOBAL);
		return "redirect:/sch/template/localList";
	}

	@RequestMapping(value="/template/gToLocalListInfo",method=RequestMethod.GET)
	@ResponseBody
	public String gToLocalListInfo(Integer updateCount){
		return "更新" + updateCount + "条方案！";
	}

	@RequestMapping(value="/template/localList",method=RequestMethod.GET)
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
		return "redirect:/sch/template/list";
	}

	@RequestMapping(value = {"/template/list" } )
	public String rotationList (String roleFlag,SchRotation rotation,String rotationName,Integer updateCount,String currRoleFlag,String orgFlow,Model model) throws UnsupportedEncodingException {
		model.addAttribute("updateCount",updateCount);
		if(StringUtil.isNotBlank(rotationName)){
//			rotationName = java.net.URLDecoder.decode(rotationName,"UTF-8");
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
		Map<String,Object> rotationOrgUnrelMap=new HashMap<>();
		//获取轮转方案（如果是对指定机构展示，则只显示非指定机构和指定本机构的）
		List<SchRotation> rotationList = schRotationtBiz.searchOrgStandardRotation(rotation);
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		if (!GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
			rotationList=schRotationtBiz.schRotations(rotationList,orgFlow);
			if(rotationList!=null)
			{
				for(SchRotation r:rotationList)
				{
					int	result = schRotationDeptBiz.getUnrelCount(orgFlow, r.getRotationFlow());
					rotationOrgUnrelMap.put(r.getRotationFlow(),result);
				}
			}
		}
		model.addAttribute("rotationOrgUnrelMap",rotationOrgUnrelMap);
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
		return "sch/template/list";
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
		return "sch/template/orgDiyRotationList";
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
			return "sch/template/viewRotation";
		}
		return "sch/template/editRotation";
	}

	/**
	 * 保存编辑 轮转方案
	 * @param rotation
	 * @param model
	 * @return
	 * @throws Exception
     */
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
				 if(RecDocCategoryEnum.Intern.getId().equals(rotation.getDoctorCategoryId()))
				 {
					 if(StringUtil.isNotBlank(rotation.getWorkOrgId())) {
						 rotation.setWorkOrgName(DictTypeEnum.SendSchool.getDictNameById(rotation.getWorkOrgId()));
					 }else{
						 rotation.setWorkOrgId("");
						 rotation.setWorkOrgName("");
					 }
				 }else{
					 rotation.setWorkOrgId("");
					 rotation.setWorkOrgName("");
				 }
			 }else{
				 rotation.setDoctorCategoryName("");
				 rotation.setWorkOrgId("");
				 rotation.setWorkOrgName("");
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
	public String rule (String roleFlag,String rotationFlow,String orgFlow,String currRoleFlag,Model model){
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

				List<SchRotationDept> rotationDeptList = schRotationDeptBiz.searchOrgSchRotationDept(rotationFlow,orgFlow);
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

				List<SchRotationGroup> rotationGroupList = schRotationtGroupBiz.searchOrgGroupOrAll(rotationFlow,orgFlow,null);
				if(rotationGroupList!=null && rotationGroupList.size()>0){
					model.addAttribute("rotationGroupList",rotationGroupList);

					Map<String,SchRotationGroup> localGroupMap = new HashMap<String, SchRotationGroup>();
					for(SchRotationGroup group : rotationGroupList){
						localGroupMap.put(group.getStandardGroupFlow(),group);
					}
					model.addAttribute("localGroupMap",localGroupMap);
				}

				//关联关系
				List<SchDeptRel> deptRelList = deptRelBiz.searchRelByOrg(orgFlow);
				if(deptRelList!=null && deptRelList.size()>0){
					Map<String,Map<String,SchDeptRel>> deptRelMap = new HashMap<String,Map<String,SchDeptRel>>();
					for(SchDeptRel deptRel : deptRelList){
						String key = deptRel.getStandardDeptId();
						if(deptRelMap.get(key)==null){
							Map<String,SchDeptRel> deptRelTempMap = new HashMap<String, SchDeptRel>();
							deptRelTempMap.put(deptRel.getSchDeptFlow(),deptRel);
							deptRelMap.put(key,deptRelTempMap);
						}else{
							deptRelMap.get(key).put(deptRel.getSchDeptFlow(),deptRel);
						}
					}
					model.addAttribute("deptRelMap",deptRelMap);
				}

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
		return "sch/template/rule";
	}

	/**
	 * 轮转规则-方案配置
	 * */
	@RequestMapping(value = {"/template/configuration"},method = RequestMethod.GET)
	public String configuration (String recordFlow,String rotationFlow,Model model,String typeId){
		model.addAttribute("typeId",typeId);
		System.err.println("000000000000"+typeId);
		SchRotation rotation = schRotationtBiz.readSchRotation(rotationFlow);
		model.addAttribute("rotation",rotation);

		SchRotationDept rotationDept = schRotationDeptBiz.readSchRotationDept(recordFlow);
		model.addAttribute("rotationDept",rotationDept);

		return "sch/template/configuration";
	}

	/**
	 *  基层实践或理论学习配置
	 * */
	@RequestMapping(value = {"/template/setPractict"},method = RequestMethod.POST)
	@ResponseBody
	public String setPractic(String recordFlow,String typeId){
		SchRotationDept schRotationDept = new SchRotationDept();
		schRotationDept.setRecordFlow(recordFlow);
		schRotationDept.setPracticOrTheory(typeId);
		int result = schRotationDeptBiz.saveSchRotationDept(schRotationDept);
		if(result > 0){
			return GlobalConstant.OPRE_SUCCESSED;
		}else{
			return GlobalConstant.OPRE_FAIL;
		}
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
		return "sch/template/ruleCfg";
	}

	/**
	 *  微调轮转科室
	 * */
	@RequestMapping(value = {"/template/saveCfg"},method = RequestMethod.POST)
	@ResponseBody
	public String saveCfg(@RequestBody List<SchRotationDept> rotationDeptList,String groupFlow,Integer deptNum,Integer maxDeptNum,String orgFlow){
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());

		if(rotationDeptList!=null){
			for(SchRotationDept rotationDept : rotationDeptList){
				String standardDeptId = rotationDept.getStandardDeptId();
				String schDeptFlow = rotationDept.getSchDeptFlow();
				if(StringUtil.isNotBlank(standardDeptId)){
					rotationDept.setStandardDeptName(DictTypeEnum.StandardDept.getDictNameById(standardDeptId));
				}
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
			}

			SchRotationGroup group = null;
			if(StringUtil.isNotBlank(groupFlow)){
				group = new SchRotationGroup();
				group.setGroupFlow(groupFlow);
				group.setDeptNum(deptNum);
				if(maxDeptNum!=null && maxDeptNum!=0){
					group.setMaxDeptNum(maxDeptNum);
				}
			}
			schRotationDeptBiz.saveRotationDeptList(rotationDeptList,group);
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}

	/**
	 *  轮转规范
	 * */
	@RequestMapping(value = {"/template/rotationStandard"},method = RequestMethod.GET)
	public String rotationStandard(String recordFlow,Model model){
		SchRotationDept rotationDept = schRotationDeptBiz.readSchRotationDept(recordFlow);
		model.addAttribute("rotationDept",rotationDept);
		return "sch/template/rotationStandard";
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
	public String rotationRequire(String recordFlow,String rotationFlow,Model model,String typeId){
		model.addAttribute("typeId",typeId);
		System.err.println("000000000000"+typeId);
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

		return "sch/template/rotationRequire";
	}

	@RequestMapping(value = {"/template/requireList"},method = RequestMethod.GET)
	public String requireList(SchRotationDeptReq deptReq,Model model,String typeId){
		if(deptReq !=null){
			//默认添加其他项
			schRotationDeptBiz.defaultOtherItem(deptReq.getRelRecordFlow(),deptReq.getRecTypeId());

			List<SchRotationDeptReq> deptReqList = schRotationDeptBiz.searchDeptReqByRel(deptReq.getRelRecordFlow(),deptReq.getRecTypeId());
			model.addAttribute("deptReqList",deptReqList);
			model.addAttribute("typeId",typeId);
		}
		return "sch/template/requireList";
	}
	@RequestMapping("/template/synchRequire")
	public String synchRequire( Model model, SchRotation rotation){

		List<SchRotation> rotationList = schRotationtBiz.searchOrgStandardRotation(rotation);
		model.addAttribute("rotationList",rotationList);

		List<SchRotationGroup> standardRotationGroupList = schRotationtGroupBiz.searchAllSchRotationGroup();
		model.addAttribute("standardRotationGroupList",standardRotationGroupList);

		List<SchRotationDept> standardRotationDeptList = schRotationDeptBiz.searchAllSchRotationDept();
		model.addAttribute("standardRotationDeptList",standardRotationDeptList);

		return "sch/template/synchRequire";
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
	public String editDeptReq(String reqFlow,Model model,String typeId,String rotationFlow) throws Exception{
		SchRotationDeptReq deptReq = schRotationDeptBiz.readDeptReq(reqFlow);
		model.addAttribute("deptReq",deptReq);
		model.addAttribute("typeId",typeId);

		SchRotation rotation = schRotationtBiz.readSchRotation(rotationFlow);
		model.addAttribute("rotation",rotation);

		return "sch/template/editRequire";
	}

	@RequestMapping(value = {"/template/saveDeptReq"},method = RequestMethod.POST)
	@ResponseBody
	public String saveDeptReq(SchRotationDeptReq deptReq,String typeId){
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
				if(JszyTCMPracticEnum.N.getId().equals(typeId)){
					deptReq.setRecTypeName(RegistryTypeEnum.getNameById(deptReq.getRecTypeId()));
				}else if(JszyTCMPracticEnum.BasicPractice.getId().equals(typeId)){
					deptReq.setRecTypeName(PracticRegistryTypeEnum.getNameById(deptReq.getRecTypeId()));
				}else if(JszyTCMPracticEnum.TheoreticalStudy.getId().equals(typeId)){
					deptReq.setRecTypeName(TheoreticalRegistryTypeEnum.getNameById(deptReq.getRecTypeId()));
				}

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

		return "sch/template/deptcfg_edit";
	}

	/**
	 * 保存方案
	 * @param rotationDeptFrom
	 * @param roleFlag
	 * @param rotationFlow
	 * @param model
     * @return
     */
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

		return "sch/doc/seldept";
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
				return "redirect:/sch/arrange/rosteringHandDept";
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

		return "sch/arrange/rosteringHandSelDept";
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
//		return "sch/doc/seldept";
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

		return "sch/doc/seldept_item";
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
	 * 医师信息查询
	 * */
//	@RequestMapping(value = {"/arrange/searchRostering_hand" }, method = RequestMethod.POST)
//	public String searchRosteringHand (DoctorSearchForm doctorSearchForm,Model model) throws Exception{
//		return rosteringHandList(doctorSearchForm,model);
//	}

//	@RequestMapping(value = {"/arrange/rosteringHand" }, method = RequestMethod.GET)
//	public String rosteringHandList (DoctorSearchForm doctorSearchForm,Model model) throws Exception{
//		List<ResDoctor> doctorList = schDoctortBiz.searchResDoctor(GlobalContext.getCurrentUser().getOrgFlow(),doctorSearchForm);
//		model.addAttribute("doctorList",doctorList);
//		return "sch/arrange/rostering_hand";
//	}

	/**
	 * 手动排班
	 * */
	@RequestMapping(value = {"/arrange/rosteringHand" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String rosteringHandList (ResDoctor doctor,String rosteringType,Model model,String isSch){
		SysUser user = GlobalContext.getCurrentUser();
		doctor.setOrgFlow(user.getOrgFlow());
		if(doctor.getSessionNumber()==null){
			doctor.setSessionNumber(DateUtil.getYear());
		}
		if(StringUtil.isBlank(doctor.getDoctorCategoryId())){
			doctor.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
		}
		if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
			doctor.setDoctorCategoryId(RecDocCategoryEnum.ChineseMedicine.getId());
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
			List<ResDoctor> doctorList = doctorBiz.searchByDocHaveRotation(doctor);
			if(doctorList != null && doctorList.size() > 0){
				Iterator<ResDoctor> iterator = doctorList.iterator();
				while (iterator.hasNext()){
					ResDoctor tempDoctor = iterator.next();
					String doctorFlow = tempDoctor.getDoctorFlow();
					if("1".equals(isSch)){
						List<SchArrangeResult> results = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctorFlow);
						if(results == null || results.size() < 1){
							iterator.remove();
						}
					}else if("0".equals(isSch)){
						List<SchArrangeResult> results = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctorFlow);
						if(results != null && results.size() > 0){
							iterator.remove();
						}
					}
				}
			}
			model.addAttribute("doctorList",doctorList);
		}
		return "sch/arrange/rosteringHand";
	}

	/**
	 * 加载排班数据
	 * @param doctorFlow
	 * @param model
     * @return
     */
	@RequestMapping(value = {"/arrange/rosteringHandDept"}, method = {RequestMethod.GET,RequestMethod.POST})
	public String rosteringHandDept(String doctorFlow,Model model){
		if(StringUtil.isNotBlank(doctorFlow)){

			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);

			if(doctor!=null){
				model.addAttribute("doctor",doctor);

				if(StringUtil.isNotBlank(doctor.getRotationFlow())){
					SchRotation rotation = schRotationtBiz.readSchRotation(doctor.getRotationFlow());
					model.addAttribute("rotation",rotation);

					List<SchRotationGroup> groupList = schRotationtGroupBiz.searchOrgGroupOrAll(doctor.getRotationFlow(),doctor.getOrgFlow(),null);
					if(groupList!=null && groupList.size()>0){
						Map<String,SchRotationGroup> groupMap = new HashMap<String, SchRotationGroup>();
						for(SchRotationGroup group : groupList){
							groupMap.put(group.getGroupFlow(),group);
						}
						model.addAttribute("groupMap",groupMap);
					}
				}

				//所有轮转科室供选修
				List<SysDept> sysDeptList = sysDeptBiz.searchDeptByOrg(doctor.getOrgFlow());
				if(sysDeptList!=null && sysDeptList.size()>0){
					List<String> deptFlows = new ArrayList<String>();
					for(SysDept dept : sysDeptList){
						deptFlows.add(dept.getDeptFlow());
					}
					List<SchDept> deptList = schDeptBiz.searchDeptByDeotFlows(deptFlows);
					if(deptList!=null && deptList.size()>0){
						model.addAttribute("schDeptList",deptList);

						List<String> schDeptFlows = new ArrayList<String>();
						for(SchDept schDept : deptList){
							schDeptFlows.add(schDept.getSchDeptFlow());
						}
						List<SchDeptRel> deptRelList = deptRelBiz.searchRelBySchDepts(schDeptFlows);
						if(deptRelList!=null && deptRelList.size()>0){
							Map<String,List<SchDeptRel>> deptRelMap = new HashMap<String, List<SchDeptRel>>();
							for(SchDeptRel deptRel : deptRelList){
								String key = deptRel.getSchDeptFlow();
								if(deptRelMap.get(key)==null){
									List<SchDeptRel> deptRelTempList = new ArrayList<SchDeptRel>();
									deptRelTempList.add(deptRel);
									deptRelMap.put(key,deptRelTempList);
								}else{
									deptRelMap.get(key).add(deptRel);
								}
							}
							model.addAttribute("deptRelMap",deptRelMap);
						}
					}
				}

				List<SchArrangeResult> resultList = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctorFlow);
				model.addAttribute("resultList",resultList);

				if(resultList==null || resultList.size()<=0){
					if(StringUtil.isNotBlank(doctor.getRotationFlow())){
						model.addAttribute("doctorFlow",doctorFlow);
						return "redirect:/sch/doc/toSelDept";
					}
				}else{
					Map<String,SchArrangeResult> resultMap = new HashMap<String, SchArrangeResult>();
					List<String> groupFlows = new ArrayList<String>();
					for(SchArrangeResult result : resultList){
						if(StringUtil.isBlank(result.getStandardDeptId())){
							resultMap.put(result.getSchDeptFlow(),result);
						}

						String groupFlow = result.getGroupFlow();
						if(!groupFlows.contains(groupFlow) && StringUtil.isNotBlank(groupFlow)){
							groupFlows.add(groupFlow);
						}
					}
					model.addAttribute("resultMap",resultMap);

					List<ResDoctorSchProcess> processList = processBiz.searchProcessByDoctor(doctorFlow);
					if(processList!=null && processList.size()>0){
						Map<String,ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
						for(ResDoctorSchProcess process : processList){
							processMap.put(process.getSchResultFlow(),process);
						}
						model.addAttribute("processMap",processMap);
					}

					if(groupFlows.size()>0){
						List<SchRotationGroup> groupList = schRotationtGroupBiz.searchGroupByGroupFlows(groupFlows);
						if(groupList!=null && groupList.size()>0){
							model.addAttribute("groupList",groupList);

							Map<String,SchRotationGroup> groupMap = new HashMap<String, SchRotationGroup>();
							for(SchRotationGroup group : groupList){
								groupMap.put(group.getGroupFlow(),group);
							}
							model.addAttribute("groupMap",groupMap);
						}
					}

					ResDoctorSchProcess searchProcess = new ResDoctorSchProcess();
					searchProcess.setUserFlow(doctorFlow);
					searchProcess.setSchFlag("Y");
					List<ResDoctorSchProcess> schProcessList = processBiz.searchProcessByDoctor(searchProcess);
					List<Map<String, String>> times = new ArrayList<>();
					if(schProcessList!=null&&schProcessList.size()>0){
						for(ResDoctorSchProcess resDoctorSchProcess:schProcessList){
							String startDate = resDoctorSchProcess.getSchStartDate();
							String endDate = resDoctorSchProcess.getSchEndDate();
							Map<String,String> map = new HashMap<>();
							map.put("startDate",startDate);
							map.put("endDate",endDate);
							times.add(map);
						}
						List<Map<String, String>> mapList = TimeUtil.getNewTimes(times);
						if(mapList.size()>1){
							model.addAttribute("series","N");
						}
					}

				}
			}
		}

		//去前10年和后10年的每月最后一天
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

		return "sch/arrange/rosteringHandDept";
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
		return "sch/arrange/groupSelAndRostering";
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
//		return "sch/arrange/rostering_hand_dept";
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
		return "sch/doc/aid/terminat";
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
		return "sch/doc/aid/terminatEdit";
	}
	@RequestMapping(value = {"/doc/aid/terminatCheck" }, method = RequestMethod.GET)
	public String terminatCheck (Model model,String doctorFlow) throws Exception{
		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctor doctor=schDoctortBiz.readResDoctor(doctorFlow);
			model.addAttribute("doctor", doctor);
		}
		return "sch/doc/aid/terminatCheck";
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

		return "sch/doc/aid/absence";
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

		return "sch/doc/aid/absenceEdit";
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
	public String schDept (String startDate, String endDate, @PathVariable String roleFlag,String orgName,String orgFlow,Model model){
		model.addAttribute("roleFlag",roleFlag);
		List<String> titleDate = null;
		boolean isWeek = SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"));
		if(!StringUtil.isNotBlank(startDate)){
			String currDate = DateUtil.getCurrDate();
            startDate = DateUtil.newDateOfAddMonths(currDate,-3);
			endDate = DateUtil.newDateOfAddMonths(currDate,4);
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
				//医院管理员增加查协同基地学员轮转情况
				List<SysOrg> orgList=new ArrayList<>();
				SysOrg sysOrg=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
				orgList.add(sysOrg);
				List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
				if(jointOrgList!=null&&jointOrgList.size()>0){
					for (SysOrg so:jointOrgList) {
						orgList.add(so);
					}
				}
				model.addAttribute("orgList",orgList);
				orgFlow = StringUtil.isBlank(orgFlow)?GlobalContext.getCurrentUser().getOrgFlow():orgFlow;
				orgName = StringUtil.isBlank(orgName)?GlobalContext.getCurrentUser().getOrgName():orgName;
				List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(orgFlow);
				arrResultList =schArrangeResultBiz.searchArrangeResultByDateAndOrg(startDate, endDate,orgFlow);
				model.addAttribute("schDeptList",schDeptList);
			}
			if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag))//平台角色下
			{
				List<SysOrg> orgList = orgBiz.searchSysOrg();
				model.addAttribute("orgList", orgList);
				if("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))){//江苏中医默认展示江苏省中医院
					if(StringUtil.isBlank(orgName)){
						orgName = "江苏省中医院";
					}
					model.addAttribute("orgName",orgName);
					SysOrg sysOrg = orgBiz.readSysOrgByName(orgName);
					if(sysOrg!=null){
						orgFlow = sysOrg.getOrgFlow();
						model.addAttribute("orgFlow",orgFlow);
					}
				}
				if(StringUtil.isNotBlank(orgName))
				{
					SysOrg sysOrg = orgBiz.readSysOrgByName(orgName);
					if(sysOrg!=null){
						orgFlow = sysOrg.getOrgFlow();
					}
					SysUser user=GlobalContext.getCurrentUser();
					String medicineTypeId=user.getMedicineTypeId();
					List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(orgFlow);
					Map<String,String> param=new HashMap<>();
					param.put("startDate",startDate);
					param.put("endDate",endDate);
					param.put("orgFlow",orgFlow);
					param.put("medicineTypeId",medicineTypeId);
					arrResultList =schArrangeResultBiz.searchArrangeResultByDateAndOrgByMap(param);
					model.addAttribute("schDeptList",schDeptList);
				}
			}
			if(GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag))//高校角色下
			{
				SysUser currentUser = GlobalContext.getCurrentUser();
				SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
				String workOrgId = currentOrg.getSendSchoolId();
				List<SysOrg> orgList = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
				model.addAttribute("orgList", orgList);
				if(StringUtil.isBlank(orgFlow)){
					if(orgList!=null&&orgList.size()>0){
						orgFlow=orgList.get(0).getOrgFlow();
                        orgName=orgList.get(0).getOrgName();
					}else{
						orgFlow=GlobalContext.getCurrentUser().getOrgFlow();
                        orgName =GlobalContext.getCurrentUser().getOrgName();
					}
				}
				List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(orgFlow);
				Map<String,Object> map=new HashMap<>();
				map.put("startDate",startDate);
				map.put("endDate",endDate);
				map.put("orgFlow",orgFlow);
				map.put("workOrgId",workOrgId);
				arrResultList =schArrangeResultBiz.searchArrangeResultByDateAndOrg1(map);
				model.addAttribute("schDeptList",schDeptList);
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
		model.addAttribute("orgFlow",orgFlow);
		model.addAttribute("orgName",orgName);
		return "sch/doc/schDept";
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
	public String schDoc (DoctorSearchForm doctorSearchForm,Model model,Integer currentPage,String role,String orgFlow,
						  HttpServletRequest request,String [] datas) throws Exception{
		List<String> docTypeList = new ArrayList<>();
		//复选框勾选标识
		Map<String, String> doctorTypeSelectMap = new HashMap<>();
		SysDict sysDict = new SysDict();
		sysDict.setDictTypeId(DictTypeEnum.DoctorType.getId());
		sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysDict> dictList = dictBiz.searchDictList(sysDict);
		if (datas != null && datas.length > 0) {
			docTypeList = Arrays.asList(datas);
			for (SysDict dict : dictList) {
				if (docTypeList.contains(dict.getDictId())) {
					doctorTypeSelectMap.put(dict.getDictId(), "checked");
				}
			}
		}
		if (datas == null) {
			docTypeList = new ArrayList<>();
			for (SysDict dict : dictList) {
				docTypeList.add(dict.getDictId());
				doctorTypeSelectMap.put(dict.getDictId(), "checked");
			}
		}
		model.addAttribute("doctorTypeSelectMap", doctorTypeSelectMap);
		model.addAttribute("role",role);
		if(StringUtil.isBlank(orgFlow)&&!"pt".equals(role)&&!"gx".equals(role)){
			orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		}
		String medicineTypeId="";
		String workOrgId="";
		if("pt".equals(role)){
			SysUser user=GlobalContext.getCurrentUser();
			medicineTypeId=user.getMedicineTypeId();
			List<SysOrg> orgList = orgBiz.searchTrainOrgList();
			model.addAttribute("orgList",orgList);
			if(StringUtil.isBlank(orgFlow)&&orgList!=null&&orgList.size()>0){
				orgFlow = orgList.get(0).getOrgFlow();
			}
		}else if("gx".equals(role)){
			SysUser currentUser = GlobalContext.getCurrentUser();
			SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
			workOrgId = currentOrg.getSendSchoolId();
			List<SysOrg> orgList = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			model.addAttribute("orgList",orgList);
			if(StringUtil.isBlank(orgFlow)&&orgList!=null&&orgList.size()>0){
				orgFlow = orgList.get(0).getOrgFlow();
			}
		}else{
			//医院管理员增加查协同基地学员轮转情况
			List<SysOrg> orgList=new ArrayList<>();
			SysOrg sysOrg=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			orgList.add(sysOrg);
			List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
			if(jointOrgList!=null&&jointOrgList.size()>0){
				for (SysOrg so:jointOrgList) {
					orgList.add(so);
				}
			}
			model.addAttribute("orgList",orgList);
		}
		//查询是否为广医进修过程页面
		String flag = InitConfig.getSysCfg("is_show_jxres");
		if(doctorSearchForm != null ){
			if(StringUtil.isBlank(doctorSearchForm.getDoctorCategoryId())){
				if(GlobalConstant.FLAG_Y.equals(flag)){
					doctorSearchForm.setDoctorCategoryId(RecDocCategoryEnum.Scholar.getId());
				}else{

                    if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
                        doctorSearchForm.setDoctorCategoryId(RecDocCategoryEnum.ChineseMedicine.getId());
                    }else{
                        doctorSearchForm.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
                    }
				}
			}
			if("all".equals(doctorSearchForm.getDoctorCategoryId())){
				doctorSearchForm.setDoctorCategoryId("");
			}
//			if(StringUtil.isBlank(doctorSearchForm.getDoctorCategoryId())){
//				doctorSearchForm.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
//			}
			model.addAttribute("doctorCategoryId",doctorSearchForm.getDoctorCategoryId());
			if(currentPage==null || currentPage==0){
				currentPage = 1;
			}

			if("responsibleTeacher".equals(role)) {
				List<String> doctorFlows = new ArrayList<>();
				ResResponsibleteacherDoctor search = new ResResponsibleteacherDoctor();
				search.setResponsibleteacherFlow(GlobalContext.getCurrentUser().getUserFlow());
				List<ResResponsibleteacherDoctor> resResponsibleteacherDoctorList = resResponsibleTeacherDoctorBiz.search(search);
				if (resResponsibleteacherDoctorList != null && resResponsibleteacherDoctorList.size() > 0) {
					for (ResResponsibleteacherDoctor resResponsibleteacherDoctor : resResponsibleteacherDoctorList) {
						String doctorFlow = resResponsibleteacherDoctor.getDoctorFlow();
						doctorFlows.add(doctorFlow);
					}
				}else{
					return "sch/doc/schDoc";
				}
				doctorSearchForm.setDoctorFlows(doctorFlows);
			}

			if("all".equals(doctorSearchForm.getDoctorCategoryId())){
				doctorSearchForm.setDoctorCategoryId("");
			}
			Map<String,Object> param = new HashMap<>();
			param.put("orgFlow",orgFlow);
			param.put("doctorSearchForm",doctorSearchForm);
			param.put("medicineTypeId",medicineTypeId);
			param.put("docTypeList",docTypeList);
			param.put("workOrgId",workOrgId);
			PageHelper.startPage(currentPage,getPageSize(request));
//			List<ResDoctor> doctorList = schDoctortBiz.searchResDoctor2(orgFlow,doctorSearchForm,medicineTypeId);
			List<ResDoctor> doctorList = schDoctortBiz.searchResDoctor2(param);

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
					Map<String,String> ageMap = new HashMap<>();
					for(SysUser user : userList){
						String userFlow = user.getUserFlow();
						userMap.put(userFlow,user);
						//大字段信息
						PubUserResume pubUserResume = pubUserResumeBiz.readPubUserResume(userFlow);
						if(pubUserResume != null){
							String xmlContent =  pubUserResume.getUserResume();
							if(StringUtil.isNotBlank(xmlContent)){
								//xml转换成JavaBean
								BaseUserResumeExtInfoForm extInfo=pubUserResumeBiz.converyToJavaBean(xmlContent,BaseUserResumeExtInfoForm.class);
								if(extInfo!=null){
									ageMap.put(userFlow,extInfo.getAge());
								}
							}
						}
					}
					model.addAttribute("ageMap",ageMap);
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

		if(GlobalConstant.FLAG_Y.equals(flag)){
			return "sch/doc/schDoc_jx";
		}
		if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
			return "sch/doc/schDoc4jszy";
		}
		return "sch/doc/schDoc";
	}

	@RequestMapping(value = {"/doc/searchSchDoc" },method = RequestMethod.POST)
	public String searchSchDoc (DoctorSearchForm doctorSearchForm,Model model,Integer currentPage,String orgFlow,
								String role,HttpServletRequest request,String [] datas) throws Exception{
//
//		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
//		String wsId = GlobalContext.getCurrentWsId();
//		List<SysRole> roleList = roleBiz.search(userFlow,wsId);
//		if(null!=roleList && roleList.size()>0){
//			role = roleList.get(0).getRoleFlow();
//		}
		return schDoc(doctorSearchForm,model,currentPage,role,orgFlow,request,datas);
	}

	@RequestMapping(value = {"/doc/rotationDetail" },method = RequestMethod.GET)
	public String rotationDetail(String doctorFlow,Model model){
		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctor doctor = schDoctortBiz.readResDoctor(doctorFlow);
			model.addAttribute("doctor",doctor);

			/*List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctorFlow);*/
			//增加“是否已评价带教/科主任”字段
			List<Map<String,Object>> arrResultList = schArrangeResultBiz.querySchArrangeResultByDoctor(doctorFlow);
			model.addAttribute("arrResultList",arrResultList);


            Map<String,Object> deptGradeMap = new HashMap<>();  //科室评价
            Map<String,Object> teacherGradeMap = new HashMap<>();//带教评价

            //评价带教老师,评价科室
            List<String> recTypeIds = new ArrayList<String>();
            List<String> processFlows= new ArrayList<>();

			Map<String,Object> processMsg = new HashMap<>();
			if(arrResultList!=null&& arrResultList.size()>0){
				for(Map<String,Object> temp: arrResultList){
					String resultFlow = temp.get("resultFlow").toString();
					ResDoctorSchProcess resDoctorSchProcess = processBiz.searchByResultFlow(resultFlow);
					processMsg.put(resultFlow,resDoctorSchProcess);
					if(resDoctorSchProcess!=null)
					{
						processFlows.add(resDoctorSchProcess.getProcessFlow());
					}
				}
			}

            recTypeIds.add(ResRecTypeEnum.DeptGrade.getId());
            recTypeIds.add(ResRecTypeEnum.TeacherGrade.getId());
            Map<String,Object> paramMap = new HashMap();
            paramMap.put("recTypeIds",recTypeIds);
            paramMap.put("processFlows",processFlows);
            paramMap.put("currentUserFlow",doctorFlow);
            List<DeptTeacherGradeInfo> gradeInfoList = resGradeBiz.searchResGradeByItems(paramMap);

            if(gradeInfoList != null && gradeInfoList.size() > 0){
                for(DeptTeacherGradeInfo tempGradeInfo : gradeInfoList){
                    String recTypeId = tempGradeInfo.getRecTypeId();
                    model.addAttribute(recTypeId,tempGradeInfo);
                    Map<String,Object> gradeMap = resRecBiz.parseGradeXml(tempGradeInfo.getRecContent());

                    if(ResRecTypeEnum.TeacherGrade.getId().equals(recTypeId)){
                        teacherGradeMap.put(tempGradeInfo.getRecFlow(),gradeMap);
                    }
                    if(ResRecTypeEnum.DeptGrade.getId().equals(recTypeId)){
                        deptGradeMap.put(tempGradeInfo.getRecFlow(),gradeMap);
                    }
//                    model.addAttribute(recTypeId+"Map",gradeMap);
                }
                model.addAttribute("teacherGradeMap",teacherGradeMap);
                model.addAttribute("deptGradeMap",deptGradeMap);
            }



			model.addAttribute("processMsg",processMsg);

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
		return "sch/doc/rotationDetail";
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
		return "sch/template/ruleList";
	}

	/**
	 * 方案复制
	 * @param rotationFlow
	 * @param rotationYear
     * @return
     */
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
	public String auditResult(ResDoctor doctor,Model model,String schStartDate,String schEndDate){
		model.addAttribute("doctor",doctor);
		if(StringUtil.isBlank(doctor.getDoctorCategoryId())){
			doctor.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
		}
		if("all".equals(doctor.getDoctorCategoryId())){
			doctor.setDoctorCategoryId("");
		}
		model.addAttribute("doctorCategoryId",doctor.getDoctorCategoryId());
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		doctor.setOrgFlow(orgFlow);

		List<SchDept> schDeptList = schDeptBiz.countDeptArea(doctor);
		model.addAttribute("schDeptList",schDeptList);

		if(doctor.getSessionNumber()==null){
			doctor.setSessionNumber(DateUtil.getYear());
		}
		Map<String,String> resultDateAreaMap = schArrangeResultBiz.countDateArea(doctor);
		if(resultDateAreaMap!=null){
			String minDate = "";
			String maxDate = "";
			if(StringUtil.isNotBlank(schStartDate)){
				minDate = schStartDate;
			}else {
				minDate = resultDateAreaMap.get("min");
			}
			if(StringUtil.isNotBlank(schEndDate)){
				maxDate = schEndDate;
			}else {
				maxDate = resultDateAreaMap.get("max");
			}

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

		return "sch/doc/auditResult";
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

		return "sch/doc/auditResultChange";
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
	 * @param addSchDeptFlows
	 * @param delResultFlows
	 * @return
	 */
	@RequestMapping(value = {"/template/saveFreeRostering"},method = RequestMethod.POST)
	@ResponseBody
	public String saveFreeRostering(String doctorFlow,String[] addSchDeptFlows,String[] delResultFlows){
		if(StringUtil.isNotBlank(doctorFlow)){
			int result = schArrangeResultBiz.editFreeRostering(doctorFlow,addSchDeptFlows,delResultFlows);
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
	 * @param model
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
		return "sch/arrange/groupRosteringHandDept";
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
	public int getUnrelCount(String orgFlow,String rotationFlow){
		int result = 0;
		if(StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(orgFlow)){
			result = schRotationDeptBiz.getUnrelCount(orgFlow, rotationFlow);
		}
		return result;
	}

	@RequestMapping("/exportExcel")
	public void exportExcel(String startDate,String endDate,ResDoctor doctor,DoctorSearchForm doctorSearchForm,
							String orgFlow,String[] datas,
							String role,HttpServletResponse response) throws Exception{
		List<String> docTypeList = new ArrayList<>();
		//复选框勾选标识
		SysDict sysDict = new SysDict();
		sysDict.setDictTypeId(DictTypeEnum.DoctorType.getId());
		sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysDict> dictList = dictBiz.searchDictList(sysDict);
		if (datas != null && datas.length > 0) {
			docTypeList = Arrays.asList(datas);
		}
		if (datas == null) {
			docTypeList = new ArrayList<>();
			for (SysDict dict : dictList) {
				docTypeList.add(dict.getDictId());
			}
		}
		//单位
		String unit = SchUnitEnum.Month.getName();
		if(SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"))){
			unit = SchUnitEnum.Week.getName();
		}else{
			unit = SchUnitEnum.Month.getName();
		}
		if("professionalBase".equals(role)){
			String trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
			doctorSearchForm.setTrainingSpeId(trainingSpeId);
		}
		boolean flag = false;
		if("responsibleTeacher".equals(role)) {
			List<String> doctorFlows = new ArrayList<>();
			ResResponsibleteacherDoctor search = new ResResponsibleteacherDoctor();
			search.setResponsibleteacherFlow(GlobalContext.getCurrentUser().getUserFlow());
			List<ResResponsibleteacherDoctor> resResponsibleteacherDoctorList = resResponsibleTeacherDoctorBiz.search(search);
			if (resResponsibleteacherDoctorList != null && resResponsibleteacherDoctorList.size() > 0) {
				for (ResResponsibleteacherDoctor resResponsibleteacherDoctor : resResponsibleteacherDoctorList) {
					String doctorFlow = resResponsibleteacherDoctor.getDoctorFlow();
					doctorFlows.add(doctorFlow);
				}
			}else{
				flag = true;
			}
			doctorSearchForm.setDoctorFlows(doctorFlows);
		}

		if(StringUtil.isBlank(orgFlow)&&!"pt".equals(role)){
			orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		}
		String medicineTypeId="";
		String workOrgId="";
		if("pt".equals(role)){
			SysUser user=GlobalContext.getCurrentUser();
			medicineTypeId=user.getMedicineTypeId();
		}else if("gx".equals(role)){
			SysUser currentUser = GlobalContext.getCurrentUser();
			SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
			workOrgId = currentOrg.getSendSchoolId();
		}
		//按条件获取医师
		Map<String,Object> param = new HashMap<>();
		param.put("orgFlow",orgFlow);
		param.put("doctorSearchForm",doctorSearchForm);
		param.put("medicineTypeId",medicineTypeId);
		param.put("docTypeList",docTypeList);
		param.put("workOrgId",workOrgId);
//			List<ResDoctor> doctorList = schDoctortBiz.searchResDoctor2(orgFlow,doctorSearchForm,medicineTypeId);
		List<ResDoctor> doctorList = schDoctortBiz.searchResDoctor2(param);
		if(flag){
			doctorList = null;
		}
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
		styleCenter.setAlignment(HorizontalAlignment.CENTER);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

		//列宽自适应
		Map<Integer, Integer> colWidthAuto = new HashMap<Integer, Integer>();

		if(doctorList!=null && doctorList.size()>0 && resultListMap!=null){
//			表头
			HSSFRow rowDeptHead = sheet.createRow(0);
			HSSFCell cellHead1 = rowDeptHead.createCell(0);
			HSSFCell cellHead2 = rowDeptHead.createCell(1);
			cellHead1.setCellValue("姓名");
			cellHead1.setCellStyle(styleLeft);
			cellHead2.setCellValue("轮转详情");
			cellHead2.setCellStyle(stylevwc);

	    	int rownum = 1;
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

	    				String deptvalue = sar.getSchDeptName()+"("+(sar.getSchMonth()==null?"0.0":(Double.parseDouble(sar.getSchMonth())+0.0))+unit+")";
	    				deptvalue = StringUtil.defaultString(deptvalue);
	    				HSSFCell deptCell = rowDept.createCell(colnum);
	    				deptCell.setCellValue(deptvalue);
	    				deptCell.setCellStyle(styleCenter);
	    				Integer deptNewWidth = deptvalue.getBytes().length*1*256;

	    				rowDate.createCell(0);
	    				String datevalue = sar.getSchStartDate()+"~"+sar.getSchEndDate();
						if(sar.getSchStartDate()==null||sar.getSchEndDate()==null){
							datevalue = "";
						}
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
				orgFlow=StringUtil.isBlank(orgFlow)?GlobalContext.getCurrentUser().getOrgFlow():orgFlow;
                if (StringUtil.isNotBlank(schDeptFlow)) {
                    SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
                    schDeptList.add(dept);
                } else {
                    schDeptList = schDeptBiz.searchSchDeptList(orgFlow);
                }
                    arrResultList =schArrangeResultBiz.searchArrangeResultByDateAndOrg(startDate, endDate,orgFlow);

            }
			if("professionalBase".equals(roleFlag))//专业基地管理员角色下
			{
				if (StringUtil.isNotBlank(schDeptFlow)) {
					SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
					schDeptList.add(dept);
				} else {
					SysUser currentUser = GlobalContext.getCurrentUser();//查询所有轮转科室
					String trainingSpeId = currentUser.getResTrainingSpeId();
					String orgFlow2 = currentUser.getOrgFlow();
					ResTrainingSpeDept search = new ResTrainingSpeDept();
					search.setOrgFlow(orgFlow2);
					search.setTrainingSpeId(trainingSpeId);
					List<ResTrainingSpeDept> resTrainingSpeDepts = resTrainingSpeDeptBiz.search(search);
					List<String> deptFlows = new ArrayList<>();
					if(resTrainingSpeDepts!=null&&resTrainingSpeDepts.size()>0){
						for(ResTrainingSpeDept resTrainingSpeDept:resTrainingSpeDepts){
							String deptFlow = resTrainingSpeDept.getDeptFlow();
							deptFlows.add(deptFlow);
						}
					}
					if(deptFlows!=null&&deptFlows.size()>0){
						schDeptList = schDeptBiz.searchDeptByDeotFlows(deptFlows);
					}
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
					SysUser user=GlobalContext.getCurrentUser();
					String medicineTypeId=user.getMedicineTypeId();
					Map<String,String> param=new HashMap<>();
					param.put("startDate",startDate);
					param.put("endDate",endDate);
					param.put("orgFlow",orgFlow);
					param.put("medicineTypeId",medicineTypeId);
					arrResultList =schArrangeResultBiz.searchArrangeResultByDateAndOrgByMap(param);

				}
			}
			if(GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag))//高校角色下
			{

				if(StringUtil.isNotBlank(orgFlow))
				{
					if (StringUtil.isNotBlank(schDeptFlow)) {
						SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
						schDeptList.add(dept);
					} else {
						schDeptList = schDeptBiz.searchSchDeptList(orgFlow);
					}
					SysUser user=GlobalContext.getCurrentUser();
					String medicineTypeId=user.getMedicineTypeId();
					Map<String,String> param=new HashMap<>();
					param.put("startDate",startDate);
					param.put("endDate",endDate);
					param.put("orgFlow",orgFlow);
					param.put("medicineTypeId",medicineTypeId);
					arrResultList =schArrangeResultBiz.searchArrangeResultByDateAndOrgByMap(param);

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
				styleCenter.setAlignment(HorizontalAlignment.CENTER);

				sheet.setColumnWidth(0, 3000);
				HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
				styleLeft.setAlignment(HorizontalAlignment.LEFT);
				styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

				HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
				stylevwc.setAlignment(HorizontalAlignment.CENTER);
				stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);


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
			return "sch/template/jointRead";
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
			if ("professionalBase".equals(roleFlag))//专业基地管理员角色下
			{
				if (StringUtil.isNotBlank(schDeptFlow)) {
					SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
					schDeptList.add(dept);
				} else {
					SysUser currentUser = GlobalContext.getCurrentUser();//查询所有轮转科室
					String trainingSpeId = currentUser.getResTrainingSpeId();
					String orgFlow2 = currentUser.getOrgFlow();
					ResTrainingSpeDept search = new ResTrainingSpeDept();
					search.setOrgFlow(orgFlow2);
					search.setTrainingSpeId(trainingSpeId);
					List<ResTrainingSpeDept> resTrainingSpeDepts = resTrainingSpeDeptBiz.search(search);
					List<String> deptFlows = new ArrayList<>();
					if(resTrainingSpeDepts!=null&&resTrainingSpeDepts.size()>0){
						for(ResTrainingSpeDept resTrainingSpeDept:resTrainingSpeDepts){
							String deptFlow = resTrainingSpeDept.getDeptFlow();
							deptFlows.add(deptFlow);
						}
					}
					if(deptFlows!=null&&deptFlows.size()>0){
						schDeptList = schDeptBiz.searchDeptByDeotFlows(deptFlows);
					}
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
            styleCenter.setAlignment(HorizontalAlignment.CENTER);

            sheet.setColumnWidth(0, 3000);
            HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
            styleLeft.setAlignment(HorizontalAlignment.LEFT);
            styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

            HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
            stylevwc.setAlignment(HorizontalAlignment.CENTER);
            stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

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
	/**
	 * 导入轮转数据要求数 界面
	 */
	@RequestMapping(value="/template/importDeptReq")
	public String importDeptReq(String rotationFlow,Model model){
		SchRotation rotation = schRotationtBiz.readSchRotation(rotationFlow);
		model.addAttribute("rotation",rotation);
		return "sch/template/importDeptReq";
	}
	/**
	 * 导入轮转数据要求数
	 */
	@RequestMapping(value="/template/importDeptReqExcel")
	@ResponseBody
	public String importDeptReqExcel(MultipartFile file,String relRecordFlow,String rotationFlow,String recTypeId,String recTypeName){
		if(file.getSize() > 0){
			try{
				Map<String,Object> args = new HashMap<>();
				args.put("relRecordFlow",relRecordFlow);
				args.put("rotationFlow",rotationFlow);
				args.put("recTypeId",recTypeId);
				args.put("recTypeName",recTypeName);
				ExcelUtile result = schRotationDeptBiz.importDeptReqExcel(file,args);
				if(null!=result){
					String code= (String) result.get("code");
					int count=(Integer) result.get("count");
					String msg= (String) result.get("msg");
					if("1".equals(code)){
						return GlobalConstant.UPLOAD_FAIL+msg;
					}else{
						if(GlobalConstant.ZERO_LINE != count){
							return GlobalConstant.UPLOAD_SUCCESSED + "导入"+count+"条记录！";
						}else{
							return GlobalConstant.UPLOAD_FAIL;
						}
					}
				}else {
					return GlobalConstant.UPLOAD_FAIL;
				}
			}catch(RuntimeException re){
				re.printStackTrace();
				return re.getMessage();
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}

	/**
	 * 导入轮转数据 界面
	 */
	@RequestMapping(value="/template/importDeptData")
	public String importDeptData(String rotationFlow,Model model){
		SchRotation rotation = schRotationtBiz.readSchRotation(rotationFlow);
		model.addAttribute("rotation",rotation);
		return "sch/template/importDeptData";
	}

	/**
	 * 导入轮转数据
	 */
	@RequestMapping(value="/template/importDeptDataExcel")
	@ResponseBody
	public String importDeptDataExcel(MultipartFile file,String recTypeId,String schDeptFlow,String processFlow,String rotationFlow){
		if(file.getSize() > 0){
			try{
				Map<String,Object> args = new HashMap<>();
				args.put("recTypeId",recTypeId);
				args.put("schDeptFlow",schDeptFlow);
				args.put("rotationFlow",rotationFlow);
				args.put("processFlow",processFlow);
				ExcelUtile result = schRotationDeptBiz.importDeptDataExcel(file,args);
				if(null!=result){
					String code= (String) result.get("code");
					int count=(Integer) result.get("count");
					String msg= (String) result.get("msg");
					if("1".equals(code)){
						return GlobalConstant.UPLOAD_FAIL+msg;
					}else{
						if(GlobalConstant.ZERO_LINE != count){
							return GlobalConstant.UPLOAD_SUCCESSED + "导入"+count+"条记录！";
						}else{
							return GlobalConstant.UPLOAD_FAIL;
						}
					}
				}else {
					return GlobalConstant.UPLOAD_FAIL;
				}
			}catch(RuntimeException re){
				re.printStackTrace();
				return re.getMessage();
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}

	/**
	 * @Department：研发部
	 * @Description 导入页面
	 * @Author ww
	 * @Date
	 */
	@RequestMapping(value = "/importRotate")
	public String importRotate(Model model) {
		return "/sch/template/importRotate";
	}

	/**
	 * @Department：研发部
	 * @Description 导入
	 * @Author ww
	 * @Date
	 */
	@RequestMapping(value = "/importRotationFromExcel")
	@ResponseBody
	public String importRotationFromExcel(MultipartFile file) {
		if (file.getSize() > 0) {
			try {
				Map map = schRotationDeptBiz.importRotationFromExcel(file);
				List<String> hintList = (List<String>) map.get("hintList");
				if (hintList.size() <= 0) {
					return GlobalConstant.UPLOAD_SUCCESSED;
				} else {
					return GlobalConstant.UPLOAD_FAIL + hintList.toString();
				}
			} catch (RuntimeException re) {
				re.printStackTrace();
				return re.getMessage();
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}
	/**
	 * @Department：研发部
	 * @Description 移除
	 * @Author ww
	 * @Date
	 */
	@RequestMapping(value = "/template/removeDept", method = {RequestMethod.POST})
	@ResponseBody
	public String removeDept(String recordFlow){
		SchRotationDept schRotationDept = rotationDeptMapper.selectByPrimaryKey(recordFlow);
		if(null != schRotationDept){
			if(StringUtil.isNotEmpty(schRotationDept.getIsRequired()) && schRotationDept.getIsRequired().equals("Y")){
				SchRotationGroup schRotationGroup = rotationGroupMapper.selectByPrimaryKey(schRotationDept.getGroupFlow());
				if(null != schRotationGroup){
					int month = Integer.parseInt(schRotationGroup.getSchMonth()) - Integer.parseInt(schRotationDept.getSchMonth());
					schRotationGroup.setSchMonth(month+"");
					rotationGroupMapper.updateByPrimaryKeySelective(schRotationGroup);
				}

			}
		}
		schRotationDept.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		rotationDeptMapper.updateByPrimaryKeySelective(schRotationDept);
		return  GlobalConstant.DELETE_SUCCESSED;
	}
}

