package com.pinde.res.model.osca.mo;

import com.pinde.sci.model.mo.OscaSkillRoomTea;
import com.pinde.sci.model.mo.OscaSkillsAssessment;

import java.util.List;


public class OscaSkillsPartnerExt extends OscaSkillsAssessment{
    private List<OscaSkillRoomTea> roomTeas;

    public List<OscaSkillRoomTea> getRoomTeas() {
        return roomTeas;
    }

    public void setRoomTeas(List<OscaSkillRoomTea> roomTeas) {
        this.roomTeas = roomTeas;
    }

}
