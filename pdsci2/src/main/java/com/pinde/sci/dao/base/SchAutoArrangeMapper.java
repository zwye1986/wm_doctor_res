package com.pinde.sci.dao.base;

import com.pinde.core.model.SchAutoArrange;
import com.pinde.core.model.SchAutoArrangeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchAutoArrangeMapper {
    int countByExample(SchAutoArrangeExample example);

    int deleteByExample(SchAutoArrangeExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SchAutoArrange record);

    int insertSelective(SchAutoArrange record);

    List<SchAutoArrange> selectByExample(SchAutoArrangeExample example);

    SchAutoArrange selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SchAutoArrange record, @Param("example") SchAutoArrangeExample example);

    int updateByExample(@Param("record") SchAutoArrange record, @Param("example") SchAutoArrangeExample example);

    int updateByPrimaryKeySelective(SchAutoArrange record);

    int updateByPrimaryKey(SchAutoArrange record);
}