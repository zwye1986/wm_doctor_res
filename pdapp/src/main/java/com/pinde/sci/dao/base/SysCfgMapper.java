package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SysCfg;
import com.pinde.sci.model.mo.SysCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysCfgMapper {
    int countByExample(SysCfgExample example);

    int deleteByExample(SysCfgExample example);

    int deleteByPrimaryKey(String cfgCode);

    int insert(SysCfg record);

    int insertSelective(SysCfg record);

    List<SysCfg> selectByExampleWithBLOBs(SysCfgExample example);

    List<SysCfg> selectByExample(SysCfgExample example);

    SysCfg selectByPrimaryKey(String cfgCode);

    int updateByExampleSelective(@Param("record") SysCfg record, @Param("example") SysCfgExample example);

    int updateByExampleWithBLOBs(@Param("record") SysCfg record, @Param("example") SysCfgExample example);

    int updateByExample(@Param("record") SysCfg record, @Param("example") SysCfgExample example);

    int updateByPrimaryKeySelective(SysCfg record);

    int updateByPrimaryKeyWithBLOBs(SysCfg record);

    int updateByPrimaryKey(SysCfg record);
}