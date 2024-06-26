package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResSupervisioReport;
import com.pinde.sci.model.mo.ResSupervisioReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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