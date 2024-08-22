package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.TeachingActivityInfoTarget;
import com.pinde.sci.model.mo.TeachingActivityInfoTargetExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeachingActivityInfoTargetMapper {
    int countByExample(TeachingActivityInfoTargetExample example);

    int deleteByExample(TeachingActivityInfoTargetExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(TeachingActivityInfoTarget record);

    int insertSelective(TeachingActivityInfoTarget record);

    List<TeachingActivityInfoTarget> selectByExample(TeachingActivityInfoTargetExample example);

    TeachingActivityInfoTarget selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") TeachingActivityInfoTarget record, @Param("example") TeachingActivityInfoTargetExample example);

    int updateByExample(@Param("record") TeachingActivityInfoTarget record, @Param("example") TeachingActivityInfoTargetExample example);

    int updateByPrimaryKeySelective(TeachingActivityInfoTarget record);

    int updateByPrimaryKey(TeachingActivityInfoTarget record);
}