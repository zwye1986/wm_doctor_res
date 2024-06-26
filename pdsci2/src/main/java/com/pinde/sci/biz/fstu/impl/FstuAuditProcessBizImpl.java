package com.pinde.sci.biz.fstu.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.IFstuAuditProcessBiz;
import com.pinde.sci.biz.fstu.IFstuSatAuthorBiz;
import com.pinde.sci.biz.fstu.IFstuSatBiz;
import com.pinde.sci.biz.srm.ISrmAchProcessBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class FstuAuditProcessBizImpl implements IFstuAuditProcessBiz {

	@Autowired
	private FstuAuditProcessMapper auditProcessMapper;
	@Autowired
	private IFstuSatBiz satBiz;


	@Override
	public List<FstuAuditProcess> search(String relFlow) {
		FstuAuditProcessExample example = new FstuAuditProcessExample();
		FstuAuditProcessExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(relFlow)){
			criteria.andRelFlowEqualTo(relFlow);
		}
		example.setOrderByClause("OPER_TIME");
		return auditProcessMapper.selectByExample(example);
	}

	@Override
	public int add(FstuAuditProcess auditProcess,FstuSat sat) {
		if(auditProcess!=null){
			auditProcess.setProcessFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(auditProcess,true);
			int result =  auditProcessMapper.insertSelective(auditProcess);
			if(result==GlobalConstant.ONE_LINE){
				satBiz.save(sat);
			}
			return result;
		}
		return 0;
	}

	@Override
	public int saveFsaProcess(FstuAuditProcess auditProcess) {
		return auditProcessMapper.insert(auditProcess);
	}

	@Override
	public List<FstuAuditProcess> searchFsaProcess(String relFlow, String statusId) {
		FstuAuditProcessExample example=new FstuAuditProcessExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andRelFlowEqualTo(relFlow).andProStatusIdEqualTo(statusId);
		example.setOrderByClause("CREATE_TIME DESC");
		return auditProcessMapper.selectByExample(example);
	}
}
