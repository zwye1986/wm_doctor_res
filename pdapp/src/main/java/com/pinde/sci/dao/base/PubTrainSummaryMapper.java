package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubTrainSummary;
import com.pinde.sci.model.mo.PubTrainSummaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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