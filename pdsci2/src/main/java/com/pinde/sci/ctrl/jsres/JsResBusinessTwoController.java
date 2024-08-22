package com.pinde.sci.ctrl.jsres;

import com.pinde.core.entyties.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.sch.ISchManualBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.mo.JsresPowerCfg;
import com.pinde.sci.model.mo.SysOrg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/jsres/businessTwo")
public class JsResBusinessTwoController extends GeneralController {
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IJsResPowerCfgBiz jsResPowerCfgBiz;
    @Autowired
    private ISchManualBiz schManualBiz;


    /**
     * 主界面
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "jsres/businessTwo/index";
    }

    /**
     * 学员权限配置
     */
    @RequestMapping(value = "/studentChangeMain")
    public String studentChangeMain() {
        return "jsres/businessTwo/studentChangeMain";
    }

    /**
     * 学员权限配置列表
     */
    @RequestMapping(value = {"/studentChangeList"})
    public String studentChangeList(Model model, Integer currentPage, HttpServletRequest request, String orgFlow,
                                    String sessionNumber, String workOrgId, String userName, String idNo, String userCode,
                                    String trainingYears, String trainingTypeId, String datas[], String ifOpen,
                                    String[] powerTypeId, String checkStatusId, String startTime, String endTime,
                                    String doctorCategoryId) throws Exception {
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
        params.put("checkStatusId", checkStatusId);//审核状态
        params.put("startTime", startTime);//数据审核区间
        params.put("endTime", endTime);//数据审核区间
        params.put("trainingTypeId", trainingTypeId);
        if ("all".equals(doctorCategoryId)) {
            doctorCategoryId = "";
        }
        params.put("doctorCategoryId", doctorCategoryId);
        if (currentPage == null) {
            currentPage = 1;
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String, Object>> list = new ArrayList<>();
        if (StringUtil.isNotBlank(ifOpen)) {
            params.put("ifOpen", ifOpen);
            for (int i = 0; i < powerTypeId.length; i++) {
                params.put(powerTypeId[i], GlobalConstant.FLAG_Y);
            }
            list = schManualBiz.userListByJsResPower(params);
        } else {
            list = schManualBiz.userList(params);
        }
        Map<String, JsresPowerCfg> cfgMap = new HashMap<>();
        for (Map<String, Object> map : list) {
            String userFlow = (String) map.get("userFlow");
            cfgMap.put(userFlow, jsResPowerCfgBiz.read("jsres_doctor_data_time_" + userFlow));
        }
        model.addAttribute("cfgMap", cfgMap);
        model.addAttribute("list", list);
        return "jsres/businessTwo/studentChangeList";
    }


    /**
     * 带教权限配置
     */
    @RequestMapping(value = "/teachChangeMain")
    public String teachChangeMain() {
        return "jsres/businessTwo/teachChangeMain";
    }

    /**
     * 带教权限配置列表
     */
    @RequestMapping(value = {"/teachChangeList"})
    public String teachChangeList(Model model, Integer currentPage, HttpServletRequest request, String orgFlow,
                                  String deptFlow, String userName, String userCode, String checkStatusId) throws Exception {
        Map<String, Object> params = new HashMap<>();
        String roleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
        params.put("roleFlow", roleFlow);
        params.put("orgFlow", orgFlow);
        params.put("deptFlow", deptFlow);
        params.put("userName", userName);
        params.put("userCode", userCode);
        params.put("checkStatusId", checkStatusId);
        if (currentPage == null) {
            currentPage = 1;
        }
        if (StringUtil.isNotBlank(roleFlow)) {
            PageHelper.startPage(currentPage, getPageSize(request));
            List<Map<String, Object>> list = schManualBiz.teaList(params);
            model.addAttribute("list", list);
        }
        return "jsres/businessTwo/teachChangeList";
    }

    /**
     * 医院权限配置
     */
    @RequestMapping(value = "/hospitalChangeMain")
    public String hospitalChangeMain(Model model) {
        SysOrg sysOrg2 = new SysOrg();
        sysOrg2.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        sysOrg2.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        List<SysOrg> allSysOrgList = orgBiz.searchOrgs(sysOrg2, null);
        model.addAttribute("allSysOrgList", allSysOrgList);
        return "jsres/businessTwo/hospitalChangeMain";
    }

    /**
     * 医院权限配置列表
     */
    @RequestMapping(value = {"/hospitalChangeList"})
    public String hospitalChangeList(SysOrg sysOrg, Integer currentPage, HttpServletRequest request, Model model, String orgFlag) {
        sysOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        sysOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        PageHelper.startPage(currentPage, getPageSize(request));
        List<SysOrg> sysOrgList = orgBiz.searchOrgs(sysOrg, orgFlag);
        model.addAttribute("sysOrgList", sysOrgList);
        return "jsres/businessTwo/hospitalChangeList";
    }


    /**
     * 医院权限 时效设置
     */
    @RequestMapping(value = "/getOrgDate")
    public String getOrgDate(String orgFlow, Model model) {
        model.addAttribute("orgFlow", orgFlow);
        String key = "jsres_payPower_startDate_" + orgFlow;
        JsresPowerCfg cfg = jsResPowerCfgBiz.read(key);
        model.addAttribute("startDateCfg", cfg);
        key = "jsres_payPower_endDate_" + orgFlow;
        cfg = jsResPowerCfgBiz.read(key);
        model.addAttribute("endDateCfg", cfg);
        return "jsres/businessTwo/orgPower";
    }

    /**
     * 医院权限设置  显示开通过程管理的基地拥有的功能
     */
    @RequestMapping(value = "/getMenu")
    public String getMenu(String orgFlow, Model model) {
        model.addAttribute("orgFlow", orgFlow);
        return "jsres/businessTwo/menuList";
    }


    /**
     * 高校权限配置
     */
    @RequestMapping(value = "/schoolChangeMain")
    public String schoolChangeMain() {
        return "jsres/businessTwo/schoolChangeMain";
    }


    /**
     * 高校权限配置列表
     */
    @RequestMapping(value = {"/schoolChangeList"})
    public String schoolChangeList(SysDict sysDict, Integer currentPage, HttpServletRequest request, Model model) {
        if (sysDict == null) {
            sysDict = new SysDict();
        }
        sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        sysDict.setDictTypeId(DictTypeEnum.SendSchool.getId());
        sysDict.setDictTypeName(DictTypeEnum.SendSchool.getName());
        PageHelper.startPage(currentPage, getPageSize(request));
        List<SysDict> dictList = dictBiz.searchDictList(sysDict);
        model.addAttribute("dictList", dictList);
        return "jsres/businessTwo/schoolChangeList";
    }

    /**
     * 高校权限配置 功能配置页面
     */
    @RequestMapping(value = "/getSchoolMenu")
    public String getSchoolMenu(String orgFlow, Model model) {
        model.addAttribute("orgFlow", orgFlow);
        return "jsres/businessTwo/schoolmenuList";
    }
}
