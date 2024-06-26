package com.pinde.sci.ctrl.gyxjgl;

import com.pinde.core.jspform.ItemGroupData;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.biz.gyxjgl.*;
import com.pinde.sci.biz.xjgl.IXjTutorBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.dao.base.EduLogMapper;
import com.pinde.sci.dao.base.EduUserMapper;
import com.pinde.sci.dao.base.PubFileMapper;
import com.pinde.sci.dao.base.TdxlEmployTutorMapper;
import com.pinde.sci.dao.gyxjgl.GyXjEmployTutorExtMapper;
import com.pinde.sci.enums.pub.UserNationEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.pub.XjImpTypeEnum;
import com.pinde.sci.enums.res.XjPartStatusEnum;
import com.pinde.sci.enums.sys.CertificateTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.MarriageTypeEnum;
import com.pinde.sci.enums.sys.PoliticsAppearanceEnum;
import com.pinde.sci.enums.gyxjgl.XjDocCategoryEnum;
import com.pinde.sci.enums.gyxjgl.XjglCourseTypeEnum;
import com.pinde.sci.form.gyxjgl.*;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.gyxjgl.*;
import org.apache.poi.hssf.usermodel.*;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/gyxjgl/user")
public class GyXjglUserCenterController extends GeneralController {
    private static Logger logger = LoggerFactory.getLogger(GyXjglUserCenterController.class);

    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IGyXjEduUserBiz eduUserBiz;
    @Autowired
    private IGyXjEduCourseBiz courseBiz;
    @Autowired
    private IGyXjEduCourseMajorBiz courseMajorBiz;
    @Autowired
    private IGyXjEduStudentCourseBiz studentCourseBiz;
    @Autowired
    private ICfgBiz cfgBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IGyXjImportRecordBiz importRecordBiz;
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
    private IGyXjTutorBiz tutorBiz;
    @Autowired
    private GyXjEmployTutorExtMapper employTutorExtMapper;
    @Autowired
    private TdxlEmployTutorMapper tdxlEmployTutorMapper;

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
                              String studyFormName,Integer currentPage, HttpServletRequest request, String from, String partId, String partStatus) {

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
        //填写学籍登记表标识
        paramMap.put("registerFlag", registerFlag);
        if(StringUtil.isNotBlank(studyFormName)){
            paramMap.put("studyFormName", Arrays.asList(studyFormName.split(",")));
        }
        List<XjEduUserInfoExt> userInfoExts = eduUserBiz.searchEduUserInfoExtBySysUser(paramMap);
        model.addAttribute("eduUserList", userInfoExts);
        if(userInfoExts != null && userInfoExts.size() > 0){
            Map<String, SysUser> userMap = new HashMap<String, SysUser>();
            Map<String, ResDoctor> resDoctorMap = new HashMap<String, ResDoctor>();
            for(XjEduUserInfoExt extInfo : userInfoExts){
                userMap.put(extInfo.getUserFlow(),extInfo.getSysUser());
                resDoctorMap.put(extInfo.getUserFlow(),extInfo.getResDoctor());
            }
            model.addAttribute("userMap", userMap);
            model.addAttribute("resDoctorMap", resDoctorMap);
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

        List<SysOrg> orgList = orgBiz.searchSysOrg();
        model.addAttribute("orgList", orgList);
        //开放学籍修改权限
        SysCfg permissionCfg = cfgBiz.read("xjgl_modify_permission");
        model.addAttribute("permissionCfg", permissionCfg);
        return "gyxjgl/plat/schoolInfo";
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
            List<PubFile> fileList = eduUserBiz.seachPubFileByFlow(userFlows);
            if (fileList != null && fileList.size() > 0) {
                Map<String, PubFile> fileMap = new HashMap<String, PubFile>();
                for (PubFile su : fileList) {
                    fileMap.put(su.getFileFlow(), su);
                }
                model.addAttribute("fileMap", fileMap);
            }
        }
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);
        model.addAttribute("deptList", deptList);
        List<SysOrg> orgList = orgBiz.searchHbresOrgList();
        model.addAttribute("orgList", orgList);
        return "gyxjgl/plat/certificateInfo";
    }

    /**
     * 学位证明统计导出(学校)
     */
    @RequestMapping("/certificateCountExport")
    public void certificateCountExport(EduUser eduUser, SysUser sysUser, ResDoctor resDoctor,
                              String startDate, String endDate, String startSid, String endSid, String startBirthday, String endBirthday, String teacherName,
                              HttpServletResponse response) throws Exception{
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
        List<Map<String, Object>> eduUserFileList = eduUserBiz.searchPubFileByEduUser(paramMap);
        if (eduUserFileList != null && eduUserFileList.size() > 0) {
            for (Map<String, Object> map : eduUserFileList) {
                String xwzs=map.get("FILE_REMARK")==null?"":(String)map.get("FILE_REMARK");
                String byzs=map.get("FILE_PATH")==null?"":(String)map.get("FILE_PATH");
                String gpzs=map.get("FILE_SUFFIX")==null?"":(String)map.get("FILE_SUFFIX");
                String zyzs=map.get("PRODUCT_TYPE")==null?"":(String)map.get("PRODUCT_TYPE");
                map.put("FILE_REMARK",StringUtil.isBlank(xwzs)?"否":"是");
                map.put("FILE_PATH",StringUtil.isBlank(byzs)?"否":"是");
                map.put("FILE_SUFFIX",StringUtil.isBlank(gpzs)?"否":"是");
                map.put("PRODUCT_TYPE",StringUtil.isBlank(zyzs)?"否":"是");
            }
            String[] titles = new String[]{
                    "PERIOD:入学年级",
                    "TRAINTYPE_NAME:学位级别",
                    "TRAIN_CATEGORY_NAME:学位类型",
                    "SID:学号",
                    "USER_NAME:姓名",
                    "MAJOR_NAME:专业名称",
                    "ORG_NAME:培养单位",
                    "FILE_REMARK:学位证书是否上传",
                    "FILE_PATH:毕业证书是否上传",
                    "FILE_SUFFIX:规培证书是否上传",
                    "PRODUCT_TYPE:执医证书是否上传"
            };
            String fileName = "证明上传情况导出.xls";
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            ExcleUtile.exportSimpleExcleByObjsAllString(titles, eduUserFileList, response.getOutputStream());
        }
    }
    /**
     * 学生成绩管理
     * @param flag 拆分成绩管理的标识
     */
    @RequestMapping(value = "/selectEduUserStudentCourseSun")
    public String selectEduUserStudentCourseSun(Integer currentPage, EduUser eduUser, SysUser sysUser, ResDoctor doctor, EduStudentCourse studentCourse,String flag,
                                                Model model, HttpServletRequest request, String from,String scoreSum,String minDegreeGrade,String maxDegreeGrade,String courseTypeScore) {
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

        List<XjEduStudentCourseExt> recordList = studentCourseBiz.searchStudentCourse(sysUser, eduUser, doctor, studentCourse,scoreSum,minDegreeGrade,maxDegreeGrade,courseTypeScore);
        model.addAttribute("recordList", recordList);

        PubImportRecord importRecord = new PubImportRecord();
        importRecord.setImpTypeId(XjImpTypeEnum.EduStudentCourse.getId());
        importRecord.setRoleFlag("admin");
        List<PubImportRecord> importRecords = importRecordBiz.searchImportRecordList(importRecord);
        model.addAttribute("importRecords", importRecords);
        if("gzykdx".equals(InitConfig.getSysCfg("xjgl_customer"))){
            model.addAttribute("infoFlag", "info");
        }
        return "gyxjgl/plat/resultManager";

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
        return "gyxjgl/plat/gradeDaoRu";
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
            }catch(RuntimeException re){
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
                            HttpServletResponse response, String from, String flag,String partId, String partStatus) throws Exception{
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
        paramMap.put("registerFlag", registerFlag);//填写学籍登记表标识
        List<XjEduUserForm> eduUserFormList = new ArrayList<XjEduUserForm>();
        List<XjEduUserInfoExt> eduUserList = eduUserBiz.searchEduUserInfoExtBySysUser(paramMap);
        if(eduUserList!=null && eduUserList.size()>0){
            for(XjEduUserInfoExt eu : eduUserList){
                XjEduUserForm eduUserForm = new XjEduUserForm();
                SysUser user = eu.getSysUser();
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
                    if("gzykdx".equals(InitConfig.getSysCfg("xjgl_customer"))){
                        if(StringUtil.isNotBlank(eu.getUserFlow())){
                            PubUserGenericContent genericContent = eduUserBiz.readPubUserGenericContent(eu.getUserFlow(),"RegistrationForm");
                            if(null != genericContent){
                                XjPollingTableForm form = eduUserBiz.parsePollingTableXml(genericContent.getContent());
                                if(form!=null){
                                    extInfoForm.setStudentSourceArea(form.getBirthPlaceProvince()+form.getBirthPlacePrefectureLevelCity()+form.getBirthPlaceCountyLevelCity());
                                }
                            }
                        }
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
        //登记信息 开放时间
        SysCfg openRegisterStartDate=cfgBiz.read("open_register_start_date");
        model.addAttribute("openRegisterStartDate", openRegisterStartDate);
        SysCfg closeRegisterEndDate=cfgBiz.read("close_register_end_date");
        model.addAttribute("closeRegisterEndDate", closeRegisterEndDate);
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
        List<SysOrg> orgList = orgBiz.searchHbresOrgList();
        model.addAttribute("orgList", orgList);
        //年级及培养层次
        SysCfg yearCfg=cfgBiz.read("xjgl_modify_year");
        model.addAttribute("yearCfg", yearCfg);
        SysCfg trainTypeCfg=cfgBiz.read("xjgl_modify_trainType");
        model.addAttribute("trainTypeCfg", trainTypeCfg);
        //开放学籍修改权限(所有Y/特定学生N)
        SysCfg permissionCfg = cfgBiz.read("xjgl_modify_permission");
        model.addAttribute("permissionCfg", permissionCfg);
        return "gyxjgl/plat/eduOption";
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
        String year = request.getParameter("yearCfg");
        String trainType = request.getParameter("trainTypeCfg");
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
        //年级及培养层次
        SysCfg yearCfg = new SysCfg();
        yearCfg.setCfgCode("xjgl_modify_year");
        yearCfg.setCfgValue(year);
        cfgList.add(yearCfg);
        SysCfg trainTypeCfg = new SysCfg();
        trainTypeCfg.setCfgCode("xjgl_modify_trainType");
        trainTypeCfg.setCfgValue(trainType);
        cfgList.add(trainTypeCfg);
        //开放修改时间权限
        String sysCfgValue=request.getParameter("xjgl_modify_permission");
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
        cfg.setCfgCode("xjgl_modify_permission");
        cfg.setCfgValue(sysCfgValue);
        cfgList.add(cfg);
        cfgBiz.save(cfgList);
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
    //成绩单打印
    @RequestMapping(value="/printYuLian")
    public String printYuLian(String userFlow,Model model) throws Exception {
        Map<String,Object> baseInfo = studentCourseBiz.queryBaseInfo(userFlow);
        List<Map<String,Object>> gradeList = studentCourseBiz.queryGradeList(userFlow);
        model.addAttribute("baseInfo",baseInfo);
        model.addAttribute("gradeList",gradeList);
        return "gyxjgl/plat/chineseScore";
    }
    //成绩单打印-选择学生
    @RequestMapping(value="/selectSidToPrint")
    public String selectSidToPrint(Model model){
        return "gyxjgl/plat/selectSidToPrint";
    }
    //成绩单打印
    @RequestMapping(value="/printYuLian4AdminOL")
    public String printYuLian4AdminOL(String startSid,String endSid,Model model) throws Exception {
        List<Map<String,Object>> dataList=new ArrayList<>();
        if(StringUtil.isNotBlank(startSid)&&StringUtil.isNotBlank(endSid)){
            EduUserExample example=new EduUserExample();
            EduUserExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            criteria.andSidBetween(startSid,endSid);
            List<EduUser> userList=eduUserMapper.selectByExample(example);
            if(userList!=null&&userList.size()>0){
                for (EduUser eu:userList) {
                    Map<String,Object> dataMap = new HashMap<>();
                    Map<String,Object> baseInfo = studentCourseBiz.queryBaseInfo(eu.getUserFlow());
                    List<Map<String,Object>> gradeList = studentCourseBiz.queryGradeList(eu.getUserFlow());
                    dataMap.put("baseInfo",baseInfo);
                    dataMap.put("gradeList",gradeList);
                    dataList.add(dataMap);
                }
            }
            model.addAttribute("dataList",dataList);
            return "gyxjgl/plat/chineseScoreOL";
        }
        return "gyxjgl/plat/chineseScoreOL";
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
//        String content = InitConfig.getSysCfg("qr_grade_search_url")+"/gyxjgl/student/resultSun?userFlow="+userFlow;
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
        //成绩单打印内容
        Map<String,Object> baseInfo = studentCourseBiz.queryBaseInfo(userFlow);
        List<Map<String,Object>> gradeList = studentCourseBiz.queryGradeList(userFlow);
        model.addAttribute("baseInfo",baseInfo);
        model.addAttribute("gradeList",gradeList);
        return "gyxjgl/plat/result";

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
            count += studentCourseBiz.save(stuCourse);
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
        return "gyxjgl/plat/editRegisterDate";
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

        return "gyxjgl/plat/impRecordList";
    }
    /**
     * 学籍信息导入
     */
    @RequestMapping(value="/importSchoolRoll")
    public String importSchoolRoll(){

        return "/gyxjgl/plat/importSchoolRoll";
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
            eduUser.setStudentSourceName(DictTypeEnum.GyStudentSource.getDictNameById(eduUser.getStudentSourceId()));
        }

        if(StringUtil.isNotBlank(eduUser.getAdmitTypeId())){
            eduUser.setAdmitTypeName(DictTypeEnum.GyAdmitType.getDictNameById(eduUser.getAdmitTypeId()));
        }
        if(StringUtil.isNotBlank(eduUser.getTrainTypeId())){
            eduUser.setTrainTypeName(DictTypeEnum.GyTrainType.getDictNameById(eduUser.getTrainTypeId()));
        }
        if(StringUtil.isNotBlank(eduUser.getMajorId())){
            eduUser.setMajorName(DictTypeEnum.GyMajor.getDictNameById(eduUser.getMajorId()));
        }
        if(StringUtil.isNotBlank(eduUser.getStudyFormId())){
            eduUser.setStudyFormName(DictTypeEnum.GyStudyForm.getDictNameById(eduUser.getStudyFormId()));
        }
        if(StringUtil.isNotBlank(eduUser.getAtSchoolStatusId())){
            eduUser.setAtSchoolStatusName(DictTypeEnum.GyAtSchoolStatus.getDictNameById(eduUser.getAtSchoolStatusId()));
        }
        if(StringUtil.isNotBlank(eduUser.getSchoolRollStatusId())){
            eduUser.setSchoolRollStatusName(DictTypeEnum.GySchoolRollStatus.getDictNameById(eduUser.getSchoolRollStatusId()));
        }
        if(StringUtil.isNotBlank(eduUser.getRecruitSeasonId())){
            eduUser.setRecruitSeasonName(DictTypeEnum.GyRecruitSeason.getDictNameById(eduUser.getRecruitSeasonId()));
        }
        if(StringUtil.isNotBlank(eduUser.getTrainCategoryId())){
            eduUser.setTrainCategoryName(DictTypeEnum.GyTrainCategory.getDictNameById(eduUser.getTrainCategoryId()));
        }
        if(StringUtil.isNotBlank(eduUser.getIsExceptionalId())){
            eduUser.setIsExceptionalName(DictTypeEnum.GyIsExceptional.getDictNameById(eduUser.getIsExceptionalId()));
        }
        if(StringUtil.isNotBlank(eduUser.getIsRecommendId())){
            eduUser.setIsRecommendName(DictTypeEnum.GyIsRecommend.getDictNameById(eduUser.getIsRecommendId()));
        }
        if(StringUtil.isNotBlank(extInfoForm.getFirstEducationContentId())){
            extInfoForm.setFirstEducationContentName(DictTypeEnum.GyFirstEducation.getDictNameById(extInfoForm.getFirstEducationContentId()));
        }
        if(StringUtil.isNotBlank(eduUser.getClassId())){
            eduUser.setClassName(DictTypeEnum.GyXjClass.getDictNameById(eduUser.getClassId()));
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
            eduUser.setAwardDegreeCategoryName(DictTypeEnum.GyDegreeCategory.getDictNameById(eduUser.getAwardDegreeCategoryId()));
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

        int result=eduUserBiz.save(eduUserForm);

        if("gzykdx".equals(InitConfig.getSysCfg("xjgl_customer"))){
            if(StringUtil.isNotBlank(user.getUserFlow())){//更新以下信息到登记表
                PubUserGenericContent generic = eduUserBiz.readPubUserGenericContent(user.getUserFlow(),"RegistrationForm");
                if(null != generic) {
                    XjPollingTableForm form = eduUserBiz.parsePollingTableXml(generic.getContent());
                    if(!GlobalConstant.RES_ROLE_SCOPE_DOCTOR.equals(roleFlag)){
                        form.setStudentId(eduUserForm.getEduUser().getSid());
                        form.setUserName(eduUserForm.getSysUser().getUserName());
                        form.setNameSpell(eduUserForm.getEduUser().getNameSpell());
                        form.setSexName(eduUserForm.getSysUser().getSexId());
                        form.setBirthDate(eduUserForm.getSysUser().getUserBirthday());
                        form.setNation(eduUserForm.getSysUser().getNationName());
                        form.setIdNumber(eduUserForm.getSysUser().getIdNo());
                    }
                    form.setPoliticalOutlook(eduUserForm.getSysUser().getPoliticsStatusName());
                    form.setSpecialty(eduUserForm.getEduUserExtInfoForm().getSpeciality());
                    form.setHomePhone(eduUserForm.getEduUserExtInfoForm().getHomePhone());
                    form.setNativePlaceProvince(eduUserForm.getSysUser().getNativePlaceProvName());
                    form.setNativePlaceProvinceId(eduUserForm.getSysUser().getNativePlaceProvId());
                    form.setNativePlaceCity(eduUserForm.getSysUser().getNativePlaceCityName());
                    form.setNativePlaceCityId(eduUserForm.getSysUser().getNativePlaceCityId());
                    form.setNativePlaceArea(eduUserForm.getSysUser().getNativePlaceAreaName());
                    form.setNativePlaceAreaId(eduUserForm.getSysUser().getNativePlaceAreaId());
                    form.setStudentSourceArea(eduUserForm.getEduUserExtInfoForm().getStudentSourceArea());//生源省市
                    form.setHomeAddress(eduUserForm.getEduUserExtInfoForm().getNowResideAddress());//现家庭住址
                    form.setPostCode(eduUserForm.getEduUserExtInfoForm().getPostCode());//邮政编码
                    form.setMobilePhone(eduUserForm.getSysUser().getUserPhone());//本人手机号码
                    form.setStudentSourceArea(eduUserForm.getEduUserExtInfoForm().getStudentSourceArea());//生源地
                    form.setStudentSourceAreaId(eduUserForm.getEduUserExtInfoForm().getStudentSourceAreaId());

                    eduUserBiz.save(user.getUserFlow(),form);
                }
            }
        }
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
                String gradeName=DictTypeEnum.GyRecruitSeason.getDictNameById(eduStudentCourse.getGradeTermId());
                eduStudentCourse.setGradeTermName(gradeName);
                if(StringUtil.isBlank(eduStudentCourse.getGradeTermId())){
                    eduStudentCourse.setGradeTermId("");
                    eduStudentCourse.setGradeTermName("");
                }
                if(StringUtil.isNotBlank(eduStudentCourse.getStudyWayId())){
                    eduStudentCourse.setStudyWayName(DictTypeEnum.GyXjStudyWay.getDictNameById(eduStudentCourse.getStudyWayId()));
                }else{
                    eduStudentCourse.setStudyWayName("");
                }
                if(StringUtil.isNotBlank(eduStudentCourse.getAssessTypeId())){
                    eduStudentCourse.setAssessTypeName(DictTypeEnum.GyXjEvaluationMode.getDictNameById(eduStudentCourse.getAssessTypeId()));
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
                studentCourseBiz.save(eduStudentCourse);
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
                    List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("GyXjIsPassed");
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
            String eName = PyUtil.getUpEname(sysUser.getUserName());
            dataMap.put("eName", eName);
            String id = eduUser.getMajorId();
            String eMajorName = dictBiz.readDict(DictTypeEnum.GyMajor.getId(),id).getDictDesc();
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
            path="/jsp/gyxjgl/print/eGrade2.docx";//英文模板
            name= "school report.docx";
        }
        if(GlobalConstant.FLAG_N.equals(printFlag)){
            path="/jsp/gyxjgl/print/grade.docx";//中文模板
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
            eduStudentCourse.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            int result=0;
//            result=studentCourseBiz.save(eduStudentCourse);
            result=studentCourseBiz.emptyCourse(eduStudentCourse);
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
     * @param flow
     * @param flag
     * @param edu
     * @return
     * @throws Exception
     */
    @RequestMapping("/eduUserFormEdit/{roleFlag}")
    public String edit(@PathVariable String roleFlag,Model model,String flow,String flag,String qrztFlag,EduUser edu,boolean isFeeMaster) throws Exception{
        String studyForm="";
        model.addAttribute("isFeeMaster",isFeeMaster);
        SysUser user=GlobalContext.getCurrentUser();
        String userFlow="";
        if(StringUtil.isNotBlank(flow)){
            userFlow=flow;
        }else{
            if(!flag.equals("Y")){
                userFlow=user.getUserFlow();
            }
        }
        if(StringUtil.isNotBlank(userFlow)){
            EduUser eduUser=eduUserBiz.findByFlow(userFlow);
            if(eduUser!=null){
                studyForm=eduUser.getStudyFormName();
                model.addAttribute("eduUser", eduUser);
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

        String orgFlow=user.getOrgFlow();
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
        List<SysOrg> orgList = orgBiz.searchSysOrg();
        model.addAttribute("orgList", orgList);
        //年级及培养层次
        SysCfg yearCfg=cfgBiz.read("xjgl_modify_year");
        model.addAttribute("yearCfg", yearCfg);
        SysCfg trainTypeCfg=cfgBiz.read("xjgl_modify_trainType");
        model.addAttribute("trainTypeCfg", trainTypeCfg);
        //开放学籍修改权限(所有Y/特定学生N)
        SysCfg permissionCfg = cfgBiz.read("xjgl_modify_permission");
        model.addAttribute("permissionCfg", permissionCfg);
        model.addAttribute("roleFlag",roleFlag);
        if("同等学力申请硕士学位".equals(studyForm)){
            return "gyxjgl/plat/interMaintainAdminTDXL";
        }
        return "gyxjgl/plat/interMaintainAdminOfGz";
    }

    /**
     * 学位证明-查看学籍信息
     */
    @RequestMapping("/userCertificateInfo")
    public String userCertificateInfo(Model model,String userFlow, EduUser edu) throws Exception{
        String studyForm="";
        if(StringUtil.isNotBlank(userFlow)){
            EduUser eduUser=eduUserBiz.findByFlow(userFlow);
            if(eduUser!=null){
                studyForm=eduUser.getStudyFormName();
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
        if("同等学力申请硕士学位".equals(studyForm)){
            return "gyxjgl/plat/maintenanceTDXL";
        }
        if("gzykdx".equals(InitConfig.getSysCfg("xjgl_customer"))){
            return "gyxjgl/plat/maintenanceOfGz";//广州医科大学学籍信息
        }
        return "gyxjgl/plat/maintenanceInfo";//南方医科大学学籍信息
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
        return "gyxjgl/student/userBaseInfo";
    }

    //查看证明
    @RequestMapping("/certificateDetail")
    public String certificateDetail(Model model,String certificateType,String userFlow,String roleFlag) throws Exception {
        String zssj = "";//证书授予时间
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
                XjEduUserExtInfoForm extInfoForm = eduUserBiz.convertXmlToExtForm(content);
                certInfo.put("medicine",extInfoForm.getAwardSubjectCategory());
                String userName = (String)certInfo.get("userName");
                if(StringUtil.isNotBlank(userName)){
                    userName = PyUtil.getUpEnameCH(userName);//处理中文名(1-4)到拼写英文名转变
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
                    if(StringUtil.isNotBlank((String)certInfo.get("period"))){
                        model.addAttribute("studyYear",Integer.parseInt(graduateYear.substring(0,4))-Integer.parseInt((String)certInfo.get("period")));
                    }
                }
                if(StringUtil.isNotBlank((String)certInfo.get("degreeLevelId"))){
                    List<SysDict> list=DictTypeEnum.GyXjDegreeLevel.getSysDictList();
                    if(list!=null&&list.size()>0){
                        for (SysDict sd:list) {
                            if(((String)certInfo.get("degreeLevelId")).equals(sd.getDictId())){
                                model.addAttribute("levelEn",sd.getDictDesc());
                            }
                        }
                    }
                }
                if("1".equals(certificateType)){
                    zssj= (String)certInfo.get("awardDegreeTime");
                }
                if("2".equals(certificateType)){
                    zssj= (String)certInfo.get("graduateTime");
                }
            }
            String currDate = new SimpleDateFormat("yyyyMMdd").format(StringUtil.isBlank(zssj)?new Date():DateUtil.parseDate(zssj,"yyyy-MM-dd"));
            model.addAttribute("currYear",currDate.substring(0,4));
            model.addAttribute("currMonth",Integer.valueOf(currDate.substring(4,6)));
            model.addAttribute("currDay",Integer.valueOf(currDate.substring(6,8)));
            model.addAttribute("lastNumOfcurrDay",currDate.substring(7,8));
        }
        return "gyxjgl/plat/certificateDetail";
    }

    //导出证明
    @RequestMapping("/exportWord")
    public void exportWord(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map dataMap = getParameterMap(request);
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        String name = "学位证明.docx";
        String path = "/jsp/gyxjgl/print/degreeCertTemeplete.docx";//学位模板
        if (dataMap.get("certificateType").equals("2")) {
            name = "毕业证明.docx";
            path = "/jsp/gyxjgl/print/graduateCertTemeplete.docx";//毕业模板
        }
        if (dataMap.get("certificateType").equals("3")||dataMap.get("certificateType").equals("4")) {
            name = "规培证明.docx";
            if(dataMap.get("certificateType").equals("4")){
                name = "执医证明.docx";
            }
            path = "/jsp/gyxjgl/print/3And4TypeTemeplete.docx";//规培和执医模板
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
            if("3".equals(dataMap.get("certificateType")) && StringUtil.isNotBlank(pf.getFileSuffix())){
                certImgPath+=pf.getFileSuffix();
                value = "<img src='"+certImgPath+"' width='440' height='315'  alt='规培证照'/>";
            }
            if("4".equals(dataMap.get("certificateType")) && StringUtil.isNotBlank(pf.getProductType())){
                certImgPath+=pf.getProductType();
                value = "<img src='"+certImgPath+"' width='440' height='315'  alt='执医证照'/>";
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
        return "gyxjgl/plat/userOperLog";
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
        return "gyxjgl/plat/partStatusInfo";
    }
    @RequestMapping("/expExcel")
    public void expExcel(EduUser eduUser,SysUser sysUser,ResDoctor doctor,EduStudentCourse studentCourse,String from,String scoreSum,String minDegreeGrade,String maxDegreeGrade,String courseTypeScore,HttpServletResponse response)throws Exception{
        SysUser user = GlobalContext.getCurrentUser();
        if (GlobalConstant.USER_LIST_LOCAL.equals(from)) {
            //培养单位
            doctor.setOrgFlow(user.getOrgFlow());
        } else if (GlobalConstant.USER_LIST_CHARGE.equals(from)) {
            //分委会
            eduUser.setTrainOrgId(user.getDeptFlow());
        }
        System.out.println("==============="+from);
        List<XjEduStudentCourseExt> recordList = studentCourseBiz.searchStudentCourse(sysUser,eduUser,doctor,studentCourse,scoreSum,minDegreeGrade,maxDegreeGrade,courseTypeScore);
        String fileName = "学员成绩单.xls";
        if("gzykdx".equals(InitConfig.getSysCfg("xjgl_customer"))){
            createExcleOfGz(response, fileName, recordList);
        }else{
            createExcle(response, fileName, recordList);
        }
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
                "normalGrade:平时成绩",
                "termGrade:期末成绩",
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
                "normalGrade:平时成绩",
                "termGrade:期末成绩",
                "courseGrade:成绩"
        };
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("Application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, dataList,response.getOutputStream(),new String []{"2","5"});
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
        return "gyxjgl/plat/ideologyManage";
    }
    @RequestMapping("/patronageRegister")
    public String patronageRegister(Integer currentPage, Model model) {
        return "gyxjgl/plat/patronageRegister";
    }
    @RequestMapping(value = "/patronageExcel")
    public void patronageExcel(HttpServletResponse response)throws Exception {
        String[] titles = new String[]{
                "courseSession:年级",
                "courseCode:姓名",
                "courseName:专业",
                "gradationName:班级",
                "courseTypeName:任免情况",
                "courseUserCount:备注"
        };
        String fileName = "任免登记.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, null, response.getOutputStream());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
    @RequestMapping("/studentManage")
    public String studentManage(String from, Integer currentPage, Model model) {
        return "gyxjgl/plat/studentManage";
    }
    @RequestMapping("/anonymousReview")
    public String anonymousReview(Model model) {
        return "gyxjgl/plat/anonymousReview";
    }
    @RequestMapping("/degreeManage")
    public String degreeManage(Integer currentPage, Model model) {
        return "gyxjgl/plat/degreeManage";
    }
    @RequestMapping("/degreeRegister")
    public String degreeRegister(Model model) {
        return "gyxjgl/plat/degreeRegister";
    }
    @RequestMapping("/degreeUpload")
    public String degreeUpload(Model model) {
        return "gyxjgl/plat/degreeUpload";
    }
    @RequestMapping(value = "/degreeExcel")
    public void degreeExcel(HttpServletResponse response)throws Exception {
        String[] titles = new String[]{
                "courseSession:序号",
                "courseCode:年级",
                "courseName:姓名",
                "gradationName:专业",
                "courseTypeName:导师",
                "courseUserCount:状态",
                "recruit:考核成绩",
                "recrdduit:获得学位"
        };
        String fileName = "学位管理.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, null, response.getOutputStream());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
    @RequestMapping("/clarkManage")
    public String clarkManage(Integer currentPage, Model model) {
        return "gyxjgl/plat/clarkManage";
    }
    @RequestMapping("/clarkRegister")
    public String clarkRegister(Model model) {
        return "gyxjgl/plat/clarkRegister";
    }
    @RequestMapping(value = "/clarkExcel")
    public void clarkExcel(HttpServletResponse response)throws Exception {
        String[] titles = new String[]{
                "courseSession:序号",
                "courseCode:年级",
                "courseName:姓名",
                "gradationName:专业",
                "courseTypeName:课程名称",
                "courseUserCount:授课老师",
                "recruit:签到情况"
        };
        String fileName = "课勤管理.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, null, response.getOutputStream());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
    @RequestMapping("/equivalentEducation")
    public String equivalentEducation(Integer currentPage, Model model) {
        return "gyxjgl/plat/equivalentEducation";
    }
    @RequestMapping("/educationRegister")
    public String educationRegister(Model model) {
        return "gyxjgl/plat/educationRegister";
    }
    @RequestMapping(value = "/educationExcel")
    public void educationExcel(HttpServletResponse response)throws Exception {
        String[] titles = new String[]{
                "courseSession:序号",
                "courseCode:年级",
                "courseName:姓名",
                "gradationName:专业",
                "courseTypeName:导师",
                "courseUserCount:二级单位",
                "recruit:录取状态",
                "grade:成绩",
                "degreeeName:获得学位"
        };
        String fileName = "同等学力教育.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, null, response.getOutputStream());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
    //查询学籍登记信息
    @RequestMapping("/schoolRegister")
    public String schoolRegister(String userFlow,String roleFlag, Model model){
        model.addAttribute("roleFlag",roleFlag);
        if(StringUtil.isBlank(userFlow)){
            userFlow = GlobalContext.getCurrentUser().getUserFlow();
        }
        //登记信息 开放时间
        SysCfg openRegisterStartDate=cfgBiz.read("open_register_start_date");
        model.addAttribute("openRegisterStartDate", openRegisterStartDate);
        SysCfg closeRegisterEndDate=cfgBiz.read("close_register_end_date");
        model.addAttribute("closeRegisterEndDate", closeRegisterEndDate);
        //开放学籍修改权限(所有Y/特定学生N)
        SysCfg permissionCfg = cfgBiz.read("xjgl_modify_permission");
        model.addAttribute("permissionCfg", permissionCfg);
        EduUser eduUser = eduUserBiz.readEduUser(userFlow);
        model.addAttribute("eduUser",eduUser);
        PubUserGenericContent genericContent = eduUserBiz.readPubUserGenericContent(userFlow,"RegistrationForm");
        if(null != genericContent) {
            XjPollingTableForm form = eduUserBiz.parsePollingTableXml(genericContent.getContent());
            model.addAttribute("resume", form);
        }
        //加载专业字典
        SysDict sd=new SysDict();
        sd.setDictTypeId("GyMajor");
        sd.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysDict> majorList=dictBiz.searchDictList(sd);
        model.addAttribute("dictTypeEnumGyMajorList", majorList);
        return "gyxjgl/plat/schoolRegister";
    }
    //保存学籍登记信息
    @RequestMapping(value="/saveSchoolRegister")
    @ResponseBody
    public String saveSchoolRegister(String userFlow, XjPollingTableForm form) throws Exception {
        int result = eduUserBiz.save(userFlow,form);
        if(result!=GlobalConstant.ZERO_LINE){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
    //上传学籍登记表照片
    @RequestMapping(value="/uploadImage")
    @ResponseBody
    public String uploadImage(MultipartFile headImg){
        if(null != headImg && headImg.getSize() > 0){
            return eduUserBiz.uploadImage(GlobalContext.getCurrentUser().getUserFlow(),headImg);
        }
        return GlobalConstant.UPLOAD_FAIL;
    }
    //自主学习平台跳转
    @RequestMapping("/autonomicLearning")
    public String autonomicLearning(Model model){
        SysUser user =GlobalContext.getCurrentUser();
        SysUserRole userRole = new SysUserRole();
        userRole.setUserFlow(user.getUserFlow());
        userRole.setWsId(GlobalConstant.NJMUEDU_WS_ID);
        List<SysUserRole> userRoleList = roleBiz.searchUserRole(userRole);
        List<String> roleFlowList=new ArrayList<String>();
        if(userRoleList != null && !userRoleList.isEmpty()){
            for(SysUserRole sysUserRole:userRoleList){
                roleFlowList.add(sysUserRole.getRoleFlow());
            }
            GlobalContext.getSession().setAttribute(GlobalConstant.CURRENT_ROLE_LIST,roleFlowList);
            userRole = userRoleList.get(0);
            if(userRole != null){
                SysRole role = roleBiz.read(userRole.getRoleFlow());
                if(role != null){
                    this.setSessionAttribute(GlobalConstant.CURRENT_USER, user);
                    this.setSessionAttribute(GlobalConstant.CURRENT_VIEW, role.getRegPageId());
                    EduUser eduUser = this.eduUserBiz.readEduUser(user.getUserFlow());
                    this.setSessionAttribute(GlobalConstant.CURR_NJMUEDU_USER, eduUser);
                    return "redirect:/"+ role.getRegPageId();
                }
            }
        }else{
            model.addAttribute("message","未授权登录！");
            return "inx/njmuedu/index";
        }
        return "inx/njmuedu/index";
    }

    @RequestMapping("/printXjTbl")
    public String printXjTbl(String userFlow,String educationType,Model model){
        model.addAttribute("educationType",educationType);
        if(StringUtil.isNotBlank(userFlow)){
            PubUserGenericContent genericContent = eduUserBiz.readPubUserGenericContent(userFlow,"RegistrationForm");
            if(null != genericContent) {
                XjPollingTableForm form = eduUserBiz.parsePollingTableXml(genericContent.getContent());
                model.addAttribute("resume", form);
            }
        }
        return "gyxjgl/plat/printXjTbl";
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
        return "gyxjgl/plat/printInterMaintainAdmin";//南方医科大学学籍信息
    }

    /**
     * 学生成绩查询
     * @param
     */
    @RequestMapping(value = "/eduUserStudentCourseList")
    public String eduUserStudentCourseList(Integer currentPage, ResDoctor doctor, EduStudentCourse studentCourse,String flag,
                                                Model model, HttpServletRequest request, String from,String scoreSum,String minDegreeGrade,String maxDegreeGrade,String courseTypeScore) {
        SysUser user = GlobalContext.getCurrentUser();
        EduUser eduUser=eduUserBiz.readEduUser(user.getUserFlow());
        if(eduUser==null){
            eduUser=new EduUser();
        }
        List<XjEduStudentCourseExt> recordCountList = studentCourseBiz.searchStudentCourse(user, eduUser, doctor, studentCourse,scoreSum,minDegreeGrade,maxDegreeGrade,courseTypeScore);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<XjEduStudentCourseExt> recordList = studentCourseBiz.searchStudentCourse(user, eduUser, doctor, studentCourse,scoreSum,minDegreeGrade,maxDegreeGrade,courseTypeScore);
        SysCfg chooseCourseStartTimeCfg = cfgBiz.read("choose_course_start_time");
        SysCfg chooseCourseEndTimeCfg = cfgBiz.read("choose_course_end_time");
        model.addAttribute("chooseCourseStartTimeCfg", chooseCourseStartTimeCfg);
        model.addAttribute("chooseCourseEndTimeCfg", chooseCourseEndTimeCfg);
        model.addAttribute("recordList", recordList);
        model.addAttribute("recordCountList", recordCountList);
        model.addAttribute("user", user);
        model.addAttribute("currentPage", currentPage);
        return "gyxjgl/student/studentResultOfGz";
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
        return "gyxjgl/plat/graduationInfors";
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
        return "gyxjgl/plat/resultCount";

    }

    //导出学籍表
    @RequestMapping("/exportXjTbl")
    public void exportXjTbl(String userFlow, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(StringUtil.isNotBlank(userFlow)) {
            PubUserGenericContent genericContent = eduUserBiz.readPubUserGenericContent(userFlow, "RegistrationForm");
            if (null != genericContent) {
                XjPollingTableForm form = eduUserBiz.parsePollingTableXml(genericContent.getContent());
                form.setDegreeType("major".equals(form.getDegreeType())?"专业学位":"学术学位");
                form.setMarriage("Y".equals(form.getMarriage())?"是":"否");
                form.setHkInSchool("Y".equals(form.getHkInSchool())?"是":"否");
                form.setHkType("Y".equals(form.getHkType())?"农业":"非农业");
                form.setWhetherCurrent("Y".equals(form.getWhetherCurrent())?"是":"否");
                form.setCultureType("Y".equals(form.getCultureType())?"定向":"非定向");
                form.setOverseasChinese("Y".equals(form.getOverseasChinese())?"是":"否");
                Map dataMap = convertBean(form);
                WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
                String name = form.getStudentId()+form.getUserName()+".docx";
                String path = "/jsp/gyxjgl/print/masterTblTemp.docx";
                if ("doctor".equals(form.getEducationType())) {
                    path = "/jsp/gyxjgl/print/doctorTblTemp.docx";
                }
                String photoPath = InitConfig.getSysCfg("upload_base_url")+"/"+form.getHeadImgUrl();
                String value = "<img src='" + photoPath + "' width='75' height='70'/>";
                dataMap.put("photo", value);
                //学历经历
                for(int i=0;i<form.getEducationList().size();i++){
                    XjPollingTableEducationForm edu = form.getEducationList().get(i);
                    dataMap.put("jlBeginAndEndDate"+i,edu.getBeginAndEndDate());
                    dataMap.put("jlSchoolAndUnit"+i,edu.getSchoolAndUnit());
                    dataMap.put("jlPostName"+i,edu.getPostName());
                    dataMap.put("jlUnitAddress"+i,edu.getUnitAddress());
                }
                //家庭成员
                for(int i=0;i<form.getFamilyList().size();i++){
                    XjPollingTableFamilyForm edu = form.getFamilyList().get(i);
                    dataMap.put("jtName"+i,edu.getName());
                    dataMap.put("jtAge"+i,edu.getAge());
                    dataMap.put("jtRelation"+i,edu.getRelation());
                    dataMap.put("jtPolitics"+i,edu.getPolitics());
                    dataMap.put("jtWorkUnit"+i,edu.getWorkUnit());
                    dataMap.put("jtPostName"+i,edu.getPostName());
                    dataMap.put("jtUnitAddress"+i,edu.getUnitAddress());
                    dataMap.put("jtMobilePhone"+i,edu.getMobilePhone());
                }
                //学籍异动
                for(int i=0;i<form.getChangeList().size();i++){
                    XjPollingTableChangeForm edu = form.getChangeList().get(i);
                    dataMap.put("ydYear"+i,edu.getYear());
                    dataMap.put("ydMonth"+i,edu.getMonth());
                    dataMap.put("ydDay"+i,edu.getDay());
                    dataMap.put("ydChangeItem"+i,edu.getChangeItem());
                    dataMap.put("ydTermYear"+i,edu.getTermYear());
                    dataMap.put("ydReason"+i,edu.getReason());
                    dataMap.put("ydMemo"+i,edu.getMemo());
                }
                //奖惩情况
                for(int i=0;i<form.getBonusPenaltyList().size();i++){
                    XjPollingTableBonusPenaltyForm edu = form.getBonusPenaltyList().get(i);
                    dataMap.put("jcDate"+i,edu.getDate());
                    dataMap.put("jcLevel"+i,edu.getLevel());
                    dataMap.put("jcName"+i,edu.getName());
                    dataMap.put("jcUnit"+i,edu.getUnit());
                    dataMap.put("jcReason"+i,edu.getReason());
                    dataMap.put("jcMemo"+i,edu.getReason());
                }
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
        }
    }
    //批量 导出学籍表
    @RequestMapping("/batchExpXjTbl")
    public void batchExpXjTbl(EduUser eduUser, SysUser sysUser, ResDoctor resDoctor, String registerFlag, String startDate, String endDate,
                              String startSid, String endSid, String startBirthday, String endBirthday, String teacherName,String from,
                              String partId, String partStatus,HttpServletRequest request, HttpServletResponse response) throws Exception {
        //下载目录
        String dir =System.getProperty("java.io.tmpdir")+File.separator+PkUtil.getUUID();
        File dirFile = new File(dir);
        if(dirFile.exists()==false){
            dirFile.mkdirs();
        }
        SysUser user = GlobalContext.getCurrentUser();
        if (GlobalConstant.USER_LIST_LOCAL.equals(from)) {
            //培养单位
            resDoctor.setOrgFlow(user.getOrgFlow());
        } else if (GlobalConstant.USER_LIST_CHARGE.equals(from)) {
            //分委会
            eduUser.setTrainOrgId(user.getDeptFlow());
        }
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
        //填写学籍登记表标识
        paramMap.put("registerFlag", GlobalConstant.FLAG_Y);
//        List<XjEduUserInfoExt> userInfoExts = eduUserBiz.searchEduUserInfoExtBySysUser(paramMap);
        List<XjEduUserInfoExt> userInfoExts = eduUserBiz.searchXjbData(paramMap);
        if(null != userInfoExts && userInfoExts.size()>0){
            for (XjEduUserInfoExt ext : userInfoExts) {
                if (StringUtil.isNotBlank(ext.getGenericContent())) {
                    XjPollingTableForm form = eduUserBiz.parsePollingTableXml(ext.getGenericContent());
                    form.setDegreeType("major".equals(form.getDegreeType()) ? "专业学位" : "学术学位");
                    form.setSexName("Man".equals(form.getSexName()) ? "男" : "女");
                    form.setMarriage("Y".equals(form.getMarriage()) ? "是" : "否");
                    form.setHkInSchool("Y".equals(form.getHkInSchool()) ? "是" : "否");
                    form.setHkType("Y".equals(form.getHkType()) ? "农业" : "非农业");
                    form.setWhetherCurrent("Y".equals(form.getWhetherCurrent()) ? "是" : "否");
                    form.setCultureType("Y".equals(form.getCultureType()) ? "定向" : "非定向");
                    form.setOverseasChinese("Y".equals(form.getOverseasChinese()) ? "是" : "否");
                    Map dataMap = convertBean(form);
                    WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
                    String name = form.getStudentId()+form.getUserName() + ".docx";
                    String path = "/jsp/gyxjgl/print/masterTblTemp.docx";
                    if ("doctor".equals(form.getEducationType())) {
                        path = "/jsp/gyxjgl/print/doctorTblTemp.docx";
                    }
                    String photoPath = InitConfig.getSysCfg("upload_base_url")+"/"+form.getHeadImgUrl();
                    String value = "<img src='" + photoPath + "' width='75' height='70'/>";
                    dataMap.put("photo", value);
                    //学历经历
                    for (int i = 0; i < form.getEducationList().size(); i++) {
                        XjPollingTableEducationForm edu = form.getEducationList().get(i);
                        dataMap.put("jlBeginAndEndDate" + i, edu.getBeginAndEndDate());
                        dataMap.put("jlSchoolAndUnit" + i, edu.getSchoolAndUnit());
                        dataMap.put("jlPostName" + i, edu.getPostName());
                        dataMap.put("jlUnitAddress" + i, edu.getUnitAddress());
                    }
                    //家庭成员
                    for (int i = 0; i < form.getFamilyList().size(); i++) {
                        XjPollingTableFamilyForm edu = form.getFamilyList().get(i);
                        dataMap.put("jtName" + i, edu.getName());
                        dataMap.put("jtAge" + i, edu.getAge());
                        dataMap.put("jtRelation" + i, edu.getRelation());
                        dataMap.put("jtPolitics" + i, edu.getPolitics());
                        dataMap.put("jtWorkUnit" + i, edu.getWorkUnit());
                        dataMap.put("jtPostName" + i, edu.getPostName());
                        dataMap.put("jtUnitAddress" + i, edu.getUnitAddress());
                        dataMap.put("jtMobilePhone" + i, edu.getMobilePhone());
                    }
                    //学籍异动
                    for (int i = 0; i < form.getChangeList().size(); i++) {
                        XjPollingTableChangeForm edu = form.getChangeList().get(i);
                        dataMap.put("ydYear" + i, edu.getYear());
                        dataMap.put("ydMonth" + i, edu.getMonth());
                        dataMap.put("ydDay" + i, edu.getDay());
                        dataMap.put("ydChangeItem" + i, edu.getChangeItem());
                        dataMap.put("ydTermYear" + i, edu.getTermYear());
                        dataMap.put("ydReason" + i, edu.getReason());
                        dataMap.put("ydMemo" + i, edu.getMemo());
                    }
                    //奖惩情况
                    for (int i = 0; i < form.getBonusPenaltyList().size(); i++) {
                        XjPollingTableBonusPenaltyForm edu = form.getBonusPenaltyList().get(i);
                        dataMap.put("jcDate" + i, edu.getDate());
                        dataMap.put("jcLevel" + i, edu.getLevel());
                        dataMap.put("jcName" + i, edu.getName());
                        dataMap.put("jcUnit" + i, edu.getUnit());
                        dataMap.put("jcReason" + i, edu.getReason());
                        dataMap.put("jcMemo" + i, edu.getMemo());
                    }
                    ServletContext context = request.getServletContext();
                    temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, null, true);
                    if (temeplete != null) {
                        File file = new File(dir+File.separator+name);
                        temeplete.save(file);
                    }
                }
            }
        }
        ZipUtil.makeDirectoryToZip(dirFile, new File(dir+".zip"), "", 7);
        String fileName = "学籍表信息.zip";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        byte[] data = getByte(new File(dir+".zip"));
        if (data != null) {
            outputStream.write(data);
        }
        outputStream.flush();
        outputStream.close();
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
    public static Map convertBean(Object bean) throws Exception {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }
    //查询同等学力聘请导师信息
    @RequestMapping("/employTutorTable")
    public String employTutorTable(String userFlow,String roleFlag,String viewFlag, Model model){
        SysUser user = GlobalContext.getCurrentUser();
        TdxlEmployTutor employTutor = new TdxlEmployTutor();
        if(StringUtil.isBlank(userFlow)){
            userFlow = GlobalContext.getCurrentUser().getUserFlow();
            model.addAttribute("user",user);
        }
        employTutor.setUserFlow(userFlow);
        EduUser eduUser = eduUserBiz.readEduUser(userFlow);
        model.addAttribute("eduUser",eduUser);
        List<TdxlEmployTutor> dataList=eduUserBiz.searchEmployTutorList(employTutor);
        if(dataList!=null&&dataList.size()>0){
            model.addAttribute("resume",dataList.get(0));
        }
        model.addAttribute("roleFlag",roleFlag);
        model.addAttribute("viewFlag",viewFlag);
        return "gyxjgl/plat/tdxlApplication";
    }
    //查询同等学力聘请导师信息
    @RequestMapping("/employTutorTableList")
    public String employTutorTableList(String roleFlag,String studentId,String userName,Integer currentPage,HttpServletRequest request, Model model){
        Map<String,String> map =new HashMap<>();
        map.put("userName",userName);
        map.put("studentId",studentId);
        if("tutor".equals(roleFlag)){
            map.put("tutorFlow",GlobalContext.getCurrentUser().getUserFlow());
        }
        if("pydw".equals(roleFlag)){
            map.put("tutorAuditId","Passed");
            map.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        }
        if("xx".equals(roleFlag)){
            map.put("orgAuditId","Passed");
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String,String>> dataList=employTutorExtMapper.queryEmployTutorList(map);
        model.addAttribute("dataList",dataList);
        model.addAttribute("roleFlag",roleFlag);
        return "gyxjgl/plat/tdxlApplicationList";
    }
    /**
     * 审核操作
     */
    @RequestMapping("/employAuditOpt")
    @ResponseBody
    public String employAuditOpt(String roleFlag,String statusId,TdxlEmployTutor employTutor){
        int num = eduUserBiz.auditOption(roleFlag,statusId,employTutor);
        if (num>0) {
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }
    //保存同等学力聘请导师申请表
    @RequestMapping(value="/saveEmployTutor")
    @ResponseBody
    public String saveEmployTutor(String roleFlag, TdxlEmployTutor employTutor){
        if("doctor".equals(roleFlag)){
            employTutor.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        }
        if("Man".equals(employTutor.getSexId())){
            employTutor.setSexName("男");
        }else if("Woman".equals(employTutor.getSexId())){
            employTutor.setSexName("女");
        }
        if("Man".equals(employTutor.getTutorSexId())){
            employTutor.setTutorSexName("男");
        }else if("Woman".equals(employTutor.getTutorSexId())){
            employTutor.setTutorSexName("女");
        }
        int num=eduUserBiz.saveEmployTutorTable(employTutor);
        if(num>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
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
            return "gyxjgl/plat/supplementList";
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
            return "gyxjgl/plat/supplementEdit";
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
        return "gyxjgl/plat/supplementEdit";
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
    @RequestMapping(value = "/insertStuGrade")
    public String insertStuGrade(String stuNo,String userName,Integer currentPage,HttpServletRequest request,Model model){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("stuNo", stuNo);
        paramMap.put("userName", userName);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<GydxjInsertGrade> gradeList = eduUserBiz.searchInsertGrade(paramMap);
        model.addAttribute("gradeList",gradeList);
        return "gyxjgl/plat/insertGradeList";
    }
    @RequestMapping(value="/editInsertGrade")
    public String editInsertGrade(String stuNo, Model model) throws Exception{
        if(StringUtil.isNotBlank(stuNo)){
            GydxjInsertGrade baseInfo = eduUserBiz.readInsertGrade(stuNo);
            model.addAttribute("baseInfo",baseInfo);
            List<GydxjInsertDetail> gradeList = eduUserBiz.searchListByStuNo(stuNo);
            model.addAttribute("gradeList",gradeList);
        }
        return "gyxjgl/plat/insertGradeEdit";
    }
    @RequestMapping("/saveInsertGrade")
    @ResponseBody
    public String saveInsertGrade(InsertGradeForm form){
        int num = eduUserBiz.saveInsertGrade(form);
        if (num > 0) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
    @RequestMapping(value="/printInsertGrade")
    public String printInsertGrade(String stuNo,Model model) throws Exception {
        GydxjInsertGrade baseInfo = eduUserBiz.readInsertGrade(stuNo);
        List<GydxjInsertDetail> gradeList = eduUserBiz.searchListByStuNo(stuNo);
        model.addAttribute("baseInfo",baseInfo);
        model.addAttribute("gradeList",gradeList);
        return "gyxjgl/plat/printInsertScore";
    }
    /**
     * 信息核准补录导出Excel
     * @param response
     * @return
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping("/exportSupplement")
    public void exportSupplement(String sid,String userName,String trainTypeId,String trainCategoryId,String submitFlag,
                            HttpServletResponse response) throws Exception{
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sid", sid);
        paramMap.put("userName", userName);
        paramMap.put("trainTypeId", trainTypeId);
        paramMap.put("trainCategoryId", trainCategoryId);
        paramMap.put("submitFlag", submitFlag);
        List<Map<String,Object>> userList = eduUserBiz.supplementList(paramMap);
        String[] titles = new String[]{
                "SID:学号",
                "USER_NAME:学生姓名",
                "ZJLX:学生身份证件类型",
                "ZJHM:学生身份证件号码",
                "SFZZ:学生是否在职",
                "RXRQ:入学日期",
                "XJZT:学籍状态",
                "FMXM1:父母或监护人1姓名",
                "FMZJLX1:父母或监护人1身份证件类型",
                "FMZJHM1:父母或监护人1身份证件号码",
                "FMXM2:父母或监护人2姓名",
                "FMZJLX2:父母或监护人2身份证件类型",
                "FMZJHM2:父母或监护人2身份证件号码"
        };
        String fileName = "信息核准补录导出.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, userList, response.getOutputStream(),new String[]{"0"});
    }
}
