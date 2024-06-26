package com.pinde.sci.ctrl.inx;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.portal.IPortalColumnManageBiz;
import com.pinde.sci.biz.portal.IPortalInfoManageBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.enums.inx.InfoStatusEnum;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.sys.OperTypeEnum;
import com.pinde.sci.form.portal.PortalInfoForm;
import com.pinde.sci.model.mo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tiger
 */

@Controller
@RequestMapping("/inx/portal")
public class InxPortalController extends GeneralController {

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
    private IPortalInfoManageBiz infoManageBiz;
    @Autowired
    private IPortalColumnManageBiz columnManageBiz;

    /**
     * 首页
     *
     * @param column
     * @param model
     * @return
     */
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String index(InxColumn column, Model model,String loginErrorMessage) throws Exception {
        PortalInfoForm form = new PortalInfoForm();
        form.setInfoStatusId(InfoStatusEnum.Passed.getId());
        form.setColumnIdLike("LM01%");
        PageHelper.startPage(1, 10);
        List<PortalInfo> lm01List = infoManageBiz.getList(form,null);
        model.addAttribute("lm01List", lm01List);

        form.setColumnIdLike("LM02%");
        PageHelper.startPage(1, 10);
        List<PortalInfo> lm02List = infoManageBiz.getList(form,null);
        model.addAttribute("lm02List", lm02List);

        form.setColumnIdLike("LM03%");
        PageHelper.startPage(1, 10);
        List<PortalInfo> lm03List = infoManageBiz.getList(form,null);
        model.addAttribute("lm03List", lm03List);

        //新闻中心
        form.setColumnIdLike("LM07%");
        PageHelper.startPage(1, 10);
        List<PortalInfo> lm07List = infoManageBiz.getList(form,null);
        List<Map<String, String>> imgList = getImgListByInfo(lm07List);
        model.addAttribute("imgList", imgList);
        model.addAttribute("lm07List", lm07List);

        //理论研究
        form.setColumnIdLike("LM08%");
        PageHelper.startPage(1, 10);
        List<PortalInfo> lm08List = infoManageBiz.getList(form,null);
        model.addAttribute("lm08List", lm08List);

        if(StringUtil.isNotEmpty(loginErrorMessage)){
            loginErrorMessage = java.net.URLDecoder.decode(loginErrorMessage,"UTF-8");
            model.addAttribute("loginErrorMessage", loginErrorMessage);
        }

        //图片路径
//		String imageBaseUrl = this.infoManageBiz.getImageBaseUrl();
//		model.addAttribute("imageBaseUrl", imageBaseUrl);
        //数据统计表
        String userRoleFlow = InitConfig.getSysCfg("portals_user_role_flow");
        if(StringUtil.isNotBlank(userRoleFlow)){
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("userRoleFlow",userRoleFlow);
            List<Map<String,Object>> resultMapList = infoManageBiz.getStatistics(paramMap);
            model.addAttribute("resultMapList",resultMapList);
        }
        return "inx/portal/index";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String login() {
        return "redirect:/inx/portal";
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
            errorLoginPage = "inx/portal/index";
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
        return "inx/portal/login";
    }

    @RequestMapping("/files")
    public String contact(HttpServletRequest request) {

        return "inx/portal/files";
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
    public String loadInfoList(String columnId, Integer currentPage, HttpServletRequest request, Model model) {
        PortalInfoForm form = new PortalInfoForm();
        form.setInfoStatusId(InfoStatusEnum.Passed.getId());
        form.setColumnIdLike("%"+columnId+"%");
//        PageHelper.startPage(currentPage, getPageSize(request));
        List<PortalInfo> infoList = this.infoManageBiz.getList(form,null);
        PortalColumn column = columnManageBiz.getById(columnId);
//        List<Map<String,String>> fileList =getFileListByInfo(infoList);
//        List<Map<String,String>> fileList =null;
//        form.setColumnId(null);
//        form.setColumnIdLike("LM03%");
//        PageHelper.startPage(1, 8);
//        List<PortalInfo> lm03List = infoManageBiz.getList(form);

        model.addAttribute("column", column);
//        model.addAttribute("lm03List", lm03List);
        model.addAttribute("infoList", infoList);
//        model.addAttribute("fileList", fileList);

        if("LM0301".equals(columnId)){
            return "inx/portal/construction";
        }

        if("LM0601".equals(columnId)){//腾讯问卷
            SysCfg cfg = cfgBiz.read("qqwj_url");
            if(cfg!=null){
                model.addAttribute("cfg",cfg.getCfgValue());
            }
        }

        if("LM0602".equals(columnId)){//咨询建议
            PortalSuggest search = new PortalSuggest();
            search.setShowFlag("Y");
            List<PortalSuggest> suggestList = infoManageBiz.getSuggestList(search);
            model.addAttribute("suggestList",suggestList);
        }

        if("LM05".equals(columnId)){//下载中心
            List<PortalFile> fileList = this.infoManageBiz.getFileList(null);
            model.addAttribute("fileList",fileList);
        }

//        if(fileList != null && fileList.size() > 0){
//            return "inx/portal/files";
//        }
        return "inx/portal/notice-list";
    }

    private List<Map<String, String>> getFileListByInfo(List<PortalInfo> infoList) {
        if(infoList == null || infoList.size() == 0) return null;
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
        Map<String ,String> map = null;
        for (PortalInfo info : infoList){
            map = new HashMap<String,String>();
            String s = info.getInfoContent();
            if(StringUtil.isEmpty(s)) continue;
            String url = "";//用于保存需要提取的链接地址
            String title = "";//用于保存需要提取的标题
            String type = "";//用于保存需要提取的标题
            Pattern pattern = Pattern.compile("href=\"(.*)\"(.*)>(.*)</a>");//提取所需要的正则表达式
            Matcher matcher = pattern.matcher(s);
            if(matcher.find()){
                url = matcher.group(1);//获取url
                title = matcher.group(3);//获取标题
                if(title.endsWith(".pdf")){
                    type="pdf";
                }
                if(title.endsWith(".doc") || title.endsWith(".docx")){
                    type="word";
                }
                map.put("title",title);
                map.put("type",type);
                map.put("url",url);
                list.add(map);
            }
        }
        return list;
    }

    private List<Map<String, String>> getImgListByInfo(List<PortalInfo> infoList) {
        if(infoList == null || infoList.size() == 0) return null;
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
        Map<String ,String> map = null;
        String baseUrl = InitConfig.getSysCfg("upload_base_url") +File.separator + "titleImages"+ File.separator;
        int i = 0;
        for (PortalInfo info : infoList){
            map = new HashMap<String,String>();
            String url = info.getTitleImg();//用于保存需要提取的链接地址
            String recordFlow = info.getInfoFlow();
            String title = info.getInfoTitle();
            if(StringUtil.isNotEmpty(url)){
                i++;
                map.put("url",baseUrl+url);
                map.put("recordFlow",recordFlow);
                map.put("title",title);
                list.add(map);
            }
            if(i>4){
                break;
            }
        }
        return list;
    }

    @RequestMapping(value = "/loadInfo", method = {RequestMethod.GET})
    public String loadInfo(String infoFlow, Model model) {

        if (StringUtil.isNotBlank(infoFlow)) {
            PortalInfo info = infoManageBiz.getByFlow(infoFlow);
            model.addAttribute("info", info);
            //浏览量
            if (info != null) {
                Long viewNum = info.getViewNum();
                if (viewNum == null) {
                    viewNum = Long.valueOf(0);
                }
                viewNum++;
                PortalInfo update = new PortalInfo();
                update.setInfoFlow(infoFlow);
                update.setViewNum(viewNum);
                infoManageBiz.modifyPortalInfo(update);

                PortalInfoForm form = new PortalInfoForm();
                form.setInfoStatusId(InfoStatusEnum.Passed.getId());
                form.setColumnId("LM03");
                PageHelper.startPage(1, 6);
                List<PortalInfo> lm03List = infoManageBiz.getList(form,null);
                model.addAttribute("lm03List", lm03List);

                PortalColumn column = columnManageBiz.getById(info.getColumnId());
                model.addAttribute("column", column);

            }
            SysUser user = userBiz.readSysUser(info.getCreateUserFlow());
            model.addAttribute("user",user);
        }

        return "inx/portal/notice-details";
    }


    @RequestMapping("/searchInfoList")
    public String searchInfoList(String searchInfo, Integer currentPage, HttpServletRequest request, Model model) {
        PortalInfoForm form = new PortalInfoForm();
        form.setInfoStatusId(InfoStatusEnum.Passed.getId());
        form.setInfoTitle(searchInfo);
//        PageHelper.startPage(currentPage, getPageSize(request));
        List<PortalInfo> infoList = this.infoManageBiz.getList(form,null);

        List<Map<String,String>> fileList =getFileListByInfo(infoList);
        form.setColumnId(null);
        form.setColumnIdLike("LM03%");
        PageHelper.startPage(1, 8);
        List<PortalInfo> lm03List = infoManageBiz.getList(form,null);
        PortalColumn column = new PortalColumn();
        column.setColumnName("搜索");
        model.addAttribute("column", column);
        model.addAttribute("lm03List", lm03List);
        model.addAttribute("infoList", infoList);
        model.addAttribute("fileList", fileList);

        if(fileList != null && fileList.size() > 0){
            return "inx/portal/files";
        }
        return "inx/portal/notice-list";
    }

}
