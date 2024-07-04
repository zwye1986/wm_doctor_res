package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SysSupervisioUser;
import com.pinde.sci.model.mo.SysSupervisioUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysSupervisioUserMapper {
    int countByExample(SysSupervisioUserExample example);

    int deleteByExample(SysSupervisioUserExample example);

    int deleteByPrimaryKey(String userFlow);

    int insert(SysSupervisioUser record);

    int insertSelective(SysSupervisioUser record);

    List<SysSupervisioUser> selectByExample(SysSupervisioUserExample example);

    SysSupervisioUser selectByPrimaryKey(String userFlow);

    int updateByExampleSelective(@Param("record") SysSupervisioUser record, @Param("example") SysSupervisioUserExample example);

    int updateByExample(@Param("record") SysSupervisioUser record, @Param("example") SysSupervisioUserExample example);

    int updateByPrimaryKeySelective(SysSupervisioUser record);

    int updateByPrimaryKey(SysSupervisioUser record);
}