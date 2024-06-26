package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresSign;
import com.pinde.sci.model.mo.JsresSignExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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