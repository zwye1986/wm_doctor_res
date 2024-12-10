package com.pinde.sci.dao.res;

import com.pinde.core.model.SysDept;
import com.pinde.core.model.SysUser;
import com.pinde.core.model.SysUserDept;
import com.pinde.sci.model.hbres.teacherRec;
import com.pinde.sci.model.mo.DeptTeacherGradeInfo;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.res.DeptTeacherGradeInfoExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2017-04-24.
 */
public interface DeptTeacherGradeInfoExtMapper {

	/**
	 * 根据老师查所有学员老师评价表
	 * @param map
	 * @return
	 */
	List<DeptTeacherGradeInfo> searchProssFlowRecRec(Map<String, Object> map);

	/**
	 * 根据老师查所有学员老师评价表平均分
	 * @param map
	 * @return
	 */
	List<Map<String,String>> searchGradeAvgMaps(Map<String, Object> map);

	/**
	 * 根据科室查所有学员对科室的评价
	 * @param map
	 * @return
	 */
	List<DeptTeacherGradeInfo> searchDeptFlowRec(Map<String, Object> map);

	List<Map<String, String>> processRecRecTeacherMap(Map<String, String> map);
	List<Map<String, String>> processJsresRecRecTeacherMap(Map<String, String> map);

	List<Map<String, String>> processRecShMap(Map<String, String> map);

	List<Map<String, String>> processJsresRecShMap(Map<String, String> map);
	/**
	 * 查询评分的content
	 * @param paramMap
	 * @return
	 */
	List<Map<String,String>> getRecContentByProcess(Map<String,Object> paramMap);
	/**
	 * 查询评分的content
	 * @param paramMap
	 * @return
	 */
	List<Map<String,String>> getJsresRecContentByProcess(Map<String,Object> paramMap);

	List<Map<String,String>> getJsresRecContentByProcess2(Map<String,Object> paramMap);
	/**
	 * 根据rec和process的情况获取用户
	 * @param map
	 * @return
	 */
	List<SysUser> getUserByRec(Map<String,Object> map);

	/**
	 * 根据userFlow查询所有科室
	 * @param userFlow
	 * @return
     */
	List<SysUserDept> searDeptNames(@Param("userFlow")String userFlow);
	/**
	 * 根据rec和process的情况获取部门
	 * @param map
	 * @return
	 */
	List<SysDept> getDeptByRec(Map<String,Object> map);


    /**根据rec和process的情况获取机构
     * @param map
     * @return
     */
    List<SysOrg> getOrgByRec(Map<String,Object> map);

	List<DeptTeacherGradeInfoExt> searchScoreList(@Param("userFlow")String userFlow,
												  @Param("roleFlag")String roleFlag,
												  @Param("sessionNumber")String sessionNumber,
												  @Param("recTypeId")String recTypeId,
												  @Param("isCurrentFlag")String isCurrentFlag);

	List<DeptTeacherGradeInfo> resSearchProssFlowRecRec(Map<String, Object> map);

	List<DeptTeacherGradeInfo> resSearchDeptFlowRec(Map<String, Object> map);

	List<SysUser> getUserByRecForUni(Map<String, Object> paramMap);

	List<SysDept> getDeptByRecForUni(Map<String,Object> map);

	List<Map<String,String>> getRecContentByProcessForUni(Map<String,Object> paramMap);

	List<Map<String,String>> getHadEvaluateByProcesses(Map<String, Object> paramsMap);

	List<Map<String,String>> getJsresGradeAvgScoreByProcessSessionNumber(Map<String, Object> paramMap);

	List<Map<String,String>> getJsresGradeAvgScoreByProcess(Map<String, Object> paramMap);

	List<Map<String,Object>> getDeptDoctorEvalStatic(Map<String, Object> paramMap);

	List<DeptTeacherGradeInfo> getDeptDoctorEvalStaticDetail(Map<String, Object> paramMap);

	List<teacherRec> getUserByRecAndAvgScore(Map<String, Object> paramMap);

	List<Map<String, String>> getRecContentByProcess2(Map<String, Object> paramMap);

	List<teacherRec> getDeptByRecAndAvgScore(Map<String, Object> paramMap);

	List<teacherRec> getDoctorByRecAndAvgScore(Map<String, Object> paramMap);

	List<Map<String, String>> getRecContentByProcess3(Map<String, Object> paramMap);

    List<teacherRec> getUserByRecAndAvgScore2(Map<String, Object> paramMap);

	List<Map<String, String>> getTeachResult(Map<String, Object> paramMap);

	List<Map<String, String>> getHeadManageDoctorAssess(Map<String, Object> paramMap);

	List<Map<String, String>> getUserByManageScore(Map<String, Object> paramMap);

	List<Map<String, String>> getPatientResult(Map<String, Object> paramMap);

	List<Map<String, String>> patientEvaluate(Map<String, Object> paramMap);


}
