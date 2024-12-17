package com.pinde.res.biz.jswjw.impl;


import com.pinde.core.model.ResPassScoreCfg;
import com.pinde.core.model.ResPassScoreCfgExample;
import com.pinde.core.model.ResScore;
import com.pinde.core.model.ResScoreExample;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.jswjw.IResScoreBiz;
import com.pinde.sci.dao.base.ResPassScoreCfgMapper;
import com.pinde.sci.dao.base.ResScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class ResScoreBizImpl implements IResScoreBiz {
	@Autowired
	private ResPassScoreCfgMapper resPassScoreCfgMapper;
	@Autowired
	private ResScoreMapper scoreMapper;
	@Override
	public ResScore searchByScoreFlow(String scoreFlow) {
		return scoreMapper.selectByPrimaryKey(scoreFlow);
	}

	@Override
	public List<ResScore> searchByScoreList(String doctorFlow) {
		ResScoreExample example=new ResScoreExample();
        ResScoreExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(doctorFlow)) {
			criteria.andDoctorFlowEqualTo(doctorFlow);
		}
		return scoreMapper.selectByExample(example);
	}
	@Override
	public List<ResScore> selectByExampleWithBLOBs(String doctorFlow) {
		ResScoreExample example=new ResScoreExample();
        ResScoreExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(doctorFlow)) {
			criteria.andDoctorFlowEqualTo(doctorFlow);
		}
		example.setOrderByClause("score_Phase_Id");
		return scoreMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public ResScore getScoreByProcess(String processFlow){
		if(!StringUtil.isNotBlank(processFlow)){
			return null;
		}
		
		ResScoreExample example=new ResScoreExample();
        ResScoreExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
		.andProcessFlowEqualTo(processFlow);
		
		List<ResScore> scores = scoreMapper.selectByExample(example);
		
		if(scores==null || scores.isEmpty()){
			return null;
		}
		
		return scores.get(0);
	}

	@Override
	public List<ResPassScoreCfg> getScoreCfgList() {

		ResPassScoreCfgExample example = new ResPassScoreCfgExample();
		return resPassScoreCfgMapper.selectByExample(example);
	}
}
