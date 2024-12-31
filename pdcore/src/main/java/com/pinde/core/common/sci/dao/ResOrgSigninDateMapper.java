package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResOrgSigninDate;
import com.pinde.core.model.ResOrgSigninDateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResOrgSigninDateMapper {
    int countByExample(ResOrgSigninDateExample example);

    int deleteByExample(ResOrgSigninDateExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResOrgSigninDate record);

    int insertSelective(ResOrgSigninDate record);

    List<ResOrgSigninDate> selectByExample(ResOrgSigninDateExample example);

    ResOrgSigninDate selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResOrgSigninDate record, @Param("example") ResOrgSigninDateExample example);

    int updateByExample(@Param("record") ResOrgSigninDate record, @Param("example") ResOrgSigninDateExample example);

    int updateByPrimaryKeySelective(ResOrgSigninDate record);

    int updateByPrimaryKey(ResOrgSigninDate record);
}