package com.pinde.sci.dao.base;

import com.pinde.core.model.ResInprocessInfo;
import com.pinde.core.model.ResInprocessInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResInprocessInfoMapper {
    int countByExample(ResInprocessInfoExample example);

    int deleteByExample(ResInprocessInfoExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResInprocessInfo record);

    int insertSelective(ResInprocessInfo record);

    List<ResInprocessInfo> selectByExampleWithBLOBs(ResInprocessInfoExample example);

    List<ResInprocessInfo> selectByExample(ResInprocessInfoExample example);

    ResInprocessInfo selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResInprocessInfo record, @Param("example") ResInprocessInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") ResInprocessInfo record, @Param("example") ResInprocessInfoExample example);

    int updateByExample(@Param("record") ResInprocessInfo record, @Param("example") ResInprocessInfoExample example);

    int updateByPrimaryKeySelective(ResInprocessInfo record);

    int updateByPrimaryKeyWithBLOBs(ResInprocessInfo record);

    int updateByPrimaryKey(ResInprocessInfo record);
}