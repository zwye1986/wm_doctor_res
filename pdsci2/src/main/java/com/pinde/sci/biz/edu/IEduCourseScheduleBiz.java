package com.pinde.sci.biz.edu;

import com.pinde.sci.form.edu.CourseInfoForm;
import com.pinde.sci.form.edu.SysOrgExt;
import com.pinde.sci.model.edu.EduCourseScheduleExt;
import com.pinde.sci.model.edu.EduUserExt;
import com.pinde.sci.model.mo.EduCourse;
import com.pinde.sci.model.mo.EduCourseSchedule;
import com.pinde.sci.model.mo.SysOrg;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IEduCourseScheduleBiz {
    /**
     * 新增或修改
     *
     * @param schedule
     * @return
     */
    int edit(EduCourseSchedule schedule);

    /**
     * 计算章节平均评分
     *
     * @param schedule
     * @return
     */
    BigDecimal searchAvgScore(EduCourseSchedule schedule);

    /**
     * 保存评分
     *
     * @param schedule
     */
    int saveChapterScore(EduCourseSchedule schedule);

    /**
     * 查询
     *
     * @param schedule
     * @return
     */
    EduCourseSchedule searchOne(EduCourseSchedule schedule);

    /**
     * 保存赞
     *
     * @param schedule
     */
    void savePraise(EduCourseSchedule schedule);

    /**
     * 查询所有评价
     *
     * @param eduCourseSchedule
     * @return
     */
    List<EduCourseSchedule> searchEvaluateList(EduCourseSchedule eduCourseSchedule);

    /**
     * 按主键查询
     *
     * @param recordFlow
     * @return
     */
    EduCourseSchedule seachOne(String recordFlow);

    /**
     * 查询某节课(或者全部)的所有学生的学习进度
     *
     * @param eduUserExtList
     * @param courseFlow
     * @return
     */
    Map<String, Map<String, Object>> searchUserScheduleMap(List<EduUserExt> eduUserExtList, String courseFlow);

    /**
     * 根据条件查询授课详细信息
     *
     * @param eduUserExtList
     * @return
     */
    Map<String, Map<String, Object>> countInfoOfTeacher(List<EduUserExt> eduUserExtList);


//	List<EduCourseScheduleExt> searchChapterScheduleMap(Map<String, Object> paramMap);

    /**
     * 查询该教师所授课程的所有学校
     *
     * @param oparamMap
     * @return
     */
    List<SysOrgExt> selectOrgOfSchedule(Map<String, Object> oparamMap);

    /**
     * 查询学校的评价
     *
     * @param paramMap
     * @return
     */
    CourseInfoForm countInfoOfTeacher(Map<String, Object> paramMap);

    List<EduCourseScheduleExt> searchCourseSchedule(Map<String, Object> paramMap);

    /**
     * 查询学生的学习记录
     *
     * @param eduUserExtList
     * @return
     */
    Map<String, Map<String, Map<String, Object>>> searchStuSchedule(List<EduUserExt> eduUserExtList);

    /**
     * 查询每个学校的课程完成情况
     *
     * @param orgList
     * @return
     */
    Map<String, Map<String, Object>> searchCourseFinishInfoByOrg(List<SysOrg> orgList, List<EduCourse> courseList);

    List<EduCourseSchedule> searchEduCourseScheduleList(EduCourseSchedule schedule);

    /**
     * 查询
     * @param paramMap
     * @return
     */
//	List<EduCourseSchedule> searchEduCourseScheduleList(Map<String, Object> paramMap);

    /**
     * 查询下一章节流水号
     *
     * @param chapterFlow
     * @param courseFlow
     * @return
     */
    String nextChapter(String chapterFlow, String courseFlow);
    /**
     * 当视频播放完成后修改学习记录并且判断考试是否及格
     * @param userFlow
     * @param chapterFlow
     * @return
     */
//	int modifyScheduleForFinishVideo(String userFlow,String chapterFlow);
}
