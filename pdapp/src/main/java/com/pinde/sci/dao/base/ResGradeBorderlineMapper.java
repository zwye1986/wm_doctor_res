package com.pinde.sci.dao.base;

import java.util.List;
import org.apache.ibatis.annotations.Param;

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