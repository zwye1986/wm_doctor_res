package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResEvaluationDept;
import com.pinde.core.model.ResEvaluationDeptExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResEvaluationDeptMapper {
    int countByExample(ResEvaluationDeptExample example);

    int deleteByExample(ResEvaluationDeptExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResEvaluationDept record);

    int insertSelective(ResEvaluationDept record);

    List<ResEvaluationDept> selectByExample(ResEvaluationDeptExample example);

    ResEvaluationDept selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResEvaluationDept record, @Param("example") ResEvaluationDeptExample example);

    int updateByExample(@Param("record") ResEvaluationDept record, @Param("example") ResEvaluationDeptExample example);

    int updateByPrimaryKeySelective(ResEvaluationDept record);

    int updateByPrimaryKey(ResEvaluationDept record);
}