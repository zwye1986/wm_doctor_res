package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResBaseFundDetail;
import com.pinde.core.model.ResBaseFundDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResBaseFundDetailMapper {
    int countByExample(ResBaseFundDetailExample example);

    int deleteByExample(ResBaseFundDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResBaseFundDetail record);

    int insertSelective(ResBaseFundDetail record);

    List<ResBaseFundDetail> selectByExample(ResBaseFundDetailExample example);

    ResBaseFundDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResBaseFundDetail record, @Param("example") ResBaseFundDetailExample example);

    int updateByExample(@Param("record") ResBaseFundDetail record, @Param("example") ResBaseFundDetailExample example);

    int updateByPrimaryKeySelective(ResBaseFundDetail record);

    int updateByPrimaryKey(ResBaseFundDetail record);
}