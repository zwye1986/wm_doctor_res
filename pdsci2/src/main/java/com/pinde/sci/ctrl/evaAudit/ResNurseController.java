package com.pinde.sci.ctrl.evaAudit;

import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.RecStatusEnum;
import com.pinde.core.common.enums.ResAssessTypeEnum;
import com.pinde.core.common.enums.pub.NurseStatusEnum;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.form.res.ResAssessCfgItemForm;
import com.pinde.sci.form.res.ResAssessCfgTitleForm;
import com.pinde.sci.model.mo.JsresPowerCfg;
import com.pinde.sci.model.mo.PubFile;
import com.pinde.sci.model.mo.ResAssessCfg;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorRecruit;
import com.pinde.sci.model.mo.ResDoctorSchProcess;
import com.pinde.sci.model.mo.SchArrangeResult;
import com.pinde.sci.model.mo.SchDept;
import com.pinde.sci.model.mo.SysRole;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/res/nurse")
public class ResNurseController extends GeneralController {

    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IRoleBiz roleBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private IResDoctorProcessBiz resDoctorProcessBiz;
    @Autowired
    private ISchArrangeResultBiz resultBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private IResAssessCfgBiz assessCfgBiz;
    @Autowired
    private IResGradeBiz resGradeBiz;
    @Autowired
    private IResRecBiz resRecBiz;
    @Autowired
    private ISchDeptBiz schDeptBiz;
    @Autowired
    private IJsResPowerCfgBiz jsResPowerCfgBiz;
    @Autowired
    private IResDoctorRecruitBiz recruitBiz;
    @Autowired
    private SysUserMapper sysUserMapper;
    private static Logger logger = LoggerFactory.getLogger(ResNurseController.class);

    @RequestMapping("/index")
    public String index(){
        return "/hbres/evaAudit/nurse/index";
    }


    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(Integer currentPage, SysUser search, String deptFlow,
                       Model model, HttpServletRequest request) {
        List<SysDept> depts = deptBiz.searchDeptByOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("depts", depts);
        SysRole sysRole = roleBiz.selectByRoleName("护士");
        model.addAttribute("sysRole", sysRole);
        List<NurseStatusEnum> nurseStatusEnums = new ArrayList<>();
        for (NurseStatusEnum value : NurseStatusEnum.values()) {
            nurseStatusEnums.add(value);
        }
        model.addAttribute("nurseStatusEnums", nurseStatusEnums);
        List<SysUser> sysUserList = null;//初始化查询结果
        //组织用户查询map
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("roleFlow", sysRole.getRoleFlow());
        paramMap.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
        paramMap.put("wsId", "res");
        if (search != null) {
            paramMap.put("userPhone", search.getUserPhone());
            paramMap.put("userName", search.getUserName());
            paramMap.put("statusId", search.getStatusId());
        }
        if (StringUtil.isNotBlank(deptFlow)) {
            paramMap.put("deptFlow", deptFlow);
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        sysUserList = userBiz.searchUserWithRole(paramMap);
        //查询多科室
        if(CollectionUtils.isNotEmpty(sysUserList)){
            for (SysUser user : sysUserList) {
                List<SysUserDept> userDept = userBiz.getUserDept(user);
                if(CollectionUtils.isNotEmpty(userDept)){
                    List<String> collect = userDept.stream().map(SysUserDept::getDeptName).collect(Collectors.toList());
                    user.setDeptName(collect.toString().replace("[","").replace("]",""));
                }
            }
        }
        model.addAttribute("sysUserList", sysUserList);
        return "/hbres/evaAudit/nurse/list";
    }

    @RequestMapping(value = "/teachingList", method = {RequestMethod.POST, RequestMethod.GET})
    public String teachingList(Integer currentPage, SysUser search, String deptFlow,
                               Model model, HttpServletRequest request, String roleFlag) {
        List<SysDept> depts = deptBiz.searchDeptByOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("depts", depts);
        model.addAttribute("search", search);
        SysRole sysRole = roleBiz.selectByRoleName("带教老师");
        model.addAttribute("sysRole", sysRole);
        List<NurseStatusEnum> nurseStatusEnums = new ArrayList<>();
        for (NurseStatusEnum value : NurseStatusEnum.values()) {
            nurseStatusEnums.add(value);
        }
        model.addAttribute("nurseStatusEnums", nurseStatusEnums);
        List<SysUser> sysUserList = null;//初始化查询结果
        //组织用户查询map
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("roleFlow", sysRole.getRoleFlow());
        paramMap.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
        if(StringUtil.isNotEmpty(roleFlag) && "global".equals(roleFlag)){
            paramMap.put("orgFlow", "");
            model.addAttribute("roleFlag",roleFlag);
        }
        paramMap.put("wsId", "res");
        if (search != null) {
            paramMap.put("userPhone", search.getUserPhone());
            paramMap.put("userName", search.getUserName());
            paramMap.put("statusId", search.getStatusId());
            paramMap.put("auditStatus", search.getAuditStatus());
        }
        if (StringUtil.isNotBlank(deptFlow)) {
            paramMap.put("deptFlow", deptFlow);
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        sysUserList = userBiz.searchUserWithRole(paramMap);
        model.addAttribute("sysUserList", sysUserList);
        return "/hbres/evaAudit/nurse/teachingList";
    }

    @RequestMapping(value = {"/teachingEdit"})
    public String teachingEdit(String userFlow, String currentPage, Model model) {
        model.addAttribute("currentPage", currentPage);
        SysUser logiunUser = GlobalContext.getCurrentUser();
        List<SysDept> depts = deptBiz.searchDeptByOrg(logiunUser.getOrgFlow());
        model.addAttribute("depts", depts);
        List<NurseStatusEnum> nurseStatusEnums = new ArrayList<>();
        for (NurseStatusEnum value : NurseStatusEnum.values()) {
            nurseStatusEnums.add(value);
        }
        model.addAttribute("nurseStatusEnums", nurseStatusEnums);
        SysRole sysRole = roleBiz.selectByRoleName("带教老师");
        model.addAttribute("sysRole", sysRole);
        if (StringUtil.isNotBlank(userFlow)) {
            SysUser sysUser = userBiz.readSysUser(userFlow);
            model.addAttribute("sysUser", sysUser);
            String tchRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
            String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");
            String isTeacher = com.pinde.core.common.GlobalConstant.FLAG_N;
            boolean isTeach = false;
            if (StringUtil.isNotBlank(tchRoleFlow) || StringUtil.isNotBlank(headRoleFlow)) {
                List<SysRole> userRoles = roleBiz.search(userFlow, com.pinde.core.common.GlobalConstant.RES_WS_ID);
                if (userRoles != null && userRoles.size() > 0) {
                    for (SysRole userRole : userRoles) {
                        String roleFlow = userRole.getRoleFlow();
                        if (tchRoleFlow.equals(roleFlow) || headRoleFlow.equals(roleFlow)) {
                            isTeacher = com.pinde.core.common.GlobalConstant.FLAG_Y;
                        }
                        if (tchRoleFlow.equals(roleFlow)) {
                            isTeach = true;
                        }
                    }
                }
            }
            model.addAttribute("isTeacher", isTeacher);
            model.addAttribute("isTeach", isTeach);
            SysDept sysDept = new SysDept();
            sysDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            sysDept.setOrgFlow(sysUser.getOrgFlow());
            List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
            model.addAttribute("sysDeptList", sysDeptList);

            List<SysUserDept> userDeptList = userBiz.getUserDept(sysUser);
            Map<String, String> userDeptMap = new HashMap<String, String>();
            for (SysUserDept userDept : userDeptList) {
                userDeptMap.put(userDept.getDeptFlow(), userDept.getDeptFlow());
            }
            model.addAttribute("userDeptMap", userDeptMap);
            if (sysUser != null && StringUtil.isNotBlank(sysUser.getCertificateFile())) {
                PubFile file = fileBiz.readFile(sysUser.getCertificateFile());
                model.addAttribute("file", file);
            }
        }
        return "/hbres/evaAudit/nurse/teachingEdit";
    }

    @RequestMapping(value = {"/teachingAudit"})
    public String teachingAudit(String userFlow, Model model) {
        if (StringUtil.isNotBlank(userFlow)) {
            SysUser sysUser = userBiz.readSysUser(userFlow);
            model.addAttribute("sysUser", sysUser);
        }
        return "/hbres/evaAudit/nurse/teachingAudit";
    }

    @RequestMapping(value = {"/saveTeachingAudit"}, method = RequestMethod.POST)
    @ResponseBody
    public String saveTeachingAudit(String userFlow, String auditFlag, Model model) {
        int result = 0;
        if (StringUtil.isNotBlank(userFlow)) {
            SysUser sysUser = userBiz.readSysUser(userFlow);
            if(StringUtil.isNotEmpty(auditFlag)){
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(auditFlag)) {
                    sysUser.setStatusId(UserStatusEnum.Activated.getId());
                    sysUser.setStatusDesc(UserStatusEnum.Activated.getName());
                    sysUser.setAuditStatus("审核通过");
                }else{
                    sysUser.setStatusId(UserStatusEnum.Locked.getId());
                    sysUser.setStatusDesc(UserStatusEnum.Locked.getName());
                    sysUser.setAuditStatus("审核不通过");
                }
            }
            result = userBiz.updateUser(sysUser);
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(auditFlag)) {
                // 基地是否开通过程
                String orgCfgCode = "jsres_" + sysUser.getOrgFlow() + "_guocheng";
                JsresPowerCfg jsresPowerCfg = jsResPowerCfgBiz.read(orgCfgCode);
                if (null != jsresPowerCfg && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jsresPowerCfg.getCfgValue()) && "Passed".equals(jsresPowerCfg.getCheckStatusId())) {
                    // 带教默认APP登录权限
                    String cfgCode = "jsres_teacher_app_login_"+ sysUser.getUserFlow();
                    JsresPowerCfg jsresCfg = new JsresPowerCfg();
                    jsresCfg.setCfgCode(cfgCode);
                    jsresCfg.setCfgValue(com.pinde.core.common.GlobalConstant.FLAG_Y);
                    jsresCfg.setCfgDesc("是否开放带教app权限");
                    jsresCfg.setCheckStatusId("Passed");
                    jsresCfg.setCheckStatusName("审核通过");
                    jsresCfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                    jsResPowerCfgBiz.save(jsresCfg);
                }
            }
        }
        if(result>0){
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
        }else{
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
        }
    }

    @RequestMapping(value = {"/resetTeachingAudit"}, method = RequestMethod.POST)
    @ResponseBody
    public String resetTeachingAudit(String userFlow, Model model) {
        int result = 0;
        if (StringUtil.isNotBlank(userFlow)) {
            SysUser sysUser = userBiz.readSysUser(userFlow);
            sysUser.setAuditStatus("待审核");
            sysUser.setStatusId(UserStatusEnum.Activated.getId());
            sysUser.setStatusDesc(UserStatusEnum.Activated.getName());
            result = userBiz.updateUser(sysUser);
        }
        if(result>0){
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
        }else{
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
        }
    }


    @RequestMapping(value = {"/edit"})
    public String edit(String userFlow, String currentPage, Model model) {
        model.addAttribute("currentPage", currentPage);
        SysUser logiunUser = GlobalContext.getCurrentUser();
        List<SysDept> depts = deptBiz.searchDeptByOrg(logiunUser.getOrgFlow());
        model.addAttribute("depts", depts);
        List<NurseStatusEnum> nurseStatusEnums = new ArrayList<>();
        for (NurseStatusEnum value : NurseStatusEnum.values()) {
            nurseStatusEnums.add(value);
        }
        model.addAttribute("nurseStatusEnums", nurseStatusEnums);
        SysRole sysRole = roleBiz.selectByRoleName("护士");
        model.addAttribute("sysRole", sysRole);
        if (StringUtil.isNotBlank(userFlow)) {
            SysUser sysUser = userBiz.readSysUser(userFlow);
            model.addAttribute("sysUser", sysUser);
            String tchRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
            String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");
            String isTeacher = com.pinde.core.common.GlobalConstant.FLAG_N;
            boolean isTeach = false;
            if (StringUtil.isNotBlank(tchRoleFlow) || StringUtil.isNotBlank(headRoleFlow)) {
                List<SysRole> userRoles = roleBiz.search(userFlow, com.pinde.core.common.GlobalConstant.RES_WS_ID);
                if (userRoles != null && userRoles.size() > 0) {
                    for (SysRole userRole : userRoles) {
                        String roleFlow = userRole.getRoleFlow();
                        if (tchRoleFlow.equals(roleFlow) || headRoleFlow.equals(roleFlow)) {
                            isTeacher = com.pinde.core.common.GlobalConstant.FLAG_Y;
                        }
                        if (tchRoleFlow.equals(roleFlow)) {
                            isTeach = true;
                        }
                    }
                }
            }
            model.addAttribute("isTeacher", isTeacher);
            model.addAttribute("isTeach", isTeach);
            SysDept sysDept = new SysDept();
            sysDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            sysDept.setOrgFlow(sysUser.getOrgFlow());
            List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
            model.addAttribute("sysDeptList", sysDeptList);

            List<SysUserDept> userDeptList = userBiz.getUserDept(sysUser);
            Map<String, String> userDeptMap = new HashMap<String, String>();
            for (SysUserDept userDept : userDeptList) {
                userDeptMap.put(userDept.getDeptFlow(), userDept.getDeptFlow());
            }
            model.addAttribute("userDeptMap", userDeptMap);
            if (sysUser != null && StringUtil.isNotBlank(sysUser.getCertificateFile())) {
                PubFile file = fileBiz.readFile(sysUser.getCertificateFile());
                model.addAttribute("file", file);
            }
        }
        return "/hbres/evaAudit/nurse/edit";
    }

    @RequestMapping(value = {"/save"}, method = RequestMethod.POST)
    @ResponseBody
    public String save(SysUser user, String[] mulDeptFlow, String roleFlow) {
        user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        //新增用户是判断
        if (StringUtil.isBlank(user.getUserFlow())) {
            //判断用户id是否重复
            SysUser old = userBiz.findByUserCode(user.getUserCode());
            if (old != null) {
                return com.pinde.core.common.GlobalConstant.USER_CODE_REPETE;
            }

            if (StringUtil.isNotBlank(user.getUserPhone())) {
                old = userBiz.findByUserPhone(user.getUserPhone());
                if (old != null) {
                    return com.pinde.core.common.GlobalConstant.USER_PHONE_REPETE;
                }
            }

        } else {
            String userFlow = user.getUserFlow();
            //判断用户id是否重复
            SysUser old = userBiz.findByUserCodeNotSelf(userFlow, user.getUserCode());
            if (old != null) {
                return com.pinde.core.common.GlobalConstant.USER_CODE_REPETE;
            }

            if (StringUtil.isNotBlank(user.getUserPhone())) {
                old = userBiz.findByUserPhoneNotSelf(userFlow, user.getUserPhone());
                if (old != null) {
                    return com.pinde.core.common.GlobalConstant.USER_PHONE_REPETE;
                }
            }

        }

        if (mulDeptFlow == null) {
            return "科室选择不能为空！";
        }

        if (!"gzykdx".equals(GlobalContext.getCurrentWsId())) {
            user.setTitleName(com.pinde.core.common.enums.DictTypeEnum.UserTitle.getDictNameById(user.getTitleId()));
        }
        user.setDegreeName(com.pinde.core.common.enums.DictTypeEnum.UserDegree.getDictNameById(user.getDegreeId()));
        user.setPostName(com.pinde.core.common.enums.DictTypeEnum.UserPost.getDictNameById(user.getPostId()));
        user.setEducationName(com.pinde.core.common.enums.DictTypeEnum.UserEducation.getDictNameById(user.getEducationId()));
        user.setCertificateLevelName(com.pinde.core.common.enums.DictTypeEnum.Certificatelevel.getDictNameById(user.getCertificateLevelId()));
        user.setOrgName(StringUtil.defaultString(InitConfig.getOrgNameByFlow(user.getOrgFlow())));
        user.setDeptFlow(mulDeptFlow[0]);
        user.setDeptName(StringUtil.defaultString(InitConfig.getDeptNameByFlow(user.getDeptFlow())));


        if (StringUtil.isNotBlank(roleFlow)) {
            userBiz.saveUser(user, roleFlow);
        } else {
            userBiz.saveUser(user);
        }
        //处理多部门选择
        List<String> allDeptFlows = new ArrayList<String>();
        if (mulDeptFlow != null) {
            allDeptFlows.addAll(Arrays.asList(mulDeptFlow));
        }
        if (StringUtil.isNotBlank(user.getDeptFlow()) && !allDeptFlows.contains(user.getDeptFlow())) {
            allDeptFlows.add(user.getDeptFlow());
        }
        if (allDeptFlows.size() > 0) {
            userBiz.addUserDept(user, allDeptFlows);
        } else {
            userBiz.disUserDept(user);
        }
        //如果当前用户修改自己的信息，同步到session
        SysUser currUser = GlobalContext.getCurrentUser();
        if (currUser.getUserFlow().equals(user.getUserFlow())) {
            currUser = userBiz.readSysUser(user.getUserFlow());
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, currUser);
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER_NAME, user.getUserName());

            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_DEPT_LIST, userBiz.getUserDept(currUser));
        }
        String wsId = (String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
        String[] roleFlows = {roleFlow};
        userRoleBiz.saveAllot(user.getUserFlow(), GlobalContext.getCurrentUser().getOrgFlow(), wsId==null?"res":wsId, roleFlows);
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping(value = {"/teachingSave"}, method = RequestMethod.POST)
    @ResponseBody
    public String teachingSave(SysUser user, String[] mulDeptFlow, String roleFlow) {
        user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        //新增用户是判断
        if (StringUtil.isBlank(user.getUserFlow())) {
            //判断用户id是否重复
            SysUser old = userBiz.findByUserCode(user.getUserCode());
            if (old != null) {
                return com.pinde.core.common.GlobalConstant.USER_CODE_REPETE;
            }

            if (StringUtil.isNotBlank(user.getUserPhone())) {
                old = userBiz.findByUserPhone(user.getUserPhone());
                if (old != null) {
                    return com.pinde.core.common.GlobalConstant.USER_PHONE_REPETE;
                }
            }

        } else {
            String userFlow = user.getUserFlow();
            //判断用户id是否重复
            SysUser old = userBiz.findByUserCodeNotSelf(userFlow, user.getUserCode());
            if (old != null) {
                return com.pinde.core.common.GlobalConstant.USER_CODE_REPETE;
            }

            if (StringUtil.isNotBlank(user.getUserPhone())) {
                old = userBiz.findByUserPhoneNotSelf(userFlow, user.getUserPhone());
                if (old != null) {
                    return com.pinde.core.common.GlobalConstant.USER_PHONE_REPETE;
                }
            }

        }

        if (!"gzykdx".equals(GlobalContext.getCurrentWsId())) {
            user.setTitleName(com.pinde.core.common.enums.DictTypeEnum.UserTitle.getDictNameById(user.getTitleId()));
        }
        user.setDegreeName(com.pinde.core.common.enums.DictTypeEnum.UserDegree.getDictNameById(user.getDegreeId()));
        user.setPostName(com.pinde.core.common.enums.DictTypeEnum.UserPost.getDictNameById(user.getPostId()));
        user.setEducationName(com.pinde.core.common.enums.DictTypeEnum.UserEducation.getDictNameById(user.getEducationId()));
        user.setCertificateLevelName(com.pinde.core.common.enums.DictTypeEnum.Certificatelevel.getDictNameById(user.getCertificateLevelId()));
        user.setOrgName(StringUtil.defaultString(InitConfig.getOrgNameByFlow(user.getOrgFlow())));
        user.setDeptFlow(mulDeptFlow[0]);
        user.setDeptName(StringUtil.defaultString(InitConfig.getDeptNameByFlow(user.getDeptFlow())));
        user.setStatusId(NurseStatusEnum.Activated.getId());
        user.setStatusDesc(NurseStatusEnum.Activated.getName());
        user.setAuditStatus("待审核");


        if (StringUtil.isNotBlank(roleFlow)) {
            userBiz.saveUser(user, roleFlow);
        } else {
            userBiz.saveUser(user);
        }
        //处理多部门选择
        List<String> allDeptFlows = new ArrayList<String>();
        if (mulDeptFlow != null) {
            allDeptFlows.addAll(Arrays.asList(mulDeptFlow));
        }
        if (StringUtil.isNotBlank(user.getDeptFlow()) && !allDeptFlows.contains(user.getDeptFlow())) {
            allDeptFlows.add(user.getDeptFlow());
        }
        if (allDeptFlows.size() > 0) {
            userBiz.addUserDept(user, allDeptFlows);
        } else {
            userBiz.disUserDept(user);
        }
        //如果当前用户修改自己的信息，同步到session
        SysUser currUser = GlobalContext.getCurrentUser();
        if (currUser.getUserFlow().equals(user.getUserFlow())) {
            currUser = userBiz.readSysUser(user.getUserFlow());
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, currUser);
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER_NAME, user.getUserName());

            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_DEPT_LIST, userBiz.getUserDept(currUser));
        }
        String wsId = (String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
        String[] roleFlows = {roleFlow};
        userRoleBiz.saveAllot(user.getUserFlow(), GlobalContext.getCurrentUser().getOrgFlow(), wsId==null?"res":wsId, roleFlows);
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping(value = "/resetPasswd", method = RequestMethod.GET)
    public @ResponseBody
    String resetPasswd(SysUser user) {
        SysUser sysuser = userBiz.readSysUser(user.getUserFlow());
        user.setUserPasswd(PasswordHelper.encryptPassword(sysuser.getUserFlow(), InitConfig.getInitPass()));
        userBiz.saveUser(user);
        return com.pinde.core.common.GlobalConstant.RESET_SUCCESSED;
    }


    @RequestMapping(value = "/activate", method = RequestMethod.GET)
    public @ResponseBody
    String activate(SysUser user) {
        this.userBiz.activateUser(user);
        return com.pinde.core.common.GlobalConstant.UnLOCK_SUCCESSED;
    }

    @RequestMapping(value = "/lock", method = RequestMethod.GET)
    public @ResponseBody
    String lock(SysUser user) {
        SysUser sysuser = userBiz.readSysUser(user.getUserFlow());
        user.setStatusId(UserStatusEnum.Locked.getId());
        user.setStatusDesc(UserStatusEnum.Locked.getName());
        userBiz.saveUser(user);
        return com.pinde.core.common.GlobalConstant.LOCK_SUCCESSED;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody
    String delete(SysUser user, String wsid) {
        user.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        userBiz.saveUser(user);
        return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
    }

    @RequestMapping(value = "/exportUserlist", method = {RequestMethod.POST, RequestMethod.GET})
    public void exportUserlist(SysUser search, String roleFlow, String deptFlow, HttpServletResponse response) throws Exception {
        String wsId = (String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
        //角色列表
        Map<String, SysRole> sysRoleMap = new HashMap<String, SysRole>();
        SysRole sysRole = new SysRole();
        sysRole.setWsId(wsId);
        List<SysRole> sysRoleList = roleBiz.search(sysRole, null);
        if (sysRoleList != null && sysRoleList.size() > 0) {
            for (SysRole sr : sysRoleList) {
                sysRoleMap.put(sr.getRoleFlow(), sr);
            }
        }
        List<SysUser> sysUserList = null;//初始化查询结果
        //组织用户查询map
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("roleFlow", roleBiz.selectByRoleName("护士").getRoleFlow());
        paramMap.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
        paramMap.put("wsId", "res");
        if (search != null) {
            paramMap.put("userPhone", search.getUserPhone());
            paramMap.put("userName", search.getUserName());
            paramMap.put("statusId", search.getStatusId());
        }
        if (StringUtil.isNotBlank(deptFlow)) {
            paramMap.put("deptFlow", deptFlow);
        }
        sysUserList = userBiz.searchUserWithRole(paramMap);
        //查询多科室
        if(CollectionUtils.isNotEmpty(sysUserList)){
            for (SysUser user : sysUserList) {
                List<SysUserDept> userDept = userBiz.getUserDept(user);
                if(CollectionUtils.isNotEmpty(userDept)){
                    List<String> collect = userDept.stream().map(SysUserDept::getDeptName).collect(Collectors.toList());
                    user.setDeptName(collect.toString().replace("[","").replace("]",""));
                }
            }
        }
        String[] titles = new String[]{
                "userCode:登录名",
                "deptName:科室",
                "userName:姓名",
                "userPhone:联系方式",
                "statusDesc:状态"
        };
        String fileName = "护士用户信息导出.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjsAllString(titles, sysUserList, response.getOutputStream());
    }

    /**
     * 护士导入
     *
     * @param deptFlow
     * @return
     */
    @RequestMapping(value = "/importUsers")
    public String importUsers(String deptFlow) {
        return "/hbres/evaAudit/nurse/importUsers";
    }

    /**
     * 带教导入
     *
     * @param deptFlow
     * @return
     */
    @RequestMapping(value = "/importTeachings")
    public String importTeachings(String deptFlow) {
        return "/hbres/evaAudit/nurse/importTeachings";
    }

    /**
     * 护士导入
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/importUsersFromExcel")
    @ResponseBody
    public String importUsersFromExcel(MultipartFile file) {
        if (file.getSize() > 0) {
            try {
                int result = importNursesFromExcel(file);
                if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + result + "条记录！";
                } else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                }
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
            }
        }
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
    }

    public int importNursesFromExcel(MultipartFile file) {
        InputStream is = null;
        try {
            is =  file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
            return parseExcel(wb);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally{
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    private Workbook createCommonWorkbook(InputStream inS) throws IOException, InvalidFormatException {
        // 首先判断流是否支持mark和reset方法，最后两个if分支中的方法才能支持
        if (!inS.markSupported()) {
            // 还原流信息
            inS = new PushbackInputStream(inS);
        }
        //        // EXCEL2003使用的是微软的文件系统
//        if (POIFSFileSystem.hasPOIFSHeader(inS)) {
//            return new HSSFWorkbook(inS);
//        }
//        // EXCEL2007使用的是OOM文件格式
//        if (POIXMLDocument.hasOOXMLHeader(inS)) {
//            // 可以直接传流参数，但是推荐使用OPCPackage容器打开
//            return new XSSFWorkbook(OPCPackage.open(inS));
//        }
        try{
            return WorkbookFactory.create(inS);
        }catch (Exception e) {
            throw new IOException("不能解析的excel版本");
        }
    }

    private int parseExcel(Workbook wb){
        int count = 0;
        int sheetNum = wb.getNumberOfSheets();
        String[] mulDeptFlow = new String[1];
        if(sheetNum>0){
            List<String> colnames = new ArrayList<String>();
            Sheet sheet;
            try{
                sheet = wb.getSheetAt(0);
            }catch(Exception e){
                sheet = wb.getSheetAt(0);
            }
            int row_num = sheet.getLastRowNum();
            //获取表头
            Row titleR =  sheet.getRow(0);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            String title = "";
            for(int i = 0 ; i <cell_num; i++){
                title = titleR.getCell(i).getStringCellValue();
                colnames.add(title);
            }
            for(int i = 1;i <= row_num; i++){
                Row r =  sheet.getRow(i);
                SysUser sysUser = new SysUser();
                String userName;
                String userPhone;
                String deptName;
                String statusId;
                String userCode;
                for (int j = 0; j < colnames.size(); j++) {
                    String value = "";
                    Cell cell = r.getCell(j);
                    if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
                        if (cell.getCellType().getCode() == 1) {
                            value = cell.getStringCellValue().trim();
                        } else {
                            value = _doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }
                    /* 姓名 联系方式 机构编号	机构名称	科室编号	科室名称	职务 登录名 状态 */
                    if("姓名".equals(colnames.get(j))){
                        userName = value;
                        sysUser.setUserName(userName);
                    }else if("联系方式".equals(colnames.get(j))){
                        userPhone = value;
                        sysUser.setUserPhone(userPhone);
                    }else if("科室名称".equals(colnames.get(j))){
                        deptName = value;
                        SysDept sysDept = deptBiz.readSysDeptByName(GlobalContext.getCurrentUser().getOrgFlow(),deptName);
                        if (sysDept == null) {
                            throw new RuntimeException("导入失败！第"+ (count+2) +"行，【"+deptName+"科室】不属于该机构！");
                        }
                        if (sysDept != null) {
                            mulDeptFlow = new String[]{sysDept.getDeptFlow()};
                        }
                    }else if("状态".equals(colnames.get(j))){
                        if (value.equals(NurseStatusEnum.Activated.getName())) {
                            statusId = NurseStatusEnum.Activated.getId();
                        } else {
                            statusId = NurseStatusEnum.Locked.getId();
                        }
                        sysUser.setStatusId(statusId);
                    }else if("登录名".equals(colnames.get(j))){
                        userCode = value;
                        sysUser.setUserCode(userCode);
                    }
                }

                if(StringUtil.isNotBlank(sysUser.getOrgFlow())&& StringUtil.isNotBlank(sysUser.getDeptName()))
                {
                    SysDept dept=deptBiz.readSysDeptByName(sysUser.getOrgFlow(),sysUser.getDeptName());
                    if(dept==null)
                        throw new RuntimeException("导入失败！第"+ (count+2) +"行，【"+sysUser.getDeptName()+"】不属于【"+sysUser.getOrgName()+"】机构！");
                    sysUser.setDeptFlow(dept.getDeptFlow());
                }
                if(StringUtil.isBlank(sysUser.getUserCode())){
                    throw new RuntimeException("导入失败！第"+ (count+2) +"行，登录名不能为空！");
                }
                //验证惟一用户登录名
                if(StringUtil.isNotBlank(sysUser.getUserCode())){
                    SysUserExample example=new SysUserExample();
                    example.createCriteria().andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow()).andUserCodeEqualTo(sysUser.getUserCode()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                    List<SysUser> sysUserList = sysUserMapper.selectByExample(example);
                    if(sysUserList != null && !sysUserList.isEmpty()){
                        throw new RuntimeException("导入失败！第"+(count+2) +"行，当前系统已存在登录名为"+sysUser.getUserCode()+"的用户");
                    }
                }
                if (StringUtil.isNotBlank(sysUser.getUserPhone())) {
                    SysUserExample example=new SysUserExample();
                    example.createCriteria().andUserPhoneEqualTo(sysUser.getUserPhone()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                    List<SysUser> sysUserList = sysUserMapper.selectByExample(example);
                    if(sysUserList != null && !sysUserList.isEmpty()){
                        throw new RuntimeException("导入失败！第"+(count+2) +"行，当前系统已存在手机号为"+sysUser.getUserPhone()+"的用户");
                    }
                }
                //执行保存
                save(sysUser, mulDeptFlow, "f3a5d27c097b49d098ed2f37ba11b7c2");
                count ++;
            }
        }
        return count;
    }

    public static String _doubleTrans(double d) {
        if ((double) Math.round(d) - d == 0.0D)
            return String.valueOf((long) d);
        else
            return String.valueOf(d);
    }

    /**
     * 带教导入
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/importTeachingFromExcel")
    @ResponseBody
    public String importTeachingFromExcel(MultipartFile file) {
        SysUser loginUser= GlobalContext.getCurrentUser();
        if (file.getSize() > 0) {
            try {
                int result = userBiz.importTeachingFromExcel(file,loginUser);
                if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + result + "条记录！";
                } else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                }
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
            }
        }
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
    }


    /**
     * 护士评价学员
     *
     * @param model
     * @param doctor
     * @param
     * @return
     */
    @RequestMapping(value = "/nurseEvaluation", method = {RequestMethod.GET, RequestMethod.POST})
    public String nurseEvaluation(Model model, ResDoctor doctor, String assessStatusId,
                                  String recTypeId, Integer currentPage, HttpServletRequest request, String[] datas) {
        String roleFlog = "nurse";
        String dataStr = "";
        List<String> docTypeList = new ArrayList<>();
        if (datas != null && datas.length > 0) {
            docTypeList = Arrays.asList(datas);
            for (String d : datas) {
                dataStr += d + ",";
            }
        }
        model.addAttribute("dataStr", dataStr);
        String userFlow = GlobalContext.getCurrentUser().getUserFlow();
        if (currentPage == null) {
            currentPage = 1;
        }
        model.addAttribute("datas", datas);
        if (StringUtil.isNotBlank(recTypeId)) {
            model.addAttribute("recTypeId", recTypeId);
        }
        //权限期间是否开通
        String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
        List<ResDoctorSchProcess> processList = null;
        Map<String, Object> param = new HashMap<>();
        param.put("assessStatusId",assessStatusId);
        param.put("roleFlog",roleFlog);
        param.put("isOpen", isOpen);
        param.put("doctor", doctor);
        //护士多科室
        List<SysUserDept> userDeptList = userBiz.getUserDept(GlobalContext.getCurrentUser());
        if(CollectionUtils.isNotEmpty(userDeptList)){
            param.put("deptFlows",userDeptList.stream().map(SysUserDept::getDeptFlow).collect(Collectors.toList()));
        }else{
            param.put("deptFlow", GlobalContext.getCurrentUser().getDeptFlow());
        }
        param.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
        param.put("docTypeList", docTypeList);
        param.put("userFlow", GlobalContext.getCurrentUser().getUserFlow());
        PageHelper.startPage(currentPage, getPageSize(request));
        processList = resDoctorProcessBiz.selectProcessByDoctorNew(param);

        if (processList != null && processList.size() > 0) {
            model.addAttribute("processList", processList);
            List<String> userFlows = new ArrayList<String>();
            Map<String, SchArrangeResult> resultMapMap = new HashMap<String, SchArrangeResult>();
            Map<String, PubFile> fileMap = new HashMap<String, PubFile>();
            for (ResDoctorSchProcess rdsp : processList) {
                if (!userFlows.contains(rdsp.getUserFlow()))
                    userFlows.add(rdsp.getUserFlow());

                String resultFlow = rdsp.getSchResultFlow();
                SchArrangeResult result = resultBiz.readSchArrangeResult(resultFlow);
                resultMapMap.put(rdsp.getProcessFlow(), result);
                if (result != null) {
                    PubFile file = fileBiz.readFile(result.getAfterFileFlow());
                    fileMap.put(rdsp.getProcessFlow(), file);
                }
                List<String> recTypeIds = new ArrayList<String>();
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.NurseDoctorGrade.getId());
                Map<String, Object> paramMap = new HashMap();
                paramMap.put("recTypeIds", recTypeIds);
                paramMap.put("processFlow", rdsp.getProcessFlow());
                paramMap.put("currentUserFlow", GlobalContext.getCurrentUser().getUserFlow());
                List<DeptTeacherGradeInfo> gradeInfoList = resGradeBiz.searchResGradeByItems(paramMap);
                if (gradeInfoList != null && gradeInfoList.size() > 0) {
                    for (DeptTeacherGradeInfo tempGradeInfo : gradeInfoList) {
                        String typeId = tempGradeInfo.getRecTypeId();
                        model.addAttribute(rdsp.getProcessFlow()+typeId, tempGradeInfo);
                        Map<String, Object> gradeMap = resRecBiz.parseGradeXml(tempGradeInfo.getRecContent());
                        model.addAttribute(typeId + rdsp.getProcessFlow(), gradeMap);
                    }
                }
            }

            model.addAttribute("resultMap", resultMapMap);
            model.addAttribute("fileMap", fileMap);

            List<SysUser> userList = userBiz.searchSysUserByuserFlows(userFlows);
            if (userList != null && userList.size() > 0) {
                Map<String, SysUser> userMap = new HashMap<String, SysUser>();
                for (SysUser su : userList) {
                    userMap.put(su.getUserFlow(), su);
                }
                model.addAttribute("userMap", userMap);
            }

            List<ResDoctor> doctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
            if (doctorList != null && doctorList.size() > 0) {
                Map<String, ResDoctor> doctorMap = new HashMap<String, ResDoctor>();
                for (ResDoctor rd : doctorList) {
                    doctorMap.put(rd.getDoctorFlow(), rd);
                }
                model.addAttribute("doctorMap", doctorMap);
            }
        }
        return "/hbres/evaAudit/nurse/nurseEvaluationList";
    }

    /**
     * 评分
     * @param recFlow
     * @param recTypeId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/grade",method={RequestMethod.GET})
    public String grade(String recFlow, String recTypeId, String doctorFlow, Model model) throws Exception{
        SysUser currUser = GlobalContext.getCurrentUser();
        ResDoctor resDoctor = doctorBiz.readDoctor(doctorFlow);
        ResDoctorRecruit newRecruit = recruitBiz.getNewRecruit(doctorFlow);
        model.addAttribute("resDoctor",resDoctor);
        model.addAttribute("recruit",newRecruit);
        if(StringUtil.isNotBlank(recTypeId) && currUser!=null){
            String cfgCodeId = null;
            if (com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId().equals(recTypeId)) {
                cfgCodeId = ResAssessTypeEnum.TeacherAssess.getId();
            } else if (com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId().equals(recTypeId)) {
                cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
            } else if (com.pinde.core.common.enums.ResRecTypeEnum.ManagerGrade.getId().equals(recTypeId)) {
                cfgCodeId = ResAssessTypeEnum.ManagerAssess.getId();
            } else if (com.pinde.core.common.enums.ResRecTypeEnum.NurseDoctorGrade.getId().equals(recTypeId)) {
                cfgCodeId = ResAssessTypeEnum.NurseDoctorAssess.getId();
            }

            DeptTeacherGradeInfo deptTeacherGradeInfo = resGradeBiz.readResGrade(recFlow);
            if(deptTeacherGradeInfo != null){
                String modifyTime = deptTeacherGradeInfo.getModifyTime();
                if(StringUtil.isNotBlank(modifyTime)){
                    String yyyyMMdd = DateUtil.transDate(modifyTime, "yyyyMMdd");
                    model.addAttribute("openTime",yyyyMMdd.substring(0,4)+"年"+yyyyMMdd.substring(4,6)+"月"+yyyyMMdd.substring(6,yyyyMMdd.length())+"日");
                }
                model.addAttribute("rec",deptTeacherGradeInfo);
                Map<String,Object> gradeMap = resRecBiz.parseGradeXml(deptTeacherGradeInfo.getRecContent());
                model.addAttribute("gradeMap",gradeMap);
                ResAssessCfg assessCfg = assessCfgBiz.readResAssessCfg(deptTeacherGradeInfo.getCfgFlow());
                getForm(model, assessCfg);
            }else {
                ResAssessCfg search = new ResAssessCfg();
                search.setCfgCodeId(cfgCodeId);
                search.setFormStatusId(com.pinde.core.common.GlobalConstant.FLAG_Y);
                List<ResAssessCfg> resAssessCfgList = assessCfgBiz.selectByExampleWithBLOBs(search);
                model.addAttribute("formCount",resAssessCfgList.size());
                if(resAssessCfgList != null && !resAssessCfgList.isEmpty()){
                    ResAssessCfg assessCfg = resAssessCfgList.get(0);
                    getForm(model, assessCfg);
                }
            }
        }
        return "/hbres/evaAudit/nurse/grade";
    }

    @RequestMapping(value="/operNurseDetail",method={RequestMethod.GET})
    public String operNurseDetail(String recFlow, String recTypeId, String doctorFlow, Model model) throws Exception{
        SysUser currUser = GlobalContext.getCurrentUser();
        ResDoctor resDoctor = doctorBiz.readDoctor(doctorFlow);
        ResDoctorRecruit newRecruit = recruitBiz.getNewRecruit(doctorFlow);
        model.addAttribute("resDoctor",resDoctor);
        model.addAttribute("recruit",newRecruit);
        if(StringUtil.isNotBlank(recTypeId) && currUser!=null){
            String cfgCodeId = null;
            if (com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId().equals(recTypeId)) {
                cfgCodeId = ResAssessTypeEnum.TeacherAssess.getId();
            } else if (com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId().equals(recTypeId)) {
                cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
            } else if (com.pinde.core.common.enums.ResRecTypeEnum.ManagerGrade.getId().equals(recTypeId)) {
                cfgCodeId = ResAssessTypeEnum.ManagerAssess.getId();
            } else if (com.pinde.core.common.enums.ResRecTypeEnum.NurseDoctorGrade.getId().equals(recTypeId)) {
                cfgCodeId = ResAssessTypeEnum.NurseDoctorAssess.getId();
            }

            DeptTeacherGradeInfo deptTeacherGradeInfo = resGradeBiz.readResGrade(recFlow);
            if(deptTeacherGradeInfo != null){
                String modifyTime = deptTeacherGradeInfo.getModifyTime();
                if(StringUtil.isNotBlank(modifyTime)){
                    String yyyyMMdd = DateUtil.transDate(modifyTime, "yyyyMMdd");
                    model.addAttribute("openTime",yyyyMMdd.substring(0,4)+"年"+yyyyMMdd.substring(4,6)+"月"+yyyyMMdd.substring(6,yyyyMMdd.length())+"日");
                }
                model.addAttribute("rec",deptTeacherGradeInfo);
                Map<String,Object> gradeMap = resRecBiz.parseGradeXml(deptTeacherGradeInfo.getRecContent());
                model.addAttribute("gradeMap",gradeMap);
                ResAssessCfg assessCfg = assessCfgBiz.readResAssessCfg(deptTeacherGradeInfo.getCfgFlow());
                getForm(model, assessCfg);
            }else {
                ResAssessCfg search = new ResAssessCfg();
                search.setCfgCodeId(cfgCodeId);
                search.setFormStatusId(com.pinde.core.common.GlobalConstant.FLAG_Y);
                List<ResAssessCfg> resAssessCfgList = assessCfgBiz.selectByExampleWithBLOBs(search);
                model.addAttribute("formCount",resAssessCfgList.size());
                if(resAssessCfgList != null && !resAssessCfgList.isEmpty()){
                    ResAssessCfg assessCfg = resAssessCfgList.get(0);
                    getForm(model, assessCfg);
                }
            }
        }
        return "/hbres/evaAudit/nurse/operNurseDetail";
    }

    private void getForm(Model model, ResAssessCfg assessCfg) throws DocumentException {
        model.addAttribute("assessCfg", assessCfg);
        Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
        String titleXpath = "//title";
        List<Element> titleElementList = dom.selectNodes(titleXpath);
        if (titleElementList != null && !titleElementList.isEmpty()) {
            List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
            for (Element te : titleElementList) {
                ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
                titleForm.setType(te.attributeValue("type"));
                titleForm.setRow(te.attributeValue("row"));
                titleForm.setId(te.attributeValue("id"));
                titleForm.setName(te.attributeValue("name"));
                List<Element> itemElementList = te.elements("item");
                List<ResAssessCfgItemForm> itemFormList = null;
                if (itemElementList != null && !itemElementList.isEmpty()) {
                    itemFormList = new ArrayList<ResAssessCfgItemForm>();
                    for (Element ie : itemElementList) {
                        ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
                        itemForm.setId(ie.attributeValue("id"));
                        itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                        itemForm.setType(ie.element("type") == null ? "" : ie.element("type").getTextTrim());
                        itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
                        itemForm.setRow(ie.element("row") == null ? "" : ie.element("row").getTextTrim());
                        itemFormList.add(itemForm);
                    }
                }
                titleForm.setItemList(itemFormList);
                titleFormList.add(titleForm);
            }
            model.addAttribute("titleFormList", titleFormList);
        }
    }

    /**
     * 保存评分
     * @param deptTeacherGradeInfo
     * @param request
     * @return
     */
    @RequestMapping(value="/saveGrade",method={RequestMethod.POST})
    @ResponseBody
    public String saveGrade(DeptTeacherGradeInfo deptTeacherGradeInfo, HttpServletRequest request){
        String recContent = createGradeXml(request.getParameterMap(), "");
        if(deptTeacherGradeInfo != null){
            if(StringUtil.isBlank(deptTeacherGradeInfo.getRecFlow())){
                SysUser user = GlobalContext.getCurrentUser();
                deptTeacherGradeInfo.setOperUserFlow(user.getUserFlow());
                deptTeacherGradeInfo.setOperUserName(user.getUserName());
                deptTeacherGradeInfo.setOperTime(DateUtil.getCurrDateTime());
                deptTeacherGradeInfo.setStatusId(RecStatusEnum.Submit.getId());
                deptTeacherGradeInfo.setStatusName(RecStatusEnum.Submit.getName());
                if(StringUtil.isNotBlank(deptTeacherGradeInfo.getSchDeptFlow())){
                    SchDept schDept = schDeptBiz.readSchDept(deptTeacherGradeInfo.getSchDeptFlow());
                    if(schDept!=null){
                        deptTeacherGradeInfo.setSchDeptName(schDept.getSchDeptName());
                        deptTeacherGradeInfo.setSchDeptFlow(schDept.getSchDeptFlow());
                        deptTeacherGradeInfo.setOrgFlow(schDept.getOrgFlow());
                        deptTeacherGradeInfo.setOrgName(schDept.getOrgName());
                        deptTeacherGradeInfo.setDeptFlow(schDept.getDeptFlow());
                        deptTeacherGradeInfo.setDeptName(schDept.getDeptName());
                    }
                }

                if(StringUtil.isNotBlank(deptTeacherGradeInfo.getRecTypeId())){
                    deptTeacherGradeInfo.setRecTypeName(com.pinde.core.common.enums.ResRecTypeEnum.getNameById(deptTeacherGradeInfo.getRecTypeId()));
                }
            }

            deptTeacherGradeInfo.setRecContent(recContent);
        }
        String allScore = recContent.split("<totalScore>")[1].split("</totalScore>")[0];
        deptTeacherGradeInfo.setAllScore(allScore);
        if (resGradeBiz.edit(deptTeacherGradeInfo) != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return deptTeacherGradeInfo.getRecFlow();
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }

    private String createGradeXml(Map<String, String[]> gradeInfo, String roleFlag){
        Document document = DocumentHelper.createDocument();
        String name="gradeInfo";
        if(StringUtil.isNotBlank(roleFlag))
        {
            name=roleFlag+"GradeInfo";
        }
        Element rootEle = document.addElement(name);
        if(gradeInfo!=null){
            String[] assessIds = gradeInfo.get("assessId");
            String[] scores = gradeInfo.get("score");
            String[] lostReasons = gradeInfo.get("lostReason");
            if(assessIds!=null && assessIds.length>0){
                for(int i = 0 ; i<assessIds.length ; i++){
                    String assessId = assessIds[i];
                    String score = scores[i];
                    Element item = rootEle.addElement("grade");
                    item.addAttribute("assessId",assessId);

                    Element scoreElement = item.addElement("score");
                    scoreElement.setText(score);
//                    Element lostReasonElement = item.addElement("lostReason");
//                    lostReasonElement.setText(lostReason);
                }
            }

            String[] totalScore = gradeInfo.get("totalScore");
            String[] lostReason = gradeInfo.get("lostReason");
            if(totalScore!=null && totalScore.length>0 && StringUtil.isNotBlank(totalScore[0])){
                Element item = rootEle.addElement("totalScore");
                item.setText(totalScore[0]);
            }
            if(lostReason!=null && lostReason.length>0 && StringUtil.isNotBlank(lostReason[0])){
                Element item = rootEle.addElement("lostReason");
                item.setText(lostReason[0]);
            }
        }
        return document.asXML();
    }

    /**
     * 提交评分
     * @param deptTeacherGradeInfo
     * @return
     */
    @RequestMapping(value="/opreResRecForGrade",method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String opreResRecForGrade(DeptTeacherGradeInfo deptTeacherGradeInfo){
        String deleteS = com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
        String deleteF = com.pinde.core.common.GlobalConstant.DELETE_FAIL;
        if(deptTeacherGradeInfo != null){
            if(StringUtil.isNotBlank(deptTeacherGradeInfo.getStatusId())){
                deptTeacherGradeInfo.setStatusName(RecStatusEnum.getNameById(deptTeacherGradeInfo.getStatusId()));
                deleteS = com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
                deleteF = com.pinde.core.common.GlobalConstant.OPRE_FAIL;
            }
            if (resGradeBiz.edit(deptTeacherGradeInfo) != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
                return deleteS;
            }
        }
        return deleteF;
    }

}
