package com.pinde.sci.dao.dwjxres;

import com.pinde.sci.model.mo.ResDoctorRecruit;
import com.pinde.sci.model.res.StuUserExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EvaluationExtMapper {
    List<StuUserExt> searchStuUserForDept(Map<String, Object> mp);

    List<StuUserExt> searchStuUserForTeacher(Map<String, Object> mp);
}
