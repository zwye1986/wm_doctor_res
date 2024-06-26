package com.pinde.sci.biz.xjgl;

import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.model.mo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IXjEduCourseBiz {
    /**
     * 查询系统中所有课程
     *
     * @return
     */
    List<EduCourse> searchAllCourseList(EduCourse eduCourse, String sort);

    /**
     * 查询某学生所选择的课程
     *
     * @param
     * @return
     */
    List<EduCourse> searchStuCourseList(EduCourse eduCourse, SysUser sysUser, String studyStatus);


    /**
     * 保存课程
     *
     * @param course
     * @return
     */
    int saveCourse(EduCourse course);

    /**
     * 查询课程
     *
     * @param course
     * @return
     */
    List<EduCourse> searchCourseList(EduCourse course);


    /**
     * 删除课程
     *
     * @param courseFlow
     * @return
     */
    int delCourse(String courseFlow);


    List<EduCourse> selectCourse(String userFlow, EduCourse course, List<String> deptFlow);

    /**
     * 课程导入
     *
     * @param file
     * @return
     */
    ExcelUtile importCourseFromExcel(MultipartFile file);

    /**
     * 按条件查询课程信息
     */
    EduCourse readCourse(String courseFlow);

    List<EduCourse> readCourse(EduCourse course);

    /**
     * 维护课程老师关系
     */
    int courseTeacherRelation(Map<String,Object> mp);

    /**
     * 根据课程flow查询关联授课老师
     */
    List<EduCourseTeacher>readCourseTeacherLst(String courseFlow);

    /**
     *  更新学籍信息各版块状态的确认情况
     */
    int savePartStatus(String userFlow,String partId,String operFlag);

    /**
     * 查询学籍各版块状态信息
     */
    List<EduUserInfoStatus> searchPartStatus(String userFlow);

    /**
     * 保存上传的课程大纲和教材(修改操作)
     */
    int saveCourseOutline(EduCourse course);

    /**
     * 查询全部课程代码
     * @param mp
     * @return
     */
    List<EduCourse> searchCourseDistinct(Map<String,Object> mp);

    /**
     * 保存授课组录入权限
     */
    int updateCourse(List<String> codeList,String inputFlag);

    /**
     * 授课组对应课程及其承担单位信息
     * @param userFlow
     * @return
     */
    List<Map<String,Object>> queryOrgOrCourse(String userFlow);
    /**
     * 授课组排课时间限制
     * @param sessionNumber
     * @return
     */
    List<EduScheduleLimit> queryScheduleLimit(String sessionNumber);
    /**
     * 排课时间限制
     * @param recordFlow
     * @return
     */
    EduScheduleLimit readScheduleLimitByFlow(String recordFlow);
    /**
     * 保存排课时间限制
     * @param limit
     * @return
     */
    int updateScheduleLimit(EduScheduleLimit limit);
    /**
     * 删除排课时间限制
     * @param recordFlow
     * @return
     */
    int delScheduleLimit(String recordFlow);
}
