package com.pinde.sci.form.xjgl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XjPaperTitleExtForm {
    //全部作者中文名单
    private String authorChiName;
    //全部作者英文名单
    private String authorEngName;
    //本人在全部作者中的排名
    private String ownerRank;
    //有无共同一作
    private String isCoAuthor;

    //****************有 共同一作 begin****************/
    //共同第一作者中文名单
    private String firstCoAuthor;
    //共同第一作者中文名单 详情
    private List<XjAuthorInfoForm> coAuthorList;
    //本人在共同一作中的排名
    private String ownerCoRank;
    //****************有 共同一作 end****************/

    //****************无 共同一作 begin****************/
    //第一作者中文名单
    private String firstAuthor;
    //所在单位
    private List<XjAuthorOrgForm> authorOrgList;
    //****************无 共同一作 end****************/

    //本人作者单位
    private List<XjAuthorOrgForm> ownerOrgList;
    //有无共同通讯作者
    private String isCoMessAuthor;

    //****************有 共同通讯作者 begin****************/
    //共同通讯作者中文名单
    private String messCoAuthor;
    //共同通讯作者中文名单 详情
    private List<XjAuthorInfoForm> messAuthorList;
    //****************有 共同通讯作者 end****************/

    //****************无 共同通讯作者 begin****************/
    //通讯作者姓名
    private String messAuthor;
    //通讯作者单位
    private List<XjAuthorOrgForm> messAuthorOrgList;
    //****************无 共同通讯作者 end****************/

    //导师是否通讯作者
    private String isMessAuthor;
    //刊物类型
    private String journalType;
    //SCI大类分区
    private String sciBigArea;
    //SCI小类分区
    private String sciSmalArea;
    //SCI影响因子
    private String scifactor;
    //文章类型
    private String articleType;
    //文章发表状态
    private String articleStatus;
    //备注
    private String memo;

    public String getAuthorChiName() {
        return authorChiName;
    }

    public void setAuthorChiName(String authorChiName) {
        this.authorChiName = authorChiName;
    }

    public String getAuthorEngName() {
        return authorEngName;
    }

    public void setAuthorEngName(String authorEngName) {
        this.authorEngName = authorEngName;
    }

    public String getOwnerRank() {
        return ownerRank;
    }

    public void setOwnerRank(String ownerRank) {
        this.ownerRank = ownerRank;
    }

    public String getIsCoAuthor() {
        return isCoAuthor;
    }

    public void setIsCoAuthor(String isCoAuthor) {
        this.isCoAuthor = isCoAuthor;
    }

    public String getFirstCoAuthor() {
        return firstCoAuthor;
    }

    public void setFirstCoAuthor(String firstCoAuthor) {
        this.firstCoAuthor = firstCoAuthor;
    }

    public List<XjAuthorInfoForm> getCoAuthorList() {
        return coAuthorList;
    }

    public void setCoAuthorList(List<XjAuthorInfoForm> coAuthorList) {
        this.coAuthorList = coAuthorList;
    }

    public String getOwnerCoRank() {
        return ownerCoRank;
    }

    public void setOwnerCoRank(String ownerCoRank) {
        this.ownerCoRank = ownerCoRank;
    }

    public String getFirstAuthor() {
        return firstAuthor;
    }

    public void setFirstAuthor(String firstAuthor) {
        this.firstAuthor = firstAuthor;
    }

    public List<XjAuthorOrgForm> getAuthorOrgList() {
        return authorOrgList;
    }

    public void setAuthorOrgList(List<XjAuthorOrgForm> authorOrgList) {
        this.authorOrgList = authorOrgList;
    }

    public List<XjAuthorOrgForm> getOwnerOrgList() {
        return ownerOrgList;
    }

    public void setOwnerOrgList(List<XjAuthorOrgForm> ownerOrgList) {
        this.ownerOrgList = ownerOrgList;
    }

    public String getIsCoMessAuthor() {
        return isCoMessAuthor;
    }

    public void setIsCoMessAuthor(String isCoMessAuthor) {
        this.isCoMessAuthor = isCoMessAuthor;
    }

    public String getMessCoAuthor() {
        return messCoAuthor;
    }

    public void setMessCoAuthor(String messCoAuthor) {
        this.messCoAuthor = messCoAuthor;
    }

    public List<XjAuthorInfoForm> getMessAuthorList() {
        return messAuthorList;
    }

    public void setMessAuthorList(List<XjAuthorInfoForm> messAuthorList) {
        this.messAuthorList = messAuthorList;
    }

    public String getMessAuthor() {
        return messAuthor;
    }

    public void setMessAuthor(String messAuthor) {
        this.messAuthor = messAuthor;
    }

    public List<XjAuthorOrgForm> getMessAuthorOrgList() {
        return messAuthorOrgList;
    }

    public void setMessAuthorOrgList(List<XjAuthorOrgForm> messAuthorOrgList) {
        this.messAuthorOrgList = messAuthorOrgList;
    }

    public String getIsMessAuthor() {
        return isMessAuthor;
    }

    public void setIsMessAuthor(String isMessAuthor) {
        this.isMessAuthor = isMessAuthor;
    }

    public String getJournalType() {
        return journalType;
    }

    public void setJournalType(String journalType) {
        this.journalType = journalType;
    }

    public String getSciBigArea() {
        return sciBigArea;
    }

    public void setSciBigArea(String sciBigArea) {
        this.sciBigArea = sciBigArea;
    }

    public String getSciSmalArea() {
        return sciSmalArea;
    }

    public void setSciSmalArea(String sciSmalArea) {
        this.sciSmalArea = sciSmalArea;
    }

    public String getScifactor() {
        return scifactor;
    }

    public void setScifactor(String scifactor) {
        this.scifactor = scifactor;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(String articleStatus) {
        this.articleStatus = articleStatus;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}

