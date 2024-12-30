package com.pinde.core.common.sci.dao;

import com.pinde.core.model.OscaSkillRoomTea;
import com.pinde.core.model.OscaSkillRoomTeaExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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