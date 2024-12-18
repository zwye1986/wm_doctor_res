package com.pinde.sci.dao.base;

import com.pinde.core.model.ResArchiveSequence;
import com.pinde.core.model.ResArchiveSequenceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResArchiveSequenceMapper {
    int countByExample(ResArchiveSequenceExample example);

    int deleteByExample(ResArchiveSequenceExample example);

    int deleteByPrimaryKey(String archiveFlow);

    int insert(ResArchiveSequence record);

    int insertSelective(ResArchiveSequence record);

    List<ResArchiveSequence> selectByExample(ResArchiveSequenceExample example);

    ResArchiveSequence selectByPrimaryKey(String archiveFlow);

    int updateByExampleSelective(@Param("record") ResArchiveSequence record, @Param("example") ResArchiveSequenceExample example);

    int updateByExample(@Param("record") ResArchiveSequence record, @Param("example") ResArchiveSequenceExample example);

    int updateByPrimaryKeySelective(ResArchiveSequence record);

    int updateByPrimaryKey(ResArchiveSequence record);
}