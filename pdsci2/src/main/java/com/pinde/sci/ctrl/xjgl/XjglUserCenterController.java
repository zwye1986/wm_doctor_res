package com.pinde.sci.ctrl.xjgl;

import com.pinde.core.jspform.ItemGroupData;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.biz.xjgl.*;
import com.pinde.sci.common.*;
import com.pinde.sci.dao.base.EduLogMapper;
import com.pinde.sci.dao.base.EduUserMapper;
import com.pinde.sci.dao.base.PubFileMapper;
import com.pinde.sci.dao.xjgl.XjEduUserExtMapper;
import com.pinde.sci.enums.pub.*;
import com.pinde.sci.enums.res.XjPartStatusEnum;
import com.pinde.sci.enums.sys.CertificateTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.MarriageTypeEnum;
import com.pinde.sci.enums.sys.PoliticsAppearanceEnum;
import com.pinde.sci.enums.xjgl.UserDegreeMajorEnum;
import com.pinde.sci.enums.xjgl.XjDocCategoryEnum;
import com.pinde.sci.enums.xjgl.XjglCourseTypeEnum;
import com.pinde.sci.form.xjgl.*;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.xjgl.*;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/xjgl/user")
public class XjglUserCenterController extends GeneralController {
    private static Logger logger = LoggerFactory.getLogger(XjglUserCenterController.class);

    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IXjEduUserBiz eduUserBiz;
    @Autowired
    private IXjEduCourseBiz courseBiz;
    @Autowired
    private IXjEduCourseMajorBiz courseMajorBiz;
    @Autowired
    private IXjEduStudentCourseBiz studentCourseBiz;

    @Autowired
    private ICfgBiz cfgBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IXjImportRecordBiz importRecordBiz;
    @Autowired
    private IDictBiz dictBiz;
   @Autowired
    private PubFileMapper fileMapper;
    @Autowired
    private EduUserMapper eduUserMapper;
    @Autowired
    private IUserRoleBiz roleBiz;
    @Autowired
    private EduLogMapper logMapper;
    @Autowired
    private IXjTutorBiz tutorBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;

    /**
     * 查询eduUser和sysUser
     * @param flag 拆分学籍信息标识
     * @param model
     * @param eduUser
     * @param sysUser
     */
    @RequestMapping("/eduUserList")
    public String eduUserList(Model model, EduUser eduUser, SysUser sysUser, ResDoctor resDoctor, String flag,String registerFlag,
                              String startDate, String endDate, String startSid, String endSid, String startBirthday, String endBirthday, String teacherName,
                              Integer currentPage, HttpServletRequest request, String from, String partId, String partStatus,String startGraduateDate,
                              String endGraduateDate,String graduateTimeYear, String graduateTimeYM,String awardDegreeTimeYear, String awardDegreeTimeYM) {

        model.addAttribute("from", from);
        model.addAttribute("flag", flag);

        if (currentPage == null) {
            currentPage = 1;
        }
        SysUser user = GlobalContext.getCurrentUser();

        //查询用户角色,确认是否为学费管理员角色
        List<String> roleFlows = new ArrayList<>();
        String userFlow = user.getUserFlow();
        List<SysUserRole> userRoles = roleBiz.getByUserFlowAndWsid(userFlow,GlobalConstant.CMIS_WS_ID);
        if(userRoles!=null&&userRoles.size()>0){
            for(SysUserRole userRole:userRoles){
                String roleFlow = userRole.getRoleFlow();
                roleFlows.add(roleFlow);
            }
        }
        SysCfg cfg = cfgBiz.read("xjgl_feeMaster_role_flow");
        boolean bool = false;
        if(cfg!=null){
            String feeFlow = cfg.getCfgCode();
            bool = roleFlows.contains(feeFlow);
        }
        model.addAttribute("isFeeMaster",bool);
        SysCfg cfg2 = cfgBiz.read("xjgl_szk_role_flow");
        boolean bool2 = false;
        if(cfg2!=null){
            String szkFlow = cfg.getCfgCode();
            bool2 = roleFlows.contains(szkFlow);
        }
        model.addAttribute("isSzkMaster",bool2);

        if (GlobalConstant.USER_LIST_LOCAL.equals(from)) {
            //培养单位
            resDoctor.setOrgFlow(user.getOrgFlow());
            model.addAttribute("orgFlow", user.getOrgFlow());
        } else if (GlobalConstant.USER_LIST_CHARGE.equals(from)) {
            //分委会
            eduUser.setTrainOrgId(user.getDeptFlow());
            model.addAttribute("trainOrgId", user.getDeptFlow());
        }

        PageHelper.startPage(currentPage, getPageSize(request));
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("eduUser", eduUser);
        paramMap.put("sysUser", sysUser);
        paramMap.put("resDoctor", resDoctor);
        paramMap.put("startDate", startDate);
        paramMap.put("endDate", endDate);
        paramMap.put("startSid", startSid);
        paramMap.put("endSid", endSid);
        paramMap.put("startBirthday", startBirthday);
        paramMap.put("endBirthday", endBirthday);
        paramMap.put("teacherName", teacherName);
        //确认版块
        paramMap.put("partId", partId);
        //确认状态
        paramMap.put("partStatus", partStatus);
        //预毕业时间
        paramMap.put("startGraduateDate", startGraduateDate);
        paramMap.put("endGraduateDate", endGraduateDate);
        //毕业时间(年份)
        paramMap.put("graduateTimeYear", graduateTimeYear);
        //毕业时间(年月)
        paramMap.put("graduateTimeYM", graduateTimeYM);
        //授予学业时间（年份）
        paramMap.put("awardDegreeTimeYear", awardDegreeTimeYear);
        //授予学业时间（年月）
        paramMap.put("awardDegreeTimeYM", awardDegreeTimeYM);

        List<EduUser> eduUserList = eduUserBiz.searchEduUserBySysUser(paramMap);
        if (eduUserList != null && eduUserList.size() > 0) {
            model.addAttribute("eduUserList", eduUserList);
            List<String> userFlows = new ArrayList<String>();
            for (EduUser eu : eduUserList) {
                userFlows.add(eu.getUserFlow());
            }
            List<SysUser> userList = userBiz.searchSysUserByuserFlows(userFlows);
            if (userList != null && userList.size() > 0) {
                Map<String, SysUser> userMap = new HashMap<String, SysUser>();
                for (SysUser su : userList) {
                    userMap.put(su.getUserFlow(), su);
                }
                model.addAttribute("userMap", userMap);
            }
            List<ResDoctor> resDctorList = eduUserBiz.searchDoctorByuserFlow(userFlows);
            if (resDctorList != null && resDctorList.size() > 0) {
                Map<String, ResDoctor> resDoctorMap = new HashMap<String, ResDoctor>();
                for (ResDoctor du : resDctorList) {
                    resDoctorMap.put(du.getDoctorFlow(), du);
                }
                model.addAttribute("resDoctorMap", resDoctorMap);
            }
        }


        String orgFlow = user.getOrgFlow();
        List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);

        model.addAttribute("deptList", deptList);
        SysCfg openFileUpdateCfg = cfgBiz.read("open_file_update");
        model.addAttribute("openFileUpdateCfg", openFileUpdateCfg);
        SysCfg openFileUpdateStartDate = cfgBiz.read("open_file_update_start_date");
        model.addAttribute("openFileUpdateStartDate", openFileUpdateStartDate);
        SysCfg closeFileUpdateEndDate = cfgBiz.read("close_file_update_end_date");
        model.addAttribute("closeFileUpdateEndDate", closeFileUpdateEndDate);

        Map<String,String> orgFlowMap = new HashMap<>();
        List<SysOrg> orgList = orgBiz.searchTrainOrgList();
        model.addAttribute("orgList", orgList);
        //开放学籍修改权限
        SysCfg permissionCfg = cfgBiz.read("xjgl_modify_permission");
        model.addAttribute("permissionCfg", permissionCfg);
        return "xjgl/plat/schoolInfo";
    }

    /**
     * 学位证明查询(学校)
     */
    @RequestMapping("/certificate")
    public String certificate(Model model, EduUser eduUser, SysUser sysUser, ResDoctor resDoctor,
                              String startDate, String endDate, String startSid, String endSid, String startBirthday, String endBirthday, String teacherName,
                              Integer currentPage, String from, HttpServletRequest request) {
        model.addAttribute("from", from);
        if (currentPage == null) {
            currentPage = 1;
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("eduUser", eduUser);
        paramMap.put("sysUser", sysUser);
        paramMap.put("resDoctor", resDoctor);
        paramMap.put("startDate", startDate);
        paramMap.put("endDate", endDate);
        paramMap.put("startSid", startSid);
        paramMap.put("endSid", endSid);
        paramMap.put("startBirthday", startBirthday);
        paramMap.put("endBirthday", endBirthday);
        paramMap.put("teacherName", teacherName);
        List<EduUser> eduUserList = eduUserBiz.searchEduUserBySysUser(paramMap);
        if (eduUserList != null && eduUserList.size() > 0) {
            model.addAttribute("eduUserList", eduUserList);
            List<String> userFlows = new ArrayList<String>();
            for (EduUser eu : eduUserList) {
                userFlows.add(eu.getUserFlow());
            }
            List<SysUser> userList = userBiz.searchSysUserByuserFlows(userFlows);
            if (userList != null && userList.size() > 0) {
                Map<String, SysUser> userMap = new HashMap<String, SysUser>();
                for (SysUser su : userList) {
                    userMap.put(su.getUserFlow(), su);
                }
                model.addAttribute("userMap", userMap);
            }
            List<ResDoctor> resDctorList = eduUserBiz.searchDoctorByuserFlow(userFlows);
            if (resDctorList != null && resDctorList.size() > 0) {
                Map<String, ResDoctor> resDoctorMap = new HashMap<String, ResDoctor>();
                for (ResDoctor du : resDctorList) {
                    resDoctorMap.put(du.getDoctorFlow(), du);
                }
                model.addAttribute("resDoctorMap", resDoctorMap);
            }
        }
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);
        model.addAttribute("deptList", deptList);
        List<SysOrg> orgList = orgBiz.searchTrainOrgList();
        model.addAttribute("orgList", orgList);
        return "xjgl/plat/certificateInfo";
    }

    /**
     * 学生成绩管理
     * @param flag 拆分成绩管理的标识
     */
    @RequestMapping(value = "/selectEduUserStudentCourseSun")
    public String selectEduUserStudentCourseSun(Integer currentPage, EduUser eduUser, SysUser sysUser, ResDoctor doctor, EduStudentCourse studentCourse,String flag,
                                                Model model, HttpServletRequest request, String from,String scoreSum,String degreeGrade,String courseTypeScore) {
        model.addAttribute("flag", flag);
        PageHelper.startPage(currentPage, getPageSize(request));
        //2016年1月4日 13:35:47  jzhang modify

        SysUser user = GlobalContext.getCurrentUser();

        if (GlobalConstant.USER_LIST_LOCAL.equals(from)) {
            //培养单位
            doctor.setOrgFlow(user.getOrgFlow());
        } else if (GlobalConstant.USER_LIST_CHARGE.equals(from)) {
            //分委会
            eduUser.setTrainOrgId(user.getDeptFlow());
        }

        List<XjEduStudentCourseExt> recordList = studentCourseBiz.searchStudentCourse(sysUser, eduUser, doctor, studentCourse,scoreSum,degreeGrade,courseTypeScore);
        model.addAttribute("recordList", recordList);

        PubImportRecord importRecord = new PubImportRecord();
        importRecord.setImpTypeId(XjImpTypeEnum.EduStudentCourse.getId());
        importRecord.setRoleFlag("admin");
        List<PubImportRecord> importRecords = importRecordBiz.searchImportRecordList(importRecord);
        model.addAttribute("importRecords", importRecords);
        List<SysOrg> orgList = orgBiz.searchTrainOrgList();
        model.addAttribute("orgList", orgList);
        return "xjgl/plat/resultManager";

    }
    /**
     * 保存学籍注册时间
     * @param form
     * @param userFlow
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/saveRegisterDate")
    @ResponseBody
    public String saveRegisterDate(@RequestBody XjEduUserExtInfoForm form, String userFlow) throws Exception{
        List<XjRegisterDateForm> registerDateList = form.getRegisterDateList();
        int result = eduUserBiz.saveRegisterDate(registerDateList, userFlow);
        if(GlobalConstant.ZERO_LINE != result){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
    /**
     * 导入的跳转页面
     * @return
     */
    @RequestMapping(value="/leadTo")
    public String leadTo(){
        return "xjgl/plat/gradeDaoRu";
    }
    /**
     * 导入操作
     * @return
     */
    @RequestMapping(value="/importGradeFromExcel")
    @ResponseBody
    public Map<String, Object> importGradeFromExcel(MultipartFile file){
        Map<String, Object> returnDataMap=new HashMap<String,Object>();
        if(file.getSize() > 0){
            try{
                returnDataMap =eduUserBiz.importGradeFromExcel(file,"admin");
            }catch(RuntimeException re){
                re.printStackTrace();
                return returnDataMap;
            }

        }
        return returnDataMap;
    }
    /**
     * 学籍信息导入
     */
    @RequestMapping(value="/importSchoolRollFromExcel")
    @ResponseBody
    public String importSchoolRollFromExcel(MultipartFile file){
        if(file.getSize() > 0){
            try{
                int result = eduUserBiz.importCourseFromExcel(file);
                if(GlobalConstant.ZERO_LINE != result){
                    return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
                }else{
                    return GlobalConstant.UPLOAD_FAIL;
                }
            }catch(Exception re){
                re.printStackTrace();
                return re.getMessage();
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    /**
     * 学籍信息导出Excel
     * @param response
     * @return
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping("/exportExcel")
    public void exportExcel(SysUser sysUser, EduUser eduUser, ResDoctor resDoctor, String registerFlag,
                            String startDate, String endDate, String startSid, String endSid, String startBirthday, String endBirthday, String teacherName,
                            HttpServletResponse response, String from, String flag,String partId, String partStatus,String startGraduateDate,String endGraduateDate
                            ,String graduateTimeYear, String graduateTimeYM,String awardDegreeTimeYear, String awardDegreeTimeYM) throws Exception{
        Map<String, Object> paramMap=new HashMap<String,Object>();
        SysUser currUser = GlobalContext.getCurrentUser();
        if(GlobalConstant.USER_LIST_LOCAL.equals(from)){
            //培养单位
            resDoctor.setOrgFlow(currUser.getOrgFlow());
        }else if(GlobalConstant.USER_LIST_CHARGE.equals(from)){
            //分委会
            eduUser.setTrainOrgId(currUser.getDeptFlow());
        }
        paramMap.put("eduUser", eduUser);
        paramMap.put("sysUser", sysUser);
        paramMap.put("resDoctor", resDoctor);
        paramMap.put("startDate", startDate);
        paramMap.put("endDate", endDate);
        paramMap.put("startSid", startSid);
        paramMap.put("endSid", endSid);
        paramMap.put("startBirthday", startBirthday);
        paramMap.put("endBirthday", endBirthday);
        paramMap.put("teacherName", teacherName);
        paramMap.put("partId", partId);//确认版块
        paramMap.put("partStatus", partStatus);//确认状态
        paramMap.put("startGraduateDate", startGraduateDate);
        paramMap.put("endGraduateDate", endGraduateDate);
        //毕业时间(年份)
        paramMap.put("graduateTimeYear", graduateTimeYear);
        //毕业时间(年月)
        paramMap.put("graduateTimeYM", graduateTimeYM);
        //授予学业时间（年份）
        paramMap.put("awardDegreeTimeYear", awardDegreeTimeYear);
        //授予学业时间（年月）
        paramMap.put("awardDegreeTimeYM", awardDegreeTimeYM);
        List<XjEduUserForm> eduUserFormList = new ArrayList<XjEduUserForm>();
        List<XjEduUserInfoExt> eduUserList = eduUserBiz.searchEduUserInfoExtBySysUser1(paramMap);
        if(eduUserList!=null && eduUserList.size()>0){
            for(XjEduUserInfoExt eu : eduUserList){
                XjEduUserForm eduUserForm = new XjEduUserForm();
                SysUser user = eu.getSysUser();
                //处理时间格式
                user.setUserBirthday(_dateTypeTrans(user.getUserBirthday()));
                eu.setPreGraduateDate(_dateTypeTrans(eu.getPreGraduateDate()));
                eu.setGraduateTime(_dateTypeTrans(eu.getGraduateTime()));
                eu.setAwardDegreeTime(_dateTypeTrans(eu.getAwardDegreeTime()));
                String nativePlaceProvName =  user.getNativePlaceProvName();
                if(nativePlaceProvName == null){
                    nativePlaceProvName = "";
                }
                String nativePlaceCityName = user.getNativePlaceCityName();
                if(nativePlaceCityName == null){
                    nativePlaceCityName = "";
                }
                String nativePlaceAreaName = user.getNativePlaceAreaName();
                if(nativePlaceAreaName == null){
                    nativePlaceAreaName = "";
                }
                String nativePlace = nativePlaceProvName+nativePlaceCityName+nativePlaceAreaName;
                user.setNativePlaceAreaName(nativePlace);
                String is5plus3 = eu.getIs5plus3();
                String isOutOfSchool = eu.getIsOutOfSchool();
                String isBackToSchool = eu.getIsBackToSchool();
                String isReported = eu.getIsReported();
                String awardDegreeFlag = eu.getAwardDegreeFlag();
                if(StringUtil.isNotBlank(is5plus3)){
                    if(GlobalConstant.FLAG_Y.equals(is5plus3)){
                        eu.setIs5plus3("是");
                    }else if(GlobalConstant.FLAG_N.equals(is5plus3)){
                        eu.setIs5plus3("否");
                    }
                }
                if(StringUtil.isNotBlank(isOutOfSchool)){
                    if(GlobalConstant.FLAG_Y.equals(isOutOfSchool)){
                        eu.setIsOutOfSchool("是");
                    }else if(GlobalConstant.FLAG_N.equals(isOutOfSchool)){
                        eu.setIsOutOfSchool("否");
                    }
                }
                if(StringUtil.isNotBlank(isBackToSchool)){
                    if(GlobalConstant.FLAG_Y.equals(isBackToSchool)){
                        eu.setIsBackToSchool("是");
                    }else if(GlobalConstant.FLAG_N.equals(isBackToSchool)){
                        eu.setIsBackToSchool("否");
                    }
                }
                if(StringUtil.isNotBlank(isReported)){
                    if(GlobalConstant.FLAG_Y.equals(isReported)){
                        eu.setIsReported("是");
                    }else if(GlobalConstant.FLAG_N.equals(isReported)){
                        eu.setIsReported("否");
                    }
                }
                if(StringUtil.isNotBlank(awardDegreeFlag)){
                    if(GlobalConstant.FLAG_Y.equals(awardDegreeFlag)){
                        eu.setAwardDegreeFlag("是");
                    }else if(GlobalConstant.FLAG_N.equals(awardDegreeFlag)){
                        eu.setAwardDegreeFlag("否");
                    }
                }
                String content = eu.getContent();
                if(StringUtil.isNotBlank(content)){
                    XjEduUserExtInfoForm extInfoForm = eduUserBiz.parseExtInfoXml(content);
                    //处理时间格式
                    extInfoForm.setXjRegDate(_dateTypeTrans(extInfoForm.getXjRegDate()));
                    extInfoForm.setReportedDate(_dateTypeTrans(extInfoForm.getReportedDate()));
                    extInfoForm.setInClassTime(_dateTypeTrans(extInfoForm.getInClassTime()));
                    extInfoForm.setClassEndTime(_dateTypeTrans(extInfoForm.getClassEndTime()));
                    extInfoForm.setReceiveTime(_dateTypeTrans(extInfoForm.getReceiveTime()));
                    extInfoForm.setOutOfSchoolDate(_dateTypeTrans(extInfoForm.getOutOfSchoolDate()));
                    extInfoForm.setBackToSchoolDate(_dateTypeTrans(extInfoForm.getBackToSchoolDate()));
                    extInfoForm.setDropOutSchoolDate(_dateTypeTrans(extInfoForm.getDropOutSchoolDate()));
                    extInfoForm.setJoinOrgTime(_dateTypeTrans(extInfoForm.getJoinOrgTime()));
                    extInfoForm.setJoinTime(_dateTypeTrans(extInfoForm.getJoinTime()));
                    extInfoForm.setUnderGraduateTime(_dateTypeTrans(extInfoForm.getUnderGraduateTime()));
                    extInfoForm.setUnderAwardDegreeTime(_dateTypeTrans(extInfoForm.getUnderAwardDegreeTime()));
                    extInfoForm.setMasterAwardDegreeTime(_dateTypeTrans(extInfoForm.getMasterAwardDegreeTime()));
                    String recruitType = extInfoForm.getRecruitType();
                    String hkInSchool = extInfoForm.getHkInSchool();
                    String bkgain = extInfoForm.getBkgain();
                    String xsgain = extInfoForm.getXsgain();
                    String ssyjsgain = extInfoForm.getSsyjsgain();
                    String ssxwgain = extInfoForm.getSsxwgain();
                    String graduateFlag = extInfoForm.getGraduateFlag();
                    String isRelationInto = extInfoForm.getIsRelationInto();
                    String isDoctorQuaCert = extInfoForm.getIsDoctorQuaCert();
                    String isMedicalPractitioner = extInfoForm.getIsMedicalPractitioner();
                    String isStay = extInfoForm.getIsStay();
                    String isRuralStudents =extInfoForm.getIsRuralStudents();
                    if(StringUtil.isNotBlank(recruitType)){
                        extInfoForm.setRecruitType(DictTypeEnum.getNameById(recruitType));
                    }
                    if(StringUtil.isNotBlank(extInfoForm.getPaperType())){
                        extInfoForm.setPaperType(DictTypeEnum.PaperType.getDictNameById(extInfoForm.getPaperType()));
                    }
                    if(StringUtil.isNotBlank(extInfoForm.getPaperSource())){
                        extInfoForm.setPaperSource(DictTypeEnum.PaperSource.getDictNameById(extInfoForm.getPaperSource()));
                    }
                    if(StringUtil.isNotBlank(extInfoForm.getDegreeDirection())){
                        extInfoForm.setDegreeDirection(DictTypeEnum.GotDegreeDirection.getDictNameById(extInfoForm.getDegreeDirection()));
                    }
                    if(StringUtil.isNotBlank(extInfoForm.getUnitNature())){
                        extInfoForm.setUnitNature(DictTypeEnum.UnitNature.getDictNameById(extInfoForm.getUnitNature()));
                    }
                    if(StringUtil.isNotBlank(extInfoForm.getWorkNature())){
                        extInfoForm.setWorkNature(DictTypeEnum.WorkNature.getDictNameById(extInfoForm.getWorkNature()));
                    }
                    if(StringUtil.isNotBlank(extInfoForm.getUnderMajor())){
                        extInfoForm.setUnderMajor(_checkMajorEnumId(extInfoForm.getUnderMajor()));
                    }
                    if(StringUtil.isNotBlank(extInfoForm.getGotBachelorCertSubject())){
                        extInfoForm.setGotBachelorCertSubject(_checkSubjectEnumId(extInfoForm.getGotBachelorCertSubject()));
                    }
                    if(StringUtil.isNotBlank(extInfoForm.getGotMasterCertSpe())){
                        extInfoForm.setGotMasterCertSpe(_checkMajorEnumId(extInfoForm.getGotMasterCertSpe()));
                    }
                    if(StringUtil.isNotBlank(extInfoForm.getMasterSubject())){
                        extInfoForm.setMasterSubject(_checkSubjectEnumId(extInfoForm.getMasterSubject()));
                    }
                    if(StringUtil.isNotBlank(hkInSchool)){
                        if(hkInSchool.equals("1")){
                            hkInSchool = "是";
                        }else if(hkInSchool.equals("2")){
                            hkInSchool = "否";
                        }
                        extInfoForm.setHkInSchool(hkInSchool);
                    }
                    if(StringUtil.isNotBlank(bkgain)){
                        if(bkgain.equals("1")){
                            bkgain = "是";
                        }else if(bkgain.equals("2")){
                            bkgain = "否";
                        }
                        extInfoForm.setBkgain(bkgain);
                    }
                    if(StringUtil.isNotBlank(xsgain)){
                        if(xsgain.equals("1")){
                            xsgain = "是";
                        }else if(xsgain.equals("2")){
                            xsgain = "否";
                        }
                        extInfoForm.setXsgain(xsgain);
                    }
                    if(StringUtil.isNotBlank(ssyjsgain)) {
                        if (ssyjsgain.equals("1")) {
                            ssyjsgain = "是";
                        } else if (ssyjsgain.equals("2")) {
                            ssyjsgain = "否";
                        }
                        extInfoForm.setSsyjsgain(ssyjsgain);
                    }
                    if(StringUtil.isNotBlank(ssxwgain)) {
                        if (ssxwgain.equals("1")) {
                            ssxwgain = "是";
                        } else if (ssxwgain.equals("2")) {
                            ssxwgain = "否";
                        }
                        extInfoForm.setSsxwgain(ssxwgain);
                    }
                    if(StringUtil.isNotBlank(graduateFlag)) {
                        if (graduateFlag.equals("1")) {
                            graduateFlag = "是";
                        } else if (graduateFlag.equals("2")) {
                            graduateFlag = "否";
                        }
                        extInfoForm.setGraduateFlag(graduateFlag);
                    }
                    if(StringUtil.isNotBlank(isRelationInto)) {
                        if (isRelationInto.equals("Y")) {
                            isRelationInto = "是";
                        } else if (isRelationInto.equals("N")) {
                            isRelationInto = "否";
                        }
                        extInfoForm.setIsRelationInto(isRelationInto);
                    }
                    if(StringUtil.isNotBlank(isDoctorQuaCert)) {
                        if (isDoctorQuaCert.equals("Y")) {
                            isDoctorQuaCert = "是";
                        } else if (isDoctorQuaCert.equals("N")) {
                            isDoctorQuaCert = "否";
                        }
                        extInfoForm.setIsDoctorQuaCert(isDoctorQuaCert);
                    }
                    if(StringUtil.isNotBlank(isMedicalPractitioner)) {
                        if (isMedicalPractitioner.equals("Y")) {
                            isMedicalPractitioner = "是";
                        } else if (isMedicalPractitioner.equals("N")) {
                            isMedicalPractitioner = "否";
                        }
                        extInfoForm.setIsMedicalPractitioner(isMedicalPractitioner);
                    }
                    if(StringUtil.isNotBlank(isStay)) {
                        if (isStay.equals("Y")) {
                            isStay = "是";
                        } else if (isStay.equals("N")) {
                            isStay = "否";
                        }
                        extInfoForm.setIsStay(isStay);
                    }
                    if(StringUtil.isNotBlank(isRuralStudents)) {
                        if (isRuralStudents.equals("Y")) {
                            isRuralStudents = "是";
                        } else if (isRuralStudents.equals("N")) {
                            isRuralStudents = "否";
                        }
                        extInfoForm.setIsRuralStudents(isRuralStudents);
                    }
                    eduUserForm.setEduUserExtInfoForm(extInfoForm);
                }
                String recordLocationName = eu.getRecordLocationName();
                if(StringUtil.isNotBlank(recordLocationName)){
                    eu.setRecordLocationName(recordLocationName.replaceAll(",",""));//档案所在地
                }
                String domicilePlaceName = user.getDomicilePlaceName();
                if(StringUtil.isNotBlank(domicilePlaceName)){
                    user.setDomicilePlaceName(domicilePlaceName.replaceAll(",",""));//入学前户口所在地
                }
                eduUserForm.setSysUser(user);
                eduUserForm.setResDoctor(eu.getResDoctor());
                eduUserForm.setEduUser(eu);
                eduUserFormList.add(eduUserForm);
            }
        }
        String fileName = fileName(flag);
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles(flag), eduUserFormList, response.getOutputStream(),new String[]{"0"});
    }

    /**
     * 学位信息导出Excel
     * @param response
     * @return
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping("/exportExcel2")
    public void exportExcel2(SysUser sysUser, EduUser eduUser, ResDoctor resDoctor,HttpServletResponse response,
                             String startDate, String endDate, String startSid, String endSid, String startBirthday, String endBirthday, String teacherName,
                            String from, String partId, String partStatus,String startGraduateDate,String endGraduateDate
                             ,String graduateTimeYear, String graduateTimeYM,String awardDegreeTimeYear, String awardDegreeTimeYM) throws Exception{
        Map<String, Object> paramMap=new HashMap<String,Object>();
        SysUser currUser = GlobalContext.getCurrentUser();
        if(GlobalConstant.USER_LIST_LOCAL.equals(from)){
            //培养单位
            resDoctor.setOrgFlow(currUser.getOrgFlow());
        }else if(GlobalConstant.USER_LIST_CHARGE.equals(from)){
            //分委会
            eduUser.setTrainOrgId(currUser.getDeptFlow());
        }
        paramMap.put("eduUser", eduUser);
        paramMap.put("sysUser", sysUser);
        paramMap.put("resDoctor", resDoctor);
        paramMap.put("startDate", startDate);
        paramMap.put("endDate", endDate);
        paramMap.put("startSid", startSid);
        paramMap.put("endSid", endSid);
        paramMap.put("startBirthday", startBirthday);
        paramMap.put("endBirthday", endBirthday);
        paramMap.put("teacherName", teacherName);
        paramMap.put("partId", partId);
        paramMap.put("partStatus", partStatus);
        paramMap.put("startGraduateDate", startGraduateDate);
        paramMap.put("endGraduateDate", endGraduateDate);
        //毕业时间(年份)
        paramMap.put("graduateTimeYear", graduateTimeYear);
        //毕业时间(年月)
        paramMap.put("graduateTimeYM", graduateTimeYM);
        //授予学业时间（年份）
        paramMap.put("awardDegreeTimeYear", awardDegreeTimeYear);
        //授予学业时间（年月）
        paramMap.put("awardDegreeTimeYM", awardDegreeTimeYM);
        List<XjEduUserForm> eduUserFormList = new ArrayList<XjEduUserForm>();
        List<XjEduUserInfoExt> eduUserList = eduUserBiz.searchEduUserInfoExtBySysUser1(paramMap);
        if(eduUserList!=null && eduUserList.size()>0){
            for(XjEduUserInfoExt eu : eduUserList){
                if(StringUtil.isNotBlank(eu.getSecondTeacher())){
                    eu.setFirstTeacher(eu.getFirstTeacher()+"，"+eu.getSecondTeacher());
                }
                XjEduUserForm eduUserForm = new XjEduUserForm();
                String content = eu.getContent();
                if(StringUtil.isNotBlank(content)){
                    XjEduUserExtInfoForm extInfoForm = eduUserBiz.parseExtInfoXml(content);
                    if(StringUtil.isNotBlank(extInfoForm.getPaperType())){
                        extInfoForm.setPaperType(DictTypeEnum.PaperType.getDictNameById(extInfoForm.getPaperType()));
                    }
                    if(StringUtil.isNotBlank(extInfoForm.getPaperSource())){
                        extInfoForm.setPaperSource(DictTypeEnum.PaperSource.getDictNameById(extInfoForm.getPaperSource()));
                    }
                    if(StringUtil.isNotBlank(extInfoForm.getDegreeDirection())){
                        extInfoForm.setDegreeDirection(DictTypeEnum.GotDegreeDirection.getDictNameById(extInfoForm.getDegreeDirection()));
                    }
                    if(StringUtil.isNotBlank(extInfoForm.getUnitNature())){
                        extInfoForm.setUnitNature(DictTypeEnum.UnitNature.getDictNameById(extInfoForm.getUnitNature()));
                    }
                    if(StringUtil.isNotBlank(extInfoForm.getWorkNature())){
                        extInfoForm.setWorkNature(DictTypeEnum.WorkNature.getDictNameById(extInfoForm.getWorkNature()));
                    }
                    eduUserForm.setEduUserExtInfoForm(extInfoForm);
                }
                SysUser user =eu.getSysUser();
                //处理时间格式
                user.setUserBirthday(_dateTypeTrans(user.getUserBirthday()));
                eu.setPreGraduateDate(_dateTypeTrans(eu.getPreGraduateDate()));
                eu.setGraduateTime(_dateTypeTrans(eu.getGraduateTime()));
                eu.setAwardDegreeTime(_dateTypeTrans(eu.getAwardDegreeTime()));
                eduUserForm.setSysUser(user);
                eduUserForm.setResDoctor(eu.getResDoctor());
                eduUserForm.setEduUser(eu);
                eduUserForm.setDefenceTime(_dateTypeTrans(eu.getDefenceTime()));
                eduUserFormList.add(eduUserForm);
            }
        }
        String [] titles = new String[]{
                "eduUser.sid:学号",
                "sysUser.userName:姓名",
                "sysUser.sexName:性别",
                "sysUser.nationName:民族",
                "sysUser.politicsStatusName:政治面貌",
                "sysUser.userBirthday:出生年月日",
                "sysUser.cretTypeName:身份证件类型",
                "sysUser.idNo:身份证件号码",
                "defenceTime:申请学位时间",
                "sysUser.domicilePlaceAddress:攻读本学位前户口所在省（直辖市）",
                "eduUser.period:入学年月",
                "eduUser.trainCategoryName:学位类别",
                "eduUser.majorName:专业名称",
                "eduUser.studyFormName:学习形式",
                "eduUser.admitTypeName:录取类别",
                "eduUser.firstTeacher:导师姓名",
                "eduUser.awardDegreeTime:获学位年月日",
                "eduUserExtInfoForm.paperTitle:论文题目",
                "eduUserExtInfoForm.paperKey:论文关键词",
                "eduUserExtInfoForm.paperType:论文类型",
                "eduUserExtInfoForm.paperSource:论文选题来源",
                "resDoctor.orgName:导师所在单位名称",
                "eduUser.trainOrgName:学位授予所在分委员会名称",
                "eduUserExtInfoForm.degreeDirection:获学位后去向",
                "eduUserExtInfoForm.unitNature:就业单位性质",
                "eduUserExtInfoForm.unitAddress:就业单位所在省（直辖市）",
                "eduUserExtInfoForm.workNature:工作性质",
                "eduUserExtInfoForm.unitName:就业单位名称",
                "eduUserExtInfoForm.unitLinkManPhone:联系电话",
                "eduUserExtInfoForm.unitLinkManEmail:电子邮箱"
        };
        String fileName = "学位信息表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, eduUserFormList, response.getOutputStream(),new String[]{"0"});
    }

    /**
     * 学籍导出信息标题
     * @param flag
     */
    private String[] titles(String flag){
        String[] titles = null;
        if("base".equals(flag)){
            titles = new String[]{//基本信息
                    "eduUser.sid:学号",
                    "sysUser.userName:姓名",
                    "eduUser.nameSpell:姓名拼音",
                    "sysUser.cretTypeName:证件类型",
                    "sysUser.idNo:证件号码",
                    "sysUser.userBirthday:出生日期",
                    "sysUser.sexName:性别",
                    "sysUser.nationName:民族",
                    "eduUser.period:入学年级",
                    "eduUser.className:班级",
                    "eduUser.majorName:专业名称",
                    "eduUser.is5plus3:是否5+3培养模式",
                    "eduUser.trainOrgName:学位分委员会",
                    "resDoctor.orgName:培养单位",
                    "eduUser.researchDirName:研究方向",
                    "eduUser.trainTypeName:培养层次",
                    "eduUser.trainCategoryName:培养类型",
                    "eduUser.firstTeacher:导师一",
                    "eduUser.secondTeacher:导师二",
                    "eduUser.schoolRollStatusName:学籍状态",
                    "eduUser.isReported:是否报到",
                    "eduUserExtInfoForm.reportedDate:报到时间",
                    "eduUser.atSchoolStatusName:在校状态",
                    "eduUser.studyFormName:学习形式",
                    "eduUserExtInfoForm.inClassTime:课程班入班时间",
                    "eduUserExtInfoForm.classEndTime:课程班结业时间",
                    "eduUserExtInfoForm.classCertNo:结业证书编号",
                    "eduUserExtInfoForm.receiveTime:导师接收时间",
                    "eduUser.isOutOfSchool:是否休学",
                    "eduUserExtInfoForm.outOfSchoolDate:休学日期",
                    "eduUser.isBackToSchool:是否复学",
                    "eduUserExtInfoForm.backToSchoolDate:复学日期",
                    "eduUserExtInfoForm.dropOutSchoolDate:退学时间"
            };
        }else if("recruit".equals(flag)){//录取信息
            titles = new String[]{
                    "eduUser.sid:学号",
                    "sysUser.userName:姓名",
                    "eduUser.studentCode:考生编号",
                    "eduUser.studentSourceName:考生来源",
                    "eduUser.recruitSeasonName:招生季节",
                    "eduUserExtInfoForm.recruitType:招录方式",
                    "eduUser.isExceptionalName:是否破格",
                    "eduUser.admitTypeName:录取类别",
                    "eduUserExtInfoForm.foreignLanguageName:外国语名称",
                    "eduUserExtInfoForm.foreignLanguageScore:外国语成绩",
                    "eduUserExtInfoForm.politicalTheoryName:政治理论",
                    "eduUserExtInfoForm.politicalTheoryScore:政治理论成绩",
                    "eduUserExtInfoForm.firstSubjectName:业务课一",
                    "eduUserExtInfoForm.firstSubjectScore:业务课一成绩",
                    "eduUserExtInfoForm.secondSubjectName:业务课二",
                    "eduUserExtInfoForm.secondSubjectScore:业务课二成绩",
                    "eduUserExtInfoForm.firstAddExamName:加试科一",
                    "eduUserExtInfoForm.firstAddExamScore:加试科一成绩",
                    "eduUserExtInfoForm.secondAddExamName:加试科二",
                    "eduUserExtInfoForm.secondAddExamScore:加试科二成绩",
                    "eduUserExtInfoForm.reexamineScore:复试成绩",
                    "eduUserExtInfoForm.totalPointsScore:总分"
            };
        }else if("required".equals(flag)){//必填信息
            titles = new String[]{
                    "eduUser.sid:学号",
                    "sysUser.userName:姓名",
                    "sysUser.nativePlaceAreaName:籍贯",
                    "sysUser.maritalName:婚姻状况",
                    "sysUser.bearName:生育状况",
                    "sysUser.politicsStatusName:政治面貌",
                    "eduUserExtInfoForm.joinOrgTime:入党（团）时间",
                    "eduUserExtInfoForm.isRelationInto:党团关系是否转入",
                    "eduUserExtInfoForm.joinTime:转入年月日",
                    "sysUser.domicilePlaceName:入学前户口所在地",
                    "sysUser.domicilePlaceAddress:入学前户口所在详细地址",
                    "eduUserExtInfoForm.hkInSchool:户口是否需要迁入我校",
                    "eduUserExtInfoForm.hkChangeNo:户口迁移证编号",
                    "eduUserExtInfoForm.oldOrgName:原学习或工作单位",
                    "eduUserExtInfoForm.oldFileLocationOrg:原档案所在单位",
                    "eduUserExtInfoForm.oldFileLocationOrgCode:原档案所在单位邮编",
                    "eduUser.recordLocationName:档案所在地",
                    "eduUserExtInfoForm.nowResideAddress:现家庭住址",
                    "eduUserExtInfoForm.postCode:邮编",
                    "eduUserExtInfoForm.linkMan:紧急联系人姓名",
                    "eduUserExtInfoForm.telephone:紧急联系人电话",
                    "eduUserExtInfoForm.isDoctorQuaCert:是否有医师资格证",
                    "eduUserExtInfoForm.codeDoctorQuaCert:资格证编号",
                    "eduUserExtInfoForm.isMedicalPractitioner:是否有职业医师执照",
                    "eduUserExtInfoForm.codeMedicalPractitioner:执照编号",
                    "eduUserExtInfoForm.isStay:是否住宿",
                    "eduUserExtInfoForm.roomNumStay:宿舍号",
                    "sysUser.userPhone:本人手机号码",
                    "sysUser.userEmail:电子邮箱",
                    "sysUser.weiXinName:微信号",
                    "sysUser.userQq:QQ号",
                    "eduUserExtInfoForm.height:身高",
                    "eduUserExtInfoForm.bloodType:血型",
                    "eduUserExtInfoForm.ybCardNo:医保卡号",
                    "eduUserExtInfoForm.isRuralStudents:是否农村学生"
            };
        }else if("optional".equals(flag)){//选填信息
            titles = new String[]{
                    "eduUser.sid:学号",
                    "sysUser.userName:姓名",
                    "eduUserExtInfoForm.foreignLanguageLevel:外语水平",
                    "eduUserExtInfoForm.mandarinLevel:普通话水平",
                    "eduUserExtInfoForm.computerLevel:计算机水平",
                    "eduUserExtInfoForm.studentSourceArea:生源省市",
                    "eduUserExtInfoForm.schoolSystem:学制",
                    "eduUserExtInfoForm.dormitoryAdd:宿舍地址",
                    "eduUserExtInfoForm.homePhone:家庭电话",
                    "eduUserExtInfoForm.mateName:配偶姓名",
                    "eduUserExtInfoForm.mateIdNo:配偶身份证",
                    "eduUserExtInfoForm.mateName:配偶工作单位",
                    "eduUserExtInfoForm.speciality:特长",
                    "eduUserExtInfoForm.mainResume:本人主要简历",
                    "eduUserExtInfoForm.reAndPuStatusContent:入学前奖惩情况",
                    "eduUserExtInfoForm.scientificTogether:曾担任过合作教学工作和进行何种科研工作",
                    "eduUserExtInfoForm.remark:备注"
            };
        }else if("fee".equals(flag)){//学费信息
            titles = new String[]{
                    "eduUser.sid:学号",
                    "sysUser.userName:姓名",
                    "eduUserExtInfoForm.accountNum:缴费账号",
                    "eduUserExtInfoForm.tuitionFee:学费总额",
                    "eduUserExtInfoForm.paytuitionFee:已缴纳学费",
                    "eduUserExtInfoForm.arrearageFee:欠缴纳学费",
                    "eduUserExtInfoForm.dormitoryFee:住宿费",
                    "eduUserExtInfoForm.payDormitoryFee:已缴纳住宿费",
                    "eduUserExtInfoForm.arrearageDormitoryFee:欠缴纳住宿费"
            };
        }else if("degree".equals(flag)){//学业与学位授予信息
            titles = new String[]{
                    "eduUser.sid:学号",
                    "sysUser.userName:姓名",
                    "eduUserExtInfoForm.firstEducationContentName:第一学历",
                    "eduUserExtInfoForm.directionalOrgName:定向培养单位",
                    "eduUserExtInfoForm.bkgain:是否获得本科学历",
                    "eduUserExtInfoForm.underStudyForm:获得本科学历的学习形式",
                    "eduUserExtInfoForm.underGraduateTime:本科毕业年月",
                    "eduUserExtInfoForm.underDiplomaCode:本科毕业证书编号",
                    "eduUserExtInfoForm.underGraduateOrgName:本科毕业单位名称",
                    "eduUserExtInfoForm.underGraduateMajor:本科毕业专业名称",
                    "eduUserExtInfoForm.xsgain:是否获得学士学位",
                    "eduUserExtInfoForm.underAwardDegreeTime:获得学士学位年月",
                    "eduUserExtInfoForm.underDegreeCertCode:学士学位证书编号",
                    "eduUserExtInfoForm.underAwardDegreeOrg:获学士学位单位名称",
                    "eduUserExtInfoForm.underMajor:获学士学位专业名称",
                    "eduUserExtInfoForm.gotBachelorCertSubject:获得学士学位学科门类",
                    "eduUserExtInfoForm.gotMasterCertSpe:获得硕士学位专业",
                    "eduUserExtInfoForm.code:最后学位证编号",
                    "eduUserExtInfoForm.ssyjsgain:是否获得硕士研究生学历",
                    "eduUserExtInfoForm.masterGraduateTime:硕士研究生毕业年月",
                    "eduUserExtInfoForm.masterDiplomaCode:硕士研究生毕业证编号",
                    "eduUserExtInfoForm.masterUnitName:硕士研究生毕业单位名称",
                    "eduUserExtInfoForm.masterGraduateMajor:硕士研究生毕业专业名称",
                    "eduUserExtInfoForm.ssxwgain:是否获得硕士学位",
                    "eduUserExtInfoForm.masterStudyForm:获得硕士学位的学习形式",
                    "eduUserExtInfoForm.masterAwardDegreeTime:获得硕士学位年月",
                    "eduUserExtInfoForm.masterDegreeCertCode:硕士学位证书编号",
                    "eduUserExtInfoForm.masterAwardDegreeOrg:获得硕士学位单位名称",
                    "eduUserExtInfoForm.masterSubject:获得硕士学位学科门类",
                    "eduUser.graduateTime:预毕业时间",
                    "eduUserExtInfoForm.graduateFlag:是否毕业",
                    "eduUser.graduateTime:毕业时间",
                    "eduUser.diplomaCode:毕业证书编号",
                    "eduUser.awardDegreeFlag:是否授予学位",
                    "eduUserExtInfoForm.awardSubjectCategory:授予学科门类",
                    "eduUser.awardDegreeCategoryName:授予学位类别",
                    "eduUser.awardDegreeTime:授予学位时间",
                    "eduUser.awardDegreeCertCode:授予学位证书编号"
            };
        }else if("paper".equals(flag)){//学位论文信息
            titles = new String[]{
                    "eduUser.sid:学号",
                    "sysUser.userName:姓名",
                    "eduUserExtInfoForm.paperTitle:论文题目",
                    "eduUserExtInfoForm.paperSource:论文选题来源",
                    "eduUserExtInfoForm.paperKey:论文关键词",
                    "eduUserExtInfoForm.paperType:论文类型"
            };
        }else if("employment".equals(flag)){//就业信息
            titles = new String[]{
                    "eduUser.sid:学号",
                    "sysUser.userName:姓名",
                    "eduUserExtInfoForm.degreeDirection:获学位后去向",
                    "eduUserExtInfoForm.unitNature:就业单位性质",
                    "eduUserExtInfoForm.unitAddress:就业单位所在省（直辖市）",
                    "eduUserExtInfoForm.workNature:工作性质",
                    "eduUserExtInfoForm.unitName:就业单位名称",
                    "eduUserExtInfoForm.unitLinkMan:就业单位联系人",
                    "eduUserExtInfoForm.unitLinkManPhone:就业单位联系人电话",
                    "eduUserExtInfoForm.unitLinkManEmail:就业单位联系人邮箱"
            };
        }else if("education".equals(flag)){//攻读学历
            titles = new String[]{
                    "eduUser.sid:学号",
                    "sysUser.userName:姓名",
                    "eduUser.graduateTime:预毕业时间",
                    "eduUserExtInfoForm.graduateFlag:是否毕业",
                    "eduUser.graduateTime:毕业时间",
                    "eduUser.diplomaCode:毕业证书编号",
                    "eduUser.awardDegreeFlag:是否授予学位",
                    "eduUserExtInfoForm.awardSubjectCategory:授予学科门类",
                    "eduUser.awardDegreeCategoryName:授予学位类别",
                    "eduUser.awardDegreeTime:授予学位时间",
                    "eduUser.awardDegreeCertCode:授予学位证书编号"
            };
        }else{//学籍所有信息
            titles = new String[]{
                    "eduUser.sid:学号",
                    "sysUser.userName:姓名",
                    "eduUser.nameSpell:姓名拼音",
                    "sysUser.cretTypeName:证件类型",
                    "sysUser.idNo:证件号码",
                    "sysUser.userBirthday:出生日期",
                    "sysUser.sexName:性别",
                    "sysUser.nationName:民族",
                    "eduUser.period:入学年级",
                    "eduUser.className:班级",
                    "eduUser.majorName:专业名称",
                    "eduUser.is5plus3:是否5+3培养模式",
                    "eduUser.trainOrgName:学位分委员会",
                    "resDoctor.orgName:培养单位",
                    "eduUser.researchDirName:研究方向",
                    "eduUser.trainTypeName:培养层次",
                    "eduUser.trainCategoryName:培养类型",
                    "eduUser.firstTeacher:导师一",
                    "eduUser.secondTeacher:导师二",
                    "eduUser.schoolRollStatusName:学籍状态",
                    "eduUser.isReported:是否报到",
                    "eduUserExtInfoForm.reportedDate:报到时间",
                    "eduUser.atSchoolStatusName:在校状态",
                    "eduUser.studyFormName:学习形式",
                    "eduUserExtInfoForm.inClassTime:课程班入班时间",
                    "eduUserExtInfoForm.classEndTime:课程班结业时间",
                    "eduUserExtInfoForm.classCertNo:结业证书编号",
                    "eduUserExtInfoForm.receiveTime:导师接收时间",
                    "eduUser.isOutOfSchool:是否休学",
                    "eduUserExtInfoForm.outOfSchoolDate:休学日期",
                    "eduUser.isBackToSchool:是否复学",
                    "eduUserExtInfoForm.backToSchoolDate:复学日期",
                    "eduUserExtInfoForm.dropOutSchoolDate:退学时间",
                    "eduUser.studentCode:考生编号",
                    "eduUser.studentSourceName:考生来源",
                    "eduUser.recruitSeasonName:招生季节",
                    "eduUserExtInfoForm.recruitType:招录方式",
                    "eduUser.isExceptionalName:是否破格",
                    "eduUser.admitTypeName:录取类别",
                    "eduUserExtInfoForm.foreignLanguageName:外国语名称",
                    "eduUserExtInfoForm.foreignLanguageScore:外国语成绩",
                    "eduUserExtInfoForm.politicalTheoryName:政治理论",
                    "eduUserExtInfoForm.politicalTheoryScore:政治理论成绩",
                    "eduUserExtInfoForm.firstSubjectName:业务课一",
                    "eduUserExtInfoForm.firstSubjectScore:业务课一成绩",
                    "eduUserExtInfoForm.secondSubjectName:业务课二",
                    "eduUserExtInfoForm.secondSubjectScore:业务课二成绩",
                    "eduUserExtInfoForm.firstAddExamName:加试科一",
                    "eduUserExtInfoForm.firstAddExamScore:加试科一成绩",
                    "eduUserExtInfoForm.secondAddExamName:加试科二",
                    "eduUserExtInfoForm.secondAddExamScore:加试科二成绩",
                    "eduUserExtInfoForm.reexamineScore:复试成绩",
                    "eduUserExtInfoForm.totalPointsScore:总分",
                    "sysUser.nativePlaceAreaName:籍贯",
                    "sysUser.maritalName:婚姻状况",
                    "sysUser.bearName:生育状况",
                    "sysUser.politicsStatusName:政治面貌",
                    "eduUserExtInfoForm.joinOrgTime:入党（团）时间",
                    "eduUserExtInfoForm.isRelationInto:党团关系是否转入",
                    "eduUserExtInfoForm.joinTime:转入年月日",
                    "sysUser.domicilePlaceName:入学前户口所在地",
                    "sysUser.domicilePlaceAddress:入学前户口所在详细地址",
                    "eduUserExtInfoForm.hkInSchool:户口是否需要迁入我校",
                    "eduUserExtInfoForm.hkChangeNo:户口迁移证编号",
                    "eduUserExtInfoForm.oldOrgName:原学习或工作单位",
                    "eduUserExtInfoForm.oldFileLocationOrg:原档案所在单位",
                    "eduUserExtInfoForm.oldFileLocationOrgCode:原档案所在单位邮编",
                    "eduUser.recordLocationName:档案所在地",
                    "eduUserExtInfoForm.nowResideAddress:现家庭住址",
                    "eduUserExtInfoForm.postCode:邮编",
                    "eduUserExtInfoForm.linkMan:紧急联系人姓名",
                    "eduUserExtInfoForm.telephone:紧急联系人电话",
                    "eduUserExtInfoForm.isDoctorQuaCert:是否有医师资格证",
                    "eduUserExtInfoForm.codeDoctorQuaCert:资格证编号",
                    "eduUserExtInfoForm.isMedicalPractitioner:是否有职业医师执照",
                    "eduUserExtInfoForm.codeMedicalPractitioner:执照编号",
                    "eduUserExtInfoForm.isStay:是否住宿",
                    "eduUserExtInfoForm.roomNumStay:宿舍号",
                    "sysUser.userPhone:本人手机号码",
                    "sysUser.userEmail:电子邮箱",
                    "sysUser.weiXinName:微信号",
                    "sysUser.userQq:QQ号",
                    "eduUserExtInfoForm.height:身高",
                    "eduUserExtInfoForm.bloodType:血型",
                    "eduUserExtInfoForm.ybCardNo:医保卡号",
                    "eduUserExtInfoForm.foreignLanguageLevel:外语水平",
                    "eduUserExtInfoForm.mandarinLevel:普通话水平",
                    "eduUserExtInfoForm.computerLevel:计算机水平",
                    "eduUserExtInfoForm.studentSourceArea:生源省市",
                    "eduUserExtInfoForm.schoolSystem:学制",
                    "eduUserExtInfoForm.dormitoryAdd:宿舍地址",
                    "eduUserExtInfoForm.homePhone:家庭电话",
                    "eduUserExtInfoForm.mateName:配偶姓名",
                    "eduUserExtInfoForm.mateIdNo:配偶身份证",
                    "eduUserExtInfoForm.mateName:配偶工作单位",
                    "eduUserExtInfoForm.speciality:特长",
                    "eduUserExtInfoForm.mainResume:本人主要简历",
                    "eduUserExtInfoForm.reAndPuStatusContent:入学前奖惩情况",
                    "eduUserExtInfoForm.scientificTogether:曾担任过合作教学工作和进行何种科研工作",
                    "eduUserExtInfoForm.remark:备注",
                    "eduUserExtInfoForm.accountNum:缴费账号",
                    "eduUserExtInfoForm.tuitionFee:学费总额",
                    "eduUserExtInfoForm.paytuitionFee:已缴纳学费",
                    "eduUserExtInfoForm.arrearageFee:欠缴纳学费",
                    "eduUserExtInfoForm.dormitoryFee:住宿费",
                    "eduUserExtInfoForm.payDormitoryFee:已缴纳住宿费",
                    "eduUserExtInfoForm.arrearageDormitoryFee:欠缴纳住宿费",
                    "eduUserExtInfoForm.firstEducationContentName:第一学历",
                    "eduUserExtInfoForm.directionalOrgName:定向培养单位",
                    "eduUserExtInfoForm.bkgain:是否获得本科学历",
                    "eduUserExtInfoForm.underStudyForm:获得本科学历的学习形式",
                    "eduUserExtInfoForm.underGraduateTime:本科毕业年月",
                    "eduUserExtInfoForm.underDiplomaCode:本科毕业证书编号",
                    "eduUserExtInfoForm.underGraduateOrgName:本科毕业单位名称",
                    "eduUserExtInfoForm.underGraduateMajor:本科毕业专业名称",
                    "eduUserExtInfoForm.xsgain:是否获得学士学位",
                    "eduUserExtInfoForm.underAwardDegreeTime:获得学士学位年月",
                    "eduUserExtInfoForm.underDegreeCertCode:学士学位证书编号",
                    "eduUserExtInfoForm.underAwardDegreeOrg:获学士学位单位名称",
                    "eduUserExtInfoForm.underMajor:获学士学位专业名称",
                    "eduUserExtInfoForm.gotBachelorCertSubject:获得学士学位学科门类",
                    "eduUserExtInfoForm.gotMasterCertSpe:获得硕士学位专业",
                    "eduUserExtInfoForm.code:最后学位证编号",
                    "eduUserExtInfoForm.ssyjsgain:是否获得硕士研究生学历",
                    "eduUserExtInfoForm.masterGraduateTime:硕士研究生毕业年月",
                    "eduUserExtInfoForm.masterDiplomaCode:硕士研究生毕业证编号",
                    "eduUserExtInfoForm.masterUnitName:硕士研究生毕业单位名称",
                    "eduUserExtInfoForm.masterGraduateMajor:硕士研究生毕业专业名称",
                    "eduUserExtInfoForm.ssxwgain:是否获得硕士学位",
                    "eduUserExtInfoForm.masterStudyForm:获得硕士学位的学习形式",
                    "eduUserExtInfoForm.masterAwardDegreeTime:获得硕士学位年月",
                    "eduUserExtInfoForm.masterDegreeCertCode:硕士学位证书编号",
                    "eduUserExtInfoForm.masterAwardDegreeOrg:获得硕士学位单位名称",
                    "eduUserExtInfoForm.masterSubject:获得硕士学位学科门类",
                    "eduUser.graduateTime:预毕业时间",
                    "eduUserExtInfoForm.graduateFlag:是否毕业",
                    "eduUser.graduateTime:毕业时间",
                    "eduUser.diplomaCode:毕业证书编号",
                    "eduUser.awardDegreeFlag:是否授予学位",
                    "eduUserExtInfoForm.awardSubjectCategory:授予学科门类",
                    "eduUser.awardDegreeCategoryName:授予学位类别",
                    "eduUser.awardDegreeTime:授予学位时间",
                    "eduUser.awardDegreeCertCode:授予学位证书编号",
                    "eduUserExtInfoForm.paperTitle:论文题目",
                    "eduUserExtInfoForm.paperSource:论文选题来源",
                    "eduUserExtInfoForm.paperKey:论文关键词",
                    "eduUserExtInfoForm.paperType:论文类型",
                    "eduUserExtInfoForm.degreeDirection:获学位后去向",
                    "eduUserExtInfoForm.unitNature:就业单位性质",
                    "eduUserExtInfoForm.unitAddress:就业单位所在省（直辖市）",
                    "eduUserExtInfoForm.workNature:工作性质",
                    "eduUserExtInfoForm.unitName:就业单位名称",
                    "eduUserExtInfoForm.unitLinkMan:就业单位联系人",
                    "eduUserExtInfoForm.unitLinkManPhone:就业单位联系人电话",
                    "eduUserExtInfoForm.unitLinkManEmail:就业单位联系人邮箱",
                    "eduUserExtInfoForm.isRuralStudents:是否农村学生"
            };
        }
        return titles;
    }

    /**
     * 学籍导出excel文件名
     * @param flag
     */
    private String fileName(String flag){
        String fileName = "学籍信息表.xls";
        if("base".equals(flag)){
            fileName = "基本信息表.xls";
        }else if("recruit".equals(flag)){
            fileName = "录取信息表.xls";
        }else if("required".equals(flag)){
            fileName = "必填信息表.xls";
        }else if("optional".equals(flag)){
            fileName = "选填信息表.xls";
        }else if("fee".equals(flag)){
            fileName = "学费信息表.xls";
        }else if("degree".equals(flag)){
            fileName = "学业与学位授予信息表.xls";
        }else if("paper".equals(flag)){
            fileName = "学位论文信息表.xls";
        }else if("employment".equals(flag)){
            fileName = "就业信息表.xls";
        }
        return fileName;
    }

    /**
     * 学籍分类操作弹框
     */
    @RequestMapping("/eduOption")
    public String eduOption(String from,Model model,String isFeeMaster){
        model.addAttribute("isFeeMaster",isFeeMaster);
        //就业信息 开放时间
        SysCfg openEmploymentStartDate=cfgBiz.read("open_employment_start_date");
        model.addAttribute("openEmploymentStartDate", openEmploymentStartDate);
        SysCfg closeEmploymentEndDate=cfgBiz.read("close_employment_end_date");
        model.addAttribute("closeEmploymentEndDate", closeEmploymentEndDate);
        //学位论文 开放时间
        SysCfg openPaperStartDate=cfgBiz.read("open_paper_start_date");
        model.addAttribute("openPaperStartDate", openPaperStartDate);
        SysCfg closePaperEndDate=cfgBiz.read("close_paper_end_date");
        model.addAttribute("closePaperEndDate", closePaperEndDate);
        //学业与学位授予信息 开放时间
        SysCfg openDegreeGrantStartDate=cfgBiz.read("open_degreeGrant_start_date");
        model.addAttribute("openDegreeGrantStartDate", openDegreeGrantStartDate);
        SysCfg closeDegreeGrantEndDate=cfgBiz.read("close_degreeGrant_end_date");
        model.addAttribute("closeDegreeGrantEndDate", closeDegreeGrantEndDate);
        //必填信息 开放时间
        SysCfg openRequiredStartDate=cfgBiz.read("open_required_start_date");
        model.addAttribute("openRequiredStartDate", openRequiredStartDate);
        SysCfg closeRequiredEndDate=cfgBiz.read("close_required_end_date");
        model.addAttribute("closeRequiredEndDate", closeRequiredEndDate);
        //选填信息 开放时间
        SysCfg openOptionalStartDate=cfgBiz.read("open_optional_start_date");
        model.addAttribute("openOptionalStartDate", openOptionalStartDate);
        SysCfg closeOptionalEndDate=cfgBiz.read("close_optional_end_date");
        model.addAttribute("closeOptionalEndDate", closeOptionalEndDate);
        List<SysOrg> orgList = orgBiz.searchTrainOrgList();
        model.addAttribute("orgList", orgList);
        //开放学籍修改权限(所有Y/特定学生N)
        SysCfg permissionCfg = cfgBiz.read("xjgl_modify_permission");
        model.addAttribute("permissionCfg", permissionCfg);
        return "xjgl/plat/eduOption";
    }

    /**
     * 保存学籍各版块的修改开放时间
     */
    @RequestMapping(value="/sysCfgUpdate")
    @ResponseBody
    public String sysCfgUpdate(HttpServletRequest request){
        String requiredStartDate = request.getParameter("requiredStartDate");
        String requiredEndDate = request.getParameter("requiredEndDate");
        String optionalStartDate = request.getParameter("optionalStartDate");
        String optionalEndDate = request.getParameter("optionalEndDate");
        String degreeGrantStartDate = request.getParameter("degreeGrantStartDate");
        String degreeGrantEndDate = request.getParameter("degreeGrantEndDate");
        String paperStartDate = request.getParameter("paperStartDate");
        String paperEndDate = request.getParameter("paperEndDate");
        String employmentStartDate = request.getParameter("employmentStartDate");
        String employmentEndDate = request.getParameter("employmentEndDate");
        String registerStartDate = request.getParameter("registerStartDate");
        String registerEndDate = request.getParameter("registerEndDate");
        List<SysCfg> cfgList = new ArrayList<SysCfg>();
        //必填信息开放起止时间
        SysCfg requiredStart = new SysCfg();
        requiredStart.setCfgCode("open_required_start_date");
        requiredStart.setCfgValue(requiredStartDate);
        cfgList.add(requiredStart);
        SysCfg requiredEnd = new SysCfg();
        requiredEnd.setCfgCode("close_required_end_date");
        requiredEnd.setCfgValue(requiredEndDate);
        cfgList.add(requiredEnd);
        //选填信息开放起止时间
        SysCfg optionalStart = new SysCfg();
        optionalStart.setCfgCode("open_optional_start_date");
        optionalStart.setCfgValue(optionalStartDate);
        cfgList.add(optionalStart);
        SysCfg optionalEnd = new SysCfg();
        optionalEnd.setCfgCode("close_optional_end_date");
        optionalEnd.setCfgValue(optionalEndDate);
        cfgList.add(optionalEnd);
        //学业与学位授予信息开放时间
        SysCfg degreeGrantStart = new SysCfg();
        degreeGrantStart.setCfgCode("open_degreeGrant_start_date");
        degreeGrantStart.setCfgValue(degreeGrantStartDate);
        cfgList.add(degreeGrantStart);
        SysCfg degreeGrantEnd = new SysCfg();
        degreeGrantEnd.setCfgCode("close_degreeGrant_end_date");
        degreeGrantEnd.setCfgValue(degreeGrantEndDate);
        cfgList.add(degreeGrantEnd);
        //学位论文信息开放时间
        SysCfg paperStart = new SysCfg();
        paperStart.setCfgCode("open_paper_start_date");
        paperStart.setCfgValue(paperStartDate);
        cfgList.add(paperStart);
        SysCfg paperEnd = new SysCfg();
        paperEnd.setCfgCode("close_paper_end_date");
        paperEnd.setCfgValue(paperEndDate);
        cfgList.add(paperEnd);
        //就业信息开放时间
        SysCfg employmentStart = new SysCfg();
        employmentStart.setCfgCode("open_employment_start_date");
        employmentStart.setCfgValue(employmentStartDate);
        cfgList.add(employmentStart);
        SysCfg employmentEnd = new SysCfg();
        employmentEnd.setCfgCode("close_employment_end_date");
        employmentEnd.setCfgValue(employmentEndDate);
        cfgList.add(employmentEnd);
        //登记信息开放时间
        SysCfg registerStart = new SysCfg();
        registerStart.setCfgCode("open_register_start_date");
        registerStart.setCfgValue(registerStartDate);
        cfgList.add(registerStart);
        SysCfg registerEnd = new SysCfg();
        registerEnd.setCfgCode("close_register_end_date");
        registerEnd.setCfgValue(registerEndDate);
        cfgList.add(registerEnd);
        cfgBiz.save(cfgList);
        //开放修改权限
        String wsId = (String)getSessionAttribute(GlobalConstant.CURRENT_WS_ID);
        String [] cfgCodes =request.getParameterValues("cfgCode");
        if(null != cfgCodes){
            List<SysCfg> sysCfgList = new ArrayList<SysCfg>();
            for(String cfgCode : cfgCodes){
                String sysCfgValue=request.getParameter(cfgCode);
                if(StringUtil.isBlank(sysCfgValue)){
                    EduUserExample example = new EduUserExample();
                    example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andIsMdfInfoEqualTo(GlobalConstant.RECORD_STATUS_Y);
                    List<EduUser> userLst = this.eduUserMapper.selectByExample(example);
                    if(null != userLst && userLst.size() > 0){
                        for(EduUser user : userLst){
                            user.setIsMdfInfo("");
                            this.eduUserMapper.updateByPrimaryKeySelective(user);//取消特定学生修改权限
                        }
                    }
                }
                SysCfg cfg=new SysCfg();
                cfg.setCfgCode(cfgCode);
                cfg.setCfgValue(sysCfgValue);
                cfg.setCfgDesc("开放学籍修改权限");
                cfg.setWsId(wsId);
                cfg.setWsName(InitConfig.getWorkStationName(cfg.getWsId()));
                if(StringUtil.isBlank(cfg.getWsName())) {
                    cfg.setWsName("全局公用配置");
                }
                cfg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                sysCfgList.add(cfg);
            }
            cfgBiz.save(sysCfgList);
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

    /**
     * 删除该批次导入的成绩记录
     * @param impFlow
     * @return
     */
    @RequestMapping(value="/delImpRecord")
    @ResponseBody
    public String delImpRecord(String impFlow){
        int result = importRecordBiz.delImpRecord(impFlow);
        if(GlobalConstant.ZERO_LINE != result){
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }
    @RequestMapping(value="/printYuLian")
    public String printYuLian(Model model, String userFlow,String printFlag,String isStmp) throws Exception {
        //为N时是中文打印  并且都是打印有成绩的课程
//        String url=this.studentCourseBiz.decodeQrCodeForGrade(userFlow);//较上版本逻辑，(每次创建新二维码)永远都成立，故无需解析（zxing解析二维码能力有缺陷）
//        String content = InitConfig.getSysCfg("qr_grade_search_url")+"/xjgl/student/resultSun?userFlow="+userFlow;
//        if(StringUtil.isBlank(url) || !url.equals(content)||true){
        studentCourseBiz.createQrCodeForGrade(userFlow);
//        }
        EduUser eduuser = eduUserBiz.readEduUser(userFlow);
        SysUser sysuser = userBiz.readSysUser(userFlow);
        ResDoctor doctor = eduUserBiz.findDoctorByFlow(userFlow);
        List<String> userFlowList = new ArrayList<String>();
        userFlowList.add(userFlow);
        List<XjEduUserExt> eduUserExts = eduUserBiz.searchEduUserCourseExtMajorList(userFlowList, null);
        XjEduUserExt eduUserExt = null;
        if (eduUserExts != null && !eduUserExts.isEmpty()) {
            eduUserExt = eduUserExts.get(0);
            List<XjEduStudentCourseExt> list = eduUserExt.getCourseExtsList();
            List<XjEduStudentCourseExt> list1 = new ArrayList<>();
            Map<String,XjEduStudentCourseExt> map=new HashMap<>();
            //打印 有成绩且 成绩合格或者成绩大于等于课程有效成绩的记录
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    XjEduStudentCourseExt ext = list.get(i);
                    if (StringUtil.isNotBlank(ext.getCourseGrade())) {
                        if ("Y".equals(ext.getCourseGrade())) {//通过，合格
                            list1.add(ext);
                        }else if(isNumeric(ext.getCourseGrade()) && Double.valueOf(ext.getCourseGrade()) >= Double.valueOf(ext.getCourse().getEffectiveGrade())){//具体成绩 且大于等于课程有效成绩
                            list1.add(ext);
                        }
                    }
                }
                /**南医大成绩打印去掉相同课程 保留最好成绩**/
                if(list1.size() > 0){
                    List<XjEduStudentCourseExt> list2 = new ArrayList<>();
                    for (int i = 0; i < list1.size(); i++) {
                        XjEduStudentCourseExt ext = list1.get(i);
                        if(map.containsKey(ext.getCourseCode())){
                            XjEduStudentCourseExt tempExt=map.get(ext.getCourseCode());
                            if ("Y".equals(ext.getCourseGrade())) {//通过，合格
                                map.put(ext.getCourseCode(),ext);
                            }else if(isNumeric(ext.getCourseGrade()) && isNumeric(tempExt.getCourseGrade())){
                                if(Double.valueOf(ext.getCourseGrade()) >= Double.valueOf(tempExt.getCourseGrade())){
                                    map.put(ext.getCourseCode(),ext);
                                }
                            }
                        }else{
                            map.put(ext.getCourseCode(),ext);
                        }
                    }
                    Iterator<Map.Entry<String,XjEduStudentCourseExt>> entries = map.entrySet().iterator();
                    while (entries.hasNext()) {
                        Map.Entry<String,XjEduStudentCourseExt> entry = entries.next();
                        if(entry.getValue()!=null){
                            list2.add(entry.getValue());
                        }
                    }
                    list1=list2;
                }
                /**南医大成绩打印去掉相同课程 保留最好成绩**/
            }
            eduUserExt.setCourseExtsList(list1);
        }
        model.addAttribute("eduUserExt", eduUserExt);
        model.addAttribute("eduuser", eduuser);
        model.addAttribute("doctor", doctor);
        model.addAttribute("sysuser", sysuser);
        String filePath = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_url")) + "/grade/";
        String fileName = userFlow + ".png";
        filePath += fileName;
        String filePath2 = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + "/grade/";
        filePath2 += fileName;
        File file = new File(filePath2);
        if (file.exists()) {
            model.addAttribute("qrcode", filePath);
            String time = DateUtil.getCurrDateTime("yyyy") + "年" + DateUtil.getCurrDateTime("MM") + "月" + DateUtil.getCurrDateTime("dd") + "日";
            model.addAttribute("time", time);
            model.addAttribute("printStmp", isStmp);
            if (printFlag.equals(GlobalConstant.FLAG_N)) {
                return "xjgl/plat/chineseScore";
            } else {
                if(StringUtil.isNotBlank(sysuser.getUserName())){
                    String eName = PyUtil.getUpEname(sysuser.getUserName());
                    model.addAttribute("eName", eName);
                }
                String majorId = eduuser.getMajorId();
                String eMajorName = dictBiz.readDict(DictTypeEnum.Major.getId(), majorId).getDictDesc();
                model.addAttribute("eMajorName", eMajorName);
                String currDate = DateUtil.getCurrDate();
                currDate = currDate.split("-")[2]+"/"+currDate.split("-")[1]+"/"+currDate.split("-")[0];
                model.addAttribute("currDate", currDate);
                return "xjgl/plat/englishScore";
            }
        }
        return "xjgl/plat/chineseScore";
    }
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]+(.?[0-9]+)?");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    @RequestMapping(value="/resultSun")
    public String resultSun(Model model, String userFlow,String period,String courseFlow) throws Exception{
//        String url=this.studentCourseBiz.decodeQrCodeForGrade(userFlow);//较上版本逻辑，(每次创建新二维码)永远都成立，故无需解析（zxing解析二维码能力有缺陷）
//        String content = InitConfig.getSysCfg("qr_grade_search_url")+"/xjgl/student/resultSun?userFlow="+userFlow;
//        if(StringUtil.isBlank(url) || !url.equals(content)||true){
            studentCourseBiz.createQrCodeForGrade(userFlow);
//        }
        EduUser eduuser=eduUserBiz.readEduUser(userFlow);
        SysUser sysuser=userBiz.readSysUser(userFlow);
        ResDoctor doctor = eduUserBiz.findDoctorByFlow(userFlow);
        List<String> userFlowList = new ArrayList<String>();
        userFlowList.add(userFlow);
        List<XjEduUserExt> eduUserExts=eduUserBiz.searchEduUserCourseExtMajorList(userFlowList,null);
        XjEduUserExt eduUserExt=null;
        if(eduUserExts!=null&&!eduUserExts.isEmpty()){
            eduUserExt=eduUserExts.get(0);
        }
        model.addAttribute("eduUserExt",eduUserExt);
//		List<EduCourseMajorExt> currCourseMajorlist=eduUserBiz.searchEduUserCourseSunList(majorId,period);
//      List<XjEduCourseMajorExt> currCourseMajorlist=eduUserBiz.searchEduUserCourseList(eduuser.getMajorId(), period);
//		model.addAttribute("currCourseMajorlist",currCourseMajorlist);
        model.addAttribute("eduuser",eduuser);
        model.addAttribute("doctor",doctor);
        model.addAttribute("sysuser",sysuser);
        String filePath = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_url"))+"/grade/";
        String fileName = userFlow+".png";
        filePath += fileName;
        model.addAttribute("qrcode",filePath);
        String time=DateUtil.getCurrDateTime("yyyy")+"年"+DateUtil.getCurrDateTime("MM")+"月"+DateUtil.getCurrDateTime("dd")+"日";
        model.addAttribute("time", time);
        return "xjgl/plat/result";

    }

    @RequestMapping(value="/delBatchGrade")
    @ResponseBody
    public String delBatchGrade(String [] recordLst){
        EduLog eduLog = new EduLog();//修改日志对象
        SysUser user=new SysUser();
        EduUser eduuser=new EduUser();
        EduStudentCourse oldEduStudentCourse=new EduStudentCourse();
        int count = 0;
        List<String> records = Arrays.asList(recordLst);
        EduStudentCourse stuCourse = new EduStudentCourse();
        stuCourse.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        for(int i=0;i<records.size();i++){
            stuCourse.setRecordFlow(records.get(i));
            count += studentCourseBiz.save(stuCourse,"GradeEdit");
            //记录日志
            oldEduStudentCourse=studentCourseBiz.searchStudentCourse(records.get(i));
            user=userBiz.readSysUser(oldEduStudentCourse.getUserFlow());
            eduuser=eduUserBiz.readEduUser(oldEduStudentCourse.getUserFlow());
            eduLog.setUserFlow(user.getUserFlow());//用户流水号
            eduLog.setChangeTime(DateUtil.getCurrentTime());//修改时间
            eduLog.setChangeUserFlow(GlobalContext.getCurrentUser().getUserFlow());//修改账户流水号
            eduLog.setChangeUserCode(GlobalContext.getCurrentUser().getUserCode());//修改账户登录名
            eduLog.setWsId(GlobalContext.getCurrentWsId());//当前工作站
            eduLog.setWsName(InitConfig.getWorkStationName(GlobalContext.getCurrentWsId()));
            eduLog.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            eduLog.setCreateTime(DateUtil.getCurrentTime());
            eduLog.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            eduLog.setChangeItem("成绩管理");
            eduLog.setLogDesc(oldEduStudentCourse.getCourseName());
            eduLog.setUserName(user.getUserName());
            eduLog.setStudyId(eduuser.getSid());
            eduLog.setGradeNumber(eduuser.getPeriod());
            eduLog.setLogFlow(PkUtil.getUUID());
            eduLog.setOldData(oldEduStudentCourse.getCourseGrade());
            eduLog.setNewData("数据删除");
            logMapper.insertSelective(eduLog);
        }
        if(count > 0){
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return  GlobalConstant.OPERATE_FAIL;
    }

    /**
     * 学籍注册时间
     * @param userFlow
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/editRegisterDate")
    public String editRegisterDate(String userFlow, Model model) throws Exception{
        if(StringUtil.isNotBlank(userFlow)){
            List<XjRegisterDateForm> registerDateList = eduUserBiz.searchRegisterDateList(userFlow);
            model.addAttribute("registerDateList", registerDateList);
        }
        return "xjgl/plat/editRegisterDate";
    }



    /**
     * 导入记录
     * @param request
     * @param currentPage2
     * @param model
     * @return
     */
    @RequestMapping(value="/impRecordList")
    public String impRecordList(HttpServletRequest request, Integer currentPage2, Model model){
        PageHelper.startPage(currentPage2, getPageSize(request));
        PubImportRecord importRecord = new PubImportRecord();
        importRecord.setImpTypeId(XjImpTypeEnum.EduStudentCourse.getId());
        importRecord.setRoleFlag("admin");
        List<PubImportRecord> importRecordList = importRecordBiz.searchImportRecordList(importRecord);
        model.addAttribute("importRecordList", importRecordList);

        return "xjgl/plat/impRecordList";
    }
    /**
     * 学籍信息导入
     */
    @RequestMapping(value="/importSchoolRoll")
    public String importSchoolRoll(){

        return "/xjgl/plat/importSchoolRoll";
    }
    /**
     * 保存eduUserForm
     * @param eduUserForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/saveEduUser")
    @ResponseBody
    public String saveUserSchool(XjEduUserForm eduUserForm,String roleFlag) throws Exception {

        SysUser user=eduUserForm.getSysUser();
        EduUser eduUser=eduUserForm.getEduUser();
        ResDoctor resDoctor=eduUserForm.getResDoctor();
        XjEduUserExtInfoForm extInfoForm=eduUserForm.getEduUserExtInfoForm();

        if(StringUtil.isNotBlank(eduUser.getStudentSourceId())){
            eduUser.setStudentSourceName(DictTypeEnum.StudentSource.getDictNameById(eduUser.getStudentSourceId()));
        }

        if(StringUtil.isNotBlank(eduUser.getAdmitTypeId())){
            eduUser.setAdmitTypeName(DictTypeEnum.AdmitType.getDictNameById(eduUser.getAdmitTypeId()));
        }
        if(StringUtil.isNotBlank(eduUser.getTrainTypeId())){
            eduUser.setTrainTypeName(DictTypeEnum.TrainType.getDictNameById(eduUser.getTrainTypeId()));
        }
        if(StringUtil.isNotBlank(eduUser.getMajorId())){
            eduUser.setMajorName(DictTypeEnum.Major.getDictNameById(eduUser.getMajorId()));
        }
        if(StringUtil.isNotBlank(eduUser.getStudyFormId())){
            eduUser.setStudyFormName(DictTypeEnum.StudyForm.getDictNameById(eduUser.getStudyFormId()));
        }
        if(StringUtil.isNotBlank(eduUser.getAtSchoolStatusId())){
            eduUser.setAtSchoolStatusName(DictTypeEnum.AtSchoolStatus.getDictNameById(eduUser.getAtSchoolStatusId()));
        }
        if(StringUtil.isNotBlank(eduUser.getSchoolRollStatusId())){
            eduUser.setSchoolRollStatusName(DictTypeEnum.SchoolRollStatus.getDictNameById(eduUser.getSchoolRollStatusId()));
        }
        if(StringUtil.isNotBlank(eduUser.getRecruitSeasonId())){
            eduUser.setRecruitSeasonName(DictTypeEnum.RecruitSeason.getDictNameById(eduUser.getRecruitSeasonId()));
        }
        if(StringUtil.isNotBlank(eduUser.getTrainCategoryId())){
            eduUser.setTrainCategoryName(DictTypeEnum.TrainCategory.getDictNameById(eduUser.getTrainCategoryId()));
        }
        if(StringUtil.isNotBlank(eduUser.getIsExceptionalId())){
            eduUser.setIsExceptionalName(DictTypeEnum.IsExceptional.getDictNameById(eduUser.getIsExceptionalId()));
        }
        if(StringUtil.isNotBlank(extInfoForm.getFirstEducationContentId())){
            extInfoForm.setFirstEducationContentName(DictTypeEnum.FirstEducation.getDictNameById(extInfoForm.getFirstEducationContentId()));
        }
        if(StringUtil.isNotBlank(eduUser.getClassId())){
            eduUser.setClassName(DictTypeEnum.XjClass.getDictNameById(eduUser.getClassId()));
        }
        if(StringUtil.isNotBlank(user.getSexId())){
            user.setSexName(UserSexEnum.getNameById(user.getSexId()));
        }
        if(StringUtil.isNotBlank(user.getCretTypeId())){
            user.setCretTypeName(CertificateTypeEnum.getNameById(user.getCretTypeId()));
        }
        if(StringUtil.isNotBlank(user.getNationId())){
            user.setNationName(UserNationEnum.getNameById(user.getNationId()));
        }
        if(StringUtil.isNotBlank(user.getPoliticsStatusId())){
            user.setPoliticsStatusName(PoliticsAppearanceEnum.getNameById(user.getPoliticsStatusId()));
        }
        if(StringUtil.isNotBlank(user.getMaritalId())){
            user.setMaritalName(MarriageTypeEnum.getNameById(user.getMaritalId()));
        }
        if(StringUtil.isNotBlank(eduUser.getAwardDegreeCategoryId())){
            eduUser.setAwardDegreeCategoryName(DictTypeEnum.DegreeCategory.getDictNameById(eduUser.getAwardDegreeCategoryId()));
        }

        if(StringUtil.isBlank(eduUser.getUserFlow())){
            EduUser edu=null;
            if(StringUtil.isNotBlank(eduUser.getSid())){
                edu = eduUserBiz.findBySid(eduUser.getSid());
                if(edu!=null){
                    return GlobalConstant.USER_SID_REPETE;
                }
            }
        }else{
            EduUser edu=null;
            String userFlow = eduUser.getUserFlow();
            if(StringUtil.isNotBlank(eduUser.getSid())){
                edu = eduUserBiz.findBySidNotSelf(userFlow, eduUser.getSid());
                if(edu!=null){
                    return GlobalConstant.USER_SID_REPETE;
                }
            }
        }
        user.setUserCode(eduUser.getSid());
        user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        user.setOrgName(GlobalContext.getCurrentUser().getOrgName());
        eduUser.setTrainOrgId(user.getDeptFlow());
        if(StringUtil.isNotBlank(user.getDeptFlow())){
            SysDept dept=this.deptBiz.readSysDept(user.getDeptFlow());
            if(dept!=null){
                user.setDeptName(dept.getDeptName());
                eduUser.setTrainOrgName(dept.getDeptName());
            }
        }
        resDoctor.setSessionNumber(eduUser.getPeriod());
        resDoctor.setDoctorCode(eduUser.getSid());
        resDoctor.setTutorName(eduUser.getFirstTeacher());
        eduUser.setTrainOrgId(user.getDeptFlow());
        eduUser.setTrainOrgName(user.getDeptName());
        resDoctor.setDoctorCategoryId(XjDocCategoryEnum.Graduate.getId());
        resDoctor.setDoctorCategoryName(XjDocCategoryEnum.Graduate.getName());
        resDoctor.setDoctorLicenseNo(extInfoForm.getCodeDoctorQuaCert());
        resDoctor.setQualifiedNo(extInfoForm.getCodeMedicalPractitioner());
        resDoctor.setQualifiedFile(extInfoForm.getIsDoctorQuaCert());
        resDoctor.setDoctorLicenseFlag(extInfoForm.getIsMedicalPractitioner());
        resDoctor.setForeignSkills(extInfoForm.getForeignLanguageName());
        resDoctor.setComputerSkills(extInfoForm.getComputerLevel());
        int result=eduUserBiz.save(eduUserForm,null);
        if(result!=GlobalConstant.ZERO_LINE){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    @RequestMapping(value="/userHeadImgUpload")
    @ResponseBody
    public String userHeadImgUpload(String userFlow,MultipartFile headImg){
        if(headImg!=null && headImg.getSize() > 0){
            return userBiz.uploadImg(userFlow,headImg);
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    @RequestMapping(value="/saveStudentCourse")
    public @ResponseBody String saveStudentCourse(@RequestBody List<EduStudentCourse> studentCourseList ){
        EduUser eduUser=new EduUser();
        EduCourse course=new EduCourse();
        SysUser user=new SysUser();
        EduCourseMajor eduCourseMajor=new EduCourseMajor();
        EduStudentCourse oldEduStudentCourse=new EduStudentCourse();
        EduLog eduLog = new EduLog();//修改日志对象
        for (EduStudentCourse eduStudentCourse : studentCourseList) {
            if (StringUtil.isNotBlank(eduStudentCourse.getCourseFlow())) {
                oldEduStudentCourse=studentCourseBiz.searchStudentCourse(eduStudentCourse.getRecordFlow());
                user=userBiz.readSysUser(eduStudentCourse.getUserFlow());
                eduUser=eduUserBiz.readEduUser(eduStudentCourse.getUserFlow());
                course=courseBiz.readCourse(eduStudentCourse.getCourseFlow());
                eduCourseMajor.setMajorId(eduUser.getMajorId());
                eduCourseMajor.setCourseFlow(eduStudentCourse.getCourseFlow());
                eduCourseMajor.setPlanYear(eduUser.getPeriod());
                List<EduCourseMajor> courseMajorList=courseMajorBiz.onlyCourseMajor(eduCourseMajor);
                String gradeName=DictTypeEnum.RecruitSeason.getDictNameById(eduStudentCourse.getGradeTermId());
                eduStudentCourse.setGradeTermName(gradeName);
                if(StringUtil.isBlank(eduStudentCourse.getGradeTermId())){
                    eduStudentCourse.setGradeTermId("");
                    eduStudentCourse.setGradeTermName("");
                }
                if(StringUtil.isNotBlank(eduStudentCourse.getStudyWayId())){
                    eduStudentCourse.setStudyWayName(DictTypeEnum.XjStudyWay.getDictNameById(eduStudentCourse.getStudyWayId()));
                }else{
                    eduStudentCourse.setStudyWayName("");
                }
                if(StringUtil.isNotBlank(eduStudentCourse.getAssessTypeId())){
                    eduStudentCourse.setAssessTypeName(DictTypeEnum.XjEvaluationMode.getDictNameById(eduStudentCourse.getAssessTypeId()));
                }else{
                    eduStudentCourse.setAssessTypeName("");
                }
                if(course!=null){
                    eduStudentCourse.setCourseCode(course.getCourseCode());
                    eduStudentCourse.setCourseName(course.getCourseName());
                    eduStudentCourse.setCourseNameEn(course.getCourseNameEn());
                    eduStudentCourse.setCourseCredit(course.getCourseCredit());
                    eduStudentCourse.setCoursePeriod(course.getCoursePeriod());
                }
                eduStudentCourse.setStudentPeriod(eduUser.getPeriod());
                if(courseMajorList!=null&&!courseMajorList.isEmpty()){
                    eduStudentCourse.setCourseTypeId(courseMajorList.get(0).getCourseTypeId());
                    eduStudentCourse.setCourseTypeName(courseMajorList.get(0).getCourseTypeName());
                }else if(StringUtil.isNotBlank(eduStudentCourse.getCourseTypeId())){
                    eduStudentCourse.setCourseTypeName(XjglCourseTypeEnum.getNameById(eduStudentCourse.getCourseTypeId()));
                }else{
                    eduStudentCourse.setCourseTypeName("");
                }
                eduLog.setUserFlow(user.getUserFlow());//用户流水号
                eduLog.setChangeTime(DateUtil.getCurrentTime());//修改时间
                eduLog.setChangeUserFlow(GlobalContext.getCurrentUser().getUserFlow());//修改账户流水号
                eduLog.setChangeUserCode(GlobalContext.getCurrentUser().getUserCode());//修改账户登录名
                eduLog.setWsId(GlobalContext.getCurrentWsId());//当前工作站
                eduLog.setWsName(InitConfig.getWorkStationName(GlobalContext.getCurrentWsId()));
                eduLog.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                eduLog.setCreateTime(DateUtil.getCurrentTime());
                eduLog.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
                eduLog.setChangeItem("成绩管理");
                eduLog.setLogDesc(eduStudentCourse.getCourseName());eduLog.setUserName(user.getUserName());
                eduLog.setStudyId(eduUser.getSid());
                eduLog.setGradeNumber(eduUser.getPeriod());
                //成绩是否修改
                if(StringUtil.isBlank(oldEduStudentCourse.getCourseGrade())&&StringUtil.isNotBlank(eduStudentCourse.getCourseGrade())){
                    eduLog.setLogFlow(PkUtil.getUUID());
                    eduLog.setNewData(eduStudentCourse.getCourseGrade());
                    logMapper.insertSelective(eduLog);
                }else if(StringUtil.isNotBlank(oldEduStudentCourse.getCourseGrade())&&StringUtil.isBlank(eduStudentCourse.getCourseGrade())){
                    eduLog.setLogFlow(PkUtil.getUUID());
                    eduLog.setOldData(oldEduStudentCourse.getCourseGrade());
                    logMapper.insertSelective(eduLog);
                }else if(StringUtil.isNotBlank(oldEduStudentCourse.getCourseGrade())&&StringUtil.isNotBlank(eduStudentCourse.getCourseGrade())&&!oldEduStudentCourse.getCourseGrade().equals(eduStudentCourse.getCourseGrade())){
                    eduLog.setLogFlow(PkUtil.getUUID());
                    eduLog.setOldData(oldEduStudentCourse.getCourseGrade());
                    eduLog.setNewData(eduStudentCourse.getCourseGrade());
                    logMapper.insertSelective(eduLog);
                }
                studentCourseBiz.save(eduStudentCourse,"GradeEdit");
            }
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }
    /**
     * 成绩打印
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/print")
    public void print(String userFlow,String majorId,String period,String printFlag, HttpServletRequest request, HttpServletResponse response) throws Exception{
        String totalCount="";
        float count=0;
        String code="";
        Map<String, Object> dataMap = new HashMap<String, Object>();
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        ServletContext context =  request.getServletContext();
        String watermark = GeneralMethod.getWatermark(null);
        EduUser eduUser=eduUserBiz.readEduUser(userFlow);
        SysUser sysUser=userBiz.readSysUser(userFlow);
//		ResDoctor doctor = resDoctorBiz.findByFlow(userFlow);
        dataMap.put("year", eduUser.getPeriod());
        dataMap.put("sid", eduUser.getSid());
        dataMap.put("userName",sysUser.getUserName());
        dataMap.put("sex", sysUser.getSexName());
        dataMap.put("birthday", sysUser.getUserBirthday());
        //dataMap.put("tacherName", doctor.getTutorName());
        dataMap.put("tacherName", StringUtil.defaultIfEmpty(eduUser.getFirstTeacher(),""));
        dataMap.put("majorName", eduUser.getMajorName());
        dataMap.put("trainTypeName", eduUser.getTrainCategoryName());
        dataMap.put("studentTypeName", eduUser.getTrainTypeName());
        List<String> userFlowList= new ArrayList<String>();
        userFlowList.add(userFlow);
        List<XjEduUserExt> eduUserExtList=eduUserBiz.searchEduUserCourseExtMajorList(userFlowList,null);
        if(eduUserExtList!=null&& !eduUserExtList.isEmpty()){
            XjEduUserExt userExt=eduUserExtList.get(0);
            List<XjEduStudentCourseExt> list = userExt.getCourseExtsList();
            List<XjEduStudentCourseExt> list1 = new ArrayList<>();
            Map<String,XjEduStudentCourseExt> map=new HashMap<>();
            //打印 有成绩且 成绩合格或者成绩大于等于课程有效成绩的记录
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    XjEduStudentCourseExt ext = list.get(i);
                    if (StringUtil.isNotBlank(ext.getCourseGrade())) {
                        if ("Y".equals(ext.getCourseGrade())) {//通过，合格
                            list1.add(ext);
                        }else if(isNumeric(ext.getCourseGrade()) && Double.valueOf(ext.getCourseGrade()) >= Double.valueOf(ext.getCourse().getEffectiveGrade())){//具体成绩 且大于等于课程有效成绩
                            list1.add(ext);
                        }
                    }
                }
                /**南医大成绩打印去掉相同课程 保留最好成绩**/
                if(list1.size() > 0){
                    List<XjEduStudentCourseExt> list2 = new ArrayList<>();
                    for (int i = 0; i < list1.size(); i++) {
                        XjEduStudentCourseExt ext = list1.get(i);
                        if(map.containsKey(ext.getCourseCode())){
                            XjEduStudentCourseExt tempExt=map.get(ext.getCourseCode());
                            if ("Y".equals(ext.getCourseGrade())) {//通过，合格
                                map.put(ext.getCourseCode(),ext);
                            }else if(isNumeric(ext.getCourseGrade()) && isNumeric(tempExt.getCourseGrade())){
                                if(Double.valueOf(ext.getCourseGrade()) >= Double.valueOf(tempExt.getCourseGrade())){
                                    map.put(ext.getCourseCode(),ext);
                                }
                            }
                        }else{
                            map.put(ext.getCourseCode(),ext);
                        }
                    }
                    Iterator<Map.Entry<String,XjEduStudentCourseExt>> entries = map.entrySet().iterator();
                    while (entries.hasNext()) {
                        Map.Entry<String,XjEduStudentCourseExt> entry = entries.next();
                        if(entry.getValue()!=null){
                            list2.add(entry.getValue());
                        }
                    }
                    list1=list2;
                }
                /**南医大成绩打印去掉相同课程 保留最好成绩**/
            }
            userExt.setCourseExtsList(list1);
            if (userExt.getCourseExtsList()!= null&&!userExt.getCourseExtsList().isEmpty()) {
                List<ItemGroupData> yearPlanTargetList = new ArrayList<ItemGroupData>();
                for(XjEduStudentCourseExt esc:userExt.getCourseExtsList()){
                    ItemGroupData  igd = new ItemGroupData();
                    Map<String , Object> objMap = new HashMap<String, Object>();
                    if(StringUtil.isNotBlank(esc.getCourseCode())){
                        code="["+esc.getCourseCode()+"]";
                    }
                    if(GlobalConstant.FLAG_Y.equals(printFlag)){
                        objMap.put("courseName","["+esc.getCourseCode()+"]"+esc.getCourseNameEn());
                    }
                    if(GlobalConstant.FLAG_N.equals(printFlag)){
                        objMap.put("courseName","["+esc.getCourseCode()+"]"+esc.getCourseName());
                    }
                    objMap.put("courseTypeName", esc.getCourse().getCourseTypeName());
                    objMap.put("coursePeriod", esc.getCoursePeriod());
                    objMap.put("courseCredit", esc.getCourseCredit());
                    List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("XjIsPassed");
                    int result=0;String grade="";
                    for(SysDict dict:dictList){
                        if(dict.getDictId().equals(esc.getCourseGrade())){
                            result=1;
                            grade=dict.getDictName();
                        }
                    }
                    DecimalFormat df = new DecimalFormat("0.0");
                    if(result==0){
                        objMap.put("courseGrade", df.format(Double.parseDouble(StringUtil.defaultIfEmpty(esc.getCourseGrade(),"0.0"))));
                    }else{
                        objMap.put("courseGrade", grade);
                    }
                    if(GlobalConstant.FLAG_Y.equals(printFlag)){
                        if(objMap.get("courseGrade")!=null) {
                            if (objMap.get("courseGrade").equals("合格") || objMap.get("courseGrade").equals("通过")) {
                                objMap.put("courseGrade", "PASS");
                            }
                            if (objMap.get("courseGrade").equals("不合格") || objMap.get("courseGrade").equals("不通过") || objMap.get("courseGrade").equals("缺考")) {
                                objMap.put("courseGrade", "");
                            }
                        }
                    }
                    igd.setObjMap(objMap);
                    yearPlanTargetList.add(igd);
                    if(StringUtil.isNotBlank(esc.getCourseCredit())){
                        count+=Float.parseFloat(esc.getCourseCredit());
                    }
                }
                dataMap.put("yearPlanTargetList", yearPlanTargetList);
                totalCount=String.valueOf(count);
                dataMap.put("totalCount", totalCount);
            }
        }
        String time=DateUtil.getCurrDateTime("yyyy")+"年"+DateUtil.getCurrDateTime("MM")+"月"+DateUtil.getCurrDateTime("dd")+"日";
        dataMap.put("time", time);
        String path = "";
        String name ="";
        if(GlobalConstant.FLAG_Y.equals(printFlag)){
            if(StringUtil.isNotBlank(sysUser.getUserName())){
                String eName = PyUtil.getUpEname(sysUser.getUserName());
                dataMap.put("eName", eName);
            }
            String id = eduUser.getMajorId();
            String eMajorName = dictBiz.readDict(DictTypeEnum.Major.getId(),id).getDictDesc();
            dataMap.put("eMajorName", eMajorName);
            dataMap.put("rollMentYear",eduUser.getPeriod());
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM.dd,yyyy", Locale.ENGLISH);
            String currDate =  dateFormat.format(date);
            if (currDate.charAt(4) == '0') {
                if (currDate.charAt(5) == '1') {
                    currDate = currDate.substring(0, 4) + currDate.substring(5, 6) +"st" + currDate.substring(6, 11);
                } else if (currDate.charAt(5) == '2') {
                    currDate = currDate.substring(0, 4) + currDate.substring(5, 6) +"nd" + currDate.substring(6, 11);
                } else if (currDate.charAt(5) == '3') {
                    currDate = currDate.substring(0, 4) + currDate.substring(5, 6) +"rd" + currDate.substring(6, 11);
                } else {
                    currDate = currDate.substring(0, 4) + currDate.substring(5, 6) +"th" + currDate.substring(6, 11);
                }
            }else{
                if (currDate.charAt(5) == '1') {
                    currDate = currDate.substring(0, 6)+"st" + currDate.substring(6, 11);
                } else if (currDate.charAt(5) == '2') {
                    currDate = currDate.substring(0, 6)+"nd" + currDate.substring(6, 11);
                } else if (currDate.charAt(5) == '3') {
                    currDate = currDate.substring(0, 6)+"rd" + currDate.substring(6, 11);
                } else {
                    currDate = currDate.substring(0, 6)+"th" + currDate.substring(6, 11);
                }
            }
            if(currDate.substring(0,3).equals("May")){
                currDate = "May "+currDate.substring(4,currDate.length());
            }
            dataMap.put("eTime",currDate);
            path="/jsp/xjgl/print/eGrade2.docx";//英文模板
            name= "school report.docx";
        }
        if(GlobalConstant.FLAG_N.equals(printFlag)){
            path="/jsp/xjgl/print/grade.docx";//中文模板
            name= "成绩单.docx";
        }
        //插入图片
        String value = "";
        //String image = "file:///"+System.getProperty("user.home")+File.separator+"grade"+File.separator+userFlow+".png";
        String image = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+ File.separator+"grade"+File.separator+userFlow+".png";
        if (StringUtil.isBlank(image)) {
            value = "";
        } else {
            value = "<img src='"+image+"' width='90' height='90'  alt='二维码'/>";
        }
        dataMap.put("headImg", value);
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
        if(temeplete!=null){
            response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
            response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = response.getOutputStream ();
            (new SaveToZipFile(temeplete)).save (out);
            out.flush ();
        }
    }


    @RequestMapping(value="/courseFlowCourse")
    @ResponseBody
    public List<XjEduCourseMajorExt> courseFlowCourse(String majorId,String period){
        List<XjEduCourseMajorExt> currCourseMajorlist=eduUserBiz.searchEduUserCourseList(majorId,period);
        return currCourseMajorlist;
    }

    @RequestMapping(value="/delCourse")
    @ResponseBody
    public String delCourse(String recordFlow){
        EduLog eduLog = new EduLog();//修改日志对象
        SysUser user=new SysUser();
        EduUser eduuser=new EduUser();
        EduStudentCourse oldEduStudentCourse=new EduStudentCourse();
        int result=0;
        if(StringUtil.isNotBlank(recordFlow)){
            result=studentCourseBiz.deleteCourse(recordFlow);
            //记录日志
            oldEduStudentCourse=studentCourseBiz.searchStudentCourse(recordFlow);
            user=userBiz.readSysUser(oldEduStudentCourse.getUserFlow());
            eduuser=eduUserBiz.readEduUser(oldEduStudentCourse.getUserFlow());
            eduLog.setUserFlow(user.getUserFlow());//用户流水号
            eduLog.setChangeTime(DateUtil.getCurrentTime());//修改时间
            eduLog.setChangeUserFlow(GlobalContext.getCurrentUser().getUserFlow());//修改账户流水号
            eduLog.setChangeUserCode(GlobalContext.getCurrentUser().getUserCode());//修改账户登录名
            eduLog.setWsId(GlobalContext.getCurrentWsId());//当前工作站
            eduLog.setWsName(InitConfig.getWorkStationName(GlobalContext.getCurrentWsId()));
            eduLog.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            eduLog.setCreateTime(DateUtil.getCurrentTime());
            eduLog.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            eduLog.setChangeItem("成绩管理");
            eduLog.setLogDesc(oldEduStudentCourse.getCourseName());
            eduLog.setUserName(user.getUserName());
            eduLog.setStudyId(eduuser.getSid());
            eduLog.setGradeNumber(eduuser.getPeriod());
            eduLog.setLogFlow(PkUtil.getUUID());
            eduLog.setOldData(oldEduStudentCourse.getCourseGrade());
            eduLog.setNewData("数据删除");
            logMapper.insertSelective(eduLog);
        }
        if(GlobalConstant.ZERO_LINE==result){
            return GlobalConstant.DELETE_FAIL;
        }else{
            return GlobalConstant.DELETE_SUCCESSED;
        }
    }
    @RequestMapping(value="/emptyCourse")
    @ResponseBody
    public String emptyCourse(String recordFlow){
        EduLog eduLog = new EduLog();//修改日志对象
        SysUser user=new SysUser();
        EduUser eduuser=new EduUser();
        EduStudentCourse oldEduStudentCourse=new EduStudentCourse();
        EduStudentCourse eduStudentCourse = studentCourseBiz.searchStudentCourse(recordFlow);
        if(eduStudentCourse!=null)
        {
//            //eduStudentCourse.setCoursePeriod("");//学时
//            //eduStudentCourse.setCourseCredit("");//学分
//            eduStudentCourse.setStudyWayId("");//修读方式id
//            eduStudentCourse.setStudyWayName("");//修读方式name
//            eduStudentCourse.setAssessTypeId("");//考核方式id
//            eduStudentCourse.setAssessTypeName("");//考核方式name
//            eduStudentCourse.setPacificGrade("");//平时成绩
//            eduStudentCourse.setTeamEndGrade("");//期末成绩
//            eduStudentCourse.setCourseGrade("");//总成绩
//            eduStudentCourse.setGradeTermId("");//获得学期
//            eduStudentCourse.setGradeTermName("");//获得学期
            eduStudentCourse.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            int result=0;
            result=studentCourseBiz.save(eduStudentCourse,"GradeEdit");
            //记录日志
            oldEduStudentCourse=studentCourseBiz.searchStudentCourse(recordFlow);
            user=userBiz.readSysUser(oldEduStudentCourse.getUserFlow());
            eduuser=eduUserBiz.readEduUser(oldEduStudentCourse.getUserFlow());
            eduLog.setUserFlow(user.getUserFlow());//用户流水号
            eduLog.setChangeTime(DateUtil.getCurrentTime());//修改时间
            eduLog.setChangeUserFlow(GlobalContext.getCurrentUser().getUserFlow());//修改账户流水号
            eduLog.setChangeUserCode(GlobalContext.getCurrentUser().getUserCode());//修改账户登录名
            eduLog.setWsId(GlobalContext.getCurrentWsId());//当前工作站
            eduLog.setWsName(InitConfig.getWorkStationName(GlobalContext.getCurrentWsId()));
            eduLog.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            eduLog.setCreateTime(DateUtil.getCurrentTime());
            eduLog.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            eduLog.setChangeItem("成绩管理");
            eduLog.setLogDesc(oldEduStudentCourse.getCourseName());
            eduLog.setUserName(user.getUserName());
            eduLog.setStudyId(eduuser.getSid());
            eduLog.setGradeNumber(eduuser.getPeriod());
            eduLog.setLogFlow(PkUtil.getUUID());
            eduLog.setOldData(oldEduStudentCourse.getCourseGrade());
            eduLog.setNewData("数据删除");
            logMapper.insertSelective(eduLog);
            if(GlobalConstant.ZERO_LINE==result){
                return GlobalConstant.FLAG_N;
            }else{
                return GlobalConstant.FLAG_Y;
            }
        }
        return  GlobalConstant.FLAG_N;
    }
    /**
     * 编辑eduUserForm
     * @param roleFlag
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/eduUserFormEdit/{roleFlag}")
    public String edit(@PathVariable String roleFlag,Model model,String userFlow,String qrztFlag,boolean isFeeMaster,boolean isSzkMaster) throws Exception{
        model.addAttribute("isFeeMaster",isFeeMaster);
        model.addAttribute("isSzkMaster",isSzkMaster);
        if(GlobalConstant.RES_ROLE_SCOPE_DOCTOR.equals(roleFlag)){
            userFlow=GlobalContext.getCurrentUser().getUserFlow();
        }
        if(StringUtil.isNotBlank(userFlow)){
            EduUser eduUser=eduUserBiz.findByFlow(userFlow);
            if(eduUser!=null){
                model.addAttribute("eduUser", eduUser);
                //对接收费系统中缴费信息
//                if(StringUtil.isNotBlank(eduUser.getSid())){
//                    Map<String,Object> stuAllFeeInfo=eduUserBiz.readStuAllFeeInfo(eduUser.getSid());
//                    model.addAttribute("stuAllFeeInfo", stuAllFeeInfo);
//                }
                String content = eduUser.getContent();
                XjEduUserExtInfoForm extInfoForm = eduUserBiz.parseExtInfoXml(content);
                model.addAttribute("extInfoForm", extInfoForm);
                //档案
                String recordLocationId=eduUser.getRecordLocationId();
                if(StringUtil.isNotBlank(recordLocationId)){
                    String[] recordId= recordLocationId.split(",");
                    model.addAttribute("recordId", recordId);
                }
                String recordLocationName=eduUser.getRecordLocationName();
                if(StringUtil.isNotBlank(recordLocationName)){
                    String[] recordName= recordLocationName.split(",");
                    model.addAttribute("recordName", recordName);
                }
            }
            SysUser sysUser=userBiz.findByFlow(userFlow);
            if(sysUser!=null){
                model.addAttribute("sysUser", sysUser);
                //户口
                String domicilePlaceId=sysUser.getDomicilePlaceId();
                if(StringUtil.isNotBlank(domicilePlaceId)){
                    String[] domicileId= domicilePlaceId.split(",");
                    model.addAttribute("domicileId", domicileId);
                }
                String domicilePlaceName=sysUser.getDomicilePlaceName();
                if(StringUtil.isNotBlank(domicilePlaceName)){
                    String[] domicileName= domicilePlaceName.split(",");
                    model.addAttribute("domicileName", domicileName);
                }
            }
            ResDoctor resDoctor=eduUserBiz.findDoctorByFlow(userFlow);
            model.addAttribute("resDoctor", resDoctor);
            //信息确认状态
            List<EduUserInfoStatus> statusLst = courseBiz.searchPartStatus(userFlow);
            Map<String,String> statusMap=new HashMap<>();
            if(null != statusLst && statusLst.size() > 0){
                for(EduUserInfoStatus infoStatus:statusLst){
                    statusMap.put(infoStatus.getPartId(),infoStatus.getPartStatus());
                }
            }
            model.addAttribute("statusMap", statusMap);
            //导师查询
            NydsOfficialDoctor officialDoctor = new NydsOfficialDoctor();
            officialDoctor.setXwkAuditStatusId("Passed");
            List<NydsOfficialDoctor> tutorList = tutorBiz.queryDoctorList(officialDoctor);
            model.addAttribute("tutorList",tutorList);
        }
        String orgFlow=GlobalContext.getCurrentUser().getOrgFlow();
        if(StringUtil.isNotBlank(orgFlow)){
            List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);
            model.addAttribute("deptList",deptList);
        }
        //就业信息 开放时间
        SysCfg openEmploymentStartDate=cfgBiz.read("open_employment_start_date");
        model.addAttribute("openEmploymentStartDate", openEmploymentStartDate);
        SysCfg closeEmploymentEndDate=cfgBiz.read("close_employment_end_date");
        model.addAttribute("closeEmploymentEndDate", closeEmploymentEndDate);
        //学位论文 开放时间
        SysCfg openPaperStartDate=cfgBiz.read("open_paper_start_date");
        model.addAttribute("openPaperStartDate", openPaperStartDate);
        SysCfg closePaperEndDate=cfgBiz.read("close_paper_end_date");
        model.addAttribute("closePaperEndDate", closePaperEndDate);
        //学业与学位授予信息 开放时间
        SysCfg openDegreeGrantStartDate=cfgBiz.read("open_degreeGrant_start_date");
        model.addAttribute("openDegreeGrantStartDate", openDegreeGrantStartDate);
        SysCfg closeDegreeGrantEndDate=cfgBiz.read("close_degreeGrant_end_date");
        model.addAttribute("closeDegreeGrantEndDate", closeDegreeGrantEndDate);
        //必填信息 开放时间
        SysCfg openRequiredStartDate=cfgBiz.read("open_required_start_date");
        model.addAttribute("openRequiredStartDate", openRequiredStartDate);
        SysCfg closeRequiredEndDate=cfgBiz.read("close_required_end_date");
        model.addAttribute("closeRequiredEndDate", closeRequiredEndDate);
        //选填信息 开放时间
        SysCfg openOptionalStartDate=cfgBiz.read("open_optional_start_date");
        model.addAttribute("openOptionalStartDate", openOptionalStartDate);
        SysCfg closeOptionalEndDate=cfgBiz.read("close_optional_end_date");
        model.addAttribute("closeOptionalEndDate", closeOptionalEndDate);
        List<SysOrg> orgList = orgBiz.searchTrainOrgList();
        model.addAttribute("orgList", orgList);
        //开放学籍修改权限(所有Y/特定学生N)
        SysCfg permissionCfg = cfgBiz.read("xjgl_modify_permission");
        model.addAttribute("permissionCfg", permissionCfg);
        model.addAttribute("roleFlag",roleFlag);
        return "xjgl/plat/interMaintainAdmin";//南方医科大学学籍信息
    }

    /**
     * 学位证明-查看学籍信息
     */
    @RequestMapping("/userCertificateInfo")
    public String userCertificateInfo(Model model,String userFlow,String isSzkMaster) throws Exception{
        model.addAttribute("isSzkMaster",isSzkMaster);
        if(StringUtil.isNotBlank(userFlow)){
            EduUser eduUser=eduUserBiz.findByFlow(userFlow);
            if(eduUser!=null){
                model.addAttribute("eduUser", eduUser);
                String content = eduUser.getContent();
                XjEduUserExtInfoForm extInfoForm = eduUserBiz.parseExtInfoXml(content);
                model.addAttribute("extInfoForm", extInfoForm);
                //档案
                String recordLocationName=eduUser.getRecordLocationName();
                if(StringUtil.isNotBlank(recordLocationName)){
                    String[] recordName= recordLocationName.split(",");
                    model.addAttribute("recordName", recordName);
                }
            }
            SysUser sysUser=userBiz.findByFlow(userFlow);
            if(sysUser!=null){
                model.addAttribute("sysUser", sysUser);
                //户口
                String domicilePlaceName=sysUser.getDomicilePlaceName();
                if(StringUtil.isNotBlank(domicilePlaceName)){
                    String[] domicileName= domicilePlaceName.split(",");
                    model.addAttribute("domicileName", domicileName);
                }
            }
            ResDoctor resDoctor=eduUserBiz.findDoctorByFlow(userFlow);
            model.addAttribute("resDoctor", resDoctor);
        }
        String orgFlow=GlobalContext.getCurrentUser().getOrgFlow();
        if(StringUtil.isNotBlank(orgFlow)){
            List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);
            model.addAttribute("deptList",deptList);
        }
        return "xjgl/plat/maintenanceInfo";//南方医科大学学籍信息
    }

    /**
     * 学位证明查询(学生)
     */
    @RequestMapping("/userBaseInfo")
    public String userBaseInfo(Model model){
        String userFlow = GlobalContext.getCurrentUser().getUserFlow();
        model.addAttribute("userFlow",userFlow);
        EduUser eduUser=eduUserBiz.findByFlow(userFlow);
        if(eduUser!=null){
            model.addAttribute("eduUser", eduUser);
        }
        SysUser sysUser=userBiz.findByFlow(userFlow);
        if(sysUser!=null){
            model.addAttribute("sysUser", sysUser);
        }
        return "xjgl/student/userBaseInfo";
    }

    //查看证明
    @RequestMapping("/certificateDetail")
    public String certificateDetail(Model model,String certificateType,String userFlow,String roleFlag) throws Exception {
        model.addAttribute("certificateType",certificateType);
        model.addAttribute("userFlow",userFlow);
        model.addAttribute("roleFlag",roleFlag);
        if(StringUtil.isNotBlank(userFlow)){
            PubFile pf = fileMapper.selectByPrimaryKey(userFlow);
            model.addAttribute("pubFile",pf);
            List<Map<String,Object>> cerInfoLst = eduUserBiz.searchCerInfoLstByUserFlow(userFlow);
            if(null != cerInfoLst && cerInfoLst.size() > 0){
                Map<String,Object> certInfo = cerInfoLst.get(0);
                String content = (String)certInfo.get("content");
                XjEduUserExtInfoForm extInfoForm = eduUserBiz.parseExtInfoXml(content);
                certInfo.put("medicine",extInfoForm.getAwardSubjectCategory());
                String userName = (String)certInfo.get("userName");
                if(StringUtil.isNotBlank(userName)){
                    userName = PyUtil.getUpEname(userName);//处理中文名(1-4)到拼写英文名转变
                    certInfo.put("nameSpell",userName);
                }
                model.addAttribute("certInfo",certInfo);
                String birthday = (String)certInfo.get("userBirthday");
                String graduateYear = (String)certInfo.get("graduateTime");
                if(StringUtil.isNotBlank(birthday) && birthday.replace("-","").length() > 7){
                    model.addAttribute("year",birthday.replace("-","").substring(0,4));
                    model.addAttribute("month",Integer.valueOf(birthday.replace("-","").substring(4,6)));
                    model.addAttribute("day",Integer.valueOf(birthday.replace("-","").substring(6,8)));
                }
                if(StringUtil.isNotBlank(graduateYear) && graduateYear.length() >= 4){
                    model.addAttribute("graduateYear",graduateYear.substring(0,4));
                }
                if(StringUtil.isNotBlank((String)certInfo.get("majorId"))){
                    SysDict sd=dictBiz.readDict(DictTypeEnum.Major.getId(),(String)certInfo.get("majorId"));
                    if(sd!=null){
                        model.addAttribute("dictNameEn",sd.getDictDesc());
                    }
                }
            }
            String currDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
            model.addAttribute("currYear",currDate.substring(0,4));
            model.addAttribute("currMonth",Integer.valueOf(currDate.substring(4,6)));
            model.addAttribute("currDay",Integer.valueOf(currDate.substring(6,8)));
            model.addAttribute("lastNumOfcurrDay",currDate.substring(7,8));
        }
        return "xjgl/plat/certificateDetail";
    }

    //导出证明
    @RequestMapping("/exportWord")
    public void exportWord(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map dataMap = getParameterMap(request);
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        String name = "学位证明.docx";
        String path = "/jsp/xjgl/print/degreeCertTemeplete.docx";//学位模板
        if (dataMap.get("certificateType").equals("2")) {
            name = "毕业证明.docx";
            path = "/jsp/xjgl/print/graduateCertTemeplete.docx";//毕业模板
        }
        String value = "";//插入证书照片对象
        String certImgPath = InitConfig.getSysCfg("upload_base_url");
        PubFile pf = fileMapper.selectByPrimaryKey((String)dataMap.get("userFlow"));
        if(null != pf){
            if("1".equals(dataMap.get("certificateType")) && StringUtil.isNotBlank(pf.getFileName())){
                certImgPath+=pf.getFileRemark();
                value = "<img src='"+certImgPath+"' width='440' height='315'  alt='学位证照'/>";
            }
            if("2".equals(dataMap.get("certificateType")) && StringUtil.isNotBlank(pf.getFilePath())){
                certImgPath+=pf.getFilePath();
                value = "<img src='"+certImgPath+"' width='440' height='315'  alt='毕业证照'/>";
            }
        }
        dataMap.put("certImg", value);
        ServletContext context = request.getServletContext();
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, null, true);
        if (temeplete != null) {
            response.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = response.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
            out.flush();
        }
    }
    @SuppressWarnings("unchecked")
    public static Map getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map map = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = map.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    //上传证书照
    @RequestMapping(value="/certImgUpload")
    @ResponseBody
    public String certImgUpload(String userFlow,String certType,MultipartFile certImg){
        if(null != certImg && certImg.getSize() > 0){
            return eduUserBiz.uploadImg(userFlow,certType,certImg);
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    /**
     * 操作日志
     * @param aim 拆分操作日志 标识
     * @return
     */
    @RequestMapping(value="/usersOperLog")
    public String userOperLog(String gradeNumber,String startStudyId,String endStudyId,String userName,String changeItem,String aim,
                              String startChangeTime,String endChangeTime,Model model,Integer currentPage,HttpServletRequest request){
        model.addAttribute("aim", aim);
        if("cj".equals(aim)){
            changeItem = "成绩管理";
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<EduLog> logs = eduUserBiz.searchUserOperLog(gradeNumber,startStudyId,endStudyId,userName,changeItem,
                startChangeTime,endChangeTime);
        if(logs!=null){
            model.addAttribute("logs",logs);
        }
        return "xjgl/plat/userOperLog";
    }

    /**
     * 学籍版块状态查询弹框
     */
    @RequestMapping("/searchPartStatus")
    public String searchPartStatus(String userFlow,Model model){
        List<EduUserInfoStatus> statusLst = courseBiz.searchPartStatus(userFlow);
        if(null != statusLst && statusLst.size() > 0){
            for(int i = 0; i < statusLst.size(); i++){
                if(XjPartStatusEnum.BaseInfo.getId().equals(statusLst.get(i).getPartId())){
                    model.addAttribute("BaseInfo",statusLst.get(i).getPartStatus());
                }
                if(XjPartStatusEnum.RecruitInfo.getId().equals(statusLst.get(i).getPartId())){
                    model.addAttribute("RecruitInfo",statusLst.get(i).getPartStatus());
                }
                if(XjPartStatusEnum.NeedInfo.getId().equals(statusLst.get(i).getPartId())){
                    model.addAttribute("NeedInfo",statusLst.get(i).getPartStatus());
                }
                if(XjPartStatusEnum.SelectInfo.getId().equals(statusLst.get(i).getPartId())){
                    model.addAttribute("SelectInfo",statusLst.get(i).getPartStatus());
                }
                if(XjPartStatusEnum.FeeInfo.getId().equals(statusLst.get(i).getPartId())){
                    model.addAttribute("FeeInfo",statusLst.get(i).getPartStatus());
                }
                if(XjPartStatusEnum.GotCertInfo.getId().equals(statusLst.get(i).getPartId())){
                    model.addAttribute("GotCertInfo",statusLst.get(i).getPartStatus());
                }
                if(XjPartStatusEnum.CertReqInfo.getId().equals(statusLst.get(i).getPartId())){
                    model.addAttribute("CertReqInfo",statusLst.get(i).getPartStatus());
                }
                if(XjPartStatusEnum.PaperInfo.getId().equals(statusLst.get(i).getPartId())){
                    model.addAttribute("PaperInfo",statusLst.get(i).getPartStatus());
                }
                if(XjPartStatusEnum.WorkInfo.getId().equals(statusLst.get(i).getPartId())){
                    model.addAttribute("WorkInfo",statusLst.get(i).getPartStatus());
                }
            }
        }
        return "xjgl/plat/partStatusInfo";
    }
    @RequestMapping("/expExcel")
    public void expExcel(EduUser eduUser,SysUser sysUser,ResDoctor doctor,EduStudentCourse studentCourse,String from,String scoreSum,String degreeGrade,String courseTypeScore,HttpServletResponse response)throws Exception{
        SysUser user = GlobalContext.getCurrentUser();
        if (GlobalConstant.USER_LIST_LOCAL.equals(from)) {
            //培养单位
            doctor.setOrgFlow(user.getOrgFlow());
        } else if (GlobalConstant.USER_LIST_CHARGE.equals(from)) {
            //分委会
            eduUser.setTrainOrgId(user.getDeptFlow());
        }
        System.out.println("==============="+from);
        List<XjEduStudentCourseExt> recordList = studentCourseBiz.searchStudentCourse(sysUser,eduUser,doctor,studentCourse,scoreSum,degreeGrade,courseTypeScore);
        String fileName = "学员成绩单.xls";
        createExcle(response, fileName, recordList);
    }
    private void createExcle(HttpServletResponse response,String fileName,List<XjEduStudentCourseExt> recordList) throws Exception {
        List<ExportEduStudentCourseInfo> dataList = new ArrayList<ExportEduStudentCourseInfo>();
        ExportEduStudentCourseInfo esc = null;
        for(XjEduStudentCourseExt ext:recordList){
            esc = new ExportEduStudentCourseInfo();
            esc.setGainYear(ext.getStudentPeriod());
            esc.setGainTerm(ext.getGradeTermName());
            esc.setSid(ext.getEduUser().getSid());
            esc.setUserName(ext.getSysUser().getUserName());
            esc.setCourseCode(ext.getCourseCode());
            esc.setCourseName(ext.getCourseName());
            esc.setCourseType(ext.getCourseTypeName());
            esc.setCoursePeriod(ext.getCoursePeriod());
            esc.setCourseCredit(ext.getCourseCredit());
            esc.setStyUnit(ext.getStudyWayName());
            esc.setTestType(ext.getAssessTypeName());
            esc.setNormalGrade(ext.getPacificGrade());
            esc.setTermGrade(ext.getTeamEndGrade());
            esc.setCourseGrade(ext.getCourseGrade());
            dataList.add(esc);
        }
        String[] titles = new String[]{
                "gainYear:获得学年",
                "gainTerm:获得学期",
                "sid:学号",
                "userName:姓名",
                "courseCode:课程代码",
                "courseName:课程名称",
                "courseType:课程类型",
                "coursePeriod:学时",
                "courseCredit:学分",
                "styUnit:修读性质",
                "testType:考核方式",
                "normalGrade:考勤成绩",
                "termGrade:考核成绩",
                "courseGrade:成绩"
        };
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("Application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcle(titles, dataList, ExportEduStudentCourseInfo.class, response.getOutputStream());
    }
    private void createExcleOfGz(HttpServletResponse response,String fileName,List<XjEduStudentCourseExt> recordList) throws Exception {
        List<Map<String,String>> dataList = new ArrayList<>();
        Map<String,String> esc = new HashMap<>();
        for(XjEduStudentCourseExt ext:recordList){
            esc = new HashMap<>();
            esc.put("gainYear",ext.getStudentPeriod());
            esc.put("gainTerm",ext.getGradeTermName());
            esc.put("sid",ext.getEduUser().getSid());
            esc.put("userName",ext.getSysUser().getUserName());
            esc.put("courseCode",ext.getCourseCode());
            esc.put("courseName",ext.getCourseName());
            esc.put("courseType",ext.getCourseTypeName());
            if("专业必选课".equals(ext.getCourseTypeName())){
                esc.put("courseType","必选课");
            }
            if("公共选修课".equals(ext.getCourseTypeName())){
                esc.put("courseType","选修课");
            }
            esc.put("coursePeriod",ext.getCoursePeriod());
            esc.put("courseCredit",ext.getCourseCredit());
            esc.put("styUnit",ext.getStudyWayName());
            esc.put("testType",ext.getAssessTypeName());
            esc.put("normalGrade",ext.getPacificGrade());
            esc.put("termGrade",ext.getTeamEndGrade());
            esc.put("courseGrade",ext.getCourseGrade());
            if(StringUtil.isNotBlank(ext.getCourseGrade())){
                if("Y".equals(ext.getCourseGrade())){
                    esc.put("courseGrade","通过");
                }
                if("N".equals(ext.getCourseGrade())){
                    esc.put("courseGrade","不通过");
                }
                if("T".equals(ext.getCourseGrade())){
                    esc.put("courseGrade","缺考");
                }
            }
            esc.put("categoryName",ext.getEduUser().getTrainCategoryName());

            if(GlobalConstant.FLAG_Y .equals(ext.getCourseGrade())){
                esc.put("courseCreditNew",ext.getCourseCredit());
            }else if(GlobalConstant.FLAG_N.equals(ext.getCourseGrade())){
                esc.put("courseCreditNew","0");
            }else if("F".equals(ext.getCourseGrade())){
                esc.put("courseCreditNew","0");
            }else if("T".equals(ext.getCourseGrade())){
                esc.put("courseCreditNew","0");
            }else{
                if(StringUtil.isNotBlank(ext.getCourseGrade())){
                    double v=Double.parseDouble(ext.getCourseGrade());
                    if(GlobalConstant.FLAG_Y.equals(ext.getDegreeCourseFlag())){
                        if(v>=75.0){
                            esc.put("courseCreditNew",ext.getCourseCredit());
                        }else{
                            esc.put("courseCreditNew","0");
                        }
                    }else{
                        if(v>=60.0){
                            esc.put("courseCreditNew",ext.getCourseCredit());
                        }else{
                            esc.put("courseCreditNew","0");
                        }
                    }
                }else{
                    esc.put("courseCreditNew","0");
                }
            }

            dataList.add(esc);
        }
        String[] titles = new String[]{
                "gainYear:获得学年",
                "gainTerm:获得学期",
                "sid:学号",
                "userName:姓名",
                "categoryName:培养类型",
                "courseCode:课程代码",
                "courseName:课程名称",
                "courseType:课程类型",
                "coursePeriod:学时",
                "courseCredit:应获学分",
                "courseCreditNew:实获学分",
                "styUnit:修读性质",
                "testType:考核方式",
                "normalGrade:考勤成绩",
                "termGrade:考核成绩",
                "courseGrade:成绩"
        };
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("Application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, dataList,response.getOutputStream(),new String[]{"2","5"});
    }
    //上传材料证明
    @RequestMapping(value="/uploadMaterialCert")
    @ResponseBody
    public String uploadMaterialCert(String userFlow, String columnName, MultipartFile file){
        if(null != file && file.getSize() > 0){
            return eduUserBiz.uploadMaterialCert(userFlow,columnName,file);
        }
        return GlobalConstant.UPLOAD_FAIL;
    }
    @RequestMapping("/ideologyManage")
    public String ideologyManage(Integer currentPage, Model model) {
        return "xjgl/plat/ideologyManage";
    }
    @RequestMapping("/studentManage")
    public String studentManage(String from, Integer currentPage, Model model) {
        return "xjgl/plat/studentManage";
    }
    @RequestMapping("/anonymousReview")
    public String anonymousReview(Integer currentPage, Model model) {
        return "xjgl/plat/anonymousReview";
    }
    @RequestMapping("/degreeManage")
    public String degreeManage(Integer currentPage, Model model) {
        return "xjgl/plat/degreeManage";
    }
    @RequestMapping("/clarkManage")
    public String clarkManage(Integer currentPage, Model model) {
        return "xjgl/plat/clarkManage";
    }
    @RequestMapping("/equivalentEducation")
    public String equivalentEducation(Integer currentPage, Model model) {
        return "xjgl/plat/equivalentEducation";
    }

    /**
     * 打印学籍信息
     */
    @RequestMapping("/printUserCertificateInfo")
    public String printUserCertificateInfo(Model model,String userFlow, EduUser edu) throws Exception{
        if(StringUtil.isNotBlank(userFlow)){
            EduUser eduUser=eduUserBiz.findByFlow(userFlow);
            if(eduUser!=null){
                model.addAttribute("eduUser", eduUser);
                String content = eduUser.getContent();
                XjEduUserExtInfoForm extInfoForm = eduUserBiz.parseExtInfoXml(content);
                model.addAttribute("extInfoForm", extInfoForm);
                //档案
                String recordLocationName=eduUser.getRecordLocationName();
                if(StringUtil.isNotBlank(recordLocationName)){
                    String[] recordName= recordLocationName.split(",");
                    model.addAttribute("recordName", recordName);
                }
            }
            SysUser sysUser=userBiz.findByFlow(userFlow);
            if(sysUser!=null){
                model.addAttribute("sysUser", sysUser);
                //户口
                String domicilePlaceName=sysUser.getDomicilePlaceName();
                if(StringUtil.isNotBlank(domicilePlaceName)){
                    String[] domicileName= domicilePlaceName.split(",");
                    model.addAttribute("domicileName", domicileName);
                }
            }
            ResDoctor resDoctor=eduUserBiz.findDoctorByFlow(userFlow);
            model.addAttribute("resDoctor", resDoctor);
        }
        String orgFlow=GlobalContext.getCurrentUser().getOrgFlow();
        if(StringUtil.isNotBlank(orgFlow)){
            List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);
            model.addAttribute("deptList",deptList);
        }
        return "xjgl/plat/printInterMaintainAdmin";//南方医科大学学籍信息
    }

    /**
     * 学生成绩查询
     * @param
     */
    @RequestMapping(value = "/eduUserStudentCourseList")
    public String eduUserStudentCourseList(Integer currentPage, ResDoctor doctor, EduStudentCourse studentCourse,String flag,
                                                Model model, HttpServletRequest request, String from,String scoreSum,String degreeGrade,String courseTypeScore) {
        SysUser user = GlobalContext.getCurrentUser();
        EduUser eduUser=eduUserBiz.readEduUser(user.getUserFlow());
        if(eduUser==null){
            eduUser=new EduUser();
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<XjEduStudentCourseExt> recordList = studentCourseBiz.searchStudentCourse(user, eduUser, doctor, studentCourse,scoreSum,degreeGrade,courseTypeScore);
        model.addAttribute("recordList", recordList);
        model.addAttribute("user", user);
        model.addAttribute("currentPage", currentPage);
        return "xjgl/student/studentResult";
    }
    /**
     * 学生毕业信息查询
     */
    @RequestMapping(value = "/eduGraduateInfoList")
    public String eduGraduateInfoList(Model model, EduUser eduUser, SysUser sysUser, ResDoctor resDoctor, String flag,String registerFlag,
                                      String startDate, String endDate, String startSid, String endSid, String startBirthday, String endBirthday, String teacherName,
                                      Integer currentPage, HttpServletRequest request, String from, String partId, String partStatus){
        PageHelper.startPage(currentPage, getPageSize(request));
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("eduUser", eduUser);
        paramMap.put("sysUser", sysUser);
        paramMap.put("resDoctor", resDoctor);
        paramMap.put("startDate", startDate);
        paramMap.put("endDate", endDate);
        paramMap.put("startSid", startSid);
        paramMap.put("endSid", endSid);
        paramMap.put("startBirthday", startBirthday);
        paramMap.put("endBirthday", endBirthday);
        paramMap.put("teacherName", teacherName);
        List<XjEduGraduateInfoExt> graduateInfoList = eduUserBiz.searchEduGraduateInfo(paramMap);
        if(graduateInfoList!=null&&graduateInfoList.size()>0){
            for (XjEduGraduateInfoExt eu:graduateInfoList) {
                String content=eu.getContent();
                if(StringUtil.isNotBlank(content)){
                    XjPollingTableForm form = eduUserBiz.parsePollingTableXml(content);
                    eu.setPollingTableForm(form);
                }
            }
        }
        model.addAttribute("graduateInfoList",graduateInfoList);
        return "xjgl/plat/graduationInfors";
    }
    @RequestMapping(value="/resultSunInfo")
    public String resultSunInfo(Model model, String userFlow,String period,String courseFlow) throws Exception{
        studentCourseBiz.createQrCodeForGrade(userFlow);
        EduUser eduuser=eduUserBiz.readEduUser(userFlow);
        SysUser sysuser=userBiz.readSysUser(userFlow);
        ResDoctor doctor = eduUserBiz.findDoctorByFlow(userFlow);
        List<String> userFlowList = new ArrayList<String>();
        userFlowList.add(userFlow);
        List<XjEduUserExt> eduUserExts=eduUserBiz.searchEduUserCourseExtMajorList(userFlowList,null);
        XjEduUserExt eduUserExt=null;
        if(eduUserExts!=null&&!eduUserExts.isEmpty()){
            eduUserExt=eduUserExts.get(0);
        }
        model.addAttribute("eduUserExt",eduUserExt);
        model.addAttribute("eduuser",eduuser);
        model.addAttribute("doctor",doctor);
        model.addAttribute("sysuser",sysuser);
        String filePath = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_url"))+"/grade/";
        String fileName = userFlow+".png";
        filePath += fileName;
        model.addAttribute("qrcode",filePath);
        String time=DateUtil.getCurrDateTime("yyyy")+"年"+DateUtil.getCurrDateTime("MM")+"月"+DateUtil.getCurrDateTime("dd")+"日";
        model.addAttribute("time", time);
        return "xjgl/plat/resultCount";

    }
    /**
     * 成绩批量打印
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/print4Admin")
    public void print4Admin(EduUser eu, SysUser su, ResDoctor rd, EduStudentCourse sc,String printFlag, HttpServletRequest request, HttpServletResponse response) throws Exception{
        String folder=System.getProperty("java.io.tmpdir")+ File.separator;
        String zipFile = PkUtil.getUUID();
        String dir = folder+zipFile;
        File dirFile = new File(dir);
        if(dirFile.exists()==false){
            dirFile.mkdirs();
        }
        String userFlow="";
        List<WordprocessingMLPackage> addTemplates = new ArrayList<WordprocessingMLPackage>();
        List<XjEduStudentCourseExt> recordList = studentCourseBiz.searchStudentCourse(su, eu, rd, sc,null,null,null);
        String name ="";
        List<String> userFlows=new ArrayList<>();
        if(recordList!=null&&recordList.size()>0){
            for (XjEduStudentCourseExt xsce:recordList) {
                if(StringUtil.isNotBlank(xsce.getUserFlow())&&!userFlows.contains(xsce.getUserFlow())){
                    userFlows.add(xsce.getUserFlow());
                }
            }
        }
        if(userFlows!=null&&userFlows.size()>0){
            String xsce="";
            for (int n=0;n<userFlows.size();n++) {
                xsce=userFlows.get(n);
                if(StringUtil.isNotBlank(xsce)){
                    userFlow=xsce;
                    String totalCount="";
                    float count=0;
                    String code="";
                    Map<String, Object> dataMap = new HashMap<String, Object>();
                    WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
                    ServletContext context =  request.getServletContext();
                    String watermark = GeneralMethod.getWatermark(null);
                    EduUser eduUser=eduUserBiz.readEduUser(userFlow);
                    SysUser sysUser=userBiz.readSysUser(userFlow);
                    dataMap.put("year", eduUser.getPeriod());
                    dataMap.put("sid", eduUser.getSid());
                    dataMap.put("userName",sysUser.getUserName());
                    dataMap.put("sex", sysUser.getSexName());
                    dataMap.put("birthday", sysUser.getUserBirthday());
                    //dataMap.put("tacherName", doctor.getTutorName());
                    dataMap.put("tacherName", StringUtil.defaultIfEmpty(eduUser.getFirstTeacher(),""));
                    dataMap.put("majorName", eduUser.getMajorName());
                    dataMap.put("trainTypeName", eduUser.getTrainCategoryName());
                    dataMap.put("studentTypeName", eduUser.getTrainTypeName());
                    List<String> userFlowList= new ArrayList<String>();
                    userFlowList.add(userFlow);
                    List<XjEduUserExt> eduUserExtList=eduUserBiz.searchEduUserCourseExtMajorList(userFlowList,null);
                    if(eduUserExtList!=null&& !eduUserExtList.isEmpty()){
                        XjEduUserExt userExt=eduUserExtList.get(0);
                        List<XjEduStudentCourseExt> list = userExt.getCourseExtsList();
                        List<XjEduStudentCourseExt> list1 = new ArrayList<>();
                        Map<String,XjEduStudentCourseExt> map=new HashMap<>();
                        //打印 有成绩且 成绩合格或者成绩大于等于课程有效成绩的记录
                        if (list != null && list.size() > 0) {
                            for (int i = 0; i < list.size(); i++) {
                                XjEduStudentCourseExt ext = list.get(i);
                                if (StringUtil.isNotBlank(ext.getCourseGrade())) {
                                    if ("Y".equals(ext.getCourseGrade())) {//通过，合格
                                        list1.add(ext);
                                    }else if(isNumeric(ext.getCourseGrade()) && Double.valueOf(ext.getCourseGrade()) >= Double.valueOf(ext.getCourse().getEffectiveGrade())){//具体成绩 且大于等于课程有效成绩
                                        list1.add(ext);
                                    }
                                }
                            }
                            /**南医大成绩打印去掉相同课程 保留最好成绩**/
                            if(list1.size() > 0){
                                List<XjEduStudentCourseExt> list2 = new ArrayList<>();
                                for (int i = 0; i < list1.size(); i++) {
                                    XjEduStudentCourseExt ext = list1.get(i);
                                    if(map.containsKey(ext.getCourseCode())){
                                        XjEduStudentCourseExt tempExt=map.get(ext.getCourseCode());
                                        if ("Y".equals(ext.getCourseGrade())) {//通过，合格
                                            map.put(ext.getCourseCode(),ext);
                                        }else if(isNumeric(ext.getCourseGrade()) && isNumeric(tempExt.getCourseGrade())){
                                            if(Double.valueOf(ext.getCourseGrade()) >= Double.valueOf(tempExt.getCourseGrade())){
                                                map.put(ext.getCourseCode(),ext);
                                            }
                                        }
                                    }else{
                                        map.put(ext.getCourseCode(),ext);
                                    }
                                }
                                Iterator<Map.Entry<String,XjEduStudentCourseExt>> entries = map.entrySet().iterator();
                                while (entries.hasNext()) {
                                    Map.Entry<String,XjEduStudentCourseExt> entry = entries.next();
                                    if(entry.getValue()!=null){
                                        list2.add(entry.getValue());
                                    }
                                }
                                list1=list2;
                            }
                            /**南医大成绩打印去掉相同课程 保留最好成绩**/
                        }
                        userExt.setCourseExtsList(list1);
                        if (userExt.getCourseExtsList()!= null&&!userExt.getCourseExtsList().isEmpty()) {
                            List<ItemGroupData> yearPlanTargetList = new ArrayList<ItemGroupData>();
                            for(XjEduStudentCourseExt esc:userExt.getCourseExtsList()){
                                ItemGroupData  igd = new ItemGroupData();
                                Map<String , Object> objMap = new HashMap<String, Object>();
                                if(StringUtil.isNotBlank(esc.getCourseCode())){
                                    code="["+esc.getCourseCode()+"]";
                                }
                                if(GlobalConstant.FLAG_Y.equals(printFlag)){
                                    objMap.put("courseName","["+esc.getCourseCode()+"]"+esc.getCourseNameEn());
                                }
                                if(GlobalConstant.FLAG_N.equals(printFlag)){
                                    objMap.put("courseName","["+esc.getCourseCode()+"]"+esc.getCourseName());
                                }
                                objMap.put("courseTypeName", esc.getCourseTypeName());
                                objMap.put("coursePeriod", esc.getCoursePeriod());
                                objMap.put("courseCredit", esc.getCourseCredit());
                                List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("XjIsPassed");
                                int result=0;String grade="";
                                for(SysDict dict:dictList){
                                    if(dict.getDictId().equals(esc.getCourseGrade())){
                                        result=1;
                                        grade=dict.getDictName();
                                    }
                                }
                                DecimalFormat df = new DecimalFormat("0.0");
                                if(result==0){
                                    objMap.put("courseGrade", df.format(Double.parseDouble(StringUtil.defaultIfEmpty(esc.getCourseGrade(),"0.0"))));
                                }else{
                                    objMap.put("courseGrade", grade);
                                }
                                if(GlobalConstant.FLAG_Y.equals(printFlag)){
                                    if(objMap.get("courseGrade")!=null) {
                                        if (objMap.get("courseGrade").equals("合格") || objMap.get("courseGrade").equals("通过")) {
                                            objMap.put("courseGrade", "PASS");
                                        }
                                        if (objMap.get("courseGrade").equals("不合格") || objMap.get("courseGrade").equals("不通过") || objMap.get("courseGrade").equals("缺考")) {
                                            objMap.put("courseGrade", "");
                                        }
                                    }
                                }
                                igd.setObjMap(objMap);
                                yearPlanTargetList.add(igd);
                                if(StringUtil.isNotBlank(esc.getCourseCredit())){
                                    count+=Float.parseFloat(esc.getCourseCredit());
                                }
                            }
                            dataMap.put("yearPlanTargetList", yearPlanTargetList);
                            totalCount=String.valueOf(count);
                            dataMap.put("totalCount", totalCount);
                        }
                    }
                    String time=DateUtil.getCurrDateTime("yyyy")+"年"+DateUtil.getCurrDateTime("MM")+"月"+DateUtil.getCurrDateTime("dd")+"日";
                    dataMap.put("time", time);
                    String path = "";
                    if(GlobalConstant.FLAG_Y.equals(printFlag)){
                        String eName = "";
                        if(StringUtil.isNotBlank(sysUser.getUserName())){
                            eName=PyUtil.getUpEname(sysUser.getUserName());
                        }
                        dataMap.put("eName", eName);
                        String id = eduUser.getMajorId();
                        String eMajorName ="";
                        if(StringUtil.isNotBlank(id)){
                            eMajorName = dictBiz.readDict(DictTypeEnum.Major.getId(),id)==null?"":dictBiz.readDict(DictTypeEnum.Major.getId(),id).getDictDesc();
                        }
                        dataMap.put("eMajorName", eMajorName);
                        dataMap.put("rollMentYear",eduUser.getPeriod());
                        Date date = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM.dd,yyyy", Locale.ENGLISH);
                        String currDate =  dateFormat.format(date);
                        if (currDate.charAt(4) == '0') {
                            if (currDate.charAt(5) == '1') {
                                currDate = currDate.substring(0, 4) + currDate.substring(5, 6) +"st" + currDate.substring(6, 11);
                            } else if (currDate.charAt(5) == '2') {
                                currDate = currDate.substring(0, 4) + currDate.substring(5, 6) +"nd" + currDate.substring(6, 11);
                            } else if (currDate.charAt(5) == '3') {
                                currDate = currDate.substring(0, 4) + currDate.substring(5, 6) +"rd" + currDate.substring(6, 11);
                            } else {
                                currDate = currDate.substring(0, 4) + currDate.substring(5, 6) +"th" + currDate.substring(6, 11);
                            }
                        }else{
                            if (currDate.charAt(5) == '1') {
                                currDate = currDate.substring(0, 6)+"st" + currDate.substring(6, 11);
                            } else if (currDate.charAt(5) == '2') {
                                currDate = currDate.substring(0, 6)+"nd" + currDate.substring(6, 11);
                            } else if (currDate.charAt(5) == '3') {
                                currDate = currDate.substring(0, 6)+"rd" + currDate.substring(6, 11);
                            } else {
                                currDate = currDate.substring(0, 6)+"th" + currDate.substring(6, 11);
                            }
                        }
                        if(currDate.substring(0,3).equals("May")){
                            currDate = "May "+currDate.substring(4,currDate.length());
                        }
                        dataMap.put("eTime",currDate);
                        path="/jsp/xjgl/print/eGrade2.docx";//英文模板
                        name= n+1+"、"+eName+"school report.docx";
                    }
                    if(GlobalConstant.FLAG_N.equals(printFlag)){
                        path="/jsp/xjgl/print/grade.docx";//中文模板
                        name=  n+1+"、"+sysUser.getUserName()+"成绩单.docx";
                    }
                    //插入图片
                    String value = "";
                    //String image = "file:///"+System.getProperty("user.home")+File.separator+"grade"+File.separator+userFlow+".png";
                    String image = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+ File.separator+"grade"+File.separator+userFlow+".png";
                    if (StringUtil.isBlank(image)) {
                        value = "";
                    } else {
                        value = "<img src='"+image+"' width='90' height='90'  alt='二维码'/>";
                    }
                    dataMap.put("headImg", value);
                    temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
                    addTemplates.add(temeplete);
                    File f = new File(dir+File.separator+name);
                    temeplete.save(f);
                }
            }
            ZipUtil.makeDirectoryToZip(dirFile, new File(folder+zipFile+".zip"), "", 7);
            String fileName = "schoolReport.zip";
            if(GlobalConstant.FLAG_N.equals(printFlag)){
                fileName = "成绩单.zip";
            }
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            byte[] data = getByte(new File(folder+zipFile+".zip"));
            if (data != null) {
                outputStream.write(data);
                new File(folder+zipFile+".zip").delete();
            }
            outputStream.flush();
            outputStream.close();
        }
    }
    public static byte[] getByte(File file) throws Exception {
        if (file == null) {
            return null;
        }
        try {
            int length = (int) file.length();
            if (length > Integer.MAX_VALUE) {    //当文件的长度超过了int的最大值
                System.out.println("this file is max ");
            }
            FileInputStream stream = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream(length);
            byte[] b = new byte[length];
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
        }
        return null;
    }
    @RequestMapping("/expExcelCddw")
    public void expExcelCddw(EduUser eduUser,SysUser sysUser,ResDoctor doctor,EduStudentCourse studentCourse,String scoreSum,String degreeGrade,String courseTypeScore,HttpServletResponse response)throws Exception{
        SysUser user = GlobalContext.getCurrentUser();
        List<XjEduStudentCourseExt> recordList = studentCourseBiz.searchGradeInfos(sysUser,eduUser,doctor,studentCourse,scoreSum,degreeGrade,courseTypeScore,user.getOrgFlow());
        String fileName = "学员成绩单.xls";
        createExcle(response, fileName, recordList);
    }

    /**
     * 成绩批量在线打印
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/print4AdminOL")
    public String print4AdminOL(EduUser eu, SysUser su, ResDoctor rd, EduStudentCourse sc,String printFlag,String isStmp,String from,Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userFlow="";
        List<Map<String,Object>> dataList=new ArrayList<>();
        if(StringUtil.isNotBlank(su.getUserName())){
            su.setUserName(URLDecoder.decode(su.getUserName(), "UTF-8"));
        }
        if(GlobalConstant.USER_LIST_CHARGE.equals(from)){
            //分委会
            eu.setTrainOrgId(GlobalContext.getCurrentUser().getDeptFlow());
        }
        su.setCreateUserFlow("one");//用作标识管理员-学生成绩查询-打印单个学生操作
        List<XjEduStudentCourseExt> recordList = studentCourseBiz.searchStudentCourse(su, eu, rd, sc,null,null,null);
        String name ="";
        List<String> userFlows=new ArrayList<>();
        if(recordList!=null&&recordList.size()>0){
            for (XjEduStudentCourseExt xsce:recordList) {
                if(StringUtil.isNotBlank(xsce.getUserFlow())&&!userFlows.contains(xsce.getUserFlow())){
                    userFlows.add(xsce.getUserFlow());
                }
            }
        }
        if(userFlows!=null&&userFlows.size()>0){
            for (int n=0;n<userFlows.size();n++) {
                Map<String,Object> dataMap=new HashMap<>();
                userFlow=userFlows.get(n);
                studentCourseBiz.createQrCodeForGrade(userFlow);
                EduUser eduuser = eduUserBiz.readEduUser(userFlow);
                SysUser sysuser = userBiz.readSysUser(userFlow);
                ResDoctor doctor = eduUserBiz.findDoctorByFlow(userFlow);
                List<String> userFlowList = new ArrayList<String>();
                userFlowList.add(userFlow);
                List<XjEduUserExt> eduUserExts = eduUserBiz.searchEduUserCourseExtMajorList(userFlowList, null);
                XjEduUserExt eduUserExt = null;
                if (eduUserExts != null && !eduUserExts.isEmpty()) {
                    eduUserExt = eduUserExts.get(0);
                    List<XjEduStudentCourseExt> list = eduUserExt.getCourseExtsList();
                    List<XjEduStudentCourseExt> list1 = new ArrayList<>();
                    Map<String,XjEduStudentCourseExt> map=new HashMap<>();
                    //打印 有成绩且 成绩合格或者成绩大于等于课程有效成绩的记录
                    if (list != null && list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            XjEduStudentCourseExt ext = list.get(i);
                            if (StringUtil.isNotBlank(ext.getCourseGrade())) {
                                if ("Y".equals(ext.getCourseGrade())) {//通过，合格
                                    list1.add(ext);
                                }else if(isNumeric(ext.getCourseGrade()) && Double.valueOf(ext.getCourseGrade()) >= Double.valueOf(ext.getCourse().getEffectiveGrade())){//具体成绩 且大于等于课程有效成绩
                                    list1.add(ext);
                                }
                            }
                        }
                        /**南医大成绩打印去掉相同课程 保留最好成绩**/
                        if(list1.size() > 0){
                            List<XjEduStudentCourseExt> list2 = new ArrayList<>();
                            for (int i = 0; i < list1.size(); i++) {
                                XjEduStudentCourseExt ext = list1.get(i);
                                if(map.containsKey(ext.getCourseCode())){
                                    XjEduStudentCourseExt tempExt=map.get(ext.getCourseCode());
                                    if ("Y".equals(ext.getCourseGrade())) {//通过，合格
                                        map.put(ext.getCourseCode(),ext);
                                    }else if(isNumeric(ext.getCourseGrade()) && isNumeric(tempExt.getCourseGrade())){
                                        if(Double.valueOf(ext.getCourseGrade()) >= Double.valueOf(tempExt.getCourseGrade())){
                                            map.put(ext.getCourseCode(),ext);
                                        }
                                    }
                                }else{
                                    map.put(ext.getCourseCode(),ext);
                                }
                            }
                            Iterator<Map.Entry<String,XjEduStudentCourseExt>> entries = map.entrySet().iterator();
                            while (entries.hasNext()) {
                                Map.Entry<String,XjEduStudentCourseExt> entry = entries.next();
                                if(entry.getValue()!=null){
                                    list2.add(entry.getValue());
                                }
                            }
                            list1=list2;
                        }
                        /**南医大成绩打印去掉相同课程 保留最好成绩**/
                    }
                    eduUserExt.setCourseExtsList(list1);
                }
                dataMap.put("eduUserExt", eduUserExt);
                dataMap.put("eduuser", eduuser);
                dataMap.put("doctor", doctor);
                dataMap.put("sysuser", sysuser);
                String filePath = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_url")) + "/grade/";
                String fileName = userFlow + ".png";
                filePath += fileName;
                String filePath2 = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + "/grade/";
                filePath2 += fileName;
                File file = new File(filePath2);
                if (file.exists()) {
                    dataMap.put("qrcode", filePath);
                    String time = DateUtil.getCurrDateTime("yyyy") + "年" + DateUtil.getCurrDateTime("MM") + "月" + DateUtil.getCurrDateTime("dd") + "日";
                    dataMap.put("time", time);
                    dataMap.put("printStmp", isStmp);
                    if (!printFlag.equals(GlobalConstant.FLAG_N)) {
                        if(StringUtil.isNotBlank(sysuser.getUserName())){
                            String eName = PyUtil.getUpEname(sysuser.getUserName());
                            dataMap.put("eName", eName);
                        }
                        String majorId = eduuser.getMajorId();
                        String eMajorName ="";
                        if(StringUtil.isNotBlank(majorId)){
                            eMajorName = dictBiz.readDict(DictTypeEnum.Major.getId(),majorId)==null?"":dictBiz.readDict(DictTypeEnum.Major.getId(),majorId).getDictDesc();
                        }
//                        String eMajorName = dictBiz.readDict(DictTypeEnum.Major.getId(), majorId).getDictDesc();
                        dataMap.put("eMajorName", eMajorName);
                        Date date = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM.dd,yyyy", Locale.ENGLISH);
                        String currDate = dateFormat.format(date);
                        if (currDate.charAt(4) == '0') {
                            if (currDate.charAt(5) == '1') {
                                currDate = currDate.substring(0, 4) + currDate.substring(5, 6) + "st" + currDate.substring(6, 11);
                            } else if (currDate.charAt(5) == '2') {
                                currDate = currDate.substring(0, 4) + currDate.substring(5, 6) + "nd" + currDate.substring(6, 11);
                            } else if (currDate.charAt(5) == '3') {
                                currDate = currDate.substring(0, 4) + currDate.substring(5, 6) + "rd" + currDate.substring(6, 11);
                            } else {
                                currDate = currDate.substring(0, 4) + currDate.substring(5, 6) + "th" + currDate.substring(6, 11);
                            }
                        } else {
                            if (currDate.charAt(5) == '1') {
                                currDate = currDate.substring(0, 6) + "st" + currDate.substring(6, 11);
                            } else if (currDate.charAt(5) == '2') {
                                currDate = currDate.substring(0, 6) + "nd" + currDate.substring(6, 11);
                            } else if (currDate.charAt(5) == '3') {
                                currDate = currDate.substring(0, 6) + "rd" + currDate.substring(6, 11);
                            } else {
                                currDate = currDate.substring(0, 6) + "th" + currDate.substring(6, 11);
                            }
                        }
                        if (currDate.substring(0, 3).equals("May")) {
                            currDate = "May " + currDate.substring(4, currDate.length());
                        }
                        dataMap.put("currDate", currDate);
                    }
                }
                dataList.add(dataMap);
            }
        }
        model.addAttribute("dataList",dataList);
        if (!printFlag.equals(GlobalConstant.FLAG_N)) {
            return "xjgl/plat/englishScoreOL";
        }
        return "xjgl/plat/chineseScoreOL";
    }
    /**
     * 学位信息采集详情
     */
    @RequestMapping("/infoCollection")
    public String infoCollection(String userFlow,String viewFlag,HttpServletRequest request,Model model) {
        return "xjgl/plat/certificateInfo";
    }
    /**
     * 学位信息采集查询
     */
    @RequestMapping("/degreeInfoList")
    public String degreeInfoList(String role,DegreeInfoMain main,Integer currentPage,HttpServletRequest request,Model model) {
        //学生角色
        if("xs".equals(role)){
            main.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        }
        if(GlobalConstant.USER_LIST_LOCAL.equals(role)){
            main.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        }
        if("fwh".equals(role)){
            main.setDeptFlow(GlobalContext.getCurrentUser().getDeptFlow());
        }
        //入学年级格式处理
        List<String> periodList=new ArrayList<>();
        if(StringUtil.isNotBlank(main.getPeriod())){
            String[] arr=main.getPeriod().split("-");
            if(arr!=null&&arr.length>0){
                periodList=Arrays.asList(arr);
                main.setPeriod("");
            }
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<DegreeInfoMain> mainList = eduUserBiz.queryDegreeInfoList1(main,periodList);
        List<SysOrg> orgList = orgBiz.searchTrainOrgList();
        List<SysDept> deptList = deptBiz.searchDeptByOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("orgList", orgList);
        model.addAttribute("mainList",mainList);
        model.addAttribute("deptList", deptList);
        model.addAttribute("role", role);
        return "xjgl/student/degreeInfoList";
    }
    /**
     * 新增/编辑 学位信息采集 界面
     */
    @RequestMapping(value="/editDegreeInfo")
    public String editDegreeInfo(String userFlow,Model model){
        if(StringUtil.isNotBlank(userFlow)){
           DegreeInfoMain main = eduUserBiz.readDegreeInfo(userFlow);
            model.addAttribute("main",main);
        }else{
            userFlow = GlobalContext.getCurrentUser().getUserFlow();
            SysUser sysUser = userBiz.readSysUser(userFlow);
            EduUser eduUser = eduUserBiz.readEduUser(userFlow);
            ResDoctor doctor = doctorBiz.readDoctor(userFlow);
            XjEduUserExtInfoForm extForm = eduUserBiz.parseExtInfoXml(eduUser.getContent());
            model.addAttribute("extForm", extForm);
            model.addAttribute("sysUser",sysUser);
            model.addAttribute("eduUser",eduUser);
            model.addAttribute("doctor",doctor);
        }
        return "xjgl/student/editDegreeInfo";
    }

    /**
     * 学位信息采集 保存操作
     */
    @RequestMapping("/saveDegreeInfo")
    @ResponseBody
    public String saveDegreeInfo(DegreeInfoMain main) throws Exception{
        int num = eduUserBiz.saveDegreeInfo(main,null);
        if (num > 0) {
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     * 学位信息采集导出
     */
    @RequestMapping("/exportDegree")
    public void exportDegree(String role,DegreeInfoMain main,HttpServletResponse response)throws Exception{
        //学生角色
        if("xs".equals(role)){
            main.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        }
        if(GlobalConstant.USER_LIST_LOCAL.equals(role)){
            main.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        }
        if("fwh".equals(role)){
            main.setDeptFlow(GlobalContext.getCurrentUser().getDeptFlow());
        }
        //入学年级格式处理
        List<String> periodList=new ArrayList<>();
        if(StringUtil.isNotBlank(main.getPeriod())){
            String[] arr=main.getPeriod().split("-");
            if(arr!=null&&arr.length>0){
                periodList=Arrays.asList(arr);
                main.setPeriod("");
            }
        }
        List<DegreeInfoMain> mainList = eduUserBiz.queryDegreeInfoList(main,periodList);
        if(null != mainList && !mainList.isEmpty()){
            for(DegreeInfoMain dim : mainList){
                //民族
                dim.setNationName((StringUtil.isBlank(dim.getNationId())?"":dim.getNationId())+(StringUtil.isBlank(dim.getNationName())?"":dim.getNationName()));
                //专业名称
                dim.setMajorName(dim.getMajorId()+dim.getMajorName());
                //入学前户口所在地
                if(StringUtil.isNotBlank(dim.getDomicilePlaceName())){
                    if(dim.getDomicilePlaceName().split(",")!=null&&dim.getDomicilePlaceName().split(",").length>0){
                        dim.setDomicilePlaceName(dim.getDomicilePlaceName().split(",")[0]);
                    }
                }
                //国家或地区
                if(StringUtil.isNotBlank(dim.getCountryArea())){
                    dim.setCountryArea(dim.getCountryArea()+UserCountryAreaEnum.getNameById(dim.getCountryArea()));
                }
                //招录方式
                dim.setRecruitType(DictTypeEnum.RecruitType.getDictNameById(dim.getRecruitType()));
                //导师姓名
                String tutor = StringUtil.isBlank(dim.getFirstTeacher())?"":dim.getFirstTeacher();
                if(StringUtil.isNotBlank(dim.getFirstTeacher()) && StringUtil.isNotBlank(dim.getSecondTeacher())){
                    tutor+="，";
                }
                dim.setFirstTeacher(tutor+(StringUtil.isBlank(dim.getSecondTeacher())?"":dim.getSecondTeacher()));
                //学习方式
                String studyType = "";
                if("tc".equals(dim.getStudyType())){
                    studyType = "脱产";
                }else if("btc".equals(dim.getStudyType())){
                    studyType = "半脱产";
                }else if("yy".equals(dim.getStudyType())){
                    studyType = "业余";
                }
                dim.setStudyType(studyType);
                //考试方式
                String testType = "";
                if("qgtk".equals(dim.getTestType())){
                    testType = "全国统考(联考)";
                }else if("tjms".equals(dim.getTestType())){
                    testType = "推荐免试";
                }else if("ddks".equals(dim.getTestType())){
                    testType = "单独考试";
                }else if("zzqgtk".equals(dim.getTestType())){
                    testType = "在职人员攻读硕士学位全国联考(MBA，工程硕士等)";
                }else if("tdxl".equals(dim.getTestType())){
                    testType = "同等学力(临床医学等)";
                }else if("zzks".equals(dim.getTestType())){
                    testType = "招生单位自主考试(软件工程等领域工程硕士)";
                }else if("other".equals(dim.getTestType())){
                    testType = "其它";
                }
                dim.setTestType(testType);
                //拟获学位名称
                dim.setGainPreDegree(UserDegreeEnum.getNameById(dim.getGainPreDegree()));
                //是否一级学科授予
                dim.setOneSubjectFlag(GlobalConstant.FLAG_Y.equals(dim.getOneSubjectFlag())?"是":"否");
                //前置学历
                dim.setPreGraduation(DictTypeEnum.PreGraduation.getDictNameById(dim.getPreGraduation()));
                //论文类型
                dim.setPaperType(DictTypeEnum.PaperType.getDictNameById(dim.getPaperType()));
                //论文选题来源
                dim.setPaperSource(DictTypeEnum.PaperSource.getDictNameById(dim.getPaperSource()));
                //获学士学位专业名称
                dim.setUnderMajor(_checkMajorEnumId(dim.getUnderMajor()));
                //获得学士学位学科门类
                if(StringUtil.isNotBlank(dim.getGotBachelorCertSubject())){
                    dim.setGotBachelorCertSubject(_checkSubjectEnumId(dim.getGotBachelorCertSubject()));
                }
                if(StringUtil.isNotBlank(dim.getTrainTypeName()) && dim.getTrainTypeName().contains("博士")){
                    //获硕士学位专业名称
                    dim.setGotMasterCertSpe(_checkMajorEnumId(dim.getGotMasterCertSpe()));
                    //获得硕士学位学科门类
                    if(StringUtil.isNotBlank(dim.getMasterSubject())){
                        dim.setMasterSubject(_checkSubjectEnumId(dim.getMasterSubject()));
                    }
                }

                //获学位后去向
                dim.setDegreeDirection(DictTypeEnum.GotDegreeDirection.getDictNameById(dim.getDegreeDirection()));
                //就业单位性质
                dim.setUnitNature(DictTypeEnum.UnitNature.getDictNameById(dim.getUnitNature()));
                //工作性质
                dim.setWorkNature(DictTypeEnum.WorkNature.getDictNameById(dim.getWorkNature()));
                //去除时间格式
                dim.setUserBirthday(StringUtil.replace(dim.getUserBirthday(),"-",""));
                dim.setGraduateTime(StringUtil.replace(dim.getGraduateTime(),"-",""));
                dim.setAwardDegreeTime(StringUtil.replace(dim.getAwardDegreeTime(),"-",""));
                dim.setUnderAwardDegreeTime(StringUtil.replace(dim.getUnderAwardDegreeTime(),"-",""));
                dim.setMasterAwardDegreeTime(StringUtil.replace(dim.getMasterAwardDegreeTime(),"-",""));
            }
        }
        String[] titles = new String[]{
                "sid:学号",
                "userName:姓名",
                "sexName:性别",
                "trainCategoryName:培养类型",
                "trainTypeName:培养层次",
                "nationName:民族",
                "politicsStatusName:政治面貌",
                "userBirthday:出生日期",
                "domicilePlaceName:入学前户口所在地",
                "cretTypeName:证件类型",
                "idNo:证件号码",
                "countryArea:国家或地区",
                "period:入学年级",
                "graduateTime:毕业时间",
                "recruitType:招录方式",
                "firstTeacher:导师姓名",
                "studentCode:考生编号",
                "studyType:学习方式",
                "testType:考试方式",
                "testQualifiedNo:综合考试合格编号",
                "gainPreDegree:拟获学位名称",
                "oneSubjectFlag:是否一级学科授予",
                "majorName:专业名称",
                "preGraduation:前置学历",
                "awardDegreeTime:拟授予学位时间",
                "awardDegreeCertCode:拟授予学位证书编号",
                "paperTitle:论文题目",
                "paperKey:论文关键词",
                "paperType:论文类型",
                "paperSource:论文选题来源",
                "underMajor:获学士学位专业名称",
                "preDegreeName:前置学位",
                "firstLevelSubjectName:前置学位一级学科名称",
                "preDegreeCertificateNo:前置学位证书编号",
                "underPreDegreeTime:获前置学位年月",
                "underPreDegreeOrg:获前置学位单位",
                "underDegreeCertCode:学士学位证书编号",
                "gotBachelorCertSubject:获得学士学位学科门类",
                "underAwardDegreeTime:获学士学位年月",
                "underAwardDegreeOrg:获学士学位单位名称",
                "gotMasterCertSpe:获硕士学位专业名称",
                "masterDegreeCertCode:硕士学位证书编号",
                "masterSubject:获得硕士学位学科门类",
                "masterAwardDegreeTime:获硕士学位年月",
                "masterAwardDegreeOrg:获硕士学位单位名称",
                "orgName:培养单位",
                "degreeDirection:获学位后去向",
                "unitNature:就业单位性质",
                "unitName:就业单位名称",
                "workNature:工作性质",
                "unitAddress:就业单位所在省",
                "userPhone:联系电话",
                "userEmail:电子邮箱",
                "userQq:QQ号码",
                "weiXinName:微信账号"
        };
        String fileName = "学位采集信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("Application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjsAllString(titles, mainList,response.getOutputStream());
    }
    public static String _dateTypeTrans(String value) {
        if(StringUtil.isNotBlank(value)){
            if (value.length()==10) {
                value=DateUtil.transDateTime(value, "yyyy-MM-dd", "yyyyMMdd");
            }
            if (value.length()==7) {
                value=DateUtil.transDateTime(value, "yyyy-MM", "yyyyMM");
            }
        }
        return value;
    }
    /**
     * 学生成绩录入-培养单位
     * @param flag 拆分成绩管理的标识
     */
    @RequestMapping(value = "/selectStudentCourseSunEdit")
    public String selectStudentCourseSunEdit(Integer currentPage, EduUser eduUser, SysUser sysUser, ResDoctor doctor, EduStudentCourse studentCourse,String flag,
                                             Model model, HttpServletRequest request, String from,String scoreSum,String degreeGrade,String courseTypeScore) {
        model.addAttribute("flag", flag);
        PageHelper.startPage(currentPage, getPageSize(request));
        SysUser user = GlobalContext.getCurrentUser();
        if (GlobalConstant.USER_LIST_LOCAL.equals(from)) {
            //培养单位
            doctor.setOrgFlow(user.getOrgFlow());
            if ("edit".equals(flag)) {
                sysUser.setCreateTime("pydw");
            }
        }
        List<XjEduStudentCourseExt> recordList = studentCourseBiz.searchStudentCourse(sysUser, eduUser, doctor, studentCourse,scoreSum,degreeGrade,courseTypeScore);
        model.addAttribute("recordList", recordList);
        List<SysOrg> orgList = orgBiz.searchTrainOrgList();
        model.addAttribute("orgList", orgList);
        return "xjgl/plat/resultManager4Pydw";

    }
    @RequestMapping(value="/resultSun4Pydw")
    public String resultSun4Pydw(Model model, String userFlow,String period,String courseFlow) throws Exception{
        EduUser eduuser=eduUserBiz.readEduUser(userFlow);
        SysUser sysuser=userBiz.readSysUser(userFlow);
        ResDoctor doctor = eduUserBiz.findDoctorByFlow(userFlow);
        EduStudentCourse ec=new EduStudentCourse();
        ec.setCreateTime("pydw");//培养单位-学生成绩录入
        List<String> userFlowList = new ArrayList<String>();
        userFlowList.add(userFlow);
        List<XjEduUserExt> eduUserExts=eduUserBiz.searchEduUserCourseExtMajorList(userFlowList,ec);
        XjEduUserExt eduUserExt=null;
        if(eduUserExts!=null&&!eduUserExts.isEmpty()){
            eduUserExt=eduUserExts.get(0);
        }
        model.addAttribute("eduUserExt",eduUserExt);
        model.addAttribute("eduuser",eduuser);
        model.addAttribute("doctor",doctor);
        model.addAttribute("sysuser",sysuser);
        return "xjgl/plat/resultSun4Pydw";

    }
    public static String _checkMajorEnumId(String id){
        UserDegreeMajorEnum[] values = UserDegreeMajorEnum.values();
        for (UserDegreeMajorEnum item : values) {
            if (item.getId().equals(id)) {
                return item.getName();
            }
        }
        return id;
    }
    public static String _checkSubjectEnumId(String id){
        UserDegreeSubjectEnum[] values = UserDegreeSubjectEnum.values();
        for (UserDegreeSubjectEnum item : values) {
            if (item.getId().equals(id)) {
                return item.getName();
            }
        }
        return id;
    }
    //查询金额明细
    @RequestMapping(value="/searchStuFeeInfo")
    public String searchStuFeeInfo(String sid,Model model){
        List<Map<String, Object>> stuFeeInfoList=new ArrayList<>();
        if(StringUtil.isNotBlank(sid)){
            stuFeeInfoList=eduUserBiz.readStuFeeInfo(sid);
        }
        model.addAttribute("stuFeeInfoList",stuFeeInfoList);
        return "xjgl/plat/stuFeeInfoList";
    }

    @RequestMapping("/supplementList")
    public String supplementList(String role,Integer currentPage,HttpServletRequest request,Model model,
                                 String sid,String userName,String trainTypeId,String trainCategoryId,String submitFlag) {
        if("xx".equals(role)){
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("sid", sid);
            paramMap.put("userName", userName);
            paramMap.put("trainTypeId", trainTypeId);
            paramMap.put("trainCategoryId", trainCategoryId);
            paramMap.put("submitFlag", submitFlag);
            PageHelper.startPage(currentPage, getPageSize(request));
            List<Map<String,Object>> userList = eduUserBiz.supplementList(paramMap);
            model.addAttribute("userList", userList);
            return "xjgl/plat/supplementList";
        }else{
            String userFlow = GlobalContext.getCurrentUser().getUserFlow();
            GydxjSupplementInfo supple = eduUserBiz.readSupplement(userFlow);
            model.addAttribute("supple",supple);
            EduUser eduUser = eduUserBiz.readEduUser(userFlow);
            model.addAttribute("eduUser",eduUser);
            if(supple==null){
                SysUser user = userBiz.readSysUser(userFlow);
                model.addAttribute("user",user);
            }
            return "xjgl/plat/supplementEdit";
        }
    }
    @RequestMapping(value="/editSupple")
    public String editSupple(String userFlow, Model model) throws Exception{
        if(StringUtil.isNotBlank(userFlow)){
            GydxjSupplementInfo supple = eduUserBiz.readSupplement(userFlow);
            model.addAttribute("supple",supple);
            if(supple==null){
                EduUser eduUser = eduUserBiz.readEduUser(userFlow);
                model.addAttribute("eduUser",eduUser);
                SysUser user = userBiz.readSysUser(userFlow);
                model.addAttribute("user",user);
            }
        }
        return "xjgl/plat/supplementEdit";
    }
    //保存核准补录信息
    @RequestMapping(value="/saveSuppleInfo")
    @ResponseBody
    public String saveSuppleInfo(GydxjSupplementInfo supple){
        int num=eduUserBiz.saveSuppleInfo(supple);
        if(num>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
    @RequestMapping(value="/backBatchOpt")
    @ResponseBody
    public String backBatchOpt(String [] recordLst){
        int count = 0;
        List<String> records = Arrays.asList(recordLst);
        GydxjSupplementInfo supple = new GydxjSupplementInfo();
        supple.setSubmitFlag("");
        for(int i=0;i<records.size();i++){
            supple.setUserFlow(records.get(i));
            count += eduUserBiz.saveSuppleInfo(supple);
        }
        if(count > 0){
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return  GlobalConstant.OPERATE_FAIL;
    }
}
