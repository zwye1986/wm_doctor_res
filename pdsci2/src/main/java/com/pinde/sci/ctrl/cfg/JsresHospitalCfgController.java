package com.pinde.sci.ctrl.cfg;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sch.ISchManualBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.mo.JsresPowerCfg;
import com.pinde.sci.model.mo.ResJointOrg;
import com.pinde.sci.model.mo.SysOrg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/jsres/hospitalCfg")
public class JsresHospitalCfgController extends GeneralController{
	
	private static Logger logger = LoggerFactory.getLogger(JsresHospitalCfgController.class);

	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IJsResPowerCfgBiz jsResPowerCfgBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
    @Autowired
    private ISchManualBiz schManualBiz;

	@RequestMapping(value = {"/main" }, method = RequestMethod.GET)
	public String main (Model model){
		SysOrg sysOrg = new SysOrg();
		sysOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		sysOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		List<SysOrg> allSysOrgList = orgBiz.searchOrgs(sysOrg,null);
		model.addAttribute("allSysOrgList", allSysOrgList);
		return "jsres/cfg/hospitalCfg/main";
	}
	@RequestMapping(value = {"/hospitalList" })
	public String hospitalList (SysOrg sysOrg, Integer currentPage, HttpServletRequest request, Model model, String orgFlag){
		sysOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		sysOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<SysOrg> sysOrgList = orgBiz.searchOrgs(sysOrg,orgFlag);
		model.addAttribute("sysOrgList", sysOrgList);
		return "jsres/cfg/hospitalCfg/hospitalList";
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public String save(String orgFlow,HttpServletRequest request){
		String [] cfgCodes =request.getParameterValues("cfgCode");
		if(cfgCodes!=null){
			List<JsresPowerCfg> cfgList = new ArrayList<JsresPowerCfg>();
			for(String cfgCode : cfgCodes){
				String sysCfgValue = request.getParameter(cfgCode);
				String sysCfgDesc = request.getParameter(cfgCode+"_desc");
				JsresPowerCfg cfg = new JsresPowerCfg();
				cfg.setCfgCode(cfgCode);
				cfg.setCfgValue(sysCfgValue);
				cfg.setCfgDesc(sysCfgDesc);

				cfg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				cfgList.add(cfg);
			}
			int count = jsResPowerCfgBiz.saveList(cfgList);
			if(count > 0){
				Map<String,Object> map = new HashMap<>();
				map.put("orgFlow",orgFlow);
				JsresPowerCfg jpc = jsResPowerCfgBiz.read("jsres_"+orgFlow+"_guocheng");
				if(null != jpc && GlobalConstant.FLAG_N.equals(jpc.getCfgValue())){
					jpc = jsResPowerCfgBiz.read("jsres_"+orgFlow+"_daoru");
				}
				if(null != jpc && GlobalConstant.FLAG_N.equals(jpc.getCfgValue())){
					jpc = jsResPowerCfgBiz.read("jsres_"+orgFlow+"_downExamFile");
				}
				if(null != jpc && GlobalConstant.FLAG_N.equals(jpc.getCfgValue())){
					jpc = jsResPowerCfgBiz.read("jsres_"+orgFlow+"_createExam");
				}
				if(null != jpc && GlobalConstant.FLAG_Y.equals(jpc.getCfgValue())){
					map.put("isSubmitId","Y");
				}else{
					map.put("isSubmitId","N");
				}
				orgBiz.updateOrgSubmit(map);
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value="/getOrgDate")
	public String getOrgDate(String orgFlow,Model model) {
		model.addAttribute("orgFlow", orgFlow);
		String key = "jsres_payPower_startDate_"+orgFlow;
		JsresPowerCfg cfg = jsResPowerCfgBiz.read(key);
		model.addAttribute("startDateCfg", cfg);
		key = "jsres_payPower_endDate_"+orgFlow;
		cfg = jsResPowerCfgBiz.read(key);
		model.addAttribute("endDateCfg", cfg);
		return "jsres/cfg/hospitalCfg/orgPower";
	}

	/**
	 * 显示开通过程管理的基地拥有的功能
	 * @param orgFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getMenu")
	public String getMenu(String orgFlow,Model model) {
		model.addAttribute("orgFlow", orgFlow);
		return "jsres/cfg/hospitalCfg/menuList";
	}

	@RequestMapping(value="/updateOrgSubmit",method= RequestMethod.POST)
	@ResponseBody
	public String updateOrgSubmit(String[] orgFlows){
		if(null != orgFlows && orgFlows.length>0){
			List<String> orgFlowList = Arrays.asList(orgFlows);
			int count = orgBiz.saveOrgSubmit(orgFlowList);
			if(count > 0) {
				return GlobalConstant.OPRE_SUCCESSED;
			}
		}
		return GlobalConstant.OPRE_FAIL;
	}

	@RequestMapping(value = {"/autoMain" }, method = RequestMethod.GET)
	public String autoMain (Model model) throws Exception{
		SysOrg sysOrg = new SysOrg();
		sysOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		sysOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		List<SysOrg> allSysOrgList = orgBiz.searchOrgs(sysOrg,null);
		model.addAttribute("allSysOrgList", allSysOrgList);
		return "jsres/cfg/hospitalCfg/autoMain";
	}

	@RequestMapping(value = {"/autoList" })
	public String autoList (SysOrg sysOrg, Integer currentPage, HttpServletRequest request, Model model, String orgFlag){
		sysOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		sysOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<SysOrg> sysOrgList = orgBiz.searchOrgs(sysOrg,orgFlag);
		model.addAttribute("sysOrgList", sysOrgList);
		return "jsres/cfg/hospitalCfg/autoList";
	}

	@RequestMapping(value="/saveOne",method= RequestMethod.POST)
	@ResponseBody
	public String saveOne(String orgFlow,String cfgCode,HttpServletRequest request){
		String [] cfgs = cfgCode.split(orgFlow);
		String cfgValue = request.getParameter(cfgCode);
		String cfgDesc = request.getParameter(cfgCode+"_desc");
		JsresPowerCfg cfg = new JsresPowerCfg();
		cfg.setCfgCode(cfgCode);
		cfg.setCfgValue(cfgValue);
		cfg.setCfgDesc(cfgDesc);
		cfg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		// 状态
		cfg.setCheckStatusId("Passed");
		cfg.setCheckStatusName("审核通过");

		int result = jsResPowerCfgBiz.save(cfg);
		if(GlobalConstant.ZERO_LINE != result){
			//判断基地是否为协同基地
			String isJointOrg = "N";
			List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(orgFlow);
			if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){//是协同基地
				isJointOrg = "Y";
			}
			List<String> doctorFlowList = resDoctorBiz.searchRecruitListByOrgFlow(orgFlow,isJointOrg);
			Map<String,String> param = new HashMap<>();
			if("jsres_org_app_menu_".equals(cfgs[0])){
				param.put("cfg","jsres_doctor_app_menu_");
				param.put("cfgDesc","是否开放学员付费菜单");
				param.put("cfgValue",cfgValue);
			}else if("jsres_org_exam_".equals(cfgs[0])){
				param.put("cfg","jsres_doctor_exam_");
				param.put("cfgDesc","是否开放学员出科考核表");
				param.put("cfgValue",cfgValue);
			}else if("jsres_org_manual_".equals(cfgs[0])){
				param.put("cfg","jsres_doctor_manual_");
				param.put("cfgDesc","是否开放学员手册");
				param.put("cfgValue",cfgValue);
			}else if("jsres_org_graduation_exam_".equals(cfgs[0])){
				param.put("cfg","jsres_doctor_graduation_exam_");
				param.put("cfgDesc","是否开放学员结业理论模拟考核");
				param.put("cfgValue",cfgValue);
			}else if("jsres_org_activity_".equals(cfgs[0])){
				param.put("cfg","jsres_doctor_activity_");
				param.put("cfgDesc","是否开放学员教学活动功能");
				param.put("cfgValue",cfgValue);
			}else if("jsres_org_attendance_".equals(cfgs[0])){
				param.put("cfg","jsres_doctor_attendance_");
				param.put("cfgDesc","是否开放学员考勤功能");
				param.put("cfgValue",cfgValue);
			}
			jsResPowerCfgBiz.saveCfgDoctorFlows(param,doctorFlowList);
			return GlobalConstant.SAVE_SUCCESSED;
		}

		return GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value = "/saveNew", method = RequestMethod.POST)
	@ResponseBody
	public String saveNew(String[] orgFlows, String recordStatus,String cfg) {
		int count = 0;
		for (String orgFlow : orgFlows) {
			String cfgCode = cfg + orgFlow;
			JsresPowerCfg jsresCfg = new JsresPowerCfg();
			jsresCfg.setCfgCode(cfgCode);
			jsresCfg.setCfgValue(recordStatus);
			if("jsres_org_app_menu_".equals(cfg)){
				jsresCfg.setCfgDesc("是否开放基地付费菜单");
			}else if("jsres_org_exam_".equals(cfg)){
				jsresCfg.setCfgDesc("是否开放基地出科考核表");
			}else if("jsres_org_manual_".equals(cfg)){
				jsresCfg.setCfgDesc("是否开放基地手册");
			}else if("jsres_org_graduation_exam_".equals(cfg)){
				jsresCfg.setCfgDesc("是否开放基地结业理论模拟考核");
			}else if("jsres_org_activity_".equals(cfg)){
				jsresCfg.setCfgDesc("是否开放基地教学活动功能");
			}else if("jsres_org_attendance_".equals(cfg)){
				jsresCfg.setCfgDesc("是否开放基地考勤功能");
			}
			jsresCfg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			int result = jsResPowerCfgBiz.save(jsresCfg);

			if(GlobalConstant.ZERO_LINE != result) {
				//判断基地是否为协同基地
				String isJointOrg = "N";
				List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(orgFlow);
				if (!tempJoinOrgs.isEmpty() && tempJoinOrgs.size() > 0) {//是协同基地
					isJointOrg = "Y";
				}
				List<String> doctorFlowList = resDoctorBiz.searchRecruitListByOrgFlow(orgFlow, isJointOrg);
				Map<String, String> param = new HashMap<>();
				if ("jsres_org_app_menu_".equals(cfg)) {
					param.put("cfg", "jsres_doctor_app_menu_");
					param.put("cfgDesc", "是否开放学员付费菜单");
					param.put("cfgValue", recordStatus);
				} else if ("jsres_org_exam_".equals(cfg)) {
					param.put("cfg", "jsres_doctor_exam_");
					param.put("cfgDesc", "是否开放学员出科考核表");
					param.put("cfgValue", recordStatus);
				} else if ("jsres_org_manual_".equals(cfg)) {
					param.put("cfg", "jsres_doctor_manual_");
					param.put("cfgDesc", "是否开放学员手册");
					param.put("cfgValue", recordStatus);
				} else if ("jsres_org_graduation_exam_".equals(cfg)) {
					param.put("cfg", "jsres_doctor_graduation_exam_");
					param.put("cfgDesc", "是否开放学员结业理论模拟考核");
					param.put("cfgValue", recordStatus);
				} else if ("jsres_org_activity_".equals(cfg)) {
					param.put("cfg", "jsres_doctor_activity_");
					param.put("cfgDesc", "是否开放学员教学活动功能");
					param.put("cfgValue", recordStatus);
				} else if ("jsres_org_attendance_".equals(cfg)) {
					param.put("cfg", "jsres_doctor_attendance_");
					param.put("cfgDesc", "是否开放学员考勤功能");
					param.put("cfgValue", recordStatus);
				}
				jsResPowerCfgBiz.saveCfgDoctorFlows(param, doctorFlowList);
				count++;
			}
		}
		if(GlobalConstant.ZERO_LINE < count){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

    @RequestMapping(value = {"/statisticsMain" }, method = RequestMethod.GET)
    public String statisticsMain (Model model){
        SysOrg sysOrg = new SysOrg();
        sysOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        sysOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        List<SysOrg> allSysOrgList = orgBiz.searchOrgs(sysOrg,null);
        model.addAttribute("allSysOrgList", allSysOrgList);
        return "jsres/cfg/hospitalCfg/statisticsMain";
    }

    @RequestMapping(value = {"/statisticsList" })
    public String statisticsList (String orgFlow, Integer currentPage, HttpServletRequest request, Model model, String sessionNumber,
                                  String datas[], String trainingTypeId){
        List<String> docTypeList=new ArrayList<>();
        if(datas!=null&&datas.length>0) {
            docTypeList.addAll(Arrays.asList(datas));
        }
        Map<String,Object> params = new HashMap<>();
        params.put("orgFlow",orgFlow);
        params.put("sessionNumber",sessionNumber);
        params.put("trainingTypeId",trainingTypeId);
        params.put("docTypeList",docTypeList);

        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String,Object>> list = schManualBiz.searchStaticsList(params);
        model.addAttribute("list", list);
        return "jsres/cfg/hospitalCfg/statisticsList";
    }

    @RequestMapping("/exportStatistics")
    @ResponseBody
    public void exportStatistics(String orgFlow, HttpServletRequest request, String sessionNumber,
                             String datas[], String trainingTypeId, HttpServletResponse response) throws Exception{
        List<String> docTypeList=new ArrayList<>();
        if(datas!=null&&datas.length>0) {
            docTypeList.addAll(Arrays.asList(datas));
        }
        Map<String,Object> params = new HashMap<>();
        params.put("orgFlow",orgFlow);
        params.put("sessionNumber",sessionNumber);
        params.put("trainingTypeId",trainingTypeId);
        params.put("docTypeList",docTypeList);
        List<Map<String,Object>> list = schManualBiz.searchStaticsList(params);
        String[] titles = new String[]{
                "orgName:基地名称",
                "zsCount:应签人数",
                "guochengCount:过程管理开通人数",
                "ckkCount:出科考试开通人数"
        };
        String fileName = "学员权限统计.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjsAllString(titles, list, response.getOutputStream());
    }

}

