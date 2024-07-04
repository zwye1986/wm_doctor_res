package com.pinde.res.dao.lcjn.ext;

import com.pinde.sci.model.mo.InxInfo;
import com.pinde.sci.model.mo.LcjnCourseInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LcjnCourseInfoExtMapper {

	List<Map<String,Object>> getLcjnCourseInfo(Map<String,String> params);

	int getCourseLastNumByFlow(@Param("courseFlow")String courseFlow);

	int checkTrainTime(@Param("userFlow")String userFlow, @Param("courseFlow")String courseFlow);

	List<Map<String,Object>> getLcjnDocCourseInfo(Map<String, String> params);

	String getCourseMinTrainStartDate(@Param("courseFlow") String courseFlow);

	int checkThisTimeIsInTrain(@Param("nowTime") String nowTime, @Param("courseFlow") String courseFlow);
}
