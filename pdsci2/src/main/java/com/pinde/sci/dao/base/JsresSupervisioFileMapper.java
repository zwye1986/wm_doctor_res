package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresSupervisioFile;
import com.pinde.sci.model.mo.JsresSupervisioFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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