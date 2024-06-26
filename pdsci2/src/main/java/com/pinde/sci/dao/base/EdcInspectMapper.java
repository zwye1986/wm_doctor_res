package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcInspect;
import com.pinde.sci.model.mo.EdcInspectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcInspectMapper {
    int countByExample(EdcInspectExample example);

    int deleteByExample(EdcInspectExample example);

    int deleteByPrimaryKey(String projFlow);

    int insert(EdcInspect record);

    int insertSelective(EdcInspect record);

    List<EdcInspect> selectByExampleWithBLOBs(EdcInspectExample example);

    List<EdcInspect> selectByExample(EdcInspectExample example);

    EdcInspect selectByPrimaryKey(String projFlow);

    int updateByExampleSelective(@Param("record") EdcInspect record, @Param("example") EdcInspectExample example);

    int updateByExampleWithBLOBs(@Param("record") EdcInspect record, @Param("example") EdcInspectExample example);

    int updateByExample(@Param("record") EdcInspect record, @Param("example") EdcInspectExample example);

    int updateByPrimaryKeySelective(EdcInspect record);

    int updateByPrimaryKeyWithBLOBs(EdcInspect record);

    int updateByPrimaryKey(EdcInspect record);
}