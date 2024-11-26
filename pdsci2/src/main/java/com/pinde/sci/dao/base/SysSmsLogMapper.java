package com.pinde.sci.dao.base;

import com.pinde.core.model.TestPaperExample;
import com.pinde.sci.model.mo.SysSmsLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysSmsLogMapper {
    int countByExample(SysSmsLogExample example);

    int deleteByExample(SysSmsLogExample example);

    int deleteByPrimaryKey(String smsLogFlow);

    int insert(TestPaperExample.SysSmsLog record);

    int insertSelective(TestPaperExample.SysSmsLog record);

    List<TestPaperExample.SysSmsLog> selectByExample(SysSmsLogExample example);

    TestPaperExample.SysSmsLog selectByPrimaryKey(String smsLogFlow);

    int updateByExampleSelective(@Param("record") TestPaperExample.SysSmsLog record, @Param("example") SysSmsLogExample example);

    int updateByExample(@Param("record") TestPaperExample.SysSmsLog record, @Param("example") SysSmsLogExample example);

    int updateByPrimaryKeySelective(TestPaperExample.SysSmsLog record);

    int updateByPrimaryKey(TestPaperExample.SysSmsLog record);
}