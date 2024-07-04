package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FstuSat;
import com.pinde.sci.model.mo.FstuSatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FstuSatMapper {
    int countByExample(FstuSatExample example);

    int deleteByExample(FstuSatExample example);

    int deleteByPrimaryKey(String satFlow);

    int insert(FstuSat record);

    int insertSelective(FstuSat record);

    List<FstuSat> selectByExample(FstuSatExample example);

    FstuSat selectByPrimaryKey(String satFlow);

    int updateByExampleSelective(@Param("record") FstuSat record, @Param("example") FstuSatExample example);

    int updateByExample(@Param("record") FstuSat record, @Param("example") FstuSatExample example);

    int updateByPrimaryKeySelective(FstuSat record);

    int updateByPrimaryKey(FstuSat record);
}