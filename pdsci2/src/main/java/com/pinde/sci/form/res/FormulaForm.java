package com.pinde.sci.form.res;

import com.pinde.sci.model.mo.ResCostCfgDetail;
import com.pinde.sci.model.mo.ResCostCfgMain;

import java.io.Serializable;
import java.util.HashMap;

public class FormulaForm  implements Serializable {

    private static final long serialVersionUID = 3458349131622340251L;

    ResCostCfgMain mainFrom;

    HashMap<String, ResCostCfgDetail> detailMap;

    public ResCostCfgMain getMainFrom() {
        return mainFrom;
    }

    public void setMainFrom(ResCostCfgMain mainFrom) {
        this.mainFrom = mainFrom;
    }

    public HashMap<String, ResCostCfgDetail> getDetailMap() {
        return detailMap;
    }

    public void setDetailMap(HashMap<String, ResCostCfgDetail> detailMap) {
        this.detailMap = detailMap;
    }
}
