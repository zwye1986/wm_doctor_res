package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresDoctorSpe;
import com.pinde.sci.model.mo.JsresDoctorSpeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JsresDoctorSpeMapper {
    int countByExample(JsresDoctorSpeExample example);

    int deleteByExample(JsresDoctorSpeExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresDoctorSpe record);

    int insertSelective(JsresDoctorSpe record);

    List<JsresDoctorSpe> selectByExample(JsresDoctorSpeExample example);

    JsresDoctorSpe selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresDoctorSpe record, @Param("example") JsresDoctorSpeExample example);

    int updateByExample(@Param("record") JsresDoctorSpe record, @Param("example") JsresDoctorSpeExample example);

    int updateByPrimaryKeySelective(JsresDoctorSpe record);

    int updateByPrimaryKey(JsresDoctorSpe record);
}