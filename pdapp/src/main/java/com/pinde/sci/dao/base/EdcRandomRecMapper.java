package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcRandomRec;
import com.pinde.sci.model.mo.EdcRandomRecExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcRandomRecMapper {
    int countByExample(EdcRandomRecExample example);

    int deleteByExample(EdcRandomRecExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EdcRandomRec record);

    int insertSelective(EdcRandomRec record);

    List<EdcRandomRec> selectByExample(EdcRandomRecExample example);

    EdcRandomRec selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EdcRandomRec record, @Param("example") EdcRandomRecExample example);

    int updateByExample(@Param("record") EdcRandomRec record, @Param("example") EdcRandomRecExample example);

    int updateByPrimaryKeySelective(EdcRandomRec record);

    int updateByPrimaryKey(EdcRandomRec record);
}