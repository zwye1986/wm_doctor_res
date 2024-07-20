package com.pinde.sci.biz.eval;

import com.pinde.sci.model.mo.ExpertEvalOrg;
import com.pinde.sci.model.mo.ExpertEvalOrgCfg;
import com.pinde.sci.model.mo.SysUser;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */
public interface IExpertEvalOrgBiz {
    List<ExpertEvalOrg> readByUserFlowAndYear(String userFlow, String evalYear,String recordStatus);

    ExpertEvalOrg readByFlow(String recordFlow);

    ExpertEvalOrg getByUserAndOrgAndYear(String expertUserFlow, String orgFlow, String evalYear, String recordStatus);

    int save(ExpertEvalOrg old);

    int saveCfg(ExpertEvalOrgCfg old);

    List<ExpertEvalOrgCfg> getCfgByUserAndOrgAndYear(String expertUserFlow, String orgFlow, String evalYear, String s);

    ExpertEvalOrgCfg readExpertOrgCfgByFlow(String recordFlow);

    int checkCfgHasResult(String recordFlow);

    void saveExpertEvalOrgCfg(String evalYear,String expertUserFlow, String orgFlow, String[] cfgFlows, String[] recordFlows);

    List<SysUser> getOrgExpertByYearAndOrgFlow(String evalYear, String orgFlow);

    List<ExpertEvalOrg> readByOrgFlowAndYear(String orgFlow, String evalYear, String recordStatus);

    int checkSpeCfgHasResult(String recordFlow, String orgFlow, String speId);

    int saveEvalOrg(ExpertEvalOrg old);
}
