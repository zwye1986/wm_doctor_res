package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.PubProj;

import java.util.List;

public interface IProjStatementBiz {
	
	/**
	 * 查找当前机构下属机构承担的项目
	 * @param proj
	 * @param orgList 
	 * @return
	 */
	public List<PubProj> searchPuisneProj(PubProj proj);  

}
