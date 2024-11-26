package com.pinde.sci.dao.base;

import com.pinde.core.model.VerificationCodeRecord;
import com.pinde.core.model.VerificationCodeRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VerificationCodeRecordMapper {
    int countByExample(VerificationCodeRecordExample example);

    int deleteByExample(VerificationCodeRecordExample example);

    int insert(VerificationCodeRecord record);

    int insertSelective(VerificationCodeRecord record);

    List<VerificationCodeRecord> selectByExample(VerificationCodeRecordExample example);

    int updateByExampleSelective(@Param("record") VerificationCodeRecord record, @Param("example") VerificationCodeRecordExample example);

    int updateByExample(@Param("record") VerificationCodeRecord record, @Param("example") VerificationCodeRecordExample example);
}