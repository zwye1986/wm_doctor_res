package com.pinde.sci.dao.base;

import com.pinde.core.model.PubProjUser;
import com.pinde.core.model.PubProjUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubProjUserMapper {
    int countByExample(PubProjUserExample example);

    int deleteByExample(PubProjUserExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(PubProjUser record);

    int insertSelective(PubProjUser record);

    List<PubProjUser> selectByExample(PubProjUserExample example);

    PubProjUser selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") PubProjUser record, @Param("example") PubProjUserExample example);

    int updateByExample(@Param("record") PubProjUser record, @Param("example") PubProjUserExample example);

    int updateByPrimaryKeySelective(PubProjUser record);

    int updateByPrimaryKey(PubProjUser record);
}