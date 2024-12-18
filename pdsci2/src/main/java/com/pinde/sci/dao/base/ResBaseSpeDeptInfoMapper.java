package com.pinde.sci.dao.base;

import com.pinde.core.model.ResBaseSpeDeptInfo;
import com.pinde.core.model.ResBaseSpeDeptInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResBaseSpeDeptInfoMapper {
    int countByExample(ResBaseSpeDeptInfoExample example);

    int deleteByExample(ResBaseSpeDeptInfoExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResBaseSpeDeptInfo record);

    int insertSelective(ResBaseSpeDeptInfo record);

    List<ResBaseSpeDeptInfo> selectByExample(ResBaseSpeDeptInfoExample example);

    ResBaseSpeDeptInfo selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResBaseSpeDeptInfo record, @Param("example") ResBaseSpeDeptInfoExample example);

    int updateByExample(@Param("record") ResBaseSpeDeptInfo record, @Param("example") ResBaseSpeDeptInfoExample example);

    int updateByPrimaryKeySelective(ResBaseSpeDeptInfo record);

    int updateByPrimaryKey(ResBaseSpeDeptInfo record);
}