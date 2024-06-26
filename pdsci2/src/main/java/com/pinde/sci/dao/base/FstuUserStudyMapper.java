package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FstuUserStudy;
import com.pinde.sci.model.mo.FstuUserStudyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FstuUserStudyMapper {
    int countByExample(FstuUserStudyExample example);

    int deleteByExample(FstuUserStudyExample example);

    int deleteByPrimaryKey(String userFlow);

    int insert(FstuUserStudy record);

    int insertSelective(FstuUserStudy record);

    List<FstuUserStudy> selectByExample(FstuUserStudyExample example);

    FstuUserStudy selectByPrimaryKey(String userFlow);

    int updateByExampleSelective(@Param("record") FstuUserStudy record, @Param("example") FstuUserStudyExample example);

    int updateByExample(@Param("record") FstuUserStudy record, @Param("example") FstuUserStudyExample example);

    int updateByPrimaryKeySelective(FstuUserStudy record);

    int updateByPrimaryKey(FstuUserStudy record);
}