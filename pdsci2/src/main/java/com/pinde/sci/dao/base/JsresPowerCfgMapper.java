package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresPowerCfg;
import com.pinde.sci.model.mo.JsresPowerCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JsresPowerCfgMapper {
    int countByExample(JsresPowerCfgExample example);

    int deleteByExample(JsresPowerCfgExample example);

    int deleteByPrimaryKey(String cfgCode);

    int insert(JsresPowerCfg record);

    int insertSelective(JsresPowerCfg record);

    List<JsresPowerCfg> selectByExample(JsresPowerCfgExample example);

    JsresPowerCfg selectByPrimaryKey(String cfgCode);

    int updateByExampleSelective(@Param("record") JsresPowerCfg record, @Param("example") JsresPowerCfgExample example);

    int updateByExample(@Param("record") JsresPowerCfg record, @Param("example") JsresPowerCfgExample example);

    int updateByPrimaryKeySelective(JsresPowerCfg record);

    int updateByPrimaryKey(JsresPowerCfg record);
}