package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.LcjnSuppliesBatch;
import com.pinde.sci.model.mo.LcjnSuppliesBatchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LcjnSuppliesBatchMapper {
    int countByExample(LcjnSuppliesBatchExample example);

    int deleteByExample(LcjnSuppliesBatchExample example);

    int deleteByPrimaryKey(String stockFlow);

    int insert(LcjnSuppliesBatch record);

    int insertSelective(LcjnSuppliesBatch record);

    List<LcjnSuppliesBatch> selectByExample(LcjnSuppliesBatchExample example);

    LcjnSuppliesBatch selectByPrimaryKey(String stockFlow);

    int updateByExampleSelective(@Param("record") LcjnSuppliesBatch record, @Param("example") LcjnSuppliesBatchExample example);

    int updateByExample(@Param("record") LcjnSuppliesBatch record, @Param("example") LcjnSuppliesBatchExample example);

    int updateByPrimaryKeySelective(LcjnSuppliesBatch record);

    int updateByPrimaryKey(LcjnSuppliesBatch record);
}