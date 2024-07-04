package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.IrbCfg;
import com.pinde.sci.model.mo.IrbCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IrbCfgMapper {
    int countByExample(IrbCfgExample example);

    int deleteByExample(IrbCfgExample example);

    int deleteByPrimaryKey(String cfgCode);

    int insert(IrbCfg record);

    int insertSelective(IrbCfg record);

    List<IrbCfg> selectByExampleWithBLOBs(IrbCfgExample example);

    List<IrbCfg> selectByExample(IrbCfgExample example);

    IrbCfg selectByPrimaryKey(String cfgCode);

    int updateByExampleSelective(@Param("record") IrbCfg record, @Param("example") IrbCfgExample example);

    int updateByExampleWithBLOBs(@Param("record") IrbCfg record, @Param("example") IrbCfgExample example);

    int updateByExample(@Param("record") IrbCfg record, @Param("example") IrbCfgExample example);

    int updateByPrimaryKeySelective(IrbCfg record);

    int updateByPrimaryKeyWithBLOBs(IrbCfg record);

    int updateByPrimaryKey(IrbCfg record);
}