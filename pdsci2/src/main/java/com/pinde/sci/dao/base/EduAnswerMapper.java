package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduAnswer;
import com.pinde.sci.model.mo.EduAnswerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduAnswerMapper {
    int countByExample(EduAnswerExample example);

    int deleteByExample(EduAnswerExample example);

    int deleteByPrimaryKey(String answerFlow);

    int insert(EduAnswer record);

    int insertSelective(EduAnswer record);

    List<EduAnswer> selectByExample(EduAnswerExample example);

    EduAnswer selectByPrimaryKey(String answerFlow);

    int updateByExampleSelective(@Param("record") EduAnswer record, @Param("example") EduAnswerExample example);

    int updateByExample(@Param("record") EduAnswer record, @Param("example") EduAnswerExample example);

    int updateByPrimaryKeySelective(EduAnswer record);

    int updateByPrimaryKey(EduAnswer record);
}