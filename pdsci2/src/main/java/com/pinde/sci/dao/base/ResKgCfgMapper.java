package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResKgCfg;
import com.pinde.sci.model.mo.ResKgCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResKgCfgMapper {
    int countByExample(ResKgCfgExample example);

    int deleteByExample(ResKgCfgExample example);

    int deleteByPrimaryKey(String cfgFlow);

    int insert(ResKgCfg record);

    int insertSelective(ResKgCfg record);

    List<ResKgCfg> selectByExample(ResKgCfgExample example);

    ResKgCfg selectByPrimaryKey(String cfgFlow);

    int updateByExampleSelective(@Param("record") ResKgCfg record, @Param("example") ResKgCfgExample example);

    int updateByExample(@Param("record") ResKgCfg record, @Param("example") ResKgCfgExample example);

    int updateByPrimaryKeySelective(ResKgCfg record);

    int updateByPrimaryKey(ResKgCfg record);
}