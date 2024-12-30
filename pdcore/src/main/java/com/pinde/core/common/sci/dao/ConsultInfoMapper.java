package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ConsultInfo;
import com.pinde.core.model.ConsultInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsultInfoMapper {
    int countByExample(ConsultInfoExample example);

    int deleteByExample(ConsultInfoExample example);

    int deleteByPrimaryKey(String consultInfoFlow);

    int insert(ConsultInfo record);

    int insertSelective(ConsultInfo record);

    List<ConsultInfo> selectByExampleWithBLOBs(ConsultInfoExample example);

    List<ConsultInfo> selectByExample(ConsultInfoExample example);

    ConsultInfo selectByPrimaryKey(String consultInfoFlow);

    int updateByExampleSelective(@Param("record") ConsultInfo record, @Param("example") ConsultInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") ConsultInfo record, @Param("example") ConsultInfoExample example);

    int updateByExample(@Param("record") ConsultInfo record, @Param("example") ConsultInfoExample example);

    int updateByPrimaryKeySelective(ConsultInfo record);

    int updateByPrimaryKeyWithBLOBs(ConsultInfo record);

    int updateByPrimaryKey(ConsultInfo record);
}