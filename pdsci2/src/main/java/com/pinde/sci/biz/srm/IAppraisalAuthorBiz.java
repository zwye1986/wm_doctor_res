package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.SrmAchAppraisalAuthor;

import java.util.List;

public interface IAppraisalAuthorBiz {
	
	/**
	 * 删除鉴定作者
	 * @param achAppraisalAuthor
	 */
	void editAppraisalAuthor(SrmAchAppraisalAuthor achAppraisalAuthor);
	
	/**
	 * 查询作者
	 * @param srmAchAppraisalAuthor
	 * @return
	 */
	List<SrmAchAppraisalAuthor> searchAuthorList(SrmAchAppraisalAuthor author);

    List<String> getAppraisalFlowByAuthor(SrmAchAppraisalAuthor author);
}
