package com.pinde.sci.form.xjgl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XjAuthorOrgForm {
    //所在单位(共同第一作者)
    private String coAuthorOrg;

    //所在单位(共同通讯作者)
    private String messAuthorOrg;

    //本人作者单位
    private String ownerOrg;

    //所在单位(第一作者中文姓名)
    private String authorOrg;

    //通讯作者单位
    private String messAuOrg;

    public String getCoAuthorOrg() {
        return coAuthorOrg;
    }

    public void setCoAuthorOrg(String coAuthorOrg) {
        this.coAuthorOrg = coAuthorOrg;
    }

    public String getMessAuthorOrg() {
        return messAuthorOrg;
    }

    public void setMessAuthorOrg(String messAuthorOrg) {
        this.messAuthorOrg = messAuthorOrg;
    }

    public String getOwnerOrg() {
        return ownerOrg;
    }

    public void setOwnerOrg(String ownerOrg) {
        this.ownerOrg = ownerOrg;
    }

    public String getAuthorOrg() {
        return authorOrg;
    }

    public void setAuthorOrg(String authorOrg) {
        this.authorOrg = authorOrg;
    }

    public String getMessAuOrg() {
        return messAuOrg;
    }

    public void setMessAuOrg(String messAuOrg) {
        this.messAuOrg = messAuOrg;
    }
}

