package com.pinde.sci.dao.base;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResIdctrlDetailMapper {
    int countByExample(ResIdctrlDetailExample example);

    int deleteByExample(ResIdctrlDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResIdctrlDetail record);

    int insertSelective(ResIdctrlDetail record);

    List<ResIdctrlDetail> selectByExample(ResIdctrlDetailExample example);

    ResIdctrlDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResIdctrlDetail record, @Param("example") ResIdctrlDetailExample example);

    int updateByExample(@Param("record") ResIdctrlDetail record, @Param("example") ResIdctrlDetailExample example);

    int updateByPrimaryKeySelective(ResIdctrlDetail record);

    int updateByPrimaryKey(ResIdctrlDetail record);
}