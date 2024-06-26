package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.*;

import java.util.List;

public interface IAchBookBiz {
	/**
	 * 根据bookFlow显示数据
	 * @param bookFlow
	 * @return
	 */
	public SrmAchBook readBook(String bookFlow);

	/**
	 * 加载
	 * @param book
	 * @param bookFlowList
	 * @param childOrgList 审核加载子机构
	 * @return
	 */
	public List<SrmAchBook> search(SrmAchBook book, List<SysOrg> childOrgList,List<String> bookFlows);
	
	/**
	 * 保存
	 * @param book
	 * @param authorList
	 * @param srmAchFile
	 * @param srmAchProcess
	 */
	public void save(SrmAchBook book, List<SrmAchBookAuthor> authorList, SrmAchFile file, SrmAchProcess process);
	public int save(SrmAchBook book);
    
	/**
	 * 修改状态
	 * @param book
	 * @param process
	 */
	public void updateBookStatus(SrmAchBook book,SrmAchProcess process);
	
	/**
	 * 修改著作及作者
	 * @param book
	 * @param authorList
	 * @return
	 */
	int edit(SrmAchBook book, List<SrmAchBookAuthor> authorList, SrmAchFile file);

	SrmAchBook bookIsExist(String isbnCode);
}
