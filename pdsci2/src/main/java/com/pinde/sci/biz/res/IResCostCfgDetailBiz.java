package com.pinde.sci.biz.res;


import com.pinde.sci.model.mo.ResCostCfgDetail;

import java.util.List;

public interface IResCostCfgDetailBiz {

    List<ResCostCfgDetail> search(ResCostCfgDetail cfgDetail);

    int saveDetail(ResCostCfgDetail detail);
}
