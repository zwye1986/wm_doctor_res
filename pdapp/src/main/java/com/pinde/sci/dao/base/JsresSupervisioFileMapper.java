package com.pinde.sci.dao.base;

import com.pinde.core.model.JsresSupervisioFile;
import com.pinde.core.model.JsresSupervisioFileExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresSupervisioFileMapper {
    int countByExample(JsresSupervisioFileExample example);

    int deleteByExample(JsresSupervisioFileExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresSupervisioFile record);

    int insertSelective(JsresSupervisioFile record);

    List<JsresSupervisioFile> selectByExample(JsresSupervisioFileExample example);

    JsresSupervisioFile selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresSupervisioFile record, @Param("example") JsresSupervisioFileExample example);

    int updateByExample(@Param("record") JsresSupervisioFile record, @Param("example") JsresSupervisioFileExample example);

    int updateByPrimaryKeySelective(JsresSupervisioFile record);

    int updateByPrimaryKey(JsresSupervisioFile record);
}