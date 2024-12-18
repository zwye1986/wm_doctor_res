package com.pinde.sci.dao.base;

import com.pinde.core.model.ResReadInfo;
import com.pinde.core.model.ResReadInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResReadInfoMapper {
    int countByExample(ResReadInfoExample example);

    int deleteByExample(ResReadInfoExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResReadInfo record);

    int insertSelective(ResReadInfo record);

    List<ResReadInfo> selectByExample(ResReadInfoExample example);

    ResReadInfo selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResReadInfo record, @Param("example") ResReadInfoExample example);

    int updateByExample(@Param("record") ResReadInfo record, @Param("example") ResReadInfoExample example);

    int updateByPrimaryKeySelective(ResReadInfo record);

    int updateByPrimaryKey(ResReadInfo record);
}