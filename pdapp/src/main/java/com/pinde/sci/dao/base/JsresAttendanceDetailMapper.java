package com.pinde.sci.dao.base;

import com.pinde.core.model.JsresAttendanceDetail;
import com.pinde.core.model.JsresAttendanceDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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