package com.pinde.sci.form.srm;

import com.pinde.sci.model.mo.PubProj;

/**
 * Created by www.0001.Ga on 2017-03-01.
 */
public class WxeysrmProjExportForm extends PubProj {
    private String projFund;
    private String startAndEndTime;
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStartAndEndTime() {
        return startAndEndTime;
    }

    public void setStartAndEndTime(String startAndEndTime) {
        this.startAndEndTime = startAndEndTime;
    }

    public String getProjFund() {
        return projFund;
    }

    public void setProjFund(String projFund) {
        this.projFund = projFund;
    }
}
