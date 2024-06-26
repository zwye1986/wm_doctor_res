package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.IrbApply;
import com.pinde.sci.model.mo.IrbApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IrbApplyMapper {
    int countByExample(IrbApplyExample example);

    int deleteByExample(IrbApplyExample example);

    int deleteByPrimaryKey(String irbFlow);

    int insert(IrbApply record);

    int insertSelective(IrbApply record);

    List<IrbApply> selectByExample(IrbApplyExample example);

    IrbApply selectByPrimaryKey(String irbFlow);

    int updateByExampleSelective(@Param("record") IrbApply record, @Param("example") IrbApplyExample example);

    int updateByExample(@Param("record") IrbApply record, @Param("example") IrbApplyExample example);

    int updateByPrimaryKeySelective(IrbApply record);

    int updateByPrimaryKey(IrbApply record);
}