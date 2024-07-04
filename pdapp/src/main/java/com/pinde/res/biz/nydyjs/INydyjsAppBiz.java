package com.pinde.res.biz.nydyjs;

import java.util.List;
import java.util.Map;

public interface INydyjsAppBiz {


	/**
	 * 首页未审核数据量
	 * @param userFlow
	 * @return
     */
	int getNotAuditNum(String userFlow);

	/**
	 * 轮转中的学员
	 * @param userFlow
	 * @return
     */
	int getIsCurrentNum(String userFlow);
	/**
	 * 已出科的学员
	 * @param userFlow
	 * @return
	 */
	int getIsSchNum(String userFlow);

	/**
	 * 获取用户
	 * @param userFlow
	 * @return
     */
	Map<String,String> readUser(String userFlow);

	/**
	 * 学员列表
	 * @param schArrangeResultMap
	 * @return
     */
	List<Map<String,String>> getStudentList(Map<String, Object> schArrangeResultMap);
	/**
	 * 数据审核学员列表
	 * @param schArrangeResultMap
	 * @return
	 */
	List<Map<String,String>> getDataStudentList(Map<String, Object> schArrangeResultMap);
	/**
	 * 申诉审核学员列表
	 * @param schArrangeResultMap
	 * @return
	 */
	List<Map<String,String>> appealStudentList(Map<String, Object> schArrangeResultMap);
	/** 出科小结审核学员列表
	 * @param schArrangeResultMap
	 * @return
	 */
	List<Map<String,String>> afterSummeryStudentList(Map<String, Object> schArrangeResultMap);
	/** 出科审核学员列表
	 * @param schArrangeResultMap
	 * @return
	 */
	List<Map<String,String>> afterEvaStudentList(Map<String, Object> schArrangeResultMap);

	/**
	 * 申诉待审核数量
	 * @param userFlow
	 * @return
     */
	int getAppealNotAuditNum(String userFlow);
	/**
	 * 出科小结待审核数量
	 * @param userFlow
	 * @return
     */
	int getAfterSummNotAuditNum(String userFlow);
	/**
	 * 出科考核待审核数量
	 * @param userFlow
	 * @return
     */
	int getAfterEvaNotAuditNum(String userFlow);

	/**
	 * 大病历数据列表
	 * @param param
	 * @return
     */
	List<Map<String,String>> getCaseRegistryList(Map<String, Object> param);

	/**
	 * 大病历完成数与要求数
	 * @param param
	 * @return
     */
	Map<String,String> getCaseRegistryProcess(Map<String, Object> param);

	/**
	 * 单条数据审核
	 * @param param
	 * @return
     */
	int dataAudit(Map<String, Object> param);
	/**
	 * 病种完成数与要求数
	 * @param param
	 * @return
	 */
	Map<String, String> getDiseaseProcess(Map<String, Object> param);

	/**
	 * 病种数据列表
	 * @param param
	 * @return
     */
	List<Map<String,String>> getDiseaseRegistryList(Map<String, Object> param);

	/**
	 * 操作完成数与要求数
	 * @param param
	 * @return
     */
	Map<String,String> getSkillProcess(Map<String, Object> param);
	/**
	 * 操作数据列表
	 * @param param
	 * @return
	 */
	List<Map<String,String>> getOperateSkillList(Map<String, Object> param);
	/**
	 * 手术完成数与要求数
	 * @param param
	 * @return
	 */
	Map<String,String> getPossSkillProcess(Map<String, Object> param);
	/**
	 * 操作数据列表
	 * @param param
	 * @return
	 */
	List<Map<String,String>> getPossSkillList(Map<String, Object> param);

	/**
	 * 活动数据列表
	 * @param param
	 * @return
     */
	List<Map<String,String>> getActivityList(Map<String, Object> param);

	/**
	 * 单个审核
	 * @param param
	 * @return
     */
	int activityAudit(Map<String, Object> param);

	/**
	 * 申诉数据列表
	 * @param param
	 * @return
     */
	List<Map<String,String>> appealDataList(Map<String, Object> param);

	/**
	 * 申诉审核
	 * @param param
	 * @return
     */
	int appealAudit(Map<String, Object> param);

	/**
	 * 出科小结详情
	 * @param param
	 * @return
     */
	Map<String,String> getAfterSummDetail(Map<String, Object> param);

	/**
	 * 单个审核出科小结
	 * @param param
	 * @return
     */
	int afterAudit(Map<String, Object> param);

	/**
	 * 病种需填写总数
	 * @param explanId
	 * @return
     */
	int getDiseaseRegistryNum(String explanId);

	/**
	 * 操作需填写总数
	 * @param explanId
	 * @return
     */
	int getOperateSkillNum(String explanId);

	/**
	 * 手术需填写总数
	 * @param explanId
	 * @return
     */
	int getPossSkillNum(String explanId);

	/**
	 * 病种要求数
	 * @param cySecId
	 * @return
     */
	int getCaRequireNum(String hosCySecId);

	/**
	 * 学员病种完成数
	 * @param docFlow
	 * @param cySecId
     * @return
     */
	int getCaRequireFinishNum(Map<String, Object> param);

	/**
	 * 大病历要求数
	 * @param cySecId
	 * @return
     */
	int getDisRequireNum(String hosCySecId);

	/**
	 * 学员大病历完成数
	 * @param param
	 * @return
     */
	int getDisRequireFinishNum(Map<String, Object> param);

	/**
	 * 操作要求数
	 * @param cySecId
	 * @return
     */
	int getOpRequireNum(String hosCySecId);

	/**
	 * 学员操作完成数
	 * @param param
	 * @return
     */
	int getOpRequireFinishNum(Map<String, Object> param);

	/**
	 * 手术要求数
	 * @param cySecId
	 * @return
     */
	int getOpsRequireNum(String hosCySecId);

	/**
	 * 学员手术完成数
	 * @param param
	 * @return
     */

	int getOpsRequireFinishNum(Map<String, Object> param);

	/**
	 * 学员填写日志天数
	 * @param param
	 * @return
     */
	Map<String, Object> getDayLogCount(Map<String, Object> param);

	/**
	 * 轮转天数
	 * @param param
	 * @return
     */
	Map<String, Object> getCycDayCount(Map<String, Object> param);

	/**
	 * 参加活动总次数
	 * @param param
	 * @return
     */
	int getActivityNum(Map<String, Object> param);

	/**
	 * 各项得分
	 * @param param
	 * @return
     */
	Map<String,Object> getScoreMap(Map<String, Object> param);

	/**
	 *判断是否打过考核表
	 * @param param
	 * @return
     */
	int getCheckStatus(Map<String, Object> param);

	/**
	 * 更新出科考核表
	 * @param param
	 * @return
     */
	int updateAfterEva(Map<String, Object> param);

	/**
	 * 新增出科考核表
	 * @param param
	 * @return
     */

	int insertAfterEva(Map<String, Object> param);

	/**
	 * 理论成绩
	 * @param afterEvaId
	 * @return
     */
	Map<String,Object> getTheroyScore(String afterEvaId);

	int deleteWfwork(String workId);

	int insertWFWORK(Map<String, Object> param2);

	List<Map<String,Object>> getDayTimeList(Map<String, Object> param2);

	int havaAppealNum(Map<String, Object> paramMap);

	/**
	 * 查询轮转几个月
	 * @param param
	 * @return
     */
	int getMonths(Map<String, Object> param);
}
