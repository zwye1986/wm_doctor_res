package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NydwElector;
import com.pinde.sci.model.mo.NydwElectorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NydwElectorMapper {
    int countByExample(NydwElectorExample example);

    int deleteByExample(NydwElectorExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NydwElector record);

    int insertSelective(NydwElector record);

    List<NydwElector> selectByExample(NydwElectorExample example);

    NydwElector selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NydwElector record, @Param("example") NydwElectorExample example);

    int updateByExample(@Param("record") NydwElector record, @Param("example") NydwElectorExample example);

    int updateByPrimaryKeySelective(NydwElector record);

    int updateByPrimaryKey(NydwElector record);
}