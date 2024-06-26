package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.RecruitInfoLog;
import com.pinde.sci.model.mo.RecruitInfoLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecruitInfoLogMapper {
    int countByExample(RecruitInfoLogExample example);

    int deleteByExample(RecruitInfoLogExample example);

    int deleteByPrimaryKey(String logFlow);

    int insert(RecruitInfoLog record);

    int insertSelective(RecruitInfoLog record);

    List<RecruitInfoLog> selectByExample(RecruitInfoLogExample example);

    RecruitInfoLog selectByPrimaryKey(String logFlow);

    int updateByExampleSelective(@Param("record") RecruitInfoLog record, @Param("example") RecruitInfoLogExample example);

    int updateByExample(@Param("record") RecruitInfoLog record, @Param("example") RecruitInfoLogExample example);

    int updateByPrimaryKeySelective(RecruitInfoLog record);

    int updateByPrimaryKey(RecruitInfoLog record);
}