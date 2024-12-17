package com.pinde.sci.dao.base;

import com.pinde.core.model.SysUserSchool;
import com.pinde.core.model.SysUserSchoolExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserSchoolMapper {
    int countByExample(SysUserSchoolExample example);

    int deleteByExample(SysUserSchoolExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SysUserSchool record);

    int insertSelective(SysUserSchool record);

    List<SysUserSchool> selectByExample(SysUserSchoolExample example);

    SysUserSchool selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SysUserSchool record, @Param("example") SysUserSchoolExample example);

    int updateByExample(@Param("record") SysUserSchool record, @Param("example") SysUserSchoolExample example);

    int updateByPrimaryKeySelective(SysUserSchool record);

    int updateByPrimaryKey(SysUserSchool record);
}