package com.pinde.sci.dao.base;

import com.pinde.core.model.JsresDoctorWork;
import com.pinde.core.model.JsresDoctorWorkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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