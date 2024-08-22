package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResTeachQualifiedPlan;
import com.pinde.sci.model.mo.ResTeachQualifiedPlanExample;
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