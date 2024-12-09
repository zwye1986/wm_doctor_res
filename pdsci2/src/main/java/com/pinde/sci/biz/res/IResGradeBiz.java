package com.pinde.sci.biz.res;

import com.pinde.core.model.SysUser;
import com.pinde.core.model.SysUserDept;
import com.pinde.sci.model.hbres.teacherRec;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.DeptTeacherGradeInfoExt;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author tiger
 *
 */
public interface IResGradeBiz {

	/**
	 * 根据老师查所有学员老师评价表
	 * @param teacherUserFlow
	 * @param typeList
	 * @return
	 */
	List<DeptTeacherGradeInfo> searchProssFlowRec(String teacherUserFlow, String startDate, String endDate, List<String> typeList);
	/**
	 * 根据老师查所有学员老师评价表平均分
	 * @return
	 */
	List<Map<String,String>> searchGradeAvgMaps(Map<String, Object> paramMap);

	/**
	 * 根据科室查所有学员对科室的评价
	 * @param deptFlow
	 * @return
	 */
	List<DeptTeacherGradeInfo> searchDeptFlowRec(String deptFlow,String startDate,String endDate);

	List<Map<String, String>> processRecRecTeacherMap(Map<String, String> map);

	List<Map<String, String>> processJsresRecRecTeacherMap(Map<String, String> map);

	/**
	 * 根据用户Flow查询所在所有科室
	 */
	List<SysUserDept> searDeptNames(String userFlow);
	/**
	 * 获取评分内容
	 * @param paramMap
	 * @return
	 */
	List<Map<String,String>> getRecContentByProcess(Map<String, Object> paramMap);
	/**
	 * 获取评分内容jsres
	 * @param paramMap
	 * @return
	 */
	List<Map<String,String>> getJsresRecContentByProcess(Map<String, Object> paramMap);

	List<Map<String,String>> getJsresRecContentByProcess2(Map<String, Object> paramMap);
	/**
	 * 根据rec和process的情况获取用户
	 *
	 * @param paramMap
	 * @return
	 */
	List<SysUser> getUserByRec(Map<String, Object> paramMap);
	/**
	 * 根据rec和process的情况获取部门
	 *
	 * @param map
	 * @return
	 */
	List<SysDept> getDeptByRec(Map<String, Object> map);
	List<SysOrg> getOrgByRec(Map<String, Object> map);
	List<Map<String, String>> processRecShMap(Map<String, String> map);
	List<Map<String, String>> processJsresRecShMap(Map<String, String> map);
    /**
     * 根据主键查找
     * @param recFlow
     * @return
     */
    DeptTeacherGradeInfo readResGrade(String recFlow);
    /**
     * 新增或修改
     * @param recGrade
     * @return
     */
    int edit(DeptTeacherGradeInfo recGrade);
    /**
     * 根据条件查找
     * @param itemsMap
     * @return
     */
    List<DeptTeacherGradeInfo> searchResGradeByItems(Map<String,Object> itemsMap);

	/**
	 * 带教科室查询评分详情
	 * @param userFlow
	 * @param roleFlag
	 * @param sessionNumber
	 * @param recTypeId
	 * @param isCurrentFlag
	 * @return
	 */
	List<DeptTeacherGradeInfoExt> searchScoreList(String userFlow, String roleFlag, String sessionNumber, String recTypeId, String isCurrentFlag);

	/**
	 * 产品用的
	 * 根据老师查所有学员老师评价表
	 * @param userFlow
	 * @return
	 */
	List<DeptTeacherGradeInfo> resSearchProssFlowRec(String userFlow, String startDate, String endDate);

	/**
	 * 产品用的
	 * 根据科室查所有学员对科室的评价
	 * @param deptFlow
	 * @return
	 */
	List<DeptTeacherGradeInfo> resSearchDeptFlowRec(String deptFlow, String startDate, String endDate);

	List<SysUser> getUserByRecForUni(Map<String, Object> paramMap);

	List<SysDept> getDeptByRecForUni(Map<String, Object> map);

	List<Map<String,String>> getRecContentByProcessForUni(Map<String, Object> paramMap);

	List<Map<String,String>> getHadEvaluateByProcesses(Map<String, Object> paramsMap);

	Map<String,Object> getJsresGradeAvgScoreByProcessSessionNumber(Map<String, Object> paramMap);

	Map<String,Object> getJsresGradeAvgScoreByProcess(Map<String, Object> paramMap);

	DeptTeacherGradeInfo readResGradeByProcessAndType(String processFlow, String recTypeId);

    DeptTeacherGradeInfo readResGradeByCfgFlow(String cfgFlow);

	List<Map<String,Object>> getDeptDoctorEvalStatic(Map<String, Object> paramMap);

	List<DeptTeacherGradeInfo> getDeptDoctorEvalStaticDetail(Map<String, Object> paramMap);

	int checkHaveEval(String deptFlow, String cfgFlow);
	/**
	 * 保存评分表单
	 * @return
	 */
	int saveEvaluateForm(String formFileName, String recFlow, String schDeptFlow, String rotationFlow, HttpServletRequest req,
						 String orgFlow, String medicineTypeId);

	List<DeptTeacherGradeInfo> searchEvalFormGradeInfo(ResDoctorSchProcess process, SysUser user,
													   List<String> recTypeIds, Map<String,String> roleFlagMap);

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
	
	
