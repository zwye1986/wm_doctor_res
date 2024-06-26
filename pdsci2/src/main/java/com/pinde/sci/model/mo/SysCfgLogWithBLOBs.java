package com.pinde.sci.model.mo;

public class SysCfgLogWithBLOBs extends SysCfgLog {
    private String cfgBigValue;

    private String cfgOldBigValue;

    public String getCfgBigValue() {
        return cfgBigValue;
    }

    public void setCfgBigValue(String cfgBigValue) {
        this.cfgBigValue = cfgBigValue;
    }

    public String getCfgOldBigValue() {
        return cfgOldBigValue;
    }

    public void setCfgOldBigValue(String cfgOldBigValue) {
        this.cfgOldBigValue = cfgOldBigValue;
    }
}