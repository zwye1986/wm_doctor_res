package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.IrbInfo;
import com.pinde.sci.model.mo.IrbInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IrbInfoMapper {
    int countByExample(IrbInfoExample example);

    int deleteByExample(IrbInfoExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(IrbInfo record);

    int insertSelective(IrbInfo record);

    List<IrbInfo> selectByExample(IrbInfoExample example);

    IrbInfo selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") IrbInfo record, @Param("example") IrbInfoExample example);

    int updateByExample(@Param("record") IrbInfo record, @Param("example") IrbInfoExample example);

    int updateByPrimaryKeySelective(IrbInfo record);

    int updateByPrimaryKey(IrbInfo record);
}