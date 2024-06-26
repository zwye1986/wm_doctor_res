package com.pinde.sci.dao.njmuedu;

import com.pinde.sci.model.mo.StudySubject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2016-10-18.
 */
public interface NjmuEduSubjectExtMapper {

    List<Map<String,Object>> searchStudents(Map<String, Object> paramMap);

    List<Map<String,String>> detailList(Map<String, String> param);

    void auditBack(@Param("detailFlows") List<String> detailFlows);

    void auditPassed(@Param("detailFlows") List<String> detailFlows);

    void auditPassedTwo(@Param("detailFlows") List<String> detailFlows);

    void auditUnPassed(@Param("detailFlows") List<String> detailFlows);

    void auditUnPassedTwo(@Param("detailFlows") List<String> detailFlows);

    List<StudySubject> findSubject();

    int findAppiontNum(@Param("subjectFlow") String subjectFlow);
}
