package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FstuBookAuthor;
import com.pinde.sci.model.mo.FstuBookAuthorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FstuBookAuthorMapper {
    int countByExample(FstuBookAuthorExample example);

    int deleteByExample(FstuBookAuthorExample example);

    int deleteByPrimaryKey(String authorFlow);

    int insert(FstuBookAuthor record);

    int insertSelective(FstuBookAuthor record);

    List<FstuBookAuthor> selectByExample(FstuBookAuthorExample example);

    FstuBookAuthor selectByPrimaryKey(String authorFlow);

    int updateByExampleSelective(@Param("record") FstuBookAuthor record, @Param("example") FstuBookAuthorExample example);

    int updateByExample(@Param("record") FstuBookAuthor record, @Param("example") FstuBookAuthorExample example);

    int updateByPrimaryKeySelective(FstuBookAuthor record);

    int updateByPrimaryKey(FstuBookAuthor record);
}