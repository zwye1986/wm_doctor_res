package com.pinde.sci.ctrl.jsres;


import com.alibaba.fastjson.JSON;
import com.pinde.core.common.GlobalConstant;
import com.pinde.core.common.enums.DictTypeEnum;
import com.pinde.core.common.enums.JsResAuditStatusEnum;
import com.pinde.core.common.enums.OrgTypeEnum;
import com.pinde.core.model.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.*;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.SchRotationDeptMapper;
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
@RequestMapping("/jsres/asseGlobal")
public class JsResDoctorGlobalAsseController extends GeneralController {
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
    private IResRecBiz resRecBiz;
    @Autowired
    private IResScoreBiz resScoreBiz;
    @Autowired
    private ISchRotationBiz rotationBiz;
    @Autowired
    private ISchArrangeResultBiz resultBiz;
    @Autowired
    private ISchRotationGroupBiz groupBiz;
    @Autowired
    private SchRotationDeptMapper rotationDeptMapper;
    @Autowired
    private IResDoctorProcessBiz processBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private ISchRotationDeptBiz schRotationDeptBiz;
    @Autowired
    private IJsResRecBiz jsResRecBiz;
    @Autowired
    private ISchDoctorDeptBiz doctorDeptBiz;
    @Autowired
    private ISchRotationDeptBiz rotationDeptBiz;
    public static final String SCOREYEAR_NOT_FIND="请选择成绩年份";
    @Autowired
    private IJsResGraduationApplyBiz graduationApplyBiz;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private IResTestConfigBiz resTestConfigBiz;

    @RequestMapping(value="/main")
    public String main(Model model) {
        return "jsres/asse/global/main";
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
        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
        model.addAttribute("orgs", orgs);
        List<String> speIds=getSpeIds(sysuser);
        if(speIds!=null)
        {
            model.addAttribute("speIds", speIds);
        }
        model.addAttribute("roleFlag", roleFlag);
        List<ResTestConfig> resTestConfigs = resTestConfigBiz.findAllEffective();
        model.addAttribute("resTestConfigs", resTestConfigs);
        return "jsres/asse/global/waitAuditMain";
    }
    public  List<String> getSpeIds(SysUser user)
    {

        List<String> speIds=new ArrayList<>();

        speIds.add("0700"); //住院医师的 全科
        speIds.add("51");//一阶段 全科方向（中医）
        speIds.add("52");//一阶段 全科方向（西医）
        speIds.add("18");//助理全科 全科方向（中医）
        speIds.add("50");//助理全科 全科方向（西医）
        if("stqky".equals(user.getUserCode()))
        {
            return  speIds;
        }else if("stzyy".equals(user.getUserCode())){
            List<String> newSpeIds=new ArrayList<>();
            List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId()); //住院医师的
            if(sysDictList!=null){
                for(SysDict dict:sysDictList)
                {
                    if(!speIds.contains(dict.getDictId()))
                    {
                        newSpeIds.add(dict.getDictId());
                    }
                }
            }
            sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.WMFirst.getId()); //一阶段
            if(sysDictList!=null){
                for(SysDict dict:sysDictList)
                {
                    if(!speIds.contains(dict.getDictId()))
                    {
                        newSpeIds.add(dict.getDictId());
                    }
                }
            }
            sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.WMSecond.getId()); //二阶段
            if(sysDictList!=null){
                for(SysDict dict:sysDictList)
                {
                    if(!speIds.contains(dict.getDictId()))
                    {
                        newSpeIds.add(dict.getDictId());
                    }
                }
            }
            sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.AssiGeneral.getId()); //助理全科
            if(sysDictList!=null){
                for(SysDict dict:sysDictList)
                {
                    if(!speIds.contains(dict.getDictId()))
                    {
                        newSpeIds.add(dict.getDictId());
                    }
                }
            }
            return newSpeIds;
        }
        return null;
    }

    @RequestMapping(value="/asseAuditListMain")
    public String asseAuditListMain(Model model) {
        return "jsres/asse/global/asseAuditListMain";
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
                                String sessionNumber,String graduationYear,String qualificationMaterialId,String passFlag,
                                String userName,String idNo,String completeBi,String auditBi,String cityId,String isNotMatch,String isNotEqual,String testId
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
        param.put("passFlag",passFlag);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("completeBi",completeBi);
        param.put("auditBi",auditBi);
        param.put("isNotMatch",isNotMatch);
        param.put("isNotEqual",isNotEqual);
        param.put("auditStatusId", com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getId());
        param.put("applyYear",applyYear);
        param.put("testId",testId);
        /*if(StringUtil.isBlank(trainingSpeId))
        {
            SysUser sysuser=GlobalContext.getCurrentUser();
            List<String> speIds=getSpeIds(sysuser);
            if(speIds!=null)
            {
                param.put("speIds", speIds);
            }
        }*/
        PageHelper.startPage(currentPage,getPageSize(request));
        System.err.println(JSON.toJSON(param));
//        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryList(param);
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryApplyList(param);
        model.addAttribute("list",list);
        String f = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResTestConfig> resTestConfigList = resTestConfigBiz.findGlobalEffective(DateUtil.getCurrDateTime2());
        if (resTestConfigList.size() > 0) {
            if (applyYear.equals(DateUtil.getYear())) {
                f = com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
        }
        model.addAttribute("f",f);
        return "jsres/asse/global/waitAuditList";
    }

    @RequestMapping(value="/waitAuditListNew")
    public String waitAuditListNew(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request,String applyYear,
                                   String orgFlow,String trainingTypeId,String trainingSpeId,String datas[],
                                   String sessionNumber,String graduationYear,String qualificationMaterialId,String passFlag,
                                   String userName,String idNo,String completeBi,String auditBi,String auditStatusId,String cityId,String isNotMatch,String testId,
                                   String educationId,String trainYear,String materialName,String qualificationMaterialCode, String qualificationMaterialUrl,
                                   String schMonthSearch, String schMonth, String proveFileUrl, String AfterEvaluation,String skillScoreSearch,String skillScore,
                                   String avgCompleteSearch,String avgComplete,String avgAuditSearch,String avgAudit,String completeTime,String qualificationName,
                                   String AfterEvaluationSearch,String AfterEvaluationScale,String passFile,String tabTag,String joinOrgFlow,String isPostpone
    ){
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String>orgFlowList=new ArrayList<String>();
        SysUser sysuser=GlobalContext.getCurrentUser();
        if(StringUtil.isBlank(orgFlow))
        {
            SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
            SysOrg sysorg =new  SysOrg();
            sysorg.setOrgProvId(org.getOrgProvId());
            if (StringUtil.isNotBlank(cityId)) {
                sysorg.setOrgCityId(cityId);
            }
            param.put("org",sysorg);
        }
//        param.put("orgFlow",orgFlow);//培训基地
        if(StringUtil.isNotBlank(orgFlow)) {
            List<String> jointOrgFlows = new ArrayList<>();
            List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(orgFlow);
            jointOrgFlows.add(orgFlow);
            if(null != jointOrgList && jointOrgList.size()>0){
                for (SysOrg so:jointOrgList) {
                    jointOrgFlows.add(so.getOrgFlow());
                }
            }
            param.put("jointOrgFlows",jointOrgFlows);
        }
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
        param.put("passFlag",passFlag);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("isNotMatch",isNotMatch);
        param.put("auditStatusId",auditStatusId);
        param.put("testId",testId);
        param.put("applyYear",applyYear);
        param.put("educationId",educationId);
        param.put("trainYear",trainYear);
        param.put("graduationMaterialName",materialName);
        param.put("qualificationName",qualificationName);
        param.put("qualificationMaterialCode",qualificationMaterialCode);
        param.put("qualificationMaterialUrl",qualificationMaterialUrl);
        param.put("passFile",passFile);
        param.put("tabTag",tabTag);
        param.put("schMonthSearch",schMonthSearch);
        param.put("schMonth",schMonth);
        param.put("proveFileUrl",proveFileUrl);
        param.put("AfterEvaluation",AfterEvaluation);
        param.put("AfterEvaluationSearch",AfterEvaluationSearch);
        param.put("AfterEvaluationScale",AfterEvaluationScale);
        param.put("completeTime", completeTime);
        param.put("skillScoreSearch",skillScoreSearch);
        param.put("skillScore",skillScore);
        param.put("avgCompleteSearch",avgCompleteSearch);
        param.put("avgComplete",avgComplete);
        param.put("avgAuditSearch",avgAuditSearch);
        param.put("avgAudit",avgAudit);
        param.put("roleFlag",roleFlag);
        param.put("jointOrgFlow",joinOrgFlow);
        param.put("isPostpone",isPostpone);
        // stzyy 该省厅账号特殊处理 不允许查看审核全科和助理全科专业的学员  禅道需求316 提出人：徐开宏 2020年6月23日15:20:40
        // stqky 该省厅账号特殊处理 只允许查看审核全科和助理全科专业的学员
        List<String> speIds = getSpeIds(sysuser);
        if(speIds!=null && speIds.size() > 0) {
            param.put("speIds", speIds);
        }
        PageHelper.startPage(currentPage,getPageSize(request));
  //      System.err.println(JSON.toJSON(param));
//        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryList(param);
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryApplyList(param);
        model.addAttribute("list",list);
        String f = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResTestConfig> resTestConfigList = resTestConfigBiz.findGlobalEffective(DateUtil.getCurrDateTime2());
        if (resTestConfigList.size() > 0) {
            if (applyYear.equals(DateUtil.getYear())) {
                f = com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
        }
        model.addAttribute("f",f);
        model.addAttribute("roleFlag",roleFlag);
//        return "jsres/asse/global/waitAuditList";
        return "jsres/asse/global/AuditList";
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
                                String sessionNumber,String graduationYear,String qualificationMaterialId,String passFlag,
                                String userName,String idNo,String completeBi,String auditBi,String auditStatusId,String cityId,String isNotMatch,String testId,
                                String educationId,String trainYear,String graduationMaterialName
                                ){
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String>orgFlowList=new ArrayList<String>();
        SysUser sysuser=GlobalContext.getCurrentUser();
        if(StringUtil.isBlank(orgFlow))
        {
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
        param.put("passFlag",passFlag);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("completeBi",completeBi);
        param.put("auditBi",auditBi);
        param.put("isNotMatch",isNotMatch);
        param.put("auditStatusId",auditStatusId);
        param.put("testId",testId);
        param.put("applyYear",applyYear);
        param.put("educationId",educationId);
        param.put("trainYear",trainYear);
        param.put("graduationMaterialName",graduationMaterialName);
        // stzyy 该省厅账号特殊处理 不允许查看审核全科和助理全科专业的学员  禅道需求316 提出人：徐开宏 2020年6月23日15:20:40
        // stqky 该省厅账号特殊处理 只允许查看审核全科和助理全科专业的学员
        List<String> speIds = getSpeIds(sysuser);
        if(speIds!=null && speIds.size() > 0)
        {
            param.put("speIds", speIds);
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryApplyList(param);
        model.addAttribute("list",list);
        String f = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResTestConfig> resTestConfigList = resTestConfigBiz.findGlobalEffective(DateUtil.getCurrDateTime2());
        if(resTestConfigList.size() > 0){
            if(applyYear.equals(DateUtil.getYear())){
                f = com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
        }
        model.addAttribute("f",f);
        model.addAttribute("roleFlag",roleFlag);
        return "jsres/asse/global/AuditList";
    }

    /**
     * 省厅已审核列表
     * @param model
     * @param roleFlag
     * @return
     */
    @RequestMapping(value="/AuditListNew")
    public String AuditListNew(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request,String applyYear,
                            String orgFlow,String trainingTypeId,String trainingSpeId,String datas[],
                            String sessionNumber,String graduationYear,String qualificationMaterialId,String passFlag,
                            String userName,String idNo,String completeBi,String auditBi,String auditStatusId,String cityId,String isNotMatch,String testId,
                            String educationId,String trainYear,String materialName,String qualificationMaterialCode, String qualificationMaterialUrl,
                            String schMonthSearch, String schMonth, String proveFileUrl, String AfterEvaluation,String skillScoreSearch,String skillScore,
                            String avgCompleteSearch,String avgComplete,String avgAuditSearch,String avgAudit,String completeTime,String qualificationName,
                            String AfterEvaluationSearch,String AfterEvaluationScale,String passFile,String tabTag,String joinOrgFlow,String isPostpone
    ){
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String>orgFlowList=new ArrayList<String>();
        SysUser sysuser=GlobalContext.getCurrentUser();
        if(StringUtil.isBlank(orgFlow))
        {
            SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
            SysOrg sysorg =new  SysOrg();
            sysorg.setOrgProvId(org.getOrgProvId());
            if (StringUtil.isNotBlank(cityId)) {
                sysorg.setOrgCityId(cityId);
            }
            param.put("org",sysorg);
        }
//        param.put("orgFlow",orgFlow);//培训基地
        if(StringUtil.isNotBlank(orgFlow)) {
            List<String> jointOrgFlows = new ArrayList<>();
            List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(orgFlow);
            jointOrgFlows.add(orgFlow);
            if(null != jointOrgList && jointOrgList.size()>0){
                for (SysOrg so:jointOrgList) {
                    jointOrgFlows.add(so.getOrgFlow());
                }
            }
            param.put("jointOrgFlows",jointOrgFlows);
        }
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
//        param.put("qualificationMaterialId",qualificationMaterialId);
        param.put("passFlag",passFlag);
        param.put("userName",userName);
        param.put("idNo",idNo);
//        param.put("completeBi",completeBi);
//        param.put("auditBi",auditBi);
        param.put("isNotMatch",isNotMatch);
        param.put("auditStatusId",auditStatusId);
        param.put("testId",testId);
        param.put("applyYear",applyYear);
        param.put("educationId",educationId);
        param.put("trainYear",trainYear);
        param.put("graduationMaterialName",materialName);
        param.put("qualificationName",qualificationName);
        param.put("qualificationMaterialCode",qualificationMaterialCode);
        param.put("qualificationMaterialUrl",qualificationMaterialUrl);
        param.put("passFile",passFile);
        param.put("tabTag",tabTag);
        param.put("roleFlag",roleFlag);
        param.put("jointOrgFlow",joinOrgFlow);
//        if("医师资格证书".equals(materialName)){
//            param.put("qualificationMaterialCode",qualificationMaterialCode);
//            param.put("qualificationMaterialUrl",qualificationMaterialUrl);
//        }else if("医师执业证书".equals(materialName)){
//            param.put("practicingCategoryCode",qualificationMaterialCode);
//            param.put("practicingCategoryUrl",qualificationMaterialUrl);
//        }
        param.put("schMonthSearch",schMonthSearch);
        param.put("schMonth",schMonth);
        param.put("proveFileUrl",proveFileUrl);
        param.put("AfterEvaluation",AfterEvaluation);
        param.put("AfterEvaluationSearch",AfterEvaluationSearch);
        param.put("AfterEvaluationScale",AfterEvaluationScale);
        param.put("isPostpone",isPostpone);
//        if(StringUtil.isNotBlank(AfterEvaluation)) {
//            param.put("AfterEvaluation", DateUtil.getDate(AfterEvaluation));
//        }
//        if(StringUtil.isNotBlank(completeTime)) {
//            String time = DateUtil.getDate(completeTime);
//            param.put("completeTime", time.substring(0,8));
//        }
        param.put("completeTime", completeTime);
        param.put("skillScoreSearch",skillScoreSearch);
        param.put("skillScore",skillScore);
        param.put("avgCompleteSearch",avgCompleteSearch);
        param.put("avgComplete",avgComplete);
        param.put("avgAuditSearch",avgAuditSearch);
        param.put("avgAudit",avgAudit);
        // stzyy 该省厅账号特殊处理 不允许查看审核全科和助理全科专业的学员  禅道需求316 提出人：徐开宏 2020年6月23日15:20:40
        // stqky 该省厅账号特殊处理 只允许查看审核全科和助理全科专业的学员
        List<String> speIds = getSpeIds(sysuser);
        if(speIds!=null && speIds.size() > 0)
        {
            param.put("speIds", speIds);
        }
        PageHelper.startPage(currentPage,getPageSize(request));
//        List<JsresGraduationInfo> list = graduationApplyBiz.queryGraduationInfoList(param);
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryApplyList(param);
        model.addAttribute("list",list);
        String f = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResTestConfig> resTestConfigList = resTestConfigBiz.findGlobalEffective(DateUtil.getCurrDateTime2());
        if(resTestConfigList.size() > 0){
            if(applyYear.equals(DateUtil.getYear())){
                f = com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
        }
        model.addAttribute("f",f);
        model.addAttribute("roleFlag",roleFlag);
        return "jsres/asse/global/AuditList";
    }

    @RequestMapping(value="/exportListNew")
    public void exportListNew(Model model,String roleFlag,HttpServletResponse response ,HttpServletRequest request,String applyYear,
                              String orgFlow,String trainingTypeId,String trainingSpeId,String datas[],String isWaitAudit,
                              String sessionNumber,String graduationYear,String qualificationMaterialId,String passFlag,
                              String userName,String idNo,String completeBi,String auditBi,String auditStatusId,String cityId,String isNotMatch,String testId,
                              String educationId,String trainYear,String materialName,String qualificationMaterialCode, String qualificationMaterialUrl,
                              String schMonthSearch, String schMonth, String proveFileUrl, String AfterEvaluation,String skillScoreSearch,String skillScore,
                              String avgCompleteSearch,String avgComplete,String avgAuditSearch,String avgAudit,String completeTime,String qualificationName,
                              String AfterEvaluationSearch,String AfterEvaluationScale,String passFile,String tabTag,String joinOrgFlow,String isPostpone
    ) throws IOException {
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String>orgFlowList=new ArrayList<String>();
        SysUser sysuser=GlobalContext.getCurrentUser();
        if(StringUtil.isBlank(orgFlow))
        {
            SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
            SysOrg sysorg =new  SysOrg();
            sysorg.setOrgProvId(org.getOrgProvId());
            if (StringUtil.isNotBlank(cityId)) {
                sysorg.setOrgCityId(cityId);
            }
            param.put("org",sysorg);
        }
//        param.put("orgFlow",orgFlow);//培训基地
        if(StringUtil.isNotBlank(orgFlow)) {
            List<String> jointOrgFlows = new ArrayList<>();
            List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(orgFlow);
            jointOrgFlows.add(orgFlow);
            if(null != jointOrgList && jointOrgList.size()>0){
                for (SysOrg so:jointOrgList) {
                    jointOrgFlows.add(so.getOrgFlow());
                }
            }
            param.put("jointOrgFlows",jointOrgFlows);
        }
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
//        param.put("qualificationMaterialId",qualificationMaterialId);
        param.put("passFlag",passFlag);
        param.put("userName",userName);
        param.put("idNo",idNo);
//        param.put("completeBi",completeBi);
//        param.put("auditBi",auditBi);
        param.put("isNotMatch",isNotMatch);
        param.put("auditStatusId",auditStatusId);
        param.put("testId",testId);
        param.put("applyYear",applyYear);
        param.put("educationId",educationId);
        param.put("trainYear",trainYear);
        param.put("graduationMaterialName",materialName);
        param.put("qualificationName",qualificationName);
        param.put("qualificationMaterialCode",qualificationMaterialCode);
        param.put("qualificationMaterialUrl",qualificationMaterialUrl);
        param.put("passFile",passFile);
        param.put("jointOrgFlow",joinOrgFlow);
        param.put("isPostpone",isPostpone);
//        if("医师资格证书".equals(materialName)){
//            param.put("qualificationMaterialCode",qualificationMaterialCode);
//            param.put("qualificationMaterialUrl",qualificationMaterialUrl);
//        }else if("医师执业证书".equals(materialName)){
//            param.put("practicingCategoryCode",qualificationMaterialCode);
//            param.put("practicingCategoryUrl",qualificationMaterialUrl);
//        }
        param.put("schMonthSearch",schMonthSearch);
        param.put("schMonth",schMonth);
        param.put("proveFileUrl",proveFileUrl);
        param.put("AfterEvaluation",AfterEvaluation);
        param.put("AfterEvaluationSearch",AfterEvaluationSearch);
        param.put("AfterEvaluationScale",AfterEvaluationScale);
//        if(StringUtil.isNotBlank(AfterEvaluation)) {
//            param.put("AfterEvaluation", DateUtil.getDate(AfterEvaluation));
//        }
//        if(StringUtil.isNotBlank(completeTime)) {
//            String time = DateUtil.getDate(completeTime);
//            param.put("completeTime", time.substring(0,8));
//        }
        param.put("completeTime", completeTime);
        param.put("skillScoreSearch",skillScoreSearch);
        param.put("skillScore",skillScore);
        param.put("avgCompleteSearch",avgCompleteSearch);
        param.put("avgComplete",avgComplete);
        param.put("avgAuditSearch",avgAuditSearch);
        param.put("avgAudit",avgAudit);
        param.put("roleFlag",roleFlag);
        param.put("tabTag",tabTag);
        // stzyy 该省厅账号特殊处理 不允许查看审核全科和助理全科专业的学员  禅道需求316 提出人：徐开宏 2020年6月23日15:20:40
        // stqky 该省厅账号特殊处理 只允许查看审核全科和助理全科专业的学员
        List<String> speIds = getSpeIds(sysuser);
        if(speIds!=null && speIds.size() > 0)
        {
            param.put("speIds", speIds);
        }
//        List<Map<String,Object>> list = graduationApplyBiz.queryGraduationInfoListExport(param);
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryListForExport(param);
//        graduationApplyBiz.chargeExportListNew(list,response,isWaitAudit);
        graduationApplyBiz.chargeExportList3(list,response,isWaitAudit,roleFlag);
    }

    @RequestMapping(value="/exportList")
    public void exportList(Model model, String roleFlag , HttpServletResponse response, HttpServletRequest request,
                             String orgFlow, String trainingTypeId, String trainingSpeId, String datas[],String applyYear,
                             String sessionNumber, String graduationYear, String qualificationMaterialId, String passFlag,
                             String userName, String idNo, String completeBi, String auditBi, String auditStatusId,String cityId,String isWaitAudit
                            ,String isNotMatch,String isNotEqual,String testId ,String educationId,String trainYear,String graduationMaterialName
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
        param.put("testId",testId);
        param.put("educationId",educationId);
        param.put("trainYear",trainYear);
        param.put("graduationMaterialName",graduationMaterialName);
        SysUser sysuser=GlobalContext.getCurrentUser();
        List<String> speIds=getSpeIds(sysuser);
        if(speIds!=null)
        {
            param.put("speIds", speIds);
        }
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryListForExport(param);
        graduationApplyBiz.chargeExportList(list,response,isWaitAudit);
    }
    @RequestMapping(value="/chargeAuditApply")
    public String chargeAuditApply(Model model,String applyFlow){
        List<JsresGraduationApplyLog> logs=graduationApplyBiz.getAuditLogsByApplyFlow(applyFlow);
        model.addAttribute("logs",logs);
        return "jsres/asse/global/chargeAuditApply";
    }

    /**
     * 省厅批量审核考核资格审查
     * @param model
     * @param applyFlow
     * @return
     */
    @RequestMapping(value="/batchAuditApply")
    public String batchAuditApply(Model model,String []applyFlow){
        model.addAttribute("size",applyFlow.length);
        model.addAttribute("flows", Arrays.asList(applyFlow));
        return "jsres/asse/global/batchAuditApply";
    }

    /**
     * 市局批量审核考核资格审查
     * @param model
     * @param applyFlow
     * @return
     */
    @RequestMapping(value="/batchChargeAuditApply")
    public String batchChargeAuditApply(Model model,String []applyFlow){
        model.addAttribute("size",applyFlow.length);
        model.addAttribute("flows", Arrays.asList(applyFlow));
        return "jsres/asse/global/batchChargeAuditApply2";
    }
    @RequestMapping(value="/chargeSaveAudit")
    @ResponseBody
    public String chargeSaveAudit(Model model,String applyFlow,String auditStatusId,String auditReason){
        JsresGraduationApply apply=graduationApplyBiz.readByFlow(applyFlow);
        if(apply!=null)
        {
            if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(apply.getCityAuditStatusId()))
            {
                return "市局已退回该学员考核资格申请信息，无法审核！";
            }
            if(StringUtil.isBlank(auditStatusId))
            {
                return "请选择审核结果！";
            }
            if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId) && StringUtil.isBlank(auditReason))
            {
                return "请输入原因！";
            }
            apply.setGlobalAuditStatusId(auditStatusId);
            apply.setGlobalAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
            apply.setGlobalReason(auditReason);
            if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId))//省厅审核不通过 直接不通过
            {
                apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalNotPassed.getId());
                apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalNotPassed.getName());
            }else{//省厅审核通过
                apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalPassed.getId());
                apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalPassed.getName());
            }
            String nowTime=DateUtil.transDateTime(DateUtil.getCurrentTime());
            JsresGraduationApplyLog log=new JsresGraduationApplyLog();
            log.setApplyFlow(applyFlow);
            log.setAuditRoleId(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL);
            log.setAuditRoleName("省厅");
            log.setAuditReason(auditReason);
            log.setAuditStatusId(auditStatusId);
            log.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
            log.setAuditTime(nowTime);
            SysUser sysuser=GlobalContext.getCurrentUser();
            log.setUserFlow(sysuser.getUserFlow());
            log.setUserName(sysuser.getUserName());
            graduationApplyBiz.saveChargeAudit(apply,log);
            return "审核成功";
        }
        return  "学员申请信息不存在，请刷新后再试！";
    }

    /**
     * 市局批量审核考核资格审查保存
     * @param model
     * @param applyFlow
     * @param auditStatusId
     * @param auditReason
     * @return
     */
    @RequestMapping(value="/batchChargeSaveAudit")
    @ResponseBody
    public String batchChargeSaveAudit(Model model,String applyFlow,String auditStatusId,String auditReason){
        JsresGraduationApply apply=graduationApplyBiz.readByFlow(applyFlow);
        if(apply!=null)
        {
            if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(apply.getLocalAuditStatusId()))
            {
                return "基地已退回该学员考核资格申请信息，无法审核！";
            }
            if(StringUtil.isBlank(auditStatusId))
            {
                return "请选择审核结果！";
            }
            if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId) && StringUtil.isBlank(auditReason))
            {
                return "请输入原因！";
            }
            apply.setCityAuditStatusId(auditStatusId);
            apply.setCityAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
            apply.setCityReason(auditReason);
            if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId))//市局审核不通过 显示市局审核不通过
            {
                apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.ChargeNotPassed.getId());
                apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.ChargeNotPassed.getName());
            }else{//市局审核通过,直接交由省厅进行审核
                apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getId());
                apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getName());
            }
            String nowTime=DateUtil.transDateTime(DateUtil.getCurrentTime());
            JsresGraduationApplyLog log=new JsresGraduationApplyLog();
            log.setApplyFlow(applyFlow);
            log.setAuditRoleId(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE);
            log.setAuditRoleName("市局");
            log.setAuditReason(auditReason);
            log.setAuditStatusId(auditStatusId);
            log.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
            log.setAuditTime(nowTime);
            SysUser sysuser=GlobalContext.getCurrentUser();
            log.setUserFlow(sysuser.getUserFlow());
            log.setUserName(sysuser.getUserName());
            graduationApplyBiz.saveChargeAudit(apply,log);
            return "审核成功";
        }
        return  "学员申请信息不存在，请刷新后再试！";
    }

    @RequestMapping(value="/batchChargeSaveAuditList")
    @ResponseBody
    public String batchChargeSaveAuditList(Model model,String []applyFlows,String auditStatusId,String auditReason){
        if(applyFlows.length>0) {
            if (StringUtil.isBlank(auditStatusId)) {
                return "请选择审核结果！";
            }
            if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId) && StringUtil.isBlank(auditReason)) {
                return "请输入原因！";
            }
            List<JsresGraduationApply> applies=new ArrayList<>();
            List<JsresGraduationApplyLog> logs=new ArrayList<>();
            for(String applyFlow:applyFlows) {
                JsresGraduationApply apply = graduationApplyBiz.readByFlow(applyFlow);
                if (apply != null) {
                    apply.setCityAuditStatusId(auditStatusId);
                    apply.setCityAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
                    apply.setCityReason(auditReason);
                    if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId))//市局审核不通过 显示市局不通过
                    {
                        apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.ChargeNotPassed.getId());
                        apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.ChargeNotPassed.getName());
                    }else{//市局审核通过,直接交由省厅进行审核
                        apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getId());
                        apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getName());
                    }
                    String nowTime = DateUtil.transDateTime(DateUtil.getCurrentTime());
                    JsresGraduationApplyLog log = new JsresGraduationApplyLog();
                    log.setApplyFlow(applyFlow);
                    log.setAuditRoleId(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE);
                    log.setAuditRoleName("市局");
                    log.setAuditReason(auditReason);
                    log.setAuditStatusId(auditStatusId);
                    log.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
                    log.setAuditTime(nowTime);
                    SysUser sysuser=GlobalContext.getCurrentUser();
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

    @RequestMapping(value="/batchSaveAudit")
    @ResponseBody
    public String batchSaveAudit(Model model,String []applyFlows,String auditStatusId,String auditReason){
        if(applyFlows.length>0) {
            if (StringUtil.isBlank(auditStatusId)) {
                return "请选择审核结果！";
            }
            if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId) && StringUtil.isBlank(auditReason)) {
                return "请输入原因！";
            }
            List<JsresGraduationApply> applies=new ArrayList<>();
            List<JsresGraduationApplyLog> logs=new ArrayList<>();
            for(String applyFlow:applyFlows) {
                JsresGraduationApply apply = graduationApplyBiz.readByFlow(applyFlow);
                if (apply != null) {
//                    if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(apply.getCityAuditStatusId())) {
//                        ResDoctorRecruit recruit=jsResDoctorRecruitBiz.readRecruit(apply.getRecruitFlow());
//                        SysUser doc=userBiz.findByFlow(recruit.getDoctorFlow());
//                        return "市局已退回学员【"+doc.getUserName()+"】考核资格申请信息，无法审核！";
//                    }
                    apply.setGlobalAuditStatusId(auditStatusId);
                    apply.setGlobalAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
                    apply.setGlobalReason(auditReason);
                    if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId))//省厅审核不通过 直接不通过
                    {
                        apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalNotPassed.getId());
                        apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalNotPassed.getName());
                    } else {//省厅审核通过
                        apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalPassed.getId());
                        apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalPassed.getName());
                    }
                    String nowTime = DateUtil.transDateTime(DateUtil.getCurrentTime());
                    JsresGraduationApplyLog log = new JsresGraduationApplyLog();
                    log.setApplyFlow(applyFlow);
                    log.setAuditRoleId(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL);
                    log.setAuditRoleName("省厅");
                    log.setAuditReason(auditReason);
                    log.setAuditStatusId(auditStatusId);
                    log.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
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
    public String AuditListMain(Model model,String roleFlag,String tabTag){
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        model.addAttribute("org",org);
        SysOrg sysorg =new  SysOrg();
        sysorg.setOrgProvId(org.getOrgProvId());
        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        sysorg.setOrgLevelId("CountryOrg");//国家基地
//        List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
        List<SysOrg> orgs=orgBiz.searchOrgListNew(sysorg);

        model.addAttribute("orgs", orgs);
        List<String> speIds=getSpeIds(sysuser);
        if(speIds!=null)
        {
            model.addAttribute("speIds", speIds);
        }
        model.addAttribute("roleFlag", roleFlag);
        List<ResTestConfig> resTestConfigs = resTestConfigBiz.findAllEffective();
        model.addAttribute("resTestConfigs", resTestConfigs);
        return "jsres/asse/global/AuditListMain";
    }

    @RequestMapping(value="/exportCountryList")
    public void exportCountryList(HttpServletResponse response ,String applyYear) throws IOException {
        //查询条件
        Map<String,Object> param=new HashMap<>();
        param.put("applyYear",DateUtil.getYear());
        SysUser sysuser = GlobalContext.getCurrentUser();
        SysOrg org = orgBiz.readSysOrg(sysuser.getOrgFlow());
        SysOrg sysorg =new  SysOrg();
        sysorg.setOrgProvId(org.getOrgProvId());
        param.put("org",sysorg);
        List<Map<String,Object>> list = graduationApplyBiz.chargeQueryListForExport2(param);
        graduationApplyBiz.exportCountryList(list,response);
    }

}
