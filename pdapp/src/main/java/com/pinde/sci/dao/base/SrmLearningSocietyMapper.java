package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmLearningSociety;
import com.pinde.sci.model.mo.SrmLearningSocietyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmLearningSocietyMapper {
    int countByExample(SrmLearningSocietyExample example);

    int deleteByExample(SrmLearningSocietyExample example);

    int deleteByPrimaryKey(String societyFlow);

    int insert(SrmLearningSociety record);

    int insertSelective(SrmLearningSociety record);

    List<SrmLearningSociety> selectByExample(SrmLearningSocietyExample example);

    SrmLearningSociety selectByPrimaryKey(String societyFlow);

    int updateByExampleSelective(@Param("record") SrmLearningSociety record, @Param("example") SrmLearningSocietyExample example);

    int updateByExample(@Param("record") SrmLearningSociety record, @Param("example") SrmLearningSocietyExample example);

    int updateByPrimaryKeySelective(SrmLearningSociety record);

    int updateByPrimaryKey(SrmLearningSociety record);
}