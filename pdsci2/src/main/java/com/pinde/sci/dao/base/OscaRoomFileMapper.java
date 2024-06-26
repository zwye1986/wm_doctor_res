package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.OscaRoomFile;
import com.pinde.sci.model.mo.OscaRoomFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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