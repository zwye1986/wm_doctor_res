package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResHospSupervSubject;
import com.pinde.sci.model.mo.ResHospSupervSubjectExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResHospSupervSubjectMapper {
    int countByExample(ResHospSupervSubjectExample example);

    int deleteByExample(ResHospSupervSubjectExample example);

    int insert(ResHospSupervSubject record);

    int insertSelective(ResHospSupervSubject record);

    List<ResHospSupervSubject> selectByExample(ResHospSupervSubjectExample example);

    int updateByExampleSelective(@Param("record") ResHospSupervSubject record, @Param("example") ResHospSupervSubjectExample example);

    int updateByExample(@Param("record") ResHospSupervSubject record, @Param("example") ResHospSupervSubjectExample example);
}