package com.pinde.sci.model.srm;

import com.pinde.sci.model.mo.PubProj;
import com.pinde.sci.model.mo.SrmExpertProjEval;
import com.pinde.sci.model.mo.SrmProjFundInfo;
import com.pinde.sci.model.mo.SysUser;

import java.util.List;

public class PubProjExt extends PubProj {
	/**
	 * 
	 */
	private static final long serialVersionUID = -418342148935797130L;

	private String recTypeId;

	private String startYear;

	private String endYear;

	private SrmExpertProjEval srmExpertGroupProj;
	
	private SrmProjFundInfo projFundInfo;
	
	private String projCateScope;
	
	private List<String> statusids;

	private List<String> projStageIds;

	private String keyword;
	
	private SysUser user;

	public List<String> getProjStageIds() {
		return projStageIds;
	}

	public void setProjStageIds(List<String> projStageIds) {
		this.projStageIds = projStageIds;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public String getRecTypeId() {
		return recTypeId;
	}

	public void setRecTypeId(String recTypeId) {
		this.recTypeId = recTypeId;
	}

	public SrmExpertProjEval getSrmExpertGroupProj() {
		return srmExpertGroupProj;
	}

	public void setSrmExpertGroupProj(SrmExpertProjEval srmExpertGroupProj) {
		this.srmExpertGroupProj = srmExpertGroupProj;
	}

	public SrmProjFundInfo getProjFundInfo() {
		return projFundInfo;
	}

	public void setProjFundInfo(SrmProjFundInfo projFundInfo) {
		this.projFundInfo = projFundInfo;
	}

	public List<String> getStatusids() {
		return statusids;
	}

	public void setStatusids(List<String> statusids) {
		this.statusids = statusids;
	}

	public String getProjCateScope() {
		return projCateScope;
	}

	public void setProjCateScope(String projCateScope) {
		this.projCateScope = projCateScope;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


}
