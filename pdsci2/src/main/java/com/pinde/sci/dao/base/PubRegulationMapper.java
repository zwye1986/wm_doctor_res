package com.pinde.sci.dao.base;

import com.pinde.core.model.PubRegulation;
import com.pinde.core.model.PubRegulationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubRegulationMapper {
    int countByExample(PubRegulationExample example);

    int deleteByExample(PubRegulationExample example);

    int deleteByPrimaryKey(String regulationFlow);

    int insert(PubRegulation record);

    int insertSelective(PubRegulation record);

    List<PubRegulation> selectByExample(PubRegulationExample example);

    PubRegulation selectByPrimaryKey(String regulationFlow);

    int updateByExampleSelective(@Param("record") PubRegulation record, @Param("example") PubRegulationExample example);

    int updateByExample(@Param("record") PubRegulation record, @Param("example") PubRegulationExample example);

    int updateByPrimaryKeySelective(PubRegulation record);

    int updateByPrimaryKey(PubRegulation record);
}