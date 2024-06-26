package com.pinde.sci.form.sch;

import java.util.List;

/**
 * Created by pdkj on 2018/6/20.
 */
public class SchRotationOrgGroupForm {
    public String groupFlow;
    public String selTypeId;
    public String isRequired;
    public String schMonth;
    public String oneSchMonth;
    public String twoSchMonth;
    public String threeSchMonth;
    public String isRemove;
    public List<SchRotationOrgDeptForm> orgStandardDeptList;

    public String getGroupFlow() {
        return groupFlow;
    }

    public void setGroupFlow(String groupFlow) {
        this.groupFlow = groupFlow;
    }

    public String getSelTypeId() {
        return selTypeId;
    }

    public void setSelTypeId(String selTypeId) {
        this.selTypeId = selTypeId;
    }

    public String getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public String getSchMonth() {
        return schMonth;
    }

    public void setSchMonth(String schMonth) {
        this.schMonth = schMonth;
    }

    public String getOneSchMonth() {
        return oneSchMonth;
    }

    public void setOneSchMonth(String oneSchMonth) {
        this.oneSchMonth = oneSchMonth;
    }

    public String getTwoSchMonth() {
        return twoSchMonth;
    }

    public void setTwoSchMonth(String twoSchMonth) {
        this.twoSchMonth = twoSchMonth;
    }

    public String getThreeSchMonth() {
        return threeSchMonth;
    }

    public void setThreeSchMonth(String threeSchMonth) {
        this.threeSchMonth = threeSchMonth;
    }

    public String getIsRemove() {
        return isRemove;
    }

    public void setIsRemove(String isRemove) {
        this.isRemove = isRemove;
    }

    public List<SchRotationOrgDeptForm> getOrgStandardDeptList() {
        return orgStandardDeptList;
    }

    public void setOrgStandardDeptList(List<SchRotationOrgDeptForm> orgStandardDeptList) {
        this.orgStandardDeptList = orgStandardDeptList;
    }
}
