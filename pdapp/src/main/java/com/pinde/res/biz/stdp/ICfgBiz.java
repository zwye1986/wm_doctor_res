package com.pinde.res.biz.stdp;

import com.pinde.core.model.SysCfg;

import java.util.List;

public interface ICfgBiz {

    String getCfgCode(String code);

    List<SysCfg> search(SysCfg cfg);

    SysCfg read(String cfgCode);
}
