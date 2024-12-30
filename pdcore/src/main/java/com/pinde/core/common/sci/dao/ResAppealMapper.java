package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResAppeal;
import com.pinde.core.model.ResAppealExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResAppealMapper {
    int countByExample(ResAppealExample example);

    int deleteByExample(ResAppealExample example);

    int deleteByPrimaryKey(String appealFlow);

    int insert(ResAppeal record);

    int insertSelective(ResAppeal record);

    List<ResAppeal> selectByExample(ResAppealExample example);

    ResAppeal selectByPrimaryKey(String appealFlow);

    int updateByExampleSelective(@Param("record") ResAppeal record, @Param("example") ResAppealExample example);

    int updateByExample(@Param("record") ResAppeal record, @Param("example") ResAppealExample example);

    int updateByPrimaryKeySelective(ResAppeal record);

    int updateByPrimaryKey(ResAppeal record);
}