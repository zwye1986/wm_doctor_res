package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.VerificationCodeRecord;
import com.pinde.sci.model.mo.VerificationCodeRecordExample;
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