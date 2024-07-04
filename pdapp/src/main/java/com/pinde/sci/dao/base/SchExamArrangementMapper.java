package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SchExamArrangement;
import com.pinde.sci.model.mo.SchExamArrangementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchExamArrangementMapper {
    int countByExample(SchExamArrangementExample example);

    int deleteByExample(SchExamArrangementExample example);

    int deleteByPrimaryKey(String arrangeFlow);

    int insert(SchExamArrangement record);

    int insertSelective(SchExamArrangement record);

    List<SchExamArrangement> selectByExample(SchExamArrangementExample example);

    SchExamArrangement selectByPrimaryKey(String arrangeFlow);

    int updateByExampleSelective(@Param("record") SchExamArrangement record, @Param("example") SchExamArrangementExample example);

    int updateByExample(@Param("record") SchExamArrangement record, @Param("example") SchExamArrangementExample example);

    int updateByPrimaryKeySelective(SchExamArrangement record);

    int updateByPrimaryKey(SchExamArrangement record);
}