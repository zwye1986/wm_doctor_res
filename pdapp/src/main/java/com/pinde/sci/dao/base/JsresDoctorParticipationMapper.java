package com.pinde.sci.dao.base;

import com.pinde.core.model.JsresDoctorParticipation;
import com.pinde.core.model.JsresDoctorParticipationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresDoctorParticipationMapper {
    int countByExample(JsresDoctorParticipationExample example);

    int deleteByExample(JsresDoctorParticipationExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresDoctorParticipation record);

    int insertSelective(JsresDoctorParticipation record);

    List<JsresDoctorParticipation> selectByExample(JsresDoctorParticipationExample example);

    JsresDoctorParticipation selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresDoctorParticipation record, @Param("example") JsresDoctorParticipationExample example);

    int updateByExample(@Param("record") JsresDoctorParticipation record, @Param("example") JsresDoctorParticipationExample example);

    int updateByPrimaryKeySelective(JsresDoctorParticipation record);

    int updateByPrimaryKey(JsresDoctorParticipation record);
}