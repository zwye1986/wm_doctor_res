package com.pinde.sci.biz.gyxjgl.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gyxjgl.*;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.gyxjgl.GyXjEduTermExtMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.gyxjgl.XjEduScheduleClassExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service
@Transactional(rollbackFor = Exception.class)
public class GyXjEduTermBizImpl implements IGyXjEduTermBiz {

    @Autowired
    private EduTermMapper eduTermMapper;
    @Autowired
    private GyXjEduTermExtMapper eduTermExtMapper;

    @Override
    public EduTerm getEduTermByFlow(String flow) {
        return  eduTermMapper.selectByPrimaryKey(flow);
    }

    @Override
    public int saveTerm(EduTerm term) {
        if (StringUtil.isNotBlank(term.getRecordFlow())) {
            GeneralMethod.setRecordInfo(term, false);
            return eduTermMapper.updateByPrimaryKeySelective(term);
        } else {
            term.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(term, true);
            return eduTermMapper.insert(term);
        }
    }

    @Override
    public List<EduTerm> seachlistByTerm(EduTerm term) {

         EduTermExample eduTermExample=new EduTermExample();
        EduTermExample.Criteria criteria=eduTermExample.createCriteria();
        term.setRecordStatus(GlobalConstant.FLAG_Y);
        eduTermExample.setOrderByClause("term_Start_Time DESC");
        addCriteria(term,criteria);
        return eduTermMapper.selectByExample(eduTermExample);
    }

    @Override
    public List<XjEduScheduleClassExt> seachClassByMap(Map<String, Object> param) {
        return eduTermExtMapper.seachClassByMap(param);
    }

    @Override
    public List<Map<String,String>> searchTeachersClassSchedule(Map<String,String> paramMap){
        return eduTermExtMapper.searchTeachersClassSchedule(paramMap);
    }

    @Override
    public List<Map<String, String>> getFirstClassByFlow(String recordFlow) {
        return eduTermExtMapper.getFirstClassByFlow(recordFlow);
    }

    @Override
    public List<Map<String, String>> getFirstClassOfGzByFlow(String recordFlow) {
        return eduTermExtMapper.getFirstClassOfGzByFlow(recordFlow);
    }

    @Override
    public List<EduTerm> searchBySessionAndGrade(String sessionNumber, String gradeTermId, String classId) {
        EduTermExample example = new EduTermExample();
        EduTermExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(sessionNumber)){
            criteria.andSessionNumberEqualTo(sessionNumber);
        }
        if(StringUtil.isNotBlank(gradeTermId)){
            criteria.andGradeTermIdEqualTo(gradeTermId);
        }
        if(StringUtil.isNotBlank(classId)){
            criteria.andClassIdEqualTo(classId);
        }
        example.setOrderByClause("CLASS_ID ASC");
        return eduTermMapper.selectByExample(example);
    }

    private void addCriteria(EduTerm term , EduTermExample.Criteria criteria) {
        if(StringUtil.isNotBlank(term.getRecordFlow())){
            criteria.andRecordFlowEqualTo(term.getRecordFlow());
        }
        if(StringUtil.isNotBlank(term.getClassId())){
            criteria.andClassIdEqualTo(term.getClassId());
        }
        if(StringUtil.isNotBlank(term.getClassName())){
            criteria.andClassNameLike(term.getClassName());
        }
        if(StringUtil.isNotBlank(term.getSessionNumber())){
            criteria.andSessionNumberEqualTo(term.getSessionNumber());
        }
        if(StringUtil.isNotBlank(term.getGradationId())){
            criteria.andGradationIdEqualTo(term.getGradationId());
        }
        if(StringUtil.isNotBlank(term.getGradationName())){
            criteria.andGradationNameLike(term.getGradationName());
        }
        if(StringUtil.isNotBlank(term.getRecordStatus())){
            criteria.andRecordStatusEqualTo(term.getRecordStatus());
        }
        if(StringUtil.isNotBlank(term.getGradeTermId())){
            criteria.andGradeTermIdEqualTo(term.getGradeTermId());
        }
        if(StringUtil.isNotBlank(term.getGradeTermName())){
            criteria.andGradeTermNameLike(term.getGradeTermName());
        }
    }


}

