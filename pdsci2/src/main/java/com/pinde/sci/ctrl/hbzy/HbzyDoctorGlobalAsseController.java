package com.pinde.sci.ctrl.hbzy;


import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.hbzy.IJszyGraduationApplyBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.hbzy.JszyResAsseAuditListEnum;
import com.pinde.sci.enums.hbzy.JszyResAuditStatusEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.hbzy.BaseUserResumeExtInfoForm;
import com.pinde.sci.model.mo.*;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/hbzy/asseGlobal")
public class HbzyDoctorGlobalAsseController extends GeneralController {
    @Autowired
    private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private OrgBizImpl orgBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private IJszyGraduationApplyBiz graduationApplyBiz;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;

    @RequestMapping(value="/main")
    public String main(Model model) {
        return "hbzy/asse/global/main";
    }

    /**
     * 省厅或基地待审核查询条件
     * @param model
     * @param roleFlag
     * @return
     */
    @RequestMapping(value="/waitAuditMain")
    public String waitAuditMain(Model model,String roleFlag){
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        model.addAttribute("org",org);
        if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
            SysOrg sysorg = new SysOrg();
            sysorg.setOrgProvId(org.getOrgProvId());
            sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
            model.addAttribute("orgs", orgs);
        }else{

            List<SysOrg>  orgs = new ArrayList<>();
            if(OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId()))
            {
                List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
                orgs.addAll(joinOrgs);
            }
            orgs.add(org);
            model.addAttribute("orgs", orgs);
        }
        List<ResJointOrg> jointOrgs = jointOrgBiz.searchJointOrgAll();
        List<String> orgFlowList = new ArrayList<String>();
        if (jointOrgs != null && !jointOrgs.isEmpty()) {
            for (ResJointOrg jointOrg : jointOrgs) {
                orgFlowList.add(jointOrg.getOrgFlow());
            }
        }
        model.addAttribute("orgFlowList", orgFlowList);
        model.addAttribute("roleFlag", roleFlag);
        return "hbzy/asse/global/waitAuditMain";
    }
    /**
     * 省厅或基地待审核列表
     * @param model
     * @param roleFlag
     * @return
     */
    @RequestMapping(value="/waitAuditList")
    public String waitAuditList(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request,String applyYear,
                                 String orgFlow,String trainingTypeId,String datas[],
                                String sessionNumber,String graduationYear,
                                String userName,String idNo,String trainingYears
                                ){
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String> orgFlowList=new ArrayList<String>();
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        model.addAttribute("org",org);
        if(StringUtil.isBlank(orgFlow))
        {
            if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
                 SysOrg sysorg =new  SysOrg();
                 sysorg.setOrgProvId(org.getOrgProvId());
                 param.put("org",sysorg);
            }else{
                if(OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId()))
                {
                    List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
                    if (joinOrgs != null && joinOrgs.size() > 0) {
                        for (SysOrg tempOrg : joinOrgs) {
                            orgFlowList.add(tempOrg.getOrgFlow());
                        }
                    }
                }
                orgFlowList.add(org.getOrgFlow());
            }
        }else{
            orgFlowList.add(orgFlow);
        }
        param.put("orgFlowList",orgFlowList);//培训基地
        param.put("orgFlow",orgFlow);//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("sessionNumber",sessionNumber);
        param.put("graduationYear",graduationYear);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("trainingYears",trainingYears);

        String nowTime=DateUtil.transDateTime(DateUtil.getCurrentTime());
        if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
            param.put("auditStatusId", JszyResAsseAuditListEnum.WaitGlobalPass.getId());
            String startDate=InitConfig.getSysCfg("jszy_global_submit_start_time");
            String endDate=InitConfig.getSysCfg("jszy_global_submit_end_time");
            String f="N";
            if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
                if(startDate.compareTo(nowTime)<=0&&endDate.compareTo(nowTime)>=0)
                {
                    if(applyYear.equals(DateUtil.getYear())){
                        f="Y";
                    }
                }
            }
            model.addAttribute("f",f);
        }else{

            String startDate=InitConfig.getSysCfg("jszy_local_submit_start_time");
            String endDate=InitConfig.getSysCfg("jszy_local_submit_end_time");
            String f="N";
            if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
                if(startDate.compareTo(nowTime)<=0&&endDate.compareTo(nowTime)>=0)
                {
                    if(applyYear.equals(DateUtil.getYear())){
                        f="Y";
                    }
                }
            }
            model.addAttribute("f",f);
            param.put("auditStatusId",JszyResAsseAuditListEnum.Auditing.getId());
        }
        param.put("applyYear",applyYear);
        PageHelper.startPage(currentPage,getPageSize(request));
        System.err.println(JSON.toJSON(param));
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryApplyList(param);
        model.addAttribute("list",list);
        return "hbzy/asse/global/waitAuditList";
    }
    /**
     * 省厅或基地已审核列表
     * @param model
     * @param roleFlag
     * @return
     */
    @RequestMapping(value="/AuditList")
    public String AuditList(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request,String applyYear,
                            String orgFlow,String trainingTypeId,String datas[],
                            String sessionNumber,String graduationYear,
                            String userName,String idNo,String trainingYears,String auditStatusId
                                ){
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String> orgFlowList=new ArrayList<String>();
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        model.addAttribute("org",org);
        if(StringUtil.isBlank(orgFlow))
        {
            if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
                SysOrg sysorg =new  SysOrg();
                sysorg.setOrgProvId(org.getOrgProvId());
                param.put("org",sysorg);
            }else{
                if(OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId()))
                {
                    List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
                    if (joinOrgs != null && joinOrgs.size() > 0) {
                        for (SysOrg tempOrg : joinOrgs) {
                            orgFlowList.add(tempOrg.getOrgFlow());
                        }
                    }
                }
                orgFlowList.add(org.getOrgFlow());
            }
        }else{
            orgFlowList.add(orgFlow);
        }
        param.put("orgFlowList",orgFlowList);//培训基地
        param.put("orgFlow",orgFlow);//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("sessionNumber",sessionNumber);
        param.put("graduationYear",graduationYear);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("trainingYears",trainingYears);
        param.put("auditStatusId",auditStatusId);
        param.put("applyYear",applyYear);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryApplyList(param);
        model.addAttribute("list",list);
        String nowTime=DateUtil.transDateTime(DateUtil.getCurrentTime());
        if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
            String startDate=InitConfig.getSysCfg("jszy_global_submit_start_time");
            String endDate=InitConfig.getSysCfg("jszy_global_submit_end_time");
            String f="N";
            if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
                if(startDate.compareTo(nowTime)<=0&&endDate.compareTo(nowTime)>=0)
                {
                    if(applyYear.equals(DateUtil.getYear())){
                        f="Y";
                    }
                }
            }
            model.addAttribute("f",f);
        }else{
            String startDate=InitConfig.getSysCfg("jszy_local_submit_start_time");
            String endDate=InitConfig.getSysCfg("jszy_local_submit_end_time");
            String f="N";
            if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
                if(startDate.compareTo(nowTime)<=0&&endDate.compareTo(nowTime)>=0)
                {
                    if(applyYear.equals(DateUtil.getYear())){
                        f="Y";
                    }
                }
            }
            model.addAttribute("f",f);
        }
        return "hbzy/asse/global/AuditList";
    }
    @RequestMapping(value="/exportList")
    public void exportList(Model model, String roleFlag , HttpServletResponse response, HttpServletRequest request,String applyYear,
                           String orgFlow,String trainingTypeId,String datas[],
                           String sessionNumber,String graduationYear,
                           String userName,String idNo,String trainingYears,String auditStatusId,String isWaitAudit
                           ) throws IOException, DocumentException {

        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String> orgFlowList=new ArrayList<String>();
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        if(StringUtil.isBlank(orgFlow))
        {
            if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
                SysOrg sysorg =new  SysOrg();
                sysorg.setOrgProvId(org.getOrgProvId());
                param.put("org",sysorg);
            }else{
                if(OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId()))
                {
                    List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
                    if (joinOrgs != null && joinOrgs.size() > 0) {
                        for (SysOrg tempOrg : joinOrgs) {
                            orgFlowList.add(tempOrg.getOrgFlow());
                        }
                    }
                }
                orgFlowList.add(org.getOrgFlow());
            }
        }else{
            orgFlowList.add(orgFlow);
        }
        param.put("orgFlowList",orgFlowList);//培训基地
        param.put("orgFlow",orgFlow);//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("sessionNumber",sessionNumber);
        param.put("graduationYear",graduationYear);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("trainingYears",trainingYears);
        param.put("auditStatusId",auditStatusId);
        param.put("applyYear",applyYear);
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryListForExport(param);
        if (list != null && list.size() > 0) {
            List<String> userFlows = new ArrayList<>();
            for (Map<String, Object> temp : list) {
                PubUserResume pubUserResume=userResumeBiz.readPubUserResume((String) temp.get("doctorFlow"));
                if(pubUserResume!=null)
                {
                    String xmlContent = pubUserResume.getUserResume();
                    if (StringUtil.isNotBlank(xmlContent)) {
                        //xml转换成JavaBean
                        BaseUserResumeExtInfoForm userResumeExt = null;
                        userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, BaseUserResumeExtInfoForm.class);
                        if (userResumeExt != null) {
                            temp.put("qualificationMaterialName", userResumeExt.getQualificationMaterialName());
                            temp.put("qualificationMaterialCode", userResumeExt.getQualificationMaterialCode());
                            temp.put("practicingScopeName", userResumeExt.getPracticingScopeName());
                        }
                    }

                }
            }

        }
        graduationApplyBiz.chargeExportList(list,response,isWaitAudit,roleFlag);
    }
    @RequestMapping(value="/chargeAuditApply")
    public String chargeAuditApply(Model model,String applyFlow,String roleFlag){
        List<JsresGraduationApplyLog> logs=graduationApplyBiz.getAuditLogsByApplyFlow(applyFlow);
        model.addAttribute("logs",logs);
        return "hbzy/asse/global/chargeAuditApply";
    }
    @RequestMapping(value="/batchAuditApply")
    public String batchAuditApply(Model model,String []applyFlow,String roleFlag){
        model.addAttribute("size",applyFlow.length);
        model.addAttribute("flows", Arrays.asList(applyFlow));
        return "hbzy/asse/global/batchAuditApply";
    }
    @RequestMapping(value="/chargeSaveAudit")
    @ResponseBody
    public String chargeSaveAudit(Model model,String applyFlow,String auditStatusId,String auditReason,String roleFlag){
        JsresGraduationApply apply=graduationApplyBiz.readByFlow(applyFlow);
        if(apply!=null)
        {
            if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
                if (JszyResAuditStatusEnum.LocalNotPassed.getId().equals(apply.getLocalAuditStatusId())) {
                    return "基地已退回该学员考核资格申请信息，无法审核！";
                }
                if (StringUtil.isBlank(auditStatusId)) {
                    return "请选择审核结果！";
                }
                if (JszyResAuditStatusEnum.NotPassed.getId().equals(auditStatusId) && StringUtil.isBlank(auditReason)) {
                    return "请输入原因！";
                }
                apply.setGlobalAuditStatusId(auditStatusId);
                apply.setGlobalAuditStatusName(JszyResAuditStatusEnum.getNameById(auditStatusId));
                apply.setGlobalReason(auditReason);
                if (JszyResAuditStatusEnum.NotPassed.getId().equals(auditStatusId))//省厅或基地审核不通过 直接不通过
                {
                    apply.setAuditStatusId(JszyResAsseAuditListEnum.GlobalNotPassed.getId());
                    apply.setAuditStatusName(JszyResAsseAuditListEnum.GlobalNotPassed.getName());
                } else {//省厅或基地审核通过
                    apply.setAuditStatusId(JszyResAsseAuditListEnum.GlobalPassed.getId());
                    apply.setAuditStatusName(JszyResAsseAuditListEnum.GlobalPassed.getName());
                }
                String nowTime = DateUtil.transDateTime(DateUtil.getCurrentTime());
                JsresGraduationApplyLog log = new JsresGraduationApplyLog();
                log.setApplyFlow(applyFlow);
                log.setAuditRoleId(GlobalConstant.USER_LIST_GLOBAL);
                log.setAuditRoleName("省厅");
                log.setAuditReason(auditReason);
                log.setAuditStatusId(auditStatusId);
                log.setAuditStatusName(JszyResAuditStatusEnum.getNameById(auditStatusId));
                log.setAuditTime(nowTime);
                SysUser sysuser = GlobalContext.getCurrentUser();
                log.setUserFlow(sysuser.getUserFlow());
                log.setUserName(sysuser.getUserName());
                graduationApplyBiz.saveChargeAudit(apply, log);
                return "审核成功";
            }else{

                if(StringUtil.isNotBlank(apply.getGlobalAuditStatusId()))
                {
                    return "省厅已审核该学员考核资格申请信息，无法审核！";
                }
                if(StringUtil.isBlank(auditStatusId))
                {
                    return "请选择审核结果！";
                }
                if(JszyResAuditStatusEnum.NotPassed.getId().equals(auditStatusId)&&StringUtil.isBlank(auditReason))
                {
                    return "请输入原因！";
                }
                apply.setLocalAuditStatusId(auditStatusId);
                apply.setLocalAuditStatusName(JszyResAuditStatusEnum.getNameById(auditStatusId));
                apply.setLocalReason(auditReason);
                if(JszyResAuditStatusEnum.NotPassed.getId().equals(auditStatusId))//市局审核不通过 直接不通过
                {
                    apply.setAuditStatusId(JszyResAuditStatusEnum.LocalNotPassed.getId());
                    apply.setAuditStatusName(JszyResAuditStatusEnum.LocalNotPassed.getName());
                }else{//市局审核通过 需要省厅审核
                    apply.setAuditStatusId(JszyResAuditStatusEnum.WaitGlobalPass.getId());
                    apply.setAuditStatusName(JszyResAuditStatusEnum.WaitGlobalPass.getName());
                }
                String nowTime=DateUtil.transDateTime(DateUtil.getCurrentTime());
                String startDate=InitConfig.getSysCfg("jszy_local_submit_start_time");
                String endDate=InitConfig.getSysCfg("jszy_local_submit_end_time");
                String f="N";
                if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
                    if(startDate.compareTo(nowTime)<=0&&endDate.compareTo(nowTime)>=0)
                    {
                        f="Y";
                    }
                }
                if("N".equals(f))
                {
                    return "当前时间不在审核时间段内，无法审核！";
                }
                JsresGraduationApplyLog log=new JsresGraduationApplyLog();
                log.setApplyFlow(applyFlow);
                log.setAuditRoleId(GlobalConstant.USER_LIST_LOCAL);
                log.setAuditRoleName("基地");
                log.setAuditReason(auditReason);
                log.setAuditStatusId(auditStatusId);
                log.setAuditStatusName(JszyResAuditStatusEnum.getNameById(auditStatusId));
                log.setAuditTime(nowTime);
                SysUser sysuser=GlobalContext.getCurrentUser();
                log.setUserFlow(sysuser.getUserFlow());
                log.setUserName(sysuser.getUserName());
                graduationApplyBiz.saveChargeAudit(apply,log);
                return "审核成功";
            }
        }
        return  "学员申请信息不存在，请刷新后再试！";
    }
    @RequestMapping(value="/batchSaveAudit")
    @ResponseBody
    public String batchSaveAudit(Model model,String []applyFlows,String auditStatusId,String auditReason,String roleFlag){
        if(applyFlows.length>0) {
            if (StringUtil.isBlank(auditStatusId)) {
                return "请选择审核结果！";
            }
            if (JszyResAuditStatusEnum.NotPassed.getId().equals(auditStatusId) && StringUtil.isBlank(auditReason)) {
                return "请输入原因！";
            }
            List<JsresGraduationApply> applies=new ArrayList<>();
            List<JsresGraduationApplyLog> logs=new ArrayList<>();
            for(String applyFlow:applyFlows) {
                JsresGraduationApply apply = graduationApplyBiz.readByFlow(applyFlow);
                if (apply != null) {
                    if (JszyResAuditStatusEnum.NotPassed.getId().equals(apply.getCityAuditStatusId())) {
                        ResDoctorRecruit recruit=jsResDoctorRecruitBiz.readRecruit(apply.getRecruitFlow());
                        SysUser doc=userBiz.findByFlow(recruit.getDoctorFlow());
                        return "市局已退回学员【"+doc.getUserName()+"】考核资格申请信息，无法审核！";
                    }
                    apply.setGlobalAuditStatusId(auditStatusId);
                    apply.setGlobalAuditStatusName(JszyResAuditStatusEnum.getNameById(auditStatusId));
                    apply.setGlobalReason(auditReason);
                    if (JszyResAuditStatusEnum.NotPassed.getId().equals(auditStatusId))//省厅或基地审核不通过 直接不通过
                    {
                        apply.setAuditStatusId(JszyResAsseAuditListEnum.GlobalNotPassed.getId());
                        apply.setAuditStatusName(JszyResAsseAuditListEnum.GlobalNotPassed.getName());
                    } else {//省厅或基地审核通过
                        apply.setAuditStatusId(JszyResAsseAuditListEnum.GlobalPassed.getId());
                        apply.setAuditStatusName(JszyResAsseAuditListEnum.GlobalPassed.getName());
                    }
                    String nowTime = DateUtil.transDateTime(DateUtil.getCurrentTime());
                    JsresGraduationApplyLog log = new JsresGraduationApplyLog();
                    log.setApplyFlow(applyFlow);
                    log.setAuditRoleId(GlobalConstant.USER_LIST_GLOBAL);
                    log.setAuditRoleName("省厅或基地");
                    log.setAuditReason(auditReason);
                    log.setAuditStatusId(auditStatusId);
                    log.setAuditStatusName(JszyResAuditStatusEnum.getNameById(auditStatusId));
                    log.setAuditTime(nowTime);
                    SysUser sysuser = GlobalContext.getCurrentUser();
                    log.setUserFlow(sysuser.getUserFlow());
                    log.setUserName(sysuser.getUserName());
                    applies.add(apply);
                    logs.add(log);
                }else {
                    return "学员申请信息不存在，请刷新后再试！";
                }
            }

            graduationApplyBiz.saveBatchAudit(applies, logs);
            return "审核成功";
        }
        return "未选择审核数据！";
    }
    @RequestMapping(value="/AuditListMain")
    public String AuditListMain(Model model,String roleFlag){
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        model.addAttribute("org",org);
        if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
            SysOrg sysorg = new SysOrg();
            sysorg.setOrgProvId(org.getOrgProvId());
            sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
            model.addAttribute("orgs", orgs);
        }else{

            List<SysOrg>  orgs = new ArrayList<>();
            if(OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId()))
            {
                List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
                orgs.addAll(joinOrgs);
            }
            orgs.add(org);
            model.addAttribute("orgs", orgs);
        }
        List<ResJointOrg> jointOrgs = jointOrgBiz.searchJointOrgAll();
        List<String> orgFlowList = new ArrayList<String>();
        if (jointOrgs != null && !jointOrgs.isEmpty()) {
            for (ResJointOrg jointOrg : jointOrgs) {
                orgFlowList.add(jointOrg.getOrgFlow());
            }
        }
        model.addAttribute("orgFlowList", orgFlowList);
        model.addAttribute("roleFlag", roleFlag);
        return "hbzy/asse/global/AuditListMain";
    }

}
