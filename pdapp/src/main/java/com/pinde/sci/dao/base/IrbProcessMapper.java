package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.IrbProcess;
import com.pinde.sci.model.mo.IrbProcessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IrbProcessMapper {
    int countByExample(IrbProcessExample example);

    int deleteByExample(IrbProcessExample example);

    int deleteByPrimaryKey(String processFlow);

    int insert(IrbProcess record);

    int insertSelective(IrbProcess record);

    List<IrbProcess> selectByExample(IrbProcessExample example);

    IrbProcess selectByPrimaryKey(String processFlow);

    int updateByExampleSelective(@Param("record") IrbProcess record, @Param("example") IrbProcessExample example);

    int updateByExample(@Param("record") IrbProcess record, @Param("example") IrbProcessExample example);

    int updateByPrimaryKeySelective(IrbProcess record);

    int updateByPrimaryKey(IrbProcess record);
}