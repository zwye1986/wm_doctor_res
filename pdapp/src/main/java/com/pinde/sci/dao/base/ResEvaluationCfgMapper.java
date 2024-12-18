package com.pinde.sci.dao.base;

import com.pinde.core.model.ResEvaluationCfg;
import com.pinde.core.model.ResEvaluationCfgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResEvaluationCfgMapper {
    int countByExample(ResEvaluationCfgExample example);

    int deleteByExample(ResEvaluationCfgExample example);

    int deleteByPrimaryKey(String cfgFlow);

    int insert(ResEvaluationCfg record);

    int insertSelective(ResEvaluationCfg record);

    List<ResEvaluationCfg> selectByExampleWithBLOBs(ResEvaluationCfgExample example);

    List<ResEvaluationCfg> selectByExample(ResEvaluationCfgExample example);

    ResEvaluationCfg selectByPrimaryKey(String cfgFlow);

    int updateByExampleSelective(@Param("record") ResEvaluationCfg record, @Param("example") ResEvaluationCfgExample example);

    int updateByExampleWithBLOBs(@Param("record") ResEvaluationCfg record, @Param("example") ResEvaluationCfgExample example);

    int updateByExample(@Param("record") ResEvaluationCfg record, @Param("example") ResEvaluationCfgExample example);

    int updateByPrimaryKeySelective(ResEvaluationCfg record);

    int updateByPrimaryKeyWithBLOBs(ResEvaluationCfg record);

    int updateByPrimaryKey(ResEvaluationCfg record);
}