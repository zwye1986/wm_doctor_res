package com.pinde.sci.biz.sch;

import com.pinde.sci.model.mo.SchDoctorDept;

import java.util.List;
import java.util.Map;


public interface ISchDoctorDeptBiz {
//	List<SchDoctorDept> searchSchDoctorDept();
	
	List<SchDoctorDept> searchSchDoctorDept(String doctorFlow);
	
	SchDoctorDept readSchDoctorDept(String recordFlow);
	
	int saveSchDoctorDept(SchDoctorDept doctorDept);
	
	int save(SchDoctorDept doctorDept);

	int update(SchDoctorDept doctorDept);

	List<SchDoctorDept> searchDoctorDeptByDoctorFlows(List<String> doctorFlows);

	int editDoctorDept(SchDoctorDept doctorDept);

	SchDoctorDept readSchDoctorDeptByObj(String doctorFlow, String schDeptFlow,
			String groupFlow, String standardDeptId);

	int countRotationUse(String rotationFlow);

	/**
	 * 清理多余的选科数据
	 * @return
	 */
	int cleanInvalidSelDept(Map<String,Object> paramMap);

	List<SchDoctorDept> searchSchDoctorDeptIgnoreStatus(String doctorFlow,String orgFlow);

	/**
	 * 批量编辑
	 * @param doctorDeptList
	 * @return
	 */
	int editDoctorDeptList(List<SchDoctorDept> doctorDeptList);

	/**
	 * 计算是否有调整数据
	 * @param doctorFlow
	 * @param rotationFlow
	 * @return
	 */
	int countSchDoctorDeptIgnoreStatus(String doctorFlow, String rotationFlow,String orgFlow);

	/**
	 * 查询缩减数据
	 * @param doctorFlow
	 * @param rotationFlow
	 * @return
	 */
//	List<SchDoctorDept> searchDoctorDeptForReduction(String doctorFlow, String rotationFlow);
	
	/**
	 * 查询缩减数据
	 * @param doctorFlow
	 * @param rotationFlow
	 * @return
	 */
	List<SchDoctorDept> searchDoctorDeptForReductionIgnoreStatus(String doctorFlow, String rotationFlow);

	/**
	 * 查询该机构所需做缩减维护的数量
	 * @param orgFlow
	 * @return
	 */
	int countReducation(String orgFlow);
	/**
	 * 江苏中医查询该机构所需做缩减维护的数量
	 * @param orgFlow
	 * @return
	 */
	int jszyCountReducation(String orgFlow);


}
