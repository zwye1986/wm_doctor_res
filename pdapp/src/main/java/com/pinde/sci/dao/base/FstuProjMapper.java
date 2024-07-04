package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FstuProj;
import com.pinde.sci.model.mo.FstuProjExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FstuProjMapper {
    int countByExample(FstuProjExample example);

    int deleteByExample(FstuProjExample example);

    int deleteByPrimaryKey(String projFlow);

    int insert(FstuProj record);

    int insertSelective(FstuProj record);

    List<FstuProj> selectByExample(FstuProjExample example);

    FstuProj selectByPrimaryKey(String projFlow);

    int updateByExampleSelective(@Param("record") FstuProj record, @Param("example") FstuProjExample example);

    int updateByExample(@Param("record") FstuProj record, @Param("example") FstuProjExample example);

    int updateByPrimaryKeySelective(FstuProj record);

    int updateByPrimaryKey(FstuProj record);
}