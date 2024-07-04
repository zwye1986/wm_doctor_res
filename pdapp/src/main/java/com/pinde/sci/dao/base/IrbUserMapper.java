package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.IrbUser;
import com.pinde.sci.model.mo.IrbUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IrbUserMapper {
    int countByExample(IrbUserExample example);

    int deleteByExample(IrbUserExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(IrbUser record);

    int insertSelective(IrbUser record);

    List<IrbUser> selectByExample(IrbUserExample example);

    IrbUser selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") IrbUser record, @Param("example") IrbUserExample example);

    int updateByExample(@Param("record") IrbUser record, @Param("example") IrbUserExample example);

    int updateByPrimaryKeySelective(IrbUser record);

    int updateByPrimaryKey(IrbUser record);
}