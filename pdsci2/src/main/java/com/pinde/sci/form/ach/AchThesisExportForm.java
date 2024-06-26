package com.pinde.sci.form.ach;

import com.pinde.sci.model.mo.SrmAchThesis;

import java.io.Serializable;

/**
 * 论文导出信息
 */
public class AchThesisExportForm implements Serializable {
    private static final long serialVersionUID = 4892845623432L;
    private SrmAchThesis srmAchThesis;
    private String authorName;
    private String workCode;
    private String firstAuthor;
    private String parallelFirstAuthor;
    private String correspondingAuthor;
    private String parallelCorrespondingAuthor;

    public String getParallelFirstAuthor() {
        return parallelFirstAuthor;
    }

    public void setParallelFirstAuthor(String parallelFirstAuthor) {
        this.parallelFirstAuthor = parallelFirstAuthor;
    }

    public String getParallelCorrespondingAuthor() {
        return parallelCorrespondingAuthor;
    }

    public void setParallelCorrespondingAuthor(String parallelCorrespondingAuthor) {
        this.parallelCorrespondingAuthor = parallelCorrespondingAuthor;
    }

    public String getWorkCode() {
        return workCode;
    }

    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }

    public SrmAchThesis getSrmAchThesis() {
        return srmAchThesis;
    }

    public void setSrmAchThesis(SrmAchThesis srmAchThesis) {
        this.srmAchThesis = srmAchThesis;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getFirstAuthor() {
        return firstAuthor;
    }

    public void setFirstAuthor(String firstAuthor) {
        this.firstAuthor = firstAuthor;
    }

    public String getCorrespondingAuthor() {
        return correspondingAuthor;
    }

    public void setCorrespondingAuthor(String correspondingAuthor) {
        this.correspondingAuthor = correspondingAuthor;
    }
}
