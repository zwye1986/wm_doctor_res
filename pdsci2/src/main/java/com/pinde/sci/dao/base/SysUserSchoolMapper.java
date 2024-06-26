package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SysUserSchool;
import com.pinde.sci.model.mo.SysUserSchoolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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