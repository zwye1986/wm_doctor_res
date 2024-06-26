package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NydbDefenceApply;
import com.pinde.sci.model.mo.NydbDefenceApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NydbDefenceApplyMapper {
    int countByExample(NydbDefenceApplyExample example);

    int deleteByExample(NydbDefenceApplyExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NydbDefenceApply record);

    int insertSelective(NydbDefenceApply record);

    List<NydbDefenceApply> selectByExampleWithBLOBs(NydbDefenceApplyExample example);

    List<NydbDefenceApply> selectByExample(NydbDefenceApplyExample example);

    NydbDefenceApply selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NydbDefenceApply record, @Param("example") NydbDefenceApplyExample example);

    int updateByExampleWithBLOBs(@Param("record") NydbDefenceApply record, @Param("example") NydbDefenceApplyExample example);

    int updateByExample(@Param("record") NydbDefenceApply record, @Param("example") NydbDefenceApplyExample example);

    int updateByPrimaryKeySelective(NydbDefenceApply record);

    int updateByPrimaryKeyWithBLOBs(NydbDefenceApply record);

    int updateByPrimaryKey(NydbDefenceApply record);
}