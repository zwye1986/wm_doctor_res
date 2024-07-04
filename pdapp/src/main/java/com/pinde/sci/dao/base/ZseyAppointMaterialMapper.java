package com.pinde.sci.dao.base;

import java.util.List;
import com.pinde.sci.model.mo.ZseyAppointMaterial;
import com.pinde.sci.model.mo.ZseyAppointMaterialExample;
import org.apache.ibatis.annotations.Param;

public interface ZseyAppointMaterialMapper {
    int countByExample(ZseyAppointMaterialExample example);

    int deleteByExample(ZseyAppointMaterialExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ZseyAppointMaterial record);

    int insertSelective(ZseyAppointMaterial record);

    List<ZseyAppointMaterial> selectByExample(ZseyAppointMaterialExample example);

    ZseyAppointMaterial selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ZseyAppointMaterial record, @Param("example") ZseyAppointMaterialExample example);

    int updateByExample(@Param("record") ZseyAppointMaterial record, @Param("example") ZseyAppointMaterialExample example);

    int updateByPrimaryKeySelective(ZseyAppointMaterial record);

    int updateByPrimaryKey(ZseyAppointMaterial record);
}