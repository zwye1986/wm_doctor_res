package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.AttachedUnitInfo;
import com.pinde.sci.model.mo.AttachedUnitInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttachedUnitInfoMapper {
    int countByExample(AttachedUnitInfoExample example);

    int deleteByExample(AttachedUnitInfoExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(AttachedUnitInfo record);

    int insertSelective(AttachedUnitInfo record);

    List<AttachedUnitInfo> selectByExampleWithBLOBs(AttachedUnitInfoExample example);

    List<AttachedUnitInfo> selectByExample(AttachedUnitInfoExample example);

    AttachedUnitInfo selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") AttachedUnitInfo record, @Param("example") AttachedUnitInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") AttachedUnitInfo record, @Param("example") AttachedUnitInfoExample example);

    int updateByExample(@Param("record") AttachedUnitInfo record, @Param("example") AttachedUnitInfoExample example);

    int updateByPrimaryKeySelective(AttachedUnitInfo record);

    int updateByPrimaryKeyWithBLOBs(AttachedUnitInfo record);

    int updateByPrimaryKey(AttachedUnitInfo record);
}