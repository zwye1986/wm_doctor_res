package com.pinde.sci.dao.base;

import com.pinde.core.model.StuUserResume;
import com.pinde.core.model.StuUserResumeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StuUserResumeMapper {
    int countByExample(StuUserResumeExample example);

    int deleteByExample(StuUserResumeExample example);

    int deleteByPrimaryKey(String resumeFlow);

    int insert(StuUserResume record);

    int insertSelective(StuUserResume record);

    List<StuUserResume> selectByExampleWithBLOBs(StuUserResumeExample example);

    List<StuUserResume> selectByExample(StuUserResumeExample example);

    StuUserResume selectByPrimaryKey(String resumeFlow);

    int updateByExampleSelective(@Param("record") StuUserResume record, @Param("example") StuUserResumeExample example);

    int updateByExampleWithBLOBs(@Param("record") StuUserResume record, @Param("example") StuUserResumeExample example);

    int updateByExample(@Param("record") StuUserResume record, @Param("example") StuUserResumeExample example);

    int updateByPrimaryKeySelective(StuUserResume record);

    int updateByPrimaryKeyWithBLOBs(StuUserResume record);

    int updateByPrimaryKey(StuUserResume record);
}