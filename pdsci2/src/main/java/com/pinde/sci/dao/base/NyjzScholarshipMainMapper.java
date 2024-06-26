package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NyjzScholarshipMain;
import com.pinde.sci.model.mo.NyjzScholarshipMainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NyjzScholarshipMainMapper {
    int countByExample(NyjzScholarshipMainExample example);

    int deleteByExample(NyjzScholarshipMainExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NyjzScholarshipMain record);

    int insertSelective(NyjzScholarshipMain record);

    List<NyjzScholarshipMain> selectByExampleWithBLOBs(NyjzScholarshipMainExample example);

    List<NyjzScholarshipMain> selectByExample(NyjzScholarshipMainExample example);

    NyjzScholarshipMain selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NyjzScholarshipMain record, @Param("example") NyjzScholarshipMainExample example);

    int updateByExampleWithBLOBs(@Param("record") NyjzScholarshipMain record, @Param("example") NyjzScholarshipMainExample example);

    int updateByExample(@Param("record") NyjzScholarshipMain record, @Param("example") NyjzScholarshipMainExample example);

    int updateByPrimaryKeySelective(NyjzScholarshipMain record);

    int updateByPrimaryKeyWithBLOBs(NyjzScholarshipMain record);

    int updateByPrimaryKey(NyjzScholarshipMain record);
}