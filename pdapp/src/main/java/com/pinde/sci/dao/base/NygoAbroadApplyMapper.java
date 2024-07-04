package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NygoAbroadApply;
import com.pinde.sci.model.mo.NygoAbroadApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NygoAbroadApplyMapper {
    int countByExample(NygoAbroadApplyExample example);

    int deleteByExample(NygoAbroadApplyExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NygoAbroadApply record);

    int insertSelective(NygoAbroadApply record);

    List<NygoAbroadApply> selectByExampleWithBLOBs(NygoAbroadApplyExample example);

    List<NygoAbroadApply> selectByExample(NygoAbroadApplyExample example);

    NygoAbroadApply selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NygoAbroadApply record, @Param("example") NygoAbroadApplyExample example);

    int updateByExampleWithBLOBs(@Param("record") NygoAbroadApply record, @Param("example") NygoAbroadApplyExample example);

    int updateByExample(@Param("record") NygoAbroadApply record, @Param("example") NygoAbroadApplyExample example);

    int updateByPrimaryKeySelective(NygoAbroadApply record);

    int updateByPrimaryKeyWithBLOBs(NygoAbroadApply record);

    int updateByPrimaryKey(NygoAbroadApply record);
}