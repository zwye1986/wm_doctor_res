package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SchArrangePeriodRel;
import com.pinde.sci.model.mo.SchArrangePeriodRelExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchArrangePeriodRelMapper {
    int countByExample(SchArrangePeriodRelExample example);

    int deleteByExample(SchArrangePeriodRelExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SchArrangePeriodRel record);

    int insertSelective(SchArrangePeriodRel record);

    List<SchArrangePeriodRel> selectByExample(SchArrangePeriodRelExample example);

    SchArrangePeriodRel selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SchArrangePeriodRel record, @Param("example") SchArrangePeriodRelExample example);

    int updateByExample(@Param("record") SchArrangePeriodRel record, @Param("example") SchArrangePeriodRelExample example);

    int updateByPrimaryKeySelective(SchArrangePeriodRel record);

    int updateByPrimaryKey(SchArrangePeriodRel record);
}