package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.TeachingActivityFormValue;
import com.pinde.sci.model.mo.TeachingActivityFormValueExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeachingActivityFormValueMapper {
    int countByExample(TeachingActivityFormValueExample example);

    int deleteByExample(TeachingActivityFormValueExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(TeachingActivityFormValue record);

    int insertSelective(TeachingActivityFormValue record);

    List<TeachingActivityFormValue> selectByExample(TeachingActivityFormValueExample example);

    TeachingActivityFormValue selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") TeachingActivityFormValue record, @Param("example") TeachingActivityFormValueExample example);

    int updateByExample(@Param("record") TeachingActivityFormValue record, @Param("example") TeachingActivityFormValueExample example);

    int updateByPrimaryKeySelective(TeachingActivityFormValue record);

    int updateByPrimaryKey(TeachingActivityFormValue record);
}