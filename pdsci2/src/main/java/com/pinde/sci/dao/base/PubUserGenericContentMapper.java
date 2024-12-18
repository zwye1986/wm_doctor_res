package com.pinde.sci.dao.base;

import com.pinde.core.model.PubUserGenericContent;
import com.pinde.core.model.PubUserGenericContentExample;
import com.pinde.core.model.PubUserGenericContentKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubUserGenericContentMapper {
    int countByExample(PubUserGenericContentExample example);

    int deleteByExample(PubUserGenericContentExample example);

    int deleteByPrimaryKey(PubUserGenericContentKey key);

    int insert(PubUserGenericContent record);

    int insertSelective(PubUserGenericContent record);

    List<PubUserGenericContent> selectByExampleWithBLOBs(PubUserGenericContentExample example);

    List<PubUserGenericContent> selectByExample(PubUserGenericContentExample example);

    PubUserGenericContent selectByPrimaryKey(String userFlow);

    int updateByExampleSelective(@Param("record") PubUserGenericContent record, @Param("example") PubUserGenericContentExample example);

    int updateByExampleWithBLOBs(@Param("record") PubUserGenericContent record, @Param("example") PubUserGenericContentExample example);

    int updateByExample(@Param("record") PubUserGenericContent record, @Param("example") PubUserGenericContentExample example);

    int updateByPrimaryKeySelective(PubUserGenericContent record);

    int updateByPrimaryKeyWithBLOBs(PubUserGenericContent record);

    int updateByPrimaryKey(PubUserGenericContent record);
}