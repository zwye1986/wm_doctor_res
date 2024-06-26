package com.pinde.sci.model.srm;

import com.pinde.sci.model.mo.SrmFundSchemeDetail;
import com.pinde.sci.model.mo.SrmProjFundDetail;

/**
 * Created by www.0001.Ga on 2017-06-16.
 */
public class FundDetailAndSchemeExt extends SrmProjFundDetail {
    private SrmFundSchemeDetail item;

    public SrmFundSchemeDetail getItem() {
        return item;
    }

    public void setItem(SrmFundSchemeDetail item) {
        this.item = item;
    }
}
