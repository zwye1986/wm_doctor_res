package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NyzlVacancySwap;
import com.pinde.sci.model.mo.NyzlVacancySwapExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NyzlVacancySwapMapper {
    int countByExample(NyzlVacancySwapExample example);

    int deleteByExample(NyzlVacancySwapExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NyzlVacancySwap record);

    int insertSelective(NyzlVacancySwap record);

    List<NyzlVacancySwap> selectByExample(NyzlVacancySwapExample example);

    NyzlVacancySwap selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NyzlVacancySwap record, @Param("example") NyzlVacancySwapExample example);

    int updateByExample(@Param("record") NyzlVacancySwap record, @Param("example") NyzlVacancySwapExample example);

    int updateByPrimaryKeySelective(NyzlVacancySwap record);

    int updateByPrimaryKey(NyzlVacancySwap record);
}