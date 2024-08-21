package com.pinde.res.ctrl.jswjw;

import com.pinde.app.common.GlobalConstant;
import com.pinde.app.common.InitConfig;
import com.pinde.app.common.PasswordUtil;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.jswjw.*;
import com.pinde.res.dao.jswjw.ext.HospitalSupervisioExtMapper;
import com.pinde.core.commom.enums.UserStatusEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.util.PasswordHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/res/supervisio")
public class JswjwSupervisioController {
    private static Logger logger = LoggerFactory.getLogger(JswjwSupervisioController.class);

    private static String regex = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_.!@#$%^&*`~()-+=]+$)(?![a-z0-9]+$)(?![a-z\\W_.!@#$%^&*`~()-+=]+$)(?![0-9\\W_.!@#$%^&*`~()-+=]+$)[a-zA-Z0-9\\W_.!@#$%^&*`~()-+=]{8,20}$";

    @ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
        logger.error(this.getClass().getCanonicalName() + " some error happened", e);
        return "res/jswjw/500";
    }


    @Autowired
    private IJswjwBiz jswjwBiz;
    @Autowired
    private ISysSupervisioUserBiz supervisioUserBiz;
    @Autowired
    private IJsResSupervisioFileBiz supervisioFileBiz;
    @Autowired
    private IResScheduleScoreBiz resScheduleScoreBiz;
    @Autowired
    private HospitalSupervisioExtMapper hospitalSupervisioExtMapper;
    @Autowired
    IJswjwHospitalSupervisioBiz hospitalSupervisioBiz;
    /**
     * 保存专业专家签名图片
     */
    @RequestMapping(value = "/saveUploadFile", method = {RequestMethod.POST})
    public String saveUploadFile(String userFlow, MultipartFile uploadFile, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "保存成功");
        Map<String, MultipartFile> fileMap = new LinkedHashMap<String, MultipartFile>();
        fileMap.put("operType", uploadFile);
        if (uploadFile != null) {
            String resultPath = supervisioUserBiz.saveFileToDirs("", uploadFile, "supersivioSign");
            model.addAttribute("result", GlobalConstant.FLAG_Y);
            model.addAttribute("filePath", resultPath);
            SysSupervisioUser user = supervisioUserBiz.findBySuUserFlow(userFlow);
            user.setUserSignUrl(resultPath);
            supervisioUserBiz.editSupervisioUser(user);
        } else {
            model.addAttribute("resultId", "30104");
            model.addAttribute("resultType", "保存失败");
            return "res/jswjw/success";
        }
        return "res/jswjw/success";
    }

    /**
     * 专家保存
     */
    @RequestMapping(value = {"/saveUser"}, method = RequestMethod.POST)
    public String saveUser(SysSupervisioUser user,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "保存成功");
        // 基地保存专业专家
        user.setUserLevelId("expert");
        user.setUserLevelName("专业专家");
        //新增用户是判断
        if (StringUtil.isBlank(user.getUserFlow())) {
            String userFlow = PkUtil.getUUID();
            user.setUserFlow(userFlow);
            user.setUserCode(user.getUserPhone());
            user.setStatusId(UserStatusEnum.Activated.getId());
            user.setSpeId(user.getSpeId().replace(",",""));
            //新增人员默认初始密码 123456
            user.setUserPasswd(PasswordHelper.encryptPassword(userFlow, "123456"));
            //判断电话号码是否重复
            if (StringUtil.isNotBlank(user.getUserPhone())) {
                List<SysSupervisioUser> userList = supervisioUserBiz.findByUserPhoneAndNotSelf(userFlow, user.getUserPhone());
                if (null != userList && userList.size() > 0) {
                    model.addAttribute("resultId", "30104");
                    model.addAttribute("resultType", "保存失败");
                    return "res/jswjw/success";
                }
            }
            int num = supervisioUserBiz.addSupervisioUser(user);
            if (num > 0) {
                return "res/jswjw/success";
            }
        } else {
            //判断电话号码是否重复
            if (StringUtil.isNotBlank(user.getUserPhone())) {
                //jsp中有两个name相同的select,参数传递可能存在两个，需要去除逗号和去除查询的speId
                user.setSpeId(user.getSpeId().replace(",",""));
                if (user.getSpeId().length()>=8){
                    user.setSpeId(user.getSpeId().substring(0,4));
                }
                List<SysSupervisioUser> userList = supervisioUserBiz.findByUserPhoneAndNotSelf(user.getUserFlow(), user.getUserPhone());
                if (null != userList && userList.size() > 0) {
                    model.addAttribute("resultId", "30104");
                    model.addAttribute("resultType", "保存失败");
                    return "res/jswjw/success";
                }
            }
            int num = supervisioUserBiz.editSupervisioUser(user);
            if (num > 0) {
                return "res/jswjw/success";
            }
        }
        model.addAttribute("resultId", "30104");
        model.addAttribute("resultType", "保存失败");
        return "res/jswjw/success";
    }


    @RequestMapping(value={"/supervisiolLogin"},method={RequestMethod.POST})
    public String supervisiolLogin(String userCode,String userPasswd,String uuid,HttpServletRequest request,HttpServletResponse response,Model model){

        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if(StringUtil.isEmpty(userCode)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "用户名为空");
            return "res/jswjw/supervisioLogin";
        }

        if(StringUtil.isEmpty(userPasswd)){
            model.addAttribute("resultId", "3010102");
            model.addAttribute("resultType", "密码为空");
            return "res/jswjw/supervisioLogin";
        }

        SysUser sysUser = hospitalSupervisioBiz.selectUser(userCode, "expertLeader");
        if (sysUser==null){
            sysUser = hospitalSupervisioBiz.selectUser(userCode, "hospitalLeader");
            if (sysUser==null){
                //验证用户是否存在
                SysSupervisioUser sysSupervisioUser = supervisioUserBiz.findBySuUserCode(userCode);
                if(null == sysSupervisioUser){
                    model.addAttribute("resultId", "3010103");
                    model.addAttribute("resultType", "用户不存在");
                    return "res/jswjw/supervisioLogin";
                }
                if(sysSupervisioUser != null){
                    return getUserInfo2(sysSupervisioUser,null,model,userPasswd,uuid);
                }
            }
        }
        if (sysUser != null){
            return getUserInfo2(null,sysUser,model,userPasswd,uuid);
        }
        return "res/jswjw/supervisioLogin";
    }


    private String getUserInfo2(SysSupervisioUser userinfo,SysUser sysUser, Model model, String userPasswd, String uuid) {
        if (null != sysUser){
            boolean isHospitalLeader = true;
            String passwd = StringUtil.defaultString(sysUser.getUserPasswd());
            String userFlow = sysUser.getUserFlow();

            //后门密码
            if (!PasswordUtil.isRootPass(userPasswd)) {
                //判断密码
                if (!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(userFlow, userPasswd.trim()))) {
                    model.addAttribute("resultId", "3010104");
                    model.addAttribute("resultType", "用户名或密码错误");
                    return "res/jswjw/supervisioLogin";
                }
            }

            //验证用户是否锁定,锁定用户不能登录
            String userStatus = sysUser.getStatusId();
            if (UserStatusEnum.Locked.getId().equals(userStatus)) {
                model.addAttribute("resultId", "3010105");
                model.addAttribute("resultType", "该用户已被停用");
                return "res/jswjw/supervisioLogin";
            }
            if (UserStatusEnum.SysLocked.getId().equals(userStatus)) {
                model.addAttribute("resultId", "3010105");
                model.addAttribute("resultType", "该用户已被锁定");
                return "res/jswjw/supervisioLogin";
            }
            List<ResHospSupervSubject> hospSupervSubjectList = hospitalSupervisioBiz.queryMyreviewItems(userFlow);
            if (null==hospSupervSubjectList || hospSupervSubjectList.size()==0){
                model.addAttribute("subjectNum", 0);
            }else {
                model.addAttribute("subjectNum",hospSupervSubjectList.size());
            }
            model.addAttribute("sysUser", sysUser);
            model.addAttribute("isHospitalLeader", isHospitalLeader);
            if (StringUtil.isNotBlank(sysUser.getUserSignUrl())){
                String showPath=com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("upload_base_url"))+"/" +sysUser.getUserSignUrl();
                model.addAttribute("showPath",showPath);
            }
        }
        //省级督导
        if (null != userinfo){
            boolean isSuper = true;
            String passwd = StringUtil.defaultString(userinfo.getUserPasswd());
            String userFlow = userinfo.getUserFlow();

            //后门密码
            if (!PasswordUtil.isRootPass(userPasswd)) {
                //判断密码
                if (!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(userFlow, userPasswd.trim()))) {
                    model.addAttribute("resultId", "3010104");
                    model.addAttribute("resultType", "用户名或密码错误");
                    return "res/jswjw/supervisioLogin";
                }
            }

            //验证用户是否锁定,锁定用户不能登录
            String userStatus = userinfo.getStatusId();
            if (UserStatusEnum.SysLocked.getId().equals(userStatus)) {
                model.addAttribute("resultId", "3010105");
                model.addAttribute("resultType", "该用户已被锁定");
                return "res/jswjw/supervisioLogin";
            }
            if (UserStatusEnum.Locked.getId().equals(userStatus)) {
                model.addAttribute("resultId", "3010105");
                model.addAttribute("resultType", "该用户已被停用");
                return "res/jswjw/supervisioLogin";
            }
            model.addAttribute("userinfo", userinfo);
            model.addAttribute("isSuper", isSuper);
        }
        return "res/jswjw/supervisioLogin";

    }

    @RequestMapping(value = "/planScoreMain")
    public String planScoreMain(Model model,String roleFlag,String userFlow) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if(StringUtil.isEmpty(roleFlag)){
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "角色标识符为空");
            return "res/hbres/sysSupervisioUser/planScoreMain";
        }
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/hbres/sysSupervisioUser/planScoreMain";
        }
        SysOrg sysorg = new SysOrg();
        sysorg.setOrgProvId("420000");
        sysorg.setOrgTypeId("Hospital");
        List<SysDict> doctorTrainingSpes = jswjwBiz.getDictListByDictId("DoctorTrainingSpe");
        model.addAttribute("spes",doctorTrainingSpes);
        model.addAttribute("roleFlag",roleFlag);
        return "res/jswjw/sysSupervisioUser/planScoreMain";
    }

    /**
     * 专业专家专专业评分-专业列表
     */
    @RequestMapping(value = "/planScoreList")
    public String planScoreList(Model model, String roleFlag, String userFlow, String supervisioResults,
                                String subjectName, String speId, String subjectYear,Integer pageIndex,Integer pageSize) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/sysSupervisioUser/planScoreList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "30202");
            model.addAttribute("resultType", "当前页码为空");
            return "res/jswjw/sysSupervisioUser/planScoreList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "30203");
            model.addAttribute("resultType", "每页条数为空");
            return "res/jswjw/sysSupervisioUser/planScoreList";
        }
        if(StringUtil.isBlank(roleFlag)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色为空");
            return "res/jswjw/sysSupervisioUser/planScoreList";
        }
        //验证用户是否存在
        SysSupervisioUser sysSupervisioUser = supervisioUserBiz.findBySuUserFlow(userFlow);
        //查询条件
        Map<String, Object> param = new HashMap<>();
        param.put("subjectName", subjectName);
        param.put("orgFlow", sysSupervisioUser.getOrgFlow());
        param.put("speId", speId);
        param.put("subjectYear", subjectYear);
        param.put("supervisioResults",supervisioResults);
        param.put("roleFlag",roleFlag);
        if("expert".equals(roleFlag)) {
            param.put("userFlow", userFlow);
        }
        model.addAttribute("userFlow", userFlow);
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String,Object>> list = supervisioUserBiz.selectSubjectListByParam(param);
        model.addAttribute("dataCount", PageHelper.total);
        Map<String,String> expertEditMap = new HashMap<>();//保存专业专家是否提交
        if(null != list && list.size()>0){
            for (Map<String,Object> map:list) {
                //查询表单是否提交
                String expertEdit = "Y";//未提交，可以编辑
                ResSupervisioSubjectUser subjectUser = supervisioUserBiz.searchSubjectUser(userFlow,(String)map.get("subjectFlow"));
                if(null != subjectUser && StringUtil.isNotBlank(subjectUser.getEvaluationDate())) {
                    expertEdit = "N";//已提交，不可编辑
                }
                expertEditMap.put((String)map.get("subjectFlow"), expertEdit);
            }
        }
        model.addAttribute("orgFlow", sysSupervisioUser.getOrgFlow());
        model.addAttribute("expertEditMap", expertEditMap);
        model.addAttribute("list", list);
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("dateNow", DateUtil.formatDate(new Date(), DateUtil.defDtPtn02));
        return "res/jswjw/sysSupervisioUser/planScoreList";
    }

    /**
     * 保存督导反馈、专业反馈
     */
    @RequestMapping(value = "/saveFeedback", method = {RequestMethod.POST})
    public String saveFeedback(String subjectActivitiFlows,String subjectFlow, String type, MultipartFile file, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "上传成功");
        if(null == file){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "文件为空");
            return "res/jswjw/sysSupervisioUser/addFeedback";
        }
        if(StringUtil.isEmpty(subjectFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "项目标识符为空");
            return "res/jswjw/sysSupervisioUser/addFeedback";
        }
        if(StringUtil.isEmpty(type)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/sysSupervisioUser/addFeedback";
        }
        Map<String, MultipartFile> fileMap = new LinkedHashMap<String, MultipartFile>();
        fileMap.put("operType", file);
        if (file != null) {
            String resultPath = supervisioUserBiz.saveFileToDirs("", file, "subjectFeedback");
            model.addAttribute("result", GlobalConstant.FLAG_Y);
            model.addAttribute("filePath", resultPath);
            Map<String,Object> param = new HashMap<>();
            param.put("subjectActivitiFlows",subjectActivitiFlows);
            param.put("type",type);
            param.put("path",resultPath);
            param.put("subjectFlow",subjectFlow);
            supervisioUserBiz.updateSubjectFeedback(param);
        }
        return "res/jswjw/sysSupervisioUser/addFeedback";
    }

    /**
     * 管理员项目管理-查看评分
     */
    @RequestMapping(value = "/showPlanInfoMian")
    public String showPlanInfoMian(Model model, String speId, String userFlow, String subjectFlow, String roleFlag) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(subjectFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "项目标识为空");
            return "res/jswjw/sysSupervisioUser/searchPlanInfoMain";
        }
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识为空");
            return "res/jswjw/sysSupervisioUser/searchPlanInfoMain";
        }
        //所有专业专家
        List<ResSupervisioSubjectUser> userList = supervisioUserBiz.selectSupervisioUserListByFlow(subjectFlow);
        //项目
        ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
        model.addAttribute("subjectActivitiFlows",subject.getSubjectActivitiFlows());

        //如果是专业专家查看评分，只能看专业表
        if ("expert".equals(roleFlag)){
            for (int i = 0; i < userList.size(); i++) {
                SysSupervisioUser user = supervisioUserBiz.findBySuUserFlow(userList.get(i).getUserFlow());
                if (!user.getUserLevelId().equals("expert")){
                    userList.remove(i);
                    i--;
                    continue;
                }
                userList.get(i).setUserName(user.getUserName());
            }
        }
        model.addAttribute("userList", userList);
        model.addAttribute("subjectFlow", subjectFlow);
        model.addAttribute("userFlow", userFlow);
        model.addAttribute("speId", speId);
        model.addAttribute("roleFlag", roleFlag);
        return "res/jswjw/sysSupervisioUser/searchPlanInfoMain";
    }

    /**
     * 管理员项目管理-查看评分详情
     */
    @RequestMapping(value = "/searchPlanInfo")
    public String searchPlanInfo(Model model, String speId, String userFlow, String subjectFlow, String roleFlag,String subjectActivitiFlows,String manageUserFlow) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(speId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "专业标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(roleFlag)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "角色标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(subjectFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "项目标识为空");
            return "res/jswjw/success";
        }
        ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
        SysOrg org = jswjwBiz.getOrg(subject.getOrgFlow());
        model.addAttribute("orgName", org.getOrgName());
        model.addAttribute("orgCityName", org.getOrgCityName());
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("isRead", GlobalConstant.RECORD_STATUS_Y);
        model.addAttribute("subjectFlow", subjectFlow);
        model.addAttribute("subjectActivitiFlows",subjectActivitiFlows);

        List<ResSupervisioSubjectUser> userList = supervisioUserBiz.selectSupervisioUserListByFlow(subjectFlow);
        model.addAttribute("userList", userList);
        model.addAttribute("userFlow", userFlow);
        model.addAttribute("speId", speId);

        //查询专家签名
        SysSupervisioUser user = supervisioUserBiz.findBySuUserFlow(userFlow);
        if (StringUtil.isNotEmpty(user.getUserSignUrl())){
            model.addAttribute("speSignUrl", user.getUserSignUrl());
        }
        model.addAttribute("user", user);

        //查询专家评分是否提交  提交不能编辑
        ResSupervisioSubjectUser subjectUser = supervisioUserBiz.selectSubjectUserByFlow(userFlow, subjectFlow);
        model.addAttribute("subjectUser", subjectUser);
        if (null != subjectUser && StringUtil.isNotBlank(subjectUser.getEvaluationDate())) {
            model.addAttribute("editFlag", "N");
            model.addAttribute("evaluationDate",DateUtil.parseDate(subjectUser.getEvaluationDate(),"yyyy-MM-dd"));
        }

        ResEvaluationScore searchScore = new ResEvaluationScore();
        searchScore.setOrgFlow(subject.getOrgFlow());
        searchScore.setEvaluationYear(subject.getSubjectYear());
        searchScore.setSpeUserFlow(userFlow);
        searchScore.setSpeId(subject.getSpeId());
        searchScore.setSubjectFlow(subjectFlow);
        //查询专家评分
        List<ResEvaluationScore> speEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);
        if (null != speEvaluationScoreList && speEvaluationScoreList.size() > 0) {
            model.addAttribute("speScoreList", speEvaluationScoreList);
        }
        //查询自评分
        searchScore.setSpeUserFlow("Owner");
        List<ResEvaluationScore> ownerEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);
        if (null != ownerEvaluationScoreList && ownerEvaluationScoreList.size() > 0) {
            model.addAttribute("ownerScoreList", ownerEvaluationScoreList);
        }
        //查询附件信息
        List<JsresSupervisioFile> supervisioFileList = supervisioFileBiz.searchSubjectFile(subject.getSubjectYear(), subjectFlow, speId,userFlow);
        if (null !=supervisioFileList && supervisioFileList.size() > 0) {
            model.addAttribute("supervisioFileList", supervisioFileList);
        }
//        return "jsres/assess/evaluationInfo_" + subject.getSpeId();
        return "res/jswjw/sysSupervisioUser/scoreFormData";
    }

    /**
     *
     * 专业评分-搜索栏
     */
    @RequestMapping(value = "/searchPlanScore")
    public String searchPlanScore(Model model, String speId, String orgFlow, String userFlow, String subjectFlow, String roleFlag,
                                  String subjectYear,String subjectActivitiFlows) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(speId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "专业标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(roleFlag)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "角色标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(subjectFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "项目标识为空");
            return "res/jswjw/success";
        }
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("userFlow", userFlow);
        model.addAttribute("orgFlow", orgFlow);
        model.addAttribute("speId", speId);
        model.addAttribute("subjectFlow", subjectFlow);
        model.addAttribute("subjectYear", subjectYear);
        model.addAttribute("subjectActivitiFlows", subjectActivitiFlows);
        model.addAttribute("evaluationDate", DateUtil.parseDate(DateUtil.getCurrDate(), "yyyy-MM-dd"));
        SysOrg org = jswjwBiz.getOrg(orgFlow);
        model.addAttribute("orgName", org.getOrgName());
        model.addAttribute("orgCityName", org.getOrgCityName());
        if (!"local".equals(roleFlag)) {
            //查询专家签名
            SysSupervisioUser user = supervisioUserBiz.findBySuUserFlow(userFlow);
            model.addAttribute("speSignUrl", user.getUserSignUrl());
            model.addAttribute("user", user);
        }

        ResEvaluationScore searchScore = new ResEvaluationScore();
        ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
        searchScore.setEvaluationYear(subject.getSubjectYear());
        searchScore.setOrgFlow(orgFlow);
        //判断当前用户：专业专家对专业表评分，管理专家对管理表评分
        searchScore.setSubjectFlow(subjectFlow);
        searchScore.setSpeId(speId);
        searchScore.setSpeUserFlow("Owner");
        //自评分
        List<ResEvaluationScore> ownerEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);
        if (null != ownerEvaluationScoreList && ownerEvaluationScoreList.size() > 0) {
            model.addAttribute("ownerScoreList", ownerEvaluationScoreList);
        }
        //专家评分
        searchScore.setSpeUserFlow(userFlow);
        List<ResEvaluationScore> speEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);

        //有评分
        if (null != speEvaluationScoreList && speEvaluationScoreList.size() > 0) {
            model.addAttribute("speScoreList", speEvaluationScoreList);
            //查询专家评分是否提交  提交不能编辑
            ResSupervisioSubjectUser subjectUser = supervisioUserBiz.selectSubjectUserByFlow(userFlow, subjectFlow);
            if (null != subjectUser && StringUtil.isNotBlank(subjectUser.getEvaluationDate())) {
                model.addAttribute("editFlag", "N");
            }
            model.addAttribute("subjectUser", subjectUser);
        }

        //为了显示存在问题详情
        ResSupervisioSubjectUser subjectUser = supervisioUserBiz.selectSubjectUserByFlow(userFlow, subjectFlow);
        model.addAttribute("subjectUser", subjectUser);
        //查询附件
        List<JsresSupervisioFile> supervisioFileList = supervisioFileBiz.searchSubjectFile(subjectYear, subjectFlow, speId,userFlow);
        if (null !=supervisioFileList && supervisioFileList.size() > 0) {
            model.addAttribute("supervisioFileList", supervisioFileList);
        }
        //提前查看附件的总分（多个附表总分一起核算，如5-1、5-2）
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        if ("local".equals(roleFlag)) {
            resScheduleScore.setGrade("local");
        } else if (roleFlag.equals("expert")) {
            resScheduleScore.setGrade("expert");
        }
        resScheduleScore.setScoreType("Totalled");
        resScheduleScore.setItemName("evaluationInfo_" + subject.getSpeId());
        List<ResScheduleScore> scoreList = resScheduleScoreBiz.queryScheduleListTotalled(resScheduleScore);
        model.addAttribute("scoreList", scoreList);
        model.addAttribute("formName", "jsres/assess/evaluationInfo_" + subject.getSpeId());
//        if (null != scoreList && scoreList.size() > 0) {
//            for (int i = 0; i < scoreList.size(); i++) {
//                model.addAttribute(scoreList.get(i).getItemId(), scoreList.get(i).getScore());
//            }
//        }
        return "res/jswjw/sysSupervisioUser/scoreFormData";

    }

    /**
     *
     * 管理表、评分表评分保存
     */
    @RequestMapping(value = "/saveSpeScore")
    public String saveSpeScore(String itemId, String itemName, String score, String orgFlow, String speId, String subjectFlow,
                               String userFlow,String indicatorsNum,String subjectActivitiFlows,String coreIndicators,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "保存成功！");
        if(StringUtil.isEmpty(speId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "专业标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(subjectFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "项目标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(itemId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "itemId为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(itemName)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "itemName为空");
            return "res/jswjw/success";
        }
        ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
        SysSupervisioUser user = supervisioUserBiz.findBySuUserFlow(userFlow);
        ResEvaluationScore searchScore = new ResEvaluationScore();
        //不为空表明是管理表，为空是专业表
        if (indicatorsNum!=null){
            searchScore.setSubjectFlow(subjectActivitiFlows);
        }else {
            searchScore.setSubjectFlow(subjectFlow);
        }
        searchScore.setItemId(itemId);
        searchScore.setOrgFlow(orgFlow);
        searchScore.setEvaluationYear(subject.getSubjectYear());
        searchScore.setSpeUserFlow(userFlow);
        ResEvaluationScore evaluationScore = supervisioUserBiz.searchEvaluationOwnerScoreByItemId(searchScore);
        if (evaluationScore == null) {
            evaluationScore = new ResEvaluationScore();
            evaluationScore.setSubjectName(subject.getSubjectName());
            evaluationScore.setItemId(itemId);
            evaluationScore.setOrgFlow(orgFlow);
            evaluationScore.setSpeId(speId);
            evaluationScore.setEvaluationYear(subject.getSubjectYear());
            evaluationScore.setSpeUserFlow(user.getUserFlow());
            evaluationScore.setItemName(itemName);
            evaluationScore.setSpeScore(score);
            evaluationScore .setCoreIndicators(coreIndicators);
            //不为空表明是管理表，为空是专业表
            if (indicatorsNum!=null){
                //将subjectActivitiFlows赋值给SubjectFlow，查询直接以subjectFlow(也就是subjectActivitiFlows)查询即可
                evaluationScore.setSubjectFlow(subjectActivitiFlows);
            }else {
                searchScore.setSpeId(speId);
                evaluationScore.setSubjectFlow(subjectFlow);
            }
        } else {
            evaluationScore.setItemId(itemId);
            evaluationScore.setItemName(itemName);
            evaluationScore.setSpeScore(score);
            evaluationScore.setCoreIndicators(coreIndicators);
        }
        supervisioUserBiz.saveScore(evaluationScore,user.getUserFlow());
        return "res/jswjw/success";
    }

    /**
     *
     * 存在意见详细填写
     */
    @RequestMapping("/saveSpeContent")
    public String saveSpeContent(String userFlow, String subjectFlow, String speContent,Model model) throws Exception {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "保存成功！");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(subjectFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "项目标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(speContent)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "意见为空");
            return "res/jswjw/success";
        }
        speContent = java.net.URLDecoder.decode(speContent, "UTF-8");
        ResSupervisioSubjectUser subjectUser = supervisioUserBiz.selectSubjectUserByFlow(userFlow, subjectFlow);
        if (null != subjectUser) {
            subjectUser.setSpeContent(speContent);
            supervisioUserBiz.saveSubjectUserTwo(subjectUser,userFlow);
            return "res/jswjw/success";
        }
        model.addAttribute("resultId", "30201");
        model.addAttribute("resultType", "保存失败！");
        return "res/jswjw/success";
    }

    /**
     *
     * 扣分原因..保存
     */
    @RequestMapping(value = "/saveSpeReason")
    public String saveSpeReason(String itemId, String itemName, String reason, String orgFlow, String speId,
                                String subjectFlow, String userFlow,String roleFlag,String subjectActivitiFlows,Model model) throws Exception {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "保存成功！");
        if(StringUtil.isEmpty(speId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "专业标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(subjectFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "项目标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(itemId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "itemId为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(itemName)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "itemName为空");
            return "res/jswjw/success";
        }
        ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
        SysSupervisioUser user = supervisioUserBiz.findBySuUserFlow(userFlow);
        reason = java.net.URLDecoder.decode(reason, "UTF-8");
        ResEvaluationScore searchScore = new ResEvaluationScore();
        searchScore.setItemId(itemId);
        searchScore.setOrgFlow(orgFlow);
        searchScore.setEvaluationYear(subject.getSubjectYear());
        searchScore.setSpeUserFlow(userFlow);
        //专业表
        if (roleFlag==null){
            searchScore.setSubjectFlow(subjectFlow);
            searchScore.setSpeId(speId);
        }
        ResEvaluationScore evaluationScore = supervisioUserBiz.searchEvaluationOwnerScoreByItemId(searchScore);
        if (evaluationScore == null) {
            evaluationScore = new ResEvaluationScore();
            evaluationScore.setSubjectName(subject.getSubjectName());
            evaluationScore.setItemId(itemId);
            evaluationScore.setOrgFlow(orgFlow);
            evaluationScore.setEvaluationYear(subject.getSubjectYear());
            evaluationScore.setSpeUserFlow(userFlow);
            evaluationScore.setSpeUserName(user.getUserName());
            evaluationScore.setItemName(itemName);
            evaluationScore.setSpeReason(reason);
            //专业表
            if (roleFlag==null){
                evaluationScore.setSubjectFlow(subjectFlow);
                evaluationScore.setSpeId(speId);
            }
        } else {
            evaluationScore.setItemId(itemId);
            evaluationScore.setItemName(itemName);
            evaluationScore.setSpeReason(reason);
        }
        supervisioUserBiz.saveScore(evaluationScore,userFlow);
        return "res/jswjw/success";
    }

    @RequestMapping(value = "/saveSpeScoreTotal")
    public String saveSpeScoreTotal(String userFlow, String subjectFlow, String speScoreTotal, String evaluationDate, String subjectActivitiFlows,Model model) {
        model.addAttribute("resultId", "30200");
        model.addAttribute("resultType", "保存失败！");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(subjectFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "项目标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(evaluationDate)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "evaluationDate为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(speScoreTotal)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "speScoreTotal为空");
            return "res/jswjw/success";
        }
        //专业专家  专业表
        ResSupervisioSubjectUser subjectUser = supervisioUserBiz.selectSubjectUserByFlow(userFlow, subjectFlow);
        if (null != subjectUser) {
            subjectUser.setSpeScoreTotal(speScoreTotal);
            subjectUser.setEvaluationDate(evaluationDate);
            supervisioUserBiz.saveSubjectUser(subjectUser,userFlow);
            //查询督导组员是否全部提交
            List<ResSupervisioSubjectUser> userList = supervisioUserBiz.selectSupervisioUserListByFlow(subjectFlow);
            String isSubmit = "Y";
            Integer scoreTotal = 0;
            int evaNum = userList.size();
            if (null != userList && userList.size() > 0) {
                for (ResSupervisioSubjectUser user : userList) {
                    if (StringUtil.isBlank(user.getEvaluationDate())) {
                        isSubmit = "N";
                        break;
                    } else {
                        if (user.getSpeScoreTotal() != null) {
                            scoreTotal += Integer.valueOf(user.getSpeScoreTotal());
                        }
                    }
                }
            } else {
                isSubmit = "N";
            }
            if (GlobalConstant.FLAG_Y.equals(isSubmit)) {
                //全部提交  计算平均分并保存
                BigDecimal total = new BigDecimal(scoreTotal);
                BigDecimal num = new BigDecimal(evaNum);
                BigDecimal result = total.divide(num, 2, BigDecimal.ROUND_HALF_UP);
                ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
                subject.setAvgScore(result + "");
                supervisioUserBiz.saveSubject(subject,userFlow);
            }
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "保存成功！");
            return "res/jswjw/success";
        }
        return "res/jswjw/success";
    }


    /**
     * 上传文件
     */
    @RequestMapping(value = "/checkUploadFile", method = {RequestMethod.POST})
    public String checkUploadFile(String itemId, String itemName, String subjectYear,String subjectFlow, MultipartFile uploadFile, Model model, String speId, String userFlow) throws UnsupportedEncodingException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "上传成功！");
        if(StringUtil.isEmpty(itemId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "itemId为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(itemName)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "itemName为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(subjectFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "subjectFlow为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(speId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "专业标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识为空");
            return "res/jswjw/success";
        }
        SysSupervisioUser user = supervisioUserBiz.findBySuUserFlow(userFlow);
        String resultPath = "";
        if (uploadFile != null && !uploadFile.isEmpty()) {
            String fileResult = supervisioUserBiz.checkImg(uploadFile);
            if (!GlobalConstant.FLAG_Y.equals(fileResult)) {
                model.addAttribute("fileErrorMsg", fileResult);
            } else {
                resultPath = supervisioUserBiz.saveFileToDirs("", uploadFile, "jsresSupervisioFile", user.getOrgFlow(), "2022", itemId);
            }
            model.addAttribute("result", fileResult);
        }
        JsresSupervisioFile jsresSupervisioFile = new JsresSupervisioFile();
        jsresSupervisioFile.setItemId(itemId);
        String tempStr = java.net.URLDecoder.decode(itemName, "UTF-8");
        jsresSupervisioFile.setItemName(tempStr);
        jsresSupervisioFile.setPlanYear(DateUtil.getYear());
        jsresSupervisioFile.setCreateTime(DateUtil.getCurrentTime());
        jsresSupervisioFile.setFileUrl(resultPath);
        jsresSupervisioFile.setCreateUserFlow(user.getUserFlow());
        jsresSupervisioFile.setFileName(uploadFile.getOriginalFilename());
        jsresSupervisioFile.setSpeId(speId);
        jsresSupervisioFile.setOrgFlow(user.getOrgFlow());
        jsresSupervisioFile.setOrgName(user.getOrgName());
        jsresSupervisioFile.setPlanYear(subjectYear);
        jsresSupervisioFile.setSubjectFlow(subjectFlow);
        supervisioUserBiz.saveJsresSupervisioFile(jsresSupervisioFile,userFlow);
        model.addAttribute("file", jsresSupervisioFile);
        return "res/jswjw/sysSupervisioUser/uploadFile";
    }

    /**
     * 删除文件
     *
     * @param recordFlow
     * @return
     */
    @RequestMapping("/removeFile")
    public String removeFile(String recordFlow,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "删除成功！");
        if(StringUtil.isEmpty(recordFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "recordFlow为空");
            return "res/jswjw/success";
        }
        supervisioUserBiz.deleteFileByPrimaryKey(recordFlow);
        return "res/jswjw/success";
    }

    /**
     * 单个附件跳转
     */
    @RequestMapping(value = "/Schedule")
    public String Schedule(String fileRoute, String orgName, String orgFlow, String subjectFlow, String isRead,
                           String userFlow, String speId, String roleFlag, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功！");
        if(StringUtil.isEmpty(speId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "专业标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(subjectFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "项目标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(orgName)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "orgName为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(orgFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "orgFlow为空");
            return "res/jswjw/success";
        }
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        resScheduleScore.setGrade("expert");
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setOrgFlow(orgFlow);
        resScheduleScore.setFileRoute(fileRoute);
        resScheduleScore.setSubjectFlow(subjectFlow);
        //根据专家自己的userFlow查询附表评分详情
        if (StringUtil.isNotBlank(userFlow)) {
            resScheduleScore.setUserFlow(userFlow);
        }
        resScheduleScore.setSpeId(speId);
        List<ResScheduleScore> scoreList = resScheduleScoreBiz.queryScheduleList(resScheduleScore);
        //为了查看省市
        SysOrg sysOrg = jswjwBiz.getOrg(orgFlow);
        if (sysOrg != null && (sysOrg.getOrgCityName() != null || sysOrg.getOrgProvName() != null))
            model.addAttribute("cityName", sysOrg.getOrgProvName() + sysOrg.getOrgCityName());
        model.addAttribute("scoreList", scoreList);
        model.addAttribute("orgName", orgName);
        model.addAttribute("orgFlow", orgFlow);
        model.addAttribute("isRead", isRead);
        model.addAttribute("subjectFlow", subjectFlow);
        model.addAttribute("speId", speId);
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("fileRoute", fileRoute);
        model.addAttribute("formName", "jsres/assess/" + fileRoute);
        model.addAttribute("scheduleDate", com.pinde.core.util.DateUtil.parseDate(com.pinde.core.util.DateUtil.getCurrDate(), "yyyy-MM-dd"));
        return "res/jswjw/sysSupervisioUser/scoreFormData";
    }

    /**
     * 附件 保存得分
     * 适用于只有得分  扣分情况（两个分值相关）
     */
    @RequestMapping(value = "/savScheduleScore")
    public String savScheduleScore(String itemId, String score, String itemName, String orgName, String orgFlow,Model model,
                                   String speId, String roleFlag, String subjectFlow, String num, String fileRoute,String userFlow) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "保存成功！");
        if(StringUtil.isEmpty(speId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "专业标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(subjectFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "项目标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(itemId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "itemId为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(itemName)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "itemName为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(roleFlag)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "角色标识为空");
            return "res/jswjw/success";
        }
        ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        resScheduleScore.setGrade("expert");
        SysSupervisioUser user = supervisioUserBiz.findBySuUserFlow(userFlow);
        resScheduleScore.setUserFlow(user.getUserFlow());
        resScheduleScore.setItemId(itemId);
        resScheduleScore.setScore(score);
        resScheduleScore.setItemName(itemName);
        resScheduleScore.setOrgFlow(orgFlow);
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setFileRoute(fileRoute);
        resScheduleScore.setSpeId(speId);
        resScheduleScore.setSubjectFlow(subjectFlow);
        resScheduleScore.setEvaluationYear(subject.getSubjectYear());
        String itemName2 = "";
        if (itemName.startsWith("d")) {
            itemName2 = "k" + itemName.substring(1);
        } else {
            itemName2 = "d" + itemName.substring(1);
        }
        if (resScheduleScoreBiz.saveSchedule(resScheduleScore,userFlow) <= 0) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "保存失败！");
            return "res/jswjw/success";
        }
        resScheduleScore.setItemName(itemName2);
        resScheduleScore.setScore(String.valueOf(new BigDecimal(num).subtract(new BigDecimal(score))));
        resScheduleScoreBiz.saveSchedule(resScheduleScore,userFlow);
        return "res/jswjw/success";
    }

    /**
     * 附表 数据为有、无的方法
     */
    @RequestMapping(value = "/savScheduleHaveAndNo")
    public String savScheduleHaveAndNo(String itemId, String score, String itemName, String orgName, String orgFlow,Model model,
                                       String speId, String roleFlag, String subjectFlow, String fileRoute,String userFlow) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "保存成功！");
        if(StringUtil.isEmpty(speId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "专业标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(subjectFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "项目标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(itemId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "itemId为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(itemName)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "itemName为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(roleFlag)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "角色标识为空");
            return "res/jswjw/success";
        }
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        resScheduleScore.setGrade("expert");
        //添加select的数据
        SysSupervisioUser user = supervisioUserBiz.findBySuUserFlow(userFlow);
        resScheduleScore.setUserFlow(user.getUserFlow());
        resScheduleScore.setItemId(itemId);
        resScheduleScore.setScore(score);
        resScheduleScore.setItemName(itemName);
        resScheduleScore.setFileRoute(fileRoute);
        resScheduleScore.setOrgFlow(orgFlow);
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setSpeId(speId);
        resScheduleScore.setSubjectFlow(subjectFlow);
        if (resScheduleScoreBiz.saveSchedule(resScheduleScore,userFlow) > 0) {
            //添加该select后面的input的数据，以itemName区分
            resScheduleScore.setItemName("d" + itemName.substring(1));
            if (resScheduleScore.getScore().equals("有")) {
                resScheduleScore.setScore("");
            } else {
                resScheduleScore.setScore("√");
            }
            if (resScheduleScoreBiz.saveSchedule(resScheduleScore,userFlow) > 0) {
                return "res/jswjw/success";
            }
        }
        model.addAttribute("resultId", "30201");
        model.addAttribute("resultType", "保存失败！");
        return "res/jswjw/success";
    }

    /**
     * 附件-保存 实得分（一个分值）
     */
    @RequestMapping(value = "/saveScheduleMK")
    public String saveScheduleMK(String itemId, String score, String itemName, String orgName, String roleFlag,Model model,
                                 String orgFlow, String speId, String subjectFlow, String num, String fileRoute,String userFlow) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "保存成功！");
        if(StringUtil.isEmpty(speId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "专业标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(subjectFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "项目标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(itemId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "itemId为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(itemName)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "itemName为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(roleFlag)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "角色标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(num)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "num为空");
            return "res/jswjw/success";
        }
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        resScheduleScore.setGrade("expert");
        SysSupervisioUser user = supervisioUserBiz.findBySuUserFlow(userFlow);
        resScheduleScore.setUserFlow(user.getUserFlow());
        resScheduleScore.setItemId(itemId);
        resScheduleScore.setScore(score);
        resScheduleScore.setFileRoute(fileRoute);
        resScheduleScore.setItemName(itemName);
        resScheduleScore.setOrgFlow(orgFlow);
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setSpeId(speId);
        resScheduleScore.setSubjectFlow(subjectFlow);
        if (resScheduleScoreBiz.saveSchedule(resScheduleScore,userFlow) <= 0) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "保存失败！");
            return "res/jswjw/success";
        }
        return "res/jswjw/success";
    }

    /**
     * 保存 多个分值总和为一个
     * jsp中，子分值和总分值的itemid一样，但是itemName不一样，name也不一致
     * 子分值的js方法中需要上传总分的itemName和分数
     *
     * @param itemId
     * @param score
     * @param itemName    子分值的itemName
     * @param roleFlag
     * @param orgName
     * @param orgFlow
     * @param speId
     * @param itemMain    总分值的itemName
     * @param scoreAll    总分值的分值
     * @return
     */
    @RequestMapping(value = "/saveScheduleManyToAll")
    public String saveScheduleManyToAll(String itemId, String score, String itemName, String roleFlag, String orgName,Model model,String userFlow,
                                        String orgFlow, String speId, String subjectFlow, String itemMain, String scoreAll, String fileRoute) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "保存成功！");
        if(StringUtil.isEmpty(speId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "专业标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(subjectFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "项目标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(itemId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "itemId为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(itemName)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "itemName为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(roleFlag)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "角色标识为空");
            return "res/jswjw/success";
        }
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        resScheduleScore.setGrade("expert");
        SysSupervisioUser user = supervisioUserBiz.findBySuUserFlow(userFlow);
        resScheduleScore.setUserFlow(user.getUserFlow());
        resScheduleScore.setItemId(itemId);
        resScheduleScore.setScore(score);
        resScheduleScore.setFileRoute(fileRoute);
        resScheduleScore.setItemName(itemName);
        resScheduleScore.setOrgFlow(orgFlow);
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setSpeId(speId);
        resScheduleScore.setSubjectFlow(subjectFlow);
        //添加多的一方数据
        if (resScheduleScoreBiz.saveSchedule(resScheduleScore,userFlow) > 0) {
            //查询多的一方数据
            resScheduleScore.setItemName(itemMain);
            resScheduleScore.setScheduleFlow(null);
            List<ResScheduleScore> scoreList = resScheduleScoreBiz.queryScheduleListNotItemName(resScheduleScore);
            //累计分数
            BigDecimal itemScoreAll=new BigDecimal(0);
            BigDecimal decimal = new BigDecimal(scoreAll);
            if (scoreList.size() > 0) {
                for (ResScheduleScore scheduleScore : scoreList) {
                    BigDecimal scoreInfo=new BigDecimal(scheduleScore.getScore());
                    itemScoreAll = itemScoreAll.add(scoreInfo);
                }
                itemScoreAll=decimal.subtract(itemScoreAll);
            } else {
                BigDecimal scoreInfo=new BigDecimal(scoreAll);
                BigDecimal scoreAll2=new BigDecimal(scoreAll);
                itemScoreAll=scoreAll2.subtract(scoreInfo);
            }
            if (itemScoreAll.compareTo(BigDecimal.ZERO)==-1){
                itemScoreAll.setScale(0);
            }
            //添加少的一方数据
            resScheduleScore.setScore(String.valueOf(itemScoreAll));

            if (resScheduleScoreBiz.saveSchedule(resScheduleScore,userFlow) > 0) {
                return "res/jswjw/success";
            }
        }
        model.addAttribute("resultId", "30201");
        model.addAttribute("resultType", "保存失败！");
        return "res/jswjw/success";
    }

    /**
     * 保存文本信息，如扣分原因
     *
     * @param itemId
     * @param itemDetailed
     * @param roleFlag
     * @param itemName
     * @param orgName
     * @param orgFlow
     * @param speId
     * @return
     */
    @RequestMapping(value = "/saveScheduleDetailed")
    public String saveScheduleDetailed(String itemId, String itemDetailed, String roleFlag, String itemName, String orgName,
                                       String orgFlow, String speId, String subjectFlow, String fileRoute,Model model,String userFlow) throws Exception {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "保存成功！");
        if(StringUtil.isEmpty(speId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "专业标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(subjectFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "项目标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(itemId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "itemId为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(itemName)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "itemName为空");
            return "res/jswjw/success";
        }
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        resScheduleScore.setGrade("expert");
        SysSupervisioUser user = supervisioUserBiz.findBySuUserFlow(userFlow);
        resScheduleScore.setUserFlow(user.getUserFlow());
        resScheduleScore.setItemId(itemId);
        resScheduleScore.setFileRoute(fileRoute);
        resScheduleScore.setItemName(itemName);
        resScheduleScore.setOrgFlow(orgFlow);
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setSpeId(speId);
        resScheduleScore.setSubjectFlow(subjectFlow);
        itemDetailed = java.net.URLDecoder.decode(itemDetailed, "UTF-8");
        resScheduleScore.setItemDetailed(itemDetailed);

        if (resScheduleScoreBiz.saveScheduleDetailed(resScheduleScore,userFlow) <= 0) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "保存失败！");
            return "res/jswjw/success";
        }
        return "res/jswjw/success";
    }


    /**
     * 附件  多个表单保存总分  以itemId区分
     *
     * @param itemIdOne    第一个的itemId
     * @param itemIdTwo    第二个的itemId
     * @param selfOneScore 第一个的分数
     * @param selfTwoScore 第二个的分数
     * @param itemName
     * @param orgName
     * @param roleFlag
     * @param orgFlow
     * @param speId
     * @return
     */
    @RequestMapping(value = "/saveManyScheduleTotalled")
    public String saveManyScheduleTotalled(String itemIdOne, String itemIdTwo, String selfOneScore, String selfTwoScore,
                                           String itemName, String orgName, String roleFlag, String orgFlow, String speId,
                                           String subjectFlow, String fileRoute,Model model,String userFlow) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "保存成功！");
        if(StringUtil.isEmpty(speId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "专业标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(subjectFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "项目标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(roleFlag)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "角色标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(itemName)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "itemName为空");
            return "res/jswjw/success";
        }
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        resScheduleScore.setGrade("expert");
        SysSupervisioUser user = supervisioUserBiz.findBySuUserFlow(userFlow);
        resScheduleScore.setUserFlow(user.getUserFlow());
        resScheduleScore.setItemId(itemIdOne);
        resScheduleScore.setScore(selfOneScore);
        resScheduleScore.setFileRoute(fileRoute);
        resScheduleScore.setItemName(itemName);
        resScheduleScore.setOrgFlow(orgFlow);
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setSpeId(speId);
        resScheduleScore.setSubjectFlow(subjectFlow);
        resScheduleScore.setScoreType("Totalled");
        if (resScheduleScoreBiz.saveSchedule(resScheduleScore,userFlow) > 0) {
            if (StringUtil.isNotBlank(selfTwoScore) && StringUtil.isNotBlank(itemIdTwo)) {
                resScheduleScore.setScore(selfTwoScore);
                resScheduleScore.setItemId(itemIdTwo);
                if (resScheduleScoreBiz.saveSchedule(resScheduleScore,userFlow) > 0) {
                    return "res/jswjw/success";
                }
            } else {
                return "res/jswjw/success";
            }

        }
        model.addAttribute("resultId", "30201");
        model.addAttribute("resultType", "保存失败！");
        return "res/jswjw/success";
    }

    /**
     * 保存附表的总分
     */
    @RequestMapping(value = "/saveScheduleTotalled")
    public String saveScheduleTotalled(String itemId, String score, String itemName, String orgName, String roleFlag,
                                       String orgFlow, String speId, String subjectFlow, String fileRoute,Model model,String userFlow) {

        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "保存成功！");
        if(StringUtil.isEmpty(speId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "专业标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(subjectFlow)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "项目标识为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(itemId)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "itemId为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(itemName)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "itemName为空");
            return "res/jswjw/success";
        }
        if(StringUtil.isEmpty(roleFlag)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "角色标识为空");
            return "res/jswjw/success";
        }
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        resScheduleScore.setGrade("expert");
        SysSupervisioUser user = supervisioUserBiz.findBySuUserFlow(userFlow);
        resScheduleScore.setUserFlow(user.getUserFlow());
        resScheduleScore.setItemId(itemId);
        resScheduleScore.setScore(score);
        resScheduleScore.setItemName(itemName);
        resScheduleScore.setOrgFlow(orgFlow);
        resScheduleScore.setFileRoute(fileRoute);
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setSpeId(speId);
        resScheduleScore.setSubjectFlow(subjectFlow);
        resScheduleScore.setScoreType("Totalled");
        if (resScheduleScoreBiz.saveSchedule(resScheduleScore,userFlow) <= 0) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "保存失败！");
            return "res/jswjw/success";
        }
        return "res/jswjw/success";
    }



}

