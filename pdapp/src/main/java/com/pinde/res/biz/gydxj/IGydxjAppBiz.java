package com.pinde.res.biz.gydxj;

import com.pinde.sci.model.mo.*;

import java.util.List;

public interface IGydxjAppBiz {

    SysUser getUserByCode(String userCode);

    SysUser readSysUser(String sysUserFlow);

    List<SysUserRole> getSysUserRole(String userFlow);

    String getCfgByCode(String code);

    ResDoctor readResDoctor(String userFlow);

    EduUser readEduUser(String userFlow);

    List<EduTeachingnotice> searchByRoles(String sysRoleFlow, String infoTypeId);

    EduTeachingnotice findNoticByFlow(String flow);

    int updateUser(SysUser sysUser);

    SysCfg readCfg(String cfgCode);

    List<EduUserInfoStatus> searchPartStatus(String userFlow);

    List<NydsOfficialDoctor> queryDoctorList(NydsOfficialDoctor officialDoctor);

    List<SysOrg> searchSysOrg();

    List<SysDept> searchDeptByOrg(String orgFlow);

    SysDept readSysDept(String deptFlow);

    EduCourse readEduCourse(String courseFlow);

    int saveEduUser(EduUser user);
}
