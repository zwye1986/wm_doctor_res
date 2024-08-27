package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresAttendanceDetail;
import com.pinde.sci.model.mo.JsresAttendanceDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresAttendanceDetailMapper {
    int countByExample(JsresAttendanceDetailExample example);

    int deleteByExample(JsresAttendanceDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresAttendanceDetail record);

    int insertSelective(JsresAttendanceDetail record);

    List<JsresAttendanceDetail> selectByExample(JsresAttendanceDetailExample example);

    JsresAttendanceDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresAttendanceDetail record, @Param("example") JsresAttendanceDetailExample example);

    int updateByExample(@Param("record") JsresAttendanceDetail record, @Param("example") JsresAttendanceDetailExample example);

    int updateByPrimaryKeySelective(JsresAttendanceDetail record);

    int updateByPrimaryKey(JsresAttendanceDetail record);
}