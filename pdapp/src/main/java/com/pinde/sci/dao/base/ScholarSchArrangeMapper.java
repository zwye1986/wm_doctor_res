package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ScholarSchArrange;
import com.pinde.sci.model.mo.ScholarSchArrangeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScholarSchArrangeMapper {
    int countByExample(ScholarSchArrangeExample example);

    int deleteByExample(ScholarSchArrangeExample example);

    int deleteByPrimaryKey(String arrangeFlow);

    int insert(ScholarSchArrange record);

    int insertSelective(ScholarSchArrange record);

    List<ScholarSchArrange> selectByExample(ScholarSchArrangeExample example);

    ScholarSchArrange selectByPrimaryKey(String arrangeFlow);

    int updateByExampleSelective(@Param("record") ScholarSchArrange record, @Param("example") ScholarSchArrangeExample example);

    int updateByExample(@Param("record") ScholarSchArrange record, @Param("example") ScholarSchArrangeExample example);

    int updateByPrimaryKeySelective(ScholarSchArrange record);

    int updateByPrimaryKey(ScholarSchArrange record);
}