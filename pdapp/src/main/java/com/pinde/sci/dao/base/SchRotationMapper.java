package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SchRotation;
import com.pinde.sci.model.mo.SchRotationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchRotationMapper {
    int countByExample(SchRotationExample example);

    int deleteByExample(SchRotationExample example);

    int deleteByPrimaryKey(String rotationFlow);

    int insert(SchRotation record);

    int insertSelective(SchRotation record);

    List<SchRotation> selectByExampleWithBLOBs(SchRotationExample example);

    List<SchRotation> selectByExample(SchRotationExample example);

    SchRotation selectByPrimaryKey(String rotationFlow);

    int updateByExampleSelective(@Param("record") SchRotation record, @Param("example") SchRotationExample example);

    int updateByExampleWithBLOBs(@Param("record") SchRotation record, @Param("example") SchRotationExample example);

    int updateByExample(@Param("record") SchRotation record, @Param("example") SchRotationExample example);

    int updateByPrimaryKeySelective(SchRotation record);

    int updateByPrimaryKeyWithBLOBs(SchRotation record);

    int updateByPrimaryKey(SchRotation record);
}