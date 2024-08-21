package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.CountryOrgInfo;
import com.pinde.sci.model.mo.CountryOrgInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CountryOrgInfoMapper {
    int countByExample(CountryOrgInfoExample example);

    int deleteByExample(CountryOrgInfoExample example);

    int deleteByPrimaryKey(String orgFlow);

    int insert(CountryOrgInfo record);

    int insertSelective(CountryOrgInfo record);

    List<CountryOrgInfo> selectByExampleWithBLOBs(CountryOrgInfoExample example);

    List<CountryOrgInfo> selectByExample(CountryOrgInfoExample example);

    CountryOrgInfo selectByPrimaryKey(String orgFlow);

    int updateByExampleSelective(@Param("record") CountryOrgInfo record, @Param("example") CountryOrgInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") CountryOrgInfo record, @Param("example") CountryOrgInfoExample example);

    int updateByExample(@Param("record") CountryOrgInfo record, @Param("example") CountryOrgInfoExample example);

    int updateByPrimaryKeySelective(CountryOrgInfo record);

    int updateByPrimaryKeyWithBLOBs(CountryOrgInfo record);

    int updateByPrimaryKey(CountryOrgInfo record);
}