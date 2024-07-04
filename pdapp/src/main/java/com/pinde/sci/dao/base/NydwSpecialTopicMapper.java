package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NydwSpecialTopic;
import com.pinde.sci.model.mo.NydwSpecialTopicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NydwSpecialTopicMapper {
    int countByExample(NydwSpecialTopicExample example);

    int deleteByExample(NydwSpecialTopicExample example);

    int deleteByPrimaryKey(String topicFlow);

    int insert(NydwSpecialTopic record);

    int insertSelective(NydwSpecialTopic record);

    List<NydwSpecialTopic> selectByExample(NydwSpecialTopicExample example);

    NydwSpecialTopic selectByPrimaryKey(String topicFlow);

    int updateByExampleSelective(@Param("record") NydwSpecialTopic record, @Param("example") NydwSpecialTopicExample example);

    int updateByExample(@Param("record") NydwSpecialTopic record, @Param("example") NydwSpecialTopicExample example);

    int updateByPrimaryKeySelective(NydwSpecialTopic record);

    int updateByPrimaryKey(NydwSpecialTopic record);
}