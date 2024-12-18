package com.pinde.core.common.sci.dao;

import com.pinde.core.model.VerificationCodeRecord;
import com.pinde.core.model.VerificationCodeRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VerificationCodeRecordMapper {
    int countByExample(VerificationCodeRecordExample example);

    int deleteByExample(VerificationCodeRecordExample example);

    int insert(VerificationCodeRecord record);

    int insertSelective(VerificationCodeRecord record);

    List<VerificationCodeRecord> selectByExample(VerificationCodeRecordExample example);

    int updateByExampleSelective(@Param("record") VerificationCodeRecord record, @Param("example") VerificationCodeRecordExample example);

    int updateByExample(@Param("record") VerificationCodeRecord record, @Param("example") VerificationCodeRecordExample example);

    int update(@Param("record") VerificationCodeRecord record);

    int countByPhone(@Param("userPhone")String userPhone);

}