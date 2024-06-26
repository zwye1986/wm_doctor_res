package com.pinde.sci.form.ach;

import com.pinde.sci.model.mo.SrmAchPatent;

import java.io.Serializable;

/**
 * 专利导出
 */
public class AchPatentExportForm implements Serializable {
    private static final long serialVersionUID = 5512343123432L;
    private SrmAchPatent srmAchPatent;
    /*专利作者*/
    private String authorName;
    /*序号*/
    private String serial;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public SrmAchPatent getSrmAchPatent() {
        return srmAchPatent;
    }

    public void setSrmAchPatent(SrmAchPatent srmAchPatent) {
        this.srmAchPatent = srmAchPatent;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
