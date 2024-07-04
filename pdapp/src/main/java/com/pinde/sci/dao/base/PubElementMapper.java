package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubElement;
import com.pinde.sci.model.mo.PubElementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PubElementMapper {
    int countByExample(PubElementExample example);

    int deleteByExample(PubElementExample example);

    int deleteByPrimaryKey(String elementFlow);

    int insert(PubElement record);

    int insertSelective(PubElement record);

    List<PubElement> selectByExample(PubElementExample example);

    PubElement selectByPrimaryKey(String elementFlow);

    int updateByExampleSelective(@Param("record") PubElement record, @Param("example") PubElementExample example);

    int updateByExample(@Param("record") PubElement record, @Param("example") PubElementExample example);

    int updateByPrimaryKeySelective(PubElement record);

    int updateByPrimaryKey(PubElement record);
}