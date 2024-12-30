package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResOrgAddress;
import com.pinde.core.model.ResOrgAddressExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResOrgAddressMapper {
    int countByExample(ResOrgAddressExample example);

    int deleteByExample(ResOrgAddressExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResOrgAddress record);

    int insertSelective(ResOrgAddress record);

    List<ResOrgAddress> selectByExample(ResOrgAddressExample example);

    ResOrgAddress selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResOrgAddress record, @Param("example") ResOrgAddressExample example);

    int updateByExample(@Param("record") ResOrgAddress record, @Param("example") ResOrgAddressExample example);

    int updateByPrimaryKeySelective(ResOrgAddress record);

    int updateByPrimaryKey(ResOrgAddress record);
}