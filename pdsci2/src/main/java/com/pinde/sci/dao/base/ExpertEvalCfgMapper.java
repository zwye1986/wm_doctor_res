package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExpertEvalCfg;
import com.pinde.sci.model.mo.ExpertEvalCfgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExpertEvalCfgMapper {
    int countByExample(ExpertEvalCfgExample example);

    int deleteByExample(ExpertEvalCfgExample example);

    int deleteByPrimaryKey(String cfgFlow);

    int insert(ExpertEvalCfg record);

    int insertSelective(ExpertEvalCfg record);

    List<ExpertEvalCfg> selectByExample(ExpertEvalCfgExample example);

    ExpertEvalCfg selectByPrimaryKey(String cfgFlow);

    int updateByExampleSelective(@Param("record") ExpertEvalCfg record, @Param("example") ExpertEvalCfgExample example);

    int updateByExample(@Param("record") ExpertEvalCfg record, @Param("example") ExpertEvalCfgExample example);

    int updateByPrimaryKeySelective(ExpertEvalCfg record);

    int updateByPrimaryKey(ExpertEvalCfg record);
}