package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmGradeScheme;
import com.pinde.sci.model.mo.SrmGradeSchemeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmGradeSchemeMapper {
    int countByExample(SrmGradeSchemeExample example);

    int deleteByExample(SrmGradeSchemeExample example);

    int deleteByPrimaryKey(String schemeFlow);

    int insert(SrmGradeScheme record);

    int insertSelective(SrmGradeScheme record);

    List<SrmGradeScheme> selectByExample(SrmGradeSchemeExample example);

    SrmGradeScheme selectByPrimaryKey(String schemeFlow);

    int updateByExampleSelective(@Param("record") SrmGradeScheme record, @Param("example") SrmGradeSchemeExample example);

    int updateByExample(@Param("record") SrmGradeScheme record, @Param("example") SrmGradeSchemeExample example);

    int updateByPrimaryKeySelective(SrmGradeScheme record);

    int updateByPrimaryKey(SrmGradeScheme record);
}