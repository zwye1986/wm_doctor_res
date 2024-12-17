package com.pinde.sci.biz.sch;

import com.pinde.core.model.SchRotationDept;
import com.pinde.core.model.SchRotationGroup;
import com.pinde.core.model.SchRotationGroupExample;

import java.util.List;
import java.util.Map;


public interface ISchRotationGroupBiz {
//	List<SchRotationGroup> searchSchRotationGroup();
	
	List<SchRotationGroup> searchSchRotationGroupOrgNull();
	
	List<SchRotationGroup> searchSchRotationGroup(String rotationFlow);

	List<SchRotationGroup> searchSchRotationGroupByItems(Map<String,String> paramMap);
	
	SchRotationGroup readSchRotationGroup(String groupFlow);
	

	int saveSchRotationGroup(SchRotationGroup rotationGroup);

	List<SchRotationDept> readSchRotationDept(String groupFlow);

//	List<SchRotationGroup> searchSchRotationGroupByorg(String orgFlow);

	List<SchRotationGroup> searchGroupByRotations(List<String> rotationFlows);

	int sumDeptNumByRotation(String rotationFlow);

	List<SchRotationGroup> searchOrgGroupByRotations(
			List<String> rotationFlows, String orgFlow);

	/**
	 * 根据组合flow集合获取组合列表
	 * @param groupFlows
	 * @return
	 */
	List<SchRotationGroup> searchGroupByGroupFlows(List<String> groupFlows);

	/**
	 * 查询机构组合科室或必轮或全部
	 * @param rotationFlow
	 * @param orgFlow
	 * @param isRequired
	 * @return
	 */
	List<SchRotationGroup> searchOrgGroupOrAll(String rotationFlow,
			String orgFlow, String isRequired);

	/**
	 * 根据标准组合获取本地组合
	 * @param standardGroupFlow
	 * @return
	 */
	SchRotationGroup readGroupByStandard(String orgFlow,String standardGroupFlow);

	List<SchRotationGroup> readSchRotationGroupByExample(SchRotationGroupExample example);

	List<SchRotationGroup> searchAllSchRotationGroup();

	List<SchRotationGroup> searchOrgGroupBySessionNumber(String rotationFlow, String orgFlow, String sessionNumber);
}
