package com.pinde.sci.ctrl.hbres;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.hbres.IHbResGraduationApplyBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.jsres.JsResAuditStatusEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.mo.*;
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
@RequestMapping("/hbres/asseGlobal")
public class HbresDoctorGlobalAsseController extends GeneralController {
    @Autowired
    private IJsResDoctorBiz jsResDoctorBiz;
    @Autowired
    private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;
    @Autowired
    private OrgBizImpl orgBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IHbResGraduationApplyBiz graduationApplyBiz;
    @Autowired
    private IFileBiz fileBiz;

    @RequestMapping(value="/main")
    public String main(Model model) {

        return "hbres/asse/global/main";
    }

    /**
     * 省厅待审核查询条件
     * @param model
     * @param roleFlag
     * @return
     */
    @RequestMapping(value="/waitAuditMain")
    public String waitAuditMain(Model model,String roleFlag){
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        model.addAttribute("org",org);
        SysOrg sysorg =new  SysOrg();
        sysorg.setOrgProvId(org.getOrgProvId());
        sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
        model.addAttribute("orgs", orgs);
        model.addAttribute("roleFlag", roleFlag);
        return "hbres/asse/global/waitAuditMain";
    }
    @RequestMapping(value="/AuditListMain")
    public String AuditListMain(Model model,String roleFlag){
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        model.addAttribute("org",org);
        SysOrg sysorg =new  SysOrg();
        sysorg.setOrgProvId(org.getOrgProvId());
        sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
        model.addAttribute("orgs", orgs);
        model.addAttribute("roleFlag", roleFlag);
        return "hbres/asse/global/AuditListMain";
    }
    /**
     * 省厅待审核列表
     * @param model
     * @param roleFlag
     * @return
     */
    @RequestMapping(value="/waitAuditList")
    public String waitAuditList(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request,String applyYear,
                                 String orgFlow,String trainingTypeId,String trainingSpeId,String datas[],
                                String sessionNumber,String graduationYear,String qualificationMaterialId,
                                String userName,String idNo,String completeBi,String auditBi,String cityId
                                ){
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String>orgFlowList=new ArrayList<String>();
        if(StringUtil.isBlank(orgFlow))
        {
            SysUser sysuser=GlobalContext.getCurrentUser();
            SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
            SysOrg sysorg =new  SysOrg();
            sysorg.setOrgProvId(org.getOrgProvId());
            if (StringUtil.isNotBlank(cityId)) {
                sysorg.setOrgCityId(cityId);
            }
            param.put("org",sysorg);
        }
        param.put("orgFlow",orgFlow);//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        List<String> sessionNumbers=new ArrayList<String>();//年级多选
        if(StringUtil.isNotBlank(sessionNumber)){
            String[] numbers=sessionNumber.split(",");
            if(numbers!=null&&numbers.length>0){
                sessionNumbers=Arrays.asList(numbers);
                sessionNumber="";
            }
        }
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("sessionNumbers",sessionNumbers);
        param.put("graduationYear",graduationYear);
        param.put("qualificationMaterialId",qualificationMaterialId);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("completeBi",completeBi);
        param.put("auditBi",auditBi);
        param.put("auditStatusId",JsResAuditStatusEnum.WaitGlobalPass.getId());
        param.put("applyYear",applyYear);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryApplyList(param);
        model.addAttribute("list",list);
        String nowTime=DateUtil.transDateTime(DateUtil.getCurrentTime());
        String startDate=InitConfig.getSysCfg("global_submit_start_time");
        String endDate=InitConfig.getSysCfg("global_submit_end_time");
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
        return "hbres/asse/global/waitAuditList";
    }
    /**
     * 省厅已审核列表
     * @param model
     * @param roleFlag
     * @return
     */
    @RequestMapping(value="/AuditList")
    public String AuditList(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request,String applyYear,
                                 String orgFlow,String trainingTypeId,String trainingSpeId,String datas[],
                                String sessionNumber,String graduationYear,String qualificationMaterialId,
                                String userName,String idNo,String completeBi,String auditBi,String auditStatusId,String cityId
                                ){
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String>orgFlowList=new ArrayList<String>();
        if(StringUtil.isBlank(orgFlow))
        {
            SysUser sysuser=GlobalContext.getCurrentUser();
            SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
            SysOrg sysorg =new  SysOrg();
            sysorg.setOrgProvId(org.getOrgProvId());
            if (StringUtil.isNotBlank(cityId)) {
                sysorg.setOrgCityId(cityId);
            }
            param.put("org",sysorg);
        }
        param.put("orgFlow",orgFlow);//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        List<String> sessionNumbers=new ArrayList<String>();//年级多选
        if(StringUtil.isNotBlank(sessionNumber)){
            String[] numbers=sessionNumber.split(",");
            if(numbers!=null&&numbers.length>0){
                sessionNumbers=Arrays.asList(numbers);
                sessionNumber="";
            }
        }
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("sessionNumbers",sessionNumbers);
        param.put("graduationYear",graduationYear);
        param.put("qualificationMaterialId",qualificationMaterialId);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("completeBi",completeBi);
        param.put("auditBi",auditBi);
        param.put("auditStatusId",auditStatusId);
        param.put("applyYear",applyYear);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryApplyList(param);
        model.addAttribute("list",list);
        String nowTime=DateUtil.transDateTime(DateUtil.getCurrentTime());
        String startDate=InitConfig.getSysCfg("global_submit_start_time");
        String endDate=InitConfig.getSysCfg("global_submit_end_time");
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
        return "hbres/asse/global/AuditList";
    }
    @RequestMapping(value="/exportList")
    public void exportList(Model model, String roleFlag , HttpServletResponse response, HttpServletRequest request,
                             String orgFlow, String trainingTypeId, String trainingSpeId, String datas[],String applyYear,
                             String sessionNumber, String graduationYear, String qualificationMaterialId, String passFlag,
                             String userName, String idNo, String completeBi, String auditBi, String auditStatusId,String cityId,String isWaitAudit
                            ,String isNotMatch,String isNotEqual
                                ) throws IOException {
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String>orgFlowList=new ArrayList<String>();
        if(StringUtil.isBlank(orgFlow))
        {
            SysUser sysuser=GlobalContext.getCurrentUser();
            SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
            SysOrg sysorg =new  SysOrg();
            sysorg.setOrgProvId(org.getOrgProvId());
            if (StringUtil.isNotBlank(cityId)) {
                sysorg.setOrgCityId(cityId);
            }
            param.put("org",sysorg);
        }
        param.put("orgFlow",orgFlow);//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("graduationYear",graduationYear);
        param.put("qualificationMaterialId",qualificationMaterialId);
        param.put("passFlag",passFlag);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("completeBi",completeBi);
        param.put("auditBi",auditBi);
        param.put("isNotMatch",isNotMatch);
        param.put("isNotEqual",isNotEqual);
        param.put("auditStatusId",auditStatusId);
        param.put("applyYear",applyYear);
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryListForExport(param);
        graduationApplyBiz.chargeExportList(list,response,isWaitAudit);
    }
    @RequestMapping(value="/chargeAuditApply")
    public String chargeAuditApply(Model model,String applyFlow){
        List<JsresGraduationApplyLog> logs=graduationApplyBiz.getAuditLogsByApplyFlow(applyFlow);
        model.addAttribute("logs",logs);
        return "hbres/asse/global/chargeAuditApply";
    }
    @RequestMapping(value="/batchAuditApply")
    public String batchAuditApply(Model model,String []applyFlow){
        model.addAttribute("size",applyFlow.length);
        model.addAttribute("flows", Arrays.asList(applyFlow));
        return "hbres/asse/global/batchAuditApply";
    }
    @RequestMapping(value="/chargeSaveAudit")
    @ResponseBody
    public String chargeSaveAudit(Model model,String applyFlow,String auditStatusId,String auditReason){
        JsresGraduationApply apply=graduationApplyBiz.readByFlow(applyFlow);
        if(apply!=null)
        {
            if(JsResAuditStatusEnum.NotPassed.getId().equals(apply.getCityAuditStatusId()))
            {
                return "市局已退回该学员考核资格申请信息，无法审核！";
            }
            if(StringUtil.isBlank(auditStatusId))
            {
                return "请选择审核结果！";
            }
            if(JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId)&&StringUtil.isBlank(auditReason))
            {
                return "请输入原因！";
            }
            apply.setGlobalAuditStatusId(auditStatusId);
            apply.setGlobalAuditStatusName(JsResAuditStatusEnum.getNameById(auditStatusId));
            apply.setGlobalReason(auditReason);
            if(JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId))//省厅审核不通过 直接不通过
            {
                apply.setAuditStatusId(JsResAuditStatusEnum.GlobalNotPassed.getId());
                apply.setAuditStatusName(JsResAuditStatusEnum.GlobalNotPassed.getName());
            }else{//省厅审核通过
                apply.setAuditStatusId(JsResAuditStatusEnum.GlobalPassed.getId());
                apply.setAuditStatusName(JsResAuditStatusEnum.GlobalPassed.getName());
            }
            String nowTime=DateUtil.transDateTime(DateUtil.getCurrentTime());
            JsresGraduationApplyLog log=new JsresGraduationApplyLog();
            log.setApplyFlow(applyFlow);
            log.setAuditRoleId(GlobalConstant.USER_LIST_GLOBAL);
            log.setAuditRoleName("省厅");
            log.setAuditReason(auditReason);
            log.setAuditStatusId(auditStatusId);
            log.setAuditStatusName(JsResAuditStatusEnum.getNameById(auditStatusId));
            log.setAuditTime(nowTime);
            SysUser sysuser=GlobalContext.getCurrentUser();
            log.setUserFlow(sysuser.getUserFlow());
            log.setUserName(sysuser.getUserName());
            graduationApplyBiz.saveChargeAudit(apply,log);
            return "审核成功";
        }
        return  "学员申请信息不存在，请刷新后再试！";
    }
    @RequestMapping(value="/batchSaveAudit")
    @ResponseBody
    public String batchSaveAudit(Model model,String []applyFlows,String auditStatusId,String auditReason){
        if(applyFlows.length>0) {
            if (StringUtil.isBlank(auditStatusId)) {
                return "请选择审核结果！";
            }
            if (JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId) && StringUtil.isBlank(auditReason)) {
                return "请输入原因！";
            }
            List<JsresGraduationApply> applies=new ArrayList<>();
            List<JsresGraduationApplyLog> logs=new ArrayList<>();
            for(String applyFlow:applyFlows) {
                JsresGraduationApply apply = graduationApplyBiz.readByFlow(applyFlow);
                if (apply != null) {
                    if (JsResAuditStatusEnum.NotPassed.getId().equals(apply.getCityAuditStatusId())) {
                        ResDoctorRecruit recruit=jsResDoctorRecruitBiz.readRecruit(apply.getRecruitFlow());
                        SysUser doc=userBiz.findByFlow(recruit.getDoctorFlow());
                        return "市局已退回学员【"+doc.getUserName()+"】考核资格申请信息，无法审核！";
                    }
                    apply.setGlobalAuditStatusId(auditStatusId);
                    apply.setGlobalAuditStatusName(JsResAuditStatusEnum.getNameById(auditStatusId));
                    apply.setGlobalReason(auditReason);
                    if (JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId))//省厅审核不通过 直接不通过
                    {
                        apply.setAuditStatusId(JsResAuditStatusEnum.GlobalNotPassed.getId());
                        apply.setAuditStatusName(JsResAuditStatusEnum.GlobalNotPassed.getName());
                    } else {//省厅审核通过
                        apply.setAuditStatusId(JsResAuditStatusEnum.GlobalPassed.getId());
                        apply.setAuditStatusName(JsResAuditStatusEnum.GlobalPassed.getName());
                    }
                    String nowTime = DateUtil.transDateTime(DateUtil.getCurrentTime());
                    JsresGraduationApplyLog log = new JsresGraduationApplyLog();
                    log.setApplyFlow(applyFlow);
                    log.setAuditRoleId(GlobalConstant.USER_LIST_GLOBAL);
                    log.setAuditRoleName("省厅");
                    log.setAuditReason(auditReason);
                    log.setAuditStatusId(auditStatusId);
                    log.setAuditStatusName(JsResAuditStatusEnum.getNameById(auditStatusId));
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

}
