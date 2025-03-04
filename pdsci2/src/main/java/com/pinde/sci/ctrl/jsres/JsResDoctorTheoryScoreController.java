package com.pinde.sci.ctrl.jsres;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pinde.core.common.enums.AfterRecTypeEnum;
import com.pinde.core.common.enums.TrainCategoryEnum;
import com.pinde.core.common.enums.jsres.CertificateStatusEnum;
import com.pinde.core.common.sci.dao.ResJointOrgMapper;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.service.impl.ResRecCheckConfigService;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResBaseBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.jsres.IJsResGraduationApplyBiz;
import com.pinde.sci.biz.jsres.IResTestConfigBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.core.model.JsResDoctorRecruitExt;
import com.pinde.core.model.ResJointOrg;
import com.pinde.core.model.ResPassScoreCfg;
import com.pinde.core.model.ResScore;
import com.pinde.core.model.ResTestConfig;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/jsres/doctorTheoryScore")
public class JsResDoctorTheoryScoreController extends GeneralController {

    private static Logger logger = LoggerFactory.getLogger(JsResDoctorTheoryScoreController.class);
    @Autowired
    private IJsResBaseBiz baseBiz;
    @Autowired
    private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private ResJointOrgMapper resJointOrgMapper;
    @Autowired
    private OrgBizImpl orgBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private IResRecBiz resRecBiz;
    @Autowired
    private IResScoreBiz resScoreBiz;
    @Resource
    private IJsResGraduationApplyBiz graduationApplyBiz;
    @Autowired
    private IResDoctorRecruitBiz resDoctorRecruitBiz;
    @Autowired
    private IResTestConfigBiz resTestConfigBiz;


    public static final String SCOREYEAR_NOT_FIND="请选择成绩年份";

    @RequestMapping(value="/doctorTheoryList")
    public String doctorTheoryList(Model model,String roleFlag){
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        SysOrg sysorg =new  SysOrg();
        sysorg.setOrgProvId(org.getOrgProvId());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
            sysorg.setOrgCityId(org.getOrgCityId());
        }
        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        List<SysOrg> orgs=orgBiz.searchOrgNotCountryOrg(sysorg);
        sysorg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
        List<SysOrg>  countryList=orgBiz.searchOrg(sysorg);
        sysorg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId());
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
//        System.out.println(orgFlowList.size());
//        System.out.println(countryList.size());
//        System.out.println(provinceList.size());
        model.addAttribute("orgs", orgs);
        model.addAttribute("roleFlag", roleFlag);
        //查询库中最新成绩的年份
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("scoreTypeId","TheoryScore");
        String lastYear = resScoreBiz.findLastYearByScoreTypeId(paramMap);
        model.addAttribute("lastYear", lastYear);
        //如果是国家基地，基地和协同  如果不是国家基地，那只有本基地
        model.addAttribute("org",org);
        if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
            List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
            model.addAttribute("jointOrgList",jointOrgList);
        }
        List<ResTestConfig> resTestConfigs = resTestConfigBiz.findAllEffective();
        model.addAttribute("resTestConfigs", resTestConfigs);
        return  "jsres/theoryScore/doctorTheoryList";
    }
    @RequestMapping(value="/doctorSkillList")
    public String doctorSkillList(Model model,String roleFlag){
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        SysOrg sysorg =new  SysOrg();
        sysorg.setOrgProvId(org.getOrgProvId());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
            sysorg.setOrgCityId(org.getOrgCityId());
        }
//        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
//        List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        List<SysOrg> orgs=orgBiz.searchOrgNotCountryOrg(sysorg);
        sysorg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
        List<SysOrg>  countryList=orgBiz.searchOrg(sysorg);
        sysorg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId());
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
//        System.out.println(orgFlowList.size());
//        System.out.println(countryList.size());
//        System.out.println(provinceList.size());
        model.addAttribute("orgs", orgs);
        model.addAttribute("roleFlag", roleFlag);
        //查询库中最新成绩的年份
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("scoreTypeId","SkillScore");
        String lastYear = resScoreBiz.findLastYearByScoreTypeId(paramMap);
        model.addAttribute("lastYear", lastYear);
        List<ResTestConfig> resTestConfigs = resTestConfigBiz.findAllEffective();
        model.addAttribute("resTestConfigs", resTestConfigs);
        return  "jsres/skillScore/doctorTheoryList";
    }
    @RequestMapping(value="/doctorPublicList")
    public String doctorPublicList(Model model,String roleFlag,String catSpeId){
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        SysOrg sysorg =new  SysOrg();
        sysorg.setOrgProvId(org.getOrgProvId());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
            sysorg.setOrgCityId(org.getOrgCityId());
        }
        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
        sysorg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
        List<SysOrg>  countryList=orgBiz.searchOrg(sysorg);
        sysorg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId());
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
        System.out.println(orgFlowList.size());
        System.out.println(countryList.size());
        System.out.println(provinceList.size());
        model.addAttribute("orgs", orgs);
        model.addAttribute("roleFlag", roleFlag);
        List<ResTestConfig> resTestConfigs = resTestConfigBiz.findAllEffective();
        model.addAttribute("resTestConfigs", resTestConfigs);
        return  "jsres/publicScore/doctorPublicList";
    }
    /**
     * 根据条件查询
     * @param model
     * @param currentPage 分页
     * @param roleFlag
     * @param request
     * @param doctor
     * @param user
     * @param baseId
     * @param jointOrgFlag 协同基地
     * @param derateFlag 显示减免
     * @param orgLevel  基地类型
     * @param datas  人员类型
     * @param graduationYear 结业考核年份
     * @param scoreYear 成绩年份
     * @param isHege 是否合格
     * @return
     */
    @RequestMapping(value="/doctorTheoryListSun")
    public String doctorRecruitSun(Model model, Integer currentPage, String roleFlag,
                                   HttpServletRequest request, ResDoctor doctor, SysUser user,
                                   String baseId, String jointOrgFlag, String orgFlow2,
                                   String derateFlag, String orgLevel, String[] datas,
                                   String graduationYear, String scoreYear, String isHege, String skillIsHege,
                                   String theroyScoreYear, String skillScoreYear, String orgCityId, String testId) {
        ResDoctorRecruit resDoctorRecruit= new ResDoctorRecruit();
        if(StringUtil.isNotBlank(graduationYear)){
            resDoctorRecruit.setGraduationYear(graduationYear);
        }
        //协同机构
        List<String> jointOrgFlowList=new ArrayList<String>();
        List<String>docTypeList=new ArrayList<String>();
        SysOrg org=new SysOrg();
        org.setOrgCityId(orgCityId);
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysUser sysUser =new SysUser();
        //培训基地
        if (StringUtil.isBlank(doctor.getOrgFlow())) {
            //培训基地
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
//                jointOrgFlowList=searchJointOrgList(jointOrgFlag,sysuser.getOrgFlow());
                jointOrgFlowList.add(sysuser.getOrgFlow());
            }
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)
                    || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)
                    || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SCHOOL)) {
                SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    jointOrgFlowList.add(sysuser.getOrgFlow());
                    SysOrg searchOrg=new SysOrg();
                    searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                    if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
                        searchOrg.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    //基地类型
                    if(StringUtil.isNotBlank(orgLevel)){
                        searchOrg.setOrgLevelId(orgLevel);
                        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
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
                    if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
                        org.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    if(StringUtil.isNotBlank(orgLevel)){
                        org.setOrgLevelId(orgLevel);
                        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
                    }
                }
            }
        }else if("allOrgs".equals(doctor.getOrgFlow())){
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
                jointOrgFlowList = searchJointOrgList(com.pinde.core.common.GlobalConstant.FLAG_Y, sysuser.getOrgFlow());
//                List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
//                if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
//                    for(ResJointOrg jointOrg:resJointOrgList){
//                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
//                    }
//                }
                jointOrgFlowList.add(sysuser.getOrgFlow());
            }
        }else{
            jointOrgFlowList.add(doctor.getOrgFlow());
            //是否勾选了协同机构
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
                if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                    for(ResJointOrg jointOrg:resJointOrgList){
                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }

        if(StringUtil.isNotBlank(orgFlow2)){
            jointOrgFlowList.add(orgFlow2);
            List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(orgFlow2);
            if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                for(ResJointOrg jointOrg:resJointOrgList){
                    jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                }
            }
        }

        //证件号
        if(StringUtil.isNotBlank(user.getIdNo())){
            sysUser.setIdNo(user.getIdNo());
        }
        //姓名
        sysUser.setUserName(user.getUserName());
        //显示减免
        if(StringUtil.isNotBlank(derateFlag)){
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(derateFlag)) {
                doctor.setTrainingTypeId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
                resDoctorRecruit.setTrainYear(com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId());
            }else{
                derateFlag="";
            }
        }else{
            derateFlag="";
        }
        //人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }


        //根据年份从res_pass_score_cfg取数据
        ResPassScoreCfg cfg = new ResPassScoreCfg();
        cfg.setCfgYear(scoreYear);
        ResPassScoreCfg resPassScoreCfg = baseBiz.readResPassScoreCfg(cfg);
        String hegeScore="60";
        if(resPassScoreCfg!=null){
            hegeScore = resPassScoreCfg.getCfgPassScore();
            if(StringUtil.isBlank(hegeScore)){
                hegeScore="60";
            }
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        resDoctorRecruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
        //resDoctorRecruit=null;
       // doctor=null; sysUser=null; org=null; jointOrgFlowList=null; derateFlag=null;scoreYear=null;isHege=null;docTypeList=null;
        List<JsResDoctorRecruitExt> doctorList=null;
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SCHOOL.equals(roleFlag)) {
            ServletContext servletContext=request.getServletContext();
            if (servletContext!=null) {
                List<SysDict> sysDictList=(List<SysDict>) servletContext.getAttribute("dictTypeEnum"+"SendSchool"+"List");
                for (SysDict sysDict : sysDictList) {
                    String dictId="send_school_"+sysDict.getDictId()+"_org_rel";
                    String orgFlow=InitConfig.getSysCfg(dictId);
                    doctor.setWorkOrgId(sysDict.getDictId());
                    if (sysuser.getOrgFlow().equals(orgFlow)) {
                        doctorList=jsResDoctorRecruitBiz.searchDoctorScoreInfoExts(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,docTypeList,hegeScore,testId);
                        break;
                    }else{
                        continue;
                    }
                }
            }
        }else  {
            doctorList = jsResDoctorRecruitBiz.searchDoctorTheoryScore(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,docTypeList,testId);
        }

        model.addAttribute("roleFlag",roleFlag);
        model.addAttribute("doctorList",doctorList);
        model.addAttribute("datas",datas);
        model.addAttribute("scoreYear",scoreYear);
        return  "jsres/theoryScore/doctorListZi";
    }

    @RequestMapping(value="/doctorTheoryListSunExport")
    public void doctorTheoryListSunExport(String roleFlag,HttpServletResponse response,
                                   HttpServletRequest request,ResDoctor doctor,SysUser user,
                                   String baseId,String jointOrgFlag,String orgFlow2,
                                   String derateFlag,String orgLevel,String[] datas,
                                   String graduationYear,String scoreYear,String isHege,
                                   String skillIsHege,String  theroyScoreYear, String  skillScoreYear,String orgCityId,String testId) throws Exception {
        ResDoctorRecruit resDoctorRecruit= new ResDoctorRecruit();
        if(StringUtil.isNotBlank(graduationYear)){
            resDoctorRecruit.setGraduationYear(graduationYear);
        }
        //协同机构
        List<String> jointOrgFlowList=new ArrayList<String>();
        List<String>docTypeList=new ArrayList<String>();
        SysOrg org=new SysOrg();
        org.setOrgCityId(orgCityId);
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysUser sysUser =new SysUser();
        //培训基地
        if (StringUtil.isBlank(doctor.getOrgFlow())) {
            //培训基地
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
//                jointOrgFlowList=searchJointOrgList(jointOrgFlag,sysuser.getOrgFlow());
                jointOrgFlowList.add(sysuser.getOrgFlow());
            }
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)
                    || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)
                    || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SCHOOL)) {
                SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    jointOrgFlowList.add(sysuser.getOrgFlow());
                    SysOrg searchOrg=new SysOrg();
                    searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                    if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
                        searchOrg.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    //基地类型
                    if(StringUtil.isNotBlank(orgLevel)){
                        searchOrg.setOrgLevelId(orgLevel);
                        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
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
                    if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
                        org.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    if(StringUtil.isNotBlank(orgLevel)){
                        org.setOrgLevelId(orgLevel);
                        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
                    }
                }
            }
        }else if("allOrgs".equals(doctor.getOrgFlow())){
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
                jointOrgFlowList = searchJointOrgList(com.pinde.core.common.GlobalConstant.FLAG_Y, sysuser.getOrgFlow());
                jointOrgFlowList.add(sysuser.getOrgFlow());
            }
        }else{
            jointOrgFlowList.add(doctor.getOrgFlow());
            //是否勾选了协同机构
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
                if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                    for(ResJointOrg jointOrg:resJointOrgList){
                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }

        if(StringUtil.isNotBlank(orgFlow2)){
            jointOrgFlowList.add(orgFlow2);
            List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(orgFlow2);
            if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                for(ResJointOrg jointOrg:resJointOrgList){
                    jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                }
            }
        }

        //证件号
        if(StringUtil.isNotBlank(user.getIdNo())){
            sysUser.setIdNo(user.getIdNo());
        }
        //姓名
        sysUser.setUserName(user.getUserName());
        //显示减免
        if(StringUtil.isNotBlank(derateFlag)){
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(derateFlag)) {
                doctor.setTrainingTypeId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
                resDoctorRecruit.setTrainYear(com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId());
            }else{
                derateFlag="";
            }
        }else{
            derateFlag="";
        }
        //人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }


        //根据年份从res_pass_score_cfg取数据
        ResPassScoreCfg cfg = new ResPassScoreCfg();
        cfg.setCfgYear(scoreYear);
        ResPassScoreCfg resPassScoreCfg = baseBiz.readResPassScoreCfg(cfg);
        String hegeScore="60";
        if(resPassScoreCfg!=null){
            hegeScore = resPassScoreCfg.getCfgPassScore();
            if(StringUtil.isBlank(hegeScore)){
                hegeScore="60";
            }
        }

        resDoctorRecruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
        //resDoctorRecruit=null;
        // doctor=null; sysUser=null; org=null; jointOrgFlowList=null; derateFlag=null;scoreYear=null;isHege=null;docTypeList=null;
        List<JsResDoctorRecruitExt> doctorList=null;
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SCHOOL.equals(roleFlag)) {
            ServletContext servletContext=request.getServletContext();
            if (servletContext!=null) {
                List<SysDict> sysDictList=(List<SysDict>) servletContext.getAttribute("dictTypeEnum"+"SendSchool"+"List");
                for (SysDict sysDict : sysDictList) {
                    String dictId="send_school_"+sysDict.getDictId()+"_org_rel";
                    String orgFlow=InitConfig.getSysCfg(dictId);
                    doctor.setWorkOrgId(sysDict.getDictId());
                    if (sysuser.getOrgFlow().equals(orgFlow)) {
                        doctorList=jsResDoctorRecruitBiz.searchDoctorScoreInfoExts(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,docTypeList,hegeScore,testId);
                        break;
                    }else{
                        continue;
                    }
                }
            }
        }else  {
            doctorList = jsResDoctorRecruitBiz.searchDoctorTheoryScore(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,docTypeList,testId);
        }
        List<Map<String,Object>> resultMapList = new ArrayList<>();
        if(doctorList!=null&&doctorList.size()>0){
            for(JsResDoctorRecruitExt doctorRecruitExt:doctorList){
                String doctorName = doctorRecruitExt.getSysUser().getUserName();
                String idNo = doctorRecruitExt.getSysUser().getIdNo();
                String cretTypeName = doctorRecruitExt.getSysUser().getCretTypeName();
                String orgName = doctorRecruitExt.getOrgName();
                String sessionNumber = doctorRecruitExt.getSessionNumber();
                String catSpeName = doctorRecruitExt.getCatSpeName();
                String speName = doctorRecruitExt.getSpeName();
                String placeName = doctorRecruitExt.getPlaceName();
                String changeSpeName = doctorRecruitExt.getChangeSpeName();
                String theoryTestId = doctorRecruitExt.getTheoryTestId();
                String extGraduationYear = doctorRecruitExt.getGraduationYear();
                String year1 = scoreYear;
                String year2 = scoreYear;
                if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
                    year1 = doctorRecruitExt.getResScore().getScorePhaseId();
                    year2 = doctorRecruitExt.getResScore().getScorePhaseName();
                }
                String realScore = doctorRecruitExt.getResScore().getRealScore();
                String theoryScore = null==doctorRecruitExt.getResScore().getTheoryScore()?"":doctorRecruitExt.getResScore().getTheoryScore()+"";
                if("1".equals(theoryScore)){
                    theoryScore="合格";
                }
                if("0".equals(theoryScore)){
                    theoryScore="不合格";
                }
                if("2".equals(theoryScore)){
                    theoryScore="缺考";
                }
                String isPass = "";
                //技能成绩
                List<ResScore> scorelist=resScoreBiz.selectByExampleWithBLOBs(doctorRecruitExt.getSysUser().getUserFlow());
                List<ResScore> skillList=new ArrayList<ResScore>();
                List<ResScore> theoryList=new ArrayList<ResScore>();
                List<ResScore> skillListByYear=new ArrayList<ResScore>();

                if(null!=scorelist&&scorelist.size()>0)
                {
                    for(ResScore resScore:scorelist)
                    {

                        if (com.pinde.core.common.enums.ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(scoreYear) && scoreYear.equals(resScore.getScorePhaseId()))
                        {
                            skillListByYear.add(resScore);
                        } else if (com.pinde.core.common.enums.ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId()))
                        {
                            skillList.add(resScore);
                        } else if (com.pinde.core.common.enums.ResScoreTypeEnum.TheoryScore.getId().equals(resScore.getScoreTypeId()))
                        {
                            theoryList.add(resScore);
                        }
                    }
                }
                if(skillListByYear.size()>0)
                {
                    skillList=skillListByYear;
                }
                Map<String,Map<String,String>> extScoreMap=new HashMap<String,Map<String,String>>();
                for(int i=0;i<theoryList.size();i++)
                {
                    Map<String,String> extScore=new HashMap<String,String>();
                    ResScore resScore=theoryList.get(i);
                    String content = null==resScore ? "":resScore.getExtScore();
                    if(StringUtil.isNotBlank(content)) {
                        Document doc = DocumentHelper.parseText(content);
                        Element root = doc.getRootElement();
                        Element extScoreInfo = root.element("extScoreInfo");
                        if (extScoreInfo != null) {
                            List<Element> extInfoAttrEles = extScoreInfo.elements();
                            if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                                for (Element attr : extInfoAttrEles) {
                                    String attrName = attr.getName();
                                    String attrValue = attr.getText();
                                    extScore.put(attrName, attrValue);
                                }
                            }
                        }
                    }
                    extScoreMap.put(resScore.getScoreFlow(),extScore);
                }
                for(ResScore score:theoryList){
                    Map<String,String> extScore = extScoreMap.get(score.getScoreFlow());
//                    isPass = score.getSkillScore().intValue()==(com.pinde.core.common.GlobalConstant.PASS)?"是":(score.getSkillScore().intValue()==(com.pinde.core.common.GlobalConstant.UNPASS)?"否":"");
                    if (score.getTheoryScore().intValue() == com.pinde.core.common.GlobalConstant.PASS) {
                        isPass="合格";
                    } else if (score.getTheoryScore().intValue() == com.pinde.core.common.GlobalConstant.UNPASS) {
                        isPass="不合格";
                    }else if(score.getTheoryScore().intValue()==2){
                        isPass="缺考";
                    }else{
                        isPass="";
                    }
                }
                Map<String,Object> resultMap = new HashMap<>();
                resultMap.put("doctorName",doctorName);
                resultMap.put("cretTypeName",cretTypeName);
                resultMap.put("idNo",idNo);
                resultMap.put("orgName",orgName);
                resultMap.put("sessionNumber",sessionNumber);
                resultMap.put("catSpeName",catSpeName);
                resultMap.put("graduationYear",extGraduationYear);
                resultMap.put("changeSpeName",changeSpeName);
                resultMap.put("placeName",placeName);
                resultMap.put("theoryTestId",theoryTestId);
                resultMap.put("speName",speName);
                resultMap.put("year1",year1);
                resultMap.put("year2",year2);
                resultMap.put("theoryScore",theoryScore);
                resultMap.put("isPass",isPass);
                resultMap.put("realScore",realScore);
                resultMapList.add(resultMap);
            }
        }
        String fileName = "理论成绩信息汇总表.xls";
        String titles[] = {
                "theoryTestId:考试编号",
                "doctorName:姓名",
                "cretTypeName:证件类型",
                "idNo:证件号码",
                "orgName:培训基地",
                "placeName:地市",
                "sessionNumber:年级",
                "graduationYear:结业年份",
                "catSpeName:培训类别",
                "speName:培训专业",
                "realScore:理论成绩",
                "theoryScore:是否合格"
        };
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjsAllString(titles, resultMapList, response.getOutputStream());
    }

    /**
     * 根据条件查询
     * @param model
     * @param currentPage 分页
     * @param roleFlag
     * @param request
     * @param doctor
     * @param user
     * @param baseId
     * @param jointOrgFlag 协同基地
     * @param derateFlag 显示减免
     * @param orgLevel  基地类型
     * @param datas  人员类型
     * @param graduationYear 结业考核年份
     * @param scoreYear 成绩年份
     * @param isHege 是否合格
     * @return
     */
    @RequestMapping(value="/doctorSkillListSun")
    public String doctorSkillListSun(Model model,Integer currentPage,String roleFlag,
                                   HttpServletRequest request,ResDoctor doctor,SysUser user,
                                   String baseId,String jointOrgFlag,String orgFlow2,
                                   String derateFlag,String orgLevel,String[] datas,
                                   String graduationYear,String scoreYear,String isHege,String orgCityId,String testId) throws DocumentException {
        ResDoctorRecruit resDoctorRecruit= new ResDoctorRecruit();
        if(StringUtil.isNotBlank(graduationYear)){
            resDoctorRecruit.setGraduationYear(graduationYear);
        }
        //协同机构
        List<String> jointOrgFlowList=new ArrayList<String>();
        List<String>docTypeList=new ArrayList<String>();
        SysOrg org=new SysOrg();
        org.setOrgCityId(orgCityId);
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysUser sysUser =new SysUser();
        //培训基地
        if (StringUtil.isBlank(doctor.getOrgFlow())) {
            //培训基地
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
                jointOrgFlowList=searchJointOrgList(jointOrgFlag,sysuser.getOrgFlow());
                jointOrgFlowList.add(sysuser.getOrgFlow());
            }
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)
                    || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)
                    || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SCHOOL)) {
                SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    jointOrgFlowList.add(sysuser.getOrgFlow());
                    SysOrg searchOrg=new SysOrg();
                    searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                    if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
                        searchOrg.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    //基地类型
                    if(StringUtil.isNotBlank(orgLevel)){
                        searchOrg.setOrgLevelId(orgLevel);
                        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
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
                    if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
                        org.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    if(StringUtil.isNotBlank(orgLevel)){
                        org.setOrgLevelId(orgLevel);
                        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
                    }
                }
            }
        }else{
            jointOrgFlowList.add(doctor.getOrgFlow());
            //是否勾选了协同机构
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
                if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                    for(ResJointOrg jointOrg:resJointOrgList){
                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }

        if(StringUtil.isNotBlank(orgFlow2)){
            jointOrgFlowList.add(orgFlow2);
            List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(orgFlow2);
            if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                for(ResJointOrg jointOrg:resJointOrgList){
                    jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                }
            }
        }

        //证件号
        if(StringUtil.isNotBlank(user.getIdNo())){
            sysUser.setIdNo(user.getIdNo());
        }
        //姓名
        sysUser.setUserName(user.getUserName());
        //显示减免
        if(StringUtil.isNotBlank(derateFlag)){
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(derateFlag)) {
                doctor.setTrainingTypeId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
                resDoctorRecruit.setTrainYear(com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId());
            }else{
                derateFlag="";
            }
        }else{
            derateFlag="";
        }
        //人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        //成绩类型为技能成绩

        PageHelper.startPage(currentPage,getPageSize(request));
        resDoctorRecruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());

        //resDoctorRecruit=null;
       // doctor=null; sysUser=null; org=null; jointOrgFlowList=null; derateFlag=null;scoreYear=null;isHege=null;docTypeList=null;
        List<JsResDoctorRecruitExt> doctorList=null;
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SCHOOL.equals(roleFlag)) {
            ServletContext servletContext=request.getServletContext();
            if (servletContext!=null) {
                List<SysDict> sysDictList=(List<SysDict>) servletContext.getAttribute("dictTypeEnum"+"SendSchool"+"List");
                for (SysDict sysDict : sysDictList) {
                    String dictId="send_school_"+sysDict.getDictId()+"_org_rel";
                    String orgFlow=InitConfig.getSysCfg(dictId);
                    doctor.setWorkOrgId(sysDict.getDictId());
                    if (sysuser.getOrgFlow().equals(orgFlow)) {
                        doctorList=jsResDoctorRecruitBiz.searchDoctorSkillScore(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,docTypeList,testId);
                        break;
                    }else{
                        continue;
                    }
                }
            }
        }else{
            doctorList = jsResDoctorRecruitBiz.searchDoctorSkillScore(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,docTypeList,testId);
        }
        Map<String,Map<String,String>> extScoreMap=new HashMap<String,Map<String,String>>();
        for(int i=0;i<doctorList.size();i++)
        {
            Map<String,String> extScore=new HashMap<String,String>();
            JsResDoctorRecruitExt ext=doctorList.get(i);
            ResScore resScore=ext.getResScore();
            String content = null==resScore ? "":resScore.getExtScore();
            if(StringUtil.isNotBlank(content)) {
                Document doc = DocumentHelper.parseText(content);
                Element root = doc.getRootElement();
                Element extScoreInfo = root.element("extScoreInfo");
                if (extScoreInfo != null) {
                    List<Element> extInfoAttrEles = extScoreInfo.elements();
                    if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                        for (Element attr : extInfoAttrEles) {
                            String attrName = attr.getName();
                            String attrValue = attr.getText();
                            extScore.put(attrName, attrValue);
                        }
                    }
                }
            }
            extScoreMap.put(resScore.getScoreFlow(),extScore);
        }
        model.addAttribute("roleFlag",roleFlag);
        model.addAttribute("doctorList",doctorList);
        model.addAttribute("extScoreMap",extScoreMap);
        model.addAttribute("datas",datas);
        model.addAttribute("scoreYear",scoreYear);
        return  "jsres/skillScore/doctorListZi";
    }


    /**
     * 技能成绩导出
     * @param model
     * @param currentPage
     * @param roleFlag
     * @param response
     * @param request
     * @param doctor
     * @param user
     * @param baseId
     * @param jointOrgFlag
     * @param derateFlag
     * @param orgLevel
     * @param datas
     * @param graduationYear
     * @param scoreYear
     * @param isHege
     * @param orgCityId
     * @param testId
     * @throws Exception
     */
    @RequestMapping("/doctorSkillListSunExport")
    public void doctorSkillListSunExport(Model model, Integer currentPage, String roleFlag, HttpServletResponse response,
                                         HttpServletRequest request, ResDoctor doctor, SysUser user,
                                         String baseId, String jointOrgFlag,String orgFlow2,
                                         String derateFlag, String orgLevel, String[] datas,
                                         String graduationYear, String scoreYear, String isHege, String orgCityId, String testId) throws Exception {
        ResDoctorRecruit resDoctorRecruit = new ResDoctorRecruit();
        if (StringUtil.isNotBlank(graduationYear)) {
            resDoctorRecruit.setGraduationYear(graduationYear);
        }
        //协同机构
        List<String> jointOrgFlowList = new ArrayList<String>();
        List<String> docTypeList = new ArrayList<String>();
        SysOrg org = new SysOrg();
        org.setOrgCityId(orgCityId);
        SysUser sysuser = GlobalContext.getCurrentUser();
        SysUser sysUser = new SysUser();
        //培训基地
        if (StringUtil.isBlank(doctor.getOrgFlow())) {
            //培训基地
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
                jointOrgFlowList = searchJointOrgList(jointOrgFlag, sysuser.getOrgFlow());
                jointOrgFlowList.add(sysuser.getOrgFlow());
            }
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)
                    || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)
                    || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SCHOOL)) {
                SysOrg sysOrg = orgBiz.readSysOrg(sysuser.getOrgFlow());
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    jointOrgFlowList.add(sysuser.getOrgFlow());
                    SysOrg searchOrg = new SysOrg();
                    searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                    if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
                        searchOrg.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    //基地类型
                    if (StringUtil.isNotBlank(orgLevel)) {
                        searchOrg.setOrgLevelId(orgLevel);
                        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
                    }
                    List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);
                    for (SysOrg g : exitOrgs) {
                        List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
                        if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                            for (ResJointOrg jointOrg : resJointOrgList) {
                                jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                            }
                        }
                        jointOrgFlowList.add(g.getOrgFlow());
                    }
                } else {
                    org.setOrgProvId(sysOrg.getOrgProvId());
                    if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
                        org.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    if (StringUtil.isNotBlank(orgLevel)) {
                        org.setOrgLevelId(orgLevel);
                        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
                    }
                }
            }
        } else {
            jointOrgFlowList.add(doctor.getOrgFlow());
            //是否勾选了协同机构
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
                if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                    for (ResJointOrg jointOrg : resJointOrgList) {
                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }

        if(StringUtil.isNotBlank(orgFlow2)){
            jointOrgFlowList.add(orgFlow2);
            List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(orgFlow2);
            if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                for(ResJointOrg jointOrg:resJointOrgList){
                    jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                }
            }
        }

        //证件号
        if (StringUtil.isNotBlank(user.getIdNo())) {
            sysUser.setIdNo(user.getIdNo());
        }
        //姓名
        sysUser.setUserName(user.getUserName());
        //显示减免
        if (StringUtil.isNotBlank(derateFlag)) {
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(derateFlag)) {
                doctor.setTrainingTypeId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
                resDoctorRecruit.setTrainYear(com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId());
            } else {
                derateFlag = "";
            }
        } else {
            derateFlag = "";
        }
        //人员类型
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        }
        //成绩类型为技能成绩

        //PageHelper.startPage(currentPage, getPageSize(request));
        resDoctorRecruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());

        //resDoctorRecruit=null;
        // doctor=null; sysUser=null; org=null; jointOrgFlowList=null; derateFlag=null;scoreYear=null;isHege=null;docTypeList=null;
        List<JsResDoctorRecruitExt> doctorList = null;
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SCHOOL.equals(roleFlag)) {
            ServletContext servletContext = request.getServletContext();
            if (servletContext != null) {
                List<SysDict> sysDictList = (List<SysDict>) servletContext.getAttribute("dictTypeEnum" + "SendSchool" + "List");
                for (SysDict sysDict : sysDictList) {
                    String dictId = "send_school_" + sysDict.getDictId() + "_org_rel";
                    String orgFlow = InitConfig.getSysCfg(dictId);
                    doctor.setWorkOrgId(sysDict.getDictId());
                    if (sysuser.getOrgFlow().equals(orgFlow)) {
                        doctorList = jsResDoctorRecruitBiz.searchDoctorSkillScore(resDoctorRecruit, doctor, sysUser, org, jointOrgFlowList, derateFlag, scoreYear, isHege, docTypeList, testId);
                        break;
                    } else {
                        continue;
                    }
                }
            }
        } else {
            doctorList = jsResDoctorRecruitBiz.searchDoctorSkillScore(resDoctorRecruit, doctor, sysUser, org, jointOrgFlowList, derateFlag, scoreYear, isHege, docTypeList, testId);
        }
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        if (doctorList != null && doctorList.size() > 0) {
            for (JsResDoctorRecruitExt doctorRecruitExt : doctorList) {
                String doctorName = doctorRecruitExt.getSysUser().getUserName();
                String idNo = doctorRecruitExt.getSysUser().getIdNo();
                String cretTypeName = doctorRecruitExt.getSysUser().getCretTypeName();
                String orgName = doctorRecruitExt.getOrgName();
                String sessionNumber = doctorRecruitExt.getSessionNumber();
                String catSpeName = doctorRecruitExt.getCatSpeName();
                String speName = doctorRecruitExt.getSpeName();
                String placeName = doctorRecruitExt.getPlaceName();
                String changeSpeName = doctorRecruitExt.getChangeSpeName();
                String skillTestId = doctorRecruitExt.getSkillTestId();
                String extGraduationYear = doctorRecruitExt.getGraduationYear();
                String realScore = doctorRecruitExt.getResScore().getRealScore();
                String skillScore = null == doctorRecruitExt.getResScore().getSkillScore() ? "" : doctorRecruitExt.getResScore().getSkillScore() + "";
                if ("1".equals(skillScore)) {
                    skillScore = "合格";
                }
                if ("0".equals(skillScore)) {
                    skillScore = "不合格";
                }
                if ("2".equals(skillScore)) {
                    skillScore = "缺考";
                }
                String isPass = "";
                //技能成绩
                List<ResScore> scorelist = resScoreBiz.selectByExampleWithBLOBs(doctorRecruitExt.getSysUser().getUserFlow());
                List<ResScore> skillList = new ArrayList<ResScore>();
                List<ResScore> theoryList = new ArrayList<ResScore>();
                List<ResScore> skillListByYear = new ArrayList<ResScore>();

                if (null != scorelist && scorelist.size() > 0) {
                    for (ResScore resScore : scorelist) {

                        if (com.pinde.core.common.enums.ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(scoreYear) && scoreYear.equals(resScore.getScorePhaseId())) {
                            skillListByYear.add(resScore);
                        } else if (com.pinde.core.common.enums.ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId())) {
                            skillList.add(resScore);
                        } else if (com.pinde.core.common.enums.ResScoreTypeEnum.TheoryScore.getId().equals(resScore.getScoreTypeId())) {
                            theoryList.add(resScore);
                        }
                    }
                }
                if (skillListByYear.size() > 0) {
                    skillList = skillListByYear;
                }
                Map<String, Map<String, String>> extScoreMap = new HashMap<String, Map<String, String>>();
                for (int i = 0; i < skillList.size(); i++) {
                    Map<String, String> extScore = new HashMap<String, String>();
                    ResScore resScore = skillList.get(i);
                    String content = null == resScore ? "" : resScore.getExtScore();
                    if (StringUtil.isNotBlank(content)) {
                        Document doc = DocumentHelper.parseText(content);
                        Element root = doc.getRootElement();
                        Element extScoreInfo = root.element("extScoreInfo");
                        if (extScoreInfo != null) {
                            List<Element> extInfoAttrEles = extScoreInfo.elements();
                            if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                                for (Element attr : extInfoAttrEles) {
                                    String attrName = attr.getName();
                                    String attrValue = attr.getText();
                                    extScore.put(attrName, attrValue);
                                }
                            }
                        }
                    }
                    extScoreMap.put(resScore.getScoreFlow(), extScore);
                }
                for (ResScore score : skillList) {
                    Map<String, String> extScore = extScoreMap.get(score.getScoreFlow());
//                    isPass = score.getSkillScore().intValue()==(com.pinde.core.common.GlobalConstant.PASS)?"是":(score.getSkillScore().intValue()==(com.pinde.core.common.GlobalConstant.UNPASS)?"否":"");
                    if (score.getSkillScore().intValue() == com.pinde.core.common.GlobalConstant.PASS) {
                        isPass = "合格";
                    } else if (score.getSkillScore().intValue() == com.pinde.core.common.GlobalConstant.UNPASS) {
                        isPass = "不合格";
                    } else if (score.getSkillScore().intValue() == 2) {
                        isPass = "缺考";
                    } else {
                        isPass = "";
                    }
                }
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("doctorName", doctorName);
                resultMap.put("cretTypeName",cretTypeName);
                resultMap.put("idNo", idNo);
                resultMap.put("orgName", orgName);
                resultMap.put("sessionNumber", sessionNumber);
                resultMap.put("catSpeName", catSpeName);
                resultMap.put("graduationYear", extGraduationYear);
                resultMap.put("changeSpeName", changeSpeName);
                resultMap.put("placeName", placeName);
                resultMap.put("skillTestId", skillTestId);
                resultMap.put("speName", speName);
                resultMap.put("skillScore", skillScore);
                resultMap.put("isPass", isPass);
                resultMap.put("realScore", realScore);
                resultMapList.add(resultMap);
            }
        }
        String fileName = "技能成绩信息汇总表.xls";
        String titles[] = {
                "skillTestId:考试编号",
                "doctorName:姓名",
                "cretTypeName:证件类型",
                "idNo:证件号码",
                "orgName:培训基地",
                "placeName:地市",
                "sessionNumber:年级",
                "graduationYear:结业年份",
                "catSpeName:培训类别",
                "speName:培训专业",
                "realScore:技能成绩",
                "skillScore:是否合格"
        };
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjsAllString(titles, resultMapList, response.getOutputStream());
    }
    @RequestMapping(value="/doctorPublicListSun")
    public String doctorPublicListSun(Model model,Integer currentPage,String roleFlag,
                                   HttpServletRequest request,ResDoctor doctor,SysUser user,
                                   String baseId,String jointOrgFlag,
                                   String derateFlag,String orgLevel,String[] datas,
                                   String graduationYear,String scoreYear,String notAllQualified,String allQualified) throws DocumentException {
        ResDoctorRecruit resDoctorRecruit= new ResDoctorRecruit();
        if(StringUtil.isNotBlank(graduationYear)){
            resDoctorRecruit.setGraduationYear(graduationYear);
        }
        //协同机构
        List<String> jointOrgFlowList=new ArrayList<String>();
        List<String>docTypeList=new ArrayList<String>();
        SysOrg org=new SysOrg();
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysUser sysUser =new SysUser();
        //培训基地
        if (StringUtil.isBlank(doctor.getOrgFlow())) {
            //培训基地
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
                jointOrgFlowList=searchJointOrgList(jointOrgFlag,sysuser.getOrgFlow());
                jointOrgFlowList.add(sysuser.getOrgFlow());
            }
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)
                    || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)
                    || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SCHOOL)) {
                SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    jointOrgFlowList.add(sysuser.getOrgFlow());
                    SysOrg searchOrg=new SysOrg();
                    searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                    if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
                        searchOrg.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    //基地类型
                    if(StringUtil.isNotBlank(orgLevel)){
                        searchOrg.setOrgLevelId(orgLevel);
                        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
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
                    if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
                        org.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    if(StringUtil.isNotBlank(orgLevel)){
                        org.setOrgLevelId(orgLevel);
                        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
                    }
                }
            }
        }else{
            jointOrgFlowList.add(doctor.getOrgFlow());
            //是否勾选了协同机构
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
                if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                    for(ResJointOrg jointOrg:resJointOrgList){
                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }
        //证件号
        if(StringUtil.isNotBlank(user.getIdNo())){
            sysUser.setIdNo(user.getIdNo());
        }
        //姓名
        sysUser.setUserName(user.getUserName());
        //显示减免
        if(StringUtil.isNotBlank(derateFlag)){
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(derateFlag)) {
                doctor.setTrainingTypeId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
                resDoctorRecruit.setTrainYear(com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId());
            }else{
                derateFlag="";
            }
        }else{
            derateFlag="";
        }
        //人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        //成绩类型为技能成绩

        PageHelper.startPage(currentPage,getPageSize(request));
        resDoctorRecruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());

        List<String> sessionNumbers=new ArrayList<String>();//年级多选
        if(StringUtil.isNotBlank(doctor.getSessionNumber())){
            String[] numbers=doctor.getSessionNumber().split(",");
            if(numbers!=null&&numbers.length>0){
                sessionNumbers= Arrays.asList(numbers);
                doctor.setSessionNumber("");
            }
        }
        List<JsResDoctorRecruitExt> doctorList=null;
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SCHOOL.equals(roleFlag)) {
            ServletContext servletContext=request.getServletContext();
            if (servletContext!=null) {
                List<SysDict> sysDictList=(List<SysDict>) servletContext.getAttribute("dictTypeEnum"+"SendSchool"+"List");
                for (SysDict sysDict : sysDictList) {
                    String dictId="send_school_"+sysDict.getDictId()+"_org_rel";
                    String orgFlow=InitConfig.getSysCfg(dictId);
                    doctor.setWorkOrgId(sysDict.getDictId());
                    if (sysuser.getOrgFlow().equals(orgFlow)) {
                        doctorList=jsResDoctorRecruitBiz.searchDoctorPublicScore1(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,notAllQualified,allQualified,docTypeList,sessionNumbers);
                        break;
                    }else{
                        continue;
                    }
                }
            }
        }else{
            doctorList = jsResDoctorRecruitBiz.searchDoctorPublicScore1(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,notAllQualified,allQualified,docTypeList,sessionNumbers);
        }
        Map<String,Map<String,String>> extScoreMap=new HashMap<String,Map<String,String>>();
        for(int i=0;i<doctorList.size();i++)
        {
            Map<String,String> extScore=new HashMap<String,String>();
            JsResDoctorRecruitExt ext=doctorList.get(i);
            ResScore resScore=ext.getResScore();
            String content = null==resScore ? "":resScore.getExtScore();
            if(StringUtil.isNotBlank(content)) {
                Document doc = DocumentHelper.parseText(content);
                Element root = doc.getRootElement();
                Element extScoreInfo = root.element("extScoreInfo");
                if (extScoreInfo != null) {
                    List<Element> extInfoAttrEles = extScoreInfo.elements();
                    if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                        for (Element attr : extInfoAttrEles) {
                            String attrName = attr.getName();
                            String attrValue = attr.getText();
                            extScore.put(attrName, attrValue);
                        }
                    }
                }
            }
            extScoreMap.put(resScore.getScoreFlow(),extScore);
        }
        model.addAttribute("roleFlag",roleFlag);
        model.addAttribute("doctorList",doctorList);
        model.addAttribute("extScoreMap",extScoreMap);
        model.addAttribute("datas",datas);
        model.addAttribute("scoreYear",scoreYear);
        return  "jsres/publicScore/doctorListZi";
    }
    public List<String> searchJointOrgList(String flag,String Flow) {
        List<String> jointOrgFlowList=new ArrayList<String>();
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(flag)) {
            List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(Flow);
            if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                for(ResJointOrg jointOrg:resJointOrgList){
                    jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                }
            }
        }
        return jointOrgFlowList;
    }
    /**
     * 理论成绩导入页面
     *
     */
    @RequestMapping(value="/importSchoolRoll")
    public String importSchoolRoll(String trainingTypeId, Model model){
        model.addAttribute("trainingTypeId", trainingTypeId);
        return "jsres/theoryScore/importSchoolRoll";
    }
    @RequestMapping(value="/importSkillScore")
    public String importSkillScore(String trainingTypeId, Model model){
        model.addAttribute("trainingTypeId", trainingTypeId);
        return "jsres/skillScore/importSchoolRoll";
    }
    @RequestMapping(value="/importPublicScore")
    public String importPublicScore(){
        return "jsres/publicScore/importSchoolRoll";
    }
    /**
     * 理论成绩导入
     */
    @RequestMapping(value="/importSchoolRollFromExcel")
    @ResponseBody
    public String importSchoolRollFromExcel(MultipartFile file,String scoreYear, String trainingTypeId){
        if(StringUtil.isBlank(scoreYear))
        {
            return SCOREYEAR_NOT_FIND;
        }
        if(file.getSize() > 0){
            try{
                ExcelUtile result = (ExcelUtile) resDoctorBiz.importCourseFromExcel(file,scoreYear, trainingTypeId);
                if(null!=result)
                {
                    String code= (String) result.get("code");
                    int count=(Integer) result.get("count");
                    String msg= (String) result.get("msg");
                    if("1".equals(code))
                    {
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + "</br>" + msg;
                    }else{
                        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
                        }else{
                            return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                        }
                    }
                }else {
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
     * 技能成绩导入
     */
    @RequestMapping(value="/importSkillScoreFromExcel")
    @ResponseBody
    public String importSkillScoreFromExcel(MultipartFile file,String scoreYear, String trainingTypeId){
        if(StringUtil.isBlank(scoreYear))
        {
            return SCOREYEAR_NOT_FIND;
        }
        if(file.getSize() > 0){
            try{
                ExcelUtile result = (ExcelUtile) resDoctorBiz.importSkillScoreFromExcel(file,scoreYear,trainingTypeId);
                if(null!=result)
                {
                    String code= (String) result.get("code");
                    int count=(Integer) result.get("count");
                    String msg= (String) result.get("msg");
                    if("1".equals(code))
                    {
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + "</br>" + msg;
                    }else{
                        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
                        }else{
                            return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                        }
                    }
                }else {
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
     * 公共科目成绩导入
     */
    @RequestMapping(value="/importPublicScoreFromExcel")
    @ResponseBody
    public String importPublicScoreFromExcel(MultipartFile file){
        if(file.getSize() > 0){
            try{
                ExcelUtile result = (ExcelUtile) resDoctorBiz.importPublicScoreFromExcel(file);
                if(null!=result)
                {
                    String code= (String) result.get("code");
                    int count=(Integer) result.get("count");
                    String msg= (String) result.get("msg");
                    if("1".equals(code))
                    {
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + msg;
                    }else{
                        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
                        }else{
                            return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                        }
                    }
                }else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                }
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
            }
        }
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
    }
    @RequestMapping(value="/deleteScore")
    @ResponseBody
    public String deleteScore(String scoreFlow,String roleFlag){
        ResScore resScore=resScoreBiz.searchByScoreFlow(scoreFlow);
        if(resScore!=null){
            resScore.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
            int result=resScoreBiz.save(resScore);
            if (result == com.pinde.core.common.GlobalConstant.ONE_LINE) {
                return com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
        }
        return com.pinde.core.common.GlobalConstant.FLAG_N;
    }
    @RequestMapping(value="/deleteSkillScore")
    @ResponseBody
    public String deleteSkillScore(String scoreFlow,String roleFlag){

        ResScore resScore=resScoreBiz.searchByScoreFlow(scoreFlow);
        if(resScore!=null){
            resScore.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
            int result=resScoreBiz.save(resScore);
            if (result == com.pinde.core.common.GlobalConstant.ONE_LINE) {
                return com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
        }
        return com.pinde.core.common.GlobalConstant.FLAG_N;
    }
    @RequestMapping(value="/deletePublicScore")
    @ResponseBody
    public String deletePublicScore(String scoreFlow,String roleFlag){
        ResScore resScore=resScoreBiz.searchByScoreFlow(scoreFlow);
        if(resScore!=null){
            resScore.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
            int result=resScoreBiz.save(resScore);
            if (result == com.pinde.core.common.GlobalConstant.ONE_LINE) {
                return com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
        }
        return com.pinde.core.common.GlobalConstant.FLAG_N;
    }
    @RequestMapping(value="/saveTheoryScore")
    @ResponseBody
    public String saveScore(ResScore score,String roleFlag){
        if(score==null){
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        String scoreFlow = score.getScoreFlow();
        if(StringUtil.isBlank(scoreFlow))
        {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        int result = resScoreBiz.save(score);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE == result) {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
    }
    @RequestMapping(value="/saveSkillIsPass")
    @ResponseBody
    public String saveSkillIsPass(ResScore score,String roleFlag,String stationName,String isPass) throws DocumentException {
        if(score==null){
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        String scoreFlow = score.getScoreFlow();
        if(StringUtil.isBlank(scoreFlow))
        {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        if(StringUtil.isBlank(isPass))
        {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }

        if("1".equals(isPass))
        {
            score.setSkillScore(BigDecimal.valueOf(Double.valueOf(com.pinde.core.common.GlobalConstant.PASS)));
        }else if("0".equals(isPass))
        {
            score.setSkillScore(BigDecimal.valueOf(Double.valueOf(com.pinde.core.common.GlobalConstant.UNPASS)));
        }else if("2".equals(isPass)){
            score.setSkillScore(BigDecimal.valueOf(Double.valueOf(2)));
        }
        int result = resScoreBiz.save(score);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE == result) {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
    }
    @RequestMapping(value="/savePublicIsPass")
    @ResponseBody
    public String savePublicIsPass(ResScore score,String roleFlag,String stationName,String isPass,String recruitFlow,String doctorFlow) throws DocumentException {
        if(score==null){
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        String scoreFlow = score.getScoreFlow();
        if(StringUtil.isBlank(scoreFlow))
        {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        if(StringUtil.isBlank(isPass))
        {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        if("1".equals(isPass))
        {
            score.setSkillScore(BigDecimal.valueOf(Double.valueOf(com.pinde.core.common.GlobalConstant.PASS)));
        }else if("0".equals(isPass))
        {
            score.setSkillScore(BigDecimal.valueOf(Double.valueOf(com.pinde.core.common.GlobalConstant.UNPASS)));
        }
        ResScore old=resScoreBiz.searchByScoreFlow(scoreFlow);
        if(!(old!=null&&StringUtil.isNotBlank(old.getRecruitFlow())))
        {
            if(StringUtil.isNotBlank(recruitFlow))
            {
                com.pinde.core.model.ResDoctorRecruit recruit = resDoctorRecruitBiz.getNewRecruit(doctorFlow);
                if(recruit!=null)
                    score.setRecruitFlow(recruit.getRecruitFlow());
            }else {
                score.setRecruitFlow(recruitFlow);
            }
        }
        score.setPaperFlow(com.pinde.core.common.GlobalConstant.FLAG_Y);
        int result = resScoreBiz.save(score);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE == result) {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
    }
    @RequestMapping(value="/saveSkillScore")
    @ResponseBody
    public String saveSkillScore(ResScore score,String roleFlag,String stationName,String skilScore) throws DocumentException {
        if(score==null){
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        String scoreFlow = score.getScoreFlow();
        if(StringUtil.isBlank(scoreFlow))
        {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        Map<String,String> map=new HashMap<String, String>();
        map.put("firstStationScore","");
        map.put("secondStationScore","");
        map.put("thirdStationScore","");
        map.put("fourthStationScore","");
        map.put("fifthStationScore","");
        map.put("sixthStationScore","");
        map.put("seventhStationScore","");
        map.put("eighthStationScore","");
        map.put("ninthStationScore","");
        map.put(stationName,skilScore);
        String extScore="";
        try {
            extScore =convertMapToXml(map,score);
        } catch (Exception e) {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        score.setExtScore(extScore);
        int result = resScoreBiz.save(score);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE == result) {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
    }

    @RequestMapping(value="/savePublicScore")
    @ResponseBody
    public String savePublicScore(ResScore score,String roleFlag,String stationName,String publicScore,String recruitFlow,String doctorFlow) throws DocumentException {

        if(score==null){
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        String scoreFlow = score.getScoreFlow();
        if(StringUtil.isBlank(scoreFlow))
        {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        score=resScoreBiz.searchByScoreFlow(score.getScoreFlow());
        if (com.pinde.core.common.enums.ResScoreTypeEnum.TheoryScore.getId().equals(stationName))//全科成绩
        {
            score.setTheoryScore(BigDecimal.valueOf(Double.valueOf(publicScore)));
        }else{  //非全科
            Map<String,String> map=new HashMap<String, String>();
            map.put("lawScore","");
            map.put("medicineScore","");
            map.put("clinicalScore","");
            map.put("ckScore","");
            map.put(stationName,publicScore);
            String extScore="";
            try {
                extScore =convertMapToXml(map,score);
            } catch (Exception e) {
                return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
            }
            score.setExtScore(extScore);
        }
        ResScore old=resScoreBiz.searchByScoreFlow(scoreFlow);
        if(!(old!=null&&StringUtil.isNotBlank(old.getRecruitFlow())))
        {
            if(StringUtil.isNotBlank(recruitFlow))
            {
                com.pinde.core.model.ResDoctorRecruit recruit = resDoctorRecruitBiz.getNewRecruit(doctorFlow);
                if(recruit!=null)
                    score.setRecruitFlow(recruit.getRecruitFlow());
            }else {
                score.setRecruitFlow(recruitFlow);
            }
        }
        score.setPaperFlow(com.pinde.core.common.GlobalConstant.FLAG_Y);
        int result = resScoreBiz.save(score);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE == result) {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
    }
    @RequestMapping(value="/doctorSubmit")
    @ResponseBody
    public String doctorSubmit(String doctorFlow) throws DocumentException {

        if(doctorFlow==null){
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        ResDoctor resDoctor=resDoctorBiz.findByFlow(doctorFlow);
        if(null==resDoctor)
        {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        resDoctor.setGraduationStatusId(CertificateStatusEnum.Submit.getId());
        resDoctor.setGraduationStatusName(CertificateStatusEnum.Submit.getName());
        resDoctor.setDisagreeReason("");
        int result = resDoctorBiz.editDoctor(resDoctor);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE == result) {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
    }
    @RequestMapping(value="/guiPeiDetail")
    public String guiPeiDetail(String doctorFlow,Model model) throws DocumentException {

        ResDoctor resDoctor=resDoctorBiz.findByFlow(doctorFlow);

        SysUser sysUser=userBiz.readSysUser(doctorFlow);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("doctor",resDoctor);
        map.put("recType", AfterRecTypeEnum.AfterSummary.getId());
        List<Map<String,Object>>  list= resScoreBiz.getALLScoreByMap(map);
        Map<String,Object> extScoreMap=new HashMap<String,Object>();
        if(null!=list&list.size()>0) {
            for (int i = 0; i < list.size(); i++) {

                List<Map<String, Object>> imagelist = new ArrayList<Map<String, Object>>();
                Map<String, Object> m = list.get(i);
                String content = null == m ? "" : m.get("recContent").toString();
                if (StringUtil.isNotBlank(content)) {
                    Document doc = DocumentHelper.parseText(content);
                    Element root = doc.getRootElement();
                    List<Element> imageEles = root.elements();

                    if (imageEles != null && imageEles.size() > 0) {

                        for (Element image : imageEles) {
                            Map<String, Object> recContent = new HashMap<String, Object>();
                            String imageFlow = image.attributeValue("imageFlow");

                            List<Element> elements = image.elements();
                            for (Element attr : elements) {
                                String attrName = attr.getName();
                                String attrValue = attr.getText();
                                recContent.put(attrName, attrValue);
                            }
                            imagelist.add(recContent);
                        }
                    }
                }
                extScoreMap.put(m.get("flow").toString(), imagelist);
            }
        }
        //计算登记进度
        Map<String, String> processPerMap = resRecBiz.getProcessPer(resDoctor.getDoctorFlow(), resDoctor.getRotationFlow());
        model.addAttribute("processPerMap", processPerMap);

        model.addAttribute("extScoreMap",extScoreMap);
        model.addAttribute("list",list);
        model.addAttribute("resDoctor",resDoctor);
        model.addAttribute("user",sysUser);
        return "jsres/doctor/guiPeiDetail";
    }

    @RequestMapping(value="/saveCompleteDate")
    @ResponseBody
    public String saveCompleteDate(String  doctorFlow ,String key,String time){
        if(doctorFlow==null){
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        ResDoctor resDoctor=resDoctorBiz.findByFlow(doctorFlow);
        if(null==resDoctor)
        {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        if("completeStartDate".equals(key))
        {
            String endDate=resDoctor.getCompleteEndDate();
            if(StringUtil.isNotBlank(endDate)&&time.compareTo(endDate)>0)
            {
                return "2";
            }
            resDoctor.setCompleteStartDate(time);
        }else if("completeEndDate".equals(key))
        {
            String start=resDoctor.getCompleteStartDate();
            if(StringUtil.isNotBlank(start)&&time.compareTo(start)<0)
            {
                return "3";
            }
            resDoctor.setCompleteEndDate(time);
        }
        int result = resDoctorBiz.editDoctor(resDoctor);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE == result) {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
        }
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
    }
    @RequestMapping(value="/publicScoreDetail")
    public String publicScoreDetail(ResScore score,Model model) throws DocumentException {
        Map<String,String> extScoreMap=new HashMap<String,String>();
        ResScore resScore=resScoreBiz.searchByScoreFlow(score.getScoreFlow());
            String content = null==resScore ? "":resScore.getExtScore();
            if(StringUtil.isNotBlank(content)) {
                Document doc = DocumentHelper.parseText(content);
                Element root = doc.getRootElement();
                Element extScoreInfo = root.element("extScoreInfo");
                if (extScoreInfo != null) {
                    List<Element> extInfoAttrEles = extScoreInfo.elements();
                    if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                        for (Element attr : extInfoAttrEles) {
                            String attrName = attr.getName();
                            String attrValue = attr.getText();
                            extScoreMap.put(attrName, attrValue);
                        }
                    }
                }
        }
        model.addAttribute("extScore",extScoreMap);
        return "jsres/doctor/publicScoreDetail";
    }
    @RequestMapping(value="/scoreDetail")
    public String scoreDetail(ResScore score,String scoreType,String scoreYear,Model model) throws DocumentException {
        List<ResScore> scorelist=resScoreBiz.selectByExampleWithBLOBs(score.getDoctorFlow());
        //技能成绩
        List<ResScore> skillList=new ArrayList<ResScore>();
        List<ResScore> theoryList=new ArrayList<ResScore>();
        List<ResScore> skillListByYear=new ArrayList<ResScore>();

        if(null!=scorelist&&scorelist.size()>0)
        {
            for(ResScore resScore:scorelist)
            {

                if (com.pinde.core.common.enums.ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(scoreYear) && scoreYear.equals(resScore.getScorePhaseId()))
                {
                    skillListByYear.add(resScore);
                } else if (com.pinde.core.common.enums.ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId()))
                {
                    skillList.add(resScore);
                } else if (com.pinde.core.common.enums.ResScoreTypeEnum.TheoryScore.getId().equals(resScore.getScoreTypeId()))
                {
                    theoryList.add(resScore);
                }
            }
        }
        if(skillListByYear.size()>0)
        {
            skillList=skillListByYear;
        }
        Map<String,Map<String,String>> extScoreMap=new HashMap<String,Map<String,String>>();
        for(int i=0;i<skillList.size();i++)
        {
            Map<String,String> extScore=new HashMap<String,String>();
            ResScore resScore=skillList.get(i);
            String content = null==resScore ? "":resScore.getExtScore();
            if(StringUtil.isNotBlank(content)) {
                Document doc = DocumentHelper.parseText(content);
                Element root = doc.getRootElement();
                Element extScoreInfo = root.element("extScoreInfo");
                if (extScoreInfo != null) {
                    List<Element> extInfoAttrEles = extScoreInfo.elements();
                    if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                        for (Element attr : extInfoAttrEles) {
                            String attrName = attr.getName();
                            String attrValue = attr.getText();
                            extScore.put(attrName, attrValue);
                        }
                    }
                }
            }
            extScoreMap.put(resScore.getScoreFlow(),extScore);
        }
        ResDoctor resDoctor=resDoctorBiz.searchByUserFlow(score.getDoctorFlow());
        SysUser sysUser=userBiz.readSysUser(score.getDoctorFlow());
        model.addAttribute("user",sysUser);
        model.addAttribute("resDoctor",resDoctor);
        model.addAttribute("skillList",skillList);
        model.addAttribute("theoryList",theoryList);
        model.addAttribute("extScoreMap",extScoreMap);
        model.addAttribute("scoreType",scoreType);

        return "jsres/doctor/skillScoreDetail";
    }

    public String convertMapToXml(Map<String,String> map,ResScore resScore) throws Exception {
        String xmlBody = null;
        Document doc=null;
        Element root =null;
        ResScore old=resScoreBiz.searchByScoreFlow(resScore.getScoreFlow());
        String content = null==old ? "":old.getExtScore();
        if(null!=old&&StringUtil.isNotBlank(content)) {
            doc = DocumentHelper.parseText(content);
            root = doc.getRootElement();
            Element extScoreInfo = root.element("extScoreInfo");
            if (extScoreInfo != null) {
                List<Element> extInfoAttrEles = extScoreInfo.elements();
                if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                    for (Element attr : extInfoAttrEles) {
                        String attrName = attr.getName();
                        String newValue = map.get(attrName);
                        if (StringUtil.isNotBlank(newValue)) {
                            attr.setText(newValue);
                        }
                    }
                }else{
                    if (map != null && map.size() > 0) {
                        for (String key : map.keySet()) {
                            Element item = extScoreInfo.element(key);
                            if(item==null)	item=extScoreInfo.addElement(key);
                            item.setText(map.get(key));
                        }
                    }
                }
            }else{
                extScoreInfo = root.addElement("extScoreInfo");
                if (map != null && map.size() > 0) {
                    for (String key : map.keySet()) {
                        Element item = extScoreInfo.addElement(key);
                        item.setText(map.get(key));
                    }
                }
            }
            xmlBody = doc.asXML();
        }else {
            doc = DocumentHelper.createDocument();
            root = doc.addElement("resExtScore");
            Element extScoreInfo = root.addElement("extScoreInfo");
            if (map != null && map.size() > 0) {
                for (String key : map.keySet()) {
                    Element item = extScoreInfo.addElement(key);
                    item.setText(map.get(key));
                }
            }
            xmlBody = doc.asXML();
        }
        return xmlBody;
    }

    @RequestMapping(value="/asseAuditForJointOrgMain")
    public String asseAuditForJointOrgMain(Model model) {
        return "jsres/asse/hospital/asseAuditForJointOrgMain";
    }

    /**
     * 医院角色结业考核头部标签页
     * @return
     */
    @RequestMapping(value="/asseGraduationForHos")
    public String asseGraduationForHos(Model model){
        SysUser currentUser = GlobalContext.getCurrentUser();
        //判断是否是协同基地
        List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(currentUser.getOrgFlow());
        if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){
            model.addAttribute("roleFlag", "jointOrg");
            return "jsres/asse/hospital/auditMainForJoint";
        }
        return "jsres/asse/hospital/main";
    }
    /**
     * 医院角色结业考核头部标签页(最新)
     * @return
     */
    @RequestMapping(value="/asseMain")
    public String asseMain(Model model,String roleFlag,String tabTag){
        SysUser currentUser = GlobalContext.getCurrentUser();
        //判断是否是协同基地
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(currentUser.getOrgFlow());
        if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
         //   return "jsres/asse/hospital/auditMainForJoint";
        }
        model.addAttribute("isJointOrg", isJointOrg);
        model.addAttribute("tabTag",tabTag);
        model.addAttribute("roleFlag",roleFlag);
        return "jsres/asse/hospital/main";
    }

    /**
     * 待审核筛选条件页
     * @return
     */
    @RequestMapping(value="/waitAuditMain")
    public String waitAuditMain(Model model){
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        model.addAttribute("org",currentOrg);
        List<SysOrg>  orgs = new ArrayList<>();
        if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(currentOrg.getOrgLevelId()))
        {
            List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(currentOrg.getOrgFlow());
            orgs.addAll(joinOrgs);
        }
        orgs.add(currentOrg);
        model.addAttribute("orgs", orgs);
        return "jsres/asse/hospital/waitAuditMain";
    }

    /**
     * 待审核列表页
     * @param model
     * @param roleFlag
     * @param currentPage
     * @param request
     * @param orgFlow
     * @param trainingTypeId
     * @param trainingSpeId
     * @param datas
     * @param sessionNumber
     * @param graduationYear
     * @param qualificationMaterialId
     * @param passFlag
     * @param userName
     * @param idNo
     * @param completeBi
     * @param auditBi
     * @return
     */
    @RequestMapping(value="/waitAuditList")
    public String waitAuditList(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request,
                                String orgFlow,String trainingTypeId,String trainingSpeId,String datas[],
                                String sessionNumber,String graduationYear,String qualificationMaterialId,String passFlag,
                                String userName,String idNo,String completeBi,String auditBi,String applyYear
    ){
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String> orgFlowList = new ArrayList();
        if (StringUtil.isNotBlank(orgFlow)) {
            orgFlowList.add(orgFlow);
        }else {
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(currentOrg.getOrgLevelId())) {
                List<SysOrg> joinOrgs = orgBiz.searchJointOrgsByOrg(currentOrg.getOrgFlow());
                if (joinOrgs != null && joinOrgs.size() > 0) {
                    for (SysOrg tempOrg : joinOrgs) {
                        orgFlowList.add(tempOrg.getOrgFlow());
                    }
                }
            }
            orgFlowList.add(currentOrg.getOrgFlow());
        }
        param.put("orgFlowList",orgFlowList);//培训基地
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
        param.put("auditStatusId", com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getId());
        param.put("applyYear",applyYear);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> list = graduationApplyBiz.chargeQueryApplyList(param);
        model.addAttribute("list",list);
        String nowTime = DateUtil.transDateTime(DateUtil.getCurrentTime());
        String startDate = InitConfig.getSysCfg("local_submit_start_time");
        String endDate = InitConfig.getSysCfg("local_submit_end_time");
        String f = com.pinde.core.common.GlobalConstant.FLAG_N;
        if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
            if(startDate.compareTo(nowTime) <= 0 && endDate.compareTo(nowTime)>=0)
            {
                f = com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
        }
        model.addAttribute("f",f);
        model.addAttribute("currentUser",currentUser);
        return "jsres/asse/hospital/waitAuditList";
    }
    @RequestMapping(value="/exportList")
    public void exportList(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request,HttpServletResponse response, String orgFlow,
                           String trainingTypeId,String trainingSpeId,String datas[], String sessionNumber,String graduationYear,
                           String qualificationMaterialId,String passFlag, String userName,String idNo,String completeBi,String auditBi,
                           String auditStatusId,String testId,String isNotMatch,String isWaitAudit,String applyYear,String joinOrgFlow
    ) throws IOException {
        SysUser currentUser = GlobalContext.getCurrentUser();
        Map<String,Object> param = new HashMap<>();
        //查询条件
        List<String> orgFlowList = new ArrayList();
        SysOrg org = orgBiz.readSysOrg(orgFlow);
        if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
            orgFlowList.add(orgFlow);
            if(StringUtil.isNotBlank(joinOrgFlow) && orgFlow.equals(joinOrgFlow)) {
                joinOrgFlow = "isNull";
            }
        }else{
            List<ResJointOrg> jointOrgList = jointOrgBiz.selectByJointOrgFlow(orgFlow);
            if(null != jointOrgList && jointOrgList.size()>0){
                orgFlowList.add(jointOrgList.get(0).getOrgFlow());
                joinOrgFlow = jointOrgList.get(0).getJointOrgFlow();
            }
        }
        param.put("orgFlowList",orgFlowList);//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("docTypeList",docTypeList);
        //判断是否是协同基地
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(currentUser.getOrgFlow());
        if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        param.put("isJointOrg",isJointOrg);
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
        param.put("auditStatusId", auditStatusId);
        param.put("testId", testId);
        param.put("isNotMatch", isNotMatch);
        param.put("applyYear", applyYear);
        param.put("jointOrgFlow",joinOrgFlow);
        List<Map<String,Object>> list = graduationApplyBiz.chargeQueryListForExport(param);
        graduationApplyBiz.chargeExportList(list,response,isWaitAudit);

    }

    @RequestMapping(value="/exportListTwo")
    public void exportListTwo(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request,HttpServletResponse response, String orgFlow,
                           String trainingTypeId,String trainingSpeId,String datas[], String sessionNumber,String graduationYear,
                           String qualificationMaterialId,String passFlag, String userName,String idNo,String completeBi,String auditBi,
                           String auditStatusId,String testId,String isNotMatch,String isWaitAudit,String applyYear,String joinOrgFlow,String tabTag
    ) throws IOException {
        SysUser currentUser = GlobalContext.getCurrentUser();
        Map<String,Object> param = new HashMap<>();
        //查询条件
        List<String> orgFlowList = new ArrayList();
        if(StringUtils.isNotEmpty(orgFlow)) orgFlowList.add(orgFlow);
        SysOrg org = orgBiz.readSysOrg(orgFlow);
        if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
            orgFlowList.add(orgFlow);
            if(StringUtil.isNotBlank(joinOrgFlow) && orgFlow.equals(joinOrgFlow)) {
                joinOrgFlow = "isNull";
            }
        }else{
            List<ResJointOrg> jointOrgList = jointOrgBiz.selectByJointOrgFlow(orgFlow);
            if(null != jointOrgList && jointOrgList.size()>0){
//                orgFlowList.add(jointOrgList.get(0).getOrgFlow());
//                joinOrgFlow = jointOrgList.get(0).getJointOrgFlow();
                if("DoctorTrainingSpe".equals(trainingTypeId)) {
                    orgFlowList.add(jointOrgList.get(0).getOrgFlow());
                    joinOrgFlow = jointOrgList.get(0).getJointOrgFlow();
                }else{
                    orgFlowList.add(jointOrgList.get(0).getJointOrgFlow());
                    joinOrgFlow = jointOrgList.get(0).getJointOrgFlow();
                }
            }
        }
        param.put("orgFlowList",orgFlowList);//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("docTypeList",docTypeList);
        //判断是否是协同基地
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(currentUser.getOrgFlow());
        if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        if(TrainCategoryEnum.AssiGeneral.getId().equals(trainingTypeId)) param.put("isJointOrg",isJointOrg);
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
        param.put("auditStatusId", auditStatusId);
        param.put("testId", testId);
        param.put("isNotMatch", isNotMatch);
        param.put("applyYear", applyYear);
        param.put("jointOrgFlow",joinOrgFlow);
        param.put("roleFlag",roleFlag);
        param.put("tabTag",tabTag);
        List<Map<String,Object>> list = graduationApplyBiz.chargeQueryListForExport(param);
        Map<Object, List<Map<String, Object>>> list2 = list.stream().collect(Collectors.groupingBy(e -> e.get("idNo")));
        List<Map<String,Object>> list3 = list2.entrySet().stream().map(e -> e.getValue().get(0)).collect(Collectors.toList());
        graduationApplyBiz.chargeExportListTwo(list3,response,isWaitAudit);

    }

    @RequestMapping(value="/exportListNew")
    public void exportListNew(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request,HttpServletResponse response,
                           String orgFlow,String trainingTypeId,String trainingSpeId,String datas[],String tabTag, String sessionNumber,
                           String graduationYear,String qualificationMaterialId,String passFlag, String userName,String idNo,String completeBi,
                           String auditBi,String auditStatusId,String testId,String isNotMatch,String isWaitAudit,String applyYear,String joinOrgFlow
    ) throws IOException {
        SysUser currentUser = GlobalContext.getCurrentUser();
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String> orgFlowList = new ArrayList();
        SysOrg org = orgBiz.readSysOrg(orgFlow);
        if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
            orgFlowList.add(orgFlow);
            if(StringUtil.isNotBlank(joinOrgFlow) && orgFlow.equals(joinOrgFlow)) {
                joinOrgFlow = "isNull";
            }
        }else{
            List<ResJointOrg> jointOrgList = jointOrgBiz.selectByJointOrgFlow(orgFlow);
            if(null != jointOrgList && jointOrgList.size()>0){
                orgFlowList.add(jointOrgList.get(0).getOrgFlow());
                joinOrgFlow = jointOrgList.get(0).getJointOrgFlow();
            }
        }
        param.put("orgFlowList",orgFlowList);//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("docTypeList",docTypeList);
        //判断是否是协同基地
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(currentUser.getOrgFlow());
        if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        param.put("isJointOrg",isJointOrg);
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
        param.put("auditStatusId", auditStatusId);
        param.put("testId", testId);
        param.put("isNotMatch", isNotMatch);
        param.put("applyYear", applyYear);
        param.put("roleFlag",roleFlag);
        param.put("tabTag",tabTag);
        param.put("jointOrgFlow",joinOrgFlow);
        List<Map<String,Object>> list = graduationApplyBiz.chargeQueryListForExport(param);
        graduationApplyBiz.chargeExportList2(list,response,isWaitAudit,roleFlag);

    }

    /**
     * 情况总览筛选条件页
     * @return
     */
    @RequestMapping(value="/auditMain")
    public String auditMain(Model model,String roleFlag,String tabTag){
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        model.addAttribute("org",currentOrg);
        List<SysOrg>  orgs = new ArrayList<>();
        orgs.add(currentOrg);
        if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(currentOrg.getOrgLevelId()))
        {
            List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(currentOrg.getOrgFlow());
            orgs.addAll(joinOrgs);
        }
        model.addAttribute("orgs", orgs);
        model.addAttribute("roleFlag", roleFlag);
        List<ResTestConfig> resTestConfigs = resTestConfigBiz.findAllEffective();
        model.addAttribute("resTestConfigs", resTestConfigs);
        return "jsres/asse/hospital/auditMain";
    }

    @RequestMapping(value="/asseAuditListMain")
    public String asseAuditListMain(Model model) {
        return "jsres/asse/hospital/asseAuditListMain";
    }

    /**
     * 情况总览列表页
     * @return
     */
    @RequestMapping(value="/auditList")
    public String auditList(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request, String orgFlow,String trainingTypeId,
                            String trainingSpeId,String datas[], String sessionNumber, String graduationYear,String qualificationMaterialId,
                            String passFlag, String userName,String idNo, String completeBi,String auditBi,String auditStatusId,String testId,
                            String isNotMatch,String applyYear,String tabTag,String isPostpone, String tempDoctorFlag
    ){
        SysUser currentUser = GlobalContext.getCurrentUser();
//        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());

        //查询条件
        Map<String,Object> param = new HashMap<>();
        List<String> orgFlowList = new ArrayList();
//        List<String> jointOrgFlowList = new ArrayList();
        orgFlowList.add(orgFlow);
        LambdaQueryWrapper<ResJointOrg> resJointOrgLambdaQueryWrapper = new LambdaQueryWrapper();
        resJointOrgLambdaQueryWrapper.eq(ResJointOrg::getOrgFlow, orgFlow).eq(ResJointOrg::getRecordStatus,"Y");
        List<ResJointOrg> jointOrgList = resJointOrgMapper.selectList(resJointOrgLambdaQueryWrapper);
        if(CollectionUtils.isNotEmpty(jointOrgList)) {
            jointOrgList.stream().forEach(e -> orgFlowList.add(e.getJointOrgFlow()));
        }
//        param.put("jointOrgFlowList",jointOrgFlowList);//协同基地
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
        param.put("testId",testId);
        param.put("isNotMatch",isNotMatch);
        param.put("completeBi",completeBi);
        param.put("auditBi",auditBi);
        param.put("auditStatusId",auditStatusId);
        param.put("applyYear",applyYear);
        param.put("roleFlag",roleFlag);
        param.put("tabTag",tabTag);

        param.put("isPostpone",isPostpone);
        param.put("tempDoctorFlag", tempDoctorFlag);
        //判断是否是协同基地
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(currentUser.getOrgFlow());
        if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }else{
            if(null != orgFlowList && orgFlowList.size()==0){
                if(!"AssiGeneral".equals(trainingTypeId)){
                    orgFlowList.add(currentUser.getOrgFlow());
                }
            }
        }
        param.put("orgFlowList",orgFlowList);//培训基地
        param.put("isJointOrg",isJointOrg);

        PageHelper.startPage(currentPage,getPageSize(request));
        //禅道2143 主基地查询数据不包含协同基地助理全科
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryApplyList2(param);
        Map<String,List<ResRec>> nonComplianceRecordsMap= new HashMap<String, List<ResRec>>();
        list.stream().forEach(e->{
            Map<String, List<ResRec>> doctorFlowMap = graduationApplyBiz.getNonComplianceRecords(e.get("doctorFlow").toString());
            nonComplianceRecordsMap.putAll(doctorFlowMap);

        });
//        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryApplyList(param);
//        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryApplyListNew(param);
        if(StringUtil.isNotBlank(roleFlag)){
            model.addAttribute("roleFlag",roleFlag);
        }
        model.addAttribute("list",list);
        model.addAttribute("nonComplianceRecordsMap",nonComplianceRecordsMap);
       /* String nowTime=DateUtil.transDateTime(DateUtil.getCurrentTime());
        String startDate=InitConfig.getSysCfg("local_submit_start_time");
        String endDate=InitConfig.getSysCfg("local_submit_end_time");*/
        String f = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResTestConfig> testConfigList = resTestConfigBiz.findLocalEffective(DateUtil.getCurrDateTime2());
        if (testConfigList.size() > 0) {
            f = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        /*if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
            if(startDate.compareTo(nowTime)<=0&&endDate.compareTo(nowTime)>=0)
            {
                f=com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
        }*/
        model.addAttribute("f",f);
        model.addAttribute("currentUser",currentUser);
        model.addAttribute("roleFlag",roleFlag);
        model.addAttribute("trainingTypeId",trainingTypeId);

        return "jsres/asse/hospital/auditList";
    }

    /**
     * 查询结业合格人员
     */
    @RequestMapping(value="/theoryPassedList")
    public String theoryPassedList(Model model,String roleFlag){
        long startTime = System.currentTimeMillis(); //开始时间
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        SysOrg sysorg =new  SysOrg();
        sysorg.setOrgProvId(org.getOrgProvId());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
            sysorg.setOrgCityId(org.getOrgCityId());
            model.addAttribute("orgCityId", org.getOrgCityId());
        }
//        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
//        List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        List<SysOrg> orgs=orgBiz.searchOrgNotCountryOrg(sysorg);
        sysorg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
        List<SysOrg>  countryList=orgBiz.searchOrg(sysorg);
        sysorg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId());
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
        //查询库中最新成绩的年份
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("scoreTypeId","TheoryScore");

//        String lastYear = resScoreBiz.findLastYearByScoreTypeId(paramMap);
//        model.addAttribute("lastYear", lastYear);
        //如果是国家基地，基地和协同  如果不是国家基地，那只有本基地
        model.addAttribute("org",org);
        if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
            List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
            model.addAttribute("jointOrgList",jointOrgList);
        }
        model.addAttribute("year", DateUtil.getYear());
        List<ResTestConfig> resTestConfigs = resTestConfigBiz.findAllEffective();
        model.addAttribute("resTestConfigs", resTestConfigs);
        long endTime = System.currentTimeMillis(); //结束时间
        logger.info("》》》》》》》》saveStep加载运行时间：" + (endTime - startTime) + "ms");
        return  "jsres/theoryScore/theoryPassedList";
    }

    @RequestMapping(value="/searchTheoryPassed")
    public String searchTheoryPassed(Model model,Integer currentPage,String roleFlag,String orgCityId,
                                     HttpServletRequest request,ResDoctor doctor,SysUser user,String jointOrgFlag,
                                     String derateFlag,String orgLevel,String[] datas,String orgFlow2,
                                     String graduationYear,String scoreYear,String isHege,String skillIsHege,
                                     String  theroyScoreYear, String  skillScoreYear,String testId,String isMakeUp,String trainingTypeId
    ){
        ResDoctorRecruit resDoctorRecruit= new ResDoctorRecruit();
        if(StringUtil.isNotBlank(graduationYear)){
            resDoctorRecruit.setGraduationYear(graduationYear);
        }
        //协同机构
        List<String> jointOrgFlowList=new ArrayList<String>();
        List<String>docTypeList=new ArrayList<String>();
        SysOrg org=new SysOrg();
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
            org.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
            SysOrg sysOrg = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
            org.setOrgCityId(sysOrg.getOrgCityId());
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
            org.setOrgCityId(orgCityId);
        }
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysUser sysUser =new SysUser();
        //培训基地
        if (StringUtil.isBlank(doctor.getOrgFlow())) {
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)
                    || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)
                    || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SCHOOL)) {
                SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    jointOrgFlowList.add(sysuser.getOrgFlow());
                    SysOrg searchOrg=new SysOrg();
                    searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                    if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
                        searchOrg.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    //基地类型
                    if(StringUtil.isNotBlank(orgLevel)){
                        searchOrg.setOrgLevelId(orgLevel);
                        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
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
                    /*if(getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)){
                        org.setOrgCityId(sysOrg.getOrgCityId());
                    }*/
                    if(StringUtil.isNotBlank(orgLevel)){
                        org.setOrgLevelId(orgLevel);
                        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
                    }
                }
            }
        }else if("allOrgs".equals(doctor.getOrgFlow())){
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
                jointOrgFlowList = searchJointOrgList(com.pinde.core.common.GlobalConstant.FLAG_Y, sysuser.getOrgFlow());
                jointOrgFlowList.add(sysuser.getOrgFlow());
            }
        }else{
            jointOrgFlowList.add(doctor.getOrgFlow());
            //是否勾选了协同机构
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
                if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                    for(ResJointOrg jointOrg:resJointOrgList){
                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }

        if(StringUtil.isNotBlank(orgFlow2)){
            jointOrgFlowList.add(orgFlow2);
            List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(orgFlow2);
            if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                for(ResJointOrg jointOrg:resJointOrgList){
                    jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                }
            }
        }
        //证件号
        if(StringUtil.isNotBlank(user.getIdNo())){
            sysUser.setIdNo(user.getIdNo());
        }
        //姓名
        sysUser.setUserName(user.getUserName());
        //显示减免
        if(StringUtil.isNotBlank(derateFlag)){
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(derateFlag)) {
                doctor.setTrainingTypeId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
                resDoctorRecruit.setTrainYear(com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId());
            }else{
                derateFlag="";
            }
        }else{
            derateFlag="";
        }
        //人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        //根据年份从res_pass_score_cfg取数据
        ResPassScoreCfg cfg = new ResPassScoreCfg();
        cfg.setCfgYear(scoreYear);
        ResPassScoreCfg resPassScoreCfg = baseBiz.readResPassScoreCfg(cfg);
        String hegeScore="60";
        if(resPassScoreCfg!=null){
            hegeScore = resPassScoreCfg.getCfgPassScore();
            if(StringUtil.isBlank(hegeScore)){
                hegeScore="60";
            }
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        resDoctorRecruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
        List<JsResDoctorRecruitExt> doctorList=null;
        doctorList=jsResDoctorRecruitBiz.searchDoctorSkillAndTheoryScoreExts1(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,theroyScoreYear,skillScoreYear,isHege,skillIsHege,docTypeList,hegeScore,testId,isMakeUp,trainingTypeId,"",roleFlag);
        model.addAttribute("roleFlag",roleFlag);
        model.addAttribute("doctorList",doctorList);
        model.addAttribute("datas",datas);
        model.addAttribute("scoreYear",scoreYear);
        return  "jsres/theoryScore/doctorPassedList";
    }
    @RequestMapping(value="/exportTheoryPassed")
    public void exportTheoryPassed(String roleFlag,HttpServletResponse response,ResDoctor doctor,SysUser user,String jointOrgFlag,
                                     String derateFlag,String orgLevel,String[] datas,String orgCityId,String orgFlow2,
                                     String graduationYear,String scoreYear,String isHege,String skillIsHege,
                                     String  theroyScoreYear, String  skillScoreYear,String testId,String isMakeUp,String trainingTypeId) throws Exception{
        ResDoctorRecruit resDoctorRecruit= new ResDoctorRecruit();
        if(StringUtil.isNotBlank(graduationYear)){
            resDoctorRecruit.setGraduationYear(graduationYear);
        }
        //协同机构
        List<String> jointOrgFlowList=new ArrayList<String>();
        List<String>docTypeList=new ArrayList<String>();
        SysOrg org=new SysOrg();
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
            org.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
            SysOrg sysOrg = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
            org.setOrgCityId(sysOrg.getOrgCityId());
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
            org.setOrgCityId(orgCityId);
        }
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysUser sysUser =new SysUser();
        //培训基地
        if (StringUtil.isBlank(doctor.getOrgFlow())) {
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)
                    || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)
                    || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SCHOOL)) {
                SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                    jointOrgFlowList.add(sysuser.getOrgFlow());
                    SysOrg searchOrg=new SysOrg();
                    searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                    if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
                        searchOrg.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    //基地类型
                    if(StringUtil.isNotBlank(orgLevel)){
                        searchOrg.setOrgLevelId(orgLevel);
                        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
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
                    /*if(getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)){
                        org.setOrgCityId(sysOrg.getOrgCityId());
                    }*/
                    if(StringUtil.isNotBlank(orgLevel)){
                        org.setOrgLevelId(orgLevel);
                        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
                    }
                }
            }
        }else if("allOrgs".equals(doctor.getOrgFlow())){
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
                jointOrgFlowList = searchJointOrgList(com.pinde.core.common.GlobalConstant.FLAG_Y, sysuser.getOrgFlow());
                jointOrgFlowList.add(sysuser.getOrgFlow());
            }
        }else{
            jointOrgFlowList.add(doctor.getOrgFlow());
            //是否勾选了协同机构
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
                List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
                if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                    for(ResJointOrg jointOrg:resJointOrgList){
                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }
        }

        if(StringUtil.isNotBlank(orgFlow2)){
            jointOrgFlowList.add(orgFlow2);
            List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(orgFlow2);
            if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                for(ResJointOrg jointOrg:resJointOrgList){
                    jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                }
            }
        }

        //证件号
        if(StringUtil.isNotBlank(user.getIdNo())){
            sysUser.setIdNo(user.getIdNo());
        }
        //姓名
        sysUser.setUserName(user.getUserName());
        //显示减免
        if(StringUtil.isNotBlank(derateFlag)){
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(derateFlag)) {
                doctor.setTrainingTypeId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
                resDoctorRecruit.setTrainYear(com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId());
            }else{
                derateFlag="";
            }
        }else{
            derateFlag="";
        }
        //人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        //根据年份从res_pass_score_cfg取数据
        ResPassScoreCfg cfg = new ResPassScoreCfg();
        cfg.setCfgYear(scoreYear);
        ResPassScoreCfg resPassScoreCfg = baseBiz.readResPassScoreCfg(cfg);
        String hegeScore="60";
        if(resPassScoreCfg!=null){
            hegeScore = resPassScoreCfg.getCfgPassScore();
            if(StringUtil.isBlank(hegeScore)){
                hegeScore="60";
            }
        }
        resDoctorRecruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
        List<JsResDoctorRecruitExt> doctorList=null;
        doctorList=jsResDoctorRecruitBiz.searchDoctorSkillAndTheoryScoreExts1(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,theroyScoreYear,skillScoreYear,isHege,skillIsHege,docTypeList,hegeScore,testId,isMakeUp,trainingTypeId,"",roleFlag);
        theoryListExportTemp(doctorList,roleFlag,scoreYear,response);
    }
    /****************************高******校******管******理******员******角******色************************************************/

    @RequestMapping(value="/doctorSkillListForUni")
    public String doctorSkillListForUni(Model model,String roleFlag){
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());

        List<SysOrg> orgs=orgBiz.getUniOrgs(org.getSendSchoolId(),org.getSendSchoolName());
        model.addAttribute("orgs", orgs);
        return  "jsres/university/doctorTheoryList";
    }
    @RequestMapping(value="/doctorSkillListSunForUni")
    public String doctorSkillListSunForUni(Model model,Integer currentPage,String trainingTypeId,
                                     HttpServletRequest request,String orgFlow,String trainingSpeId,
                                     String sessionNumber,String userName,String idNo,
                                     String trainingYears,String skillIsHege,
                                     String graduationYear,String scoreYear,String isHege) throws DocumentException {

        //成绩类型为技能成绩
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
        param.put("trainingYears",trainingYears);
        param.put("skillIsHege",skillIsHege);
        param.put("scoreYear",scoreYear);
        param.put("isHege",isHege);
        param.put("org",org);

        //根据年份从res_pass_score_cfg取数据
        ResPassScoreCfg cfg = new ResPassScoreCfg();
        cfg.setCfgYear((String) param.get("scoreYear"));
        ResPassScoreCfg resPassScoreCfg = baseBiz.readResPassScoreCfg(cfg);
        String hegeScore="60";
        if(resPassScoreCfg!=null){
            hegeScore = resPassScoreCfg.getCfgPassScore();
            if(StringUtil.isBlank(hegeScore)){
                hegeScore="60";
            }
        }
        param.put("hegeScore",hegeScore);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<JsResDoctorRecruitExt> doctorList=null;
        if(org==null||(StringUtil.isBlank(org.getSendSchoolId())&&StringUtil.isBlank(org.getSendSchoolName())))
        {
            doctorList = null;
        }else {
            doctorList = jsResDoctorRecruitBiz.searchDoctorSkillScoreForUni(param);
        }
        model.addAttribute("doctorList",doctorList);
        model.addAttribute("scoreYear",scoreYear);
        return  "jsres/university/skillScore/doctorListZi";
    }
    /**
     * 查询结业合格人员
     */
    @RequestMapping(value="/theoryPassedListForUni")
    public String theoryPassedListForUni(Model model,String roleFlag){
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        List<SysOrg> orgs=orgBiz.getUniOrgs(org.getSendSchoolId(),org.getSendSchoolName());
        model.addAttribute("orgs", orgs);
        model.addAttribute("year",DateUtil.getYear());
        return  "jsres/university/theoryPassedList";
    }
    /**
     * 根据条件查询结业合格人员
     */
    @RequestMapping(value="/searchTheoryPassedForUni")
    public String searchTheoryPassedForUni(Model model,Integer currentPage,String trainingTypeId,String roleFlag,
                                           HttpServletRequest request,String orgFlow,String trainingSpeId,
                                           String sessionNumber,String userName,String idNo,
                                           String trainingYears,String graduationYear,String scoreYear,
                                           String  theroyScoreYear, String  skillScoreYear){
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
        param.put("trainingYears",trainingYears);
        param.put("skillIsHege", com.pinde.core.common.GlobalConstant.FLAG_Y);
        param.put("scoreYear",scoreYear);
        param.put("isHege","1");
        param.put("theroyScoreYear",theroyScoreYear);
        param.put("skillScoreYear",skillScoreYear);
        param.put("doctorLicenseFlag","passed");//存放标识，用于成绩三年有效期内合格判断
        param.put("org",org);

        //根据年份从res_pass_score_cfg取数据
        ResPassScoreCfg cfg = new ResPassScoreCfg();
        cfg.setCfgYear((String) param.get("scoreYear"));
        ResPassScoreCfg resPassScoreCfg = baseBiz.readResPassScoreCfg(cfg);
        String hegeScore="60";
        if(resPassScoreCfg!=null){
            hegeScore = resPassScoreCfg.getCfgPassScore();
            if(StringUtil.isBlank(hegeScore)){
                hegeScore="60";
            }
        }
        param.put("hegeScore",hegeScore);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<JsResDoctorRecruitExt> doctorList=null;
        if(org==null||(StringUtil.isBlank(org.getSendSchoolId())&&StringUtil.isBlank(org.getSendSchoolName())))
        {
            doctorList = null;
        }else {
            doctorList = jsResDoctorRecruitBiz.searchDoctorSkillScoreForUni1(param);
        }
        model.addAttribute("roleFlag",roleFlag);
        model.addAttribute("doctorList",doctorList);
        model.addAttribute("scoreYear",scoreYear);
        return  "jsres/theoryScore/doctorPassedList";
    }
    @RequestMapping(value="/exportTheoryPassedForUni")
    public void exportTheoryPassedForUni(String trainingTypeId,String roleFlag,HttpServletResponse response,
                                           HttpServletRequest request,String orgFlow,String trainingSpeId,
                                           String sessionNumber,String userName,String idNo,
                                           String trainingYears,String graduationYear,String scoreYear,
                                           String  theroyScoreYear, String  skillScoreYear)throws Exception{
        SysUser user=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(user.getOrgFlow());
        Map<String,Object> param=new HashMap<>();
        param.put("orgFlow",orgFlow);
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("idNo",idNo);
        param.put("userName",userName);
        param.put("graduationYear",graduationYear);
        param.put("trainingYears",trainingYears);
        param.put("skillIsHege", com.pinde.core.common.GlobalConstant.FLAG_Y);
        param.put("scoreYear",scoreYear);
        param.put("isHege","1");
        param.put("theroyScoreYear",theroyScoreYear);
        param.put("skillScoreYear",skillScoreYear);
        param.put("doctorLicenseFlag","passed");//存放标识，用于成绩三年有效期内合格判断
        param.put("org",org);

        //根据年份从res_pass_score_cfg取数据
        ResPassScoreCfg cfg = new ResPassScoreCfg();
        cfg.setCfgYear((String) param.get("scoreYear"));
        ResPassScoreCfg resPassScoreCfg = baseBiz.readResPassScoreCfg(cfg);
        String hegeScore="60";
        if(resPassScoreCfg!=null){
            hegeScore = resPassScoreCfg.getCfgPassScore();
            if(StringUtil.isBlank(hegeScore)){
                hegeScore="60";
            }
        }
        param.put("hegeScore",hegeScore);
        List<JsResDoctorRecruitExt> doctorList=null;
        if(org==null||(StringUtil.isBlank(org.getSendSchoolId())&&StringUtil.isBlank(org.getSendSchoolName())))
        {
            doctorList = null;
        }else {
            doctorList = jsResDoctorRecruitBiz.searchDoctorSkillScoreForUni1(param);
        }
        theoryListExportTemp(doctorList,roleFlag,scoreYear,response);
    }

    public void theoryListExportTemp(List<JsResDoctorRecruitExt> doctorList,String roleFlag,String scoreYear,HttpServletResponse response) throws Exception{
        List<Map<String,Object>> resultMapList = new ArrayList<>();
        if(doctorList!=null&&doctorList.size()>0){
            for(JsResDoctorRecruitExt doctorRecruitExt:doctorList){
                String doctorName = doctorRecruitExt.getSysUser().getUserName();
                String idNo = doctorRecruitExt.getSysUser().getIdNo();
                String orgName = doctorRecruitExt.getOrgName();
                String sessionNumber = doctorRecruitExt.getSessionNumber();
                String catSpeName = doctorRecruitExt.getCatSpeName();
                String speName = doctorRecruitExt.getSpeName();
                String placeName = doctorRecruitExt.getPlaceName();
                String skillTestId = doctorRecruitExt.getSkillTestId();
                String theoryTestId = doctorRecruitExt.getTheoryTestId();
//                String changeSpeName = doctorRecruitExt.getChangeSpeName();
                String changeSpeName = doctorRecruitExt.getResDoctor().getTrainingSpeName();
                String graduationYear = doctorRecruitExt.getGraduationYear();
                String qualifiedTestId = "";
                if (StringUtil.isNotBlank(skillTestId) && StringUtil.isNotBlank(theoryTestId)) {
                    qualifiedTestId = Integer.valueOf(skillTestId) > Integer.valueOf(theoryTestId) ? skillTestId : theoryTestId;
                }
                if (StringUtil.isBlank(skillTestId)) {
                    qualifiedTestId = StringUtil.isBlank(theoryTestId) ? "" : theoryTestId;
                }
                if (StringUtil.isBlank(theoryTestId)) {
                    qualifiedTestId = StringUtil.isBlank(skillTestId) ? "" : skillTestId;
                }
                String localReason = doctorRecruitExt.getLocalReason();
                String cityReason = doctorRecruitExt.getCityReason();
                String globalReason = doctorRecruitExt.getGlobalReason();
                String graduationCertificateNo = doctorRecruitExt.getGraduationCertificateNo();
                String year1 = scoreYear;
                String year2 = scoreYear;
                year1 = doctorRecruitExt.getResScore().getScorePhaseId();
                year2 = doctorRecruitExt.getResScore().getScorePhaseName();
                String theoryScore = null==doctorRecruitExt.getResScore().getTheoryScore()?"":doctorRecruitExt.getResScore().getTheoryScore()+"";
                if("1".equals(theoryScore)){
                    theoryScore="合格";
                }
                if("0".equals(theoryScore)){
                    theoryScore="不合格";
                }
                if("2".equals(theoryScore)){
                    theoryScore="缺考";
                }
                String isPass = "";
                //技能成绩
                List<ResScore> scorelist=resScoreBiz.selectByExampleWithBLOBs(doctorRecruitExt.getSysUser().getUserFlow());
                List<ResScore> skillList=new ArrayList<ResScore>();
                List<ResScore> theoryList=new ArrayList<ResScore>();
                List<ResScore> skillListByYear=new ArrayList<ResScore>();

                if(null!=scorelist&&scorelist.size()>0)
                {
                    for(ResScore resScore:scorelist)
                    {

                        if (com.pinde.core.common.enums.ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(scoreYear) && scoreYear.equals(resScore.getScorePhaseId()))
                        {
                            skillListByYear.add(resScore);
                        } else if (com.pinde.core.common.enums.ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId()))
                        {
                            skillList.add(resScore);
                        } else if (com.pinde.core.common.enums.ResScoreTypeEnum.TheoryScore.getId().equals(resScore.getScoreTypeId()))
                        {
                            theoryList.add(resScore);
                        }
                    }
                }
                if(skillListByYear.size()>0)
                {
                    skillList=skillListByYear;
                }
                Map<String,Map<String,String>> extScoreMap=new HashMap<String,Map<String,String>>();
                for(int i=0;i<skillList.size();i++)
                {
                    Map<String,String> extScore=new HashMap<String,String>();
                    ResScore resScore=skillList.get(i);
                    String content = null==resScore ? "":resScore.getExtScore();
                    if(StringUtil.isNotBlank(content)) {
                        Document doc = DocumentHelper.parseText(content);
                        Element root = doc.getRootElement();
                        Element extScoreInfo = root.element("extScoreInfo");
                        if (extScoreInfo != null) {
                            List<Element> extInfoAttrEles = extScoreInfo.elements();
                            if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                                for (Element attr : extInfoAttrEles) {
                                    String attrName = attr.getName();
                                    String attrValue = attr.getText();
                                    extScore.put(attrName, attrValue);
                                }
                            }
                        }
                    }
                    extScoreMap.put(resScore.getScoreFlow(),extScore);
                }
                for(ResScore score:skillList){
                    Map<String,String> extScore = extScoreMap.get(score.getScoreFlow());
                    DecimalFormat df = new DecimalFormat("0.0");
//                    isPass = score.getSkillScore().intValue()==(com.pinde.core.common.GlobalConstant.PASS)?"是":(score.getSkillScore().intValue()==(com.pinde.core.common.GlobalConstant.UNPASS)?"否":"");
                    if (score.getSkillScore().intValue() == com.pinde.core.common.GlobalConstant.PASS) {
                        isPass="合格";
                    } else if (score.getSkillScore().intValue() == com.pinde.core.common.GlobalConstant.UNPASS) {
                        isPass="不合格";
                    }else if(score.getSkillScore().intValue()==2){
                        isPass="缺考";
                    }else{
                        isPass="";
                    }
                }
                Map<String,Object> resultMap = new HashMap<>();
                resultMap.put("doctorName",doctorName);
                resultMap.put("idNo",idNo);
                resultMap.put("orgName",orgName);
                resultMap.put("placeName",placeName);
                resultMap.put("sessionNumber",sessionNumber);
                resultMap.put("graduationYear",graduationYear);
                resultMap.put("catSpeName",catSpeName);
                resultMap.put("speName",speName);
                resultMap.put("changeSpeName",changeSpeName);
                resultMap.put("qualifiedTestId",qualifiedTestId);
                resultMap.put("theoryTestId",theoryTestId);
                resultMap.put("skillTestId",skillTestId);
                resultMap.put("graduationCertificateNo",graduationCertificateNo);
                resultMap.put("localReason",localReason);
                resultMap.put("cityReason",cityReason);
                resultMap.put("globalReason",globalReason);
                resultMapList.add(resultMap);
            }
        }
        String fileName = "结业合格人员表.xls";
        String titles[] = {
                "doctorName:姓名",
                "idNo:证件号码",
                "orgName:培训基地",
                "placeName:地市",
                "sessionNumber:年级",
                "graduationYear:结业年份",
                "catSpeName:培训类别",
                "changeSpeName:报考专业",
                "speName:培训专业",
                "qualifiedTestId:合格批次",
                "theoryTestId:理论合格成绩考试编号",
                "skillTestId:技能合格成绩考试编号",
                "graduationCertificateNo:结业证书编码",
                "localReason:基地审核意见",
                "cityReason:市局审核意见",
                "globalReason:省厅审核意见"
        };
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjsAllString(titles, resultMapList, response.getOutputStream());
    }

    @RequestMapping(value="/updateAffirm",method= RequestMethod.POST)
    @ResponseBody
    public String updateAffirm(String[] scoreFlows){
        if(null != scoreFlows && scoreFlows.length>0){
            List<String> flowList = Arrays.asList(scoreFlows);
            List<String> scoreFlowList = new ArrayList<>();
            for (String flow:flowList) {
                String[] str = flow.split(",");
                scoreFlowList.addAll(Arrays.asList(str));
            }
            int count = resScoreBiz.updateAffirm(scoreFlowList);
            if(count > 0) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
            }
        }
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
    }
    @RequestMapping(value="/updateNotAffirm",method= RequestMethod.POST)
    @ResponseBody
    public String updateNotAffirm(String[] scoreFlows){
        if(null != scoreFlows && scoreFlows.length>0){
            List<String> flowList = Arrays.asList(scoreFlows);
            List<String> scoreFlowList = new ArrayList<>();
            for (String flow:flowList) {
                String[] str = flow.split(",");
                scoreFlowList.addAll(Arrays.asList(str));
            }
            int count = resScoreBiz.updateNotAffirm(scoreFlowList);
            if(count > 0) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
            }
        }
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
    }
}
