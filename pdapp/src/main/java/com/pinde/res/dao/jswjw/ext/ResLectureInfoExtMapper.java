package com.pinde.res.dao.jswjw.ext;

import com.pinde.sci.model.mo.ResLectureInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResLectureInfoExtMapper {

	List<ResLectureInfo> searchLecturesList(@Param("orgFlow") String orgFlow, @Param("roleId") String roleId, @Param("roleFlow") String roleFlow);

	List<Map<String,String>> getHistoryLecture(String userFlow);

	int checkRegistNum(@Param("lectureFlow") String lectureFlow);

	List<ResLectureInfo> checkJoinList(@Param("lectureFlow") String lectureFlow, @Param("userFlow") String userFlow);
}
