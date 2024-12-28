package com.pinde.core.common.sci.dao;

import com.pinde.core.model.SysDeptMonthPlanItem;
import com.pinde.core.model.SysDeptMonthPlanItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDeptMonthPlanItemMapper {
    int countByExample(SysDeptMonthPlanItemExample example);

    int deleteByExample(SysDeptMonthPlanItemExample example);

    int deleteByPrimaryKey(String itemFlow);

    int insert(SysDeptMonthPlanItem record);

    int insertSelective(SysDeptMonthPlanItem record);

    List<SysDeptMonthPlanItem> selectByExampleWithBLOBs(SysDeptMonthPlanItemExample example);

    List<SysDeptMonthPlanItem> selectByExample(SysDeptMonthPlanItemExample example);

    SysDeptMonthPlanItem selectByPrimaryKey(String itemFlow);

    int updateByExampleSelective(@Param("record") SysDeptMonthPlanItem record, @Param("example") SysDeptMonthPlanItemExample example);

    int updateByExampleWithBLOBs(@Param("record") SysDeptMonthPlanItem record, @Param("example") SysDeptMonthPlanItemExample example);

    int updateByExample(@Param("record") SysDeptMonthPlanItem record, @Param("example") SysDeptMonthPlanItemExample example);

    int updateByPrimaryKeySelective(SysDeptMonthPlanItem record);

    int updateByPrimaryKeyWithBLOBs(SysDeptMonthPlanItem record);

    int updateByPrimaryKey(SysDeptMonthPlanItem record);
}