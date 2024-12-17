package com.pinde.sci.dao.base;

import com.pinde.core.model.ResBaseeval;
import com.pinde.core.model.ResBaseevalExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResBaseevalMapper {
    int countByExample(ResBaseevalExample example);

    int deleteByExample(ResBaseevalExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResBaseeval record);

    int insertSelective(ResBaseeval record);

    List<ResBaseeval> selectByExample(ResBaseevalExample example);

    ResBaseeval selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResBaseeval record, @Param("example") ResBaseevalExample example);

    int updateByExample(@Param("record") ResBaseeval record, @Param("example") ResBaseevalExample example);

    int updateByPrimaryKeySelective(ResBaseeval record);

    int updateByPrimaryKey(ResBaseeval record);
}