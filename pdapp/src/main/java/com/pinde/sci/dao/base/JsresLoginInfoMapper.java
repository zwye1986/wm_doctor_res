package com.pinde.sci.dao.base;

import com.pinde.core.model.JsresLoginInfo;
import com.pinde.core.model.JsresLoginInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JsresLoginInfoMapper {
    int countByExample(JsresLoginInfoExample example);

    int deleteByExample(JsresLoginInfoExample example);

    int deleteByPrimaryKey(String reocrdFlow);

    int insert(JsresLoginInfo record);

    int insertSelective(JsresLoginInfo record);

    List<JsresLoginInfo> selectByExample(JsresLoginInfoExample example);

    JsresLoginInfo selectByPrimaryKey(String reocrdFlow);

    int updateByExampleSelective(@Param("record") JsresLoginInfo record, @Param("example") JsresLoginInfoExample example);

    int updateByExample(@Param("record") JsresLoginInfo record, @Param("example") JsresLoginInfoExample example);

    int updateByPrimaryKeySelective(JsresLoginInfo record);

    int updateByPrimaryKey(JsresLoginInfo record);
}