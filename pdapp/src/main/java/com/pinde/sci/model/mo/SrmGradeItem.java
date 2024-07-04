package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;
import java.math.BigDecimal;

public class SrmGradeItem extends MybatisObject {
    private String itemFlow;

    private String itemName;

    private String schemeFlow;

    private Integer itemScore;

    private String itemScoreTip;

    private String itemDesc;

    private Integer ordinal;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private BigDecimal scoreWeight;

    private String itemType;

    private String secondLevelForm;

    public String getItemFlow() {
        return itemFlow;
    }

    public void setItemFlow(String itemFlow) {
        this.itemFlow = itemFlow;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSchemeFlow() {
        return schemeFlow;
    }

    public void setSchemeFlow(String schemeFlow) {
        this.schemeFlow = schemeFlow;
    }

    public Integer getItemScore() {
        return itemScore;
    }

    public void setItemScore(Integer itemScore) {
        this.itemScore = itemScore;
    }

    public String getItemScoreTip() {
        return itemScoreTip;
    }

    public void setItemScoreTip(String itemScoreTip) {
        this.itemScoreTip = itemScoreTip;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
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

    public BigDecimal getScoreWeight() {
        return scoreWeight;
    }

    public void setScoreWeight(BigDecimal scoreWeight) {
        this.scoreWeight = scoreWeight;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getSecondLevelForm() {
        return secondLevelForm;
    }

    public void setSecondLevelForm(String secondLevelForm) {
        this.secondLevelForm = secondLevelForm;
    }
}