package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExpertEvalOrgSpeCfg;
import com.pinde.sci.model.mo.ExpertEvalOrgSpeCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpertEvalOrgSpeCfgMapper {
    int countByExample(ExpertEvalOrgSpeCfgExample example);

    int deleteByExample(ExpertEvalOrgSpeCfgExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ExpertEvalOrgSpeCfg record);

    int insertSelective(ExpertEvalOrgSpeCfg record);

    List<ExpertEvalOrgSpeCfg> selectByExample(ExpertEvalOrgSpeCfgExample example);

    ExpertEvalOrgSpeCfg selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ExpertEvalOrgSpeCfg record, @Param("example") ExpertEvalOrgSpeCfgExample example);

    int updateByExample(@Param("record") ExpertEvalOrgSpeCfg record, @Param("example") ExpertEvalOrgSpeCfgExample example);

    int updateByPrimaryKeySelective(ExpertEvalOrgSpeCfg record);

    int updateByPrimaryKey(ExpertEvalOrgSpeCfg record);
}