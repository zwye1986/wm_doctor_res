package com.pinde.core.common.sci.dao;

import com.pinde.core.model.JsresDoctorWorkDetail;
import com.pinde.core.model.JsresDoctorWorkDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresDoctorWorkDetailMapper {
    int countByExample(JsresDoctorWorkDetailExample example);

    int deleteByExample(JsresDoctorWorkDetailExample example);

    int deleteByPrimaryKey(String detailFlow);

    int insert(JsresDoctorWorkDetail record);

    int insertSelective(JsresDoctorWorkDetail record);

    List<JsresDoctorWorkDetail> selectByExample(JsresDoctorWorkDetailExample example);

    JsresDoctorWorkDetail selectByPrimaryKey(String detailFlow);

    int updateByExampleSelective(@Param("record") JsresDoctorWorkDetail record, @Param("example") JsresDoctorWorkDetailExample example);

    int updateByExample(@Param("record") JsresDoctorWorkDetail record, @Param("example") JsresDoctorWorkDetailExample example);

    int updateByPrimaryKeySelective(JsresDoctorWorkDetail record);

    int updateByPrimaryKey(JsresDoctorWorkDetail record);
}