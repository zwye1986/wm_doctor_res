package com.pinde.sci.biz.test.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.test.ITestResultBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.TestResultMapper;
import com.pinde.sci.model.mo.TestResult;
import com.pinde.sci.model.mo.TestResultExample;
import com.pinde.sci.model.mo.TestResultExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
//@Transactional(rollbackFor = Exception.class)
public class TestResultBizImpl implements ITestResultBiz{
    
	@Autowired
	private TestResultMapper testResultMapper;
	/**
	 * 编辑考试成绩
	 * @param result
	 * @return
	 */
	@Override
	public int edit(TestResult result) {
		if(StringUtil.isNotBlank(result.getResultFlow())){
			GeneralMethod.setRecordInfo(result, false);
			return testResultMapper.updateByPrimaryKeySelective(result);
		}else{
			result.setResultFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(result, true);
			return testResultMapper.insertSelective(result);
			}
	}


	@Override
	public List<TestResult> searchResult(String userFlow, TestResult result) {
		TestResultExample example = new TestResultExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andUserFlowEqualTo(userFlow);
		if(StringUtil.isNotBlank(result.getTestTypeId())){
			criteria.andTestTypeIdEqualTo(result.getTestTypeId());
		}
		if(StringUtil.isNotBlank(result.getTestTypeName())){
			criteria.andTestTypeNameLike(result.getTestTypeName());
		}
		if(StringUtil.isNotBlank(result.getPaperName())){
			criteria.andPaperNameLike("%"+result.getPaperName()+"%");
		}
		if(StringUtil.isNotBlank(result.getPassFlag())){
			criteria.andPassFlagEqualTo(result.getPassFlag());
		}
		return testResultMapper.selectByExample(example);
	}

}
