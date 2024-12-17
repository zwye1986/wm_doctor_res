package com.pinde.sci.dao.base;

import com.pinde.core.model.SchRotationDept;
import com.pinde.core.model.SchRotationDeptExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchRotationDeptMapper {
    int countByExample(SchRotationDeptExample example);

    int deleteByExample(SchRotationDeptExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SchRotationDept record);

    int insertSelective(SchRotationDept record);

    List<SchRotationDept> selectByExampleWithBLOBs(SchRotationDeptExample example);

    List<SchRotationDept> selectByExample(SchRotationDeptExample example);

    SchRotationDept selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SchRotationDept record, @Param("example") SchRotationDeptExample example);

    int updateByExampleWithBLOBs(@Param("record") SchRotationDept record, @Param("example") SchRotationDeptExample example);

    int updateByExample(@Param("record") SchRotationDept record, @Param("example") SchRotationDeptExample example);

    int updateByPrimaryKeySelective(SchRotationDept record);

    int updateByPrimaryKeyWithBLOBs(SchRotationDept record);

    int updateByPrimaryKey(SchRotationDept record);
}