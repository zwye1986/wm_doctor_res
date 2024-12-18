package com.pinde.sci.dao.base;

import com.pinde.core.model.SysCfgLog;
import com.pinde.core.model.SysCfgLogExample;
import com.pinde.core.model.SysCfgLogWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysCfgLogMapper {
    int countByExample(SysCfgLogExample example);

    int deleteByExample(SysCfgLogExample example);

    int deleteByPrimaryKey(String cfgFlow);

    int insert(SysCfgLogWithBLOBs record);

    int insertSelective(SysCfgLogWithBLOBs record);

    List<SysCfgLogWithBLOBs> selectByExampleWithBLOBs(SysCfgLogExample example);

    List<SysCfgLog> selectByExample(SysCfgLogExample example);

    SysCfgLogWithBLOBs selectByPrimaryKey(String cfgFlow);

    int updateByExampleSelective(@Param("record") SysCfgLogWithBLOBs record, @Param("example") SysCfgLogExample example);

    int updateByExampleWithBLOBs(@Param("record") SysCfgLogWithBLOBs record, @Param("example") SysCfgLogExample example);

    int updateByExample(@Param("record") SysCfgLog record, @Param("example") SysCfgLogExample example);

    int updateByPrimaryKeySelective(SysCfgLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysCfgLogWithBLOBs record);

    int updateByPrimaryKey(SysCfgLog record);
}