package com.pinde.sci.biz.eval;

import com.pinde.sci.model.mo.ExpertEvalOrgSpe;
import com.pinde.sci.model.mo.ExpertEvalOrgSpeCfg;
import com.pinde.sci.model.mo.SysUser;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/25.
 */
public interface IExpertEvalOrgSpeBiz {

    List<Map<String,String>> expertOrgSpeList(Map<String, Object> paramMap);

    List<SysUser> getSpeExpertByYearAndOrgFlow(String evalYear, String orgFlow, String speId);

    List<ExpertEvalOrgSpe> readByOrgFlowAndYearAndSpeId(String evalYear, String orgFlow, String speId, String recordStatus);

    ExpertEvalOrgSpe readByFlow(String recordFlow);

    ExpertEvalOrgSpe getByUserAndOrgAndYearAndSpe(String expertUserFlow, String orgFlow, String speId, String evalYear,String recordStatus);

    int save(ExpertEvalOrgSpe old);

    ExpertEvalOrgSpeCfg readExpertOrgSpeCfgByFlow(String recordFlow);

    void saveExpertEvalOrgSpeCfg(String evalYear, String expertUserFlow, String orgFlow, String speId, String[] cfgFlows, String[] recordFlows);

    List<ExpertEvalOrgSpeCfg> getCfgByUserAndOrgAndYear(String expertUserFlow, String orgFlow, String evalYear, String speId, String recordStatus);

    int saveEvalOrgSpe(ExpertEvalOrgSpe old);
}
