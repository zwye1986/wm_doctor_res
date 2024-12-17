package com.pinde.res.biz.stdp;

import com.pinde.core.model.InxInfo;
import com.pinde.core.model.ResReadInfo;

public interface IInxInfoBiz {

    /**
     * 根据infoFlow查询
     *
     * @param infoFlow
     * @return
     */
    InxInfo getByInfoFlow(String infoFlow);

    ResReadInfo getReadInfoByFlow(String infoFlow, String userFlow);

    int saveReadInfo(String userFlow, ResReadInfo resReadInfo);
}
