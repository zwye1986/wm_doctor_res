package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.IrbInfoUser;
import com.pinde.sci.model.mo.IrbInfoUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IrbInfoUserMapper {
    int countByExample(IrbInfoUserExample example);

    int deleteByExample(IrbInfoUserExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(IrbInfoUser record);

    int insertSelective(IrbInfoUser record);

    List<IrbInfoUser> selectByExample(IrbInfoUserExample example);

    IrbInfoUser selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") IrbInfoUser record, @Param("example") IrbInfoUserExample example);

    int updateByExample(@Param("record") IrbInfoUser record, @Param("example") IrbInfoUserExample example);

    int updateByPrimaryKeySelective(IrbInfoUser record);

    int updateByPrimaryKey(IrbInfoUser record);
}