package com.pinde.sci.dao.base;

import com.pinde.core.model.SchArrangeTime;
import com.pinde.core.model.SchArrangeTimeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchArrangeTimeMapper {
    int countByExample(SchArrangeTimeExample example);

    int deleteByExample(SchArrangeTimeExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SchArrangeTime record);

    int insertSelective(SchArrangeTime record);

    List<SchArrangeTime> selectByExample(SchArrangeTimeExample example);

    SchArrangeTime selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SchArrangeTime record, @Param("example") SchArrangeTimeExample example);

    int updateByExample(@Param("record") SchArrangeTime record, @Param("example") SchArrangeTimeExample example);

    int updateByPrimaryKeySelective(SchArrangeTime record);

    int updateByPrimaryKey(SchArrangeTime record);
}