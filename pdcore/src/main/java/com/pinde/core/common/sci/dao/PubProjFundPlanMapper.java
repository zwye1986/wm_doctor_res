package com.pinde.core.common.sci.dao;

import com.pinde.core.model.PubProjFundPlan;
import com.pinde.core.model.PubProjFundPlanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubProjFundPlanMapper {
    int countByExample(PubProjFundPlanExample example);

    int deleteByExample(PubProjFundPlanExample example);

    int deleteByPrimaryKey(String fundPlanFlow);

    int insert(PubProjFundPlan record);

    int insertSelective(PubProjFundPlan record);

    List<PubProjFundPlan> selectByExample(PubProjFundPlanExample example);

    PubProjFundPlan selectByPrimaryKey(String fundPlanFlow);

    int updateByExampleSelective(@Param("record") PubProjFundPlan record, @Param("example") PubProjFundPlanExample example);

    int updateByExample(@Param("record") PubProjFundPlan record, @Param("example") PubProjFundPlanExample example);

    int updateByPrimaryKeySelective(PubProjFundPlan record);

    int updateByPrimaryKey(PubProjFundPlan record);
}