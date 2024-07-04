package com.pinde.res.biz.jswjw.impl;

import com.pinde.app.common.GlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.jswjw.ISchExamCfgBiz;
import com.pinde.sci.dao.base.ResDoctorGraduationExamMapper;
import com.pinde.sci.dao.base.SchExamArrangementExtMapper;
import com.pinde.sci.dao.base.SchExamArrangementMapper;
import com.pinde.sci.dao.base.SchExamDoctorArrangementMapper;
import com.pinde.sci.model.mo.ResDoctorGraduationExam;
import com.pinde.sci.model.mo.SchExamArrangement;
import com.pinde.sci.model.mo.SchExamArrangementExample;
import com.pinde.sci.model.mo.SchExamDoctorArrangement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class SchExamCfgBizImpl implements ISchExamCfgBiz {
	@Autowired
	private SchExamArrangementMapper schExamArrangementMapper;
	@Autowired
	private SchExamArrangementExtMapper schExamArrangementExtMapper;
    @Autowired
    private SchExamDoctorArrangementMapper doctorArrangementMapper;
    @Autowired
    private ResDoctorGraduationExamMapper graduationExamMapper;

    @Override
    public List<SchExamArrangement> searchList(SchExamArrangement seam) {
        SchExamArrangementExample example=new SchExamArrangementExample();
        SchExamArrangementExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(seam.getArrangeFlow()))
        {
            criteria.andArrangeFlowEqualTo(seam.getArrangeFlow());
        }
        if(StringUtil.isNotBlank(seam.getOrgFlow()))
        {
            criteria.andOrgFlowEqualTo(seam.getOrgFlow());
        }
        if(StringUtil.isNotBlank(seam.getTrainingSpeId()))
        {
            criteria.andTrainingSpeIdEqualTo(seam.getTrainingSpeId());
        }
        if(StringUtil.isNotBlank(seam.getTrainingTypeId()))
        {
            criteria.andTrainingTypeIdEqualTo(seam.getTrainingTypeId());
        }
        if(StringUtil.isNotBlank(seam.getSessionNumber()))
        {
            criteria.andSessionNumberEqualTo(seam.getSessionNumber());
        }
        if(StringUtil.isNotBlank(seam.getAssessmentYear()))
        {
            criteria.andAssessmentYearEqualTo(seam.getAssessmentYear());
        }
        example.setOrderByClause("ASSESSMENT_YEAR,TRAINING_TYPE_ID");
        return schExamArrangementMapper.selectByExample(example);
    }
    @Override
	public SchExamArrangement readByFlow(String arrangeFlow) {
		return schExamArrangementMapper.selectByPrimaryKey(arrangeFlow);
	}


    @Override
	public List<Map<String,String>> searchExamLogByItems(Map<String, String> paramMap) {
		return schExamArrangementExtMapper.searchExamLogByItems(paramMap);
	}


    @Override
    public int findDocExamCount(String userFlow, String arrangeFlow) {
        return schExamArrangementExtMapper.findDocExamCount(userFlow,arrangeFlow);
    }

    @Override
    public int save(SchExamDoctorArrangement result) {
        result.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        result.setCreateTime( DateUtil.getCurrDateTime());
        result.setModifyTime( DateUtil.getCurrDateTime());
        return doctorArrangementMapper.insertSelective(result);
    }

    @Override
    public Map<String, Object> getSuiJiConfig(SchExamArrangement ment,String userFlow) {
        return schExamArrangementExtMapper.getSuiJiConfig(ment, userFlow);
    }

    @Override
    public Map<String, Object> getGuDingConfig(SchExamArrangement ment) {
        return schExamArrangementExtMapper.getGuDingConfig(ment);
    }

    @Override
    public int saveGraduationExam(ResDoctorGraduationExam result) {
        result.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        result.setCreateTime( DateUtil.getCurrDateTime());
        result.setModifyTime( DateUtil.getCurrDateTime());
        return graduationExamMapper.insertSelective(result);
    }
}
