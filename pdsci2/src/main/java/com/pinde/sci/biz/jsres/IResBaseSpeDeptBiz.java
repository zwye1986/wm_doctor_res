package com.pinde.sci.biz.jsres;

import com.pinde.core.model.ResBaseSpeDept;

public interface IResBaseSpeDeptBiz {

    //查询科室信息（当前基地）
    ResBaseSpeDept readBaseByDeptFlow(String deptFlow);

    //查询专业信息（当前基地）
    ResBaseSpeDept readBaseBySpeFlow(String speFlow);

    int saveInfo(ResBaseSpeDept resBaseSpeDept);
}
