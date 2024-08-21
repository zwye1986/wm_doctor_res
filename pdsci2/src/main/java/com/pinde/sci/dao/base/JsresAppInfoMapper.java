package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresAppInfo;
import com.pinde.sci.model.mo.JsresAppInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresAppInfoMapper {
    int countByExample(JsresAppInfoExample example);

    int deleteByExample(JsresAppInfoExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresAppInfo record);

    int insertSelective(JsresAppInfo record);

    List<JsresAppInfo> selectByExample(JsresAppInfoExample example);

    JsresAppInfo selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresAppInfo record, @Param("example") JsresAppInfoExample example);

    int updateByExample(@Param("record") JsresAppInfo record, @Param("example") JsresAppInfoExample example);

    int updateByPrimaryKeySelective(JsresAppInfo record);

    int updateByPrimaryKey(JsresAppInfo record);
}