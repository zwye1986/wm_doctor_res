package com.pinde.sci.dao.recruit;

import com.pinde.core.model.RecruitInfo;
import com.pinde.sci.model.recruit.RecruitInfoExt;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RecruitInfoExtMapper {

    List<RecruitInfoExt> searchAdmitInfoByFlow(@Param(value="recruitFlow")String recruitFlow);

    List<RecruitInfo> selectRecruitInfoByIdNo(@Param(value = "idNo")String idNo);

    List<RecruitInfoExt> selectRecruitInfoAboutAudit(HashMap map);

    List<String> searchTicket(@Param(value = "recruitYear")String recruitYear,@Param(value = "trainingSpeId")String trainingSpeId);


    List<RecruitInfoExt> searchCanExamRecruitInfo(Map<String, String> param);
    List<RecruitInfoExt> selectRecruitByUserFlow(@Param(value = "userFlow")String userFlow, @Param(value = "orgFlow")String orgFlow);

    List<RecruitInfoExt> searchRecruitInfoByFlow(@Param("recruitFlow") String recruitFlow);
}
