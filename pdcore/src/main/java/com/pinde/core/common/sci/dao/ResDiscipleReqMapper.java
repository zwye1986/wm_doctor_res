package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResDiscipleReq;
import com.pinde.core.model.ResDiscipleReqExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDiscipleReqMapper {
    int countByExample(ResDiscipleReqExample example);

    int deleteByExample(ResDiscipleReqExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResDiscipleReq record);

    int insertSelective(ResDiscipleReq record);

    List<ResDiscipleReq> selectByExample(ResDiscipleReqExample example);

    ResDiscipleReq selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResDiscipleReq record, @Param("example") ResDiscipleReqExample example);

    int updateByExample(@Param("record") ResDiscipleReq record, @Param("example") ResDiscipleReqExample example);

    int updateByPrimaryKeySelective(ResDiscipleReq record);

    int updateByPrimaryKey(ResDiscipleReq record);
}