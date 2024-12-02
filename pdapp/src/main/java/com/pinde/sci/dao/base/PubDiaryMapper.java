package com.pinde.sci.dao.base;

import com.pinde.core.model.PubDiary;
import com.pinde.core.model.PubDiaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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