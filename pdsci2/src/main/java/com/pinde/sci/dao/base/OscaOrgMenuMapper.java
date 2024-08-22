package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.OscaOrgMenu;
import com.pinde.sci.model.mo.OscaOrgMenuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OscaOrgMenuMapper {
    int countByExample(OscaOrgMenuExample example);

    int deleteByExample(OscaOrgMenuExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(OscaOrgMenu record);

    int insertSelective(OscaOrgMenu record);

    List<OscaOrgMenu> selectByExample(OscaOrgMenuExample example);

    OscaOrgMenu selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") OscaOrgMenu record, @Param("example") OscaOrgMenuExample example);

    int updateByExample(@Param("record") OscaOrgMenu record, @Param("example") OscaOrgMenuExample example);

    int updateByPrimaryKeySelective(OscaOrgMenu record);

    int updateByPrimaryKey(OscaOrgMenu record);
}