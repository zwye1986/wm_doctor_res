package com.pinde.res.dao.jswjw.ext;

import com.pinde.sci.model.mo.OscaSkillRoomDoc;
import com.pinde.sci.model.mo.OscaTeaScanDoc;

import java.util.List;
import java.util.Map;

public interface OscaSkillRoomDocExtMapper {
    OscaSkillRoomDoc getOscaSkillRoomDocByDoc(Map<String, Object> param);

    Map<String,Object> getDocStationAllScore(Map<String, String> param);

    Map<String,Object> getDocStationAllSaveScore(Map<String, String> param);

    Map<String,Object> getDocStationAllSaveScoreByParenterFlow(Map<String, String> param);

    List<OscaTeaScanDoc> getOscaNotSubmitInfo();
}
