package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.IFundInfoBiz;
import com.pinde.sci.biz.srm.IFundInfoDetailBiz;
import com.pinde.sci.biz.srm.IFundProcessBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.PubProjMapper;
import com.pinde.sci.dao.base.SrmProjFundInfoMapper;
import com.pinde.sci.dao.srm.ProjFundExtMapper;
import com.pinde.sci.enums.srm.AchStatusEnum;
import com.pinde.sci.enums.srm.ProjFundTypeEnum;
import com.pinde.sci.enums.srm.ProjScheduleStatusEnum;
import com.pinde.sci.enums.srm.ProjStageEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.SrmProjFundInfoExample.Criteria;
import com.pinde.sci.model.srm.PubProjExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class FundInfoBizImpl implements IFundInfoBiz{

	@Autowired
	private SrmProjFundInfoMapper fundInfoMapper;
	@Autowired
	private IFundProcessBiz processBiz;
	@Autowired
	private IFundInfoDetailBiz fundInfoDetailBiz;
	@Autowired
	private ProjFundExtMapper projFundExtMapper;
	@Autowired
	private PubProjMapper projMapper;
	
	@Override
	public List<SrmProjFundInfo> searchFundInfo(SrmProjFundInfo fundInfo) {
		SrmProjFundInfoExample example=new SrmProjFundInfoExample();
		Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(fundInfo.getProjFlow())){
			criteria.andProjFlowEqualTo(fundInfo.getProjFlow());
		}
		if(StringUtil.isNotBlank(fundInfo.getBudgetStatusId())){
			criteria.andBudgetStatusIdEqualTo(fundInfo.getBudgetStatusId());
		}
		if(StringUtil.isNotBlank(fundInfo.getCreateUserFlow())){
			criteria.andCreateUserFlowEqualTo(fundInfo.getCreateUserFlow());
		}
		if(StringUtil.isNotBlank(fundInfo.getFundFlow())){
			criteria.andFundFlowEqualTo(fundInfo.getFundFlow());
		}
		if(StringUtil.isNotBlank(fundInfo.getSchemeFlow())){
			criteria.andSchemeFlowEqualTo(fundInfo.getSchemeFlow());
		}
		return fundInfoMapper.selectByExample(example);
	}

	@Override
	public void saveFundInfo(SrmProjFundInfo fundInfo,List<SrmProjFundDetail> fundDtlList,SrmFundProcess process) {
		SrmProjFundInfo findFund = this.getFundInfoByFlow(fundInfo.getFundFlow());
		if(findFund!=null&&findFund.getSchemeFlow()!=null&&!findFund.getSchemeFlow().equals(fundInfo.getSchemeFlow())&&AchStatusEnum.FirstBack.getId().equals(findFund.getBudgetStatusId())){//审核不通过时修改了预算方案
			this.fundInfoDetailBiz.updateRecordStatusByFundFlow(findFund.getFundFlow(),GlobalConstant.RECORD_STATUS_N);
		}
		//保存或修改经费信息
		if(StringUtil.isNotBlank(fundInfo.getFundFlow())){
			GeneralMethod.setRecordInfo(fundInfo, false);
			fundInfoMapper.updateByPrimaryKeySelective(fundInfo);
		}else{
			fundInfo.setFundFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(fundInfo, true);
			fundInfoMapper.insertSelective(fundInfo);
		}
		//保存或修改经费明细信息
		for(int i=0;i<fundDtlList.size();i++){
			if(StringUtil.isBlank(fundDtlList.get(i).getFundTypeId())) {
				fundDtlList.get(i).setFundTypeId(ProjFundTypeEnum.Budget.getId());
				fundDtlList.get(i).setFundTypeName(ProjFundTypeEnum.Budget.getName());
			}
				fundDtlList.get(i).setFundFlow(fundInfo.getFundFlow());
			if(StringUtil.isNotBlank(fundDtlList.get(i).getFundDetailFlow())){
				GeneralMethod.setRecordInfo(fundDtlList.get(i), false);
				fundInfoDetailBiz.updateFundDetail(fundDtlList.get(i));
			 }else{
				 fundDtlList.get(i).setFundDetailFlow(PkUtil.getUUID());
				 GeneralMethod.setRecordInfo(fundDtlList.get(i), true);
				 fundInfoDetailBiz.saveFundDetail(fundDtlList.get(i));
			 }
		}
		//保存过程信息
		process.setFundFlow(fundInfo.getFundFlow());
		process.setFundProcessFlow(PkUtil.getUUID());
		GeneralMethod.setRecordInfo(process, true);
		process.setOperateTime(fundInfo.getModifyTime());
		processBiz.saveFundProcess(process);
	}

	@Override
	public void saveSurplusInfo(SrmProjFundInfo fundInfo,SrmProjFundDetail fundDtl,SrmFundProcess process) {

		//保存或修改经费信息
		if(StringUtil.isNotBlank(fundInfo.getFundFlow())){
			GeneralMethod.setRecordInfo(fundInfo, false);
			fundInfoMapper.updateByPrimaryKeySelective(fundInfo);
		}else{
			fundInfo.setFundFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(fundInfo, true);
			fundInfoMapper.insertSelective(fundInfo);
		}
		fundDtl.setProvideDateTime(DateUtil.getCurrDateTime());
		//保存或修改经费明细信息(两条记录 项目结余   个人、医院 账户转入)
		if(StringUtil.isNotBlank(fundDtl.getFundFlow())) {
			fundDtl.setFundDetailFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(fundDtl, true);
			fundInfoDetailBiz.saveFundDetail(fundDtl);
		}
		fundDtl.setFundFlow(fundInfo.getFundFlow());
		fundDtl.setFundDetailFlow("");
		if(StringUtil.isNotBlank(fundDtl.getFundDetailFlow())){
			GeneralMethod.setRecordInfo(fundDtl, false);
			fundInfoDetailBiz.updateFundDetail(fundDtl);
		}else{
			fundDtl.setFundDetailFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(fundDtl, true);
			fundInfoDetailBiz.saveFundDetail(fundDtl);
		}

		//保存过程信息
		if(StringUtil.isBlank(process.getFundFlow())) {
			process.setFundFlow(fundInfo.getFundFlow());
		}
		process.setFundProcessFlow(PkUtil.getUUID());
		GeneralMethod.setRecordInfo(process, true);
		process.setOperateTime(fundInfo.getModifyTime());
		processBiz.saveFundProcess(process);
	}

	@Override
	public List<PubProjExt> searchBudgetAuditList(PubProjExt projExt) {

		if(null==projExt.getProjFundInfo()){
			projExt.setProjFundInfo(new SrmProjFundInfo());
		}
		return this.projFundExtMapper.selectProjFundInfoList(projExt);
	}

	@Override
	public List<PubProjExt> searchPubProjExtList(PubProjExt projExt) {
		if(null==projExt.getProjFundInfo()){
			projExt.setProjFundInfo(new SrmProjFundInfo());
		}
		return this.projFundExtMapper.selectProjFundInfoExtList(projExt);
	}

	@Override
	public void updateFundInfoStatus(SrmProjFundInfo fundInfo,
			SrmFundProcess process) {
		fundInfoMapper.updateByPrimaryKeySelective(fundInfo);
		if(StringUtil.isBlank(process.getFundProcessFlow())) {
			process.setFundProcessFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(process, true);
			processBiz.saveFundProcess(process);
		}else{
			GeneralMethod.setRecordInfo(process, false);
			processBiz.updateFundProcess(process);
		}
	}

	@Override
	public SrmProjFundInfo getFundInfoByFlow(String fundFlow) {
		return this.fundInfoMapper.selectByPrimaryKey(fundFlow);
	}

	@Override
	public void budgetAudit(SrmProjFundInfo fundInfo, SrmFundProcess process) {
		this.updateFundInfoStatus(fundInfo, process);
		PubProj proj = new PubProj();
		proj.setProjFlow(fundInfo.getProjFlow());
		if(AchStatusEnum.FirstAudit.getId().equals(fundInfo.getBudgetStatusId())){
			proj.setProjStageId(ProjStageEnum.Schedule.getId());
			proj.setProjStageName(ProjStageEnum.Schedule.getName());
			proj.setProjStatusId(ProjScheduleStatusEnum.Apply.getId());
			proj.setProjStatusName(ProjScheduleStatusEnum.Apply.getName());
			this.projMapper.updateByPrimaryKeySelective(proj);
		}
		
	}

    @Override
    public void updateFundInfo(SrmProjFundInfo fundInfo) {
        fundInfoMapper.updateByPrimaryKeySelective(fundInfo);
    }
}
