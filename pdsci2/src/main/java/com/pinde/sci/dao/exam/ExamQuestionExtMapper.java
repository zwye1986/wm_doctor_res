package com.pinde.sci.dao.exam;

import com.pinde.sci.model.exam.ExamQuestionExt;
import com.pinde.sci.model.mo.ExamQuestionWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExamQuestionExtMapper {

//	public List<ExamQuestionWithBLOBs> searchExamQuestionBySubjectQuestionType(@Param(value="subjectFlow") String subjectFlow,@Param(value="questionTypeId") String questionTypeId);

    List<ExamQuestionWithBLOBs> searchExamQuestionBySubjectSubject(@Param(value = "subjectFlow") String subjectFlow, @Param(value = "subjectFlow2") String subjectFlow2);

    List<ExamQuestionWithBLOBs> searchExamQuestionBySubjectBook(@Param(value = "subjectFlow") String subjectFlow, @Param(value = "bookFlow") String bookFlow);

//	public List<ExamQuestionWithBLOBs> searchExamQuestionByBookQuestionType(@Param(value="bookFlow") String bookFlow,@Param(value="questionTypeId") String questionTypeId);

    List<ExamQuestionWithBLOBs> searchExamQuestionByBookSubject(@Param(value = "bookFlow") String bookFlow, @Param(value = "subjectFlow") String subjectFlow);

    /**
     * 根据书号和题目类型查询题目包过子题
     *
     * @param bookFlow
     * @param questionTypeId
     * @return
     */
    List<ExamQuestionExt> searchExamQuestionAndSubQuestionByBookQuestionType(@Param(value = "bookFlow") String bookFlow, @Param(value = "questionTypeId") String questionTypeId);

    /**
     * 根据科目主键和题目类型查询题目包过子题
     *
     * @param bookFlow
     * @param questionTypeId
     * @return
     */
    List<ExamQuestionExt> searchExamQuestionAndSubQuestionBySubjectQuestionType(@Param(value = "subjectFlow") String bookFlow, @Param(value = "questionTypeId") String questionTypeId);

    List<ExamQuestionWithBLOBs> searchExamQuestionByBookBook(@Param(value = "bookFlow") String bookFlow, @Param(value = "bookFlow2") String bookFlow2);

    int insertExamQuestionDelhis(Map<String, Object> map);

}
