package com.pinde.sci.dao.base;

import com.pinde.core.model.ResLectureInfo;
import com.pinde.core.model.ResLectureInfoExample;
import com.pinde.sci.model.jsres.ParticipateInfoExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResLectureInfoMapper {
    int countByExample(ResLectureInfoExample example);

    int deleteByExample(ResLectureInfoExample example);

    int deleteByPrimaryKey(String lectureFlow);

    int insert(ResLectureInfo record);

    int insertSelective(ResLectureInfo record);

    List<ResLectureInfo> selectByExampleWithBLOBs(ResLectureInfoExample example);

    List<ResLectureInfo> selectByExample(ResLectureInfoExample example);

    ResLectureInfo selectByPrimaryKey(String lectureFlow);

    int updateByExampleSelective(@Param("record") ResLectureInfo record, @Param("example") ResLectureInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") ResLectureInfo record, @Param("example") ResLectureInfoExample example);

    int updateByExample(@Param("record") ResLectureInfo record, @Param("example") ResLectureInfoExample example);

    int updateByPrimaryKeySelective(ResLectureInfo record);

    int updateByPrimaryKeyWithBLOBs(ResLectureInfo record);

    int updateByPrimaryKey(ResLectureInfo record);

    /**
     * 查询所有讲座通知对象
     * @param orgFlow
     * @return
     */
     List<Map<String, Object>> queryNotification(String orgFlow);

    List<ParticipateInfoExt> queryParticipateList(String orgFlow);

    List<Map<String, Object>> queryAssessScoreList(String orgFlow);
}