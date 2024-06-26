package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.OscaSkillsStationFile;
import com.pinde.sci.model.mo.OscaSkillsStationFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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