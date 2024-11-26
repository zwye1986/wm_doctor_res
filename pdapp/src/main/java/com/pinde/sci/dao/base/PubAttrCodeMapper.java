package com.pinde.sci.dao.base;

import com.pinde.core.model.PubAttrCode;
import com.pinde.core.model.PubAttrCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PubAttrCodeMapper {
    int countByExample(PubAttrCodeExample example);

    int deleteByExample(PubAttrCodeExample example);

    int deleteByPrimaryKey(String codeFlow);

    int insert(PubAttrCode record);

    int insertSelective(PubAttrCode record);

    List<PubAttrCode> selectByExample(PubAttrCodeExample example);

    PubAttrCode selectByPrimaryKey(String codeFlow);

    int updateByExampleSelective(@Param("record") PubAttrCode record, @Param("example") PubAttrCodeExample example);

    int updateByExample(@Param("record") PubAttrCode record, @Param("example") PubAttrCodeExample example);

    int updateByPrimaryKeySelective(PubAttrCode record);

    int updateByPrimaryKey(PubAttrCode record);
}