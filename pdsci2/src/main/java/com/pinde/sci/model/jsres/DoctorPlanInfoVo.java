package com.pinde.sci.model.jsres;

import com.pinde.core.model.SysOrg;

import java.util.List;

/**
 * @创建人 zsq
 * @创建时间 2021/6/3
 * 描述
 */
public class DoctorPlanInfoVo implements java.io.Serializable {
    private String signupMsg;

    private SysOrg sysOrg;

    private String signupBtnFlag;

    private List<OrgSpeListVo> OrgSpeList;

    public String getSignupMsg() {
        return signupMsg;
    }

    public void setSignupMsg(String signupMsg) {
        this.signupMsg = signupMsg;
    }

    public SysOrg getSysOrg() {
        return sysOrg;
    }

    public void setSysOrg(SysOrg sysOrg) {
        this.sysOrg = sysOrg;
    }

    public String getSignupBtnFlag() {
        return signupBtnFlag;
    }

    public void setSignupBtnFlag(String signupBtnFlag) {
        this.signupBtnFlag = signupBtnFlag;
    }

    public List<OrgSpeListVo> getOrgSpeList() {
        return OrgSpeList;
    }

    public void setOrgSpeList(List<OrgSpeListVo> orgSpeList) {
        OrgSpeList = orgSpeList;
    }
}
