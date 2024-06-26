package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExamQuestion;
import com.pinde.sci.model.mo.ExamQuestionExample;
import com.pinde.sci.model.mo.ExamQuestionWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamQuestionMapper {
    int countByExample(ExamQuestionExample example);

    int deleteByExample(ExamQuestionExample example);

    int deleteByPrimaryKey(String questionFlow);

    int insert(ExamQuestionWithBLOBs record);

    int insertSelective(ExamQuestionWithBLOBs record);

    List<ExamQuestionWithBLOBs> selectByExampleWithBLOBs(ExamQuestionExample example);

    List<ExamQuestion> selectByExample(ExamQuestionExample example);

    ExamQuestionWithBLOBs selectByPrimaryKey(String questionFlow);

    int updateByExampleSelective(@Param("record") ExamQuestionWithBLOBs record, @Param("example") ExamQuestionExample example);

    int updateByExampleWithBLOBs(@Param("record") ExamQuestionWithBLOBs record, @Param("example") ExamQuestionExample example);

    int updateByExample(@Param("record") ExamQuestion record, @Param("example") ExamQuestionExample example);

    int updateByPrimaryKeySelective(ExamQuestionWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ExamQuestionWithBLOBs record);

    int updateByPrimaryKey(ExamQuestion record);
}