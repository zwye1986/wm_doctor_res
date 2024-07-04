package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ScresSchInfo;
import com.pinde.sci.model.mo.ScresSchInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScresSchInfoMapper {
    int countByExample(ScresSchInfoExample example);

    int deleteByExample(ScresSchInfoExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ScresSchInfo record);

    int insertSelective(ScresSchInfo record);

    List<ScresSchInfo> selectByExample(ScresSchInfoExample example);

    ScresSchInfo selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ScresSchInfo record, @Param("example") ScresSchInfoExample example);

    int updateByExample(@Param("record") ScresSchInfo record, @Param("example") ScresSchInfoExample example);

    int updateByPrimaryKeySelective(ScresSchInfo record);

    int updateByPrimaryKey(ScresSchInfo record);
}