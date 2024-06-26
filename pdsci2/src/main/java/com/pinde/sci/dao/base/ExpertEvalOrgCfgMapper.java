package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExpertEvalOrgCfg;
import com.pinde.sci.model.mo.ExpertEvalOrgCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpertEvalOrgCfgMapper {
    int countByExample(ExpertEvalOrgCfgExample example);

    int deleteByExample(ExpertEvalOrgCfgExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ExpertEvalOrgCfg record);

    int insertSelective(ExpertEvalOrgCfg record);

    List<ExpertEvalOrgCfg> selectByExample(ExpertEvalOrgCfgExample example);

    ExpertEvalOrgCfg selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ExpertEvalOrgCfg record, @Param("example") ExpertEvalOrgCfgExample example);

    int updateByExample(@Param("record") ExpertEvalOrgCfg record, @Param("example") ExpertEvalOrgCfgExample example);

    int updateByPrimaryKeySelective(ExpertEvalOrgCfg record);

    int updateByPrimaryKey(ExpertEvalOrgCfg record);
}