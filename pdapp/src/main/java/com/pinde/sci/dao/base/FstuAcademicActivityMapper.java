package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FstuAcademicActivity;
import com.pinde.sci.model.mo.FstuAcademicActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FstuAcademicActivityMapper {
    int countByExample(FstuAcademicActivityExample example);

    int deleteByExample(FstuAcademicActivityExample example);

    int deleteByPrimaryKey(String academicFlow);

    int insert(FstuAcademicActivity record);

    int insertSelective(FstuAcademicActivity record);

    List<FstuAcademicActivity> selectByExample(FstuAcademicActivityExample example);

    FstuAcademicActivity selectByPrimaryKey(String academicFlow);

    int updateByExampleSelective(@Param("record") FstuAcademicActivity record, @Param("example") FstuAcademicActivityExample example);

    int updateByExample(@Param("record") FstuAcademicActivity record, @Param("example") FstuAcademicActivityExample example);

    int updateByPrimaryKeySelective(FstuAcademicActivity record);

    int updateByPrimaryKey(FstuAcademicActivity record);
}