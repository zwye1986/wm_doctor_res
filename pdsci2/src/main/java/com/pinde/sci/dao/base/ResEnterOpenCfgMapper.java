package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResEnterOpenCfg;
import com.pinde.sci.model.mo.ResEnterOpenCfgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResEnterOpenCfgMapper {
    int countByExample(ResEnterOpenCfgExample example);

    int deleteByExample(ResEnterOpenCfgExample example);

    int deleteByPrimaryKey(String cfgFlow);

    int insert(ResEnterOpenCfg record);

    int insertSelective(ResEnterOpenCfg record);

    List<ResEnterOpenCfg> selectByExample(ResEnterOpenCfgExample example);

    ResEnterOpenCfg selectByPrimaryKey(String cfgFlow);

    int updateByExampleSelective(@Param("record") ResEnterOpenCfg record, @Param("example") ResEnterOpenCfgExample example);

    int updateByExample(@Param("record") ResEnterOpenCfg record, @Param("example") ResEnterOpenCfgExample example);

    int updateByPrimaryKeySelective(ResEnterOpenCfg record);

    int updateByPrimaryKey(ResEnterOpenCfg record);
}