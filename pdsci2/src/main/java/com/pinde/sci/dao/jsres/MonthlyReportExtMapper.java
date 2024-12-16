package com.pinde.sci.dao.jsres;

import com.pinde.core.model.*;
import com.pinde.sci.model.jsres.*;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.SchArrangeResult;
import com.pinde.sci.model.mo.SysMonthlyChangeInfo;
import com.pinde.sci.model.mo.SysMonthlyDocCycleInfo;
import com.pinde.sci.model.mo.SysMonthlyNotUseappInfo;
import com.pinde.sci.model.mo.SysMonthlyReturnDelayInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MonthlyReportExtMapper {
	//参加理论考核人数
	int getParticipateInNum(Map<String, Object> paramMap);

	//理论考试总次数
	int getTheoryAll(Map<String, Object> paramMap);

	//合格人数
	int getPassNum(Map<String, Object> paramMap);

	//不合格人数
	int getNotPassNum(Map<String, Object> paramMap);

	//教学活动总量
	int getActivitySum(Map<String, Object> paramMap);

	//教学活动数量最多科室和最少科室
	List<Map<String, Object>> getActivityMostDept(Map<String, Object> paramMap);

	List<Map<String, Object>> getActivityLeastDept(Map<String, Object> paramMap);

	//教学活动数量最多基地和最少基地
	List<Map<String, Object>> getActivityMostOrg(Map<String, Object> paramMap);

	List<Map<String, Object>> getActivityLeastOrg(Map<String, Object> paramMap);

	//按活动类型分类统计次数
	List<Map<String, String>> getActivityByType(Map<String, Object> paramMap);


	List<SysMonthlyChangeInfo> getSysMonthlyChangeInfo(Map<String, Object> paramMap);

	List<SysMonthlyReturnDelayInfo> getSysMonthlyReturnDelayInfo(Map<String, Object> paramMap);

	List<SysMonthlyDocCycleInfo> getSysMonthlyDocCycleInfo(Map<String, Object> paramMap);

	//高校查询入科人数
	int findInNum(Map<String, Object> paramMap);

	//高校查询出科人数
	int findOutNum(Map<String, Object> paramMap);

	//高校查询出科异常人数
	int findErrorOutNum(Map<String, Object> paramMap);

	//高校查若单个月没使用APP人数
	List<Map<String, String>> getNoAppNum(Map<String, Object> paramMap);

	//高校查3个月没使用APP人数
	List<Map<String, String>> getNoAppNum3(Map<String, Object> paramMap);

	//查询SYS_USER表信息
	List<SysUser> getUserList(Map<String, Object> paramMap);

	//查询RES_DOCTOR表信息
	List<ResDoctor> getDoctorList(Map<String, Object> paramMap);

	//查询recruit表信息
    List<com.pinde.core.model.ResDoctorRecruit> getRecruitList(Map<String, Object> paramMap);

	//基地查询出入科人数详情（图5）
	List<Map<String, String>> getHospitalChart5Detail(Map<String, Object> paramMap);

	//基地查询出入科人员详情（图5）
	List<Map<String, String>> getHospitalChart5DoctorDetail(Map<String, Object> paramMap);

	//基地出科考核人员详情
	List<Map<String, String>> getHospitalChart6Detail(Map<String, Object> paramMap);

	//基地参加理论考核人员详情
	List<Map<String, String>> getHospitalChart6Detail2(Map<String, Object> paramMap);

	//基地轮转数据登记详情
	List<Map<String, String>> getHospitalChart7Detail(Map<String, Object> paramMap);

	//基地轮转数据审核详情
	List<Map<String, Object>> getHospitalChart8Detail(Map<String, Object> paramMap);

	//教学活动统计详情
	List<Map<String, Object>> getHospitalChart9Detail(Map<String, Object> paramMap);

	//根据基地分组查询人员数量
	List<Map<String, Object>> getHospitalDoctorsNum(Map<String, Object> paramMap);

	//根据基地分组查询单个月没使用APP人
	List<Map<String, Object>> getAppData(Map<String, Object> paramMap);

	List<SysMonthlyNotUseappInfo> getAppDataDetail(Map<String, Object> paramMap);

	//根据基地分组查询3个月没使用APP人
	List<Map<String, Object>> getAppData3(Map<String, Object> paramMap);

	List<SysMonthlyNotUseappInfo> getAppData3Detail(Map<String, Object> paramMap);

	List<SysOrg> getAppDatayuh(Map<String, Object> paramMap);

	Integer getTrainTotal(Map<String, Object> paramMap);

	Integer getTrainerSum(/*@Param("orgFlow")String orgFlow*/Map<String,Object> paramMap);

	List<Map<String,Object>> findActivityListyuh(/*@Param("orgFlow")String orgFlow*/Map<String,Object> paramMap);

	List<TeachingActivityResult> getScanTime1AndScanTime2(@Param("activityFlow")String activityFlow);
	List<TeachingActivityResult> getScanTime1AndScanTime2LeftjoinUserFlow(Map<String,Object> paramMap);

	List<Map<String,Object>> findOutOfficeDoctorInfo(Map<String,Object> paramMap);//学员出科

	List<Map<String, Object>> findOutOfficeFinishSumAndTotalSum(Map<String, Object> paramMap);//查询学员的培训数据完成个数和总数

	List<Map<String, String>> findDoctorOutOffice(Map<String, Object> paramMap);//查询学员的培训数据完成个数和总数


    List<JsResDoctorRecruitExt> getUserInfoAndOrgNameByUniversity(Map<String, Object> paramMap);


	List<JsResDoctorRecruitExt> getTraincountbySpeNameSessionNumberDocType(Map<String, Object> paramMap);
    List<String> getAppUserSum(Map<String, Object> paramMap);

    List<DoctorInfoParam> findManzuTiaojianDoctor(Map<String, Object> paramMap);

	List<JiaoxueActiveAndResultParam> findActivityInfoAndActivityResultByTiaojian(Map<String, Object> paramMap);//一对多 activityAndResult

	/*学员轮转异常*/
	List<Map<String,Object>> searchDocCycleList(Map<String,Object> paramMap);
	List<SchArrangeResult> searchCycleArrangeResults (Map<String,Object> paramMap);
	Integer getCurrentMonthInExceptionCount(@Param("doctorFlow")String doctorFlow,@Param("startDate")String startDate,@Param("endDate")String endDate);

	List<SchArrangeResult> getCurrentMonthInException(@Param("doctorFlow")String doctorFlow);

	/*学员轮转数据月报*/

	DoctorLunZhuanDataMonthReport findDoctorLunZhuanDataMonthReport(Map<String,Object> paramMap);

	List<PersonChangeEntity>selectFindChangeSpe(Map<String,Object> paramMap);

    List<Map<String,Object>> searchDocCycleListNew(Map<String, Object> map);

	List<Map<String,Object>> searchDocCKYCBySpeId(Map<String, Object> param);

	List<Map<String,Object>> searchDocCKYCBySpeId2(Map<String, Object> param);

	List<Map<String,Object>> searchNotNumListInfo(Map<String, Object> param);

	List<Map<String,Object>> searchNotExamNumListInfo(Map<String, Object> param);

	List<DoctorLunZhuanExceptionParam> searchDocCycleListOrg(Map<String, Object> param);

    List<JsresDataStatistics> searchDataStatistics(Map<String, Object> param);

	Map<String, Object> searchVisitsList(Map<String, Object> param);

	List<JsresMonthStatistics> searchMonthDataList(Map<String, Object> param);
}

