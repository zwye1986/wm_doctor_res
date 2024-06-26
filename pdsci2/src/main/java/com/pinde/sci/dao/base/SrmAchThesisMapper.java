package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmAchThesis;
import com.pinde.sci.model.mo.SrmAchThesisExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmAchThesisMapper {
    int countByExample(SrmAchThesisExample example);

    int deleteByExample(SrmAchThesisExample example);

    int deleteByPrimaryKey(String thesisFlow);

    int insert(SrmAchThesis record);

    int insertSelective(SrmAchThesis record);

    List<SrmAchThesis> selectByExample(SrmAchThesisExample example);

    SrmAchThesis selectByPrimaryKey(String thesisFlow);

    int updateByExampleSelective(@Param("record") SrmAchThesis record, @Param("example") SrmAchThesisExample example);

    int updateByExample(@Param("record") SrmAchThesis record, @Param("example") SrmAchThesisExample example);

    int updateByPrimaryKeySelective(SrmAchThesis record);

    int updateByPrimaryKey(SrmAchThesis record);
}