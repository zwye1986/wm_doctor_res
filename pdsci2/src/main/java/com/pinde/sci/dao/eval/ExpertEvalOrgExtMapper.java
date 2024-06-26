package com.pinde.sci.dao.eval;

import com.pinde.sci.model.mo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExpertEvalOrgExtMapper {

    int checkCfgHasResult(@Param("recordFlow") String recordFlow);

    List<SysUser> getOrgExpertByYearAndOrgFlow(@Param("evalYear") String evalYear, @Param("orgFlow") String orgFlow);

    int checkSpeCfgHasResult(@Param("recordFlow") String recordFlow, @Param("orgFlow") String orgFlow, @Param("speId") String speId);
}
