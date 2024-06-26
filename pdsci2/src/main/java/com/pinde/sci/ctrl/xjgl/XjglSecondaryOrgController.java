package com.pinde.sci.ctrl.xjgl;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.xjgl.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.pub.XjImpTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.xjgl.ExportEduStudentCourseInfo;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.xjgl.XjEduStudentCourseExt;
import oracle.sql.DATE;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/xjgl/secondaryOrg")
public class XjglSecondaryOrgController extends GeneralController {
    @Autowired
    private IXjEduSecondaryOrgBiz secondaryOrgBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IXjEduCourseBiz eduCourseBiz;
    @Autowired
    private IXjImportRecordBiz importRecordBiz;
    @Autowired
    private IXjEduStudentCourseBiz studentCourseBiz;
    @Autowired
    private IXjEduUserBiz eduUserBiz;
    /**
     * 二级机构管理
     */
    @RequestMapping(value="/secondaryOrgList" )
    public String secondaryOrgList(String orgFlow, Integer currentPage, Model model) {
        PageHelper.startPage(currentPage, 10);
        List<SysUser> userList = secondaryOrgBiz.querySecondaryOrgAdminList(orgFlow);
        model.addAttribute("dataList",userList);

        List<EduCourse> records=eduCourseBiz.searchCourseList(new EduCourse());
        Map<String,String> orgMap = new HashMap<String, String>();
        for(EduCourse temp : records){
            if(StringUtil.isNotBlank(temp.getAssumeOrgFlow())){
                orgMap.put(temp.getAssumeOrgFlow(), temp.getAssumeOrgName());
            }
        }
        model.addAttribute("orgMap",orgMap);
        return "xjgl/plat/xjglSecondaryOrg";
    }
    @RequestMapping(value="/addSecondaryAdmin")
    public String addSecondaryAdmin(String userFlow, Model model){
        List<EduCourse> records=eduCourseBiz.searchCourseList(new EduCourse());
        Map<String,String> orgMap = new HashMap<String, String>();
        for(EduCourse temp : records){
            if(StringUtil.isNotBlank(temp.getAssumeOrgFlow())){
                orgMap.put(temp.getAssumeOrgFlow(), temp.getAssumeOrgName());
            }
        }
        model.addAttribute("orgMap",orgMap);
        if(StringUtil.isNotBlank(userFlow)){
            SysUser sysUser = userBiz.findByFlow(userFlow);
            model.addAttribute("sysUser",sysUser);
        }
        return "xjgl/plat/addSecondaryAdmin";
    }
    @RequestMapping("/saveSecondaryAdmin")
    @ResponseBody
    public String saveSecondaryAdmin(SysUser user){
        int num = secondaryOrgBiz.saveSecondaryAdmin(user);
        if (num > 0) {
            return GlobalConstant.SAVE_SUCCESSED;
        }else if(num == -1){
            return "此用户名已存在！";
        }else if(num == -2){
            return "此二级机构已存在管理员账户！";
        }else if(num == -3){
            return "系统二级机构角色未设置！";
        }
        return GlobalConstant.SAVE_FAIL;
    }
    @RequestMapping("/resetPwd")
    @ResponseBody
    public String resetPwd(String userFlow){
        int num = secondaryOrgBiz.resetOrgAdminPwd(userFlow);
        if (num > 0) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    //成绩管理 flag为拆分成绩管理的标识
    @RequestMapping(value = "/gradeResultList")
    public String gradeResultList(Integer currentPage, HttpServletRequest request, EduUser eduUser, SysUser sysUser, EduStudentCourse studentCourse, String flag, Model model) {
        model.addAttribute("flag", flag);
        String userFlow = GlobalContext.getCurrentUser().getUserFlow();
        PageHelper.startPage(currentPage, getPageSize(request));
        sysUser.setUserFlow(userFlow);
        sysUser.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        List<XjEduStudentCourseExt> recordList = secondaryOrgBiz.searchStudentCourse(sysUser, eduUser, studentCourse);
        model.addAttribute("recordList", recordList);
        PubImportRecord importRecord = new PubImportRecord();
        importRecord.setImpTypeId(XjImpTypeEnum.EduStudentCourse.getId());
        importRecord.setRoleFlag("secondaryOrg");
        List<PubImportRecord> importRecords = importRecordBiz.searchImportRecordList(importRecord);
        model.addAttribute("importRecords", importRecords);
//        EduCourseTeachingGroup courseGroup = secondaryOrgBiz.searchCourseGroupByFlow(userFlow);
//        model.addAttribute("courseGroup",courseGroup);
        //当前二级单位课程
        EduCourse course=new EduCourse();
        course.setCourseSession(studentCourse.getStudentPeriod());
        course.setAssumeOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        List<EduCourse> courseList=secondaryOrgBiz.querySecondaryOrgEduCourse(course);
        model.addAttribute("courseList", courseList);
        return "xjgl/secondaryOrg/gradeResultList";
    }
    //二级单位成绩录入页面
    @RequestMapping(value="/resultSun")
    public String resultSun(String courseCode,String studentPeriod, String sid,Model model) throws Exception {
        if(StringUtil.isBlank(studentPeriod)){
            studentPeriod= DateUtil.getYear();
        }
        if(StringUtil.isNotBlank(courseCode)){
            EduCourse course=null;
            EduCourse eduCourse=new EduCourse();
            eduCourse.setCourseCode(courseCode);
            eduCourse.setCourseSession(studentPeriod);
            eduCourse.setAssumeOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
            List<EduCourse> courseList = secondaryOrgBiz.querySecondaryOrgEduCourse(eduCourse);
            if(courseList!=null&&courseList.size()>0){
                course=courseList.get(0);
                model.addAttribute("course",course);
                Map<String,String> parMp = new HashMap<>();
                if(null != course){
                    parMp.put("courseCode",course.getCourseCode());
                    parMp.put("courseFlow",course.getCourseFlow());
                }
                parMp.put("roleFlag","secondaryOrg");
                parMp.put("sid",sid);
                parMp.put("assumeOrgFlow",GlobalContext.getCurrentUser().getOrgFlow());
                List<Map<String,Object>> dataList = secondaryOrgBiz.getGradeAuditStus(parMp);
                model.addAttribute("dataList",dataList);
            }
        }
        EduCourse course=new EduCourse();
        course.setAssumeOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        List<EduCourse> courseList=secondaryOrgBiz.querySecondaryOrgEduCourse(course);
        model.addAttribute("courseList", courseList);
        model.addAttribute("studentPeriod", studentPeriod);
        return "xjgl/secondaryOrg/result";
    }

    //保存二级单位成绩录入
    @RequestMapping(value="/saveStudentGrade")
    public @ResponseBody String saveStudentGrade(@RequestBody List<EduStudentCourse> studentCourseList){
        int count = 0;
        String resultStr = GlobalConstant.SAVE_SUCCESSED;
        if(null != studentCourseList && studentCourseList.size() > 0){
            for (EduStudentCourse eduStudentCourse : studentCourseList) {
                if(StringUtil.isNotBlank(eduStudentCourse.getAssessTypeId())){
                    eduStudentCourse.setAssessTypeName(DictTypeEnum.XjEvaluationMode.getDictNameById(eduStudentCourse.getAssessTypeId()));
                }
                if(StringUtil.isNotBlank(eduStudentCourse.getStudyWayId())){
                    eduStudentCourse.setStudyWayName(DictTypeEnum.XjStudyWay.getDictNameById(eduStudentCourse.getStudyWayId()));
                }
                if(StringUtil.isNotBlank(eduStudentCourse.getGradeTermId())){
                    eduStudentCourse.setGradeTermName(DictTypeEnum.RecruitSeason.getDictNameById(eduStudentCourse.getGradeTermId()));
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

    //学校-二级单位成绩管理
    @RequestMapping(value = "/gradeResultAudit")
    public String gradeResultAudit(Integer currentPage, HttpServletRequest request, String sid, String userName, String studentPeriod,String gradeTermId,String studyWayId,String courseCode,String assumeOrgFlow,Model model) {
        if(StringUtil.isBlank(studentPeriod)){
            studentPeriod= DateUtil.getYear();
        }
        Map<String,String> parMp = new HashMap<>();
        parMp.put("sid",sid);
        parMp.put("userName",userName);
        parMp.put("studentPeriod",studentPeriod);
        parMp.put("gradeTermId",gradeTermId);
        parMp.put("studyWayId",studyWayId);
        parMp.put("courseCode",courseCode);
        parMp.put("assumeOrgFlow",assumeOrgFlow);
        parMp.put("roleFlag","admin");
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> dataList = secondaryOrgBiz.getGradeAuditStus(parMp);
        model.addAttribute("dataList",dataList);
        EduCourse course=new EduCourse();
        course.setAssumeOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        List<EduCourse> courseList=secondaryOrgBiz.querySecondaryOrgEduCourse(course);
        model.addAttribute("courseList", courseList);
        model.addAttribute("studentPeriod", studentPeriod);

        List<EduCourse> recordList = eduCourseBiz.searchCourseList(new EduCourse());
        Map<String,String> assumeOrgFlowMap = new HashMap<String, String>();
        for(EduCourse course1:recordList){
            if (StringUtil.isNotBlank(course1.getAssumeOrgFlow())) {
                assumeOrgFlowMap.put(course1.getAssumeOrgFlow(), course1.getAssumeOrgName());
            }
        }
        model.addAttribute("assumeOrgFlowMap", assumeOrgFlowMap);
        return "xjgl/plat/gradeResultAudit";
    }

    //导出学生成绩
    @RequestMapping("/expExcel")
    public void expExcel(String courseCode,String studentPeriod, String sid, HttpServletResponse response)throws Exception{
        String courseName="";
        String coursePeriod="";
        String courseCredit="";
        String assumeOrgName="";
        List<Map<String,Object>> dataList=new ArrayList<>();
        if(StringUtil.isBlank(studentPeriod)){
            studentPeriod= DateUtil.getYear();
        }
        if(StringUtil.isNotBlank(courseCode)){
            EduCourse course=null;
            EduCourse eduCourse=new EduCourse();
            eduCourse.setCourseCode(courseCode);
            eduCourse.setCourseSession(studentPeriod);
            eduCourse.setAssumeOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
            List<EduCourse> courseList = secondaryOrgBiz.querySecondaryOrgEduCourse(eduCourse);
            if(courseList!=null&&courseList.size()>0){
                course=courseList.get(0);
                courseName=course.getCourseName();
                coursePeriod=course.getCoursePeriod();
                courseCredit=course.getCourseCredit();
                assumeOrgName=course.getAssumeOrgName();
                Map<String,String> parMp = new HashMap<>();
                if(null != course){
                    parMp.put("courseCode",course.getCourseCode());
                    parMp.put("courseFlow",course.getCourseFlow());
                }
                parMp.put("roleFlag","secondaryOrg");
                parMp.put("sid",sid);
                parMp.put("assumeOrgFlow",GlobalContext.getCurrentUser().getOrgFlow());
                dataList = secondaryOrgBiz.getGradeAuditStus(parMp);
            }
        }
        List<Map<String,Object>> dataListTemp=new ArrayList<>();
        if(dataList!=null&dataList.size()>0){
            for(int i=0;i<dataList.size();i=i+ 2){
                Map<String,Object> tempMap=new HashMap<>();
                tempMap.put("SID",dataList.get(i).get("SID"));
                tempMap.put("USER_NAME",dataList.get(i).get("USER_NAME"));
                tempMap.put("COURSE_GRADE",dataList.get(i).get("COURSE_GRADE"));
                if(i+1<dataList.size()){
                    tempMap.put("SID1",dataList.get(i+1).get("SID"));
                    tempMap.put("USER_NAME1",dataList.get(i+1).get("USER_NAME"));
                    tempMap.put("COURSE_GRADE1",dataList.get(i+1).get("COURSE_GRADE"));
                }else {
                    tempMap.put("SID1","");
                    tempMap.put("USER_NAME1","");
                    tempMap.put("COURSE_GRADE1","");
                }
                dataListTemp.add(tempMap);
            }
        }

        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFCellStyle styleRight = wb.createCellStyle(); //居中
        styleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        styleRight.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet(courseName+"成绩单");
        //列宽自适应
//        sheet.setDefaultColumnWidth((short)50);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));//合并单元格
        HSSFRow rowOne = sheet.createRow(0);//第一行
        rowOne.setHeightInPoints(20);
        HSSFCell cellOne = rowOne.createCell(0);
        cellOne.setCellStyle(styleCenter);
        cellOne.setCellValue(courseName+"成绩单");
        HSSFRow rowTwo = sheet.createRow(1);//第二行
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 6));//合并单元格
        HSSFCell cellFive = rowTwo.createCell(0);
        cellFive.setCellStyle(styleLeft);
        cellFive.setCellValue("承担单位："+assumeOrgName+" 学时："+coursePeriod+" 学分："+courseCredit);
        HSSFRow rowFour = sheet.createRow(2);//第四行
        String[] titles = new String[]{
                "学号",
                "姓名",
                "成绩",
                "",
                "学号",
                "姓名",
                "成绩"
        };
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowFour.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 256);
        }
        int rowNum = 3;
        String[] resultList = null;
        if (dataListTemp != null && !dataListTemp.isEmpty()) {
            for (int i = 0; i < dataListTemp.size(); i++, rowNum++) {
                HSSFRow rowFive = sheet.createRow(rowNum);
                resultList = new String[]{
                        dataListTemp.get(i).get("SID")==null?"":dataListTemp.get(i).get("SID").toString(),
                        dataListTemp.get(i).get("USER_NAME")==null?"":dataListTemp.get(i).get("USER_NAME").toString(),
                        dataListTemp.get(i).get("COURSE_GRADE")==null?"":dataListTemp.get(i).get("COURSE_GRADE").toString(),
                        "",
                        dataListTemp.get(i).get("SID1")==null?"":dataListTemp.get(i).get("SID1").toString(),
                        dataListTemp.get(i).get("USER_NAME1")==null?"":dataListTemp.get(i).get("USER_NAME1").toString(),
                        dataListTemp.get(i).get("COURSE_GRADE1")==null?"":dataListTemp.get(i).get("COURSE_GRADE1").toString()
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFive.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(resultList[j]);
                }
            }
        }
        HSSFRow rowFoot = sheet.createRow(rowNum);
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 6));//合并单元格
        HSSFCell cellTotal1 = rowFoot.createCell(0);
        cellTotal1.setCellStyle(styleRight);
        cellTotal1.setCellValue(DateUtil.getCurrDate());

        String fileName = courseName+"成绩单.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }
    //成绩导入操作
    @RequestMapping(value="/importGradeFromExcel")
    @ResponseBody
    public Map<String, Object> importGradeFromExcel(MultipartFile file){
        Map<String, Object> returnDataMap=new HashMap<String,Object>();
        if(file.getSize() > 0){
            try{
                returnDataMap =eduUserBiz.importGradeFromExcel(file,"secondaryOrg");
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
        importRecord.setRoleFlag("secondaryOrg");
        List<PubImportRecord> importRecordList = importRecordBiz.searchImportRecordList(importRecord);
        model.addAttribute("importRecordList", importRecordList);
        return "xjgl/secondaryOrg/impRecordList";
    }

    @RequestMapping(value="/courseMajor")
    public String courseMajor(String planYear,String assumeOrgFlow, String courseFlow, Model model){
        String curryear=DateUtil.getCurrDateTime("yyyy");
        if (StringUtil.isBlank(planYear)) {
            planYear = curryear;
        }
        model.addAttribute("planYear", planYear);
        assumeOrgFlow=GlobalContext.getCurrentUser().getOrgFlow();
        model.addAttribute("assumeOrgFlow",assumeOrgFlow);
        EduCourse eduCourseParam=new EduCourse();
        eduCourseParam.setCourseSession(planYear);
        List<EduCourse> recordList1 = eduCourseBiz.searchCourseList(eduCourseParam);
        List<EduCourse> recordList = eduCourseBiz.searchCourseList(new EduCourse());
        Map<String,String> assumeOrgFlowMap = new HashMap<String, String>();
        Map<String,List<EduCourse>> orgCourseMap = new HashMap<String, List<EduCourse>>();
        for(EduCourse course:recordList){
            if (StringUtil.isNotBlank(course.getAssumeOrgFlow())) {
                assumeOrgFlowMap.put(course.getAssumeOrgFlow(), course.getAssumeOrgName());
            }
        }
        for(EduCourse course1:recordList1){
            List<EduCourse> temp = orgCourseMap.get(course1.getAssumeOrgFlow());
            if(temp == null){
                temp = new ArrayList<EduCourse>();
            }
            temp.add(course1);
            orgCourseMap.put(course1.getAssumeOrgFlow(), temp);
        }
        model.addAttribute("assumeOrgFlowMap", assumeOrgFlowMap);
        List<EduCourse> courseList = new ArrayList<EduCourse>();

        if(StringUtil.isNotBlank(assumeOrgFlow)){
            courseList = orgCourseMap.get(assumeOrgFlow);
        }else{
            courseList = recordList1;
        }
        if(StringUtil.isNotBlank(courseFlow)){
            courseList.clear();
            EduCourse eduCourse = eduCourseBiz.readCourse(courseFlow);
            courseList.add(eduCourse);
        }

        //上课人数
        Map<String, List<XjEduStudentCourseExt>> studentMap = new HashMap<String, List<XjEduStudentCourseExt>>();
        if(courseList!=null&&courseList.size()>0){
            for(EduCourse course : courseList){
                List<XjEduStudentCourseExt> studentCourseExt = studentCourseBiz.searchStudentCourseExtWithUserList(planYear, course.getCourseFlow());
                studentMap.put(course.getCourseFlow(), studentCourseExt);
            }
        }
        model.addAttribute("courseList", courseList);
        model.addAttribute("studentMap", studentMap);
        return "xjgl/secondaryOrg/courseOverview";
    }

    @RequestMapping(value="/exportMultiExcle", method={RequestMethod.POST,RequestMethod.GET})
    public void exportMultiExcle(String planYear, String assumeOrgFlow,HttpServletRequest request, HttpServletResponse response) throws Exception {
        EduCourse example = new EduCourse();
        example.setCourseSession(planYear);
        example.setAssumeOrgFlow(assumeOrgFlow);
        List<EduCourse> courseList = eduCourseBiz.searchCourseList(example);
        String folder=System.getProperty("java.io.tmpdir")+ File.separator;
        String zipFile = PkUtil.getUUID();
        String dir = folder+zipFile;
        File dirFile = new File(dir);
        if(dirFile.exists()==false){
            dirFile.mkdirs();
        }
        if(courseList != null && !courseList.isEmpty()){
            for(EduCourse course : courseList){
                String courseFlow = course.getCourseFlow();
                String[] titles = new String[]{
                        ":序号",
                        "eduUser.sid:学号",
                        "sysUser.userName:姓名",
                        "eduUser.className:行政班级"
                };
                if (StringUtil.isNotBlank(courseFlow)) {
                    List<XjEduStudentCourseExt> studentCourseExt = studentCourseBiz.searchStudentCourseExtWithUserList(planYear, courseFlow);
                    int studentCount = 0;
                    if(studentCourseExt != null && !studentCourseExt.isEmpty()){
                        studentCount =  studentCourseExt.size();
                    }
                    StringBuffer sb = new StringBuffer();
                    sb.append("承担单位："+ StringUtil.defaultIfEmpty(course.getDeptName(), "--"));
                    sb.append("  课程：[" + course.getCourseCode() +"]" +course.getCourseName());
                    sb.append("  培养层次：" +  StringUtil.defaultIfEmpty(course.getGradationName(), "--"));
                    sb.append("  学分：" +  StringUtil.defaultIfEmpty(course.getCourseCredit(), "--"));
                    sb.append("  总学时：" + StringUtil.defaultIfEmpty(course.getCoursePeriod(), "--"));
                    sb.append("  上课人数：" + studentCount);
                    String courseInfo = sb.toString();
                    String[] headLines = new String[]{
                            "课程信息",
                            courseInfo,
                            planYear + "年学员名册"
                    };
                    String fileName = course.getCourseCode()+course.getCourseName()+"学员名册.xls";
                    File excel = new File(dir+File.separator+fileName);
                    ExcleUtile.exportSimpleExcleWithHeadlineByObjs(headLines, titles, studentCourseExt, excel);
                }
            }
        }
        ZipUtil.makeDirectoryToZip(dirFile, new File(folder+zipFile+".zip"), "", 7);

        String fileName = "课程信息.zip";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());

        byte[] data = getByte(new File(folder+zipFile+".zip"));
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

    //二级单位课程情况展示成绩录入页面
    @RequestMapping(value="/resultSunFromInfo")
    public String resultSunFromInfo(String courseFlow, String sid,Model model) throws Exception {
        if(StringUtil.isNotBlank(courseFlow)){
            EduCourse course = eduCourseBiz.readCourse(courseFlow);
            model.addAttribute("course",course);
            Map<String,String> parMp = new HashMap<>();
            if(null != course){
                parMp.put("courseCode",course.getCourseCode());
                parMp.put("courseFlow",course.getCourseFlow());
            }
            parMp.put("roleFlag","secondaryOrg");
            parMp.put("sid",sid);
            parMp.put("assumeOrgFlow",GlobalContext.getCurrentUser().getOrgFlow());
            List<Map<String,Object>> dataList = secondaryOrgBiz.getGradeAuditStus(parMp);
            model.addAttribute("dataList",dataList);
        }
        EduCourse course=new EduCourse();
        course.setAssumeOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        List<EduCourse> courseList=secondaryOrgBiz.querySecondaryOrgEduCourse(course);
        model.addAttribute("courseList", courseList);
        return "xjgl/secondaryOrg/resultFromInfo";
    }
}
