package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResExam;
import com.pinde.sci.model.mo.ResExamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResExamMapper {
    int countByExample(ResExamExample example);

    int deleteByExample(ResExamExample example);

    int deleteByPrimaryKey(String examFlow);

    int insert(ResExam record);

    int insertSelective(ResExam record);

    List<ResExam> selectByExample(ResExamExample example);

    ResExam selectByPrimaryKey(String examFlow);

    int updateByExampleSelective(@Param("record") ResExam record, @Param("example") ResExamExample example);

    int updateByExample(@Param("record") ResExam record, @Param("example") ResExamExample example);

    int updateByPrimaryKeySelective(ResExam record);

    int updateByPrimaryKey(ResExam record);
}