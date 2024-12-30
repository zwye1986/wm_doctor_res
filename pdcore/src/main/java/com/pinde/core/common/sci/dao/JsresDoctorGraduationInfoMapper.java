package com.pinde.core.common.sci.dao;

import com.pinde.core.model.JsresDoctorGraduationInfo;
import com.pinde.core.model.JsresDoctorGraduationInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresDoctorGraduationInfoMapper {
    int countByExample(JsresDoctorGraduationInfoExample example);

    int deleteByExample(JsresDoctorGraduationInfoExample example);

    int deleteByPrimaryKey(String infoFlow);

    int insert(JsresDoctorGraduationInfo record);

    int insertSelective(JsresDoctorGraduationInfo record);

    List<JsresDoctorGraduationInfo> selectByExample(JsresDoctorGraduationInfoExample example);

    JsresDoctorGraduationInfo selectByPrimaryKey(String infoFlow);

    int updateByExampleSelective(@Param("record") JsresDoctorGraduationInfo record, @Param("example") JsresDoctorGraduationInfoExample example);

    int updateByExample(@Param("record") JsresDoctorGraduationInfo record, @Param("example") JsresDoctorGraduationInfoExample example);

    int updateByPrimaryKeySelective(JsresDoctorGraduationInfo record);

    int updateByPrimaryKey(JsresDoctorGraduationInfo record);
}