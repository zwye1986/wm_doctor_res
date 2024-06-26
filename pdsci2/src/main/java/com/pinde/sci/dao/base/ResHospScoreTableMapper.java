package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResHospScoreTable;
import com.pinde.sci.model.mo.ResHospScoreTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResHospScoreTableMapper {
    int countByExample(ResHospScoreTableExample example);

    int deleteByExample(ResHospScoreTableExample example);

    int insert(ResHospScoreTable record);

    int insertSelective(ResHospScoreTable record);

    List<ResHospScoreTable> selectByExample(ResHospScoreTableExample example);

    int updateByExampleSelective(@Param("record") ResHospScoreTable record, @Param("example") ResHospScoreTableExample example);

    int updateByExample(@Param("record") ResHospScoreTable record, @Param("example") ResHospScoreTableExample example);
}