package com.pinde.res.model.osca.mo;

import com.pinde.sci.model.mo.OscaSkillRoom;
import com.pinde.sci.model.mo.OscaSkillsAssessment;

import java.util.List;


public class OscaSkillsRoomExt extends OscaSkillsAssessment {
    private List<OscaSkillRoom> skillRooms;

    public List<OscaSkillRoom> getSkillRooms() {
        return skillRooms;
    }

    public void setSkillRooms(List<OscaSkillRoom> skillRooms) {
        this.skillRooms = skillRooms;
    }
}
