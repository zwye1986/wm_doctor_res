package com.pinde.core.common.sci.dao;

import com.pinde.core.model.SchExamStandardDept;
import com.pinde.core.model.SchExamStandardDeptExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchExamStandardDeptMapper {
    int countByExample(SchExamStandardDeptExample example);

    int deleteByExample(SchExamStandardDeptExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SchExamStandardDept record);

    int insertSelective(SchExamStandardDept record);

    List<SchExamStandardDept> selectByExample(SchExamStandardDeptExample example);

    SchExamStandardDept selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SchExamStandardDept record, @Param("example") SchExamStandardDeptExample example);

    int updateByExample(@Param("record") SchExamStandardDept record, @Param("example") SchExamStandardDeptExample example);

    int updateByPrimaryKeySelective(SchExamStandardDept record);

    int updateByPrimaryKey(SchExamStandardDept record);
}