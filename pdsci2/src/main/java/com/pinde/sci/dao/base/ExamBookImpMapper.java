package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExamBookImp;
import com.pinde.sci.model.mo.ExamBookImpExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamBookImpMapper {
    int countByExample(ExamBookImpExample example);

    int deleteByExample(ExamBookImpExample example);

    int deleteByPrimaryKey(String bookImpFlow);

    int insert(ExamBookImp record);

    int insertSelective(ExamBookImp record);

    List<ExamBookImp> selectByExample(ExamBookImpExample example);

    ExamBookImp selectByPrimaryKey(String bookImpFlow);

    int updateByExampleSelective(@Param("record") ExamBookImp record, @Param("example") ExamBookImpExample example);

    int updateByExample(@Param("record") ExamBookImp record, @Param("example") ExamBookImpExample example);

    int updateByPrimaryKeySelective(ExamBookImp record);

    int updateByPrimaryKey(ExamBookImp record);
}