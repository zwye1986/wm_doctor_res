package com.pinde.sci.biz.res.impl;

import com.pinde.core.common.sci.dao.ResDoctorOrgHistoryMapper;
import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResDoctorOrgHistory;
import com.pinde.core.model.ResDoctorOrgHistoryExample;
import com.pinde.core.model.ResDoctorOrgHistoryExample.Criteria;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorOrgHistoryBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.res.ResDoctorExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class ResDoctorOrgHistoryBizImpl implements IResDoctorOrgHistoryBiz{
	@Autowired
	private ResDoctorOrgHistoryMapper docOrgHistoryMapper;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private ResDoctorExtMapper doctorExtMapper;
	
	
	@Override
	public int editDocOrgHistory(ResDoctorOrgHistory docOrgHistory) {
		if(docOrgHistory!=null){
			if(StringUtil.isNotBlank(docOrgHistory.getRecordFlow())){
				GeneralMethod.setRecordInfo(docOrgHistory, false);
				return docOrgHistoryMapper.updateByPrimaryKeySelective(docOrgHistory);
			}else{
				docOrgHistory.setRecordFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(docOrgHistory, true);
				return docOrgHistoryMapper.insertSelective(docOrgHistory);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}
	
	@Override
	public int editDocOrgHistoryAndDoc(ResDoctorOrgHistory docOrgHistory,ResDoctor doctor) {
		doctorBiz.editDoctor(doctor);
		return editDocOrgHistory(docOrgHistory);
	}

	@Override
	public ResDoctorOrgHistory readDocOrgHistory(String recordFlow) {
		return docOrgHistoryMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public List<ResDoctorOrgHistory> searchIsInDocByDocOrgHis(ResDoctorOrgHistory docOrgHistory) {
		ResDoctorOrgHistoryExample example = new ResDoctorOrgHistoryExample();
        Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		setCriteria(docOrgHistory,criteria);
		example.setOrderByClause("OUT_DATE");
		return docOrgHistoryMapper.selectByExample(example);
	}
	
	private void setCriteria(ResDoctorOrgHistory docOrgHistory,Criteria criteria){
		if(docOrgHistory!=null){
			if(StringUtil.isNotBlank(docOrgHistory.getOrgFlow())){
				criteria.andOrgFlowEqualTo(docOrgHistory.getOrgFlow());
			}
			if(StringUtil.isNotBlank(docOrgHistory.getOrgName())){
				criteria.andOrgNameLike("%"+docOrgHistory.getOrgName()+"%");
			}
			if(StringUtil.isNotBlank(docOrgHistory.getHistoryOrgFlow())){
				criteria.andHistoryOrgFlowEqualTo(docOrgHistory.getHistoryOrgFlow());
			}
			if(StringUtil.isNotBlank(docOrgHistory.getHistoryOrgName())){
				criteria.andHistoryOrgNameLike("%"+docOrgHistory.getHistoryOrgName()+"%");
			}
			if(StringUtil.isNotBlank(docOrgHistory.getChangeStatusId())){
				criteria.andChangeStatusIdEqualTo(docOrgHistory.getChangeStatusId());
			}
			if(StringUtil.isNotBlank(docOrgHistory.getDoctorFlow())){
				criteria.andDoctorFlowEqualTo(docOrgHistory.getDoctorFlow());
			}
			if(StringUtil.isNotBlank(docOrgHistory.getDoctorName())){
				criteria.andDoctorNameLike("%"+docOrgHistory.getDoctorName()+"%");
			}
			if(StringUtil.isNotBlank(docOrgHistory.getSessionNumber())){
				criteria.andSessionNumberEqualTo(docOrgHistory.getSessionNumber());
			}
		}
	}
	
	@Override
	public List<ResDoctorOrgHistory> searchHistoryByDoctorFlows(List<String> doctorFlows) {
		ResDoctorOrgHistoryExample example = new ResDoctorOrgHistoryExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowIn(doctorFlows);
		example.setOrderByClause("OUT_DATE");
		return docOrgHistoryMapper.selectByExample(example);
	}

	@Override
	public List<ResDoctorOrgHistory> searchOrgFlowByhistoryOrgFlow(
			ResDoctorOrgHistory docOrgHistory) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("docOrgHistory", docOrgHistory);
		List<ResDoctorOrgHistory> list= doctorExtMapper.searchOrgFlowByhistoryOrgFlow(map);
		return list;
	}
}
