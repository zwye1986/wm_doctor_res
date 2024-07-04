package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NydwTopicDetail;
import com.pinde.sci.model.mo.NydwTopicDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NydwTopicDetailMapper {
    int countByExample(NydwTopicDetailExample example);

    int deleteByExample(NydwTopicDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NydwTopicDetail record);

    int insertSelective(NydwTopicDetail record);

    List<NydwTopicDetail> selectByExample(NydwTopicDetailExample example);

    NydwTopicDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NydwTopicDetail record, @Param("example") NydwTopicDetailExample example);

    int updateByExample(@Param("record") NydwTopicDetail record, @Param("example") NydwTopicDetailExample example);

    int updateByPrimaryKeySelective(NydwTopicDetail record);

    int updateByPrimaryKey(NydwTopicDetail record);
}