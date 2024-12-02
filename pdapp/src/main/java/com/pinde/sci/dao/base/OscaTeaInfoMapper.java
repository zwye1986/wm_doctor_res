package com.pinde.sci.dao.base;

import com.pinde.core.model.OscaTeaInfo;
import com.pinde.core.model.OscaTeaInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OscaTeaInfoMapper {
    int countByExample(OscaTeaInfoExample example);

    int deleteByExample(OscaTeaInfoExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(OscaTeaInfo record);

    int insertSelective(OscaTeaInfo record);

    List<OscaTeaInfo> selectByExample(OscaTeaInfoExample example);

    OscaTeaInfo selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") OscaTeaInfo record, @Param("example") OscaTeaInfoExample example);

    int updateByExample(@Param("record") OscaTeaInfo record, @Param("example") OscaTeaInfoExample example);

    int updateByPrimaryKeySelective(OscaTeaInfo record);

    int updateByPrimaryKey(OscaTeaInfo record);
}