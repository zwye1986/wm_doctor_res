package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.SrmAchBookAuthor;

import java.util.List;

public interface IAchBookAuthorBiz {

	/**
	 * 修改一条著作作者
	 * @param achBookAuthor
	 */
	void editBookAthor(SrmAchBookAuthor author);
	
	/**
	 * 查询作者
	 * @return
	 */
	List<SrmAchBookAuthor> searchAuthorList(SrmAchBookAuthor author);
}
