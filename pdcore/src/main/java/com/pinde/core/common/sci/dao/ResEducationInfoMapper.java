package com.pinde.core.common.sci.dao;
import java.util.List;

import com.pinde.core.model.ResEducationInfo;
import com.pinde.core.model.ResEducationInfoExample;
import org.apache.ibatis.annotations.Param;

public interface ResEducationInfoMapper {
    int countByExample(ResEducationInfoExample example);

    int deleteByExample(ResEducationInfoExample example);

    int deleteByPrimaryKey(String educationFlow);

    int insert(ResEducationInfo record);

    int insertSelective(ResEducationInfo record);

    List<ResEducationInfo> selectByExample(ResEducationInfoExample example);

    ResEducationInfo selectByPrimaryKey(String educationFlow);

    int updateByExampleSelective(@Param("record") ResEducationInfo record, @Param("example") ResEducationInfoExample example);

    int updateByExample(@Param("record") ResEducationInfo record, @Param("example") ResEducationInfoExample example);

    int updateByPrimaryKeySelective(ResEducationInfo record);

    int updateByPrimaryKey(ResEducationInfo record);
}