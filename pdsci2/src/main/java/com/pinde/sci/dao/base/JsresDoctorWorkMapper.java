package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresDoctorWork;
import com.pinde.sci.model.mo.JsresDoctorWorkExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresDoctorWorkMapper {
    int countByExample(JsresDoctorWorkExample example);

    int deleteByExample(JsresDoctorWorkExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresDoctorWork record);

    int insertSelective(JsresDoctorWork record);

    List<JsresDoctorWork> selectByExample(JsresDoctorWorkExample example);

    JsresDoctorWork selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresDoctorWork record, @Param("example") JsresDoctorWorkExample example);

    int updateByExample(@Param("record") JsresDoctorWork record, @Param("example") JsresDoctorWorkExample example);

    int updateByPrimaryKeySelective(JsresDoctorWork record);

    int updateByPrimaryKey(JsresDoctorWork record);
}