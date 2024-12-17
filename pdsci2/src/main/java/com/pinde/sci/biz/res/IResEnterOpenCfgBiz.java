package com.pinde.sci.biz.res;


import com.pinde.core.model.ResEnterOpenCfg;
import com.pinde.sci.model.mo.SchArrangeTime;

import java.util.List;

public interface IResEnterOpenCfgBiz {
    /**
     * 查询报名时间配置信息
     * @param orgFlow
     * @return
     */
    ResEnterOpenCfg readResEnterOpenCfg(String orgFlow);

    /**
     * 保存报名时间配置信息
     * @param enterOpenCfg
     * @return
     */
    int saveEnterOpenCfg(ResEnterOpenCfg enterOpenCfg);

    List<SchArrangeTime> getArrangeTimes(String orgFlow);

    SchArrangeTime getArrangeTime(String recordFlow);

    SchArrangeTime getArrangeTimeByOrgYear(String sessionNumber, String orgFlow);

    int saveSchArrangeTime(SchArrangeTime time);
}

