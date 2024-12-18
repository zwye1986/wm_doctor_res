package com.pinde.sci.biz.osca.impl;

import com.pinde.core.model.OscaSkillsAssessment;
import com.pinde.sci.biz.osca.IOscaCityBiz;
import com.pinde.sci.dao.osca.OscaSkillsAssessmentExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class OscaCityBizImpl implements IOscaCityBiz {
    @Autowired
    private OscaSkillsAssessmentExtMapper skillsAssessmentExtMapper;


    @Override
    public List<OscaSkillsAssessment> searchSkillsAssessment(Map<String, Object> paramMap) {
        return skillsAssessmentExtMapper.searchCitySkillsAssessment(paramMap);
    }
}
