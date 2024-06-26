package com.pinde.sci.form.ach;

import com.pinde.sci.model.mo.SrmAchCopyright;

import java.io.Serializable;

/**
 * 著作权导出信息
 */
public class AchCopyrightExportForm implements Serializable {
    private static final long serialVersionUID = 553132531234321L;
    private SrmAchCopyright srmAchCopyright;
    /*著作权作者*/
    private String authorName;

    public SrmAchCopyright getSrmAchCopyright() {
        return srmAchCopyright;
    }

    public void setSrmAchCopyright(SrmAchCopyright srmAchCopyright) {
        this.srmAchCopyright = srmAchCopyright;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
