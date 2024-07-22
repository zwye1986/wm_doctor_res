package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubImpactorFactor;
import com.pinde.sci.model.mo.PubImpactorFactorExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubImpactorFactorMapper {
    int countByExample(PubImpactorFactorExample example);

    int deleteByExample(PubImpactorFactorExample example);

    int deleteByPrimaryKey(String factorFlow);

    int insert(PubImpactorFactor record);

    int insertSelective(PubImpactorFactor record);

    List<PubImpactorFactor> selectByExample(PubImpactorFactorExample example);

    PubImpactorFactor selectByPrimaryKey(String factorFlow);

    int updateByExampleSelective(@Param("record") PubImpactorFactor record, @Param("example") PubImpactorFactorExample example);

    int updateByExample(@Param("record") PubImpactorFactor record, @Param("example") PubImpactorFactorExample example);

    int updateByPrimaryKeySelective(PubImpactorFactor record);

    int updateByPrimaryKey(PubImpactorFactor record);
}