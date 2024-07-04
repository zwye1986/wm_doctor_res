package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class NydbCommitteeMember extends MybatisObject {
    private String recordFlow;

    private String defenceFlow;

    private String memberName;

    private String memberPost;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String memberOrg;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getDefenceFlow() {
        return defenceFlow;
    }

    public void setDefenceFlow(String defenceFlow) {
        this.defenceFlow = defenceFlow;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPost() {
        return memberPost;
    }

    public void setMemberPost(String memberPost) {
        this.memberPost = memberPost;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserFlow() {
        return createUserFlow;
    }

    public void setCreateUserFlow(String createUserFlow) {
        this.createUserFlow = createUserFlow;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUserFlow() {
        return modifyUserFlow;
    }

    public void setModifyUserFlow(String modifyUserFlow) {
        this.modifyUserFlow = modifyUserFlow;
    }

    public String getMemberOrg() {
        return memberOrg;
    }

    public void setMemberOrg(String memberOrg) {
        this.memberOrg = memberOrg;
    }
}