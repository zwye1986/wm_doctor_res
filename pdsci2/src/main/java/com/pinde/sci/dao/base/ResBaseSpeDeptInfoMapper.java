package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResBaseSpeDeptInfo;
import com.pinde.sci.model.mo.ResBaseSpeDeptInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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