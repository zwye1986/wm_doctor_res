package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduQuestion;
import com.pinde.sci.model.mo.EduQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduQuestionMapper {
    int countByExample(EduQuestionExample example);

    int deleteByExample(EduQuestionExample example);

    int deleteByPrimaryKey(String questionFlow);

    int insert(EduQuestion record);

    int insertSelective(EduQuestion record);

    List<EduQuestion> selectByExample(EduQuestionExample example);

    EduQuestion selectByPrimaryKey(String questionFlow);

    int updateByExampleSelective(@Param("record") EduQuestion record, @Param("example") EduQuestionExample example);

    int updateByExample(@Param("record") EduQuestion record, @Param("example") EduQuestionExample example);

    int updateByPrimaryKeySelective(EduQuestion record);

    int updateByPrimaryKey(EduQuestion record);
}