package com.pinde.sci.ctrl.gyxjgl;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.sci.biz.gyxjgl.IGyXjEduCourseBiz;
import com.pinde.sci.biz.gyxjgl.IGyXjEduStudentCourseBiz;
import com.pinde.sci.biz.gyxjgl.IGyXjEduUserBiz;
import com.pinde.sci.biz.gyxjgl.IGyXjImportRecordBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.pub.XjImpTypeEnum;
import com.pinde.sci.form.gyxjgl.ExportEduStudentCourseInfo;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.gyxjgl.XjEduStudentCourseExt;
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
@RequestMapping("/gyxjgl/teachingGroup")
public class GyXjglTeachingGroupController extends GeneralController {
    private static Logger logger = LoggerFactory.getLogger(GyXjglTeachingGroupController.class);
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IGyXjEduStudentCourseBiz studentCourseBiz;
    @Autowired
    private IGyXjImportRecordBiz importRecordBiz;
    @Autowired
    private IGyXjEduUserBiz eduUserBiz;
    @Autowired
    private IGyXjEduCourseBiz courseBiz;

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
        return "gyxjgl/teachingGroup/accountManage";
    }
    //授课组绑定课程维护
    @RequestMapping(value="/addCourseGroup")
    public String addCourseGroup(String userFlow, Model model){
        Map<String,String> parMp = new HashMap<>();
        parMp.put("userFlow",userFlow);
        parMp.put("roleFlow",InitConfig.getSysCfg("xjgl_group_role_flow"));
        List<Map<String,Object>> dataList = studentCourseBiz.getCourseGroupList(parMp);
        model.addAttribute("courseGroup",dataList.size()>0?dataList.get(0):null);
        return "gyxjgl/teachingGroup/addCourseGroup";
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
        List<SysOrg> orgList = orgBiz.searchHbresOrgList();
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
        return "gyxjgl/teachingGroup/choosedCourseStus";
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
        return "gyxjgl/teachingGroup/gradeResult";
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
        return "gyxjgl/teachingGroup/impRecordList";
    }
    //授课组成绩录入页面
    @RequestMapping(value="/resultSun")
    public String resultSun(String courseFlow, Model model) throws Exception {
        EduCourse course = courseBiz.readCourse(courseFlow);
        model.addAttribute("course",course);
        Map<String,String> parMp = new HashMap<>();
        if(null != course){//防止课程被删除
            parMp.put("courseCode",course.getCourseCode());
        }else{
            parMp.put("courseFlow",courseFlow);
        }
        parMp.put("roleFlag","teachingGroup");
        List<Map<String,Object>> dataList = studentCourseBiz.getGradeAuditStus(parMp);
        model.addAttribute("dataList",dataList);
        return "gyxjgl/teachingGroup/result";
    }
    //保存授课组成绩录入
    @RequestMapping(value="/saveStudentGrade")
    public @ResponseBody String saveStudentGrade(@RequestBody List<EduStudentCourse> studentCourseList){
        int count = 0;
        String resultStr = GlobalConstant.SAVE_SUCCESSED;
        if(null != studentCourseList && studentCourseList.size() > 0){
            for (EduStudentCourse eduStudentCourse : studentCourseList) {
                count += studentCourseBiz.save(eduStudentCourse);
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
                "normalGrade:平时成绩",
                "termGrade:期末成绩",
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
        return "gyxjgl/teachingGroup/gradeAuditManage";
    }
    //授课组绑定课程维护
    @RequestMapping(value="/gradeAuditOption")
    public String gradeAuditOption(String courseFlow, Model model){
        EduCourse course = courseBiz.readCourse(courseFlow);
        model.addAttribute("course",course);
        Map<String,String> parMp = new HashMap<>();
        if(null != course){//防止课程被删除
            parMp.put("courseCode",course.getCourseCode());
        }else{
            parMp.put("courseFlow",courseFlow);
        }
        parMp.put("roleFlag","admin");
        List<Map<String,Object>> dataList = studentCourseBiz.getGradeAuditStus(parMp);
        model.addAttribute("dataList",dataList);
        return "gyxjgl/teachingGroup/gradeAuditOption";
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
    public String auditPartStu(String [] recordList,String auditStatusId){
        int count = 0;
        List<String> records = Arrays.asList(recordList);
        for(int i=0;i<records.size();i++){
            count += studentCourseBiz.auditSelectGrade(records.get(i),auditStatusId);
        }
        if(count > 0){
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return  GlobalConstant.OPERATE_FAIL;
    }
    @RequestMapping(value = "/expAccount")
    public void expAccount(String userCode, String courseFlow,HttpServletResponse response) throws Exception {
        Map<String,String> parMp = new HashMap<>();
        parMp.put("userCode",userCode);
        parMp.put("courseFlow",courseFlow);
        parMp.put("roleFlow",InitConfig.getSysCfg("xjgl_group_role_flow"));
        List<Map<String,Object>> userList = studentCourseBiz.getCourseGroupList(parMp);
        String[] titles = new String[]{
                "USER_CODE:授课组账户",
                "USER_NAME:账户名称"
        };
        ExcleUtile.exportSimpleExcleByObjs(titles, userList, response.getOutputStream(),new String[]{"0","1"});
        String fileName = "登记项目信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
}
