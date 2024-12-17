package com.pinde.sci.dao.base;

import com.pinde.core.model.SchArrange;
import com.pinde.core.model.SchArrangeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchArrangeMapper {
    int countByExample(SchArrangeExample example);

    int deleteByExample(SchArrangeExample example);

    int deleteByPrimaryKey(String arrangeFlow);

    int insert(SchArrange record);

    int insertSelective(SchArrange record);

    List<SchArrange> selectByExample(SchArrangeExample example);

    SchArrange selectByPrimaryKey(String arrangeFlow);

    int updateByExampleSelective(@Param("record") SchArrange record, @Param("example") SchArrangeExample example);

    int updateByExample(@Param("record") SchArrange record, @Param("example") SchArrangeExample example);

    int updateByPrimaryKeySelective(SchArrange record);

    int updateByPrimaryKey(SchArrange record);
}