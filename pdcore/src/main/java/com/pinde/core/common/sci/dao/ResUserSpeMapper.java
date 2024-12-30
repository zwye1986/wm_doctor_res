package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResUserSpe;
import com.pinde.core.model.ResUserSpeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResUserSpeMapper {
    int countByExample(ResUserSpeExample example);

    int deleteByExample(ResUserSpeExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResUserSpe record);

    int insertSelective(ResUserSpe record);

    List<ResUserSpe> selectByExample(ResUserSpeExample example);

    ResUserSpe selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResUserSpe record, @Param("example") ResUserSpeExample example);

    int updateByExample(@Param("record") ResUserSpe record, @Param("example") ResUserSpeExample example);

    int updateByPrimaryKeySelective(ResUserSpe record);

    int updateByPrimaryKey(ResUserSpe record);
}