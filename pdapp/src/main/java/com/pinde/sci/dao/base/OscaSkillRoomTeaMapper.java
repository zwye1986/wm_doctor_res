package com.pinde.sci.dao.base;

import com.pinde.core.model.OscaSkillRoomTea;
import com.pinde.core.model.OscaSkillRoomTeaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OscaSkillRoomTeaMapper {
    int countByExample(OscaSkillRoomTeaExample example);

    int deleteByExample(OscaSkillRoomTeaExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(OscaSkillRoomTea record);

    int insertSelective(OscaSkillRoomTea record);

    List<OscaSkillRoomTea> selectByExample(OscaSkillRoomTeaExample example);

    OscaSkillRoomTea selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") OscaSkillRoomTea record, @Param("example") OscaSkillRoomTeaExample example);

    int updateByExample(@Param("record") OscaSkillRoomTea record, @Param("example") OscaSkillRoomTeaExample example);

    int updateByPrimaryKeySelective(OscaSkillRoomTea record);

    int updateByPrimaryKey(OscaSkillRoomTea record);
}