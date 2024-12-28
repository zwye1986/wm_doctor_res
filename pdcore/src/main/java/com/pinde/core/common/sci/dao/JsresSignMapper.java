package com.pinde.core.common.sci.dao;

import com.pinde.core.model.JsresSign;
import com.pinde.core.model.JsresSignExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresSignMapper {
    int countByExample(JsresSignExample example);

    int deleteByExample(JsresSignExample example);

    int deleteByPrimaryKey(String signFlow);

    int insert(JsresSign record);

    int insertSelective(JsresSign record);

    List<JsresSign> selectByExample(JsresSignExample example);

    JsresSign selectByPrimaryKey(String signFlow);

    int updateByExampleSelective(@Param("record") JsresSign record, @Param("example") JsresSignExample example);

    int updateByExample(@Param("record") JsresSign record, @Param("example") JsresSignExample example);

    int updateByPrimaryKeySelective(JsresSign record);

    int updateByPrimaryKey(JsresSign record);
}