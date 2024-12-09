package com.pinde.sci.ctrl.jsres;


import com.pinde.core.common.enums.*;
import com.pinde.core.model.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.*;
import com.pinde.sci.biz.jszy.IJszyDoctorAuthBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorRecruitBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.res.IResScoreBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.form.jsres.UserResumeExtInfoForm;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/jsres/examSignUp")
public class JsResDoctorExamSignUpController extends GeneralController {

    @Autowired
    private IJsResRecruitDoctorInfoBiz recruitDoctorInfoBiz;
    @Autowired
    private IResOrgSpeBiz resOrgSpeBiz;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;

    @Autowired
    private IJszyDoctorAuthBiz doctorAuthBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private OrgBizImpl orgBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IResDoctorRecruitBiz doctorRecruitBiz;
    @Autowired
    private IResTestConfigBiz resTestConfigBiz;
    @Autowired
    private IOrgBiz sysOrgBiz;
    @Autowired
    private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
    @Autowired
    private IResScoreBiz resScoreBiz;
    @Autowired
    private IJsResGraduationApplyBiz jsresGraduationApplyBiz;
    private static Logger logger = LoggerFactory.getLogger(JsResDoctorExamSignUpController.class);


    @RequestMapping("/main")
    public String main(Model model) {
        SysUser user = GlobalContext.getCurrentUser();
        model.addAttribute("user", user);
        ResDoctor doctor = resDoctorBiz.readDoctor(user.getUserFlow());
        model.addAttribute("doctor", doctor);
        String currYear = DateUtil.getYear();
        model.addAttribute("currYear", currYear);
        String currDateTime = DateUtil.getCurrDateTime2();
        String orgFlow = user.getOrgFlow();
        //如果user表中没有org_flow去医师表中的
        if (StringUtil.isBlank(orgFlow)) {
            orgFlow = doctor.getOrgFlow();
        }
        //如果当前学员是协同基地取他主基地所在的城市
        List<ResJointOrg> resJointOrgList = jointOrgBiz.selectByJointOrgFlow(orgFlow);
        if (resJointOrgList != null && resJointOrgList.size() > 0) {
            orgFlow = resJointOrgList.get(0).getOrgFlow();
        }
        SysOrg sysOrg = sysOrgBiz.readSysOrg(orgFlow);
        List<ResTestConfig> resTestConfigList = resTestConfigBiz.findEffective(currDateTime, sysOrg.getOrgCityId());
        //当前城市的学员有没有正在进行的考试
        Boolean inTime = resTestConfigList.size() > 0 ? true : false;
        model.addAttribute("inTime", inTime);
        List<JsresExamSignup> signups = doctorRecruitBiz.readDoctorExanSignUps(user.getUserFlow());
        model.addAttribute("signups", signups);
        //是否有补考报名的资格（在系统中有资格审核记录且学员为非合格人员才有资格）
        String isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_Y;
        // 已结业学员 不允许补考报名 禅道bug：2648
        if (doctor != null && "21".equals(doctor.getDoctorStatusId())) {
            isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
        }
        // isAllowApply为N 不能参加补考，无需在做结业申请判断
        if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(isAllowApply)) {
            ResDoctorRecruit recruit = new ResDoctorRecruit();
            recruit.setDoctorFlow(doctor.getDoctorFlow());
            recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME DESC");
            //在系统中是否有资格审核记录
            if (recruitList != null && recruitList.size() > 0) {
                ResDoctorRecruit resDoctorRecruit = recruitList.get(0);
                if (resDoctorRecruit != null) {
                    JsresGraduationApply apply = jsresGraduationApplyBiz.searchByRecruitFlow(resDoctorRecruit.getRecruitFlow(), "");
                    if (apply == null) {
                        // 2019级及以前助理全科学员走补考
                        if("2019".compareTo(doctor.getSessionNumber()) >= 0 && doctor.getTrainingSpeId().equals("50")){
                            isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_Y;
                        }else{
                            isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
                        }
                    }
                }
            }
        }
        // isAllowApply为N 不能参加补考，无需在做成绩判断
        if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(isAllowApply)) {
            String isSkillQualifed = com.pinde.core.common.GlobalConstant.FLAG_N;
            String isTheoryQualifed = com.pinde.core.common.GlobalConstant.FLAG_N;
            String isSkill = com.pinde.core.common.GlobalConstant.FLAG_Y;
            String isTheory = com.pinde.core.common.GlobalConstant.FLAG_Y;
            String validYear = String.valueOf(Integer.valueOf(DateUtil.getYear()) - 3);
            List<ResScore> resScoreList = doctorRecruitBiz.selectAllScore(user.getUserFlow(), null);
            if (resScoreList != null && resScoreList.size() > 0) {
                //3年内的技能成绩集合
                List<ResScore> skillList = resScoreList.stream().filter(resScore -> "SkillScore".equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
                //3年内的理论成绩集合
                List<ResScore> theoryList = resScoreList.stream().filter(resScore -> "TheoryScore".equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
                if (skillList.size() > 0) {
                    for (ResScore resScore : skillList) {
                        if ("1".equals(String.valueOf(resScore.getSkillScore()))) {
                            //3年内的技能成绩有合格的
                            isSkillQualifed = com.pinde.core.common.GlobalConstant.FLAG_Y;
                            break;
                        }
                    }
                }else{
                    isSkill = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
                // 如果技能考试没有合格记录 无需在判断理论成绩
                if (theoryList.size() > 0) {
                    if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(isSkillQualifed)) {
                        for (ResScore resScore : theoryList) {
                            if ("1".equals(String.valueOf(resScore.getTheoryScore()))) {
                                //3年内的理论成绩有合格的
                                isTheoryQualifed = com.pinde.core.common.GlobalConstant.FLAG_Y;
                                break;
                            }
                        }
                    }
                }else{
                    isTheory = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
            }
            // 需求变更2018（不含）届以前学员 不做该判断  未参加过考核也可以补考
            if ("2018".compareTo(doctor.getSessionNumber()) <= 0 && com.pinde.core.common.GlobalConstant.FLAG_N.equals(isSkill) && com.pinde.core.common.GlobalConstant.FLAG_N.equals(isTheory)) {
                // 2019/2018/2017级助理全科全走补考报名
                if("2019".compareTo(doctor.getSessionNumber()) >= 0 && doctor.getTrainingSpeId().equals("50")){
                    isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_Y;
                }else{
                    //从未参加过考核
                    isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
            }
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isSkillQualifed) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isTheoryQualifed)) {
                //3年内理论成绩和技能成绩都合格
                isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
            }
        }
        model.addAttribute("isAllowApply", isAllowApply);
        return "jsres/examSignUp/signupMain";
    }


    @RequestMapping(value = "/signUp")
    public String signUp(Model model, String typeId) {

        SysUser user = GlobalContext.getCurrentUser();
        model.addAttribute("user", user);
        ResDoctor doctor = resDoctorBiz.readDoctor(user.getUserFlow());
        model.addAttribute("doctor", doctor);

        ResDoctorRecruit resDoctorRecruit = null;
        if (doctor != null) {
            resDoctorRecruit = doctorRecruitBiz.readResDoctorRecruitBySessionNumber(doctor.getDoctorFlow(), doctor.getSessionNumber());
        }
        ;
        if (resDoctorRecruit != null) {
            Map<String, List<Map<String, String>>> speMap = setSpeMap(model);
            List<Map<String, String>> spes = speMap.get(resDoctorRecruit.getSpeId());
            if (spes != null && spes.size() > 0) {
                model.addAttribute("needChange", com.pinde.core.common.GlobalConstant.FLAG_Y);
                model.addAttribute("spes", spes);
            } else {
                model.addAttribute("needChange", com.pinde.core.common.GlobalConstant.FLAG_N);
            }
        }
        return "jsres/examSignUp/signUp";
    }
    @RequestMapping(value = "/showSignup")
    public String showSignup(Model model, String signupFlow  ) {
        JsresExamSignup signup=doctorRecruitBiz.readExamSignByFlow(signupFlow);
        if(signup!=null) {
            SysUser user = userBiz.readSysUser(signup.getDoctorFlow());
            model.addAttribute("user", user);
            ResDoctor doctor = resDoctorBiz.readDoctor(signup.getDoctorFlow());
            model.addAttribute("doctor", doctor);
        }
        model.addAttribute("signup", signup);

        return "jsres/examSignUp/showSignup";
    }

    @RequestMapping(value = "/auditSignup")
    public String auditSignup(Model model, String signupFlow, String roleFlag) {
        JsresExamSignup signup = doctorRecruitBiz.readExamSignByFlow(signupFlow);
        if (signup != null) {
            SysUser user = userBiz.readSysUser(signup.getDoctorFlow());
            model.addAttribute("user", user);
            List<ResScore> scorelist = resScoreBiz.selectByExampleWithBLOBs(user.getUserFlow());
            String validYear = String.valueOf(Integer.valueOf(DateUtil.getYear()) - 10);
            if (scorelist != null && scorelist.size() > 0) {
                //获取出3年内的成绩集合
                scorelist = scorelist.stream().filter(resScore -> StringUtil.isNotBlank(resScore.getTestId()) && StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
                //过滤得到3年内的技能成绩
                List<ResScore> skillList = scorelist.stream().filter(resScore -> com.pinde.core.common.enums.ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId())).collect(Collectors.toList());
                //过滤得到3年内的理论成绩
                List<ResScore> theoryList = scorelist.stream().filter(resScore -> com.pinde.core.common.enums.ResScoreTypeEnum.TheoryScore.getId().equals(resScore.getScoreTypeId())).collect(Collectors.toList());
                if (scorelist != null && scorelist.size() > 0) {
                    skillList.sort(Comparator.comparing(resScore -> resScore.getTestId()));
                }
                if (theoryList != null && theoryList.size() > 0) {
                    theoryList.sort(Comparator.comparing(resScore -> resScore.getTestId()));
                }
                model.addAttribute("skillList", skillList);
                model.addAttribute("theoryList", theoryList);
            }
            model.addAttribute("scorelist", scorelist);
            ResDoctor doctor = resDoctorBiz.readDoctor(signup.getDoctorFlow());
            model.addAttribute("doctor", doctor);
        }
        List<JsresExamSignupLog> logs = doctorRecruitBiz.getAuditLogsBySignupFlow(signupFlow);
        model.addAttribute("logs", logs);
        model.addAttribute("signup", signup);
        model.addAttribute("roleFlag", roleFlag);
        return "jsres/examSignUp/auditSignup";
    }

    /**
     * 补考审核
     * @param model
     * @param signupFlow
     * @param auditStatusId
     * @param auditReason
     * @param roleFlag
     * @return
     */
    @RequestMapping(value = "/localSaveAudit")
    @ResponseBody
    public String localSaveAudit(Model model, String signupFlow, String auditStatusId, String auditReason, String roleFlag) {
        JsresExamSignup signup = doctorRecruitBiz.readByFlow(signupFlow);
        if (signup != null) {
            if (StringUtil.isBlank(auditStatusId)) {
                return "请选择审核结果！";
            }
            if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId) && StringUtil.isBlank(auditReason)) {
                return "请输入原因！";
            }
            if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
                signup.setLocalAuditStatusId(auditStatusId);
                signup.setLocalAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
                signup.setLocalReason(auditReason);
            } else if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
                signup.setCityAuditStatusId(auditStatusId);
                signup.setCityAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
                signup.setCityReason(auditReason);
            } else if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
                signup.setGlobalAuditStatusId(auditStatusId);
                signup.setGlobalAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
                signup.setGlobalReason(auditReason);
            }
            List<ResTestConfig> resTestConfigList = resTestConfigBiz.findLocalEffective(DateUtil.getCurrDateTime2());
            if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId)) {
                if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
                    signup.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.LocalNotPassed.getId());
                    signup.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.LocalNotPassed.getName());
                } else if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
                    signup.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.ChargeNotPassed.getId());
                    signup.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.ChargeNotPassed.getName());
                } else if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
                    signup.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalNotPassed.getId());
                    signup.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalNotPassed.getName());
                }
            } else {
                if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
                    //基地审核通过后判断需不需要市局审核
                    if (resTestConfigList.size() > 0) {
                        ResTestConfig resTestConfig = resTestConfigList.get(0);
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getChargeAudit())) {
                            signup.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getId());
                            signup.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getName());
                        } else {
                            signup.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getId());
                            signup.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getName());
                        }
                    }
                } else if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
                    signup.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getId());
                    signup.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getName());
                } else if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
                    signup.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalPassed.getId());
                    signup.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalPassed.getName());
                }
            }
            String nowTime = DateUtil.transDateTime(DateUtil.getCurrentTime());
            String f = com.pinde.core.common.GlobalConstant.FLAG_N;
            if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
                resTestConfigList = resTestConfigBiz.findChargeEffective(DateUtil.getCurrDateTime2());
            } else if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
                resTestConfigList = resTestConfigBiz.findGlobalEffective(DateUtil.getCurrDateTime2());
            }
            if (resTestConfigList.size() > 0) {
                f = com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
            if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(f)) {
                return "当前时间不在审核时间段内，无法审核！";
            }
            JsresExamSignupLog log = new JsresExamSignupLog();
            if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
                log.setAuditRoleId(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL);
                log.setAuditRoleName("基地");
            } else if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
                log.setAuditRoleId(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE);
                log.setAuditRoleName("市局");
            } else if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
                log.setAuditRoleId(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL);
                log.setAuditRoleName("省厅");
            }
            log.setSignupFlow(signupFlow);
            log.setAuditReason(auditReason);
            log.setAuditStatusId(auditStatusId);
            log.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
            log.setAuditTime(nowTime);
            SysUser sysuser = GlobalContext.getCurrentUser();
            log.setUserFlow(sysuser.getUserFlow());
            log.setUserName(sysuser.getUserName());
            doctorRecruitBiz.saveChargeAudit(signup, log);
            return "审核成功";
        }
        return "学员申请信息不存在，请刷新后再试！";
    }

    @RequestMapping(value="/saveAudit")
    @ResponseBody
    public String saveAudit(String signupFlow,String flag,String auditInfo){

        SysUser currentUser  = GlobalContext.getCurrentUser();
        JsresExamSignup signup=doctorRecruitBiz.readExamSignByFlow(signupFlow);
        if(signup!=null) {
            if(StringUtil.isBlank(flag))
            {
                return "选择审核状态！";
            }
            if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(flag) && StringUtil.isBlank(auditInfo))
            {
                return "请输入审核意见！";
            }
            signup.setAuditInfo(auditInfo);
            signup.setAuditUserFlow(currentUser.getUserFlow());
            signup.setAuditUserName(currentUser.getUserName());
            signup.setAuditTime(DateUtil.getCurrentTime());
            if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(flag))
            {
                signup.setAuditStatusId("GlobalNotPassed");
                signup.setAuditStatusName("审核不通过");
            }else{
                signup.setAuditStatusId("GlobalPassed");
                signup.setAuditStatusName("审核通过");
            }
            int c=doctorRecruitBiz.saveSignUp(signup);
            if(c==0)
            {
                return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
            }
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
        }
        return "申请信息不存在";
    }

    /**
     * 提交报名
     * @param signupTypeIds
     * @param signup
     * @return
     */
    @RequestMapping(value = "/saveSignUp")
    @ResponseBody
    public String saveSignUp(String[] signupTypeIds, JsresExamSignup signup) {
        SysUser currentUser = GlobalContext.getCurrentUser();
        ResDoctor doctor = resDoctorBiz.readDoctor(currentUser.getUserFlow());
        signup.setDoctorFlow(doctor.getDoctorFlow());
        signup.setDoctorTypeId(doctor.getDoctorTypeId());
        signup.setDoctorTypeName(doctor.getDoctorTypeName());
        String orgFlow = currentUser.getOrgFlow();
        //如果user表中没有org_flow去医师表中的
        if (StringUtil.isBlank(orgFlow)) {
            orgFlow = doctor.getOrgFlow();
        }
        //如果当前学员是协同基地取他主基地所在的城市
        List<ResJointOrg> resJointOrgList = jointOrgBiz.selectByJointOrgFlow(orgFlow);
        if (resJointOrgList != null && resJointOrgList.size() > 0) {
            orgFlow = resJointOrgList.get(0).getOrgFlow();
        }
        SysOrg sysOrg = sysOrgBiz.readSysOrg(orgFlow);
        List<ResTestConfig> resTestConfigList = resTestConfigBiz.findEffective(DateUtil.getCurrDateTime2(), sysOrg.getOrgCityId());
        if (resTestConfigList.size() > 0) {
            ResTestConfig resTestConfig = resTestConfigList.get(0);
            signup.setTestId(resTestConfig.getTestId());
            //判断需不需要基地审核，需要则是待基地审核，不要要再判断需不需要市局审核，需要则是待市局审核，都不需要则是待省厅审核
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getLocalAudit())) {
                signup.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getId());
                signup.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getName());
            } else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getChargeAudit())) {
                signup.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getId());
                signup.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getName());
            } else {
                signup.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getId());
                signup.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getName());
            }
        } else {
            return "当前没有正在进行的考试";
        }
        String isSkillQualifed = com.pinde.core.common.GlobalConstant.FLAG_N;
        String isTheoryQualifed = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResScore> resScoreList = doctorRecruitBiz.selectAllScore(currentUser.getUserFlow(), null);
        String validYear = String.valueOf(Integer.valueOf(DateUtil.getYear()) - 3);
        if (resScoreList.size() > 0) {
            //3年内的技能成绩集合
            List<ResScore> skillList = resScoreList.stream().filter(resScore -> "SkillScore".equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
            //3年内的理论成绩集合
            List<ResScore> theoryList = resScoreList.stream().filter(resScore -> "TheoryScore".equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
            if (skillList.size() > 0) {
                for (ResScore resScore : skillList) {
                    if ("1".equals(String.valueOf(resScore.getSkillScore()))) {
                        //3年内的技能成绩有合格的
                        isSkillQualifed = com.pinde.core.common.GlobalConstant.FLAG_Y;
                    }
                }
            }
            if (theoryList.size() > 0) {
                for (ResScore resScore : theoryList) {
                    if ("1".equals(String.valueOf(resScore.getTheoryScore()))) {
                        //3年内的理论成绩有合格的
                        isTheoryQualifed = com.pinde.core.common.GlobalConstant.FLAG_Y;
                    }
                }
            }
        }
        int c = 0;
//        List<JsresExamSignup> signupLists = doctorRecruitBiz.readDoctorExanSignUps(doctor.getDoctorFlow(), null);
//        if(CollectionUtils.isNotEmpty(signupLists) && signupLists.size() == 2){
//            return "您已考核超过三次，无法进行补考申请。";
//        }
        for (String signupTypeId : signupTypeIds) {
            List<JsresExamSignup> signupList = doctorRecruitBiz.getSignUpListByYear(DateUtil.getYear(), doctor.getDoctorFlow(), signupTypeId);
            if (signupList != null && signupList.size() > 0) {
                for (JsresExamSignup jsresExamSignup : signupList) {
                    //如果有补考记录状态是待审核或者审核通过不允许多次提交
                    if (StringUtil.isNotBlank(jsresExamSignup.getAuditStatusId()) &&
                            ("Auditing".equals(jsresExamSignup.getAuditStatusId()) ||
                                    "WaitChargePass".equals(jsresExamSignup.getAuditStatusId()) ||
                                    "WaitGlobalPass".equals(jsresExamSignup.getAuditStatusId()) ||
                                    "GlobalPassed".equals(jsresExamSignup.getAuditStatusId()))) {
                        if ("Theory".equals(signupTypeId)) {
                            return "您已提交过补考报名，请勿重复提交。";
                        }
                        if ("Skill".equals(signupTypeId)) {
                            return "您已提交过补考报名，请勿重复提交。";
                        }
                    }
                }
            }
        }
        for (String signupTypeId : signupTypeIds) {
            signup.setSignupFlow("");
            signup.setSignupTypeId(signupTypeId);
            if ("Theory".equals(signupTypeId)) {
                signup.setSignupTypeName("理论补考");
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isTheoryQualifed)) {
                    return "您的历史成绩中，有报名科目的合格记录，请勿重复报名";
                }
            }
            if ("Skill".equals(signupTypeId)) {
                signup.setSignupTypeName("技能补考");
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isSkillQualifed)) {
                    return "您的历史成绩中，有报名科目的合格记录，请勿重复报名";
                }
            }
            c += doctorRecruitBiz.saveSignUp(signup);
        }
        if (c == 0) {
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        }
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
    }
    @RequestMapping(value="/examsignupmain")
    public String examsignupmain(Model model,String typeId,String roleFlag) {
        model.addAttribute("typeId",typeId);
        model.addAttribute("roleFlag",roleFlag);
        return "jsres/examSignUp/main";
    }
    @RequestMapping(value="/globalMain")
    public String globalMain(Model model,String roleFlag,String typeId,String tabTag){
        model.addAttribute("tabTag", tabTag);
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
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
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {

            SysUser sysuser=GlobalContext.getCurrentUser();
            SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
            model.addAttribute("org",org);
            SysOrg sysorg =new  SysOrg();
            sysorg.setOrgProvId(org.getOrgProvId());
            sysorg.setOrgCityId(org.getOrgCityId());
            sysorg.setOrgLevelId("CountryOrg");//国家基地
            sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
            List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
            model.addAttribute("orgs", orgs);

        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
            SysUser sysuser=GlobalContext.getCurrentUser();
            SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
            model.addAttribute("org",org);
            SysOrg sysorg =new  SysOrg();
            sysorg.setOrgProvId(org.getOrgProvId());
            sysorg.setOrgLevelId("CountryOrg");//国家基地
            sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
            List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
            model.addAttribute("orgs", orgs);
            List<String> speIds=getSpeIds(sysuser);
            if(speIds!=null)
            {
                model.addAttribute("speIds", speIds);
            }
        }
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("typeId", typeId);
        List<ResTestConfig> resTestConfigs = resTestConfigBiz.findAllEffective();
        model.addAttribute("resTestConfigs", resTestConfigs);
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
            if("SecondWait".equals(tabTag) || "SecondWait2".equals(tabTag)){
                return "jsres/examSignUp/localMainWaitAudit";
            }else {
                return "jsres/examSignUp/localMain";
            }

        }
        if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
            if("SecondWait".equals(tabTag) || "SecondWait2".equals(tabTag)){
                return "jsres/examSignUp/chargeMainWaitAudit";
            }else {
                return "jsres/examSignUp/chargeMain";
            }
        }
        if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
            if("SecondWait".equals(tabTag) || "SecondWait2".equals(tabTag)){
                return "jsres/examSignUp/globalMainWaitAudit";
            }else {
                return "jsres/examSignUp/globalMain";
            }
        }
        if("SecondWait".equals(tabTag)){
            return "jsres/examSignUp/globalMainWaitAudit";
        }else {
            return "jsres/examSignUp/globalMain";
        }
     //   return "jsres/examSignUp/globalMain";
    }

    @RequestMapping(value="/signList")
    public String signList(Model model, String roleFlag,String typeId, Integer currentPage , HttpServletRequest request,
                           String signupYear, String orgFlow, String trainingTypeId,String trainingYears, String trainingSpeId, String datas[],
                           String sessionNumber,  String userName, String idNo, String auditStatusId, String cityId,String signUpTypeId,
                           String testId,String jointOrgFlag,String tabTag, String joinOrgFlow,String isPostpone
    ){
        SysUser currentUser = GlobalContext.getCurrentUser();
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String> orgFlowList=new ArrayList<String>();
        String f = com.pinde.core.common.GlobalConstant.FLAG_N;
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
            SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
            orgFlowList.add(currentOrg.getOrgFlow());
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(currentOrg.getOrgLevelId())) {
                List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(currentOrg.getOrgFlow());
                if(joinOrgs!=null)
                {
                    for(SysOrg sysOrg:joinOrgs)
                    {
                        orgFlowList.add(sysOrg.getOrgFlow());
                    }
                }
            }
            List<ResTestConfig> localEffective = resTestConfigBiz.findLocalEffective(DateUtil.getCurrDateTime2());
            if (localEffective.size() > 0) {
                if (DateUtil.getYear().equals(signupYear)) {
                    f = com.pinde.core.common.GlobalConstant.FLAG_Y;
                }
            }
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
            SysUser sysuser = GlobalContext.getCurrentUser();
            SysOrg org = orgBiz.readSysOrg(sysuser.getOrgFlow());
//            model.addAttribute("org", org);
            SysOrg sysorg = new SysOrg();
            sysorg.setOrgProvId(org.getOrgProvId());
            sysorg.setOrgCityId(org.getOrgCityId());
            param.put("org",sysorg);
//            sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
//            List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
//            if (orgs != null) {
//                for (SysOrg sysOrg : orgs) {
//                    orgFlowList.add(sysOrg.getOrgFlow());
//                }
//            }
            List<ResTestConfig> chargeEffective = resTestConfigBiz.findChargeEffective(DateUtil.getCurrDateTime2());
            if (chargeEffective.size() > 0) {
                if (DateUtil.getYear().equals(signupYear)) {
                    f = com.pinde.core.common.GlobalConstant.FLAG_Y;
                }
            }
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
            SysUser sysuser = GlobalContext.getCurrentUser();
            SysOrg org = orgBiz.readSysOrg(sysuser.getOrgFlow());
//            model.addAttribute("org", org);
            SysOrg sysorg = new SysOrg();
            sysorg.setOrgProvId(org.getOrgProvId());
            if (StringUtil.isNotBlank(cityId)) {
                sysorg.setOrgCityId(cityId);
            }
            param.put("org",sysorg);
//            sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
//            List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
//            if (orgs != null) {
//                for (SysOrg sysOrg : orgs) {
//                    orgFlowList.add(sysOrg.getOrgFlow());
//                }
//            }
            List<ResTestConfig> globalEffective = resTestConfigBiz.findGlobalEffective(DateUtil.getCurrDateTime2());
            if (globalEffective.size() > 0) {
                if (DateUtil.getYear().equals(signupYear)) {
                    f = com.pinde.core.common.GlobalConstant.FLAG_Y;
                }
            }
        }

//        List<String> jointOrgFlowList = new ArrayList<>();
        //新增需求，市局（只有市局要加协同基地限制，详见禅道BUG3391）
//        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
//            if (StringUtil.isBlank(orgFlow)) {
//                SysOrg sysOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
//                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
//                    jointOrgFlowList.add(currentUser.getOrgFlow());
//                    SysOrg searchOrg = new SysOrg();
//                    searchOrg.setOrgProvId(sysOrg.getOrgProvId());
//                    if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
//                        searchOrg.setOrgCityId(sysOrg.getOrgCityId());
//                    }
//                    List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);
//                    for (SysOrg g : exitOrgs) {
//                        List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
//                        if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
//                            for (ResJointOrg jointOrg : resJointOrgList) {
//                                if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
//                                    cityId = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
//                                    if (StringUtil.isNotBlank(cityId) && cityId.equals(sysOrg.getOrgCityId())) {
//                                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
//                                    }
//                                } else {
//                                    jointOrgFlowList.add(jointOrg.getJointOrgFlow());
//                                }
//                            }
//                        }
//                        jointOrgFlowList.add(g.getOrgFlow());
//                    }
//                }
//            } else {
//                jointOrgFlowList.add(orgFlow);
//                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
//                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
//                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
//                        for (ResJointOrg jointOrg : resJointOrgList) {
//                            jointOrgFlowList.add(jointOrg.getJointOrgFlow());
//                        }
//                    }
//                }
//            }
//            orgFlowList.addAll(jointOrgFlowList);
//        }
//        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)
//                || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
//            if(StringUtil.isNotBlank(orgFlow)){
//                jointOrgFlowList.add(orgFlow);
//                List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
//                if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
//                    for (ResJointOrg jointOrg : resJointOrgList) {
//                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
//                    }
//                }
//            }else{
//                SysOrg sysOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
//                jointOrgFlowList.add(currentUser.getOrgFlow());
//                SysOrg searchOrg = new SysOrg();
//                searchOrg.setOrgProvId(sysOrg.getOrgProvId());
//                searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
//                if(getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
//                    searchOrg.setOrgCityId(sysOrg.getOrgCityId());
//                }
//                List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);
//                for (SysOrg g : exitOrgs) {
//                    List<SysOrg> resJointOrgList = orgBiz.searchJointOrgsByOrg(g.getOrgFlow());
//                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
//                        if(roleFlag.equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)){
//                            jointOrgFlowList.add(g.getOrgFlow());
//                        }else{
//                            if(StringUtil.isNotBlank(cityId)){
//                                if(cityId.equals(g.getOrgCityId())) {
//                                    jointOrgFlowList.add(g.getOrgFlow());
//                                }
//                            }else {
//                                jointOrgFlowList.add(g.getOrgFlow());
//                            }
//                        }
////                        jointOrgFlowList.add(g.getOrgFlow());
//                        for (SysOrg so : resJointOrgList) {
//                            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
//                                cityId = so.getOrgCityId();
//                                if (StringUtil.isNotBlank(cityId) && cityId.equals(so.getOrgCityId())) {
//                                    jointOrgFlowList.add(so.getOrgFlow());
//                                }
//                            } else {
//                                if(StringUtil.isNotBlank(cityId)){
//                                    if(cityId.equals(so.getOrgCityId())) {
//                                        jointOrgFlowList.add(so.getOrgFlow());
//                                    }
//                                }else {
//                                    jointOrgFlowList.add(so.getOrgFlow());
//                                }
//                            }
//                        }
//                    }else{
//                        if(StringUtil.isNotBlank(cityId)){
//                            if(cityId.equals(g.getOrgCityId())) {
//                                jointOrgFlowList.add(g.getOrgFlow());
//                            }
//                        }else {
//                            jointOrgFlowList.add(g.getOrgFlow());
//                        }
//                    }
//                }
//            }
//        }
//        orgFlowList.addAll(jointOrgFlowList);
        // bug 市局账号 地区数据权限限制有误，与省厅查询到一样数据
        param.put("orgFlowList",orgFlowList);//培训基地list
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("typeId",typeId);
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("trainingYears",trainingYears);
        param.put("signupYear",signupYear);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("auditStatusId",auditStatusId);
        param.put("signUpTypeId",signUpTypeId);
        param.put("testId",testId);
        param.put("orgFlow",orgFlow);
        param.put("roleFlag",roleFlag);
        param.put("tabTag",tabTag);
        param.put("jointOrgFlow",joinOrgFlow);
        param.put("isPostpone",isPostpone);
        SysUser sysuser=GlobalContext.getCurrentUser();
        List<String> speIds=getSpeIds(sysuser);
        if(speIds!=null && speIds.size() > 0)
        {
            param.put("speIds", speIds);
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> list=doctorRecruitBiz.queryExamSignUpList(param);
        Map<String,String> trainMap = new HashMap<>();
        if (list.size() > 0) {
            for (Map<String, Object> stringObjectMap : list) {
                ResDoctorRecruit recruit = new ResDoctorRecruit();
                recruit.setDoctorFlow((String) stringObjectMap.get("doctorFlow"));
                recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
                if (recruitList.size() > 0) {
                    recruit = recruitList.get(0);
                    String endTime = "";
                    String startTime = "";
                    //开始时间
                    String recruitDate = recruit.getRecruitDate();
                    //培养年限
                    String trianYear = recruit.getTrainYear();
                    String graudationYear = recruit.getGraduationYear();
                    if (StringUtil.isNotBlank(recruitDate) && StringUtil.isNotBlank(graudationYear)) {
                        try {
                            int year = 0;
                            year = Integer.valueOf(graudationYear) - Integer.valueOf(recruitDate.substring(0, 4));
                            if (year != 0) {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = sdf.parse(recruitDate);
                                startTime = recruitDate;
                                //然后使用Calendar操作日期
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(date);
                                calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                                calendar.add(Calendar.DATE, -1);
                                //把得到的日期格式化成字符串类型的时间
                                endTime = sdf.format(calendar.getTime());
                            }
                        } catch (Exception e) {
                            endTime = "";
                        }
                    }
                    //如果没有结业考核年份，按照届别与培养年限计算
                    if (StringUtil.isNotBlank(recruitDate) && StringUtil.isNotBlank(trianYear) && StringUtil.isBlank(endTime)) {
                        int year = 0;
                        if (com.pinde.core.common.enums.JsResTrainYearEnum.OneYear.getId().equals(trianYear)) {
                            year = 1;
                        }
                        if (com.pinde.core.common.enums.JsResTrainYearEnum.TwoYear.getId().equals(trianYear)) {
                            year = 2;
                        }
                        if (com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId().equals(trianYear)) {
                            year = 3;
                        }
                        if (year != 0) {
                            try {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = sdf.parse(recruitDate);
                                startTime = recruitDate;
                                //然后使用Calendar操作日期
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(date);
                                calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                                calendar.add(Calendar.DATE, -1);
                                //把得到的日期格式化成字符串类型的时间
                                endTime = sdf.format(calendar.getTime());

                            } catch (Exception e) {

                            }
                        }
                    }
                    trainMap.put((String)stringObjectMap.get("signupFlow")+"startDate",startTime);
                    trainMap.put((String)stringObjectMap.get("signupFlow")+"endTime",endTime);
                }
            }
        }
        model.addAttribute("trainMap", trainMap);
        model.addAttribute("typeId", typeId);
        model.addAttribute("list", list);
        model.addAttribute("f", f);
        model.addAttribute("roleFlag", roleFlag);
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
            return "jsres/examSignUp/localSignList";
        }
        if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
            return "jsres/examSignUp/chargeSignList";
        }
        if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
            return "jsres/examSignUp/globalSignList";
        }
        return "jsres/examSignUp/signList";
    }

    public List<String> searchJointOrgList(String flag, String Flow) {
        List<String> jointOrgFlowList = new ArrayList<String>();
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(flag)) {
            List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(Flow);
            if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                for (ResJointOrg jointOrg : resJointOrgList) {
                    jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                }
            }
        }
        return jointOrgFlowList;
    }
    @RequestMapping(value="/exportList")
    public void exportList(Model model, String roleFlag,String typeId, Integer currentPage , HttpServletRequest request,HttpServletResponse response,
                           String signupYear, String orgFlow, String trainingTypeId,String trainingYears, String trainingSpeId, String datas[],
                            String sessionNumber,  String userName, String idNo, String auditStatusId, String cityId,String signUpTypeId,String testId,
                           String tabTag,String joinOrgFlow,String isPostpone
    ) throws IOException {
        SysUser currentUser = GlobalContext.getCurrentUser();
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String> orgFlowList=new ArrayList<String>();
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
            SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
            orgFlowList.add(currentOrg.getOrgFlow());
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(currentOrg.getOrgLevelId()))
            {
                List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(currentOrg.getOrgFlow());
                if(joinOrgs!=null)
                {
                    for(SysOrg sysOrg:joinOrgs)
                    {
                        orgFlowList.add(sysOrg.getOrgFlow());
                    }
                }
            }
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
//            SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
//            model.addAttribute("org",org);
//            SysOrg sysorg =new  SysOrg();
//            sysorg.setOrgProvId(org.getOrgProvId());
//            sysorg.setOrgCityId(org.getOrgCityId());
//            sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
//            List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
//            if(orgs!=null) {
//                for(SysOrg sysOrg:orgs) {
//                    orgFlowList.add(sysOrg.getOrgFlow());
//                }
//            }
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
//            SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
//            model.addAttribute("org",org);
//            SysOrg sysorg =new  SysOrg();
//            sysorg.setOrgProvId(org.getOrgProvId());
//            if(StringUtil.isNotBlank(cityId)) {
//                sysorg.setOrgCityId(cityId);
//            }
//            sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
//            List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
//            if(orgs!=null) {
//                for(SysOrg sysOrg:orgs)
//                {
//                    orgFlowList.add(sysOrg.getOrgFlow());
//                }
//            }
        }
        List<String> jointOrgFlowList = new ArrayList<>();
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)
                || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
            if(StringUtil.isNotBlank(orgFlow)){
                jointOrgFlowList.add(orgFlow);
                List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
                if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                    for (ResJointOrg jointOrg : resJointOrgList) {
                        jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                    }
                }
            }else{
                SysOrg sysOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
                jointOrgFlowList.add(currentUser.getOrgFlow());
                SysOrg searchOrg = new SysOrg();
                searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
                    searchOrg.setOrgCityId(sysOrg.getOrgCityId());
                }
                List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);
                for (SysOrg g : exitOrgs) {
                    List<SysOrg> resJointOrgList = orgBiz.searchJointOrgsByOrg(g.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        if (roleFlag.equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
                            jointOrgFlowList.add(g.getOrgFlow());
                        }else{
                            if(StringUtil.isNotBlank(cityId)){
                                if(cityId.equals(g.getOrgCityId())) {
                                    jointOrgFlowList.add(g.getOrgFlow());
                                }
                            }else {
                                jointOrgFlowList.add(g.getOrgFlow());
                            }
                        }
                        for (SysOrg so : resJointOrgList) {
                            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
                                cityId = so.getOrgCityId();
                                if (StringUtil.isNotBlank(cityId) && cityId.equals(so.getOrgCityId())) {
                                    jointOrgFlowList.add(so.getOrgFlow());
                                }
                            } else {
                                if(StringUtil.isNotBlank(cityId)){
                                    if(cityId.equals(so.getOrgCityId())) {
                                        jointOrgFlowList.add(so.getOrgFlow());
                                    }
                                }else {
                                    jointOrgFlowList.add(so.getOrgFlow());
                                }
                            }
                        }
                    }else{
                        if(StringUtil.isNotBlank(cityId)){
                            if(cityId.equals(g.getOrgCityId())) {
                                jointOrgFlowList.add(g.getOrgFlow());
                            }
                        }else {
                            jointOrgFlowList.add(g.getOrgFlow());
                        }
                    }
                }
            }
        }
        orgFlowList.addAll(jointOrgFlowList);
        param.put("orgFlowList",orgFlowList);//培训基地list
        param.put("orgFlow",orgFlow);//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("typeId",typeId);
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("trainingYears",trainingYears);
        param.put("signupYear",signupYear);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("auditStatusId",auditStatusId);
        param.put("signUpTypeId",signUpTypeId);
        param.put("testId",testId);
//        param.put("orgFlow",orgFlow);
        param.put("roleFlag",roleFlag);
        param.put("tabTag",tabTag);
        param.put("jointOrgFlow",joinOrgFlow);
        param.put("isPostpone",isPostpone);
        SysUser sysuser=GlobalContext.getCurrentUser();
        List<String> speIds=getSpeIds(sysuser);
        if(speIds!=null)
        {
            param.put("speIds", speIds);
        }
        List<Map<String,Object>> list=doctorRecruitBiz.queryExamSignUpList(param);
        chargeExportList(list,response,typeId,roleFlag);
    }

    private void chargeExportList(List<Map<String, Object>> list, HttpServletResponse response, String typeId, String roleFlag) throws IOException {
//创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);
        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFCellStyle styleRight = wb.createCellStyle(); //居中
        styleRight.setAlignment(HorizontalAlignment.RIGHT);
        styleRight.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFFont font =wb.createFont();
        font.setFontHeightInPoints((short)17);
        HSSFFont fontTwo =wb.createFont();
        fontTwo.setFontHeightInPoints((short)12);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        HSSFCellStyle styleTwo = wb.createCellStyle();
        styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        HSSFCellStyle styleThree = wb.createCellStyle();
        styleThree.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        //表头
        HSSFRow row = sheet.createRow(0);
        HSSFCell hSSFCell0 = row.createCell(0);
        hSSFCell0.setCellValue("江苏省住院医师规范化培训和助理全科医生培训结业理论统考资格审核名册");
        styleTwo.setFont(fontTwo);
        hSSFCell0.setCellStyle(styleThree);
        // 为工作簿添加sheet
        HSSFRow rowOne = sheet.createRow(1);//第一行
        String[] titles = null;
        sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, (short) 0, 0, (short) 31));
        if("charge".equals(roleFlag)){
            titles = new String[]{
                    "考试编号",
                    "姓名",
                    "性别",
                    "证件类型",
                    "出生日期",
                    "民族",
                    "证件号码",
                    "手机号码",
                    "是否在协同单位完成培训",
                    "国家基地",
                    "培训基地",
                    "年级",
                    "人员类型",
                    "补考申请年份",
                    "结业年份",
                    "培训类别",
                    "培训专业",
                    "报考专业",
                    "培训年限",
                    "补考类型",
                    "审核状态",
                    "是否为军队人员",
                    "是否为西部支援住院医师",
                    "医师资格取得时间",
                    "执业类别",
                    "现有学历",
                    "现有学位",
                    "学位类型",
                    "毕业学校",
                    "毕业时间",
                    "毕业专业",
                    "进入基地时间"
            };
        }else{
            titles = new String[]{
                    "考试编号",
                    "姓名",
                    "性别",
                    "证件类型",
                    "出生日期",
                    "民族",
                    "证件号码",
                    "手机号码",
                    "是否在协同单位完成培训",
                    "国家基地",
                    "培训基地",
                    "年级",
                    "人员类型",
                    "补考申请年份",
                    "结业年份",
                    "培训类别",
                    "培训专业",
                    "报考专业",
                    "培训年限",
                    "补考类型",
                    "审核状态",
                    "是否为军队人员",
                    "是否为西部支援住院医师",
                    "医师资格取得时间",
                    "执业类别",
                    "现有学历",
                    "现有学位",
                    "学位类型",
                    "毕业学校",
                    "毕业时间",
                    "毕业专业",
                    "进入基地时间"
            };
        }


        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowOne.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles[i].getBytes().length * 506);
        }

        int rownum =2;
        if(list!=null&&!list.isEmpty()){

            for(Map<String,Object> sd : list){
                HSSFRow rowDepts= sheet.createRow(rownum);
                int i=0;

                HSSFCell cell = rowDepts.createCell(i++);//序号
                cell.setCellValue((String) sd.get("testId"));
                cell.setCellStyle(styleCenter);
                HSSFCell cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String)sd.get("userName"));
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String) sd.get("sexName"));
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String) sd.get("cretTypeName"));
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String) sd.get("userBirthday"));
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String) sd.get("nationName"));
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String) sd.get("idNo"));
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String) sd.get("userPhone"));
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                if (sd.get("countryOrgFlow") == null || ((String) sd.get("countryOrgFlow")).equals((String) sd.get("orgFlow"))) {
                    cell01.setCellValue("否");
                    cell01.setCellStyle(styleCenter);
                } else {
                    cell01.setCellValue("是");
                    cell01.setCellStyle(styleCenter);
                }
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String) sd.get("countryOrgName"));
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String)sd.get("orgName"));
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String)sd.get("sessionNumber"));
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String) sd.get("doctorTypeName"));
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String) sd.get("signupYear"));
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String) sd.get("graduationYear"));
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String)sd.get("trainingTypeName"));
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String)sd.get("trainingSpeName"));
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String)sd.get("changeSpeName"));
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                String trainingYears="";
                if("OneYear".equals(sd.get("trainingYears")))
                {
                    trainingYears="一年";
                }
                if("TwoYear".equals(sd.get("trainingYears")))
                {
                    trainingYears="二年";
                }
                if("ThreeYear".equals(sd.get("trainingYears")))
                {
                    trainingYears="三年";
                }
                cell01.setCellValue(trainingYears);
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                String signupType = "";
                if("Skill".equals(sd.get("signupTypeId"))){
                    signupType = "技能补考";
                }
                if("Theory".equals(sd.get("signupTypeId"))){
                    signupType = "理论补考";
                }
                cell01.setCellValue(signupType);
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String) sd.get("auditStatusName"));
                cell01.setCellStyle(styleCenter);
                /*cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String) sd.get("isArmy"));
                cell01.setCellStyle(styleCenter);*/
                UserResumeExtInfoForm userResumeExt = null;
                try {
                    userResumeExt = userResumeBiz.converyToJavaBean((String) sd.get("userResume"), UserResumeExtInfoForm.class);
                } catch (DocumentException e) {
                    logger.error("", e);
                }
                String westernSupportResidents = "";
                String qualificationCertificateTime = "";
                String practicingCategoryName = "";
                String degreeName = "";
                String graduationTime = "";
                String specialized = "";
                String armyName = "";
                if (userResumeExt != null) {
                    if (StringUtil.isNotBlank(userResumeExt.getWesternSupportResidents())) {
                        if (userResumeExt.getWesternSupportResidents().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
                            westernSupportResidents = "是";
                        } else if (userResumeExt.getWesternSupportResidents().equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
                            westernSupportResidents = "否";
                        }
                    }
                    qualificationCertificateTime = userResumeExt.getHaveQualificationCertificateTime();
                    practicingCategoryName = userResumeExt.getPracticingCategoryLevelName();
                    degreeName = userResumeExt.getDegreeName();
                    graduationTime = userResumeExt.getGraduationTime();
                    specialized = userResumeExt.getSpecialized();
                    String armyType = userResumeExt.getArmyType();
                    if (StringUtil.isNotBlank(armyType)) {
                        armyName = com.pinde.core.common.enums.ArmyTypeEnum.getNameById(armyType);
                    }
                }
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue(armyName);
                cell01.setCellStyle(styleCenter);

                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue(westernSupportResidents);
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue(qualificationCertificateTime);
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue(practicingCategoryName);
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String) sd.get("educationName"));
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue(degreeName);
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String) sd.get("currDegreeCategoryName"));
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String) sd.get("GRADUATED_NAME"));
              //  cell01.setCellValue((String) sd.get("workOrgName"));
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue(graduationTime);
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue(specialized);
                cell01.setCellStyle(styleCenter);
                cell01 = rowDepts.createCell(i++);
                cell01.setCellValue((String) sd.get("recruitDate"));
                cell01.setCellStyle(styleCenter);

                rownum++;
            }
        }
        String name = "补考审核表.xls";
        response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
        response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        ExcleUtile.setCookie(response);
        wb.write(response.getOutputStream());
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

    /**
     * 省厅批量补考审核
     * @param model
     * @param signupFlow
     * @return
     */
    @RequestMapping(value = "/batchAuditApply")
    public String batchAuditApply(Model model, String[] signupFlow) {
        model.addAttribute("size", signupFlow.length);
        model.addAttribute("flows", Arrays.asList(signupFlow));
        return "jsres/examSignUp/batchAuditApply";
    }

    /**
     * 市局批量补考审核
     * @param model
     * @param signupFlow
     * @return
     */
    @RequestMapping(value = "/batchChargeAuditApply")
    public String batchChargeAuditApply(Model model, String[] signupFlow) {
        model.addAttribute("size", signupFlow.length);
        model.addAttribute("flows", Arrays.asList(signupFlow));
        return "jsres/examSignUp/batchChargeAuditApply2";
    }

    /**
     * 批量补考审核
     * @param model
     * @param signupFlows
     * @param auditStatusId
     * @param auditReason
     * @return
     */
    @RequestMapping(value = "/batchSaveAudit")
    @ResponseBody
    public String batchSaveAudit(Model model, String[] signupFlows, String auditStatusId, String auditReason) {
        if (signupFlows.length > 0) {
            if (StringUtil.isBlank(auditStatusId)) {
                return "请选择审核结果！";
            }
            if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId) && StringUtil.isBlank(auditReason)) {
                return "请输入原因！";
            }
            List<JsresExamSignup> signups = new ArrayList<>();
            List<JsresExamSignupLog> logs = new ArrayList<>();
            for (String signupFlow : signupFlows) {
                JsresExamSignup signup = doctorRecruitBiz.readByFlow(signupFlow);
                if (signup != null) {
                    signup.setGlobalAuditStatusId(auditStatusId);
                    signup.setGlobalAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
                    signup.setGlobalReason(auditReason);
                    if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId)) {
                        signup.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalNotPassed.getId());
                        signup.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalNotPassed.getName());
                    } else {
                        signup.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalPassed.getId());
                        signup.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalPassed.getName());
                    }
                    String nowTime = DateUtil.transDateTime(DateUtil.getCurrentTime());
                    JsresExamSignupLog log = new JsresExamSignupLog();
                    log.setSignupFlow(signupFlow);
                    log.setAuditRoleId(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL);
                    log.setAuditRoleName("省厅");
                    log.setAuditReason(auditReason);
                    log.setAuditStatusId(auditStatusId);
                    log.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
                    log.setAuditTime(nowTime);
                    SysUser sysuser = GlobalContext.getCurrentUser();
                    log.setUserFlow(sysuser.getUserFlow());
                    log.setUserName(sysuser.getUserName());
                    signups.add(signup);
                    logs.add(log);
                } else {
                    return "学员申请信息不存在，请刷新后再试！";
                }
            }

            doctorRecruitBiz.saveBatchAudit(signups, logs);
            return "审核成功";
        }
        return "未选择审核数据！";
    }

    /**
     * 市局批量补考审核
     * @param model
     * @param signupFlows
     * @param auditStatusId
     * @param auditReason
     * @return
     */
    @RequestMapping(value = "/batchChargeSaveAudit")
    @ResponseBody
    public String batchChargeSaveAudit(Model model, String[] signupFlows, String auditStatusId, String auditReason) {
        if (signupFlows.length > 0) {
            if (StringUtil.isBlank(auditStatusId)) {
                return "请选择审核结果！";
            }
            if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId) && StringUtil.isBlank(auditReason)) {
                return "请输入原因！";
            }
            List<JsresExamSignup> signups = new ArrayList<>();
            List<JsresExamSignupLog> logs = new ArrayList<>();
            for (String signupFlow : signupFlows) {
                JsresExamSignup signup = doctorRecruitBiz.readByFlow(signupFlow);
                if (signup != null) {
                    signup.setCityAuditStatusId(auditStatusId);
                    signup.setCityAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
                    signup.setCityReason(auditReason);
                    if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId)) {
                        signup.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.ChargeNotPassed.getId());
                        signup.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.ChargeNotPassed.getName());
                    } else {
                        signup.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getId());
                        signup.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getName());
                    }
                    String nowTime = DateUtil.transDateTime(DateUtil.getCurrentTime());
                    JsresExamSignupLog log = new JsresExamSignupLog();
                    log.setSignupFlow(signupFlow);
                    log.setAuditRoleId(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE);
                    log.setAuditRoleName("市局");
                    log.setAuditReason(auditReason);
                    log.setAuditStatusId(auditStatusId);
                    log.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
                    log.setAuditTime(nowTime);
                    SysUser sysuser = GlobalContext.getCurrentUser();
                    log.setUserFlow(sysuser.getUserFlow());
                    log.setUserName(sysuser.getUserName());
                    signups.add(signup);
                    logs.add(log);
                } else {
                    return "学员申请信息不存在，请刷新后再试！";
                }
            }

            doctorRecruitBiz.saveBatchAudit(signups, logs);
            return "审核成功";
        }
        return "未选择审核数据！";
    }

    private Map<String,List<Map<String,String>>> setSpeMap(Model model) {
        Map<String,List<Map<String,String>>> speMap=new HashMap<>();
        List<Map<String,String>> spes=new ArrayList<>();
        Map<String,String> spe =new HashMap<>();
        spe.put("speId","0400");
        spe.put("speName","皮肤科");
        spes.add(spe);
        speMap.put("10",spes);//		皮肤性病科
        spes=new ArrayList<>();
        spe =new HashMap<>();
        spe.put("speId","0700");
        spe.put("speName","全科");
        spes.add(spe);
        speMap.put("52",spes);//		全科方向（西医）
        spes=new ArrayList<>();
        spe =new HashMap<>();
        spe.put("speId","0900");
        spe.put("speName","外科");
        spes.add(spe);
        spe =new HashMap<>();
        spe.put("speId","1000");
        spe.put("speName","外科（神经外科方向）");
        spes.add(spe);
        spe =new HashMap<>();
        spe.put("speId","1100");
        spe.put("speName","外科（胸心外科方向）");
        spes.add(spe);
        spe =new HashMap<>();
        spe.put("speId","1200");
        spe.put("speName","外科（泌尿外科方向）");
        spes.add(spe);
        spe =new HashMap<>();
        spe.put("speId","1300");
        spe.put("speName","外科（整形外科方向）");
        spes.add(spe);
        spe =new HashMap<>();
        spe.put("speId","1400");
        spe.put("speName","骨科");
        spes.add(spe);
        speMap.put("2",spes);//		外科
        spes=new ArrayList<>();
        spe =new HashMap<>();
        spe.put("speId","2000");
        spe.put("speName","临床病理科");
        spes.add(spe);
        speMap.put("7",spes);//		病理科
        spes=new ArrayList<>();
        spe =new HashMap<>();
        spe.put("speId","2100");
        spe.put("speName","检验医学科");
        spes.add(spe);
        speMap.put("4",spes);//		医学检验科
        spes=new ArrayList<>();

        spe =new HashMap<>();
        spe.put("speId","2200");
        spe.put("speName","放射科");
        spes.add(spe);
        spe =new HashMap<>();
        spe.put("speId","2500");
        spe.put("speName","放射肿瘤科");
        spes.add(spe);
        spe =new HashMap<>();
        spe.put("speId","2400");
        spe.put("speName","核医学科");
        spes.add(spe);
        spe =new HashMap<>();
        spe.put("speId","2300");
        spe.put("speName","超声医学科");
        spes.add(spe);
        speMap.put("6",spes);//		医学影像科
        spes=new ArrayList<>();
        spe =new HashMap<>();
        spe.put("speId","2800");
        spe.put("speName","口腔全科");
        spes.add(spe);
        spe =new HashMap<>();
        spe.put("speId","2900");
        spe.put("speName","口腔内科");
        spes.add(spe);
        spe =new HashMap<>();
        spe.put("speId","3000");
        spe.put("speName","口腔颌面外科");
        spes.add(spe);
        spe =new HashMap<>();
        spe.put("speId","3100");
        spe.put("speName","口腔修复科");
        spes.add(spe);
        spe =new HashMap<>();
        spe.put("speId","3200");
        spe.put("speName","口腔正畸科");
        spes.add(spe);
        spe =new HashMap<>();
        spe.put("speId","3300");
        spe.put("speName","口腔病理科");
        spes.add(spe);
        spe =new HashMap<>();
        spe.put("speId","3400");
        spe.put("speName","口腔颌面影像科");
        spes.add(spe);
        speMap.put("17",spes);//		口腔科
        return speMap;
    }


}