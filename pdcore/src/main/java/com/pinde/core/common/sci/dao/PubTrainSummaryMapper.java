package com.pinde.core.common.sci.dao;

import com.pinde.core.model.PubTrainSummary;
import com.pinde.core.model.PubTrainSummaryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubTrainSummaryMapper {
    int countByExample(PubTrainSummaryExample example);

    int deleteByExample(PubTrainSummaryExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(PubTrainSummary record);

    int insertSelective(PubTrainSummary record);

    List<PubTrainSummary> selectByExampleWithBLOBs(PubTrainSummaryExample example);

    List<PubTrainSummary> selectByExample(PubTrainSummaryExample example);

    PubTrainSummary selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") PubTrainSummary record, @Param("example") PubTrainSummaryExample example);

    int updateByExampleWithBLOBs(@Param("record") PubTrainSummary record, @Param("example") PubTrainSummaryExample example);

    int updateByExample(@Param("record") PubTrainSummary record, @Param("example") PubTrainSummaryExample example);

    int updateByPrimaryKeySelective(PubTrainSummary record);

    int updateByPrimaryKeyWithBLOBs(PubTrainSummary record);

    int updateByPrimaryKey(PubTrainSummary record);
}