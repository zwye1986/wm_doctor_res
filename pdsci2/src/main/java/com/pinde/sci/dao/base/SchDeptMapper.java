package com.pinde.sci.dao.base;

import com.pinde.core.model.SchDept;
import com.pinde.core.model.SchDeptExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchDeptMapper {
    int countByExample(SchDeptExample example);

    int deleteByExample(SchDeptExample example);

    int deleteByPrimaryKey(String schDeptFlow);

    int insert(SchDept record);

    int insertSelective(SchDept record);

    List<SchDept> selectByExample(SchDeptExample example);

    SchDept selectByPrimaryKey(String schDeptFlow);

    int updateByExampleSelective(@Param("record") SchDept record, @Param("example") SchDeptExample example);

    int updateByExample(@Param("record") SchDept record, @Param("example") SchDeptExample example);

    int updateByPrimaryKeySelective(SchDept record);

    int updateByPrimaryKey(SchDept record);
}