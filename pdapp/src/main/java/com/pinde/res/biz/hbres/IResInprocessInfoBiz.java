package com.pinde.res.biz.hbres;

import java.util.List;

public interface IResInprocessInfoBiz {

    ResInprocessInfo readByDeptFlowAndOrgFlow(String deptFlow, String orgFlow);

    List<ResInprocessInfoMember> readMembersByRecordFlow(String recordFlow);

    int deleteMemberNotInFlow(String recordFlow, List<String> memberFlows);


    List<ResInprocessInfo> readInfoList(String orgFlow);

    ResInprocessInfo readByDeptFlow(String schDeptFlow);
}
