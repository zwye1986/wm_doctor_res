package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SysDeptMonthPlan;
import com.pinde.sci.model.mo.SysDeptMonthPlanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDeptMonthPlanMapper {
    int countByExample(SysDeptMonthPlanExample example);

    int deleteByExample(SysDeptMonthPlanExample example);

    int deleteByPrimaryKey(String planFlow);

    int insert(SysDeptMonthPlan record);

    int insertSelective(SysDeptMonthPlan record);

    List<SysDeptMonthPlan> selectByExample(SysDeptMonthPlanExample example);

    SysDeptMonthPlan selectByPrimaryKey(String planFlow);

    int updateByExampleSelective(@Param("record") SysDeptMonthPlan record, @Param("example") SysDeptMonthPlanExample example);

    int updateByExample(@Param("record") SysDeptMonthPlan record, @Param("example") SysDeptMonthPlanExample example);

    int updateByPrimaryKeySelective(SysDeptMonthPlan record);

    int updateByPrimaryKey(SysDeptMonthPlan record);
}