package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResBaseFund;
import com.pinde.sci.model.mo.ResBaseFundExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResBaseFundMapper {
    int countByExample(ResBaseFundExample example);

    int deleteByExample(ResBaseFundExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResBaseFund record);

    int insertSelective(ResBaseFund record);

    List<ResBaseFund> selectByExample(ResBaseFundExample example);

    ResBaseFund selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResBaseFund record, @Param("example") ResBaseFundExample example);

    int updateByExample(@Param("record") ResBaseFund record, @Param("example") ResBaseFundExample example);

    int updateByPrimaryKeySelective(ResBaseFund record);

    int updateByPrimaryKey(ResBaseFund record);
}