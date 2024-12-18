package com.pinde.sci.dao.base;

import com.pinde.core.model.ResDiscipleRecordInfo;
import com.pinde.core.model.ResDiscipleRecordInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDiscipleRecordInfoMapper {
    int countByExample(ResDiscipleRecordInfoExample example);

    int deleteByExample(ResDiscipleRecordInfoExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResDiscipleRecordInfo record);

    int insertSelective(ResDiscipleRecordInfo record);

    List<ResDiscipleRecordInfo> selectByExample(ResDiscipleRecordInfoExample example);

    ResDiscipleRecordInfo selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResDiscipleRecordInfo record, @Param("example") ResDiscipleRecordInfoExample example);

    int updateByExample(@Param("record") ResDiscipleRecordInfo record, @Param("example") ResDiscipleRecordInfoExample example);

    int updateByPrimaryKeySelective(ResDiscipleRecordInfo record);

    int updateByPrimaryKey(ResDiscipleRecordInfo record);
}