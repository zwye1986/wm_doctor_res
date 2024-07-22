package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExamBookScanInfo;
import com.pinde.sci.model.mo.ExamBookScanInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamBookScanInfoMapper {
    int countByExample(ExamBookScanInfoExample example);

    int deleteByExample(ExamBookScanInfoExample example);

    int deleteByPrimaryKey(String scanFlow);

    int insert(ExamBookScanInfo record);

    int insertSelective(ExamBookScanInfo record);

    List<ExamBookScanInfo> selectByExample(ExamBookScanInfoExample example);

    ExamBookScanInfo selectByPrimaryKey(String scanFlow);

    int updateByExampleSelective(@Param("record") ExamBookScanInfo record, @Param("example") ExamBookScanInfoExample example);

    int updateByExample(@Param("record") ExamBookScanInfo record, @Param("example") ExamBookScanInfoExample example);

    int updateByPrimaryKeySelective(ExamBookScanInfo record);

    int updateByPrimaryKey(ExamBookScanInfo record);
}