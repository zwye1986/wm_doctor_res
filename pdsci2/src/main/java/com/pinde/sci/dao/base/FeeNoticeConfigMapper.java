package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FeeNoticeConfig;
import com.pinde.sci.model.mo.FeeNoticeConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FeeNoticeConfigMapper {
    int countByExample(FeeNoticeConfigExample example);

    int deleteByExample(FeeNoticeConfigExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(FeeNoticeConfig record);

    int insertSelective(FeeNoticeConfig record);

    List<FeeNoticeConfig> selectByExample(FeeNoticeConfigExample example);

    FeeNoticeConfig selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") FeeNoticeConfig record, @Param("example") FeeNoticeConfigExample example);

    int updateByExample(@Param("record") FeeNoticeConfig record, @Param("example") FeeNoticeConfigExample example);

    int updateByPrimaryKeySelective(FeeNoticeConfig record);

    int updateByPrimaryKey(FeeNoticeConfig record);
}