package com.pinde.sci.dao.base;

import com.pinde.core.model.JsresOrgTeacherAuditTemp;
import com.pinde.core.model.JsresOrgTeacherAuditTempExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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