package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubWorkLog;
import com.pinde.sci.model.mo.PubWorkLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PubWorkLogMapper {
    int countByExample(PubWorkLogExample example);

    int deleteByExample(PubWorkLogExample example);

    int deleteByPrimaryKey(String logFlow);

    int insert(PubWorkLog record);

    int insertSelective(PubWorkLog record);

    List<PubWorkLog> selectByExampleWithBLOBs(PubWorkLogExample example);

    List<PubWorkLog> selectByExample(PubWorkLogExample example);

    PubWorkLog selectByPrimaryKey(String logFlow);

    int updateByExampleSelective(@Param("record") PubWorkLog record, @Param("example") PubWorkLogExample example);

    int updateByExampleWithBLOBs(@Param("record") PubWorkLog record, @Param("example") PubWorkLogExample example);

    int updateByExample(@Param("record") PubWorkLog record, @Param("example") PubWorkLogExample example);

    int updateByPrimaryKeySelective(PubWorkLog record);

    int updateByPrimaryKeyWithBLOBs(PubWorkLog record);

    int updateByPrimaryKey(PubWorkLog record);
}