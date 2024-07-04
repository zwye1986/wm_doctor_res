package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmAchScoreType;
import com.pinde.sci.model.mo.SrmAchScoreTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmAchScoreTypeMapper {
    int countByExample(SrmAchScoreTypeExample example);

    int deleteByExample(SrmAchScoreTypeExample example);

    int deleteByPrimaryKey(String typeFlow);

    int insert(SrmAchScoreType record);

    int insertSelective(SrmAchScoreType record);

    List<SrmAchScoreType> selectByExample(SrmAchScoreTypeExample example);

    SrmAchScoreType selectByPrimaryKey(String typeFlow);

    int updateByExampleSelective(@Param("record") SrmAchScoreType record, @Param("example") SrmAchScoreTypeExample example);

    int updateByExample(@Param("record") SrmAchScoreType record, @Param("example") SrmAchScoreTypeExample example);

    int updateByPrimaryKeySelective(SrmAchScoreType record);

    int updateByPrimaryKey(SrmAchScoreType record);
}