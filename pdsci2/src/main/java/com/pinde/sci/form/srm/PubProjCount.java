package com.pinde.sci.form.srm;

import java.io.Serializable;

/**
 * Created by www.0001.Ga on 2017-08-01.
 */
public class PubProjCount implements Serializable {
    private static final long serialVersionUID = 393139759780544227L;
    private String projTypeId;//项目类型
    private String projTypeName;//项目类型
    private int applyNum;//项目申报数
    private int applyPassNum;//申报书审核通过数
    private int applyPassScale;//申报审核通过比例
    private int reviewNum;//网络评审数
    private int approvePassNum;//立项通过数量
    private int approvePassScale;//立项通过比例

    public String getProjTypeId() {
        return projTypeId;
    }

    public void setProjTypeId(String projTypeId) {
        this.projTypeId = projTypeId;
    }

    public String getProjTypeName() {
        return projTypeName;
    }

    public void setProjTypeName(String projTypeName) {
        this.projTypeName = projTypeName;
    }

    public int getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(int applyNum) {
        this.applyNum = applyNum;
    }

    public int getApplyPassNum() {
        return applyPassNum;
    }

    public void setApplyPassNum(int applyPassNum) {
        this.applyPassNum = applyPassNum;
    }

    public int getApplyPassScale() {
        return applyPassScale;
    }

    public void setApplyPassScale(int applyPassScale) {
        this.applyPassScale = applyPassScale;
    }

    public int getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(int reviewNum) {
        this.reviewNum = reviewNum;
    }

    public int getApprovePassNum() {
        return approvePassNum;
    }

    public void setApprovePassNum(int approvePassNum) {
        this.approvePassNum = approvePassNum;
    }

    public int getApprovePassScale() {
        return approvePassScale;
    }

    public void setApprovePassScale(int approvePassScale) {
        this.approvePassScale = approvePassScale;
    }
}
