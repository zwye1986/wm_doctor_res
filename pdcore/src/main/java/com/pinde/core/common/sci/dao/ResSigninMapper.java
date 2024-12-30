package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResSignin;
import com.pinde.core.model.ResSigninExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResSigninMapper {
    int countByExample(ResSigninExample example);

    int deleteByExample(ResSigninExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResSignin record);

    int insertSelective(ResSignin record);

    List<ResSignin> selectByExample(ResSigninExample example);

    ResSignin selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResSignin record, @Param("example") ResSigninExample example);

    int updateByExample(@Param("record") ResSignin record, @Param("example") ResSigninExample example);

    int updateByPrimaryKeySelective(ResSignin record);

    int updateByPrimaryKey(ResSignin record);
}