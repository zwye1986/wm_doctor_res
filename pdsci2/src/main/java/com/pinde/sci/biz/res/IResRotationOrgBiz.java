package com.pinde.sci.biz.res;

import com.pinde.sci.model.mo.ResRotationOrg;

import java.util.List;

public interface IResRotationOrgBiz {
	/**
	 * 编辑
	 * @param rotationOrg
	 * @return
	 */
	int save(ResRotationOrg rotationOrg);

	/**
	 *	根据主键recordFlow查询单条数据
	 * @param recordFlow
	 * @return
	 */
	ResRotationOrg searchByScoreFlow(String recordFlow);

	/**
	 * rotationFlow查询多条数据
	 * @param rotationFlow
	 * @return
	 */
	List<ResRotationOrg> searchByRotationFlow(String rotationFlow);

	List<ResRotationOrg> searchByRotationFlowY(String rotationFlow);

	/**
	 * rotationFlow和orgFlow查询单条数据
	 * @param rotationFlow
	 * @param orgFlow
	 * @return
	 */
	List<ResRotationOrg> searchByRotationFlowOrg(String rotationFlow,String orgFlow);
	
	List<ResRotationOrg> ResRotationOrgAll();
}
