
package com.pinde.sci.ctrl.inx;

import com.alibaba.fastjson.JSON;
import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.jsres.JsresSendMessageEnum;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.core.common.enums.sys.OperTypeEnum;
import com.pinde.core.model.InxInfo;
import com.pinde.core.model.SysOrg;
import com.pinde.core.model.SysRole;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ClientIPUtils;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IInxBiz;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.jsres.IJsResBaseBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorBiz;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.jsres.IResMessageBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sch.ISchDeptExternalRelBiz;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.DESUtil;
import com.pinde.sci.common.util.RSAUtils;
import com.pinde.sci.common.util.SMSUtil;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.model.jsres.LoginVo;
import com.pinde.sci.model.jsres.ResultVo;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.util.jsres.ResultUtil;
import com.wf.captcha.utils.CaptchaUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author tiger
 */
@Controller
@RequestMapping("/inx/jsres")
public class InxJsResController extends GeneralController {
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
    private IInxBiz inxBiz;
    @Autowired
    private ICfgBiz cfgBiz;
    @Autowired
    private ISchDeptExternalRelBiz externalDeptBiz;
    @Autowired
    private IJsResDoctorBiz jsResDoctorBiz;
    @Autowired
    private IJsResPowerCfgBiz jsResPowerCfgBiz;
    @Autowired
    private IJsResBaseBiz baseBiz;
    @Autowired
    private IResMessageBiz messageBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;

    private static String regex = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_.!@#$%^&*`~()-+=]+$)(?![a-z0-9]+$)(?![a-z\\W_.!@#$%^&*`~()-+=]+$)(?![0-9\\W_.!@#$%^&*`~()-+=]+$)[a-zA-Z0-9\\W_.!@#$%^&*`~()-+=]{8,20}$";

    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String resIndex(Model model, HttpServletRequest request) {
        InxInfo info = new InxInfo();
        PageHelper.startPage(1, 4);
//		System.out.println("Read InxInfo...............");
        List<InxInfo> infos = this.noticeBiz.findNoticeWithBLOBs(info);
        model.addAttribute("infos", infos);

        PageHelper.startPage(1, 4);
        List<ResMessage> messages = this.messageBiz.searchMessageByOrg(null, null);
        model.addAttribute("messages", messages);
//		SysLogExample example = new SysLogExample();
//		example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOperIdEqualTo(OperTypeEnum.LogIn.getId());
//		System.out.println("Read SysLog...............");
//		int count = logMapper.countByExample(example);
//		model.addAttribute("count", count);
//		System.out.println("SysLog count..............." + count);
        String url = "/inx/jsres";
        String title = "江苏省住院医师规范化培训管理平台";
        request.getSession().setAttribute("loginUrl", url);
        request.getSession().setAttribute("sysTitle", title);
        // 获取公钥系数和公钥指数
        KeyPair defaultKeyPair = RSAUtils.getDefaultKeyPair();
        setSessionAttribute("defaultKeyPair",defaultKeyPair);
        RSAPublicKey publicKey = (RSAPublicKey)defaultKeyPair.getPublic();
        if (null != publicKey) {
            //公钥-系数(n)
            model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
            //公钥-指数(e1)
            model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
        }
        return "/inx/jsres/login";
    }

    @RequestMapping(value = "/{url}", method = {RequestMethod.GET})
    public String resIndex(Model model, @PathVariable String url) {
        InxInfo info = new InxInfo();
        PageHelper.startPage(1, 4);
        List<InxInfo> infos = this.noticeBiz.findNoticeWithBLOBs(info);
        model.addAttribute("infos", infos);
        PageHelper.startPage(1, 4);
        List<ResMessage> messages = this.messageBiz.searchMessageByOrg(null, null);
        model.addAttribute("messages", messages);
//		SysLogExample example = new SysLogExample();
//		example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOperIdEqualTo(OperTypeEnum.LogIn.getId());
//		int count = logMapper.countByExample(example);
//		model.addAttribute("count", count);
        // 获取公钥系数和公钥指数
        RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
        if (null != publicKey) {
            //公钥-系数(n)
            model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
            //公钥-指数(e1)
            model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
        }
        if (StringUtil.isNotBlank(url)) {
            url = "/inx/jsres/" + url + "/login";
        } else {
            url = "/inx/jsres/login";
        }
        return url;
    }

    @RequestMapping(value = "/noticeView")
    public String message(Model model, String infoFlow) {

        model.addAttribute("msg", noticeBiz.findNoticByFlow(infoFlow));
        return "/inx/jsres/message";
    }

    @RequestMapping(value = "/messageView")
    public String messageView(Model model, String messageFlow) {
        ResMessage message = this.messageBiz.findMessageByFlow(messageFlow);
        model.addAttribute("msg", message);
        return "/jsres/message/message";
    }

    @RequestMapping(value = "/messageViewByOrg")
    public String messageViewByOrg(Model model, String orgFlow,String sessionNumber,Integer currentPage) {
        PageHelper.startPage(currentPage, 10);
        List<ResMessage> list = this.messageBiz.findMessageByOrgFlow(orgFlow,sessionNumber);
        if (null != list && list.size()>0 && list.size()==1){
            model.addAttribute("msg", list.get(0));
            return "/jsres/message/message";
        }
        model.addAttribute("messages", list);
        return "/jsres/message/login_more";
    }

    @RequestMapping(value = "/allNotice", method = {RequestMethod.GET})
    public String allNotice(Integer currentPage, Model model, String flag) {
        InxInfo info = new InxInfo();
        PageHelper.startPage(currentPage, 10);
        List<InxInfo> infos = this.noticeBiz.findNotice(info);
        model.addAttribute("infos", infos);
        if (StringUtil.isBlank(flag)) {
            return "/inx/jsres/login_more";
        } else {
            return "/inx/jsres/msg";
        }
    }

    @RequestMapping(value = "/allOrgMessage", method = {RequestMethod.GET,RequestMethod.POST})
    public String allOrgMessage(Model model,String sessionNumber) {
        model.addAttribute("sessionNumber", sessionNumber);
        return "/jsres/message/allOrgMessage";
    }


    @RequestMapping(value = "/allOrgMessageList", method = {RequestMethod.GET,RequestMethod.POST})
    public String allOrgMessageList(Model model,String sessionNumber) throws ParseException {
        List<Map<String, Object>> list = messageBiz.findMessageOrgAndCount(sessionNumber);
        HashMap<String, String> messageMap = new HashMap<>();
        for (Map<String, Object> map : list) {
            messageMap.put(map.get("orgFlow").toString(),map.get("num").toString());
        }
        model.addAttribute("messageMap",messageMap);


        String year = DateUtil.getYear();
        if ( StringUtil.isNotBlank(sessionNumber) &&  year.equals(sessionNumber)){
            String date2 = com.pinde.core.util.DateUtil.getAppointDate2(new Date(), -7);
            List<Map<String, Object>> mapList = messageBiz.findMessageOrgAndTime(date2);
            HashMap<String, String> newMap = new HashMap<>();
            for (Map<String, Object> map : mapList) {
                newMap.put(map.get("orgFlow").toString(),map.get("num").toString());
            }
            model.addAttribute("newMap", newMap);
        }

        SysOrg sysorg = new SysOrg();
        sysorg.setOrgLevelId("CountryOrg");
        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        List<SysOrg> orgs = orgBiz.searchOrgList(sysorg);
        model.addAttribute("orgs", orgs);
        model.addAttribute("sessionNumber", sessionNumber);
        return "/jsres/message/allOrgMessageList";
    }


    @RequestMapping(value = "/allMessage", method = {RequestMethod.GET})
    public String allMessage(Integer currentPage, Model model, String flag) {
        ResMessage message = new ResMessage();
        PageHelper.startPage(currentPage, 10);
        List<ResMessage> messages = this.messageBiz.findMessage(message);
        model.addAttribute("messages", messages);
        if (StringUtil.isBlank(flag)) {
            return "/jsres/message/login_more";
        } else {
            return "/jsres/message/msg";
        }
    }

    /**
     * 跳转至注册页面
     *
     * @return
     */
    @RequestMapping(value = "/register")
    public String register(Model model) {
        // 获取公钥系数和公钥指数
        KeyPair defaultKeyPair = RSAUtils.getDefaultKeyPair();
        setSessionAttribute("defaultKeyPairRegister",defaultKeyPair);
        RSAPublicKey publicKey = (RSAPublicKey)defaultKeyPair.getPublic();
        if (null != publicKey) {
            //公钥-系数(n)
            model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
            //公钥-指数(e1)
            model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
        }
        return "inx/jsres/register";
    }

    /**
     * @Department：研发部
     * @Description 忘记密码
     * @Author Zjie
     * @Date 0029, 2020年10月29日
     */
    @RequestMapping(value = "/forgetPassword")
    public String forgetPassword(Model model) {
        RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
        if (null != publicKey) {
            //公钥-系数(n)
            model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
            //公钥-指数(e1)
            model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
        }
        return "inx/jsres/forgetPassword";
    }

    /**
     * 验证邮箱
     */
    @RequestMapping(value = "/checkEmail")
    @ResponseBody
    public String checkEmail(String userEmail) {
        userEmail = userEmail.trim();
        SysUser user = userBiz.findByUserEmail(userEmail);

        if (user != null) {
            return com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE;
        }
        user = userBiz.findByUserCode(userEmail);
        if (user != null) {
            return com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE;
        }
        return "";
    }

    /**
     * 验证身份证
     */
    @RequestMapping(value = "/checkIdNo")
    @ResponseBody
    public String checkIdNo(String idNo) {
        if (StringUtil.isNotBlank(idNo)) {
            idNo = idNo.trim();
            SysUser user = userBiz.findByIdNo(idNo);
            if (user != null) {
                SysRole role = new SysRole();
                role.setWsId("osca");
                role.setRoleName("学员");
                boolean roleIsExist = userRoleBiz.userExistRole(user, role);
                if (roleIsExist && idNo.equals(user.getUserCode())) {
                    return "证件号码已存在，请用证件号进行登录";
                } else {
                    return com.pinde.core.common.GlobalConstant.USER_ID_NO_REPETE;
                }
            }
        }
        return "";
    }

    /**
     * 验证手机号
     */
    @RequestMapping(value = "/checkUserPhone")
    @ResponseBody
    public String checkUserPhone(String userPhone) {
        if (StringUtil.isNotBlank(userPhone)) {
            userPhone = userPhone.trim();
            SysUser user = userBiz.findByUserPhone(userPhone);
            if (user != null) {
                return com.pinde.core.common.GlobalConstant.USER_PHONE_REPETE;
            }
        }
        return "";
    }

    /**
     * 验证是否弱密码
     */
    @RequestMapping(value = {"/checkWeakPassword"}, method = {RequestMethod.POST})
    @ResponseBody
    public String checkWeakPassword(String password) {
        if (StringUtil.isNotBlank(password)) {
            // 解密
            password = RSAUtils.decryptStringByJs(password);
            if (StringUtil.isNotBlank(InitConfig.weekPasswordMap.get(password.trim()))) {
                return com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
        }
        return "";
    }

    /**
     * 注册
     *
     * @return
     */
    @RequestMapping("/saveRegister")
    public String saveRegister(SysUser registerUser, String verifyCode, Model model) {
        //注册信息校验
        String sessionValidateCode = StringUtil.defaultString((String) getSessionAttribute("verifyCode"));

        String errorMsg = "";
        if (StringUtil.isBlank(verifyCode) || StringUtil.isBlank(sessionValidateCode) || !sessionValidateCode.equalsIgnoreCase(verifyCode)) {
            errorMsg = SpringUtil.getMessage("validateCode.notEquals");
            model.addAttribute("errorMsg", errorMsg);

            RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
            if (null != publicKey) {
                //公钥-系数(n)
                model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                //公钥-指数(e1)
                model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
            }
            return "inx/jsres/register";
        }
        //是否已注册
        String userEmail = registerUser.getUserEmail().trim();
        SysUser user = userBiz.findByUserEmail(userEmail);
        if (user != null) {
            model.addAttribute("errorMsg", com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE);
            RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
            if (null != publicKey) {
                //公钥-系数(n)
                model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                //公钥-指数(e1)
                model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
            }
            return "inx/jsres/register";
        }
        user = userBiz.findByUserCode(userEmail);
        if (user != null) {
            model.addAttribute("errorMsg", com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE);
            RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
            if (null != publicKey) {
                //公钥-系数(n)
                model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                //公钥-指数(e1)
                model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
            }
            return "inx/jsres/register";
        }
        //判断用户身份证号是否重复
        user = userBiz.findByIdNo(registerUser.getIdNo());
        if (user != null) {
            model.addAttribute("errorMsg", com.pinde.core.common.GlobalConstant.USER_ID_NO_REPETE);
            RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
            if (null != publicKey) {
                //公钥-系数(n)
                model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                //公钥-指数(e1)
                model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
            }
            return "inx/jsres/register";
        }
        this.inxBiz.saveRegistUser(registerUser);
        model.addAttribute("activationCode", registerUser.getUserFlow());
        return "redirect:/inx/jsres/sendEmail";
    }

    /**
     * 发送邮件
     *
     * @param activationCode
     * @param model
     * @return
     */
    @RequestMapping("/sendEmail")
    public String sendEmail(String activationCode, Model model) {
        SysUser user = userBiz.readSysUser(activationCode);

        //发送email
//		inxBiz.sendEmail(user.getUserFlow(), user.getUserEmail());

        model.addAttribute("activationCode", user.getUserFlow());
        model.addAttribute("userEmail", user.getUserEmail());
        return "inx/jsres/sendEmail";
    }

    /**
     * 重新发送
     *
     * @param userEmail
     * @return
     */
    @RequestMapping("/reSendEmail")
    @ResponseBody
    public String reSendEmail(String userEmail) {
        if (StringUtil.isNotBlank(userEmail)) {
            SysUser findUser = userBiz.findByUserEmail(userEmail);
            if (findUser != null) {
                int result = inxBiz.sendEmail(findUser.getUserFlow(), userEmail);
                if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                    return com.pinde.core.common.GlobalConstant.FLAG_Y;
                }
            }
        }
        return com.pinde.core.common.GlobalConstant.FLAG_N;
    }

    /**
     * 邮箱连接激活用户
     *
     * @return
     */
    @RequestMapping("/activateuser")
    public String activateUser(String activationCode, Model model) {
        this.inxBiz.activateUser(activationCode);
//		SysUser user = this.userBiz.readSysUser(activationCode);
//		SysUserRole userRole = new SysUserRole();
//		userRole.setUserFlow(user.getUserFlow());
//		userRole.setWsId(com.pinde.core.common.GlobalConstant.RES_WS_ID);
//		List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
//		if(!userRoleList.isEmpty()){
//			userRole = userRoleList.get(0);
//		}
//		SysRole role = roleBiz.read(userRole.getRoleFlow()); 
//		if(role!=null){
//			setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, user);
//			return "redirect:"+getRoleUrl(role.getRoleFlow());
//		}
        return "redirect:/inx/jsres/activatesucc";
    }

    @RequestMapping("/activatesucc")
    public String activatesucc(HttpServletRequest request) {
        return "inx/jsres/activatesucc";
    }

    private Object checkLogin2(String userCode, String userPasswd) {
        String loginErrorMessage = "";
        //默认登录失败界面
        String sessionValidateCode = StringUtil.defaultString((String) getSessionAttribute("verifyCode"));

        String rootVerifyCode = InitConfig.getSysCfg("rootVerifyCode");

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
        SysUser user = userBiz.findByUserCode(userCode);
        SysUser userNew = userBiz.loginByUserPhone(userCode);
		/*if(user==null){
			user = userBiz.findByUserPhone(userCode);
		}*/
//		if(user==null){
//			user = userBiz.findByUserCode(userCode);
//		}
//		if(user==null){
//			user = userBiz.findByIdNo(userCode);
//		}
        if (user == null && userNew == null) {
            loginErrorMessage = "账号或密码不正确!";
            return loginErrorMessage;
        }
        if (user != null) {
            //后门密码
            if (!InitPasswordUtil.isRootPass(userPasswd)) {
                //判断密码
                String passwd = StringUtil.defaultString(user.getUserPasswd());
                String lastLoginErrorTime = user.getLastLoginErrorTime();
                String currDateTime2 = com.pinde.core.util.DateUtil.getCurrDateTime2();
                String loginErrorCountOld = user.getLoginErrorCount();
                if (!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), userPasswd.trim()))) {
                    if (StringUtil.isNotBlank(loginErrorCountOld) && "10".equals(loginErrorCountOld)) {
                        if (com.pinde.core.util.DateUtil.signMinutesBetweenTowDate(lastLoginErrorTime, currDateTime2) > 10) {
                            user.setLastLoginErrorTime(currDateTime2);
                            user.setLoginErrorCount("1");
                            user.setStatusId(UserStatusEnum.Activated.getId());
                            user.setStatusDesc(UserStatusEnum.Activated.getName());
                            userBiz.updateUser(user);
                        }
                    } else {
                        if (StringUtil.isBlank(lastLoginErrorTime) || com.pinde.core.util.DateUtil.signMinutesBetweenTowDate(lastLoginErrorTime, currDateTime2) > 60) {
                            user.setLoginErrorCount("");
                        }
                        user.setLastLoginErrorTime(com.pinde.core.util.DateUtil.getCurrDateTime2());
                        int loginErrorCount = StringUtil.isBlank(loginErrorCountOld) ? 0 : Integer.valueOf(loginErrorCountOld);
                        String loginErrorCountNew = loginErrorCount + 1 + "";
                        user.setLoginErrorCount(loginErrorCountNew);
                        if ("10".equals(loginErrorCountNew)) {
                            user.setStatusId(UserStatusEnum.SysLocked.getId());
                            user.setStatusDesc(UserStatusEnum.SysLocked.getName());
                        }
                        userBiz.updateUser(user);
                    }
                    if (UserStatusEnum.Activated.getId().equals(user.getStatusId())) {
                        loginErrorMessage = "账号或密码不正确！";
                        return loginErrorMessage;
                    }
                } else {
                    if (StringUtil.isNotBlank(loginErrorCountOld) && "10".equals(loginErrorCountOld)) {
                        if (com.pinde.core.util.DateUtil.signMinutesBetweenTowDate(lastLoginErrorTime, currDateTime2) > 10) {
                            user.setLastLoginErrorTime("");
                            user.setLoginErrorCount("");
                            user.setStatusId(UserStatusEnum.Activated.getId());
                            user.setStatusDesc(UserStatusEnum.Activated.getName());
                            userBiz.updateUser(user);
                        }
                    } else {
                        user.setLastLoginErrorTime("");
                        user.setLoginErrorCount("");
                        userBiz.updateUser(user);
                    }
                }
            }
            if (UserStatusEnum.Added.getId().equals(user.getStatusId())) {
                loginErrorMessage = "用户未激活";
                return loginErrorMessage;
            }
            if (UserStatusEnum.Locked.getId().equals(user.getStatusId())) {
                loginErrorMessage = "用户已停用";
                return loginErrorMessage;
            }
            if (UserStatusEnum.SysLocked.getId().equals(user.getStatusId())) {
                loginErrorMessage = "用户已锁定";
                return loginErrorMessage;
            }
            if (UserStatusEnum.Reged.getId().equals(user.getStatusId())) {
                loginErrorMessage = "用户待审核";
                return loginErrorMessage;
            }
        }
        if (user == null && userNew != null) {
            //后门密码
            if (!InitPasswordUtil.isRootPass(userPasswd)) {
                //判断密码
                String passwd = StringUtil.defaultString(userNew.getUserPasswd());
                String lastLoginErrorTime = userNew.getLastLoginErrorTime();
                String currDateTime2 = com.pinde.core.util.DateUtil.getCurrDateTime2();
                String loginErrorCountOld = userNew.getLoginErrorCount();
                if (!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(userNew.getUserFlow(), userPasswd.trim()))) {
                    if (StringUtil.isNotBlank(loginErrorCountOld) && "10".equals(loginErrorCountOld)) {
                        if (com.pinde.core.util.DateUtil.signMinutesBetweenTowDate(lastLoginErrorTime, currDateTime2) > 10) {
                            userNew.setLastLoginErrorTime(currDateTime2);
                            userNew.setLoginErrorCount("1");
                            userNew.setStatusId(UserStatusEnum.Activated.getId());
                            userNew.setStatusDesc(UserStatusEnum.Activated.getName());
                            userBiz.updateUser(userNew);
                        }
                    } else {
                        if (StringUtil.isBlank(lastLoginErrorTime) || com.pinde.core.util.DateUtil.signMinutesBetweenTowDate(lastLoginErrorTime, currDateTime2) > 60) {
                            userNew.setLoginErrorCount("");
                        }
                        userNew.setLastLoginErrorTime(com.pinde.core.util.DateUtil.getCurrDateTime2());
                        int loginErrorCount = StringUtil.isBlank(loginErrorCountOld) ? 0 : Integer.valueOf(loginErrorCountOld);
                        String loginErrorCountNew = loginErrorCount + 1 + "";
                        userNew.setLoginErrorCount(loginErrorCountNew);
                        if ("10".equals(loginErrorCountNew)) {
                            userNew.setStatusId(UserStatusEnum.SysLocked.getId());
                            userNew.setStatusDesc(UserStatusEnum.SysLocked.getName());
                        }
                        userBiz.updateUser(userNew);
                    }
                    if (UserStatusEnum.Activated.getId().equals(userNew.getStatusId())) {
                        loginErrorMessage = "账号或密码不正确！";
                        return loginErrorMessage;
                    }
                } else {
                    if (StringUtil.isNotBlank(loginErrorCountOld) && "10".equals(loginErrorCountOld)) {
                        if (com.pinde.core.util.DateUtil.signMinutesBetweenTowDate(lastLoginErrorTime, currDateTime2) > 10) {
                            userNew.setLastLoginErrorTime("");
                            userNew.setLoginErrorCount("");
                            userNew.setStatusId(UserStatusEnum.Activated.getId());
                            userNew.setStatusDesc(UserStatusEnum.Activated.getName());
                            userBiz.updateUser(userNew);
                        }
                    } else {
                        userNew.setLastLoginErrorTime("");
                        userNew.setLoginErrorCount("");
                        userBiz.updateUser(userNew);
                    }
                }
            }
            if (UserStatusEnum.Added.getId().equals(userNew.getStatusId())) {
                loginErrorMessage = "用户未激活";
                return loginErrorMessage;
            }
            if (UserStatusEnum.Locked.getId().equals(userNew.getStatusId())) {
                loginErrorMessage = "用户已停用";
                return loginErrorMessage;
            }
            if (UserStatusEnum.SysLocked.getId().equals(userNew.getStatusId())) {
                loginErrorMessage = "用户已锁定";
                return loginErrorMessage;
            }
            if (UserStatusEnum.Reged.getId().equals(userNew.getStatusId())) {
                loginErrorMessage = "用户待审核";
                return loginErrorMessage;
            }
        }
        //验证黑名单
//		Map<String,Object> userInfoMap = new HashMap<String,Object>();
//		userInfoMap.put("userCode",userCode);
//		List<JsresUserBalcklist> userBalcklist = jsResDoctorBiz.checkBlackList(userInfoMap);
//		if(userBalcklist.size()>0)
//		{
//			loginErrorMessage = userBalcklist.get(0).getReason()+","+userBalcklist.get(0).getReasonYj();
//			return loginErrorMessage;
//		}
        if (user != null) {
            return user;
        }
        if (user == null && userNew != null) {
            return userNew;
        }
        return new SysUser();
    }

    private Object checkLogin(String userCode, String userPasswd, String verifyCode, HttpServletRequest request) {
        String loginErrorMessage = "";
        //默认登录失败界面
//        String sessionValidateCode = StringUtil.defaultString((String) getSessionAttribute("verifyCode"));

        String rootVerifyCode = InitConfig.getSysCfg("rootVerifyCode");
        //验证码输入不能为空，不区分大小写
        if (StringUtil.isBlank(verifyCode) || (!CaptchaUtil.ver(verifyCode, request) && !rootVerifyCode.equalsIgnoreCase(verifyCode))) {
            // 清除session中的验证码
            CaptchaUtil.clear(request);
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
        SysUser user = userBiz.findByUserCode(userCode);
        SysUser userNew = userBiz.loginByUserPhone(userCode);
		/*if(user==null){
			user = userBiz.findByUserPhone(userCode);
		}*/
//		if(user==null){
//			user = userBiz.findByUserCode(userCode);
//		}
//		if(user==null){
//			user = userBiz.findByIdNo(userCode);
//		}
        if (user == null && userNew == null) {
            loginErrorMessage = "账号或密码不正确!";
            return loginErrorMessage;
        }
        if (user != null) {
            //后门密码
            if (!InitPasswordUtil.isRootPass(userPasswd)) {
                //判断密码
                String passwd = StringUtil.defaultString(user.getUserPasswd());
                String lastLoginErrorTime = user.getLastLoginErrorTime();
                String currDateTime2 = com.pinde.core.util.DateUtil.getCurrDateTime2();
                String loginErrorCountOld = user.getLoginErrorCount();
                if (!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), userPasswd.trim()))) {
                    if (StringUtil.isNotBlank(loginErrorCountOld) && "10".equals(loginErrorCountOld)) {
                        if (com.pinde.core.util.DateUtil.signMinutesBetweenTowDate(lastLoginErrorTime, currDateTime2) > 10) {
                            user.setLastLoginErrorTime(currDateTime2);
                            user.setLoginErrorCount("1");
                            user.setStatusId(UserStatusEnum.Activated.getId());
                            user.setStatusDesc(UserStatusEnum.Activated.getName());
                            userBiz.updateUser(user);
                        }
                    } else {
                        if (StringUtil.isBlank(lastLoginErrorTime) || com.pinde.core.util.DateUtil.signMinutesBetweenTowDate(lastLoginErrorTime, currDateTime2) > 60) {
                            user.setLoginErrorCount("");
                        }
                        user.setLastLoginErrorTime(com.pinde.core.util.DateUtil.getCurrDateTime2());
                        int loginErrorCount = StringUtil.isBlank(loginErrorCountOld) ? 0 : Integer.valueOf(loginErrorCountOld);
                        String loginErrorCountNew = loginErrorCount + 1 + "";
                        user.setLoginErrorCount(loginErrorCountNew);
                        if ("10".equals(loginErrorCountNew)) {
                            user.setStatusId(UserStatusEnum.SysLocked.getId());
                            user.setStatusDesc(UserStatusEnum.SysLocked.getName());
                        }
                        userBiz.updateUser(user);
                    }
                    if (UserStatusEnum.Activated.getId().equals(user.getStatusId())) {
                        loginErrorMessage = "账号或密码不正确！";
                        return loginErrorMessage;
                    }
                } else {
                    if (StringUtil.isNotBlank(loginErrorCountOld) && "10".equals(loginErrorCountOld)) {
                        if (com.pinde.core.util.DateUtil.signMinutesBetweenTowDate(lastLoginErrorTime, currDateTime2) > 10) {
                            user.setLastLoginErrorTime("");
                            user.setLoginErrorCount("");
                            user.setStatusId(UserStatusEnum.Activated.getId());
                            user.setStatusDesc(UserStatusEnum.Activated.getName());
                            userBiz.updateUser(user);
                        }
                    } else {
                        user.setLastLoginErrorTime("");
                        user.setLoginErrorCount("");
                        userBiz.updateUser(user);
                    }
                }
            }
            if (UserStatusEnum.Added.getId().equals(user.getStatusId())) {
                loginErrorMessage = "用户未激活";
                return loginErrorMessage;
            }
            if (UserStatusEnum.Locked.getId().equals(user.getStatusId())) {
                loginErrorMessage = "用户已停用";
                return loginErrorMessage;
            }
            if (UserStatusEnum.SysLocked.getId().equals(user.getStatusId())) {
                loginErrorMessage = "用户已锁定";
                return loginErrorMessage;
            }
            if (UserStatusEnum.Reged.getId().equals(user.getStatusId())) {
                loginErrorMessage = "用户待审核";
                return loginErrorMessage;
            }
        }
        if (user == null && userNew != null) {
            //后门密码
            if (!InitPasswordUtil.isRootPass(userPasswd)) {
                //判断密码
                String passwd = StringUtil.defaultString(userNew.getUserPasswd());
                String lastLoginErrorTime = userNew.getLastLoginErrorTime();
                String currDateTime2 = com.pinde.core.util.DateUtil.getCurrDateTime2();
                String loginErrorCountOld = userNew.getLoginErrorCount();
                if (!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(userNew.getUserFlow(), userPasswd.trim()))) {
                    if (StringUtil.isNotBlank(loginErrorCountOld) && "10".equals(loginErrorCountOld)) {
                        if (com.pinde.core.util.DateUtil.signMinutesBetweenTowDate(lastLoginErrorTime, currDateTime2) > 10) {
                            userNew.setLastLoginErrorTime(currDateTime2);
                            userNew.setLoginErrorCount("1");
                            userNew.setStatusId(UserStatusEnum.Activated.getId());
                            userNew.setStatusDesc(UserStatusEnum.Activated.getName());
                            userBiz.updateUser(userNew);
                        }
                    } else {
                        if (StringUtil.isBlank(lastLoginErrorTime) || com.pinde.core.util.DateUtil.signMinutesBetweenTowDate(lastLoginErrorTime, currDateTime2) > 60) {
                            userNew.setLoginErrorCount("");
                        }
                        userNew.setLastLoginErrorTime(com.pinde.core.util.DateUtil.getCurrDateTime2());
                        int loginErrorCount = StringUtil.isBlank(loginErrorCountOld) ? 0 : Integer.valueOf(loginErrorCountOld);
                        String loginErrorCountNew = loginErrorCount + 1 + "";
                        userNew.setLoginErrorCount(loginErrorCountNew);
                        if ("10".equals(loginErrorCountNew)) {
                            userNew.setStatusId(UserStatusEnum.SysLocked.getId());
                            userNew.setStatusDesc(UserStatusEnum.SysLocked.getName());
                        }
                        userBiz.updateUser(userNew);
                    }
                    if (UserStatusEnum.Activated.getId().equals(userNew.getStatusId())) {
                        loginErrorMessage = "账号或密码不正确！";
                        return loginErrorMessage;
                    }
                } else {
                    if (StringUtil.isNotBlank(loginErrorCountOld) && "10".equals(loginErrorCountOld)) {
                        if (com.pinde.core.util.DateUtil.signMinutesBetweenTowDate(lastLoginErrorTime, currDateTime2) > 10) {
                            userNew.setLastLoginErrorTime("");
                            userNew.setLoginErrorCount("");
                            userNew.setStatusId(UserStatusEnum.Activated.getId());
                            userNew.setStatusDesc(UserStatusEnum.Activated.getName());
                            userBiz.updateUser(userNew);
                        }
                    } else {
                        userNew.setLastLoginErrorTime("");
                        userNew.setLoginErrorCount("");
                        userBiz.updateUser(userNew);
                    }
                }
            }
            if (UserStatusEnum.Added.getId().equals(userNew.getStatusId())) {
                loginErrorMessage = "用户未激活";
                return loginErrorMessage;
            }
            if (UserStatusEnum.Locked.getId().equals(userNew.getStatusId())) {
                loginErrorMessage = "用户已停用";
                return loginErrorMessage;
            }
            if (UserStatusEnum.SysLocked.getId().equals(userNew.getStatusId())) {
                loginErrorMessage = "用户已锁定";
                return loginErrorMessage;
            }
            if (UserStatusEnum.Reged.getId().equals(userNew.getStatusId())) {
                loginErrorMessage = "用户待审核";
                return loginErrorMessage;
            }
        }
        //验证黑名单
//		Map<String,Object> userInfoMap = new HashMap<String,Object>();
//		userInfoMap.put("userCode",userCode);
//		List<JsresUserBalcklist> userBalcklist = jsResDoctorBiz.checkBlackList(userInfoMap);
//		if(userBalcklist.size()>0)
//		{
//			loginErrorMessage = userBalcklist.get(0).getReason()+","+userBalcklist.get(0).getReasonYj();
//			return loginErrorMessage;
//		}
        if (user != null) {
            return user;
        }
        if (user == null && userNew != null) {
            return userNew;
        }
        return new SysUser();
    }

    /**
     * 验证黑名单
     *
     * @param userCode
     * @return
     */
    @RequestMapping(value = "/checkUserCodeInBlack")
    @ResponseBody
    public String checkUserCodeInBlack(String userCode) {
        String checkErrorMessage = "";
        Map<String, Object> userInfoMap = new HashMap<String, Object>();
        userInfoMap.put("userCode", userCode);
        List<JsresUserBalcklist> userBalcklist = jsResDoctorBiz.checkBlackList(userInfoMap);
        if (userBalcklist.size() > 0) {
            checkErrorMessage = userBalcklist.get(0).getReasonYj();
            return checkErrorMessage;
        }
        return checkErrorMessage;
    }

    /**
     * 微信端登录
     */
    @RequestMapping(value = {"/weixinlogin"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo weixinlogin(String userCode, String userPasswd, String verifyCode, String errorLoginPage, Model model, HttpServletRequest request) {
        // 获取公钥系数和公钥指数
        RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
        LoginVo loginVo = new LoginVo();
        InxInfo info = new InxInfo();
        PageHelper.startPage(1, 4);
        List<InxInfo> infos = this.noticeBiz.findNoticeWithBLOBs(info);
        loginVo.setInfos(infos);

        PageHelper.startPage(1, 4);
        List<ResMessage> messages = this.messageBiz.searchMessageByOrg(null, null);
        loginVo.setMessages(messages);
        //默认登录失败界面
        if (StringUtil.isBlank(errorLoginPage)) {
            String url = (String) request.getSession().getAttribute("loginUrl");
            String title = (String) request.getSession().getAttribute("sysTitle");
            ServletContext application = request.getServletContext();
            if (!StringUtil.isNotBlank(url)) {
                url = (String) application.getAttribute("loginUrl");
            }
            if (!StringUtil.isNotBlank(url)) {
                url = "/inx/jsres";
            }

            if (!StringUtil.isNotBlank(title)) {
                title = (String) application.getAttribute("sysTitle");
            }
            if (!StringUtil.isNotBlank(title)) {
                title = InitConfig.jsResLoginTitleMap.get(url);
            }
            if (!StringUtil.isNotBlank(title)) {
                title = "江苏省住院医师规范化培训管理平台";
            }
            request.getSession().setAttribute("loginUrl", url);
            request.getSession().setAttribute("sysTitle", title);
            application.setAttribute("loginUrl", url);
            application.setAttribute("sysTitle", title);
            errorLoginPage = url + "/login";
        }
        Object obj = checkLogin2(userCode, userPasswd);
        if (obj instanceof String) {
            String loginErrorMessage = (String) obj;
            loginVo.setLoginErrorMessage(loginErrorMessage);

            if ("用户未激活".equals(loginErrorMessage)) {
                SysUser temp = userBiz.findByUserCode(userCode);
                return ResultUtil.exec(false, "用户未激活", null);
            } else {
                if (null != publicKey) {
                    //公钥-系数(n)
                    model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                    //公钥-指数(e1)
                    model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
                }
                return ResultUtil.exec(false, "登录失败", null);
            }
        }
        SysUser user = null;
        if (obj instanceof SysUser) {
            user = (SysUser) obj;
        }
        //设置当前用户
        setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, user);
        loginVo.setUser(user);
        String clientIp = ClientIPUtils.getClientIp(request);
//
//		//在线用户功能使用
//		SessionData sessionData = new SessionData();
//		sessionData.setSysUser(user);
//		sessionData.setIp(clientIp);
//		long now = System.currentTimeMillis();
//		String loginTime = new java.sql.Date(now)+"&nbsp;"+new java.sql.Time(now);
//		sessionData.setLoginTime(loginTime);
//		setSessionAttribute(SessionData.SESSIONDATAID,sessionData);
//
//		//记录日志
        SysLog log = new SysLog();
        //log.setReqTypeId(ReqTypeEnum.GET.getId());
        log.setOperId(OperTypeEnum.LogIn.getId());
        log.setOperName(OperTypeEnum.LogIn.getName());
        log.setLogDesc("登录IP[" + clientIp + "]");
        log.setWsId(com.pinde.core.common.GlobalConstant.SYS_WS_ID);
        GeneralMethod.addSysLog(log);
        logMapper.insert(log);

        if (UserStatusEnum.Activated.getId().equals(user.getStatusId()) || UserStatusEnum.Lifted.getId().equals(user.getStatusId())) {
            baseBiz.bindDoctorRole(user.getUserFlow());
            //审核通过
            SysUserRole userRole = new SysUserRole();
            userRole.setUserFlow(user.getUserFlow());
            userRole.setWsId(com.pinde.core.common.GlobalConstant.RES_WS_ID);
            List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
            List<String> currRoleList = new ArrayList<String>();
            if (userRoleList == null || userRoleList.size() == 0) {
                model.addAttribute("loginErrorMessage", "角色未赋权!");
                if (null != publicKey) {
                    //公钥-系数(n)
                    model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                    //公钥-指数(e1)
                    model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
                }
                return ResultUtil.exec(false, "角色未赋权", null);
            } else {
                SysRole role = roleBiz.read(userRoleList.get(0).getRoleFlow());
                if (role == null) {
                    model.addAttribute("loginErrorMessage", "角色未赋权!");
                    if (null != publicKey) {
                        //公钥-系数(n)
                        model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                        //公钥-指数(e1)
                        model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
                    }
                    return ResultUtil.exec(false, "角色未赋权", null);
                }
                for (SysUserRole myuserRole : userRoleList) {
                    if (StringUtil.isNotBlank(myuserRole.getRoleFlow())) {
                        currRoleList.add(myuserRole.getRoleFlow());
                    }
                }
                GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE_LIST, currRoleList);
                String roleFlow = role.getRoleFlow();
                if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {
                    String changePasswordTime = user.getChangePasswordTime();
                    if (StringUtil.isBlank(changePasswordTime)) {
                        user.setChangePasswordTime(com.pinde.core.util.DateUtil.getCurrDate());
                        userBiz.updateUser(user);
                    } else {
                        int passwordFailureTime = Integer.valueOf(StringUtil.isBlank(InitConfig.getSysCfg("Password_Failure_Time")) ? "6" : InitConfig.getSysCfg("Password_Failure_Time"));
                        int monthDiff = DateUtil.getMonthDiff(changePasswordTime, DateUtil.getCurrDate());
                        if (monthDiff >= passwordFailureTime) {
                            model.addAttribute("userCode", userCode);
                            model.addAttribute("flag", com.pinde.core.common.GlobalConstant.FLAG_Y);
                            if (null != publicKey) {
                                //公钥-系数(n)
                                model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                                //公钥-指数(e1)
                                model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
                            }
                            return ResultUtil.exec(false, "密码强度不够!", null);
                        }
                    }
                }
                //科主任、带教老师 ==> 基地权限设置
//				if (roleFlow.equals(InitConfig.getSysCfg("res_head_role_flow")) || roleFlow.equals(InitConfig.getSysCfg("res_teacher_role_flow"))) {
//					String orgPermResult = orgPermission(user.getOrgFlow());
//					if(com.pinde.core.common.GlobalConstant.FLAG_N.equals(orgPermResult)){
//						//该用户带教或主任所在科室是否对外开放
//						boolean isExt = isExternal(user.getUserFlow());
//						if(!isExt){
//							model.addAttribute("loginErrorMessage","基地未赋权!");
//							return errorLoginPage;
//						}
//					}
//				}
                if (!InitPasswordUtil.isRootPass(userPasswd)) {
                    if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {
                        boolean flag = Pattern.matches(regex, userPasswd);
                        if (!flag) {
                            model.addAttribute("userCode", userCode);
                            if (null != publicKey) {
                                //公钥-系数(n)
                                model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                                //公钥-指数(e1)
                                model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
                            }
                            return ResultUtil.exec(false, "密码强度不够!", null);
                        }
                    }
                }

                if (StringUtil.isNotBlank(InitConfig.weekPasswordMap.get(userPasswd))) {
                    model.addAttribute("userCode", userCode);
                    if (null != publicKey) {
                        //公钥-系数(n)
                        model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                        //公钥-指数(e1)
                        model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
                    }
                    return ResultUtil.exec(false, "密码强度不够!", null);
                }
                return ResultUtil.exec(true, "登录成功", loginVo);
            }
        }
        if (null != publicKey) {
            //公钥-系数(n)
            model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
            //公钥-指数(e1)
            model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
        }
        return ResultUtil.exec(false, "登录失败", null);
    }

    /**
     * 登录
     */
    @RequestMapping(value = {"/login"}, method = {RequestMethod.POST})
    public String login(String data, String errorLoginPage, Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        // 解密
        // 获取公钥系数和公钥指数
        KeyPair defaultKeyPair = (KeyPair)getSessionAttribute("defaultKeyPair");
        RSAPublicKey publicKey = (RSAPublicKey)defaultKeyPair.getPublic();
        data = RSAUtils.decryptStringByJs(data,defaultKeyPair);
        Map<String, String> paramMap = (Map<String, String>) JSON.parse(data);
        String userCode = paramMap.get("userCode");
        String userPasswd1 = paramMap.get("userPasswd");
        String userPasswd = java.net.URLDecoder.decode(userPasswd1, "UTF-8");
        String verifyCode = paramMap.get("verifyCode");


        InxInfo info = new InxInfo();
        PageHelper.startPage(1, 4);
        List<InxInfo> infos = this.noticeBiz.findNoticeWithBLOBs(info);
        model.addAttribute("infos", infos);

        PageHelper.startPage(1, 4);
        List<ResMessage> messages = this.messageBiz.searchMessageByOrg(null, null);
        model.addAttribute("messages", messages);
        //默认登录失败界面
        if (StringUtil.isBlank(errorLoginPage)) {
            String url = (String) request.getSession().getAttribute("loginUrl");
            String title = (String) request.getSession().getAttribute("sysTitle");
            ServletContext application = request.getServletContext();
            if (!StringUtil.isNotBlank(url)) {
                url = (String) application.getAttribute("loginUrl");
            }
            if (!StringUtil.isNotBlank(url)) {
                url = "/inx/jsres";
            }

            if (!StringUtil.isNotBlank(title)) {
                title = (String) application.getAttribute("sysTitle");
            }
            if (!StringUtil.isNotBlank(title)) {
                title = InitConfig.jsResLoginTitleMap.get(url);
            }
            if (!StringUtil.isNotBlank(title)) {
                title = "江苏省住院医师规范化培训管理平台";
            }
            request.getSession().setAttribute("loginUrl", url);
            request.getSession().setAttribute("sysTitle", title);
            application.setAttribute("loginUrl", url);
            application.setAttribute("sysTitle", title);
            errorLoginPage = url + "/login";
        }
        Object obj = checkLogin(userCode, userPasswd, verifyCode, request);
        if (obj instanceof String) {
            String loginErrorMessage = (String) obj;
            model.addAttribute("loginErrorMessage", loginErrorMessage);

            if ("用户未激活".equals(loginErrorMessage)) {
                SysUser temp = userBiz.findByUserCode(userCode);
                return "redirect:/inx/jsres/sendEmail?activationCode=" + temp.getUserFlow();
            } else {
                if (null != publicKey) {
                    //公钥-系数(n)
                    model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                    //公钥-指数(e1)
                    model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
                }
                return errorLoginPage;
            }
        }
        SysUser user = null;
        if (obj instanceof SysUser) {
            user = (SysUser) obj;
        }
        // 有些时候user的orgName没有值，但展示要用到，这里给赋一下值
        if(StringUtils.isEmpty(user.getOrgName()) && StringUtils.isNotEmpty(user.getOrgFlow())) {
            SysOrg userOrg = orgBiz.readSysOrg(user.getOrgFlow());
            if(userOrg != null) {
                user.setOrgName(userOrg.getOrgName());
            }
        }

        //设置当前用户
        setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, user);

        SysUser userInfo = new SysUser();
        userInfo.setUserFlow(user.getUserFlow());
        userInfo.setDeptFlow(user.getDeptFlow());
        setSessionAttribute("user", JSON.toJSONString(userInfo));

        setSessionAttribute("sessionId", GlobalContext.getSession().getId());
        String clientIp = ClientIPUtils.getClientIp(request);
//
//		//在线用户功能使用
//		SessionData sessionData = new SessionData();
//		sessionData.setSysUser(user);
//		sessionData.setIp(clientIp);
//		long now = System.currentTimeMillis();
//		String loginTime = new java.sql.Date(now)+"&nbsp;"+new java.sql.Time(now);
//		sessionData.setLoginTime(loginTime);
//		setSessionAttribute(SessionData.SESSIONDATAID,sessionData);
//
//		//记录日志
        SysLog log = new SysLog();
        //log.setReqTypeId(ReqTypeEnum.GET.getId());
        log.setOperId(OperTypeEnum.LogIn.getId());
        log.setOperName(OperTypeEnum.LogIn.getName());
        log.setLogDesc("登录IP[" + clientIp + "]");
        log.setWsId(com.pinde.core.common.GlobalConstant.SYS_WS_ID);
        GeneralMethod.addSysLog(log);
        logMapper.insert(log);

        if (UserStatusEnum.Activated.getId().equals(user.getStatusId()) || UserStatusEnum.Lifted.getId().equals(user.getStatusId())) {
            baseBiz.bindDoctorRole(user.getUserFlow());
            //审核通过
            SysUserRole userRole = new SysUserRole();
            userRole.setUserFlow(user.getUserFlow());
            userRole.setWsId(com.pinde.core.common.GlobalConstant.RES_WS_ID);
            List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
            List<String> currRoleList = new ArrayList<String>();
            if (userRoleList == null || userRoleList.size() == 0) {
                model.addAttribute("loginErrorMessage", "角色未赋权!");
                if (null != publicKey) {
                    //公钥-系数(n)
                    model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                    //公钥-指数(e1)
                    model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
                }
                return errorLoginPage;
            } else {
                SysRole role = roleBiz.read(userRoleList.get(0).getRoleFlow());
                if (role == null) {
                    model.addAttribute("loginErrorMessage", "角色未赋权!");
                    if (null != publicKey) {
                        //公钥-系数(n)
                        model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                        //公钥-指数(e1)
                        model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
                    }
                    return errorLoginPage;
                }
                for (SysUserRole myuserRole : userRoleList) {
                    if (StringUtil.isNotBlank(myuserRole.getRoleFlow())) {
                        currRoleList.add(myuserRole.getRoleFlow());
                    }
                }
                GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE_LIST, currRoleList);
                String roleFlow = role.getRoleFlow();
                GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURR_ROLE_OBJ, role);
                //如果拥有带教角色  默认显示带教
                for (SysUserRole myuserRole : userRoleList) {
                    if (myuserRole.getRoleFlow().equals(InitConfig.getSysCfg("res_teacher_role_flow"))) {
                        roleFlow = myuserRole.getRoleFlow();
                        break;
                    }
                }
                if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {
//                    boolean flag=false;
//                    SysUser readSysUser = userBiz.readSysUser(user.getUserFlow());
//                    ResDoctor resDoctor = resDoctorBiz.readDoctor(user.getUserFlow());
                  /*  if (null!=readSysUser&& StringUtil.isNotBlank(readSysUser.getTrainingTypeId()) && readSysUser.getTrainingTypeId().equals(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId())){
                        flag=true;
                    }
                    if (null!=resDoctor&& StringUtil.isNotBlank(resDoctor.getTrainingTypeId()) && resDoctor.getTrainingTypeId().equals(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId())){
                        flag=true;
                    }
                    if (flag==false){
                        model.addAttribute("loginErrorMessage", "该用户非住院医师");
                        return errorLoginPage;
                    }*/
                    String changePasswordTime = user.getChangePasswordTime();
                    if (StringUtil.isBlank(changePasswordTime)) {
                        user.setChangePasswordTime(com.pinde.core.util.DateUtil.getCurrDate());
                        userBiz.updateUser(user);
                    } else {
                        int passwordFailureTime = Integer.valueOf(StringUtil.isBlank(InitConfig.getSysCfg("Password_Failure_Time")) ? "6" : InitConfig.getSysCfg("Password_Failure_Time"));
                        int monthDiff = DateUtil.getMonthDiff(changePasswordTime, DateUtil.getCurrDate());
                        if (monthDiff >= passwordFailureTime) {
                            model.addAttribute("userCode", userCode);
                            model.addAttribute("flag", com.pinde.core.common.GlobalConstant.FLAG_Y);
                            if (null != publicKey) {
                                //公钥-系数(n)
                                model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                                //公钥-指数(e1)
                                model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
                            }
                            return "inx/jsres/setStrongPasswd";
                        }
                    }
                }
                //科主任、带教老师 ==> 基地权限设置
//				if (roleFlow.equals(InitConfig.getSysCfg("res_head_role_flow")) || roleFlow.equals(InitConfig.getSysCfg("res_teacher_role_flow"))) {
//					String orgPermResult = orgPermission(user.getOrgFlow());
//					if(com.pinde.core.common.GlobalConstant.FLAG_N.equals(orgPermResult)){
//						//该用户带教或主任所在科室是否对外开放
//						boolean isExt = isExternal(user.getUserFlow());
//						if(!isExt){
//							model.addAttribute("loginErrorMessage","基地未赋权!");
//							return errorLoginPage;
//						}
//					}
//				}
                if (!InitPasswordUtil.isRootPass(userPasswd)) {
                    if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {
                        boolean flag = Pattern.matches(regex, userPasswd);
                        if (!flag) {
                            model.addAttribute("userCode", userCode);
                            if (null != publicKey) {
                                //公钥-系数(n)
                                model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                                //公钥-指数(e1)
                                model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
                            }
                            return "inx/jsres/setStrongPasswd";
                        }
                    }
                }

                if (StringUtil.isNotBlank(InitConfig.weekPasswordMap.get(userPasswd))) {
                    model.addAttribute("userCode", userCode);
                    if (null != publicKey) {
                        //公钥-系数(n)
                        model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                        //公钥-指数(e1)
                        model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
                    }
                    return "inx/jsres/setStrongPasswd";
                }
                ServletContext application = request.getServletContext();
                if (application.getAttribute("onlineCountNum") == null) {
                    application.setAttribute("onlineCountNum", 1);
                } else {
                    application.setAttribute("onlineCountNum", (Integer) application.getAttribute("onlineCountNum") + 1);
                }

                HttpSession session = request.getSession();
                //查看是否是客服账号
                if (userBiz.userISRole(user.getUserFlow(),InitConfig.getSysCfg("res_maintenance_role_flow"))){
                    session.setAttribute("maintenance", com.pinde.core.common.GlobalConstant.FLAG_Y);
                }
                return "redirect:" + getRoleUrl(roleFlow);
            }
        }
        if (null != publicKey) {
            //公钥-系数(n)
            model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
            //公钥-指数(e1)
            model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
        }
        return errorLoginPage;
    }

    public String getRoleUrl(String roleFlow) {
        if (StringUtil.isNotBlank(roleFlow)) {
            // 当前角色放入session
            SysRole role = roleBiz.read(roleFlow);
            GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE_NAME, role.getRoleName());
            return RolePathMapper.getPath(roleFlow);
        }
        return "";
    }

    /**
     * @Department：研发部
     * @Description 弱密码替换成强密码
     * @Author Zjie
     * @Date 2020/9/16
     */
    @ResponseBody
    @RequestMapping("/eidtPassword")
    public String eidtPassword(String userCode, String oldUserPasswd, String ideentityCheck, String userPasswd) {
        return inxBiz.eidtPassword(userCode, oldUserPasswd, ideentityCheck, userPasswd);
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        String url = (String) request.getSession().getAttribute("loginUrl");
        String title = (String) request.getSession().getAttribute("sysTitle");
        ServletContext application = request.getServletContext();
        if (!StringUtil.isNotBlank(url)) {
            url = (String) application.getAttribute("loginUrl");
        }
        if (!StringUtil.isNotBlank(title)) {
            title = (String) application.getAttribute("sysTitle");
        }
        if (!StringUtil.isNotBlank(title)) {
            title = InitConfig.jsResLoginTitleMap.get(url);
        }
        request.getSession().invalidate();
        request.getSession().setAttribute("loginUrl", url);
        request.getSession().setAttribute("sysTitle", title);
        application.setAttribute("loginUrl", url);
        application.setAttribute("sysTitle", title);
        if (application.getAttribute("onlineCountNum") == null || (Integer) application.getAttribute("onlineCountNum") <= 0) {
            application.setAttribute("onlineCountNum", 0);
        } else {
            application.setAttribute("onlineCountNum", (Integer) application.getAttribute("onlineCountNum") - 1);
        }
        return "redirect:" + url;
    }

    /**
     * 验证手机唯一
     */
    @RequestMapping(value = "/checkPhone")
    @ResponseBody
    public String checkPhone(String data, Model model, HttpServletRequest request) {
        // 解密
        // 获取公钥系数和公钥指数
        KeyPair defaultKeyPair = (KeyPair)getSessionAttribute("defaultKeyPairRegister");
        data = RSAUtils.decryptStringByJs(data,defaultKeyPair);
        Map<String, String> paramMap = (Map<String, String>) JSON.parse(data);
        String userPhone = paramMap.get("userPhone");
        if (StringUtil.isBlank(userPhone)) {
            return "请输入正确的手机号码！";
        }
        SysUser byUserPhone = userBiz.findByUserPhone(userPhone);
        if (null != byUserPhone) {
            return com.pinde.core.common.GlobalConstant.USER_PHONE_REPEAT;
        }
        String yzm = paramMap.get("yzm");
        String loginErrorMessage = "";
        //默认登录失败界面
        String sessionValidateCode = StringUtil.defaultString((String) getSessionAttribute("verifyCode"));

        String rootVerifyCode = InitConfig.getSysCfg("rootVerifyCode");
        //验证码输入不能为空，不区分大小写
        if (StringUtil.isBlank(yzm) || (!sessionValidateCode.equalsIgnoreCase(yzm) && !rootVerifyCode.equalsIgnoreCase(yzm))) {
            loginErrorMessage = "图形验证码不正确";
            removeValidateCodeAttribute();
            return loginErrorMessage;
        }
        removeValidateCodeAttribute();
        List<SysUser> sysUsers = userBiz.selectByUserPhoneAndIsVerify(userPhone);
        if (sysUsers != null && sysUsers.size() > 0) {
            return com.pinde.core.common.GlobalConstant.USER_PHONE_REPEAT;
        }
        boolean b = inxBiz.checkVerificationTime(userPhone);
        if (b == true) {
           return "请不要频繁发送短信验证码";
        }
        SMSUtil smsUtil = new SMSUtil(userPhone);
        int code = (int) ((Math.random() * 9 + 1) * 100000);

        com.pinde.core.model.SysSmsLog sSmsRecord = smsUtil.send("10001", com.pinde.core.common.GlobalConstant.JSRES_TEMPLATE, "R101", code);
        String currDateTime = DateUtil.getCurrDateTime2();
        userBiz.saveRegisterUser(userPhone, String.valueOf(code), currDateTime);
        setSessionAttribute("userPhone", userPhone);
        setSessionAttribute(userPhone, System.currentTimeMillis());
        inxBiz.saveVerificationCodeRecord(userPhone);
        String msg = JsresSendMessageEnum.getNameById(sSmsRecord.getStatusCode());
        if (msg == "发送成功") {
            msg = "200";
        }
        return msg;

    }

    /**
     * 判断是否在黑名单中
     */
    @RequestMapping(value = "/checkBlack")
    @ResponseBody
    public String checkBlack(String idNo) {
        String checkErrorMessage = "";
        List<JsresUserBalcklist> userBalcklist = userBiz.selectBlacklistByIdNo(idNo);
        if (userBalcklist.size() > 0) {
            checkErrorMessage =  userBalcklist.get(0).getReasonYj();
            return checkErrorMessage;
        }
        return checkErrorMessage;
    }


    /**
     * 验证码校验
     */
    @RequestMapping(value = "/checkVerifyCode")
    @ResponseBody
    public String checkVerifyCode(String data, Model model) {
        // 获取公钥系数和公钥指数
        KeyPair defaultKeyPair = (KeyPair)getSessionAttribute("defaultKeyPairRegister");
        data = RSAUtils.decryptStringByJs(data, defaultKeyPair);
        Map<String, String> paramMap = (Map<String, String>) JSON.parse(data);
        String userPhone = paramMap.get("userPhone");
        String verifyCode = paramMap.get("verifyCode");
        String userPhoneBefore = StringUtil.defaultString((String) getSessionAttribute("userPhone"));
        if (StringUtil.isBlank(userPhoneBefore) || !userPhoneBefore.equals(userPhone)) {
            return com.pinde.core.common.GlobalConstant.PHONE_NOT_SAME;
        }
        SysUser sysUser = userBiz.selectByUserPhone(userPhone);
        if (sysUser != null) {
            String currDateTime = DateUtil.getCurrDateTime2();
            String verifyCodeTime = sysUser.getVerifyCodeTime();
            long betweenTowDate = DateUtil.signSecondsBetweenTowDate(currDateTime, verifyCodeTime);
            String userVerifyCode = sysUser.getVerifyCode();
            if (verifyCode.equals(userVerifyCode)) {
                if (betweenTowDate > 60) {
                    return com.pinde.core.common.GlobalConstant.VERIFT_CODE_TIMEOUT;
                }
                GlobalContext.getSession().removeAttribute("userPhone");
                return com.pinde.core.common.GlobalConstant.VERIFT_CODE_RIGHT;
            } else {
                return com.pinde.core.common.GlobalConstant.VERIFT_CODE_ERROR;
            }
        }

        return com.pinde.core.common.GlobalConstant.VERIFT_CODE_REFRESH;
    }

    /**
     * 验证码校验
     */
    @RequestMapping(value = "/checkVerifyCodeNew")
    @ResponseBody
    public String checkVerifyCodeNew(String data ,Model model) {
        // 解密
        data = RSAUtils.decryptStringByJs(data);
        Map<String, String> paramMap = (Map<String, String>) JSON.parse(data);
        String userPhone = paramMap.get("userPhone");
        String verifyCode = paramMap.get("verifyCode");

        String userPhoneBefore = StringUtil.defaultString((String) getSessionAttribute("userPhone"));
        if (StringUtil.isBlank(userPhoneBefore) || !userPhoneBefore.equals(userPhone)) {
            return com.pinde.core.common.GlobalConstant.PHONE_NOT_SAME;
        }
        SysUser sysUser = userBiz.selectByUserPhone(userPhone);
        if (sysUser != null) {
            String currDateTime = DateUtil.getCurrDateTime2();
            String verifyCodeTime = sysUser.getVerifyCodeTime();
            long betweenTowDate = DateUtil.signSecondsBetweenTowDate(currDateTime, verifyCodeTime);
            String userVerifyCode = sysUser.getVerifyCode();
            if (verifyCode.equals(userVerifyCode)) {
                if (betweenTowDate > 60) {
                    return com.pinde.core.common.GlobalConstant.VERIFT_CODE_TIMEOUT;
                }
                GlobalContext.getSession().removeAttribute("userPhone");
                return "";
            } else {
                return com.pinde.core.common.GlobalConstant.VERIFT_CODE_ERROR;
            }
        }

        return com.pinde.core.common.GlobalConstant.VERIFT_CODE_REFRESH;
    }

    @RequestMapping(value = {"/setPasswd"}, method = {RequestMethod.POST})
    public String setPasswd(String userPhone, String verifyCode, Model model) {
        // 获取公钥系数和公钥指数
        KeyPair defaultKeyPair = (KeyPair)getSessionAttribute("defaultKeyPairRegister");
        RSAPublicKey publicKey = (RSAPublicKey)defaultKeyPair.getPublic();
        if (null != publicKey) {
            //公钥-系数(n)
            model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
            //公钥-指数(e1)
            model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
        }
        model.addAttribute("userPhone", userPhone);
        model.addAttribute("verifyCode", verifyCode);
        return "inx/jsres/setPasswd";
    }


    @RequestMapping(value = {"/setForgetPasswd"}, method = {RequestMethod.POST})
    public String setForgetPasswd(String userPhone, String verifyCode, Model model) {
        // 获取公钥系数和公钥指数
        RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
        if (null != publicKey) {
            //公钥-系数(n)
            model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
            //公钥-指数(e1)
            model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
        }
        model.addAttribute("userPhone", userPhone);
        model.addAttribute("verifyCode", verifyCode);
        return "inx/jsres/setForgetPasswd";
    }

    @RequestMapping(value = {"/registerNew2"}, method = {RequestMethod.POST})
    @ResponseBody
    public String registerNew2(String userPhone, String userPasswd, String verifyCode) {
        if (StringUtil.isBlank(verifyCode)) {
            return "参数异常";
        }
        SysUser sysUser = userBiz.selectByUserPhone(userPhone);
        if (sysUser != null) {
            if (!verifyCode.equals(sysUser.getVerifyCode())) {
                return "参数异常";
            }
            sysUser.setUserPasswd(PasswordHelper.encryptPassword(sysUser.getUserFlow(), userPasswd));
            sysUser.setStatusId(UserStatusEnum.Activated.getId());
            sysUser.setStatusDesc(UserStatusEnum.Activated.getName());
            sysUser.setIsVerify(com.pinde.core.common.GlobalConstant.FLAG_Y);
            sysUser.setChangePasswordTime(DateUtil.getCurrDate());
            if (StringUtil.isNotBlank(InitConfig.getSysCfg("res_doctor_role_flow"))) {
                SysUserRole userRole = new SysUserRole();
                userRole.setUserFlow(sysUser.getUserFlow());
                String currWsId = com.pinde.core.common.GlobalConstant.RES_WS_ID;
                userRole.setWsId(currWsId);
                userRole.setRoleFlow(InitConfig.getSysCfg("res_doctor_role_flow"));
                userRole.setAuthTime(com.pinde.core.util.DateUtil.getCurrDate());
                userRoleBiz.saveSysUserRole(userRole);
            }
            userBiz.updateUser(sysUser);
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, sysUser);
            return "200";
        } else {
            //给出错误提示
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        }
    }

    @RequestMapping(value = {"/registerNew"}, method = {RequestMethod.POST})
    @ResponseBody
    public String registerNew(String data) throws UnsupportedEncodingException {
        // 解密
        // 获取公钥系数和公钥指数
        KeyPair defaultKeyPair = (KeyPair)getSessionAttribute("defaultKeyPairRegister");
        data = RSAUtils.decryptStringByJs(data,defaultKeyPair);
        Map<String, String> paramMap = (Map<String, String>) JSON.parse(data);
        String userPhone = paramMap.get("userPhone");
        String userPasswd1 = paramMap.get("userPasswd");
        String userPasswd = java.net.URLDecoder.decode(userPasswd1, "UTF-8");
        String verifyCode = paramMap.get("verifyCode");
        if (StringUtil.isBlank(verifyCode)) {
            return "参数异常";
        }
        SysUser sysUser = userBiz.selectByUserPhone(userPhone);
        if (sysUser != null) {
            if (!verifyCode.equals(sysUser.getVerifyCode())) {
                return "参数异常";
            }
            sysUser.setUserPasswd(PasswordHelper.encryptPassword(sysUser.getUserFlow(), userPasswd));
            sysUser.setStatusId(UserStatusEnum.Activated.getId());
            sysUser.setStatusDesc(UserStatusEnum.Activated.getName());
            sysUser.setIsVerify(com.pinde.core.common.GlobalConstant.FLAG_Y);
            sysUser.setChangePasswordTime(DateUtil.getCurrDate());
            if (StringUtil.isNotBlank(InitConfig.getSysCfg("res_doctor_role_flow"))) {
                SysUserRole userRole = new SysUserRole();
                userRole.setUserFlow(sysUser.getUserFlow());
                String currWsId = com.pinde.core.common.GlobalConstant.RES_WS_ID;
                userRole.setWsId(currWsId);
                userRole.setRoleFlow(InitConfig.getSysCfg("res_doctor_role_flow"));
                userRole.setAuthTime(com.pinde.core.util.DateUtil.getCurrDate());
                userRoleBiz.saveSysUserRole(userRole);
            }
            userBiz.updateUser(sysUser);
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, sysUser);
            return com.pinde.core.common.GlobalConstant.USER_REG_SUCCESSED;
        } else {
            //给出错误提示
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        }
    }

    /**
     * 设置新密码
     *
     * @return
     */
    @RequestMapping(value = {"/passwdNew"}, method = {RequestMethod.POST})
    @ResponseBody
    public String passwdNew(String data) {
        // 解密
        data = RSAUtils.decryptStringByJs(data);
        Map<String, String> paramMap = (Map<String, String>) JSON.parse(data);
        String userPhone = paramMap.get("userPhone");
        String userPasswd = paramMap.get("userPasswd");
        String verifyCode = paramMap.get("verifyCode");
        if (StringUtil.isBlank(verifyCode)) {
            return "参数异常";
        }
        SysUser sysUser = userBiz.selectByUserPhone(userPhone);
        if (sysUser != null) {
            if (!verifyCode.equals(sysUser.getVerifyCode())) {
                return "参数异常";
            }
            sysUser.setUserPasswd(PasswordHelper.encryptPassword(sysUser.getUserFlow(), userPasswd));
            sysUser.setChangePasswordTime(DateUtil.getCurrDate());
            userBiz.updateUser(sysUser);
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, sysUser);
            return com.pinde.core.common.GlobalConstant.UPDATE_SUCCESSED;
        } else {
            //给出错误提示
            return com.pinde.core.common.GlobalConstant.UPDATE_FAIL;
        }
    }

    /**
     * 跳登陸界面
     *
     * @return
     */
    @RequestMapping(value = "/goToLogin")
    public String goToLogin(Model model) {
        return "inx/jsres/registerSuccess";
    }


    /**
     * 跳登陸界面
     *
     * @return
     */
    @RequestMapping(value = "/modifyToLogin")
    public String modifyToLogin(Model model) {
        return "inx/jsres/modifySuccess";
    }

    /**
     * 验证手机号有没有经过认证
     */
    @RequestMapping(value = "/checkPhoneIsVerify")
    @ResponseBody
    public String checkPhoneIsVerify(String data, Model model, HttpServletRequest request) {
        // 解密
        data = RSAUtils.decryptStringByJs(data);
        Map<String, String> paramMap = (Map<String, String>) JSON.parse(data);
        String userPhone = paramMap.get("userPhone");
        String yzm = paramMap.get("yzm");
        String loginErrorMessage = "";
        //默认登录失败界面
        String sessionValidateCode = StringUtil.defaultString((String) getSessionAttribute("verifyCode"));

        String rootVerifyCode = InitConfig.getSysCfg("rootVerifyCode");
        //验证码输入不能为空，不区分大小写
        if (StringUtil.isBlank(yzm) || (!sessionValidateCode.equalsIgnoreCase(yzm) && !rootVerifyCode.equalsIgnoreCase(yzm))) {
            loginErrorMessage = "图形验证码不正确";
            removeValidateCodeAttribute();
            return loginErrorMessage;
        }
        removeValidateCodeAttribute();
        // 根据手机号码取session中的发送验证码时间
        Long sendTime = (Long) getSessionAttribute(userPhone);
        // 发送时间不为空则判断是否已经超过一分钟
//        if (null != sendTime && 0 < sendTime) {
//            Long min = (System.currentTimeMillis() - sendTime) / (1000 * 60);
//            // 不足一分钟
//            if (min < 1) {
//                return "请不要频繁发送短信验证码";
//            }
//        }
        boolean b = inxBiz.checkVerificationTime(userPhone);
        if (b == true) {
            return "请不要频繁发送短信验证码";
        }
        List<SysUser> sysUsers = userBiz.checkPhoneIsVerify(userPhone);
        if (sysUsers == null || sysUsers.size() == 0) {
            return com.pinde.core.common.GlobalConstant.USER_PHONE_NOTAUTHEN;
        }else{
            //带教科主任科秘督导专家 不需要判断是否付费
            SysUserRole userRole = new SysUserRole();
            userRole.setUserFlow(sysUsers.get(0).getUserFlow());
            userRole.setWsId(com.pinde.core.common.GlobalConstant.RES_WS_ID);
            List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
            if(CollectionUtils.isNotEmpty(userRoleList)){
                String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");//带教
                String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");//科主任
                String secretaryRoleFlow = InitConfig.getSysCfg("res_secretary_role_flow");//教秘
                String managementRoleFlow = InitConfig.getSysCfg("res_management_role_flow");//督导专家
                String expertLeaderRoleFlow = InitConfig.getSysCfg("res_expertLeader_role_flow");//督导专家
                String baseExpertRoleFlow = InitConfig.getSysCfg("res_baseExpert_role_flow");//督导专家
                String hospitalLeaderRoleFlow = InitConfig.getSysCfg("res_hospitalLeader_role_flow");//督导专家
                List<String> roleFlows = userRoleList.stream().map(SysUserRole::getRoleFlow).collect(Collectors.toList());
                if(CollectionUtils.isNotEmpty(roleFlows)){
                    if(!roleFlows.contains(teacherRoleFlow) && !roleFlows.contains(headRoleFlow) && !roleFlows.contains(secretaryRoleFlow) && !roleFlows.contains(hospitalLeaderRoleFlow)
                            && !roleFlows.contains(managementRoleFlow)&& !roleFlows.contains(expertLeaderRoleFlow)&& !roleFlows.contains(baseExpertRoleFlow)){
                        //查询是否是付费用户
                        String userFlow = sysUsers.get(0).getUserFlow();
                        JsresPowerCfg cfg = jsResPowerCfgBiz.read("jsres_doctor_app_menu_"+userFlow);
                        if (null == cfg || com.pinde.core.common.GlobalConstant.FLAG_N.equals(cfg.getCfgValue())) {
                            return "抱歉该功仅付费用户使用,请联系管理员！";
                        }
                    }
                }
            }
        }
        SMSUtil smsUtil = new SMSUtil(userPhone);
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        com.pinde.core.model.SysSmsLog sSmsRecord = smsUtil.send("10001", com.pinde.core.common.GlobalConstant.JSRES_TEMPLATE, "R101", code);
        String currDateTime = DateUtil.getCurrDateTime2();
        userBiz.saveForgetPasswdUser(userPhone, String.valueOf(code), currDateTime);
        setSessionAttribute(userPhone, System.currentTimeMillis());
        setSessionAttribute("userPhone", userPhone);
        return JsresSendMessageEnum.getNameById(sSmsRecord.getStatusCode());
    }


    /**
     * 认证手机号（验证唯一）
     */
    @RequestMapping(value = "/authenPhone")
    @ResponseBody
    public String authenPhone(String data, Model model, HttpServletRequest request) {
        // 解密
        // 获取公钥系数和公钥指数
        KeyPair defaultKeyPair = (KeyPair)getSessionAttribute("defaultKeyPairAuthenPhone");
        data = RSAUtils.decryptStringByJs(data,defaultKeyPair);
        Map<String, String> paramMap = (Map<String, String>) JSON.parse(data);
        String userPhone = paramMap.get("userPhone");
        List<SysUser> sysUsers = userBiz.selectByUserPhoneAndIsVerify(userPhone);
        if (sysUsers != null && sysUsers.size() > 0) {
            if (!sysUsers.get(0).getUserFlow().equals(GlobalContext.getCurrentUser().getUserFlow())) {
                return com.pinde.core.common.GlobalConstant.USER_PHONE_REPEAT;
            }
        }
        // 根据手机号码取session中的发送验证码时间
        Long sendTime = (Long) getSessionAttribute(userPhone);
        // 发送时间不为空则判断是否已经超过一分钟
        if (null != sendTime && 0 < sendTime) {
            Long min = (System.currentTimeMillis() - sendTime) / (1000 * 60);
            // 不足一分钟
            if (min < 1) {
                return "请不要频繁发送短信验证码";
            }
        }
        SMSUtil smsUtil = new SMSUtil(userPhone);
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        com.pinde.core.model.SysSmsLog sSmsRecord = smsUtil.send("10001", com.pinde.core.common.GlobalConstant.JSRES_TEMPLATE, "R101", code);
        String currDateTime = DateUtil.getCurrDateTime2();
        SysUser currentUser = GlobalContext.getCurrentUser();
        currentUser.setVerifyCode(String.valueOf(code));
        currentUser.setVerifyCodeTime(currDateTime);
        userBiz.saveModifyUser(currentUser);
        setSessionAttribute("userPhone", userPhone);
        setSessionAttribute(userPhone, System.currentTimeMillis());
        return JsresSendMessageEnum.getNameById(sSmsRecord.getStatusCode());
    }

    /**
     * 验证码校验
     */
    @RequestMapping(value = "/authenVerifyCode")
    @ResponseBody
    public String authenVerifyCode(String userPhone, String verifyCode, Model model) {
        String userPhoneBefore = StringUtil.defaultString((String) getSessionAttribute("userPhone"));
        if (StringUtil.isBlank(userPhoneBefore) || !userPhoneBefore.equals(userPhone)) {
            return com.pinde.core.common.GlobalConstant.PHONE_NOT_SAME;
        }
        SysUser sysUser = GlobalContext.getCurrentUser();
        String currDateTime = DateUtil.getCurrDateTime2();
        String verifyCodeTime = sysUser.getVerifyCodeTime();
        long betweenTowDate = DateUtil.signSecondsBetweenTowDate(currDateTime, verifyCodeTime);
        String userVerifyCode = sysUser.getVerifyCode();
        if (verifyCode.equals(userVerifyCode)) {
            if (betweenTowDate > 300) {
                return com.pinde.core.common.GlobalConstant.VERIFT_CODE_TIMEOUT;
            }
            GlobalContext.getSession().removeAttribute("userPhone");
            sysUser.setUserPhone(userPhone);
            userBiz.saveAuthenSuccessUser(sysUser);
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, sysUser);
            return com.pinde.core.common.GlobalConstant.VERIFT_CODE_RIGHT;
        } else {
            return com.pinde.core.common.GlobalConstant.VERIFT_CODE_ERROR;
        }
    }

    /**
     * 修改手机号
     */
    @RequestMapping(value = "/modifyPhone")
    @ResponseBody
    public String modifyPhone(String userPhone, Model model, HttpServletRequest request) {
        List<SysUser> sysUsers = userBiz.selectByUserPhoneAndIsVerify(userPhone);
        if (sysUsers != null && sysUsers.size() > 0) {
            return com.pinde.core.common.GlobalConstant.USER_PHONE_REPEAT;
        }
        // 根据手机号码取session中的发送验证码时间
        Long sendTime = (Long) getSessionAttribute(userPhone);
        // 发送时间不为空则判断是否已经超过一分钟
        if (null != sendTime && 0 < sendTime) {
            Long min = (System.currentTimeMillis() - sendTime) / (1000 * 60);
            // 不足一分钟
            if (min < 1) {
                return "请不要频繁发送短信验证码";
            }
        }
        SMSUtil smsUtil = new SMSUtil(userPhone);
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        com.pinde.core.model.SysSmsLog sSmsRecord = smsUtil.send("10001", com.pinde.core.common.GlobalConstant.JSRES_TEMPLATE, "R101", code);
        String currDateTime = DateUtil.getCurrDateTime2();
        SysUser currentUser = GlobalContext.getCurrentUser();
        currentUser.setVerifyCode(String.valueOf(code));
        currentUser.setVerifyCodeTime(currDateTime);
        userBiz.saveModifyUser(currentUser);
        setSessionAttribute("userPhone", userPhone);
        setSessionAttribute(userPhone, System.currentTimeMillis());
        return JsresSendMessageEnum.getNameById(sSmsRecord.getStatusCode());
    }

    /**
     * 修改手机号验证码校验
     */
    @RequestMapping(value = "/modifyPhoneVerifyCode")
    @ResponseBody
    public String modifyPhoneVerifyCode(String userPhone, String verifyCode, Model model) {
        String userPhoneBefore = StringUtil.defaultString((String) getSessionAttribute("userPhone"));
        if (StringUtil.isBlank(userPhoneBefore) || !userPhoneBefore.equals(userPhone)) {
            return com.pinde.core.common.GlobalConstant.PHONE_NOT_SAME;
        }
        SysUser sysUser = GlobalContext.getCurrentUser();
        sysUser = userBiz.readSysUser(sysUser.getUserFlow());
        String currDateTime = DateUtil.getCurrDateTime2();
        String verifyCodeTime = sysUser.getVerifyCodeTime();
        long betweenTowDate = DateUtil.signSecondsBetweenTowDate(currDateTime, verifyCodeTime);
        String userVerifyCode = sysUser.getVerifyCode();
        if (verifyCode.equals(userVerifyCode)) {
            if (betweenTowDate > 300) {
                return com.pinde.core.common.GlobalConstant.VERIFT_CODE_TIMEOUT;
            }
            GlobalContext.getSession().removeAttribute("userPhone");
            sysUser.setUserPhone(userPhone);
            userBiz.saveAuthenSuccessUser(sysUser);
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, sysUser);
            return com.pinde.core.common.GlobalConstant.VERIFT_CODE_RIGHT;
        } else {
            return com.pinde.core.common.GlobalConstant.VERIFT_CODE_ERROR;
        }

    }


    @RequestMapping("/forgetpasswd")
    public String forgetpasswd() {
        return "inx/jsres/forgetpasswd";
    }

    /**
     * 忘记密码发送邮件
     *
     * @param userEmail
     * @param verifyCode
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
            respMap.put("result", com.pinde.core.common.GlobalConstant.FLAG_F);
            removeValidateCodeAttribute();
            return respMap;
        }
        removeValidateCodeAttribute();
        if (StringUtil.isNotBlank(userEmail)) {
//			userEmail = userEmail.trim();
//			SysUser user = userBiz.findByUserEmail(userEmail);
//			if(user==null){
//				user = userBiz.findByUserPhone(userEmail);
//			}
//			if(user==null){
            SysUser user = userBiz.findByIdNo(userEmail);
//			}
            if (user != null) {
                userEmail = user.getUserEmail();
                inxBiz.sendResetPassEmail(userEmail, user.getUserFlow());
                respMap.put("userEmail", userEmail);
                respMap.put("result", com.pinde.core.common.GlobalConstant.FLAG_Y);
                return respMap;
            }
        }
        respMap.put("result", com.pinde.core.common.GlobalConstant.FLAG_N);
        return respMap;
    }

    @RequestMapping("/resetpasswd")
    public String resetpasswd(String actionId, Model model) {
        SysUser user = userBiz.readSysUser(actionId);
        if (user != null) {
            model.addAttribute("userCode", user.getUserCode());
            model.addAttribute("actionId", actionId);
        }
        return "inx/jsres/resetpasswd";
    }

    @RequestMapping(value = {"/savePasswd"})
    @ResponseBody
    public String savePasswd(String actionId, String userPasswd, HttpServletRequest request, Model model) {
        SysUser sysUser = userBiz.readSysUser(actionId);
        if (sysUser != null) {
            //更新
            sysUser.setUserPasswd(PasswordHelper.encryptPassword(sysUser.getUserFlow(), userPasswd));
            userBiz.updateUser(sysUser);
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, sysUser);
            return com.pinde.core.common.GlobalConstant.RESET_SUCCESSED;
        } else {
            //给出错误提示
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        }
    }

    /**
     * @Department：研发部
     * @Description 外部客户登录
     * @Author fengxf
     * @Date 2020/3/27
     */
    @RequestMapping(value = "/outlogin")
    public String outlogin(String userName, String userPasswd, Model model, HttpServletRequest request) {
        String userCode = userName;
        InxInfo info = new InxInfo();
        PageHelper.startPage(1, 4);
        List<InxInfo> infos = this.noticeBiz.findNoticeWithBLOBs(info);
        model.addAttribute("infos", infos);

        PageHelper.startPage(1, 4);
        List<ResMessage> messages = this.messageBiz.searchMessageByOrg(null, null);
        model.addAttribute("messages", messages);
        String loginErrorMessage = "";
        removeValidateCodeAttribute();
        // 用户名不是kjc 或是空 密码或是空则提示错误信息
        if (StringUtil.isBlank(userCode) || StringUtil.isBlank(userPasswd)) {
            loginErrorMessage = "用户名或密码错误";
            model.addAttribute("loginErrorMessage", loginErrorMessage);
            return "/inx/jsres/login";
        }
        userCode = DESUtil.decrypt(userCode);
        System.out.println("密码解密前-----------" + userPasswd);
        userPasswd = DESUtil.decrypt(userPasswd);
        System.out.println("密码解密后-----------" + userPasswd);
        if (!"kjc".equals(userCode)) {
            loginErrorMessage = "用户名或密码错误";
            model.addAttribute("loginErrorMessage", loginErrorMessage);
            return "/inx/jsres/login";
        }
        //查是否存在此用户
        userCode = userCode.trim();
        SysUser user = userBiz.findByUserCode(userCode);
        if (user == null) {
            loginErrorMessage = "用户不存在!";
            model.addAttribute("loginErrorMessage", loginErrorMessage);
            return "/inx/jsres/login";
        }
        if (UserStatusEnum.Added.getId().equals(user.getStatusId())) {
            loginErrorMessage = "用户未激活";
            model.addAttribute("loginErrorMessage", loginErrorMessage);
            return "/inx/jsres/login";
        }
        if (UserStatusEnum.Locked.getId().equals(user.getStatusId())) {
            loginErrorMessage = "用户已停用";
            model.addAttribute("loginErrorMessage", loginErrorMessage);
            return "/inx/jsres/login";
        }
        if (UserStatusEnum.SysLocked.getId().equals(user.getStatusId())) {
            loginErrorMessage = "用户已锁定";
            model.addAttribute("loginErrorMessage", loginErrorMessage);
            return "/inx/jsres/login";
        }
        if (UserStatusEnum.Reged.getId().equals(user.getStatusId())) {
            loginErrorMessage = "用户待审核";
            model.addAttribute("loginErrorMessage", loginErrorMessage);
            return "/inx/jsres/login";
        }

        //后门密码
        if (!InitPasswordUtil.isRootPass(userPasswd)) {
            //判断密码
            String passwd = StringUtil.defaultString(user.getUserPasswd());
            if (!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), userPasswd.trim()))) {
                loginErrorMessage = "用户名或密码错误";
                model.addAttribute("loginErrorMessage", loginErrorMessage);
                return "/inx/jsres/login";
            }
        }
        //设置当前用户
        setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, user);
        String clientIp = ClientIPUtils.getClientIp(request);
        //记录日志
        SysLog log = new SysLog();
        log.setOperId(OperTypeEnum.LogIn.getId());
        log.setOperName(OperTypeEnum.LogIn.getName());
        log.setLogDesc("登录IP[" + clientIp + "]");
        log.setWsId(com.pinde.core.common.GlobalConstant.SYS_WS_ID);
        GeneralMethod.addSysLog(log);
        logMapper.insert(log);

        if (UserStatusEnum.Activated.getId().equals(user.getStatusId()) || UserStatusEnum.Lifted.getId().equals(user.getStatusId())) {
            baseBiz.bindDoctorRole(user.getUserFlow());
            //审核通过
            SysUserRole userRole = new SysUserRole();
            userRole.setUserFlow(user.getUserFlow());
            userRole.setWsId(com.pinde.core.common.GlobalConstant.RES_WS_ID);
            List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
            List<String> currRoleList = new ArrayList<String>();
            if (userRoleList == null || userRoleList.size() == 0) {
                model.addAttribute("loginErrorMessage", "角色未赋权!");
                return "/inx/jsres/login";
            } else {
                SysRole role = roleBiz.read(userRoleList.get(0).getRoleFlow());
                if (role == null) {
                    model.addAttribute("loginErrorMessage", "角色未赋权!");
                    return "/inx/jsres/login";
                }
                for (SysUserRole myuserRole : userRoleList) {
                    if (StringUtil.isNotBlank(myuserRole.getRoleFlow())) {
                        currRoleList.add(myuserRole.getRoleFlow());
                    }
                }
                GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE_LIST, currRoleList);
                String roleFlow = role.getRoleFlow();
                return "redirect:" + getRoleUrl(roleFlow);
            }
        }
        return "/inx/jsres/login";
    }

    /**
     * @Department：研发部
     * @Description 角色切换
     * @Author fengxf
     * @Date 2024/2/4
     */
    @RequestMapping(value="/changeRole")
    public String changeRole(String roleFlow){
        return "redirect:" + getRoleUrl(roleFlow);
    }
}
