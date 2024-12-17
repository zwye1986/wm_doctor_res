package com.pinde.sci.dao.sys;

import com.pinde.core.model.SysDept;
import com.pinde.sci.model.jsres.LzDeptItem;
import com.pinde.core.model.SchAndStandardDeptCfg;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysDeptExtMapper {
	/**
	 * 根据rec和process的情况获取部门
	 * @param map
	 * @return
	 */
	List<SysDept> getDeptByRec(Map<String,Object> map);
	List<SysDept> getDeptByRecForUni(Map<String,Object> map);

	List<Map<String,String>> searchDeptByUnion(@Param("dept") SysDept dept, @Param("isUnion") String isUnion);

	List<Map<String,String>> searchDeptByUnionSpe(@Param("dept") SysDept dept, @Param("isUnion") String isUnion);

	//查询科主任所属科室
	List<Map<String,Object>> queryDeptListByFlow(@Param("userFlow") String userFlow);

	List<Map<String,String>> searchDeptByExt(Map<String, Object> paramMap);

	List<Map<String, Object>> searchDeptByDoctor(@Param("userFlow") String userFlow, @Param("orgFlow") String orgFlow);

	List<SysDept> searchDeptBySpe(@Param("resTrainingSpeId") String resTrainingSpeId, @Param("orgFlow") String orgFlow);

	/**
	 * 获取本机构部门及对本机构公开的部门
	 * @param paramMap
	 * @return
	 */
	List<SysDept> getBaseAndExtDept(Map<String,Object> paramMap);

	String selectDeptByParam(@Param("orgFlow") String orgFlow, @Param("deptName") String deptName);

	List<String> selectDeptByParamList(@Param("orgFlow") String orgFlow, @Param("deptName") String deptName);

	/**
	 * ~~~~~~~~~溺水的鱼~~~~~~~~
	 * @Author: 吴强
	 * @Date: 2024/9/20 16:24
	 * @Description: 根据标准科室code集合查询轮转科室,在基地管理中设置其对应关系
	 */
	List<SysDept> getSchDeptByBzDeptCode(@Param("rotationFlow")String rotationFlow,
										 @Param("orgFlow")String orgFlow);
	/**
	 * 根据标准科室查询轮转科室
	 * */
	List<SchAndStandardDeptCfg> getSchDeptByBzIds(@Param("bzDeptIds")List<String> bzDeptIds,
												  @Param("deptFlows")List<String> deptFlows,
												  @Param("orgFlow")String orgFlow);
	List<String> relToMeOrgFlow(@Param("myOrgFlow") String myOrgFlow);

	/**
	 * ~~~~~~~~~溺水的鱼~~~~~~~~
	 * @Author: 吴强
	 * @Date: 2024/9/28 13:45
	 * @Description: 获取可排班的轮转科室列表，包含协同单位
	 * @Param: orgFlow:主机构id，默认当前登陆人的机构
	 */
	List<LzDeptItem> deptSelectData(@Param("orgFlow")String orgFlow);

	List<SchAndStandardDeptCfg> getBzDeptListByLzDeptFlows(@Param("deptFlowList")List<String> deptFlowList);


	SchAndStandardDeptCfg getBzDeptByDeptFlow(@Param("deptFlow") String deptFlow);

	List<String> deptFlowsByUserId(@Param("userFlow")String userFlow);

}


