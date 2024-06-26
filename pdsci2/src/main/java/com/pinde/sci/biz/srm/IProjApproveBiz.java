package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.*;

import java.util.List;

/**
 * 项目立项阶段的业务层
 * @author shenzhen
 *
 */
public interface IProjApproveBiz {

	/**
	 * 项目立项
	 * 1:更新项目阶段为立项阶段 ， 合同填写状态
	 * 2：process中新增一条记录
	 * @param proj 将立项要填写的一些项目信息更新到proj上
	 * @param remark 立项结果描述
	 * @param sug 立项意见
	 */
	void setUp(PubProj proj , String remark , String sug , String setUpXml);

	void setUp(PubProj proj , String remark , String sug , String setUpXml,SrmProjFundInfo fundInfo,List<PubProjAuthor> authorList);

	/**
	 * 查询
	 * 
	 * @param proj
	 * @return
	 */
	public List<PubProj> searchApproveProjList(PubProj proj,String startYear,String endYear);
	
	/**
	 * 查询下拨列表
	 * @param proj
	 * @return
	 */
	public List<PubProj> searchFundPlanList(PubProj proj);
	
	/**
	 * 根据项目拨款计划自身的属性查询项目拨款计划列表
	 * @param projFlow
	 * @return
	 */
	public List<PubProjFundPlan> searchProjFundPlanList(PubProjFundPlan projFindPlan);
	
	/**
	 * 下拨
	 * @param fundPlan
	 */
	public void addFundPlan(PubProj proj , List<PubProjFundPlan> fundPlans , String flag);
	
	/**
	 * 根据立项编号查询
	 * @param projNo
	 * @return
	 */
	List<PubProj> getPubProjByProjNo(String projNo);

	/**
	 * 添加立项
	 * @param proj
	 * @param projAuthor
	 * @param fundInfo
     * @param process
     */
	int addSetUp(PubProj proj , List<PubProjAuthor> projAuthorList,SrmProjFundInfo fundInfo,PubProjProcess process);

	String reAuditOption(String projFlow);
}
