package com.pinde.sci.biz.res;

import com.pinde.core.model.ResLectureInfo;
import com.pinde.core.model.ResLectureInfoRole;
import com.pinde.core.model.ResLectureScanRegist;
import com.pinde.sci.model.jsres.ParticipateInfoExt;

import java.util.List;
import java.util.Map;

public interface IResLectureInfoBiz {
    /**
     * 根据日期,内容,主讲人查询当前机构下讲座信息
     */
    List<ResLectureInfo> SearchByDateContentTeacherNameType(String orgFlow,String lectureTrainDate, String content, String lectureTeacherName,String lectureTypeId,String place);

    List<ResLectureInfo> SearchByDateContentTeacherNameTypeTwo(String orgFlow,String lectureTrainStartDate,String lectureTrainEndDate, String content, String lectureTeacherName,String lectureTypeId,String place);
    /**
     * 添加/修改一条讲座信息
     */
    int editLectureInfo(ResLectureInfo resLectureInfo);
    /**
     * 根据主键读信息
     */
    ResLectureInfo read(String lectureFlow);
    /**
     * 查询当前机构下的所有最新讲座
     */
    List<ResLectureInfo> searchNewLectures(String orgFlow, String roleId, String roleFlow,ResLectureInfo resLectureInfo);

    int addLectureInfo(ResLectureInfo lectureInfo, String[] itemId);

    List<ResLectureInfoRole> readLectureRoleList(String lectureFlow);

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
     * @Description 判断讲座信息是否能删除
     * @Author fengxf
     * @Date 2019/12/17
     */
    boolean getDelLectureInfoFlag(String lectureFlow);

    /**
     * @Department：研发部
     * @Description 保存讲座活动评价信息
     * @Author fengxf
     * @Date 2020/2/14
     */
    String saveLectureEval(ResLectureScanRegist scanRegist, Map<String, Object> paramMap);
}