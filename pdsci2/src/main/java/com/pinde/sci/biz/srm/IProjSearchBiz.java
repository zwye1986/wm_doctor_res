package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.PubProj;
import com.pinde.sci.model.mo.SysOrg;

import java.util.List;

public interface IProjSearchBiz {
     
	public List<PubProj> searchProj(PubProj proj,List<SysOrg> orgList);
	
	public List<PubProj> searchProj(PubProj proj);

	public List<PubProj> searchProj(PubProj proj,List<String> stageIdNotInList,List<String> statusIdInList,String startYear,String endYear);

    public List<PubProj> searchProjWithBLOBs(PubProj proj);

	public List<PubProj> searchProjByprojListScope(PubProj proj,String projListScope);

	/**
	 * 查询非APPLY状态的立项
	 * @param pubProj
     * @return
     */
	List<PubProj> searchPassApplyProj(PubProj pubProj,String startYear,String endYear);

}
