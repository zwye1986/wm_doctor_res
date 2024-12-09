package com.pinde.sci.dao.base;

import com.pinde.core.model.ResLectureEvaDetail;
import com.pinde.core.model.ResLectureEvaDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResLectureEvaDetailMapper {
    int countByExample(ResLectureEvaDetailExample example);

    int deleteByExample(ResLectureEvaDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResLectureEvaDetail record);

    int insertSelective(ResLectureEvaDetail record);

    List<ResLectureEvaDetail> selectByExampleWithBLOBs(ResLectureEvaDetailExample example);

    List<ResLectureEvaDetail> selectByExample(ResLectureEvaDetailExample example);

    ResLectureEvaDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResLectureEvaDetail record, @Param("example") ResLectureEvaDetailExample example);

    int updateByExampleWithBLOBs(@Param("record") ResLectureEvaDetail record, @Param("example") ResLectureEvaDetailExample example);

    int updateByExample(@Param("record") ResLectureEvaDetail record, @Param("example") ResLectureEvaDetailExample example);

    int updateByPrimaryKeySelective(ResLectureEvaDetail record);

    int updateByPrimaryKeyWithBLOBs(ResLectureEvaDetail record);

    int updateByPrimaryKey(ResLectureEvaDetail record);
}