package com.pinde.core.model;

public class PersonChangeEntity extends SysOrg {

    /**
     * 延培人数
     */
    private Integer changeTimeNum;

    /**
     * 变更基地人数
     */
    private Integer speChangeNum;

    public Integer getChangeTimeNum() {
        return changeTimeNum;
    }

    public void setChangeTimeNum(Integer changeTimeNum) {
        this.changeTimeNum = changeTimeNum;
    }

    public Integer getSpeChangeNum() {
        return speChangeNum;
    }

    public void setSpeChangeNum(Integer speChangeNum) {
        this.speChangeNum = speChangeNum;
    }
}
