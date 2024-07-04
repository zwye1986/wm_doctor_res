package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcVisitModule;
import com.pinde.sci.model.mo.EdcVisitModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcVisitModuleMapper {
    int countByExample(EdcVisitModuleExample example);

    int deleteByExample(EdcVisitModuleExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EdcVisitModule record);

    int insertSelective(EdcVisitModule record);

    List<EdcVisitModule> selectByExample(EdcVisitModuleExample example);

    EdcVisitModule selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EdcVisitModule record, @Param("example") EdcVisitModuleExample example);

    int updateByExample(@Param("record") EdcVisitModule record, @Param("example") EdcVisitModuleExample example);

    int updateByPrimaryKeySelective(EdcVisitModule record);

    int updateByPrimaryKey(EdcVisitModule record);
}