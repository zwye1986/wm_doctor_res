package com.pinde.core.common.sci.dao;

import com.pinde.core.model.JsresLoginInfo;
import com.pinde.core.model.JsresLoginInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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