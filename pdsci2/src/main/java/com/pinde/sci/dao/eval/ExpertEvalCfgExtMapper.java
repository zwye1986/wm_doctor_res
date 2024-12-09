package com.pinde.sci.dao.eval;

import com.pinde.core.model.SysDict;
import com.pinde.sci.model.mo.ExpertEvalCfg;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExpertEvalCfgExtMapper {

    List<ExpertEvalCfg> searchParentList(Map<String, Object> paramMap);

    Integer getMaxOrdinal(@Param("cfgFlow") String cfgFlow);

    List<ExpertEvalCfg> getChildrenListNotInCfg(@Param("cfgFlow") String cfgFlow, @Param("orgFlow") String orgFlow, @Param("expertUserFlow") String expertUserFlow);

    List<ExpertEvalCfg> getChildrenListNotInCfgNotManage(@Param("cfgFlow") String cfgFlow, @Param("orgFlow") String orgFlow, @Param("expertUserFlow") String expertUserFlow, @Param("speId") String speId);

    List<SysDict> getSpeListNotSelf(@Param("cfgFlow") String cfgFlow);

    List<ExpertEvalCfg> checkSpeCfgNotSelf(@Param("cfgFlow") String cfgFlow, @Param("speId") String speId);
}
