package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExamBookReloadRec;
import com.pinde.sci.model.mo.ExamBookReloadRecExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamBookReloadRecMapper {
    int countByExample(ExamBookReloadRecExample example);

    int deleteByExample(ExamBookReloadRecExample example);

    int deleteByPrimaryKey(String reloadRecFlow);

    int insert(ExamBookReloadRec record);

    int insertSelective(ExamBookReloadRec record);

    List<ExamBookReloadRec> selectByExample(ExamBookReloadRecExample example);

    ExamBookReloadRec selectByPrimaryKey(String reloadRecFlow);

    int updateByExampleSelective(@Param("record") ExamBookReloadRec record, @Param("example") ExamBookReloadRecExample example);

    int updateByExample(@Param("record") ExamBookReloadRec record, @Param("example") ExamBookReloadRecExample example);

    int updateByPrimaryKeySelective(ExamBookReloadRec record);

    int updateByPrimaryKey(ExamBookReloadRec record);
}