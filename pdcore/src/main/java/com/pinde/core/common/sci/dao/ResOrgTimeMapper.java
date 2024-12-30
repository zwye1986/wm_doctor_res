package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResOrgTime;
import com.pinde.core.model.ResOrgTimeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResOrgTimeMapper {
    int countByExample(ResOrgTimeExample example);

    int deleteByExample(ResOrgTimeExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResOrgTime record);

    int insertSelective(ResOrgTime record);

    List<ResOrgTime> selectByExample(ResOrgTimeExample example);

    ResOrgTime selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResOrgTime record, @Param("example") ResOrgTimeExample example);

    int updateByExample(@Param("record") ResOrgTime record, @Param("example") ResOrgTimeExample example);

    int updateByPrimaryKeySelective(ResOrgTime record);

    int updateByPrimaryKey(ResOrgTime record);
}