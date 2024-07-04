package com.pinde.res.biz.jswjw;

import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorSchProcess;
import com.pinde.sci.model.mo.SysUser;

import java.util.List;
import java.util.Map;

public interface IResDoctorProcessBiz {
	/**
	 * 保存或修改
	 * @param process
	 * @return
	 */
	int edit(ResDoctorSchProcess process,SysUser user);
	/**
	 * 根据主键查找
	 * @param processFlow
	 * @return
	 */
	ResDoctorSchProcess read(String processFlow);

	List<ResDoctorSchProcess> searchProcessByDoctor(String doctorFlow);
	List<ResDoctorSchProcess> searchProcessByDoctor(ResDoctor doctor,
													ResDoctorSchProcess process, Map<String, String> roleFlagMap);
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
