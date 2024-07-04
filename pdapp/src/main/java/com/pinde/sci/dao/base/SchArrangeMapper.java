package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SchArrange;
import com.pinde.sci.model.mo.SchArrangeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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