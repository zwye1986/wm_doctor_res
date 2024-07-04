package com.pinde.res.dao.hzyy.ext;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HzyyTutorAppMapper {

	List<Map<String,String>> stuImgList(@Param("userFlow") String userFlow, @Param("startTime") String startTime, @Param("endTime") String endTime);

	List<Map<String,String>> stuSchImgList(Map<String, Object> searchMap);

	int delFile(@Param("fileFlow") String fileFlow);

	Map<String,String> readFile(String fileFlow);

	int saveFile(Map<String, Object> param);
}
