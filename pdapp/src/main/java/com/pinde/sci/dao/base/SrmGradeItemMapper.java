package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmGradeItem;
import com.pinde.sci.model.mo.SrmGradeItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmGradeItemMapper {
    int countByExample(SrmGradeItemExample example);

    int deleteByExample(SrmGradeItemExample example);

    int deleteByPrimaryKey(String itemFlow);

    int insert(SrmGradeItem record);

    int insertSelective(SrmGradeItem record);

    List<SrmGradeItem> selectByExample(SrmGradeItemExample example);

    SrmGradeItem selectByPrimaryKey(String itemFlow);

    int updateByExampleSelective(@Param("record") SrmGradeItem record, @Param("example") SrmGradeItemExample example);

    int updateByExample(@Param("record") SrmGradeItem record, @Param("example") SrmGradeItemExample example);

    int updateByPrimaryKeySelective(SrmGradeItem record);

    int updateByPrimaryKey(SrmGradeItem record);
}