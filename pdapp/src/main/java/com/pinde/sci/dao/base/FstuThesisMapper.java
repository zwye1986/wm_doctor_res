package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FstuThesis;
import com.pinde.sci.model.mo.FstuThesisExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FstuThesisMapper {
    int countByExample(FstuThesisExample example);

    int deleteByExample(FstuThesisExample example);

    int deleteByPrimaryKey(String thesisFlow);

    int insert(FstuThesis record);

    int insertSelective(FstuThesis record);

    List<FstuThesis> selectByExample(FstuThesisExample example);

    FstuThesis selectByPrimaryKey(String thesisFlow);

    int updateByExampleSelective(@Param("record") FstuThesis record, @Param("example") FstuThesisExample example);

    int updateByExample(@Param("record") FstuThesis record, @Param("example") FstuThesisExample example);

    int updateByPrimaryKeySelective(FstuThesis record);

    int updateByPrimaryKey(FstuThesis record);
}