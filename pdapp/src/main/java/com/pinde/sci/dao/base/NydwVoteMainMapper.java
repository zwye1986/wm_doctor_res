package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NydwVoteMain;
import com.pinde.sci.model.mo.NydwVoteMainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NydwVoteMainMapper {
    int countByExample(NydwVoteMainExample example);

    int deleteByExample(NydwVoteMainExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NydwVoteMain record);

    int insertSelective(NydwVoteMain record);

    List<NydwVoteMain> selectByExample(NydwVoteMainExample example);

    NydwVoteMain selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NydwVoteMain record, @Param("example") NydwVoteMainExample example);

    int updateByExample(@Param("record") NydwVoteMain record, @Param("example") NydwVoteMainExample example);

    int updateByPrimaryKeySelective(NydwVoteMain record);

    int updateByPrimaryKey(NydwVoteMain record);
}