package com.pinde.sci.dao.lcjn;

import com.pinde.core.model.SysDict;
import com.pinde.sci.model.mo.LcjnCourseTime;
import com.pinde.sci.model.mo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LcjnBaseManagerExtMapper {
	//培训信息管理查询
	List<Map<String, Object>> queryCourseList(Map<String, String> map);
	//技能配置管理查询
	List<Map<String, Object>> querySkillConfigList(@Param("orgFlow") String orgFlow);
	//根据时间段查询培训课程
	List<Map<String, Object>> searchCourse(Map<String,String> paramMap);
	//根据课程技能查询配置的耗材及固定资产详情(关联获取耗材及固定资产名称)
	List<Map<String, Object>> queryConfigDetailByFlow(@Param("skillFlow") String skillFlow);
	//根据角色查询人员列表
	List<SysUser> getStudents(Map<String, String> map);
	//查询某月所有培训课程时间
	List<LcjnCourseTime> queryCourseTimeList(@Param("startTime") String startTime,@Param("endTime") String endTime);
	//查询已维护好且非停用耗材和固定资产
	List<SysDict> searchStartDictList(SysDict dict);
	//返回某时间内的老师及课程情况
	List<Map<String,String>> queryTeaTimeCourseList(@Param("trainStartTime") String trainStartTime,@Param("trainEndTime") String trainEndTime,@Param("courseFlow") String courseFlow);
}
