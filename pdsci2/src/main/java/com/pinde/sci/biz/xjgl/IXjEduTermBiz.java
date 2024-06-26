package com.pinde.sci.biz.xjgl;

import com.pinde.sci.form.xjgl.XjEduUserExtInfoForm;
import com.pinde.sci.form.xjgl.XjEduUserForm;
import com.pinde.sci.form.xjgl.XjRegisterDateForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.xjgl.XjEduCourseMajorExt;
import com.pinde.sci.model.xjgl.XjEduScheduleClassExt;
import com.pinde.sci.model.xjgl.XjEduUserExt;
import com.pinde.sci.model.xjgl.XjEduUserInfoExt;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IXjEduTermBiz {

    EduTerm getEduTermByFlow(String flow);

    int saveTerm(EduTerm term);

    List<EduTerm> seachlistByTerm(EduTerm term);

    List<XjEduScheduleClassExt> seachClassByMap(Map<String, Object> param);

    List<EduTerm> searchBySessionAndGrade(String sessionNumber,String gradeTermId);

    List<Map<String,String>> searchTeachersClassSchedule(Map<String,String> paramMap);

    List<Map<String,String>> getFirstClassByFlow(String recordFlow);

    List<Map<String,String>> getFirstClassOfGzByFlow(String recordFlow);
}
