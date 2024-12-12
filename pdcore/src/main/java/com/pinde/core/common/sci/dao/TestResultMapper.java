package com.pinde.core.common.sci.dao;

import com.pinde.core.model.TestResult;
import com.pinde.core.model.TestResultExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestResultMapper {
    int countByExample(TestResultExample example);

    int deleteByExample(TestResultExample example);

    int deleteByPrimaryKey(String resultFlow);

    int insert(TestResult record);

    int insertSelective(TestResult record);

    List<TestResult> selectByExample(TestResultExample example);

    TestResult selectByPrimaryKey(String resultFlow);

    int updateByExampleSelective(@Param("record") TestResult record, @Param("example") TestResultExample example);

    int updateByExample(@Param("record") TestResult record, @Param("example") TestResultExample example);

    int updateByPrimaryKeySelective(TestResult record);

    int updateByPrimaryKey(TestResult record);
}