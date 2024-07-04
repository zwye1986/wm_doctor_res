package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NydwVoteDetail;
import com.pinde.sci.model.mo.NydwVoteDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NydwVoteDetailMapper {
    int countByExample(NydwVoteDetailExample example);

    int deleteByExample(NydwVoteDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NydwVoteDetail record);

    int insertSelective(NydwVoteDetail record);

    List<NydwVoteDetail> selectByExample(NydwVoteDetailExample example);

    NydwVoteDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NydwVoteDetail record, @Param("example") NydwVoteDetailExample example);

    int updateByExample(@Param("record") NydwVoteDetail record, @Param("example") NydwVoteDetailExample example);

    int updateByPrimaryKeySelective(NydwVoteDetail record);

    int updateByPrimaryKey(NydwVoteDetail record);
}