package com.pinde.sci.dao.base;

import com.pinde.core.model.JsszportalCommunicationRe;
import com.pinde.core.model.JsszportalCommunicationReExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsszportalCommunicationReMapper {
    int countByExample(JsszportalCommunicationReExample example);

    int deleteByExample(JsszportalCommunicationReExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsszportalCommunicationRe record);

    int insertSelective(JsszportalCommunicationRe record);

    List<JsszportalCommunicationRe> selectByExampleWithBLOBs(JsszportalCommunicationReExample example);

    List<JsszportalCommunicationRe> selectByExample(JsszportalCommunicationReExample example);

    JsszportalCommunicationRe selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsszportalCommunicationRe record, @Param("example") JsszportalCommunicationReExample example);

    int updateByExampleWithBLOBs(@Param("record") JsszportalCommunicationRe record, @Param("example") JsszportalCommunicationReExample example);

    int updateByExample(@Param("record") JsszportalCommunicationRe record, @Param("example") JsszportalCommunicationReExample example);

    int updateByPrimaryKeySelective(JsszportalCommunicationRe record);

    int updateByPrimaryKeyWithBLOBs(JsszportalCommunicationRe record);

    int updateByPrimaryKey(JsszportalCommunicationRe record);
}