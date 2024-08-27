package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.TeachingActivityTarget;
import com.pinde.sci.model.mo.TeachingActivityTargetExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeachingActivityTargetMapper {
    int countByExample(TeachingActivityTargetExample example);

    int deleteByExample(TeachingActivityTargetExample example);

    int deleteByPrimaryKey(String targetFlow);

    int insert(TeachingActivityTarget record);

    int insertSelective(TeachingActivityTarget record);

    List<TeachingActivityTarget> selectByExample(TeachingActivityTargetExample example);

    TeachingActivityTarget selectByPrimaryKey(String targetFlow);

    int updateByExampleSelective(@Param("record") TeachingActivityTarget record, @Param("example") TeachingActivityTargetExample example);

    int updateByExample(@Param("record") TeachingActivityTarget record, @Param("example") TeachingActivityTargetExample example);

    int updateByPrimaryKeySelective(TeachingActivityTarget record);

    int updateByPrimaryKey(TeachingActivityTarget record);
}