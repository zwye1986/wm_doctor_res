package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduLog;
import com.pinde.sci.model.mo.EduLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduLogMapper {
    int countByExample(EduLogExample example);

    int deleteByExample(EduLogExample example);

    int deleteByPrimaryKey(String logFlow);

    int insert(EduLog record);

    int insertSelective(EduLog record);

    List<EduLog> selectByExample(EduLogExample example);

    EduLog selectByPrimaryKey(String logFlow);

    int updateByExampleSelective(@Param("record") EduLog record, @Param("example") EduLogExample example);

    int updateByExample(@Param("record") EduLog record, @Param("example") EduLogExample example);

    int updateByPrimaryKeySelective(EduLog record);

    int updateByPrimaryKey(EduLog record);
}