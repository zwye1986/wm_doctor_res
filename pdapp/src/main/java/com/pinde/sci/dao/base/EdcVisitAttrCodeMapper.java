package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcVisitAttrCode;
import com.pinde.sci.model.mo.EdcVisitAttrCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcVisitAttrCodeMapper {
    int countByExample(EdcVisitAttrCodeExample example);

    int deleteByExample(EdcVisitAttrCodeExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EdcVisitAttrCode record);

    int insertSelective(EdcVisitAttrCode record);

    List<EdcVisitAttrCode> selectByExample(EdcVisitAttrCodeExample example);

    EdcVisitAttrCode selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EdcVisitAttrCode record, @Param("example") EdcVisitAttrCodeExample example);

    int updateByExample(@Param("record") EdcVisitAttrCode record, @Param("example") EdcVisitAttrCodeExample example);

    int updateByPrimaryKeySelective(EdcVisitAttrCode record);

    int updateByPrimaryKey(EdcVisitAttrCode record);
}