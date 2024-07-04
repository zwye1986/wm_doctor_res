package com.pinde.res.biz.stdp.impl;


import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.stdp.IExamResultsBiz;
import com.pinde.sci.dao.base.ExamResultsMapper;
import com.pinde.sci.model.mo.ExamResults;
import com.pinde.sci.model.mo.ExamResultsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class ExamResultsBizImpl implements IExamResultsBiz {
	@Autowired
	private ExamResultsMapper resultsMapper;

	@Override
	public ExamResults getByProcessFlowAndScore(String processFlow, BigDecimal theoryScore) {
		ExamResultsExample examResultsExample=new ExamResultsExample();
		examResultsExample.createCriteria().andRecordStatusEqualTo("Y").andProcessFlowEqualTo(processFlow)
				.andTheoryScoreEqualTo(theoryScore);
		examResultsExample.setOrderByClause("SUBMIT_TIME DESC");
		List<ExamResults> list=resultsMapper.selectByExample(examResultsExample);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<ExamResults> getByProcessFlow(String processFlow) {
		if(StringUtil.isNotBlank(processFlow))
		{
			ExamResultsExample examResultsExample=new ExamResultsExample();
			examResultsExample.createCriteria().andRecordStatusEqualTo("Y").andProcessFlowEqualTo(processFlow);
			examResultsExample.setOrderByClause("SUBMIT_TIME DESC");
			return resultsMapper.selectByExample(examResultsExample);
		}
		return null;
	}

	@Override
	public ExamResults getByFlow(String resultId) {
		return resultsMapper.selectByPrimaryKey(resultId);
	}
}
