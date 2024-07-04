package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResArchiveSequence;
import com.pinde.sci.model.mo.ResArchiveSequenceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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