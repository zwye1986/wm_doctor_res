package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SchEntryReport;
import com.pinde.sci.model.mo.SchEntryReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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