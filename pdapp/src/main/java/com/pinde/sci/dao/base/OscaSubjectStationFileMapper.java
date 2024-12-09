package com.pinde.sci.dao.base;

import com.pinde.core.model.OscaSubjectStationFile;
import com.pinde.core.model.OscaSubjectStationFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OscaSubjectStationFileMapper {
    int countByExample(OscaSubjectStationFileExample example);

    int deleteByExample(OscaSubjectStationFileExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(OscaSubjectStationFile record);

    int insertSelective(OscaSubjectStationFile record);

    List<OscaSubjectStationFile> selectByExample(OscaSubjectStationFileExample example);

    OscaSubjectStationFile selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") OscaSubjectStationFile record, @Param("example") OscaSubjectStationFileExample example);

    int updateByExample(@Param("record") OscaSubjectStationFile record, @Param("example") OscaSubjectStationFileExample example);

    int updateByPrimaryKeySelective(OscaSubjectStationFile record);

    int updateByPrimaryKey(OscaSubjectStationFile record);
}