package com.pinde.sci.dao.res;

import com.pinde.sci.form.res.CourseMajorNumForm;
import com.pinde.sci.form.res.EduStudentCourseForm;
import com.pinde.sci.model.mo.EduAnswer;
import com.pinde.sci.model.mo.EduCourse;
import com.pinde.sci.model.mo.EduQuestion;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.res.EduCourseExt;

import java.util.List;
import java.util.Map;

public interface ResEduCourseExtMapper {
    /**
     * 查询课程
     *
     * @param courseFlow 课程流水号
     * @return
     */
    EduCourseExt selectOneWithChapters(String courseFlow);

    /**
     * 查询某一学生所选择的课程
     *
     * @param paramMap
     * @return
     */
    List<EduCourse> searchStuCourseList(Map<String, Object> paramMap);

    /**
     * 查询某一老师任教的课程信息
     *
     * @param paramMap
     * @return
     */
    List<EduCourse> searchTchCourseList(Map<String, Object> paramMap);

    /**
     * 统计某一老师任教课程数量
     *
     * @param paramMap
     * @return
     */
    int countTchCourse(Map<String, Object> paramMap);

    /**
     * 查看选择某一老师课程的所有学生
     *
     * @param sysUser
     * @return
     */
    List<SysUser> searchUserByTch(SysUser sysUser);

    /**
     * 根据条件查询问题
     *
     * @param paramMap
     * @return
     */
    List<EduQuestion> searchTchEduQuestionsList(Map<String, Object> paramMap);

    /**
     * 查询该教师任教的所有课程的问题的答案
     *
     * @param questionFlow
     * @return
     */
    List<EduAnswer> searchEduAnswerList(String questionFlow);

    /**
     * 查询选择了某一门课的所有学生信息
     *
     * @param courseFlow
     * @return
     */
    List<SysUser> searchUserByCourse(String courseFlow);

    /**
     * 查询选择了某一门课的所有学生信息(除了自己)
     *
     * @param paramMap
     * @return
     */
    List<SysUser> searchUserByCourseNotIncludeSelf(Map<String, Object> paramMap);

    /**
     * 根据用户流水号和课程类型查询课程信息
     *
     * @param paramMap
     * @return
     */
//    public List<EduCourse> searchUserCourseByType(Map<String, Object> paramMap);

    /**
     * 根据用户流水号和课程类型查询课程数量(resedu)
     *
     * @param paramMap
     * @return
     */
    int countUserCourse(Map<String, Object> paramMap);

    /**
     * 根据用户流水号查询该用户获得的总学分
     *
     * @param userFlow
     * @return
     */
    int countUserAllCredit(String userFlow);

    /**
     * 查询某一门课中处于某种学习状态的人数
     *
     * @param paramMap
     * @return
     */
    int countUserByStudyStatus(Map<String, Object> paramMap);

    /**
     * 查询某学生的本专业推荐课程(选的人数最多)
     *
     * @param
     * @return
     */
    List<EduCourseExt> countRecommendCourseByChooseMost(Map<String, Object> paramMap);

    /**
     * 查询某学生的本专业推荐课程(课程评分最高)
     *
     * @param
     * @return
     */
    List<EduCourseExt> countRecommendCourseByScoreMost(Map<String, Object> paramMap);

    /**
     * 教师课程评分排行
     *
     * @param paramMap
     * @return
     */
    List<EduCourseExt> searchCourseListByScoreOrder(Map<String, Object> paramMap);

    /**
     * 教师课程问题排行
     *
     * @param paramMap
     * @return
     */
    List<EduCourseExt> searchCourseListByQuestionOrder(Map<String, Object> paramMap);

    /**
     * 根据查询条件查询课程--管理员
     *
     * @param paramMap
     * @return
     */
    List<EduCourse> searchCourseByCondition(Map<String, Object> paramMap);

    /**
     * 获取学生所有已获得学分的课程
     *
     * @param userFlow
     * @return
     */
    List<EduCourse> selectStudentCreditCourses(String userFlow);

    /**
     * 查询学生需要加入课表的必修课
     *
     * @param paramMap
     * @return
     */
    List<EduCourse> selectAddCoursesPersonal(Map<String, Object> paramMap);

    List<EduStudentCourseForm> searchCourse(Map<String, Object> paramMap);

    /**
     * 根据当前用户流水号查询课程
     *
     * @param paramMap
     * @return
     */
    List<EduCourseExt> selectCourseList(Map<String, Object> paramMap);


    List<EduCourse> seleEduCourse(Map<String, Object> paramMap);

    /**
     * 查询course表的专业记录
     *
     * @param paramMap
     * @return
     */
    List<EduCourseExt> selectCourseMajor(Map<String, Object> paramMap);

    List<CourseMajorNumForm> selectCourseMajorNum(Map<String, Object> paramMap);

}
