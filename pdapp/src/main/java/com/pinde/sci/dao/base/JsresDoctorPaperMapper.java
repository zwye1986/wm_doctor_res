package com.pinde.sci.dao.base;

import com.pinde.core.model.JsresDoctorPaper;
import com.pinde.core.model.JsresDoctorPaperExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresDoctorPaperMapper {
    int countByExample(JsresDoctorPaperExample example);

    int deleteByExample(JsresDoctorPaperExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresDoctorPaper record);

    int insertSelective(JsresDoctorPaper record);

    List<JsresDoctorPaper> selectByExample(JsresDoctorPaperExample example);

    JsresDoctorPaper selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresDoctorPaper record, @Param("example") JsresDoctorPaperExample example);

    int updateByExample(@Param("record") JsresDoctorPaper record, @Param("example") JsresDoctorPaperExample example);

    int updateByPrimaryKeySelective(JsresDoctorPaper record);

    int updateByPrimaryKey(JsresDoctorPaper record);
}