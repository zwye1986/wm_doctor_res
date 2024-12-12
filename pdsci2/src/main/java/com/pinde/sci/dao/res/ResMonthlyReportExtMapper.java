package com.pinde.sci.dao.res;

import com.pinde.core.model.SysUser;
import com.pinde.sci.model.mo.*;

import java.util.List;
import java.util.Map;

public interface ResMonthlyReportExtMapper {
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
	List<Map<String,Object>> getActivityMostDept(Map<String, Object> paramMap);
	List<Map<String,Object>> getActivityLeastDept(Map<String, Object> paramMap);
	//按活动类型分类统计次数
	List<Map<String,String>> getActivityByType(Map<String, Object> paramMap);

	List<SysMonthlyChangeInfo> getSysMonthlyChangeInfo(Map<String, Object> paramMap);
	List<SysMonthlyReturnDelayInfo> getSysMonthlyReturnDelayInfo(Map<String, Object> paramMap);

	List<SysMonthlyDocCycleInfo> getSysMonthlyDocCycleInfo(Map<String, Object> paramMap);

	//高校查询入科人数
	int findInNum(Map<String, Object> paramMap);
	//高校查询出科人数
	int findOutNum(Map<String, Object> paramMap);
	//高校查询出科异常人数
	int findErrorOutNum(Map<String, Object> paramMap);
	//高校查若干个月没使用APP人数
	List<Map<String,String>> getNoAppNum(Map<String, Object> paramMap);
	//高校查3个月没使用APP人数
	List<Map<String,String>> getNoAppNum3(Map<String,Object> paramMap);
	//查询SYS_USER表信息
	List<SysUser> getUserList(Map<String, Object> paramMap);
	//查询RES_DOCTOR表信息
	List<ResDoctor> getDoctorList(Map<String, Object> paramMap);
	//查询recruit表信息
    List<com.pinde.core.model.ResDoctorRecruit> getRecruitList(Map<String, Object> paramMap);

	//基地查询出入科人数详情（图5）
	List<Map<String,String>> getHospitalChart5Detail(Map<String, Object> paramMap);
	//基地查询出入科人员详情（图5）
	List<Map<String,String>> getHospitalChart5DoctorDetail(Map<String, Object> paramMap);
	//基地出科考核人员详情
	List<Map<String,String>> getHospitalChart6Detail(Map<String, Object> paramMap);
	//基地参加理论考核人员详情
	List<Map<String,String>> getHospitalChart6Detail2(Map<String, Object> paramMap);
	//基地轮转数据登记详情
	List<Map<String,String>> getHospitalChart7Detail(Map<String, Object> paramMap);
	//基地轮转数据审核详情
	List<Map<String,Object>> getHospitalChart8Detail(Map<String, Object> paramMap);
	//教学活动统计详情
	List<Map<String,Object>> getHospitalChart9Detail(Map<String, Object> paramMap);


}
