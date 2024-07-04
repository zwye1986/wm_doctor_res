package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmAchReseachrep;
import com.pinde.sci.model.mo.SrmAchReseachrepExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmAchReseachrepMapper {
    int countByExample(SrmAchReseachrepExample example);

    int deleteByExample(SrmAchReseachrepExample example);

    int deleteByPrimaryKey(String reseachrepFlow);

    int insert(SrmAchReseachrep record);

    int insertSelective(SrmAchReseachrep record);

    List<SrmAchReseachrep> selectByExample(SrmAchReseachrepExample example);

    SrmAchReseachrep selectByPrimaryKey(String reseachrepFlow);

    int updateByExampleSelective(@Param("record") SrmAchReseachrep record, @Param("example") SrmAchReseachrepExample example);

    int updateByExample(@Param("record") SrmAchReseachrep record, @Param("example") SrmAchReseachrepExample example);

    int updateByPrimaryKeySelective(SrmAchReseachrep record);

    int updateByPrimaryKey(SrmAchReseachrep record);
}