package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResGradeBorderline;
import com.pinde.core.model.ResGradeBorderlineExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResGradeBorderlineMapper {
    int countByExample(ResGradeBorderlineExample example);

    int deleteByExample(ResGradeBorderlineExample example);

    int deleteByPrimaryKey(String borderlineFlow);

    int insert(ResGradeBorderline record);

    int insertSelective(ResGradeBorderline record);

    List<ResGradeBorderline> selectByExample(ResGradeBorderlineExample example);

    ResGradeBorderline selectByPrimaryKey(String borderlineFlow);

    int updateByExampleSelective(@Param("record") ResGradeBorderline record, @Param("example") ResGradeBorderlineExample example);

    int updateByExample(@Param("record") ResGradeBorderline record, @Param("example") ResGradeBorderlineExample example);

    int updateByPrimaryKeySelective(ResGradeBorderline record);

    int updateByPrimaryKey(ResGradeBorderline record);
}