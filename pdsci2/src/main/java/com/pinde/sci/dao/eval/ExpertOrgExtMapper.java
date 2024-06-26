package com.pinde.sci.dao.eval;

import com.pinde.sci.model.mo.SysOrg;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExpertOrgExtMapper {

    List<SysOrg> searchOrg(Map<String, Object> paramMap);

    int getExpertSpeByOrgAndYear(@Param("orgFlow") String orgFlow, @Param("evalYear") String evalYear);

    List<SysOrg> evalOrgQueryList(Map<String, Object> paramMap);

    List<Map<String,Object>> showOrgClinicalList(Map<String, Object> paramMap);

    List<Map<String,Object>> evalOrgQueryOrgPm(Map<String, Object> paramMap);

    List<Map<String,Object>> evalOrgQueryOrgSpePm(Map<String, Object> paramMap);
}
