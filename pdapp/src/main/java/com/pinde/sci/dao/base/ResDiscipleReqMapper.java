package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResDiscipleReq;
import com.pinde.sci.model.mo.ResDiscipleReqExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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