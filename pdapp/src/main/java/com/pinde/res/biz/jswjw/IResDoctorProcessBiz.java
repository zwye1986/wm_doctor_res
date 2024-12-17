package com.pinde.res.biz.jswjw;

import com.pinde.core.model.ResDoctorSchProcess;
import com.pinde.core.model.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @author zhangwenyun
 */
public interface IResDoctorProcessBiz {
	/**
	 * 保存或修改
	 * @param process
	 * @return
	 */
    int edit(ResDoctorSchProcess process, SysUser user);
	/**
	 * 根据主键查找
	 * @param processFlow
	 * @return
	 */
	ResDoctorSchProcess read(String processFlow);
	List<Map<String,String>> schDoctorSchProcessQuery(Map<String, String> map);

	/**
	 * 按部门统计学员
	 * @param isCurrentFlag
	 * @param userFlow
	 * @param y
	 * @return
	 */
	int schProcessStudentDistinctQuery(String deptFlow, String userFlow, String y);

	int checkProcessEval(String processFlow);

}
