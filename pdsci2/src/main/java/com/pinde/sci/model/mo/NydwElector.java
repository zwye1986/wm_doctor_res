package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class NydwElector extends MybatisObject {
    private String recordFlow;

    private String voteFlow;

    private String partyBranchId;

    private String partyBranchName;

    private String electorFlow;

    private String electorName;

    private String sexId;

    private String sexName;

    private String pydwOrgFlow;

    private String pydwOrgName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getVoteFlow() {
        return voteFlow;
    }

    public void setVoteFlow(String voteFlow) {
        this.voteFlow = voteFlow;
    }

    public String getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(String partyBranchId) {
        this.partyBranchId = partyBranchId;
    }

    public String getPartyBranchName() {
        return partyBranchName;
    }

    public void setPartyBranchName(String partyBranchName) {
        this.partyBranchName = partyBranchName;
    }

    public String getElectorFlow() {
        return electorFlow;
    }

    public void setElectorFlow(String electorFlow) {
        this.electorFlow = electorFlow;
    }

    public String getElectorName() {
        return electorName;
    }

    public void setElectorName(String electorName) {
        this.electorName = electorName;
    }

    public String getSexId() {
        return sexId;
    }

    public void setSexId(String sexId) {
        this.sexId = sexId;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getPydwOrgFlow() {
        return pydwOrgFlow;
    }

    public void setPydwOrgFlow(String pydwOrgFlow) {
        this.pydwOrgFlow = pydwOrgFlow;
    }

    public String getPydwOrgName() {
        return pydwOrgName;
    }

    public void setPydwOrgName(String pydwOrgName) {
        this.pydwOrgName = pydwOrgName;
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
}