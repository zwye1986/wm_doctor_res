package com.pinde.sci.biz.sys;

import com.pinde.sci.model.mo.SysCfg;
import com.pinde.sci.model.mo.SysWsConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IWsCfgBiz {

    List<SysWsConfig> searchList(String wsId);

    SysWsConfig searchByKey(String wsId);

    int save(SysWsConfig config);

    int updateDefault(SysWsConfig config);

    SysWsConfig getDefaultCfg();
}
