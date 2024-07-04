package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.IrbStamp;
import com.pinde.sci.model.mo.IrbStampExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IrbStampMapper {
    int countByExample(IrbStampExample example);

    int deleteByExample(IrbStampExample example);

    int deleteByPrimaryKey(String stampFlow);

    int insert(IrbStamp record);

    int insertSelective(IrbStamp record);

    List<IrbStamp> selectByExample(IrbStampExample example);

    IrbStamp selectByPrimaryKey(String stampFlow);

    int updateByExampleSelective(@Param("record") IrbStamp record, @Param("example") IrbStampExample example);

    int updateByExample(@Param("record") IrbStamp record, @Param("example") IrbStampExample example);

    int updateByPrimaryKeySelective(IrbStamp record);

    int updateByPrimaryKey(IrbStamp record);
}