package com.pinde.sci.biz.nyzl;


import com.pinde.sci.model.mo.NyzlRecruitQuota;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.nyzl.NyzlOrgRecruitQuotaExt;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IRecruitQuotaBiz {
    //查询招生指标
    List<NyzlRecruitQuota> searchRecruitQuotaList(NyzlRecruitQuota recruitQuota);
    //保存招生指标
    int save(NyzlRecruitQuota recruitQuota);
    //根据recordFlow查询
    NyzlRecruitQuota searchRecruitQuotaByRecordFlow(String recordFlow);
    //查询培养单位
    List<NyzlOrgRecruitQuotaExt> searchOrgList(SysOrg sysOrg);
    //查询分委会招生指标
    List<Map<String,String>> searchFwhList(Map<String,String> map);
    //统计指标数
    Map<String,String> countRecruitQuota(String recruitYear,String stuSignFlag,String orgFlow);
    //招生指标导入操作
    int importRecruitQuota(MultipartFile file, String stuSignFlag);
}
