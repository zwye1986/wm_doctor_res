package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResBaseFundDetail;
import com.pinde.sci.model.mo.ResBaseFundDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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