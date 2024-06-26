package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResOrgTime;
import com.pinde.sci.model.mo.ResOrgTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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