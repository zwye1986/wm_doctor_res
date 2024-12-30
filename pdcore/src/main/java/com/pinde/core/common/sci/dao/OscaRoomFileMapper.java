package com.pinde.core.common.sci.dao;

import com.pinde.core.model.OscaRoomFile;
import com.pinde.core.model.OscaRoomFileExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OscaRoomFileMapper {
    int countByExample(OscaRoomFileExample example);

    int deleteByExample(OscaRoomFileExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(OscaRoomFile record);

    int insertSelective(OscaRoomFile record);

    List<OscaRoomFile> selectByExample(OscaRoomFileExample example);

    OscaRoomFile selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") OscaRoomFile record, @Param("example") OscaRoomFileExample example);

    int updateByExample(@Param("record") OscaRoomFile record, @Param("example") OscaRoomFileExample example);

    int updateByPrimaryKeySelective(OscaRoomFile record);

    int updateByPrimaryKey(OscaRoomFile record);
}