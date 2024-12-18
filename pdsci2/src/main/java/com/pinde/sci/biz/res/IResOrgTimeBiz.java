package com.pinde.sci.biz.res;


import com.pinde.core.model.ResOrgTime;
import com.pinde.sci.form.res.ResOrgTimeForm;

import java.util.List;

public interface IResOrgTimeBiz {


    List<ResOrgTime> readOrgTime(String orgFlow);

    int saveOrgTime(ResOrgTime time);

    ResOrgTime readOrgTimeByFlow(String recordFlow);

    ResOrgTime readOrgOneTime(String orgFlow);

    int saveOrgTimes(ResOrgTimeForm bean);
}

