package com.pinde.sci.dao.base;

import com.pinde.core.model.ResHospScoreTable;
import com.pinde.core.model.ResHospScoreTableExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResHospScoreTableMapper {
    int countByExample(ResHospScoreTableExample example);

    int deleteByExample(ResHospScoreTableExample example);

    int insert(ResHospScoreTable record);

    int insertSelective(ResHospScoreTable record);

    List<ResHospScoreTable> selectByExample(ResHospScoreTableExample example);

    int updateByExampleSelective(@Param("record") ResHospScoreTable record, @Param("example") ResHospScoreTableExample example);

    int updateByExample(@Param("record") ResHospScoreTable record, @Param("example") ResHospScoreTableExample example);
}