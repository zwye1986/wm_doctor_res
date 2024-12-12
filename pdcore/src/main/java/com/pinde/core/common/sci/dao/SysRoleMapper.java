package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ActivityCfgExt;
import com.pinde.core.model.SysRole;
import com.pinde.core.model.SysRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper {
    int countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(String roleFlow);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(String roleFlow);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    int addActivityCfg(Map<String,String> param);

    int delActivityCfg(@Param("recordFlow")String recordFlow);

    int updActivityCfg(Map<String,String> param);

    List<ActivityCfgExt> searchActvity(Map<String,String> param);

}