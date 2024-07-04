package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.TeachingActivitySpeaker;
import com.pinde.sci.model.mo.TeachingActivitySpeakerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeachingActivitySpeakerMapper {
    int countByExample(TeachingActivitySpeakerExample example);

    int deleteByExample(TeachingActivitySpeakerExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(TeachingActivitySpeaker record);

    int insertSelective(TeachingActivitySpeaker record);

    List<TeachingActivitySpeaker> selectByExample(TeachingActivitySpeakerExample example);

    TeachingActivitySpeaker selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") TeachingActivitySpeaker record, @Param("example") TeachingActivitySpeakerExample example);

    int updateByExample(@Param("record") TeachingActivitySpeaker record, @Param("example") TeachingActivitySpeakerExample example);

    int updateByPrimaryKeySelective(TeachingActivitySpeaker record);

    int updateByPrimaryKey(TeachingActivitySpeaker record);
}