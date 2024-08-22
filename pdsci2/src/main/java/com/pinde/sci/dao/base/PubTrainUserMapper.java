package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubTrainUser;
import com.pinde.sci.model.mo.PubTrainUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubTrainUserMapper {
    int countByExample(PubTrainUserExample example);

    int deleteByExample(PubTrainUserExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(PubTrainUser record);

    int insertSelective(PubTrainUser record);

    List<PubTrainUser> selectByExample(PubTrainUserExample example);

    PubTrainUser selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") PubTrainUser record, @Param("example") PubTrainUserExample example);

    int updateByExample(@Param("record") PubTrainUser record, @Param("example") PubTrainUserExample example);

    int updateByPrimaryKeySelective(PubTrainUser record);

    int updateByPrimaryKey(PubTrainUser record);
}