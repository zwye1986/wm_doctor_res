package com.pinde.sci.dao.base;

import com.pinde.core.model.SchExternalDept;
import com.pinde.core.model.SchExternalDeptExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchExternalDeptMapper {
    int countByExample(SchExternalDeptExample example);

    int deleteByExample(SchExternalDeptExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SchExternalDept record);

    int insertSelective(SchExternalDept record);

    List<SchExternalDept> selectByExample(SchExternalDeptExample example);

    SchExternalDept selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SchExternalDept record, @Param("example") SchExternalDeptExample example);

    int updateByExample(@Param("record") SchExternalDept record, @Param("example") SchExternalDeptExample example);

    int updateByPrimaryKeySelective(SchExternalDept record);

    int updateByPrimaryKey(SchExternalDept record);
}