package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SchRotationDept;
import com.pinde.sci.model.mo.SchRotationDeptExample;
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

    /**
     * 定制sql，条件是定制的，不通用
     */
    List<SchRotationDept> selectListWithBLOBs(@Param("rotationFlowList") List<String> rotationFlowList, @Param("standardDeptIdList") List<String> standardDeptIdList, @Param("groupFlowList") List<String> groupFlowList);

    /**
     * 定制sql，条件是定制的，不通用
     * @param deptFlowList
     * @param groupFlowList
     * @return
     */
    List<SchRotationDept> selectListByGroupAndDept(@Param("deptFlowList") List<String> deptFlowList, @Param("groupFlowList") List<String> groupFlowList);

    List<SchRotationDept> searchSchRotationDeptByPartitionList(@Param("rotationFlowListList") List<List<String>> rotationFlowListList);

    SchRotationDept selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SchRotationDept record, @Param("example") SchRotationDeptExample example);

    int updateByExampleWithBLOBs(@Param("record") SchRotationDept record, @Param("example") SchRotationDeptExample example);

    int updateByExample(@Param("record") SchRotationDept record, @Param("example") SchRotationDeptExample example);

    int updateByPrimaryKeySelective(SchRotationDept record);

    int updateByPrimaryKeyWithBLOBs(SchRotationDept record);

    int updateByPrimaryKey(SchRotationDept record);
}