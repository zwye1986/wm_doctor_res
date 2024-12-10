package com.pinde.sci.dao.base;

import java.util.List;

import com.pinde.core.model.SysUserDept;
import com.pinde.core.model.SysUserDeptExample;
import org.apache.ibatis.annotations.Param;

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