package com.pinde.sci.dao.base;

import com.pinde.core.model.SchRotationOrgDept;
import com.pinde.core.model.SchRotationOrgDeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchRotationOrgDeptMapper {
    int countByExample(SchRotationOrgDeptExample example);

    int deleteByExample(SchRotationOrgDeptExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SchRotationOrgDept record);

    int insertSelective(SchRotationOrgDept record);

    List<SchRotationOrgDept> selectByExampleWithBLOBs(SchRotationOrgDeptExample example);

    List<SchRotationOrgDept> selectByExample(SchRotationOrgDeptExample example);

    SchRotationOrgDept selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SchRotationOrgDept record, @Param("example") SchRotationOrgDeptExample example);

    int updateByExampleWithBLOBs(@Param("record") SchRotationOrgDept record, @Param("example") SchRotationOrgDeptExample example);

    int updateByExample(@Param("record") SchRotationOrgDept record, @Param("example") SchRotationOrgDeptExample example);

    int updateByPrimaryKeySelective(SchRotationOrgDept record);

    int updateByPrimaryKeyWithBLOBs(SchRotationOrgDept record);

    int updateByPrimaryKey(SchRotationOrgDept record);
}