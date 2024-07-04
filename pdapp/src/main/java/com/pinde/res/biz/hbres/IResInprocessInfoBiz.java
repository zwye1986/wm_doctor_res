package com.pinde.res.biz.hbres;

import com.pinde.sci.model.mo.ResInprocessInfo;
import com.pinde.sci.model.mo.ResInprocessInfoMember;

import java.util.List;

public interface IResInprocessInfoBiz {

    ResInprocessInfo readByDeptFlowAndOrgFlow(String deptFlow, String orgFlow);

    List<ResInprocessInfoMember> readMembersByRecordFlow(String recordFlow);

    int deleteMemberNotInFlow(String recordFlow, List<String> memberFlows);


    List<ResInprocessInfo> readInfoList(String orgFlow);

    ResInprocessInfo readByDeptFlow(String schDeptFlow);
}
