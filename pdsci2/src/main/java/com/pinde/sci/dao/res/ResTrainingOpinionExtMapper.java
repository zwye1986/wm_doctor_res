package com.pinde.sci.dao.res;

import com.pinde.sci.model.mo.ResAnnualAssessment;
import com.pinde.sci.model.mo.ResTrainingOpinion;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.res.AnnualAssessmentExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResTrainingOpinionExtMapper {

    List<ResTrainingOpinion> searchOpinionByUni(@Param(value = "content") String content,@Param(value = "orgFlow") String orgFlow,@Param(value = "org") SysOrg org);

    List<ResTrainingOpinion> searchOpinionByUni4hb(Map<String,Object> paramMap);

    List<ResTrainingOpinion> searchByOpinionUserContentAndReplayStatusNew(@Param(value = "opinionUserContent")String opinionUserContent,
                                                                          @Param(value = "replayStatus")String replayStatus, @Param(value = "orgFlow")String orgFlow, @Param(value = "resTrainingSpeId")String resTrainingSpeId);
}
