package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResPassScoreCfg;
import com.pinde.core.model.ResPassScoreCfgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResPassScoreCfgMapper {
    int countByExample(ResPassScoreCfgExample example);

    int deleteByExample(ResPassScoreCfgExample example);

    int deleteByPrimaryKey(String cfgYear);

    int insert(ResPassScoreCfg record);

    int insertSelective(ResPassScoreCfg record);

    List<ResPassScoreCfg> selectByExample(ResPassScoreCfgExample example);

    ResPassScoreCfg selectByPrimaryKey(String cfgYear);

    int updateByExampleSelective(@Param("record") ResPassScoreCfg record, @Param("example") ResPassScoreCfgExample example);

    int updateByExample(@Param("record") ResPassScoreCfg record, @Param("example") ResPassScoreCfgExample example);

    int updateByPrimaryKeySelective(ResPassScoreCfg record);

    int updateByPrimaryKey(ResPassScoreCfg record);
}