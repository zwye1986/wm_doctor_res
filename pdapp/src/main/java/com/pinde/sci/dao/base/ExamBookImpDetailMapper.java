package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExamBookImpDetail;
import com.pinde.sci.model.mo.ExamBookImpDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamBookImpDetailMapper {
    int countByExample(ExamBookImpDetailExample example);

    int deleteByExample(ExamBookImpDetailExample example);

    int deleteByPrimaryKey(String impDetailFlow);

    int insert(ExamBookImpDetail record);

    int insertSelective(ExamBookImpDetail record);

    List<ExamBookImpDetail> selectByExampleWithBLOBs(ExamBookImpDetailExample example);

    List<ExamBookImpDetail> selectByExample(ExamBookImpDetailExample example);

    ExamBookImpDetail selectByPrimaryKey(String impDetailFlow);

    int updateByExampleSelective(@Param("record") ExamBookImpDetail record, @Param("example") ExamBookImpDetailExample example);

    int updateByExampleWithBLOBs(@Param("record") ExamBookImpDetail record, @Param("example") ExamBookImpDetailExample example);

    int updateByExample(@Param("record") ExamBookImpDetail record, @Param("example") ExamBookImpDetailExample example);

    int updateByPrimaryKeySelective(ExamBookImpDetail record);

    int updateByPrimaryKeyWithBLOBs(ExamBookImpDetail record);

    int updateByPrimaryKey(ExamBookImpDetail record);
}