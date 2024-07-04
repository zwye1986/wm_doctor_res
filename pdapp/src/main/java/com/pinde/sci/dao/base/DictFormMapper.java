package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.DictForm;
import com.pinde.sci.model.mo.DictFormExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictFormMapper {
    int countByExample(DictFormExample example);

    int deleteByExample(DictFormExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(DictForm record);

    int insertSelective(DictForm record);

    List<DictForm> selectByExample(DictFormExample example);

    DictForm selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") DictForm record, @Param("example") DictFormExample example);

    int updateByExample(@Param("record") DictForm record, @Param("example") DictFormExample example);

    int updateByPrimaryKeySelective(DictForm record);

    int updateByPrimaryKey(DictForm record);
}