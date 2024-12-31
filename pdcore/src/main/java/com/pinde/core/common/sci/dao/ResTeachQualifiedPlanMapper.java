package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResTeachQualifiedPlan;
import com.pinde.core.model.ResTeachQualifiedPlanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResTeachQualifiedPlanMapper {
    int countByExample(ResTeachQualifiedPlanExample example);

    int deleteByExample(ResTeachQualifiedPlanExample example);

    int deleteByPrimaryKey(String planFlow);

    int insert(ResTeachQualifiedPlan record);

    int insertSelective(ResTeachQualifiedPlan record);

    List<ResTeachQualifiedPlan> selectByExample(ResTeachQualifiedPlanExample example);

    ResTeachQualifiedPlan selectByPrimaryKey(String planFlow);

    int updateByExampleSelective(@Param("record") ResTeachQualifiedPlan record, @Param("example") ResTeachQualifiedPlanExample example);

    int updateByExample(@Param("record") ResTeachQualifiedPlan record, @Param("example") ResTeachQualifiedPlanExample example);

    int updateByPrimaryKeySelective(ResTeachQualifiedPlan record);

    int updateByPrimaryKey(ResTeachQualifiedPlan record);
}