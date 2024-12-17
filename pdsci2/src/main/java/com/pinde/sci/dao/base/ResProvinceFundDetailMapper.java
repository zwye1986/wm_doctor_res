package com.pinde.sci.dao.base;

import com.pinde.core.model.ResProvinceFundDetail;
import com.pinde.core.model.ResProvinceFundDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResProvinceFundDetailMapper {
    int countByExample(ResProvinceFundDetailExample example);

    int deleteByExample(ResProvinceFundDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResProvinceFundDetail record);

    int insertSelective(ResProvinceFundDetail record);

    List<ResProvinceFundDetail> selectByExample(ResProvinceFundDetailExample example);

    ResProvinceFundDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResProvinceFundDetail record, @Param("example") ResProvinceFundDetailExample example);

    int updateByExample(@Param("record") ResProvinceFundDetail record, @Param("example") ResProvinceFundDetailExample example);

    int updateByPrimaryKeySelective(ResProvinceFundDetail record);

    int updateByPrimaryKey(ResProvinceFundDetail record);
}