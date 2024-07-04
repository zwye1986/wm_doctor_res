package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubRegulation;
import com.pinde.sci.model.mo.PubRegulationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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