package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmAchSat;
import com.pinde.sci.model.mo.SrmAchSatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmAchSatMapper {
    int countByExample(SrmAchSatExample example);

    int deleteByExample(SrmAchSatExample example);

    int deleteByPrimaryKey(String satFlow);

    int insert(SrmAchSat record);

    int insertSelective(SrmAchSat record);

    List<SrmAchSat> selectByExample(SrmAchSatExample example);

    SrmAchSat selectByPrimaryKey(String satFlow);

    int updateByExampleSelective(@Param("record") SrmAchSat record, @Param("example") SrmAchSatExample example);

    int updateByExample(@Param("record") SrmAchSat record, @Param("example") SrmAchSatExample example);

    int updateByPrimaryKeySelective(SrmAchSat record);

    int updateByPrimaryKey(SrmAchSat record);
}