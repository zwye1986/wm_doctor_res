package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FstuSatAuthor;
import com.pinde.sci.model.mo.FstuSatAuthorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FstuSatAuthorMapper {
    int countByExample(FstuSatAuthorExample example);

    int deleteByExample(FstuSatAuthorExample example);

    int deleteByPrimaryKey(String authorFlow);

    int insert(FstuSatAuthor record);

    int insertSelective(FstuSatAuthor record);

    List<FstuSatAuthor> selectByExample(FstuSatAuthorExample example);

    FstuSatAuthor selectByPrimaryKey(String authorFlow);

    int updateByExampleSelective(@Param("record") FstuSatAuthor record, @Param("example") FstuSatAuthorExample example);

    int updateByExample(@Param("record") FstuSatAuthor record, @Param("example") FstuSatAuthorExample example);

    int updateByPrimaryKeySelective(FstuSatAuthor record);

    int updateByPrimaryKey(FstuSatAuthor record);
}