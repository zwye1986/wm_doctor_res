package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResReg;
import com.pinde.sci.model.mo.ResRegExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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