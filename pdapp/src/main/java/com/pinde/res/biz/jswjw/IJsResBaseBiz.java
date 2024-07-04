package com.pinde.res.biz.jswjw;

import com.pinde.sci.model.mo.ResOrgSpe;
import com.pinde.sci.model.mo.ResPassScoreCfg;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IJsResBaseBiz {

    /**
     * 加载分数配置
     * @param cfgYear
     * @return
     */
    ResPassScoreCfg readResPassScoreCfg(ResPassScoreCfg resPassScoreCfg);

}
