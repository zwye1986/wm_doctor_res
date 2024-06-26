package com.pinde.sci.ctrl.inx;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.inx.IInxBiz;
import com.pinde.sci.biz.inx.IInxInfoBiz;
import com.pinde.sci.biz.inx.IinxColumnManageBiz;
import com.pinde.sci.biz.inx.IinxInfoManageBiz;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.pub.impl.PubResumeBizImpl;
import com.pinde.sci.biz.res.IResRecBiz;
import com.pinde.sci.biz.res.impl.ResDoctorBizImpl;
import com.pinde.sci.biz.sch.ISchRotationDeptBiz;
import com.pinde.sci.biz.srm.IProjSearchBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.inx.TempExtMapper;
import com.pinde.sci.enums.jszy.JszyBaseStatusEnum;
import com.pinde.sci.enums.jszy.JszyResDoctorAuditStatusEnum;
import com.pinde.sci.enums.jszy.JszyResTrainYearEnum;
import com.pinde.sci.enums.jszy.JszyTrainCategoryEnum;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.res.ResBaseStatusEnum;
import com.pinde.sci.enums.res.ResRecTypeEnum;
import com.pinde.sci.enums.sys.OperTypeEnum;
import com.pinde.sci.form.inx.InxInfoForm;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.form.res.ResAssessCfgItemForm;
import com.pinde.sci.form.res.ResAssessCfgTitleForm;
import com.pinde.sci.form.srm.SrmExportExcel;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.HbResResumeExtInfoForm;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author tiger
 */

@Controller
@RequestMapping("/inx/jssrm")
public class InxJssrmController extends GeneralController {

    private static Logger logger = LoggerFactory.getLogger(InxJssrmController.class);
    @Autowired
    private IInxInfoBiz inxInfoBiz;
    @Autowired
    private IinxColumnManageBiz columnBiz;
    @Autowired
    private IinxInfoManageBiz infoManageBiz;
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
    private IInxBiz inxBiz;
    @Autowired
    private ILoginBiz loginBiz;
    @Autowired
    private IProjSearchBiz projSeeBiz;

    /**
     * 首页
     *
     * @param column
     * @param model
     * @return
     */
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String index(InxColumn column, Model model) {
        return "inx/jssrm/index";
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
            errorLoginPage = "login";
        }
        //默认登录成功界面
        if (StringUtil.isBlank(successLoginPage)) {
            successLoginPage = "redirect:/main?time=" + new Date();
        }

        String sessionValidateCode = StringUtil.defaultString((String) getSessionAttribute("verifyCode"));
        //验证码输入不能为空，不区分大小写
        if (StringUtil.isBlank(verifyCode) || !sessionValidateCode.equalsIgnoreCase(verifyCode)) {
            model.addAttribute("loginErrorMessage", SpringUtil.getMessage("validateCode.notEquals"));
            //登录日志
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
     * 加载首页资讯列表
     *
     * @param columnId
     * @param jsp
     * @param model
     * @param endIndex
     * @return
     */
    @RequestMapping(value = "/queryData", method = {RequestMethod.GET})
    public String queryData(String columnId, String jsp, String endIndex, String isWithBlobs, String imgUrl, Model model) {
        InxInfoForm form = new InxInfoForm();
        form.setColumnId(columnId);
        if (GlobalConstant.FLAG_Y.equals(isWithBlobs)) {
            form.setIsWithBlobs(isWithBlobs);
        }
        PageHelper.startPage(1, Integer.parseInt(endIndex));
        List<InxInfo> infoList = inxInfoBiz.getList(form);
        model.addAttribute("infoList", infoList);
        if (GlobalConstant.FLAG_Y.equals(imgUrl)) {
            //图片路径
            String imageBaseUrl = this.infoManageBiz.getImageBaseUrl();
            model.addAttribute("imageBaseUrl", imageBaseUrl);
        }
        return jsp;
    }

    /**
     * 加载新闻图片
     *
     * @param columnId
     * @param jsp
     * @param model
     * @param endIndex
     * @return
     */
    @RequestMapping(value = "/queryImgNews", method = {RequestMethod.GET})
    public String queryImgNews(String columnId, String endIndex, String jsp, Model model) {
        String imageBaseUrl = this.infoManageBiz.getImageBaseUrl();//图片url根路径
        model.addAttribute("imageBaseUrl", imageBaseUrl);
        InxInfoForm form = new InxInfoForm();
        form.setColumnId(columnId);
        form.setHasImage(GlobalConstant.FLAG_Y);
        PageHelper.startPage(1, Integer.parseInt(endIndex));
        List<InxInfo> imgNewsList = inxInfoBiz.getList(form);
        model.addAttribute("imgNewsList", imgNewsList);
        return jsp;
    }

    /**
     * 新闻中心
     *
     * @return
     */
    @RequestMapping(value = "/queryByColumnId", method = {RequestMethod.GET})
    public ModelAndView queryByColumnId() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("inx/jssrm/news");
        return mav;
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
    @RequestMapping(value = "/loadInfoList", method = {RequestMethod.GET})
    public ModelAndView loadInfoList(String columnId, String isWithBlobs, Integer currentPage, HttpServletRequest request, Model model) {
        ModelAndView mav = new ModelAndView();
        InxInfoForm form = new InxInfoForm();
        form.setColumnId(columnId);
        if (GlobalConstant.FLAG_Y.equals(isWithBlobs)) {
            form.setIsWithBlobs(GlobalConstant.FLAG_Y);
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<InxInfo> infoList = this.inxInfoBiz.getList(form);
        mav.addObject("infoList", infoList);
        mav.setViewName("inx/jssrm/infoList");
        return mav;
    }

    /**
     * 查看资讯
     *
     * @param columnId
     * @param model
     * @return
     */
    @RequestMapping(value = "/getByInfoFlow", method = {RequestMethod.GET})
    public String getByInfoFlow(String columnId, Model model) {
        //查询全部资讯（==>获取下一条）
        if (StringUtil.isNotBlank(columnId)) {
            InxInfoForm form = new InxInfoForm();
            form.setColumnId(columnId);
            List<InxInfo> infoList = this.inxInfoBiz.getList(form);
            model.addAttribute("infoList", infoList);
        }
        return "inx/jssrm/news_child";
    }


    /**
     * 加载一条资讯
     *
     * @param infoFlow
     * @param model
     * @return
     */
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
            }
        }
        return "inx/jssrm/info";
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
        return "inx/jssrm/index";
    }

    /**
     * 注册页面
     *
     * @return
     */
    @RequestMapping(value = "/register", method = {RequestMethod.GET})
    public String register() {
        return "inx/jssrm/register";
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
            return GlobalConstant.USER_EMAIL_REPETE;
        }
        user = userBiz.findByUserCode(userEmail);
        if (user != null) {
            return GlobalConstant.USER_EMAIL_REPETE;
        }
        return null;
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
            return "inx/jssrm/register";
        }
        //是否已注册
        String userEmail = registerUser.getUserEmail().trim();
        SysUser user = userBiz.findByUserEmail(userEmail);
        if (user != null) {
            model.addAttribute("errorMsg", GlobalConstant.USER_EMAIL_REPETE);
            return "inx/jssrm/register";
        }
        user = userBiz.findByUserCode(userEmail);
        if (user != null) {
            model.addAttribute("errorMsg", GlobalConstant.USER_EMAIL_REPETE);
            return "inx/jssrm/register";
        }
        this.inxBiz.saveRegistUser(registerUser);
        model.addAttribute("activationCode", registerUser.getUserFlow());
        return "redirect:/inx/jssrm/sendEmail";
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
        inxBiz.sendEmail(user.getUserFlow(), user.getUserEmail());

        model.addAttribute("activationCode", user.getUserFlow());
        model.addAttribute("userEmail", user.getUserEmail());
        return "inx/jssrm/sendEmail";
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
                if (GlobalConstant.ZERO_LINE != result) {
                    return GlobalConstant.FLAG_Y;
                }
            }
        }
        return GlobalConstant.FLAG_N;
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
//		userRole.setWsId(GlobalConstant.RES_WS_ID);
//		List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
//		if(!userRoleList.isEmpty()){
//			userRole = userRoleList.get(0);
//		}
//		SysRole role = roleBiz.read(userRole.getRoleFlow()); 
//		if(role!=null){
//			setSessionAttribute(GlobalConstant.CURRENT_USER, user);	
//			return "redirect:"+getRoleUrl(role.getRoleFlow());
//		}
        return "redirect:/inx/jssrm/activatesucc";
    }

    /**
     * 激活成功
     *
     * @param request
     * @return
     */
    @RequestMapping("/activatesucc")
    public String activatesucc(HttpServletRequest request) {
        return "inx/jssrm/activatesucc";
    }

    @RequestMapping("/updateXmlForSrmForm20160612Temp")
    @ResponseBody
    public Object updateXmlForSrmForm20160612Temp() throws Exception {//修改科研表单问题数据
        //http://localhost:8080/pdsci/inx/jssrm/updateXmlForSrmForm20160612Temp
//		http://wskj.jswst.gov.cn/pdsci/inx/jssrm/updateXmlForSrmForm20160612Temp
        //jsswst.yxzdrc
        String result = "结果：";
        PubProjMapper pubProjMapper = SpringUtil.getBean(PubProjMapper.class);
        PubProjRecMapper pubProjRecMapper = SpringUtil.getBean(PubProjRecMapper.class);
        PubProjExample example = new PubProjExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProjTypeIdEqualTo("jsswst.yxzdrc");
        List<PubProj> projs = pubProjMapper.selectByExample(example);
        if (projs != null && !projs.isEmpty()) {
            List<String> projFlows = new ArrayList<String>();
            for (PubProj pp : projs) {
                projFlows.add(pp.getProjFlow());
            }
            PubProjRecExample recExample = new PubProjRecExample();
            recExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProjFlowIn(projFlows);
            List<PubProjRec> pprs = pubProjRecMapper.selectByExampleWithBLOBs(recExample);
            for (PubProjRec ppr : pprs) {
//				if("6b3d0b8b9394421096778663d4ffdc59".equals(ppr.getRecFlow())) {
                boolean toUpdate = false;
                String content = ppr.getRecContent();

//					if(null != content){
//						content = content.replace("<step5><item name=\"main_name\"", "<step5><item name=\"name\"");
//						content = content.replace("<step5><item name=\"main_birthday\"", "<step5><item name=\"birthday\"");
//
//						content = content.replace("step2><item name=\"name\"", "step2><item name=\"main_name\"");
//						content = content.replace("step2><item name=\"birthday\"", "step2><item name=\"main_birthday\"");
//
//						ppr.setRecContent(content);
//						pubProjRecMapper.updateByPrimaryKeyWithBLOBs(ppr);
//
//						result += ("<br/><br/>" + ppr.getRecFlow());
//					}


                Document dom = DocumentHelper.parseText(content);
                Element root = dom.getRootElement();
                Element step2 = root.element("step5");
                if (null != step2) {
                    Element nameItem = (Element) step2.selectSingleNode("//item[@name='main_name']");
                    if (nameItem != null) {
                        Attribute nameAttr = nameItem.attribute("name");
                        nameAttr.setValue("name");
                        toUpdate = true;
                    }
                    Element birthItem = (Element) step2.selectSingleNode("//item[@name='main_birthday']");
                    if (birthItem != null) {
                        Attribute birthAttr = birthItem.attribute("name");
                        birthAttr.setValue("birthday");
                        toUpdate = true;
                    }
                    if (toUpdate) {
                        ppr.setRecContent(dom.asXML());
                        pubProjRecMapper.updateByPrimaryKeyWithBLOBs(ppr);
                        result += ("<br/><br/>" + ppr.getRecFlow());
                        result += ("<br/>" + "s==" + step2.getName() + "；" + nameItem.asXML() + "；；" + birthItem.asXML());
                    }
                }
            }
//			}
        }
        return result;
    }

    @RequestMapping("/updateXmlForUserResumeForm20161216Temp")
    @ResponseBody
    public Object updateXmlForUserResumeForm20161216Temp() {//修改科研表单问题数据
        String result = "结果：";

        TempExtMapper userResumeExtMapper = SpringUtil.getBean(TempExtMapper.class);
        PubResumeBizImpl userResumeBiz = SpringUtil.getBean(PubResumeBizImpl.class);
        PubUserResumeMapper userResumpMapper = SpringUtil.getBean(PubUserResumeMapper.class);
        ResDoctorBizImpl resDoctorBiz = SpringUtil.getBean(ResDoctorBizImpl.class);
        List<PubUserResume> list = userResumeExtMapper.selectUserResume();
        if (list != null && list.size() > 0) {
            result += "<br/> 一共处理：" + list.size() + "条信息";
            for (PubUserResume pubUserResume : list) {
                //大字段信息
                if (pubUserResume != null) {
                    ResDoctor doctor = resDoctorBiz.findByFlow(pubUserResume.getUserFlow());
                    String xmlContent = pubUserResume.getUserResume();
                    if (StringUtil.isNotBlank(xmlContent)) {
                        //xml转换成JavaBean
                        HbResResumeExtInfoForm extInfo = null;
                        try {
                            extInfo = userResumeBiz.converyToJavaBean(xmlContent, HbResResumeExtInfoForm.class);
                        } catch (DocumentException e) {
                            extInfo = null;
                        }
                        if (extInfo != null) {

                            BaseUserResumeExtInfoForm userInfoForm = new BaseUserResumeExtInfoForm();
                            userInfoForm.setUserFlow(extInfo.getUserFlow());
                            userInfoForm.setUserName(extInfo.getUserName());
                            userInfoForm.setAge(extInfo.getAge());
                            //医疗卫生机构
                            String medicalInstitution = extInfo.getMedicalInstitution();
                            if (StringUtil.isNotBlank(medicalInstitution)) {
                                if (medicalInstitution.equals("医院"))
                                    userInfoForm.setMedicalHeaithOrgId("1");
                                if (medicalInstitution.equals("专业公共卫生机构"))
                                    userInfoForm.setMedicalHeaithOrgId("2");
                                if (medicalInstitution.equals("基层医疗卫生机构"))
                                    userInfoForm.setMedicalHeaithOrgId("3");
                            }
                            //基层医疗卫生机构类型
                            String basicmedicalInstitutionType = extInfo.getBasicmedicalInstitutionType();
                            if (StringUtil.isNotBlank(basicmedicalInstitutionType)) {
                                if (basicmedicalInstitutionType.equals("社区卫生服务中心(站)"))
                                    userInfoForm.setBasicHealthOrgId("1");
                                if (basicmedicalInstitutionType.equals("街道卫生院"))
                                    userInfoForm.setBasicHealthOrgId("2");
                                if (basicmedicalInstitutionType.equals("乡镇卫生院"))
                                    userInfoForm.setBasicHealthOrgId("3");
                                if (basicmedicalInstitutionType.equals("村卫生室"))
                                    userInfoForm.setBasicHealthOrgId("4");
                                if (basicmedicalInstitutionType.equals("门诊部"))
                                    userInfoForm.setBasicHealthOrgId("5");
                                if (basicmedicalInstitutionType.equals("诊所(医务室)"))
                                    userInfoForm.setBasicHealthOrgId("6");
                            }
                            //医院属性
                            String hospitalProperty = extInfo.getHospitalProperty();
                            if (StringUtil.isNotBlank(hospitalProperty)) {
                                if (hospitalProperty.equals("省级")) {
                                    userInfoForm.setHospitalAttrId("1");
                                    userInfoForm.setHospitalAttrName("省级");
                                }
                                if (hospitalProperty.equals("地市级")) {
                                    userInfoForm.setHospitalAttrId("2");
                                    userInfoForm.setHospitalAttrName("地市级");
                                }
                                if (hospitalProperty.equals("县级")) {
                                    userInfoForm.setHospitalAttrId("3");
                                    userInfoForm.setHospitalAttrName("县级");
                                }
                            }
                            //医院类别
                            String hospitalType = extInfo.getHospitalType();
                            if (StringUtil.isNotBlank(hospitalType)) {
                                if (hospitalType.equals("综合医院")) {
                                    userInfoForm.setHospitalCategoryId("1");
                                    userInfoForm.setHospitalCategoryName("综合医院");
                                }
                                if (hospitalType.equals("中医医院")) {
                                    userInfoForm.setHospitalCategoryId("2");
                                    userInfoForm.setHospitalCategoryName("中医医院");
                                }
                                if (hospitalType.equals("中西医结合医院")) {
                                    userInfoForm.setHospitalCategoryId("3");
                                    userInfoForm.setHospitalCategoryName("中西医结合医院");
                                }
                                if (hospitalType.equals("民族医院")) {
                                    userInfoForm.setHospitalCategoryId("4");
                                    userInfoForm.setHospitalCategoryName("民族医院");
                                }
                                if (hospitalType.equals("专科医院")) {
                                    userInfoForm.setHospitalCategoryId("5");
                                    userInfoForm.setHospitalCategoryName("专科医院");
                                }
                                if (hospitalType.equals("护理院")) {
                                    userInfoForm.setHospitalCategoryId("6");
                                    userInfoForm.setHospitalCategoryName("护理院");
                                }
                            }
                            //单位性质
                            String unitProperty = extInfo.getUnitProperty();
                            if (StringUtil.isNotBlank(unitProperty)) {
                                if (unitProperty.equals("一级")) {
                                    userInfoForm.setBaseAttributeId("5");
                                    userInfoForm.setBaseAttributeName("一级");
                                }
                                if (unitProperty.equals("二级")) {
                                    userInfoForm.setBaseAttributeId("6");
                                    userInfoForm.setBaseAttributeName("二级");
                                }
                                if (unitProperty.equals("三级")) {
                                    userInfoForm.setBaseAttributeId("7");
                                    userInfoForm.setBaseAttributeName("三级");
                                }
                                if (unitProperty.equals("三级乙等")) {
                                    userInfoForm.setBaseAttributeId("8");
                                    userInfoForm.setBaseAttributeName("三级乙等");
                                }
                                if (unitProperty.equals("三级甲等")) {
                                    userInfoForm.setBaseAttributeId("9");
                                    userInfoForm.setBaseAttributeName("三级甲等");
                                }
                            }
                            //本科毕业院校
                            userInfoForm.setGraduatedName(extInfo.getGraduatedCollegeName());
                            //本科毕业年份
                            userInfoForm.setGraduationTime(extInfo.getGraduatedCollegeYear());
                            //本科毕业专业
                            userInfoForm.setSpecialized(extInfo.getGraduatedCollegeMajor());
                            if (doctor != null) {
                                //学位
                                String degreeCategoryName = doctor.getDegreeCategoryName();
                                if (StringUtil.isNotBlank(degreeCategoryName)) {
                                    if (degreeCategoryName.equals("博士学位")) {
                                        userInfoForm.setDegreeId("01");
                                        userInfoForm.setDegreeName("博士");
                                    }
                                    if (degreeCategoryName.equals("硕士学位")) {
                                        userInfoForm.setDegreeId("02");
                                        userInfoForm.setDegreeName("硕士");
                                    }
                                    if (degreeCategoryName.equals("学士学位")) {
                                        userInfoForm.setDegreeId("03");
                                        userInfoForm.setDegreeName("学士");
                                    }
                                    if (degreeCategoryName.equals("无学位")) {
                                        userInfoForm.setDegreeId("04");
                                        userInfoForm.setDegreeName("暂无学位");
                                    }
                                }
                            }
                            //硕士毕业院校
                            userInfoForm.setMasterGraSchoolName(extInfo.getGraduatedMasterName());
                            //硕士毕业时间
                            userInfoForm.setMasterGraTime(extInfo.getGraduatedMasterYear());
                            //硕士毕业专业
                            userInfoForm.setMasterMajor(extInfo.getGraduatedMasterMajor());
                            //学位
                            String masterDegreeCategory = extInfo.getMasterDegreeCategory();
                            if (StringUtil.isNotBlank(masterDegreeCategory)) {
                                if (masterDegreeCategory.equals("博士学位")) {
                                    userInfoForm.setMasterDegreeId("01");
                                    userInfoForm.setMasterDegreeName("博士");
                                }
                                if (masterDegreeCategory.equals("硕士学位")) {
                                    userInfoForm.setMasterDegreeId("02");
                                    userInfoForm.setMasterDegreeName("硕士");
                                }
                                if (masterDegreeCategory.equals("学士学位")) {
                                    userInfoForm.setMasterDegreeId("03");
                                    userInfoForm.setMasterDegreeName("学士");
                                }
                                if (masterDegreeCategory.equals("无学位")) {
                                    userInfoForm.setMasterDegreeId("04");
                                    userInfoForm.setMasterDegreeName("暂无学位");
                                }
                            }
                            //学位类型
                            String masterDegreeType = extInfo.getMasterDegreeType();
                            if (StringUtil.isNotBlank(masterDegreeType)) {
                                if (masterDegreeType.equals("专业型")) {
                                    userInfoForm.setMasterDegreeTypeId("1");
                                    userInfoForm.setMasterDegreeTypeName("专业型");
                                }
                                if (masterDegreeType.equals("科学型")) {
                                    userInfoForm.setMasterDegreeTypeId("2");
                                    userInfoForm.setMasterDegreeTypeName("科学型");
                                }
                            }
                            //博士毕业院校
                            String graduatedDoctorName = extInfo.getGraduatedDoctorName();
                            userInfoForm.setDoctorGraSchoolName(graduatedDoctorName);
                            //博士毕业年份
                            userInfoForm.setDoctorGraTime(extInfo.getGraduatedDoctorYear());
                            //博士毕业专业
                            userInfoForm.setDoctorMajor(extInfo.getGraduatedDoctorMajor());
                            //学位
                            String doctorDegreeCategory = extInfo.getDoctorDegreeCategory();
                            if (StringUtil.isNotBlank(doctorDegreeCategory)) {
                                if (doctorDegreeCategory.equals("博士学位")) {
                                    userInfoForm.setDoctorDegreeId("01");
                                    userInfoForm.setDoctorDegreeName("博士");
                                }
                                if (doctorDegreeCategory.equals("硕士学位")) {
                                    userInfoForm.setDoctorDegreeId("02");
                                    userInfoForm.setDoctorDegreeName("硕士");
                                }
                                if (doctorDegreeCategory.equals("学士学位")) {
                                    userInfoForm.setDoctorDegreeId("03");
                                    userInfoForm.setDoctorDegreeName("学士");
                                }
                                if (doctorDegreeCategory.equals("无学位")) {
                                    userInfoForm.setDoctorDegreeId("04");
                                    userInfoForm.setDoctorDegreeName("暂无学位");
                                }
                            }

                            //学位类型
                            String doctorDegreeType = extInfo.getDoctorDegreeType();
                            if (StringUtil.isNotBlank(doctorDegreeType)) {
                                if (doctorDegreeType.equals("专业型")) {
                                    userInfoForm.setDoctorDegreeTypeId("1");
                                    userInfoForm.setDoctorDegreeTypeName("专业型");
                                }
                                if (doctorDegreeType.equals("科学型")) {
                                    userInfoForm.setDoctorDegreeTypeId("2");
                                    userInfoForm.setDoctorDegreeTypeName("科学型");
                                }
                            }

                            result += "old content:" + xmlContent;
                            xmlContent = JaxbUtil.convertToXml(userInfoForm);
                            result += "   new content:" + xmlContent + "<br/>";
                            pubUserResume.setUserResume(xmlContent);
                            GeneralMethod.setRecordInfo(pubUserResume, false);
                            userResumpMapper.updateByPrimaryKeyWithBLOBs(pubUserResume);
                        }

                    }
                }
            }
        }
        return result;
    }

    @RequestMapping("/updateXmlForResRecForm20170104Temp")
    @ResponseBody
    public Object updateXmlForResRecForm20170104Temp() throws DocumentException {//
        String result = "结果：";
        //带教老师评分
        ResRecMapper recMapper = SpringUtil.getBean(ResRecMapper.class);
        TempExtMapper userResumeExtMapper = SpringUtil.getBean(TempExtMapper.class);
        List<ResRec> list = userResumeExtMapper.getTeacherGradeList();
        if (list != null && list.size() > 0) {
            result += "<br/> 一共处理：" + list.size() + "条信息";
            for (ResRec rec : list) {
                boolean toUpdate = false;
                String content = rec.getRecContent();
                Document dom = DocumentHelper.parseText(content);
                Element root = dom.getRootElement();
                if (root != null) {
                    List<Element> items = root.elements("grade");
                    Element totalScore = root.element("totalScore");
                    double allScore = Double.valueOf(totalScore.getTextTrim());
                    double sum = 0;
                    if (items != null && items.size() > 0) {
                        for (Element e : items) {
                            String assessId = e.attributeValue("assessId");
                            Element score = e.element("score");//已经评好的分数
                            double sc = Double.valueOf(score.getTextTrim());
                            sum += sc;
                        }
                    }
                    if (sum != 0 && sum < allScore) {
                        toUpdate = true;
                        totalScore.setText(String.valueOf(sum));
                    }
                    result += "old content:" + rec.getRecContent() + "   new content" + dom.asXML();
                    result += " <br> ==========================================================================";
                }
                if (toUpdate) {
                    rec.setRecContent(dom.asXML());
                    recMapper.updateByPrimaryKeySelective(rec);
                    result += ("<br/><br/>" + rec.getOrgName() + "；");
                    result += ("<br/>");
                }
            }
        }

        result += ("<br/>" + "科室评分处理；");
        //科室评分
        List<ResRec> list2 = userResumeExtMapper.getDeptGradeList();
        if (list2 != null && list2.size() > 0) {
            result += "<br/> 一共处理：" + list2.size() + "条信息";
            for (ResRec rec : list2) {
                boolean toUpdate = false;
                String content = rec.getRecContent();
                Document dom = DocumentHelper.parseText(content);
                Element root = dom.getRootElement();
                if (root != null) {
                    List<Element> items = root.elements("grade");
                    Element totalScore = root.element("totalScore");
                    double allScore = Double.valueOf(totalScore.getTextTrim());
                    double sum = 0;
                    if (items != null && items.size() > 0) {
                        for (Element e : items) {
                            String assessId = e.attributeValue("assessId");
                            Element score = e.element("score");//已经评好的分数
                            double sc = Double.valueOf(score.getTextTrim());
                            sum += sc;
                        }
                    }
                    if (sum != 0 && sum < allScore) {
                        toUpdate = true;
                        totalScore.setText(String.valueOf(sum));
                    }
                    result += "old content:" + rec.getRecContent() + "   new content" + dom.asXML();
                    result += " <br> ==========================================================================";
                }
                if (toUpdate) {
                    rec.setRecContent(dom.asXML());
                    recMapper.updateByPrimaryKeySelective(rec);
                    result += ("<br/><br/>" + rec.getOrgName() + "；");
                }
            }
        }
        return result;
    }

    @RequestMapping("/updateXmlForResRecForm20161227Temp")
    @ResponseBody
    public Object updateXmlForResRecForm20161227Temp() throws DocumentException {//
        String result = "结果：";
        //带教老师评分
        ResRecMapper recMapper = SpringUtil.getBean(ResRecMapper.class);
        TempExtMapper userResumeExtMapper = SpringUtil.getBean(TempExtMapper.class);
        List<ResRec> list = userResumeExtMapper.getTeacherGradeList();
        //系统配置的分数
        ResAssessCfg search = new ResAssessCfg();
        search.setCfgCodeId("TeacherAssess");//带教
        ResAssessCfgExample racexample = new ResAssessCfgExample();
        com.pinde.sci.model.mo.ResAssessCfgExample.Criteria criteria = racexample.createCriteria()
                .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andCfgCodeIdEqualTo("TeacherAssess");
        ResAssessCfgMapper assessCfgMapper = SpringUtil.getBean(ResAssessCfgMapper.class);
        List<ResAssessCfg> assessCfgList = assessCfgMapper.selectByExampleWithBLOBs(racexample);
        Map<String, ResAssessCfgItemForm> itemFormList = null;
        if (assessCfgList != null && !assessCfgList.isEmpty()) {
            ResAssessCfg assessCfg = assessCfgList.get(0);
            Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
            String titleXpath = "//title";
            List<Element> titleElementList = dom.selectNodes(titleXpath);
            itemFormList = new HashMap<String, ResAssessCfgItemForm>();
            if (titleElementList != null && !titleElementList.isEmpty()) {
                List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
                for (Element te : titleElementList) {
                    ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
                    titleForm.setId(te.attributeValue("id"));
                    titleForm.setName(te.attributeValue("name"));
                    List<Element> itemElementList = te.elements("item");

                    if (itemElementList != null && !itemElementList.isEmpty()) {
                        for (Element ie : itemElementList) {
                            ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
                            itemForm.setId(ie.attributeValue("id"));
                            itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                            itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
                            itemFormList.put(ie.attributeValue("id"), itemForm);
                        }
                    }
                }
            }
        }
        if (list != null && list.size() > 0) {
            result += "<br/> 一共处理：" + list.size() + "条信息";
            for (ResRec rec : list) {
                boolean toUpdate = false;
                String content = rec.getRecContent();
                Document dom = DocumentHelper.parseText(content);
                Element root = dom.getRootElement();
                if (root != null) {
                    List<Element> items = root.elements("grade");
                    if (items != null && items.size() > 0) {
                        for (Element e : items) {
                            String assessId = e.attributeValue("assessId");
                            Element score = e.element("score");//已经评好的分数
                            ResAssessCfgItemForm itemForm = itemFormList.get(assessId);//标准
                            int biaozhun = 0;
                            if (itemForm != null) {
                                String sc = itemForm.getScore();
                                if (StringUtil.isNotBlank(sc)) {
                                    biaozhun = Integer.valueOf(sc);
                                }
                            }
                            if (score != null) {
                                String scoreStr = score.getText();
                                if (StringUtil.isNotBlank(scoreStr)) {
                                    double scorea = Double.valueOf(scoreStr);
                                    if (scorea > biaozhun && biaozhun != 0) {//评价的分是否大于标准分，如果是则评价分改为标准分
                                        score.setText(String.valueOf(biaozhun));
                                        toUpdate = true;
                                        result += "olditem:" + scoreStr + " newitem:" + biaozhun + "   <br>";
                                    }
                                }
                            }
                        }
                        result += "old content:" + rec.getRecContent() + "   new content" + dom.asXML();
                        result += " <br> ==========================================================================";

                    }
                }
                if (toUpdate) {
                    rec.setRecContent(dom.asXML());
                    recMapper.updateByPrimaryKeyWithBLOBs(rec);
                    result += ("<br/><br/>" + rec.getOrgName());
                    result += ("<br/>" + "；");
                }
            }
        }

        result += ("<br/>" + "科室评分处理；");
        //科室评分
        List<ResRec> list2 = userResumeExtMapper.getDeptGradeList();
        //系统配置的分数
        racexample = new ResAssessCfgExample();
        com.pinde.sci.model.mo.ResAssessCfgExample.Criteria criteria2 = racexample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andCfgCodeIdEqualTo("DeptAssess");
        assessCfgList = assessCfgMapper.selectByExampleWithBLOBs(racexample);
        itemFormList = null;
        if (assessCfgList != null && !assessCfgList.isEmpty()) {
            ResAssessCfg assessCfg = assessCfgList.get(0);
            Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
            String titleXpath = "//title";
            List<Element> titleElementList = dom.selectNodes(titleXpath);
            itemFormList = new HashMap<String, ResAssessCfgItemForm>();
            if (titleElementList != null && !titleElementList.isEmpty()) {
                List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
                for (Element te : titleElementList) {
                    ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
                    titleForm.setId(te.attributeValue("id"));
                    titleForm.setName(te.attributeValue("name"));
                    List<Element> itemElementList = te.elements("item");

                    if (itemElementList != null && !itemElementList.isEmpty()) {
                        for (Element ie : itemElementList) {
                            ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
                            itemForm.setId(ie.attributeValue("id"));
                            itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                            itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
                            itemFormList.put(ie.attributeValue("id"), itemForm);
                        }
                    }
                }
            }
        }
        if (list2 != null && list2.size() > 0) {
            result += "<br/> 一共处理：" + list2.size() + "条信息";
            for (ResRec rec : list2) {
                boolean toUpdate = false;
                String content = rec.getRecContent();
                Document dom = DocumentHelper.parseText(content);
                Element root = dom.getRootElement();
                if (root != null) {
                    List<Element> items = root.elements("grade");
                    if (items != null && items.size() > 0) {
                        for (Element e : items) {
                            String assessId = e.attributeValue("assessId");
                            Element score = e.element("score");//已经评好的分数
                            ResAssessCfgItemForm itemForm = itemFormList.get(assessId);//标准
                            int biaozhun = 0;
                            if (itemForm != null) {
                                String sc = itemForm.getScore();
                                if (StringUtil.isNotBlank(sc)) {
                                    biaozhun = Integer.valueOf(sc);
                                }
                            }
                            if (score != null) {
                                String scoreStr = score.getText();
                                if (StringUtil.isNotBlank(scoreStr)) {
                                    double scorea = Double.valueOf(scoreStr);
                                    if (scorea > biaozhun && biaozhun != 0) {//评价的分是否大于标准分，如果是则评价分改为标准分
                                        score.setText(String.valueOf(biaozhun));
                                        toUpdate = true;
                                        result += "olditem:" + scoreStr + " newitem:" + biaozhun + "   <br>";
                                    }
                                }
                            }
                        }
                        result += "old content:" + rec.getRecContent() + "   new content" + dom.asXML();
                        result += " <br> ==========================================================================";
                    }
                }
                if (toUpdate) {
                    rec.setRecContent(dom.asXML());
                    recMapper.updateByPrimaryKeyWithBLOBs(rec);
                    result += ("<br/><br/>" + rec.getOrgName());
                    result += ("<br/>" + "；");
                }
            }
        }
        return result;
    }

    /**
     * 更改科教强卫所有项目开始时间-结束时间为2016-01-01至2020-12-31(包括xml)
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateXmlForSrmForm20170728Temp")
    @ResponseBody
    public Object updateXmlForSrmForm20160628Temp() throws Exception {//修改科研表单问题数据
        //http://localhost:8080/pdsci2/inx/jssrm/updateXmlForSrmForm20170728Temp
//		http://wskj.jswst.gov.cn/pdsci/inx/jssrm/updateXmlForSrmForm20170728Temp
        //jsswst.yxzdrc
        String result = "结果：";
        PubProjMapper pubProjMapper = SpringUtil.getBean(PubProjMapper.class);
        PubProjRecMapper pubProjRecMapper = SpringUtil.getBean(PubProjRecMapper.class);
        PubProjExample example = new PubProjExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);//andProjCategoryIdEqualTo("qw");//PROJ_CATEGORY_ID
        List<PubProj> projs = pubProjMapper.selectByExampleWithBLOBs(example);


        int i = 0;
        List<String> projFlowList = new ArrayList<>();
        if (projs != null && !projs.isEmpty()) {
            for (PubProj ppr : projs) {
                boolean toUpdate = false;
                String content = ppr.getProjInfo();
                if (StringUtil.isNotBlank(content)) {
                    Document dom = DocumentHelper.parseText(content);
                    Element root = dom.getRootElement();
                    Element step1 = root.element("step1");
                    if (null != step1) {
                        Element projStartTime = (Element) step1.selectSingleNode("//item[@name='projStartTime']");
                        if (projStartTime != null) {
                            Element startTime = projStartTime.element("value");
                            if (null != startTime) {
                                startTime.setText("2017-07-01");
                            } else {
                                projStartTime.addElement("value").setText("2017-07-01");
                            }
                            toUpdate = true;
                        }
                        Element projEndTime = (Element) step1.selectSingleNode("//item[@name='projEndTime']");
                        if (projEndTime != null) {
                            Element endTime = projEndTime.element("value");
                            if (null != endTime) {
                                endTime.setText("2019-06-30");
                            } else {
                                projEndTime.addElement("value").setText("2019-06-30");
                            }
                            toUpdate = true;
                        }
                        if (toUpdate) {
                            projFlowList.add(ppr.getProjFlow());
                            i++;
                            ppr.setProjInfo(dom.asXML());
                            ppr.setProjStartTime("2017-07-01");
                            ppr.setProjEndTime("2019-06-30");
                            pubProjMapper.updateByPrimaryKeyWithBLOBs(ppr);
                            result += ("项目" + i + "<br/><br/>" + ppr.getProjName());
                            result += ("<br/>" + "s==" + step1.getName() + "；" + projStartTime.asXML() + "；；" + projEndTime.asXML());
                        }
                    }
                }
            }
        }
        if (!projFlowList.isEmpty()) {
            PubProjRecExample recExample = new PubProjRecExample();
            recExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProjFlowIn(projFlowList);
            List<PubProjRec> projRecList = pubProjRecMapper.selectByExampleWithBLOBs(recExample);
            if (projRecList != null && projRecList.size() > 0) {
                for (PubProjRec rec : projRecList) {
                    boolean toUpdate = false;
                    String content = rec.getRecContent();
                    if (StringUtil.isNotBlank(content)) {
                        Document dom = DocumentHelper.parseText(content);
                        Element root = dom.getRootElement();
                        Element step2 = root.element("step2");
                        if (null != step2) {
                            Element projStartTime = (Element) step2.selectSingleNode("//item[@name='projStartDate']");
                            if (projStartTime != null) {
                                Element startTime = projStartTime.element("value");
                                if (null != startTime) {
                                    startTime.setText("2017-07");
                                } else {
                                    projStartTime.addElement("value").setText("2017-07");
                                }
                                toUpdate = true;
                            }
                            Element projEndTime = (Element) step2.selectSingleNode("//item[@name='projEndDate']");
                            if (projEndTime != null) {
                                Element endTime = projEndTime.element("value");
                                if (null != endTime) {
                                    endTime.setText("2019-06");
                                } else {
                                    projEndTime.addElement("value").setText("2019-06");
                                }
                                toUpdate = true;
                            }
                            if (toUpdate) {
                                i++;
                                rec.setRecContent(dom.asXML());

                                pubProjRecMapper.updateByPrimaryKeyWithBLOBs(rec);
                                result += ("申报书" + i + "<br/><br/>" + rec.getProjFlow());
                                result += ("<br/>" + "s==" + step2.getName() + "；" + projStartTime.asXML() + "；；" + projEndTime.asXML());
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    @RequestMapping("/updateXmlFor20160830ResRecTemp")
    @ResponseBody
    public Object updateXmlFor20160830ResRecTemp() throws Exception {//系统内原有信息，退培类型统一改成协议退培。
        //http://localhost:8080/pdsci/inx/jssrm/updateXmlFor20160830ResRecTemp
        String result = "结果：";
        ResRecMapper recMapper = SpringUtil.getBean(ResRecMapper.class);
        ResRecExample example = new ResRecExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andRecTypeIdEqualTo(ResRecTypeEnum.ReturnTraining.getId());
        //需要被修改的数据
        List<ResRec> resRecs = recMapper.selectByExampleWithBLOBs(example);
        for (ResRec rec : resRecs) {
            boolean toUpdate = false;
            String content = rec.getRecContent();
            Document dom = DocumentHelper.parseText(content);
            Element root = dom.getRootElement();
            if (root != null) {
                Element policyNameElement = root.element("policyName");
                policyNameElement.addAttribute("id", "1");
                policyNameElement.setText("协议退培");
                toUpdate = true;
                result += "old content:" + rec.getRecContent().toString() + "   new content:" + dom.asXML().toString();
            }
            if (toUpdate) {
                rec.setRecContent(dom.asXML());
                recMapper.updateByPrimaryKeyWithBLOBs(rec);
                result += ("<br/>" + "；");
            }
        }
        return result;
    }

    @RequestMapping("/updateXmlForSrmForm20160705TeacherAssessTemp")
    @ResponseBody
    public Object updateXmlForSrmForm20160705TeacherAssessTemp() throws Exception {//修改将已经被评价过的带教老师以及科主任的分值按照百分制重新更新一下（原来是9分制）。
        //http://localhost:8080/pdsci/inx/jssrm/updateXmlForSrmForm20160705TeacherAssessTemp
//		http://wskj.jswst.gov.cn/pdsci/inx/jssrm/updateXmlForSrmForm20160612Temp
        //jsswst.yxzdrc
        String result = "结果：";
        ResRecMapper recMapper = SpringUtil.getBean(ResRecMapper.class);
        List<String> list = new ArrayList<>();
        list.add("TeacherGrade");
        //list.add("DeptGrade");
        ResRecExample example = new ResRecExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andRecTypeIdIn(list).andCreateTimeLike("2016%");
        //系统配置的分数
        ResAssessCfg search = new ResAssessCfg();
        search.setCfgCodeId("TeacherAssess");//带教
        ResAssessCfgExample racexample = new ResAssessCfgExample();
        com.pinde.sci.model.mo.ResAssessCfgExample.Criteria criteria = racexample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andCfgCodeIdEqualTo("TeacherAssess");
        ResAssessCfgMapper assessCfgMapper = SpringUtil.getBean(ResAssessCfgMapper.class);
        List<ResAssessCfg> assessCfgList = assessCfgMapper.selectByExampleWithBLOBs(racexample);
        Map<String, ResAssessCfgItemForm> itemFormList = null;
        if (assessCfgList != null && !assessCfgList.isEmpty()) {
            ResAssessCfg assessCfg = assessCfgList.get(0);
            Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
            String titleXpath = "//title";
            List<Element> titleElementList = dom.selectNodes(titleXpath);
            itemFormList = new HashMap<String, ResAssessCfgItemForm>();
            if (titleElementList != null && !titleElementList.isEmpty()) {
                List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
                for (Element te : titleElementList) {
                    ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
                    titleForm.setId(te.attributeValue("id"));
                    titleForm.setName(te.attributeValue("name"));
                    List<Element> itemElementList = te.elements("item");

                    if (itemElementList != null && !itemElementList.isEmpty()) {
                        for (Element ie : itemElementList) {
                            ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
                            itemForm.setId(ie.attributeValue("id"));
                            itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                            itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
                            itemFormList.put(ie.attributeValue("id"), itemForm);
                        }
                    }
                }
            }
        }
        //需要被修改的数据
        List<ResRec> resRecs = recMapper.selectByExampleWithBLOBs(example);
        for (ResRec rec : resRecs) {
            boolean toUpdate = false;
            String content = rec.getRecContent();
            Document dom = DocumentHelper.parseText(content);
            Element root = dom.getRootElement();
            if (root != null) {
                List<Element> items = root.elements("grade");
                if (items != null && items.size() > 0) {
                    int total = 0;
                    Element totalScore = root.element("totalScore");
                    String oldScor = totalScore.getText();
                    if (StringUtil.isNotBlank(oldScor) && Double.valueOf(oldScor) <= 81) {
                        result += "old:" + totalScore.getText() + " <br>";
                        for (Element e : items) {
                            String assessId = e.attributeValue("assessId");

                            Element score = e.element("score");//已经评好的分数
                            ResAssessCfgItemForm itemForm = itemFormList.get(assessId);//标准
                            int biaozhun = 9;
                            if (itemForm != null) {
                                String sc = itemForm.getScore();
                                if (StringUtil.isNotBlank(sc)) {
                                    biaozhun = Integer.valueOf(sc);
                                }
                            }
                            if (score != null) {
                                String scoreStr = score.getText();

                                if (StringUtil.isNotBlank(scoreStr)) {
                                    double scorea = Double.valueOf(scoreStr);
                                    double per = scorea / 9.0;
                                    int newScore = (int) Math.rint(per * biaozhun);
                                    total += newScore;
                                    score.setText(String.valueOf(newScore));
                                    toUpdate = true;
                                    result += "olditem:" + scoreStr + " newitem:" + newScore + "   <br>";
                                }
                            }
                        }
                        if (total != 0) {
                            totalScore.setText(String.valueOf(total));
                            toUpdate = true;
                        }

                        result += "old content:" + rec.getRecContent() + "   new content" + dom.asXML();
                        result += " new:" + total + " <br> ==========================================================================";
                    }
                }
            }
            if (toUpdate) {
                rec.setRecContent(dom.asXML());
                recMapper.updateByPrimaryKeyWithBLOBs(rec);
                result += ("<br/><br/>" + rec.getOrgName());
                result += ("<br/>" + "；");
            }
        }
        return result;
    }

    @RequestMapping("/updateXmlForSrmForm20160705DeptAssessTemp")
    @ResponseBody
    public Object updateXmlForSrmForm20160705DeptAssessTemp() throws Exception {//修改将已经被评价过的带教老师以及科主任的分值按照百分制重新更新一下（原来是9分制）。
        //http://localhost:8080/pdsci/inx/jssrm/updateXmlForSrmForm20160705DeptAssessTemp
//		http://wskj.jswst.gov.cn/pdsci/inx/jssrm/updateXmlForSrmForm20160612Temp
        //jsswst.yxzdrc
        String result = "结果：";
        ResRecMapper recMapper = SpringUtil.getBean(ResRecMapper.class);
        List<String> list = new ArrayList<>();
        list.add("DeptGrade");
        ResRecExample example = new ResRecExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andRecTypeIdIn(list).andCreateTimeLike("2016%");
        //系统配置的分数
        ResAssessCfgExample racexample = new ResAssessCfgExample();
        com.pinde.sci.model.mo.ResAssessCfgExample.Criteria criteria = racexample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andCfgCodeIdEqualTo("DeptAssess");
        ResAssessCfgMapper assessCfgMapper = SpringUtil.getBean(ResAssessCfgMapper.class);
        List<ResAssessCfg> assessCfgList = assessCfgMapper.selectByExampleWithBLOBs(racexample);
        Map<String, ResAssessCfgItemForm> itemFormList = null;
        if (assessCfgList != null && !assessCfgList.isEmpty()) {
            ResAssessCfg assessCfg = assessCfgList.get(0);
            Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
            String titleXpath = "//title";
            List<Element> titleElementList = dom.selectNodes(titleXpath);
            itemFormList = new HashMap<String, ResAssessCfgItemForm>();
            if (titleElementList != null && !titleElementList.isEmpty()) {
                List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
                for (Element te : titleElementList) {
                    ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
                    titleForm.setId(te.attributeValue("id"));
                    titleForm.setName(te.attributeValue("name"));
                    List<Element> itemElementList = te.elements("item");

                    if (itemElementList != null && !itemElementList.isEmpty()) {
                        for (Element ie : itemElementList) {
                            ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
                            itemForm.setId(ie.attributeValue("id"));
                            itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                            itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
                            itemFormList.put(ie.attributeValue("id"), itemForm);
                        }
                    }
                }
            }
        }
        //需要被修改的数据
        List<ResRec> resRecs = recMapper.selectByExampleWithBLOBs(example);
        for (ResRec rec : resRecs) {
            boolean toUpdate = false;
            String content = rec.getRecContent();
            Document dom = DocumentHelper.parseText(content);
            Element root = dom.getRootElement();
            if (root != null) {
                List<Element> items = root.elements("grade");
                if (items != null && items.size() > 0) {
                    int total = 0;
                    Element totalScore = root.element("totalScore");
                    String oldScor = totalScore.getText();
                    if (StringUtil.isNotBlank(oldScor) && Double.valueOf(oldScor) <= 81) {
                        result += "old:" + totalScore.getText() + " <br>";
                        for (Element e : items) {
                            String assessId = e.attributeValue("assessId");

                            Element score = e.element("score");//已经评好的分数
                            ResAssessCfgItemForm itemForm = itemFormList.get(assessId);//标准
                            int biaozhun = 9;
                            if (itemForm != null) {
                                String sc = itemForm.getScore();
                                if (StringUtil.isNotBlank(sc)) {
                                    biaozhun = Integer.valueOf(sc);
                                }
                            }
                            if (score != null) {
                                String scoreStr = score.getText();

                                if (StringUtil.isNotBlank(scoreStr)) {
                                    double scorea = Double.valueOf(scoreStr);
                                    double per = scorea / 9.0;
                                    int newScore = (int) Math.rint(per * biaozhun);
                                    total += newScore;
                                    score.setText(String.valueOf(newScore));
                                    toUpdate = true;
                                    result += "olditem:" + scoreStr + " newitem:" + newScore + "   <br>";
                                }
                            }
                        }
                        if (total != 0) {
                            totalScore.setText(String.valueOf(total));
                            toUpdate = true;
                        }

                        result += "old content:" + rec.getRecContent() + "   new content" + dom.asXML();
                        result += " new:" + total + " <br> ==========================================================================";
                    }
                }
            }
            if (toUpdate) {
                rec.setRecContent(dom.asXML());
                recMapper.updateByPrimaryKeyWithBLOBs(rec);
                result += ("<br/><br/>" + rec.getOrgName());
                result += ("<br/>" + "；");
            }
        }


        return result;
    }

    /**
     * 科研项目信息导出（含xml内容）
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping("/exportExcelXmlForWXSrmForm20160802DeptAssessTemp")
    @ResponseBody
    public void exportExcelXmlForWXSrmForm20160802DeptAssessTemp(HttpServletResponse response) throws Exception {
        String[] titles = new String[]{
                "proj.applyOrgName:申报单位",
                "proj.projYear:年度",
                "proj.projName:项目名称",
                "proj.projTypeName:项目类别",
                "proj.applyUserName:项目负责人",
                "proj.projStageName:项目阶段",
                "proj.projStatusName:项目状态",
                "subjectName:学科名称",
                "subjectCode:学科代码"
        };
        PubProj proj = new PubProj();
        proj.setProjStageId("Approve");
        proj.setProjCategoryId("qw");
        List<PubProj> searchList = projSeeBiz.searchProjWithBLOBs(proj);
        List<SrmExportExcel> exportExcelList = new ArrayList<SrmExportExcel>();
        if (searchList != null && !searchList.isEmpty()) {
            for (PubProj p : searchList) {
                SrmExportExcel exportExcel = new SrmExportExcel();
                String content = p.getProjInfo();
                if (null != content) {
                    Document dom = DocumentHelper.parseText(content);
                    Element root = dom.getRootElement();
                    Element step1 = root.element("step1");
                    if (null != step1) {
                        Element subjectCode = (Element) step1.selectSingleNode("//item[@name='subjectCode']");
                        Element subjectName = (Element) step1.selectSingleNode("//item[@name='subjectName']");
                        if (subjectCode != null && subjectName != null) {
                            Element code = subjectCode.element("value");
                            Element name = subjectName.element("value");
                            exportExcel.setSubjectCode(code.getText());
                            exportExcel.setSubjectName(name.getText());
                        }
                    }
                }
                exportExcel.setProj(p);
                exportExcelList.add(exportExcel);
            }
        }
        String fileName = "项目导出表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, exportExcelList, response.getOutputStream());
    }

    /**
     * 更改苏州科研项目合同书
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateXmlForSrmForm20161229Temp")
    @ResponseBody
    public Object updateXmlForSrmForm20161229Temp() throws Exception {//修改科研合同问题数据
        //http://localhost:8080/pdsci2/inx/jssrm/updateXmlForSrmForm20161229Temp
        String result = "结果：";
        TempExtMapper tempExtMapper = SpringUtil.getBean(TempExtMapper.class);
        PubProjRecMapper pubProjRecMapper = SpringUtil.getBean(PubProjRecMapper.class);
        List<PubProjRec> recs = tempExtMapper.getPubProjRecList();
        if (recs != null && !recs.isEmpty()) {
            for (PubProjRec ppr : recs) {
                boolean toUpdate = false;
                String content = ppr.getRecContent();
                if (StringUtil.isNotBlank(content)) {
                    Document dom = DocumentHelper.parseText(content);
                    Element root = dom.getRootElement();
                    Element step1 = root.element("step6");
                    Element homePage = root.element("homePage");
                    if (null != step1) {
                        Element year1 = (Element) step1.selectSingleNode("//item[@name='year1']");
                        if (year1 != null) {
                            Element year = year1.element("value");
                            if (null != year) {
                                year.setText("2017");
                            } else {
                                year1.addElement("value").setText("2017");
                            }
                            toUpdate = true;
                        }
                        Element year2 = (Element) step1.selectSingleNode("//item[@name='year2']");
                        if (year2 != null) {
                            Element year = year2.element("value");
                            if (null != year) {
                                year.setText("2018");
                            } else {
                                year2.addElement("value").setText("2018");
                            }
                            toUpdate = true;
                        }
                        Element year3 = (Element) step1.selectSingleNode("//item[@name='year3']");
                        if (year3 != null) {
                            Element year = year3.element("value");
                            if (null != year) {
                                year.setText("2019");
                            } else {
                                year3.addElement("value").setText("2019");
                            }
                            toUpdate = true;
                        }
                        if (toUpdate) {
                            ppr.setRecContent(dom.asXML());
                            pubProjRecMapper.updateByPrimaryKeyWithBLOBs(ppr);
                            result += ("<br/><br/>" + ppr.getProjFlow());
                            //result += ("<br/>" + "s==" + step1.getName() + "；" + projStartTime.asXML() + "；；" + projEndTime.asXML());
                        }
                    }
                    /*if(null != homePage){
                        Element projStartTime2 = (Element) homePage.selectSingleNode("//item[@name='contractStartTime']");
                        if (projStartTime2 != null) {
                            Element startTime = projStartTime2.element("value");
                            if (null != startTime) {
                                startTime.setText("2017-01-01");
                            } else {
                                projStartTime2.addElement("value").setText("2017-01-01");
                            }
                            toUpdate = true;
                        }

                        Element projEndTime2 = (Element) homePage.selectSingleNode("//item[@name='contractEndTime']");
                        if (projEndTime2 != null) {
                            Element endTime = projEndTime2.element("value");
                            if (null != endTime) {
                                endTime.setText("");
                            } else {
                                projEndTime2.addElement("value").setText("");
                            }
                            toUpdate = true;
                        }
                        if (toUpdate) {
                            ppr.setRecContent(dom.asXML());
                            pubProjRecMapper.updateByPrimaryKeyWithBLOBs(ppr);
                            result += ("<br/><br/>" + ppr.getProjFlow());
                            //result += ("<br/>" + "s==" + step1.getName() + "；" + projStartTime.asXML() + "；；" + projEndTime.asXML());
                        }
                    }*/
                }
            }
        }
        return result;
    }

    /**
     * 江苏西医退培功能优化
     * 原退培信息不需省厅审核，先优化需省厅审核
     * 省厅审核的字段放入RES_CONTENT中auditStatusName,auditOpinion
     * 为了融合新老数据现将老数据中已有退培信息全部改成已审核通过
     *
     * @return
     * @throws DocumentException
     */
    @RequestMapping("/updateXmlForResRecForm20170105Temp")
    @ResponseBody
    public Object updateXmlForResRecForm20170105Temp() throws DocumentException {//
        String result = "结果：";
        //带教老师评分
        ResRecMapper recMapper = SpringUtil.getBean(ResRecMapper.class);
        ResRecExample example = new ResRecExample();
        ResRecExample.Criteria crieria = example.createCriteria();
        crieria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        crieria.andRecTypeIdEqualTo(ResRecTypeEnum.ReturnTraining.getId());
        List<ResRec> resRecs = recMapper.selectByExampleWithBLOBs(example);
        int count = 0;
        for (ResRec resRec : resRecs) {
            String recContent = resRec.getRecContent();
            Document dom = DocumentHelper.parseText(recContent);
            Element root = dom.getRootElement();
            Element auditStatusElement = root.element("auditStatusName");
            if (auditStatusElement != null) {
                continue;
            }
            String auditStatusId = "1";
            String auditStatusName = ResBaseStatusEnum.Passed.getName();
            String auditOpinion = "";
            auditStatusElement = root.addElement("auditStatusName");
            Element auditOpinionElement = root.addElement("auditOpinion");
            auditStatusElement.addAttribute("id", auditStatusId);
            auditStatusElement.setText(auditStatusName);
            auditOpinionElement.setText(auditOpinion);
            resRec.setRecContent(dom.asXML());
            resRec.setRecVersion("1.2");
            //更新rec数据
            recMapper.updateByPrimaryKeySelective(resRec);
            count++;
        }
        result += "<br/> 总共：" + resRecs.size() + "条信息";
        result += "<br/> 修改：" + count + "条信息";
        return result;
    }

    /**
     * 江苏中医减免独立出单独审核功能需要由省厅审核
     * 以前功能是学员填写招录申请信息自己填写减免年份，管理员审核同意自动同意学员的减免申请
     * 现在需要将以前的一部分数据res_doctor_recruit表的train_year
     * 插入到RES_DOCTOR_REDUCTION表中默认省厅审核同意
     * res_doctor_recruit表的prove_file_url存到pub_file中
     *
     * @return
     * @throws DocumentException
     */
    @RequestMapping("/updateReductionInfo20171130Temp")
    @ResponseBody
    public Object updateReductionInfo20171130Temp() throws DocumentException {//
        String result = "结果：";
        //招录信息
        ResDoctorRecruitMapper recruitMapper = SpringUtil.getBean(ResDoctorRecruitMapper.class);
        //减免信息
        ResDoctorReductionMapper reductionMapper = SpringUtil.getBean(ResDoctorReductionMapper.class);
        //存储文件
        PubFileMapper fileMapper = SpringUtil.getBean(PubFileMapper.class);
        ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        ResDoctorRecruitExample.Criteria crieria = example.createCriteria();
        crieria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        crieria.andAuditStatusIdEqualTo(JszyResDoctorAuditStatusEnum.Passed.getId());
        //培训专业是ChineseMedicine或TCMAssiGeneral才可以减免
        List<String> catSpeIds = new ArrayList<>();
        catSpeIds.add(JszyTrainCategoryEnum.ChineseMedicine.getId());
        catSpeIds.add(JszyTrainCategoryEnum.TCMGeneral.getId());
        crieria.andCatSpeIdIn(catSpeIds);
        //train_year如果是1或者2说明以前减免过
        List<String> trainYears = new ArrayList<>();
        trainYears.add(JszyResTrainYearEnum.OneYear.getId());
        trainYears.add(JszyResTrainYearEnum.TwoYear.getId());
        crieria.andTrainYearIn(trainYears);


        List<ResDoctorRecruit> recruits = recruitMapper.selectByExample(example);
        int count = 0;
        if (recruits != null && recruits.size() > 0) {
            for (ResDoctorRecruit tempRecruit : recruits) {
                String recruitFlow = tempRecruit.getRecruitFlow();
                ResDoctorReductionExample reductionExample = new ResDoctorReductionExample();
                ResDoctorReductionExample.Criteria reductionCrieria = reductionExample.createCriteria();
                reductionCrieria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
                reductionCrieria.andRecruitFlowEqualTo(recruitFlow);
                List<ResDoctorReduction> recrutions = reductionMapper.selectByExample(reductionExample);
                //RES_DOCTOR_REDUCTION表没数据
                if(recrutions == null || recrutions.size() < 1){
                    ResDoctorReduction reduction = new ResDoctorReduction();
                    PubFile pubFile = new PubFile();
                    String afterReduceTrainYear = tempRecruit.getTrainYear();
                    String reduceYear = Integer.parseInt(JszyResTrainYearEnum.ThreeYear.getId()) - Integer.parseInt(afterReduceTrainYear) + "";

                    //更新ResDoctorReduction
                    reduction.setRecruitFlow(tempRecruit.getRecruitFlow());
                    reduction.setDoctorFlow(tempRecruit.getDoctorFlow());
                    reduction.setDefaultTrainYear(JszyResTrainYearEnum.ThreeYear.getId());
                    reduction.setReduceYear(reduceYear);
                    reduction.setAfterReduceTrainYear(afterReduceTrainYear);
                    reduction.setAuditStatusId(JszyBaseStatusEnum.GlobalPassed.getId());
                    reduction.setAuditStatusName(JszyBaseStatusEnum.GlobalPassed.getName());
                    reduction.setGlobalAuditUserFlow("00000000000000000000000000000000");
                    reduction.setGlobalAuditTime("20171130000000");
                    reduction.setRecordFlow(PkUtil.getUUID());
                    GeneralMethod.setRecordInfo(reduction, true);
                    reductionMapper.insertSelective(reduction);

                    //更新PubFile
                    String filePath = tempRecruit.getProveFileUrl();
                    String fileSuffix = null;
                    if (StringUtil.isNotBlank(filePath)) {
                        fileSuffix = filePath.substring(filePath.lastIndexOf("."));
                    }
                    pubFile.setFilePath(tempRecruit.getProveFileUrl());
                    pubFile.setFileName("减免附件" + fileSuffix);
                    pubFile.setFileSuffix(fileSuffix);
                    pubFile.setProductType("Reduction");
                    pubFile.setProductFlow(reduction.getRecordFlow());
                    pubFile.setFileFlow(PkUtil.getUUID());
                    GeneralMethod.setRecordInfo(pubFile, true);
                    fileMapper.insertSelective(pubFile);

                    count++;
                }
            }
        }
        result += "<br/> 总共：" + recruits.size() + "条信息";
        result += "<br/> 修改：" + count + "条信息";
        return result;
    }

    /**
     * 江苏西医退培延期功能优化
     * 源于拆分RES_REC表将老数据从RES_REC（recTypeId区分）中迁移至RES_DOCOTR_DELAY_TETURN中
     *
     * @return
     * @throws DocumentException
     */
    @RequestMapping("/updateXmlForResRecForm20170510Temp")
    @ResponseBody
    public Object updateXmlForResRecForm20170510Temp() throws DocumentException {//
        String result = "结果：";
        ResRecMapper recMapper = SpringUtil.getBean(ResRecMapper.class);
        ResDoctorMapper doctorMapper = SpringUtil.getBean(ResDoctorMapper.class);
        SysUserMapper userMapper = SpringUtil.getBean(SysUserMapper.class);
        ResDocotrDelayTeturnMapper docotrDelayTeturnMapper = SpringUtil.getBean(ResDocotrDelayTeturnMapper.class);
        ResDoctorRecruitMapper recruitMapper = SpringUtil.getBean(ResDoctorRecruitMapper.class);
        ResRecExample example = new ResRecExample();
        List<String> recTypeIds = new ArrayList<>();
        recTypeIds.add(ResRecTypeEnum.ReturnTraining.getId());
        recTypeIds.add(ResRecTypeEnum.Delay.getId());
        ResRecExample.Criteria crieria = example.createCriteria();
        crieria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        crieria.andRecTypeIdIn(recTypeIds);
        List<ResRec> resRecs = recMapper.selectByExampleWithBLOBs(example);
        int count = 0;
        for (ResRec resRec : resRecs) {
            String doctorFlow = resRec.getOperUserFlow();
            ResDoctor doctor = doctorMapper.selectByPrimaryKey(doctorFlow);
            ResDocotrDelayTeturn resDocotrDelayTeturn = new ResDocotrDelayTeturn();
            String recContent = resRec.getRecContent();
            Document dom = DocumentHelper.parseText(recContent);
            Element root = dom.getRootElement();
            //退培延期通用的信息修改
            resDocotrDelayTeturn.setRecordFlow(resRec.getRecFlow());
            resDocotrDelayTeturn.setOrgFlow(resRec.getOrgFlow());
            resDocotrDelayTeturn.setOrgName(resRec.getOrgName());
            resDocotrDelayTeturn.setDoctorFlow(doctor.getDoctorFlow());
            resDocotrDelayTeturn.setDoctorName(doctor.getDoctorName());
            //老数据中没有doctorType故从RES_DOCTOR中取，该记录是医师人员最新的doctorType
            //而在退培延期功能从RES_REC拆分出来以后，该记录是医师人员当前的doctorType
            //注：下面两行代码可能会插入脏数据（医师修改doctorType后）。
            resDocotrDelayTeturn.setDoctorTypeId(doctor.getDoctorTypeId());
            resDocotrDelayTeturn.setDoctorTypeName(doctor.getDoctorTypeName());


            resDocotrDelayTeturn.setTypeId(resRec.getRecTypeId());
            resDocotrDelayTeturn.setTypeName(resRec.getRecTypeName());
            Element sessionNumberElement = root.element("sessionNumber");
            if (sessionNumberElement != null) {
                String sessionNumber = sessionNumberElement.getText();
                resDocotrDelayTeturn.setSessionNumber(sessionNumber);
            }
            Element trainingSpeElement = root.element("trainSpe");
            if (trainingSpeElement != null) {
                String trainingSpeId = trainingSpeElement.attributeValue("id");
                String trainingSpeName = trainingSpeElement.getText();
                resDocotrDelayTeturn.setTrainingSpeId(trainingSpeId);
                resDocotrDelayTeturn.setTrainingSpeName(trainingSpeName);
            }
            String policyUserFlow = resRec.getModifyUserFlow();
            SysUser policyUser = userMapper.selectByPrimaryKey(policyUserFlow);
            resDocotrDelayTeturn.setPolicyTime(resRec.getCreateTime());
            resDocotrDelayTeturn.setPolicyUserFlow(policyUserFlow);
            resDocotrDelayTeturn.setPolicyUserName(policyUser.getUserName());
            //记录基本信息
            resDocotrDelayTeturn.setRecordStatus(resRec.getRecordStatus());
            resDocotrDelayTeturn.setCreateUserFlow(resRec.getCreateUserFlow());
            resDocotrDelayTeturn.setCreateTime(resRec.getCreateTime());
            resDocotrDelayTeturn.setModifyUserFlow(resRec.getModifyUserFlow());
            resDocotrDelayTeturn.setModifyTime(resRec.getModifyTime());
            if (ResRecTypeEnum.ReturnTraining.getId().equals(resRec.getRecTypeId())) {
                //退培信息修改
                //退培中没有存入recruitFlow
                //退培中没有存入trainingYears
                //退培中没有存入trainingType
                //退培中没有存入graduationYear
                Element policyNameElement = root.element("policyName");
                if (policyNameElement != null) {
                    String policyId = policyNameElement.attributeValue("id");
                    String policyName = policyNameElement.getText();
                    resDocotrDelayTeturn.setPolicyId(policyId);
                    resDocotrDelayTeturn.setPolicyName(policyName);
                }
                Element policyElement = root.element("policy");
                if (policyElement != null) {
                    String policy = policyElement.getText();
                    resDocotrDelayTeturn.setPolicy(policy);
                }
                Element reasonNameElement = root.element("reasonName");
                if (reasonNameElement != null) {
                    String reasonId = reasonNameElement.attributeValue("id");
                    String reasonName = reasonNameElement.getText();
                    resDocotrDelayTeturn.setReasonId(reasonId);
                    resDocotrDelayTeturn.setReasonName(reasonName);
                }
                Element reasonElement = root.element("reason");
                if (reasonElement != null) {
                    String reason = reasonElement.getText();
                    resDocotrDelayTeturn.setReason(reason);
                }
                Element dispositonElement = root.element("dispositon");
                if (dispositonElement != null) {
                    String dispositon = dispositonElement.getText();
                    resDocotrDelayTeturn.setDispositon(dispositon);
                }
                Element remarkElement = root.element("remark");
                if (remarkElement != null) {
                    String remark = remarkElement.getText();
                    resDocotrDelayTeturn.setRemark(remark);
                }
                //退培需要审核
                String auditUserFlow = resRec.getModifyUserFlow();
                SysUser auditUser = userMapper.selectByPrimaryKey(auditUserFlow);
                resDocotrDelayTeturn.setAuditTime(resRec.getModifyTime());
                resDocotrDelayTeturn.setAuditUserFlow(auditUserFlow);
                resDocotrDelayTeturn.setAuditUserName(auditUser.getUserName());
                Element auditStatusElement = root.element("auditStatusName");
                if (auditStatusElement != null) {
                    String auditStatusId = auditStatusElement.attributeValue("id");
                    String auditStatusName = auditStatusElement.getText();
                    resDocotrDelayTeturn.setAuditStatusId(auditStatusId);
                    resDocotrDelayTeturn.setAuditStatusName(auditStatusName);
                }
                Element auditOpinionElement = root.element("auditOpinion");
                if (auditOpinionElement != null) {
                    String auditOpinion = auditOpinionElement.getText();
                    resDocotrDelayTeturn.setAuditOpinion(auditOpinion);
                }
            }
            if (ResRecTypeEnum.Delay.getId().equals(resRec.getRecTypeId())) {
                //延期信息修改
                Element recruitFlowElement = root.element("recruitFlow");
                ResDoctorRecruit recruit = null;
                if (recruitFlowElement != null) {
                    String recruitFlow = recruitFlowElement.getText();
                    recruit = recruitMapper.selectByPrimaryKey(recruitFlow);
                    resDocotrDelayTeturn.setTrainingYears(recruit.getTrainYear());
                    resDocotrDelayTeturn.setTrainingTypeId(recruit.getCatSpeId());
                    resDocotrDelayTeturn.setTrainingTypeName(recruit.getCatSpeName());
                    resDocotrDelayTeturn.setGraduationYear(recruit.getGraduationYear());
                    resDocotrDelayTeturn.setRecruitFlow(recruitFlow);
                }
                Element delayReasonElement = root.element("delayReason");
                if (delayReasonElement != null) {
                    String delayReason = delayReasonElement.getText();
                    resDocotrDelayTeturn.setDelayreason(delayReason);
                }
                Element graduationYearElement = root.element("graduationYear");
                if (graduationYearElement != null) {
                    String graduationYear = graduationYearElement.getText();
                    resDocotrDelayTeturn.setGraduationYear(graduationYear);
                }
                Element delayPicValueFileElement = root.element("delayPicValueFile");
                if (delayPicValueFileElement != null) {
                    String delayPicValueFile = delayPicValueFileElement.getText();
                    resDocotrDelayTeturn.setDelayFilePath(delayPicValueFile);
                }
            }
            //更新rec数据
            docotrDelayTeturnMapper.insertSelective(resDocotrDelayTeturn);
            count++;
        }
        result += "<br/> 总共：" + resRecs.size() + "条信息";
        result += "<br/> 修改：" + count + "条信息";
        return result;
    }

    /**
     * 初始化所有科研项目学科代码
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateProjSubjIdFromProjInfoXml20170223Temp")
    @ResponseBody
    public Object updateProjSubjIdFromProjInfoXml20170223Temp() throws Exception {//修改科研表单问题数据
        //http://localhost:8080/pdsci/inx/jssrm/updateProjSubjIdFromProjInfoXml20170223Temp
//		http://wskj.jswst.gov.cn/pdsci/inx/jssrm/updateProjSubjIdFromProjInfoXml20170223Temp
        //jsswst.yxzdrc
        String result = "结果：";
        PubProjMapper pubProjMapper = SpringUtil.getBean(PubProjMapper.class);
        PubProjExample example = new PubProjExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);//andProjCategoryIdEqualTo("qw");//PROJ_CATEGORY_ID
        List<PubProj> projs = pubProjMapper.selectByExampleWithBLOBs(example);
        if (projs != null && !projs.isEmpty()) {
            int i = 0;
            for (PubProj ppr : projs) {

                i++;
                boolean toUpdate = false;
                String content = ppr.getProjInfo();
                if (StringUtil.isNotBlank(content)) {
                    Document dom = DocumentHelper.parseText(content);
                    Element root = dom.getRootElement();
                    Element step1 = root.element("step1");
                    if (null != step1) {
                        Element subjectCode = (Element) step1.selectSingleNode("//item[@name='subjectCode']");
                        if (subjectCode != null) {
                            Element code = subjectCode.element("value");
                            if (null != code) {
                                if (StringUtil.isNotBlank(code.getText())) {
                                    System.err.println(i + "-----code--:" + code.getText());
                                    ppr.setSubjId(code.getText());
                                }
                            }
                            toUpdate = true;
                        }
                        Element subjectName = (Element) step1.selectSingleNode("//item[@name='subjectName']");
                        if (subjectName != null) {
                            Element name = subjectName.element("value");
                            if (null != name) {
                                if (StringUtil.isNotBlank(name.getText())) {
                                    System.err.println(i + "-----name--:" + name.getText());
                                    ppr.setSubjName(name.getText());
                                }
                            }
                            toUpdate = true;
                        }
                        if (toUpdate) {
                            pubProjMapper.updateByPrimaryKeyWithBLOBs(ppr);
                            result += ("<br/><br/>" + ppr.getProjName());
                            result += ("<br/>" + "s==" + step1.getName() + "；" + subjectCode.asXML() + "；；" + subjectName.asXML());
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 初始化所有科研项目工号
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateProjApplyUserEmpnumFromProjInfoXml20170301Temp")
    @ResponseBody
    public Object updateProjApplyUserEmpnumFromProjInfoXml20170301Temp() throws Exception {//修改科研表单问题数据
        //http://localhost:8080/pdsci/inx/jssrm/updateProjApplyUserEmpnumFromProjInfoXml20170301Temp
//		http://wskj.jswst.gov.cn/pdsci/inx/jssrm/updateProjApplyUserEmpnumFromProjInfoXml20170301Temp
        //jsswst.yxzdrc
        String result = "结果：";
        PubProjMapper pubProjMapper = SpringUtil.getBean(PubProjMapper.class);
        PubProjExample example = new PubProjExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProjTypeIdEqualTo("wxdermyy.xmdj");//PROJ_CATEGORY_ID
        List<PubProj> projs = pubProjMapper.selectByExampleWithBLOBs(example);
        if (projs != null && !projs.isEmpty()) {
            for (PubProj ppr : projs) {

                boolean toUpdate = false;
                String content = ppr.getProjInfo();
                if (StringUtil.isNotBlank(content)) {
                    Document dom = DocumentHelper.parseText(content);
                    Element root = dom.getRootElement();
                    Element step1 = root.element("step1");
                    if (null != step1) {
                        Element jobNumber = (Element) step1.selectSingleNode("//item[@name='jobNumber']");
                        if (jobNumber != null) {
                            Element applyUserEmpnum = jobNumber.element("value");
                            if (null != applyUserEmpnum) {
                                if (StringUtil.isNotBlank(applyUserEmpnum.getText())) {
                                    ppr.setApplyUserEmpnum(applyUserEmpnum.getText());
                                }
                            }
                            toUpdate = true;
                        }

                        if (toUpdate) {
                            pubProjMapper.updateByPrimaryKeyWithBLOBs(ppr);
                            result += ("<br/><br/>" + ppr.getProjName());
                            result += ("<br/>" + "s==" + step1.getName() + "；" + jobNumber.asXML());
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 初始化中管局科研项目项目类型
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateProjTypeIdFromProjInfoXml20170329Temp")
    @ResponseBody
    public Object updateProjTypeIdFromProjInfoXml20170329Temp() throws Exception {//修改科研表单问题数据
        String result = "结果：";
        PubProjMapper pubProjMapper = SpringUtil.getBean(PubProjMapper.class);
        PubProjRecMapper pubProjRecMapper = SpringUtil.getBean(PubProjRecMapper.class);
        PubProjExample example = new PubProjExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProjTypeIdEqualTo("jszyyj.ybxm");
        List<PubProj> projs = pubProjMapper.selectByExample(example);
        if (projs != null && !projs.isEmpty()) {
            List<String> projFlows = new ArrayList<String>();
            for (PubProj pp : projs) {
                projFlows.add(pp.getProjFlow());
            }
            PubProjRecExample recExample = new PubProjRecExample();
            recExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProjFlowIn(projFlows);
            List<PubProjRec> recs = pubProjRecMapper.selectByExampleWithBLOBs(recExample);
            for (PubProjRec ppr : recs) {
                boolean toUpdate = false;
                String content = ppr.getRecContent();
                if (StringUtil.isNotBlank(content)) {
                    Document dom = DocumentHelper.parseText(content);
                    Element root = dom.getRootElement();
                    Element step1 = root.element("step1");
                    if (null != step1) {
                        Element proType = (Element) step1.selectSingleNode("//item[@name='proType']");
                        if (proType != null) {
                            Element year = proType.element("value");
                            if (null != year) {
                                year.setText("一般项目");
                            } else {
                                proType.addElement("value").setText("一般项目");
                            }
                            toUpdate = true;
                        } else {
                            Element projTypeEl = step1.addElement("item");
                            projTypeEl.addAttribute("name", "proType");
                            projTypeEl.addAttribute("remark", "项目类型");
                            projTypeEl.addAttribute("multiple", "N");
                            projTypeEl.addElement("value").setText("一般项目");
                            toUpdate = true;
                        }
                        if (toUpdate) {
                            ppr.setRecContent(dom.asXML());
                            pubProjRecMapper.updateByPrimaryKeyWithBLOBs(ppr);
                            result += ("<br/><br/>" + ppr.getProjFlow());
                            //result += ("<br/>" + "s==" + step1.getName() + "；" + projStartTime.asXML() + "；；" + projEndTime.asXML());
                        }
                    }
                }
            }
        }
        return result;
    }

    @Autowired
    private IResRecBiz resRecBiz;
    @Autowired
    private ISchRotationDeptBiz schRotationDeptBiz;
    @RequestMapping("/updateJsresRotationDeptPer20180105Temp")
    @ResponseBody
    public Object updateJsresRotationDeptPer20180105Temp() throws Exception {//修改科研表单问题数据
        String result = "结果：";
        ResDoctorMapper resDoctorMapper=SpringUtil.getBean(ResDoctorMapper.class);
        ResStandardDeptPerMapper perMapper=SpringUtil.getBean(ResStandardDeptPerMapper.class);
        ResDoctorExample example=new ResDoctorExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andOrgFlowIsNotNull().andRotationFlowIsNotNull();
        List<ResDoctor> doctorList=resDoctorMapper.selectByExample(example);
        Map<String,List<SchRotationDept>> deptMap=new HashMap<>();
        if(doctorList!=null&&doctorList.size()>0){
            for(ResDoctor doctor:doctorList) {
                if(StringUtil.isNotBlank(doctor.getRotationFlow())) {
                        Map<String,String> finishPerMap = resRecBiz.getProcessPer(doctor.getDoctorFlow(),doctor.getRotationFlow());

                        Map<String, Object> paramMap = new HashMap<>();
                        paramMap.put("doctorFlow",doctor.getDoctorFlow());
                        List<ResRec> recs = resRecBiz.searchProssingRec(paramMap);
                        Map<String, String> finishPerMap2 = resRecBiz.getProcessPer(doctor.getDoctorFlow(), doctor.getRotationFlow(),null, 0, recs);
                        if (finishPerMap2 ==null) {
                            finishPerMap2=new HashMap<>();
                        }
                        List<SchRotationDept> depts= deptMap.get(doctor.getRotationFlow());
                    if(depts==null) {
                        depts = schRotationDeptBiz.searchSchRotationDept(doctor.getRotationFlow());
                        deptMap.put(doctor.getRotationFlow(),depts);
                    }
                        if(finishPerMap!=null&&depts!=null&&depts.size()>0) {
                            for(SchRotationDept dept:depts) {
                                String allPer="0";
                                String processPer="0";
                                allPer = finishPerMap.get(dept.getRecordFlow());
                                processPer = finishPerMap2.get(dept.getRecordFlow());
                                ResStandardDeptPer per=null;
                                ResStandardDeptPerExample example2=new ResStandardDeptPerExample();
                                example2.createCriteria().andDoctorFlowEqualTo(doctor.getDoctorFlow()).andSchRotationDeptFlowEqualTo(dept.getRecordFlow())
                                        .andRotationFlowEqualTo(doctor.getRotationFlow())
                                        .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
                                example2.setOrderByClause("create_time desc");
                                List<ResStandardDeptPer> pers= perMapper.selectByExample(example2);
                                if(pers!=null&&!pers.isEmpty())
                                {
                                    per=pers.get(0);
                                }
                                if(per==null)
                                {
                                    per=new ResStandardDeptPer();
                                    per.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                                    per.setDoctorFlow(doctor.getDoctorFlow());
                                    per.setSchRotationDeptFlow(dept.getRecordFlow());
                                    per.setRotationFlow(doctor.getRotationFlow());
                                    per.setRotationName(doctor.getRotationName());
                                    per.setSessionNumber(doctor.getSessionNumber());
                                }
                                if(StringUtil.isBlank(allPer)){
                                    allPer="0";
                                }
                                if(StringUtil.isBlank(processPer)){
                                    processPer="0";
                                }
                                per.setAllPer(allPer);
                                per.setProcessPer(processPer);
                                if(StringUtil.isNotBlank(per.getPerFlow()))
                                {
                                    per.setModifyTime(DateUtil.getCurrDateTime());
                                    per.setModifyUserFlow(doctor.getDoctorFlow());
                                    perMapper.updateByPrimaryKeySelective(per);
                                }else{
                                    per.setPerFlow(PkUtil.getUUID());
                                    per.setCreateTime(DateUtil.getCurrDateTime());
                                    per.setCreateUserFlow(doctor.getDoctorFlow());
                                    per.setModifyTime(DateUtil.getCurrDateTime());
                                    per.setModifyUserFlow(doctor.getDoctorFlow());
                                    per.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                                    perMapper.insertSelective(per);
                                }
                                result += ("<br/>" + per.getPerFlow()+" 学员姓名："+doctor.getDoctorName()+" 标准科室名称："+dept.getStandardDeptName()+" allPer:"+per.getAllPer()+" ProcessPer："+per.getProcessPer()+" "+"<br/>");
                            }
                        }
                }
            }
        }
        return result;
    }
}
