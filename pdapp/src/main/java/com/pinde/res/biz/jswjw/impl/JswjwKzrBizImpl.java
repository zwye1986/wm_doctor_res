package com.pinde.res.biz.jswjw.impl;


import com.pinde.res.biz.jswjw.IJswjwKzrBiz;
import com.pinde.res.dao.jswjw.ext.ResDoctorSchProcessExtMapper;
import com.pinde.res.dao.jswjw.ext.SysUserExtMapper;
import com.pinde.sci.dao.base.ResScoreMapper;
import com.pinde.core.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class JswjwKzrBizImpl implements IJswjwKzrBiz {

	@Autowired
	private ResDoctorSchProcessExtMapper resDoctorProcessExtMapper;
	@Autowired
	private ResScoreMapper scoreMapper;
	@Autowired
	private SysUserExtMapper userExtMapper;
	@Override
	public List<Map<String, String>> schDoctorSchProcessHead(Map<String, String> schArrangeResultMap) {
		return resDoctorProcessExtMapper.schDoctorSchProcessHead(schArrangeResultMap);
	}

	@Override
	public List<Map<String, String>> deptTeacherUsers(Map<String, String> schArrangeResultMap) {
		return resDoctorProcessExtMapper.deptTeacherUsers(schArrangeResultMap);
	}

	@Override
	public List<Map<String, String>> deptTeacherDocList(Map<String, String> schArrangeResultMap) {
		return resDoctorProcessExtMapper.deptTeacherDocList(schArrangeResultMap);
	}

	@Override
	public List<Map<String, String>> attendList(Map<String, String> schArrangeResultMap) {
		return resDoctorProcessExtMapper.attendList(schArrangeResultMap);
	}

	@Override
	public int deptUsersCount(Map<String, String> param) {
		return resDoctorProcessExtMapper.deptUsersCount(param);
	}

	@Override
	public List<Map<String, String>> afterUserList(Map<String, String> schArrangeResultMap) {
		return resDoctorProcessExtMapper.afterUserList(schArrangeResultMap);
	}
	@Override
	public List<Map<String, String>> temporaryOutAuditList(Map<String, String> schArrangeResultMap) {
		return resDoctorProcessExtMapper.temporaryOutAuditList(schArrangeResultMap);
	}
	@Override
	public List<Map<String, String>> temporaryOutList(Map<String, String> schArrangeResultMap) {
		return resDoctorProcessExtMapper.temporaryOutList(schArrangeResultMap);
	}
	@Override
	public List<Map<String, String>> afterAuditList(Map<String, String> schArrangeResultMap) {
		return resDoctorProcessExtMapper.afterAuditList(schArrangeResultMap);
	}

	@Override
	public int deptUsersDocCount(Map<String, String> param) {
		return resDoctorProcessExtMapper.deptUsersDocCount(param);
	}

	@Override
	public List<SysUser> readDeptTeachAndHead(String deptFlow, String teacherRoleFlow, String headRoleFlow) {
		return userExtMapper.readDeptTeachAndHead(deptFlow,teacherRoleFlow,headRoleFlow);
	}

	@Override
	public int schDoctorSchProcessHeadCount(Map<String, String> param) {
		return resDoctorProcessExtMapper.jsresHeadCount(param);
	}

	@Override
	public List<Map<String, String>> schDoctorSchProcessHeadUserList(Map<String, String> schArrangeResultMap) {

		return resDoctorProcessExtMapper.jsresHeadUserList(schArrangeResultMap);
	}

	@Override
	public List<SysUser> teacherRoleCheckUser(String deptFlow, String role,String userName, String userFlow) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("sysDeptFlow", deptFlow);
		map.put("roleFlow", role);
		map.put("userName", userName);
		map.put("userFlow", userFlow);
		List<SysUser> sysUserList=userExtMapper.teacherRoleCheckUser(map);
		return sysUserList;
	}

	@Override
	public List<Map<String, String>> deptUsers(Map<String, String> param) {
		return userExtMapper.deptUsers(param);
	}
}
  