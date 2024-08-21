package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.OscaSkillRoom;
import com.pinde.sci.model.mo.OscaSkillRoomExample;
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