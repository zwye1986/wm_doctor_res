package com.pinde.sci.form.ach;

import com.pinde.sci.model.mo.SrmAchReseachrep;

import java.io.Serializable;

/**
 * 研究报告导出信息
 */
public class AchReseachrepExportForm implements Serializable{
    private static final long serialVersionUID = 4892343123432L;
    private SrmAchReseachrep srmAchReseachrep;
    /*研究报告作者*/
    private String authorName;

    public SrmAchReseachrep getSrmAchReseachrep() {
        return srmAchReseachrep;
    }

    public void setSrmAchReseachrep(SrmAchReseachrep srmAchReseachrep) {
        this.srmAchReseachrep = srmAchReseachrep;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
