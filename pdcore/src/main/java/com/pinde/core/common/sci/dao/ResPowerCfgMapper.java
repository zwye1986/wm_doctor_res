package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResPowerCfg;
import com.pinde.core.model.ResPowerCfgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResPowerCfgMapper {
    int countByExample(ResPowerCfgExample example);

    int deleteByExample(ResPowerCfgExample example);

    int deleteByPrimaryKey(String cfgCode);

    int insert(ResPowerCfg record);

    int insertSelective(ResPowerCfg record);

    List<ResPowerCfg> selectByExample(ResPowerCfgExample example);

    ResPowerCfg selectByPrimaryKey(String cfgCode);

    int updateByExampleSelective(@Param("record") ResPowerCfg record, @Param("example") ResPowerCfgExample example);

    int updateByExample(@Param("record") ResPowerCfg record, @Param("example") ResPowerCfgExample example);

    int updateByPrimaryKeySelective(ResPowerCfg record);

    int updateByPrimaryKey(ResPowerCfg record);
}