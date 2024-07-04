package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresDeptConfig;
import com.pinde.sci.model.mo.JsresDeptConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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