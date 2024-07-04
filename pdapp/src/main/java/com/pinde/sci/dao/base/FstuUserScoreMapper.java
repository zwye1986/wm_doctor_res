package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FstuUserScore;
import com.pinde.sci.model.mo.FstuUserScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FstuUserScoreMapper {
    int countByExample(FstuUserScoreExample example);

    int deleteByExample(FstuUserScoreExample example);

    int deleteByPrimaryKey(String userFlow);

    int insert(FstuUserScore record);

    int insertSelective(FstuUserScore record);

    List<FstuUserScore> selectByExample(FstuUserScoreExample example);

    FstuUserScore selectByPrimaryKey(String userFlow);

    int updateByExampleSelective(@Param("record") FstuUserScore record, @Param("example") FstuUserScoreExample example);

    int updateByExample(@Param("record") FstuUserScore record, @Param("example") FstuUserScoreExample example);

    int updateByPrimaryKeySelective(FstuUserScore record);

    int updateByPrimaryKey(FstuUserScore record);
}