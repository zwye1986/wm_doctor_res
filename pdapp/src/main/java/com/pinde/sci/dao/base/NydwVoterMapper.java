package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NydwVoter;
import com.pinde.sci.model.mo.NydwVoterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NydwVoterMapper {
    int countByExample(NydwVoterExample example);

    int deleteByExample(NydwVoterExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NydwVoter record);

    int insertSelective(NydwVoter record);

    List<NydwVoter> selectByExample(NydwVoterExample example);

    NydwVoter selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NydwVoter record, @Param("example") NydwVoterExample example);

    int updateByExample(@Param("record") NydwVoter record, @Param("example") NydwVoterExample example);

    int updateByPrimaryKeySelective(NydwVoter record);

    int updateByPrimaryKey(NydwVoter record);
}