package com.pinde.sci.dao.osca;

import com.pinde.sci.model.osca.OscaCheckInfoExt;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


public interface OscaCheckInfoExtMapper {
	//引导页预约流程数据
	int queryAppointCount(@Param("orgFlow") String orgFlow);
	//引导页待办事项数据
	List<Map<String,Object>> queryGuideInfoList(@Param("orgFlow") String orgFlow);
	//预约学员信息
	List<OscaCheckInfoExt> queryAppointList(Map<String, String> map);
	//查询单个预约学员信(为生成准考证)
	OscaCheckInfoExt queryAppointByFlow(@Param("recordFlow") String recordFlow);
	//查询某方案下安排的考场
	List<Map<String,Object>> queryRoomList(@Param("clinicalFlow") String clinicalFlow);
	//查询学员每站考核进度
	List<Map<String,Object>> queryScheduleList(Map<String, String> map);
	//查询学员每站考核进度(屏显)
	List<Map<String,Object>> studentsProcess(Map<String, Object> paramMap);
	//查询每站考核成绩
	List<Map<String,Object>> queryGradeList(Map<String, String> map);
	//查询学员某站考核成绩
	Map<String,Object> queryDoctorGrade(Map<String, String> map);
	//查询考核信息下绑定的考站
	List<Map<String,Object>> queryStationByFlow(@Param("clinicalFlow") String clinicalFlow);
	//判断此学员是否为招录医师
	int registDoctor(@Param("userFlow") String userFlow);
	//屏显页面内容考试查询
	List<Map<String,Object>> queryRoomDoctorList(Map<String, String> map);
	//屏显页面内容考试查询(新)
	List<Map<String,Object>> queryRoomDoctorList2(Map<String, Object> paramMap);
	//查询该考场即将移除考官的 已签到（扫描） 的学生人数
	int querySignNoSubScoreNum(Map<String,Object> param);
	//临床技能考核信息
	List<Map<String,Object>> queryDataList(Map<String,Object> map);
	//查询某个考核信息下审核通过的学员Flow
	List<String> queryDoctorFlowList(@Param("clinicalFlow")String clinicalFlow,@Param("testNum")String testNum);
	//查询某个考核信息的考核时间(字符串拼接)
	Map<String,String> queryOsaTime(@Param("clinicalFlow")String clinicalFlow);
	//查询某个考核信息的考核开始时间
	List<String> queryOsaFirstTime(@Param("clinicalFlow")String clinicalFlow);
	//屏显信息
	List<Map<String,Object>> screenInfo(Map<String,Object> paramMap);
	//屏显信息设置
	List<Map<String,Object>> screenSelect(Map<String,Object> paramMap);
}
