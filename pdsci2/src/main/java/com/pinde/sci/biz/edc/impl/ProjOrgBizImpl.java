package com.pinde.sci.biz.edc.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.edc.IProjOrgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.EdcProjOrgMapper;
import com.pinde.sci.dao.base.PubPatientMapper;
import com.pinde.sci.dao.base.PubProjOrgMapper;
import com.pinde.sci.dao.pub.PubProjOrgExtMapper;
import com.pinde.sci.enums.edc.PatientTypeEnum;
import com.pinde.sci.enums.pub.ProjOrgTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.PubProjOrgExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(rollbackFor=Exception.class)
public class ProjOrgBizImpl implements IProjOrgBiz {
	
	@Autowired
	private PubProjOrgMapper pubProjOrgMapper;
	@Autowired
	private PubPatientMapper patientMapper;
	@Autowired
	private EdcProjOrgMapper edcProjOrgMapper;
	@Autowired
	private PubProjOrgExtMapper projOrgExtMapper;

	@Override
	public List<PubProjOrg> search(String projFlow) {
		PubProjOrgExample example = new PubProjOrgExample();
		Criteria criteria = example.createCriteria();
		criteria.andProjFlowEqualTo(projFlow);
//		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("RECORD_STATUS DESC,CENTER_NO");
		return pubProjOrgMapper.selectByExample(example);
	}

	@Override
	public PubProjOrg read(String recordFlow) {
		return pubProjOrgMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public void add(PubProjOrg projOrg) {
		projOrg.setRecordFlow(PkUtil.getUUID());
		if (StringUtil.isNotBlank(projOrg.getOrgTypeId())) {
			projOrg.setOrgTypeName(ProjOrgTypeEnum.getNameById(projOrg.getOrgTypeId()));
		}
		GeneralMethod.setRecordInfo(projOrg, true);
		
		
		//默认添加测试样本
		for(int i=1;i<=GlobalConstant.DEFAULT_TEST_PATIENT_COUNT;i++){
			PubPatient patient = new  PubPatient();
			patient.setPatientFlow(PkUtil.getUUID());
			patient.setPatientSeq(i);
			patient.setPatientCode(i+"");
			patient.setProjFlow(projOrg.getProjFlow());
			patient.setOrgFlow(projOrg.getOrgFlow());
			patient.setPatientTypeId(PatientTypeEnum.Test.getId());
			patient.setPatientTypeName(PatientTypeEnum.Test.getName());
			GeneralMethod.setRecordInfo(patient, true);
			patientMapper.insert(patient);
		}
		
		pubProjOrgMapper.insert(projOrg);		
	}
	
	@Override
	public void addProjOrg(PubProjOrg projOrg) {
		projOrg.setRecordFlow(PkUtil.getUUID());
		if (StringUtil.isNotBlank(projOrg.getOrgTypeId())) {
			projOrg.setOrgTypeName(ProjOrgTypeEnum.getNameById(projOrg.getOrgTypeId()));
		}
		GeneralMethod.setRecordInfo(projOrg, true);
		
		pubProjOrgMapper.insert(projOrg);		
	}

	@Override
	public void mod(PubProjOrg projOrg) {
		GeneralMethod.setRecordInfo(projOrg, false);
		if (StringUtil.isNotBlank(projOrg.getOrgTypeId())) {
			projOrg.setOrgTypeName(ProjOrgTypeEnum.getNameById(projOrg.getOrgTypeId()));
		}
		pubProjOrgMapper.updateByPrimaryKeySelective(projOrg);		
	}

	@Override
	public void del(PubProjOrg projOrg) {
		GeneralMethod.setRecordInfo(projOrg, false);
		pubProjOrgMapper.updateByPrimaryKeySelective(projOrg);			
	}

	@Override
	public List<PubProjOrg> searchProjOrg(String projFlow) {
		PubProjOrgExample example = new PubProjOrgExample();
		Criteria criteria = example.createCriteria();
		criteria.andProjFlowEqualTo(projFlow);
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("CENTER_NO");
		return pubProjOrgMapper.selectByExample(example);
	}

	@Override
	public PubProjOrg readProjOrg(String projFlow, String orgFlow) {
		PubProjOrgExample example = new PubProjOrgExample();
		Criteria criteria = example.createCriteria();
		criteria.andProjFlowEqualTo(projFlow);
		criteria.andOrgFlowEqualTo(orgFlow);
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<PubProjOrg> projOrgList =  pubProjOrgMapper.selectByExample(example);
		if(projOrgList !=null && projOrgList.size()>0){
			return projOrgList.get(0);
		}
		return null;
	}

	@Override
	public EdcProjOrg readEdcProjOrg(String projFlow, String orgFlow) {
		EdcProjOrgExample example = new EdcProjOrgExample();
		example.createCriteria().andProjFlowEqualTo(projFlow).andOrgFlowEqualTo(orgFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<EdcProjOrg> list = edcProjOrgMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	
	@Override
	public int saveProjOrgLock(EdcProjOrg edcProjOrg){
			if(StringUtil.isNotBlank(edcProjOrg.getRecordFlow())){
				EdcProjOrgExample example = new EdcProjOrgExample();
				example.createCriteria().andProjFlowEqualTo(edcProjOrg.getProjFlow()).andOrgFlowEqualTo(edcProjOrg.getOrgFlow());
				int result = edcProjOrgMapper.updateByExample(edcProjOrg,example);
				if(result != GlobalConstant.ZERO_LINE){
					return GlobalConstant.ONE_LINE; 
				}
			}else{
				edcProjOrg.setRecordFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(edcProjOrg, true);
				int result = edcProjOrgMapper.insert(edcProjOrg);
				if(result != GlobalConstant.ZERO_LINE){
					return GlobalConstant.ONE_LINE; 
				}
			}
		return GlobalConstant.ZERO_LINE;
	}


	@Override
	public int delProjOrgByRecordFlows(List<String> recordFlowList) {
		return projOrgExtMapper.delete(recordFlowList);
	}
	
	@Override
	public List<PubProjOrg> searchProjOrg(PubProjOrg projOrg) {
		PubProjOrgExample example = new PubProjOrgExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(projOrg.getProjFlow())) {
			criteria.andProjFlowEqualTo(projOrg.getProjFlow());
		}
		if (StringUtil.isNotBlank(projOrg.getOrgFlow())) {
			criteria.andOrgFlowEqualTo(projOrg.getOrgFlow());
		}
		if (StringUtil.isNotBlank(projOrg.getOrgTypeId())) {
			criteria.andOrgTypeIdEqualTo(projOrg.getOrgTypeId());
		}
		example.setOrderByClause("CENTER_NO");
		return pubProjOrgMapper.selectByExample(example);
	}
}
