package com.pinde.sci.dao.base;

import com.pinde.core.model.SysLoginAbility;
import com.pinde.core.model.SysLoginAbilityExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysLoginAbilityMapper {
    int countByExample(SysLoginAbilityExample example);

    int deleteByExample(SysLoginAbilityExample example);

    int deleteByPrimaryKey(String userCode);

    int insert(SysLoginAbility record);

    int insertSelective(SysLoginAbility record);

    List<SysLoginAbility> selectByExample(SysLoginAbilityExample example);

    SysLoginAbility selectByPrimaryKey(String userCode);

    int updateByExampleSelective(@Param("record") SysLoginAbility record, @Param("example") SysLoginAbilityExample example);

    int updateByExample(@Param("record") SysLoginAbility record, @Param("example") SysLoginAbilityExample example);

    int updateByPrimaryKeySelective(SysLoginAbility record);

    int updateByPrimaryKey(SysLoginAbility record);
}