package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SchAutoArrangeCfg;
import com.pinde.sci.model.mo.SchAutoArrangeCfgExample;
import java.util.List;

import com.pinde.sci.model.mo.SchRotation;
import org.apache.ibatis.annotations.Param;

public interface SchAutoArrangeCfgMapper {
    int countByExample(SchAutoArrangeCfgExample example);

    int deleteByExample(SchAutoArrangeCfgExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SchAutoArrangeCfg record);

    int insertSelective(SchAutoArrangeCfg record);

    List<SchAutoArrangeCfg> selectByExample(SchAutoArrangeCfgExample example);

    SchAutoArrangeCfg selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SchAutoArrangeCfg record, @Param("example") SchAutoArrangeCfgExample example);

    int updateByExample(@Param("record") SchAutoArrangeCfg record, @Param("example") SchAutoArrangeCfgExample example);

    int updateByPrimaryKeySelective(SchAutoArrangeCfg record);

    int updateByPrimaryKey(SchAutoArrangeCfg record);

}