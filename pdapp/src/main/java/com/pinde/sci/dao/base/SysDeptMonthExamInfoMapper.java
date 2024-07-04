package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SysDeptMonthExamInfo;
import com.pinde.sci.model.mo.SysDeptMonthExamInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDeptMonthExamInfoMapper {
    int countByExample(SysDeptMonthExamInfoExample example);

    int deleteByExample(SysDeptMonthExamInfoExample example);

    int deleteByPrimaryKey(String examFlow);

    int insert(SysDeptMonthExamInfo record);

    int insertSelective(SysDeptMonthExamInfo record);

    List<SysDeptMonthExamInfo> selectByExample(SysDeptMonthExamInfoExample example);

    SysDeptMonthExamInfo selectByPrimaryKey(String examFlow);

    int updateByExampleSelective(@Param("record") SysDeptMonthExamInfo record, @Param("example") SysDeptMonthExamInfoExample example);

    int updateByExample(@Param("record") SysDeptMonthExamInfo record, @Param("example") SysDeptMonthExamInfoExample example);

    int updateByPrimaryKeySelective(SysDeptMonthExamInfo record);

    int updateByPrimaryKey(SysDeptMonthExamInfo record);
}