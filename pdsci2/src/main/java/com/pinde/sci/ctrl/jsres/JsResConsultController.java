package com.pinde.sci.ctrl.jsres;

import com.alibaba.fastjson.JSONArray;
import com.pinde.core.model.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IConsultInfoBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.jsres.ConsultRoleEnum;
import com.pinde.sci.model.mo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/jsres/consult")
public class JsResConsultController extends GeneralController {
    private static Logger logger = LoggerFactory.getLogger(JsResConsultController.class);

    @Autowired
    private IDictBiz dictBiz;

    @Autowired
    private IUserRoleBiz userRoleBiz;

    @Autowired
    private IRoleBiz roleBiz;

    @Autowired
    private IOrgBiz orgBiz;

    @Autowired
    private IConsultInfoBiz consultInfoBiz;

    @Autowired
    private IResDoctorBiz resDoctorBiz;

    @RequestMapping("/main")
    public String main(Model model){
        //验证角色
        SysUser currentUser = GlobalContext.getCurrentUser();
        if (currentUser == null) {
            List<String> orgCityNameList = consultInfoBiz.searchOrgCityNameList();
            model.addAttribute("orgCityNameList",orgCityNameList);
            return "jsres/consult/doctor/doctorMain";
        }
        SysUserRole userRole = new SysUserRole();
        userRole.setUserFlow(currentUser.getUserFlow());
        userRole.setWsId(GlobalConstant.RES_WS_ID);
        List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
        SysRole role = roleBiz.read(userRoleList.get(0).getRoleFlow());
        String roleFlow = role.getRoleFlow();
        if (roleFlow.equals(InitConfig.getSysCfg("res_charge_role_flow"))){
            return "jsres/consult/charge/chargeMain";
        }else if (roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow"))){
            List<String> orgCityNameList = consultInfoBiz.searchOrgCityNameList();
            model.addAttribute("orgCityNameList",orgCityNameList);
            return "jsres/consult/doctor/doctorMain";
        }else if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))){
            List<String> orgCityNameList = consultInfoBiz.searchOrgCityNameList();
            model.addAttribute("orgCityNameList",orgCityNameList);
//            return "jsres/consult/charge/chargeMain";
            return "jsres/consult/doctor/doctorMain";
        }else {
            List<String> orgCityNameList = consultInfoBiz.searchOrgCityNameList();
            model.addAttribute("orgCityNameList",orgCityNameList);
            return "jsres/consult/doctor/doctorMain";
        }
    }

    @RequestMapping("/enquireManage")
    public String enquireManage(){
        return "jsres/consult/charge/enquireManage";
    }

    @RequestMapping("/enquireInfoManage")
    public String enquireInfoManage(String consultQuestion,String consultTypeId,String consultTypeSonId,String consultQuestionRoleId,String isAnswer,Model model,Integer currentPage, HttpServletRequest request){
        SysUser currentUser = GlobalContext.getCurrentUser();
        String orgFlow = currentUser.getOrgFlow();
        SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
        //查询条件
        HashMap<String, String> map = new HashMap<>();
        map.put("consultQuestion",consultQuestion);
        map.put("consultTypeId",consultTypeId);
        map.put("consultTypeSonId",consultTypeSonId);
        map.put("consultQuestionRoleId",consultQuestionRoleId);

        if ("on".equals(isAnswer)){
            isAnswer = GlobalConstant.FLAG_N;
            map.put("isAnswer",isAnswer);
        }
        //问答专区
        map.put("isPolicy", GlobalConstant.FLAG_N);

        //管理员所在地区
        map.put("orgCityId",sysOrg.getOrgCityId());
        map.put("orgCityName",sysOrg.getOrgCityName());

        PageHelper.startPage(currentPage,getPageSize(request));
        List<ConsultInfo> consultInfos = consultInfoBiz.search2(map);
        for (ConsultInfo c : consultInfos) {
            c.setConsultQuestionCreateTime(DateUtil.transDateTime(c.getConsultQuestionCreateTime()));
        }
        model.addAttribute("consultInfos",consultInfos);
        model.addAttribute("consultQuestion",consultQuestion);
        model.addAttribute("isAnswer",isAnswer);
        model.addAttribute("consultQuestionRoleId",consultQuestionRoleId);
        model.addAttribute("currentPage",currentPage);
        return "jsres/consult/charge/enquireInfoManage";
    }

    @RequestMapping("/policyManage")
    public String policyManage(){
        return "jsres/consult/charge/policyManage";
    }

    @RequestMapping("/policyInfoManage")
    public String policyInfoManage(String consultQuestion,String orderBy,Model model,Integer currentPage, HttpServletRequest request){
        SysUser currentUser = GlobalContext.getCurrentUser();
        String orgFlow = currentUser.getOrgFlow();
        SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
        //查询条件

        HashMap<String, String> map = new HashMap<>();
        map.put("consultQuestion",consultQuestion);
        //政策专区
        map.put("isPolicy", GlobalConstant.FLAG_Y);

        //管理员所在地区
        map.put("orgCityId",sysOrg.getOrgCityId());
        map.put("orgCityName",sysOrg.getOrgCityName());

        map.put("orderBy",orderBy);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<ConsultInfo> consultInfos = consultInfoBiz.search2(map);
        for (ConsultInfo c : consultInfos) {
            c.setConsultQuestionCreateTime(DateUtil.transDateTime(c.getConsultQuestionCreateTime()));
        }
        model.addAttribute("consultInfos",consultInfos);
        model.addAttribute("currentPage",currentPage);
        return "jsres/consult/charge/policyInfoManage";
    }

    @RequestMapping("/replyForm")
    public String replyForm(Model model,String consultInfoFlow,String currentPage){
        ConsultInfo consultInfo = consultInfoBiz.read(consultInfoFlow);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("consultInfo",consultInfo);
        return "jsres/consult/charge/replyForm";
    }

    @RequestMapping("/replyConsult")
    @ResponseBody
    public String replyConsult(ConsultInfo consultInfo){
        if(StringUtil.isNotBlank(consultInfo.getIsPolicy())){
            if (GlobalConstant.RECORD_STATUS_Y.equals(consultInfo.getIsPolicy())){
                consultInfo.setChargeName(GlobalContext.getCurrentUser().getUserName());
            }
        }
        if (StringUtil.isNotBlank(consultInfo.getConsultTypeId())){
            consultInfo.setConsultTypeName(DictTypeEnum.ConsultType.getDictNameById(consultInfo.getConsultTypeId()));
        }
        if (StringUtil.isNotBlank(consultInfo.getConsultTypeSonId())) {
            consultInfo.setConsultTypeSonName(DictTypeEnum.ConsultType.getDictNameById(consultInfo.getConsultTypeId()+"."+consultInfo.getConsultTypeSonId()));
        }
        consultInfo.setIsAnswer(GlobalConstant.FLAG_Y);
        int c = consultInfoBiz.saveConsultInfo(consultInfo);
        if (c == 1){
            return GlobalConstant.SAVE_SUCCESSED;
        }else {
            return GlobalConstant.SAVE_FAIL;
        }
    }

    @RequestMapping("/addConsultForm")
    public String addConsultForm(){
        return "jsres/consult/charge/addConsultForm";
    }

    //验证身份,新增consult
    @RequestMapping("/addConsultInfo")
    @ResponseBody
    public String addConsultInfo(ConsultInfo consultInfo){
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysUserRole userRole = new SysUserRole();
        userRole.setUserFlow(currentUser.getUserFlow());
        userRole.setWsId(GlobalConstant.RES_WS_ID);
        List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
        String roleFlow = "";
        if(userRoleList != null && userRoleList.size() > 0){
            SysRole role = roleBiz.read(userRoleList.get(0).getRoleFlow());
            roleFlow = role.getRoleFlow();
        }
        consultInfo.setConsultQuestionerFlow(currentUser.getUserFlow());
        consultInfo.setConsultQuestionerName(currentUser.getUserName());
        consultInfo.setConsultVisitNumber("0");
        if (StringUtil.isNotBlank(consultInfo.getConsultTypeId())){
            consultInfo.setConsultTypeName(DictTypeEnum.ConsultType.getDictNameById(consultInfo.getConsultTypeId()));
        }
        if (StringUtil.isNotBlank(consultInfo.getConsultTypeSonId())) {
            consultInfo.setConsultTypeSonName(DictTypeEnum.ConsultType.getDictNameById(consultInfo.getConsultTypeId()+"."+consultInfo.getConsultTypeSonId()));
        }
        if (roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow"))){
            consultInfo.setConsultQuestionRoleId(ConsultRoleEnum.Admin.getId());
            consultInfo.setConsultQuestionRoleName(ConsultRoleEnum.Admin.getName());
            consultInfo.setConsultQuestionCreateTime(DateUtil.getCurrDateTime());
            SysOrg sysOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
            consultInfo.setOrgCityId(sysOrg.getOrgCityId());
            consultInfo.setOrgCityName(sysOrg.getOrgCityName());
        }else if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))){
            consultInfo.setConsultQuestionRoleId(ConsultRoleEnum.Doctor.getId());
            consultInfo.setConsultQuestionRoleName(ConsultRoleEnum.Doctor.getName());
            ResDoctor resDoctor = resDoctorBiz.searchByUserFlow(currentUser.getUserFlow());
            consultInfo.setConsultQuestionerGrade(resDoctor.getSessionNumber());
            consultInfo.setConsultQuestionerBaseFlow(resDoctor.getOrgFlow());
            consultInfo.setConsultQuestionerBaseName(resDoctor.getOrgName());
            SysOrg sysOrg = orgBiz.readSysOrg(resDoctor.getOrgFlow());
            consultInfo.setOrgCityId(sysOrg.getOrgCityId());
            consultInfo.setOrgCityName(sysOrg.getOrgCityName());
            consultInfo.setConsultQuestionCreateTime(DateUtil.getCurrDateTime());
        }else if (roleFlow.equals(InitConfig.getSysCfg("res_charge_role_flow"))){
            consultInfo.setConsultQuestionCreateTime(DateUtil.getCurrDateTime());
            SysOrg sysOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
            consultInfo.setOrgCityId(sysOrg.getOrgCityId());
            consultInfo.setOrgCityName(sysOrg.getOrgCityName());
            consultInfo.setIsPolicy(GlobalConstant.FLAG_Y);
            consultInfo.setIsAnswer(GlobalConstant.FLAG_Y);
            consultInfo.setChargeName(currentUser.getUserName());
        } else {
            return "当前登录身份角色异常";
        }
        int c = consultInfoBiz.saveConsultInfo(consultInfo);
        if (c == 1){
            return GlobalConstant.SAVE_SUCCESSED;
        }else {
            return GlobalConstant.SAVE_FAIL;
        }
    }

    @RequestMapping("/deleteAll")
    @ResponseBody
    public String deleteAll(@RequestParam String chkList){
        List<String> list =(List<String>) JSONArray.parse(chkList);
        consultInfoBiz.deleteAll(list);
        return GlobalConstant.DELETE_SUCCESSED;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String consultInfoFlow){
        consultInfoBiz.delete(consultInfoFlow);
        return GlobalConstant.DELETE_SUCCESSED;
    }

    @RequestMapping("/editConsultForm")
    public String editConsultForm(String consultInfoFlow,Model model){
        ConsultInfo consultInfo = consultInfoBiz.read(consultInfoFlow);
        model.addAttribute("consultInfo",consultInfo);
        return "jsres/consult/charge/editConsultForm";
    }

    @RequestMapping("/doEdit")
    @ResponseBody
    public String doEdit(ConsultInfo consultInfo){
        if (StringUtil.isNotBlank(consultInfo.getConsultTypeId())){
            consultInfo.setConsultTypeName(DictTypeEnum.ConsultType.getDictNameById(consultInfo.getConsultTypeId()));
        }
        if (StringUtil.isNotBlank(consultInfo.getConsultTypeSonId())) {
            consultInfo.setConsultTypeSonName(DictTypeEnum.ConsultType.getDictNameById(consultInfo.getConsultTypeId()+"."+consultInfo.getConsultTypeSonId()));
        }
        int c = consultInfoBiz.saveConsultInfo(consultInfo);
        if (c == 1){
            return GlobalConstant.SAVE_SUCCESSED;
        }else {
            return GlobalConstant.SAVE_FAIL;
        }
    }



    //学员,基地功能
    @RequestMapping(value={"/enquireArea"},method={RequestMethod.POST})
    public String enquireArea(String orgCityName,String consultQuestion,String orderBy,Model model,Integer currentPage, HttpServletRequest request){
        //查询条件
        HashMap<String, String> map = new HashMap<>();
        map.put("consultQuestion",consultQuestion);
        //问答专区
        map.put("isPolicy", GlobalConstant.FLAG_N);

        //管理员所在地区
        map.put("orgCityName",orgCityName);
        map.put("isAnswer", GlobalConstant.FLAG_Y);
        map.put("orderBy",orderBy);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<ConsultInfo> consultInfos = consultInfoBiz.search2(map);
        for (ConsultInfo c : consultInfos) {
            c.setConsultQuestionCreateTime(DateUtil.transDateTime(c.getConsultQuestionCreateTime()));
        }
        model.addAttribute("consultInfos",consultInfos);
        model.addAttribute("consultQuestion",consultQuestion);
        model.addAttribute("orgCityName",orgCityName);
        model.addAttribute("orderBy",orderBy);
        model.addAttribute("currentPage",currentPage);
        return "jsres/consult/doctor/enquireArea";
    }

    @RequestMapping(value={"/policyArea"},method={RequestMethod.POST})
    public String policyArea(String orgCityName,String consultQuestion,String orderBy,Model model,Integer currentPage, HttpServletRequest request){
        //查询条件
        HashMap<String, String> map = new HashMap<>();
        map.put("consultQuestion",consultQuestion);
        //政策专区
        map.put("isPolicy", GlobalConstant.FLAG_Y);

        //管理员所在地区
        map.put("orgCityName",orgCityName);
        map.put("isAnswer", GlobalConstant.FLAG_Y);
        map.put("orderBy",orderBy);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<ConsultInfo> consultInfos = consultInfoBiz.search2(map);
        for (ConsultInfo c : consultInfos) {
            c.setConsultQuestionCreateTime(DateUtil.transDateTime(c.getConsultQuestionCreateTime()));
        }
        model.addAttribute("consultInfos",consultInfos);
        model.addAttribute("consultQuestion",consultQuestion);
        model.addAttribute("orgCityName",orgCityName);
        model.addAttribute("orderBy",orderBy);
        model.addAttribute("currentPage",currentPage);
        return "jsres/consult/doctor/policyArea";
    }

    @RequestMapping("/loadTitle")
    @ResponseBody
    public List<SysDict> loadTitle(@RequestParam(value = "consultTypeId", required = true) String consultTypeId){
        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(consultTypeId);
        return sysDictList;
    }

    @RequestMapping("/addQuestionForm")
    public String addQuestionForm(){
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysUserRole userRole = new SysUserRole();
        userRole.setUserFlow(currentUser.getUserFlow());
        userRole.setWsId(GlobalConstant.RES_WS_ID);
        List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
        SysRole role = roleBiz.read(userRoleList.get(0).getRoleFlow());
        String roleFlow = role.getRoleFlow();
        if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))){
            ResDoctor resDoctor = resDoctorBiz.readDoctor(currentUser.getUserFlow());
            if (resDoctor != null){
                String orgFlow = resDoctor.getOrgFlow();
                if (orgFlow != null && orgFlow.length() > 0){
                }else {
                    return "jsres/consult/noLogin";
                }
            }else {
                return "jsres/consult/noLogin";
            }
            return "jsres/consult/doctor/addQuestionForm";
        }else if (roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow"))){
            return "jsres/consult/doctor/addQuestionForm";
        }else {
            return "jsres/consult/noLogin";
        }
    }


    @RequestMapping("/myQuestionForm")
    public String myQuestionForm(Model model,Integer currentPage, HttpServletRequest request){
        model.addAttribute("currentPage",currentPage);
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysUserRole userRole = new SysUserRole();
        userRole.setUserFlow(currentUser.getUserFlow());
        userRole.setWsId(GlobalConstant.RES_WS_ID);
        List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
        SysRole role = roleBiz.read(userRoleList.get(0).getRoleFlow());
        String roleFlow = role.getRoleFlow();
        if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))){
            ResDoctor resDoctor = resDoctorBiz.readDoctor(currentUser.getUserFlow());
            if (resDoctor != null){
                String orgFlow = resDoctor.getOrgFlow();
                if (orgFlow != null && orgFlow.length() > 0){
                }else {
                    return "jsres/consult/noLogin1";
                }
            }else {
                return "jsres/consult/noLogin1";
            }
            PageHelper.startPage(currentPage,getPageSize(request));
            List<ConsultInfo> consultInfos = consultInfoBiz.searchMyQuestion(currentUser.getUserFlow());
            model.addAttribute("consultInfos",consultInfos);
            return "jsres/consult/doctor/myQuestionForm";
        }else if (roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow"))){
            PageHelper.startPage(currentPage,getPageSize(request));
            List<ConsultInfo> consultInfos = consultInfoBiz.searchMyQuestion(currentUser.getUserFlow());
            model.addAttribute("consultInfos",consultInfos);
            return "jsres/consult/doctor/myQuestionForm";
        }else {
            PageHelper.startPage(currentPage,getPageSize(request));
            List<ConsultInfo> consultInfos = consultInfoBiz.searchMyQuestion(currentUser.getUserFlow());
            model.addAttribute("consultInfos",consultInfos);
            return "jsres/consult/noLogin1";
        }
    }

    @RequestMapping("/myQuestion")
    public String myQuestion(Model model,Integer currentPage, HttpServletRequest request){
        model.addAttribute("currentPage",currentPage);
        SysUser currentUser = GlobalContext.getCurrentUser();
        PageHelper.startPage(currentPage,getPageSize(request));
        List<ConsultInfo> consultInfos = consultInfoBiz.searchMyQuestion(currentUser.getUserFlow());
        model.addAttribute("consultInfos",consultInfos);
        return "jsres/consult/doctor/myQuestion";
    }

    @RequestMapping("/detailConsult")
    public String detailConsult(String consultInfoFlow,Model model){
        consultInfoBiz.detail(consultInfoFlow);
        ConsultInfo consultInfo = consultInfoBiz.read(consultInfoFlow);
        consultInfo.setConsultQuestionCreateTime(DateUtil.transDateTime(consultInfo.getConsultQuestionCreateTime()));
        model.addAttribute("consultInfo",consultInfo);
        return "jsres/consult/doctor/detailConsult";
    }
}
