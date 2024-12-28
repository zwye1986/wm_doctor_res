package com.pinde.core.common.sci.dao;

import com.pinde.core.model.SysUserLog;
import com.pinde.core.model.SysUserLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserLogMapper {
    int countByExample(SysUserLogExample example);

    int deleteByExample(SysUserLogExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SysUserLog record);

    int insertSelective(SysUserLog record);

    List<SysUserLog> selectByExample(SysUserLogExample example);

    SysUserLog selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SysUserLog record, @Param("example") SysUserLogExample example);

    int updateByExample(@Param("record") SysUserLog record, @Param("example") SysUserLogExample example);

    int updateByPrimaryKeySelective(SysUserLog record);

    int updateByPrimaryKey(SysUserLog record);
}