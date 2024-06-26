package com.pinde.sci.dao.nyzl;


import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.nyzl.NyzlOrgRecruitQuotaExt;

import java.util.List;
import java.util.Map;

public interface RecruitQuotaExtMapper {
    //查询招生指标
    List<NyzlOrgRecruitQuotaExt> queryOrgList(SysOrg sysOrg);
    List<Map<String,String>> queryFwhList(Map<String,String> map);
    //统计指标数
    Map<String,String> countRecruitQuota(Map<String,String> map);
}
