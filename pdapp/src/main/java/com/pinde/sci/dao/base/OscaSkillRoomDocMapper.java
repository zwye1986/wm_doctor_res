package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.OscaSkillRoomDoc;
import com.pinde.sci.model.mo.OscaSkillRoomDocExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OscaSkillRoomDocMapper {
    int countByExample(OscaSkillRoomDocExample example);

    int deleteByExample(OscaSkillRoomDocExample example);

    int deleteByPrimaryKey(String docRoomFlow);

    int insert(OscaSkillRoomDoc record);

    int insertSelective(OscaSkillRoomDoc record);

    List<OscaSkillRoomDoc> selectByExample(OscaSkillRoomDocExample example);

    OscaSkillRoomDoc selectByPrimaryKey(String docRoomFlow);

    int updateByExampleSelective(@Param("record") OscaSkillRoomDoc record, @Param("example") OscaSkillRoomDocExample example);

    int updateByExample(@Param("record") OscaSkillRoomDoc record, @Param("example") OscaSkillRoomDocExample example);

    int updateByPrimaryKeySelective(OscaSkillRoomDoc record);

    int updateByPrimaryKey(OscaSkillRoomDoc record);
}