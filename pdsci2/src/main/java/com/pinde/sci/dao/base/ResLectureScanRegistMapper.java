package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResLectureScanRegist;
import com.pinde.sci.model.mo.ResLectureScanRegistExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResLectureScanRegistMapper {
    int countByExample(ResLectureScanRegistExample example);

    int deleteByExample(ResLectureScanRegistExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResLectureScanRegist record);

    int insertSelective(ResLectureScanRegist record);

    List<ResLectureScanRegist> selectByExampleWithBLOBs(ResLectureScanRegistExample example);

    List<ResLectureScanRegist> selectByExample(ResLectureScanRegistExample example);

    ResLectureScanRegist selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResLectureScanRegist record, @Param("example") ResLectureScanRegistExample example);

    int updateByExampleWithBLOBs(@Param("record") ResLectureScanRegist record, @Param("example") ResLectureScanRegistExample example);

    int updateByExample(@Param("record") ResLectureScanRegist record, @Param("example") ResLectureScanRegistExample example);

    int updateByPrimaryKeySelective(ResLectureScanRegist record);

    int updateByPrimaryKeyWithBLOBs(ResLectureScanRegist record);

    int updateByPrimaryKey(ResLectureScanRegist record);
}