package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.SrmFundScheme;
import com.pinde.sci.model.mo.SrmProjFundInfo;
import com.pinde.sci.model.mo.SrmProjSourceScheme;

import java.util.List;

public interface IFundSchemeBiz {
	/**
	 * 根据schemeFlow查读数据
	 * @param schemeFlow
	 * @return
	 */
	public SrmFundScheme readFundScheme(String schemeFlow);
	/**
	 * 据参数进行增加或者更新
	 * @param scheme
	 * @return
	 */
	public int saveFundScheme(SrmFundScheme scheme);
	/**
	 * 查看所有方案
	 * @param scheme
	 * @return
	 */
	public List<SrmFundScheme> searchFundScheme(SrmFundScheme scheme);

	public List<SrmFundScheme> searchFundSchemeByFlows(SrmFundScheme scheme,List<String> schemeFlows);
	/**
	 * 查询一个项目来源下是否已经有启动的方案
	 * @param scheme
	 * @return
	 */
	public List<SrmFundScheme> searchStartScheme(String projTypeId);
	/**
	 * 删除方案信息
	 * @param scheme
	 * @return
	 */
	public int deleteFundScheme(SrmFundScheme scheme);
	/**
	 * 查询申请了预算的项目信息
	 * @param projFundInfo
	 * @return
	 */
	public List<SrmProjFundInfo> searchProjFundInfo(SrmProjFundInfo projFundInfo);

	List<SrmProjSourceScheme> searchSourceScheme(SrmProjSourceScheme sourceScheme);

	int saveSourceScheme(List<SrmProjSourceScheme> sourceSchemeList,String schemeFlow);
}
