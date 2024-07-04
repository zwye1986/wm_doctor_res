package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SysRolePopedom;
import com.pinde.sci.model.mo.SysRolePopedomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRolePopedomMapper {
    int countByExample(SysRolePopedomExample example);

    int deleteByExample(SysRolePopedomExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SysRolePopedom record);

    int insertSelective(SysRolePopedom record);

    List<SysRolePopedom> selectByExample(SysRolePopedomExample example);

    SysRolePopedom selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SysRolePopedom record, @Param("example") SysRolePopedomExample example);

    int updateByExample(@Param("record") SysRolePopedom record, @Param("example") SysRolePopedomExample example);

    int updateByPrimaryKeySelective(SysRolePopedom record);

    int updateByPrimaryKey(SysRolePopedom record);
}