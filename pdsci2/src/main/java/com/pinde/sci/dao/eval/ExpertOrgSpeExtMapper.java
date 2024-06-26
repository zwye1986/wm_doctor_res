package com.pinde.sci.dao.eval;

import com.pinde.sci.model.mo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExpertOrgSpeExtMapper {

    List<Map<String,String>> expertOrgSpeList(Map<String, Object> paramMap);

    List<SysUser> getSpeExpertByYearAndOrgFlow(@Param("evalYear") String evalYear, @Param("orgFlow") String orgFlow, @Param("speId") String speId);
}
