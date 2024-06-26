package com.pinde.sci.biz.gyxjgl;

import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.model.mo.EduCourse;
import com.pinde.sci.model.mo.EduCourseMajor;
import com.pinde.sci.model.mo.EduMajorCredit;
import com.pinde.sci.model.gyxjgl.XjEduCourseMajorExt;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IGyXjEduCourseMajorBiz {
    List<EduCourseMajor> searchCourseMajorByCourseFlowList(EduCourseMajor courseMajor, List<String> courseFlowList);
    /**
     * 查询专业及其包含的课程
     * @param courseMajor
     * @return
     */
//	public List<MajorCourseForm> searchIncludeCourseMajor(EduCourseMajor courseMajor,EduCourse course);

    /**
     * 保存专业课程关系
     *
     * @param courseMajor
     * @return
     */
    int save(EduCourseMajor courseMajor);

    /**
     * 查询某专业下不包含的课程
     *
     * @param majorId
     * @param year
     * @return
     */
    List<EduCourse> searchCourseNotIncludeMajor(String majorId, String assumeOrgFlow, String year,String planYear);

    /**
     * 根据专业查询课程
     *
     * @param majorId
     * @param year
     * @return
     */
    List<XjEduCourseMajorExt> searchCourseByMajor(String majorId, String year);

    /**
     * 查询课程专业关系
     *
     * @param courseMajor
     * @return
     */
    List<EduCourseMajor> searchCourseMajorListNoStatus(EduCourseMajor courseMajor);

    /**
     * 删除
     *
     * @param courseMajor
     * @return
     */
    int delete(EduCourseMajor courseMajor);

    /**
     * 查询可以引用的年限
     *
     * @param year
     * @param recommendFlag
     * @return
     */
    List<String> searchRecommendYear(String year, String recommendFlag);

    /**
     * 查询并保存引用数据
     *
     * @param courseMajor
     * @return
     */
    int saveRecommendData(EduCourseMajor courseMajor, String targetYear);

    /**
     * 删除引用数据
     *
     * @param courseMajor
     * @return
     */
    int delRecommendData(EduCourseMajor courseMajor);

    List<EduCourseMajor> searchCourseMajorList(EduCourseMajor courseMajor);

    /**
     * 查询该年度该专业所设全部课程
     *
     * @param
     * @param
     * @param
     * @return
     */
    Map<String, Object> extractCourseMajorMap(String period, List<XjEduCourseMajorExt> courseMajorExtList);

    /**
     * 查询该年度该专业所设全部课程
     *
     * @param planYear
     * @param majorId
     * @param trainTypeId 培养层次
     * @return
     */
    List<XjEduCourseMajorExt> searchCourseMajorExtList(String planYear, String majorId, String trainTypeId);

    /**
     * 查询该年度该专业所设全部课程（支持课程过滤）
     */
    List<XjEduCourseMajorExt> searchCourseMajorExtList(String planYear, String majorId, String trainTypeId, String courseName);

    /**
     * 查询该年度管理员帮该学员 已补选的课程
     */
    List<XjEduCourseMajorExt> searchCourseExtList(String planYear, String userFlow);

    /**
     * 查询该年度该专业该培养层次  待补选课程
     *
     * @param planYear
     * @param majorId
     * @param userFlow
     * @return
     */
    List<XjEduCourseMajorExt> searchReplenishCourseMajorExtList(String planYear, String majorId, String trainTypeId, String userFlow);

    /**
     * 根据多条件查询出唯一记录
     *
     */
    List<EduCourseMajor> onlyCourseMajor(EduCourseMajor courseMajor);

    /**
     * EXL导入专业课程
     */
    ExcelUtile importMajorCoursesFromExcel(MultipartFile file);

    /**
     * 修改/添加一条专业课程
     */
    int editEduCourseMajor(EduCourseMajor courseMajor);

    /**
     * 查询该年度该专业所有专业课程
     */
    List<EduCourseMajor> searchCourseList(EduCourseMajor courseMajor);
    /**
     * 根据课程查询相关的专业
     */
    List<EduCourseMajor> searchCoursesBycourseFlow(String courseFlow);
    /**
     * 查询某专业全部的课程
     *
     * @param majorId
     * @param year
     * @return
     */
    List<EduCourse> searchCourseAllMajor(String majorId, String year);

    /**
     * 保存专业学分要求
     * @param eduMajorCredit
     * @return
     */
    int saveMajorCredit(EduMajorCredit eduMajorCredit);

    /**
     * 删除专业学分要求
     * @param eduMajorCredit
     * @return
     */
    int deleteMajorCredit(EduMajorCredit eduMajorCredit);

    /**
     * 查询专业学分要求
     * @param eduMajorCredit
     * @return
     */
    List<EduMajorCredit> searchMajorCreditList(EduMajorCredit eduMajorCredit);
}
