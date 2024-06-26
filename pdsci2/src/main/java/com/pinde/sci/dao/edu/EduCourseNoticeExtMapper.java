package com.pinde.sci.dao.edu;

import com.pinde.sci.model.edu.EduCourseNoticeExt;

import java.util.List;

public interface EduCourseNoticeExtMapper {
    /**
     * 查询课程通知
     *
     * @param courseFlow
     * @return
     */
    List<EduCourseNoticeExt> searchCourseNoticeListByCourseFlow(String courseFlow);

    /**
     * 查询课程通知res
     *
     * @param courseFlow
     * @return
     */
    List<EduCourseNoticeExt> searchCourseNoticeListByCourseFlowRes(String courseFlow);
}
