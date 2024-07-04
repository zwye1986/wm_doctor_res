package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FstuPatentAuthor;
import com.pinde.sci.model.mo.FstuPatentAuthorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FstuPatentAuthorMapper {
    int countByExample(FstuPatentAuthorExample example);

    int deleteByExample(FstuPatentAuthorExample example);

    int deleteByPrimaryKey(String authorFlow);

    int insert(FstuPatentAuthor record);

    int insertSelective(FstuPatentAuthor record);

    List<FstuPatentAuthor> selectByExample(FstuPatentAuthorExample example);

    FstuPatentAuthor selectByPrimaryKey(String authorFlow);

    int updateByExampleSelective(@Param("record") FstuPatentAuthor record, @Param("example") FstuPatentAuthorExample example);

    int updateByExample(@Param("record") FstuPatentAuthor record, @Param("example") FstuPatentAuthorExample example);

    int updateByPrimaryKeySelective(FstuPatentAuthor record);

    int updateByPrimaryKey(FstuPatentAuthor record);
}