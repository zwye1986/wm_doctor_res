package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FstuBook;
import com.pinde.sci.model.mo.FstuBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FstuBookMapper {
    int countByExample(FstuBookExample example);

    int deleteByExample(FstuBookExample example);

    int deleteByPrimaryKey(String bookFlow);

    int insert(FstuBook record);

    int insertSelective(FstuBook record);

    List<FstuBook> selectByExample(FstuBookExample example);

    FstuBook selectByPrimaryKey(String bookFlow);

    int updateByExampleSelective(@Param("record") FstuBook record, @Param("example") FstuBookExample example);

    int updateByExample(@Param("record") FstuBook record, @Param("example") FstuBookExample example);

    int updateByPrimaryKeySelective(FstuBook record);

    int updateByPrimaryKey(FstuBook record);
}