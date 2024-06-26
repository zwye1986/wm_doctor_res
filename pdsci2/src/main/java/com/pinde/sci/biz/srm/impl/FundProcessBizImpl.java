package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.IFundProcessBiz;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.SrmFundProcessMapper;
import com.pinde.sci.model.mo.SrmFundProcess;
import com.pinde.sci.model.mo.SrmFundProcessExample;
import com.pinde.sci.model.mo.SrmFundProcessExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class FundProcessBizImpl implements IFundProcessBiz{
	@Autowired
	private SrmFundProcessMapper fundProcessMapper;

	@Override
	public void saveFundProcess(SrmFundProcess fundProcess) {
		fundProcessMapper.insert(fundProcess);
	}

	@Override
	public void updateFundProcess(SrmFundProcess fundProcess) {
		fundProcessMapper.updateByPrimaryKeySelective(fundProcess);
	}

	@Override
	public List<SrmFundProcess> searchFundProcess(String detailFlow,String statusId) {
		SrmFundProcessExample example = new SrmFundProcessExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andFundDetailFlowEqualTo(detailFlow).andOperStatusIdEqualTo(statusId);
		
		List<SrmFundProcess> processList = fundProcessMapper.selectByExample(example);
		return processList;
	}

	@Override
	public SrmFundProcess readFundProcess(SrmFundProcess process) {
		
	    List<SrmFundProcess> processList = searchFundProcesses(process);
		if(null != processList && processList.size() > 0) {
			return processList.get(0);
		}
		return null;
	}

	@Override
	public List<SrmFundProcess> searchFundProcesses(SrmFundProcess process) {
		SrmFundProcessExample example = new SrmFundProcessExample();
	    Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
	    if(StringUtil.isNotBlank(process.getFundFlow())){
	    	criteria.andFundFlowEqualTo(process.getFundFlow());
	    }
	    if(StringUtil.isNotBlank(process.getOperStatusId())){
	    	criteria.andOperStatusIdEqualTo(process.getOperStatusId());
	    }
	    if(StringUtil.isNotBlank(process.getFundDetailFlow())){
	    	criteria.andFundDetailFlowEqualTo(process.getFundDetailFlow());
	    }
		example.setOrderByClause("CREATE_TIME");
	    List<SrmFundProcess> processList = fundProcessMapper.selectByExample(example);
	    return processList;
	}

}
