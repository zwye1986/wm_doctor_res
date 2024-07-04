package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcAttrCode;
import com.pinde.sci.model.mo.EdcAttrCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcAttrCodeMapper {
    int countByExample(EdcAttrCodeExample example);

    int deleteByExample(EdcAttrCodeExample example);

    int deleteByPrimaryKey(String codeFlow);

    int insert(EdcAttrCode record);

    int insertSelective(EdcAttrCode record);

    List<EdcAttrCode> selectByExample(EdcAttrCodeExample example);

    EdcAttrCode selectByPrimaryKey(String codeFlow);

    int updateByExampleSelective(@Param("record") EdcAttrCode record, @Param("example") EdcAttrCodeExample example);

    int updateByExample(@Param("record") EdcAttrCode record, @Param("example") EdcAttrCodeExample example);

    int updateByPrimaryKeySelective(EdcAttrCode record);

    int updateByPrimaryKey(EdcAttrCode record);
}