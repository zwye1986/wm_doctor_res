package com.pinde.sci.form.ach;

import com.pinde.sci.model.mo.SrmAchBook;

import java.io.Serializable;

/**
 *著作导出信息
 */
public class AchBookExportForm implements Serializable {
    private static final long serialVersionUID = 4313531234321L;
    private SrmAchBook srmAchBook;
    /*参编作者*/
    private String authorName;

    public SrmAchBook getSrmAchBook() {
        return srmAchBook;
    }

    public void setSrmAchBook(SrmAchBook srmAchBook) {
        this.srmAchBook = srmAchBook;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
