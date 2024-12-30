package com.pinde.core.common.sci.dao;

import com.pinde.core.model.SysSmsLog;
import com.pinde.core.model.SysSmsLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysSmsLogMapper {
    int countByExample(SysSmsLogExample example);

    int deleteByExample(SysSmsLogExample example);

    int deleteByPrimaryKey(String smsLogFlow);

    int insert(SysSmsLog record);

    int insertSelective(SysSmsLog record);

    List<SysSmsLog> selectByExample(SysSmsLogExample example);

    SysSmsLog selectByPrimaryKey(String smsLogFlow);

    int updateByExampleSelective(@Param("record") SysSmsLog record, @Param("example") SysSmsLogExample example);

    int updateByExample(@Param("record") SysSmsLog record, @Param("example") SysSmsLogExample example);

    int updateByPrimaryKeySelective(SysSmsLog record);

    int updateByPrimaryKey(SysSmsLog record);
}