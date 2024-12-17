package com.pinde.sci.dao.base;

import com.pinde.core.model.SchEntryReport;
import com.pinde.core.model.SchEntryReportExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchEntryReportMapper {
    int countByExample(SchEntryReportExample example);

    int deleteByExample(SchEntryReportExample example);

    int deleteByPrimaryKey(String reportFlow);

    int insert(SchEntryReport record);

    int insertSelective(SchEntryReport record);

    List<SchEntryReport> selectByExample(SchEntryReportExample example);

    SchEntryReport selectByPrimaryKey(String reportFlow);

    int updateByExampleSelective(@Param("record") SchEntryReport record, @Param("example") SchEntryReportExample example);

    int updateByExample(@Param("record") SchEntryReport record, @Param("example") SchEntryReportExample example);

    int updateByPrimaryKeySelective(SchEntryReport record);

    int updateByPrimaryKey(SchEntryReport record);
}