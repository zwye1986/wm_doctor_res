package com.pinde.sci.dao.base;

import com.pinde.core.model.PubProjSearch;
import com.pinde.core.model.PubProjSearchExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubProjSearchMapper {
    int countByExample(PubProjSearchExample example);

    int deleteByExample(PubProjSearchExample example);

    int deleteByPrimaryKey(String projSearchFlow);

    int insert(PubProjSearch record);

    int insertSelective(PubProjSearch record);

    List<PubProjSearch> selectByExample(PubProjSearchExample example);

    PubProjSearch selectByPrimaryKey(String projSearchFlow);

    int updateByExampleSelective(@Param("record") PubProjSearch record, @Param("example") PubProjSearchExample example);

    int updateByExample(@Param("record") PubProjSearch record, @Param("example") PubProjSearchExample example);

    int updateByPrimaryKeySelective(PubProjSearch record);

    int updateByPrimaryKey(PubProjSearch record);
}