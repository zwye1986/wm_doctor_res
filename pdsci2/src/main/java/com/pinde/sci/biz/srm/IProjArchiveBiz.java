package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.PubProj;
import com.pinde.sci.model.mo.PubProjProcess;
import com.pinde.sci.model.srm.PubProjExt;

import java.util.List;

public interface IProjArchiveBiz {
    
	/**
	 * 查询符合条件的项目
	 * @param proj
	 * @return
	 */
	public List<PubProj> searchProj(PubProjExt proj);
	/**
	 * 保存归档结果
	 * @param project
	 * @param process
	 */
	public void saveArchiveResult(PubProj project,PubProjProcess process);
}
