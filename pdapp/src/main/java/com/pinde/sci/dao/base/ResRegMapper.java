package com.pinde.sci.dao.base;

import com.pinde.core.model.ResReg;
import com.pinde.core.model.ResRegExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResRegMapper {
    int countByExample(ResRegExample example);

    int deleteByExample(ResRegExample example);

    int deleteByPrimaryKey(String regFlow);

    int insert(ResReg record);

    int insertSelective(ResReg record);

    List<ResReg> selectByExample(ResRegExample example);

    ResReg selectByPrimaryKey(String regFlow);

    int updateByExampleSelective(@Param("record") ResReg record, @Param("example") ResRegExample example);

    int updateByExample(@Param("record") ResReg record, @Param("example") ResRegExample example);

    int updateByPrimaryKeySelective(ResReg record);

    int updateByPrimaryKey(ResReg record);
}