package com.pinde.sci.ctrl.inx;

import com.pinde.core.common.enums.sys.OperTypeEnum;
import com.pinde.core.model.ResDoctorRecruit;
import com.pinde.core.model.SysLog;
import com.pinde.core.model.SysUser;
import com.pinde.core.model.SysUserRole;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IInxBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorRecruitBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.SessionData;
import com.pinde.sci.dao.base.SysLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/inx/pubedu")
public class PubeduController extends GeneralController {
    @Autowired
    private IInxBiz inxBiz;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private IRoleBiz roleBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IResDoctorRecruitBiz resDoctorRecruitBiz;
    @Autowired
    private SysLogMapper logMapper;
    @RequestMapping("")
    public String login(){
        return "inx/pubedu/login";
    }
    /**
     * 登录
     */
    @RequestMapping("/login")
    public String login(String userCode, String userPasswd, String verifyCode , Model model , HttpServletRequest request){
        String loginErrorMessage = "";
        String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCode"));
        if (StringUtil.isBlank(verifyCode) ||!sessionValidateCode.equalsIgnoreCase(verifyCode)) {
            loginErrorMessage = "验证码不正确";
            model.addAttribute("loginErrorMessage" , loginErrorMessage);
            return "inx/pubedu/login";
        }
        try{
            SysUser user = inxBiz.login(userCode, userPasswd);
            //设置当前用户
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, user);
            if (com.pinde.core.common.GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())) {
                return "redirect:/main?time="+new Date();
            }
            //在线用户功能使用
            SessionData sessionData = new SessionData();
            sessionData.setSysUser(user);
            sessionData.setIp(request.getRemoteAddr());
            long now = System.currentTimeMillis();
            String loginTime = new java.sql.Date(now)+"&nbsp;"+new java.sql.Time(now);
            sessionData.setLoginTime(loginTime);
            setSessionAttribute(SessionData.SESSIONDATAID,sessionData);

            SysUserRole userRole = new SysUserRole();
            userRole.setUserFlow(user.getUserFlow());
            userRole.setWsId(com.pinde.core.common.GlobalConstant.RES_WS_ID);
            List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
            if(null != userRoleList) {
                for (SysUserRole sysUserRole : userRoleList) {
                    if (sysUserRole.getRoleFlow().equals(InitConfig.getSysCfg("res_pubedu_admin_role_flow"))) {
                        //记录日志
                        SysLog log = new SysLog();
                        log.setOperId(OperTypeEnum.LogIn.getId());
                        log.setOperName(OperTypeEnum.LogIn.getName());
                        log.setLogDesc("登录IP[" + request.getRemoteAddr() + "]");
                        log.setWsId(com.pinde.core.common.GlobalConstant.SYS_WS_ID);
                        GeneralMethod.addSysLog(log);
                        logMapper.insert(log);
                        return "redirect:/pubedu/hospital/home";
                    }
                }
                ResDoctorRecruit resDoctorRecruit = new ResDoctorRecruit();
                resDoctorRecruit.setDoctorFlow(user.getUserFlow());
                resDoctorRecruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
                //查询res_doctor_recruit表中按创建时间为最新的且审核通过的数据
                List<com.pinde.core.model.ResDoctorRecruit> resDoctorRecruitList = resDoctorRecruitBiz.searchDoctorRecruits(resDoctorRecruit);
                if (!resDoctorRecruitList.isEmpty()) {
                    //取第一条数据
                    resDoctorRecruit = resDoctorRecruitList.get(0);
                    if (user.getUserFlow().equals(resDoctorRecruit.getDoctorFlow())) {
                        for (SysUserRole sysUserRole : userRoleList) {
                            if (sysUserRole.getRoleFlow().equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {
                                //记录日志
                                SysLog log = new SysLog();
                                log.setOperId(OperTypeEnum.LogIn.getId());
                                log.setOperName(OperTypeEnum.LogIn.getName());
                                log.setLogDesc("登录IP[" + request.getRemoteAddr() + "]");
                                log.setWsId(com.pinde.core.common.GlobalConstant.SYS_WS_ID);
                                GeneralMethod.addSysLog(log);
                                logMapper.insert(log);
                                return "redirect:/pubedu/doctor/home";
                            }
                        }
                    }
                }
            }
            loginErrorMessage = "未赋权";
        } catch (RuntimeException e) {
            logger.error("", e);
        }
        model.addAttribute("loginErrorMessage" , loginErrorMessage);
        return "inx/pubedu/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/inx/pubedu";
    }

    private static final Logger logger = LoggerFactory.getLogger(PubeduController.class);



}
