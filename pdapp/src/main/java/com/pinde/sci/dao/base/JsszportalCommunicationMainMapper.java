package com.pinde.sci.dao.base;

import com.pinde.core.model.JsszportalCommunicationMain;
import com.pinde.core.model.JsszportalCommunicationMainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JsszportalCommunicationMainMapper {
    int countByExample(JsszportalCommunicationMainExample example);

    int deleteByExample(JsszportalCommunicationMainExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsszportalCommunicationMain record);

    int insertSelective(JsszportalCommunicationMain record);

    List<JsszportalCommunicationMain> selectByExampleWithBLOBs(JsszportalCommunicationMainExample example);

    List<JsszportalCommunicationMain> selectByExample(JsszportalCommunicationMainExample example);

    JsszportalCommunicationMain selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsszportalCommunicationMain record, @Param("example") JsszportalCommunicationMainExample example);

    int updateByExampleWithBLOBs(@Param("record") JsszportalCommunicationMain record, @Param("example") JsszportalCommunicationMainExample example);

    int updateByExample(@Param("record") JsszportalCommunicationMain record, @Param("example") JsszportalCommunicationMainExample example);

    int updateByPrimaryKeySelective(JsszportalCommunicationMain record);

    int updateByPrimaryKeyWithBLOBs(JsszportalCommunicationMain record);

    int updateByPrimaryKey(JsszportalCommunicationMain record);
}