package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduExamScore;
import com.pinde.sci.model.mo.EduExamScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduExamScoreMapper {
    int countByExample(EduExamScoreExample example);

    int deleteByExample(EduExamScoreExample example);

    int deleteByPrimaryKey(String detaliFlow);

    int insert(EduExamScore record);

    int insertSelective(EduExamScore record);

    List<EduExamScore> selectByExample(EduExamScoreExample example);

    EduExamScore selectByPrimaryKey(String detaliFlow);

    int updateByExampleSelective(@Param("record") EduExamScore record, @Param("example") EduExamScoreExample example);

    int updateByExample(@Param("record") EduExamScore record, @Param("example") EduExamScoreExample example);

    int updateByPrimaryKeySelective(EduExamScore record);

    int updateByPrimaryKey(EduExamScore record);
}