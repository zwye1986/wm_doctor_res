package com.pinde.res.dao.jswjw.ext;


import com.pinde.core.model.ResDocotrDelayTeturn;
import com.pinde.core.model.ResDocotrDelayTeturnExample;
import com.pinde.core.model.ResDoctor;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResDocotrDelayTeturnExtMapper {

    List<Map<String,String>> findTrainCharts(@Param(value = "orgFlowList") List<String> orgFlowList,
                                             @Param(value = "year") String sessionNumber, @Param(value = "speName") String speName, @Param(value = "docTypeList") List<String> docTypeList, @Param(value = "resTrainingSpeId") String resTrainingSpeId);

    List<ResDocotrDelayTeturn> searchInfoForUni(@Param(value = "docotrDelayTeturn") ResDocotrDelayTeturn docotrDelayTeturn, @Param(value = "resDoctor") ResDoctor currdoctor);

    List<ResDocotrDelayTeturn> searchInfo(Map<String, Object> paramMap);

    List<ResDocotrDelayTeturn> searchInfoNew(ResDocotrDelayTeturnExample example);
}
