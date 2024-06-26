package com.pinde.sci.ctrl.jsres;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.jsres.CertificateStatusEnum;
import com.pinde.sci.enums.jsres.JsResDoctorAuditStatusEnum;
import com.pinde.sci.enums.jsres.JsResTrainYearEnum;
import com.pinde.sci.enums.jsres.TrainCategoryEnum;
import com.pinde.sci.enums.res.RegStatusEnum;
import com.pinde.sci.enums.res.ResDoctorStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.jsres.UserResumeExtInfoForm;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jsres/doctorScoreApply")
public class JsResDoctorScoreApplyController extends GeneralController {
    @Autowired
    private IJsResDoctorBiz jsResDoctorBiz;
    @Autowired
    private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
    @Autowired
    private IUserBiz userBiz;

    @Autowired
    private IResJointOrgBiz resJointBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private OrgBizImpl orgBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;
    @RequestMapping(value="/provinceDoctorList")
    public String provinceDoctorList(Model model, String roleFlag){
        SysUser sysuser= GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        SysOrg sysorg =new  SysOrg();
        sysorg.setOrgProvId(org.getOrgProvId());
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
            sysorg.setOrgCityId(org.getOrgCityId());
        }
        sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        sysorg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
        sysorg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
        List<SysOrg>  countryList=orgBiz.searchOrg(sysorg);
        sysorg.setOrgLevelId(OrgLevelEnum.ProvinceOrg.getId());
        List<SysOrg>  provinceList=orgBiz.searchOrg(sysorg);

        List<ResJointOrg> jointOrgs=jointOrgBiz.searchJointOrgAll();
        List<String> orgFlowList=new ArrayList<String>();
        if(jointOrgs!=null&&!jointOrgs.isEmpty()){
            for(ResJointOrg jointOrg:jointOrgs){
                orgFlowList.add(jointOrg.getOrgFlow());
            }
        }
        List<ResJointOrg> joinOrgs = resJointBiz.searchResJointByJointOrgFlow(sysuser.getOrgFlow());
        //是协同基地
        if(!joinOrgs.isEmpty()&&joinOrgs.size()>0){
            model.addAttribute("isJointOrg","1");
        }
        model.addAttribute("orgFlowList", orgFlowList);
        model.addAttribute("countryList", countryList);
        model.addAttribute("provinceList", provinceList);
        model.addAttribute("orgs", orgs);
        model.addAttribute("roleFlag", roleFlag);
        return  "jsres/doctorScoreApply/scoreList";
    }

    @RequestMapping(value="/certificateManagement")
    public String certificateManagement(Model model, String roleFlag){
        SysUser sysuser= GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        SysOrg sysorg =new  SysOrg();
        sysorg.setOrgProvId(org.getOrgProvId());
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
            sysorg.setOrgCityId(org.getOrgCityId());
        }
        sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
        sysorg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
        List<SysOrg>  countryList=orgBiz.searchOrg(sysorg);
        sysorg.setOrgLevelId(OrgLevelEnum.ProvinceOrg.getId());
        List<SysOrg>  provinceList=orgBiz.searchOrg(sysorg);

        List<ResJointOrg> jointOrgs=jointOrgBiz.searchJointOrgAll();
        List<String> orgFlowList=new ArrayList<String>();
        if(jointOrgs!=null&&!jointOrgs.isEmpty()){
            for(ResJointOrg jointOrg:jointOrgs){
                orgFlowList.add(jointOrg.getOrgFlow());
            }
        }
        model.addAttribute("orgFlowList", orgFlowList);
        model.addAttribute("countryList", countryList);
        model.addAttribute("provinceList", provinceList);
        model.addAttribute("orgs", orgs);
        model.addAttribute("roleFlag", roleFlag);
        return  "jsres/doctorScoreApply/certificateManagement";
    }


    @RequestMapping(value="/doctorScoreQuery")
    public String doctorScoreQuery(Model model,Integer currentPage,String roleFlag,
                                   HttpServletRequest request,ResDoctor doctor,
                                   SysUser user,String baseId,String jointOrgFlag,
                                   String derateFlag,String orgLevel,String[] datas,String graduationYear,String grantCertf,String unGrantCertf){
        ResDoctorRecruit resDoctorRecruit= new ResDoctorRecruit();
        if(StringUtil.isNotBlank(graduationYear)){
            resDoctorRecruit.setGraduationYear(graduationYear);
        }
        List<String> jointOrgFlowList=new ArrayList<String>();
        List<String>docTypeList=new ArrayList<String>();
        SysOrg org=new SysOrg();
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysUser sysUser =new SysUser();
        if (StringUtil.isBlank(doctor.getOrgFlow())) {
            if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
                jointOrgFlowList=searchJointOrgList(jointOrgFlag,sysuser.getOrgFlow());
                jointOrgFlowList.add(sysuser.getOrgFlow());
            }
            if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)
                    ||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.RES_ROLE_SCOPE_SCHOOL)) {
                SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
                if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
                    jointOrgFlowList.add(sysuser.getOrgFlow());
                    SysOrg searchOrg=new SysOrg();
                    searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                    if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
                        searchOrg.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    if(StringUtil.isNotBlank(orgLevel)){
                        searchOrg.setOrgLevelId(orgLevel);
                        searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                    }
                    List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
                    for(SysOrg g:exitOrgs){
                        List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
                        if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                            for(ResJointOrg jointOrg:resJointOrgList){
                                jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                            }
                        }
                        jointOrgFlowList.add(g.getOrgFlow());
                    }
                }else{
                    org.setOrgProvId(sysOrg.getOrgProvId());
                    if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
                        org.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    if(StringUtil.isNotBlank(orgLevel)){
                        org.setOrgLevelId(orgLevel);
                        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                    }
                }
            }
        }else{
            jointOrgFlowList.add(doctor.getOrgFlow());
            if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
                List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
                if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                    for(ResJointOrg jointOrg:resJointOrgList){
                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }
        if(StringUtil.isNotBlank(user.getIdNo())){
            sysUser.setIdNo(user.getIdNo());
        }
        sysUser.setUserName(user.getUserName());
        if(StringUtil.isNotBlank(derateFlag)){
            if(GlobalConstant.FLAG_Y.equals(derateFlag)){
                doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
                resDoctorRecruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
            }else{
                derateFlag="";
            }
        }else{
            derateFlag="";
        }
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        List<String> cerStatusList=new ArrayList<String>();
        if(StringUtil.isBlank(grantCertf)&&StringUtil.isBlank(unGrantCertf))
        {
            if(roleFlag.equals(GlobalConstant.USER_LIST_LOCAL)||roleFlag.equals(GlobalConstant.USER_LIST_CHARGE)) {
                cerStatusList.add(CertificateStatusEnum.Submit.getId());
                cerStatusList.add(CertificateStatusEnum.ManagerPassed.getId());
            }
            if(roleFlag.equals(GlobalConstant.USER_LIST_GLOBAL)) {
                cerStatusList.add(CertificateStatusEnum.ManagerPassed.getId());
            }
        }
        if(StringUtil.isNotBlank(grantCertf))
        {
            cerStatusList.add(CertificateStatusEnum.GrantCertf.getId());
        }
        if(StringUtil.isNotBlank(unGrantCertf))
        {
            cerStatusList.add(CertificateStatusEnum.UnGrantCertf.getId());
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        resDoctorRecruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
        List<Map<String,Object>> doctorList=null;
        if (GlobalConstant.RES_ROLE_SCOPE_SCHOOL.equals(roleFlag)) {
            ServletContext servletContext=request.getServletContext();
            if (servletContext!=null) {
                List<SysDict> sysDictList=(List<SysDict>) servletContext.getAttribute("dictTypeEnum"+"SendSchool"+"List");
                for (SysDict sysDict : sysDictList) {
                    String dictId="send_school_"+sysDict.getDictId()+"_org_rel";
                    String orgFlow=InitConfig.getSysCfg(dictId);
                    doctor.setWorkOrgId(sysDict.getDictId());
                    if (sysuser.getOrgFlow().equals(orgFlow)) {
                        doctorList=jsResDoctorRecruitBiz.doctorScoreQuery(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,docTypeList,cerStatusList);
                        break;
                    }else{
                        continue;
                    }
                }
            }
        }else{
            doctorList = jsResDoctorRecruitBiz.doctorScoreQuery(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,docTypeList,cerStatusList);
        }
        Map<String,Object> userResumeExtMap=new HashMap<String,Object>();
        if(null!=doctorList&&doctorList.size()>0)
        {
            for(Map<String,Object> bean:doctorList)
            {
                PubUserResume pubUserResume = userResumeBiz.readPubUserResume((String)bean.get("DOCTOR_FLOW"));
                if(pubUserResume != null) {
                    String xmlContent = pubUserResume.getUserResume();
                    if (StringUtil.isNotBlank(xmlContent)) {
                        //xml转换成JavaBean
                        UserResumeExtInfoForm userResumeExt = null;
                        try {
                            userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                        } catch (DocumentException e) {
                            e.getMessage();
                        }
                        if (userResumeExt != null) {
                            if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                                List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
                                if (sysDictList != null && !sysDictList.isEmpty()) {
                                    for (SysDict dict : sysDictList) {
                                        if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                                            if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
                                                userResumeExt.setGraduatedName(dict.getDictName());
                                            }
                                        }
                                    }

                                }
                            }
                            userResumeExtMap.put((String) bean.get("DOCTOR_FLOW"), userResumeExt);
                        }
                    }
                }
            }
        }
//        Map<String,Object> isCountryOrgMap=new HashMap<String,Object>();
//        if(null!=doctorList&&doctorList.size()>0)
//        {
//            for(Map<String,Object> bean:doctorList)
//            {
//                String f="0";//0 为江苏省 1为国家
//                ResDoctor resDoctor=resDoctorBiz.findByFlow((String) bean.get("DOCTOR_FLOW"));
//                if(null!=resDoctor) {
//                    int sessionYear = Integer.valueOf(resDoctor.getSessionNumber());
//                    //2013年以前全部用江苏省证书
//                    if (sessionYear <= 2013) {
//                        //江苏省生成规则待定
//                        f="0";
//                    } else if (sessionYear == 2014) {
//                        SysOrg org1 = orgBiz.readSysOrg(resDoctor.getOrgFlow());
//                        //只有国家基地使用国家证书
//                        if (org1.getOrgLevelId().equals(OrgLevelEnum.CountryOrg.getId())) {
//                            f="1";
//                        } else {
//                            //江苏省生成规则待定
//                            f="0";
//                        }
//                    } else {
//                        SysOrg org1 = orgBiz.readSysOrg(resDoctor.getOrgFlow());
//                        //国家基地使用国家证书
//                        if (org1.getOrgLevelId().equals(OrgLevelEnum.CountryOrg.getId())) {
//                            f="1";
//                        } else {
//                            List<ResJointOrg> jointOrgList = resJointBiz.searchResJointByJointOrgFlow(org1.getOrgFlow());
//                            if (jointOrgList != null && jointOrgList.size() > 0) {
//                                ResJointOrg resJointOrg = jointOrgList.get(0);
//                                SysOrg org2 = orgBiz.readSysOrg(resJointOrg.getOrgFlow());
//                                //国家基地的协同基地也使用国家证书
//                                if (org2.getOrgLevelId().equals(OrgLevelEnum.CountryOrg.getId())) {
//                                    f="1";
//                                } else {
//                                    //江苏省生成规则待定
//                                    f="0";
//                                }
//                            } else {
//                                //江苏省生成规则待定
//                                f="0";
//                            }
//                        }
//                    }
//                }
//                isCountryOrgMap.put((String) bean.get("DOCTOR_FLOW"), f);
//
//            }
//        }
        List<ResJointOrg> jointOrgs = resJointBiz.searchResJointByJointOrgFlow(sysuser.getOrgFlow());
        //是协同基地
        if(!jointOrgs.isEmpty()&&jointOrgs.size()>0){
            model.addAttribute("isJointOrg","1");
        }
        model.addAttribute("roleFlag",roleFlag);
        model.addAttribute("doctorList",doctorList);
        model.addAttribute("userResumeExtMap",userResumeExtMap);
        //model.addAttribute("isCountryOrgMap",isCountryOrgMap);
        model.addAttribute("datas",datas);
        return  "jsres/doctorScoreApply/doctorListZi";
    }
    public List<String> searchJointOrgList(String flag,String Flow) {
        List<String> jointOrgFlowList=new ArrayList<String>();
        if(GlobalConstant.FLAG_Y.equals(flag)){
            List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(Flow);
            if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                for(ResJointOrg jointOrg:resJointOrgList){
                    jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                }
            }
        }
        return jointOrgFlowList;
    }

    @RequestMapping(value="/doctorScoreApplyAudit")
    @ResponseBody
    public String doctorScoreApplyAudit(String doctorFlow,String roleFlag,String applyType,String reason,String doctorStatusId) throws DocumentException, UnsupportedEncodingException {
        if(doctorFlow==null){
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
            ResDoctor resDoctor=resDoctorBiz.findByFlow(doctorFlow);
            if(null==resDoctor)
            {
                return GlobalConstant.OPRE_FAIL_FLAG;
            }
            //培训基地审核
            if(roleFlag.equals(GlobalConstant.USER_LIST_LOCAL)) {
                //上报
                if (StringUtil.isNotBlank(applyType) && applyType.equals(CertificateStatusEnum.ManagerPassed.getId())) {
                    resDoctor.setGraduationStatusId(CertificateStatusEnum.ManagerPassed.getId());
                    resDoctor.setGraduationStatusName(CertificateStatusEnum.ManagerPassed.getName());
                    resDoctor.setDisagreeReason("");
                } else {
                    //退回
                    resDoctor.setGraduationStatusId("");
                    resDoctor.setGraduationStatusName("");
                    reason = java.net.URLDecoder.decode(reason, "UTF-8");
                    resDoctor.setDisagreeReason(reason);
                }
            }else  //省厅审核
            if(roleFlag.equals(GlobalConstant.USER_LIST_GLOBAL)) {
                //同意发证
                if (StringUtil.isNotBlank(applyType) && applyType.equals(CertificateStatusEnum.GrantCertf.getId())) {
                    resDoctor.setGraduationStatusId(CertificateStatusEnum.GrantCertf.getId());
                    resDoctor.setGraduationStatusName(CertificateStatusEnum.GrantCertf.getName());
                    resDoctor.setDisagreeReason("同意发放合格证书");
                    if(!StringUtil.isNotBlank(resDoctor.getCompleteNo())) {
                        //年份代码（4位）+省（自治区、直辖市）代码（2位）+专业代码（4位）+培训基地代码（3位）+该培训基地该年度结业人员顺序号（3位）
                        String certificateNo = resDoctorBiz.getCertificateNo(resDoctor);
                        resDoctor.setCompleteNo(certificateNo);
                    }
                    //状态改为结业
                    resDoctor.setDoctorStatusId("21");
                    resDoctor.setDoctorStatusName(DictTypeEnum.DoctorStatus.getDictNameById("21"));
                } else {
                    //暂不发证
                    resDoctor.setGraduationStatusId(CertificateStatusEnum.UnGrantCertf.getId());
                    resDoctor.setGraduationStatusName(CertificateStatusEnum.UnGrantCertf.getName());
                    try{
                        doctorStatusId = java.net.URLDecoder.decode(doctorStatusId, "UTF-8");
                        doctorStatusId = doctorStatusId+java.net.URLDecoder.decode(reason, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        doctorStatusId="暂不发证";
                    }
                    resDoctor.setDisagreeReason(doctorStatusId);
                    //状态改为已考核待结业
                    resDoctor.setDoctorStatusId("22");
                    resDoctor.setDoctorStatusName(DictTypeEnum.DoctorStatus.getDictNameById("22"));
                }
                //获取最新培训记录
                if(StringUtil.isNotBlank(doctorFlow)){
                    ResDoctorRecruit docRecruit =  new ResDoctorRecruit();
                    docRecruit.setDoctorFlow(doctorFlow);
                    docRecruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                    List<ResDoctorRecruit> docRecruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(docRecruit, "CREATE_TIME DESC");
                    if(docRecruitList != null && !docRecruitList.isEmpty()) {
                        
                        docRecruit = docRecruitList.get(0);
                        //DoctorRecruit回写
                        ResDoctorRecruitWithBLOBs docRecruitWithBLOBs = new ResDoctorRecruitWithBLOBs();
                        docRecruitWithBLOBs.setRecruitFlow(docRecruit.getRecruitFlow());
                        //状态改为结业
                        docRecruitWithBLOBs.setDoctorStatusId(resDoctor.getDoctorStatusId());
                        docRecruitWithBLOBs.setDoctorStatusName(DictTypeEnum.DoctorStatus.getDictNameById(resDoctor.getDoctorStatusId()));
                        jsResDoctorRecruitBiz.saveDoctorRecruit(docRecruitWithBLOBs);
                    }
                }
            }
            int result = resDoctorBiz.editDoctor(resDoctor);
            if(GlobalConstant.ZERO_LINE==result){
                return GlobalConstant.FLAG_N;
            }
        return GlobalConstant.FLAG_Y;
    }
    //基地单个学生申请退回 页面
    @RequestMapping(value = "/applyBack")
    public String applyBack(String doctorFlow,String roleFlag,String applyType,String reason,Model model) throws DocumentException {

        model.addAttribute("doctorFlow",doctorFlow);
        model.addAttribute("roleFlag",roleFlag);
        model.addAttribute("applyType",applyType);
        return "jsres/doctorScoreApply/localBack";
    }

    //批量审核页面
    @RequestMapping(value = "/batchApply")
    public String batchApply(String[] datas,String roleFlag,Model model) throws DocumentException {
        model.addAttribute("doctorFlow",datas);
        model.addAttribute("size",datas.length);
        model.addAttribute("roleFlag",roleFlag);
        return "jsres/doctorScoreApply/batchApply";
    }

    //批量审核
    @RequestMapping(value = "/batchApplyAudit")
    @ResponseBody
    public String batchApplyAudit(String[] datas,String roleFlag,String applyType,String reason,String doctorStatusId) throws DocumentException {
        if(datas==null){
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        int count=resDoctorBiz.batchApplyAudit(datas,roleFlag,applyType,reason,doctorStatusId);

        return "本次操作成功"+count+"条！失败"+(datas.length-count)+"条！";
    }

    @RequestMapping(value = "/certificateNoQuery")
    public String certificateNoQuery(String cityId,String roleFlag,String orgLevel,String userName,String idNo,String certificateNum,String orgFlow,Model model) {
        List<ResDoctor> list=new ArrayList<ResDoctor>();
        Map<String,Object> map=new HashMap<String,Object>();
        List<String> jointOrgFlowList=new ArrayList<String>();

        //省厅查询
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)) {
            //基地
            if (StringUtil.isBlank(orgFlow)) {
                SysOrg searchOrg=new SysOrg();
                searchOrg.setOrgProvId("320000");
                if(StringUtil.isNotBlank(cityId))
                {
                    searchOrg.setOrgCityId(cityId);
                }
                searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
                for(SysOrg g:exitOrgs){
                    List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
                    if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                        for(ResJointOrg jointOrg:resJointOrgList){
                            jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                    jointOrgFlowList.add(g.getOrgFlow());
                }
            }else{
                jointOrgFlowList.add(orgFlow);
                List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(orgFlow);
                if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                    for(ResJointOrg jointOrg:resJointOrgList){
                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }
        //市局
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
            SysUser sysuser=GlobalContext.getCurrentUser();
            //基地
            if (StringUtil.isBlank(orgFlow)) {
                SysOrg searchOrg=new SysOrg();
                searchOrg.setOrgProvId("320000");
                searchOrg.setOrgCityId(sysuser.getNativePlaceCityId());
                if(StringUtil.isNotBlank(orgLevel)){
                    searchOrg.setOrgLevelId(orgLevel);
                }
                searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
                for(SysOrg g:exitOrgs){
                    List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
                    if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                        for(ResJointOrg jointOrg:resJointOrgList){
                            jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                    jointOrgFlowList.add(g.getOrgFlow());
                }
            }else{
                jointOrgFlowList.add(orgFlow);
                List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(orgFlow);
                if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                    for(ResJointOrg jointOrg:resJointOrgList){
                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
            cityId=sysuser.getNativePlaceCityId();
        }
        //基地
        if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
            SysUser sysuser=GlobalContext.getCurrentUser();

            SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
            if(OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
                jointOrgFlowList = searchJointOrgList("Y", sysuser.getOrgFlow());
            }
            jointOrgFlowList.add(sysuser.getOrgFlow());
        }
        map.put("cityId",cityId);
        map.put("proId","320000");
        map.put("userName",userName);
        map.put("idNo",idNo);
        map.put("cerNum",certificateNum);
        map.put("jointOrgFlowList",jointOrgFlowList);
        list=resDoctorBiz.searchResDoctorByMap(map);

        model.addAttribute("list",list);
        model.addAttribute("roloFlag",roleFlag);

        return "jsres/doctorScoreApply/certificateList";
    }
    @RequestMapping(value = "/showCertificate")
    public String showCertificate(String doctorFlow,Model model) {

        ResDoctor resDoctor=resDoctorBiz.searchByUserFlow(doctorFlow);
        SysUser sysUser=userBiz.findByFlow(doctorFlow);
        String completeNo="";
        completeNo=getShowCountryOrProvince(resDoctor);
        List<ResJointOrg> jointOrgs = resJointBiz.searchResJointByJointOrgFlow(resDoctor.getOrgFlow());
        //是协同基地 显示国家基地
        if(!jointOrgs.isEmpty()&&jointOrgs.size()>0){
            resDoctor.setOrgName(jointOrgs.get(0).getOrgName());
        }
        model.addAttribute("completeNo",completeNo);
        model.addAttribute("resDoctor",resDoctor);
        model.addAttribute("sysUser",sysUser);
        model.addAttribute("date", DateUtil.getCurrDate());
        return "jsres/showCertificate/info";
    }
    private String getShowCountryOrProvince(ResDoctor resDoctor) {
        String completeNo="";
        String sessionNumber=resDoctor.getSessionNumber();
        if(StringUtil.isBlank(sessionNumber))
        {
            return "";
        }
        //所有助理全科人员都只生成助理全科证书
        if(resDoctor.getTrainingTypeId().equals(TrainCategoryEnum.AssiGeneral.getId()))
        {
            completeNo="AssiGeneral";
        }else {
            int sessionYear = Integer.valueOf(resDoctor.getSessionNumber());
            //2013年以前全部用江苏省证书
            if (sessionYear <= 2013) {
                //江苏省生成规则待定
                completeNo = getProvinceOrgNo(resDoctor);
            } else if (sessionYear == 2014) {
                SysOrg org = orgBiz.readSysOrg(resDoctor.getOrgFlow());
                //只有国家基地使用国家证书
                if (org.getOrgLevelId().equals(OrgLevelEnum.CountryOrg.getId())) {
                    completeNo = "country";
                } else {
                    //江苏省生成规则待定
                    completeNo = getProvinceOrgNo(resDoctor);
                }
            } else {
                SysOrg org = orgBiz.readSysOrg(resDoctor.getOrgFlow());
                //国家基地使用国家证书
                if (org.getOrgLevelId().equals(OrgLevelEnum.CountryOrg.getId())) {
                    completeNo = "country";
                } else {
                    List<ResJointOrg> jointOrgList = resJointBiz.searchResJointByJointOrgFlow(org.getOrgFlow());
                    if (jointOrgList != null && jointOrgList.size() > 0) {
                        ResJointOrg resJointOrg = jointOrgList.get(0);
                        SysOrg org2 = orgBiz.readSysOrg(resJointOrg.getOrgFlow());
                        //国家基地的协同基地也使用国家证书
                        if (org2.getOrgLevelId().equals(OrgLevelEnum.CountryOrg.getId())) {
                            completeNo = "country";
                        } else {
                            //江苏省生成规则待定
                            completeNo = getProvinceOrgNo(resDoctor);
                        }
                    } else {
                        //江苏省生成规则待定
                        completeNo = getProvinceOrgNo(resDoctor);
                    }
                }
            }
        }
        return completeNo;
    }


    public String getProvinceOrgNo(ResDoctor resDoctor)
    {
        String no="";
        if(resDoctor.getTrainingSpeId().equals("51")||resDoctor.getTrainingSpeId().equals("52")||resDoctor.getTrainingSpeId().equals("0700"))
        {
            no="provinceAll";
        }
        else{
            //非全科
            no="provinceNoAll";
        }
        return no;
    }
    @RequestMapping(value = "/doctorShowCertificate")
    public String doctorShowCertificate(String doctorFlow,Model model) {

        ResDoctor resDoctor=resDoctorBiz.searchByUserFlow(doctorFlow);
        SysUser sysUser=userBiz.findByFlow(doctorFlow);
        String completeNo="";
        completeNo=getShowCountryOrProvince(resDoctor);
        if(StringUtil.isNotBlank(resDoctor.getOrgFlow())) {
            List<ResJointOrg> jointOrgs = resJointBiz.searchResJointByJointOrgFlow(resDoctor.getOrgFlow());
            //是协同基地 显示国家基地
            if (!jointOrgs.isEmpty() && jointOrgs.size() > 0) {
                resDoctor.setOrgName(jointOrgs.get(0).getOrgName());
            }
        }
        model.addAttribute("completeNo",completeNo);
        model.addAttribute("resDoctor",resDoctor);
        model.addAttribute("sysUser",sysUser);
        model.addAttribute("date", DateUtil.getCurrDate());
        return "jsres/showCertificate/doctorCertificate";
    }

    /****************************高******校******管******理******员******角******色************************************************/
    @RequestMapping(value="/provinceDoctorListForUni")
    public String provinceDoctorListForUni(Model model){
        SysUser sysuser= GlobalContext.getCurrentUser();
        SysOrg sysorg=orgBiz.readSysOrg(sysuser.getOrgFlow());
        List<SysOrg> orgs=orgBiz.getUniOrgs(sysorg.getSendSchoolId(),sysorg.getSendSchoolName());
        model.addAttribute("orgs", orgs);

        return  "jsres/university/scoreList";
    }
    @RequestMapping(value="/doctorScoreQueryForUni")
    public String doctorScoreQueryForUni(Model model,Integer currentPage,String orgFlow,
                                   HttpServletRequest request,String trainingTypeId,String trainingSpeId,
                                         String sessionNumber,String idNo,
                                   String userName,String graduationYear,String grantCertf,String unGrantCertf){
        SysUser user=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(user.getOrgFlow());
        Map<String,Object> param=new HashMap<>();
        if(StringUtil.isNotEmpty(user.getSchool())){
            param.put("school",user.getSchool());
        }
        param.put("orgFlow",orgFlow);
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("idNo",idNo);
        param.put("userName",userName);
        param.put("graduationYear",graduationYear);

        List<String> cerStatusList=new ArrayList<String>();
        if(StringUtil.isBlank(grantCertf)&&StringUtil.isBlank(unGrantCertf)) {
            cerStatusList.add(CertificateStatusEnum.Submit.getId());
            cerStatusList.add(CertificateStatusEnum.ManagerPassed.getId());
        }
        if(StringUtil.isNotBlank(grantCertf))
        {
            cerStatusList.add(CertificateStatusEnum.GrantCertf.getId());
        }
        if(StringUtil.isNotBlank(unGrantCertf))
        {
            cerStatusList.add(CertificateStatusEnum.UnGrantCertf.getId());
        }
        param.put("cerStatusList",cerStatusList);
        param.put("org",org);
        PageHelper.startPage(currentPage,getPageSize(request));
         List<Map<String,Object>>   doctorList =null;
        if(org==null||(StringUtil.isBlank(org.getSendSchoolId())&&StringUtil.isBlank(org.getSendSchoolName())))
        {
            doctorList = null;
        }else {
            doctorList = jsResDoctorRecruitBiz.doctorScoreQueryForUni(param);
        }
        Map<String,Object> userResumeExtMap=new HashMap<String,Object>();
        if(null!=doctorList&&doctorList.size()>0)
        {
            for(Map<String,Object> bean:doctorList)
            {
                PubUserResume pubUserResume = userResumeBiz.readPubUserResume((String)bean.get("DOCTOR_FLOW"));
                if(pubUserResume != null) {
                    String xmlContent = pubUserResume.getUserResume();
                    if (StringUtil.isNotBlank(xmlContent)) {
                        //xml转换成JavaBean
                        UserResumeExtInfoForm userResumeExt = null;
                        try {
                            userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                        } catch (DocumentException e) {
                            e.getMessage();
                        }
                        if (userResumeExt != null) {
                            if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                                List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
                                if (sysDictList != null && !sysDictList.isEmpty()) {
                                    for (SysDict dict : sysDictList) {
                                        if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                                            if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
                                                userResumeExt.setGraduatedName(dict.getDictName());
                                            }
                                        }
                                    }

                                }
                            }
                            userResumeExtMap.put((String) bean.get("DOCTOR_FLOW"), userResumeExt);
                        }
                    }
                }
            }
        }
        model.addAttribute("doctorList",doctorList);
        model.addAttribute("userResumeExtMap",userResumeExtMap);
        return  "jsres/university/doctorListZi";
    }
    @RequestMapping(value="/certificateManagementForUni")
    public String certificateManagementForUni(Model model){
        SysUser sysuser= GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        List<SysOrg> orgs=orgBiz.getUniOrgs(org.getSendSchoolId(),org.getSendSchoolName());
        model.addAttribute("orgs",orgs);
        return  "jsres/university/certificateManagement";
    }

    @RequestMapping(value = "/certificateNoQueryForUni")
    public String certificateNoQueryForUni(String userName,String idNo,String certificateNum,Model model) {
        List<ResDoctor> list=new ArrayList<ResDoctor>();
        Map<String,Object> map=new HashMap<String,Object>();

        SysUser sysuser= GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        map.put("org",org);
        if(StringUtil.isNotEmpty(sysuser.getSchool())){
            map.put("school",sysuser.getSchool());
        }
        map.put("proId","320000");
        map.put("userName",userName);
        map.put("idNo",idNo);
        map.put("cerNum",certificateNum);
        list=resDoctorBiz.searchResDoctorByMapForUni(map);
        model.addAttribute("list",list);

        return "jsres/university/certificateList";
    }
}
