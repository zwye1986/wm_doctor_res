package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FstuAchieveAuthor;
import com.pinde.sci.model.mo.FstuAchieveAuthorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FstuAchieveAuthorMapper {
    int countByExample(FstuAchieveAuthorExample example);

    int deleteByExample(FstuAchieveAuthorExample example);

    int deleteByPrimaryKey(String authorFlow);

    int insert(FstuAchieveAuthor record);

    int insertSelective(FstuAchieveAuthor record);

    List<FstuAchieveAuthor> selectByExample(FstuAchieveAuthorExample example);

    FstuAchieveAuthor selectByPrimaryKey(String authorFlow);

    int updateByExampleSelective(@Param("record") FstuAchieveAuthor record, @Param("example") FstuAchieveAuthorExample example);

    int updateByExample(@Param("record") FstuAchieveAuthor record, @Param("example") FstuAchieveAuthorExample example);

    int updateByPrimaryKeySelective(FstuAchieveAuthor record);

    int updateByPrimaryKey(FstuAchieveAuthor record);
}