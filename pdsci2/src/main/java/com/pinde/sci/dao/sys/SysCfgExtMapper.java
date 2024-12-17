package com.pinde.sci.dao.sys;

import com.pinde.core.model.SysCfg;

import java.util.List;
import java.util.Map;

public interface SysCfgExtMapper {
    int getOpenSwitchCount(Map<String,Object> paramMap);

    List<SysCfg> searchByPreAndSuf(Map<String,Object> paramMap);

    int updateSchoolSubmit(Map<String, Object> map);

    int saveSchoolSubmit(List<String> dictFlowList);

    int updateCheckAll(Map<String, Object> param);

    int updateSchoolNotSubmit(List<String> dictFlowList);
}


