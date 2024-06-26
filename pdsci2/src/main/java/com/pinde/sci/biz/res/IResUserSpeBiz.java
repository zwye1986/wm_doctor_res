package com.pinde.sci.biz.res;

import com.pinde.sci.model.mo.ResUserSpe;

import java.util.List;

public interface IResUserSpeBiz {

	/**
	 * 读取一条人员与专业关系
	 * @param recordFlow
	 * @return
	 */
//	ResUserSpe readUserSpe(String recordFlow);

	/**
	 * 更具用户查询该用户的所有关联专业
	 * @param userFlow
	 * @return
	 */
	List<ResUserSpe> searchUserSpesByUser(String userFlow);

	/**
	 * 编辑一条关系
	 * @param userSpe
	 * @return
	 */
	int editUserSpe(ResUserSpe userSpe);

	/**
	 * 查询所有关系,不过滤禁用数据
	 * @param userFlow
	 * @return
	 */
//	List<ResUserSpe> searchAllUserSpesByUser(String userFlow);

	/**
	 * 根据专业和用户关联关系
	 * @param userFlow
	 * @param speIds
	 * @return
	 */
	int editUserSpesBySpes(String userFlow, List<String> speIds);

	/**
	 * 读取用户集合内的关系
	 * @param userFlows
	 * @return
	 */
	List<ResUserSpe> searchAllUserSpes(List<String> userFlows);


}
