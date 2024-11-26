package com.pinde.res.dao.jswjw.ext;

import com.pinde.core.model.SysOrg;

import java.util.List;
import java.util.Map;

public interface SysOrgExtMapper {
    SysOrg selectByPrimaryKey(String orgFlow);
    List<SysOrg> searchOrgNotJointOrg(Map<String, Object> paramMap);
    List<SysOrg> searchJointOrgsByOrg(String orgFlow);
}