package com.pinde.sci.biz.res;


import com.pinde.sci.model.mo.ResOrgSigninDate;

import java.util.List;

public interface IResOrgDateBiz {


    List<ResOrgSigninDate> readOrgTime(String orgFlow, String startDate, String endDate);

    int saveOrgTime(ResOrgSigninDate time);

    ResOrgSigninDate readOrgTimeByFlow(String recordFlow);

    ResOrgSigninDate readOrgTimeByDate(String orgFlow, String signinDate);
}

