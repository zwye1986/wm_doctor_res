package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubTrain;
import com.pinde.sci.model.mo.PubTrainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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