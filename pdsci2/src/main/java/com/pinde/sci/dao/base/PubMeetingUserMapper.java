package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubMeetingUser;
import com.pinde.sci.model.mo.PubMeetingUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubMeetingUserMapper {
    int countByExample(PubMeetingUserExample example);

    int deleteByExample(PubMeetingUserExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(PubMeetingUser record);

    int insertSelective(PubMeetingUser record);

    List<PubMeetingUser> selectByExample(PubMeetingUserExample example);

    PubMeetingUser selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") PubMeetingUser record, @Param("example") PubMeetingUserExample example);

    int updateByExample(@Param("record") PubMeetingUser record, @Param("example") PubMeetingUserExample example);

    int updateByPrimaryKeySelective(PubMeetingUser record);

    int updateByPrimaryKey(PubMeetingUser record);
}