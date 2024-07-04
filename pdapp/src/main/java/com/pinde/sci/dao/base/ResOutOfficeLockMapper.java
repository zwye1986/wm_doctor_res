package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResOutOfficeLock;
import com.pinde.sci.model.mo.ResOutOfficeLockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResOutOfficeLockMapper {
    int countByExample(ResOutOfficeLockExample example);

    int deleteByExample(ResOutOfficeLockExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResOutOfficeLock record);

    int insertSelective(ResOutOfficeLock record);

    List<ResOutOfficeLock> selectByExample(ResOutOfficeLockExample example);

    ResOutOfficeLock selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResOutOfficeLock record, @Param("example") ResOutOfficeLockExample example);

    int updateByExample(@Param("record") ResOutOfficeLock record, @Param("example") ResOutOfficeLockExample example);

    int updateByPrimaryKeySelective(ResOutOfficeLock record);

    int updateByPrimaryKey(ResOutOfficeLock record);
}