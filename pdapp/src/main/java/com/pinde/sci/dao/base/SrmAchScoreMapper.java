package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmAchScore;
import com.pinde.sci.model.mo.SrmAchScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmAchScoreMapper {
    int countByExample(SrmAchScoreExample example);

    int deleteByExample(SrmAchScoreExample example);

    int deleteByPrimaryKey(String scoreFlow);

    int insert(SrmAchScore record);

    int insertSelective(SrmAchScore record);

    List<SrmAchScore> selectByExample(SrmAchScoreExample example);

    SrmAchScore selectByPrimaryKey(String scoreFlow);

    int updateByExampleSelective(@Param("record") SrmAchScore record, @Param("example") SrmAchScoreExample example);

    int updateByExample(@Param("record") SrmAchScore record, @Param("example") SrmAchScoreExample example);

    int updateByPrimaryKeySelective(SrmAchScore record);

    int updateByPrimaryKey(SrmAchScore record);
}