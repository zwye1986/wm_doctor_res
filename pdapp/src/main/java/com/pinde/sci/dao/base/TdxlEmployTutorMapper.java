package com.pinde.sci.dao.base;

import com.pinde.core.model.TdxlEmployTutor;
import com.pinde.core.model.TdxlEmployTutorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TdxlEmployTutorMapper {
    int countByExample(TdxlEmployTutorExample example);

    int deleteByExample(TdxlEmployTutorExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(TdxlEmployTutor record);

    int insertSelective(TdxlEmployTutor record);

    List<TdxlEmployTutor> selectByExampleWithBLOBs(TdxlEmployTutorExample example);

    List<TdxlEmployTutor> selectByExample(TdxlEmployTutorExample example);

    TdxlEmployTutor selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") TdxlEmployTutor record, @Param("example") TdxlEmployTutorExample example);

    int updateByExampleWithBLOBs(@Param("record") TdxlEmployTutor record, @Param("example") TdxlEmployTutorExample example);

    int updateByExample(@Param("record") TdxlEmployTutor record, @Param("example") TdxlEmployTutorExample example);

    int updateByPrimaryKeySelective(TdxlEmployTutor record);

    int updateByPrimaryKeyWithBLOBs(TdxlEmployTutor record);

    int updateByPrimaryKey(TdxlEmployTutor record);
}