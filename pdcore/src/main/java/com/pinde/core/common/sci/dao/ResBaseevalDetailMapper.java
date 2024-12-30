package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResBaseevalDetail;
import com.pinde.core.model.ResBaseevalDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResBaseevalDetailMapper {
    int countByExample(ResBaseevalDetailExample example);

    int deleteByExample(ResBaseevalDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResBaseevalDetail record);

    int insertSelective(ResBaseevalDetail record);

    List<ResBaseevalDetail> selectByExample(ResBaseevalDetailExample example);

    ResBaseevalDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResBaseevalDetail record, @Param("example") ResBaseevalDetailExample example);

    int updateByExample(@Param("record") ResBaseevalDetail record, @Param("example") ResBaseevalDetailExample example);

    int updateByPrimaryKeySelective(ResBaseevalDetail record);

    int updateByPrimaryKey(ResBaseevalDetail record);
}