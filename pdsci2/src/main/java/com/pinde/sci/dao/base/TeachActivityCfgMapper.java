package com.pinde.sci.dao.base;

import com.pinde.core.model.TeachActivityCfg;
import com.pinde.core.model.TeachActivityCfgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeachActivityCfgMapper {
    int countByExample(TeachActivityCfgExample example);

    int deleteByExample(TeachActivityCfgExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(TeachActivityCfg record);

    int insertSelective(TeachActivityCfg record);

    List<TeachActivityCfg> selectByExample(TeachActivityCfgExample example);

    TeachActivityCfg selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") TeachActivityCfg record, @Param("example") TeachActivityCfgExample example);

    int updateByExample(@Param("record") TeachActivityCfg record, @Param("example") TeachActivityCfgExample example);

    int updateByPrimaryKeySelective(TeachActivityCfg record);

    int updateByPrimaryKey(TeachActivityCfg record);
}