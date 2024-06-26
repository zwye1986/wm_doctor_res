package com.pinde.sci.biz.njmuedu;

import com.pinde.sci.model.mo.StudySubject;
import com.pinde.sci.model.mo.StudySubjectDetail;

import java.util.List;
import java.util.Map;

public interface INjmuEduSubjectBiz {

    int addSubject(StudySubject studySubject) throws Exception;

    List<StudySubject> subjectList(StudySubject subject);

    StudySubject readSubject(String subjectFlow);

    /**
     *查询课程审核通过人数
     */
    int selectCountByAuditStatusId(String subjectFlow) throws  Exception;

    List<Map<String,String>> detailList(Map<String, String> param);

    void auditPassed(List<String> detailFlows);

    void auditPassedTwo(List<String> detailFlows);

    void auditUnPassed(List<String> detailFlows);

    void auditUnPassedTwo(List<String> detailFlows);

    void auditBack(List<String> detailFlows);

    /**
     * 保存预约课程
     */
    int saveSubject(StudySubject studySubject) throws Exception;
    /**
     * 查询发布课程
     */
    List<StudySubject> findAllSubject(String postStatus) throws Exception;

    /**
     * 查询预约人数未满的课程
     */
    List<StudySubject> findSubject(String postStatus) throws Exception;

    /**
     * 查询一条发布课程记录
     */
    StudySubject findBySubjectFlow(String subjectFlow) throws Exception;

    int findAppiontNum(String subjectFlow);

    List<StudySubjectDetail> findByDoctorFlow(String doctorFlow);

    StudySubjectDetail findBydetailFlow(String detailFlow);

    StudySubjectDetail getSubjectDetail(String subjectFlow, String userFlow);





}
