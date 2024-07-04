package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ScresRecuritMoreSpe;
import com.pinde.sci.model.mo.ScresRecuritMoreSpeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScresRecuritMoreSpeMapper {
    int countByExample(ScresRecuritMoreSpeExample example);

    int deleteByExample(ScresRecuritMoreSpeExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ScresRecuritMoreSpe record);

    int insertSelective(ScresRecuritMoreSpe record);

    List<ScresRecuritMoreSpe> selectByExample(ScresRecuritMoreSpeExample example);

    ScresRecuritMoreSpe selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ScresRecuritMoreSpe record, @Param("example") ScresRecuritMoreSpeExample example);

    int updateByExample(@Param("record") ScresRecuritMoreSpe record, @Param("example") ScresRecuritMoreSpeExample example);

    int updateByPrimaryKeySelective(ScresRecuritMoreSpe record);

    int updateByPrimaryKey(ScresRecuritMoreSpe record);
}