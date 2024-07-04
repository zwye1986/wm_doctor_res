package com.pinde.res.dao.hbres.ext;

import com.pinde.sci.model.mo.ResLectureInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HbResLectureInfoExtMapper {

	List<ResLectureInfo> searchLecturesList(@Param("orgFlow") String orgFlow, @Param("roleId") String roleId, @Param("roleFlow") String roleFlow);

	List<Map<String,String>> getHistoryLecture(@Param("userFlow") String userFlow);

	int findLectureRegistNum(@Param("lectureFlow") String lectureFlow);

	int checkRegistNum(@Param("lectureFlow") String lectureFlow);

	List<ResLectureInfo> checkJoinList(@Param("lectureFlow") String lectureFlow, @Param("userFlow") String userFlow);
}
