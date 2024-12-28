package com.pinde.core.common.sci.dao;

import com.pinde.core.model.OscaFrom;
import com.pinde.core.model.OscaFromExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OscaFromMapper {
    int countByExample(OscaFromExample example);

    int deleteByExample(OscaFromExample example);

    int deleteByPrimaryKey(String fromFlow);

    int insert(OscaFrom record);

    int insertSelective(OscaFrom record);

    List<OscaFrom> selectByExampleWithBLOBs(OscaFromExample example);

    List<OscaFrom> selectByExample(OscaFromExample example);

    OscaFrom selectByPrimaryKey(String fromFlow);

    int updateByExampleSelective(@Param("record") OscaFrom record, @Param("example") OscaFromExample example);

    int updateByExampleWithBLOBs(@Param("record") OscaFrom record, @Param("example") OscaFromExample example);

    int updateByExample(@Param("record") OscaFrom record, @Param("example") OscaFromExample example);

    int updateByPrimaryKeySelective(OscaFrom record);

    int updateByPrimaryKeyWithBLOBs(OscaFrom record);

    int updateByPrimaryKey(OscaFrom record);
}