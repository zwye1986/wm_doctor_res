package com.pinde.res.dao.gzzy.ext;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GzzyLiveTrainingMapper {

	//读取一个学生的所有反馈意见
	List<Map<String,String>> readByOpinionUserFlow(Map<String,Object> paramMap);
	//根据主键读一条反馈信息
	Map<String,String> read(@Param("trainingOpinionFlow")String trainingOpinionFlow);
	//根据主键删除一条反馈信息
	void delOpinion(@Param("trainingOpinionFlow")String trainingOpinionFlow);
	//增加一条信息
	void addOpinion(Map<String,Object> paramMap );
	//修改一条信息
	void editOpinion(Map<String,Object> paramMap );
	//读取一个学生的所有培通知指南
	List<Map<String,String>> readNoticeByOrgFlow(Map<String,Object> paramMap);
	//根据主键读取一条住培通知指南
	Map<String,String> readNotice(@Param("recordFlow")String recordFlow);
	//获取当天签到次数
	List<Map<String,String>> getAttendanceDetailList(Map<String,Object> paramMap);

	List<Map<String,String>> getAttendance(Map<String,Object> paramMap);

	void addAttendance(Map<String,Object> paramMap );

	void addAttendanceDetail(Map<String,Object> paramMap );
}
