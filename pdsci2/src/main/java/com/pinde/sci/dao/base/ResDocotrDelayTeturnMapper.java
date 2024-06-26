package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResDocotrDelayTeturn;
import com.pinde.sci.model.mo.ResDocotrDelayTeturnExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDocotrDelayTeturnMapper {
    int countByExample(ResDocotrDelayTeturnExample example);

    int deleteByExample(ResDocotrDelayTeturnExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResDocotrDelayTeturn record);

    int insertSelective(ResDocotrDelayTeturn record);

    List<ResDocotrDelayTeturn> selectByExample(ResDocotrDelayTeturnExample example);

    ResDocotrDelayTeturn selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResDocotrDelayTeturn record, @Param("example") ResDocotrDelayTeturnExample example);

    int updateByExample(@Param("record") ResDocotrDelayTeturn record, @Param("example") ResDocotrDelayTeturnExample example);

    int updateByPrimaryKeySelective(ResDocotrDelayTeturn record);

    int updateByPrimaryKey(ResDocotrDelayTeturn record);

    int saveSynDoctorStatus(ResDocotrDelayTeturn record);
}