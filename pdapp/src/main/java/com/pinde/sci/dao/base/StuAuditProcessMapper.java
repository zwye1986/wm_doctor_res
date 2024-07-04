package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.StuAuditProcess;
import com.pinde.sci.model.mo.StuAuditProcessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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