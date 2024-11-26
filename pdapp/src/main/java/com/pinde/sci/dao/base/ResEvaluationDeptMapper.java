package com.pinde.sci.dao.base;

import com.pinde.core.model.ResEvaluationDept;
import com.pinde.core.model.ResEvaluationDeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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