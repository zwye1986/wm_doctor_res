package com.pinde.sci.dao.base;

import com.pinde.sci.model.jsres.ActivityCfgExt;
import com.pinde.core.model.ActivityAuditCfg;
import com.pinde.sci.model.mo.SysRole;
import com.pinde.sci.model.mo.SysRoleExample;
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

    List<ActivityAuditCfg> searchActvityNew(Map<String, String> param);
}