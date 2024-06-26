package com.pinde.sci.biz.srm.impl;


import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.*;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.PubProjMapper;
import com.pinde.sci.dao.pub.PubProjExtMapper;
import com.pinde.sci.enums.srm.ProcessRemarkEnum;
import com.pinde.sci.enums.srm.ProjApplyStatusEnum;
import com.pinde.sci.enums.srm.ProjCompleteStatusEnum;
import com.pinde.sci.enums.srm.ProjScheduleStatusEnum;
import com.pinde.sci.model.mo.PubProj;
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
public class ProjCompleteBizImpl implements IProjCompleteBiz {

	@Autowired
	private PubProjMapper pubProjMapper;
	
	@Autowired
	private IProjRecBiz projRecBiz;
	
	@Autowired
	private IProjProcessBiz projProcessBiz;
	
	@Autowired
	private PubProjExtMapper pubProjExtMapper;
	
	@Autowired
	private ICommonBiz commonBiz;
	
	@Autowired
	private IPubProjBiz projBiz;
	
	
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
//		paramMap.put("status1", ProjCompleteStatusEnum.Submit.getId());
//		paramMap.put("status2", ProjCompleteStatusEnum.SecondBack.getId());
//		paramMap.put("status3", ProjCompleteStatusEnum.ThirdBack.getId());

		if(StringUtil.isNotBlank(proj.getProjStatusId())){
			if("All".equals(proj.getProjStatusId())){//所有
				proj.setProjStageId("");
				proj.setProjStatusId("");
			} else if("Submit".equals(proj.getProjStatusId())){
				paramMap.put("status1", ProjScheduleStatusEnum.Submit.getId());
				paramMap.put("status2", ProjScheduleStatusEnum.SecondBack.getId());
				paramMap.put("status3", ProjScheduleStatusEnum.ThirdBack.getId());
				proj.setProjStatusId("");
			}else if("FirstAudit".equals(proj.getProjStatusId())){
				proj.setProjStatusId(ProjScheduleStatusEnum.FirstAudit.getId());
				proj.setProjStageId("");//所有阶段报告为审核通过的申报书
			}else {
				paramMap.put("status1", ProjScheduleStatusEnum.Submit.getId());
				paramMap.put("status2", ProjScheduleStatusEnum.SecondBack.getId());
				paramMap.put("status3", ProjScheduleStatusEnum.ThirdBack.getId());
			}
		}

		paramMap.put("orgFlow", currentOrgFlow);
		paramMap.put("proj", proj);
		paramMap.put("scope", GlobalConstant.USER_LIST_LOCAL);
		paramMap.put("chargeOrgFlow", InitConfig.getSysCfg("global_org_flow"));
		paramMap.put("recTypeId", recTypeId);
		if(StringUtil.isNotBlank(startYear)) {
			paramMap.put("startYear", startYear);
		}
		if(StringUtil.isNotBlank(endYear)) {
			paramMap.put("endYear", endYear);
		}
		return this.pubProjExtMapper.selectCompleteAduitProjList(paramMap);
		//扩展查询，针对不同recType项目通用
		//return commonBiz.searchProjList(proj, recTypeId);
	}

	@Override
	public List<PubProj> searchChargeProj(PubProj proj,String recTypeId) {
		SysUser currentUser = GlobalContext.getCurrentUser();
		proj.setRecordStatus(GlobalConstant.FLAG_Y);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status1", ProjCompleteStatusEnum.FirstAudit.getId());
		paramMap.put("status2", ProjCompleteStatusEnum.ThirdBack.getId());
		paramMap.put("proj", proj);
		paramMap.put("scope", GlobalConstant.USER_LIST_CHARGE);
		paramMap.put("chargeOrgFlow", currentUser.getOrgFlow());
		paramMap.put("recTypeId", recTypeId);
		return this.pubProjExtMapper.selectCompleteAduitProjList(paramMap);
		//扩展查询，针对不同recType项目通用
		//return commonBiz.searchProjList(proj, recTypeId);
	}

	@Override
	public List<PubProj> searchGlobalProj(PubProj proj,String recTypeId) {
		proj.setRecordStatus(GlobalConstant.FLAG_Y);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status1", ProjCompleteStatusEnum.SecondAudit.getId());
		paramMap.put("status2", ProjCompleteStatusEnum.FirstAudit.getId());
		paramMap.put("proj", proj);
		paramMap.put("scope", GlobalConstant.USER_LIST_GLOBAL);
		paramMap.put("chargeOrgFlow", InitConfig.getSysCfg("global_org_flow"));
		paramMap.put("recTypeId", recTypeId);
		return this.pubProjExtMapper.selectCompleteAduitProjList(paramMap);
		//扩展查询，针对不同recType项目通用
		//return commonBiz.searchProjList(proj, recTypeId);
	}
	
	@Override
	public void completeAudit(String projFlow, String recTypeId, String projListScope,
			String agreeFlag, String auditContent) {
		String _statusId = "";
		String _statusName = "";
		String _remark = "";
		
		if(GlobalConstant.PROJ_STATUS_SCOPE_LOCAL.equals(projListScope)){
			_statusId = ProjCompleteStatusEnum.FirstAudit.getId();
			_statusName = ProjCompleteStatusEnum.FirstAudit.getName();
			_remark = ProcessRemarkEnum.FirstAuditAgree.getName();
		}
		else if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(projListScope)){
			_statusId = ProjCompleteStatusEnum.SecondAudit.getId();
			_statusName = ProjCompleteStatusEnum.SecondAudit.getName();
			 _remark = ProcessRemarkEnum.SecondAuditAgree.getName();
		}else if(GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL.equals(projListScope)){
			_statusId =  ProjCompleteStatusEnum.ThirdAudit.getId();
			_statusName =InitConfig.getSysCfg("top_org_level") + ProjCompleteStatusEnum.ThirdAudit.getName();
			_remark =InitConfig.getSysCfg("top_org_level") + ProcessRemarkEnum.ThirdAuditAgree.getName();
		}
		//是否退回填写状态
		if(GlobalConstant.FLAG_N.equals(agreeFlag)){
			_statusId = ProjCompleteStatusEnum.Apply.getId();
			_statusName = ProjCompleteStatusEnum.Apply.getName();
			if(GlobalConstant.PROJ_STATUS_SCOPE_LOCAL.equals(projListScope)){
				_remark = ProcessRemarkEnum.FirstAuditNotAgree.getName();
			}else if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(projListScope)){
				_remark = ProcessRemarkEnum.SecondAuditNotAgree.getName();
				_statusId = ProjCompleteStatusEnum.SecondBack.getId();
				_statusName = ProjCompleteStatusEnum.SecondBack.getName();
			}else if(GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL.equals(projListScope)){
				_remark =InitConfig.getSysCfg("top_org_level") + ProcessRemarkEnum.ThirdAuditNotAgree.getName();
				_statusId = ProjCompleteStatusEnum.ThirdBack.getId();
				_statusName =InitConfig.getSysCfg("top_org_level") + ProjCompleteStatusEnum.ThirdBack.getName();
			}
		}
		
		PubProjRec rec = this.projRecBiz.selectProjRecByProjFlowAndRecType(projFlow, recTypeId);
		
		if(rec!=null){
			PubProj proj = this.projBiz.readProject(rec.getProjFlow());
		    rec.setProjStatusId(_statusId);
		    rec.setProjStatusName(_statusName);
		    this.projProcessBiz.addProcess(rec,_remark,auditContent);
		    
		    String backApplyUser = InitConfig.getSysCfg("srm_back_apply_user");
			if(GlobalConstant.FLAG_Y.equals(backApplyUser) && GlobalConstant.FLAG_N.equals(agreeFlag)){
				//直接退回到项目负责人
				_statusId =  ProjApplyStatusEnum.Apply.getId();
				_statusName = ProjApplyStatusEnum.Apply.getName();
			}
			
			rec.setProjStatusId(_statusId);
			rec.setProjStatusName(_statusName);
			GeneralMethod.setRecordInfo(rec, false);
			this.projRecBiz.modProjRec(rec);
		    
		    //回写项目阶段和状态
			proj.setProjStatusId(_statusId);
			proj.setProjStatusName(_statusName);
			GeneralMethod.setRecordInfo(proj, false);
			this.projBiz.modProject(proj);
		}
		
	}
	
}
