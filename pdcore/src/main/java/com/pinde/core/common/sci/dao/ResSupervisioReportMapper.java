package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResSupervisioReport;
import com.pinde.core.model.ResSupervisioReportExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResSupervisioReportMapper {
    int countByExample(ResSupervisioReportExample example);

    int deleteByExample(ResSupervisioReportExample example);

    int insert(ResSupervisioReport record);

    int insertSelective(ResSupervisioReport record);

    List<ResSupervisioReport> selectByExampleWithBLOBs(ResSupervisioReportExample example);

    List<ResSupervisioReport> selectByExample(ResSupervisioReportExample example);

    int updateByExampleSelective(@Param("record") ResSupervisioReport record, @Param("example") ResSupervisioReportExample example);

    int updateByExampleWithBLOBs(@Param("record") ResSupervisioReport record, @Param("example") ResSupervisioReportExample example);

    int updateByExample(@Param("record") ResSupervisioReport record, @Param("example") ResSupervisioReportExample example);
}