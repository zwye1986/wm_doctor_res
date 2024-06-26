package com.pinde.sci.biz.zseyjxres;


import com.pinde.sci.model.mo.ScholarSchArrange;

import java.util.List;

public interface IZseyScholarSchArrangeBiz {
    /**
     * 保存
     */
    int save(ScholarSchArrange arrange);

    /**
     * 根据主键查询
     */
    ScholarSchArrange selectByArrangeFlow(String arrangeFlow);

    /**
     * 查询
     */
    List<ScholarSchArrange> searchArrange(ScholarSchArrange arrange);

    /**
     * 删除
     */
    int delbyFlows(List<String> arrangeFlowList);

}
