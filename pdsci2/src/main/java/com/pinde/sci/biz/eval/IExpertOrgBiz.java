package com.pinde.sci.biz.eval;

import com.pinde.sci.model.mo.ExpertOrg;
import com.pinde.sci.model.mo.SysOrg;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/25.
 */
public interface IExpertOrgBiz {

    List<SysOrg> searchOrg(Map<String, Object> paramMap);

    ExpertOrg readByFlow(String recordFlow);

    int save(ExpertOrg old);

    ExpertOrg getByOrgAndYear(String orgFlow, String evalYear, String recordStatus);

    int getExpertSpeByOrgAndYear(String orgFlow, String evalYear);

    int findExpertOrgResult(String evalYear, String orgFlow, String speId, String expertUserFlow);

    List<SysOrg> evalOrgQueryList(Map<String, Object> paramMap);

    List<Map<String,Object>> showOrgClinicalList(Map<String, Object> paramMap);

    List<Map<String,Object>> evalOrgQueryOrgPm(Map<String, Object> paramMap);

    List<Map<String,Object>> evalOrgQueryOrgSpePm(Map<String, Object> paramMap);
}
