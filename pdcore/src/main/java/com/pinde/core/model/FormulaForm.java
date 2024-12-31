package com.pinde.core.model;

import java.util.HashMap;

public class FormulaForm implements java.io.Serializable {

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
