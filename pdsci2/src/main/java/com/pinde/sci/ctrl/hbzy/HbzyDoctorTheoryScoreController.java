package com.pinde.sci.ctrl.hbzy;


import com.pinde.core.entyties.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResBaseBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.jsres.IJsResGraduationApplyBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.enums.jsres.*;
import com.pinde.sci.enums.res.AfterRecTypeEnum;
import com.pinde.sci.enums.res.ResScoreTypeEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hbzy/doctorTheoryScore")
public class HbzyDoctorTheoryScoreController extends GeneralController {
    @Autowired
    private IJsResDoctorBiz jsResDoctorBiz;
    @Autowired
    private IJsResBaseBiz baseBiz;
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
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IResRecBiz resRecBiz;
    @Autowired
    private IResScoreBiz resScoreBiz;
    @Autowired
    private IJsResGraduationApplyBiz graduationApplyBiz;
    @Autowired
    private IResDoctorRecruitBiz resDoctorRecruitBiz;
    public static final String SCOREYEAR_NOT_FIND="请选择成绩年份";

    @RequestMapping(value="/doctorTheoryList")
    public String doctorTheoryList(Model model,String roleFlag){
        SysUser sysuser=GlobalContext.getCurrentUser();
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
        if(OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())){
            List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
            model.addAttribute("jointOrgList",jointOrgList);
        }

        return  "hbzy/theoryScore/doctorTheoryList";
    }
    @RequestMapping(value="/doctorSkillList")
    public String doctorSkillList(Model model,String roleFlag){
        SysUser sysuser=GlobalContext.getCurrentUser();
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
        return  "hbzy/skillScore/doctorTheoryList";
    }
    @RequestMapping(value="/doctorPublicList")
    public String doctorPublicList(Model model,String roleFlag){
        SysUser sysuser=GlobalContext.getCurrentUser();
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
        System.out.println(orgFlowList.size());
        System.out.println(countryList.size());
        System.out.println(provinceList.size());
        model.addAttribute("orgs", orgs);
        model.addAttribute("roleFlag", roleFlag);
        return  "hbzy/publicScore/doctorPublicList";
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
    public String doctorRecruitSun(Model model,Integer currentPage,String roleFlag,
                                   HttpServletRequest request,ResDoctor doctor,SysUser user,
                                   String baseId,String jointOrgFlag,
                                   String derateFlag,String orgLevel,String[] datas,
                                   String graduationYear,String scoreYear,String isHege,String skillIsHege){
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
            if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
//                jointOrgFlowList=searchJointOrgList(jointOrgFlag,sysuser.getOrgFlow());
                jointOrgFlowList.add(sysuser.getOrgFlow());
            }
            if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)
                    ||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)
                    ||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.RES_ROLE_SCOPE_SCHOOL)) {
                SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
                if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
                    jointOrgFlowList.add(sysuser.getOrgFlow());
                    SysOrg searchOrg=new SysOrg();
                    searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                    if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
                        searchOrg.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    //基地类型
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
        }else if("allOrgs".equals(doctor.getOrgFlow())){
            if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
                jointOrgFlowList=searchJointOrgList(GlobalConstant.FLAG_Y,sysuser.getOrgFlow());
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
            if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
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
            if(GlobalConstant.FLAG_Y.equals(derateFlag)){
                doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
                resDoctorRecruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
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
        resDoctorRecruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
        //resDoctorRecruit=null;
       // doctor=null; sysUser=null; org=null; jointOrgFlowList=null; derateFlag=null;scoreYear=null;isHege=null;docTypeList=null;
        List<JsResDoctorRecruitExt> doctorList=null;
        if (GlobalConstant.RES_ROLE_SCOPE_SCHOOL.equals(roleFlag)) {
            ServletContext servletContext=request.getServletContext();
            if (servletContext!=null) {
                List<SysDict> sysDictList=(List<SysDict>) servletContext.getAttribute("dictTypeEnum"+"SendSchool"+"List");
                for (SysDict sysDict : sysDictList) {
                    String dictId="send_school_"+sysDict.getDictId()+"_org_rel";
                    String orgFlow=InitConfig.getSysCfg(dictId);
                    doctor.setWorkOrgId(sysDict.getDictId());
                    if (sysuser.getOrgFlow().equals(orgFlow)) {
                        doctorList=jsResDoctorRecruitBiz.searchDoctorScoreInfoExts(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,docTypeList,hegeScore,"");
                        break;
                    }else{
                        continue;
                    }
                }
            }
        }else if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
            doctorList=jsResDoctorRecruitBiz.searchDoctorSkillAndTheoryScoreExts(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,skillIsHege,docTypeList,hegeScore);
        }else{
             doctorList = jsResDoctorRecruitBiz.searchDoctorScoreInfoExts(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,docTypeList,hegeScore,"");
        }

        model.addAttribute("roleFlag",roleFlag);
        model.addAttribute("doctorList",doctorList);
        model.addAttribute("datas",datas);
        model.addAttribute("scoreYear",scoreYear);
        return  "hbzy/theoryScore/doctorListZi";
    }
    //根据条件打印结业成绩
    @RequestMapping(value="/doctorTheoryListSunExport")
    public void doctorTheoryListSunExport(String roleFlag,HttpServletResponse response,
                                   HttpServletRequest request,ResDoctor doctor,SysUser user,
                                   String baseId,String jointOrgFlag,
                                   String derateFlag,String orgLevel,String[] datas,
                                   String graduationYear,String scoreYear,String isHege,String skillIsHege) throws Exception {
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
            if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
//                jointOrgFlowList=searchJointOrgList(jointOrgFlag,sysuser.getOrgFlow());
                jointOrgFlowList.add(sysuser.getOrgFlow());
            }
            if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)
                    ||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)
                    ||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.RES_ROLE_SCOPE_SCHOOL)) {
                SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
                if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
                    jointOrgFlowList.add(sysuser.getOrgFlow());
                    SysOrg searchOrg=new SysOrg();
                    searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                    if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
                        searchOrg.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    //基地类型
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
        }else if("allOrgs".equals(doctor.getOrgFlow())){
            if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
                jointOrgFlowList=searchJointOrgList(GlobalConstant.FLAG_Y,sysuser.getOrgFlow());
                jointOrgFlowList.add(sysuser.getOrgFlow());
            }
        }else{
            jointOrgFlowList.add(doctor.getOrgFlow());
            //是否勾选了协同机构
            if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
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
            if(GlobalConstant.FLAG_Y.equals(derateFlag)){
                doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
                resDoctorRecruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
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

        resDoctorRecruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
        //resDoctorRecruit=null;
        // doctor=null; sysUser=null; org=null; jointOrgFlowList=null; derateFlag=null;scoreYear=null;isHege=null;docTypeList=null;
        List<JsResDoctorRecruitExt> doctorList=null;
        if (GlobalConstant.RES_ROLE_SCOPE_SCHOOL.equals(roleFlag)) {
            ServletContext servletContext=request.getServletContext();
            if (servletContext!=null) {
                List<SysDict> sysDictList=(List<SysDict>) servletContext.getAttribute("dictTypeEnum"+"SendSchool"+"List");
                for (SysDict sysDict : sysDictList) {
                    String dictId="send_school_"+sysDict.getDictId()+"_org_rel";
                    String orgFlow=InitConfig.getSysCfg(dictId);
                    doctor.setWorkOrgId(sysDict.getDictId());
                    if (sysuser.getOrgFlow().equals(orgFlow)) {
                        doctorList=jsResDoctorRecruitBiz.searchDoctorScoreInfoExts(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,docTypeList,hegeScore,"");
                        break;
                    }else{
                        continue;
                    }
                }
            }
        }else if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
            doctorList=jsResDoctorRecruitBiz.searchDoctorSkillAndTheoryScoreExts(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,skillIsHege,docTypeList,hegeScore);
        }else{
            doctorList = jsResDoctorRecruitBiz.searchDoctorScoreInfoExts(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,docTypeList,hegeScore,"");
        }
        List<Map<String,Object>> resultMapList = new ArrayList<>();
        if(doctorList!=null&&doctorList.size()>0){
            for(JsResDoctorRecruitExt doctorRecruitExt:doctorList){
                String doctorName = doctorRecruitExt.getSysUser().getUserName();
                String idNo = doctorRecruitExt.getSysUser().getIdNo();
                String orgName = doctorRecruitExt.getOrgName();
                String sessionNumber = doctorRecruitExt.getSessionNumber();
                String catSpeName = doctorRecruitExt.getCatSpeName();
                String speName = doctorRecruitExt.getSpeName();
                String year = scoreYear;
                String theoryScore="";
                if(doctorRecruitExt.getResScore().getTheoryScore()!=null){
                    theoryScore = doctorRecruitExt.getResScore().getTheoryScore().intValue()==(GlobalConstant.PASS)?"合格":(doctorRecruitExt.getResScore().getTheoryScore().intValue()==(GlobalConstant.UNPASS)?"不合格":"缺考");
                }
                String firstStationScore = "";
                String secondStationScore = "";
                String thirdStationScore = "";
                String fourthStationScore = "";
                String fifthStationScore = "";
                String sixthStationScore = "";
                String seventhStationScore = "";
                String eighthStationScore = "";
                String ninthStationScore = "";
                String all = "";
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

                        if(ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId())&&StringUtil.isNotBlank(scoreYear)&&scoreYear.equals(resScore.getScorePhaseId()))
                        {
                            skillListByYear.add(resScore);
                        }else if(ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId()))
                        {
                            skillList.add(resScore);
                        }else if(ResScoreTypeEnum.TheoryScore.getId().equals(resScore.getScoreTypeId()))
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
                    firstStationScore = extScore.get("firstStationScore");
                    secondStationScore = extScore.get("secondStationScore");
                    thirdStationScore = extScore.get("thirdStationScore");
                    fourthStationScore = extScore.get("fourthStationScore");
                    fifthStationScore = extScore.get("fifthStationScore");
                    sixthStationScore = extScore.get("sixthStationScore");
                    seventhStationScore = extScore.get("seventhStationScore");
                    eighthStationScore = extScore.get("eighthStationScore");
                    ninthStationScore = extScore.get("ninthStationScore");
                    double all0 = Double.parseDouble(StringUtil.defaultIfEmpty(firstStationScore,"0"))+
                            Double.parseDouble(StringUtil.defaultIfEmpty(secondStationScore,"0"))+
                            Double.parseDouble(StringUtil.defaultIfEmpty(thirdStationScore,"0"))+
                            Double.parseDouble(StringUtil.defaultIfEmpty(fourthStationScore,"0"))+
                            Double.parseDouble(StringUtil.defaultIfEmpty(fifthStationScore,"0"))+
                            Double.parseDouble(StringUtil.defaultIfEmpty(sixthStationScore,"0"))+
                            Double.parseDouble(StringUtil.defaultIfEmpty(seventhStationScore,"0"))+
                            Double.parseDouble(StringUtil.defaultIfEmpty(eighthStationScore,"0"))+
                            Double.parseDouble(StringUtil.defaultIfEmpty(ninthStationScore,"0"));
                    DecimalFormat df = new DecimalFormat("0.0");
                    all = df.format(all0);
                    isPass = score.getSkillScore().intValue()==(GlobalConstant.PASS)?"合格":(score.getSkillScore().intValue()==(GlobalConstant.UNPASS)?"不合格":"缺考");
                }
                Map<String,Object> resultMap = new HashMap<>();
                resultMap.put("doctorName",doctorName);
                resultMap.put("idNo",idNo);
                resultMap.put("orgName",orgName);
                resultMap.put("sessionNumber",sessionNumber);
                resultMap.put("catSpeName",catSpeName);
                resultMap.put("speName",speName);
                resultMap.put("year",year);
                resultMap.put("theoryScore",theoryScore);
                resultMap.put("firstStationScore",firstStationScore);
                resultMap.put("secondStationScore",secondStationScore);
                resultMap.put("thirdStationScore",thirdStationScore);
                resultMap.put("fourthStationScore",fourthStationScore);
                resultMap.put("fifthStationScore",fifthStationScore);
                resultMap.put("sixthStationScore",sixthStationScore);
                resultMap.put("seventhStationScore",seventhStationScore);
                resultMap.put("eighthStationScore",eighthStationScore);
                resultMap.put("ninthStationScore",ninthStationScore);
                resultMap.put("all",all);
                resultMap.put("isPass",isPass);
                resultMapList.add(resultMap);
            }
        }
        String fileName = "学员成绩信息汇总表.xls";
        String titles[] = {
                "doctorName:姓名",
                "idNo:证件号码",
                "orgName:培训基地",
                "sessionNumber:年级",
                "catSpeName:培训类别",
                "speName:培训专业",
                "year:理论成绩年份",
                "theoryScore:理论成绩",
                "year:技能成绩年份",
                "firstStationScore:第一站",
                "secondStationScore:第二站",
                "thirdStationScore:第三站",
                "fourthStationScore:第四站",
                "fifthStationScore:第五站",
                "sixthStationScore:第六站",
                "seventhStationScore:第七站",
                "eighthStationScore:第八站",
                "ninthStationScore:第九站",
                "all:总分",
                "isPass:是否合格"
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
     * @param cityId
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
                                   String cityId,String jointOrgFlag,
                                   String derateFlag,String orgLevel,String[] datas,
                                   String graduationYear,String scoreYear,String isHege) throws DocumentException {
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
            if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
                jointOrgFlowList=searchJointOrgList(jointOrgFlag,sysuser.getOrgFlow());
                jointOrgFlowList.add(sysuser.getOrgFlow());
            }
            if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)
                    ||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)
                    ||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.RES_ROLE_SCOPE_SCHOOL)) {
                SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
                if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
                    jointOrgFlowList.add(sysuser.getOrgFlow());
                    SysOrg searchOrg=new SysOrg();
                    searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                    if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
                        searchOrg.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    //基地类型
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
                    if(StringUtil.isNotEmpty(cityId)){
                        org.setOrgCityId(cityId);
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
            }
        }else{
            jointOrgFlowList.add(doctor.getOrgFlow());
            //是否勾选了协同机构
            if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
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
            if(GlobalConstant.FLAG_Y.equals(derateFlag)){
                doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
                resDoctorRecruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
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
        resDoctorRecruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());

        //resDoctorRecruit=null;
       // doctor=null; sysUser=null; org=null; jointOrgFlowList=null; derateFlag=null;scoreYear=null;isHege=null;docTypeList=null;
        List<JsResDoctorRecruitExt> doctorList=null;
        if (GlobalConstant.RES_ROLE_SCOPE_SCHOOL.equals(roleFlag)) {
            ServletContext servletContext=request.getServletContext();
            if (servletContext!=null) {
                List<SysDict> sysDictList=(List<SysDict>) servletContext.getAttribute("dictTypeEnum"+"SendSchool"+"List");
                for (SysDict sysDict : sysDictList) {
                    String dictId="send_school_"+sysDict.getDictId()+"_org_rel";
                    String orgFlow=InitConfig.getSysCfg(dictId);
                    doctor.setWorkOrgId(sysDict.getDictId());
                    if (sysuser.getOrgFlow().equals(orgFlow)) {
                        doctorList=jsResDoctorRecruitBiz.searchDoctorSkillScore(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,docTypeList,"");
                        break;
                    }else{
                        continue;
                    }
                }
            }
        }else{
            doctorList = jsResDoctorRecruitBiz.searchDoctorSkillScore(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,docTypeList,"");
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
        return  "hbzy/skillScore/doctorListZi";
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
            if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
                jointOrgFlowList=searchJointOrgList(jointOrgFlag,sysuser.getOrgFlow());
                jointOrgFlowList.add(sysuser.getOrgFlow());
            }
            if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)
                    ||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)
                    ||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.RES_ROLE_SCOPE_SCHOOL)) {
                SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
                if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
                    jointOrgFlowList.add(sysuser.getOrgFlow());
                    SysOrg searchOrg=new SysOrg();
                    searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                    if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
                        searchOrg.setOrgCityId(sysOrg.getOrgCityId());
                    }
                    //基地类型
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
            //是否勾选了协同机构
            if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
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
            if(GlobalConstant.FLAG_Y.equals(derateFlag)){
                doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
                resDoctorRecruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
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
        resDoctorRecruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());

        List<JsResDoctorRecruitExt> doctorList=null;
        if (GlobalConstant.RES_ROLE_SCOPE_SCHOOL.equals(roleFlag)) {
            ServletContext servletContext=request.getServletContext();
            if (servletContext!=null) {
                List<SysDict> sysDictList=(List<SysDict>) servletContext.getAttribute("dictTypeEnum"+"SendSchool"+"List");
                for (SysDict sysDict : sysDictList) {
                    String dictId="send_school_"+sysDict.getDictId()+"_org_rel";
                    String orgFlow=InitConfig.getSysCfg(dictId);
                    doctor.setWorkOrgId(sysDict.getDictId());
                    if (sysuser.getOrgFlow().equals(orgFlow)) {
                        doctorList=jsResDoctorRecruitBiz.searchDoctorPublicScore(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,notAllQualified,allQualified,docTypeList);
                        break;
                    }else{
                        continue;
                    }
                }
            }
        }else{
            doctorList = jsResDoctorRecruitBiz.searchDoctorPublicScore(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,notAllQualified,allQualified,docTypeList);
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
        return  "hbzy/publicScore/doctorListZi";
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
    /**
     * 理论成绩导入页面
     *
     */
    @RequestMapping(value="/importSchoolRoll")
    public String importSchoolRoll(){

        return "hbzy/theoryScore/importSchoolRoll";
    }
    @RequestMapping(value="/importSkillScore")
    public String importSkillScore(){
        return "hbzy/skillScore/importSchoolRoll";
    }
    @RequestMapping(value="/importPublicScore")
    public String importPublicScore(){
        return "hbzy/publicScore/importSchoolRoll";
    }
    /**
     * 理论成绩导入
     */
    @RequestMapping(value="/importSchoolRollFromExcel")
    @ResponseBody
    public String importSchoolRollFromExcel(MultipartFile file,String scoreYear){
        if(StringUtil.isBlank(scoreYear))
        {
            return SCOREYEAR_NOT_FIND;
        }
        if(file.getSize() > 0){
            try{
                ExcelUtile result = (ExcelUtile) resDoctorBiz.importCourseFromExcel(file, scoreYear, scoreYear);
                if(null!=result)
                {
                    String code= (String) result.get("code");
                    int count=(Integer) result.get("count");
                    String msg= (String) result.get("msg");
                    if("1".equals(code))
                    {
                        return GlobalConstant.UPLOAD_FAIL+msg;
                    }else{
                        if(GlobalConstant.ZERO_LINE != count){
                            return GlobalConstant.UPLOAD_SUCCESSED + "导入"+count+"条记录！";
                        }else{
                            return GlobalConstant.UPLOAD_FAIL;
                        }
                    }
                }else {
                    return GlobalConstant.UPLOAD_FAIL;
                }
            }catch(RuntimeException re){
                re.printStackTrace();
                return re.getMessage();
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    /**
     * 技能成绩导入
     */
    @RequestMapping(value="/importSkillScoreFromExcel")
    @ResponseBody
    public String importSkillScoreFromExcel(MultipartFile file,String scoreYear){
        if(StringUtil.isBlank(scoreYear))
        {
            return SCOREYEAR_NOT_FIND;
        }
        if(file.getSize() > 0){
            try{
                ExcelUtile result = (ExcelUtile) resDoctorBiz.importSkillScoreFromExcel(file, scoreYear, scoreYear);
                if(null!=result)
                {
                    String code= (String) result.get("code");
                    int count=(Integer) result.get("count");
                    String msg= (String) result.get("msg");
                    if("1".equals(code))
                    {
                        return GlobalConstant.UPLOAD_FAIL+msg;
                    }else{
                        if(GlobalConstant.ZERO_LINE != count){
                            return GlobalConstant.UPLOAD_SUCCESSED + "导入"+count+"条记录！";
                        }else{
                            return GlobalConstant.UPLOAD_FAIL;
                        }
                    }
                }else {
                    return GlobalConstant.UPLOAD_FAIL;
                }
            }catch(RuntimeException re){
                re.printStackTrace();
                return re.getMessage();
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
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
                        return GlobalConstant.UPLOAD_FAIL+msg;
                    }else{
                        if(GlobalConstant.ZERO_LINE != count){
                            return GlobalConstant.UPLOAD_SUCCESSED + "导入"+count+"条记录！";
                        }else{
                            return GlobalConstant.UPLOAD_FAIL;
                        }
                    }
                }else {
                    return GlobalConstant.UPLOAD_FAIL;
                }
            }catch(RuntimeException re){
                re.printStackTrace();
                return re.getMessage();
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }
    @RequestMapping(value="/deleteScore")
    @ResponseBody
    public String deleteScore(String scoreFlow,String roleFlag){
        ResScore resScore=resScoreBiz.searchByScoreFlow(scoreFlow);
        if(resScore!=null){
            resScore.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            int result=resScoreBiz.save(resScore);
            if(result==GlobalConstant.ONE_LINE){
                return GlobalConstant.FLAG_Y;
            }
        }
        return GlobalConstant.FLAG_N;
    }
    @RequestMapping(value="/deleteSkillScore")
    @ResponseBody
    public String deleteSkillScore(String scoreFlow,String roleFlag){

        ResScore resScore=resScoreBiz.searchByScoreFlow(scoreFlow);
        if(resScore!=null){
            resScore.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            int result=resScoreBiz.save(resScore);
            if(result==GlobalConstant.ONE_LINE){
                return GlobalConstant.FLAG_Y;
            }
        }
        return GlobalConstant.FLAG_N;
    }
    @RequestMapping(value="/deletePublicScore")
    @ResponseBody
    public String deletePublicScore(String scoreFlow,String roleFlag){
        ResScore resScore=resScoreBiz.searchByScoreFlow(scoreFlow);
        if(resScore!=null){
            resScore.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            int result=resScoreBiz.save(resScore);
            if(result==GlobalConstant.ONE_LINE){
                return GlobalConstant.FLAG_Y;
            }
        }
        return GlobalConstant.FLAG_N;
    }
    @RequestMapping(value="/saveTheoryScore")
    @ResponseBody
    public String saveScore(ResScore score,String roleFlag){
        if(score==null){
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        String scoreFlow = score.getScoreFlow();
        if(StringUtil.isBlank(scoreFlow))
        {
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        int result = resScoreBiz.save(score);
       if(GlobalConstant.ZERO_LINE==result){
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }
    @RequestMapping(value="/saveSkillIsPass")
    @ResponseBody
    public String saveSkillIsPass(ResScore score,String roleFlag,String stationName,String isPass) throws DocumentException {
        if(score==null){
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        String scoreFlow = score.getScoreFlow();
        if(StringUtil.isBlank(scoreFlow))
        {
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        if(StringUtil.isBlank(isPass))
        {
            return GlobalConstant.OPRE_FAIL_FLAG;
        }

        if("1".equals(isPass))
        {
            score.setSkillScore(BigDecimal.valueOf(Double.valueOf(GlobalConstant.PASS)));
        }else if("0".equals(isPass))
        {
            score.setSkillScore(BigDecimal.valueOf(Double.valueOf(GlobalConstant.UNPASS)));
        }
        int result = resScoreBiz.save(score);
        if(GlobalConstant.ZERO_LINE==result){
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }
    @RequestMapping(value="/savePublicIsPass")
    @ResponseBody
    public String savePublicIsPass(ResScore score,String roleFlag,String stationName,String isPass,String recruitFlow,String doctorFlow) throws DocumentException {
        if(score==null){
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        String scoreFlow = score.getScoreFlow();
        if(StringUtil.isBlank(scoreFlow))
        {
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        if(StringUtil.isBlank(isPass))
        {
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        if("1".equals(isPass))
        {
            score.setSkillScore(BigDecimal.valueOf(Double.valueOf(GlobalConstant.PASS)));
        }else if("0".equals(isPass))
        {
            score.setSkillScore(BigDecimal.valueOf(Double.valueOf(GlobalConstant.UNPASS)));
        }
        ResScore old=resScoreBiz.searchByScoreFlow(scoreFlow);
        if(!(old!=null&&StringUtil.isNotBlank(old.getRecruitFlow())))
        {
            if(StringUtil.isNotBlank(recruitFlow))
            {
                ResDoctorRecruit recruit=resDoctorRecruitBiz.getNewRecruit(doctorFlow);
                if(recruit!=null)
                    score.setRecruitFlow(recruit.getRecruitFlow());
            }else {
                score.setRecruitFlow(recruitFlow);
            }
        }
        score.setPaperFlow(GlobalConstant.FLAG_Y);
        int result = resScoreBiz.save(score);
        if(GlobalConstant.ZERO_LINE==result){
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }
    @RequestMapping(value="/saveSkillScore")
    @ResponseBody
    public String saveSkillScore(ResScore score,String roleFlag,String stationName,String skilScore) throws DocumentException {
        if(score==null){
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        String scoreFlow = score.getScoreFlow();
        if(StringUtil.isBlank(scoreFlow))
        {
            return GlobalConstant.OPRE_FAIL_FLAG;
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
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        score.setExtScore(extScore);
        int result = resScoreBiz.save(score);
        if(GlobalConstant.ZERO_LINE==result){
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }

    @RequestMapping(value="/savePublicScore")
    @ResponseBody
    public String savePublicScore(ResScore score,String roleFlag,String stationName,String publicScore,String recruitFlow,String doctorFlow) throws DocumentException {

        if(score==null){
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        String scoreFlow = score.getScoreFlow();
        if(StringUtil.isBlank(scoreFlow))
        {
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        score=resScoreBiz.searchByScoreFlow(score.getScoreFlow());
        if(ResScoreTypeEnum.TheoryScore.getId().equals(stationName))//全科成绩
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
                return GlobalConstant.OPRE_FAIL_FLAG;
            }
            score.setExtScore(extScore);
        }
        ResScore old=resScoreBiz.searchByScoreFlow(scoreFlow);
        if(!(old!=null&&StringUtil.isNotBlank(old.getRecruitFlow())))
        {
            if(StringUtil.isNotBlank(recruitFlow))
            {
                ResDoctorRecruit recruit=resDoctorRecruitBiz.getNewRecruit(doctorFlow);
                if(recruit!=null)
                    score.setRecruitFlow(recruit.getRecruitFlow());
            }else {
                score.setRecruitFlow(recruitFlow);
            }
        }
        score.setPaperFlow(GlobalConstant.FLAG_Y);
        int result = resScoreBiz.save(score);
        if(GlobalConstant.ZERO_LINE==result){
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }
    @RequestMapping(value="/doctorSubmit")
    @ResponseBody
    public String doctorSubmit(String doctorFlow) throws DocumentException {

        if(doctorFlow==null){
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        ResDoctor resDoctor=resDoctorBiz.findByFlow(doctorFlow);
        if(null==resDoctor)
        {
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        resDoctor.setGraduationStatusId(CertificateStatusEnum.Submit.getId());
        resDoctor.setGraduationStatusName(CertificateStatusEnum.Submit.getName());
        resDoctor.setDisagreeReason("");
        int result = resDoctorBiz.editDoctor(resDoctor);
        if(GlobalConstant.ZERO_LINE==result){
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
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
        return "hbzy/doctor/guiPeiDetail";
    }

    @RequestMapping(value="/saveCompleteDate")
    @ResponseBody
    public String saveCompleteDate(String  doctorFlow ,String key,String time){
        if(doctorFlow==null){
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        ResDoctor resDoctor=resDoctorBiz.findByFlow(doctorFlow);
        if(null==resDoctor)
        {
            return GlobalConstant.OPRE_FAIL_FLAG;
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
        if(GlobalConstant.ZERO_LINE==result){
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
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
        return "hbzy/doctor/publicScoreDetail";
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

                if(ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId())&&StringUtil.isNotBlank(scoreYear)&&scoreYear.equals(resScore.getScorePhaseId()))
                {
                    skillListByYear.add(resScore);
                }else if(ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId()))
                {
                    skillList.add(resScore);
                }else if(ResScoreTypeEnum.TheoryScore.getId().equals(resScore.getScoreTypeId()))
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

        return "hbzy/doctor/skillScoreDetail";
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
            return "hbzy/asse/hospital/auditMainForJoint";
        }
        return "hbzy/asse/hospital/main";
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
        if(OrgLevelEnum.CountryOrg.getId().equals(currentOrg.getOrgLevelId()))
        {
            List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(currentOrg.getOrgFlow());
            orgs.addAll(joinOrgs);
        }
        orgs.add(currentOrg);
        model.addAttribute("orgs", orgs);
        return "hbzy/asse/hospital/waitAuditMain";
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
            if(OrgLevelEnum.CountryOrg.getId().equals(currentOrg.getOrgLevelId())) {
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
        param.put("auditStatusId", JsResAuditStatusEnum.Auditing.getId());
        param.put("applyYear",applyYear);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> list = graduationApplyBiz.chargeQueryApplyList(param);
        model.addAttribute("list",list);
        String nowTime = DateUtil.transDateTime(DateUtil.getCurrentTime());
        String startDate = InitConfig.getSysCfg("local_submit_start_time");
        String endDate = InitConfig.getSysCfg("local_submit_end_time");
        String f="N";
        if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
            if(startDate.compareTo(nowTime) <= 0 && endDate.compareTo(nowTime)>=0)
            {
                f="Y";
            }
        }
        model.addAttribute("f",f);
        model.addAttribute("currentUser",currentUser);
        return "hbzy/asse/hospital/waitAuditList";
    }
    @RequestMapping(value="/exportList")
    public void exportList(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request,HttpServletResponse response,
                                String orgFlow,String trainingTypeId,String trainingSpeId,String datas[],
                                String sessionNumber,String graduationYear,String qualificationMaterialId,String passFlag,
                                String userName,String idNo,String completeBi,String auditBi,String auditStatusId,String isWaitAudit,String applyYear
    ) throws IOException {
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String> orgFlowList = new ArrayList();
        if (StringUtil.isNotBlank(orgFlow)) {
            orgFlowList.add(orgFlow);
        }else {
            if(OrgLevelEnum.CountryOrg.getId().equals(currentOrg.getOrgLevelId())) {
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
        param.put("auditStatusId", auditStatusId);
        param.put("applyYear", applyYear);
        List<Map<String,Object>> list = graduationApplyBiz.chargeQueryListForExport(param);
        graduationApplyBiz.chargeExportList(list,response,isWaitAudit);

    }

    /**
     * 情况总览筛选条件页
     * @return
     */
    @RequestMapping(value="/auditMain")
    public String auditMain(Model model){
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        model.addAttribute("org",currentOrg);
        List<SysOrg>  orgs = new ArrayList<>();
        if(OrgLevelEnum.CountryOrg.getId().equals(currentOrg.getOrgLevelId()))
        {
            List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(currentOrg.getOrgFlow());
            orgs.addAll(joinOrgs);
        }
        orgs.add(currentOrg);
        model.addAttribute("orgs", orgs);
        return "hbzy/asse/hospital/auditMain";
    }

    /**
     * 情况总览列表页
     * @return
     */
    @RequestMapping(value="/auditList")
    public String auditList(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request,
                            String orgFlow,String trainingTypeId,String trainingSpeId,String datas[],
                            String sessionNumber,String graduationYear,String qualificationMaterialId,String passFlag,
                            String userName,String idNo,String completeBi,String auditBi,String auditStatusId,String applyYear
    ){
        SysUser currentUser = GlobalContext.getCurrentUser();
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String> orgFlowList = new ArrayList();
        if (StringUtil.isNotBlank(orgFlow)) {
            orgFlowList.add(orgFlow);
        }else {
            if(OrgLevelEnum.CountryOrg.getId().equals(currentOrg.getOrgLevelId())) {
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
        param.put("auditStatusId",auditStatusId);
        param.put("applyYear",applyYear);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryApplyList(param);
        if(StringUtil.isNotBlank(roleFlag)){
            model.addAttribute("roleFlag",roleFlag);
        }
        model.addAttribute("list",list);
        String nowTime=DateUtil.transDateTime(DateUtil.getCurrentTime());
        String startDate=InitConfig.getSysCfg("local_submit_start_time");
        String endDate=InitConfig.getSysCfg("local_submit_end_time");
        String f="N";
        if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
            if(startDate.compareTo(nowTime)<=0&&endDate.compareTo(nowTime)>=0)
            {
                f="Y";
            }
        }
        model.addAttribute("f",f);
        model.addAttribute("currentUser",currentUser);
        return "hbzy/asse/hospital/auditList";
    }

    /****************************高******校******管******理******员******角******色************************************************/

    @RequestMapping(value="/doctorSkillListForUni")
    public String doctorSkillListForUni(Model model,String roleFlag){
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());

        List<SysOrg> orgs=orgBiz.getUniOrgs(org.getSendSchoolId(),org.getSendSchoolName());
        model.addAttribute("orgs",orgs);
        return  "hbzy/university/doctorTheoryList";
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
        return  "hbzy/university/skillScore/doctorListZi";
    }
}
