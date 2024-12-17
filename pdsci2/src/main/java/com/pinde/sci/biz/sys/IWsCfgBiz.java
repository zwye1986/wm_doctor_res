package com.pinde.sci.biz.sys;

import com.pinde.core.model.SysWsConfig;

import java.util.List;

public interface IWsCfgBiz {

    List<SysWsConfig> searchList(String wsId);

    SysWsConfig searchByKey(String wsId);

    int save(SysWsConfig config);

    int updateDefault(SysWsConfig config);

    SysWsConfig getDefaultCfg();
}
