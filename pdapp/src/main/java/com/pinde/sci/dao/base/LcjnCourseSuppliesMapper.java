package com.pinde.sci.dao.base;

import com.pinde.core.model.LcjnCourseSupplies;
import com.pinde.core.model.LcjnCourseSuppliesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LcjnCourseSuppliesMapper {
    int countByExample(LcjnCourseSuppliesExample example);

    int deleteByExample(LcjnCourseSuppliesExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(LcjnCourseSupplies record);

    int insertSelective(LcjnCourseSupplies record);

    List<LcjnCourseSupplies> selectByExample(LcjnCourseSuppliesExample example);

    LcjnCourseSupplies selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") LcjnCourseSupplies record, @Param("example") LcjnCourseSuppliesExample example);

    int updateByExample(@Param("record") LcjnCourseSupplies record, @Param("example") LcjnCourseSuppliesExample example);

    int updateByPrimaryKeySelective(LcjnCourseSupplies record);

    int updateByPrimaryKey(LcjnCourseSupplies record);
}