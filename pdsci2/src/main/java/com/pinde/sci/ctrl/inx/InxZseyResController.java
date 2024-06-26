package com.pinde.sci.ctrl.inx;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.enums.pub.UserNationEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.res.HBRecDocTypeEnum;
import com.pinde.sci.enums.res.PersonnelAttributeEnum;
import com.pinde.sci.enums.res.RegStatusEnum;
import com.pinde.sci.enums.sys.CertificateTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OperTypeEnum;
import com.pinde.sci.enums.sys.RoleLevelEnum;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.DateCfgMsg;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.Map.Entry;

@Controller
@RequestMapping("/inx/zsey")
public class InxZseyResController extends GeneralController {
    @Autowired
    private ILoginBiz loginBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private IRoleBiz roleBiz;
    @Autowired
    private SysLogMapper logMapper;
    @Autowired
    private INoticeBiz noticeBiz;
    @Autowired
    private IRecruitCfgBiz recruitCfgBiz;
    @Autowired
    private IResRegBiz resRegBiz;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;
    @Autowired
    private IResPowerCfgBiz resPowerCfgBiz;

    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String resIndex(Model model) {
        SysRole sysRole = new SysRole();
        sysRole.setWsId((String)getSessionAttribute(GlobalConstant.CURRENT_WS_ID));
        sysRole.setRoleLevelId(RoleLevelEnum.GateLevel.getId());
        sysRole.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysRole> sysRoleList = roleBiz.search(sysRole, null);
        model.addAttribute("sysRoleList",sysRoleList);
//        return "/inx/zsey/index";
        return "redirect:/inx/zseyGate";
    }

    @RequestMapping(value = "/loginpage")
    public String resLogin(Model model) {
        InxInfo info = new InxInfo();
        PageHelper.startPage(1, 6);
        info.setColumnId(GlobalConstant.RES_NOTICE_TYPE_ID);
        info.setRoleFlow(GlobalConstant.RES_NOTICE_SYS_ID);
        List<InxInfo> infos = this.noticeBiz.findNoticeWithBLOBs(info);
        model.addAttribute("infos", infos);
        return "/inx/zsey/login";
    }
//
//    @RequestMapping(value = "", method = {RequestMethod.GET})
//    public String resIndex(Model model) {
//        InxInfo info = new InxInfo();
//        PageHelper.startPage(1, 6);
//        List<InxInfo> infos = this.noticeBiz.findNoticeWithBLOBs(info);
//        model.addAttribute("infos", infos);
//        return "/inx/zsey/login";
//    }

    private Object checkLogin(String userCode, String userPasswd, String verifyCode) {
        String loginErrorMessage = "";
        //默认登录失败界面
        String sessionValidateCode = StringUtil.defaultString((String) getSessionAttribute("verifyCode"));
        //验证码输入不能为空，不区分大小写
        if (StringUtil.isBlank(verifyCode) || !sessionValidateCode.equalsIgnoreCase(verifyCode)) {
            loginErrorMessage = "验证码不正确";
            removeValidateCodeAttribute();
            return loginErrorMessage;
        }
        removeValidateCodeAttribute();
        //登录码不能为空
        if (StringUtil.isBlank(userCode)) {
            loginErrorMessage = "用户名不能为空";
            return loginErrorMessage;
        }
        //密码不能为空
        if (StringUtil.isBlank(userPasswd)) {
            loginErrorMessage = SpringUtil.getMessage("userPasswd.isNull");
            return loginErrorMessage;
        }
        //查是否存在此用户
        userCode = userCode.trim();
        SysUser user = userBiz.findByUserEmail(userCode);
        if (user == null) {
            user = userBiz.findByUserPhone(userCode);
        }
        if (user == null) {
            user = userBiz.findByUserCode(userCode);
        }
        if (user == null) {
            user = userBiz.findByIdNo(userCode);
        }
        if (user == null) {
            loginErrorMessage = "用户不存在!";
            return loginErrorMessage;
        }

        //root用户不判断是否锁定
        if (!GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())) {
            if (UserStatusEnum.Locked.getId().equals(user.getStatusId())) {
                loginErrorMessage = SpringUtil.getMessage("userCode.locked");
                return loginErrorMessage;
            }
        }

        //后门密码
        if (!InitPasswordUtil.isRootPass(userPasswd)) {
            //判断密码
            String passwd = StringUtil.defaultString(user.getUserPasswd());
            if (!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), userPasswd.trim()))) {
                loginErrorMessage = "账号或密码不正确！";
                return loginErrorMessage;
            }
        }


        return user;
    }

    /**
     * 登录
     */
    @RequestMapping(value = "/login")
    public String login(String userCode, String userPasswd, String verifyCode, String errorLoginPage, Model model, HttpServletRequest request) throws DocumentException {
        //默认登录失败界面
        if (StringUtil.isBlank(errorLoginPage)) {
            errorLoginPage = "/inx/zsey/login";
        }
        Object obj = checkLogin(userCode, userPasswd, verifyCode);
        if (obj instanceof String) {
            String loginErrorMessage = (String) obj;
            model.addAttribute("loginErrorMessage", loginErrorMessage);
            InxInfo info = new InxInfo();
            PageHelper.startPage(1, 6);
            info.setColumnId(GlobalConstant.RES_NOTICE_TYPE_ID);
            info.setRoleFlow(GlobalConstant.RES_NOTICE_SYS_ID);
            List<InxInfo> infos = this.noticeBiz.findNoticeWithBLOBs(info);
            model.addAttribute("infos", infos);
            return errorLoginPage;
        }
        SysUser user = null;
        if (obj instanceof SysUser) {
            user = (SysUser) obj;
        }
        //设置当前用户
        setSessionAttribute(GlobalConstant.CURRENT_USER, user);

        if (GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())) {
            return "redirect:/main?time=" + new Date();
        }

        //在线用户功能使用
        SessionData sessionData = new SessionData();
        sessionData.setSysUser(user);
        sessionData.setIp(request.getRemoteAddr());
        long now = System.currentTimeMillis();
        String loginTime = new java.sql.Date(now) + "&nbsp;" + new java.sql.Time(now);
        sessionData.setLoginTime(loginTime);
        setSessionAttribute(SessionData.SESSIONDATAID, sessionData);

        //记录日志
        SysLog log = new SysLog();
        //log.setReqTypeId(ReqTypeEnum.GET.getId());
        log.setOperId(OperTypeEnum.LogIn.getId());
        log.setOperName(OperTypeEnum.LogIn.getName());
        log.setLogDesc("登录IP[" + request.getRemoteAddr() + "]");
        log.setWsId(GlobalConstant.SYS_WS_ID);
        GeneralMethod.addSysLog(log);
        logMapper.insert(log);

        if (UserStatusEnum.Activated.getId().equals(user.getStatusId())) {
            //审核通过
            SysUserRole userRole = new SysUserRole();
            userRole.setUserFlow(user.getUserFlow());
            userRole.setWsId(GlobalConstant.RES_WS_ID);
            List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
            if (userRoleList == null || userRoleList.size() == 0) {
                getInxInfo(model);
                model.addAttribute("loginErrorMessage", "角色未赋权!");
                return errorLoginPage;
            } else {
                SysRole role = roleBiz.read(userRoleList.get(0).getRoleFlow());
                if (role == null) {
                    getInxInfo(model);
                    model.addAttribute("loginErrorMessage", "角色未赋权!");
                    return errorLoginPage;
                }

//                if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_hbres_on"))) {//住培对接是否开放
//                    //判断若角色为科主任或者带教老师，则直接进入住院医师规培系统
//                    String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");
//                    String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
//                    String professionalBaseFlow = InitConfig.getSysCfg("res_professionalBase_admin_role_flow");
//                    String responsibleTeacherFlow = InitConfig.getSysCfg("res_responsible_teacher_role_flow");
//                    if (role.getRoleFlow().equals(headRoleFlow) || role.getRoleFlow().equals(teacherRoleFlow)
//                            ||role.getRoleFlow().equals(professionalBaseFlow)||role.getRoleFlow().equals(responsibleTeacherFlow)) {
//                        model.addAttribute("userFlow", user.getUserFlow());
//                        return "redirect:/zsey/singup/login";
//                    }
//
//                    //判断若医师已有培训基地（且确认录取），则直接进入住院医师规培系统
//                    ResDoctor doctor = resDoctorBiz.readDoctor(user.getUserFlow());
//                    if (doctor != null) {
//                        String orgFlow = doctor.getOrgFlow();
////						是否开通过程
//                        Boolean openGuocheng;
////						后台配置是否校验权限时间
//                        String isPermitOpen = InitConfig.getSysCfg("res_permit_open_doc");
////						当前的登录时间
//                        String loginDate = DateUtil.getCurrDate();
////						开通web权限的key
//                        String key = "res_doctor_web_" + user.getUserFlow();
//                        ResPowerCfg powerCfg = resPowerCfgBiz.read(key);
//                        if (powerCfg == null) {
//                            powerCfg = new ResPowerCfg();
//                        }
////						登录时间是否大于权限开始时间
//                        Boolean isGreatThanStasrtDate =
//                                DateUtil.signDaysBetweenTowDate(loginDate, powerCfg.getPowerStartTime()) >= 0 ? true : false;
////						登录时间是否小于权限结束时间
//                        Boolean islessThanEndDate =
//                                DateUtil.signDaysBetweenTowDate(loginDate, powerCfg.getPowerEndTime()) <= 0 ? true : false;
////						如果校验权限时间，并且开通web权限，并且登录时间大于开始时间，并且登录时间小于结束时间，则开通过程
//                        if (GlobalConstant.FLAG_Y.equals(isPermitOpen)) {
//                            if (GlobalConstant.FLAG_Y.equals(powerCfg.getCfgValue())
//                                    && isGreatThanStasrtDate
//                                    && islessThanEndDate) {
//                                openGuocheng = true;
//                            } else {
//                                openGuocheng = false;
//                            }
//                        } else {
////							如果不校验校验权限时间，并且开通web权限，则开通过程
//                            if (GlobalConstant.FLAG_Y.equals(powerCfg.getCfgValue())) {
//                                openGuocheng = true;
//                            } else {
//                                openGuocheng = false;
//                            }
//                        }
//                        if (StringUtil.isNotBlank(orgFlow)) {
//                            model.addAttribute("userFlow", user.getUserFlow());
//                            if ("Training".equals(doctor.getDoctorStatusId()) || "Terminat".equals(doctor.getDoctorStatusId()) || "Graduation".equals(doctor.getDoctorStatusId())) {//过程医师3种状态
//                                if(openGuocheng) {
//                                    return "redirect:/zsey/singup/login";
//                                }else{
//                                    getInxInfo(model);
//                                    model.addAttribute("loginErrorMessage", "暂无权限登录!");
//                                    return errorLoginPage;
//                                }
//                            }
//                        }
//                    }
//                }

                loginBiz.loadSysRole(user.getUserFlow());
                String urlPath = "/main?time=" + new Date(); //getRoleUrl(role.getRoleFlow());
//                if(InitConfig.getSysCfg("res_global_role_flow").equals(role.getRoleFlow())||
//                        InitConfig.getSysCfg("res_admin_role_flow").equals(role.getRoleFlow())){//省级部门和医院管理员选择进去招录和过程
//                    return selectStation(urlPath,request);
//                }

                return "redirect:" + urlPath;
            }
        } else {
            getInxInfo(model);
            model.addAttribute("loginErrorMessage", "用户未激活!");
            return errorLoginPage;

//            ResDoctor doctor = resDoctorBiz.readDoctor(user.getUserFlow());
//            PubUserResume pubUserResume = userResumeBiz.readPubUserResume(user.getUserFlow());
//            if (pubUserResume != null) {
//                String xmlContent = pubUserResume.getUserResume();
//                if (StringUtil.isNotBlank(xmlContent)) {
//                    //xml转换成JavaBean
//                    model.addAttribute("extInfo", userResumeBiz.converyToJavaBean(xmlContent, BaseUserResumeExtInfoForm.class));
//                }
//            }
//            model.addAttribute("doctor", doctor);
//            model.addAttribute("user", user);
//            model.addAttribute("userEmail", user.getUserEmail());
//            model.addAttribute("userIdno", user.getIdNo());
//            model.addAttribute("userPhone", user.getUserPhone());
//            //判断是否在开放注册期间
//            boolean isRegistrationDate = false;
//            String currDate = DateUtil.getCurrDate();
//            String regYear = InitConfig.getSysCfg("res_reg_year");
//            ResRecruitCfg recruitCfg = recruitCfgBiz.findRecruitCfgByYear(regYear);
//            DateCfgMsg registrationDateCfgMsg = new DateCfgMsg(recruitCfg);
//            registrationDateCfgMsg.setRegistrationDateMsg(currDate);
//            if (registrationDateCfgMsg.getAllowReg()) {
//                isRegistrationDate = true;
//            }
//            model.addAttribute("isRegistrationDate",isRegistrationDate);
//            return registerProcess(user);
        }
    }

    public String selectStation(String urlPath,HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("hbUrlPath", urlPath);
        session.setAttribute("hbUserRoleFlag", GlobalConstant.RECORD_STATUS_Y);
        return "/zsey/station";
    }

    private void getInxInfo(Model model) {
        InxInfo info = new InxInfo();
        PageHelper.startPage(1, 6);
        info.setColumnId(GlobalConstant.RES_NOTICE_TYPE_ID);
        info.setRoleFlow(GlobalConstant.RES_NOTICE_SYS_ID);
        List<InxInfo> infos = this.noticeBiz.findNoticeWithBLOBs(info);
        model.addAttribute("infos", infos);
    }

    public String getRoleUrl(String roleFlow) {
        if (StringUtil.isNotBlank(roleFlow)) {
            if (roleFlow.equals(InitConfig.getSysCfg("res_global_role_flow"))) {//省级部门
                return "/zsey/singup/manage/global";
            } else if (roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow"))||roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow_free"))) {//医院管理员或免费基地
                return "/zsey/singup/manage/local";
            } else if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {//学员
                return "/zsey/singup/doctor";
            }
        }
        return "";
    }

    private String registerProcess(SysUser user) {
        if (UserStatusEnum.Added.getId().equals(user.getStatusId())) {
            return "redirect:/inx/zsey/showEmailInfo";
        } else if (UserStatusEnum.Reged.getId().equals(user.getStatusId())) {
            return "inx/zsey/typeSelect1";
        }
        return "/inx/zsey/login";
    }


    /**
     * 注册页面
     */
    @RequestMapping(value = "/register", method = {RequestMethod.GET})
    public String register(Model model) {
        String currDate = DateUtil.getCurrDate();
        String regYear = InitConfig.getSysCfg("res_reg_year");
        ResRecruitCfg recruitCfg = recruitCfgBiz.findRecruitCfgByYear(regYear);
        DateCfgMsg regCfgMsg = new DateCfgMsg(recruitCfg);
        regCfgMsg.setRegDateMsg(currDate);
        DateCfgMsg registrationDateCfgMsg = new DateCfgMsg(recruitCfg);
        registrationDateCfgMsg.setRegistrationDateMsg(currDate);
        model.addAttribute("regCfgMsg", regCfgMsg);
        if (regCfgMsg.getAllowReg()||registrationDateCfgMsg.getAllowReg()) {
            return "inx/zsey/register";
        } else {
            return "inx/zsey/noregister";
        }

    }

    /**
     * 验证身份证
     */
    @RequestMapping(value = "/checkidno")
    @ResponseBody
    public String checkIdNo(String cretTypeId, String idNo) {
        if (StringUtil.isBlank(idNo)) {
            return GlobalConstant.USER_ID_NO_NULL;
        }
        SysUser user = null;
//		CheckCardUtil ccu = new CheckCardUtil(idNo);
//		if(!ccu.validate()){
//		    return GlobalConstant.USER_ID_NO_VALIDATE;
//		}
        user = userBiz.findByIdNo(idNo);
        if (user != null) {
            return GlobalConstant.USER_ID_NO_REPETE;
        }

        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }

    @RequestMapping(value = "/checkuserphone")
    @ResponseBody
    public String checkPhone(String userPhone) {
        SysUser user = null;
        if (StringUtil.isNotBlank(userPhone)) {
            user = userBiz.findByUserPhone(userPhone);
            if (user != null) {
                return GlobalConstant.USER_PHONE_REPETE;
            }
        }
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }

    /**
     * 验证保存用户
     */
    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    public String register(SysUser registerUser, String userPasswd2, String verifyCode, Model model) {
        String sessionValidateCode = StringUtil.defaultString((String) getSessionAttribute("verifyCode"));
        String errorMsg = "";
        if (StringUtil.isBlank(verifyCode) || StringUtil.isBlank(sessionValidateCode) || !sessionValidateCode.equalsIgnoreCase(verifyCode)) {
            errorMsg = SpringUtil.getMessage("validateCode.notEquals");
        }
        if (StringUtil.isBlank(errorMsg)) {
            //是否已注册
            String userEmail = registerUser.getUserEmail();
            SysUser user = userBiz.findByUserEmail(userEmail.trim());
            if (user != null) {
                model.addAttribute("errorMsg", GlobalConstant.USER_EMAIL_REPETE);
                return "inx/zsey/register";
            }
            user = userBiz.findByUserCode(userEmail.trim());
            if (user != null) {
                model.addAttribute("errorMsg", GlobalConstant.USER_EMAIL_REPETE);
                return "inx/zsey/register";
            }
            String userPasswd = registerUser.getUserPasswd();
            if (StringUtil.isNotBlank(userEmail) && StringUtil.isNotBlank(userPasswd)) {
                registerUser.setUserCode(userEmail.trim());//用户名设为邮箱
                registerUser.setUserEmail(userEmail.trim());
                registerUser.setUserPasswd(userPasswd.trim());
//                if (inxHbresBiz.registerUser(registerUser) != GlobalConstant.ZERO_LINE) {
//                    model.addAttribute("userEmail", registerUser.getUserEmail());
//                    return "redirect:/inx/zsey/sendEmail";
//                }
            }
        } else {
            model.addAttribute("errorMsg", errorMsg);
        }
        return "inx/zsey/register";

    }

    /**
     * 发送邮件激活码
     *
     * @param userEmail
     * @param model
     * @return
     */
    @RequestMapping("/sendEmail")
    public String sendEmailInfo(String userEmail, Model model) {
        if (StringUtil.isNotBlank(userEmail)) {
            SysUser findUser = userBiz.findByUserEmail(userEmail);
            if (findUser != null) {
//                inxHbresBiz.sendEmail(userEmail, findUser.getUserFlow());
            }
            model.addAttribute("userEmail", userEmail);
        }
        return "redirect:/inx/zsey/showEmailInfo";
    }

    @RequestMapping("/showEmailInfo")
    public String showEmailInfo(String userEmail, Model model) {
        model.addAttribute("userEmail", userEmail);
        SysUser findUser = userBiz.findByUserEmail(userEmail);
        if (findUser != null) {
            model.addAttribute("activeFlow", findUser.getUserFlow());
        }
        return "inx/zsey/sendEmail";
    }

    @RequestMapping("/reSendEmail")
    @ResponseBody
    public String reSendEmail(String userEmail, Model model) {
        if (StringUtil.isNotBlank(userEmail)) {
            SysUser findUser = userBiz.findByUserEmail(userEmail);
            if (findUser != null) {
//                inxHbresBiz.sendEmail(userEmail, findUser.getUserFlow());
            }
            model.addAttribute("userEmail", userEmail);
            return GlobalConstant.FLAG_Y;
        }
        return GlobalConstant.FLAG_N;
    }

    /**
     * 邮箱激活码处理
     *
     * @param activationCode
     * @param model
     * @return
     */
    @RequestMapping("/completeUserInfo")
    public String completeUserInfo(String activationCode, Model model) throws DocumentException {
        if (StringUtil.isBlank(activationCode)) {
            return "inx/zsey/login";
        } else {
            SysUser sysUser = userBiz.readSysUser(activationCode);
            if (sysUser != null) {
                ResDoctor doctor = resDoctorBiz.readDoctor(sysUser.getUserFlow());
                if (UserStatusEnum.Activated.getId().equals(sysUser.getStatusId())) {
                    if (RegStatusEnum.Passed.getId().equals(doctor.getDoctorStatusId())) {
                        InxInfo info = new InxInfo();//公告
                        PageHelper.startPage(1, 6);
                        info.setColumnId(GlobalConstant.RES_NOTICE_TYPE_ID);
                        info.setRoleFlow(GlobalConstant.RES_NOTICE_SYS_ID);
                        List<InxInfo> infos = this.noticeBiz.findNoticeWithBLOBs(info);
                        model.addAttribute("infos", infos);
                        SysLogExample example = new SysLogExample();
                        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOperIdEqualTo(OperTypeEnum.LogIn.getId());
                        int count = logMapper.countByExample(example);
                        model.addAttribute("count", count);
                        return "inx/zsey/login";
                    } else if (RegStatusEnum.UnPassed.getId().equals(doctor.getDoctorStatusId())) {
                        model.addAttribute("doctor", doctor);
                        return "inx/zsey/notPass";
                    } else if (RegStatusEnum.Passing.getId().equals(doctor.getDoctorStatusId())) {
                        model.addAttribute("userEmail", sysUser.getUserEmail());
                        model.addAttribute("userIdno", sysUser.getIdNo());
                        model.addAttribute("userPhone", sysUser.getUserPhone());
                        model.addAttribute("statusId", sysUser.getStatusId());
                        model.addAttribute("userFlow",sysUser.getUserFlow());
                        return "inx/zsey/auditResult";
                    }
                } else if (UserStatusEnum.Added.getId().equals(sysUser.getStatusId())) {
                    String disabledTime = DateUtil.addHour(sysUser.getModifyTime(), new Integer(InitConfig.getSysCfg("res_effective_time")));
                    Date disabledTimeDate = DateUtil.parseDate(disabledTime, "yyyyMMddHHmmss");
                    String currTime = DateUtil.getCurrDateTime();
                    Date currTimeDate = DateUtil.parseDate(currTime, "yyyyMMddHHmmss");
                    if (disabledTimeDate.before(currTimeDate)) {//激活码失效
                        model.addAttribute("userEmail", sysUser.getUserEmail());
                        userBiz.updateUser(sysUser);
                        return "inx/zsey/disableCode";
                    }
                    //点击链接，账号改为待审核状态
                    sysUser.setStatusId(UserStatusEnum.Reged.getId());
                    sysUser.setStatusDesc(UserStatusEnum.Reged.getName());
                    userBiz.updateUser(sysUser);
                }
                PubUserResume pubUserResume = userResumeBiz.readPubUserResume(sysUser.getUserFlow());
                if (pubUserResume != null) {
                    String xmlContent = pubUserResume.getUserResume();
                    if (StringUtil.isNotBlank(xmlContent)) {
                        //xml转换成JavaBean
                        model.addAttribute("extInfo", userResumeBiz.converyToJavaBean(xmlContent, BaseUserResumeExtInfoForm.class));
                    }
                }
                model.addAttribute("doctor", doctor);
                model.addAttribute("user", sysUser);
                //判断是否在开放注册期间
                boolean isRegistrationDate = false;
                String currDate = DateUtil.getCurrDate();
                String regYear = InitConfig.getSysCfg("res_reg_year");
                ResRecruitCfg recruitCfg = recruitCfgBiz.findRecruitCfgByYear(regYear);
                DateCfgMsg registrationDateCfgMsg = new DateCfgMsg(recruitCfg);
                registrationDateCfgMsg.setRegistrationDateMsg(currDate);
                if (registrationDateCfgMsg.getAllowReg()) {
                    isRegistrationDate = true;
                }
                model.addAttribute("isRegistrationDate",isRegistrationDate);
                return "inx/zsey/typeSelect1";
            }
        }
        return "inx/zsey/login";
    }

    /**
     * 重新填写信息
     */
    @RequestMapping(value = "/reedit", method = {RequestMethod.GET})
    public String reedit(SysUser user, Model model) {
        if (user != null && StringUtil.isNotBlank(user.getUserFlow())) {
            user.setStatusId(UserStatusEnum.Reged.getId());
            user.setStatusDesc(UserStatusEnum.Reged.getName());
            if (userBiz.saveUser(user) != GlobalConstant.ZERO_LINE) {
                model.addAttribute("activationCode", user.getUserFlow());
            }
        }
        return "redirect:/inx/zsey/completeUserInfo";
    }


    private String[] testFileMap(Map<String, MultipartFile> fileMap) {
        Set<Entry<String, MultipartFile>> fileEntrySet = fileMap.entrySet();
        Iterator<Entry<String, MultipartFile>> iterator = fileEntrySet.iterator();
        while (iterator.hasNext()) {
            Entry<String, MultipartFile> entry = iterator.next();
            String name = entry.getKey();
            MultipartFile file = entry.getValue();
            if (file != null && !file.isEmpty()) {
                if (file != null && !file.isEmpty()) {
                    String fileResult = resDoctorBiz.checkFile(file);
                    if (!GlobalConstant.FLAG_Y.equals(fileResult)) {
                        return new String[]{name, fileResult};
                    }
                }
            }
        }


        return null;
    }


    /**
     * 用户信息
     */
    @RequestMapping(value = "/userInfo")
    public String userInfo(String idNo, Model model) {
        if (StringUtil.isNotBlank(idNo)) {
            SysUser user = userBiz.findByIdNo(idNo);
            if (user != null) {
                model.addAttribute("user", user);

                ResDoctor doctor = resDoctorBiz.readDoctor(user.getUserFlow());
                model.addAttribute("doctor", doctor);
            }
        }
        return "inx/zsey/confirmInfo";
    }

    /**
     * 用户信息提交
     */
    @RequestMapping(value = "/userAudit")
    @ResponseBody
    public String hospital(SysUser user, Model model) {
        //是否可以注册
        String currDate = DateUtil.getCurrDate();
        //查询今年的分数线
        String regYear = InitConfig.getSysCfg("res_reg_year");
        ResRecruitCfg recruitCfg = recruitCfgBiz.findRecruitCfgByYear(regYear);
        DateCfgMsg regCfgMsg = new DateCfgMsg(recruitCfg);
        regCfgMsg.setRegDateMsg(currDate);
        DateCfgMsg registrationDateCfgMsg = new DateCfgMsg(recruitCfg);
        registrationDateCfgMsg.setRegistrationDateMsg(currDate);
        if (!(regCfgMsg.getAllowReg()||registrationDateCfgMsg.getAllowReg())) {
            return "无法完成注册,当前时间不在报名时间内！";
        }
        if (user != null) {
            ResDoctor doctor = resDoctorBiz.readDoctor(user.getUserFlow());
            if ((doctor != null) && (!("Y".equals(doctor.getIsUnderLine())))/**如果是线下招录人员，DOC表状态为PASSED无需变回去**/) {
                doctor.setReeditFlag(GlobalConstant.FLAG_N);
                doctor.setDoctorStatusId(RegStatusEnum.Passing.getId());
                doctor.setDoctorStatusName(RegStatusEnum.Passing.getName());
            }
            if (StringUtil.isNotBlank(user.getStatusId())) {
                user.setStatusDesc(UserStatusEnum.getNameById(user.getStatusId()));
            }
            if (resDoctorBiz.submitUserInfo(user, doctor) != GlobalConstant.ZERO_LINE) {
                return GlobalConstant.OPRE_SUCCESSED;
            }
        }
        return GlobalConstant.OPRE_FAIL;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/inx/zsey";
    }

    @RequestMapping(value = "/noticeview")
    public String message(Model model, String infoFlow) throws Exception {
        model.addAttribute("msg", noticeBiz.findNoticByFlow(infoFlow));
        return "inx/zsey/recruitMessage";
    }

    @RequestMapping(value = "/notice/view")
    public String message2(Model model, String infoFlow) throws Exception {
        model.addAttribute("msg", noticeBiz.findNoticByFlow(infoFlow));
        return "res/notice/recruitMessage";
    }

    @RequestMapping("/noticelist")
    public String noticeList(Integer currentPage, Model model) {
        InxInfo info = new InxInfo();
        PageHelper.startPage(currentPage, 10);
        //info.setColumnId("HBRESGG");
        info.setColumnId(GlobalConstant.RES_NOTICE_TYPE_ID);
        info.setRoleFlow(GlobalConstant.RES_NOTICE_SYS_ID);
        List<InxInfo> infos = this.noticeBiz.findNotice(info);
        model.addAttribute("infos", infos);
        return "inx/zsey/morenotice";
    }

    @RequestMapping("/forgetpasswd")
    public String forgetpasswd() {
        return "inx/zsey/forgetpasswd";
    }

    /**
     * 忘记密码发送邮件
     *
     * @param userEmail,verifyCode,model
     * @param model
     * @return
     */
    @RequestMapping("/sendResetPassEmail")
    @ResponseBody
    public Map<String, String> sendResetPassEmail(String userEmail, String verifyCode, Model model) {
        Map<String, String> respMap = new HashMap<String, String>();
        String sessionValidateCode = StringUtil.defaultString((String) getSessionAttribute("verifyCodeComplex"));
        //验证码输入不能为空，不区分大小写
        if (StringUtil.isBlank(verifyCode) || !sessionValidateCode.equalsIgnoreCase(verifyCode)) {
            respMap.put("errorMessage", SpringUtil.getMessage("validateCode.notEquals"));
            respMap.put("result", GlobalConstant.FLAG_F);
            removeValidateCodeAttribute();
            return respMap;
        }
        removeValidateCodeAttribute();
        if (StringUtil.isNotBlank(userEmail)) {
            userEmail = userEmail.trim();
            SysUser user = userBiz.findByUserEmail(userEmail);
            if (user == null) {
                user = userBiz.findByUserPhone(userEmail);
            }
            if (user == null) {
                user = userBiz.findByIdNo(userEmail);
            }
            if (user != null) {
                userEmail = user.getUserEmail();
//                inxHbresBiz.sendResetPassEmail(userEmail, user.getUserFlow());
                respMap.put("userEmail", userEmail);
                respMap.put("result", GlobalConstant.FLAG_Y);
                return respMap;
            }
        }
        respMap.put("result", GlobalConstant.FLAG_N);
        return respMap;
    }

    @RequestMapping("/resetpasswd")
    public String resetpasswd(String actionId, Model model) {
        SysUser user = userBiz.readSysUser(actionId);
        if (user != null) {
            model.addAttribute("userCode", user.getUserCode());
            model.addAttribute("actionId", actionId);
        }
        return "inx/zsey/resetpasswd";
    }

    @RequestMapping(value = {"/zsey/savePasswd"})
    @ResponseBody
    public String savePasswd(String actionId, String userPasswd, HttpServletRequest request, Model model) {
        SysUser sysUser = userBiz.readSysUser(actionId);
        if (sysUser != null) {
            //更新
            sysUser.setUserPasswd(PasswordHelper.encryptPassword(sysUser.getUserFlow(), userPasswd));
            userBiz.updateUser(sysUser);
            setSessionAttribute(GlobalConstant.CURRENT_USER, sysUser);
            return GlobalConstant.RESET_SUCCESSED;
        } else {
            //给出错误提示
            return GlobalConstant.SAVE_FAIL;
        }
    }

    @RequestMapping("/uploadFile")
    public String uploadFile(String operType, Model model) {
        model.addAttribute("operType", operType);
        return "inx/zsey/uploadFile";
    }

    @RequestMapping(value = "/checkUploadFile", method = {RequestMethod.POST})
    public String checkUploadFile(String operType, MultipartFile uploadFile, Model model) {
        model.addAttribute("operType", operType);
        Map<String, MultipartFile> fileMap = new LinkedHashMap<String, MultipartFile>();
        fileMap.put(operType, uploadFile);
        String[] fileCheckInfo = testFileMap(fileMap);
        model.addAttribute("operType", operType);
        if (fileCheckInfo != null) {
            model.addAttribute("result", GlobalConstant.FLAG_N);
            model.addAttribute("fileErrorMsg", fileCheckInfo);
        } else {
            if (uploadFile != null) {
                String filePath = this.resDoctorBiz.saveImg("", uploadFile, "zseyImages");
                model.addAttribute("result", GlobalConstant.FLAG_Y);
                model.addAttribute("filePath", filePath.equals(GlobalConstant.FLAG_N) ? "" : filePath);
            }
        }
        return "inx/zsey/uploadFile";
    }

    @RequestMapping(value = {"/zsey/finishUserConfirm"}, method = {RequestMethod.GET})
    @ResponseBody
    public String finishUserConfirm(String userFlow) {
        ResDoctor doctor = resDoctorBiz.readDoctor(userFlow);
        SysUser user = userBiz.readSysUser(userFlow);
        ResReg recentReg = resRegBiz.searchRecentYearResReg(user.getUserFlow());
        String regYear = InitConfig.getSysCfg("res_reg_year");
        if (
                user != null &&
                        (
                                doctor == null ||
                                        RegStatusEnum.UnSubmit.getId().equals(doctor.getDoctorStatusId()) ||
                                        !regYear.equals(recentReg.getRegYear()) ||
                                        (
                                                RegStatusEnum.UnPassed.getId().equals(doctor.getDoctorStatusId()) &&
                                                        GlobalConstant.FLAG_Y.equals(doctor.getReeditFlag())
                                        )
                        )
                ) {
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPRE_FAIL;
    }
}
