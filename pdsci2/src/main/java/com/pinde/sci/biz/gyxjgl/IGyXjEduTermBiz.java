package com.pinde.sci.biz.gyxjgl;

import com.pinde.sci.form.gyxjgl.XjEduUserExtInfoForm;
import com.pinde.sci.form.gyxjgl.XjEduUserForm;
import com.pinde.sci.form.gyxjgl.XjRegisterDateForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.gyxjgl.XjEduCourseMajorExt;
import com.pinde.sci.model.gyxjgl.XjEduScheduleClassExt;
import com.pinde.sci.model.gyxjgl.XjEduUserExt;
import com.pinde.sci.model.gyxjgl.XjEduUserInfoExt;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IGyXjEduTermBiz {

    EduTerm getEduTermByFlow(String flow);

    int saveTerm(EduTerm term);

    List<EduTerm> seachlistByTerm(EduTerm term);

    List<XjEduScheduleClassExt> seachClassByMap(Map<String, Object> param);

    List<EduTerm> searchBySessionAndGrade(String sessionNumber,String gradeTermId,String classId);

    List<Map<String,String>> searchTeachersClassSchedule(Map<String,String> paramMap);

    List<Map<String,String>> getFirstClassByFlow(String recordFlow);

    List<Map<String,String>> getFirstClassOfGzByFlow(String recordFlow);
}
