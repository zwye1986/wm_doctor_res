package com.pinde.sci.form.srm;

import com.pinde.sci.model.mo.SrmFundProcess;
import com.pinde.sci.model.mo.SrmProjFundDetail;
import com.pinde.sci.model.mo.SrmProjFundInfo;

import java.util.List;

/**
 * Created by www.0001.Ga on 2017-06-12.
 */
public class FundDetailExtForm  {
    private SrmProjFundInfo fundInfo;
    private SrmFundProcess process;
    private List<SrmProjFundDetail> fundDetailList;

    public SrmProjFundInfo getFundInfo() {
        return fundInfo;
    }

    public void setFundInfo(SrmProjFundInfo fundInfo) {
        this.fundInfo = fundInfo;
    }

    public SrmFundProcess getProcess() {
        return process;
    }

    public void setProcess(SrmFundProcess process) {
        this.process = process;
    }

    public List<SrmProjFundDetail> getFundDetailList() {
        return fundDetailList;
    }

    public void setFundDetailList(List<SrmProjFundDetail> fundDetailList) {
        this.fundDetailList = fundDetailList;
    }
}
