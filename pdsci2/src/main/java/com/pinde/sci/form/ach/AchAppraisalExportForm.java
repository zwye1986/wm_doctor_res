package com.pinde.sci.form.ach;

import com.pinde.sci.model.mo.SrmAchAppraisal;

import java.io.Serializable;

/**
 * 鉴定导出信息
 */
public class AchAppraisalExportForm implements Serializable {
    private static final long serialVersionUID = 553132531234321L;
    private SrmAchAppraisal srmAchAppraisal;
    /*鉴定完成人*/
    private String authorName;

    public SrmAchAppraisal getSrmAchAppraisal() {
        return srmAchAppraisal;
    }

    public void setSrmAchAppraisal(SrmAchAppraisal srmAchAppraisal) {
        this.srmAchAppraisal = srmAchAppraisal;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
