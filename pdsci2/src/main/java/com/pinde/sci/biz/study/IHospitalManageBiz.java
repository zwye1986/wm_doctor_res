package com.pinde.sci.biz.study;

import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.StudySubject;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author xieshihai
 */
public interface IHospitalManageBiz {
      int addSubject(StudySubject studySubject) throws Exception;

      List<StudySubject> subjectList(StudySubject subject);

      List<Map<String,Object>> searchStudents(Map<String, Object> paramMap);

      void saveUser(SysUser user, ResDoctor doctor);

      int importUserFromExcel(MultipartFile file);

      StudySubject readSubject(String subjectFlow);

      List<Map<String,String>> detailList(Map<String, String> param);

      void auditBack(List<String> detailFlows);

      void auditPassed(List<String> detailFlows);

      void auditUnPassed(List<String> detailFlows);

}
