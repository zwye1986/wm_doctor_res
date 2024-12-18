package com.pinde.sci.dao.sch;


import com.pinde.core.model.SchRotationDept;
import com.pinde.core.model.SchRotationDeptExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * proj扩展mapper
 * @author Administrator
 *
 */
public interface SchRotationDeptExtMapper {
	/**
	 * 计算未关联的科室数量
	 * @param orgFlow
	 * @param rotationFlow
	 * @return
	 */
	int getUnrelCount(@Param(value="orgFlow")String orgFlow,@Param(value="rotationFlow")String rotationFlow);

	/**
	 * 计算该轮转科室的使用情况
	 * @param schDeptFlow
	 * @param deptFlow
	 * @return
	 */
	int getSchInUsedCount(@Param(value="schDeptFlow")String schDeptFlow,@Param(value="deptFlow")String deptFlow);

	/**
	 * 已经同步该方案的机构
	 * @param rotationFlow
	 * @return
	 */
	List<String> isUpdateOrg(@Param(value="rotationFlow")String rotationFlow);

	/**
	 * 为各机构更新是否必轮状态
	 * @param isRequired
	 * @return
	 */
	int setRotationDeptRequired(@Param(value="isRequired")String isRequired,@Param(value="standardGroupFlow")String standardGroupFlow);

	/**
	 * 计算方案内时间必轮
	 * @param orgFlow
	 * @return
	 */
	List<Map<String,Object>> getRotationMustSum(@Param(value="orgFlow")String orgFlow);

	/**
	 * 计算方案内时间选科
	 * @param orgFlow
	 * @return
	 */
	List<Map<String,Object>> getRotationGroupSum(@Param(value="orgFlow")String orgFlow);

	/**
	 * 获取当前这个机构标准规则下的轮转科室
	 * @param standardDeptRecFlow
	 * @return
	 */
	List<SchRotationDept> searchRotationDeptByStanard(@Param(value="standardDeptRecFlow")String standardDeptRecFlow,@Param(value="orgFlow")String orgFlow);

	List<SchRotationDept> doctorGetNotSchDept(@Param("rotationFlow") String rotationFlow, @Param("doctorFlow") String doctorFlow);

	List<Map<String,String>> readStandardRotationDeptByresultFlows(@Param("resultFlows") List<String> resultFlows);

	List<SchRotationDept> selectByExampleTwo(SchRotationDeptExample example);
} 
