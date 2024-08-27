package com.pinde.sci.biz.res;

import com.pinde.sci.model.mo.ResInprocessInfo;
import com.pinde.sci.model.mo.ResInprocessInfoMember;

import java.util.List;
import java.util.Map;

public interface IResInprocessInfoBiz {

    ResInprocessInfo readByDeptFlowAndOrgFlow(String deptFlow, String orgFlow);

    List<ResInprocessInfoMember> readMembersByRecordFlow(String recordFlow);

    int deleteMemberNotInFlow(String recordFlow, List<String> memberFlows);

    int saveMember(ResInprocessInfoMember m);

    int saveInfo(ResInprocessInfo info);

    List<ResInprocessInfo> readInfoList(String orgFlow);
    List<ResInprocessInfo> readInfoList4Global(Map<String,Object> map);
}
