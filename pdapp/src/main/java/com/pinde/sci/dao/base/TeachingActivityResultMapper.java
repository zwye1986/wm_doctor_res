package com.pinde.sci.dao.base;

import com.pinde.core.model.TeachingActivityResult;
import com.pinde.core.model.TeachingActivityResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeachingActivityResultMapper {
    int countByExample(TeachingActivityResultExample example);

    int deleteByExample(TeachingActivityResultExample example);

    int deleteByPrimaryKey(String resultFlow);

    int insert(TeachingActivityResult record);

    int insertSelective(TeachingActivityResult record);

    List<TeachingActivityResult> selectByExample(TeachingActivityResultExample example);

    TeachingActivityResult selectByPrimaryKey(String resultFlow);

    int updateByExampleSelective(@Param("record") TeachingActivityResult record, @Param("example") TeachingActivityResultExample example);

    int updateByExample(@Param("record") TeachingActivityResult record, @Param("example") TeachingActivityResultExample example);

    int updateByPrimaryKeySelective(TeachingActivityResult record);

    int updateByPrimaryKey(TeachingActivityResult record);
}