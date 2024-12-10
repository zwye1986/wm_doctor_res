package com.pinde.sci.dao.res;

import com.pinde.core.model.ResSchProcessExpress;
import com.pinde.core.model.SysUser;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorSchProcessExt;
import com.pinde.sci.model.res.ResRecExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResRecDiseaseRegistryExtMapper {
	List<Map<String,Object>> searchAuditCount(@Param(value = "userFlow") String userFlow, @Param(value = "roleFlag") String roleFlag);

	List<ResRecExt> searchAuditList(@Param(value = "userFlow") String userFlow,
                                    @Param(value = "roleFlag") String roleFlag,
                                    @Param(value = "recTypeId") String recTypeId,
                                    @Param(value = "doctorFlow") String doctorFlow,
                                    @Param(value = "isCurrentFlag") String isCurrentFlag);

	List<ResRecExt> searchRegistryList(@Param(value = "userFlow") String userFlow,
                                       @Param(value = "roleFlag") String roleFlag,
                                       @Param(value = "rec") ResRec rec,
                                       @Param(value = "process") ResDoctorSchProcess process);

	List<ResRecExt> searchScoreList(@Param(value = "userFlow") String userFlow,
                                    @Param(value = "roleFlag") String roleFlag,
                                    @Param(value = "sessionNumber") String sessionNumber,
                                    @Param(value = "recTypeId") String recTypeId,
                                    @Param(value = "isCurrentFlag") String isCurrentFlag);

	List<ResRecExt> searchTeacherAudit(@Param(value = "schDeptFlow") String schDeptFlow,
                                       @Param(value = "isCurrentFlag") String isCurrentFlag,
                                       @Param(value = "userFlow") String userFlow);

	List<ResDoctorSchProcessExt> searchProcessExt(@Param(value = "processExt") ResDoctorSchProcessExt processExt);

	List<ResRec> searchRecByUserAndSchdept(@Param(value = "userFlows") List<String> userFlows,
                                           @Param(value = "processFlows") List<String> processFlows,
                                           @Param(value = "recTypeId") String recTypeId,
                                           @Param(value = "itemName") String itemName);

	List<ResRecExt> searchRecExtByRecExt(@Param(value = "recExt") ResRecExt recExt);

	List<Map<String,Object>> countRecWithDoc(@Param(value = "userFlows") List<String> userFlows, @Param(value = "processFlows") List<String> processFlows, @Param(value = "isAudit") String isAudit);

	List<ResRec> searchByRecForAudit(@Param(value = "processFlow") String processFlow,
                                     @Param(value = "recTypeId") String recTypeId);

	List<Map<String,Object>> searchTeacherAuditCount(@Param(value = "headUserFlow") String headUserFlow, @Param(value = "isAudit") String isAudit);

	List<Map<String,Object>> searchDoctorNotAuditCount(@Param(value = "schDeptFlow") String schDeptFlow, @Param(value = "teacherUserFlow") String teacherUserFlow, @Param(value = "isAudit") String isAudit, @Param(value = "recTypeIds") List<String> recTypeIds);

	/**
	 * 查询当前带教或科主任的待出科人员数据
	 * @param process
	 * @param user
	 * @param recTypeIds
	 * @return
	 */
	List<ResRec> searchAfterAuditRec(@Param(value = "process") ResDoctorSchProcess process, @Param(value = "user") SysUser user, @Param(value = "recTypeIds") List<String> recTypeIds, @Param(value = "roleFlagMap") Map<String, String> roleFlagMap);

	List<ResSchProcessExpress> searchAfterAuditRecNew(@Param(value = "process") ResDoctorSchProcess process, @Param(value = "user") SysUser user, @Param(value = "recTypeIds") List<String> recTypeIds, @Param(value = "roleFlagMap") Map<String, String> roleFlagMap);

	/**
	 * 获取该机构待出科情况
	 * @param orgFlow
	 * @return
	 */
	List<Map<String,Object>> searchDeptWillAfter(@Param(value = "orgFlow") String orgFlow);

	/**
	 * 获取该机构待出科情况
	 * @param orgFlow
	 * @return
	 */
	List<Map<String,Object>> searchDeptWillAfterDoc(@Param(value = "orgFlow") String orgFlow, @Param("deptFlow") String deptFlow);
	/**
	 * 获取未审核数
	 * @param teacherUserFlow
	 * @param typeList
	 * @return
	 */
	int notAudited(@Param(value = "teacherUserFlow") String teacherUserFlow, @Param(value = "startDate") String startDate, @Param(value = "endDate") String endDate, @Param("typeList") List<String> typeList);
	/**
	 * 获取已审核数
	 * @param teacherUserFlow
	 * @param typeList
	 * @return
	 */
	int isNotAudited(@Param(value = "teacherUserFlow") String teacherUserFlow, @Param(value = "startDate") String startDate, @Param(value = "endDate") String endDate, @Param("typeList") List<String> typeList);

	/**
	 * 获取未审核数
	 * @return
	 */
	List<Map<String,String>> notAuditedMaps(Map<String, Object> paramMap);
	/**
	 * 获取已审核数
	 * @return
	 */
	public List<Map<String,String>> isNotAuditedMaps(Map<String, Object> paramMap);


	/**
	 * 查询轮转中登记的数据
	 * @param paramMap
	 * @return
	 */
	List<ResRec> searchProssingRec(Map<String, Object> paramMap);

	/**
	 * 根据老师查所有学员老师评价表
	 * @param map
	 * @return
	 */
	List<ResRec> searchProssFlowRecRec(Map<String, Object> map);
	List<ResRec> resSearchProssFlowRecRec(Map<String, Object> map);

	List<ResRec> searchProssFlowRec(Map<String, Object> map);

	/**
	 * 根据科室查所有学员对科室的评价
	 * @param map
	 * @return
	 */
	List<ResRec> searchDeptFlowRec(Map<String, Object> map);
	List<ResRec> resSearchDeptFlowRec(Map<String, Object> map);

	/**
	 * 查询评分的content
	 * @param paramMap
	 * @return
	 */
	List<Map<String,String>> getRecContentByProcess(Map<String, Object> paramMap);
	List<Map<String,String>> getRecContentByProcessForUni(Map<String, Object> paramMap);

	/**
	 * 批量修改注册状态
	 * @param paramMap
	 * @return
	 */
	int modifyResRegStatus(Map<String, Object> paramMap);

	/**
	 * 查询学员的数据登记信息
	 * @param recTypeIds
	 * @param roleFlagMap
     * @return
     */
	List<ResRec> searchSXSRecData(@Param(value = "recTypeIds") List<String> recTypeIds, @Param(value = "roleFlagMap") Map<String, String> roleFlagMap);

	List<Map<String,String>> findTrainCharts(@Param(value = "orgFlowList") List<String> orgFlowList, @Param(value = "year") String year, @Param(value = "speName") String speName);

	//湖北需求根据rotationFlow，doctorFlow，recTypeId查询ResRec
	List<ResRec> searchByRecWithBLOBsByMap4Hb(Map paramMap);

	/****************************高******校******管******理******员******角******色************************************************/

	List<Map<String,String>> searchInfoForUni(Map<String, Object> paramMap);

	List<ResRec> searchDelayInfoForUni(Map<String, Object> paramMap);

	List<Map<String,String>> searchInfo2(Map<String, Object> paramMap);

	int orgBatchAuditDoctorInfo(Map<String, Object> param);

	List<TeachingActivityInfo> searchJoinActivityByProcessFlow(@Param("processFlow") String processFlow);

	List<TeachingActivityInfo> searchJoinActivityByProcessFlow2(@Param("processFlow") String processFlow);

	List<TeachingActivityInfo> searchJoinActivityByStrandDeptFlow(@Param("doctorFlow") String doctorFlow, @Param("recordFlow") String recordFlow);

	List<TeachingActivityInfo> searchJoinActivityByStrandDeptFlowAndNotScore(@Param("doctorFlow") String doctorFlow, @Param("recordFlow") String recordFlow);

	List<Map<String,Object>> queryJoinActivityAndEditList(@Param("doctorFlow") String doctorFlow, @Param("recTypeId") String recTypeId, @Param("processFlow") String processFlow);


    // 要求一键审核
    int oneKeyAudit(@Param("record") ResRec record, @Param("regTypeIds") List<String> regTypeIds, @Param("userFlow") String userFlow);

    // 培训数据一键审核 （机构）
    int oneKeyAuditByOrg(@Param("orgFlow") String orgFlow, @Param("userFlow") String userFlow);

	List<DeptTeacherGradeInfo> searchEvalFormGradeInfo(@Param(value = "process") ResDoctorSchProcess process, @Param(value = "user") SysUser user, @Param(value = "recTypeIds") List<String> recTypeIds, @Param(value = "roleFlagMap") Map<String, String> roleFlagMap);

	/**
	 * @Department：研发部
	 * @Description 查询审核信息
	 * @Author fengxf
	 * @Date 2020/11/18
	 */
	List<Map<String,String>> searchAuditedDataList(Map<String, Object> paramMap);

	/**
	 * @Department：研发部
	 * @Description 查询审核信息拆分成每个学员
	 * @Author xieyongheng
	 * @Date 2023/4/12
	 */
	List<Map<String,String>> searchAuditedDataListByOper(Map<String, Object> paramMap);

	List<ResRec> searchRecAndActivityByProcess(Map<String, Object> paramMap);
}
