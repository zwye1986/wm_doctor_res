package com.pinde.sci.dao.base;

import com.pinde.core.model.PubMsg;
import com.pinde.core.model.PubMsgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubMsgMapper {
    int countByExample(PubMsgExample example);

    int deleteByExample(PubMsgExample example);

    int deleteByPrimaryKey(String msgFlow);

    int insert(PubMsg record);

    int insertSelective(PubMsg record);

    List<PubMsg> selectByExampleWithBLOBs(PubMsgExample example);

    List<PubMsg> selectByExample(PubMsgExample example);

    PubMsg selectByPrimaryKey(String msgFlow);

    int updateByExampleSelective(@Param("record") PubMsg record, @Param("example") PubMsgExample example);

    int updateByExampleWithBLOBs(@Param("record") PubMsg record, @Param("example") PubMsgExample example);

    int updateByExample(@Param("record") PubMsg record, @Param("example") PubMsgExample example);

    int updateByPrimaryKeySelective(PubMsg record);

    int updateByPrimaryKeyWithBLOBs(PubMsg record);

    int updateByPrimaryKey(PubMsg record);
}