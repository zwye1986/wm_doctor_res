package com.pinde.sci.biz.njmuedu;

import com.pinde.sci.model.mo.EduCourse;
import com.pinde.sci.model.mo.EduCourseChapter;
import com.pinde.sci.model.njmuedu.EduCourseChapterExt;
import com.pinde.sci.model.njmuedu.EduUserExt;

import java.util.List;
import java.util.Map;

public interface INjmuEduCourseChapterBiz {
    /**
     * 查找章节
     *
     * @param chapterFlow
     * @return
     */
    EduCourseChapter seachOne(String chapterFlow);

    /**
     * 查询课程全部章节
     *
     * @return
     */
    List<EduCourseChapter> getAllChapter(String courseFlow);

    /**
     * 保存章节信息
     * @param chapter
     * @param file
     * @return
     * @throws Exception
     */
//	int saveChapterInfo(EduCourseChapter chapter, MultipartFile file) throws Exception;

    /**
     * 保存章节
     *
     * @param chapter
     * @return
     */
    int saveChapter(EduCourseChapter chapter);

    /**
     * 根据课程流水号查出没有富章节的章节
     *
     * @param eduCourse
     * @return
     */
    int countNoParentChapterByCourseFlow(EduCourse eduCourse);

    /**
     * 根据课程流水号查出有富章节的章节
     *
     * @param eduCourse
     * @return
     */
    int countParentChapterByCourseFlow(EduCourse eduCourse);

    /**
     * 查找章节（包含扩展属性）
     *
     * @param chapterFlow
     * @return
     */
    EduCourseChapterExt seachOneWithExt(String chapterFlow);

    /**
     * 删除章节图片
     *
     * @param chapterFlow
     * @return
     */
    int deleteChapterImg(String chapterFlow);

    /**
     * 批量删除
     *
     * @param chapterFlowList
     * @return
     */
    int updateByChapterFlowList(List<String> chapterFlowList);

    /**
     * 查询
     *
     * @param chapter
     * @param order
     * @param isParent
     * @return
     */
    List<EduCourseChapter> searchCourseChapterList(EduCourseChapter chapter, String order, String isParent);

    List<EduCourseChapter> searchChapterListByChapterFlowList(List<String> chapterFlowList);

    /**
     * 查询老师们的所教授章节的详细信息
     *
     * @param eduUserExtList
     * @return
     */
    Map<String, Map<String, List<EduCourseChapterExt>>> searchTeachersChapterList(List<EduUserExt> eduUserExtList);

    /**
     * 查询老师所教授的章节基本信息
     *
     * @param teacherId
     * @return
     */
    List<EduCourseChapter> searchChapterListByTeacherId(String teacherId);

    /**
     * 保存章节并返回流水号
     *
     * @param chapter
     * @return
     */
    String saveChapterReturnFlow(EduCourseChapter chapter);

    /**
     * 获取章节计数
     *
     * @param chapter
     * @return
     */
    int getChapterCount(EduCourseChapter chapter);

    EduCourseChapter readEduCourseChapter(String chapterFlow);
}
