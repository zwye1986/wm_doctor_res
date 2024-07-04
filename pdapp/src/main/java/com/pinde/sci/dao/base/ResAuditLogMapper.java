package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResAuditLog;
import com.pinde.sci.model.mo.ResAuditLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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