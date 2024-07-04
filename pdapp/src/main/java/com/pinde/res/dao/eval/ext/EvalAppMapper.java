package com.pinde.res.dao.eval.ext;

import com.pinde.sci.model.mo.ExpertEvalCfg;
import com.pinde.sci.model.mo.SysOrg;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EvalAppMapper {

	List<SysOrg> getExpertEvalOrg(@Param("evalYear") String evalYear, @Param("userFlow") String userFlow);

	List<ExpertEvalCfg> getExpertCfgChildrens(@Param("cfgFlow") String cfgFlow, @Param("evalYear") String evalYear, @Param("userFlow") String userFlow, @Param("orgFlow") String orgFlow);
}
