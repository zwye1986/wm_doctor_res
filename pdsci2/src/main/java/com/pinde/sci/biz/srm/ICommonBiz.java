package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.PubProj;

import java.util.List;

public interface ICommonBiz {

	
	/**
	 * 查询项目列表通用方法 现要求能满足但是需求改变的话会有问题
	 * @param proj
	 * @param recTypeId
	 * @return
	 */
	public List<PubProj> searchProjList(PubProj proj, String recTypeId);
	
}
