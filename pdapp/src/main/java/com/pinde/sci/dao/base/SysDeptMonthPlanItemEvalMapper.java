package com.pinde.sci.dao.base;

import com.pinde.core.model.SysDeptMonthPlanItemEval;
import com.pinde.core.model.SysDeptMonthPlanItemEvalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDeptMonthPlanItemEvalMapper {
    int countByExample(SysDeptMonthPlanItemEvalExample example);

    int deleteByExample(SysDeptMonthPlanItemEvalExample example);

    int deleteByPrimaryKey(String evalFlow);

    int insert(SysDeptMonthPlanItemEval record);

    int insertSelective(SysDeptMonthPlanItemEval record);

    List<SysDeptMonthPlanItemEval> selectByExampleWithBLOBs(SysDeptMonthPlanItemEvalExample example);

    List<SysDeptMonthPlanItemEval> selectByExample(SysDeptMonthPlanItemEvalExample example);

    SysDeptMonthPlanItemEval selectByPrimaryKey(String evalFlow);

    int updateByExampleSelective(@Param("record") SysDeptMonthPlanItemEval record, @Param("example") SysDeptMonthPlanItemEvalExample example);

    int updateByExampleWithBLOBs(@Param("record") SysDeptMonthPlanItemEval record, @Param("example") SysDeptMonthPlanItemEvalExample example);

    int updateByExample(@Param("record") SysDeptMonthPlanItemEval record, @Param("example") SysDeptMonthPlanItemEvalExample example);

    int updateByPrimaryKeySelective(SysDeptMonthPlanItemEval record);

    int updateByPrimaryKeyWithBLOBs(SysDeptMonthPlanItemEval record);

    int updateByPrimaryKey(SysDeptMonthPlanItemEval record);
}