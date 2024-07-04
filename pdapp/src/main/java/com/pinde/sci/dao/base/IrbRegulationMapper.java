package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.IrbRegulation;
import com.pinde.sci.model.mo.IrbRegulationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IrbRegulationMapper {
    int countByExample(IrbRegulationExample example);

    int deleteByExample(IrbRegulationExample example);

    int deleteByPrimaryKey(String regFlow);

    int insert(IrbRegulation record);

    int insertSelective(IrbRegulation record);

    List<IrbRegulation> selectByExample(IrbRegulationExample example);

    IrbRegulation selectByPrimaryKey(String regFlow);

    int updateByExampleSelective(@Param("record") IrbRegulation record, @Param("example") IrbRegulationExample example);

    int updateByExample(@Param("record") IrbRegulation record, @Param("example") IrbRegulationExample example);

    int updateByPrimaryKeySelective(IrbRegulation record);

    int updateByPrimaryKey(IrbRegulation record);
}