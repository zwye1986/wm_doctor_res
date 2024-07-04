package com.pinde.res.biz.njmu2;

import com.pinde.sci.model.mo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface INjmu2AppBiz {

	/**
	 * 根据用户名查询用户
	 * @param userCode
	 * @return
	 */
	SysUser getUserByCode(String userCode);

	/**
	 * 获取医师信息
	 * @param userFlow
	 * @return
	 */
	ResDoctor readResDoctor(String userFlow);

	/**
	 * 获取该用户的所有角色
	 * @param userFlow
	 * @return
	 */
	List<SysUserRole> getSysUserRole(String userFlow);

	/**
	 * 获取配置
	 * @param code
	 * @return
	 */
	String getCfgByCode(String code);

	/**
	 * 获取该医师轮转计划的开始时间和结束时间
	 * @param doctorFlow
	 * @return
	 */
	Map<String, Object> getDocResultArea(String doctorFlow);

	/**
	 * 获取该医师实际轮转的开始时间和结束时间
	 * @param doctorFlow
	 * @return
	 */
	Map<String, Object> getDocProcessArea(String doctorFlow);

	/**
	 * 获取登记数据
	 * @param recFlow
	 * @return
	 */
	ResRec getRecByRecFlow(String recFlow);

	/**
	 * 解析登记数据xml
	 * @param recContent
	 * @return
	 */
	Map<String, String> parseRecContent(String recContent);

	/**
	 * 获取评分模版
	 * @param cfgCode
	 * @return
	 */
	ResAssessCfg getGradeTemplate(String cfgCode);

	/**
	 * 解析评分模版
	 * @param content
	 * @return
	 */
	List<Map<String, Object>> parseAssessCfg(String content);
	
	/**
	 * 解析评分数据
	 * @param content
	 * @return
	 */
	List<Map<String,String>> parseDocGradeXml(String content);

	/**
	 * 获取登记数据及解析后数据
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> getParsedRecs(Map<String, Object> paramMap);

	/**
	 * 根据科室和类型获取一条登记信息
	 * @param processFlow
	 * @param recTypeId
	 * @return
	 */
	ResRec getRecByRecType(String processFlow, String recTypeId);

	/**
	 * 根据标准组和标准科室获取一条标准规则
	 * @param standardGroupFlow
	 * @param standardDeptId
	 * @return
	 */
	SchRotationDept getRotationDeptByResult(String standardGroupFlow,
			String standardDeptId);

	/**
	 * 根据条件获取要求
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> getReqByResult(Map<String, Object> paramMap);

	/**
	 * 编辑一条登记数据
	 * @param recFlow
	 * @param operUserFlow
	 * @param resultFlow
	 * @param recTypeId
	 * @param itemId
	 * @param request
	 */
	void editRec(String recFlow,
			String operUserFlow,
			String resultFlow,
			String recTypeId,
			String itemId,
			HttpServletRequest request,String roleId);

	/**
	 * 根据reqFlow读取一条req
	 * @param reqFlow
	 * @param relRecordFlow
	 * @param itemId
	 * @return
	 */
	SchRotationDeptReq readReq(String reqFlow, String relRecordFlow, String itemId);

	/**
	 * 删除一条rec
	 * @param recFlow
	 */
	void delRec(String recFlow);

	/**
	 * 验证签到信息并且签到
	 * @param userFlow
	 * @param deptFlow
	 * @param signinType
	 * @return
	 */
	boolean signin(String userFlow, String deptFlow, String signinType);

	/**
	 * 教师端的登记数据列表
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> getTeacherParsedRecs(Map<String, Object> paramMap);

	/**
	 * 获取该科室最签到记录
	 * @param userFlow
	 * @return
	 */
	List<ResSignin> getSignin(String userFlow);

	/**
	 * 获取登录人
	 * @param userFlow
	 * @return
     */
	SysUser getUserByFlow(String userFlow);

	/**
	 * 获取签到信息
	 * @param paramMap
	 * @return
     */
	List<Map<String,Object>> searchSign(Map<String, Object> paramMap);

	/**
	 * 培训学员查询
	 * @param resDoctorExt
	 * @return
     */
	List<ResDoctorExt> searchDocUser(ResDoctorExt resDoctorExt);

	/**
	 * 学员安排轮转进度查询
	 * @param userFlows
	 * @return
     */
	List<Map<String,Object>> countResultByUser(List<String> userFlows);

	/**
	 * 学员实际轮转进度查询
	 * @param userFlows
	 * @return
     */
	List<Map<String,Object>> countProcessByUser(List<String> userFlows);

	/*
	查询平台下所有机构
	 */
	List<SysOrg> searchSysOrg();


	/**
	 * 查询机构下的轮转科室
	 * @param orgFlow
	 * @return
	 */
	List<SchDept> searchSchDeptList(String orgFlow);

	/**
	 * 机构下轮转科室的详细信息
	 * @param startDate
	 * @param endDate
	 * @param orgFlow
	 * @return
	 */
	List<SchArrangeResult> searchArrangeResultByDateAndOrg(String startDate, String endDate, String orgFlow,String sessionNumber);


	List<ResDoctorSchProcess> searchProcessByDoctor(String doctorFlow);

	List<SchArrangeResult> searchSchArrangeResultByDoctor(String doctorFlow);

	/**
	 *
	 * @param resultList
	 * @param doctorFlow
     * @return
     */
	Map<String,String> getFinishPer(List<SchArrangeResult> resultList, String doctorFlow);

	List<ResSignin> getSigninByDeptFlow(String userFlow, String schDeptFlow);

	List<SysDict> getDictListByDictId(String doctorTrainingSpe);
}