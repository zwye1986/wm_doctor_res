package com.pinde.core.common.sci.dao;

import com.pinde.core.model.OscaSkillsStationFile;
import com.pinde.core.model.OscaSkillsStationFileExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OscaSkillsStationFileMapper {
    int countByExample(OscaSkillsStationFileExample example);

    int deleteByExample(OscaSkillsStationFileExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(OscaSkillsStationFile record);

    int insertSelective(OscaSkillsStationFile record);

    List<OscaSkillsStationFile> selectByExample(OscaSkillsStationFileExample example);

    OscaSkillsStationFile selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") OscaSkillsStationFile record, @Param("example") OscaSkillsStationFileExample example);

    int updateByExample(@Param("record") OscaSkillsStationFile record, @Param("example") OscaSkillsStationFileExample example);

    int updateByPrimaryKeySelective(OscaSkillsStationFile record);

    int updateByPrimaryKey(OscaSkillsStationFile record);
}