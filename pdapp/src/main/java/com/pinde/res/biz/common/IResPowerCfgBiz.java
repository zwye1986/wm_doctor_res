package com.pinde.res.biz.common;

import com.pinde.core.model.ResPowerCfg;

import java.util.Map;

/**
 * Created by pdkj on 2017/5/16.
 */
public interface IResPowerCfgBiz {
    //获取产品配置
    String getResPowerCfg(Map<String, String> paramMap);
    ResPowerCfg read(String cfgCode);
}
