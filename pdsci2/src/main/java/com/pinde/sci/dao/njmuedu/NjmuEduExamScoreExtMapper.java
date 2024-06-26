package com.pinde.sci.dao.njmuedu;

import com.pinde.sci.model.njmuedu.EduExamScoreExt;
import com.pinde.sci.model.njmuedu.EduUserCourseExt;

import java.util.List;
import java.util.Map;

public interface NjmuEduExamScoreExtMapper {

    List<EduExamScoreExt> searchEduExamScoreList(Map<String,Object> paramMap);

    List<EduUserCourseExt> searchEduUserList(Map<String,Object> paramMap);
}
