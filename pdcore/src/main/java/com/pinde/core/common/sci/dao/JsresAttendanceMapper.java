package com.pinde.core.common.sci.dao;

import com.pinde.core.model.JsresAttendance;
import com.pinde.core.model.JsresAttendanceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresAttendanceMapper {
    int countByExample(JsresAttendanceExample example);

    int deleteByExample(JsresAttendanceExample example);

    int deleteByPrimaryKey(String attendanceFlow);

    int insert(JsresAttendance record);

    int insertSelective(JsresAttendance record);

    List<JsresAttendance> selectByExample(JsresAttendanceExample example);

    JsresAttendance selectByPrimaryKey(String attendanceFlow);

    int updateByExampleSelective(@Param("record") JsresAttendance record, @Param("example") JsresAttendanceExample example);

    int updateByExample(@Param("record") JsresAttendance record, @Param("example") JsresAttendanceExample example);

    int updateByPrimaryKeySelective(JsresAttendance record);

    int updateByPrimaryKey(JsresAttendance record);
}