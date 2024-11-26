package com.pinde.sci.dao.base;

import com.pinde.core.model.ResStandardDeptPer;
import com.pinde.core.model.ResStandardDeptPerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResStandardDeptPerMapper {
    int countByExample(ResStandardDeptPerExample example);

    int deleteByExample(ResStandardDeptPerExample example);

    int deleteByPrimaryKey(String perFlow);

    int insert(ResStandardDeptPer record);

    int insertSelective(ResStandardDeptPer record);

    List<ResStandardDeptPer> selectByExample(ResStandardDeptPerExample example);

    ResStandardDeptPer selectByPrimaryKey(String perFlow);

    int updateByExampleSelective(@Param("record") ResStandardDeptPer record, @Param("example") ResStandardDeptPerExample example);

    int updateByExample(@Param("record") ResStandardDeptPer record, @Param("example") ResStandardDeptPerExample example);

    int updateByPrimaryKeySelective(ResStandardDeptPer record);

    int updateByPrimaryKey(ResStandardDeptPer record);
}