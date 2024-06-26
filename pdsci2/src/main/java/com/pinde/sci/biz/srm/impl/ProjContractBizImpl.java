package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.*;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.SysOrgMapper;
import com.pinde.sci.dao.pub.PubProjExtMapper;
import com.pinde.sci.enums.pub.ProjCategroyEnum;
import com.pinde.sci.enums.srm.*;
import com.pinde.sci.model.mo.PubProj;
import com.pinde.sci.model.mo.PubProjProcess;
import com.pinde.sci.model.mo.PubProjRec;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class ProjContractBizImpl implements IProjContractBiz{

	
	@Autowired
	private IProjApplyBiz applyBiz;
	@Autowired
	private IProjRecBiz projRecBiz;
	@Autowired
	private IProjProcessBiz projProcessBiz;
	@Autowired
	private PubProjExtMapper pubProjExtMapper;
	@Autowired
	private SysOrgMapper sysOrgMapper;
	@Autowired
	private ICommonBiz commonBiz;
	@Autowired
	private IPubProjBiz projBiz;

	@Override
	public List<PubProj> searchLocalProj(PubProj proj,String recTypeId) {
		
		SysUser currentUser = GlobalContext.getCurrentUser();
		String currentOrgFlow = currentUser.getOrgFlow();
		if(StringUtil.isNotBlank(proj.getApplyOrgFlow())){
			currentOrgFlow = proj.getApplyOrgFlow();
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		proj.setRecordStatus(GlobalConstant.FLAG_Y);
		paramMap.put("status1", ProjContractStatusEnum.Submit.getId());
		paramMap.put("status2", ProjContractStatusEnum.SecondBack.getId());
		paramMap.put("status3", ProjContractStatusEnum.ThirdBack.getId());
		paramMap.put("orgFlow", currentOrgFlow);
		paramMap.put("proj", proj);
		paramMap.put("scope", GlobalConstant.USER_LIST_LOCAL);
		paramMap.put("chargeOrgFlow", InitConfig.getSysCfg("global_org_flow"));
		return this.pubProjExtMapper.selectContractAduitProjList(paramMap);
		
		
	}

	@Override
	public List<PubProj> searchChargeProj(PubProj proj,String recTypeId) {
		SysUser currentUser = GlobalContext.getCurrentUser();
		proj.setRecordStatus(GlobalConstant.FLAG_Y);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status1", ProjContractStatusEnum.FirstAudit.getId());
		paramMap.put("status2", ProjContractStatusEnum.ThirdBack.getId());
		paramMap.put("proj", proj);
		paramMap.put("scope", GlobalConstant.USER_LIST_CHARGE);
		paramMap.put("chargeOrgFlow", currentUser.getOrgFlow());
		return this.pubProjExtMapper.selectContractAduitProjList(paramMap);
		
	}

	@Override
	public List<PubProj> searchGlobalProj(PubProj proj,String recTypeId,String startYear,String endYear) {
	    
		proj.setRecordStatus(GlobalConstant.FLAG_Y);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status1", ProjContractStatusEnum.SecondAudit.getId());
		paramMap.put("status2", ProjContractStatusEnum.FirstAudit.getId());
		paramMap.put("proj", proj);
		if(StringUtil.isNotBlank(startYear)) {
			paramMap.put("startYear", startYear);
		}
		if(StringUtil.isNotBlank(endYear)) {
			paramMap.put("endYear", endYear);
		}
		paramMap.put("scope", GlobalConstant.USER_LIST_GLOBAL);
		paramMap.put("chargeOrgFlow", InitConfig.getSysCfg("global_org_flow"));
		return this.pubProjExtMapper.selectContractAduitProjList(paramMap);
	}

	@Override
	public void contractAudit(String projFlow, String projListScope,
			String agreeFlag, String auditContent) {
		String _statusId = "";
		String _statusName = "";
		String _remark = "";
		
		if(GlobalConstant.PROJ_STATUS_SCOPE_LOCAL.equals(projListScope)){
			//合同
			_statusId = ProjContractStatusEnum.FirstAudit.getId();
			_statusName = ProjContractStatusEnum.FirstAudit.getName();
			_remark = ProcessRemarkEnum.FirstAuditAgree.getName();
		}
		else if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(projListScope)){
			//合同
			_statusId =  ProjContractStatusEnum.SecondAudit.getId();
			_statusName = ProjContractStatusEnum.SecondAudit.getName();
			 _remark = ProcessRemarkEnum.SecondAuditAgree.getName();
		}else if(GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL.equals(projListScope)){
			//合同
			_statusId =  ProjContractStatusEnum.ThirdAudit.getId();
			_statusName =InitConfig.getSysCfg("top_org_level") + ProjContractStatusEnum.ThirdAudit.getName();
			_remark =InitConfig.getSysCfg("top_org_level") + ProcessRemarkEnum.ThirdAuditAgree.getName();
		}
		//是否退回填写状态
		if(GlobalConstant.FLAG_N.equals(agreeFlag)){
			//合同
			_statusId = ProjContractStatusEnum.Apply.getId();
			_statusName = ProjContractStatusEnum.Apply.getName();
			if(GlobalConstant.PROJ_STATUS_SCOPE_LOCAL.equals(projListScope)){
				_remark = ProcessRemarkEnum.FirstAuditNotAgree.getName();
			}else if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(projListScope)){
				_remark = ProcessRemarkEnum.SecondAuditNotAgree.getName();
				_statusId = ProjContractStatusEnum.SecondBack.getId();
				_statusName = ProjContractStatusEnum.SecondBack.getName();
			}else if(GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL.equals(projListScope)){
				_remark =InitConfig.getSysCfg("top_org_level") + ProcessRemarkEnum.ThirdAuditNotAgree.getName();
				_statusId = ProjContractStatusEnum.ThirdBack.getId();
				_statusName =InitConfig.getSysCfg("top_org_level") + ProjContractStatusEnum.ThirdBack.getName();
			}
		}
		PubProj proj = this.projBiz.readProject(projFlow);
		proj.setProjStatusId(_statusId);
		proj.setProjStatusName(_statusName);
		PubProjRec rec = this.projRecBiz.selectProjRecByProjFlowAndRecType(projFlow, ProjRecTypeEnum.Contract.getId());
		if(rec!=null){
		    rec.setProjStatusId(_statusId);
		    rec.setProjStatusName(_statusName);
		    this.projProcessBiz.addProcess(rec,_remark,auditContent);
		}else{
			PubProjProcess process = new PubProjProcess();
			process.setProjFlow(proj.getProjFlow());
			process.setProjStageId(proj.getProjStageId());
			process.setProjStageName(proj.getProjStageName());
			process.setProjStatusId(proj.getProjStatusId());
			process.setProjStatusName(proj.getProjStatusName());
			process.setAuditContent(auditContent);
			process.setProcessRemark(_remark);
			process.setOperTime(DateUtil.getCurrDateTime());
			this.projProcessBiz.addProcess(process);
		}
		
		String backApplyUser = InitConfig.getSysCfg("srm_back_apply_user");
		if(GlobalConstant.FLAG_Y.equals(backApplyUser) && GlobalConstant.FLAG_N.equals(agreeFlag)){
			//直接退回到项目负责人
			_statusId =  ProjApplyStatusEnum.Apply.getId();
			_statusName = ProjApplyStatusEnum.Apply.getName();
			//如果是合同阶段
			if(proj.getProjStageId().equals(ProjStageEnum.Contract.getId())){
				_statusId =  ProjContractStatusEnum.Apply.getId();
				_statusName = ProjContractStatusEnum.Apply.getName();
			}
			proj.setProjStatusId(_statusId);
			proj.setProjStatusName(_statusName);
		}
		
		if(rec!=null){
			rec.setProjStatusId(_statusId);
			rec.setProjStatusName(_statusName);
			GeneralMethod.setRecordInfo(rec, false);
			this.projRecBiz.modProjRec(rec);
		}
		//如果不是重点专科项目(重点专科只有合同阶段)
		if(!proj.getProjCategoryId().equals(ProjCategroyEnum.Zk.getId())){
		//如果是最高部门审核通过 则将阶段改为进展阶段 状态改为填写状态
		if(StringUtil.isEquals(InitConfig.getSysCfg("srm_for_use") , GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL) && ProjContractStatusEnum.ThirdAudit.getId().equals(_statusId)){
			proj.setProjStageId(ProjStageEnum.Schedule.getId());
			proj.setProjStageName(ProjStageEnum.Schedule.getName());
			proj.setProjStatusId(ProjScheduleStatusEnum.Apply.getId());
			proj.setProjStatusName(ProjScheduleStatusEnum.Apply.getName());
		}else if(StringUtil.isEquals(InitConfig.getSysCfg("srm_for_use") , GlobalConstant.PROJ_STATUS_SCOPE_LOCAL) && ProjContractStatusEnum.FirstAudit.getId().equals(_statusId)){
			proj.setProjStageId(ProjStageEnum.Schedule.getId());
			proj.setProjStageName(ProjStageEnum.Schedule.getName());
			proj.setProjStatusId(ProjScheduleStatusEnum.Apply.getId());
			proj.setProjStatusName(ProjScheduleStatusEnum.Apply.getName());
		}
		}
		//回写项目阶段和状态
		GeneralMethod.setRecordInfo(proj, false);
		projBiz.modProject(proj);

	}

	@Override
	public void controctBackForThridAudit(String recFlow) {
		PubProjRec contract = this.projRecBiz.selectProjRecNoContentByFlow(recFlow);
		if(contract!=null){
			contract.setProjStatusId(ProjContractStatusEnum.Apply.getId());
			contract.setProjStatusName(ProjContractStatusEnum.Apply.getName());
			GeneralMethod.setRecordInfo(contract, false);
			this.projRecBiz.modProjRec(contract);
			
			PubProj proj = new PubProj();
			proj.setProjFlow(contract.getProjFlow());
			proj.setProjStageId(ProjStageEnum.Contract.getId());
			proj.setProjStageName(ProjStageEnum.Contract.getName());
			proj.setProjStatusId(ProjContractStatusEnum.Apply.getId());
			proj.setProjStatusName(ProjContractStatusEnum.Apply.getName());
			this.projBiz.modProject(proj);
			
			PubProjProcess process = new PubProjProcess();
			process.setProjFlow(contract.getProjFlow());
			process.setRecFlow(contract.getRecFlow());
			process.setProjStageId(ProjStageEnum.Contract.getId());
			process.setProjStageName(ProjStageEnum.Contract.getName());
			process.setProjStatusId(ProjContractStatusEnum.ThirdBack.getId());
			process.setProjStatusName(InitConfig.getSysCfg("top_org_level") + ProjContractStatusEnum.ThirdBack.getName());
			process.setRecTypeId(ProjRecTypeEnum.Contract.getId());
			process.setRecTypeName(ProjRecTypeEnum.Contract.getName());
			process.setProcessRemark(InitConfig.getSysCfg("top_org_level") + ProcessRemarkEnum.ThirdAuditNotAgree.getName());
			process.setAuditContent(InitConfig.getSysCfg("top_org_level") + ProcessRemarkEnum.ThirdAuditNotAgree.getName());
			this.projProcessBiz.addProcess(process);
		}
		
	}
	
	
}
