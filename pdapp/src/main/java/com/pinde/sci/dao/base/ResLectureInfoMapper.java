package com.pinde.sci.dao.base;

import com.pinde.core.model.ResLectureInfo;
import com.pinde.core.model.ResLectureInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
}