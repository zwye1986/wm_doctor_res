package com.pinde.sci.model.jszy;

import com.pinde.core.model.ResJointOrg;

/**
 * Created by pdkj20 on 2018/3/29.
 */
public class JszyResJointOrgExt extends ResJointOrg {
    private String jointOrgLevelName;
    private String jointOrgCode;

    public String getJointOrgLevelName() {
        return jointOrgLevelName;
    }

    public void setJointOrgLevelName(String jointOrgLevelName) {
        this.jointOrgLevelName = jointOrgLevelName;
    }

    public String getJointOrgCode() {
        return jointOrgCode;
    }

    public void setJointOrgCode(String jointOrgCode) {
        this.jointOrgCode = jointOrgCode;
    }
}
