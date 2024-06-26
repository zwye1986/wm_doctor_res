package com.pinde.sci.biz.pub;

import com.pinde.sci.model.mo.PubTrainUser;
import com.pinde.sci.model.mo.SysDept;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPubTrainUserBiz {
	/**
	 * 查询同机构培训人员
	 * @param orgFlow
	 * @return
	 */
	List<SysUser> queryUserListFromSysUser(String orgFlow, String deptFlow);
	
	/**
	 * 查询培训人员
	 * @param
	 */
	List<PubTrainUser> queryTrainUserList(PubTrainUser trainUser);
	
	/**
	 * 保存或修改培训人员
	 * @param user
	 * @return
	 */
	int editUser(PubTrainUser user);
	/**
	 * 保存培训人员文件
	 * @param
	 * @param
	 */
	String saveFiles(List<MultipartFile> filesList, String[] recordFlows, String[] fileNos);
	
	/**
	 * 查询部门
	 * @return
	 */
	List<SysDept> queryIrbDept();
	/**
	 * 按条件查询培训人员记录数
	 * @param trainUser
	 * @return
	 */
	long queryTrainUserCount(PubTrainUser trainUser);


}
