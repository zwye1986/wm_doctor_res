package com.pinde.core.common.sci.dao;

import com.pinde.core.model.OscaSkillRoom;
import com.pinde.core.model.OscaSkillRoomExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OscaSkillRoomMapper {
    int countByExample(OscaSkillRoomExample example);

    int deleteByExample(OscaSkillRoomExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(OscaSkillRoom record);

    int insertSelective(OscaSkillRoom record);

    List<OscaSkillRoom> selectByExample(OscaSkillRoomExample example);

    OscaSkillRoom selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") OscaSkillRoom record, @Param("example") OscaSkillRoomExample example);

    int updateByExample(@Param("record") OscaSkillRoom record, @Param("example") OscaSkillRoomExample example);

    int updateByPrimaryKeySelective(OscaSkillRoom record);

    int updateByPrimaryKey(OscaSkillRoom record);
}