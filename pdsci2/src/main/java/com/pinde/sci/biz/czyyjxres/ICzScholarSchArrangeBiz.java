package com.pinde.sci.biz.czyyjxres;


import com.pinde.sci.model.mo.ScholarSchArrange;

import java.util.List;
import java.util.Map;

public interface ICzScholarSchArrangeBiz {
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

    int checkScholarDate(Map<String, Object> paramMap);

    int getArrangedNum(Map<String, Object> paramMap);



}
