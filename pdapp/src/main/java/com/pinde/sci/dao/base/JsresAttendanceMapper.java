package com.pinde.sci.dao.base;

import com.pinde.core.model.JsresAttendance;
import com.pinde.core.model.JsresAttendanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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