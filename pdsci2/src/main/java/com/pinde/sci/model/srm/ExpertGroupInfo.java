package com.pinde.sci.model.srm;

import com.pinde.sci.model.mo.SrmExpertGroup;

import java.util.List;

/**
 * Created by www.0001.Ga on 2016.9.18.
 */
public class ExpertGroupInfo {
    private List<ExpertInfo> expertInfoList;
    private SrmExpertGroup expertGroup;

    public List<ExpertInfo> getExpertInfoList() {
        return expertInfoList;
    }

    public void setExpertInfoList(List<ExpertInfo> expertInfoList) {
        this.expertInfoList = expertInfoList;
    }

    public SrmExpertGroup getExpertGroup() {
        return expertGroup;
    }

    public void setExpertGroup(SrmExpertGroup expertGroup) {
        this.expertGroup = expertGroup;
    }
}
