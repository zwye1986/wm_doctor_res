package com.pinde.sci.dao.base;

import com.pinde.core.model.ResBase;
import com.pinde.core.model.ResBaseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResBaseMapper {
    int countByExample(ResBaseExample example);

    int deleteByExample(ResBaseExample example);

    int deleteByPrimaryKey(String orgFlow);

    int insert(ResBase record);

    int insertSelective(ResBase record);

    List<ResBase> selectByExampleWithBLOBs(ResBaseExample example);

    List<ResBase> selectByExample(ResBaseExample example);

    ResBase selectByPrimaryKey(String orgFlow);

    int updateByExampleSelective(@Param("record") ResBase record, @Param("example") ResBaseExample example);

    int updateByExampleWithBLOBs(@Param("record") ResBase record, @Param("example") ResBaseExample example);

    int updateByExample(@Param("record") ResBase record, @Param("example") ResBaseExample example);

    int updateByPrimaryKeySelective(ResBase record);

    int updateByPrimaryKeyWithBLOBs(ResBase record);

    int updateByPrimaryKey(ResBase record);
}