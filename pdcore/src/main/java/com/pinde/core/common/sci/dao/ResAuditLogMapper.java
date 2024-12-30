package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResAuditLog;
import com.pinde.core.model.ResAuditLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResAuditLogMapper {
    int countByExample(ResAuditLogExample example);

    int deleteByExample(ResAuditLogExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResAuditLog record);

    int insertSelective(ResAuditLog record);

    List<ResAuditLog> selectByExample(ResAuditLogExample example);

    ResAuditLog selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResAuditLog record, @Param("example") ResAuditLogExample example);

    int updateByExample(@Param("record") ResAuditLog record, @Param("example") ResAuditLogExample example);

    int updateByPrimaryKeySelective(ResAuditLog record);

    int updateByPrimaryKey(ResAuditLog record);
}