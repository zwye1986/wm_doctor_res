package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class NydwVoteDetail extends MybatisObject {
    private String recordFlow;

    private String voteFlow;

    private String electorFlow;

    private String electorName;

    private String voterFlow;

    private String voterName;

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

    public String getVoterFlow() {
        return voterFlow;
    }

    public void setVoterFlow(String voterFlow) {
        this.voterFlow = voterFlow;
    }

    public String getVoterName() {
        return voterName;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
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