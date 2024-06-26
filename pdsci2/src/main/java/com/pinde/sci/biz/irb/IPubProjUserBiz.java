package com.pinde.sci.biz.irb;

import com.pinde.sci.model.mo.PubProjUser;
import com.pinde.sci.model.pub.PubProjUserExt;

import java.util.List;

public interface IPubProjUserBiz {
	/**
	 * 新增或修改
	 * @param user
	 * @return
	 */
	public int edit(PubProjUser user);
	/**
	 * 批量新增或修改
	 * @param users
	 */
	public void editUsers(List<PubProjUser> users);

	/**
	 * 查询扩展列表
	 * @param pubProjUserExt
	 * @return
	 */
	public List<PubProjUserExt> queryExtList(PubProjUserExt pubProjUserExt);
	void saveProjUserOrder(String[] recordFlow);
}
