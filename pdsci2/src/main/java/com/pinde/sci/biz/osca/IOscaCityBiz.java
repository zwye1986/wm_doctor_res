package com.pinde.sci.biz.osca;

import com.pinde.sci.model.mo.*;

import java.util.List;
import java.util.Map;

public interface IOscaCityBiz {
    //根据条件查询本市预约信息
    List<OscaSkillsAssessment> searchSkillsAssessment(Map<String,Object> paramMap);
    //根据条件查询本市考核信息


}
