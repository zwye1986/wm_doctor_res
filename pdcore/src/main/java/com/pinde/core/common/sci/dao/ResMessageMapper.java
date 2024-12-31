package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResMessage;
import com.pinde.core.model.ResMessageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResMessageMapper {
    int countByExample(ResMessageExample example);

    int deleteByExample(ResMessageExample example);

    int deleteByPrimaryKey(String messageFlow);

    int insert(ResMessage record);

    int insertSelective(ResMessage record);

    List<ResMessage> selectByExampleWithBLOBs(ResMessageExample example);

    List<ResMessage> selectByExample(ResMessageExample example);

    ResMessage selectByPrimaryKey(String messageFlow);

    int updateByExampleSelective(@Param("record") ResMessage record, @Param("example") ResMessageExample example);

    int updateByExampleWithBLOBs(@Param("record") ResMessage record, @Param("example") ResMessageExample example);

    int updateByExample(@Param("record") ResMessage record, @Param("example") ResMessageExample example);

    int updateByPrimaryKeySelective(ResMessage record);

    int updateByPrimaryKeyWithBLOBs(ResMessage record);

    int updateByPrimaryKey(ResMessage record);
}