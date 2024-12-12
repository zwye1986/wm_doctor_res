package com.pinde.sci.biz.res.impl;


import com.pinde.core.common.sci.dao.TestPaperMapper;
import com.pinde.core.model.TestPaper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.ResPaperBiz;
import com.pinde.sci.dao.base.ResPaperMapper;
import com.pinde.sci.model.mo.ResPaper;
import com.pinde.sci.model.mo.ResPaperExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class ResPaperBizImpl implements ResPaperBiz{
	@Autowired
	private ResPaperMapper paperMapper;
	@Autowired
	private TestPaperMapper testPaperMapper;

	@Override
	public ResPaper getPaperByRotationAndDept(String speId,String standardDeptId) {
		if(!StringUtil.isNotBlank(speId)){
			return null;
		}
		if(!StringUtil.isNotBlank(standardDeptId)){
			return null;
		}
		
		ResPaperExample example = new ResPaperExample();

        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
		.andSpeIdEqualTo(speId)
		.andDeptFlowEqualTo(standardDeptId);
		
		example.setOrderByClause("CREATE_TIME DESC");
		
		List<ResPaper> papers = paperMapper.selectByExample(example);
		
		if(papers==null || papers.isEmpty()){
			return null;
		}
		
		return papers.get(0);
	}

	@Override
	public ResPaper getPaperByOrgStandardDeptId(String orgName,String standardDeptId) {
		if(!StringUtil.isNotBlank(orgName)){
			return null;
		}
		if(!StringUtil.isNotBlank(standardDeptId)){
			return null;
		}

		ResPaperExample example = new ResPaperExample();

        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
		.andSpeIdEqualTo("0")
		.andOrgNameEqualTo(orgName)
		.andDeptFlowEqualTo(standardDeptId);

		example.setOrderByClause("CREATE_TIME DESC");

		List<ResPaper> papers = paperMapper.selectByExample(example);

		if(papers==null || papers.isEmpty()){
			return null;
		}

		return papers.get(0);
	}

	@Override
	public ResPaper getPaperBySpeId(String speId) {
		if(!StringUtil.isNotBlank(speId)){
			return null;
		}

		ResPaperExample example = new ResPaperExample();

        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
		.andSpeIdEqualTo(speId)
		.andDeptFlowEqualTo("0");

		example.setOrderByClause("CREATE_TIME DESC");

		List<ResPaper> papers = paperMapper.selectByExample(example);

		if(papers==null || papers.isEmpty()){
			return null;
		}

		return papers.get(0);
	}

	@Override
	public ResPaper getPaperByStandardDeptId(String standardDeptId){
		if(!StringUtil.isNotBlank(standardDeptId)){
			return null;
		}
		
		ResPaperExample example = new ResPaperExample();

        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
		.andDeptFlowEqualTo(standardDeptId).andOrgNameIsNull()
		.andSpeIdEqualTo("0");
		
		example.setOrderByClause("CREATE_TIME DESC");
		
		List<ResPaper> papers = paperMapper.selectByExample(example);
		
		if(papers==null || papers.isEmpty()){
			return null;
		}
		
		return papers.get(0);
	}
	
	@Override
	public TestPaper readTestPaper(String paperFlow){
		if(!StringUtil.isNotBlank(paperFlow)){
			return null;
		}
		return testPaperMapper.selectByPrimaryKey(paperFlow);
	}
}
