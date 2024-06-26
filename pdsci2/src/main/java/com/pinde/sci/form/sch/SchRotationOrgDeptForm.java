package com.pinde.sci.form.sch;

import java.util.List;

/**
 * Created by pdkj on 2018/6/20.
 */
public class SchRotationOrgDeptForm {
    public String recordFlow;
    public String recordStatus;
    public List<SchRotationOrgSchDeptForm> orgSchDeptList;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public List<SchRotationOrgSchDeptForm> getOrgSchDeptList() {
        return orgSchDeptList;
    }

    public void setOrgSchDeptList(List<SchRotationOrgSchDeptForm> orgSchDeptList) {
        this.orgSchDeptList = orgSchDeptList;
    }
}
