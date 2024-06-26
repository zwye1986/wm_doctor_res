package com.pinde.sci.biz.eval;

import com.pinde.sci.model.mo.ExpertEvalCfg;
import com.pinde.sci.model.mo.ExpertEvalResult;
import com.pinde.sci.model.mo.SysDict;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/25.
 */
public interface IEvalCfgBiz {

    List<ExpertEvalCfg> searchParentList(Map<String, Object> paramMap);

    int save(ExpertEvalCfg cfg);

    List<ExpertEvalCfg> searchChildrenList(String cfgFlow);

    ExpertEvalCfg readByFlow(String cfgFlow);

    ExpertEvalCfg findByEvalYear(String evalYear);

    ExpertEvalCfg findByEvalYearNotSelf(String cfgFlow, String evalYear);

    void publishChildrenCfg(String cfgFlow);

    void delChildrenCfg(String cfgFlow);

    ExpertEvalCfg findByCfgName(String cfgName, String evalYear);

    ExpertEvalCfg findByCfgNameNotSelf(String cfgFlow, String cfgName, String evalYear);

    Integer getMaxOrdinal(String parentCfgFlow);

    List<ExpertEvalCfg> getChildrenListNotInCfg(String cfgFlow, String orgFlow, String expertUserFlow);

    ExpertEvalCfg findByEvalYearFirst(String evalYear);

    List<ExpertEvalCfg> getChildrenListNotInCfgNotManage(String cfgFlow, String orgFlow, String expertUserFlow, String speId);

    ExpertEvalCfg getFirstChildByType(String cfgFlow, String id);

    ExpertEvalResult getOrgCfgEvalReustl(String evalYear, String orgFlow, String cfgFlow);

    Map<String,Object> parseContent(String evalContent);

    ExpertEvalCfg getSpeCfg(String evalYear, String speId);

    List<SysDict> getSpeListNotSelf(String cfgFlow);

    ExpertEvalCfg checkSpeCfgNotSelf(String cfgFlow, String speId);

    ExpertEvalCfg findByCfgNameByParent(String cfgName, String evalYear, String parentCfgFlow);

    ExpertEvalCfg findByCfgNameNotSelfByParent(String cfgFlow, String cfgName, String evalYear, String parentCfgFlow);
}
