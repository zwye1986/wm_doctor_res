package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NyzlAdmissionSubject;
import com.pinde.sci.model.mo.NyzlAdmissionSubjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NyzlAdmissionSubjectMapper {
    int countByExample(NyzlAdmissionSubjectExample example);

    int deleteByExample(NyzlAdmissionSubjectExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NyzlAdmissionSubject record);

    int insertSelective(NyzlAdmissionSubject record);

    List<NyzlAdmissionSubject> selectByExample(NyzlAdmissionSubjectExample example);

    NyzlAdmissionSubject selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NyzlAdmissionSubject record, @Param("example") NyzlAdmissionSubjectExample example);

    int updateByExample(@Param("record") NyzlAdmissionSubject record, @Param("example") NyzlAdmissionSubjectExample example);

    int updateByPrimaryKeySelective(NyzlAdmissionSubject record);

    int updateByPrimaryKey(NyzlAdmissionSubject record);
}