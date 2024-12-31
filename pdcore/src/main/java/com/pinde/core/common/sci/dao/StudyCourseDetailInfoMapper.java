package com.pinde.core.common.sci.dao;

import com.pinde.core.model.StudyCourseDetailInfo;
import com.pinde.core.model.StudyCourseDetailInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudyCourseDetailInfoMapper {
    int countByExample(StudyCourseDetailInfoExample example);

    int deleteByExample(StudyCourseDetailInfoExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(StudyCourseDetailInfo record);

    int insertSelective(StudyCourseDetailInfo record);

    List<StudyCourseDetailInfo> selectByExample(StudyCourseDetailInfoExample example);

    StudyCourseDetailInfo selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") StudyCourseDetailInfo record, @Param("example") StudyCourseDetailInfoExample example);

    int updateByExample(@Param("record") StudyCourseDetailInfo record, @Param("example") StudyCourseDetailInfoExample example);

    int updateByPrimaryKeySelective(StudyCourseDetailInfo record);

    int updateByPrimaryKey(StudyCourseDetailInfo record);
}