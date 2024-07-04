package com.pinde.res.ctrl.gydxj;

import com.pinde.app.common.GlobalConstant;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.JaxbUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.gydxj.IGydxjAdminBiz;
import com.pinde.res.biz.gydxj.IGydxjAppBiz;
import com.pinde.res.biz.gydxj.IGydxjStudentBiz;
import com.pinde.res.biz.gydxj.IGydxjTeacherBiz;
import com.pinde.res.enums.gydxj.*;
import com.pinde.res.enums.lcjn.DictTypeEnum;
import com.pinde.res.enums.stdp.CertificateTypeEnum;
import com.pinde.res.enums.stdp.UserSexEnum;
import com.pinde.res.model.gydxj.mo.SubmitApplyForm;
import com.pinde.res.model.gydxj.mo.XjEduUserExtInfoForm;
import com.pinde.res.model.gydxj.mo.XjEduUserForm;
import com.pinde.sci.model.mo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/res/gydxj/student")
public class GydxjStudentController {
    private static Logger logger = LoggerFactory.getLogger(GydxjStudentController.class);
    @Autowired
    private IGydxjAppBiz appBiz;
    @Autowired
    private IGydxjStudentBiz stuBiz;
    @Autowired
    private IGydxjTeacherBiz teaBiz;

    @ExceptionHandler(Throwable.class)
    public String exception(Throwable e) {
        logger.error(this.getClass().getCanonicalName() + " some error happened", e);
        return "res/gydxj/500";
    }

    @RequestMapping(value = {"/test"}, method = {RequestMethod.GET})
    public String test() {
        return "res/gydxj/student/test";
    }

    @RequestMapping(value = {"/pushNotice"})
    public String pushNotice(Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        return "res/gydxj/backResult";
    }

    @RequestMapping("/eduUserFormEdit")
    public String edit(String userFlow,String roleId,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "用户流水号为空");
            return "res/gydxj/student/eduUserFormEdit";
        }
        model.addAttribute("roleId",roleId);
        EduUser eduUser=appBiz.readEduUser(userFlow);
        model.addAttribute("eduUser", eduUser);
        String content = eduUser.getContent();
        XjEduUserExtInfoForm extInfoForm = stuBiz.parseExtInfoXml(content);
        model.addAttribute("extInfoForm", extInfoForm);
        SysUser sysUser=appBiz.readSysUser(userFlow);
        model.addAttribute("sysUser", sysUser);
        ResDoctor resDoctor=appBiz.readResDoctor(userFlow);
        model.addAttribute("resDoctor", resDoctor);
        return "res/gydxj/student/eduUserFormEdit";
    }
    @RequestMapping("/confirmStatus")
    public String confirmStatus(String userFlow,String partId,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow) || StringUtil.isBlank(partId)) {
            model.addAttribute("resultId", "3010107");
            model.addAttribute("resultType", "所需参数出错");
            return "res/gydxj/backResult";
        }
        stuBiz.confirmStatus(userFlow, partId);
        return "res/gydxj/backResult";
    }
    @RequestMapping(value="/saveEduUser")
    public String saveEduUser(XjEduUserForm eduUserForm, Model model) throws Exception {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
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
        if(StringUtil.isNotBlank(extInfoForm.getFirstEducationContentId())){
            extInfoForm.setFirstEducationContentName(DictTypeEnum.GyFirstEducation.getDictNameById(extInfoForm.getFirstEducationContentId()));
        }
        if(StringUtil.isNotBlank(eduUser.getAwardDegreeCategoryId())){
            eduUser.setAwardDegreeCategoryName(DictTypeEnum.GyDegreeCategory.getDictNameById(eduUser.getAwardDegreeCategoryId()));
        }
        if(StringUtil.isNotBlank(user.getDeptFlow())){
            SysDept dept=appBiz.readSysDept(user.getDeptFlow());
            if(dept!=null){
                user.setDeptName(dept.getDeptName());
                eduUser.setTrainOrgId(user.getDeptFlow());
                eduUser.setTrainOrgName(dept.getDeptName());
            }
        }
        resDoctor.setSessionNumber(eduUser.getPeriod());
        resDoctor.setDoctorCode(eduUser.getSid());
        resDoctor.setTutorName(eduUser.getFirstTeacher());
        resDoctor.setDoctorCategoryId("Graduate");
        resDoctor.setDoctorCategoryName("研究生");
        resDoctor.setDoctorLicenseNo(extInfoForm.getCodeDoctorQuaCert());
        resDoctor.setQualifiedNo(extInfoForm.getCodeMedicalPractitioner());
        resDoctor.setQualifiedFile(extInfoForm.getIsDoctorQuaCert());
        resDoctor.setDoctorLicenseFlag(extInfoForm.getIsMedicalPractitioner());
        resDoctor.setForeignSkills(extInfoForm.getForeignLanguageName());
        resDoctor.setComputerSkills(extInfoForm.getComputerLevel());
        int result=stuBiz.save(eduUserForm);
        if(result <= 0){
            model.addAttribute("resultId", "3010109");
            model.addAttribute("resultType", "保存失败");
        }
        return "res/gydxj/backResult";
    }

    @RequestMapping("/applyList")
    public String applyList(String searchStr,String userFlow,String applyTypeId,String statusId,Integer pageIndex,Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "用户流水号为空");
            return "res/gydxj/student/applyList";
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userFlow", userFlow);
        paramMap.put("applyTypeId", applyTypeId);
        paramMap.put("statusId", statusId);
        paramMap.put("searchStr", searchStr);
        List<String> typeList = new ArrayList<>();
        for(UserChangeApplyTypeEnum en : UserChangeApplyTypeEnum.values()){
            typeList.add(en.getId());
        }
        paramMap.put("typeList", typeList);
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String,Object>> applyList = stuBiz.searchApplyList(paramMap);
        model.addAttribute("applyList", applyList);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/gydxj/student/applyList";
    }

    @RequestMapping("/applyTypeList")
    public String applyTypeList(Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        return "res/gydxj/student/applyTypeList";
    }

    @RequestMapping("/editApply")
    public String editApply(String userFlow,String applyTypeId,String recordFlow,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "用户流水号为空");
            return "res/gydxj/student/editApply";
        }
        if(StringUtil.isEmpty(applyTypeId)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "申请类型为空");
            return "res/gydxj/student/editApply";
        }
        EduUser user=appBiz.readEduUser(userFlow);
        ResDoctor doctor = appBiz.readResDoctor(userFlow);
        SysUser sysUser = appBiz.readSysUser(userFlow);
        List<EduCourse> eduCourseList=stuBiz.searchStuCourseList(userFlow);
        model.addAttribute("user",user);
        model.addAttribute("doctor",doctor);
        model.addAttribute("sysUser",sysUser);
        model.addAttribute("eduCourseList",eduCourseList);
        if(StringUtil.isNotBlank(recordFlow)){
            EduUserChangeApply apply=stuBiz.readEduUserChangeApply(recordFlow);
            SubmitApplyForm form= JaxbUtil.converyToJavaBean(apply.getContent(), SubmitApplyForm.class);
            model.addAttribute("form",form);
            model.addAttribute("apply", apply);
        }
        return "res/gydxj/student/editApply";
    }
    @RequestMapping("/saveApply")
    public String saveApply(EduUserChangeApply apply,SubmitApplyForm form,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(apply.getApplyTypeId())){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "申请类型为空");
            return "res/gydxj/backResult";
        }
        stuBiz.saveApply(form,apply);
        return "res/gydxj/backResult";
    }
    @RequestMapping("/submitApply")
    public String submitApply(String recordFlow,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(recordFlow)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "记录流水号为空");
            return "res/gydxj/backResult";
        }
        stuBiz.submitApply(recordFlow);
        return "res/gydxj/backResult";
    }

    @RequestMapping("/classSchedule")
    public String classSchedule(String classTime,String userFlow,String termFlow,Integer pageIndex,Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(classTime)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "时间不能为空");
            return "res/gydxj/student/classSchedule";
        }
        Map<String,String> timeMap = new HashMap<>();
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String,Object>> classList = stuBiz.classScheduleList(classTime,userFlow,termFlow);
        for (ClassOrderEnumOfGz e : ClassOrderEnumOfGz.values()) {
            timeMap.put(e.getName().substring(1,e.getName().length()-1),e.getTime());
        }
        model.addAttribute("timeMap",timeMap);
        model.addAttribute("classList",classList);
        model.addAttribute("dataCount",PageHelper.total);
        return "res/gydxj/student/classSchedule";
    }

    @RequestMapping("/courseType")
    public String courseType(Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
//        Map<String,Object> dataMap = stuBiz.searchNeedData();
//        model.addAttribute("dataMap",dataMap);
        return "res/gydxj/student/courseType";
    }

    @RequestMapping("/chooseCourse")
    public String chooseCourse(String searchStr,String courseTypeId,String userFlow,String studentPeriod,String majorId,String allFlag,Integer pageIndex,Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        Map<String,Object> param = new HashMap<>();
        param.put("searchStr",searchStr);
        param.put("courseTypeId",courseTypeId);
        param.put("studentPeriod",studentPeriod);
        param.put("majorId",majorId);
        param.put("allFlag",allFlag);
        param.put("role","student");
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String,Object>> courseList = stuBiz.searchEduCourse(param);
        model.addAttribute("dataCount",PageHelper.total);
        //已选课
        param.put("userFlow",userFlow);
        Map<String,String> courseMap = new HashMap<>();
        List<Map<String,Object>> selectList = stuBiz.searchChooseCourse(param);
        if(null != selectList && !selectList.isEmpty()){
            for(Map<String,Object> mp : selectList){
                courseMap.put((String)mp.get("COURSE_FLOW"),"Y");
            }
        }
        model.addAttribute("courseMap",courseMap);
        //已选课置顶排序
        List<Map<String,Object>> newCourseList = new ArrayList<>();
        if(null!=courseList && !courseList.isEmpty()){
            for(Map<String,Object> mp : courseList){
                if(GlobalConstant.FLAG_Y.equals(courseMap.get((String)mp.get("COURSE_FLOW")))){
                    newCourseList.add(mp);
                }
            }
            for(Map<String,Object> mp : courseList){
                if(!GlobalConstant.FLAG_Y.equals(courseMap.get((String)mp.get("COURSE_FLOW")))){
                    newCourseList.add(mp);
                }
            }
        }
        model.addAttribute("courseList",newCourseList);
        return "res/gydxj/student/chooseCourse";
    }

    @RequestMapping("/stuCourse")
    public String stuCourse(String userFlow,String studentPeriod,Integer pageIndex,Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        Map<String,Object> param = new HashMap<>();
        param.put("userFlow",userFlow);
        param.put("studentPeriod",studentPeriod);
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String,Object>> courseList = stuBiz.searchStuCourse(param);
        model.addAttribute("courseList",courseList);
        model.addAttribute("dataCount",PageHelper.total);
        return "res/gydxj/student/stuCourse";
    }

    @RequestMapping("/saveCourse")
    public String stuCourse(String userFlow, String studentPeriod, String courseFlowList,String delFlowList,String roleId, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(null != courseFlowList && courseFlowList.length() > 2){
            courseFlowList=courseFlowList.substring(2,courseFlowList.length()-2);
            String [] flowArry = courseFlowList.split("\",\"");
            List<String> flowList = Arrays.asList(flowArry);
            Map<String,Object> map = new HashMap<>();
            map.put("userFlow",userFlow);
            map.put("flowList",flowList);
            List<String> exitFlow =  stuBiz.queryExitCourseFlow(map);
            List<String> newList = new ArrayList<>();
            for(String flow : flowList){
                boolean flag = false;
                for(String exit : exitFlow){
                    if(flow.equals(exit)){
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    newList.add(flow);
                }
            }
            for(String flow : newList){
                EduCourse course = appBiz.readEduCourse(flow);
                stuBiz.saveStuCourse(course,userFlow,studentPeriod,roleId);
            }
        }
        //取消已选课程
        if(null != delFlowList && delFlowList.length() > 2) {
            delFlowList = delFlowList.substring(2, delFlowList.length() - 2);
            String[] flowArry = delFlowList.split("\",\"");
            List<String> flowList = Arrays.asList(flowArry);
            Map<String,Object> map = new HashMap<>();
            map.put("userFlow",userFlow);
            map.put("flowList",flowList);
            stuBiz.delStuCourse(map);
        }
        return "res/gydxj/backResult";
    }

    @RequestMapping("/signClass")
    public String signClass(String userFlow,String courseFlow,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "3010100");
            model.addAttribute("resultType", "用户流水号不存在");
            return "res/gydxj/backResult";
        }
        if(StringUtil.isEmpty(courseFlow)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "课程流水号不存在");
            return "res/gydxj/backResult";
        }
        String signTime = DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm:ss");
        String curDate = DateUtil.getCurrDateTime("yyyy-MM-dd");
        String curTime = DateUtil.getCurrDateTime("HH:mm");
        if(compareDate(curTime,"08:30")>=0 && compareDate(curTime,"09:10")==-1){
            curTime = "一";
        }else if(compareDate(curTime,"09:15")>=0 && compareDate(curTime,"09:55")==-1){
            curTime = "二";
        }else if(compareDate(curTime,"10:05")>=0 && compareDate(curTime,"10:45")==-1){
            curTime = "三";
        }else if(compareDate(curTime,"10:50")>=0 && compareDate(curTime,"11:30")==-1){
            curTime = "四";
        }else if(compareDate(curTime,"11:35")>=0 && compareDate(curTime,"12:15")==-1){
            curTime = "五";
        }else if(compareDate(curTime,"12:20")>=0 && compareDate(curTime,"13:05")==-1){
            curTime = "六";
        }else if(compareDate(curTime,"13:10")>=0 && compareDate(curTime,"13:50")==-1){
            curTime = "七";
        }else if(compareDate(curTime,"14:00")>=0 && compareDate(curTime,"14:40")==-1){
            curTime = "八";
        }else if(compareDate(curTime,"14:45")>=0 && compareDate(curTime,"15:25")==-1){
            curTime = "九";
        }else if(compareDate(curTime,"15:35")>=0 && compareDate(curTime,"16:15")==-1){
            curTime = "十";
        }else if(compareDate(curTime,"16:20")>=0 && compareDate(curTime,"17:00")==-1){
            curTime = "十一";
        }else if(compareDate(curTime,"17:05")>=0 && compareDate(curTime,"17:45")==-1){
            curTime = "十二";
        }else if(compareDate(curTime,"17:50")>=0 && compareDate(curTime,"18:30")==-1){
            curTime = "十三";
        }else if(compareDate(curTime,"18:30")>=0 && compareDate(curTime,"19:10")==-1){
            curTime = "十四";
        }else if(compareDate(curTime,"19:15")>=0 && compareDate(curTime,"19:55")==-1){
            curTime = "十五";
        }else if(compareDate(curTime,"20:00")>=0 && compareDate(curTime,"20:40")==-1){
            curTime = "十六";
        }else{
            model.addAttribute("resultId", "3010102");
            model.addAttribute("resultType", "当前时间没有课");
            return "res/gydxj/backResult";
        }
        int choosedCount = stuBiz.searchEduStuCourse(userFlow,courseFlow);
        if(choosedCount <= 0){
            model.addAttribute("resultId", "3010106");
            model.addAttribute("resultType", "此人未选该课程，无法签到");
            return "res/gydxj/backResult";
        }
        Map<String,Object> map = new HashMap<>();
        map.put("courseFlow",courseFlow);
        map.put("curDate",curDate);
        map.put("curTime",curTime);
        List<EduScheduleClass> classList = stuBiz.searchClassList(map);
        if(null != classList && !classList.isEmpty()){
            Map<String,Object> args = new HashMap<>();
            args.put("userFlow",userFlow);
            args.put("signTime",signTime);
            args.put("classFlow",classList.get(0).getRecordFlow());
            int count = stuBiz.signClass(args);
            if(count > 0){
                return "res/gydxj/backResult";
            }else{
                model.addAttribute("resultId", "3010104");
                model.addAttribute("resultType", "此人该课程已签到");
                return "res/gydxj/backResult";
            }
        }else{
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "当前时间没有课");
            return "res/gydxj/backResult";
        }
    }

    private int compareDate(String date1,String date2){
        DateFormat dateFormat=new SimpleDateFormat("HH:mm");
        try {
            Date d1 = dateFormat.parse(date1);
            Date d2 = dateFormat.parse(date2);
            if(d1.equals(d2)){
                return 0;
            }else if(d1.before(d2)){
                return -1;
            }else if(d1.after(d2)){
                return 1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -2;
    }

    @RequestMapping(value={"/searchCourse"},method={RequestMethod.POST})
    public String searchCourse(String recordFlow,String courseFlow,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(courseFlow)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "课程流水号为空");
            return "res/gydxj/student/searchCourse";
        }
        if(StringUtil.isEmpty(recordFlow)){
            model.addAttribute("resultId", "3010102");
            model.addAttribute("resultType", "课堂流水号为空");
            return "res/gydxj/student/searchCourse";
        }
        EduCourse course = appBiz.readEduCourse(courseFlow);
        if(course == null){
            model.addAttribute("resultId", "3010102");
            model.addAttribute("resultType", "该课程未维护！");
            return "res/gydxj/student/searchCourse";
        }
        model.addAttribute("course",course);
        Map<String,String> timeMap = new HashMap<>();
        for (ClassOrderEnumOfGz e : ClassOrderEnumOfGz.values()) {
            timeMap.put(e.getName().substring(1,e.getName().length()-1),e.getTime());
        }
        model.addAttribute("timeMap",timeMap);
        List<Map<String,Object>> classList=teaBiz.scheduleCourseList2(courseFlow,recordFlow);
        if(null!=classList && !classList.isEmpty()){
            model.addAttribute("classInfo",classList.get(0));
        }
        List<EduScheduleTeacher> teachers = stuBiz.searchTeacherByClass(recordFlow);
        if(null!=teachers && !teachers.isEmpty()){
            model.addAttribute("teacher",teachers.get(0));
        }
        return "res/gydxj/student/searchCourse";
    }
}

