package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ZseySuppliesBatch;
import com.pinde.sci.model.mo.ZseySuppliesBatchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZseySuppliesBatchMapper {
    int countByExample(ZseySuppliesBatchExample example);

    int deleteByExample(ZseySuppliesBatchExample example);

    int deleteByPrimaryKey(String batchFlow);

    int insert(ZseySuppliesBatch record);

    int insertSelective(ZseySuppliesBatch record);

    List<ZseySuppliesBatch> selectByExample(ZseySuppliesBatchExample example);

    ZseySuppliesBatch selectByPrimaryKey(String batchFlow);

    int updateByExampleSelective(@Param("record") ZseySuppliesBatch record, @Param("example") ZseySuppliesBatchExample example);

    int updateByExample(@Param("record") ZseySuppliesBatch record, @Param("example") ZseySuppliesBatchExample example);

    int updateByPrimaryKeySelective(ZseySuppliesBatch record);

    int updateByPrimaryKey(ZseySuppliesBatch record);
}