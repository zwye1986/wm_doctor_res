package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SysDeptMonthPlanItem;
import com.pinde.sci.model.mo.SysDeptMonthPlanItemExample;
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