package com.pinde.sci.form.gzzyjxres;

import java.io.Serializable;

/**
 * 进修学员科研信息（课题）
 * @Copyright njpdxx.com
 * @since 2017/6/5
 */
public class ProjectForm implements Serializable{
    /**
     * 标题内容
     */
    private String titleName;

    /**
     * 作者名次
     */
    private String authorRank;

    /**
     * 课题级别
     */
    private String projectLevel;
    /**
     * 课题获批时间
     */
    private String projectDate;

    /**
     * 合同书号
     */
    private String pubNumber;
    /**
     * 获奖情况
     */
    private String awardSituation;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getAuthorRank() {
        return authorRank;
    }

    public void setAuthorRank(String authorRank) {
        this.authorRank = authorRank;
    }

    public String getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(String projectLevel) {
        this.projectLevel = projectLevel;
    }

    public String getProjectDate() {
        return projectDate;
    }

    public void setProjectDate(String projectDate) {
        this.projectDate = projectDate;
    }

    public String getPubNumber() {
        return pubNumber;
    }

    public void setPubNumber(String pubNumber) {
        this.pubNumber = pubNumber;
    }

    public String getAwardSituation() {
        return awardSituation;
    }

    public void setAwardSituation(String awardSituation) {
        this.awardSituation = awardSituation;
    }
}
