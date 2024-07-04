package com.pinde.res.dao.jswjw.ext;

import com.pinde.sci.model.mo.DeptTeacherGradeInfo;
import com.pinde.sci.model.mo.SysDept;
import com.pinde.sci.model.mo.SysUser;
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
	List<Map<String,String>> getRecContentByProcess(Map<String, Object> paramMap);
	/**
	 * 查询评分的content
	 * @param paramMap
	 * @return
	 */
	List<Map<String,String>> getJsresRecContentByProcess(Map<String, Object> paramMap);
	/**
	 * 根据rec和process的情况获取用户
	 * @param map
	 * @return
	 */
	List<SysUser> getUserByRec(Map<String, Object> map);
	/**
	 * 根据rec和process的情况获取部门
	 * @param map
	 * @return
	 */
	List<SysDept> getDeptByRec(Map<String, Object> map);


	List<DeptTeacherGradeInfo> resSearchProssFlowRecRec(Map<String, Object> map);

	List<DeptTeacherGradeInfo> resSearchDeptFlowRec(Map<String, Object> map);

	List<SysUser> getUserByRecForUni(Map<String, Object> paramMap);

	List<SysDept> getDeptByRecForUni(Map<String, Object> map);

	List<Map<String,String>> getRecContentByProcessForUni(Map<String, Object> paramMap);

	List<DeptTeacherGradeInfo> searchHbresProssFlowRecRec(Map<String, Object> map);

	List<DeptTeacherGradeInfo> searchHbresDeptFlowRec(Map<String, Object> map);
}
