package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.PubProj;

import java.util.List;

/**
 * 项目申请阶段业务层
 * 
 * @author shenzhen
 * 
 */
public interface IProjApplyBiz {
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
     * 申报审核
     * @param projFlow
     * @param projListScope
     * @param agreeFlag
     * @param auditContent
     * @param acceptNumber 受理编号
     */

	public void applyAudit(String projFlow , String projListScope,String  agreeFlag , String auditContent,String acceptNumber);
	
}
