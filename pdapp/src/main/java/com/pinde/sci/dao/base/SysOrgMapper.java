package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysOrgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysOrgMapper {
    int countByExample(SysOrgExample example);

    int deleteByExample(SysOrgExample example);

    int deleteByPrimaryKey(String orgFlow);

    int insert(SysOrg record);

    int insertSelective(SysOrg record);

    List<SysOrg> selectByExampleWithBLOBs(SysOrgExample example);

    List<SysOrg> selectByExample(SysOrgExample example);

    SysOrg selectByPrimaryKey(String orgFlow);

    int updateByExampleSelective(@Param("record") SysOrg record, @Param("example") SysOrgExample example);

    int updateByExampleWithBLOBs(@Param("record") SysOrg record, @Param("example") SysOrgExample example);

    int updateByExample(@Param("record") SysOrg record, @Param("example") SysOrgExample example);

    int updateByPrimaryKeySelective(SysOrg record);

    int updateByPrimaryKeyWithBLOBs(SysOrg record);

    int updateByPrimaryKey(SysOrg record);
}