package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.GcpCfg;
import com.pinde.sci.model.mo.GcpCfgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GcpCfgMapper {
    int countByExample(GcpCfgExample example);

    int deleteByExample(GcpCfgExample example);

    int deleteByPrimaryKey(String cfgCode);

    int insert(GcpCfg record);

    int insertSelective(GcpCfg record);

    List<GcpCfg> selectByExampleWithBLOBs(GcpCfgExample example);

    List<GcpCfg> selectByExample(GcpCfgExample example);

    GcpCfg selectByPrimaryKey(String cfgCode);

    int updateByExampleSelective(@Param("record") GcpCfg record, @Param("example") GcpCfgExample example);

    int updateByExampleWithBLOBs(@Param("record") GcpCfg record, @Param("example") GcpCfgExample example);

    int updateByExample(@Param("record") GcpCfg record, @Param("example") GcpCfgExample example);

    int updateByPrimaryKeySelective(GcpCfg record);

    int updateByPrimaryKeyWithBLOBs(GcpCfg record);

    int updateByPrimaryKey(GcpCfg record);
}