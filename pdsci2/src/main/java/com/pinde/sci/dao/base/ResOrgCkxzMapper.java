package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResOrgCkxz;
import com.pinde.sci.model.mo.ResOrgCkxzExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResOrgCkxzMapper {
    int countByExample(ResOrgCkxzExample example);

    int deleteByExample(ResOrgCkxzExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResOrgCkxz record);

    int insertSelective(ResOrgCkxz record);

    List<ResOrgCkxz> selectByExample(ResOrgCkxzExample example);

    ResOrgCkxz selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResOrgCkxz record, @Param("example") ResOrgCkxzExample example);

    int updateByExample(@Param("record") ResOrgCkxz record, @Param("example") ResOrgCkxzExample example);

    int updateByPrimaryKeySelective(ResOrgCkxz record);

    int updateByPrimaryKey(ResOrgCkxz record);
}