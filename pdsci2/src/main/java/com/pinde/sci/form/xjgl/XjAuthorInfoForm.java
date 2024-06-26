package com.pinde.sci.form.xjgl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XjAuthorInfoForm {
    //共同第一作者中文姓名
    private String coAuthorName;
    //所在单位(共同第一作者)
    private List<XjAuthorOrgForm> coAuthorOrgList;

    //共同通讯作者中文姓名
    private String messAuthorName;
    //所在单位(共同通讯作者)
    private List<XjAuthorOrgForm> messAuthorOrgList;

    public String getCoAuthorName() {
        return coAuthorName;
    }

    public void setCoAuthorName(String coAuthorName) {
        this.coAuthorName = coAuthorName;
    }

    public List<XjAuthorOrgForm> getCoAuthorOrgList() {
        return coAuthorOrgList;
    }

    public void setCoAuthorOrgList(List<XjAuthorOrgForm> coAuthorOrgList) {
        this.coAuthorOrgList = coAuthorOrgList;
    }

    public String getMessAuthorName() {
        return messAuthorName;
    }

    public void setMessAuthorName(String messAuthorName) {
        this.messAuthorName = messAuthorName;
    }

    public List<XjAuthorOrgForm> getMessAuthorOrgList() {
        return messAuthorOrgList;
    }

    public void setMessAuthorOrgList(List<XjAuthorOrgForm> messAuthorOrgList) {
        this.messAuthorOrgList = messAuthorOrgList;
    }
}

