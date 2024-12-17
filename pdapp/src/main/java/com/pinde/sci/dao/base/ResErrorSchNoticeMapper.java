package com.pinde.sci.dao.base;

import com.pinde.core.model.ResErrorSchNotice;
import com.pinde.core.model.ResErrorSchNoticeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResErrorSchNoticeMapper {
    int countByExample(ResErrorSchNoticeExample example);

    int deleteByExample(ResErrorSchNoticeExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResErrorSchNotice record);

    int insertSelective(ResErrorSchNotice record);

    List<ResErrorSchNotice> selectByExample(ResErrorSchNoticeExample example);

    ResErrorSchNotice selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResErrorSchNotice record, @Param("example") ResErrorSchNoticeExample example);

    int updateByExample(@Param("record") ResErrorSchNotice record, @Param("example") ResErrorSchNoticeExample example);

    int updateByPrimaryKeySelective(ResErrorSchNotice record);

    int updateByPrimaryKey(ResErrorSchNotice record);
}