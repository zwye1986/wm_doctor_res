package com.pinde.sci.dao.base;

import com.pinde.core.model.StuBatch;
import com.pinde.core.model.StuBatchExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StuBatchMapper {
    int countByExample(StuBatchExample example);

    int deleteByExample(StuBatchExample example);

    int deleteByPrimaryKey(String batchFlow);

    int insert(StuBatch record);

    int insertSelective(StuBatch record);

    List<StuBatch> selectByExample(StuBatchExample example);

    StuBatch selectByPrimaryKey(String batchFlow);

    int updateByExampleSelective(@Param("record") StuBatch record, @Param("example") StuBatchExample example);

    int updateByExample(@Param("record") StuBatch record, @Param("example") StuBatchExample example);

    int updateByPrimaryKeySelective(StuBatch record);

    int updateByPrimaryKey(StuBatch record);
}