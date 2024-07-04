package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExamQuestionDetail;
import com.pinde.sci.model.mo.ExamQuestionDetailExample;
import com.pinde.sci.model.mo.ExamQuestionDetailWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamQuestionDetailMapper {
    int countByExample(ExamQuestionDetailExample example);

    int deleteByExample(ExamQuestionDetailExample example);

    int deleteByPrimaryKey(String detailFlow);

    int insert(ExamQuestionDetailWithBLOBs record);

    int insertSelective(ExamQuestionDetailWithBLOBs record);

    List<ExamQuestionDetailWithBLOBs> selectByExampleWithBLOBs(ExamQuestionDetailExample example);

    List<ExamQuestionDetail> selectByExample(ExamQuestionDetailExample example);

    ExamQuestionDetailWithBLOBs selectByPrimaryKey(String detailFlow);

    int updateByExampleSelective(@Param("record") ExamQuestionDetailWithBLOBs record, @Param("example") ExamQuestionDetailExample example);

    int updateByExampleWithBLOBs(@Param("record") ExamQuestionDetailWithBLOBs record, @Param("example") ExamQuestionDetailExample example);

    int updateByExample(@Param("record") ExamQuestionDetail record, @Param("example") ExamQuestionDetailExample example);

    int updateByPrimaryKeySelective(ExamQuestionDetailWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ExamQuestionDetailWithBLOBs record);

    int updateByPrimaryKey(ExamQuestionDetail record);
}