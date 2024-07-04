package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NywjAnswerDetail;
import com.pinde.sci.model.mo.NywjAnswerDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NywjAnswerDetailMapper {
    int countByExample(NywjAnswerDetailExample example);

    int deleteByExample(NywjAnswerDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NywjAnswerDetail record);

    int insertSelective(NywjAnswerDetail record);

    List<NywjAnswerDetail> selectByExample(NywjAnswerDetailExample example);

    NywjAnswerDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NywjAnswerDetail record, @Param("example") NywjAnswerDetailExample example);

    int updateByExample(@Param("record") NywjAnswerDetail record, @Param("example") NywjAnswerDetailExample example);

    int updateByPrimaryKeySelective(NywjAnswerDetail record);

    int updateByPrimaryKey(NywjAnswerDetail record);
}