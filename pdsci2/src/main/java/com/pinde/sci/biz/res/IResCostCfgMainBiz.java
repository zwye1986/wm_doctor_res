package com.pinde.sci.biz.res;

import com.pinde.sci.model.mo.ResCostCfgMain;

import java.util.List;

public interface IResCostCfgMainBiz {

    List<ResCostCfgMain> search(ResCostCfgMain costCfgMain);

    ResCostCfgMain readCostCfgMain(String mainFlow);

    int saveMain(ResCostCfgMain main);

    int updateMain(ResCostCfgMain main);
}
