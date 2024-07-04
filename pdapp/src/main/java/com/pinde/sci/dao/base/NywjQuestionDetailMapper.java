package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NywjQuestionDetail;
import com.pinde.sci.model.mo.NywjQuestionDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NywjQuestionDetailMapper {
    int countByExample(NywjQuestionDetailExample example);

    int deleteByExample(NywjQuestionDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NywjQuestionDetail record);

    int insertSelective(NywjQuestionDetail record);

    List<NywjQuestionDetail> selectByExample(NywjQuestionDetailExample example);

    NywjQuestionDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NywjQuestionDetail record, @Param("example") NywjQuestionDetailExample example);

    int updateByExample(@Param("record") NywjQuestionDetail record, @Param("example") NywjQuestionDetailExample example);

    int updateByPrimaryKeySelective(NywjQuestionDetail record);

    int updateByPrimaryKey(NywjQuestionDetail record);
}