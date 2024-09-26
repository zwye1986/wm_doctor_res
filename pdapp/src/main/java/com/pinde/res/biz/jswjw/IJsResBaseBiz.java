package com.pinde.res.biz.jswjw;

import com.pinde.sci.model.mo.ResPassScoreCfg;

public interface IJsResBaseBiz {

    /**
     * 加载分数配置
     * @param cfgYear
     * @return
     */
    ResPassScoreCfg readResPassScoreCfg(ResPassScoreCfg resPassScoreCfg);

}
