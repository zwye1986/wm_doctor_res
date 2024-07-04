package com.pinde.res.biz.stdp;


import com.pinde.sci.model.mo.ResOrgAddress;
import com.pinde.sci.model.mo.ResOrgTime;

import java.util.List;

public interface IResOrgTimeBiz {


    List<ResOrgTime> readOrgTime(String orgFlow);

    ResOrgTime readOrgTimeByFlow(String recordFlow);

    ResOrgTime readOrgOneTime(String orgFlow);

    List<ResOrgAddress> readOrgAddress(String orgFlow);
}

