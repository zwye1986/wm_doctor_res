package com.pinde.sci.biz.sch;

import com.pinde.sci.model.mo.SchDoctorAbsence;
import com.pinde.sci.model.mo.ZseyHrKqMonth;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


public interface IZseyHrKqMonthBiz {
	ZseyHrKqMonth read(String monthFlow);

	int edit(ZseyHrKqMonth zseyHrKqMonth);

	//通过年月找到唯一记录
	ZseyHrKqMonth searchByKqDate(String userFlow,String kqDate);

	//统计某学员的考勤记录
	List<Map<String,String>> searchKq(String startDate,String endDate,String userFlow);

	//根据条件查询某月轮转学员列表
	List<Map<String,Object>> searchProcessByMonth(Map<String,Object> paramMap);

	//按时间维度的考勤查询
	List<Map<String,Object>> searchProcessByTime(Map<String,Object> paramMap);

	//按轮转科室维度的考勤查询
	List<Map<String,Object>> searchProcessByDept(Map<String,Object> paramMap);

	//查询时间范围内的所有考勤数据
	List<ZseyHrKqMonth> searchKqByDates(List<String> dates,String userFlow);

}
