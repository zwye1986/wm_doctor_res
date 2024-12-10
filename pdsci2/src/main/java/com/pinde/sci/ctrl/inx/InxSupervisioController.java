package com.pinde.sci.ctrl.inx;

import com.alibaba.fastjson.JSON;
import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.core.common.enums.sys.OperTypeEnum;
import com.pinde.core.common.enums.sys.ReqTypeEnum;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.jsres.ISysSupervisioUserBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.RSAUtils;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.model.mo.InxInfo;
import com.pinde.sci.model.mo.SysLog;
import com.pinde.sci.model.mo.SysSupervisioUser;
import com.pinde.core.model.SysUser;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.Map;

/**
 * @author tiger
 */

@Controller
@RequestMapping("/inx/supervisio")
public class InxSupervisioController extends GeneralController {
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private IRoleBiz roleBiz;
    @Autowired
    private SysLogMapper logMapper;
    @Autowired
    private INoticeBiz noticeBiz;
    @Autowired
    private ISysSupervisioUserBiz supervisioUserBiz;

    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String index(Model model) {
        // 获取公钥系数和公钥指数
        RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
        if (null != publicKey) {
            //公钥-系数(n)
            model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
            //公钥-指数(e1)
            model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
        }
        return "inx/supervisio/login";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, Model model) {
        String wsId = (String) GlobalContext.getSession().getAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
        request.getSession().invalidate();
        GlobalContext.getSession().setAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID, wsId);
        // 获取公钥系数和公钥指数
        RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
        if (null != publicKey) {
            //公钥-系数(n)
            model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
            //公钥-指数(e1)
            model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
        }
        return "inx/supervisio/login";
    }

    @ResponseBody
    @RequestMapping("/eidtPassword")
    public String eidtPassword(String userCode, String oldUserPasswd, String ideentityCheck, String userPasswd) {
        return supervisioUserBiz.eidtPassword(userCode, oldUserPasswd, ideentityCheck, userPasswd);
    }

    @RequestMapping(value = "/modifyToLogin")
    public String modifyToLogin(Model model) {
        return "inx/supervisio/modifySuccess";
    }

    @RequestMapping(value = {"/checkWeakPassword"}, method = {RequestMethod.POST})
    @ResponseBody
    public String checkWeakPassword(String password) {
        if (StringUtil.isNotBlank(password)) {
            // 解密
            if (StringUtil.isNotBlank(InitConfig.weekPasswordMap.get(password.trim()))) {
                return com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
        }
        return "";
    }

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

        SysSupervisioUser sysSupervisioUser = supervisioUserBiz.findByUserCode(userCode);
        if (sysSupervisioUser == null) {
            loginErrorMessage = "用户不存在!";
            return loginErrorMessage;
        }
        if (sysSupervisioUser != null) {
            return getUserInfo2(sysSupervisioUser, loginErrorMessage, userPasswd);
        }
        return loginErrorMessage;

    }

    public Object getUserInfo2(SysSupervisioUser user, String loginErrorMessage, String userPasswd) {
        //root用户不判断是否锁定
        if (!com.pinde.core.common.GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())) {
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
    public String login(String data, String errorLoginPage, Model model, HttpServletRequest request) throws Exception {
        // 解密
        data = RSAUtils.decryptStringByJs(data);
        Map<String, String> paramMap = (Map<String, String>) JSON.parse(data);
        String userCode = paramMap.get("userCode");
        String userPasswd = paramMap.get("userPasswd");
        String verifyCode = paramMap.get("verifyCode");
        // 获取公钥系数和公钥指数
        RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();

        String loginErrorMessage = "";
        //默认登录失败界面
        if (StringUtil.isBlank(errorLoginPage)) {
            errorLoginPage = "inx/supervisio/login";
        }
        Object obj = checkLogin(userCode, userPasswd, verifyCode);
        if (obj.equals("用户不存在!")) {
            loginErrorMessage = (String) obj;
            model.addAttribute("userCode", userCode);
            model.addAttribute("loginErrorMessage", loginErrorMessage);
            return errorLoginPage;
        }

        //后门密码
        if (!InitPasswordUtil.isRootPass(userPasswd)) {
            if (obj.equals("账号或密码不正确！")) {
                loginErrorMessage = (String) obj;
                model.addAttribute("userCode", userCode);
                model.addAttribute("loginErrorMessage", loginErrorMessage);
                return errorLoginPage;
            }
            //判断密码
            if (StringUtil.isNotBlank(InitConfig.weekPasswordMap.get(userPasswd))) {
                model.addAttribute("userCode", userCode);
                errorLoginPage = "inx/supervisio/setStrongPasswd";
                if (null != publicKey) {
                    //公钥-系数(n)
                    model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                    //公钥-指数(e1)
                    model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
                }
                return errorLoginPage;
            }
        }
        if (obj instanceof String) {
            loginErrorMessage = (String) obj;
            model.addAttribute("loginErrorMessage", loginErrorMessage);
            InxInfo info = new InxInfo();
            PageHelper.startPage(1, 6);
            List<InxInfo> infos = this.noticeBiz.findNoticeWithBLOBs(info);
            model.addAttribute("infos", infos);
            if (null != publicKey) {
                //公钥-系数(n)
                model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                //公钥-指数(e1)
                model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
            }
            return errorLoginPage;
        }
        SysUser user = null;
        SysSupervisioUser supervisioUser = null;

        String clientIp = ClientIPUtils.getClientIp(request);
        if (obj instanceof SysSupervisioUser) {
            supervisioUser = (SysSupervisioUser) obj;
            //设置当前用户
            user = new SysUser();
            user.setUserFlow(supervisioUser.getUserFlow());
            user.setUserName(supervisioUser.getUserName());
            user.setUserCode(supervisioUser.getUserCode());
            if(StringUtil.isNotBlank(supervisioUser.getOrgFlow())){
                user.setOrgFlow(supervisioUser.getOrgFlow());
                user.setOrgName(supervisioUser.getOrgName());
            }
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, user);
        }
        //记录日志
        SysLog log = new SysLog();
        log.setOperId(OperTypeEnum.LogIn.getId());
        log.setOperName(OperTypeEnum.LogIn.getName());
        log.setLogDesc("登录IP[" + clientIp + "]");
        log.setWsId(com.pinde.core.common.GlobalConstant.SYS_WS_ID);
        log.setLogFlow(PkUtil.getUUID());
        log.setUserFlow(supervisioUser.getUserFlow());
        log.setUserCode(supervisioUser.getUserCode());
        log.setUserName(supervisioUser.getUserName());
        if (StringUtil.isBlank(log.getReqTypeId())) {
            log.setReqTypeId(ReqTypeEnum.GET.getId());
        }
        log.setReqTypeName(ReqTypeEnum.getNameById(log.getReqTypeId()));
        log.setLogTime(DateUtil.getCurrDateTime());
        if (StringUtil.isBlank(log.getWsId())) {
            log.setWsId(GlobalContext.getCurrentWsId());
        }
        log.setWsName(InitConfig.getWorkStationName(log.getWsId()));
        log.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
        log.setCreateTime(DateUtil.getCurrDateTime());
        log.setCreateUserFlow(supervisioUser.getUserFlow());
        log.setModifyTime(DateUtil.getCurrDateTime());
        log.setModifyUserFlow(supervisioUser.getUserFlow());
        logMapper.insert(log);

        if (null != supervisioUser) {
            if (UserStatusEnum.Activated.getId().equals(supervisioUser.getStatusId())) {
                if ("expert".equals(supervisioUser.getUserLevelId())) {
                    //专业专家
                    setSessionAttribute("UserLevel","专业专家");
                    return "redirect:/jsres/supervisio/index/expert";
                }

            }
        }
        loginErrorMessage = "角色未赋权!";
        model.addAttribute("loginErrorMessage", loginErrorMessage);
        return errorLoginPage;

    }
}
