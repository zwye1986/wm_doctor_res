package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.StuBatch;
import com.pinde.sci.model.mo.StuBatchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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