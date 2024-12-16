package com.pinde.sci.dao.base;

import com.pinde.core.model.PubTrain;
import com.pinde.core.model.PubTrainExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubTrainMapper {
    int countByExample(PubTrainExample example);

    int deleteByExample(PubTrainExample example);

    int deleteByPrimaryKey(String trainFlow);

    int insert(PubTrain record);

    int insertSelective(PubTrain record);

    List<PubTrain> selectByExample(PubTrainExample example);

    PubTrain selectByPrimaryKey(String trainFlow);

    int updateByExampleSelective(@Param("record") PubTrain record, @Param("example") PubTrainExample example);

    int updateByExample(@Param("record") PubTrain record, @Param("example") PubTrainExample example);

    int updateByPrimaryKeySelective(PubTrain record);

    int updateByPrimaryKey(PubTrain record);
}