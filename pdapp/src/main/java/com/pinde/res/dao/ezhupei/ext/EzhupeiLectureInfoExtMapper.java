package com.pinde.res.dao.ezhupei.ext;

import com.pinde.core.model.ResLectureInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EzhupeiLectureInfoExtMapper {

	List<ResLectureInfo> searchLecturesList(@Param("orgFlow") String orgFlow, @Param("roleId") String roleId, @Param("roleFlow") String roleFlow);

	List<Map<String,String>> getHistoryLecture(String userFlow);
}
