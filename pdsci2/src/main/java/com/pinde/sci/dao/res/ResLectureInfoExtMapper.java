package com.pinde.sci.dao.res;

import com.pinde.sci.model.jsres.ParticipateInfoExt;
import com.pinde.core.model.ResLectureInfo;
import com.pinde.core.model.ResLectureRandomScan;
import com.pinde.core.model.ResLectureScanRegist;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResLectureInfoExtMapper {

	List<ResLectureInfo> searchLecturesList(@Param("orgFlow") String orgFlow, @Param("roleId") String roleId, @Param("roleFlow") String roleFlow, @Param("resLectureInfo") ResLectureInfo resLectureInfo);

	List<Map<String,String>> getHistoryLecture(String userFlow);

	int checkRegistNum(@Param("lectureFlow") String lectureFlow);

	void deleteLectureRole(@Param("resLectureInfo") ResLectureInfo resLectureInfo, @Param("roleFlows") List<String> roleFlows);

	List<ResLectureScanRegist> searchIsScan(@Param("lectureFlow") String lectureFlow, @Param("roles") List<String> roles);

	List<ResLectureScanRegist> searchRegistByLectureFlow(@Param("lectureFlow") String lectureFlow, @Param("roles") List<String> roles);

	List<ResLectureInfo> checkJoinList(@Param("lectureFlow") String lectureFlow, @Param("userFlow") String userFlow);

    List<ResLectureRandomScan> searchIsRandomScan(@Param("lectureFlow") String lectureFlow, @Param("roles") List<String> roles);

	List<Map<String, String>> searchIsScanNew(@Param("lectureFlow") String lectureFlow, @Param("roles") List<String> roles);

	List<Map<String, String>> searchRegistByLectureFlowNew(@Param("lectureFlow") String lectureFlow, @Param("roles") List<String> roles);

	/**
	 * 查询所有讲座通知对象
	 * @param orgFlow
	 * @return
	 */
	List<Map<String, Object>> queryNotification(String orgFlow);

	List<ParticipateInfoExt> queryParticipateList(String orgFlow);

	List<Map<String, Object>> queryAssessScoreList(String orgFlow);

	/**
	 * @Department：研发部
	 * @Description 查询讲座下的签到人数
	 * @Author fengxf
	 * @Date 2019/12/17
	 */
	int countLectureSign(String lectureFlow);

	/**
	 * @Department：研发部
	 * @Description 查询讲座下的评价人数
	 * @Author fengxf
	 * @Date 2019/12/17
	 */
	int countLectureEvel(String lectureFlow);
}
