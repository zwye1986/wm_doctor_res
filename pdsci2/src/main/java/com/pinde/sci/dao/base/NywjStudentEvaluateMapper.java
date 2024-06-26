package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NywjStudentEvaluate;
import com.pinde.sci.model.mo.NywjStudentEvaluateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NywjStudentEvaluateMapper {
    int countByExample(NywjStudentEvaluateExample example);

    int deleteByExample(NywjStudentEvaluateExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NywjStudentEvaluate record);

    int insertSelective(NywjStudentEvaluate record);

    List<NywjStudentEvaluate> selectByExampleWithBLOBs(NywjStudentEvaluateExample example);

    List<NywjStudentEvaluate> selectByExample(NywjStudentEvaluateExample example);

    NywjStudentEvaluate selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NywjStudentEvaluate record, @Param("example") NywjStudentEvaluateExample example);

    int updateByExampleWithBLOBs(@Param("record") NywjStudentEvaluate record, @Param("example") NywjStudentEvaluateExample example);

    int updateByExample(@Param("record") NywjStudentEvaluate record, @Param("example") NywjStudentEvaluateExample example);

    int updateByPrimaryKeySelective(NywjStudentEvaluate record);

    int updateByPrimaryKeyWithBLOBs(NywjStudentEvaluate record);

    int updateByPrimaryKey(NywjStudentEvaluate record);
}