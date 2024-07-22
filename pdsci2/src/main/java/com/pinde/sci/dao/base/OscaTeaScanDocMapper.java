package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.OscaTeaScanDoc;
import com.pinde.sci.model.mo.OscaTeaScanDocExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OscaTeaScanDocMapper {
    int countByExample(OscaTeaScanDocExample example);

    int deleteByExample(OscaTeaScanDocExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(OscaTeaScanDoc record);

    int insertSelective(OscaTeaScanDoc record);

    List<OscaTeaScanDoc> selectByExample(OscaTeaScanDocExample example);

    OscaTeaScanDoc selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") OscaTeaScanDoc record, @Param("example") OscaTeaScanDocExample example);

    int updateByExample(@Param("record") OscaTeaScanDoc record, @Param("example") OscaTeaScanDocExample example);

    int updateByPrimaryKeySelective(OscaTeaScanDoc record);

    int updateByPrimaryKey(OscaTeaScanDoc record);
}