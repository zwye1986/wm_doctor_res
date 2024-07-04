package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubProjSearch;
import com.pinde.sci.model.mo.PubProjSearchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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