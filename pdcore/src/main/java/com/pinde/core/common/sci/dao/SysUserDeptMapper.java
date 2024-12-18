package com.pinde.core.common.sci.dao;

import com.pinde.core.model.SysUserDept;
import com.pinde.core.model.SysUserDeptExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserDeptMapper {
    int countByExample(SysUserDeptExample example);

    int deleteByExample(SysUserDeptExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SysUserDept record);

    int insertSelective(SysUserDept record);

    List<SysUserDept> selectByExample(SysUserDeptExample example);

    SysUserDept selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SysUserDept record, @Param("example") SysUserDeptExample example);

    int updateByExample(@Param("record") SysUserDept record, @Param("example") SysUserDeptExample example);

    int updateByPrimaryKeySelective(SysUserDept record);

    int updateByPrimaryKey(SysUserDept record);
}