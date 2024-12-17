package com.pinde.sci.dao.base;

import com.pinde.core.model.JsresDeptConfig;
import com.pinde.core.model.JsresDeptConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresDeptConfigMapper {
    int countByExample(JsresDeptConfigExample example);

    int deleteByExample(JsresDeptConfigExample example);

    int deleteByPrimaryKey(String cfgFlow);

    int insert(JsresDeptConfig record);

    int insertSelective(JsresDeptConfig record);

    List<JsresDeptConfig> selectByExample(JsresDeptConfigExample example);

    JsresDeptConfig selectByPrimaryKey(String cfgFlow);

    int updateByExampleSelective(@Param("record") JsresDeptConfig record, @Param("example") JsresDeptConfigExample example);

    int updateByExample(@Param("record") JsresDeptConfig record, @Param("example") JsresDeptConfigExample example);

    int updateByPrimaryKeySelective(JsresDeptConfig record);

    int updateByPrimaryKey(JsresDeptConfig record);
}