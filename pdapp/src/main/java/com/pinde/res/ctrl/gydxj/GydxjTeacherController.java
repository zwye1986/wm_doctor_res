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
import com.pinde.res.enums.gydxj.ClassOrderEnumOfGz;
import com.pinde.res.enums.gydxj.UserChangeApplyStatusEnum;
import com.pinde.res.enums.gydxj.UserChangeApplyTypeEnum;
import com.pinde.res.enums.lcjn.DictTypeEnum;
import com.pinde.res.model.gydxj.mo.SubmitApplyForm;
import com.pinde.res.model.gydxj.mo.XjEduUserExtInfoForm;
import com.pinde.sci.model.mo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/res/gydxj/teacher")
public class GydxjTeacherController {
    private static Logger logger = LoggerFactory.getLogger(GydxjTeacherController.class);

    @Autowired
    private IGydxjAppBiz appBiz;
    @Autowired
    private IGydxjTeacherBiz teaBiz;
    @Autowired
    private IGydxjAdminBiz admBiz;
    @Autowired
    private IGydxjStudentBiz stuBiz;

    @ExceptionHandler(Throwable.class)
    public String exception(Throwable e) {
        logger.error(this.getClass().getCanonicalName() + " some error happened", e);
        return "res/gydxj/500";
    }

    @RequestMapping(value = {"/test"}, method = {RequestMethod.GET})
    public String test() {
        return "res/gydxj/teacher/test";
    }

    @RequestMapping("/searchCourse")
    public String searchCourse(String userFlow,Integer pageIndex,Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3010102");
            model.addAttribute("resultType", "用户流水号不存在");
            return "res/gydxj/teacher/courseList";
        }
        List<Map<String,Object>> course = teaBiz.searchCourse(userFlow);
        if(null==course || course.isEmpty() || StringUtil.isBlank((String)course.get(0).get(("COURSE_CODE")))){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "该账号未绑定课程");
            return "res/gydxj/teacher/courseList";
        }
        PageHelper.startPage(pageIndex, pageSize);
//        List<EduCourse> courseList=teaBiz.qryCourseList((String)course.get(0).get(("COURSE_CODE")));
        Map<String,Object> map = new HashMap<>();
        map.put("courseCode",course.get(0).get(("COURSE_CODE")));
        map.put("classTime", DateUtil.getCurrDate());
        List<Map<String,Object>> courseList=teaBiz.qryCourseList2(map);
        //平均出勤率
        Map<String,Object> avgMap = new HashMap<>();
        if(null!= courseList && !courseList.isEmpty()){
            for(Map<String,Object> m : courseList){
                List<Map<String,Object>> classList=teaBiz.scheduleCourseList2((String) m.get("COURSE_FLOW"),null);
                int avgTotal=0;
                int avgCount = 0;
                if(null != classList && !classList.isEmpty()){
                    for(Map<String,Object> p : classList){
                        if(DateUtil.getCurrDate().compareTo((String)p.get("CLASS_TIME")) > -1){
                            avgCount++;
                        }
                        if(!"0".equals(p.get("TOTLE_NUM").toString())){
                            avgTotal += (Integer.valueOf(p.get("JOIN_NUM").toString())*100/Integer.valueOf(p.get("TOTLE_NUM").toString()));
                        }
                    }
                    avgMap.put((String)m.get("COURSE_FLOW"),new DecimalFormat("0.00").format((float)avgTotal/avgCount));
                }
            }
        }
        model.addAttribute("avgMap", avgMap);
        model.addAttribute("courseList", courseList);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/gydxj/teacher/courseList";
    }
    @RequestMapping("/scheduleCourse")
    public String scheduleCourse(String courseFlow,Integer pageIndex,Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(courseFlow)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "课程流水号不存在");
            return "res/gydxj/teacher/classList";
        }
        Map<String,String> timeMap = new HashMap<>();
        for (ClassOrderEnumOfGz e : ClassOrderEnumOfGz.values()) {
            timeMap.put(e.getName().substring(1,e.getName().length()-1),e.getTime());
        }
        model.addAttribute("timeMap",timeMap);
        PageHelper.startPage(pageIndex, pageSize);
//        List<EduScheduleClass> classList=teaBiz.scheduleCourseList(courseFlow);
        List<Map<String,Object>> classList=teaBiz.scheduleCourseList2(courseFlow,null);
        model.addAttribute("classList", classList);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/gydxj/teacher/classList";
    }

    @RequestMapping("/signDetail")
    public String signDetail(String recordFlow,String signFlag,Integer pageIndex,Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(recordFlow)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "课堂流水号不存在");
            return "res/gydxj/teacher/signDetail";
        }
        PageHelper.startPage(pageIndex, pageSize);
        Map<String,Object> map = new HashMap<>();
        map.put("recordFlow",recordFlow);
        map.put("signFlag",signFlag);
        List<Map<String,Object>> userList = teaBiz.searchSignUserList(map);
        model.addAttribute("eduUserList", userList);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/gydxj/teacher/signDetail";
    }

    @RequestMapping("/signOutOpt")
    public String signOutOpt(String userFlow,String recordFlow,String sign, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(recordFlow)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "课堂流水号不存在");
            return "res/gydxj/backResult";
        }
        if(StringUtil.isBlank(sign)){
            model.addAttribute("resultId", "3010102");
            model.addAttribute("resultType", "签到签退标识为空");
            return "res/gydxj/backResult";
        }
        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "学生流水号为空");
            return "res/gydxj/backResult";
        }
        teaBiz.signOutOpt(userFlow,recordFlow,sign);
        return "res/gydxj/backResult";
    }

    @RequestMapping("/stuCourseList")
    public String stuCourseList(String userFlow,String courseFlow,String submitFlag,Integer pageIndex,Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "用户流水号不存在");
            return "res/gydxj/teacher/stuCourseList";
        }
        List<Map<String,Object>> course = teaBiz.searchCourse(userFlow);
        if(null==course || course.isEmpty() || StringUtil.isBlank((String)course.get(0).get(("COURSE_CODE")))){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "该账号未绑定课程");
            return "res/gydxj/teacher/stuCourseList";
        }
        Map<String,Object> map = new HashMap<>();
        map.put("roleId","Teacher");
//        map.put("courseCode",course.get(0).get(("COURSE_CODE")));
        map.put("courseFlow",courseFlow);
        map.put("submitFlag",submitFlag);
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String,Object>> stuCourseList=admBiz.getGradeAuditStus(map);
        model.addAttribute("stuCourseList", stuCourseList);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/gydxj/teacher/stuCourseList";
    }

    @RequestMapping("/courseList")
    public String courseList(String userFlow,Integer pageIndex,Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "用户流水号不存在");
            return "res/gydxj/teacher/stuCourseList";
        }
        List<Map<String,Object>> course = teaBiz.searchCourse(userFlow);
        if(null==course || course.isEmpty() || StringUtil.isBlank((String)course.get(0).get(("COURSE_CODE")))){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "该账号未绑定课程");
            return "res/gydxj/teacher/stuCourseList";
        }
        Map<String,Object> map = new HashMap<>();
        map.put("courseCode",course.get(0).get(("COURSE_CODE")));
        PageHelper.startPage(pageIndex, pageSize);
        List<EduCourse> courseList=admBiz.searchCourseList(map);
        model.addAttribute("courseList", courseList);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/gydxj/teacher/skzCourseList";
    }

    @RequestMapping("/gradeDetail")
    public String gradeDetail(String recordFlow,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(recordFlow)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "成绩流水号不存在");
            return "res/gydxj/teacher/gradeDetail";
        }
        Map<String,Object> map = new HashMap<>();
        map.put("recordFlow",recordFlow);
        List<Map<String,Object>> gradeInfo=admBiz.getGradeAuditStus(map);
        model.addAttribute("grade", gradeInfo.get(0));
        return "res/gydxj/teacher/gradeDetail";
    }

    @RequestMapping("/saveGrade")
    public String saveGrade(EduStudentCourse course, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(course.getRecordFlow())){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "成绩流水号不存在");
            return "res/gydxj/backResult";
        }
        if(StringUtil.isNotBlank(course.getCourseGrade())){
            if(!isNumeric(course.getCourseGrade())){
                boolean flag = false;
                for(SysDict dict : DictTypeEnum.sysListDictMap.get("GyXjIsPassed")){
                    if(course.getCourseGrade().equals(dict.getDictName())){
                        flag = true;
                        course.setCourseGrade(dict.getDictId());
                        break;
                    }
                }
                if(!flag){
                    course.setCourseGrade("");
                }
            }
        }
        course.setRoleFlag("teachingGroup");
        course.setSubmitFlag(GlobalConstant.FLAG_Y);
        course.setAuditStatusId("");
        course.setAuditStatusName("");
        teaBiz.saveStuCourseGrade(course);
        return "res/gydxj/backResult";
    }
    private static boolean isNumeric(String str){
        for (int i = 0; i < str.length(); i++){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    @RequestMapping("/auditApplyList")
    public String auditApplyList(String userFlow,String auditFlag,Integer pageIndex,Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "用户流水号不存在");
            return "res/gydxj/teacher/stuCourseList";
        }
        Map<String,Object> map = new HashMap<>();
        map.put("userFlow",userFlow);
        map.put("auditFlag",auditFlag);
        List<String> typeList = new ArrayList<>();
        for(UserChangeApplyTypeEnum en : UserChangeApplyTypeEnum.values()){
            typeList.add(en.getId());
        }
        map.put("typeList", typeList);
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String,Object>> applyUserList=teaBiz.getApplyUserList(map);
        model.addAttribute("applyUserList", applyUserList);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/gydxj/teacher/auditApplyList";
    }
    @RequestMapping("/applyDetail")
    public String applyDetail(String userFlow,String recordFlow,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(recordFlow)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "异动流水号不存在");
            return "res/gydxj/teacher/applyDetail";
        }
        EduUser user=appBiz.readEduUser(userFlow);
        ResDoctor doctor = appBiz.readResDoctor(userFlow);
    SysUser sysUser = appBiz.readSysUser(userFlow);
    model.addAttribute("user",user);
    model.addAttribute("doctor",doctor);
    model.addAttribute("sysUser",sysUser);
    if(StringUtil.isNotBlank(recordFlow)){
        EduUserChangeApply apply=stuBiz.readEduUserChangeApply(recordFlow);
        SubmitApplyForm form= JaxbUtil.converyToJavaBean(apply.getContent(), SubmitApplyForm.class);
        model.addAttribute("form",form);
        model.addAttribute("apply", apply);
    }
    return "res/gydxj/teacher/applyDetail";
}

    @RequestMapping("/auditApply")
    public String auditApply(EduUserChangeApply apply,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(apply.getRecordFlow())){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "异动流水号不存在");
            return "res/gydxj/backResult";
        }
        if("Approve".equals(apply.getStatusId())){
            apply.setStatusName(UserChangeApplyStatusEnum.Approve.getName());
        }else{
            apply.setStatusName(UserChangeApplyStatusEnum.NotApprove.getName());
        }
        teaBiz.auditApplyInfo(apply);
        return "res/gydxj/backResult";
    }
    @RequestMapping(value={"/stuList"},method={RequestMethod.POST})
    public String stuList(String searchStr,String userFlow,Integer pageIndex,Integer pageSize,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("searchStr",searchStr);
        paramMap.put("teacher",userFlow);
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String,Object>> eduUserList = admBiz.searchEduUser(paramMap);
        model.addAttribute("eduUserList", eduUserList);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/gydxj/teacher/stuList";
    }

    @RequestMapping(value={"/stuCourseDetail"},method={RequestMethod.POST})
    public String stuCourseDetail(String userFlow,String period,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userFlow",userFlow);
        List<Map<String,Object>> eduUserList = admBiz.searchEduUser(paramMap);
        model.addAttribute("eduUser", eduUserList.get(0));;
        List<EduStudentCourse> studentCourseList = admBiz.searchStuCourseListByFlow(userFlow);
        if(studentCourseList != null && !studentCourseList.isEmpty()){
            Map<String, Map<String,Map<String, List<EduStudentCourse>>>> studentCourseMap = new LinkedHashMap<String, Map<String,Map<String, List<EduStudentCourse>>>>();
            Map<String,Integer> studentPeriodCountMap = new HashMap<String, Integer>();
            for(EduStudentCourse esc :studentCourseList){
                if(!period.equals(esc.getStudentPeriod())){
                    continue;
                }
                Map<String,Map<String, List<EduStudentCourse>>> periodUserCourseMap = studentCourseMap.get(userFlow);
                if(periodUserCourseMap == null){
                    periodUserCourseMap = new HashMap<String,Map<String, List<EduStudentCourse>>>();
                }
                String sp = esc.getStudentPeriod();
                Map<String, List<EduStudentCourse>> courseMap = periodUserCourseMap.get(sp);
                if(courseMap == null){
                    courseMap = new HashMap<String, List<EduStudentCourse>>();
                }
                String courseTypeId = esc.getCourseTypeId();
                List<EduStudentCourse> tempList = courseMap.get(courseTypeId);
                if(tempList == null){
                    tempList = new ArrayList<EduStudentCourse>();
                }
                tempList.add(esc);
                courseMap.put(courseTypeId, tempList);
                periodUserCourseMap.put(sp, courseMap);
                studentCourseMap.put(userFlow, periodUserCourseMap);
                studentPeriodCountMap.put(userFlow, periodUserCourseMap.size());
            }
            model.addAttribute("studentCourseMap", studentCourseMap);
            model.addAttribute("studentPeriodCountMap", studentPeriodCountMap);
        }
        return "res/gydxj/teacher/stuCourseDetail";
    }

    @RequestMapping(value={"/stuCourseChange"},method={RequestMethod.POST})
    public String stuCourseChange(String userFlow,String majorId,String allFlag,Integer pageIndex,Integer pageSize,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        Map<String,Object> param = new HashMap<>();
        param.put("role","teacher");
        param.put("majorId",majorId);
        param.put("allFlag",allFlag);
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
        List<Map<String,Object>> selectedList = new ArrayList<>();
        List<Map<String,Object>> noSelectList = new ArrayList<>();
        for(Map<String,Object> mp : courseList){
            if("Y".equals(courseMap.get((String)mp.get("COURSE_FLOW")))){
                selectedList.add(mp);
            }else{
                noSelectList.add(mp);
            }
        }
        model.addAttribute("selectedList",selectedList);
        model.addAttribute("noSelectList",noSelectList);
        return "res/gydxj/teacher/stuCourseChange";
    }
}

