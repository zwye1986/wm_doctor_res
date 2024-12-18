package com.pinde.sci.dao.base;

import com.pinde.core.model.ResProvinceFund;
import com.pinde.core.model.ResProvinceFundExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResProvinceFundMapper {
    int countByExample(ResProvinceFundExample example);

    int deleteByExample(ResProvinceFundExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResProvinceFund record);

    int insertSelective(ResProvinceFund record);

    List<ResProvinceFund> selectByExample(ResProvinceFundExample example);

    ResProvinceFund selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResProvinceFund record, @Param("example") ResProvinceFundExample example);

    int updateByExample(@Param("record") ResProvinceFund record, @Param("example") ResProvinceFundExample example);

    int updateByPrimaryKeySelective(ResProvinceFund record);

    int updateByPrimaryKey(ResProvinceFund record);
}