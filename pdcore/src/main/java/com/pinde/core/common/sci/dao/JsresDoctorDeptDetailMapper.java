package com.pinde.core.common.sci.dao;

import com.pinde.core.model.JsresDoctorDeptDetail;
import com.pinde.core.model.JsresDoctorDeptDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresDoctorDeptDetailMapper {
    int countByExample(JsresDoctorDeptDetailExample example);

    int deleteByExample(JsresDoctorDeptDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresDoctorDeptDetail record);

//    int insertSelective(JsresDoctorDeptDetail record);

    List<JsresDoctorDeptDetail> selectByExample(JsresDoctorDeptDetailExample example);

    JsresDoctorDeptDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresDoctorDeptDetail record, @Param("example") JsresDoctorDeptDetailExample example);

    int updateByExample(@Param("record") JsresDoctorDeptDetail record, @Param("example") JsresDoctorDeptDetailExample example);

    int updateByPrimaryKeySelective(JsresDoctorDeptDetail record);

    int updateByPrimaryKey(JsresDoctorDeptDetail record);
}