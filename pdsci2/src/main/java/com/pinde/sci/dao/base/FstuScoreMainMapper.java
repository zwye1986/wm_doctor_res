package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FstuScoreMain;
import com.pinde.sci.model.mo.FstuScoreMainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FstuScoreMainMapper {
    int countByExample(FstuScoreMainExample example);

    int deleteByExample(FstuScoreMainExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(FstuScoreMain record);

    int insertSelective(FstuScoreMain record);

    List<FstuScoreMain> selectByExample(FstuScoreMainExample example);

    FstuScoreMain selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") FstuScoreMain record, @Param("example") FstuScoreMainExample example);

    int updateByExample(@Param("record") FstuScoreMain record, @Param("example") FstuScoreMainExample example);

    int updateByPrimaryKeySelective(FstuScoreMain record);

    int updateByPrimaryKey(FstuScoreMain record);
}