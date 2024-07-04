package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcDcf;
import com.pinde.sci.model.mo.EdcDcfExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcDcfMapper {
    int countByExample(EdcDcfExample example);

    int deleteByExample(EdcDcfExample example);

    int deleteByPrimaryKey(String dcfFlow);

    int insert(EdcDcf record);

    int insertSelective(EdcDcf record);

    List<EdcDcf> selectByExample(EdcDcfExample example);

    EdcDcf selectByPrimaryKey(String dcfFlow);

    int updateByExampleSelective(@Param("record") EdcDcf record, @Param("example") EdcDcfExample example);

    int updateByExample(@Param("record") EdcDcf record, @Param("example") EdcDcfExample example);

    int updateByPrimaryKeySelective(EdcDcf record);

    int updateByPrimaryKey(EdcDcf record);
}