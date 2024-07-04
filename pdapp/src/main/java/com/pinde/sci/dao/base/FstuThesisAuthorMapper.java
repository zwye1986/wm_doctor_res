package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FstuThesisAuthor;
import com.pinde.sci.model.mo.FstuThesisAuthorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FstuThesisAuthorMapper {
    int countByExample(FstuThesisAuthorExample example);

    int deleteByExample(FstuThesisAuthorExample example);

    int deleteByPrimaryKey(String authorFlow);

    int insert(FstuThesisAuthor record);

    int insertSelective(FstuThesisAuthor record);

    List<FstuThesisAuthor> selectByExample(FstuThesisAuthorExample example);

    FstuThesisAuthor selectByPrimaryKey(String authorFlow);

    int updateByExampleSelective(@Param("record") FstuThesisAuthor record, @Param("example") FstuThesisAuthorExample example);

    int updateByExample(@Param("record") FstuThesisAuthor record, @Param("example") FstuThesisAuthorExample example);

    int updateByPrimaryKeySelective(FstuThesisAuthor record);

    int updateByPrimaryKey(FstuThesisAuthor record);
}