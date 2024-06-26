package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResSupervisioSubjectRecords;
import com.pinde.sci.model.mo.ResSupervisioSubjectRecordsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResSupervisioSubjectRecordsMapper {
    int countByExample(ResSupervisioSubjectRecordsExample example);

    int deleteByExample(ResSupervisioSubjectRecordsExample example);

    int insert(ResSupervisioSubjectRecords record);

    int insertSelective(ResSupervisioSubjectRecords record);

    List<ResSupervisioSubjectRecords> selectByExample(ResSupervisioSubjectRecordsExample example);

    int updateByExampleSelective(@Param("record") ResSupervisioSubjectRecords record, @Param("example") ResSupervisioSubjectRecordsExample example);

    int updateByExample(@Param("record") ResSupervisioSubjectRecords record, @Param("example") ResSupervisioSubjectRecordsExample example);
}