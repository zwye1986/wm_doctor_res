package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmAchTopic;
import com.pinde.sci.model.mo.SrmAchTopicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmAchTopicMapper {
    int countByExample(SrmAchTopicExample example);

    int deleteByExample(SrmAchTopicExample example);

    int deleteByPrimaryKey(String topicFlow);

    int insert(SrmAchTopic record);

    int insertSelective(SrmAchTopic record);

    List<SrmAchTopic> selectByExample(SrmAchTopicExample example);

    SrmAchTopic selectByPrimaryKey(String topicFlow);

    int updateByExampleSelective(@Param("record") SrmAchTopic record, @Param("example") SrmAchTopicExample example);

    int updateByExample(@Param("record") SrmAchTopic record, @Param("example") SrmAchTopicExample example);

    int updateByPrimaryKeySelective(SrmAchTopic record);

    int updateByPrimaryKey(SrmAchTopic record);
}