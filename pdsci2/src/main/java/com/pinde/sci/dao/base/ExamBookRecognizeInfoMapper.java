package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExamBookRecognizeInfo;
import com.pinde.sci.model.mo.ExamBookRecognizeInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamBookRecognizeInfoMapper {
    int countByExample(ExamBookRecognizeInfoExample example);

    int deleteByExample(ExamBookRecognizeInfoExample example);

    int deleteByPrimaryKey(String recognizeFlow);

    int insert(ExamBookRecognizeInfo record);

    int insertSelective(ExamBookRecognizeInfo record);

    List<ExamBookRecognizeInfo> selectByExample(ExamBookRecognizeInfoExample example);

    ExamBookRecognizeInfo selectByPrimaryKey(String recognizeFlow);

    int updateByExampleSelective(@Param("record") ExamBookRecognizeInfo record, @Param("example") ExamBookRecognizeInfoExample example);

    int updateByExample(@Param("record") ExamBookRecognizeInfo record, @Param("example") ExamBookRecognizeInfoExample example);

    int updateByPrimaryKeySelective(ExamBookRecognizeInfo record);

    int updateByPrimaryKey(ExamBookRecognizeInfo record);
}