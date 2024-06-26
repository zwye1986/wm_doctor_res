package com.pinde.sci.model.srm;

import com.pinde.sci.model.mo.PubProj;
import com.pinde.sci.model.mo.SrmProjFundInfo;

import java.math.BigDecimal;

/**
 * Created by MyPC on 2016/12/22.
 */
public class RegisProjFundExt extends PubProj {
    private static final long serialVersionUID = 6610469354483743473L;

    private SrmProjFundInfo projFundInfo;

    private BigDecimal fundDetailNum;

    public BigDecimal getFundDetailNum() {
        return fundDetailNum;
    }

    public void setFundDetailNum(BigDecimal fundDetailNum) {
        this.fundDetailNum = fundDetailNum;
    }

    public SrmProjFundInfo getProjFundInfo() {
        return projFundInfo;
    }

    public void setProjFundInfo(SrmProjFundInfo projFundInfo) {
        this.projFundInfo = projFundInfo;
    }
    
}
