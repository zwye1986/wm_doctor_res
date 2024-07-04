package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExamBook;
import com.pinde.sci.model.mo.ExamBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamBookMapper {
    int countByExample(ExamBookExample example);

    int deleteByExample(ExamBookExample example);

    int deleteByPrimaryKey(String bookFlow);

    int insert(ExamBook record);

    int insertSelective(ExamBook record);

    List<ExamBook> selectByExample(ExamBookExample example);

    ExamBook selectByPrimaryKey(String bookFlow);

    int updateByExampleSelective(@Param("record") ExamBook record, @Param("example") ExamBookExample example);

    int updateByExample(@Param("record") ExamBook record, @Param("example") ExamBookExample example);

    int updateByPrimaryKeySelective(ExamBook record);

    int updateByPrimaryKey(ExamBook record);
}