package com.pinde.sci.dao.gyxjgl;

import com.pinde.sci.form.gyxjgl.XjOneCourseCreditForm;
import com.pinde.sci.form.gyxjgl.XjStudentCourseNumForm;
import com.pinde.sci.model.mo.EduCourseTeachingGroup;
import com.pinde.sci.model.gyxjgl.XjEduStudentCourseExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author tiger
 */
public interface GyXjEduStudentCourseExtMapper {

    /**
     * 查询某学生选择的所有课程(resedu)
     *
     * @param paramMap
     * @return
     */
    List<XjEduStudentCourseExt> searchEduStudentCourseExt(Map<String, Object> paramMap);

    /**
     * 根据条件统计课程相关信息
     *
     * @param paramMap
     * @return
     */
    int countCourseInfoByCondition(Map<String, Object> paramMap);

    /**
     * 查询选修了某节课的学生的学分
     *
     * @param paramMap
     * @return
     */
    List<XjOneCourseCreditForm> searchCourseCreditForm(Map<String, Object> paramMap);

//	List<StudentCourseNumForm> selectStudentCourse(Map<String, Object> paramMap);

    /**
     * 查询已选课程记录
     *
     * @param paramMap
     * @return
     */
    List<XjStudentCourseNumForm> searchStudentCourseChooseCount(Map<String, Object> paramMap);


    /**
     * 获取该年度该学员的所有选择课程
     * @param studentCourseExt
     * @return
     */
//	List<EduStudentCourseExt> searchStudentCourseExtList(EduStudentCourseExt studentCourseExt);

//	List<EduStudentCourseExt> searchStudentCourseExtListByUserFlowList(List<String> userFlowList);

    /**
     * 查询课程人数
     *
     * @param paramMap
     * @return
     */
//	List<StudentCourseNumForm> searchCourseCountByCourseFlowList(Map<String, Object> paramMap);

    List<XjEduStudentCourseExt> searchStudentCourseExtWithUserList(Map<String, Object> paramMap);

    List<XjEduStudentCourseExt> searchStudentCourse(Map<String, Object> paramMap);
    //授课组查询 已选所属课程的（学校管理员未录入成绩的）学生课程成绩
    List<XjEduStudentCourseExt> searchStudentCourseByTeacherGroup(Map<String, Object> paramMap);

    /**
     * 查询某学生选择的所有课程及其学习情况
     *
     * @param paramMap
     * @return
     */
    List<XjEduStudentCourseExt> searchEduStudentCourseExtByUserFlow(Map<String, Object> paramMap);
    //查询某学生所有课程总从学分
    List<Map<String,Object>> getScoreSum(Map<String,Object> paramMap);
    //授课组-选课学生查询
    List<Map<String,Object>> getChoosedCourseStus(Map<String,Object> paramMap);
    //查询授课组角色账户及其课程关系
    List<Map<String,Object>> getCourseGroupList(Map<String,String> parMp);
    //学校-授课组成绩管理
    List<Map<String,Object>> getGradeAuditGroupList(Map<String,String> parMp);
    //学校-授课组成绩管理详情
    List<Map<String,Object>> getGradeAuditStus(Map<String,String> parMp);
    //授课组对应选课的学生成绩一键通过
    int auditSigleGroupGrade(@Param("courseCode") String courseCode);
    //查询已选课程人数
    List<Map<String,Object>> queryChoosedCourseNum(Map<String,Object> parMp);
    //查询学生基本信息（成绩打印）
    Map<String,Object> queryBaseInfo(@Param("userFlow") String userFlow);
    //查询学生成绩（成绩打印）
    List<Map<String,Object>> queryGradeList(@Param("userFlow") String userFlow);
    //教学进度审批表数据
    List<Map<String,Object>> queryScheduleClass(Map map);
}
