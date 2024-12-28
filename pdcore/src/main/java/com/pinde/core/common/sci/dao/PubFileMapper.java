package com.pinde.core.common.sci.dao;

import com.pinde.core.model.PubFile;
import com.pinde.core.model.PubFileExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubFileMapper {
    int countByExample(PubFileExample example);

    int deleteByExample(PubFileExample example);

    int deleteByPrimaryKey(String fileFlow);

    int insert(PubFile record);

    int insertSelective(PubFile record);

    List<PubFile> selectByExampleWithBLOBs(PubFileExample example);

    List<PubFile> selectByExample(PubFileExample example);

    PubFile selectByPrimaryKey(String fileFlow);

    int updateByExampleSelective(@Param("record") PubFile record, @Param("example") PubFileExample example);

    int updateByExampleWithBLOBs(@Param("record") PubFile record, @Param("example") PubFileExample example);

    int updateByExample(@Param("record") PubFile record, @Param("example") PubFileExample example);

    int updateByPrimaryKeySelective(PubFile record);

    int updateByPrimaryKeyWithBLOBs(PubFile record);

    int updateByPrimaryKey(PubFile record);
}