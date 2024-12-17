package com.pinde.sci.biz.res;

import com.pinde.core.model.ResBaseeval;
import com.pinde.core.model.ResBaseevalDetail;
import com.pinde.core.model.ResBaseevalForm;
import com.pinde.core.model.ResBaseevalFormCfg;

import java.util.List;

public interface IResEvalBiz {
    //根据主键查询评价表单
    ResBaseevalForm readForm(String recordFlow);

    //根据条件查询评价表单
    List<ResBaseevalForm> search(ResBaseevalForm resBaseevalForm);

    //根据条件查询评价表单配置
    List<ResBaseevalFormCfg> searchCfg(ResBaseevalFormCfg resBaseevalFormCfg);

    //编辑评价表单配置
    int editEvalCfg(ResBaseevalFormCfg baseevalFormCfg);

    //根据条件查询评价记录
    List<ResBaseeval> searchBaseeval(ResBaseeval baseeval);

    //编辑评价记录
    int editBaseeval(ResBaseeval baseeval);

    //编辑评价记录明细
    int editBaseevalDetail(ResBaseevalDetail baseevalDetail);

    //编辑评价记录+评价记录明细
    int editBaseevalTings(String jsonData,String role);

    //根据条件查询评价记录明细
    List<ResBaseevalDetail> searchBaseevalDetail(ResBaseevalDetail baseevalDetail);

    //批量编辑评价表单
    int editEvalCfgs(ResBaseevalFormCfg baseevalFormCfg,String jsonData);
}
