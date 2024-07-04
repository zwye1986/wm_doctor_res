package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduUserInfoStatus;
import com.pinde.sci.model.mo.EduUserInfoStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduUserInfoStatusMapper {
    int countByExample(EduUserInfoStatusExample example);

    int deleteByExample(EduUserInfoStatusExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduUserInfoStatus record);

    int insertSelective(EduUserInfoStatus record);

    List<EduUserInfoStatus> selectByExample(EduUserInfoStatusExample example);

    EduUserInfoStatus selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduUserInfoStatus record, @Param("example") EduUserInfoStatusExample example);

    int updateByExample(@Param("record") EduUserInfoStatus record, @Param("example") EduUserInfoStatusExample example);

    int updateByPrimaryKeySelective(EduUserInfoStatus record);

    int updateByPrimaryKey(EduUserInfoStatus record);
}