package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcAppLog;
import com.pinde.sci.model.mo.EdcAppLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcAppLogMapper {
    int countByExample(EdcAppLogExample example);

    int deleteByExample(EdcAppLogExample example);

    int deleteByPrimaryKey(String logFlow);

    int insert(EdcAppLog record);

    int insertSelective(EdcAppLog record);

    List<EdcAppLog> selectByExampleWithBLOBs(EdcAppLogExample example);

    List<EdcAppLog> selectByExample(EdcAppLogExample example);

    EdcAppLog selectByPrimaryKey(String logFlow);

    int updateByExampleSelective(@Param("record") EdcAppLog record, @Param("example") EdcAppLogExample example);

    int updateByExampleWithBLOBs(@Param("record") EdcAppLog record, @Param("example") EdcAppLogExample example);

    int updateByExample(@Param("record") EdcAppLog record, @Param("example") EdcAppLogExample example);

    int updateByPrimaryKeySelective(EdcAppLog record);

    int updateByPrimaryKeyWithBLOBs(EdcAppLog record);

    int updateByPrimaryKey(EdcAppLog record);
}