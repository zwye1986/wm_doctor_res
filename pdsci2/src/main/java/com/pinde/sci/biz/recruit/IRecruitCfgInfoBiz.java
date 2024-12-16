package com.pinde.sci.biz.recruit;

import com.pinde.core.model.RecruitCfgInfo;

import java.util.List;

public interface IRecruitCfgInfoBiz {
    RecruitCfgInfo searchCfgInfoByYear(String year,String orgFlow);

    Integer addRecCfgInfo(RecruitCfgInfo recruitCfgInfo);

    Integer updateRecCfgInfo(RecruitCfgInfo recruitCfgInfo);

    List<String> searchAllRecruitYear(String orgFlow);

    List<RecruitCfgInfo> cfgs(String orgFlow);

    RecruitCfgInfo readByFlow(String cfgFlow);

    Integer RecruitCfgInfo(String cfgFlow,String orgFlow);

    RecruitCfgInfo getCurrYearCfgInfo(String orgFlow);
}
