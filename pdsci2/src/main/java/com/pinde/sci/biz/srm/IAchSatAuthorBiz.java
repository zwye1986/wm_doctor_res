package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.SrmAchSatAuthor;

import java.util.List;

public interface IAchSatAuthorBiz {
	
	/**
	 * 删除一科技作者
	 * @param achSatAuthor
	 */
	int editSatAuthor(SrmAchSatAuthor achSatAuthor);
	
	/**
	 * 查询作者
	 * @param author
	 * @return
	 */
	List<SrmAchSatAuthor> searchAuthorList(SrmAchSatAuthor author);
}
