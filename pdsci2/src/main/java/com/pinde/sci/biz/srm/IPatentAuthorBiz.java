package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.SrmAchPatentAuthor;

import java.util.List;

public interface IPatentAuthorBiz {   
    /**
     * 修改专利作者信息
     * @param achPatentAuthor
     * @return
     */
    public int editAuthor(SrmAchPatentAuthor author);
	
	/**
	 * 查询作者
	 * @param srmAchPatentAuthor
	 * @return
	 */
	List<SrmAchPatentAuthor> searchAuthorList(SrmAchPatentAuthor author);
}
