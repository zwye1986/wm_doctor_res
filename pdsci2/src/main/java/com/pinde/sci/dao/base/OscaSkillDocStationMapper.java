package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.OscaSkillDocStation;
import com.pinde.sci.model.mo.OscaSkillDocStationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OscaSkillDocStationMapper {
    int countByExample(OscaSkillDocStationExample example);

    int deleteByExample(OscaSkillDocStationExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(OscaSkillDocStation record);

    int insertSelective(OscaSkillDocStation record);

    List<OscaSkillDocStation> selectByExample(OscaSkillDocStationExample example);

    OscaSkillDocStation selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") OscaSkillDocStation record, @Param("example") OscaSkillDocStationExample example);

    int updateByExample(@Param("record") OscaSkillDocStation record, @Param("example") OscaSkillDocStationExample example);

    int updateByPrimaryKeySelective(OscaSkillDocStation record);

    int updateByPrimaryKey(OscaSkillDocStation record);
}