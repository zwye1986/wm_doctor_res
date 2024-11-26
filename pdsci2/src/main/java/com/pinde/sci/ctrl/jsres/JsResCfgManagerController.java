package com.pinde.sci.ctrl.jsres;

import com.pinde.core.util.DateUtil;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.ResOrgCkxzMapper;
import com.pinde.sci.keyUtil.PdUtil;
import com.pinde.sci.model.jsres.ActivityCfgExt;
import com.pinde.sci.model.mo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jsres/cfgManager")
public class JsResCfgManagerController extends GeneralController {
    private static Logger logger= LoggerFactory.getLogger(JsResCfgManagerController.class);

    @Autowired
    protected IJsResPowerCfgBiz jsResPowerCfgBiz;

    @Autowired
    private IRoleBiz roleBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private ResOrgCkxzMapper orgCkxzMapper;

    @RequestMapping(value = {"/main"})
    public String main() {
        return "jsres/hospital/cfgManager/main";
    }
    @RequestMapping(value = {"/edit"})
    public String edit(Model model,String recordFlow) {
        Map<String,String> param = new HashMap<>();
        param.put("recordFlow",recordFlow);
        param.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("targets",roleBiz.searchActvity(param));

        List<JsresDeptConfig> deptConfigList = jsResPowerCfgBiz.searchDeptConfigs(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("deptConfigList",deptConfigList);

        ResOrgCkxzExample example = new ResOrgCkxzExample();
        ResOrgCkxzExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());
        example.setOrderByClause("SESSION_GRADE,SESSION_YEAR");
        List<ResOrgCkxz> orgCkxzList = orgCkxzMapper.selectByExample(example);
        model.addAttribute("orgCkxzList",orgCkxzList);


        List<SysDept> sysDeptList = deptBiz.searchDeptByOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("sysDeptList",sysDeptList);

        String code="jsres_cksh_" + GlobalContext.getCurrentUser().getOrgFlow() + "_";
        List<JsresPowerCfg> ckshList = jsResPowerCfgBiz.selectLikeCode("%"+code+"%");
        HashMap<String, String> ckshMap = new HashMap<>();
        for (JsresPowerCfg cfg : ckshList) {
            String substring = cfg.getCfgCode().substring(code.length());
            ckshMap.put(substring,substring);
        }
        model.addAttribute("ckshMap",ckshMap);
        return "jsres/hospital/cfgManager/editCfg";
    }


    @RequestMapping(value = {"/add"})
    public String add(Model model,String recordFlow) {
        List<SysCfg> sysRoleList= roleBiz.searchRoleList();
        model.addAttribute("sysRoleList", sysRoleList);
        model.addAttribute("recordFlow",recordFlow);
        Map<String,String> param = new HashMap<>();
        param.put("recordFlow",recordFlow);
        param.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        List<ActivityCfgExt> list = roleBiz.searchActvity(param);
        model.addAttribute("targets", list);
        if(recordFlow!=null) {
            String auditRole = list.get(0).getAuditRole();
            model.addAttribute("auditRole", auditRole);
            model.addAttribute("submitRole", list.get(0).getSubmitRole());
        }
        return "jsres/hospital/cfgManager/addActivityCfg";
    }

    @RequestMapping(value = {"/addActivityCfg"})
    @ResponseBody
    public String addActivityCfg(Model model,String recordFlow,String roleFlow,String auditRoleFlow,String subRoleName,String auditRoleName) {
        Map<String,String> param = new HashMap<>();
        param.put("recordFlow",recordFlow);
        param.put("submitRole",roleFlow);
        param.put("auditRole",auditRoleFlow);
        param.put("recordStatus", GlobalConstant.FLAG_Y);
        param.put("createTime",PdUtil.getCurrDateTime2());
        param.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        param.put("subRoleName",subRoleName);
        param.put("auditRoleName",auditRoleName);
        Map<String,String> roleParam = new HashMap<>();
        roleParam.put("submitRole",roleFlow);
        roleParam.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        List<ActivityCfgExt> subList = roleBiz.searchActvity(roleParam);
        if(subList.size()>=1){
            return "该活动发起人角色已具备活动发起权限";
        }
        int i = roleBiz.addActivityCfg(param);
        if( i>0 ) {
            return GlobalConstant.SAVE_SUCCESSED;
        }else{
            return  GlobalConstant.SAVE_FAIL;
        }
    }
    @RequestMapping(value = {"/delActivityCfg"})
    @ResponseBody
    public String delActivityCfg(String recordFlow) {
        int i = roleBiz.delActivityCfg(recordFlow);
        if(i>0) {
            return GlobalConstant.DELETE_SUCCESSED;
        }else{
            return GlobalConstant.DELETE_FAIL;
        }
    }
    @RequestMapping(value = {"/updateActivity"})
    @ResponseBody
    public String updateActivity(String recordFlow,String roleFlow,String auditRoleFlow,String subRoleName,String auditRoleName) {
        Map<String,String> param = new HashMap<>();
        param.put("recordFlow",recordFlow);
        param.put("submitRole",roleFlow);
        param.put("auditRole",auditRoleFlow);
        param.put("modifyTime",PdUtil.getCurrDateTime2());
        param.put("subRoleName",subRoleName);
        param.put("auditRoleName",auditRoleName);
        Map<String,String> roleParam = new HashMap<>();
        roleParam.put("submitRole",roleFlow);
        roleParam.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        List<ActivityCfgExt> subList = roleBiz.searchActvity(roleParam);
        if(subList.size()>=1 && (!recordFlow.equals(subList.get(0).getRecordFlow()))){
            return "该活动发起人角色已具备活动发起权限";
        }
        int i = roleBiz.updActivityCfg(param);
        if (i>=1) {
            return GlobalConstant.OPRE_SUCCESSED;
        }else{
            return GlobalConstant.OPRE_FAIL;
        }
    }
    @RequestMapping(value = {"/searchActivity"})
    public List<ActivityCfgExt> searchActivity(String recordFlow) {
        Map<String,String> param = new HashMap<>();
        param.put("recordFlow",recordFlow);
        param.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        return roleBiz.searchActvity(param);
    }

    @RequestMapping(value="/save",method= RequestMethod.POST)
    @ResponseBody
    public String save(HttpServletRequest request){
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        String value = "out_test_check_"+ orgFlow;
        String [] cfgCodes =request.getParameterValues("cfgCode");
        if(cfgCodes!=null){
            String nowTime= DateUtil.getCurrDateTime2();
            List<JsresPowerCfg> sysCfgList = new ArrayList<JsresPowerCfg>();
            for(String cfgCode : cfgCodes){
                String sysCfgValue=request.getParameter(cfgCode);
                String sysCfgDesc=request.getParameter(cfgCode+"_desc");
                JsresPowerCfg cfg = new JsresPowerCfg();
                cfg.setCfgCode(cfgCode);
                cfg.setCfgValue(sysCfgValue);
                cfg.setCfgDesc(sysCfgDesc);
                cfg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                sysCfgList.add(cfg);

                if(value.equals(cfgCode) && GlobalConstant.RECORD_STATUS_Y.equals(sysCfgValue)){
                    JsresDeptConfig config = jsResPowerCfgBiz.searchBaseDeptConfig(orgFlow);
                    if(null == config){
                        config = new JsresDeptConfig();
                        config.setDeptName("默认");
                        config.setOrgFlow(orgFlow);
                        config.setTestNum("5");
                        config.setScorePass("60");
                        config.setIsTestOut(GlobalConstant.FLAG_Y);
                        config.setTeacherWrite(GlobalConstant.FLAG_N);
                        jsResPowerCfgBiz.saveDeptConfig(config);
                    }
                }
            }
            jsResPowerCfgBiz.saveList(sysCfgList);
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping(value = {"/addDetailConfg"})
    public String addDetailConfg(Model model) {
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);
        model.addAttribute("deptList",deptList);
        model.addAttribute("orgFlow",orgFlow);
        return "jsres/hospital/cfgManager/addDetailConfg";
    }

    @RequestMapping(value = {"/addCkxzConfg"})
    public String addCkxzConfg(Model model) {
        return "jsres/hospital/cfgManager/addCkxzConfg";
    }

    @RequestMapping(value = {"/saveDeptConfig"})
    @ResponseBody
    public String saveDeptConfig(JsresDeptConfig deptConfig,HttpServletRequest request){
        int num = jsResPowerCfgBiz.saveDeptConfig(deptConfig);
        if(num > 0) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    @RequestMapping(value = {"/saveCkxzConfig"})
    @ResponseBody
    public String saveCkxzConfig(ResOrgCkxz orgCkxz){
        int num = jsResPowerCfgBiz.saveCkxzConfig(orgCkxz);
        if (num== -1){
            return "该年份数据已存在，请勿重复添加！";
        }
        if(num > 0) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    @RequestMapping(value = {"/searchDeptConfig"})
    public String searchDeptConfig(Model model) {
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        List<JsresDeptConfig> deptConfigList = jsResPowerCfgBiz.searchDeptConfigs(orgFlow);
        model.addAttribute("deptConfigList",deptConfigList);

        ResOrgCkxzExample example = new ResOrgCkxzExample();
        ResOrgCkxzExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());
        example.setOrderByClause("SESSION_GRADE,SESSION_YEAR");
        List<ResOrgCkxz> orgCkxzList = orgCkxzMapper.selectByExample(example);
        model.addAttribute("orgCkxzList",orgCkxzList);
        return "jsres/hospital/cfgManager/editCfg";
    }

    @RequestMapping(value = {"/editDetailConfg"})
    public String editDetailConfg(Model model,String cfgFlow) {
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        JsresDeptConfig deptConfig = jsResPowerCfgBiz.searchDeptCfgByCfgFlow(cfgFlow);
        model.addAttribute("deptConfig",deptConfig);
        model.addAttribute("orgFlow",orgFlow);
        return "jsres/hospital/cfgManager/editDetailConfg";
    }

    @RequestMapping(value = {"/editCkxzConfig"})
    public String editCkxzConfig(Model model,String recordFlow) {
        ResOrgCkxz ckxz = orgCkxzMapper.selectByPrimaryKey(recordFlow);
        model.addAttribute("ckxz",ckxz);
        return "jsres/hospital/cfgManager/editCkxzConfg";
    }

    @RequestMapping(value = {"/delDeptConfig"})
    @ResponseBody
    public String delDeptConfig(JsresDeptConfig deptConfig,HttpServletRequest request){
        deptConfig.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        int num = jsResPowerCfgBiz.saveDeptConfig(deptConfig);
        if(num > 0) {
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    @RequestMapping(value = {"/delCkxzConfig"})
    @ResponseBody
    public String delCkxzConfig(String recordFlow){
        ResOrgCkxz resOrgCkxz = orgCkxzMapper.selectByPrimaryKey(recordFlow);
        resOrgCkxz.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        int num = jsResPowerCfgBiz.saveCkxzConfig(resOrgCkxz);
        if(num > 0) {
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    @RequestMapping(value = {"/saveCkshConfig"})
    @ResponseBody
    public String saveCkshConfig(String[] mulDeptFlow){
        return jsResPowerCfgBiz.saveCkshConfig(mulDeptFlow);
    }
}
