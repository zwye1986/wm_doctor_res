package com.pinde.sci.dao.edu;

import com.pinde.sci.form.edu.ChapterForm;
import com.pinde.sci.model.edu.EduCourseChapterExt;

import java.math.BigDecimal;

public interface EduCourseChapterExtMapper {
    EduCourseChapterExt selectOneWithExt(String chapterFlow);

    /**
     * 批量删除
     *
     * @param form
     * @return
     */
    int updateByChapterFlowList(ChapterForm form);

    short selectMAXChapterOrder();

    BigDecimal sumAllChapterCredit(String courseFlow);
}
