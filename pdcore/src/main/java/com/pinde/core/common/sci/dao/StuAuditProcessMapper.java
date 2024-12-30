package com.pinde.core.common.sci.dao;

import com.pinde.core.model.StuAuditProcess;
import com.pinde.core.model.StuAuditProcessExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StuAuditProcessMapper {
    int countByExample(StuAuditProcessExample example);

    int deleteByExample(StuAuditProcessExample example);

    int deleteByPrimaryKey(String processFlow);

    int insert(StuAuditProcess record);

    int insertSelective(StuAuditProcess record);

    List<StuAuditProcess> selectByExample(StuAuditProcessExample example);

    StuAuditProcess selectByPrimaryKey(String processFlow);

    int updateByExampleSelective(@Param("record") StuAuditProcess record, @Param("example") StuAuditProcessExample example);

    int updateByExample(@Param("record") StuAuditProcess record, @Param("example") StuAuditProcessExample example);

    int updateByPrimaryKeySelective(StuAuditProcess record);

    int updateByPrimaryKey(StuAuditProcess record);
}