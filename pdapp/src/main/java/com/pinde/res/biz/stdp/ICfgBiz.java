package com.pinde.res.biz.stdp;

import com.pinde.sci.model.mo.SysCfg;

import java.util.List;

public interface ICfgBiz {

    String getCfgCode(String code);

    List<SysCfg> search(SysCfg cfg);

    SysCfg read(String cfgCode);
}
