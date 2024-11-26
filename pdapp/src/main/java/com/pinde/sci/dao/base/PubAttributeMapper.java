package com.pinde.sci.dao.base;

import com.pinde.core.model.PubAttribute;
import com.pinde.core.model.PubAttributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PubAttributeMapper {
    int countByExample(PubAttributeExample example);

    int deleteByExample(PubAttributeExample example);

    int deleteByPrimaryKey(String attrFlow);

    int insert(PubAttribute record);

    int insertSelective(PubAttribute record);

    List<PubAttribute> selectByExample(PubAttributeExample example);

    PubAttribute selectByPrimaryKey(String attrFlow);

    int updateByExampleSelective(@Param("record") PubAttribute record, @Param("example") PubAttributeExample example);

    int updateByExample(@Param("record") PubAttribute record, @Param("example") PubAttributeExample example);

    int updateByPrimaryKeySelective(PubAttribute record);

    int updateByPrimaryKey(PubAttribute record);
}