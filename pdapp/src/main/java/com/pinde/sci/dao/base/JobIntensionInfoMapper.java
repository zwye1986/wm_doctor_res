package com.pinde.sci.dao.base;

import com.pinde.core.model.JobIntensionInfo;
import com.pinde.core.model.JobIntensionInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobIntensionInfoMapper {
    int countByExample(JobIntensionInfoExample example);

    int deleteByExample(JobIntensionInfoExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JobIntensionInfo record);

    int insertSelective(JobIntensionInfo record);

    List<JobIntensionInfo> selectByExample(JobIntensionInfoExample example);

    JobIntensionInfo selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JobIntensionInfo record, @Param("example") JobIntensionInfoExample example);

    int updateByExample(@Param("record") JobIntensionInfo record, @Param("example") JobIntensionInfoExample example);

    int updateByPrimaryKeySelective(JobIntensionInfo record);

    int updateByPrimaryKey(JobIntensionInfo record);
}