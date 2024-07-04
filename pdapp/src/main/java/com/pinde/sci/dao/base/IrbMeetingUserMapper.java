package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.IrbMeetingUser;
import com.pinde.sci.model.mo.IrbMeetingUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IrbMeetingUserMapper {
    int countByExample(IrbMeetingUserExample example);

    int deleteByExample(IrbMeetingUserExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(IrbMeetingUser record);

    int insertSelective(IrbMeetingUser record);

    List<IrbMeetingUser> selectByExample(IrbMeetingUserExample example);

    IrbMeetingUser selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") IrbMeetingUser record, @Param("example") IrbMeetingUserExample example);

    int updateByExample(@Param("record") IrbMeetingUser record, @Param("example") IrbMeetingUserExample example);

    int updateByPrimaryKeySelective(IrbMeetingUser record);

    int updateByPrimaryKey(IrbMeetingUser record);
}