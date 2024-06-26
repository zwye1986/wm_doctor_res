package com.pinde.sci.biz.study.impl;

import com.pinde.sci.biz.study.IStudySubjectDetailBiz;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.StudySubjectDetailMapper;
import com.pinde.sci.dao.base.StudySubjectMapper;
import com.pinde.sci.model.mo.StudySubjectDetail;
import com.pinde.sci.model.mo.StudySubjectDetailExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class StudySubjectDetailBizImpl implements IStudySubjectDetailBiz {
    @Autowired
    private StudySubjectMapper subjectMapper;
    @Autowired
    private StudySubjectDetailMapper subjectDetailMapper;

    @Override
    public StudySubjectDetail findBySubjectFlow(String subjectFlow) throws Exception {

//        return subjectDetailMapper.selectBySubjectFlow(subjectFlow);
        return null;
    }

    @Override
    public List<StudySubjectDetail> findByDoctorFlow(String doctorFlow) {
        StudySubjectDetailExample example = new StudySubjectDetailExample();
        StudySubjectDetailExample.Criteria criteria = example.createCriteria().andDoctorFlowEqualTo(doctorFlow)
                .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        return subjectDetailMapper.selectByExample(example);
    }

    @Override
    public int selectCountByAuditStatusId(String subjectFlow) throws Exception {
        StudySubjectDetailExample subjectDetailExample = new StudySubjectDetailExample();
        StudySubjectDetailExample.Criteria criteria = subjectDetailExample.createCriteria().andSubjectFlowEqualTo(subjectFlow)
                .andAuditStatusIdEqualTo("Passed").andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        return subjectDetailMapper.countByExample(subjectDetailExample);
    }
}
