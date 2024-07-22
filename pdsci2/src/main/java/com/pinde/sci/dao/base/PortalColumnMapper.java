package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PortalColumn;
import com.pinde.sci.model.mo.PortalColumnExample;
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