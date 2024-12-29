package com.pinde.core.common.sci.dao;

import com.pinde.core.model.PubMeetingFile;
import com.pinde.core.model.PubMeetingFileExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubMeetingFileMapper {
    int countByExample(PubMeetingFileExample example);

    int deleteByExample(PubMeetingFileExample example);

    int deleteByPrimaryKey(String fileFlow);

    int insert(PubMeetingFile record);

    int insertSelective(PubMeetingFile record);

    List<PubMeetingFile> selectByExample(PubMeetingFileExample example);

    PubMeetingFile selectByPrimaryKey(String fileFlow);

    int updateByExampleSelective(@Param("record") PubMeetingFile record, @Param("example") PubMeetingFileExample example);

    int updateByExample(@Param("record") PubMeetingFile record, @Param("example") PubMeetingFileExample example);

    int updateByPrimaryKeySelective(PubMeetingFile record);

    int updateByPrimaryKey(PubMeetingFile record);
}