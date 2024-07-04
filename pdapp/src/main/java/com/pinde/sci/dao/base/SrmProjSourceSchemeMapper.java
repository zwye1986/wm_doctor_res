package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmProjSourceScheme;
import com.pinde.sci.model.mo.SrmProjSourceSchemeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmProjSourceSchemeMapper {
    int countByExample(SrmProjSourceSchemeExample example);

    int deleteByExample(SrmProjSourceSchemeExample example);

    int deleteByPrimaryKey(String sourceFlow);

    int insert(SrmProjSourceScheme record);

    int insertSelective(SrmProjSourceScheme record);

    List<SrmProjSourceScheme> selectByExample(SrmProjSourceSchemeExample example);

    SrmProjSourceScheme selectByPrimaryKey(String sourceFlow);

    int updateByExampleSelective(@Param("record") SrmProjSourceScheme record, @Param("example") SrmProjSourceSchemeExample example);

    int updateByExample(@Param("record") SrmProjSourceScheme record, @Param("example") SrmProjSourceSchemeExample example);

    int updateByPrimaryKeySelective(SrmProjSourceScheme record);

    int updateByPrimaryKey(SrmProjSourceScheme record);
}