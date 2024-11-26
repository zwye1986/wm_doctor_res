package com.pinde.sci.dao.base;

import com.pinde.core.model.TeachingActivityTarget;
import com.pinde.core.model.TeachingActivityTargetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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

    List<String> selectJointOrgFlow(String userFlow);

    List<String> selectMainOrgFlow(String userFlow);
}