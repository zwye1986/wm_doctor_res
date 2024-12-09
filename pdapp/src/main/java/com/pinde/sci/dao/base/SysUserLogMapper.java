package com.pinde.sci.dao.base;

import com.pinde.core.model.SysUserLog;
import com.pinde.core.model.SysUserLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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