package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResDiscipleRecordInfo;
import com.pinde.sci.model.mo.ResDiscipleRecordInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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