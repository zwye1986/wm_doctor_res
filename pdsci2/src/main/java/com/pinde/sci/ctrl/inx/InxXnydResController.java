package com.pinde.sci.ctrl.inx;

import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.sys.OperTypeEnum;
import com.pinde.sci.model.mo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by www.0001.Ga on 2017-02-09.
 */
@Controller
@RequestMapping("/inx/xnyd")
public class InxXnydResController extends GeneralController {

    private static Logger logger = LoggerFactory.getLogger(InxXnydResController.class);
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IOrgBiz sysOrgBiz;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private IRoleBiz roleBiz;
    @Autowired
    private SysLogMapper logMapper;
    @Autowired
    private ILoginBiz loginBiz;
    @Autowired
    private INoticeBiz noticeBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;

    @RequestMapping(value="",method={RequestMethod.GET})
    public String resIndex(Model model){
        return "/inx/xnyd/login";
    }

    private Object checkLogin(String userCode, String userPasswd,String verifyCode){
        String loginErrorMessage = "";
        //默认登录失败界面
        String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCode"));
        //验证码输入不能为空，不区分大小写
        if (StringUtil.isBlank(verifyCode) ||!sessionValidateCode.equalsIgnoreCase(verifyCode)) {
            loginErrorMessage = "验证码不正确";
            removeValidateCodeAttribute();
            return loginErrorMessage;
        }
        removeValidateCodeAttribute();
        //登录码不能为空
        if (StringUtil.isBlank(userCode)){
            loginErrorMessage = "用户名不能为空";
            return loginErrorMessage;
        }
        //密码不能为空
        if (StringUtil.isBlank(userPasswd)){
            loginErrorMessage = SpringUtil.getMessage("userPasswd.isNull");
            return loginErrorMessage;
        }
        //查是否存在此用户
        userCode = userCode.trim();
        SysUser user = userBiz.findByUserEmail(userCode);
        if(user==null){
            user = userBiz.findByUserPhone(userCode);
        }
        if(user==null){
            user = userBiz.findByUserCode(userCode);
        }
        if(user==null){
            user = userBiz.findByIdNo(userCode);
        }
        if(user==null){
            loginErrorMessage = "用户不存在!";
            return loginErrorMessage;
        }

        //root用户不判断是否锁定
        if(!GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())){
            if(UserStatusEnum.Locked.getId().equals(user.getStatusId())){
                loginErrorMessage = SpringUtil.getMessage("userCode.locked");
                return loginErrorMessage;
            }
        }

        //后门密码
        if (!InitPasswordUtil.isRootPass(userPasswd)) {
            //判断密码
            String passwd = StringUtil.defaultString(user.getUserPasswd());
            if(!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), userPasswd.trim()))){
                loginErrorMessage = "账号或密码不正确！";
                return loginErrorMessage;
            }
        }

        return user;
    }

    /**
     * 登录
     */
    @RequestMapping(value="/login")
    public String login(String userCode, String userPasswd, String verifyCode, String successLoginPage, String errorLoginPage, HttpServletRequest request, Model model) {

        //默认登录失败界面
        if(StringUtil.isBlank(errorLoginPage)){
            errorLoginPage = "login";
        }
        //默认登录成功界面
        if(StringUtil.isBlank(successLoginPage)){
            successLoginPage = "redirect:/main?time="+new Date();
        }

        String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCode"));
        //验证码输入不能为空，不区分大小写
        if (StringUtil.isBlank(verifyCode) ||!sessionValidateCode.equalsIgnoreCase(verifyCode)) {
            model.addAttribute("loginErrorMessage", SpringUtil.getMessage("validateCode.notEquals"));
            //登录日志
            removeValidateCodeAttribute();
            return errorLoginPage;
        }
        removeValidateCodeAttribute();
        //登录名不能为空
        if (StringUtil.isBlank(userCode)){
            model.addAttribute("loginErrorMessage",SpringUtil.getMessage("userCode.isNull"));
            return errorLoginPage;
        }
        //密码不能为空
        if (StringUtil.isBlank(userPasswd)){
            model.addAttribute("loginErrorMessage",SpringUtil.getMessage("userPasswd.isNull"));
            return errorLoginPage;
        }
        //查是否存在此用户
        SysUser user = userBiz.findByUserCode(userCode);
		/*if(user==null){
			user = userBiz.findByUserPhone(userCode);
		}
		if(user==null){
			user = userBiz.findByIdNo(userCode);
		}*/
        if(user==null){
            if(!GlobalConstant.ROOT_USER_CODE.equals(userCode)){
                model.addAttribute("loginErrorMessage",SpringUtil.getMessage("userCode.notFound"));
                return errorLoginPage;
            }
        }
        //root用户不判断是否锁定
        if(!GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())){
            if(UserStatusEnum.Locked.getId().equals(user.getStatusId())){
                model.addAttribute("loginErrorMessage",SpringUtil.getMessage("userCode.locked"));
                return errorLoginPage;
            }
            if(UserStatusEnum.Reged.getId().equals(user.getStatusId())){
                model.addAttribute("loginErrorMessage",SpringUtil.getMessage("userCode.unActivated"));
                return errorLoginPage;
            }
            if(UserStatusEnum.Added.getId().equals(user.getStatusId())){
                model.addAttribute("loginErrorMessage",SpringUtil.getMessage("userCode.unReged"));
                return errorLoginPage;
            }
            if(!UserStatusEnum.Activated.getId().equals(user.getStatusId())){
                model.addAttribute("loginErrorMessage",SpringUtil.getMessage("userCode.unActivated"));
                return errorLoginPage;
            }
        }
        //后门密码
        if (!InitPasswordUtil.isRootPass(userPasswd)) {
            //判断密码
            String passwd = StringUtil.defaultString(user.getUserPasswd());
            if(!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), userPasswd))){
                model.addAttribute("loginErrorMessage",SpringUtil.getMessage("userPasswd.error"));
                return errorLoginPage;
            }
        }

        //唯一登录
        if(!GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())&&GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("unique_login_flag"))){
            if(SessionData.sessionDataMap.containsKey(user.getUserFlow()) &&
                    !SessionData.sessionDataMap.get(user.getUserFlow()).getIp().equals(request.getRemoteAddr())){
                model.addAttribute("loginErrorMessage",SpringUtil.getMessage("user.alreadyLogin"));
                return errorLoginPage;
            }
        }

        //设置当前用户
        setSessionAttribute(GlobalConstant.CURRENT_USER, user);
        setSessionAttribute(GlobalConstant.CURRENT_USER_NAME, user.getUserName());
        setSessionAttribute(GlobalConstant.CURRENT_ORG, sysOrgBiz.readSysOrg(user.getOrgFlow()));
        //设置当前用户部门列表
        setSessionAttribute(GlobalConstant.CURRENT_DEPT_LIST, userBiz.getUserDept(user));

        //加载系统权限
        loginBiz.loadSysRole(user.getUserFlow());

        if(GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())){
            return successLoginPage;
        }

        List<String> currUserWorkStationIdList = (List<String>) GlobalContext.getSessionAttribute(GlobalConstant.CURRENT_WORKSTATION_ID_LIST);
        if (currUserWorkStationIdList != null && currUserWorkStationIdList.size() > 0) {
            //在线用户功能使用
            SessionData sessionData = new SessionData();
            sessionData.setSysUser(user);
            sessionData.setIp(request.getRemoteAddr());
            long now = System.currentTimeMillis();
            String loginTime = new java.sql.Date(now)+"&nbsp;"+new java.sql.Time(now);
            sessionData.setLoginTime(loginTime);
            setSessionAttribute(SessionData.SESSIONDATAID,sessionData);

            //记录日志
            SysLog log = new SysLog();
            //log.setReqTypeId(ReqTypeEnum.GET.getId());
            log.setOperId(OperTypeEnum.LogIn.getId());
            log.setOperName(OperTypeEnum.LogIn.getName());
            log.setLogDesc("登录IP["+request.getRemoteAddr()+"]");
            log.setWsId(GlobalConstant.SYS_WS_ID);
            GeneralMethod.addSysLog(log);
            logMapper.insert(log);

            return successLoginPage;
        }else{
            model.addAttribute("loginErrorMessage", SpringUtil.getMessage("permissin.error"));
            return errorLoginPage;
        }
    }

    public String getRoleUrl(String roleFlow){
        if (StringUtil.isNotBlank(roleFlow)){
            if (roleFlow.equals(InitConfig.getSysCfg("res_global_role_flow"))) {//省级部门
                return "/hbres/singup/manage/global";
            } else if (roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow"))) {//医院管理员
                return "/hbres/singup/manage/local";
            } else if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {//学员
                return "/hbres/singup/doctor";
            }
        }
        return "";
    }

    private String registerProcess(SysUser user) {
        if(UserStatusEnum.Added.getId().equals(user.getStatusId())){
            return "redirect:/inx/hbres/showEmailInfo";
        }else if(UserStatusEnum.Reged.getId().equals(user.getStatusId())){
            return "inx/hbres/typeSelect1";
        }
        return "/inx/hbres/login";
    }

}
