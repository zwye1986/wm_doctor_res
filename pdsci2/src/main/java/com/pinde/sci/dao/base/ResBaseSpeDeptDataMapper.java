package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResBaseSpeDeptData;
import com.pinde.sci.model.mo.ResBaseSpeDeptDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResBaseSpeDeptDataMapper {
    int countByExample(ResBaseSpeDeptDataExample example);

    int deleteByExample(ResBaseSpeDeptDataExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResBaseSpeDeptData record);

    int insertSelective(ResBaseSpeDeptData record);

    List<ResBaseSpeDeptData> selectByExample(ResBaseSpeDeptDataExample example);

    ResBaseSpeDeptData selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResBaseSpeDeptData record, @Param("example") ResBaseSpeDeptDataExample example);

    int updateByExample(@Param("record") ResBaseSpeDeptData record, @Param("example") ResBaseSpeDeptDataExample example);

    int updateByPrimaryKeySelective(ResBaseSpeDeptData record);

    int updateByPrimaryKey(ResBaseSpeDeptData record);
}