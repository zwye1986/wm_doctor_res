package com.pinde.core.common.sci.dao;

import com.pinde.core.model.PortalColumn;
import com.pinde.core.model.PortalColumnExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PortalColumnMapper {
    int countByExample(PortalColumnExample example);

    int deleteByExample(PortalColumnExample example);

    int deleteByPrimaryKey(String columnFlow);

    int insert(PortalColumn record);

    int insertSelective(PortalColumn record);

    List<PortalColumn> selectByExample(PortalColumnExample example);

    PortalColumn selectByPrimaryKey(String columnFlow);

    int updateByExampleSelective(@Param("record") PortalColumn record, @Param("example") PortalColumnExample example);

    int updateByExample(@Param("record") PortalColumn record, @Param("example") PortalColumnExample example);

    int updateByPrimaryKeySelective(PortalColumn record);

    int updateByPrimaryKey(PortalColumn record);
}