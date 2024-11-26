package com.pinde.sci.dao.base;

import com.pinde.core.model.ResOrgCkxz;
import com.pinde.core.model.ResOrgCkxzExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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