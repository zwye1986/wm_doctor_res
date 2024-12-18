package com.pinde.sci.dao.res;

import com.pinde.core.model.ResLectureInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LectureInfoTargetExtMapper {

	/**
	 * @Department：研发部
	 * @Description 保存讲座活动关联的评价指标信息
	 * @Author fengxf
	 * @Date 2020/2/12
	 */
	int saveLectureInfoTarget(ResLectureInfo resLectureInfo);

	/**
	 * @Department：研发部
	 * @Description 作废讲座活动评价信息
	 * @Author fengxf
	 * @Date 2020/2/12
	 */
	int delLectureInfoTarget(ResLectureInfo resLectureInfo);

	/**
	 * @Department：研发部
	 * @Description 查询讲座活动评价指标信息
	 * @Author fengxf
	 * @Date 2020/2/12
	 */
	List<Map<String,Object>> searchLectureTargetEvalList(@Param("lectureFlow") String lectureFlow, @Param("roles") List<String> roles);

}
