package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FstuAuditProcess;
import com.pinde.sci.model.mo.FstuAuditProcessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FstuAuditProcessMapper {
    int countByExample(FstuAuditProcessExample example);

    int deleteByExample(FstuAuditProcessExample example);

    int deleteByPrimaryKey(String processFlow);

    int insert(FstuAuditProcess record);

    int insertSelective(FstuAuditProcess record);

    List<FstuAuditProcess> selectByExample(FstuAuditProcessExample example);

    FstuAuditProcess selectByPrimaryKey(String processFlow);

    int updateByExampleSelective(@Param("record") FstuAuditProcess record, @Param("example") FstuAuditProcessExample example);

    int updateByExample(@Param("record") FstuAuditProcess record, @Param("example") FstuAuditProcessExample example);

    int updateByPrimaryKeySelective(FstuAuditProcess record);

    int updateByPrimaryKey(FstuAuditProcess record);
}