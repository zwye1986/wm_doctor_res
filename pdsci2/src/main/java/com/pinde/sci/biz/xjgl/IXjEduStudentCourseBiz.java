package com.pinde.sci.biz.xjgl;

import com.pinde.sci.form.xjgl.XjStudentCourseNumForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.xjgl.XjEduStudentCourseExt;

import java.util.List;
import java.util.Map;

/**
 * @author tiger
 */
public interface IXjEduStudentCourseBiz {

    /**
     * 修改学生选课情况
     *
     * @param eduStudentCourse
     * @return
     */
    int save(EduStudentCourse eduStudentCourse,String editFlag);
    /**
     * 查询学生选择的课程的详细信息
     * @param eduUserExtList
     * @return
     */
    /**
     * 查询选修了某节课的学生的学分
     *
     * @param
     * @return
     */
    Map<String, Object> searchCourseCreditForm(String courseFlow);


    /**
     * 查询学生选课记录
     *
     * @param studentCourse
     * @return
     */
    List<EduStudentCourse> searchStudentCourseList(EduStudentCourse studentCourse);

    List<EduStudentCourse> searchStudentCourseList(List<String> courseFlowList);


//    List<XjStudentCourseNumForm> studentCourseNumList(Map<String, Object> map);

    /**
     * 保存网上选课
     *
     * @param
     * @return
     */
    int saveStudentCourseByCourseFlowList(String studentPeriod, String userFlow, List<EduStudentCourse> studentCourseList, String replenishFlag,String saveBeforeFlag);

    /**
     * 查询该届学生该专业所有课程已选人数（已选）
     *
     * @param paramMap
     * @return
     */
    List<XjStudentCourseNumForm> searchStudentCourseChooseCount(Map<String, Object> paramMap);

    /**
     * 保存课程维护
     *
     * @param
     * @param
     * @return
     */
    int saveCourseMaintain(EduStudentCourse studentCourse);

    /**
     * 根据课程类别组织该用户所选课程对应的课程Map
     *
     * @param userFlow
     * @return
     */
    Map<String, Object> extractStudentCourseMapByCourseType(String studentPeriod, String userFlow);


    List<EduStudentCourse> searchStudentCourseListByUserFlowList(List<String> userFlowList);


    List<XjEduStudentCourseExt> searchStudentCourseExtWithUserList(String planYear, String courseFlow);

    /**
     * 生成成绩单的二维码并保存
     *
     * @param userFlow
     * @return
     * @throws Exception
     */
    String createQrCodeForGrade(String userFlow) throws Exception;

    /**
     * 解析成绩单二维码返回url
     *
     * @param userFlow
     * @return
     * @throws Exception
     */
    String decodeQrCodeForGrade(String userFlow) throws Exception;

    EduStudentCourse searchStudentCourse(String recordFlow);

    int deleteCourse(String recordFlow);

    List<XjEduStudentCourseExt> searchStudentCourse(SysUser sysUser,EduUser eduUser,ResDoctor doctor,EduStudentCourse studentCourse,String scoreSum,String degreeGrade,String courseTypeScore);

    int reOpenChooseCourse(String userFlow);

    //查询学生的总学列表
    List<Map<String,Object>> getScoreSum(Map<String,Object> paramMap);
    //授课组-成绩管理
    List<XjEduStudentCourseExt> searchStudentCourse(SysUser sysUser,EduUser eduUser,EduStudentCourse studentCourse);
    //授课组-选课学生查询
    List<Map<String,Object>> getChoosedCourseStus(Map<String,Object> paramMap);
    //查询授课组角色账户及其课程关系
    List<Map<String,Object>> getCourseGroupList(Map<String,String> parMp);
    //授课组与课程建立联系
    int saveCourseGroup(EduCourseTeachingGroup group);
    //学校-授课组成绩管理
    List<Map<String,Object>> getGradeAuditGroupList(Map<String,String> parMp);
    //承担单位-授课组成绩管理
    List<Map<String,Object>> getGradeAuditsCddw(Map<String,String> parMp);
    //学校-授课组成绩管理详情
    List<Map<String,Object>> getGradeAuditStus(Map<String,String> parMp);
    //授课组对应选课的学生成绩一键通过
    int auditSigleGroupGrade(String courseCode);
    //授课组对应选课的学生成绩一键通过（承担单位）
    int auditSigleGroupGradeCddw(String courseCode);
    //授课组对应选课的学生成绩一键通过
    int auditSelectGrade(String recordFlow,String auditStatusId,String roleFlag);
    //查询授课组和课程关系
    EduCourseTeachingGroup searchCourseGroupByFlow(String userFlow);
    /**
     * 修改学生选择学位课程情况
     *
     * @param eduStudentCourse
     * @return
     */
    int saveDegreeCourse(EduStudentCourse eduStudentCourse);

    List<XjEduStudentCourseExt> searchGradeInfos(SysUser sysUser,EduUser eduUser,ResDoctor doctor,EduStudentCourse studentCourse,String scoreSum,String degreeGrade,String courseTypeScore,String assumeOrgFlow);

}
