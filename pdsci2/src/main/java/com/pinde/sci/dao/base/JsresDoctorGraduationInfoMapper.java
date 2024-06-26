package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresDoctorGraduationInfo;
import com.pinde.sci.model.mo.JsresDoctorGraduationInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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