package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FstuAchieve;
import com.pinde.sci.model.mo.FstuAchieveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FstuAchieveMapper {
    int countByExample(FstuAchieveExample example);

    int deleteByExample(FstuAchieveExample example);

    int deleteByPrimaryKey(String achieveFlow);

    int insert(FstuAchieve record);

    int insertSelective(FstuAchieve record);

    List<FstuAchieve> selectByExample(FstuAchieveExample example);

    FstuAchieve selectByPrimaryKey(String achieveFlow);

    int updateByExampleSelective(@Param("record") FstuAchieve record, @Param("example") FstuAchieveExample example);

    int updateByExample(@Param("record") FstuAchieve record, @Param("example") FstuAchieveExample example);

    int updateByPrimaryKeySelective(FstuAchieve record);

    int updateByPrimaryKey(FstuAchieve record);
}