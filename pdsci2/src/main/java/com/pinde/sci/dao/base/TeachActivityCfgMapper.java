package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.TeachActivityCfg;
import com.pinde.sci.model.mo.TeachActivityCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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