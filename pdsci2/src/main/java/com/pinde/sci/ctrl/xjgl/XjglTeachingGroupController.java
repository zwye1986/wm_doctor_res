package com.pinde.sci.ctrl.xjgl;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.biz.xjgl.IXjEduCourseBiz;
import com.pinde.sci.biz.xjgl.IXjEduStudentCourseBiz;
import com.pinde.sci.biz.xjgl.IXjEduUserBiz;
import com.pinde.sci.biz.xjgl.IXjImportRecordBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.enums.pub.XjImpTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.xjgl.ExportEduStudentCourseInfo;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.xjgl.XjEduStudentCourseExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/xjgl/teachingGroup")
public class XjglTeachingGroupController extends GeneralController {
    private static Logger logger = LoggerFactory.getLogger(XjglTeachingGroupController.class);
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IXjEduStudentCourseBiz studentCourseBiz;
    @Autowired
    private IXjImportRecordBiz importRecordBiz;
    @Autowired
    private IXjEduUserBiz eduUserBiz;
    @Autowired
    private IXjEduCourseBiz courseBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IUserRoleBiz roleBiz;

    //授课组账号维护
    @RequestMapping(value = "/accountManage")
    public String accountManage(Integer currentPage, HttpServletRequest request, String userCode, String courseFlow, Model model) {
        Map<String,String> parMp = new HashMap<>();
        parMp.put("userCode",userCode);
        parMp.put("courseFlow",courseFlow);
        parMp.put("roleFlow",InitConfig.getSysCfg("xjgl_group_role_flow"));
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> userList = studentCourseBiz.getCourseGroupList(parMp);
        model.addAttribute("dataList",userList);
        return "xjgl/teachingGroup/accountManage";
    }
    //授课组绑定课程维护
    @RequestMapping(value="/addCourseGroup")
    public String addCourseGroup(String userFlow, Model model){
        Map<String,String> parMp = new HashMap<>();
        parMp.put("userFlow",userFlow);
        parMp.put("roleFlow",InitConfig.getSysCfg("xjgl_group_role_flow"));
        List<Map<String,Object>> dataList = studentCourseBiz.getCourseGroupList(parMp);
        model.addAttribute("courseGroup",dataList.size()>0?dataList.get(0):null);
        return "xjgl/teachingGroup/addCourseGroup";
    }
    //绑定课程保存操作
    @RequestMapping("/saveCourseGroup")
    @ResponseBody
    public String saveCourseGroup(EduCourseTeachingGroup group){
        int num = studentCourseBiz.saveCourseGroup(group);
        if (num > 0) {
            return GlobalConstant.SAVE_SUCCESSED;
        }else if(num == -1){
            return "该课程授课组已存在";
        }else if(num == -2){
            return "该课程或者该授课组已维护";
        }
        return GlobalConstant.SAVE_FAIL;
    }
    //选课学生查询列表
    @RequestMapping(value = "/choosedCourseStus")
    public String choosedCourseStus(Integer currentPage, HttpServletRequest request, ResDoctor resDoctor, EduUser eduUser, Model model) {
        List<SysOrg> orgList = orgBiz.searchTrainOrgList();
        model.addAttribute("orgList", orgList);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("userFlow",GlobalContext.getCurrentUser().getUserFlow());
        paramMap.put("sid",eduUser.getSid());
        paramMap.put("doctorName",resDoctor.getDoctorName());
        paramMap.put("period",eduUser.getPeriod());
        paramMap.put("majorId",eduUser.getMajorId());
        paramMap.put("orgFlow",resDoctor.getOrgFlow());
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> studentList = studentCourseBiz.getChoosedCourseStus(paramMap);
        model.addAttribute("studentList",studentList);
        return "xjgl/teachingGroup/choosedCourseStus";
    }
    //成绩管理 flag为拆分成绩管理的标识
    @RequestMapping(value = "/gradeResult")
    public String gradeResult(Integer currentPage, HttpServletRequest request, EduUser eduUser, SysUser sysUser, EduStudentCourse studentCourse, String flag, Model model) {
        model.addAttribute("flag", flag);
        String userFlow = GlobalContext.getCurrentUser().getUserFlow();
        PageHelper.startPage(currentPage, getPageSize(request));
        sysUser.setUserFlow(userFlow);
        List<XjEduStudentCourseExt> recordList = studentCourseBiz.searchStudentCourse(sysUser, eduUser, studentCourse);
        model.addAttribute("recordList", recordList);
        PubImportRecord importRecord = new PubImportRecord();
        importRecord.setImpTypeId(XjImpTypeEnum.EduStudentCourse.getId());
        importRecord.setRoleFlag("teachingGroup");
        List<PubImportRecord> importRecords = importRecordBiz.searchImportRecordList(importRecord);
        model.addAttribute("importRecords", importRecords);
        EduCourseTeachingGroup courseGroup = studentCourseBiz.searchCourseGroupByFlow(userFlow);
        model.addAttribute("courseGroup",courseGroup);
        //录入权限
        SysCfg allCourse=cfgBiz.read("input_all_course_authority");
        model.addAttribute("allCourse", allCourse);
        SysCfg inputSession=cfgBiz.read("input_session_authority");
        model.addAttribute("inputSession", inputSession);
        SysCfg inputCourseSession=cfgBiz.read("input_course_session_authority");
        model.addAttribute("inputCourseSession", inputCourseSession);
        SysCfg inputStartDate=cfgBiz.read("input_start_date_authority");
        model.addAttribute("inputStartDate", inputStartDate);
        SysCfg inputEndDate=cfgBiz.read("input_end_date_authority");
        model.addAttribute("inputEndDate", inputEndDate);
        if(courseGroup!=null&&StringUtil.isNotBlank(courseGroup.getCourseCode())){
            Map<String,Object> map=new HashMap<>();
            map.put("courseCode",courseGroup.getCourseCode());
            List<EduCourse> ecList=courseBiz.searchCourseDistinct(map);
            if(ecList!=null&&ecList.size()>0){
                model.addAttribute("resultInput", ecList.get(0).getResultInput());
            }
        }
        return "xjgl/teachingGroup/gradeResult";
    }
    //成绩导入操作
    @RequestMapping(value="/importGradeFromExcel")
    @ResponseBody
    public Map<String, Object> importGradeFromExcel(MultipartFile file){
        Map<String, Object> returnDataMap=new HashMap<String,Object>();
        if(file.getSize() > 0){
            try{
                returnDataMap =eduUserBiz.importGradeFromExcel(file,"teachingGroup");
            }catch(RuntimeException re){
                re.printStackTrace();
                return returnDataMap;
            }

        }
        return returnDataMap;
    }
    //导入成绩记录
    @RequestMapping(value="/impRecordList")
    public String impRecordList(HttpServletRequest request, Integer currentPage2, Model model){
        PageHelper.startPage(currentPage2, getPageSize(request));
        PubImportRecord importRecord = new PubImportRecord();
        importRecord.setImpTypeId(XjImpTypeEnum.EduStudentCourse.getId());
        importRecord.setRoleFlag("teachingGroup");
        List<PubImportRecord> importRecordList = importRecordBiz.searchImportRecordList(importRecord);
        model.addAttribute("importRecordList", importRecordList);
        return "xjgl/teachingGroup/impRecordList";
    }
    //授课组成绩录入页面
    @RequestMapping(value="/resultSun")
    public String resultSun(String courseFlow, String authorityFlag,Model model) throws Exception {
        EduCourse course = courseBiz.readCourse(courseFlow);
        model.addAttribute("course",course);
        Map<String,String> parMp = new HashMap<>();
        if(null != course){//防止课程被删除
            parMp.put("courseCode",course.getCourseCode());
        }else{
            parMp.put("courseFlow",courseFlow);
        }
        parMp.put("roleFlag","teachingGroup");
        if(StringUtil.isNotBlank(authorityFlag)&&!GlobalConstant.FLAG_Y.equals(authorityFlag)){
            parMp.put("studentPeriod",authorityFlag);
        }
        List<Map<String,Object>> dataList = studentCourseBiz.getGradeAuditStus(parMp);
        model.addAttribute("dataList",dataList);
        return "xjgl/teachingGroup/result";
    }
    //保存授课组成绩录入
    @RequestMapping(value="/saveStudentGrade")
    public @ResponseBody String saveStudentGrade(@RequestBody List<EduStudentCourse> studentCourseList){
        int count = 0;
        String resultStr = GlobalConstant.SAVE_SUCCESSED;
        if(null != studentCourseList && studentCourseList.size() > 0){
            for (EduStudentCourse eduStudentCourse : studentCourseList) {
                if(StringUtil.isNotBlank(eduStudentCourse.getGradeTermId())){
                    eduStudentCourse.setGradeTermName(DictTypeEnum.RecruitSeason.getDictNameById(eduStudentCourse.getGradeTermId()));
                }
                if(StringUtil.isNotBlank(eduStudentCourse.getStudyWayId())){
                    eduStudentCourse.setStudyWayName(DictTypeEnum.XjStudyWay.getDictNameById(eduStudentCourse.getStudyWayId()));
                }
                if(StringUtil.isNotBlank(eduStudentCourse.getAssessTypeId())){
                    eduStudentCourse.setAssessTypeName(DictTypeEnum.XjEvaluationMode.getDictNameById(eduStudentCourse.getAssessTypeId()));
                }
                count += studentCourseBiz.save(eduStudentCourse,"GradeEdit");
            }
            resultStr = count>0?resultStr:GlobalConstant.SAVE_FAIL;
            if(GlobalConstant.FLAG_Y.equals(studentCourseList.get(0).getSubmitFlag())){
                resultStr = count>0?"提交成功":"提交失败";
            }
        }
        return resultStr;
    }

    //导出选课学生成绩
    @RequestMapping("/expExcel")
    public void expExcel(EduUser eduUser, SysUser sysUser, EduStudentCourse studentCourse, HttpServletResponse response)throws Exception{
        List<XjEduStudentCourseExt> recordList = studentCourseBiz.searchStudentCourse(sysUser, eduUser, studentCourse);
        String fileName = "学员成绩单.xls";
        createExcle(response, fileName, recordList);
    }
    private void createExcle(HttpServletResponse response, String fileName, List<XjEduStudentCourseExt> recordList) throws Exception {
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
        ExcleUtile.exportSimpleExcle(titles, dataList, ExportEduStudentCourseInfo.class, response.getOutputStream());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
    //学校-授课组成绩管理
    @RequestMapping(value = "/gradeAuditManage")
    public String gradeAuditManage(Integer currentPage, HttpServletRequest request, String courseSession, String courseFlow, Model model) {
        Map<String,String> parMp = new HashMap<>();
        parMp.put("courseSession",courseSession);
        parMp.put("courseFlow",courseFlow);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> dataList = studentCourseBiz.getGradeAuditGroupList(parMp);
        model.addAttribute("dataList",dataList);
        model.addAttribute("roleFlag","admin");
        return "xjgl/teachingGroup/gradeAuditManage";
    }
    //授课组绑定课程维护
    @RequestMapping(value="/gradeAuditOption")
    public String gradeAuditOption(String courseFlow, String roleFlag,Model model){
        EduCourse course = courseBiz.readCourse(courseFlow);
        model.addAttribute("course",course);
        Map<String,String> parMp = new HashMap<>();
        if(null != course){//防止课程被删除
            parMp.put("courseCode",course.getCourseCode());
        }else{
            parMp.put("courseFlow",courseFlow);
        }
        parMp.put("roleFlag",roleFlag);
        List<Map<String,Object>> dataList = studentCourseBiz.getGradeAuditStus(parMp);
        model.addAttribute("dataList",dataList);
        model.addAttribute("roleFlag",roleFlag);
        if("cddw".equals(roleFlag)){
            return "xjgl/assumeOrg/gradeAuditOptionOrg";
        }
        return "xjgl/teachingGroup/gradeAuditOption";
    }
    //一键审核通过
    @RequestMapping(value="/auditAll")
    @ResponseBody
    public String auditAll(String [] courseCodeList){
        int count = 0;
        List<String> records = Arrays.asList(courseCodeList);
        for(int i=0;i<records.size();i++){
            count += studentCourseBiz.auditSigleGroupGrade(records.get(i));
        }
        if(count > 0){
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return  GlobalConstant.OPERATE_FAIL;
    }
    //通过所选记录
    @RequestMapping(value="/auditPartStu")
    @ResponseBody
    public String auditPartStu(String [] recordList,String auditStatusId,String roleFlag){
        int count = 0;
        List<String> records = Arrays.asList(recordList);
        for(int i=0;i<records.size();i++){
            count += studentCourseBiz.auditSelectGrade(records.get(i),auditStatusId,roleFlag);
        }
        if(count > 0){
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return  GlobalConstant.OPERATE_FAIL;
    }
    //成绩录入权限列表
    @RequestMapping(value="/courseAuthorityList")
    public String courseAuthorityList(String courseCode,String courseName,Integer currentPage, HttpServletRequest request, Model model){
        SysCfg allCourse=cfgBiz.read("input_all_course_authority");
        model.addAttribute("allCourse", allCourse);
        SysCfg inputSession=cfgBiz.read("input_session_authority");
        model.addAttribute("inputSession", inputSession);
        SysCfg inputCourseSession=cfgBiz.read("input_course_session_authority");
        model.addAttribute("inputCourseSession", inputCourseSession);
        SysCfg inputStartDate=cfgBiz.read("input_start_date_authority");
        model.addAttribute("inputStartDate", inputStartDate);
        SysCfg inputEndDate=cfgBiz.read("input_end_date_authority");
        model.addAttribute("inputEndDate", inputEndDate);
        Map<String,Object> parMp = new HashMap<>();
        parMp.put("courseCode",courseCode);
        parMp.put("courseName",courseName);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<EduCourse> dataList = courseBiz.searchCourseDistinct(parMp);
        model.addAttribute("dataList",dataList);
        return "xjgl/teachingGroup/authorityList";
    }
    //成绩录入权限设置
    @RequestMapping(value="/courseAuthorityInfo")
    public String courseAuthorityInfo(HttpServletRequest request, Model model){
        SysCfg allCourse=cfgBiz.read("input_all_course_authority");
        model.addAttribute("allCourse", allCourse);
        SysCfg inputSession=cfgBiz.read("input_session_authority");
        model.addAttribute("inputSession", inputSession);
        SysCfg inputCourseSession=cfgBiz.read("input_course_session_authority");
        model.addAttribute("inputCourseSession", inputCourseSession);
        SysCfg inputStartDate=cfgBiz.read("input_start_date_authority");
        model.addAttribute("inputStartDate", inputStartDate);
        SysCfg inputEndDate=cfgBiz.read("input_end_date_authority");
        model.addAttribute("inputEndDate", inputEndDate);
        return "xjgl/teachingGroup/editAuthority";
    }
    /**
     * 保存成绩录入权限
     */
    @RequestMapping(value="/sysCfgUpdate")
    @ResponseBody
    public String sysCfgUpdate(String jsonData,HttpServletRequest request){
        List<String> codeList = new ArrayList<>();
        if(StringUtil.isNotBlank(jsonData)){
            Map<String,Object> mp = JSON.parseObject(jsonData,Map.class);
            codeList = (List<String>)mp.get("courseCodeList");
        }

        String allCourse = request.getParameter("allCourse");
        String inputSession = request.getParameter("inputSession");
        String inputCourseSession = request.getParameter("inputCourseSession");
        String inputStartDate = request.getParameter("inputStartDate");
        String inputEndDate = request.getParameter("inputEndDate");
        if(StringUtil.isNotBlank(allCourse)){
            courseBiz.updateCourse(null,GlobalConstant.FLAG_N);
            if(GlobalConstant.FLAG_N.equals(allCourse)&&codeList!=null&&codeList.size()>0){
                courseBiz.updateCourse(codeList,GlobalConstant.FLAG_Y);
            }
        }
        List<SysCfg> cfgList = new ArrayList<SysCfg>();
        //课程成绩管理权限
        SysCfg requiredStart = new SysCfg();
        requiredStart.setCfgCode("input_all_course_authority");
        requiredStart.setCfgValue(allCourse);
        cfgList.add(requiredStart);
        //录入年度
        SysCfg requiredEnd = new SysCfg();
        requiredEnd.setCfgCode("input_session_authority");
        requiredEnd.setCfgValue(inputSession);
        cfgList.add(requiredEnd);
        SysCfg input_course_session = new SysCfg();
        input_course_session.setCfgCode("input_course_session_authority");
        input_course_session.setCfgValue(inputCourseSession);
        cfgList.add(input_course_session);
        //录入时间
        SysCfg optionalStart = new SysCfg();
        optionalStart.setCfgCode("input_start_date_authority");
        optionalStart.setCfgValue(inputStartDate);
        cfgList.add(optionalStart);
        SysCfg optionalEnd = new SysCfg();
        optionalEnd.setCfgCode("input_end_date_authority");
        optionalEnd.setCfgValue(inputEndDate);
        cfgList.add(optionalEnd);

        cfgBiz.save(cfgList);
        return GlobalConstant.SAVE_SUCCESSED;
    }
    //成绩录入权限设置
    @RequestMapping(value="/editCourseAuthority")
    public String editCourseAuthority(Model model){
        Map<String,Object> parMp = new HashMap<>();
        List<EduCourse> courseList = courseBiz.searchCourseDistinct(parMp);
        model.addAttribute("courseList",courseList);
        return "xjgl/teachingGroup/editCourseAuthority";
    }
    @RequestMapping(value="/saveAccount")
    @ResponseBody
    public String saveAccount(String userCode,String userName){
        if(StringUtil.isNotBlank(userCode)){
            userCode = userCode.trim();
            SysUser user1 = userBiz.findByUserCode(userCode);
            if(user1 != null){
                return GlobalConstant.USER_CODE_REPETE;
            }
        }
        SysUser user =new SysUser();
        //绑定研究生管理系统(cmis)授课组角色
        String roleFlow = InitConfig.getSysCfg("xjgl_group_role_flow");//cmis授课组角色配置
        if(StringUtil.isBlank(roleFlow)){
            return "后台学籍配置未绑定授课组角色！";
        }
        //新增用户并激活
        user.setUserFlow(PkUtil.getUUID());
        user.setUserCode(userCode);
        user.setUserName(userName);
        GeneralMethod.setRecordInfo(user, true);
        int num1=userBiz.addUser(user);
        SysUserRole record = new SysUserRole();
        record.setUserFlow(user.getUserFlow());
        record.setWsId("cmis");
        record.setRoleFlow(roleFlow);
        GeneralMethod.setRecordInfo(record,true);
        int num2=roleBiz.addSysUserRole(record);
        if(num1>0&&num2>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
}
