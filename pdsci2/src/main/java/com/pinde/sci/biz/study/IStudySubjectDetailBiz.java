package com.pinde.sci.biz.study;

import com.pinde.sci.model.mo.StudySubject;
import com.pinde.sci.model.mo.StudySubjectDetail;

import java.util.List;

public interface IStudySubjectDetailBiz {
    /**
     * 查询是否预约
     */
    StudySubjectDetail findBySubjectFlow(String subjectFlow) throws Exception;

    List<StudySubjectDetail> findByDoctorFlow(String doctorFlow);

    /**
     *查询课程审核通过人数
     */
    int selectCountByAuditStatusId(String subjectFlow) throws  Exception;
}
