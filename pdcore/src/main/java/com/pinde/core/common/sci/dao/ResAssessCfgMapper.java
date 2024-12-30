package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResAssessCfg;
import com.pinde.core.model.ResAssessCfgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResAssessCfgMapper {
    int countByExample(ResAssessCfgExample example);

    int deleteByExample(ResAssessCfgExample example);

    int deleteByPrimaryKey(String cfgFlow);

    int insert(ResAssessCfg record);

    int insertSelective(ResAssessCfg record);

    List<ResAssessCfg> selectByExampleWithBLOBs(ResAssessCfgExample example);

    List<ResAssessCfg> selectByExample(ResAssessCfgExample example);

    ResAssessCfg selectByPrimaryKey(String cfgFlow);

    int updateByExampleSelective(@Param("record") ResAssessCfg record, @Param("example") ResAssessCfgExample example);

    int updateByExampleWithBLOBs(@Param("record") ResAssessCfg record, @Param("example") ResAssessCfgExample example);

    int updateByExample(@Param("record") ResAssessCfg record, @Param("example") ResAssessCfgExample example);

    int updateByPrimaryKeySelective(ResAssessCfg record);

    int updateByPrimaryKeyWithBLOBs(ResAssessCfg record);

    int updateByPrimaryKey(ResAssessCfg record);
}