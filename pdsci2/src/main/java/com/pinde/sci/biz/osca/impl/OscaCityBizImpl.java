package com.pinde.sci.biz.osca.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.osca.IOscaCityBiz;
import com.pinde.sci.biz.osca.IOscaProvincialBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.osca.OscaDoctorAssessmentExtMapper;
import com.pinde.sci.dao.osca.OscaSkillsAssessmentExtMapper;
import com.pinde.sci.enums.osca.AuditStatusEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class OscaCityBizImpl implements IOscaCityBiz {
    @Autowired
    private OscaSkillsAssessmentExtMapper skillsAssessmentExtMapper;


    @Override
    public List<OscaSkillsAssessment> searchSkillsAssessment(Map<String, Object> paramMap) {
        return skillsAssessmentExtMapper.searchCitySkillsAssessment(paramMap);
    }
}
