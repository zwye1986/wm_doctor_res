package com.pinde.sci.ctrl.res;

import com.pinde.core.common.enums.RecDocCategoryEnum;
import com.pinde.core.common.enums.ResDoctorStatusEnum;
import com.pinde.core.common.enums.sch.SchStatusEnum;
import com.pinde.core.model.SysDept;
import com.pinde.core.model.SysDict;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.ISchDoctorAbsenceBiz;
import com.pinde.sci.biz.sch.ISchRotationBiz;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.inx.InxInfoExtMapper;
import com.pinde.sci.form.sch.DoctorSearchForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;
@Controller
@RequestMapping("/res/platform")
public class ResPlatformController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResPlatformController.class);
	
	@Autowired
	private ISchRotationBiz schRotationtBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IResDoctorOrgHistoryBiz orgHistoryBiz;
	@Autowired
	private ISchDoctorAbsenceBiz absenceBiz;
	@Autowired
	private IResJointOrgBiz jointBiz;
	@Autowired
	private IResScoreBiz resScoreBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private ICfgBiz cfgBiz;
	@Autowired
	private IResSignInBiz signInBiz;
	@Autowired
	private InxInfoExtMapper inxInfoExtMapper;


	/**
	 *  轮转信息列表
	 * */
	@RequestMapping(value="/rotation",method=RequestMethod.GET)
	public String rotation(Model model){
        model.addAttribute("roleFlag", com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL);
		
		return "redirect:/sch/template/list";
	}
	
	/**
	 *  医院维护
	 * */
	@RequestMapping(value="/hospitalList",method={RequestMethod.GET,RequestMethod.POST})
	public String hospitalList(SysOrg sysOrg,Integer currentPage,HttpServletRequest request,Model model){
		if(currentPage==null){
			currentPage=1;
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<SysOrg> orgList = orgBiz.searchOrderBy(sysOrg);
		if(orgList!=null && orgList.size()>0){
			model.addAttribute("orgList",orgList);
			List<String> orgFlows = new ArrayList<String>();
			for(SysOrg org : orgList){
				orgFlows.add(org.getOrgFlow());
			}
			String roleFlow = InitConfig.getSysCfg("res_admin_role_flow");
			List<SysUser> userList = userBiz.searchUserByRoleAndOrgFlows(roleFlow,orgFlows);
			if(userList!=null && userList.size()>0){
				Map<String,SysUser> userMap = new HashMap<String, SysUser>();
				for(SysUser user : userList){
					if(userMap.get(user.getOrgFlow())==null){
						userMap.put(user.getOrgFlow(),user);
					}
				}
				model.addAttribute("userMap",userMap);
			}
		}
		//协同医院
		List<ResJointOrg> jointList = jointBiz.searchJointOrgAll();
		if(jointList!=null &&jointList.size()>0){
			Map<String,List<ResJointOrg>> jointOrgMap = new HashMap<String,List<ResJointOrg>>();
			for(ResJointOrg jointOrg : jointList){
				String key = jointOrg.getOrgFlow();
				if(jointOrgMap.get(key)==null){
					List<ResJointOrg> jointOrgList = new ArrayList<ResJointOrg>();
					jointOrgList.add(jointOrg);
					jointOrgMap.put(key,jointOrgList);
				}else{
					jointOrgMap.get(key).add(jointOrg);
				}
			}
			model.addAttribute("jointOrgMap",jointOrgMap);
		}
		
		return "/res/college/hospitalList";
	}
	
	/**
	 *  医院详情
	 * */
	@RequestMapping(value="/hospitalDetail",method=RequestMethod.GET)
	public String hospitalDetail(Model model){
		
		return "/res/college/hosInfo";
	}
	
	/**
	 *  医院详情
	 * */
	@RequestMapping(value="/internList",method=RequestMethod.GET)
	public String internList(Model model){
		
		return "/res/college/internList";
	}
	
	/**
	 *  学院信息列表
	 * */
	@RequestMapping(value="/userInfoList",method={RequestMethod.GET,RequestMethod.POST})
	public String internList(ResDoctorExt doctor,String isQuery,Integer currentPage,HttpServletRequest request,Model model){
		if(doctor!=null ){
			if(currentPage==null){
				currentPage=1;
			}
			if(StringUtil.isBlank(isQuery) && !"/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url")))
			{
				doctor.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
			}
			if("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
                model.addAttribute("cundang", com.pinde.core.common.GlobalConstant.FLAG_N);
			}
			model.addAttribute("doctorCategoryId",doctor.getDoctorCategoryId());
			SysUser user=GlobalContext.getCurrentUser();
			String medicineTypeId=user.getMedicineTypeId();
			//复选框勾选标识
			Map<String,String> doctorTypeSelectMap = new HashMap<>();
			List<String> doctorTypeIdList = doctor.getDoctorTypeIdList();
			SysDict sysDict = new SysDict();
            sysDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorType.getId());
            sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			List<SysDict> dictList = dictBiz.searchDictList(sysDict);
			if (doctorTypeIdList == null || doctorTypeIdList.size() < 1) {

			}
			if(dictList!=null&&dictList.size()>0){
				if(doctorTypeIdList!=null&&doctorTypeIdList.size()>0){
					for (SysDict dict:dictList){
						if(doctorTypeIdList.contains(dict.getDictId())){
							doctorTypeSelectMap.put(dict.getDictId(),"checked");
						}
					}
				}else {
					doctorTypeIdList = new ArrayList<>();
					for (SysDict dict:dictList){
						doctorTypeIdList.add(dict.getDictId());
						doctorTypeSelectMap.put(dict.getDictId(),"checked");
					}
					doctor.setDoctorTypeIdList(doctorTypeIdList);
				}

			}
			model.addAttribute("doctorTypeSelectMap",doctorTypeSelectMap);
            doctor.setLinkXjFlag("nfykdx".equals(InitConfig.getSysCfg("xjgl_customer")) ? com.pinde.core.common.GlobalConstant.FLAG_Y : null);
			PageHelper.startPage(currentPage,getPageSize(request));
            doctor.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			List<ResDoctorExt> doctorList = doctorBiz.searchDocUser(doctor,medicineTypeId);
			model.addAttribute("doctorList",doctorList);
		}
		if("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
			return "/res/college/userInfoList4jszy";
		}
		return "/res/college/userInfoList";
	}
	/**
	 * 学员维护导入
	 */
	@RequestMapping(value="/importStudentMainExcel")
	@ResponseBody
	public String importStudentMainExcel(MultipartFile file,String orgFlow){
		if(file.getSize() > 0){
			try{
				int result = doctorBiz.importStudentMainExcel(file,orgFlow);
                if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + result + "条记录！";
				}else{
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
				}
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}

	/**
	 * 国家医师协会导出
	 * @param doctor
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportDoc")
	public void exportDoc(ResDoctorExt doctor, HttpServletResponse response)throws Exception{
        doctor.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		SysUser user=GlobalContext.getCurrentUser();
		String medicineTypeId=user.getMedicineTypeId();
		List<ResDoctorExt> doctorList = doctorBiz.searchDocUser(doctor, medicineTypeId);

		if("Intern".equals(InitConfig.getSysCfg("res_sch_type"))){
			doctorBiz.exportForDetail2(doctorList,response);
		}else {
			doctorBiz.exportForDetail(doctorList,response);
		}
	}

	/**
	 * 国家医师协会导出2019
	 * @param doctor
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportDoc2")
	public void exportDoc2(ResDoctorExt doctor, HttpServletResponse response)throws Exception{
        doctor.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		SysUser user=GlobalContext.getCurrentUser();
		String medicineTypeId=user.getMedicineTypeId();
		List<ResDoctorExt> doctorList = doctorBiz.searchDocUser(doctor, medicineTypeId);
		doctorBiz.exportForDetail4HB2017(doctorList,response);
	}

	/**
	 *  学院计划分配信息
	 * */
	@RequestMapping(value="/trainPlan",method={RequestMethod.GET,RequestMethod.POST})
	public String trainPlan(ResDoctor doctor,Model model){
		if(doctor!=null){
			if(StringUtil.isNotBlank(doctor.getDoctorCategoryId())){
				Map<String,Object> countMap = new HashMap<String, Object>();
                List<Map<String, Object>> planCountMap = doctorBiz.searchTrainPlanCount(doctor, com.pinde.core.common.GlobalConstant.FLAG_Y);
				if(planCountMap!=null && planCountMap.size()>0){
					for(Map<String,Object> map : planCountMap){
						countMap.put(map.get("key")+"plan",map.get("value"));
					}
				}
                List<Map<String, Object>> notPlanCountMap = doctorBiz.searchTrainPlanCount(doctor, com.pinde.core.common.GlobalConstant.FLAG_N);
				if(notPlanCountMap!=null && notPlanCountMap.size()>0){
					for(Map<String,Object> map : notPlanCountMap){
						countMap.put(map.get("key")+"notPlan",map.get("value"));
					}
				}
				model.addAttribute("countMap",countMap);
				
				String sessionNumber = null;
				String doctorCategoryId = null;
				if(doctor!=null){
					sessionNumber = doctor.getSessionNumber();
					doctorCategoryId = doctor.getDoctorCategoryId();
				}
				List<SchRotation> rotationList =  schRotationtBiz.searchSchRotationForPlatform(doctorCategoryId);
				if(rotationList!=null && rotationList.size()>0){
					Map<String,List<SchRotation>> rotationMap = new HashMap<String, List<SchRotation>>();
					for(SchRotation rotation : rotationList){
						if(rotationMap.get(rotation.getSpeId())==null){
							List<SchRotation> rotationTempList = new ArrayList<SchRotation>();
							rotationTempList.add(rotation);
							rotationMap.put(rotation.getSpeId(),rotationTempList);
						}else{
							rotationMap.get(rotation.getSpeId()).add(rotation);
						}
					}
					model.addAttribute("rotationMap",rotationMap);
				}
				
				List<Map<String,String>> selRotationMapList = doctorBiz.searGroupRotation(doctor);
				if(selRotationMapList!=null && selRotationMapList.size()>0){
					Map<String,Map<String,String>> selRotationMap = new HashMap<String, Map<String,String>>();
					for(Map<String,String> map : selRotationMapList){
						Map<String,String> rotationTempMap = new HashMap<String, String>();
						rotationTempMap.put("name",map.get("name"));
						rotationTempMap.put("flow",map.get("flow"));
						selRotationMap.put(map.get("key"),rotationTempMap);
					}
					model.addAttribute("selRotationMap",selRotationMap);
				}
			}
		}
		return "/res/college/trainPlan";
	}
	
	/**
	 *  学员计划分配
	 * */
	@RequestMapping(value="/allotRotation",method={RequestMethod.POST})
	@ResponseBody
	public String allotRotation(ResDoctor doctor,Model model){
		if(doctor!=null){
            if (doctorBiz.modifyResDoctorRotation(doctor) != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
	}
	
	/**
	 *  通知公告
	 * */
	@RequestMapping(value="/notice/{roleFlag}",method={RequestMethod.POST,RequestMethod.GET})
	public String notice(@PathVariable String roleFlag,Integer currentPage ,HttpServletRequest request, Model model){
		model.addAttribute("roleFlag", roleFlag);
		if(currentPage==null){
			currentPage=1;
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<InxInfo> infos = null;
		//searchInfoByOrgBeforeDate扩展
        if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
            infos = this.noticeBiz.searchInfoByOrg(null, com.pinde.core.common.GlobalConstant.RES_NOTICE_TYPE5_ID, com.pinde.core.common.GlobalConstant.RES_NOTICE_SYS_ID);
		}else{
			SysUser user = GlobalContext.getCurrentUser();
			String userOrgFlow= user.getOrgFlow();
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("zy_hospital_zcfg"))) {
                infos = this.inxInfoExtMapper.searchInfoByOrg2(userOrgFlow, com.pinde.core.common.GlobalConstant.RES_NOTICE_SYS_ID);
			}else {
                infos = this.noticeBiz.searchInfoByOrg(userOrgFlow, com.pinde.core.common.GlobalConstant.RES_NOTICE_TYPE5_ID, com.pinde.core.common.GlobalConstant.RES_NOTICE_SYS_ID);
			}
		}
		
		model.addAttribute("infos",infos);
		return "/res/notice/notice";
	}	
	
	@ResponseBody
	@RequestMapping("/saveNotice/{roleFlag}")
	public String saveNotice(@PathVariable String roleFlag,InxInfo info,String infoRoleFlows,String sessionNumber){
        if (!com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
			SysUser user = GlobalContext.getCurrentUser();
			String userOrgFlow= user.getOrgFlow();
			String userOrgName=user.getOrgName();
			info.setOrgFlow(userOrgFlow);
			info.setOrgName(userOrgName);
		}
        info.setRoleFlow(com.pinde.core.common.GlobalConstant.RES_NOTICE_SYS_ID);
		noticeBiz.editInfo(info,infoRoleFlows,sessionNumber);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping("/findNoticeByFlow")
	@ResponseBody
	public Object findNoticeByFlow(String infoFlow){
		Map<String,Object> resp=new HashMap<>();
		resp.put("info",this.noticeBiz.findNoticByFlow(infoFlow));
		resp.put("infoRoles",this.noticeBiz.readRoleByFlow(infoFlow));
		return resp;
	}
	
	@RequestMapping(value="/noticeView")
	public String message(Model model,String infoFlow) throws Exception{
		SysUser user = GlobalContext.getCurrentUser();
		if(user!=null)
		{
			noticeBiz.saveReadInfo(user.getUserFlow(),infoFlow);
		}
		model.addAttribute("msg", noticeBiz.findNoticByFlow(infoFlow));
		return "/res/notice/message";
	}
	
	@ResponseBody
	@RequestMapping("/delNotice")
	public String delNotice(String infoFlow){
		this.noticeBiz.delNotice(infoFlow);
        return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
	}
	
	/**
	 *  用户编辑
	 */
	@RequestMapping(value={"/editDocSimple"},method={RequestMethod.GET})
	public String editDocSimple(String doctorFlow,Model model){
        model.addAttribute("roleFlag", com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL);
		model.addAttribute("doctorFlow",doctorFlow);
		
		return "redirect:/res/manager/editDocSimple";
	}
	
	@RequestMapping("/searchSysOrg")
    @ResponseBody
    public List<SysOrg> searchSysOrg(){
		SysOrg org = new SysOrg();
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgList = orgBiz.searchOrderBy(org);
   		return orgList;
   	}
	@RequestMapping(value={"/doctorStatistics"},method={RequestMethod.GET,RequestMethod.POST})
	public String doctorStatistics(String orgName,String sessionNumber,String speId,Model model){
		SysOrg org = new SysOrg();
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		org.setOrgName(orgName);
		List<SysOrg> orgList = orgBiz.searchOrderBy(org);
		if(orgList!=null && orgList.size()>0){
			model.addAttribute("orgList",orgList);
			
			ResDoctor doctor = new ResDoctor();
			doctor.setSessionNumber(sessionNumber);
			doctor.setTrainingSpeId(speId);
			
			List<Map<String,Object>> countResultMapList = doctorBiz.countDocByOrg(orgName,doctor);
			Map<String,Object> allCountMap = parseCountMapList(countResultMapList);
			model.addAttribute("allCountMap",allCountMap);
			
			doctor.setDoctorStatusId(ResDoctorStatusEnum.Training.getId());
			List<Map<String,Object>> countTrainingResultMapList = doctorBiz.countDocByOrg(orgName,doctor);
			Map<String,Object> countTrainingResultMap = parseCountMapList(countTrainingResultMapList);
			model.addAttribute("countTrainingResultMap",countTrainingResultMap);
			
			doctor.setDoctorStatusId(ResDoctorStatusEnum.Graduation.getId());
			List<Map<String,Object>> countGraduationResultMapList = doctorBiz.countDocByOrg(orgName,doctor);
			Map<String,Object> countGraduationResultMap = parseCountMapList(countGraduationResultMapList);
			model.addAttribute("countGraduationResultMap",countGraduationResultMap);
		}
		return "/res/college/doctorStatistics";
	}
	@RequestMapping(value={"/baseStatistics"},method={RequestMethod.GET,RequestMethod.POST})
	public String baseStatistics(String orgName,String sessionNumber,String speId,Model model){
		SysOrg org = new SysOrg();
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		org.setOrgName(orgName);
		List<SysOrg> orgList = orgBiz.searchOrderBy(org);
		if(orgList!=null && orgList.size()>0){
			model.addAttribute("orgList",orgList);
			ResDoctor doctor = new ResDoctor();
			doctor.setSessionNumber(sessionNumber);
			doctor.setTrainingSpeId(speId);
			
			List<Map<String,Object>> countResultMapList = doctorBiz.countDocByOrg(orgName,doctor);
			Map<String,Object> allCountMap = parseCountMapList(countResultMapList);
			model.addAttribute("allCountMap",allCountMap);
			
			doctor.setDoctorStatusId(ResDoctorStatusEnum.Training.getId());
			List<Map<String,Object>> countTrainingResultMapList = doctorBiz.countDocByOrg(orgName,doctor);
			Map<String,Object> countTrainingResultMap = parseCountMapList(countTrainingResultMapList);
			model.addAttribute("countTrainingResultMap",countTrainingResultMap);
			
			doctor.setDoctorStatusId(ResDoctorStatusEnum.Graduation.getId());
			List<Map<String,Object>> countGraduationResultMapList = doctorBiz.countDocByOrg(orgName,doctor);
			Map<String,Object> countGraduationResultMap = parseCountMapList(countGraduationResultMapList);
			model.addAttribute("countGraduationResultMap",countGraduationResultMap);
		}
		return "/res/college/baseStatistics";
	}
	
	private Map<String,Object> parseCountMapList(List<Map<String,Object>> countResultMapList){
		Map<String,Object> countResultMap = null;
		if(countResultMapList!=null && countResultMapList.size()>0){
			countResultMap = new HashMap<String, Object>();
			for(Map<String,Object> map : countResultMapList){
				countResultMap.put((String)map.get("key"),map.get("value"));
			}
		}
		return countResultMap;
	}
	
	/**
	 *  学员分配
	 */
	@RequestMapping(value={"/doctorAllot"})
	public String doctorAllot(Model model,Integer currentPage,SysOrg sysOrg,HttpServletRequest request){
		SysUser user = GlobalContext.getCurrentUser();//当前用户
		String orgFlow = user.getOrgFlow();
		if(currentPage==null){
			currentPage=1;
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<SysOrg> sysOrgList=orgBiz.searchByOrgNotSelf(orgFlow,sysOrg);
		model.addAttribute("sysOrgList", sysOrgList);
		List<SysDept> deptList=deptBiz.searchDeptByOrg(orgFlow);
		model.addAttribute("deptList", deptList);
		return "/res/college/doctorAllot";
	}
	
	/**
	 * 变更报表
	 * @param orgHistory
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/turnSearch"},method={RequestMethod.GET,RequestMethod.POST})
	public String turnSearch(ResDoctorOrgHistory orgHistory,Model model){
		orgHistory.setChangeStatusId(SchStatusEnum.AuditY.getId());
		List<ResDoctorOrgHistory> orgHistoryList = orgHistoryBiz.searchIsInDocByDocOrgHis(orgHistory);
		if(orgHistoryList!=null && orgHistoryList.size()>0){
			model.addAttribute("orgHistoryList",orgHistoryList);
			List<String> userFlows = new ArrayList<String>();
			for(ResDoctorOrgHistory history : orgHistoryList){
				String userFlow = history.getDoctorFlow();
				if(!userFlows.contains(userFlow)){
					userFlows.add(userFlow);
				}
			}
			
			List<SysUser> userList = userBiz.searchSysUserByuserFlows(userFlows);
			if(userList!=null && userList.size()>0){
				Map<String,SysUser> userMap = new HashMap<String, SysUser>();
				for(SysUser user : userList){
					userMap.put(user.getUserFlow(),user);
				}
				model.addAttribute("userMap",userMap);
			}
			
			List<ResDoctor> doctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
			if(doctorList!=null && doctorList.size()>0){
				Map<String,ResDoctor> doctorMap = new HashMap<String, ResDoctor>();
				for(ResDoctor doctor : doctorList){
					doctorMap.put(doctor.getDoctorFlow(),doctor);
				}
				model.addAttribute("doctorMap",doctorMap);
			}
		}
		return "/res/college/turnSearch";
	}
	
	
	@RequestMapping(value={"/absenceAudit"},method={RequestMethod.GET,RequestMethod.POST})
	public String absenceAudit(SchDoctorAbsence absence,Integer currentPage,HttpServletRequest request,Model model){
		if(currentPage==null){
			currentPage=1;
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		
		List<SchDoctorAbsence> absenceList = absenceBiz.searchSchDoctorAbsenceList(absence);
		model.addAttribute("absenceList",absenceList);
		return "/res/college/absenceAudit";
	}
	
	/**
	 * ResDoctor和sysUser(扩展在ResDoctorExt)查询
	 * @param model
	 * @param resDoctorExt
	 * @param currentPage
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/stuManaList/{roleFlag}")
	public String stuManaList(@PathVariable String roleFlag,Model model,ResDoctorExt resDoctorExt,Integer currentPage,HttpServletRequest request){
		model.addAttribute("roleFlag", roleFlag);
		if(currentPage==null){
			currentPage=1;
		}
		if(StringUtil.isBlank(resDoctorExt.getDoctorCategoryId())){
			resDoctorExt.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
		}
		if("all".equals(resDoctorExt.getDoctorCategoryId())){
			resDoctorExt.setDoctorCategoryId("");
		}
		model.addAttribute("doctorCategoryId",resDoctorExt.getDoctorCategoryId());
		PageHelper.startPage(currentPage,getPageSize(request));
		List<ResDoctorExt> resDocExtList=null;
        if (!com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
			SysUser user=GlobalContext.getCurrentUser();
			resDoctorExt.setOrgFlow(user.getOrgFlow());
			resDocExtList=doctorBiz.searchDocUser(resDoctorExt, "");
		}else{
			SysUser user=GlobalContext.getCurrentUser();
			String medicineTypeId=user.getMedicineTypeId();
			resDocExtList=doctorBiz.searchDocUser(resDoctorExt, medicineTypeId);
		}
		Map<String,ResScore> map=new HashMap<String,ResScore>();
		for (ResDoctorExt doctorExt : resDocExtList) {
			List<ResScore> resScoreList=resScoreBiz.searchByScoreList(doctorExt.getSysUser().getUserFlow());
			for (ResScore resScore : resScoreList) {
				String key="";
				if (StringUtil.isNotBlank(resScore.getScorePhaseId())) {
					key=doctorExt.getSysUser().getUserFlow()+resScore.getScorePhaseId();
				}else{
					key=doctorExt.getSysUser().getUserFlow()+resScore.getScoreTypeId();
				}
				map.put(key, resScore);
			}
		}
		model.addAttribute("map",map);
		model.addAttribute("resDocExtList", resDocExtList);
		return "/res/college/stuManaList";
	} 
	
	@RequestMapping(value="/basicInfo")
	public String basicInfo(String doctorFlow,Model model){
		List<ResScore> resScoreList=resScoreBiz.searchByScoreList(doctorFlow);
		Map<String,Object> map=new HashMap<String, Object>();
		for (ResScore resScore : resScoreList) {
			String key="";
			if (StringUtil.isNotBlank(resScore.getScorePhaseId())) {
				key=resScore.getScoreTypeId()+resScore.getScorePhaseId();
			}else{
				key=resScore.getScoreTypeId();
			}
			map.put(key, resScore);
		}
		model.addAttribute("map",map);
		/*ResDoctor doctor = doctorBiz.findByFlow(doctorFlow);*/
		/*ResScore resScore=resScoreBiz.searchByScoreFlow(scoreFlow)doctorFlow*/
		return "res/college/stuManaZi";
	}
	@RequestMapping(value="/opstBasicInfo")
	public String opstBasicInfo(String doctorFlow,Model model){
		ResDoctor doctor=doctorBiz.readDoctor(doctorFlow);
		model.addAttribute("doctor",doctor);
		return "res/college/basicInfoZi";
	}
	/**
	 * jointRead页面保存
	 * @param orgFlow
	 * @param jointOrgFlows
	 * @param delJointFlows
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(String orgFlow,String[] jointOrgFlows,String[] delJointFlows){
		List<ResJointOrg> jointOrgList = new ArrayList<ResJointOrg>();
		//勾上复选框保存
		if(jointOrgFlows!=null && jointOrgFlows.length>0){
			List<String> orgFlows = new ArrayList<String>();
			orgFlows.add(orgFlow);
			List<String> jointOrgFlowList = Arrays.asList(jointOrgFlows);
			orgFlows.addAll(jointOrgFlowList);
			List<SysOrg> orgList=orgBiz.searchOrgFlowIn(orgFlows);
			if(orgList!=null && orgList.size()>0){
				Map<String,SysOrg> orgMap = new HashMap<String,SysOrg>();
				for(SysOrg org : orgList){
					orgMap.put(org.getOrgFlow(),org);
				}
				for(String jointOrgFlow : jointOrgFlows){
					ResJointOrg jointOrg = new ResJointOrg();
					jointOrg.setOrgFlow(orgFlow);
					jointOrg.setOrgName(orgMap.get(orgFlow).getOrgName());
					jointOrg.setJointOrgFlow(jointOrgFlow);
					jointOrg.setJointOrgName(orgMap.get(jointOrgFlow).getOrgName());
					jointOrgList.add(jointOrg);
				}
			}
		}
		//去掉复选框保存
		if(delJointFlows!=null && delJointFlows.length>0){
			for(String jointFlow : delJointFlows){
				ResJointOrg jointOrg = new ResJointOrg();
				jointOrg.setJointFlow(jointFlow);
                jointOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
				jointOrgList.add(jointOrg);
			}
		}
		int result=jointBiz.editJointOrgList(jointOrgList);
        if (result != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}
	/**
	 * 
	 * @param flow
	 * @param model
	 * @return
	 */
	@RequestMapping("/jointAll")
	public String jointAll(String flow,String currentPage,Model model){
		if(StringUtil.isNotBlank(flow)){
			SysOrg org=orgBiz.readSysOrg(flow) ;
			model.addAttribute("org", org);
			//查询
			List<SysOrg> resultList = orgBiz.searchOrgNotSelf(flow);
			model.addAttribute("resultList", resultList);
			//jointRead页面checkbox框保存之后默认选中
			List<ResJointOrg> jointList=jointBiz.searchResJointByOrgFlow(flow);
			if(jointList!=null && jointList.size()>0){
				Map<String,ResJointOrg> jointMap = new HashMap<String, ResJointOrg>();
				for(ResJointOrg joint : jointList){
					jointMap.put(joint.getJointOrgFlow(),joint);
				}
				model.addAttribute("jointMap",jointMap);
			}
		}
		model.addAttribute("currentPage",currentPage);
		return "/res/college/jointRead";
	}

	/**
	 *
	 * @param flow
	 * @param model
	 * @return
	 */
	@RequestMapping("/jointAll4sczy")
	public String jointAll4sczy(String flow,String currentPage,Model model){
		if(StringUtil.isNotBlank(flow)){
			SysOrg org=orgBiz.readSysOrg(flow) ;
			model.addAttribute("org", org);
			//查询
			List<SysOrg> resultList = orgBiz.searchAllJointOrg4sczy(flow);
			model.addAttribute("resultList", resultList);
			//jointRead页面checkbox框保存之后默认选中
			List<ResJointOrg> jointList=jointBiz.searchResJointByOrgFlow(flow);
			if(jointList!=null && jointList.size()>0){
				Map<String,ResJointOrg> jointMap = new HashMap<String, ResJointOrg>();
				for(ResJointOrg joint : jointList){
					jointMap.put(joint.getJointOrgFlow(),joint);
				}
				model.addAttribute("jointMap",jointMap);
			}
		}
		model.addAttribute("currentPage",currentPage);
		return "/sczyres/manage/jointRead";
	}
	@RequestMapping("/exportExcel")
	public void exportExcel(String orgName,String sessionNumber,String speId,DoctorSearchForm doctorSearchForm,HttpServletResponse response) throws Exception{
		SysOrg org = new SysOrg();
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		org.setOrgName(orgName);
		List<SysOrg> orgList = orgBiz.searchOrderBy(org);
        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId());
		
		ResDoctor doctor = new ResDoctor();
		doctor.setSessionNumber(sessionNumber);
		doctor.setTrainingSpeId(speId);
		
		List<Map<String,Object>> countResultMapList = doctorBiz.countDocByOrg(orgName,doctor);
		Map<String,Object> allCountMap = parseCountMapList(countResultMapList);
		
		doctor.setDoctorStatusId(ResDoctorStatusEnum.Training.getId());
		List<Map<String,Object>> countTrainingResultMapList = doctorBiz.countDocByOrg(orgName,doctor);
		Map<String,Object> countTrainingResultMap = parseCountMapList(countTrainingResultMapList);
		
		doctor.setDoctorStatusId(ResDoctorStatusEnum.Graduation.getId());
		List<Map<String,Object>> countGraduationResultMapList = doctorBiz.countDocByOrg(orgName,doctor);
		Map<String,Object> countGraduationResultMap = parseCountMapList(countGraduationResultMapList);
		
		
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
	    Map<Integer,Integer> colWidthAuto = new HashMap<Integer, Integer>();
	    
	    if(orgList!=null && orgList.size()>0){
	    	int rownum = 0;
	    	//合并单元格
	    	sheet.addMergedRegion(new CellRangeAddress(0,rownum+1,0,0));
	    	//创建第一行
	    	HSSFRow rowDep = sheet.createRow(0);
	    	//创建第二行
	    	HSSFRow rowDept = sheet.createRow(1);
	    	
    		HSSFCell cel = rowDep.createCell(0);
    		cel.setCellValue("专业");
    		cel.setCellStyle(styleLeft);
    		int colnum = 1;
    		int c = 3;
    		int s = 0;
    		int jielie=0;
    		for(SysOrg so : orgList){
    			jielie=c;
				HSSFCell orgCell = rowDep.createCell(colnum);
				orgCell.setCellValue(so.getOrgName());
				orgCell.setCellStyle(styleCenter);
				
				HSSFCell cCell = rowDep.createCell(colnum+1);
				cCell.setCellValue("");
				
				HSSFCell sCell = rowDep.createCell(colnum+2);
				sCell.setCellValue("");
				
				HSSFCell zCell = rowDept.createCell(s+1);
				zCell.setCellValue("招录");
				zCell.setCellStyle(styleCenter);
				
				HSSFCell pCell = rowDept.createCell(s+2);
				pCell.setCellValue("在培");
				pCell.setCellStyle(styleCenter);
				
				HSSFCell jCell = rowDept.createCell(s+3);
				jCell.setCellValue("结业");
				jCell.setCellStyle(styleCenter);
				sheet.addMergedRegion(new CellRangeAddress(0,0,s+1,c));
				colnum=colnum+3;
				c=c+3;
				s=s+3;
    		}
    		int rownums = 2;
    		int jallCount=0;
    		int jcountTrainingResult=0;
    		int jcountGraduationResult=0;
	    	for(SysDict sd : sysDictList){
	    		HSSFRow rowDepts= sheet.createRow(rownums);
	    		HSSFCell cell = rowDepts.createCell(0);
	    		cell.setCellValue(sd.getDictName());
	    		cell.setCellStyle(styleLeft);
	    		int lie = 1;
	    		for(SysOrg so : orgList){
		    		String key = so.getOrgFlow()+sd.getDictId();
		    		Object allCount=null;
		    		Object countTrainingResult=null;
		    		Object countGraduationResult=null;
		    		if (allCountMap!=null) {
		    			allCount= allCountMap.get(key);
					}
		    		if (countTrainingResultMap!=null) {
		    			 countTrainingResult=countTrainingResultMap.get(key);
		    		}
		    		if (countGraduationResultMap!=null) {
		    			countGraduationResult=countGraduationResultMap.get(key);
		    		}
		    		HSSFCell zCell = rowDepts.createCell(lie);
		    		if (allCount!=null) {
		    			int a=Integer.parseInt(String.valueOf(allCount));
		    			jallCount=jallCount+a;
		    			zCell.setCellValue(allCount.toString());
					}else{
						zCell.setCellValue("0");
					}
		    		zCell.setCellStyle(styleCenter);
					
					HSSFCell pCell = rowDepts.createCell(lie+1);
					if (countTrainingResult!=null) {
						int a=Integer.parseInt(String.valueOf(countTrainingResult));
						jcountTrainingResult=jcountTrainingResult+a;
						pCell.setCellValue(countTrainingResult.toString());
					}else{
						pCell.setCellValue("0");
					}
					pCell.setCellStyle(styleCenter);
					
					HSSFCell jCell = rowDepts.createCell(lie+2);
					if (countGraduationResult!=null) {
						int a=Integer.parseInt(String.valueOf(countGraduationResult));
						jcountGraduationResult=jcountGraduationResult+a;
						jCell.setCellValue(countGraduationResult.toString());
					}else{
						jCell.setCellValue("0");
					}
					jCell.setCellStyle(styleCenter);
		    		lie=lie+3;
		    	}
	    		HSSFCell jieCell = rowDepts.createCell(jielie+1);
				jieCell.setCellValue(jallCount);
				jieCell.setCellStyle(styleCenter);
				HSSFCell jiez = rowDepts.createCell(jielie+2);
				jiez.setCellValue(jcountTrainingResult);
				jiez.setCellStyle(styleCenter);
				HSSFCell jiej = rowDepts.createCell(jielie+3);
				jiej.setCellValue(jcountGraduationResult);
				jiej.setCellStyle(styleCenter);
				jallCount=0;
	    		jcountTrainingResult=0;
	    		jcountGraduationResult=0;
	    		rownums++;
	    	}
	    	//汇总统计
	    	HSSFCell orgCell = rowDep.createCell(jielie+1);
			orgCell.setCellValue("汇总统计");
			orgCell.setCellStyle(styleCenter);
			
			HSSFCell cCell = rowDep.createCell(jielie+2);
			cCell.setCellValue("");
			
			HSSFCell sCell = rowDep.createCell(jielie+3);
			sCell.setCellValue("");
			
			HSSFCell zCell = rowDept.createCell(jielie+1);
			zCell.setCellValue("招录");
			zCell.setCellStyle(styleCenter);
			
			HSSFCell pCell = rowDept.createCell(jielie+2);
			pCell.setCellValue("在培");
			pCell.setCellStyle(styleCenter);
			
			HSSFCell jCell = rowDept.createCell(jielie+3);
			jCell.setCellValue("结业");
			jCell.setCellStyle(styleCenter);
			sheet.addMergedRegion(new CellRangeAddress(0,0,jielie+1,jielie+3));
	    	}
	    String fileName = "住院医师统计表.xls";
	    fileName = URLEncoder.encode(fileName, "UTF-8");
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); 
	    response.setContentType("application/octet-stream;charset=UTF-8");
	    wb.write(response.getOutputStream());
	}
	
	
	@RequestMapping("/exportBase")
	public void exportBase(String orgName,String sessionNumber,String speId,DoctorSearchForm doctorSearchForm,HttpServletResponse response) throws Exception{
		SysOrg org = new SysOrg();
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		org.setOrgName(orgName);
		List<SysOrg> orgList = orgBiz.searchOrderBy(org);
        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId());
		ResDoctor doctor = new ResDoctor();
		doctor.setSessionNumber(sessionNumber);
		doctor.setTrainingSpeId(speId);
		
		List<Map<String,Object>> countResultMapList = doctorBiz.countDocByOrg(orgName,doctor);
		Map<String,Object> allCountMap = parseCountMapList(countResultMapList);
		
		doctor.setDoctorStatusId(ResDoctorStatusEnum.Training.getId());
		List<Map<String,Object>> countTrainingResultMapList = doctorBiz.countDocByOrg(orgName,doctor);
		Map<String,Object> countTrainingResultMap = parseCountMapList(countTrainingResultMapList);
		
		doctor.setDoctorStatusId(ResDoctorStatusEnum.Graduation.getId());
		List<Map<String,Object>> countGraduationResultMapList = doctorBiz.countDocByOrg(orgName,doctor);
		Map<String,Object> countGraduationResultMap = parseCountMapList(countGraduationResultMapList);
		
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
	    Map<Integer,Integer> colWidthAuto = new HashMap<Integer, Integer>();
	    
	    if(orgList!=null && orgList.size()>0){
	    	//合并单元格
	    	/*sheet.addMergedRegion(new CellRangeAddress(0,rownum+1,0,0));*/
	    	//创建第一行
	    	HSSFRow rowDep = sheet.createRow(0);
    		HSSFCell cel = rowDep.createCell(0);
    		cel.setCellValue("专业");
    		cel.setCellStyle(styleLeft);
    		
    		int colnum = 1;
    		int jielie=0;
    		for(SysOrg so : orgList){
    			Integer width = colWidthAuto.get(colnum);
				if(width==null){
					width = 0;
				}
    			jielie=colnum;
				HSSFCell orgCell = rowDep.createCell(colnum);
				orgCell.setCellValue(so.getOrgName());
				orgCell.setCellStyle(styleCenter);
				
				Integer dateNewWidth = so.getOrgName().getBytes().length*1*256;
				width = width<dateNewWidth?dateNewWidth:width;
				sheet.setColumnWidth(colnum,width);
				colWidthAuto.put(colnum,width);
				colnum++;
    		}
    		int rownum = 1;
	    	for(SysDict sd : sysDictList){
	    		HSSFRow rowDepts= sheet.createRow(rownum);
	    		HSSFCell cell = rowDepts.createCell(0);
	    		cell.setCellValue(sd.getDictName());
	    		cell.setCellStyle(styleLeft);
	    		int lie = 1;
	    		int orgHe=0;
	    		int jiehe=0;
	    		for(SysOrg so : orgList){
	    			sheet.setColumnWidth(0, 4500);
		    		String key = so.getOrgFlow()+sd.getDictId();
		    		Object allCount=null;
		    		Object countTrainingResult=null;
		    		Object countGraduationResult=null;
		    		if (allCountMap!=null) {
		    			allCount= allCountMap.get(key);
					}
		    		if (countTrainingResultMap!=null) {
		    			 countTrainingResult=countTrainingResultMap.get(key);
		    		}
		    		if (countGraduationResultMap!=null) {
		    			countGraduationResult=countGraduationResultMap.get(key);
		    		}
		    		HSSFCell orgCell = rowDepts.createCell(lie);
		    		if (allCount!=null) {
		    			int a=Integer.parseInt(String.valueOf(allCount));
		    			orgHe=orgHe+a;
					}
		    		if(countTrainingResult!=null){
						int b=Integer.parseInt(String.valueOf(countTrainingResult));
						orgHe=orgHe+b;
					}
		    		if(countGraduationResult!=null){
						int c=Integer.parseInt(String.valueOf(countGraduationResult));
						orgHe=orgHe+c;
					}
		    		jiehe=jiehe+orgHe;
					orgCell.setCellValue(orgHe);
		    		orgCell.setCellStyle(styleCenter);
		    		orgHe=0;
		    		lie++;
		    	}
	    		
	    		HSSFCell jieCell = rowDepts.createCell(jielie+1);
				jieCell.setCellValue(jiehe);
				jieCell.setCellStyle(styleCenter);
				jiehe=0;
				rownum++;
	    	}
	    	//汇总统计
	    	HSSFCell orgCell = rowDep.createCell(jielie+1);
			orgCell.setCellValue("汇总统计");
			orgCell.setCellStyle(styleCenter);
	    	}
	    String fileName = "规培基地统计表.xls";
	    fileName = URLEncoder.encode(fileName, "UTF-8");
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); 
	    response.setContentType("application/octet-stream;charset=UTF-8");
	    wb.write(response.getOutputStream());
	}
	
	
	/**
	 * 权限设置
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/orgPermission", method={RequestMethod.POST,RequestMethod.GET})
	public String orgPermission(SysOrg sysOrg, Integer currentPage, HttpServletRequest request, Model model,String orgFlag){
        sysOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        sysOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<SysOrg> allSysOrgList = orgBiz.searchOrgs(sysOrg,orgFlag);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<SysOrg> sysOrgList = orgBiz.searchOrgs(sysOrg,orgFlag);
		model.addAttribute("sysOrgList", sysOrgList);
		model.addAttribute("allSysOrgList", allSysOrgList);
		return "res/platform/orgPermission";
	}
	
	/**
	 * 权限设置
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/orgPermissionList", method={RequestMethod.POST,RequestMethod.GET})
	public String orgPermissionList(SysOrg sysOrg, Integer currentPage, HttpServletRequest request, Model model,String orgFlag){
        sysOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        sysOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<SysOrg> sysOrgList = orgBiz.searchOrgs(sysOrg,orgFlag);
		model.addAttribute("sysOrgList", sysOrgList);
//		if(sysOrgList != null && !sysOrgList.isEmpty()){
//			List<String> cfgCodeList = new ArrayList<String>();
//			for(SysOrg so : sysOrgList){
//				cfgCodeList.add("jswjw_"+so.getOrgFlow()+"_P001");
//				cfgCodeList.add("jswjw_"+so.getOrgFlow()+"_P002");
//			}
//			SysCfg sysCfg = new SysCfg();
//			sysCfg.setWsId(com.pinde.core.common.GlobalConstant.RES_WS_ID);
//			List<SysCfg> sysCfgList = cfgBiz.searchCfgByCfgCodeList(sysCfg, cfgCodeList);
//			if(sysCfgList != null && !sysCfgList.isEmpty()){
//				Map<String, SysCfg> sysCfgMap = new HashMap<String, SysCfg>();
//				for(SysCfg sc : sysCfgList){
//					sysCfgMap.put(sc.getCfgCode(), sc);
//				}
//				model.addAttribute("sysCfgMap", sysCfgMap);
//			}
//		}
		return "res/platform/orgPermissionList";
	}

	/**
	 * 医师签到查询
	 * @param signin
	 * @param currentPage
	 * @param request
	 * @param model
     * @return
     */
	@RequestMapping(value="/docotrSignInfo", method={RequestMethod.POST,RequestMethod.GET})
	public String docotrSignInfo(ResSignin signin, Integer currentPage, HttpServletRequest request, Model model){
		PageHelper.startPage(currentPage, getPageSize(request));
		SysUser user=GlobalContext.getCurrentUser();
		String medicineTypeId=user.getMedicineTypeId();
		List<Map<String, Object>> signInfoMaps = signInBiz.searchSignInfo(signin,medicineTypeId);
		model.addAttribute("signInfoMaps", signInfoMaps);
		return "res/platform/docotrSignInfo";
	}

	/**
	 * 派送学校权限设置
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/schoolPermission", method = {RequestMethod.POST, RequestMethod.GET})
	public String schoolPermission(SysDict sysDict, SysOrg sysOrg, Integer currentPage, HttpServletRequest request, Model model, String orgFlag) {
		if (sysDict == null) {
			sysDict = new SysDict();
		}
        sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        sysDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.SendSchool.getId());
        sysDict.setDictTypeName(com.pinde.core.common.enums.DictTypeEnum.SendSchool.getName());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<SysDict> dictList = dictBiz.searchDictList(sysDict);
		model.addAttribute("dictList", dictList);
		return "res/platform/schoolPermission";
	}

	/**
	 * 设置派送学校过程管理
	 *
	 * @return
	 */
	@RequestMapping(value = "/changeOne", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String changeOne(String sysDictId, String recordStatues, HttpServletRequest request) {
		String cfgCode = "jswjw_sendSchool_"+sysDictId+"_P007";
		SysCfg cfg=cfgBiz.read(cfgCode);
		List<SysCfg> sysCfgList = new ArrayList<SysCfg>();
		if(cfg==null){
			cfg=new SysCfg();
			cfg.setCfgCode(cfgCode);
			cfg.setCfgDesc("是否开放派送学校过程管理");
			cfg.setWsId("res");
			cfg.setWsName(InitConfig.getWorkStationName(cfg.getWsId()));
			sysCfgList.add(cfg);
			cfgBiz.saveSysCfgInfo(sysCfgList);
		}
		cfg.setCfgValue(recordStatues);
		sysCfgList.add(cfg);
		int result=cfgBiz.saveSysCfgInfo(sysCfgList);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
			ServletContext application = request.getServletContext();
			Map<String,String> sysCfgMap = (Map<String, String>) application.getAttribute("sysCfgMap");
			sysCfgMap.put(cfg.getCfgCode(), cfg.getCfgValue());
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}

	/**
	 * 学员维护导入
	 */
	@RequestMapping(value="/importStudentMainExcel4jszy")
	@ResponseBody
	public String importStudentMainExcel4jszy(MultipartFile file,String orgFlow,String role){
		if(file.getSize() > 0){
			try{
				int result = doctorBiz.importStudentMainExcel4jszy(file,orgFlow,role);
                if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + result + "条记录！";
				}else{
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
				}
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}
}