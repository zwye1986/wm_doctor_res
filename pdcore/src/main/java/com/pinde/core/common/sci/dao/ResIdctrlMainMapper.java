package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResIdctrlMain;
import com.pinde.core.model.ResIdctrlMainExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResIdctrlMainMapper {
    int countByExample(ResIdctrlMainExample example);

    int deleteByExample(ResIdctrlMainExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResIdctrlMain record);

    int insertSelective(ResIdctrlMain record);

    List<ResIdctrlMain> selectByExample(ResIdctrlMainExample example);

    ResIdctrlMain selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResIdctrlMain record, @Param("example") ResIdctrlMainExample example);

    int updateByExample(@Param("record") ResIdctrlMain record, @Param("example") ResIdctrlMainExample example);

    int updateByPrimaryKeySelective(ResIdctrlMain record);

    int updateByPrimaryKey(ResIdctrlMain record);
}