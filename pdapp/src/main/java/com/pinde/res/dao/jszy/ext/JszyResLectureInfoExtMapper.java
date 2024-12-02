package com.pinde.res.dao.jszy.ext;

import com.pinde.core.model.ResLectureInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JszyResLectureInfoExtMapper {
	List<ResLectureInfo> searchLecturesList(@Param("orgFlow") String orgFlow, @Param("roleId") String roleId, @Param("roleFlow") String roleFlow);


	List<Map<String,String>> getHistoryLecture(String userFlow);
	List<ResLectureInfo> checkJoinList(@Param("lectureFlow") String lectureFlow, @Param("userFlow") String userFlow);
}
