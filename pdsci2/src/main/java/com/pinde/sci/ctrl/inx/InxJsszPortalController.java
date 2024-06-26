package com.pinde.sci.ctrl.inx;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.portal.IPortalColumnManageBiz;
import com.pinde.sci.biz.portal.IPortalInfoManageBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tiger
 */

@Controller
@RequestMapping("/inx/jsszPortal")
public class InxJsszPortalController extends GeneralController {

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
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private IRoleBiz roleBiz;

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
        form.setColumnIdLike("LM09%");
        PageHelper.startPage(1, 8);
        List<PortalInfo> lm09List = infoManageBiz.getList(form,null);
        Map<String,Object> lm09Map = new HashMap<>();
        int i = 0;
        if(lm09List!=null&&lm09List.size()>0){
            for(PortalInfo info:lm09List){
                lm09Map.put(String.valueOf(i),info);
                i++;
            }
        }
        model.addAttribute("lm09Map", lm09Map);
        model.addAttribute("lm09List", lm09List);

        form.setColumnIdLike("LM10%");
        PageHelper.startPage(1, 5);
        List<PortalInfo> lm10List = infoManageBiz.getList(form,null);
        List<Map<String, String>> imgList = getImgListByInfo(lm10List);
        model.addAttribute("imgList", imgList);
        model.addAttribute("lm10List", lm10List);

        form.setColumnIdLike("LM11%");
        PageHelper.startPage(1, 6);
        List<PortalInfo> lm11List = infoManageBiz.getList(form,null);
        model.addAttribute("lm11List", lm11List);

        form.setColumnIdLike("LM12%");
        PageHelper.startPage(1, 6);
        List<PortalInfo> lm12List = infoManageBiz.getList(form,null);
        model.addAttribute("lm12List", lm12List);

        form.setColumnIdLike("LM13%");
        PageHelper.startPage(1, 6);
        List<PortalInfo> lm13List = infoManageBiz.getList(form,null);
        model.addAttribute("lm13List", lm13List);

        form.setColumnIdLike("LM14%");
        PageHelper.startPage(1, 6);
        List<PortalInfo> lm14List = infoManageBiz.getList(form,null);
        model.addAttribute("lm14List", lm14List);

        PageHelper.startPage(1, 6);
        List<PortalFile> fileList = this.infoManageBiz.getFileList(null);
        model.addAttribute("fileList",fileList);

        PageHelper.startPage(1, 6);
        List<JsszportalCommunicationMain> communicationMainList = infoManageBiz.searchCommunicationMain(null,null);
        model.addAttribute("communicationMainList",communicationMainList);

        if(StringUtil.isNotEmpty(loginErrorMessage)){
            loginErrorMessage = java.net.URLDecoder.decode(loginErrorMessage,"UTF-8");
            model.addAttribute("loginErrorMessage", loginErrorMessage);
        }

        return "inx/jsszPortal/index";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String login() {
        return "redirect:/inx/jsszPortal";
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
    public String login(String userCode, String userPasswd, String verifyCode, String successLoginPage,String type,
                        String errorLoginPage, HttpServletRequest request, Model model) {

        //默认登录失败界面
        if (StringUtil.isBlank(errorLoginPage)) {
            errorLoginPage = "redirect:/inx/jsszPortal";
        }
        //默认登录成功界面
        if (StringUtil.isBlank(successLoginPage)) {
            successLoginPage = "redirect:/inx/jsszPortal";
        }

        String sessionValidateCode = StringUtil.defaultString((String) getSessionAttribute("verifyCode"));
        //验证码输入不能为空，不区分大小写
        if (StringUtil.isBlank(verifyCode) || !sessionValidateCode.equalsIgnoreCase(verifyCode)) {
            model.addAttribute("loginErrorMessage","验证码不正确");
            removeValidateCodeAttribute();
            return errorLoginPage;
        }
        removeValidateCodeAttribute();
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

        SysUserRole userRole = new SysUserRole();
        userRole.setUserFlow(user.getUserFlow());
        userRole.setWsId(GlobalConstant.PORTALS_WS_ID);
        List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
        if(!GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())){
            if(userRoleList == null || userRoleList.size() == 0){
                model.addAttribute("loginErrorMessage", "角色未赋权！");
                return errorLoginPage;
            }else{
                String portals_patient_role_flow = InitConfig.getSysCfg("portals_patient_role_flow");
                String portals_doctor_role_flow = InitConfig.getSysCfg("portals_doctor_role_flow");
                SysRole role = roleBiz.read(userRoleList.get(0).getRoleFlow());
                //判断是否为管理员
                boolean glyFlag = true;
                if(portals_patient_role_flow.equals(role.getRoleFlow()) || portals_doctor_role_flow.equals(role.getRoleFlow())){
                    glyFlag = false;
                }
                model.addAttribute("glyFlag", glyFlag);
                //设置当前角色
                setSessionAttribute("currentRole",role.getRoleFlow());
            }
        }

        //加载系统权限
        loginBiz.loadSysRole(user.getUserFlow());

        //设置当前用户
        setSessionAttribute(GlobalConstant.CURRENT_USER, user);


        if (GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())) {
            return  "redirect:/main?time=" + new Date();
        }

//        List<String> currUserWorkStationIdList = (List<String>) GlobalContext.getSessionAttribute(GlobalConstant.CURRENT_WORKSTATION_ID_LIST);
//        if (currUserWorkStationIdList != null && currUserWorkStationIdList.size() > 0) {
            //在线用户功能使用
//            SessionData sessionData = new SessionData();
//            sessionData.setSysUser(user);
//            sessionData.setIp(request.getRemoteAddr());
//            long now = System.currentTimeMillis();
//            String loginTime = new java.sql.Date(now) + "&nbsp;" + new java.sql.Time(now);
//            sessionData.setLoginTime(loginTime);
//            setSessionAttribute(SessionData.SESSIONDATAID, sessionData);

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
//        } else {
//            model.addAttribute("loginErrorMessage", SpringUtil.getMessage("permissin.error"));
//            return errorLoginPage;
//        }

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
        form.setColumnId(columnId);
//        PageHelper.startPage(currentPage, getPageSize(request));
        List<PortalInfo> infoList = this.infoManageBiz.getList(form,null);
        PortalColumn column = columnManageBiz.getById(columnId);
//        List<Map<String,String>> fileList =getFileListByInfo(infoList);
//        List<Map<String,String>> fileList =null;

        model.addAttribute("column", column);
        model.addAttribute("infoList", infoList);

        if("LM14".equals(columnId)){//下载中心
            List<PortalFile> fileList = this.infoManageBiz.getFileList(null);
            model.addAttribute("fileList",fileList);
        }
        return "inx/jsszPortal/newslist";
    }

    //医患交流主表列表
    @RequestMapping("/communicationMainList")
    public String communicationMainList(JsszportalCommunicationMain communicationMain,Integer currentPage,HttpServletRequest request,
                              String order,Model model){
        PageHelper.startPage(currentPage, getPageSize(request));
        List<JsszportalCommunicationMain> communicationMainList = infoManageBiz.searchCommunicationMain(communicationMain,order);
        model.addAttribute("communicationMainList",communicationMainList);
        return "inx/jsszPortal/list";
    }

    //医患交流详情列表
    @RequestMapping("/communicationDetailList")
    public String communicationDetailList(String recordFlow,Integer currentPage,HttpServletRequest request,Model model){
        JsszportalCommunicationMain communicationMain = infoManageBiz.readCommunicationMain(recordFlow);
        model.addAttribute("communicationMain",communicationMain);
        SysUser mainUser = userBiz.readSysUser(communicationMain.getUploadUserFlow());
        model.addAttribute("mainUser",mainUser);

        JsszportalCommunicationRe search = new JsszportalCommunicationRe();
        search.setMainFlow(recordFlow);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<JsszportalCommunicationRe> communicationReList = infoManageBiz.searchCommunicationRe(search);
        model.addAttribute("communicationReList",communicationReList);
        Map<String,Object> userMap = new HashMap<>();
        if(communicationReList!=null&&communicationReList.size()>0){
            for(JsszportalCommunicationRe communicationRe:communicationReList){
                String userFlow = communicationRe.getUploadUserFlow();
                userMap.put(communicationRe.getRecordFlow(),userBiz.readSysUser(userFlow));
            }
        }
        model.addAttribute("userMap",userMap);
        return "inx/jsszPortal/viewpost";
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
            if(StringUtil.isNotEmpty(url)){
                i++;
                map.put("url",baseUrl+url);
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
            SysUser createUser = userBiz.readSysUser(info.getCreateUserFlow());
            model.addAttribute("createUser", createUser);
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
        }

        return "inx/jsszPortal/detail";
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

    /**
     * 显示新增医患交流界面
     * @return
     */
    @RequestMapping(value="/addCommunication")
    public String addCommunication(Model model){
        return "inx/jsszPortal/editCommunication";
    }

    /**
     * 保存医患交流主表
     * @return
     */
    @RequestMapping(value="/saveCommunicationMain")
    public @ResponseBody
    int saveCommunicationMain(JsszportalCommunicationMain communicationMain){
        if(StringUtil.isBlank(communicationMain.getRecordFlow())){//新增
            SysUser user = GlobalContext.getCurrentUser();
            if(user!=null){
                communicationMain.setUploadUserFlow(user.getUserFlow());
                communicationMain.setUploadUserName(user.getUserName());
            }
            String date = DateUtil.getCurrDate();
            communicationMain.setUploadTime(date);
            communicationMain.setReplayTimes("0");
        }
        return infoManageBiz.editCommunicationMain(communicationMain);
    }

    //保存医患交流子表
    @RequestMapping(value="/saveCommunicationRe")
    public @ResponseBody
    int saveCommunicationRe(JsszportalCommunicationRe communicationRe){
        if(StringUtil.isBlank(communicationRe.getRecordFlow())){//新增
            SysUser user = GlobalContext.getCurrentUser();
            if(user!=null){
                communicationRe.setUploadUserFlow(user.getUserFlow());
                communicationRe.setUploadUserName(user.getUserName());
            }
            String date = DateUtil.getCurrDateTime2();
            communicationRe.setUploadTime(date);
        }
        int result = infoManageBiz.editCommunicationRe(communicationRe);
        if(1==result){
            String mainFlow = communicationRe.getMainFlow();
            JsszportalCommunicationMain communicationMain = infoManageBiz.readCommunicationMain(mainFlow);
            String replayTimes = communicationMain.getReplayTimes();
            communicationMain.setReplayTimes(String.valueOf(Integer.parseInt(replayTimes)+1));
            communicationMain.setNewestReplayTime(communicationRe.getUploadTime());
            infoManageBiz.editCommunicationMain(communicationMain);
        }
        return 1;
    }

    //删除医患交流子表
    @RequestMapping(value="/delCommunicationRe")
    public @ResponseBody
    int delCommunicationRe(String mainFlow,String recordFlow){
        JsszportalCommunicationRe communicationRe = new JsszportalCommunicationRe();
        communicationRe.setRecordStatus("N");
        communicationRe.setRecordFlow(recordFlow);
        int result = infoManageBiz.editCommunicationRe(communicationRe);
        if(1==result){
            JsszportalCommunicationMain communicationMain = infoManageBiz.readCommunicationMain(mainFlow);
            String replayTimes = communicationMain.getReplayTimes();
            communicationMain.setReplayTimes(String.valueOf(Integer.parseInt(replayTimes)-1));
            communicationMain.setNewestReplayTime(communicationRe.getUploadTime());
            infoManageBiz.editCommunicationMain(communicationMain);
        }
        return 1;
    }
}
