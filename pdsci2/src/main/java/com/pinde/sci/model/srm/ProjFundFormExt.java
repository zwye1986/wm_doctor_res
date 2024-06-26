package com.pinde.sci.model.srm;

import com.pinde.sci.model.mo.PubProj;
import com.pinde.sci.model.mo.SrmProjFundForm;
import com.pinde.sci.model.mo.SrmProjFundInfo;

import java.util.List;

/**
 * Created by www.0001.Ga on 2016.9.7.
 */
public class ProjFundFormExt extends SrmProjFundForm{

    private SrmProjFundInfo projFundInfo;

    private PubProj proj;

    private List<String> operStatusIds;

    public SrmProjFundInfo getProjFundInfo() {
        return projFundInfo;
    }

    public void setProjFundInfo(SrmProjFundInfo projFundInfo) {
        this.projFundInfo = projFundInfo;
    }

    public PubProj getProj() {
        return proj;
    }

    public void setProj(PubProj proj) {
        this.proj = proj;
    }

    public List<String> getOperStatusIds() {
        return operStatusIds;
    }

    public void setOperStatusIds(List<String> operStatusIds) {
        this.operStatusIds = operStatusIds;
    }
}
