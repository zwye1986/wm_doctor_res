package com.pinde.sci.dao.base;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResInfoRoleMapper {
    int countByExample(ResInfoRoleExample example);

    int deleteByExample(ResInfoRoleExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResInfoRole record);

    int insertSelective(ResInfoRole record);

    List<ResInfoRole> selectByExample(ResInfoRoleExample example);

    ResInfoRole selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResInfoRole record, @Param("example") ResInfoRoleExample example);

    int updateByExample(@Param("record") ResInfoRole record, @Param("example") ResInfoRoleExample example);

    int updateByPrimaryKeySelective(ResInfoRole record);

    int updateByPrimaryKey(ResInfoRole record);
}