package com.pinde.sci.form.ach;

import com.pinde.sci.model.mo.SrmAchSat;

import java.io.Serializable;

/**
 * 奖项信息导出
 */
public class AchSatExportForm implements Serializable{
    private static final long serialVersionUID = 48948658723432L;
    private SrmAchSat srmAchSat;
    /*奖项作者*/
    private String authorName;

    public SrmAchSat getSrmAchSat() {
        return srmAchSat;
    }

    public void setSrmAchSat(SrmAchSat srmAchSat) {
        this.srmAchSat = srmAchSat;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
