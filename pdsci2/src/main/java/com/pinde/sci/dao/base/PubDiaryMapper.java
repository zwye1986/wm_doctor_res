package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubDiary;
import com.pinde.sci.model.mo.PubDiaryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubDiaryMapper {
    int countByExample(PubDiaryExample example);

    int deleteByExample(PubDiaryExample example);

    int deleteByPrimaryKey(String diaryFlow);

    int insert(PubDiary record);

    int insertSelective(PubDiary record);

    List<PubDiary> selectByExample(PubDiaryExample example);

    PubDiary selectByPrimaryKey(String diaryFlow);

    int updateByExampleSelective(@Param("record") PubDiary record, @Param("example") PubDiaryExample example);

    int updateByExample(@Param("record") PubDiary record, @Param("example") PubDiaryExample example);

    int updateByPrimaryKeySelective(PubDiary record);

    int updateByPrimaryKey(PubDiary record);
}