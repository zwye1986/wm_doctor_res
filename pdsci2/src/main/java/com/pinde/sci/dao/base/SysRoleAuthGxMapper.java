package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SysRoleAuthGx;
import com.pinde.sci.model.mo.SysRoleAuthGxExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleAuthGxMapper {
    int countByExample(SysRoleAuthGxExample example);

    int deleteByExample(SysRoleAuthGxExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SysRoleAuthGx record);

    int insertSelective(SysRoleAuthGx record);

    List<SysRoleAuthGx> selectByExample(SysRoleAuthGxExample example);

    SysRoleAuthGx selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SysRoleAuthGx record, @Param("example") SysRoleAuthGxExample example);

    int updateByExample(@Param("record") SysRoleAuthGx record, @Param("example") SysRoleAuthGxExample example);

    int updateByPrimaryKeySelective(SysRoleAuthGx record);

    int updateByPrimaryKey(SysRoleAuthGx record);
}