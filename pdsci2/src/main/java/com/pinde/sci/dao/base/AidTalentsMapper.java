package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.AidTalents;
import com.pinde.sci.model.mo.AidTalentsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AidTalentsMapper {
    int countByExample(AidTalentsExample example);

    int deleteByExample(AidTalentsExample example);

    int deleteByPrimaryKey(String talentsFlow);

    int insert(AidTalents record);

    int insertSelective(AidTalents record);

    List<AidTalents> selectByExampleWithBLOBs(AidTalentsExample example);

    List<AidTalents> selectByExample(AidTalentsExample example);

    AidTalents selectByPrimaryKey(String talentsFlow);

    int updateByExampleSelective(@Param("record") AidTalents record, @Param("example") AidTalentsExample example);

    int updateByExampleWithBLOBs(@Param("record") AidTalents record, @Param("example") AidTalentsExample example);

    int updateByExample(@Param("record") AidTalents record, @Param("example") AidTalentsExample example);

    int updateByPrimaryKeySelective(AidTalents record);

    int updateByPrimaryKeyWithBLOBs(AidTalents record);

    int updateByPrimaryKey(AidTalents record);
}