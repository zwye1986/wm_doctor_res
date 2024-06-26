package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.GydxjInsertGrade;
import com.pinde.sci.model.mo.GydxjInsertGradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GydxjInsertGradeMapper {
    int countByExample(GydxjInsertGradeExample example);

    int deleteByExample(GydxjInsertGradeExample example);

    int deleteByPrimaryKey(String stuNo);

    int insert(GydxjInsertGrade record);

    int insertSelective(GydxjInsertGrade record);

    List<GydxjInsertGrade> selectByExample(GydxjInsertGradeExample example);

    GydxjInsertGrade selectByPrimaryKey(String stuNo);

    int updateByExampleSelective(@Param("record") GydxjInsertGrade record, @Param("example") GydxjInsertGradeExample example);

    int updateByExample(@Param("record") GydxjInsertGrade record, @Param("example") GydxjInsertGradeExample example);

    int updateByPrimaryKeySelective(GydxjInsertGrade record);

    int updateByPrimaryKey(GydxjInsertGrade record);
}