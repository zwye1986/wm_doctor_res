package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresUserBalcklist;
import com.pinde.sci.model.mo.JsresUserBalcklistExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresUserBalcklistMapper {
    int countByExample(JsresUserBalcklistExample example);

    int deleteByExample(JsresUserBalcklistExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresUserBalcklist record);

    int insertSelective(JsresUserBalcklist record);

    List<JsresUserBalcklist> selectByExample(JsresUserBalcklistExample example);

    JsresUserBalcklist selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresUserBalcklist record, @Param("example") JsresUserBalcklistExample example);

    int updateByExample(@Param("record") JsresUserBalcklist record, @Param("example") JsresUserBalcklistExample example);

    int updateByPrimaryKeySelective(JsresUserBalcklist record);

    int updateByPrimaryKey(JsresUserBalcklist record);
}