package com.pinde.core.common.sci.dao;

import com.pinde.core.model.PersonStaticExample;
import com.pinde.core.model.SysOrg;
import com.pinde.core.model.SysOrgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    int updateByOrgFlow(@Param("orgFlow") String orgFlow, @Param("orgName") String orgName);

    List<PersonStaticExample> selectByExampleSession(Map<String, Object> paramMap);

    /**
     * 定制sql，不通用，不要乱用
     *
     * @param sysOrg
     * @return
     */
    List<SysOrg> selectJointOrgAllList(SysOrg sysOrg);
}