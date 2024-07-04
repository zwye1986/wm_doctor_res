package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.StudyCourseDetailInfo;
import com.pinde.sci.model.mo.StudyCourseDetailInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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