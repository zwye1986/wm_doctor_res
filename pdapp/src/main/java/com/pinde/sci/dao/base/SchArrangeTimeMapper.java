package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SchArrangeTime;
import com.pinde.sci.model.mo.SchArrangeTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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