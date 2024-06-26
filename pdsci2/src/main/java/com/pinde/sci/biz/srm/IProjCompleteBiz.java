package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.PubProj;

import java.util.List;

public interface IProjCompleteBiz {

	/**
	 * 查询承担单位项目列表
	 * 
	 * @param proj
	 * @param currUser
	 */
	public List<PubProj> searchLocalProj(PubProj proj, String recTypeId,String startYear,String endYear);

	/**
	 * 查询主管单位项目列表
	 * 
	 * @param proj
	 * @param currUser
	 */
	public List<PubProj> searchChargeProj(PubProj proj, String recTypeId);

	/**
	 * 查询所有项目列表
	 * 
	 * @param proj
	 * @param currUser
	 */
	public List<PubProj> searchGlobalProj(PubProj proj, String recTypeId);
	
	/**
	 * 完成阶段的审核
	 * @param recFlow
	 * @param projListScope
	 * @param agreeFlag
	 * @param auditContent
	 */
	public void completeAudit(String projFlow, String recTypeId, String projListScope, String agreeFlag,
			String auditContent);
//	
//	public PubProj readProject(String projectFlow);
//
//	/**
//	 * 查询
//	 * @param proj
//	 * @return
//	 */
//	public List<PubProj> searchProj(PubProj proj);

	
	
//	/**
//	 * 主管部门查询
//	 * @param proj
//	 * @param orgFlow
//	 * @return
//	 */
//	public List<PubProj> searchChargeProjList(PubProj proj , String orgFlow);
	
	


//	/**
//	 * 查询当前登录用户权限范围项目 
//	 * @param proj
//	 * @param currUser
//	 * @return
//	 */
//	public List<PubProj> searchPersonalProj(PubProj proj); 
	
//	/**
//	 * 更新项目信息的重载
//	 * @param proj
//	 * @param currUser
//	 */
//	public void modProject(PubProj proj);
	
//	public void changeProjStatusForAudit(PubProj proj, String projListScope,String isNotAgree);

} 
 