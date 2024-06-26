package com.pinde.sci.biz.njmuedu;

import com.pinde.sci.model.mo.EduExamScore;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.njmuedu.EduExamScoreExt;
import com.pinde.sci.model.njmuedu.EduUserCourseExt;
import com.pinde.sci.model.njmuedu.EduUserExt;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface INjmuEduExamScoreBiz {
    int saveEduExamScore(EduExamScore eduExamScore);

    int saveEduExamScore(SysUser user, List<EduExamScore> eduExamScoreList);

    int importUserFromExcel(MultipartFile file);

    List<EduExamScoreExt> searchEduExamScoreList(Map<String, Object> paramMap);

    Map<String, Object> findEduExamScoreByList(List<EduUserExt> eduUserExtList ,String courseFlow);

    List<EduExamScore> findEduExamScore(String userFlow,String courseFlow);

    List<EduUserCourseExt> searchEduUserList(Map<String,Object> paramMap);

    Map<String,Map<String,Object>> searchStuScore(List<EduUserExt> eduUserExtList);

    Map<String,Map<String,Object>> searchStuCourse(List<EduUserExt> eduUserExtList);

    Map<String, Object> searchByStudentList(List<EduUserExt> eduUserExtList,String courseFlow);

    Map<String,Map<String,Object>> searchFinish(List<EduUserExt> eduUserExtList);

    Map<String,Object> findEduStudentCourseByList(List<EduUserExt> eduUserExtList,String courseFlow);

    int searchByIsqualified(String userFlow);

    int searchByStudyStatusId(String userFlow);

}
