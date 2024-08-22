package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.TeachingActivityInfo;
import com.pinde.sci.model.mo.TeachingActivityInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeachingActivityInfoMapper {
    int countByExample(TeachingActivityInfoExample example);

    int deleteByExample(TeachingActivityInfoExample example);

    int deleteByPrimaryKey(String activityFlow);

    int insert(TeachingActivityInfo record);

    int insertSelective(TeachingActivityInfo record);

    List<TeachingActivityInfo> selectByExampleWithBLOBs(TeachingActivityInfoExample example);

    List<TeachingActivityInfo> selectByExample(TeachingActivityInfoExample example);

    TeachingActivityInfo selectByPrimaryKey(String activityFlow);

    int updateByExampleSelective(@Param("record") TeachingActivityInfo record, @Param("example") TeachingActivityInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") TeachingActivityInfo record, @Param("example") TeachingActivityInfoExample example);

    int updateByExample(@Param("record") TeachingActivityInfo record, @Param("example") TeachingActivityInfoExample example);

    int updateByPrimaryKeySelective(TeachingActivityInfo record);

    int updateByPrimaryKeyWithBLOBs(TeachingActivityInfo record);

    int updateByPrimaryKey(TeachingActivityInfo record);
}