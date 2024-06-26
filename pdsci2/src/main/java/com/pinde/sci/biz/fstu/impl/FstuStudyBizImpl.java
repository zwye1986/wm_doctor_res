package com.pinde.sci.biz.fstu.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.IFstuStudyBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.FstuStudyMapper;
import com.pinde.sci.model.mo.FstuStudy;
import com.pinde.sci.model.mo.FstuStudyExample;
import com.pinde.sci.model.mo.FstuStudyExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@Transactional(rollbackFor=Exception.class)
public class FstuStudyBizImpl implements IFstuStudyBiz{
	
	@Autowired
	private FstuStudyMapper studyMapper;

	@Override
	public FstuStudy findByFlow(String studyFlow) {

		return studyMapper.selectByPrimaryKey(studyFlow);
	}
	/**
	 * 编辑
	 */
	@Override
	public int saveOrEdit(FstuStudy study) {
			if(StringUtil.isNotBlank(study.getStudyFlow())){
				GeneralMethod.setRecordInfo(study, false);
				return studyMapper.updateByPrimaryKeySelective(study);
			}else{
				study.setStudyFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(study, true);
				return studyMapper.insertSelective(study);
				}
		}
	/**
	 * FstuStudy查询
	 */
	@Override
	public List<FstuStudy> search(FstuStudy study) {
		FstuStudyExample example=new FstuStudyExample();
		Criteria criteria =example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(study.getStudyOrgName())){ 
			criteria.andStudyOrgNameLike("%"+study.getStudyOrgName()+"%");
		}
		return studyMapper.selectByExample(example);
	}
	@Override
	public List<FstuStudy> searchStudys() {
		FstuStudyExample example=new FstuStudyExample();
		Criteria criteria =example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		return studyMapper.selectByExample(example);
	}
	
	@Override
	public List<FstuStudy> searchByUserFlows(List<String> userFlow,List<String> auditStatusId){
		FstuStudyExample example=new FstuStudyExample();
		Criteria criteria =example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andUserFlowIn(userFlow);
		if(auditStatusId!=null && auditStatusId.size()>0){
			criteria.andAuditStatusIdIn(auditStatusId);
		}
		return studyMapper.selectByExample(example);
	}
}