package com.pinde.sci.form.czyyjxres;

import java.io.Serializable;

/**
 * @author wangs
 * @Copyright njpdxx.com
 * @since 2017/7/11
 */
public class ThesisForm implements Serializable {
    /**
     * 论文题目
     */
    private String titleName;

    /**
     * 作者名次
     */
    private String authorRank;

    /**
     * 杂志名称
     */
    private String pubName;
    /**
     * 杂志名称发表时间
     */
    private String pubDate;

    /**
     * 刊号
     */
    private String pubNumber;
    /**
     * 获奖情况
     */
    private String awardSituation;
    /**
     * 是否SCI或核心
     */
    private String isSCI;

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

    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
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

    public String getIsSCI() {
        return isSCI;
    }

    public void setIsSCI(String isSCI) {
        this.isSCI = isSCI;
    }
}
