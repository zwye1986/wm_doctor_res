package com.pinde.sci.dao.base;

import com.pinde.core.model.SysDept;
import com.pinde.core.model.SysDeptExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDeptMapper {
    int countByExample(SysDeptExample example);

    int deleteByExample(SysDeptExample example);

    int deleteByPrimaryKey(String deptFlow);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    List<SysDept> selectByExample(SysDeptExample example);

    SysDept selectByPrimaryKey(String deptFlow);

    int updateByExampleSelective(@Param("record") SysDept record, @Param("example") SysDeptExample example);

    int updateByExample(@Param("record") SysDept record, @Param("example") SysDeptExample example);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    int deleteDeptByKey(@Param("deptFlow") String deptFlow);
}