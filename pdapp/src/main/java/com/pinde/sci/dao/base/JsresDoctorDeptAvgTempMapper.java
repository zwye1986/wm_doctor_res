package com.pinde.sci.dao.base;

import com.pinde.core.model.JsresDoctorDeptAvgTemp;
import com.pinde.core.model.JsresDoctorDeptAvgTempExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresDoctorDeptAvgTempMapper {
    int countByExample(JsresDoctorDeptAvgTempExample example);

    int deleteByExample(JsresDoctorDeptAvgTempExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresDoctorDeptAvgTemp record);

    int insertSelective(JsresDoctorDeptAvgTemp record);

    List<JsresDoctorDeptAvgTemp> selectByExample(JsresDoctorDeptAvgTempExample example);

    JsresDoctorDeptAvgTemp selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresDoctorDeptAvgTemp record, @Param("example") JsresDoctorDeptAvgTempExample example);

    int updateByExample(@Param("record") JsresDoctorDeptAvgTemp record, @Param("example") JsresDoctorDeptAvgTempExample example);

    int updateByPrimaryKeySelective(JsresDoctorDeptAvgTemp record);

    int updateByPrimaryKey(JsresDoctorDeptAvgTemp record);
}