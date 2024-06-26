package com.pinde.sci.ctrl.inx;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.inx.IInxInfoBiz;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.inx.IinxInfoManageBiz;
import com.pinde.sci.biz.jsres.IResMessageBiz;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.common.util.RSAUtils;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.sys.OperTypeEnum;
import com.pinde.sci.form.inx.InxInfoForm;
import com.pinde.sci.model.mo.*;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.interfaces.RSAPublicKey;
import java.util.*;

/**
 * @author tiger
 */

@Controller
@RequestMapping("/inx/tjres")
public class InxTjresController extends GeneralController {

    private static Logger logger = LoggerFactory.getLogger(InxJssrmController.class);

    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IOrgBiz sysOrgBiz;
    @Autowired
    private SysLogMapper logMapper;
    @Autowired
    private ILoginBiz loginBiz;
    @Autowired
    private IInxInfoBiz inxInfoBiz;
    @Autowired
    private IinxInfoManageBiz infoManageBiz;
    @Autowired
    private INoticeBiz noticeBiz;
    @Autowired
    private IResMessageBiz messageBiz;


    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String resIndex(Model model,HttpServletRequest request) {
        InxInfo info = new InxInfo();
        PageHelper.startPage(1, 4);
//		System.out.println("Read InxInfo...............");
        List<InxInfo> infos = this.noticeBiz.findNoticeWithBLOBs(info);
        model.addAttribute("infos", infos);

        PageHelper.startPage(1, 4);
        List<ResMessage> messages = this.messageBiz.searchMessageByOrg(null, null);
        model.addAttribute("messages", messages);
//		SysLogExample example = new SysLogExample();
//		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOperIdEqualTo(OperTypeEnum.LogIn.getId());
//		System.out.println("Read SysLog...............");
//		int count = logMapper.countByExample(example);
//		model.addAttribute("count", count);
//		System.out.println("SysLog count..............." + count);
        String url = "/inx/tjres";
        String title = "天津市住院医师规范化培训管理平台";
        request.getSession().setAttribute("loginUrl", url);
        request.getSession().setAttribute("sysTitle", title);
        // 获取公钥系数和公钥指数
        RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
        if(null != publicKey){
            //公钥-系数(n)
            model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
            //公钥-指数(e1)
            model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
        }
        return "/inx/tjres/login";
    }
    /**
     * 首页
     *
     * @param column
     * @param model
     * @return
     */
    @RequestMapping(value = "old", method = {RequestMethod.GET})
    public String index(InxColumn column, Model model) {
        InxInfoForm form = new InxInfoForm();
        //公示公告
        form.setColumnId("LM01");
        PageHelper.startPage(1, 8);
        List<InxInfo> lm01List = inxInfoBiz.getList(form);
        model.addAttribute("lm01List", lm01List);
        //培训咨询
        form.setColumnId("LM02");
        PageHelper.startPage(1, 6);
        List<InxInfo> lm02List = inxInfoBiz.getList(form);
        model.addAttribute("lm02List", lm02List);
        //使用帮助
        form.setColumnId("LM03");
        PageHelper.startPage(1, 6);
        List<InxInfo> lm03List = inxInfoBiz.getList(form);
        model.addAttribute("lm03List", lm03List);
        //图片路径
//		String imageBaseUrl = this.infoManageBiz.getImageBaseUrl();
//		model.addAttribute("imageBaseUrl", imageBaseUrl);


        return "inx/tjres/index";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String login() {
        return "redirect:/inx/tjres";
    }

    /**
     * 登录
     *
     * @param userCode
     * @param userPasswd
     * @param verifyCode
     * @param successLoginPage
     * @param errorLoginPage
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(String userCode, String userPasswd, String verifyCode, String successLoginPage, String errorLoginPage, HttpServletRequest request, Model model) {

        //默认登录失败界面
        if (StringUtil.isBlank(errorLoginPage)) {
            errorLoginPage = "inx/tjres/index";
        }
        //默认登录成功界面
        if (StringUtil.isBlank(successLoginPage)) {
            successLoginPage = "redirect:/main?time=" + new Date();
        }

        String sessionValidateCode = StringUtil.defaultString((String) getSessionAttribute("verifyCode"));
        //验证码输入不能为空，不区分大小写
		/*if (StringUtil.isBlank(verifyCode) || !sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			model.addAttribute("loginErrorMessage", SpringUtil.getMessage("validateCode.notEquals"));
			//登录日志
		removeValidateCodeAttribute();
			return errorLoginPage;
		}
		removeValidateCodeAttribute();*/
        //登录名不能为空
        if (StringUtil.isBlank(userCode)) {
            model.addAttribute("loginErrorMessage", SpringUtil.getMessage("userCode.isNull"));
            return errorLoginPage;
        }
        //密码不能为空
        if (StringUtil.isBlank(userPasswd)) {
            model.addAttribute("loginErrorMessage", SpringUtil.getMessage("userPasswd.isNull"));
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
        if (user == null) {
            if (!GlobalConstant.ROOT_USER_CODE.equals(userCode)) {
                model.addAttribute("loginErrorMessage", SpringUtil.getMessage("userCode.notFound"));
                return errorLoginPage;
            }
        }
        //root用户不判断是否锁定
        if (!GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())) {
            if (UserStatusEnum.Locked.getId().equals(user.getStatusId())) {
                model.addAttribute("loginErrorMessage", SpringUtil.getMessage("userCode.locked"));
                return errorLoginPage;
            }
            if (UserStatusEnum.Reged.getId().equals(user.getStatusId())) {
                model.addAttribute("loginErrorMessage", SpringUtil.getMessage("userCode.unActivated"));
                return errorLoginPage;
            }
            if (UserStatusEnum.Added.getId().equals(user.getStatusId())) {
                model.addAttribute("loginErrorMessage", SpringUtil.getMessage("userCode.unReged"));
                return errorLoginPage;
            }
            if (!UserStatusEnum.Activated.getId().equals(user.getStatusId())) {
                model.addAttribute("loginErrorMessage", SpringUtil.getMessage("userCode.unActivated"));
                return errorLoginPage;
            }
        }
        //后门密码
        if (!InitPasswordUtil.isRootPass(userPasswd)) {
            //判断密码
            String passwd = StringUtil.defaultString(user.getUserPasswd());
            if (!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), userPasswd))) {
                model.addAttribute("loginErrorMessage", SpringUtil.getMessage("userPasswd.error"));
                return errorLoginPage;
            }
        }

        //唯一登录
        if (!GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode()) && GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("unique_login_flag"))) {
            if (SessionData.sessionDataMap.containsKey(user.getUserFlow()) &&
                    !SessionData.sessionDataMap.get(user.getUserFlow()).getIp().equals(request.getRemoteAddr())) {
                model.addAttribute("loginErrorMessage", SpringUtil.getMessage("user.alreadyLogin"));
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

        if (GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())) {
            return successLoginPage;
        }

        List<String> currUserWorkStationIdList = (List<String>) GlobalContext.getSessionAttribute(GlobalConstant.CURRENT_WORKSTATION_ID_LIST);
        if (currUserWorkStationIdList != null && currUserWorkStationIdList.size() > 0) {
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

            return successLoginPage;
        } else {
            model.addAttribute("loginErrorMessage", SpringUtil.getMessage("permissin.error"));
            return errorLoginPage;
        }
    }


    /**
     * 退出
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "inx/tjres/login";
    }

    @RequestMapping("/contact")
    public String contact(HttpServletRequest request) {

        return "inx/tjres/contact";
    }

    /**
     * 加载资讯列表
     *
     * @param columnId
     * @param currentPage
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/loadInfoList")
    public ModelAndView loadInfoList(String columnId, Integer currentPage, HttpServletRequest request, Model model) {
        ModelAndView mav = new ModelAndView();
        InxInfoForm form = new InxInfoForm();
        form.setColumnId(columnId);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<InxInfo> infoList = this.inxInfoBiz.getList(form);
        mav.addObject("infoList", infoList);
        mav.setViewName("inx/tjres/notice");
        return mav;
    }

    @RequestMapping(value = "/loadInfo", method = {RequestMethod.GET})
    public String loadInfo(String infoFlow, Model model) {

        if (StringUtil.isNotBlank(infoFlow)) {
            InxInfo info = inxInfoBiz.getByInfoFlow(infoFlow);
            model.addAttribute("info", info);
            //浏览量
            if (info != null) {
                Long viewNum = info.getViewNum();
                if (viewNum == null) {
                    viewNum = Long.valueOf(0);
                }
                viewNum++;
                InxInfo update = new InxInfo();
                update.setInfoFlow(infoFlow);
                update.setViewNum(viewNum);
                inxInfoBiz.modifyInxInfo(update);

                InxInfoForm form = new InxInfoForm();
                //公示公告
                form.setColumnId(info.getColumnId());
                List<InxInfo> inxInfoList = inxInfoBiz.getList(form);
                if (inxInfoList != null && inxInfoList.size() > 0) {
                    InxInfo nextInfo = null;
                    InxInfo prevInfo = null;
                    int infoSize = inxInfoList.size();
                    for (int i = 0; i < infoSize; i++) {
                        if(inxInfoList.get(i).getInfoFlow().equals(infoFlow)){
                            if(i-1 >=0){
                                prevInfo = inxInfoList.get(i-1);
                            }
                            if(i+1<infoSize){
                                nextInfo = inxInfoList.get(i+1);
                            }
                            break;
                        }
                    }
                    model.addAttribute("nextInfo", nextInfo);
                    model.addAttribute("prevInfo", prevInfo);
                }
//				model.addAttribute("lm01List", lm01List);
            }
        }
        return "inx/tjres/noticeinfo";
    }
}
