package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PersonStaticExample;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysOrgExample;
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

    List<SysOrg> selectByExample(SysOrg example);

    /**
     * 定制sql，不通用，不要乱用
     * @param sysOrg
     * @return
     */
    List<SysOrg> selectJointOrgAllList(SysOrg sysOrg);

    int updateByExampleSelective(@Param("record") SysOrg record, @Param("example") SysOrgExample example);

    int updateByExampleWithBLOBs(@Param("record") SysOrg record, @Param("example") SysOrgExample example);

    int updateByExample(@Param("record") SysOrg record, @Param("example") SysOrgExample example);

    int updateByPrimaryKeySelective(SysOrg record);

    int updateByPrimaryKeyWithBLOBs(SysOrg record);

    int updateByPrimaryKey(SysOrg record);

    int updateByOrgFlow(@Param("orgFlow") String orgFlow,@Param("orgName") String orgName);

    List<PersonStaticExample> selectByExampleSession(Map<String, Object> paramMap);
}