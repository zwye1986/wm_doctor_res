package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FstuStudy;
import com.pinde.sci.model.mo.FstuStudyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FstuStudyMapper {
    int countByExample(FstuStudyExample example);

    int deleteByExample(FstuStudyExample example);

    int deleteByPrimaryKey(String studyFlow);

    int insert(FstuStudy record);

    int insertSelective(FstuStudy record);

    List<FstuStudy> selectByExample(FstuStudyExample example);

    FstuStudy selectByPrimaryKey(String studyFlow);

    int updateByExampleSelective(@Param("record") FstuStudy record, @Param("example") FstuStudyExample example);

    int updateByExample(@Param("record") FstuStudy record, @Param("example") FstuStudyExample example);

    int updateByPrimaryKeySelective(FstuStudy record);

    int updateByPrimaryKey(FstuStudy record);
}