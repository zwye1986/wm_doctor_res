package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResDiscipleInfo;
import com.pinde.sci.model.mo.ResDiscipleInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDiscipleInfoMapper {
    int countByExample(ResDiscipleInfoExample example);

    int deleteByExample(ResDiscipleInfoExample example);

    int deleteByPrimaryKey(String discipleFlow);

    int insert(ResDiscipleInfo record);

    int insertSelective(ResDiscipleInfo record);

    List<ResDiscipleInfo> selectByExample(ResDiscipleInfoExample example);

    ResDiscipleInfo selectByPrimaryKey(String discipleFlow);

    int updateByExampleSelective(@Param("record") ResDiscipleInfo record, @Param("example") ResDiscipleInfoExample example);

    int updateByExample(@Param("record") ResDiscipleInfo record, @Param("example") ResDiscipleInfoExample example);

    int updateByPrimaryKeySelective(ResDiscipleInfo record);

    int updateByPrimaryKey(ResDiscipleInfo record);
}