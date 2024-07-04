package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcModule;
import com.pinde.sci.model.mo.EdcModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcModuleMapper {
    int countByExample(EdcModuleExample example);

    int deleteByExample(EdcModuleExample example);

    int deleteByPrimaryKey(String moduleFlow);

    int insert(EdcModule record);

    int insertSelective(EdcModule record);

    List<EdcModule> selectByExample(EdcModuleExample example);

    EdcModule selectByPrimaryKey(String moduleFlow);

    int updateByExampleSelective(@Param("record") EdcModule record, @Param("example") EdcModuleExample example);

    int updateByExample(@Param("record") EdcModule record, @Param("example") EdcModuleExample example);

    int updateByPrimaryKeySelective(EdcModule record);

    int updateByPrimaryKey(EdcModule record);
}