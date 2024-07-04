package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.TestResult;
import com.pinde.sci.model.mo.TestResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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