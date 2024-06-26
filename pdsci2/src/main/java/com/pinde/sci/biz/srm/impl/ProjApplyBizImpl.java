package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.*;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.PubProjMapper;
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
public class ProjApplyBizImpl implements IProjApplyBiz{

	@Autowired
	private IProjRecBiz projRecBiz;
	@Autowired
	private IProjProcessBiz processBiz;
	@Autowired
	private PubProjMapper pubProjMapper;
	@Autowired
	private PubProjExtMapper pubProjExtMapper;
	@Autowired
	private ICommonBiz commonBiz;
	@Autowired
	private IPubProjBiz projBiz;
	@Autowired
	private IOrgBiz orgBiz;
	
	@Override
	public List<PubProj> searchLocalProj(PubProj proj,String recTypeId,String startYear,String endYear) {
		SysUser currentUser = GlobalContext.getCurrentUser();
		String currentOrgFlow = currentUser.getOrgFlow();
		if(StringUtil.isNotBlank(proj.getApplyOrgFlow())){
			currentOrgFlow = proj.getApplyOrgFlow();
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		proj.setRecordStatus(GlobalConstant.FLAG_Y);
		//paramMap.put("isStatus", ProjApplyStatusEnum.Submit.getId());
		paramMap.put("status1", ProjApplyStatusEnum.Submit.getId());
		paramMap.put("status2", ProjApplyStatusEnum.SecondBack.getId());
		paramMap.put("status3", ProjApplyStatusEnum.ThirdBack.getId());
		if(StringUtil.isNotBlank(startYear)) {
			paramMap.put("startYear", startYear);
		}
		if(StringUtil.isNotBlank(endYear)) {
			paramMap.put("endYear", endYear);
		}
		paramMap.put("orgFlow", currentOrgFlow);
		paramMap.put("proj", proj);
		paramMap.put("scope", GlobalConstant.USER_LIST_LOCAL);
		paramMap.put("chargeOrgFlow", InitConfig.getSysCfg("global_org_flow"));
		return this.pubProjExtMapper.selectApplyAduitProjList(paramMap);
	}

	@Override
	public List<PubProj> searchChargeProj(PubProj proj,String recTypeId) {
		SysUser currentUser = GlobalContext.getCurrentUser();
		//String currentOrgFlow = currentUser.getOrgFlow();
		//if(StringUtil.isNotBlank(proj.getApplyOrgFlow())){
			//currentOrgFlow = proj.getApplyOrgFlow();
		//}
		proj.setRecordStatus(GlobalConstant.FLAG_Y);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//paramMap.put("isStatus", ProjApplyStatusEnum.FirstAudit.getId());
		paramMap.put("status1", ProjApplyStatusEnum.FirstAudit.getId());
		paramMap.put("status2", ProjApplyStatusEnum.ThirdBack.getId());
		//paramMap.put("orgFlow", currentOrgFlow);
		paramMap.put("proj", proj);
		paramMap.put("scope", GlobalConstant.USER_LIST_CHARGE);
		paramMap.put("chargeOrgFlow", currentUser.getOrgFlow());
		return this.pubProjExtMapper.selectApplyAduitProjList(paramMap);
	}
 
	@Override
	public List<PubProj> searchGlobalProj(PubProj proj,String recTypeId) {
//		SysUser currentUser = GlobalContext.getCurrentUser();
//      String currentOrgFlow = currentUser.getOrgFlow();
//		if(StringUtil.isNotBlank(proj.getApplyOrgFlow())){
//			currentOrgFlow = proj.getApplyOrgFlow();
//		}
		proj.setRecordStatus(GlobalConstant.FLAG_Y);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//paramMap.put("orgFlow", currentOrgFlow);
		//paramMap.put("isStatus", ProjApplyStatusEnum.SecondAudit.getId());
		//paramMap.put("isNotStatus1", ProjApplyStatusEnum.Apply.getId());
		//paramMap.put("isNotStatus2", ProjApplyStatusEnum.ThirdAudit.getId());
		paramMap.put("status1", ProjApplyStatusEnum.SecondAudit.getId());
		paramMap.put("status2", ProjApplyStatusEnum.FirstAudit.getId());
		paramMap.put("proj", proj);
		paramMap.put("scope", GlobalConstant.USER_LIST_GLOBAL);
		paramMap.put("chargeOrgFlow", InitConfig.getSysCfg("global_org_flow"));
		return this.pubProjExtMapper.selectApplyAduitProjList(paramMap);
	}


	@Override
	public void applyAudit(String projFlow, String projListScope,
			String agreeFlag, String auditContent,String acceptNumber) {
		String _statusId = "";
		String _statusName = "";
		String _remark = "";
		if(GlobalConstant.PROJ_STATUS_SCOPE_LOCAL.equals(projListScope)){
			//申请
			 _statusId =  ProjApplyStatusEnum.FirstAudit.getId();
			_statusName = ProjApplyStatusEnum.FirstAudit.getName();
			_remark = ProcessRemarkEnum.FirstAuditAgree.getName();
		}else if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(projListScope)){
			//申请
			_statusId =  ProjApplyStatusEnum.SecondAudit.getId();
			_statusName = ProjApplyStatusEnum.SecondAudit.getName();
			_remark = ProcessRemarkEnum.SecondAuditAgree.getName();
		}else if(GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL.equals(projListScope)){
			//申请
			_statusId =  ProjApplyStatusEnum.ThirdAudit.getId();
			_statusName =InitConfig.getSysCfg("top_org_level") + ProjApplyStatusEnum.ThirdAudit.getName();
			_remark =InitConfig.getSysCfg("top_org_level") + ProcessRemarkEnum.ThirdAuditAgree.getName();
		}
		//是否退回填写状态
		if(GlobalConstant.FLAG_N.equals(agreeFlag)){
			//申请
			_statusId =  ProjApplyStatusEnum.Apply.getId();
			_statusName = ProjApplyStatusEnum.Apply.getName();
			
			if(GlobalConstant.PROJ_STATUS_SCOPE_LOCAL.equals(projListScope)){
				_remark = ProcessRemarkEnum.FirstAuditNotAgree.getName();
			}else if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(projListScope)){
				_remark = ProcessRemarkEnum.SecondAuditNotAgree.getName();
				_statusId = ProjApplyStatusEnum.SecondBack.getId();
				_statusName = ProjApplyStatusEnum.SecondBack.getName();
			}else if(GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL.equals(projListScope)){
				_remark =InitConfig.getSysCfg("top_org_level") + ProcessRemarkEnum.ThirdAuditNotAgree.getName();
				_statusId = ProjApplyStatusEnum.ThirdBack.getId();
				_statusName =InitConfig.getSysCfg("top_org_level") + ProjApplyStatusEnum.ThirdBack.getName();
			}
		}
		
		PubProj proj = this.projBiz.readProject(projFlow);
		proj.setProjStatusId(_statusId);
		proj.setProjStatusName(_statusName);
		if(StringUtil.isNotBlank(acceptNumber)){
            proj.setAcceptNumber(acceptNumber);
        }
		PubProjRec rec = this.projRecBiz.selectProjRecByProjFlowAndRecType(projFlow, ProjRecTypeEnum.Apply.getId());
		if(rec!=null){
			rec.setProjStatusId(_statusId);
			rec.setProjStatusName(_statusName);
			
			this.processBiz.addProcess(rec,_remark,auditContent);
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
			this.processBiz.addProcess(process);
		}
		
		String backApplyUser = InitConfig.getSysCfg("srm_back_apply_user");
		if(GlobalConstant.FLAG_Y.equals(backApplyUser) && GlobalConstant.FLAG_N.equals(agreeFlag)){
			//直接退回到项目负责人
			_statusId =  ProjApplyStatusEnum.Apply.getId();
			_statusName = ProjApplyStatusEnum.Apply.getName();
			proj.setProjStatusId(_statusId);
			proj.setProjStatusName(_statusName);
		}
		
		if(rec!=null){
			rec.setProjStatusId(_statusId);
			rec.setProjStatusName(_statusName);
			GeneralMethod.setRecordInfo(rec, false);
			this.projRecBiz.modProjRec(rec);
		}
		
		if(StringUtil.isEquals(InitConfig.getSysCfg("srm_for_use") , GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL) && ProjApplyStatusEnum.ThirdAudit.getId().equals(_statusId)){
		    proj.setProjStageId(ProjStageEnum.Approve.getId());
		    proj.setProjStageName(ProjStageEnum.Approve.getName());
			proj.setProjStatusId(ProjApproveStatusEnum.Approving.getId());
			proj.setProjStatusName(ProjApproveStatusEnum.Approving.getName());
			if(StringUtil.isNotBlank(proj.getProjCategoryId()) && ProjCategroyEnum.Bj.getId().equals(proj.getProjCategoryId())){//
				proj.setProjStageName(ProjStageEnum.Award.getName());//科技报奖项目进入发奖阶段
				proj.setProjStageId(ProjStageEnum.Award.getId());
			}
		}else if(StringUtil.isEquals(InitConfig.getSysCfg("srm_for_use") , GlobalConstant.PROJ_STATUS_SCOPE_LOCAL) && ProjApplyStatusEnum.FirstAudit.getId().equals(_statusId)){
			proj.setProjStageId(ProjStageEnum.Approve.getId());
		    proj.setProjStageName(ProjStageEnum.Approve.getName());
			proj.setProjStatusId(ProjApproveStatusEnum.Approving.getId());
			proj.setProjStatusName(ProjApproveStatusEnum.Approving.getName());
		}
		GeneralMethod.setRecordInfo(proj, false);
		projBiz.modProject(proj);
		
	}


}
