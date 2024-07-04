package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FstuScoreConfig;
import com.pinde.sci.model.mo.FstuScoreConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FstuScoreConfigMapper {
    int countByExample(FstuScoreConfigExample example);

    int deleteByExample(FstuScoreConfigExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(FstuScoreConfig record);

    int insertSelective(FstuScoreConfig record);

    List<FstuScoreConfig> selectByExample(FstuScoreConfigExample example);

    FstuScoreConfig selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") FstuScoreConfig record, @Param("example") FstuScoreConfigExample example);

    int updateByExample(@Param("record") FstuScoreConfig record, @Param("example") FstuScoreConfigExample example);

    int updateByPrimaryKeySelective(FstuScoreConfig record);

    int updateByPrimaryKey(FstuScoreConfig record);
}