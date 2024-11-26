package com.pinde.sci.dao.base;

import com.pinde.core.model.SchDeptRel;
import com.pinde.core.model.SchDeptRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchDeptRelMapper {
    int countByExample(SchDeptRelExample example);

    int deleteByExample(SchDeptRelExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SchDeptRel record);

    int insertSelective(SchDeptRel record);

    List<SchDeptRel> selectByExample(SchDeptRelExample example);

    SchDeptRel selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SchDeptRel record, @Param("example") SchDeptRelExample example);

    int updateByExample(@Param("record") SchDeptRel record, @Param("example") SchDeptRelExample example);

    int updateByPrimaryKeySelective(SchDeptRel record);

    int updateByPrimaryKey(SchDeptRel record);
}