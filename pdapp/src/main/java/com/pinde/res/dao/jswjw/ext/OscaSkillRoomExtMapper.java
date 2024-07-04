package com.pinde.res.dao.jswjw.ext;

import com.pinde.res.model.jswjw.mo.OscaSkillRoomExt;
import com.pinde.sci.model.mo.OscaDoctorAssessment;
import com.pinde.sci.model.mo.OscaSkillsAssessment;
import com.pinde.sci.model.mo.SysUser;

import java.util.List;
import java.util.Map;

public interface OscaSkillRoomExtMapper {

    OscaSkillRoomExt getDocRoom(Map<String, Object> param);

    OscaSkillRoomExt getStationBestRoom(Map<String, Object> param);

    int checkIsWait(Map<String, Object> param);

    int checkIsAssess(Map<String, Object> param);

    List<OscaSkillRoomExt> getStationAllRoom(Map<String, Object> param);
}
