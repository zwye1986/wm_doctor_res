package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcProjParam;
import com.pinde.sci.model.mo.EdcProjParamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcProjParamMapper {
    int countByExample(EdcProjParamExample example);

    int deleteByExample(EdcProjParamExample example);

    int deleteByPrimaryKey(String projFlow);

    int insert(EdcProjParam record);

    int insertSelective(EdcProjParam record);

    List<EdcProjParam> selectByExampleWithBLOBs(EdcProjParamExample example);

    List<EdcProjParam> selectByExample(EdcProjParamExample example);

    EdcProjParam selectByPrimaryKey(String projFlow);

    int updateByExampleSelective(@Param("record") EdcProjParam record, @Param("example") EdcProjParamExample example);

    int updateByExampleWithBLOBs(@Param("record") EdcProjParam record, @Param("example") EdcProjParamExample example);

    int updateByExample(@Param("record") EdcProjParam record, @Param("example") EdcProjParamExample example);

    int updateByPrimaryKeySelective(EdcProjParam record);

    int updateByPrimaryKeyWithBLOBs(EdcProjParam record);

    int updateByPrimaryKey(EdcProjParam record);
}