package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SysRoleColumn;
import com.pinde.sci.model.mo.SysRoleColumnExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleColumnMapper {
    int countByExample(SysRoleColumnExample example);

    int deleteByExample(SysRoleColumnExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SysRoleColumn record);

    int insertSelective(SysRoleColumn record);

    List<SysRoleColumn> selectByExample(SysRoleColumnExample example);

    SysRoleColumn selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SysRoleColumn record, @Param("example") SysRoleColumnExample example);

    int updateByExample(@Param("record") SysRoleColumn record, @Param("example") SysRoleColumnExample example);

    int updateByPrimaryKeySelective(SysRoleColumn record);

    int updateByPrimaryKey(SysRoleColumn record);
}