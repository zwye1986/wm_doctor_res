package com.pinde.res.dao.jswjw.ext;

import com.pinde.core.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysUserExtMapper {
	
	List<SysUser> searchUserList(Map<String,Object> paramMap);

	List<SysUser> teacherRoleCheckUser(Map<String, Object> map);

	List<Map<String,String>> teacherRoleCheckSpeUser(Map<String, Object> map);

	List<Map<String,String>> deptUsers(Map<String, String> param);

	List<SysUser> readDeptTeachAndHead(@Param("deptFlow") String deptFlow, @Param("teacherRoleFlow") String teacherRoleFlow, @Param("headRoleFlow") String headRoleFlow);

	/**
	 * @Department：研发部
	 * @Description 查询用户基本信息
	 * @Author fengxf
	 * @Date 2022/3/3
	 */
	SysUser getUserBaseInfo(@Param("userFlow") String userFlow);
}
