package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NydsDoctorTopic;
import com.pinde.sci.model.mo.NydsDoctorTopicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NydsDoctorTopicMapper {
    int countByExample(NydsDoctorTopicExample example);

    int deleteByExample(NydsDoctorTopicExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NydsDoctorTopic record);

    int insertSelective(NydsDoctorTopic record);

    List<NydsDoctorTopic> selectByExample(NydsDoctorTopicExample example);

    NydsDoctorTopic selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NydsDoctorTopic record, @Param("example") NydsDoctorTopicExample example);

    int updateByExample(@Param("record") NydsDoctorTopic record, @Param("example") NydsDoctorTopicExample example);

    int updateByPrimaryKeySelective(NydsDoctorTopic record);

    int updateByPrimaryKey(NydsDoctorTopic record);
}