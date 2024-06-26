package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubUserGenericContent;
import com.pinde.sci.model.mo.PubUserGenericContentExample;
import com.pinde.sci.model.mo.PubUserGenericContentKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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