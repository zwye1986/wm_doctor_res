package com.pinde.sci.dao.base;

import com.pinde.core.model.PubProjAuthor;
import com.pinde.core.model.PubProjAuthorExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubProjAuthorMapper {
    int countByExample(PubProjAuthorExample example);

    int deleteByExample(PubProjAuthorExample example);

    int deleteByPrimaryKey(String authorFlow);

    int insert(PubProjAuthor record);

    int insertSelective(PubProjAuthor record);

    List<PubProjAuthor> selectByExample(PubProjAuthorExample example);

    PubProjAuthor selectByPrimaryKey(String authorFlow);

    int updateByExampleSelective(@Param("record") PubProjAuthor record, @Param("example") PubProjAuthorExample example);

    int updateByExample(@Param("record") PubProjAuthor record, @Param("example") PubProjAuthorExample example);

    int updateByPrimaryKeySelective(PubProjAuthor record);

    int updateByPrimaryKey(PubProjAuthor record);
}