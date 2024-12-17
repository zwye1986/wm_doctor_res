package com.pinde.sci.dao.base;

import com.pinde.core.model.SchRotationDeptAfter;
import com.pinde.core.model.SchRotationDeptAfterExample;
import com.pinde.core.model.SchRotationDeptAfterWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchRotationDeptAfterMapper {
    int countByExample(SchRotationDeptAfterExample example);

    int deleteByExample(SchRotationDeptAfterExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SchRotationDeptAfterWithBLOBs record);

    int insertSelective(SchRotationDeptAfterWithBLOBs record);

    List<SchRotationDeptAfterWithBLOBs> selectByExampleWithBLOBs(SchRotationDeptAfterExample example);

    List<SchRotationDeptAfter> selectByExample(SchRotationDeptAfterExample example);

    SchRotationDeptAfterWithBLOBs selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SchRotationDeptAfterWithBLOBs record, @Param("example") SchRotationDeptAfterExample example);

    int updateByExampleWithBLOBs(@Param("record") SchRotationDeptAfterWithBLOBs record, @Param("example") SchRotationDeptAfterExample example);

    int updateByExample(@Param("record") SchRotationDeptAfter record, @Param("example") SchRotationDeptAfterExample example);

    int updateByPrimaryKeySelective(SchRotationDeptAfterWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SchRotationDeptAfterWithBLOBs record);

    int updateByPrimaryKey(SchRotationDeptAfter record);
}