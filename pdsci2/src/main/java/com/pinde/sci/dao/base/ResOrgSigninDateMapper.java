package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResOrgSigninDate;
import com.pinde.sci.model.mo.ResOrgSigninDateExample;
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