package com.pinde.sci.dao.base;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PubUserResumeMapper {
    int countByExample(PubUserResumeExample example);

    int deleteByExample(PubUserResumeExample example);

    int deleteByPrimaryKey(String userFlow);

    int insert(PubUserResume record);

    int insertSelective(PubUserResume record);

    List<PubUserResume> selectByExampleWithBLOBs(PubUserResumeExample example);

    List<PubUserResume> selectByExample(PubUserResumeExample example);

    PubUserResume selectByPrimaryKey(String userFlow);

    int updateByExampleSelective(@Param("record") PubUserResume record, @Param("example") PubUserResumeExample example);

    int updateByExampleWithBLOBs(@Param("record") PubUserResume record, @Param("example") PubUserResumeExample example);

    int updateByExample(@Param("record") PubUserResume record, @Param("example") PubUserResumeExample example);

    int updateByPrimaryKeySelective(PubUserResume record);

    int updateByPrimaryKeyWithBLOBs(PubUserResume record);

    int updateByPrimaryKey(PubUserResume record);
}