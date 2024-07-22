package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresOrgTeacherAuditTemp;
import com.pinde.sci.model.mo.JsresOrgTeacherAuditTempExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresOrgTeacherAuditTempMapper {
    int countByExample(JsresOrgTeacherAuditTempExample example);

    int deleteByExample(JsresOrgTeacherAuditTempExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresOrgTeacherAuditTemp record);

    int insertSelective(JsresOrgTeacherAuditTemp record);

    List<JsresOrgTeacherAuditTemp> selectByExample(JsresOrgTeacherAuditTempExample example);

    JsresOrgTeacherAuditTemp selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresOrgTeacherAuditTemp record, @Param("example") JsresOrgTeacherAuditTempExample example);

    int updateByExample(@Param("record") JsresOrgTeacherAuditTemp record, @Param("example") JsresOrgTeacherAuditTempExample example);

    int updateByPrimaryKeySelective(JsresOrgTeacherAuditTemp record);

    int updateByPrimaryKey(JsresOrgTeacherAuditTemp record);
}