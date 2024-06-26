package com.pinde.sci.biz.fstu;

import com.pinde.sci.model.mo.FstuSatAuthor;
import com.pinde.sci.model.mo.SrmAchAppraisalAuthor;
import com.pinde.sci.model.mo.SrmAchSatAuthor;

import java.util.List;

public interface IFstuSatAuthorBiz {
	
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
	List<FstuSatAuthor> searchAuthorList(FstuSatAuthor author);
	/**
	 * 删除一科技作者
	 */
	void deleteOneAuthor(FstuSatAuthor satAuthor);
}
