package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresDoctorDeptDetail;
import com.pinde.sci.model.mo.JsresDoctorDeptDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JsresDoctorDeptDetailMapper {
    int countByExample(JsresDoctorDeptDetailExample example);

    int deleteByExample(JsresDoctorDeptDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresDoctorDeptDetail record);

    int insertSelective(JsresDoctorDeptDetail record);

    List<JsresDoctorDeptDetail> selectByExample(JsresDoctorDeptDetailExample example);

    JsresDoctorDeptDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresDoctorDeptDetail record, @Param("example") JsresDoctorDeptDetailExample example);

    int updateByExample(@Param("record") JsresDoctorDeptDetail record, @Param("example") JsresDoctorDeptDetailExample example);

    int updateByPrimaryKeySelective(JsresDoctorDeptDetail record);

    int updateByPrimaryKey(JsresDoctorDeptDetail record);
}